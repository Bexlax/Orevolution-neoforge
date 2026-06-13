package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ToolSetOnFireOnHit extends OrevolutionToolPower {
    private final int timeInSeconds;

    public ToolSetOnFireOnHit(String tooltipId, IConditional conditional, int timeInSeconds) {
        super(tooltipId, conditional);
        this.timeInSeconds = timeInSeconds;
    }

    @Override
    public Object addTooltipValue() {
        return timeInSeconds;
    }

    @Override
    public float onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker, DamageSource source, float dmgAmount) {
        if(!getCBoolean(stack, null, attacker.level(), attacker, target)) return super.onHitEntity(stack, target, attacker, source, dmgAmount);

        target.igniteForSeconds(timeInSeconds);

        return super.onHitEntity(stack, target, attacker, source, dmgAmount);
    }
}
