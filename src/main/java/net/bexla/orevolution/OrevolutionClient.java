package net.bexla.orevolution;

import com.mojang.blaze3d.vertex.PoseStack;
import net.bexla.orevolution.content.data.BiomeFogModifier;
import net.bexla.orevolution.content.data.powers.tools.ToolExpandHarvestArea;
import net.bexla.orevolution.content.interfaces.IToolPower;
import net.bexla.orevolution.content.renders.FieryArrowRender;
import net.bexla.orevolution.content.renders.RdxRender;
import net.bexla.orevolution.content.types.FogManager;
import net.bexla.orevolution.content.types.ItemPowerRegistry;
import net.bexla.orevolution.content.types.item.BronzeTotemItem;
import net.bexla.orevolution.content.types.item.GeoScannerItem;
import net.bexla.orevolution.content.types.item.MotionDetectorItem;
import net.bexla.orevolution.content.types.particles.YellowSmokeParticle;
import net.bexla.orevolution.init.RegDataComponents;
import net.bexla.orevolution.init.RegEntityTypes;
import net.bexla.orevolution.init.RegItems;
import net.bexla.orevolution.init.RegParticleTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SmokeParticle;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import org.joml.Matrix4f;

import java.util.Iterator;
import java.util.List;

import static net.bexla.orevolution.Orevolution.lc;

@Mod(value = Orevolution.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Orevolution.MODID, value = Dist.CLIENT)
public class OrevolutionClient {
    public OrevolutionClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    private static final Component DURABILITY_MULTIPLIER = Component.translatable("item.durability_multiplier");

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        List<Component> tooltip = event.getToolTip();

        if (stack.has(RegDataComponents.MAX_DAMAGE_MULTIPLIER.get()) && OrevolutionConfig.CLIENT.displayDurabilityModifier.get()) {
            int modifierIndex = findInsertionPoint(tooltip);

            if (modifierIndex < 0) modifierIndex = 1;

            double multiplier = stack.getOrDefault(RegDataComponents.MAX_DAMAGE_MULTIPLIER.get(), 1.0);

            double change = (multiplier - 1.0) * 100.0;
            int percent = (int) Math.round(Math.abs(change));

            String key;
            ChatFormatting color;

            if (change > 0) {
                key = "attribute.modifier.plus.2";
                color = ChatFormatting.DARK_GREEN;
            } else if (change < 0) {
                key = "attribute.modifier.take.2";
                color = ChatFormatting.RED;
            } else {
                key = "attribute.modifier.equals.2";
                color = ChatFormatting.GRAY;
            }

            tooltip.add(modifierIndex, CommonComponents.space());
            tooltip.add(modifierIndex + 1, Component.translatable(key, percent, DURABILITY_MULTIPLIER).withStyle(color));
        }

