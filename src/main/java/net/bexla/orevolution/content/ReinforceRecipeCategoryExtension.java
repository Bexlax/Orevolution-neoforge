package net.bexla.orevolution.content;

import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.content.types.recipe.ReinforceSmithingRecipe;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class ReinforceRecipeCategoryExtension
        extends OrevolutionSmithingExtension<ReinforceSmithingRecipe> {

    @Override
    protected ItemStack getTemplate() {
        return new ItemStack(RegItems.REINFORCED_TEMPLATE.get());
    }

    @Override
    protected Ingredient getBase() {
        return Ingredient.of(OrevolutionTags.Items.ACCEPTS_REINFORCEMENT);
    }

    @Override
    protected ItemStack getAddition() {
        return new ItemStack(RegItems.TUNGSTEN_INGOT.get());
    }
}