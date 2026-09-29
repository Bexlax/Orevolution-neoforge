package net.bexla.orevolution.content;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.category.extensions.vanilla.smithing.IExtendableSmithingRecipeCategory;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import net.bexla.orevolution.content.types.recipe.CoatSmithingRecipe;
import net.bexla.orevolution.content.types.recipe.ReinforceSmithingRecipe;
import net.minecraft.resources.ResourceLocation;

import static net.bexla.orevolution.Orevolution.lc;

@JeiPlugin
public class OrevolutionJEIPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return lc("orevolution");
    }

    @Override
    public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
        IExtendableSmithingRecipeCategory smithingCategory = registration.getSmithingCategory();

        smithingCategory.addExtension(ReinforceSmithingRecipe.class, new ReinforceRecipeCategoryExtension());
        smithingCategory.addExtension(CoatSmithingRecipe.class, new CoatRecipeCategoryExtension());
    }
}