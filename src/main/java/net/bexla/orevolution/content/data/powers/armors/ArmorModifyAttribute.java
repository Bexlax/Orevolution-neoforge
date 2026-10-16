package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.armor.OrevolutionArmorPower;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.function.DoubleUnaryOperator;

public class ArmorModifyAttribute extends OrevolutionArmorPower {
    private final Holder<Attribute> attributeToModify;
    private final AttributeModifier modifier;

    public ArmorModifyAttribute(String tooltipId, @NotNull IConditional conditional, Holder<Attribute> attributeToModify, ResourceLocation id, double amount, AttributeModifier.Operation operation, DoubleUnaryOperator display) {
        super(tooltipId, conditional);
        this.attributeToModify = attributeToModify;
        this.modifier = new AttributeModifier(id, amount, operation);
    }

    public ArmorModifyAttribute(String tooltipId, @NotNull IConditional conditional, Holder<Attribute> attributeToModify, ResourceLocation id, double amount, AttributeModifier.Operation operation) {
        this(tooltipId, conditional, attributeToModify, id, amount, operation, (d) -> d);
    }

    @Override
    public Object[] addTooltipValue() {
        return new Object[] {
                Component.translatable(attributeToModify.value().getDescriptionId()),
                modifierValue()
        };
    }

    private String modifierValue() {
        double value = modifier.amount();
        Component valueComp = attributeToModify.value().toValueComponent(modifier.operation(), value, TooltipFlag.NORMAL);

        return valueComp.getString();
    }

    @Override
    public void onEquip(LivingEntity wearer) {
        if (!(wearer instanceof Player player)) return;

        AttributeInstance attribute = player.getAttribute(attributeToModify);

        if (attribute == null) return;

        if(attribute.hasModifier(modifier.id())) return;

        attribute.addTransientModifier(new AttributeModifier(modifier.id(), modifier.amount(), modifier.operation()));
    }

    @Override
    public void onUnequip(LivingEntity wearer) {
        if (!(wearer instanceof Player player)) return;

        AttributeInstance attribute = player.getAttribute(attributeToModify);

        if (attribute == null) return;

        attribute.removeModifier(modifier.id());
    }
}
