package net.bexla.orevolution.mixins;

import net.bexla.orevolution.events.BronzeTotemEvents;
import net.bexla.orevolution.init.RegDataComponents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.totemInHotbar;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(method = "blockUsingShield", at = @At("HEAD"), cancellable = true)
    private void orevolution$injectBronzeShieldBreaking(LivingEntity entity, CallbackInfo ci) {
        Player player = (Player)(Object)this;

        if(player.getUseItem().getOrDefault(RegDataComponents.REINFORCED.get(), false) && entity.canDisableShield()) {
            entity.knockback(0.7, player.getX() - entity.getX(), player.getZ() - entity.getZ());
            player.getUseItem().setDamageValue(player.getUseItem().getDamageValue() + 25);
            entity.getMainHandItem().setDamageValue(entity.getMainHandItem().getDamageValue() + 5);
            ci.cancel();
        }
    }

    @Unique
    private float orevolution$preventedExhaustion;

    @Inject(method = "causeFoodExhaustion", at = @At("HEAD"), cancellable = true)
    private void orevolution$quartzTotem(float exhaustion, CallbackInfo ci) {
        Player player = (Player) (Object) this;

        ItemStack totem = totemInHotbar(player, RegDataComponents.QUARTZ_TOTEM.get());

        if (totem.isEmpty()) {
            return;
        }

        orevolution$addPreventedExhaustion(player, totem, exhaustion);

        ci.cancel();
    }

    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;setSaturation(F)V"))
    private void orevolution$quartzTotemSaturation(FoodData foodData, float saturation) {
        Player player = (Player) (Object) this;

        ItemStack totem = totemInHotbar(player, RegDataComponents.QUARTZ_TOTEM.get());

        if (totem.isEmpty()) {
            foodData.setSaturation(saturation);
            return;
        }

        float increase = saturation - foodData.getSaturationLevel();

        if (increase > 0.0F) {
            orevolution$addPreventedExhaustion(player, totem, increase);
        }

        foodData.setSaturation(saturation);
    }

    @Redirect(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;setFoodLevel(I)V"))
    private void orevolution$quartzTotemFood(FoodData foodData, int foodLevel) {
        Player player = (Player) (Object) this;

        ItemStack totem = totemInHotbar(player, RegDataComponents.QUARTZ_TOTEM.get());

        if (totem.isEmpty()) {
            foodData.setFoodLevel(foodLevel);
            return;
        }

        int increase = foodLevel - foodData.getFoodLevel();

        if (increase > 0) {
            orevolution$addPreventedExhaustion(player, totem, increase);
        }

        foodData.setFoodLevel(foodLevel);
    }

    @Unique
    private void orevolution$addPreventedExhaustion(Player player, ItemStack totem, float exhaustion) {
        if (player.level().getDifficulty() == Difficulty.PEACEFUL) {
            orevolution$preventedExhaustion = 0;
            return;
        }

        orevolution$preventedExhaustion += exhaustion;

        int durability = (int) (orevolution$preventedExhaustion / 4.0F);

        if (durability <= 0) {
            return;
        }

        orevolution$preventedExhaustion %= 4.0F;

        BronzeTotemEvents.damageTotem(durability, player, totem, null);
    }
}
