package net.bexla.orevolution.events;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.types.ToolPowerRegistry;
import net.bexla.orevolution.content.types.interfaces.IToolPower;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TieredItem;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = Orevolution.MODID)
public class OrevolutionToolPowersSubscriber {
    @SubscribeEvent
    public static void onBlockBreak(BlockDropsEvent evt) {
        if(!(evt.getBreaker() instanceof Player player)) return;

        ItemStack stack = player.getMainHandItem();

        if (!(stack.getItem() instanceof TieredItem tieredItem)) return;

        IToolPower power = null;

        if (tieredItem instanceof SwordItem) {
            if (!OrevolutionConfig.COMMON.weaponsPowers.get()) return;

            power = ToolPowerRegistry.getWeaponPower(tieredItem.getTier());
        }
        else if (tieredItem instanceof DiggerItem) {
            if (!OrevolutionConfig.COMMON.toolsPowers.get()) return;

            power = ToolPowerRegistry.getToolPower(tieredItem.getTier());
        }

        if (power != null) {
            evt.setCanceled(power.onDropXPBlock(stack, player.level(), evt.getPos(), player, evt.getState(), evt.getDroppedExperience()));
        }
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent evt) {
        Player player = evt.getPlayer();
        ItemStack stack = player.getMainHandItem();

        if (!(stack.getItem() instanceof TieredItem tieredItem)) return;

        IToolPower power = null;

        if (tieredItem instanceof SwordItem) {
            if (!OrevolutionConfig.COMMON.weaponsPowers.get()) return;

            power = ToolPowerRegistry.getWeaponPower(tieredItem.getTier());
        }
        else if (tieredItem instanceof DiggerItem) {
            if (!OrevolutionConfig.COMMON.toolsPowers.get()) return;

            power = ToolPowerRegistry.getToolPower(tieredItem.getTier());
        }

        if (power != null) {
            evt.setCanceled(power.onMineBlock(stack, player.level(), evt.getPos(), player, evt.getState()));
        }
    }

    @SubscribeEvent
    public static void onLivingDamaged(LivingDamageEvent.Pre evt) {
        DamageSource source = evt.getSource();

        if (!(source.getEntity() instanceof Player player)) return;

        LivingEntity target = evt.getEntity();
        ItemStack stack = player.getMainHandItem();

        if (!(stack.getItem() instanceof TieredItem tieredItem)) return;

        IToolPower power = null;

        if (tieredItem instanceof SwordItem) {
            if (!OrevolutionConfig.COMMON.weaponsPowers.get()) return;

            power = ToolPowerRegistry.getWeaponPower(tieredItem.getTier());
        }
        else if (tieredItem instanceof DiggerItem) {
            if (!OrevolutionConfig.COMMON.toolsPowers.get()) return;

            power = ToolPowerRegistry.getToolPower(tieredItem.getTier());
        }

        if (power != null) {
            float powerVal = power.onHitEntity(stack, target, player, source, evt.getOriginalDamage());
            evt.setNewDamage(powerVal);
        }
    }
}
