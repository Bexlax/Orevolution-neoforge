package net.bexla.orevolution.content.types.power.armor;

import net.bexla.orevolution.content.interfaces.IArmorPower;
import net.bexla.orevolution.content.interfaces.IConditional;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class OrevolutionArmorPower implements IArmorPower {
    private final String tooltip_id;
    public final IConditional conditional;

    public OrevolutionArmorPower(String tooltipId, @NotNull IConditional conditional) {
        this.tooltip_id = tooltipId;
        this.conditional = conditional;
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        List<MutableComponent> shiftComponent = ctrlTooltip();
        List<Component> tips = new ArrayList<>();
        Object[] objects = addTooltipValue();
        MutableComponent condition = conditional.value();

        tips.add(Component.translatable("power.orevolution." + this.tooltip_id, objects).withStyle(ChatFormatting.GREEN));
        if(condition != null) {
            tips.add(CommonComponents.space().append(condition.withStyle(ChatFormatting.DARK_GRAY)));
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
        String path = "power.orevolution.explanation." + this.tooltip_id;
        MutableComponent ctrlTip = Component.translatable(path);

        if(!ctrlTip.getString().equals(path))
            return List.of(ctrlTip);
        return null;
    }

    public Object[] addTooltipValue() {
        return new Object[0];
    }

    public String getTooltipID() {
        return this.tooltip_id;
    }

    public boolean condition(ItemStack stack, Level level, LivingEntity player, LivingEntity target) {
        return conditional.shouldActivate(stack, null, level, player, target);
    }
}