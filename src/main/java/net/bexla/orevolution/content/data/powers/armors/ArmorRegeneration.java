package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.armor.OrevolutionArmorPower;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ArmorRegeneration extends OrevolutionArmorPower {
    private final int regenerationPerTick;
    private final int tickCooldown;

    public ArmorRegeneration(String tooltipId, @NotNull IConditional conditional, int regenerationPerTick, int tickCooldown) {
        super(tooltipId, conditional);
        this.regenerationPerTick = regenerationPerTick;
        this.tickCooldown = tickCooldown;
    }

    @Override
    public Object addTooltipValue() {
        return tickCooldown / 20;
    }

    @Override
    public void onTickWhileWorn(ItemStack stack, LivingEntity wearer, EquipmentSlot slot) {
        if (!(wearer instanceof Player player)) return;

        Level level = player.level();

        if (!getCBoolean(stack, level, player, null) || level.isClientSide()) return;

        if (level.getGameTime() % tickCooldown != 0) return;

        if (!stack.isDamaged()) return;

        int newDamage = stack.getDamageValue() - regenerationPerTick;

        stack.setDamageValue(Math.max(0, newDamage));
    }
}
