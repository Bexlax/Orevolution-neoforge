package net.bexla.orevolution.content.types.recipe;

import com.mojang.serialization.MapCodec;
import net.bexla.orevolution.init.RegDataComponents;
import net.bexla.orevolution.init.RegItems;
import net.bexla.orevolution.init.RegRecipeSerializers;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.item.crafting.SmithingRecipeInput;
import net.minecraft.world.level.Level;

public class CoatSmithingRecipe implements SmithingRecipe {
    public CoatSmithingRecipe() {}

    public static final CoatSmithingRecipe INSTANCE = new CoatSmithingRecipe();

    @Override
    public boolean matches(SmithingRecipeInput input, Level level) {
        ItemStack stack = input.base();

        return input.template().is(RegItems.COATING_TEMPLATE.get())
                && input.addition().is(RegItems.TUNGSTEN_INGOT.get())
                && (!stack.getAttributeModifiers().modifiers().isEmpty() || stack.has(DataComponents.ATTRIBUTE_MODIFIERS))
                && !stack.getOrDefault(RegDataComponents.REINFORCED.get(), false)
                && !stack.getOrDefault(RegDataComponents.COATED.get(), false);
    }

    @Override
    public ItemStack assemble(SmithingRecipeInput input, HolderLookup.Provider registries) {
        ItemStack stack = input.base().copy();

        boolean slotExists = stack.getEquipmentSlot() != null;
        boolean isArmor = slotExists && stack.getEquipmentSlot().isArmor();

        stack.set(RegDataComponents.COATED.get(), true);

        if (stack.has(DataComponents.MAX_DAMAGE)) {
            stack.set(RegDataComponents.MAX_DAMAGE_MULTIPLIER.get(), isArmor ? 0.85D : 0.7D);
        }

        return stack;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        ItemStack stack = new ItemStack(Items.IRON_PICKAXE);
        stack.set(RegDataComponents.COATED.get(), true);
        stack.set(RegDataComponents.MAX_DAMAGE_MULTIPLIER.get(), 0.7D);
        return stack;
    }

    @Override
    public boolean isTemplateIngredient(ItemStack stack) {
        return stack.is(RegItems.COATING_TEMPLATE.get());
    }

    @Override
    public boolean isBaseIngredient(ItemStack stack) {
        return !stack.getAttributeModifiers().modifiers().isEmpty() || stack.has(DataComponents.ATTRIBUTE_MODIFIERS);
    }

    @Override
    public boolean isAdditionIngredient(ItemStack stack) {
        return stack.is(RegItems.TUNGSTEN_INGOT.get());
    }

    @Override
    public boolean isIncomplete() {
        return false;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RegRecipeSerializers.COAT.get();
    }

    public static class Serializer implements RecipeSerializer<CoatSmithingRecipe> {

        public static final CoatSmithingRecipe.Serializer INSTANCE = new CoatSmithingRecipe.Serializer();

        private static final MapCodec<CoatSmithingRecipe> CODEC =
                MapCodec.unit(CoatSmithingRecipe.INSTANCE);

        private static final StreamCodec<RegistryFriendlyByteBuf, CoatSmithingRecipe> STREAM_CODEC =
                StreamCodec.unit(CoatSmithingRecipe.INSTANCE);

        @Override
        public MapCodec<CoatSmithingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CoatSmithingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}