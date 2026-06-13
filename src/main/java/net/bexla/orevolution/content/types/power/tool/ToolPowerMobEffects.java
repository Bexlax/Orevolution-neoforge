package net.bexla.orevolution.content.types.power.tool;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.ArrayList;
import java.util.List;

public class ToolPowerMobEffects extends OrevolutionToolPower {
    protected final List<Holder<MobEffect>> effectTarget;
    protected final List<Holder<MobEffect>> effectAttacker;
    protected final int duration;
    protected final int amplifier;
    protected final String tooltip_attacker_id;

    public ToolPowerMobEffects(String tooltip_target_id, String tooltip_attacker_id, IConditional conditional, int duration, int amplifier, List<Holder<MobEffect>> effectTarget, List<Holder<MobEffect>> effectAttacker) {
        super(tooltip_target_id, conditional);
        this.tooltip_attacker_id = tooltip_attacker_id;
        this.effectTarget = effectTarget;
        this.effectAttacker = effectAttacker;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        List<Component> tips = new ArrayList<>();
        if(this.effectTarget != null) {
            tips.add(Component.translatable("tooltip.orevolution." + getTooltipID()).withStyle(ChatFormatting.GREEN));
            for (Holder<MobEffect> p : this.effectTarget) {
                tips.add(Component.literal(" - " + p.value().getDisplayName().getString() + (this.amplifier > 0 ? Component.translatable("potion.potency." + this.amplifier).toString() : "")).withStyle(ChatFormatting.AQUA));
            }

            tips.add(Component.empty());
        }
        if(this.effectAttacker != null) {
            tips.add(Component.translatable("tooltip.orevolution." + tooltip_attacker_id).withStyle(ChatFormatting.GREEN));
            for (Holder<MobEffect> p : this.effectAttacker) {
                tips.add(Component.literal(" - " + p.value().getDisplayName().getString() + (this.amplifier > 0 ? Component.translatable("potion.potency." + this.amplifier).toString() : "")).withStyle(ChatFormatting.AQUA));
            }

            tips.add(Component.empty());
        }

        return tips;
    }
}