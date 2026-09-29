package net.bexla.orevolution.events;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.crafting.DataComponentIngredient;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

@EventBusSubscriber(modid = Orevolution.MODID)
public class BrewingSubscriber {
    @SubscribeEvent
    public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addRecipe(
                potionIngredient(Potions.AWKWARD),
                Ingredient.of(RegBlocks.MOONSTONE),
                RegItems.GLOWING_BOTTLE.toStack()
        );

        builder.addRecipe(
                potionIngredient(Potions.STRONG_REGENERATION),
                Ingredient.of(RegBlocks.MOONSTONE),
                RegItems.LIFE_BOTTLE.toStack()
        );

        builder.addRecipe(
                potionIngredient(Potions.STRONG_STRENGTH),
                Ingredient.of(RegBlocks.MOONSTONE),
                RegItems.FIERCE_BOTTLE.toStack()
        );

        builder.addRecipe(
                potionIngredient(Potions.STRONG_SWIFTNESS),
                Ingredient.of(RegBlocks.MOONSTONE),
                RegItems.LIGHTNING_BOTTLE.toStack()
        );

        builder.addMix(
                Potions.AWKWARD,
                Items.BONE,
                RegItems.QUICKNESS
        );

        builder.addMix(
                RegItems.QUICKNESS,
                Items.REDSTONE,
                RegItems.QUICKNESS_LONG
        );
        builder.addMix(
                RegItems.QUICKNESS,
                Items.GLOWSTONE_DUST,
                RegItems.QUICKNESS_STRONG
        );

        builder.addMix(
                Potions.AWKWARD,
                RegItems.PLATINUM_NUGGET.get(),
                RegItems.PURIFICATION
        );

        builder.addMix(
                RegItems.PURIFICATION,
                Items.REDSTONE,
                RegItems.PURIFICATION_LONG
        );
        builder.addMix(
                RegItems.PURIFICATION,
                Items.GLOWSTONE_DUST,
                RegItems.PURIFICATION_STRONG
        );


        builder.addMix(
                Potions.POISON,
                RegItems.PYRITE.get(),
                RegItems.INTOXICATION
        );

        builder.addMix(
                Potions.LONG_POISON,
                RegItems.PYRITE.get(),
                RegItems.INTOXICATION_LONG
        );

        builder.addMix(
                Potions.STRONG_POISON,
                RegItems.PYRITE.get(),
                RegItems.INTOXICATION_STRONG
        );

        builder.addMix(
                RegItems.INTOXICATION,
                Items.REDSTONE,
                RegItems.INTOXICATION_LONG
        );
        builder.addMix(
                RegItems.INTOXICATION,
                Items.GLOWSTONE_DUST,
                RegItems.INTOXICATION_STRONG
        );
    }

    public static Ingredient potionIngredient(Holder<Potion> potion) {
        return DataComponentIngredient.of(
                true,
                DataComponents.POTION_CONTENTS,
                new PotionContents(potion),
                Items.POTION
        );
    }
}
