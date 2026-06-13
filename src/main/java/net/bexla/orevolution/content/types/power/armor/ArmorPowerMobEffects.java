package net.bexla.orevolution.content.types.power.armor;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ArmorPowerMobEffects extends OrevolutionArmorPower {
    protected final List<Holder<MobEffect>> effectsMob;
    protected final List<Holder<MobEffect>> effectsPlayer;
    protected final int duration;
    protected final int amplifier;
    protected final String tooltip_wearer_id;

    public ArmorPowerMobEffects(String tooltip_target_id, String tooltip_wearer_id, @NotNull IConditional conditional, int duration, int amplifier, @NotNull List<Holder<MobEffect>> effectsMob, @NotNull List<Holder<MobEffect>> effectsPlayer) {
        super(tooltip_target_id, conditional);
        this.effectsMob = effectsMob;
        this.effectsPlayer = effectsPlayer;
        this.duration = duration;
        this.amplifier = amplifier;
        this.tooltip_wearer_id = tooltip_wearer_id;
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        List<Component> tips = new ArrayList<>();
        if(!this.effectsMob.isEmpty()) {
            tips.add(Component.translatable("tooltip.orevolution." + getTooltipID()).withStyle(ChatFormatting.GREEN));
            for (Holder<MobEffect> p : this.effectsMob) {
                tips.add(Component.literal(" - " + p.value().getDisplayName().getString() + (this.amplifier > 0 ? " " + Component.translatable("potion.potency." + this.amplifier).getString() : "")).withStyle(ChatFormatting.AQUA));
            }
            tips.add(Component.empty());
        }
        if(!this.effectsPlayer.isEmpty()) {
            tips.add(Component.translatable("tooltip.orevolution." + tooltip_wearer_id).withStyle(ChatFormatting.GREEN));
            for (Holder<MobEffect> p : this.effectsPlayer) {
                tips.add(Component.literal(" - " + p.value().getDisplayName().getString() + (this.amplifier > 0 ? " " + Component.translatable("potion.potency." + this.amplifier).getString() : "")).withStyle(ChatFormatting.AQUA));
            }
            tips.add(Component.empty());
        }

        return tips;
    }
}