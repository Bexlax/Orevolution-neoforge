package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class ToolsDurabilitySpeed extends OrevolutionToolPower {
    public ToolsDurabilitySpeed(String tooltipId, IConditional conditional) {
        super(tooltipId, conditional);
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        List<Component> tips = new ArrayList<>();
        tips.add(Component.translatable("tooltip.orevolution." + getTooltipID()).withStyle(ChatFormatting.GREEN));

        if(Screen.hasControlDown()) {
            tips.add(Component.translatable("tooltip.orevolution." + getTooltipID() + "_explanation").withStyle(ChatFormatting.DARK_GRAY));
        }
        else {
            tips.add(Component.translatable("tooltip.orevolution.press_key", Component.translatable("key.keyboard.left.control").getString()).withStyle(ChatFormatting.DARK_GRAY));
        }

        tips.add(Component.literal(""));

        return tips;
    }

    @Override
    public float setDestroySpeed(ItemStack stack, BlockState state, float defaultSpeed) {
        if(!getCBoolean(stack, state, null, null, null)) return defaultSpeed;

        float durabilityPercent = 1.0F - ((float) stack.getDamageValue() / stack.getMaxDamage());

        float missingDurability = 1.0F - durabilityPercent;

        float speedMultiplier = 1.0F + (float)Math.pow(missingDurability, 1.5F) * 3F;

        return defaultSpeed * speedMultiplier;
    }
}
