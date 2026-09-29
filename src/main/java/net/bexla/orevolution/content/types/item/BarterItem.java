package net.bexla.orevolution.content.types.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BarterItem extends Item {
    public BarterItem(Properties properties) {
        super(properties);
    }

    public boolean isPiglinCurrency(ItemStack stack) {
        return true;
    }
}
