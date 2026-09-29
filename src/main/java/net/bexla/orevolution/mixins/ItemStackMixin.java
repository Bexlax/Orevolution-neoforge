package net.bexla.orevolution.mixins;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.types.ToolModifiers;
import net.bexla.orevolution.init.RegDataComponents;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.TriState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(method = "isCorrectToolForDrops", at = @At("HEAD"), cancellable = true)
    private void orevolution$injectModifierHarvestCheck(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (!OrevolutionConfig.COMMON.modProgression.get()) return;

        ItemStack stack = (ItemStack)(Object)this;

        TriState result = ToolModifiers.isCorrectForDrops(stack, state);

        if (result != TriState.DEFAULT) {
            cir.setReturnValue(result == TriState.TRUE);
        }
    }

    @Unique
    private int orevolution$getModifiedMaxDamage(ItemStack stack) {
        int durability = stack.getItem().getMaxDamage(stack);

        if (stack.getItem() instanceof TieredItem item) {
            durability = ToolModifiers.getMaxUses(item.getTier(), durability);
        }

        return Math.max(1, (int)Math.round(durability * stack.getOrDefault(RegDataComponents.MAX_DAMAGE_MULTIPLIER, 1.0D)));
    }

    @Inject(method = "getMaxDamage", at = @At("HEAD"), cancellable = true)
    private void orevolution$getMaxDamage(CallbackInfoReturnable<Integer> cir) {
        if (!OrevolutionConfig.COMMON.equipmentDurability.get()) return;

        ItemStack stack = (ItemStack) (Object) this;
        cir.setReturnValue(orevolution$getModifiedMaxDamage(stack));
    }

    @Inject(method = "getBarWidth", at = @At("HEAD"), cancellable = true)
    private void orevolution$getBarWidth(CallbackInfoReturnable<Integer> cir) {
        if (!OrevolutionConfig.COMMON.equipmentDurability.get()) return;

        ItemStack stack = (ItemStack) (Object) this;
        int maxDamage = orevolution$getModifiedMaxDamage(stack);

        cir.setReturnValue(Math.round(13.0F - (float) stack.getDamageValue() * 13.0F / maxDamage));
    }

    @Inject(method = "getBarColor", at = @At("HEAD"), cancellable = true)
    private void orevolution$getBarColor(CallbackInfoReturnable<Integer> cir) {
        if (!OrevolutionConfig.COMMON.equipmentDurability.get()) return;

        ItemStack stack = (ItemStack) (Object) this;
        int maxDamage = orevolution$getModifiedMaxDamage(stack);

        float ratio = (float) Math.max(0, maxDamage - stack.getDamageValue()) / maxDamage;
        cir.setReturnValue(Mth.hsvToRgb(ratio / 3.0F, 1.0F, 1.0F));
    }
}
