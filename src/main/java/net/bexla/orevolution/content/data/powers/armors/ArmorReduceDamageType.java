package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.armor.OrevolutionArmorPower;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;

public class ArmorReduceDamageType extends OrevolutionArmorPower {
    final TagKey<DamageType> damagetype;
    final float damagePercentage;

    public ArmorReduceDamageType(String tooltipId, IConditional conditional, TagKey<DamageType> damagetype, float damagePercentage) {
        super(tooltipId, conditional);
        this.damagetype = damagetype;
        this.damagePercentage = damagePercentage;
    }

    @Override
    public Object[] addTooltipValue() {
        return new Object[] {
                (int) ((1 - damagePercentage) * 100) + "%"
        };
    }

    @Override
    public float onDamaged(LivingEntity wearer, DamageSource source, float amount) {
        if(!condition(null, wearer.level(), wearer, source.getEntity() instanceof LivingEntity livingEntity? livingEntity : null)) return amount;

        return source.is(damagetype)? amount * damagePercentage : amount;
    }
}