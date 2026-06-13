package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ToolRegeneration extends OrevolutionToolPower {
    private final int regenerationPerTick;
    private final int tickCooldown;

    public ToolRegeneration(String tooltipId, IConditional conditional, int regenerationPerTick, int tickCooldown) {
        super(tooltipId, conditional);
        this.regenerationPerTick = regenerationPerTick;
        this.tickCooldown = tickCooldown;
    }

    @Override
    public Object addTooltipValue() {
        return tickCooldown / 20;
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!(entity instanceof Player player)) return;

        if (!getCBoolean(stack, null, level, player, null) || level.isClientSide()) return;

        if (level.getGameTime() % tickCooldown != 0) return;

        if (!stack.isDamaged()) return;

        int newDamage = stack.getDamageValue() - regenerationPerTick;

        stack.setDamageValue(Math.max(0, newDamage));
    }
}
