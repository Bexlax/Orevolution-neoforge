package net.bexla.orevolution.content.types.item;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.DyedItemColor;

public class BronzeArmorItem extends ArmorItem {
    private static final int DEFAULT_COLOR = FastColor.ARGB32.color(58, 179, 218);

    public BronzeArmorItem(Holder<ArmorMaterial> material, ArmorItem.Type type, Item.Properties properties) {
        super(material, type, properties.component(DataComponents.DYED_COLOR, new DyedItemColor(DEFAULT_COLOR, false)));
    }
}