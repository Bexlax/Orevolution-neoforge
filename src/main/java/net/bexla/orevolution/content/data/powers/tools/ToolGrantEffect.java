package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.ToolPowerMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class ToolGrantEffect extends ToolPowerMobEffects {
    private final boolean onlyOnHand;

    public ToolGrantEffect(String tooltipId, IConditional conditional, int duration, int amplifier, List<Holder<MobEffect>> effect, boolean onlyOnHand) {
        super("", tooltipId, conditional, duration, amplifier, null, effect);
        this.onlyOnHand = onlyOnHand;
    }

    public ToolGrantEffect(String tooltipId, IConditional conditional, int duration, int amplifier, Holder<MobEffect> effect, boolean onlyOnHand) {
        super("", tooltipId, conditional, duration, amplifier, null, effect);
        this.onlyOnHand = onlyOnHand;
    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if(!(entity instanceof LivingEntity living)) return;

        boolean inHand = stack.getEquipmentSlot() == EquipmentSlot.MAINHAND || stack.getEquipmentSlot() == EquipmentSlot.OFFHAND;

        if(onlyOnHand && !inHand) return;

        if (entity instanceof LivingEntity livingEntity && getCondition(stack, null, level, living, null)) {
            for(Holder<MobEffect> p : this.effectAttacker) {
                livingEntity.addEffect(new MobEffectInstance(p, 20, 0, false, false));
            }
        }
    }
}