package net.bexla.orevolution.content.types.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;

import java.util.List;

public class SpecialBottleItem extends Item {
    public static final int DRINK_DURATION = 32;

    private final int effectAmp;
    private final int effectDuration;
    private final Holder<MobEffect> mobEffect;

    public SpecialBottleItem(Item.Properties properties, int effectAmp, int effectDuration, Holder<MobEffect> mobEffect) {
        super(properties);
        this.effectAmp = effectAmp;
        this.effectDuration = effectDuration;
        this.mobEffect = mobEffect;
    }

    public int getEffectDuration() {
        return this.effectDuration;
    }

    public int getEffectAmp() {
        return this.effectAmp;
    }

    public Holder<MobEffect> getMobEffect() {
        return this.mobEffect;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, stack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!level.isClientSide) {
            level.playSound(null, livingEntity.blockPosition(), SoundEvents.WANDERING_TRADER_DRINK_POTION, livingEntity.getSoundSource(), 1.0F, 1.0F);
            livingEntity.addEffect(new MobEffectInstance(mobEffect, effectDuration, effectAmp, false, false, true));
        }

        stack.consume(1, livingEntity);
        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity livingEntity) {
        return DRINK_DURATION;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        return ItemUtils.startUsingInstantly(level, player, interactionHand);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, List<Component> componentList, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, tooltipContext, componentList, tooltipFlag);
        Integer integer = itemStack.getOrDefault(DataComponents.OMINOUS_BOTTLE_AMPLIFIER, effectAmp);
        List<MobEffectInstance> list = List.of(new MobEffectInstance(mobEffect, effectDuration, integer, false, false, true));
        PotionContents.addPotionTooltip(list, componentList::add, 1.0F, tooltipContext.tickRate());
    }
}
