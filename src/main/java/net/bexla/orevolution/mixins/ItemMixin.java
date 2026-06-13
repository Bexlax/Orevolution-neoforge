package net.bexla.orevolution.mixins;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.types.ToolPowerRegistry;
import net.bexla.orevolution.content.types.interfaces.IToolPower;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(Item.class)
public class ItemMixin {
    private static final Component HARVEST_TIER = Component.translatable("tooltip.orevolution.harvest_tier");

    @Inject(method = "appendHoverText", at = @At("TAIL"))
    private void orevolution$injectPowerTooltip(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag, CallbackInfo ci) {
        if(stack.getItem() instanceof TieredItem tieredItem) {
            Tier tier = tieredItem.getTier();
//
//            String return_string = TierProgressRegistry.isTierSorted(tier) ? TierProgressRegistry.getName(tier).getPath() : isCorrectTierVanilla(tier);
//
//            if(TierProgressRegistry.getAssociatedTierFromSecondary(tier) != null) {
//                return_string = TierProgressRegistry.getName(TierProgressRegistry.getAssociatedTierFromSecondary(tier)).getPath();
//            }
//
//            if ((stack.is(OrevolutionTags.Items.tinProgFollow) && !(stack.is(OrevolutionTags.Items.tinProgExcept))) && !OrevolutionConfig.CLIENT.tinProgTip.get()) {
//                return_string = "tin";
//            }
//            else if ((stack.is(OrevolutionTags.Items.platProgFollow) && !(stack.is(OrevolutionTags.Items.platProgExcept))) && !OrevolutionConfig.CLIENT.platProgTip.get()) {
//                return_string = "platinum";
//            }

            List<Component> tip = new ArrayList<>();

            if (tieredItem instanceof SwordItem && OrevolutionConfig.CLIENT.weaponsPowersTip.get()) {
                IToolPower power = ToolPowerRegistry.getWeaponPower(tier);
                if (power != null) {
                    tip.addAll(power.appendTooltip(stack, context, tooltipComponents, tooltipFlag));
                }
            } else if (tieredItem instanceof DiggerItem && OrevolutionConfig.CLIENT.weaponsPowersTip.get()) {
                IToolPower power = ToolPowerRegistry.getToolPower(tier);
                if (power != null) {
                    tip.addAll(power.appendTooltip(stack, context, tooltipComponents, tooltipFlag));
                }
            }
//
//            if (OrevolutionConfig.CLIENT.harvestTip.get() && !return_string.isEmpty()) {
//                tip.add(HARVEST_TIER);
//                tip.add(Component.literal(" - " + Component.translatable("tiers.orevolution." + return_string.toLowerCase()).getString()).withStyle(ChatFormatting.YELLOW));
//            }

            tooltipComponents.addAll(1, tip);
        }
    }

//    private static String isCorrectTierVanilla(Tier tier)
//    {
//        int i = tier.getLevel();
//        return switch (i) {
//            case 0 -> "wood";
//            case 1 -> "tin";
//            case 2 -> "platinum";
//            case 3 -> "diamond";
//            default -> "netherite";
//        };
//    }

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void orevolution$injectInventoryTick(ItemStack stack, Level level, Entity entity, int slotIndex, boolean selectedIndex, CallbackInfo cir) {
        if(stack.getItem() instanceof TieredItem tieredItem) {

            Tier tier = tieredItem.getTier();
            
            if (tieredItem instanceof SwordItem) {
                if (!OrevolutionConfig.COMMON.weaponsPowers.get()) return;

                IToolPower power = ToolPowerRegistry.getWeaponPower(tier);
                power.onInventoryTick(stack, level, entity, slotIndex, selectedIndex);
            }
            if(stack.getItem() instanceof DiggerItem) {
                if (!OrevolutionConfig.COMMON.toolsPowers.get()) return;

                IToolPower power = ToolPowerRegistry.getToolPower(tier);
                power.onInventoryTick(stack, level, entity, slotIndex, selectedIndex);
            }
        }
    }

    @Inject(method = "mineBlock", at = @At("HEAD"))
    private void orevolution$injectPowerMining(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity, CallbackInfoReturnable<Boolean> cir) {
        if(stack.getItem() instanceof DiggerItem tieredItem) {
            if (!OrevolutionConfig.COMMON.toolsPowers.get()) return;

            IToolPower power = ToolPowerRegistry.getToolPower(tieredItem.getTier());
            if(power != null && power.onUseOverride(stack, level, miningEntity) && cir.isCancellable()) {
                cir.setReturnValue(true);
                cir.cancel();
            }
        }
        else if(stack.getItem() instanceof SwordItem tieredItem) {
            if (!OrevolutionConfig.COMMON.toolsPowers.get()) return;

            IToolPower power = ToolPowerRegistry.getWeaponPower(tieredItem.getTier());
            if(power != null && power.onUseOverride(stack, level, miningEntity) && cir.isCancellable()) {
                cir.setReturnValue(true);
                cir.cancel();
            }
        }
    }

    @Inject(method = "hurtEnemy", at = @At("HEAD"))
    private void orevolution$injectPowerAttackEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir) {
        if(stack.getItem() instanceof DiggerItem tieredItem) {
            if (!OrevolutionConfig.COMMON.toolsPowers.get()) return;

            IToolPower power = ToolPowerRegistry.getToolPower(tieredItem.getTier());
            if(power != null && power.onUseOverride(stack, attacker.level(), attacker) && cir.isCancellable()) {
                cir.setReturnValue(true);
                cir.cancel();
            }
        }
        else if(stack.getItem() instanceof SwordItem tieredItem) {
            if (!OrevolutionConfig.COMMON.toolsPowers.get()) return;

            IToolPower power = ToolPowerRegistry.getWeaponPower(tieredItem.getTier());
            if(power != null && power.onUseOverride(stack, attacker.level(), attacker) && cir.isCancellable()) {
                cir.setReturnValue(true);
                cir.cancel();
            }
        }
    }
}
