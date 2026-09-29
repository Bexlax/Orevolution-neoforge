package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.armor.ArmorPowerMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArmorCauseEffectsNearby extends ArmorPowerMobEffects {
    private final double range;

    public ArmorCauseEffectsNearby(String tooltip_id, @NotNull IConditional conditional, int duration, int amplifier, List<Holder<MobEffect>> effectsMob, double range) {
        super(tooltip_id, "", conditional, duration, amplifier, effectsMob, List.of());
        this.range = range;
    }

    public ArmorCauseEffectsNearby(String tooltip_id, IConditional conditional, int baseDuration, int amplifier, Holder<MobEffect> effectAttacker, double range) {
        this(tooltip_id, conditional, baseDuration, amplifier, effectAttacker != null? List.of(effectAttacker) : List.of(), range);
    }

    @Override
    public Object[] addTooltipValue() {
        return new Object[] {
                (int)range
        };
    }

    @Override
    public void onTickWhileWorn(ItemStack stack, LivingEntity wearer, EquipmentSlot slot) {
        if(!(wearer instanceof Player player)) return;

        Level level = player.level();

        if(level.isClientSide) return;

        AABB area = player.getBoundingBox().inflate(range);

        List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, area);

        final int fourthDura = duration / 4;

        for(LivingEntity entity : entities) {
            if(!entity.isAlive()) continue;
            if(!condition(stack, wearer.level(), wearer, entity)) continue;

            if(!this.effectsMob.isEmpty()) {
                for(Holder<MobEffect> p : this.effectsMob) {
                    MobEffectInstance current = entity.getEffect(p);

                    if(current == null || current.getDuration() < fourthDura || current.getAmplifier() < amplifier) {
                        entity.addEffect(new MobEffectInstance(p, this.duration, this.amplifier));
                    }
                }
            }
        }
    }
}
