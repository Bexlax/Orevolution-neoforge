package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.bexla.orevolution.init.RegDataComponents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ToolSoulBound extends OrevolutionToolPower {

    public ToolSoulBound(String tooltipId, IConditional conditional) {
        super(tooltipId, conditional);
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if(!getCBoolean(stack, null, level, (LivingEntity) entity, null)) {
            stack.set(RegDataComponents.SOUL_BOUND, false);
            return;
        }

        stack.set(RegDataComponents.SOUL_BOUND, true);
    }
}
