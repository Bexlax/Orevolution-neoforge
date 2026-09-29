package net.bexla.orevolution.content.types.mobeffect;

import net.bexla.orevolution.init.RegMobEffects;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public class PurificationMobEffect extends MobEffect {
    public PurificationMobEffect() {
        super(MobEffectCategory.HARMFUL, 0xE8F7FF);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide()) return true;

        double radius = 1.0D + amplifier * 2.0D;

        List<LivingEntity> nearby = entity.level().getEntitiesOfClass(
                LivingEntity.class,
                entity.getBoundingBox().inflate(radius),
                target -> target != entity && target.getType().is(EntityTypeTags.UNDEAD)
        );

        for (LivingEntity target : nearby) {
            MobEffectInstance current = target.getEffect(RegMobEffects.PURIFICATION);

            if (current == null || current.getDuration() < 40 || current.getAmplifier() < amplifier) {
                target.addEffect(new MobEffectInstance(
                        RegMobEffects.PURIFICATION,
                        100,
                        amplifier,
                        false,
                        true,
                        true
                ));
            }
        }

        if (entity.getType().is(EntityTypeTags.UNDEAD)) {
            entity.hurt(entity.damageSources().magic(), (amplifier + 1.0F) * 0.5F);
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int i = 25 >> amplifier;
        return i == 0 || duration % i == 0;
    }
}