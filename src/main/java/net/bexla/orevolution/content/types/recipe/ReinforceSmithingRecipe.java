package net.bexla.orevolution.content.types.recipe;

import com.mojang.serialization.MapCodec;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.init.RegDataComponents;
import net.bexla.orevolution.init.RegItems;
import net.bexla.orevolution.init.RegRecipeSerializers;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Unit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.item.crafting.SmithingRecipeInput;
import net.minecraft.world.level.Level;

public class ReinforceSmithingRecipe implements SmithingRecipe {
    public ReinforceSmithingRecipe() {}

    public static final ReinforceSmithingRecipe INSTANCE = new ReinforceSmithingRecipe();

    @Override
    public boolean matches(SmithingRecipeInput input, Level level) {
        ItemStack stack = input.base();

        return input.template().is(RegItems.REINFORCED_TEMPLATE.get())
                && input.addition().is(RegItems.TUNGSTEN_INGOT.get())
                && stack.is(OrevolutionTags.Items.ACCEPTS_REINFORCEMENT)
                && !stack.is(OrevolutionTags.Items.REINFORCE_BLACKLIST)
                && !stack.getOrDefault(RegDataComponents.REINFORCED.get(), false)
                && !stack.getOrDefault(RegDataComponents.COATED.get(), false);
    }

    @Override
    public ItemStack assemble(SmithingRecipeInput input, HolderLookup.Provider registries) {
        ItemStack stack = input.base().copy();

        stack.set(RegDataComponents.REINFORCED.get(), true);

        if (stack.has(DataComponents.MAX_DAMAGE))
            stack.set(RegDataComponents.MAX_DAMAGE_MULTIPLIER.get(), stack.getEquipmentSlot() != null && stack.getEquipmentSlot().isArmor() ? 1.8D : 1.5D);

        stack.set(DataComponents.FIRE_RESISTANT, Unit.INSTANCE);

        return stack;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        ItemStack stack = new ItemStack(Items.IRON_PICKAXE);
        stack.set(RegDataComponents.REINFORCED.get(), true);
        stack.set(RegDataComponents.MAX_DAMAGE_MULTIPLIER.get(), 1.5D);
        stack.set(DataComponents.FIRE_RESISTANT, Unit.INSTANCE);

        return stack;
    }

    @Override
    public boolean isTemplateIngredient(ItemStack stack) {
        return stack.is(RegItems.REINFORCED_TEMPLATE.get());
    }

    @Override
    public boolean isBaseIngredient(ItemStack stack) {
        return stack.is(OrevolutionTags.Items.ACCEPTS_REINFORCEMENT)
                && !stack.is(OrevolutionTags.Items.REINFORCE_BLACKLIST);
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
        return RegRecipeSerializers.REINFORCE.get();
    }

    public static class Serializer implements RecipeSerializer<ReinforceSmithingRecipe> {

        public static final Serializer INSTANCE = new Serializer();

        private static final MapCodec<ReinforceSmithingRecipe> CODEC =
                MapCodec.unit(ReinforceSmithingRecipe.INSTANCE);

        private static final StreamCodec<RegistryFriendlyByteBuf, ReinforceSmithingRecipe> STREAM_CODEC =
                StreamCodec.unit(ReinforceSmithingRecipe.INSTANCE);

        @Override
        public MapCodec<ReinforceSmithingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ReinforceSmithingRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}