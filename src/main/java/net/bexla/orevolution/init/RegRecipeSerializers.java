package net.bexla.orevolution.init;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.types.recipe.CoatSmithingRecipe;
import net.bexla.orevolution.content.types.recipe.ProfessionalFireworkRocketRecipe;
import net.bexla.orevolution.content.types.recipe.ReinforceSmithingRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class RegRecipeSerializers {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Orevolution.MODID);

    public static final Supplier<RecipeSerializer<?>> PROFESSIONAL_FIREWORK_ROCKET =
            RECIPE_SERIALIZERS.register(
                    "professional_firework_rocket",
                    ProfessionalFireworkRocketRecipe.Serializer::new
            );

    public static final Supplier<RecipeSerializer<?>> REINFORCE =
            RECIPE_SERIALIZERS.register(
                    "reinforcing",
                    ReinforceSmithingRecipe.Serializer::new
            );

    public static final Supplier<RecipeSerializer<?>> COAT =
            RECIPE_SERIALIZERS.register(
                    "coating",
                    CoatSmithingRecipe.Serializer::new
            );
}