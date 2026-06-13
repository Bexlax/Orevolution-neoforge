package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.armor.OrevolutionArmorPower;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class ArmorModifyAttribute extends OrevolutionArmorPower {
    private final Holder<Attribute> attributeToModify;
    private final ResourceLocation location;
    private final double amount;
    private final AttributeModifier.Operation operation;


    public ArmorModifyAttribute(String tooltipId, @NotNull IConditional conditional, Holder<Attribute> attributeToModify, ResourceLocation location, double amount, AttributeModifier.Operation operation) {
        super(tooltipId, conditional);
        this.attributeToModify = attributeToModify;
        this.location = location;
        this.amount = amount;
        this.operation = operation;
    }

    @Override
    public Object addTooltipValue() {
        return "+" + (int)amount;
    }

    @Override
    public void onEquip(LivingEntity wearer) {
        if (!(wearer instanceof Player player)) return;

        AttributeInstance reach = player.getAttribute(attributeToModify);

        if (reach == null) return;

        if(reach.hasModifier(location)) return;

        reach.addTransientModifier(new AttributeModifier(location, amount, operation));
    }

    @Override
    public void onUnequip(LivingEntity wearer) {
        if (!(wearer instanceof Player player)) return;

        AttributeInstance reach = player.getAttribute(attributeToModify);

        if (reach == null) return;

        reach.removeModifier(location);
    }
}
