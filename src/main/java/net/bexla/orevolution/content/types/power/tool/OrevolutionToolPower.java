package net.bexla.orevolution.content.types.power.tool;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.interfaces.IToolPower;
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
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OrevolutionToolPower implements IToolPower {
    protected static final Logger LOGGER = LogUtils.getLogger();
    private final String tooltip_id;
    private final IConditional conditional;

    public OrevolutionToolPower(String tooltipId, IConditional conditional) {
        this.tooltip_id = tooltipId;
        this.conditional = conditional;
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        Object tooltipval = addTooltipValue();
        MutableComponent shiftComponent = ctrlTooltip();
        List<Component> tips = new ArrayList<>();
        if(tooltipval != null) {
            tips.add(Component.translatable("tooltip.orevolution." + this.tooltip_id, tooltipval).withStyle(ChatFormatting.GREEN));
        }
        else {
            tips.add(Component.translatable("tooltip.orevolution." + this.tooltip_id).withStyle(ChatFormatting.GREEN));
        }

        if(shiftComponent == null) {
            tips.add(Component.literal(""));
            return tips;
        }

        if(Screen.hasControlDown()) {
            tips.add(shiftComponent.withStyle(ChatFormatting.DARK_GRAY));
        }
        else {
            tips.add(Component.translatable("tooltip.orevolution.press_key", Component.translatable("key.keyboard.left.control").getString()).withStyle(ChatFormatting.DARK_GRAY));
        }

        tips.add(Component.literal(""));

        return tips;
    }

    public MutableComponent ctrlTooltip() {
        return null;
    }

    public Object addTooltipValue() {
        return null;
    }

    public String getTooltipID() {
        return this.tooltip_id;
    }

    public boolean getCBoolean(ItemStack stack, BlockState state, Level level, LivingEntity player, LivingEntity possibleTarget) {
        return conditional.shouldActivate(stack, state, level, player, possibleTarget);
    }
}