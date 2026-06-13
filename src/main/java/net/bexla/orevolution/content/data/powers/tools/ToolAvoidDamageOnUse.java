package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ToolAvoidDamageOnUse extends OrevolutionToolPower {
    public ToolAvoidDamageOnUse(String tooltipId, IConditional conditional) {
        super(tooltipId, conditional);
    }

    @Override
    public boolean onUseOverride(ItemStack stack, Level level, LivingEntity player) {
        return getCBoolean(stack, null, level, player, null);
    }
}
