package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ToolIncreaseDamageOnHit extends OrevolutionToolPower {
    private final float damageToAdd;

    public ToolIncreaseDamageOnHit(String tooltipId, IConditional conditional, float damageToAdd) {
        super(tooltipId, conditional);
        this.damageToAdd = damageToAdd;
    }

    @Override
    public Object addTooltipValue() {
        return "+" + (int)damageToAdd;
    }

    @Override
    public float onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker, DamageSource source, float dmgAmount) {
        return getCBoolean(stack, null, attacker.level(), attacker, target)? dmgAmount + damageToAdd : super.onHitEntity(stack, target, attacker, source, dmgAmount);
    }
}
