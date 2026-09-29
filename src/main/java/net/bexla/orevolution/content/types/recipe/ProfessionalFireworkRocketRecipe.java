package net.bexla.orevolution.content.types.recipe;

import com.mojang.serialization.MapCodec;
import net.bexla.orevolution.init.RegItems;
import net.bexla.orevolution.init.RegRecipeSerializers;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class ProfessionalFireworkRocketRecipe extends CustomRecipe {

    private static final Ingredient ROCKET_INGREDIENT = Ingredient.of(Items.FIREWORK_ROCKET);
    private static final Ingredient CELESTITE_INGREDIENT = Ingredient.of(RegItems.CELESTITE_SHARD.get());

    public ProfessionalFireworkRocketRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        boolean foundRocket = false;
        int shardCount = 0;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);

            if (stack.isEmpty()) {
                continue;
            }

            if (ROCKET_INGREDIENT.test(stack)) {
                if (foundRocket) {
                    return false;
                }

                foundRocket = true;
            } else if (CELESTITE_INGREDIENT.test(stack)) {
                if (++shardCount > 4) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return foundRocket && shardCount >= 1;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack sourceRocket = ItemStack.EMPTY;
        int shardCount = 0;

        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);

            if (stack.isEmpty()) {
                continue;
            }

            if (ROCKET_INGREDIENT.test(stack)) {
                sourceRocket = stack;
            } else if (CELESTITE_INGREDIENT.test(stack)) {
                shardCount++;
            }
        }

        ItemStack result = new ItemStack(RegItems.PROFESSIONAL_FIREWORK_ROCKET.get());

        Fireworks fireworks = sourceRocket.get(DataComponents.FIREWORKS);

        if (fireworks != null) {
            int newFlight = fireworks.flightDuration() + 2 + (shardCount - 1);

            result.set(
                    DataComponents.FIREWORKS,
                    new Fireworks(newFlight, fireworks.explosions())
            );
        }

        return result;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return new ItemStack(RegItems.PROFESSIONAL_FIREWORK_ROCKET.get());
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RegRecipeSerializers.PROFESSIONAL_FIREWORK_ROCKET.get();
    }

    public static class Serializer implements RecipeSerializer<ProfessionalFireworkRocketRecipe> {
        @Override
        public MapCodec<ProfessionalFireworkRocketRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ProfessionalFireworkRocketRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        public static final MapCodec<ProfessionalFireworkRocketRecipe> CODEC =
                CraftingBookCategory.CODEC.fieldOf("category")
                        .xmap(ProfessionalFireworkRocketRecipe::new,
                                ProfessionalFireworkRocketRecipe::category);

        public static final StreamCodec<RegistryFriendlyByteBuf, ProfessionalFireworkRocketRecipe> STREAM_CODEC =
                CraftingBookCategory.STREAM_CODEC.map(
                        ProfessionalFireworkRocketRecipe::new,
                        ProfessionalFireworkRocketRecipe::category
                ).cast();
    }
}