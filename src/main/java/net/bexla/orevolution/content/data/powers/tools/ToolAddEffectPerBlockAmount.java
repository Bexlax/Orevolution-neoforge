package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.bexla.orevolution.init.RegDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ToolAddEffectPerBlockAmount extends OrevolutionToolPower {
    private final Holder<MobEffect> effect;
    private final int minBlocks;
    private final int effectTime;
    private final int maxAmplifier;

    public ToolAddEffectPerBlockAmount(String tooltipId, IConditional conditional, Holder<MobEffect> effect, int minHits, int effectTime, int maxAmplifier) {
        super(tooltipId, conditional);
        this.effect = effect;
        this.minBlocks = minHits;
        this.effectTime = effectTime;
        this.maxAmplifier = maxAmplifier;
    }

    @Override
    public boolean onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity entity, BlockState state) {
        if(!getCBoolean(stack, null, level, entity, null)) return super.onMineBlock(stack, level, pos, entity, state);

        Holder<MobEffect> eff = effect;

        int blocksMined = stack.getOrDefault(RegDataComponents.BLOCKS_MINED, 0);
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
        stack.set(RegDataComponents.BLOCKS_MINED, blocksMined);
        return super.onMineBlock(stack, level, pos, entity, state);
    }
}
