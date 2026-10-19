package net.bexla.orevolution.mixins;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public class MobMixin {
    @Inject(method = "populateDefaultEquipmentSlots", at = @At("TAIL"))
    private void orevolution$populateAdditionalEquipment(RandomSource random, DifficultyInstance difficulty, CallbackInfo ci) {
        if(!OrevolutionConfig.COMMON.mobEquipment.get()) return;

        Mob mob = (Mob) (Object) this;

        float difficultyMultiplier = difficulty.getSpecialMultiplier();
        if (difficultyMultiplier <= 0.0F) return;

        if (random.nextFloat() < 0.15F * difficultyMultiplier) {
            if(!OrevolutionConfig.COMMON.generateTinOre.get()) return;

            orevolution$equipIfEmpty(mob, random, EquipmentSlot.HEAD, RegItems.TIN_HELMET.get());
            orevolution$equipIfEmpty(mob, random, EquipmentSlot.CHEST, RegItems.TIN_CHESTPLATE.get());
            orevolution$equipIfEmpty(mob, random, EquipmentSlot.LEGS, RegItems.TIN_LEGGINGS.get());
            orevolution$equipIfEmpty(mob, random, EquipmentSlot.FEET, RegItems.TIN_BOOTS.get());
        }

        if (random.nextFloat() < 0.05F * difficultyMultiplier) {
            if(!OrevolutionConfig.COMMON.generatePlatinumOre.get()) return;

            orevolution$equipIfEmpty(mob, random, EquipmentSlot.HEAD, RegItems.PLATINUM_HELMET.get());
            orevolution$equipIfEmpty(mob, random, EquipmentSlot.CHEST, RegItems.PLATINUM_CHESTPLATE.get());
            orevolution$equipIfEmpty(mob, random, EquipmentSlot.LEGS, RegItems.PLATINUM_LEGGINGS.get());
            orevolution$equipIfEmpty(mob, random, EquipmentSlot.FEET, RegItems.PLATINUM_BOOTS.get());
        }
    }

    @Unique
    private static void orevolution$equipIfEmpty(Mob mob, RandomSource random, EquipmentSlot slot, net.minecraft.world.item.Item item) {
        if (!mob.getItemBySlot(slot).isEmpty()) return;
        
        if (random.nextFloat() < 0.85F) {
            mob.setItemSlot(slot, new ItemStack(item));
        }
    }
}
