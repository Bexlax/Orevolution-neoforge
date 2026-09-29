package net.bexla.orevolution.content.types.power.armor;

import net.bexla.orevolution.content.interfaces.IArmorPower;
import net.bexla.orevolution.content.interfaces.IConditional;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class OrevolutionArmorPower implements IArmorPower {
    private final String tooltip_id;
    private final IConditional conditional;

    public OrevolutionArmorPower(String tooltipId, @NotNull IConditional conditional) {
        this.tooltip_id = tooltipId;
        this.conditional = conditional;
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        List<Component> tips = new ArrayList<>();
        Object[] objects = addTooltipValue();

        tips.add(Component.translatable("power.orevolution." + this.tooltip_id, objects).withStyle(ChatFormatting.GREEN));

        return tips;
    }

    public Object[] addTooltipValue() {
        return new Object[0];
    }

    public String getTooltipID() {
        return this.tooltip_id;
    }

    public boolean condition(ItemStack stack, Level level, LivingEntity player, LivingEntity possibleTarget) {
        return conditional.shouldActivate(stack, null, level, player, possibleTarget);
    }
}