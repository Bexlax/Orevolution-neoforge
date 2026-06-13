package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class ToolGiveXPOnKill extends OrevolutionToolPower {
    private final int xpAmount;

    public ToolGiveXPOnKill(String tooltipId, IConditional conditional, int xpAmount) {
        super(tooltipId, conditional);
        this.xpAmount = xpAmount;
    }

    @Override
    public Object addTooltipValue() {
        return xpAmount;
    }

    @Override
    public float onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker, DamageSource source, float dmgAmount) {
        if(dmgAmount >= target.getHealth()) {
            attacker.level().addFreshEntity(
                    new ExperienceOrb(
                            attacker.level(),
                            target.getX(),
                            target.getY(),
                            target.getZ(),
                            xpAmount
                    )
            );
        }
        return super.onHitEntity(stack, target, attacker, source, dmgAmount);
    }
}
