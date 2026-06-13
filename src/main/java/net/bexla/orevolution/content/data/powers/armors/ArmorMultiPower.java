package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.data.Conditionals;
import net.bexla.orevolution.content.types.power.armor.OrevolutionArmorPower;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class ArmorMultiPower extends OrevolutionArmorPower {
    private final List<OrevolutionArmorPower> powers;

    public ArmorMultiPower(List<OrevolutionArmorPower> powers) {
        super("", Conditionals.always());
        this.powers = powers;
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        List<Component> tips = new ArrayList<>();
        if(Screen.hasAltDown()) {
            for (OrevolutionArmorPower p : this.powers) {
                tips.addAll(p.appendTooltip(stack, level, lines));
            }
        }
        else {
            tips.add(Component.translatable("tooltip.orevolution.press_key", Component.translatable("key.keyboard.left.alt").getString()).withStyle(ChatFormatting.DARK_GRAY));
        }
        return tips;
    }

    public void onTickWhileWorn(ItemStack stack, LivingEntity wearer, EquipmentSlot slot) {
        for(OrevolutionArmorPower p : this.powers) {
            p.onTickWhileWorn(stack, wearer, slot);
        }
    }

    public float onDamaged(LivingEntity wearer, DamageSource source, float amount) {
        for(OrevolutionArmorPower p : this.powers) {
            return p.onDamaged(wearer, source, amount);
        }
        return super.onDamaged(wearer, source, amount);
    }

    @Override
    public void onAttackTarget(LivingEntity wearer, LivingEntity target) {
        for(OrevolutionArmorPower p : this.powers) {
            p.onAttackTarget(wearer, target);
        }
    }

    @Override
    public boolean onDeath(LivingEntity wearer, DamageSource source) {
        for(OrevolutionArmorPower p : this.powers) {
            return p.onDeath(wearer, source);
        }
        return false;
    }

    @Override
    public boolean onFall(LivingEntity wearer, float distance, float damageMultiplier) {
        for(OrevolutionArmorPower p : this.powers) {
            return p.onFall(wearer, distance, damageMultiplier);
        }
        return false;
    }

    @Override
    public void onKnockback(LivingEntity wearer, float strength, double ratioX, double ratioZ) {
        for(OrevolutionArmorPower p : this.powers) {
            p.onKnockback(wearer, strength, ratioX, ratioZ);
        }
    }

    @Override
    public void onEquip(LivingEntity wearer) {
        for(OrevolutionArmorPower p : this.powers) {
            p.onEquip(wearer);
        }
    }

    @Override
    public void onUnequip(LivingEntity wearer) {
        for(OrevolutionArmorPower p : this.powers) {
            p.onUnequip(wearer);
        }
    }
}
