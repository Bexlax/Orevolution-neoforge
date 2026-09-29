package net.bexla.orevolution.content.types.mobeffect;

import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class LesserPurificationMobEffect extends MobEffect {
    public LesserPurificationMobEffect() {
        super(MobEffectCategory.HARMFUL, 0xE8F7FF);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide()) return true;

        if (entity.getType().is(EntityTypeTags.UNDEAD)) {
            entity.hurt(entity.damageSources().magic(), (amplifier + 1.0F) * 0.5F);
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int i = 40 >> amplifier;
        return i == 0 || duration % i == 0;
    }
}