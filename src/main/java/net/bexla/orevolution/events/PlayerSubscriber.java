package net.bexla.orevolution.events;

import galena.oreganized.content.item.ElectrumArmorItem;
import galena.oreganized.index.OAttributes;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.data.OrevolutionTiers;
import net.bexla.orevolution.content.types.ToolPowerRegistry;
import net.bexla.orevolution.content.types.interfaces.IToolPower;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.Iterator;

@EventBusSubscriber(modid = Orevolution.MODID)
public class PlayerSubscriber {
    private static final String SOULBOUND_ITEMS = "SoulboundItems";

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onItemAttributes(ItemAttributeModifierEvent event) {
        if(!ModList.get().isLoaded("oreganized")) return;
        if(OrevolutionConfig.MODCOMPAT.kineticDamage.get()) return;

        event.removeAllModifiersFor(OAttributes.KINETIC_DAMAGE);

        if(OrevolutionConfig.MODCOMPAT.speedPerArmorPiece.get()) return;

        event.removeIf(entry -> {
            if(event.getItemStack().getItem() instanceof ElectrumArmorItem) {
                return entry.attribute().equals(Attributes.MOVEMENT_SPEED);
            }
            return false;
        });
    }

    @SubscribeEvent
    public static void onDrops(LivingDropsEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        ListTag stored = new ListTag();

        Level level = player.level();

        Iterator<ItemEntity> iterator = event.getDrops().iterator();

        while (iterator.hasNext()) {
            ItemEntity itemEntity = iterator.next();
            ItemStack stack = itemEntity.getItem();

            if (isSoulbound(stack)) {

                HolderLookup.Provider provider = level.registryAccess();

                CompoundTag itemTag = (CompoundTag) stack.save(provider);

                stored.add(itemTag);

                iterator.remove();
            }
        }

        if (!stored.isEmpty()) {
            player.getPersistentData().put(SOULBOUND_ITEMS, stored);
        }
    }

    @SubscribeEvent
    public static void onClone(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) return;

        CompoundTag oldData = event.getOriginal().getPersistentData();

        if (oldData.contains(SOULBOUND_ITEMS)) {
            event.getEntity().getPersistentData().put(
                    SOULBOUND_ITEMS,
                    oldData.getList(SOULBOUND_ITEMS, Tag.TAG_COMPOUND)
            );
        }
    }

    @SubscribeEvent
    public static void onRespawn(PlayerEvent.PlayerRespawnEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();

        CompoundTag data = player.getPersistentData();

        if (!data.contains(SOULBOUND_ITEMS)) return;

        Level level = player.level();

        ListTag list = data.getList(SOULBOUND_ITEMS, Tag.TAG_COMPOUND);

        for (Tag tag : list) {
            HolderLookup.Provider provider = level.registryAccess();

            ItemStack stack = ItemStack.parseOptional(provider, (CompoundTag) tag);

            if (!player.addItem(stack)) {
                player.drop(stack, false);
            }
        }

        data.remove(SOULBOUND_ITEMS);
    }

    private static boolean isSoulbound(ItemStack stack) {
        return (stack.getItem() instanceof TieredItem tool && tool.getTier() == OrevolutionTiers.ToolTiers.AETHERSTEEL)
                || (stack.getItem() instanceof ArmorItem armor && armor.getMaterial() == OrevolutionTiers.ArmorMats.AETHERSTEEL);
    }

    private static final Component CANT_HARVEST_ORE = Component.translatable("actionbar.orevolution.cant_harvest_ore");

    @SubscribeEvent
    public static void onHarvestCheck(PlayerEvent.HarvestCheck event) {
        Player player = event.getEntity();
        if (player.isCreative() || player.isSpectator()) return;

        if (!(player.getMainHandItem().getItem() instanceof TieredItem tiered)) return;

        BlockState state = event.getTargetBlock();
        Tier tier = tiered.getTier();
        boolean isCorrectTier = player.getMainHandItem().isCorrectToolForDrops(state);

        showCantHarvestWarning(player, isCorrectTier, state, tiered);
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

        showCantHarvestWarning(player, isCorrectTier, state, tiered);

        final float originalSpeed = event.getOriginalSpeed();
        float newSpeed = originalSpeed;

        if (tiered instanceof SwordItem && OrevolutionConfig.COMMON.weaponsPowers.get()) {
            IToolPower power = ToolPowerRegistry.getWeaponPower(tier);
            newSpeed = power.setDestroySpeed(stack, state, originalSpeed);
        } else if (tiered instanceof DiggerItem && OrevolutionConfig.COMMON.toolsPowers.get()) {
            IToolPower power = ToolPowerRegistry.getToolPower(tier);
            newSpeed = power.setDestroySpeed(stack, state, originalSpeed);
        }

        if (OrevolutionConfig.COMMON.safeOreBreaking.get() && !isCorrectTier && state.is(Tags.Blocks.ORES)) {
            newSpeed = 0.0F;
        }

        if (newSpeed != originalSpeed) {
            event.setNewSpeed(newSpeed);
        }
    }

    private static void showCantHarvestWarning(Player player, boolean isCorrectTier, BlockState state, Item item) {
        if (OrevolutionConfig.CLIENT.warnBreak.get() && !isCorrectTier) {
            if (isToolCorrectForBlock(item, state)) {
                player.displayClientMessage(CANT_HARVEST_ORE, true);
            }
        }
    }

    private static boolean isToolCorrectForBlock(Item item, BlockState state) {
        return (state.is(BlockTags.MINEABLE_WITH_PICKAXE) && item instanceof PickaxeItem)
                || (state.is(BlockTags.MINEABLE_WITH_SHOVEL) && item instanceof ShovelItem)
                || (state.is(BlockTags.MINEABLE_WITH_AXE) && item instanceof AxeItem)
                || (state.is(BlockTags.MINEABLE_WITH_HOE) && item instanceof HoeItem)
                || (state.is(BlockTags.SWORD_EFFICIENT) && item instanceof SwordItem);
    }
}
