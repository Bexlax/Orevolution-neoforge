package net.bexla.orevolution.content;

import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.content.types.recipe.CoatSmithingRecipe;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class CoatRecipeCategoryExtension
        extends OrevolutionSmithingExtension<CoatSmithingRecipe> {

    @Override
    protected ItemStack getTemplate() {
        return new ItemStack(RegItems.COATING_TEMPLATE.get());
    }

    @Override
    protected Ingredient getBase() {
        return Ingredient.of(OrevolutionTags.Items.ACCEPTS_COAT);
    }

    @Override
    protected ItemStack getAddition() {
        return new ItemStack(RegItems.TUNGSTEN_INGOT.get());
    }
}