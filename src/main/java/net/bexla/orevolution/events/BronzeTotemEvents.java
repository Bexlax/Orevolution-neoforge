package net.bexla.orevolution.events;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionUtils;
import net.bexla.orevolution.content.types.item.BronzeTotemItem;
import net.bexla.orevolution.init.RegDataComponents;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Unit;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingHealEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.totemInHotbar;

@EventBusSubscriber(modid = Orevolution.MODID)
public class BronzeTotemEvents {
    @SubscribeEvent
    public static void onMobEffectAdded(MobEffectEvent.Added event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        MobEffectInstance effect = event.getEffectInstance();

        if (effect.getDuration() <= 47) {
            return;
        }

        ItemStack diamond = totemInHotbar(player, RegDataComponents.DIAMOND_TOTEM.get());
        ItemStack lapis = totemInHotbar(player, RegDataComponents.LAPIS_TOTEM.get());

        if (!diamond.isEmpty() && effect.getEffect().value().isBeneficial()) {
            damageTotem(5, player, diamond, null);
            OrevolutionUtils.displayDebug(player, "effect time increased");
        }

        if (!lapis.isEmpty() && effect.getEffect().value().getCategory() == MobEffectCategory.HARMFUL) {
            damageTotem(5, player, lapis, null);
            OrevolutionUtils.displayDebug(player, "effect time decreased");
        }

    }

    @SubscribeEvent
    public static void onLivingHeal(LivingHealEvent event) {
        if (!(event.getEntity() instanceof Player player))
            return;

        ItemStack totem = totemInHotbar(player, RegDataComponents.EMERALD_TOTEM.get());

        OrevolutionUtils.displayDebug(player, "totem itemstack was empty");

        if (totem.isEmpty()) return;

        float originalAmount = event.getAmount();
        event.setAmount(originalAmount * 2.0F);

        damageTotem(1, player, totem, null);

        OrevolutionUtils.displayDebug(player, "increased heal was applied");
    }

    public static ItemStack damageTotem(int amount, Player player, ItemStack stack, InteractionHand hand) {
        if (stack.isEmpty() || !stack.isDamageableItem()) {
            return stack;
        }

        if (!(player.level() instanceof ServerLevel serverLevel)) {
            return stack;
        }

        if (stack.getDamageValue() + amount >= stack.getMaxDamage()) {
            stack.setDamageValue(0);
            for(DataComponentType<Unit> component : BronzeTotemItem.COMPONENTS) {
                if(stack.has(component))
                    stack.remove(component);
            }
            return stack;
        }

        if(hand == null) {
            stack.hurtAndBreak(
                    amount,
                    serverLevel,
                    player,
                    i -> {
                        Vec3 pos = player.position();

                        serverLevel.playSound(player, player.blockPosition(), i.getBreakingSound(), SoundSource.PLAYERS);
                        serverLevel.sendParticles(
                                new ItemParticleOption(ParticleTypes.ITEM, stack),
                                pos.x, pos.y + 0.5, pos.z,
                                10,
                                0.2, 0.2, 0.2,
                                0.05
                        );
                    }
            );
        }
        else {
            stack.hurtAndBreak(
                    amount,
                    player,
                    Player.getSlotForHand(hand)
            );
        }

        return stack;
    }
}