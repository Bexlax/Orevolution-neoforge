package net.bexla.orevolution.content.types.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;

import java.util.LinkedHashMap;
import java.util.Map;

public class ReinforceRecipeBuilder {
    private final RecipeCategory category;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public ReinforceRecipeBuilder(RecipeCategory category) {
        this.category = category;
    }

    public static ReinforceRecipeBuilder smithing(RecipeCategory category) {
        return new ReinforceRecipeBuilder(category);
    }

    public ReinforceRecipeBuilder unlocks(String key, Criterion<?> criterion) {
        criteria.put(key, criterion);
        return this;
    }

    public void save(RecipeOutput output, ResourceLocation id) {
        ensureValid(id);

        Advancement.Builder advancement = output.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);

        criteria.forEach(advancement::addCriterion);

        output.accept(id, ReinforceSmithingRecipe.INSTANCE, advancement.build(id.withPrefix("recipes/" + category.getFolderName() + "/")));
    }

    private void ensureValid(ResourceLocation id) {
        if (criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }
}