package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.utility.OrevolutionUtils;
import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ToolDamageDurabilityRelative extends OrevolutionToolPower {
    private final boolean reversed;

    public ToolDamageDurabilityRelative(String tooltipId, IConditional conditional, boolean reversed) {
        super(tooltipId, conditional);
        this.reversed = reversed;
    }

    public ToolDamageDurabilityRelative(String tooltipId, IConditional conditional) {
        this(tooltipId, conditional, false);
    }

    @Override
    public float onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker, DamageSource source, float dmgAmount) {
        if(!getCondition(stack, null, attacker.level(), attacker, target)) return super.onHitEntity(stack, target, attacker, source, dmgAmount);

        if(reversed)
            return (dmgAmount * 4) / OrevolutionUtils.durabilityPercentage(stack);
        return dmgAmount * OrevolutionUtils.durabilityPercentage(stack);
    }
}
