package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.armor.OrevolutionArmorPower;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArmorReduceDamageType extends OrevolutionArmorPower {
    final List<ResourceKey<DamageType>> damagetype;
    final float damagePercentage;

    public ArmorReduceDamageType(String tooltipId, @NotNull IConditional conditional, List<ResourceKey<DamageType>> damagetype, float damagePercentage) {
        super(tooltipId, conditional);
        this.damagetype = damagetype;
        this.damagePercentage = damagePercentage;
    }

    public ArmorReduceDamageType(String tooltipId, @NotNull IConditional conditional, ResourceKey<DamageType> damagetype, float damagePercentage) {
        super(tooltipId, conditional);
        this.damagetype = List.of(damagetype);
        this.damagePercentage = damagePercentage;
    }

    @Override
    public Object addTooltipValue() {
        return (int)((1 - damagePercentage) * 100) + "%";
    }

    @Override
    public float onDamaged(LivingEntity wearer, DamageSource source, float amount) {
        if(!getCBoolean(null, wearer.level(), wearer, source.getEntity() instanceof LivingEntity livingEntity? livingEntity : null)) return 1;

        for(ResourceKey<DamageType> type : damagetype) {
            return source.is(type)? damagePercentage : 1;
        }
        return 1;
    }
}
