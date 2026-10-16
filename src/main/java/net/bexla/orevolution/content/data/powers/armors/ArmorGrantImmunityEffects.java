package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.armor.OrevolutionArmorPower;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ArmorGrantImmunityEffects extends OrevolutionArmorPower {
    private final MobEffectCategory category;
    protected final List<Holder<MobEffect>> effectsPlayer;

    public ArmorGrantImmunityEffects(String tooltipId, IConditional conditional, List<Holder<MobEffect>> effects) {
        super(tooltipId, conditional);
        this.effectsPlayer = effects;
        this.category = null;
    }

    public ArmorGrantImmunityEffects(String tooltipId, IConditional conditional, Holder<MobEffect> effects) {
        super(tooltipId, conditional);
        this.effectsPlayer = List.of(effects);
        this.category = null;
    }

    public ArmorGrantImmunityEffects(String tooltipId, IConditional conditional, MobEffectCategory category) {
        super(tooltipId, conditional);
        this.category = category;
        this.effectsPlayer = List.of();
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        List<Component> tips = new ArrayList<>();
        Object[] objects = addTooltipValue();
        MutableComponent condition = conditional.value();

        if(!this.effectsPlayer.isEmpty()) {
            tips.add(Component.translatable("power.orevolution." + getTooltipID(), objects).withStyle(ChatFormatting.GREEN));
            if(condition != null) {
                tips.add(CommonComponents.space().append(condition.withStyle(ChatFormatting.DARK_GRAY)));
            }
            for (Holder<MobEffect> p : this.effectsPlayer) {
                tips.add(Component.literal(" - " + p.value().getDisplayName().getString()).withStyle(ChatFormatting.AQUA));
            }
        }
        if(category != null) {
            tips.add(Component.translatable("power.orevolution." + getTooltipID(), objects).withStyle(ChatFormatting.GREEN));
            if(condition != null) {
                tips.add(CommonComponents.space().append(condition.withStyle(ChatFormatting.DARK_GRAY)));
            }
        }

        return tips;
    }


    @Override
    public MobEffectEvent.Applicable.Result onEffectApplicable(LivingEntity wearer, MobEffectInstance effectInstance, @Nullable Entity source, boolean applicable) {
        if(this.effectsPlayer.isEmpty())
            if(effectInstance.getEffect().value().getCategory() == category)
                return MobEffectEvent.Applicable.Result.DO_NOT_APPLY;

        for(Holder<MobEffect> p : this.effectsPlayer) {
            if(!effectInstance.getEffect().is(p)) continue;
            return MobEffectEvent.Applicable.Result.DO_NOT_APPLY;
        }
        return MobEffectEvent.Applicable.Result.DEFAULT;
    }
}
