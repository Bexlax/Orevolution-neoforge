package net.bexla.orevolution.mixins;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Zombie.class)
public class ZombieMixin {
    @Inject(method = "populateDefaultEquipmentSlots", at = @At("TAIL"))
    private void orevolution$injectEquipment(RandomSource random, DifficultyInstance difficulty, CallbackInfo ci) {
        Zombie zombie = (Zombie)(Object)this;

        if (random.nextFloat() < 0.3F) {
            ItemStack stack = zombie.getMainHandItem();
            Item actuallyGoodItems = orevolution$replaceIronTools(stack);
            if (actuallyGoodItems != null) {
                zombie.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(actuallyGoodItems));
            }
        }
    }

    @Unique
    private Item orevolution$replaceIronTools(ItemStack replaceable) {
        if (replaceable.is(Items.IRON_SWORD) && OrevolutionConfig.COMMON.generatePlatOre.get()) return RegItems.PLATINUM_SHOVEL.get();
        if (replaceable.is(Items.IRON_SHOVEL) && OrevolutionConfig.COMMON.generateTinOre.get()) return RegItems.TIN_SWORD.get();

        return null;
    }
}
