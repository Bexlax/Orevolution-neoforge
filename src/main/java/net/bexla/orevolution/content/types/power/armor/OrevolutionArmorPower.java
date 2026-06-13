package net.bexla.orevolution.content.types.power.armor;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.content.types.interfaces.IArmorPower;
import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OrevolutionArmorPower implements IArmorPower {
    protected static final Logger LOGGER = LogUtils.getLogger();
    private final String tooltip_id;
    private final IConditional conditional;

    public OrevolutionArmorPower(String tooltipId, @NotNull IConditional conditional) {
        this.tooltip_id = tooltipId;
        this.conditional = conditional;
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        Object tooltipval = addTooltipValue();
        List<Component> tips = new ArrayList<>();
        if(tooltipval != null) {
            tips.add(Component.translatable("tooltip.orevolution." + this.tooltip_id, tooltipval).withStyle(ChatFormatting.GREEN));
        }
        else {
            tips.add(Component.translatable("tooltip.orevolution." + this.tooltip_id).withStyle(ChatFormatting.GREEN));
        }

        tips.add(Component.empty());

        return tips;
    }

    public Object addTooltipValue() {
        return null;
    }

    public String getTooltipID() {
        return this.tooltip_id;
    }

    public boolean getCBoolean(ItemStack stack, Level level, LivingEntity player, LivingEntity possibleTarget) {
        return conditional.shouldActivate(stack, null, level, player, possibleTarget);
    }
}