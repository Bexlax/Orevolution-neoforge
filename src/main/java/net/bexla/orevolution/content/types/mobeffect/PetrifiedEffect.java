package net.bexla.orevolution.content.types.mobeffect;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class PetrifiedEffect extends MobEffect {

    public PetrifiedEffect() {
        super(MobEffectCategory.HARMFUL, 0x4B8B3B);

        this.addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                ResourceLocation.fromNamespaceAndPath("orevolution", "petrified_speed"),
                -0.15D,
                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide()) {
            return true;
        }

        float damage = amplifier + 1.0F;

        if (entity.getHealth() > 1.0F) {
            entity.hurt(entity.damageSources().magic(), damage);
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        int interval = 40 >> amplifier;

        if (interval <= 0) {
            interval = 1;
        }

        return duration % interval == 0;
    }
}