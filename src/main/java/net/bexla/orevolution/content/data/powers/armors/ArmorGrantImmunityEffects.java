package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.armor.OrevolutionArmorPower;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class ArmorGrantImmunityEffects extends OrevolutionArmorPower {
    private final List<Holder<MobEffect>> effects;

    public ArmorGrantImmunityEffects(String tooltipId, IConditional conditional, List<Holder<MobEffect>> effects) {
        super(tooltipId, conditional);
        this.effects = effects;
    }

    public ArmorGrantImmunityEffects(String tooltipId, IConditional conditional, Holder<MobEffect> effect) {
        super(tooltipId, conditional);
        this.effects = effect != null? List.of(effect) : List.of();
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        List<Component> tips = new ArrayList<>();
        tips.add(Component.translatable("tooltip.orevolution." + getTooltipID()).withStyle(ChatFormatting.GREEN));
        for (Holder<MobEffect> p : this.effects) {
            tips.add(Component.literal(" - " + p.value().getDisplayName().getString()).withStyle(ChatFormatting.AQUA));
        }

        tips.add(Component.empty());

        return tips;
    }

    @Override
    public void onTickWhileWorn(ItemStack stack, LivingEntity wearer, EquipmentSlot slot) {
        if(!getCBoolean(stack, wearer.level(), wearer, null)) return;

        for(Holder<MobEffect> p : this.effects) {
            wearer.removeEffect(p);
        }
    }
}
