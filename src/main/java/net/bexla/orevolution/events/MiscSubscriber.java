package net.bexla.orevolution.events;

import galena.oreganized.argentum.index.ArgentumAttributes;
import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.electrum.index.ElectrumAttributes;
import galena.oreganized.electrum.index.ElectrumItems;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.data.OrevolutionTiers;
import net.bexla.orevolution.content.types.menu.SteelAnvilScreen;
import net.bexla.orevolution.init.RegDataComponents;
import net.bexla.orevolution.init.RegItems;
import net.bexla.orevolution.init.RegMenus;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Unit;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.event.AnvilUpdateEvent;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.living.LivingShieldBlockEvent;
import net.neoforged.neoforge.event.entity.player.PlayerContainerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Iterator;
import java.util.List;

@EventBusSubscriber(modid = Orevolution.MODID)
public class MiscSubscriber {
    @SubscribeEvent
    public static void onShieldBlock(LivingShieldBlockEvent event) {
        if(!event.getBlocked()) return;

        DamageSource source = event.getDamageSource();
        Entity entity = source.getEntity();

        if(entity != null && !(entity instanceof LivingEntity)) return;

        if(event.getEntity().getUseItem().getOrDefault(RegDataComponents.COATED, false)) {
            entity.hurt(new DamageSource(entity.damageSources().damageTypes.getHolderOrThrow(DamageTypes.PLAYER_ATTACK), event.getEntity()), event.getBlockedDamage() * 0.6f);
        }
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        Inventory inventory = player.getInventory();

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            ItemStack replacement = replaceDeprecatedItem(stack);

            if (replacement != stack) {
                inventory.setItem(i, replacement);
            }
        }
    }

    @SubscribeEvent
    public static void onContainerOpen(PlayerContainerEvent.Open event) {
        AbstractContainerMenu menu = event.getContainer();

        for (Slot slot : menu.slots) {
            ItemStack stack = slot.getItem();

            ItemStack replacement = replaceDeprecatedItem(stack);

            if (replacement != stack) {
                slot.set(replacement);
            }
        }
    }

    private static ItemStack replaceDeprecatedItem(ItemStack stack) {
        boolean reinforce = false;

        if (stack.is(RegItems.R_HELMET.get())) {
            stack = stack.transmuteCopy(Items.NETHERITE_HELMET);
            reinforce = true;
        }
        if (stack.is(RegItems.R_CHESTPLATE.get())) {
            stack = stack.transmuteCopy(Items.NETHERITE_CHESTPLATE);
            reinforce = true;
        }
        if (stack.is(RegItems.R_LEGGINGS.get())) {
            stack = stack.transmuteCopy(Items.NETHERITE_LEGGINGS);
            reinforce = true;
        }
        if (stack.is(RegItems.R_BOOTS.get())) {
            stack = stack.transmuteCopy(Items.NETHERITE_BOOTS);
            reinforce = true;
        }

        if(reinforce) {
            stack.set(RegDataComponents.REINFORCED.get(), true);
            stack.set(RegDataComponents.MAX_DAMAGE_MULTIPLIER.get(), stack.getItem() instanceof ArmorItem? 1.8 : 1.5D);
            stack.set(DataComponents.FIRE_RESISTANT, Unit.INSTANCE);
        }

        return stack;
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(RegMenus.STEEL_ANVIL.get(), SteelAnvilScreen::new);
    }

    private static final String SOULBOUND_ITEMS = "SoulboundItems";

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onItemAttributes(ItemAttributeModifierEvent event) {
        if(!ModList.get().isLoaded("oreganized")) return;

        if(!OrevolutionConfig.MODCOMPAT.kineticDamage.get()) {
            event.removeIf(entry -> {
                List<DeferredItem<Item>> tools = List.of(
                        ElectrumItems.ELECTRUM_SWORD,
                        ElectrumItems.ELECTRUM_PICKAXE,
                        ElectrumItems.ELECTRUM_AXE,
                        ElectrumItems.ELECTRUM_SHOVEL,
                        ElectrumItems.ELECTRUM_HOE
                );
                for(DeferredItem<Item> electrum : tools){
                    if(event.getItemStack().getItem() == electrum.get()) {
                        return entry.attribute().equals(ElectrumAttributes.KINETIC_DAMAGE);
                    }
                }
                return false;
            });
        }

        if(!OrevolutionConfig.MODCOMPAT.speedPerArmorPiece.get()) {
            event.removeIf(entry -> {
                for(DeferredItem<ArmorItem> electrum : ElectrumItems.electrumArmor().toList()){
                    if(event.getItemStack().getItem() == electrum.get()) {
                        return entry.attribute().equals(Attributes.MOVEMENT_SPEED);
                    }
                }
                return false;
            });
        }

        if(!OrevolutionConfig.MODCOMPAT.invincibilityPerArmorPiece.get()) {
            event.removeIf(entry -> {
                for(DeferredItem<ArmorItem> silver : ArgentumItems.silverArmor().toList()){
                    if(event.getItemStack().getItem() == silver.get()) {
                        return entry.attribute().equals(ArgentumAttributes.INVINCIBILITY_FRAMES);
                    }
                }
                return false;
            });
        }
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

    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        if(event.getRight().is(RegItems.TUNGSTEN_INGOT))
            event.setCost(event.getCost());
    }

        /*@SubscribeEvent
    public static void onRenderFog(ViewportEvent.RenderFog event) {
        Camera camera = event.getCamera();

        if (!(camera.getEntity() instanceof Player player))
            return;

        if (camera.getFluidInCamera() != FogType.LAVA)
            return;

        if (!isWearingMyPRECIOUSTungstenArmor(player))
            return;

        MobEffectInstance fireRes = player.getEffect(MobEffects.FIRE_RESISTANCE);
        if (fireRes == null)
            return;

        float progress = Mth.clamp(fireRes.getDuration() / 900.0F, 0.0F, 1.0F);

        float near = Mth.lerp(1.0F - progress, -8.0F, 0.0F);
        float far = Mth.lerp(1.0F - progress, 48.0F, 5.0F);

        event.setNearPlaneDistance(near);
        event.setFarPlaneDistance(far);
        event.setCanceled(true);
    }

    private static boolean isWearingMyPRECIOUSTungstenArmor(Player player) {

        for (ItemStack stack : player.getArmorSlots()) {

            if (!(stack.getItem() instanceof ArmorItem armor))
                return false;

            if (armor.getMaterial() != OrevolutionTiers.ArmorMats.TUNGSTEN)
                return false;
        }

        return true;
    }*/

}
