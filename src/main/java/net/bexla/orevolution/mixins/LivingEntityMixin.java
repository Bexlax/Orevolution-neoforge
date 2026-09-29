package net.bexla.orevolution.mixins;

import net.bexla.orevolution.init.RegDataComponents;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.totemInHotbar;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @ModifyVariable(
            method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z",
            at = @At("HEAD"),
            argsOnly = true
    )
    private MobEffectInstance orevolution$modifyTotemEffect(MobEffectInstance effect) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (!(entity instanceof Player player)) {
            return effect;
        }

        if (effect.getDuration() <= 47) {
            return effect;
        }

        ItemStack diamond = totemInHotbar(player, RegDataComponents.DIAMOND_TOTEM.get());
        ItemStack lapis = totemInHotbar(player, RegDataComponents.LAPIS_TOTEM.get());

        if (!diamond.isEmpty() && effect.getEffect().value().isBeneficial()) {
            return new MobEffectInstance(
                    effect.getEffect(),
                    effect.getDuration() * 2,
                    effect.getAmplifier(),
                    effect.isAmbient(),
                    effect.isVisible(),
                    effect.showIcon()
            );
        }

        if (!lapis.isEmpty() && effect.getEffect().value().getCategory() == MobEffectCategory.HARMFUL) {
            return new MobEffectInstance(
                    effect.getEffect(),
                    Math.max(1, effect.getDuration() / 2),
                    effect.getAmplifier(),
                    effect.isAmbient(),
                    effect.isVisible(),
                    effect.showIcon()
            );
        }

        return effect;
    }
}