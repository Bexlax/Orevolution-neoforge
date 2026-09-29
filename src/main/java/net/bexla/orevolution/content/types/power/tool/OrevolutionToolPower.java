package net.bexla.orevolution.content.types.power.tool;

import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.interfaces.IToolPower;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class OrevolutionToolPower implements IToolPower {
    public final String tooltip_id;
    public final IConditional conditional;

    public OrevolutionToolPower(String tooltipId, IConditional conditional) {
        this.tooltip_id = tooltipId;
        this.conditional = conditional;
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        List<MutableComponent> shiftComponent = ctrlTooltip();
        List<Component> tips = new ArrayList<>();
        MutableComponent condition = conditional.value();

        Object[] objects = addTooltipValue();
        tips.add(Component.translatable("power.orevolution." + this.tooltip_id, objects).withStyle(ChatFormatting.GREEN));
        if(condition != null) {
            tips.add(condition.withStyle(ChatFormatting.DARK_GRAY));
        }

        if (Screen.hasControlDown()) {
            if (shiftComponent != null) {
                for (MutableComponent component : shiftComponent) {
                    tips.add(component.withStyle(ChatFormatting.DARK_GRAY));
                }
            }
        } else {
            if (shiftComponent != null) {
                tips.add(Component.translatable(
                        "power.orevolution.press_key",
                        Component.translatable("key.keyboard.left.control").getString()
                ).withStyle(ChatFormatting.DARK_GRAY));
            }
        }

        return tips;
    }

    public List<MutableComponent> ctrlTooltip() {
        return null;
    }

    public Object[] addTooltipValue() {
        return new Object[0];
    }

    public boolean getCondition(ItemStack stack, BlockState state, Level level, LivingEntity player, LivingEntity possibleTarget) {
        return conditional.shouldActivate(stack, state, level, player, possibleTarget);
    }
}