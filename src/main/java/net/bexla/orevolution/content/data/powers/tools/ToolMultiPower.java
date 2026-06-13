package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.Conditionals;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class ToolMultiPower extends OrevolutionToolPower {
    private final List<OrevolutionToolPower> powers;

    public ToolMultiPower(List<OrevolutionToolPower> powers) {
        super("", Conditionals.always());
        this.powers = powers;
    }

    public float onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker, DamageSource source, float dmgAmount) {
        for(OrevolutionToolPower p : this.powers) {
            return p.onHitEntity(stack, target, attacker, source, dmgAmount);
        }
        return super.onHitEntity(stack, target, attacker, source, dmgAmount);
    }

    public boolean onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity player, BlockState state) {
        for(OrevolutionToolPower p : this.powers) {
            return p.onMineBlock(stack, level, pos, player, state);
        }
        return false;
    }

    public boolean onDropXPBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity player, BlockState state, int xpToDrop) {
        for(OrevolutionToolPower p : this.powers) {
            return p.onDropXPBlock(stack, level, pos, player, state, xpToDrop);
        }
        return false;
    }

    public void onInventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        for(OrevolutionToolPower p : this.powers) {
            p.onInventoryTick(stack, level, entity, slot, selected);
        }
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        List<Component> tips = new ArrayList<>();
        if(Screen.hasAltDown()) {
            for (OrevolutionToolPower p : this.powers) {
                tips.addAll(p.appendTooltip(stack, context, tooltipComponents, tooltipFlag));
            }
        }
        else {
            tips.add(Component.translatable("tooltip.orevolution.press_key", Component.translatable("key.keyboard.left.alt").getString()).withStyle(ChatFormatting.DARK_GRAY));
        }
        return tips;
    }
}
