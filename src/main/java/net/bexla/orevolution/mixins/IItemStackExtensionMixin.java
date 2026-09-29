package net.bexla.orevolution.mixins;

import net.bexla.orevolution.content.data.utility.OrevolutionUtils;
import net.bexla.orevolution.init.RegDataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.common.extensions.IItemStackExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IItemStackExtension.class)
public interface IItemStackExtensionMixin {

    @Inject(method = "getAttributeModifiers", at = @At("RETURN"), cancellable = true)
    private void orevolution$modifyAttributes(CallbackInfoReturnable<ItemAttributeModifiers> cir) {
        ItemStack stack = (ItemStack) (Object) this;
        ItemAttributeModifiers modifiers = cir.getReturnValue();

        if (stack.has(RegDataComponents.COATED.get())) {
            modifiers = OrevolutionUtils.applyCoating(stack, modifiers);
        }

        cir.setReturnValue(modifiers);
    }
}