package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.armor.ArmorPowerMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ArmorGrantEffects extends ArmorPowerMobEffects {
    public ArmorGrantEffects(String tooltipId, IConditional conditional, int duration, int amplifier, List<Holder<MobEffect>> effect) {
        super("", tooltipId, conditional, duration, amplifier, null, effect);
    }

    public ArmorGrantEffects(String tooltipId, IConditional conditional, int duration, int amplifier, Holder<MobEffect> effect) {
        super("", tooltipId, conditional, duration, amplifier, null, effect);
    }

    @Override
    public void onTickWhileWorn(ItemStack helmet, LivingEntity wearer, EquipmentSlot slot) {
        if(!condition(helmet, wearer.level(), wearer, null)) return;

        applyAllEffects(wearer, null);
    }
}
