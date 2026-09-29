package net.bexla.orevolution.events;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionUtils;
import net.bexla.orevolution.content.interfaces.IToolPower;
import net.bexla.orevolution.content.types.ItemPowerRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.List;

@EventBusSubscriber(modid = Orevolution.MODID)
public class OrevolutionToolPowersSubscriber {
    private static IToolPower getPower(ItemStack stack) {
        if (!(stack.getItem() instanceof TieredItem tieredItem)) return IToolPower.EMPTY;

        return tieredItem instanceof TieredItem ? ItemPowerRegistry.getPowerForItem(stack) : IToolPower.EMPTY;
    }

    @SubscribeEvent
    public static void onBlockDrops(BlockDropsEvent evt) {
        if (!(evt.getBreaker() instanceof Player player)) return;
        if(player.isCreative()) return;

        IToolPower power = getPower(player.getMainHandItem());

        if (power != IToolPower.EMPTY) {
            evt.setCanceled(power.onDropXPBlock(
                    player.getMainHandItem(),
                    player.level(), evt.getPos(), player,
                    evt.getState(), evt.getDroppedExperience()
            ));
        }
    }

    @SubscribeEvent
    public static void onBlockDrops(BlockEvent.BreakEvent evt) {
        Player player = evt.getPlayer();
        if(player.isCreative()) return;

        IToolPower power = getPower(player.getMainHandItem());

        if (power != IToolPower.EMPTY) {
            evt.setCanceled(power.onMineBlock(
                    player.getMainHandItem(), player.level(),
                    evt.getPos(), player, evt.getState()
            ));
        }
    }

    @SubscribeEvent
    public static void onLivingDamaged(LivingDamageEvent.Pre evt) {
        if (!(evt.getSource().getEntity() instanceof Player player)) {
            return;
        }

        LivingEntity target = evt.getEntity();

        IToolPower power = getPower(player.getMainHandItem());

        if (power != IToolPower.EMPTY) {
            evt.setNewDamage(power.onHitEntity(
                    player.getMainHandItem(),
                    target, player, evt.getSource(),
                    evt.getOriginalDamage()
            ));
        }

        OrevolutionUtils.displayDebug(player, "new damage: " + evt.getNewDamage() + ", og damage: " + evt.getOriginalDamage());
    }

    @SubscribeEvent
    public static void onLivingDamaged(CriticalHitEvent evt) {
        if(!(evt.getTarget() instanceof LivingEntity target)) return;

        Player player = evt.getEntity();

        IToolPower power = getPower(player.getMainHandItem());

        if (power != IToolPower.EMPTY) {
            evt.setDamageMultiplier(power.onCriticalHit(
                    player.getMainHandItem(),
                    target, player, evt.getDamageMultiplier(),
                    evt.isCriticalHit()
            ));
        }

        OrevolutionUtils.displayDebug(player, "new crit: " + evt.getDamageMultiplier() + ", vanilla crit: " + evt.getVanillaMultiplier());
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        List<Component> tooltip = event.getToolTip();
        ItemStack stack = event.getItemStack();

        if(!(stack.getItem() instanceof TieredItem)) return;

        IToolPower power = ItemPowerRegistry.getPowerForItem(stack);
        if (power.equals(IToolPower.EMPTY)) return;

        List<Component> tooltipComponents = power.appendTooltip(stack, event.getContext(), tooltip, event.getFlags());

        tooltip.addAll(1, tooltipComponents);

        int index = 1 + tooltipComponents.size() - 1;

        if (index + 1 >= tooltip.size() || !tooltip.get(index + 1).equals(Component.empty())) {
            tooltip.add(index + 1, Component.empty());
        }
    }
}