        if (stack.has(RegDataComponents.WIP)) {
            tooltip.add(1, Component.literal("May change or be removed in the future").withStyle(ChatFormatting.AQUA));
            tooltip.add(1, Component.literal("Work In Progress").withStyle(ChatFormatting.AQUA));
        }
    }

    private static int findInsertionPoint(List<Component> tooltip) {
        for (int i = 0; i < tooltip.size(); i++) {
            if (tooltip.get(i).equals(Component.empty())) {
                return i;
            }
        }

        return -1;
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register(
                (stack, i) -> i > 0 ? -1 : DyedItemColor.getOrDefault(stack, -6265536),
                RegItems.BRONZE_HELMET.get(), RegItems.BRONZE_CHESTPLATE.get(), RegItems.BRONZE_LEGGINGS.get(), RegItems.BRONZE_BOOTS.get()
        );
    }

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        FogManager.register(new BiomeFogModifier());
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Pre event) {
        FogManager.onClientTick();
    }

    @SubscribeEvent
    public static void onWorldRenderLast(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_CUTOUT_BLOCKS) {
            return;
        }

        Minecraft instance = Minecraft.getInstance();
        renderBlockOutline(
                instance.level,
                event.getCamera(),
                event.getPartialTick(),
                event.getPoseStack(),
                instance.renderBuffers().bufferSource(),
                instance.gameRenderer,
                event.getProjectionMatrix(),
                instance.gameRenderer.lightTexture(),
                instance.levelRenderer
        );
    }

    // Taken and modified from JustHammers mod. All credits go to ErrorMikey and their (i assume they have one, genuinely got no idea) team.
    // https://github.com/nanite/JustHammers/blob/mc/1.21.1/common/src/main/java/pro/mikey/justhammers/client/SelectionOutlineRender.java#L25
    public static void renderBlockOutline(ClientLevel world, Camera camera, DeltaTracker v, PoseStack poseStack, MultiBufferSource consumers, GameRenderer gameRenderer, Matrix4f matrix4f, LightTexture lightTexture, LevelRenderer levelRenderer) {
        // Get the player
        if (world == null) {
            return;
        }

        if(!OrevolutionConfig.CLIENT.steel_outline.get()) return;

        Minecraft mc = Minecraft.getInstance();

        if(mc.options.hideGui) return;

        Player player = mc.player;
        Level level = mc.level;

        if (player == null) return;

        if(player.isCreative() || player.isSpectator()) return;

        // Get the player's held item
        var heldItem = player.getMainHandItem();
        var offHandItem = player.getOffhandItem();
        if (heldItem.isEmpty() && offHandItem.isEmpty()) return;
        if (!(heldItem.getItem() instanceof TieredItem tieredItem)) return;

        IToolPower tpower = ItemPowerRegistry.getPowerForItem(heldItem);

        // check if item's power should be affected by this visual change
        if (!(tpower instanceof ToolExpandHarvestArea)) return;

        var blockHitResult = mc.hitResult;
        if (blockHitResult == null || blockHitResult.getType() != HitResult.Type.BLOCK) {
            return;
        }

        // Get the block's position
        var blockPos = ((BlockHitResult) blockHitResult).getBlockPos();
        var direction = ((BlockHitResult) blockHitResult).getDirection();

        // Get the block at the pos
        var block = world.getBlockState(blockPos);
        var incorrectFor = tieredItem.getTier().getIncorrectBlocksForDrops();
        if (block.is(incorrectFor)) return;
        if (!heldItem.isCorrectToolForDrops(block)) return;

        var boundingBox = getAreaOfEffect(blockPos, direction);

        poseStack.pushPose();
        poseStack.translate(-camera.getPosition().x(), -camera.getPosition().y(), -camera.getPosition().z());

        Iterator<BlockPos> blockPosStream = BlockPos.betweenClosedStream(boundingBox).iterator();
        while (blockPosStream.hasNext()) {
            BlockPos pos = blockPosStream.next();
            if (pos.equals(blockPos)) continue;

            BlockState blockState = world.getBlockState(pos);

            if (blockState.is(incorrectFor)) continue;
            if (!heldItem.isCorrectToolForDrops(blockState)) continue;
            if (blockState.getDestroySpeed(level, pos) > block.getDestroySpeed(level, pos)) continue;

            FluidState fluidState = blockState.getFluidState();
            if (blockState.isAir() || (!fluidState.isEmpty())) {
                continue;
            }

            VoxelShape renderShape = blockState.getShape(world, pos);

            poseStack.pushPose();
            poseStack.translate(pos.getX(), pos.getY(), pos.getZ());

            LevelRenderer.renderVoxelShape(poseStack, consumers.getBuffer(RenderType.lines()), renderShape, 0, 0, 0, 0.0F, 0.0F, 0.0F, 0.35F, true);
            poseStack.popPose();
        }

        poseStack.popPose();
    }

    // Also taken and modified from the JustHammers mod. All credits go to ErrorMikey and their team.
    // https://github.com/nanite/JustHammers/blob/mc/1.21.1/common/src/main/java/pro/mikey/justhammers/HammerItem.java#L258
    public static BoundingBox getAreaOfEffect(BlockPos blockPos, Direction direction) {
        var size = (3 / 2);
        var offset = 0;

        return switch (direction) {
            case DOWN, UP -> new BoundingBox(blockPos.getX() - size, blockPos.getY(), blockPos.getZ() - size, blockPos.getX() + size, blockPos.getY(), blockPos.getZ() + size);
            case NORTH, SOUTH -> new BoundingBox(blockPos.getX() - size, blockPos.getY() - size + offset, blockPos.getZ(), blockPos.getX() + size, blockPos.getY() + size + offset, blockPos.getZ());
            case WEST, EAST -> new BoundingBox(blockPos.getX(), blockPos.getY() - size + offset, blockPos.getZ() - size, blockPos.getX(), blockPos.getY() + size + offset, blockPos.getZ() + size);
        };
    }

    @SubscribeEvent
    public static void setup(FMLClientSetupEvent event) {
        ItemProperties.register(
                RegItems.TIN_SHIELD.get(),
                ResourceLocation.withDefaultNamespace("blocking"),
                (stack, level, user, i) ->
                        user != null && user.isUsingItem() && user.getUseItem() == stack ? 1.0F : 0.0F
        );

        ItemProperties.register(
                RegItems.PLATINUM_SHIELD.get(),
                ResourceLocation.withDefaultNamespace("blocking"),
                (stack, level, user, i) ->
                        user != null && user.isUsingItem() && user.getUseItem() == stack ? 1.0F : 0.0F
        );

        ItemProperties.register(
                RegItems.AETHERSTEEL_SHIELD.get(),
                ResourceLocation.withDefaultNamespace("blocking"),
                (stack, level, user, i) ->
                        user != null && user.isUsingItem() && user.getUseItem() == stack ? 1.0F : 0.0F
        );

        ItemProperties.register(RegItems.LIVINGSTONE_SHIELD.get(),
                ResourceLocation.withDefaultNamespace("blocking"),
                (stack, level, user, i) ->
                        user != null && user.isUsingItem() && user.getUseItem() == stack ? 1.0F : 0.0F
        );

        ItemProperties.register(
                RegItems.VERDITE_SHIELD.get(),
                ResourceLocation.withDefaultNamespace("blocking"),
                (stack, level, user, i) ->
                        user != null && user.isUsingItem() && user.getUseItem() == stack ? 1.0F : 0.0F
        );

        ItemProperties.register(
                RegItems.GEO_SCANNER.get(),
                lc("scan_level"),
                (stack, level, entity, seed) -> GeoScannerItem.getScannerStage(stack)
        );

        ItemProperties.register(
                RegItems.MOTION_DETECTOR.get(),
                lc("motion_state"),
                (stack, level, entity, seed) -> MotionDetectorItem.getMotionState(stack)
        );

        ItemProperties.register(
                RegItems.BRONZE_TOTEM.get(),
                lc("socket_type"),
                (stack, level, entity, seed) -> BronzeTotemItem.getTotemSocket(stack)
        );
    }


    @SubscribeEvent
    public static void registerEntityRender(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(RegEntityTypes.FIERY_ARROW.get(), FieryArrowRender::new);
        event.registerEntityRenderer(RegEntityTypes.PRIMED_RDX.get(), RdxRender::new);
    }

    @SubscribeEvent
    public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(
                RegParticleTypes.YELLOW_SMOKE.get(),
                SmokeParticle.Provider::new
        );
        event.registerSpriteSet(
                RegParticleTypes.GAS.get(),
                YellowSmokeParticle.Provider::new
        );
    }

    @SubscribeEvent
    public static void onAddPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.CLIENT_RESOURCES) {
            registerBuiltinResourcePack(event, "orevolution_style", "Orevolution Styled", PackSource.BUILT_IN, false);
        }
    }

    private static void registerBuiltinResourcePack(AddPackFindersEvent event, String folder, String name, PackSource source, boolean alwaysActive) {
        event.addPackFinders(
                lc("resourcepacks/" + folder),
                PackType.CLIENT_RESOURCES,
                Component.literal(name),
                source,
                alwaysActive,
                Pack.Position.TOP);
    }

}
