package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ToolAvoidDurabilityLose extends OrevolutionToolPower {
    public ToolAvoidDurabilityLose(String tooltipId, IConditional conditional) {
        super(tooltipId, conditional);
    }

    @Override
    public boolean onUseOverride(ItemStack stack, Level level, LivingEntity player) {
        return getCondition(stack, null, level, player, null);
    }
}
