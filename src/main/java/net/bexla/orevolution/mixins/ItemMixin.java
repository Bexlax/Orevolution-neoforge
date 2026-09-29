package net.bexla.orevolution.mixins;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.content.interfaces.IToolPower;
import net.bexla.orevolution.content.types.ItemPowerRegistry;
import net.bexla.orevolution.init.RegDataComponents;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "getName", at = @At("RETURN"), cancellable = true)
    private void orevolution$tungstenName(ItemStack stack, CallbackInfoReturnable<Component> cir) {
        boolean isReinforced = stack.getOrDefault(RegDataComponents.REINFORCED.get(), false);
        boolean isCoated = stack.getOrDefault(RegDataComponents.COATED.get(), false);

        if (!isReinforced && !isCoated) return;

        Component og = cir.getReturnValue();
        String key = isReinforced ? "item.orevolution.reinforced" : "item.orevolution.coated";

        cir.setReturnValue(Component.translatable(key, og));
    }

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void orevolution$injectInventoryTick(ItemStack stack, Level level, Entity entity, int slotIndex, boolean selectedIndex, CallbackInfo cir) {
        if(stack.getItem() instanceof TieredItem) {
            IToolPower power = ItemPowerRegistry.getPowerForItem(stack);
            if (power.equals(IToolPower.EMPTY)) return;

            power.onInventoryTick(stack, level, entity, slotIndex, selectedIndex);
        }
    }

    @Inject(method = "mineBlock", at = @At("HEAD"), cancellable = true)
    private void orevolution$injectPowerMining(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity, CallbackInfoReturnable<Boolean> cir) {
        if(stack.getItem() instanceof TieredItem) {
            IToolPower power = ItemPowerRegistry.getPowerForItem(stack);
            if(power != IToolPower.EMPTY && power.onUseOverride(stack, level, miningEntity))
                cir.setReturnValue(true);
        }
    }

    @Inject(method = "hurtEnemy", at = @At("HEAD"), cancellable = true)
    private void orevolution$injectPowerAttackEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir) {
        if(stack.getItem() instanceof TieredItem) {
            IToolPower power = ItemPowerRegistry.getPowerForItem(stack);
            if(!power.equals(IToolPower.EMPTY) && power.onUseOverride(stack, attacker.level(), attacker))
                cir.setReturnValue(true);
        }
    }

    @Inject(at = @At("RETURN"), method = "isValidRepairItem", cancellable = true)
    private void isValidRepairItem(ItemStack item, ItemStack repairIngredient, CallbackInfoReturnable<Boolean> cir) {
        if (repairIngredient.is(RegItems.TUNGSTEN_INGOT) && !item.is(OrevolutionTags.Items.TUNGSTEN_REPAIR_BLACKLIST) && OrevolutionConfig.COMMON.tungstenUniversalRepair.get())
            cir.setReturnValue(true);
    }
}
