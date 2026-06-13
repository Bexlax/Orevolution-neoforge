package net.bexla.orevolution.mixins;

import com.google.common.collect.ImmutableMap;
import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.data.OrevolutionTiers;
import net.bexla.orevolution.content.types.ToolPowerRegistry;
import net.minecraft.util.Mth;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.function.Supplier;
@Mixin(ItemStack.class)

public class ItemstackMixin {
    private static final Map<Tier, Supplier<Integer>> BALANCED_DURABILITIES = ImmutableMap.<Tier, Supplier<Integer>>builder()
            .put(Tiers.IRON, OrevolutionConfig.TOOLSTATS.ironMaxUses)
            .put(Tiers.DIAMOND, OrevolutionConfig.TOOLSTATS.diamondMaxUses)
            .put(Tiers.GOLD, OrevolutionConfig.TOOLSTATS.goldMaxUses)
            .put(Tiers.NETHERITE, OrevolutionConfig.TOOLSTATS.netheriteMaxUses)
            .put(Tiers.STONE, OrevolutionConfig.TOOLSTATS.stoneMaxUses)
            .put(Tiers.WOOD, OrevolutionConfig.TOOLSTATS.woodMaxUses)
            .put(OrevolutionTiers.ToolTiers.TIN, OrevolutionConfig.TOOLSTATS.tinMaxUses)
            .put(OrevolutionTiers.ToolTiers.PLATINUM, OrevolutionConfig.TOOLSTATS.platMaxUses)
            .put(OrevolutionTiers.ToolTiers.AETHERSTEEL, OrevolutionConfig.TOOLSTATS.aetherMaxUses)
            .put(OrevolutionTiers.ToolTiers.STEEL, OrevolutionConfig.TOOLSTATS.steelMaxUses)
            .put(OrevolutionTiers.ToolTiers.LIVINGSTONE, OrevolutionConfig.TOOLSTATS.livingstoneMaxUses)
            .put(OrevolutionTiers.ToolTiers.VERDITE, OrevolutionConfig.TOOLSTATS.verditeMaxUses)
            .build();

    @Inject(method = "getMaxDamage", at = @At("RETURN"), cancellable = true)
    private void orevolution$injectModifierMaxUses(CallbackInfoReturnable<Integer> cir) {
        if(!OrevolutionConfig.COMMON.equipmentDurability.get()) return;

        ItemStack stack = (ItemStack) (Object) this;

        if (!(stack.getItem() instanceof TieredItem tiered)) return;

        int uses = cir.getReturnValue();

        Supplier<Integer> override = BALANCED_DURABILITIES.get(tiered.getTier());
        if (override != null) {
            uses = override.get();
        }

//        if (ModCompat.isModLoaded(ModCompat.oreganized())) {
//            try {
//                Class<?> c = Class.forName("galena.oreganized.index.OItemTiers");
//
//                Tier electrum = (Tier) c.getField("ELECTRUM").get(null);
//
//                if (tiered.getTier() == electrum) {
//                    uses = OrevolutionConfig.MODCOMPAT.electrumMaxUses.get();
//                }
//            } catch (Exception ignored) { }
//        }
//        if (ModCompat.isModLoaded(ModCompat.backport())) {
//            try {
//                Class<?> c = Class.forName("com.github.smallinger.copperagebackport.item.tools.CopperTier");
//
//                Tier copper = (Tier) c.getField("INSTANCE").get(null);
//
//                if (tiered.getTier() == copper) {
//                    uses = OrevolutionConfig.MODCOMPAT.electrumMaxUses.get();
//                }
//            } catch (Exception ignored) { }
//        }

        if (tiered instanceof SwordItem && OrevolutionConfig.COMMON.weaponsPowers.get()) {
            uses = ToolPowerRegistry.getWeaponPower(tiered.getTier()).setMaxUses(stack, uses);
        }
        else if (tiered instanceof DiggerItem && OrevolutionConfig.COMMON.toolsPowers.get()) {
            uses = ToolPowerRegistry.getToolPower(tiered.getTier()).setMaxUses(stack, uses);
        }

        cir.setReturnValue(uses);
    }

    // bar width fix
    @Inject(method = "getBarWidth", at = @At("RETURN"), cancellable = true)
    public void orevolution$injectBarWidth(CallbackInfoReturnable<Integer> cir) {
        ItemStack stack = (ItemStack)(Object)this;

        if (!(stack.getItem() instanceof TieredItem tiered)) return;

        int uses = tiered.getDefaultInstance().getMaxDamage();

        Supplier<Integer> override = BALANCED_DURABILITIES.get(tiered.getTier());
        if (override != null) {
            uses = override.get();
        }

        if (tiered instanceof SwordItem && OrevolutionConfig.COMMON.weaponsPowers.get()) {
            uses = ToolPowerRegistry.getWeaponPower(tiered.getTier()).setMaxUses(stack, uses);
        }
        else if (tiered instanceof DiggerItem && OrevolutionConfig.COMMON.toolsPowers.get()) {
            uses = ToolPowerRegistry.getToolPower(tiered.getTier()).setMaxUses(stack, uses);
        }

        cir.setReturnValue(Math.round(13.0F - (float)stack.getDamageValue() * 13.0F / (float)uses));
    }

    // bar color fix
    @Inject(method = "getBarColor", at = @At("RETURN"), cancellable = true)
    public void orevolution$injectBarColor(CallbackInfoReturnable<Integer> cir) {
        ItemStack stack = (ItemStack)(Object)this;

        if (!(stack.getItem() instanceof TieredItem tiered)) return;

        int stackMaxDamage = tiered.getDefaultInstance().getMaxDamage();

        Supplier<Integer> override = BALANCED_DURABILITIES.get(tiered.getTier());
        if (override != null) {
            stackMaxDamage = override.get();
        }

        if (tiered instanceof SwordItem && OrevolutionConfig.COMMON.weaponsPowers.get()) {
            stackMaxDamage = ToolPowerRegistry.getWeaponPower(tiered.getTier()).setMaxUses(stack, stackMaxDamage);
        }
        else if (tiered instanceof DiggerItem && OrevolutionConfig.COMMON.toolsPowers.get()) {
            stackMaxDamage = ToolPowerRegistry.getToolPower(tiered.getTier()).setMaxUses(stack, stackMaxDamage);
        }

        float ratio = (float)Math.max(0, stackMaxDamage - stack.getDamageValue()) / (float)stackMaxDamage;
        float f = Math.max(0.0F, ratio);
        cir.setReturnValue(Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F));
    }
}
