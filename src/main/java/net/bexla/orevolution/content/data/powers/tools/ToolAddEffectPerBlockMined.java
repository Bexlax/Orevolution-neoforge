package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.bexla.orevolution.init.RegDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class ToolAddEffectPerBlockMined extends OrevolutionToolPower {
    private final Holder<MobEffect> effect;
    private final int minBlocks;
    private final int effectTime;
    private final int maxAmplifier;

    public ToolAddEffectPerBlockMined(String tooltipId, IConditional conditional, Holder<MobEffect> effect, int minHits, int effectTime, int maxAmplifier) {
        super(tooltipId, conditional);
        this.effect = effect;
        this.minBlocks = minHits;
        this.effectTime = effectTime;
        this.maxAmplifier = maxAmplifier;
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        List<Component> tips = new ArrayList<>();
        Object condition = conditional.value();

        if(this.effect != null) {
            tips.add(Component.translatable("power.orevolution." + tooltip_id, this.minBlocks, condition != null? condition : "%s").withStyle(ChatFormatting.GREEN));
            tips.add(Component.literal(" - " + this.effect.value().getDisplayName().getString()).withStyle(ChatFormatting.AQUA));
        }

        return tips;
    }

    @Override
    public boolean onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity entity, BlockState state) {
        if(!getCondition(stack, null, level, entity, null)) return super.onMineBlock(stack, level, pos, entity, state);

        Holder<MobEffect> eff = effect;

        int blocksMined = stack.getOrDefault(RegDataComponents.CONSECUTIVE_BLOCKS, 0);
        MobEffectInstance currentEffect = entity.getEffect(eff);
        int effectsStacked = currentEffect != null? currentEffect.getAmplifier() : 0;

        blocksMined++;
        if (blocksMined >= minBlocks) {
            if (effectsStacked < maxAmplifier) {
                entity.removeEffect(eff);
                entity.addEffect(new MobEffectInstance(eff, effectTime, effectsStacked, false, true));
            }
            blocksMined = 0;
        }
        stack.set(RegDataComponents.CONSECUTIVE_BLOCKS, blocksMined);
        return super.onMineBlock(stack, level, pos, entity, state);
    }
}
