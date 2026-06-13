package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.armor.ArmorPowerMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ArmorGrantEffects extends ArmorPowerMobEffects {
    public ArmorGrantEffects(String tooltipId, IConditional conditional, int duration, int amplifier, List<Holder<MobEffect>> effect) {
        super("", tooltipId, conditional, duration, amplifier, List.of(), effect);
    }

    public ArmorGrantEffects(String tooltipId, IConditional conditional, int duration, int amplifier, Holder<MobEffect> effect) {
        super("", tooltipId, conditional, duration, amplifier, List.of(), effect != null? List.of(effect) : List.of());
    }

    @Override
    public void onTickWhileWorn(ItemStack stack, LivingEntity wearer, EquipmentSlot slot) {
        if(!getCBoolean(stack, wearer.level(), wearer, null)) return;

        for(Holder<MobEffect> p : this.effectsPlayer) {
            if(wearer.hasEffect(p)) {
                wearer.getEffect(p).update(new MobEffectInstance(p, this.duration, this.amplifier));
            }
            else {
                wearer.addEffect(new MobEffectInstance(p, this.duration, this.amplifier));
            }
        }
    }
}
