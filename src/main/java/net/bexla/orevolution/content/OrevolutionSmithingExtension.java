package net.bexla.orevolution.content;

import mezz.jei.api.gui.builder.IIngredientAcceptor;
import mezz.jei.api.recipe.category.extensions.vanilla.smithing.ISmithingCategoryExtension;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.item.crafting.SmithingRecipeInput;

public abstract class OrevolutionSmithingExtension<R extends SmithingRecipe> implements ISmithingCategoryExtension<R> {
    protected abstract ItemStack getTemplate();

    protected abstract Ingredient getBase();

    protected abstract ItemStack getAddition();

    @Override
    public <T extends IIngredientAcceptor<T>> void setTemplate(R recipe, T ingredientAcceptor) {
        ingredientAcceptor.addItemStack(getTemplate());
    }

    @Override
    public <T extends IIngredientAcceptor<T>> void setBase(R recipe, T ingredientAcceptor) {
        ingredientAcceptor.addIngredients(getBase());
    }

    @Override
    public <T extends IIngredientAcceptor<T>> void setAddition(R recipe, T ingredientAcceptor) {
        ingredientAcceptor.addItemStack(getAddition());
    }

    @Override
    public <T extends IIngredientAcceptor<T>> void setOutput(R recipe, T ingredientAcceptor) {
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel level = minecraft.level;

        if (level == null) {
            return;
        }

        HolderLookup.Provider registries = level.registryAccess();

        for (ItemStack base : getBase().getItems()) {
            SmithingRecipeInput input = new SmithingRecipeInput(getTemplate(), base.copy(), getAddition());
            ItemStack output = recipe.assemble(input, registries);

            if (!output.isEmpty()) {
                ingredientAcceptor.addItemStack(output);
            }
        }
    }
}