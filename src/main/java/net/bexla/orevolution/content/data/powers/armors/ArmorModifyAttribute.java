package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.armor.OrevolutionArmorPower;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

public class ArmorModifyAttribute extends OrevolutionArmorPower {
    private final Holder<Attribute> attributeToModify;
    private final AttributeModifier modifier;

    public ArmorModifyAttribute(String tooltipId, @NotNull IConditional conditional, Holder<Attribute> attributeToModify, ResourceLocation id, double amount, AttributeModifier.Operation operation) {
        super(tooltipId, conditional);
        this.attributeToModify = attributeToModify;
        this.modifier = new AttributeModifier(id, amount, operation);
    }

    @Override
    public Object[] addTooltipValue() {
        return new Object[] {
                attributeToModify.value().toComponent(modifier, TooltipFlag.NORMAL).getString(),
        };
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
