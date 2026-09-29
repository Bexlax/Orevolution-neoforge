package net.bexla.orevolution.events;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.data.utility.OrevolutionUtils;
import net.bexla.orevolution.content.interfaces.IToolPower;
import net.bexla.orevolution.content.types.ItemPowerRegistry;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = Orevolution.MODID)
public class BlockSubscriber {
    private static final String INCORRECT_TIER = "actionbar.orevolution.cant_harvest_ore";
    private static final String INCORRECT_TOOL = "actionbar.orevolution.cant_harvest_block";

    @SubscribeEvent
    public static void onHarvestCheck(PlayerEvent.HarvestCheck event) {
        Player player = event.getEntity();
        if (player.isCreative() || player.isSpectator()) return;

        ItemStack heldItem = player.getMainHandItem();
        if (!(heldItem.getItem() instanceof TieredItem)) return;

        BlockState state = event.getTargetBlock();

        if (!state.requiresCorrectToolForDrops()) return;

        TagKey<Block> heldTag = OrevolutionUtils.getToolTag(heldItem);
        TagKey<Block> requiredTag = OrevolutionUtils.getRequiredToolTag(state);

        if (!event.canHarvest()) {
            if (heldTag != null && heldTag.equals(requiredTag)) {
                if (OrevolutionConfig.CLIENT.warnBreak.get()) {
                    player.displayClientMessage(
                            Component.translatable(INCORRECT_TIER, heldItem.getDisplayName()),
                            true
                    );
                }
            } else {
                if (OrevolutionConfig.CLIENT.warnIncorrectToolType.get()) {
                    player.displayClientMessage(
                            Component.translatable(
                                    INCORRECT_TOOL,
                                    requiredTag != null
                                            ? OrevolutionUtils.getToolTypeName(requiredTag)
                                            : Component.translatable("tool.orevolution.unknown_tool")
                            ),
                            true
                    );
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent evt) {
        if(!OrevolutionConfig.COMMON.generateLivingstoneOre.get()) return;

        Player player = evt.getPlayer();
        BlockState state = evt.getState();
        Level level = player.level();
        BlockPos pos = evt.getPos();

        if(level.random.nextFloat() > 0.25) return;

        if(state.getBlock() instanceof CropBlock crop) {
            if(level.getBlockState(pos.below(2)).is(Tags.Blocks.STONES) && crop.getAge(state) >= crop.getMaxAge()) {
                Containers.dropItemStack(
                        level,
                        pos.getX() + 0.5,
                        pos.getY(),
                        pos.getZ() + 0.5,
                        new ItemStack(RegItems.PETRIFIED_SEED.get())
                );
            }
        }
    }

    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        if (player.isCreative() || player.isSpectator()) {
            return;
        }

        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof TieredItem tiered)) {
            return;
        }

        BlockState state = event.getState();
        Tier tier = tiered.getTier();
        boolean isCorrectTier = player.getMainHandItem().isCorrectToolForDrops(state);

        final float originalSpeed = event.getOriginalSpeed();
        float newSpeed;

        IToolPower power = ItemPowerRegistry.getPowerForItem(stack);
        newSpeed = power.setDestroySpeed(stack, state, originalSpeed);

        if (OrevolutionConfig.COMMON.safeOreBreaking.get() && !isCorrectTier && state.is(Tags.Blocks.ORES)) {
            newSpeed = 0.0F;
        }

        if (newSpeed != originalSpeed) {
            event.setNewSpeed(newSpeed);
        }
    }

}
