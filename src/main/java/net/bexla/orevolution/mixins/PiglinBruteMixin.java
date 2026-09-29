package net.bexla.orevolution.mixins;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinBrute.class)
public class PiglinBruteMixin {
    @Inject(method = "populateDefaultEquipmentSlots", at = @At("TAIL"))
    private void orevolution$injectEquipment(RandomSource random, DifficultyInstance difficulty, CallbackInfo ci) {
        PiglinBrute brute = (PiglinBrute)(Object)this;

        if (random.nextFloat() < 0.1F && OrevolutionConfig.COMMON.generateTungstenOre.get()) {
            brute.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(RegItems.TUNGSTEN_SWORD.get()));
            brute.setDropChance(EquipmentSlot.MAINHAND, 0.9F);
        }
    }

    @Inject(method = "wantsToPickUp", at = @At("HEAD"), cancellable = true)
    private void orevolution$injectDesiredItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if(stack.is(RegItems.TUNGSTEN_SWORD.get())) {
            cir.setReturnValue(true);
        }
    }
}
