package net.bexla.orevolution.content.types.power.armor;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.interfaces.IConditional;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class ArmorPowerMobEffects extends OrevolutionArmorPower {
    protected final List<Holder<MobEffect>> effectsMob;
    protected final List<Holder<MobEffect>> effectsPlayer;
    protected final int duration;
    protected final int amplifier;
    protected final String tooltip_wearer_id;

    public ArmorPowerMobEffects(String tooltip_target_id, String tooltip_wearer_id, IConditional conditional, int duration, int amplifier, List<Holder<MobEffect>> effectsMob, List<Holder<MobEffect>> effectsPlayer) {
        super(tooltip_target_id, conditional);
        this.effectsMob = effectsMob != null? effectsMob : List.of();
        this.effectsPlayer = effectsPlayer != null? effectsPlayer : List.of();
        this.duration = duration;
        this.amplifier = amplifier;
        this.tooltip_wearer_id = tooltip_wearer_id;
    }

    public ArmorPowerMobEffects(String tooltip_target_id, String tooltip_wearer_id, IConditional conditional, int duration, int amplifier, Holder<MobEffect> effectsMob, Holder<MobEffect> effectsPlayer) {
        super(tooltip_target_id, conditional);
        this.effectsMob = effectsMob != null? List.of(effectsMob) : List.of();
        this.effectsPlayer = effectsPlayer != null? List.of(effectsPlayer) : List.of();
        this.duration = duration;
        this.amplifier = amplifier;
        this.tooltip_wearer_id = tooltip_wearer_id;
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        List<Component> tips = new ArrayList<>();
        Object[] objects = addTooltipValue();
        MutableComponent condition = conditional.value();

        if(!this.effectsMob.isEmpty()) {
            tips.add(Component.translatable("power.orevolution." + getTooltipID(), objects).withStyle(ChatFormatting.GREEN));
            if(condition != null) {
                tips.add(CommonComponents.space().append(condition.withStyle(ChatFormatting.DARK_GRAY)));
            }
            for (Holder<MobEffect> p : this.effectsMob) {
                tips.add(Component.literal(" - " + p.value().getDisplayName().getString() + (this.amplifier > 0 ? " " + Component.translatable("potion.potency." + this.amplifier).getString() : "")).withStyle(ChatFormatting.AQUA));
            }
        }
        if(!this.effectsPlayer.isEmpty()) {
            tips.add(Component.translatable("power.orevolution." + tooltip_wearer_id, objects).withStyle(ChatFormatting.GREEN));
            if(condition != null) {
                tips.add(CommonComponents.space().append(condition.withStyle(ChatFormatting.DARK_GRAY)));
            }
            for (Holder<MobEffect> p : this.effectsPlayer) {
                tips.add(Component.literal(" - " + p.value().getDisplayName().getString() + (this.amplifier > 0 ? " " + Component.translatable("potion.potency." + this.amplifier).getString() : "")).withStyle(ChatFormatting.AQUA));
            }
        }

        return tips;
    }

    public void applyAllEffects(LivingEntity wearer, LivingEntity target) {
        if(!this.effectsMob.isEmpty() && target != null) {
            for(Holder<MobEffect> p : this.effectsMob) {
                if(target.hasEffect(p)) {
                    if(wearer.getEffect(p).getDuration() > duration / 4) continue;
                }
                target.addEffect(new MobEffectInstance(p, this.duration, this.amplifier, OrevolutionConfig.CLIENT.displayParticleEffectsFromArmor.get(), OrevolutionConfig.CLIENT.displayEffectsFromArmor.get()));
            }
        }

        if(!this.effectsPlayer.isEmpty()) {
            for(Holder<MobEffect> p : this.effectsPlayer) {
                if(wearer.hasEffect(p)) {
                    if(wearer.getEffect(p).getDuration() > duration / 4) continue;
                }
                wearer.addEffect(new MobEffectInstance(p, this.duration, this.amplifier, OrevolutionConfig.CLIENT.displayParticleEffectsFromArmor.get(), OrevolutionConfig.CLIENT.displayEffectsFromArmor.get()));
            }
        }
    }
}