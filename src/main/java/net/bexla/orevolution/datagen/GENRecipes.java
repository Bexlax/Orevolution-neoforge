package net.bexla.orevolution.datagen;

import com.notunanancyowen.spears.Spears;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.kinetics.crusher.CrushingRecipe;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe;
import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.content.types.recipe.CoatRecipeBuilder;
import net.bexla.orevolution.content.types.recipe.ProfessionalFireworkRocketRecipe;
import net.bexla.orevolution.content.types.recipe.ReinforceRecipeBuilder;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegConditionSerializer;
import net.bexla.orevolution.init.RegDataComponents;
import net.bexla.orevolution.init.RegItems;
import net.bexla.orevolution.init.modcompat.FDRegistry;
import net.bexla.orevolution.init.modcompat.SBRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Unit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.conditions.NotCondition;
import org.infernalstudios.shieldexp.init.ItemsInit;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static net.bexla.orevolution.Orevolution.lc;

public class GENRecipes extends BlueprintRecipeProvider {
    public GENRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(Orevolution.MODID, output, provider);
    }

    protected static final Ingredient TIN_TOOLS_SMELTABLES = Ingredient.of(
                    RegItems.TIN_SWORD.get(),
                    RegItems.TIN_PICKAXE.get(),
                    RegItems.TIN_AXE.get(),
                    RegItems.TIN_SHOVEL.get(),
                    RegItems.TIN_HOE.get(),
                    RegItems.TIN_HELMET.get(),
                    RegItems.TIN_CHESTPLATE.get(),
                    RegItems.TIN_LEGGINGS.get(),
                    RegItems.TIN_BOOTS.get()
            );
    protected static final Ingredient CASSITERITE_TOOLS_SMELTABLES = Ingredient.of(
                    RegItems.CASSITERITE_SWORD.get(),
                    RegItems.CASSITERITE_PICKAXE.get(),
                    RegItems.CASSITERITE_AXE.get(),
                    RegItems.CASSITERITE_SHOVEL.get(),
                    RegItems.CASSITERITE_HOE.get(),
                    RegItems.CASSITERITE_HELMET.get(),
                    RegItems.CASSITERITE_CHESTPLATE.get(),
                    RegItems.CASSITERITE_LEGGINGS.get(),
                    RegItems.CASSITERITE_BOOTS.get()
    );
    protected static final Ingredient PLATINUM_TOOLS_SMELTABLES = Ingredient.of(
                    RegItems.PLATINUM_SWORD.get(),
                    RegItems.PLATINUM_PICKAXE.get(),
                    RegItems.PLATINUM_AXE.get(),
                    RegItems.PLATINUM_SHOVEL.get(),
                    RegItems.PLATINUM_HOE.get(),
                    RegItems.PLATINUM_HELMET.get(),
                    RegItems.PLATINUM_CHESTPLATE.get(),
                    RegItems.PLATINUM_LEGGINGS.get(),
                    RegItems.PLATINUM_BOOTS.get()
            );
    protected static final Ingredient VERDITE_TOOLS_SMELTABLES = Ingredient.of(
                    RegItems.VERDITE_SWORD.get(),
                    RegItems.VERDITE_PICKAXE.get(),
                    RegItems.VERDITE_AXE.get(),
                    RegItems.VERDITE_SHOVEL.get(),
                    RegItems.VERDITE_HOE.get(),
                    RegItems.VERDITE_HELMET.get(),
                    RegItems.VERDITE_CHESTPLATE.get(),
                    RegItems.VERDITE_LEGGINGS.get(),
                    RegItems.VERDITE_BOOTS.get()
            );

    @Override
    public void buildRecipes(@NotNull RecipeOutput consumer, HolderLookup.@NotNull Provider provider) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.SPECTRAL_ARROW, 16)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.ARROW)
                .define('B', RegItems.GLOWING_BOTTLE)
                .unlockedBy("has_glowing_bottle", has(RegItems.GLOWING_BOTTLE.get())).save(consumer, "spectral_arrow_from_bottle");

        ore(RegItems.TIN_INGOT.get(), Ingredient.of(OrevolutionTags.Items.TIN_ORES), 200, 0.4F, "orevolution:tin_ingot", consumer);
        ore(RegItems.CASSITERITE_INGOT.get(), Ingredient.of(OrevolutionTags.Items.CASSITERITE_ORES), 200, 0.4F, "orevolution:cassiterite_ingot", consumer);
        ore(RegItems.PLATINUM_INGOT.get(), Ingredient.of(OrevolutionTags.Items.PLATINUM_ORES), 200, 1.2F, "orevolution:platinum_ingot", consumer);
        ore(RegItems.TUNGSTEN_INGOT.get(), Ingredient.of(OrevolutionTags.Items.TUNGSTEN_ORES), 300, 1.6F, "orevolution:tungsten_ingot", consumer);

        ore(RegItems.AETHERSTEEL_CHUNK.get(), Ingredient.of(RegBlocks.PRIMITIVE_AETHERROCK), 300, 1.6F, "orevolution:tungsten_ingot", consumer);

        ore(RegItems.TIN_INGOT.get(), Ingredient.of(RegItems.RAW_TIN.get()), 200, 0.4F, "orevolution:tin_ingot", consumer);
        ore(RegItems.TIN_INGOT.get(), Ingredient.of(RegItems.CASSITERITE_INGOT.get()), 200, 0.4F, "orevolution:tin_ingot", consumer);
        ore(RegItems.CASSITERITE_INGOT.get(), Ingredient.of(RegItems.RAW_CASSITERITE.get()), 200, 0.4F, "orevolution:cassiterite_ingot", consumer);
        ore(RegItems.PLATINUM_INGOT.get(), Ingredient.of(RegItems.RAW_PLATINUM.get()), 200, 1.2F, "orevolution:platinum_ingot", consumer);
        ore(RegItems.TUNGSTEN_INGOT.get(), Ingredient.of(RegItems.RAW_TUNGSTEN.get()), 300, 1.6F, "orevolution:tungsten_ingot", consumer);

        oreSmeltingRecipe(RegItems.TIN_INGOT.get(), Ingredient.of(AllItems.CRUSHED_TIN.asItem()), 150, 0.4F, "orevolution:tin_ingot", consumer);
        oreBlastingRecipe(RegItems.TIN_INGOT.get(), Ingredient.of(AllItems.CRUSHED_TIN.asItem()), 75, 0.4F, "orevolution:tin_ingot", consumer);

        oreSmeltingRecipe(RegItems.PLATINUM_INGOT.get(), Ingredient.of(AllItems.CRUSHED_PLATINUM.asItem()), 200, 1.2F, "orevolution:platinum_ingot", consumer);
        oreBlastingRecipe(RegItems.PLATINUM_INGOT.get(), Ingredient.of(AllItems.CRUSHED_PLATINUM.asItem()), 100, 1.2F, "orevolution:platinum_ingot", consumer);

        oreSmeltingRecipe(RegItems.TUNGSTEN_INGOT.get(), Ingredient.of(RegItems.CRUSHED_TUNGSTEN.get()), 300, 1.6F, "orevolution:tungsten_ingot", consumer);
        oreBlastingRecipe(RegItems.TUNGSTEN_INGOT.get(), Ingredient.of(RegItems.CRUSHED_TUNGSTEN.get()), 150, 1.6F, "orevolution:tungsten_ingot", consumer);

        SimpleCookingRecipeBuilder.smelting(TIN_TOOLS_SMELTABLES, RecipeCategory.MISC, RegItems.TIN_NUGGET.get(), 0.1F, 150)
                .unlockedBy("has_tin_pickaxe", has(RegItems.TIN_PICKAXE.get()))
                .unlockedBy("has_tin_shovel", has(RegItems.TIN_SHOVEL.get()))
                .unlockedBy("has_tin_axe", has(RegItems.TIN_AXE.get()))
                .unlockedBy("has_tin_hoe", has(RegItems.TIN_HOE.get()))
                .unlockedBy("has_tin_sword", has(RegItems.TIN_SWORD.get()))
                .save(consumer, lc(getSmeltingRecipeName(RegItems.TIN_NUGGET.get())));

        SimpleCookingRecipeBuilder.blasting(TIN_TOOLS_SMELTABLES, RecipeCategory.MISC, RegItems.TIN_NUGGET.get(), 0.1F, 150)
                .unlockedBy("has_tin_pickaxe", has(RegItems.TIN_PICKAXE.get()))
                .unlockedBy("has_tin_shovel", has(RegItems.TIN_SHOVEL.get()))
                .unlockedBy("has_tin_axe", has(RegItems.TIN_AXE.get()))
                .unlockedBy("has_tin_hoe", has(RegItems.TIN_HOE.get()))
                .unlockedBy("has_tin_sword", has(RegItems.TIN_SWORD.get()))
                .save(consumer, lc(getBlastingRecipeName(RegItems.TIN_NUGGET.get())));

        SimpleCookingRecipeBuilder.smelting(CASSITERITE_TOOLS_SMELTABLES, RecipeCategory.MISC, RegItems.CASSITERITE_NUGGET.get(), 0.1F, 150)
                .unlockedBy("has_cassiterite_pickaxe", has(RegItems.CASSITERITE_PICKAXE.get()))
                .unlockedBy("has_cassiterite_shovel", has(RegItems.CASSITERITE_SHOVEL.get()))
                .unlockedBy("has_cassiterite_axe", has(RegItems.CASSITERITE_AXE.get()))
                .unlockedBy("has_cassiterite_hoe", has(RegItems.CASSITERITE_HOE.get()))
                .unlockedBy("has_cassiterite_sword", has(RegItems.CASSITERITE_SWORD.get()))
                .unlockedBy("has_tin_helmet", has(RegItems.TIN_HELMET.get()))
                .unlockedBy("has_tin_chestplate", has(RegItems.TIN_CHESTPLATE.get()))
                .unlockedBy("has_tin_leggings", has(RegItems.TIN_LEGGINGS.get()))
                .unlockedBy("has_tin_boots", has(RegItems.TIN_BOOTS.get()))
                .save(consumer, lc(getSmeltingRecipeName(RegItems.CASSITERITE_NUGGET.get())));

        SimpleCookingRecipeBuilder.blasting(CASSITERITE_TOOLS_SMELTABLES, RecipeCategory.MISC, RegItems.CASSITERITE_NUGGET.get(), 0.1F, 150)
                .unlockedBy("has_cassiterite_pickaxe", has(RegItems.CASSITERITE_PICKAXE.get()))
                .unlockedBy("has_cassiterite_shovel", has(RegItems.CASSITERITE_SHOVEL.get()))
                .unlockedBy("has_cassiterite_axe", has(RegItems.CASSITERITE_AXE.get()))
                .unlockedBy("has_cassiterite_hoe", has(RegItems.CASSITERITE_HOE.get()))
                .unlockedBy("has_cassiterite_sword", has(RegItems.CASSITERITE_SWORD.get()))
                .unlockedBy("has_cassiterite_helmet", has(RegItems.CASSITERITE_HELMET.get()))
                .unlockedBy("has_cassiterite_chestplate", has(RegItems.CASSITERITE_CHESTPLATE.get()))
                .unlockedBy("has_cassiterite_leggings", has(RegItems.CASSITERITE_LEGGINGS.get()))
                .unlockedBy("has_cassiterite_boots", has(RegItems.CASSITERITE_BOOTS.get()))
                .save(consumer, lc(getBlastingRecipeName(RegItems.CASSITERITE_NUGGET.get())));

        SimpleCookingRecipeBuilder.smelting(PLATINUM_TOOLS_SMELTABLES, RecipeCategory.MISC, RegItems.PLATINUM_NUGGET.get(), 0.1F, 200)
                .unlockedBy("has_platinum_pickaxe", has(RegItems.PLATINUM_PICKAXE.get()))
                .unlockedBy("has_platinum_shovel", has(RegItems.PLATINUM_SHOVEL.get()))
                .unlockedBy("has_platinum_axe", has(RegItems.PLATINUM_AXE.get()))
                .unlockedBy("has_platinum_hoe", has(RegItems.PLATINUM_HOE.get()))
                .unlockedBy("has_platinum_sword", has(RegItems.PLATINUM_SWORD.get()))
                .unlockedBy("has_platinum_helmet", has(RegItems.PLATINUM_HELMET.get()))
                .unlockedBy("has_platinum_chestplate", has(RegItems.PLATINUM_CHESTPLATE.get()))
                .unlockedBy("has_platinum_leggings", has(RegItems.PLATINUM_LEGGINGS.get()))
                .unlockedBy("has_platinum_boots", has(RegItems.PLATINUM_BOOTS.get()))
                .save(consumer, lc(getSmeltingRecipeName(RegItems.PLATINUM_NUGGET.get())));

        SimpleCookingRecipeBuilder.blasting(PLATINUM_TOOLS_SMELTABLES, RecipeCategory.MISC, RegItems.PLATINUM_NUGGET.get(), 0.1F, 200)
                .unlockedBy("has_platinum_pickaxe", has(RegItems.PLATINUM_PICKAXE.get()))
                .unlockedBy("has_platinum_shovel", has(RegItems.PLATINUM_SHOVEL.get()))
                .unlockedBy("has_platinum_axe", has(RegItems.PLATINUM_AXE.get()))
                .unlockedBy("has_platinum_hoe", has(RegItems.PLATINUM_HOE.get()))
                .unlockedBy("has_platinum_sword", has(RegItems.PLATINUM_SWORD.get()))
                .unlockedBy("has_platinum_helmet", has(RegItems.PLATINUM_HELMET.get()))
                .unlockedBy("has_platinum_chestplate", has(RegItems.PLATINUM_CHESTPLATE.get()))
                .unlockedBy("has_platinum_leggings", has(RegItems.PLATINUM_LEGGINGS.get()))
                .unlockedBy("has_platinum_boots", has(RegItems.PLATINUM_BOOTS.get()))
                .save(consumer, lc(getBlastingRecipeName(RegItems.PLATINUM_NUGGET.get())));

        SimpleCookingRecipeBuilder.smelting(VERDITE_TOOLS_SMELTABLES, RecipeCategory.MISC, RegItems.VERDITE_NUGGET.get(), 0.1F, 200)
                .unlockedBy("has_verdite_pickaxe", has(RegItems.VERDITE_PICKAXE.get()))
                .unlockedBy("has_verdite_shovel", has(RegItems.VERDITE_SHOVEL.get()))
                .unlockedBy("has_verdite_axe", has(RegItems.VERDITE_AXE.get()))
                .unlockedBy("has_verdite_hoe", has(RegItems.VERDITE_HOE.get()))
                .unlockedBy("has_verdite_sword", has(RegItems.VERDITE_SWORD.get()))
                .unlockedBy("has_verdite_helmet", has(RegItems.VERDITE_HELMET.get()))
                .unlockedBy("has_verdite_chestplate", has(RegItems.VERDITE_CHESTPLATE.get()))
                .unlockedBy("has_verdite_leggings", has(RegItems.VERDITE_LEGGINGS.get()))
                .unlockedBy("has_verdite_boots", has(RegItems.VERDITE_BOOTS.get()))
                .save(consumer, lc(getSmeltingRecipeName(RegItems.VERDITE_NUGGET.get())));

        SimpleCookingRecipeBuilder.blasting(VERDITE_TOOLS_SMELTABLES, RecipeCategory.MISC, RegItems.VERDITE_NUGGET.get(), 0.1F, 200)
                .unlockedBy("has_verdite_pickaxe", has(RegItems.VERDITE_PICKAXE.get()))
                .unlockedBy("has_verdite_shovel", has(RegItems.VERDITE_SHOVEL.get()))
                .unlockedBy("has_verdite_axe", has(RegItems.VERDITE_AXE.get()))
                .unlockedBy("has_verdite_hoe", has(RegItems.VERDITE_HOE.get()))
                .unlockedBy("has_verdite_sword", has(RegItems.VERDITE_SWORD.get()))
                .unlockedBy("has_verdite_helmet", has(RegItems.VERDITE_HELMET.get()))
                .unlockedBy("has_verdite_chestplate", has(RegItems.VERDITE_CHESTPLATE.get()))
                .unlockedBy("has_verdite_leggings", has(RegItems.VERDITE_LEGGINGS.get()))
                .unlockedBy("has_verdite_boots", has(RegItems.VERDITE_BOOTS.get()))
                .save(consumer, lc(getBlastingRecipeName(RegItems.VERDITE_NUGGET.get())));

        autoCompact(RegBlocks.TIN_BLOCK.get().asItem(), RegItems.TIN_INGOT.get(), consumer);
        autoCompact(RegBlocks.CASSITERITE_BLOCK.get().asItem(), RegItems.CASSITERITE_INGOT.get(), consumer);
        autoCompact(RegBlocks.PLATINUM_BLOCK.get().asItem(), RegItems.PLATINUM_INGOT.get(), consumer);
        autoCompact(RegBlocks.TUNGSTEN_BLOCK.get().asItem(), RegItems.TUNGSTEN_INGOT.get(), consumer);
        autoCompact(RegBlocks.AETHERSTEEL_BLOCK.get().asItem(), RegItems.AETHERSTEEL_INGOT.get(), consumer);
        autoCompact(RegBlocks.BRONZE_BLOCK.get().asItem(), RegItems.BRONZE_ALLOY.get(), consumer);
        autoCompact(RegBlocks.STEEL_BLOCK.get().asItem(), RegItems.STEEL_ALLOY.get(), consumer);
        autoCompact(RegBlocks.VERDITE_BLOCK.get().asItem(), RegItems.VERDITE_INGOT.get(), consumer);

        autoCompact(RegBlocks.RAW_TIN_BLOCK.get().asItem(), RegItems.RAW_TIN.get(), consumer);
        autoCompact(RegBlocks.RAW_CASSITERITE_BLOCK.get().asItem(), RegItems.RAW_CASSITERITE.get(), consumer);
        autoCompact(RegBlocks.RAW_PLATINUM_BLOCK.get().asItem(), RegItems.RAW_PLATINUM.get(), consumer);
        autoCompact(RegBlocks.RAW_TUNGSTEN_BLOCK.get().asItem(), RegItems.RAW_TUNGSTEN.get(), consumer);

        autoCompact(RegBlocks.UNKNOWN_ROOTS_BLOCK.get().asItem(), RegBlocks.UNKNOWN_ROOTS.get().asItem(), consumer);
        autoCompact(RegItems.TIN_INGOT.get(), RegItems.TIN_NUGGET.get(), consumer);
        autoCompact(RegItems.CASSITERITE_INGOT.get(), RegItems.CASSITERITE_NUGGET.get(), consumer);
        autoCompact(RegItems.PLATINUM_INGOT.get(), RegItems.PLATINUM_NUGGET.get(), consumer);
        autoCompact(RegItems.TUNGSTEN_INGOT.get(), RegItems.TUNGSTEN_NUGGET.get(), consumer);
        autoCompact(RegBlocks.LIVINGSTONE_BLOCK.get().asItem(), RegItems.LIVINGSTONE_SHARD.get(), consumer);
        autoCompact(RegItems.VERDITE_INGOT.get(), RegItems.VERDITE_NUGGET.get(), consumer);
        autoCompact(Items.QUARTZ, RegItems.QUARTZ_CHIP.get(), consumer);

        autoCompact(Items.COPPER_INGOT, RegItems.COPPER_NUGGET.get(), consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, RegBlocks.CELESTITE_BLOCK)
                .pattern("AA")
                .pattern("AA")
                .define('A', RegItems.CELESTITE_SHARD.get())
                .unlockedBy(getHasName(RegItems.CELESTITE_SHARD.get()), has(RegItems.CELESTITE_SHARD.get()));

        autoCompact(RegBlocks.PYRITE_BLOCK.get().asItem(), RegItems.PYRITE.get(), consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GUNPOWDER, 3)
                .requires(RegItems.PYRITE.get())
                .requires(Items.CHARCOAL)
                .requires(Tags.Items.GUNPOWDERS)
                .unlockedBy("has_pyrite", has(RegItems.PYRITE.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RegItems.FIERY_ARROW, 2)
                .requires(RegItems.PYRITE.get())
                .requires(Items.ARROW)
                .requires(RegItems.PYRITE.get())
                .requires(Tags.Items.GUNPOWDERS)
                .unlockedBy("has_pyrite", has(RegItems.PYRITE.get())).save(consumer);

        SpecialRecipeBuilder.special(ProfessionalFireworkRocketRecipe::new).save(consumer, lc("professional_firework_rocket"));

        ReinforceRecipeBuilder.smithing(RecipeCategory.TOOLS).unlocks("has_tungsten", has(OrevolutionTags.Items.TUNGSTEN_INGOTS)).save(consumer, lc("reinforce"));
        CoatRecipeBuilder.smithing(RecipeCategory.TOOLS).unlocks("has_tungsten", has(OrevolutionTags.Items.TUNGSTEN_INGOTS)).save(consumer, lc("coat"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.SPECTRAL_ARROW, 16)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.ARROW)
                .define('B', RegItems.GLOWING_BOTTLE)
                .unlockedBy("has_glowing_bottle", has(RegItems.GLOWING_BOTTLE.get())).save(consumer, "spectral_arrow_from_glowing_bottle");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.MOTION_DETECTOR, 1)
                .pattern("CAC")
                .pattern("ABA")
                .pattern(" A ")
                .define('C', RegItems.CELESTITE_SHARD)
                .define('B', Items.REDSTONE)
                .define('A', OrevolutionTags.Items.PLATINUM_INGOTS)
                .unlockedBy("has_celestite_shard", has(RegItems.CELESTITE_SHARD.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.GEO_SCANNER, 1)
                .pattern(" A ")
                .pattern("CBC")
                .pattern("AAA")
                .define('C', Items.AMETHYST_SHARD)
                .define('B', Items.REDSTONE)
                .define('A', RegItems.TIN_INGOT)
                .unlockedBy("has_amethyst_shard", has(Items.AMETHYST_SHARD)).save(consumer);

        makeLanternSmall(RegBlocks.TIN_LANTERN.get(), OrevolutionTags.Items.TIN_INGOTS, "tin", false).save(consumer);
        makeLanternSmall(RegBlocks.BRONZE_LANTERN.get(), OrevolutionTags.Items.BRONZE_INGOTS, "bronze", false).save(consumer);
        makeLantern(RegBlocks.GOLDEN_LANTERN.get(), Tags.Items.INGOTS_GOLD, "gold", false).save(consumer);
        makeLantern(RegBlocks.PLATINUM_LANTERN.get(), OrevolutionTags.Items.PLATINUM_INGOTS, "platinum", false).save(consumer);

        makeLanternSmall(RegBlocks.TIN_SOUL_LANTERN.get(), OrevolutionTags.Items.TIN_INGOTS, "tin", true).save(consumer);
        makeLanternSmall(RegBlocks.BRONZE_SOUL_LANTERN.get(), OrevolutionTags.Items.BRONZE_INGOTS, "bronze", true).save(consumer);
        makeLantern(RegBlocks.GOLDEN_SOUL_LANTERN.get(), Tags.Items.INGOTS_GOLD, "gold", true).save(consumer);
        makeLantern(RegBlocks.PLATINUM_SOUL_LANTERN.get(), OrevolutionTags.Items.PLATINUM_INGOTS, "platinum", true).save(consumer);

        makeBlockSets(
                RegBlocks.AETHERROCK,
                RegBlocks.AETHERROCK_SLAB,
                RegBlocks.AETHERROCK_STAIR,
                RegBlocks.AETHERROCK_WALL,
                consumer
        );
        makeBlockSets(
                RegBlocks.POLISHED_AETHERROCK,
                RegBlocks.POLISHED_AETHERROCK_SLAB,
                RegBlocks.POLISHED_AETHERROCK_STAIR,
                RegBlocks.POLISHED_AETHERROCK_WALL,
                consumer
        );
        makeBlockSets(
                RegBlocks.AETHERROCK_BRICKS,
                RegBlocks.AETHERROCK_BRICKS_SLAB,
                RegBlocks.AETHERROCK_BRICKS_STAIR,
                RegBlocks.AETHERROCK_BRICKS_WALL,
                consumer
        );

        makeBlockSets(
                RegBlocks.RHYOLITE,
                RegBlocks.RHYOLITE_SLAB,
                RegBlocks.RHYOLITE_STAIR,
                RegBlocks.RHYOLITE_WALL,
                consumer
        );
        makeBlockSets(
                RegBlocks.POLISHED_RHYOLITE,
                RegBlocks.POLISHED_RHYOLITE_SLAB,
                RegBlocks.POLISHED_RHYOLITE_STAIR,
                RegBlocks.POLISHED_RHYOLITE_WALL,
                consumer
        );
        makeBlockSets(
                RegBlocks.RHYOLITE_BRICKS,
                RegBlocks.RHYOLITE_BRICKS_SLAB,
                RegBlocks.RHYOLITE_BRICKS_STAIR,
                RegBlocks.RHYOLITE_BRICKS_WALL,
                consumer
        );

        makeBlockSets(
                RegBlocks.CHERT,
                RegBlocks.CHERT_SLAB,
                RegBlocks.CHERT_STAIR,
                RegBlocks.CHERT_WALL,
                consumer
        );
        makeBlockSets(
                RegBlocks.POLISHED_CHERT,
                RegBlocks.POLISHED_CHERT_SLAB,
                RegBlocks.POLISHED_CHERT_STAIR,
                RegBlocks.POLISHED_CHERT_WALL,
                consumer
        );
        makeBlockSets(
                RegBlocks.CHERT_BRICKS,
                RegBlocks.CHERT_BRICKS_SLAB,
                RegBlocks.CHERT_BRICKS_STAIR,
                RegBlocks.CHERT_BRICKS_WALL,
                consumer
        );

        makeBlockSets(
                RegBlocks.LIVINGSTONE_BLOCK,
                RegBlocks.LIVINGSTONE_SLAB,
                RegBlocks.LIVINGSTONE_STAIR,
                RegBlocks.LIVINGSTONE_WALL,
                consumer
        );
        makeBlockSets(
                RegBlocks.POLISHED_LIVINGSTONE,
                RegBlocks.POLISHED_LIVINGSTONE_SLAB,
                RegBlocks.POLISHED_LIVINGSTONE_STAIR,
                RegBlocks.POLISHED_LIVINGSTONE_WALL,
                consumer
        );
        makeBlockSets(
                RegBlocks.LIVINGSTONE_BRICKS,
                RegBlocks.LIVINGSTONE_BRICKS_SLAB,
                RegBlocks.LIVINGSTONE_BRICKS_STAIR,
                RegBlocks.LIVINGSTONE_BRICKS_WALL,
                consumer
        );

        makeBlockSets(
                RegBlocks.CUT_STEEL_BLOCK,
                RegBlocks.CUT_STEEL_SLAB,
                RegBlocks.CUT_STEEL_STAIR,
                null,
                consumer
        );

        makeBlockSets(
                RegBlocks.POLISHED_TUNGSTEN,
                RegBlocks.POLISHED_TUNGSTEN_SLAB,
                RegBlocks.POLISHED_TUNGSTEN_STAIR,
                null,
                consumer
        );
        makeBlockSets(
                RegBlocks.TUNGSTEN_BRICKS,
                RegBlocks.TUNGSTEN_BRICKS_SLAB,
                RegBlocks.TUNGSTEN_BRICKS_STAIR,
                null,
                consumer
        );
        makeBlockSets(
                RegBlocks.CUT_TUNGSTEN_BLOCK,
                RegBlocks.CUT_TUNGSTEN_SLAB,
                RegBlocks.CUT_TUNGSTEN_STAIR,
                null,
                consumer
        );

        makeBlockSets(
                RegBlocks.POLISHED_CELESTITE,
                RegBlocks.POLISHED_CELESTITE_SLAB,
                RegBlocks.POLISHED_CELESTITE_STAIR,
                RegBlocks.POLISHED_CELESTITE_WALL,
                consumer
        );
        makeBlockSets(
                RegBlocks.CELESTITE_BRICKS,
                RegBlocks.CELESTITE_BRICKS_SLAB,
                RegBlocks.CELESTITE_BRICKS_STAIR,
                RegBlocks.CELESTITE_BRICKS_WALL,
                consumer
        );

        makeBlockSets(
                RegBlocks.POLISHED_AMETHYST,
                RegBlocks.POLISHED_AMETHYST_SLAB,
                RegBlocks.POLISHED_AMETHYST_STAIR,
                RegBlocks.POLISHED_AMETHYST_WALL,
                consumer
        );
        makeBlockSets(
                RegBlocks.AMETHYST_BRICKS,
                RegBlocks.AMETHYST_BRICKS_SLAB,
                RegBlocks.AMETHYST_BRICKS_STAIR,
                RegBlocks.AMETHYST_BRICKS_WALL,
                consumer
        );

        quadTransform(RegBlocks.POLISHED_AETHERROCK, RegBlocks.AETHERROCK).save(consumer);
        quadTransform(RegBlocks.POLISHED_RHYOLITE, RegBlocks.RHYOLITE).save(consumer);
        quadTransform(RegBlocks.POLISHED_LIVINGSTONE, RegBlocks.LIVINGSTONE_BLOCK).save(consumer);

        quadTransform(RegBlocks.LIVINGSTONE_BRICKS, RegBlocks.LIVINGSTONE_BLOCK).save(consumer);
        quadTransform(RegBlocks.VERDITE_BRICKS, RegBlocks.LIVINGSTONE_BLOCK).save(consumer);

        makePillar(RegBlocks.AETHERROCK_BRICKS, RegBlocks.AETHERROCK).save(consumer);

        quadTransform(RegBlocks.AETHERROCK_TILES, RegBlocks.POLISHED_AETHERROCK).save(consumer);

        quadTransform(RegBlocks.POLISHED_TUNGSTEN, RegItems.TUNGSTEN_INGOT).save(consumer);
        quadTransform(RegBlocks.TUNGSTEN_BRICKS, RegBlocks.CUT_TUNGSTEN_BLOCK).save(consumer);

        makePillarItem(RegBlocks.STEEL_PILLAR, RegItems.STEEL_ALLOY).save(consumer);

        quadTransform(RegBlocks.BRONZE_TILES, RegItems.BRONZE_ALLOY).save(consumer);

        quadTransform(RegBlocks.TIN_TILES, RegItems.TIN_INGOT).save(consumer);
        quadTransform(RegBlocks.TIN_BRICKS, RegBlocks.TIN_TILES).save(consumer);

        quadTransform(RegBlocks.CUT_TUNGSTEN_BLOCK, RegBlocks.POLISHED_TUNGSTEN).save(consumer);
        quadTransform(RegBlocks.CUT_STEEL_BLOCK, RegItems.STEEL_ALLOY).save(consumer);

        quadTransform(RegBlocks.PLATINUM_TILES, RegItems.PLATINUM_INGOT).save(consumer);
        quadTransform(RegBlocks.GOLD_TILES, () -> Items.GOLD_INGOT).save(consumer);
        makePillarItem(RegBlocks.PLATINUM_PILLAR, RegItems.PLATINUM_INGOT).save(consumer);
        makePillarItem(RegBlocks.GOLD_PILLAR, () -> Items.GOLD_INGOT).save(consumer);

        makeBarsItem(RegBlocks.PLATINUM_BARS, RegItems.PLATINUM_INGOT).save(consumer);
        makeBarsItem(RegBlocks.TUNGSTEN_BARS, RegItems.TUNGSTEN_INGOT).save(consumer);
        makeBarsItem(RegBlocks.BRONZE_BARS, RegItems.BRONZE_ALLOY).save(consumer);
        makeBarsItem(RegBlocks.STEEL_BARS, RegItems.STEEL_ALLOY).save(consumer);
        makeBarsItem(RegBlocks.TIN_BARS, RegItems.TIN_INGOT).save(consumer);
        makeBarsItem(RegBlocks.GOLD_BARS, () -> Items.GOLD_INGOT).save(consumer);

//        stonecutting(RegBlocks.TUNGSTEN_BLOCK, RegBlocks.CHISELED_TUNGSTEN_BRICKS, 12).save(consumer);
//        stonecutting(RegBlocks.TUNGSTEN_BLOCK, RegBlocks.POLISHED_TUNGSTEN, 6).save(consumer);
//        stonecutting(RegBlocks.TUNGSTEN_BLOCK, RegBlocks.CUT_TUNGSTEN_BLOCK, 6).save(consumer);
//        stonecutting(RegBlocks.TUNGSTEN_BLOCK, RegBlocks.TUNGSTEN_BRICKS, 6).save(consumer);
//        stonecutting(RegBlocks.TUNGSTEN_BLOCK, RegBlocks.CHISELED_TUNGSTEN_BLOCK, 12).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RegItems.BRONZE_ALLOY.get(), 1)
                .requires(OrevolutionTags.Items.TIN_INGOTS)
                .requires(OrevolutionTags.Items.TIN_INGOTS)
                .requires(Tags.Items.INGOTS_COPPER)
                .requires(Tags.Items.INGOTS_COPPER)
                .unlockedBy("has_tin_ingot", has(OrevolutionTags.Items.TIN_INGOTS)).save(consumer.withConditions(RegConditionSerializer.Conditions.ALLOW_BRONZE_ALLOY));
        alloyHigh("iron_ingot", RegItems.STEEL_ALLOY.get(), Tags.Items.INGOTS_IRON, Items.COAL, Items.BLAZE_POWDER).save(consumer.withConditions(new NotCondition(CREATE), RegConditionSerializer.Conditions.ALLOW_STEEL_ALLOY));
        alloyHigh("aethersteel_ingot", RegItems.AETHERSTEEL_INGOT.get(), RegItems.AETHERSTEEL_CHUNK.get(), OrevolutionTags.Items.PLATINUM_INGOTS).save(consumer.withConditions(new NotCondition(CREATE)));

        toolSet("tin_ingot",
                RegItems.TIN_SWORD.get(),
                RegItems.TIN_PICKAXE.get(),
                RegItems.TIN_AXE.get(),
                RegItems.TIN_SHOVEL.get(),
                RegItems.TIN_HOE.get(),
                FDRegistry.TIN_KNIFE.get(),
                RegItems.TIN_SHIELD.get(),
                SBRegistry.TIN_SPEAR.get(),
                OrevolutionTags.Items.TIN_INGOTS, consumer);
        armorSet("tin_ingot",
                RegItems.TIN_HELMET.get(),
                RegItems.TIN_CHESTPLATE.get(),
                RegItems.TIN_LEGGINGS.get(),
                RegItems.TIN_BOOTS.get(),
                OrevolutionTags.Items.TIN_INGOTS, consumer);

        toolSet("platinum_ingot",
                RegItems.PLATINUM_SWORD.get(),
                RegItems.PLATINUM_PICKAXE.get(),
                RegItems.PLATINUM_AXE.get(),
                RegItems.PLATINUM_SHOVEL.get(),
                RegItems.PLATINUM_HOE.get(),
                FDRegistry.PLATINUM_KNIFE.get(),
                RegItems.PLATINUM_SHIELD.get(),
                SBRegistry.PLATINUM_SPEAR.get(),
                OrevolutionTags.Items.PLATINUM_INGOTS, consumer);
        armorSet("platinum_ingot",
                RegItems.PLATINUM_HELMET.get(),
                RegItems.PLATINUM_CHESTPLATE.get(),
                RegItems.PLATINUM_LEGGINGS.get(),
                RegItems.PLATINUM_BOOTS.get(),
                OrevolutionTags.Items.PLATINUM_INGOTS, consumer);

        makeToolsExtra(
                RegItems.LIVINGSTONE_SWORD.get(),
                RegItems.LIVINGSTONE_PICKAXE.get(),
                RegItems.LIVINGSTONE_AXE.get(),
                RegItems.LIVINGSTONE_SHOVEL.get(),
                RegItems.LIVINGSTONE_HOE.get(),
                FDRegistry.LIVINGSTONE_KNIFE.get(),
                RegItems.LIVINGSTONE_SHIELD.get(),
                SBRegistry.LIVINGSTONE_SPEAR.get(),
                RegBlocks.LIVINGSTONE_BLOCK.get(), RegItems.LIVINGSTONE_SHARD.get(), consumer);
        armorSet("livingstone_block",
                RegItems.LIVINGSTONE_HELMET.get(),
                RegItems.LIVINGSTONE_CHESTPLATE.get(),
                RegItems.LIVINGSTONE_LEGGINGS.get(),
                RegItems.LIVINGSTONE_BOOTS.get(),
                OrevolutionTags.Items.LIVINGSTONE_BLOCKS, consumer);

        toolSet("verdite_ingot",
                RegItems.VERDITE_SWORD.get(),
                RegItems.VERDITE_PICKAXE.get(),
                RegItems.VERDITE_AXE.get(),
                RegItems.VERDITE_SHOVEL.get(),
                RegItems.VERDITE_HOE.get(),
                FDRegistry.VERDITE_KNIFE.get(),
                RegItems.VERDITE_SHIELD.get(),
                SBRegistry.VERDITE_SPEAR.get(),
                OrevolutionTags.Items.VERDITE_INGOTS, consumer);
        armorSet("verdite_ingot",
                RegItems.VERDITE_HELMET.get(),
                RegItems.VERDITE_CHESTPLATE.get(),
                RegItems.VERDITE_LEGGINGS.get(),
                RegItems.VERDITE_BOOTS.get(),
                OrevolutionTags.Items.VERDITE_INGOTS, consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, RegBlocks.STEEL_DOOR.get())
                .pattern("AA")
                .pattern("AA")
                .pattern("AA")
                .define('A', RegItems.STEEL_ALLOY.get())
                .unlockedBy("has_steel_alloy", has(RegItems.STEEL_ALLOY.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.BASIC_TEMPLATE.get())
                .pattern("ACA")
                .pattern("DBD")
                .pattern("DDD")
                .define('A', Tags.Items.INGOTS_COPPER)
                .define('B', Items.AMETHYST_SHARD)
                .define('C', OrevolutionTags.Items.TIN_INGOTS)
                .define('D', OrevolutionTags.Items.COPPER_NUGGETS)
                .unlockedBy("has_copper_ingot", has(Tags.Items.INGOTS_COPPER)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.DOWNGRADE_TEMPLATE.get())
                .pattern("ACA")
                .pattern("DBD")
                .pattern("DDD")
                .define('A', OrevolutionTags.Items.TIN_INGOTS)
                .define('B', RegItems.CELESTITE_SHARD)
                .define('C', Tags.Items.INGOTS_COPPER)
                .define('D', OrevolutionTags.Items.TIN_NUGGETS)
                .unlockedBy("has_celestite_shards", has(RegItems.CELESTITE_SHARD)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.AETHERSTEEL_TEMPLATE.get(), 2)
                .pattern("ACA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Blocks.END_STONE)
                .define('B', Items.NETHERITE_SCRAP)
                .define('C', RegItems.AETHERSTEEL_TEMPLATE.get())
                .unlockedBy("has_aethersteel_template", has(RegItems.AETHERSTEEL_TEMPLATE.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.REINFORCED_TEMPLATE.get(), 2)
                .pattern("ACA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.GOLD_INGOT)
                .define('B', Items.BLACKSTONE)
                .define('C', RegItems.REINFORCED_TEMPLATE.get())
                .unlockedBy("has_reinforced_template", has(RegItems.REINFORCED_TEMPLATE.get())).save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.COATING_TEMPLATE.get(), 2)
                .pattern("ACA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.GOLD_INGOT)
                .define('B', Items.BLACKSTONE)
                .define('C', RegItems.COATING_TEMPLATE.get())
                .unlockedBy("has_coating_template", has(RegItems.COATING_TEMPLATE.get())).save(consumer);



        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.COATING_TEMPLATE.get())
                .pattern("ACA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', RegItems.PLATINUM_INGOT)
                .define('B', Items.BASALT)
                .define('C', RegItems.REINFORCED_TEMPLATE.get())
                .unlockedBy("has_reinforced_template", has(RegItems.REINFORCED_TEMPLATE.get())).save(consumer, lc("coating_template_from_reinforced_template"));
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.REINFORCED_TEMPLATE.get())
                .pattern("ACA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', RegItems.PLATINUM_INGOT)
                .define('B', Items.BASALT)
                .define('C', RegItems.COATING_TEMPLATE.get())
                .unlockedBy("has_coating_template", has(RegItems.COATING_TEMPLATE.get())).save(consumer, lc("reinforced_template_from_coating_template"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegBlocks.TUNGSTEN_SPONGE.get(), 1)
                .pattern(" A ")
                .pattern("AXA")
                .pattern(" A ")
                .define('A', OrevolutionTags.Items.TUNGSTEN_INGOTS)
                .define('X', Items.SPONGE)
                .unlockedBy("has_sponge", has(Items.SPONGE)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.BRONZE_RADAR.get(), 1)
                .pattern(" A ")
                .pattern("AXA")
                .pattern("AAA")
                .define('A', RegItems.BRONZE_ALLOY.get())
                .define('X', Items.REDSTONE)
                .unlockedBy("has_bronze_alloy", has(RegItems.BRONZE_ALLOY.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.PLATINUM_BERRIES.get(), 1)
                .pattern("AAA")
                .pattern("AXA")
                .pattern("AAA")
                .define('A', RegItems.PLATINUM_NUGGET.get())
                .define('X', Items.SWEET_BERRIES)
                .unlockedBy("has_sweet_berries", has(Items.SWEET_BERRIES)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.VERDITE_SPIDER_EYE.get(), 1)
                .pattern("AAA")
                .pattern("AXA")
                .pattern("AAA")
                .define('A', RegItems.VERDITE_NUGGET.get())
                .define('X', Items.SPIDER_EYE)
                .unlockedBy("has_spider_eye", has(Items.SPIDER_EYE)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.VERDITE_APPLE.get(), 1)
                .pattern("AAA")
                .pattern("AXA")
                .pattern("AAA")
                .define('A', RegItems.VERDITE_INGOT.get())
                .define('X', Items.GOLDEN_APPLE)
                .unlockedBy("has_golden_apple", has(Items.GOLDEN_APPLE)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.PLATINUM_APPLE.get(), 1)
                .pattern("AAA")
                .pattern("AXA")
                .pattern("AAA")
                .define('A', OrevolutionTags.Items.PLATINUM_INGOTS)
                .define('X', Items.APPLE)
                .unlockedBy("has_apple", has(Items.APPLE)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegBlocks.RDX.get(), 4)
                .pattern("ABA")
                .pattern("BXB")
                .pattern("ABA")
                .define('A', RegItems.PYRITE.get())
                .define('B', RegBlocks.CELESTITE_BLOCK.get())
                .define('X', Blocks.TNT)
                .unlockedBy("has_apple", has(Items.APPLE)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.BRONZE_TOTEM.get(), 1)
                .pattern("AAA")
                .pattern(" A ")
                .define('A', RegItems.BRONZE_ALLOY.get())
                .unlockedBy("has_bronze_alloy", has(RegItems.BRONZE_ALLOY.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RegItems.ANCIENT_STEW)
                .requires(RegItems.ANCIENT_FRUIT)
                .requires(RegItems.ANCIENT_FRUIT)
                .requires(RegItems.ANCIENT_FRUIT)
                .requires(RegItems.ANCIENT_FRUIT)
                .requires(Items.BOWL)
                .unlockedBy("has_ancient_fruit", has(RegItems.ANCIENT_FRUIT))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RegItems.ROTTEN_STEW)
                .requires(Items.ROTTEN_FLESH)
                .requires(Items.SPIDER_EYE)
                .requires(Items.BONE)
                .requires(Items.BOWL)
                .unlockedBy("has_spider_eye", has(Items.SPIDER_EYE))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RegItems.ENRICHED_EYE)
                .requires(Tags.Items.NUGGETS_IRON)
                .requires(OrevolutionTags.Items.TIN_NUGGETS)
                .requires(Items.SPIDER_EYE)
                .unlockedBy("has_bronze_totem", has(RegItems.BRONZE_TOTEM))
                .save(consumer);

        ItemStack diamond = RegItems.BRONZE_TOTEM.toStack();
        diamond.set(RegDataComponents.DIAMOND_TOTEM.get(), Unit.INSTANCE);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, diamond)
                .requires(Items.DIAMOND)
                .requires(Items.DIAMOND)
                .requires(RegItems.BRONZE_TOTEM)
                .requires(Items.DIAMOND)
                .requires(Items.DIAMOND)
                .unlockedBy("has_bronze_totem", has(RegItems.BRONZE_TOTEM))
                .save(consumer, lc("bronze_totem_diamond"));

        ItemStack lapis = RegItems.BRONZE_TOTEM.toStack();
        lapis.set(RegDataComponents.LAPIS_TOTEM.get(), Unit.INSTANCE);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, lapis)
                .requires(Items.LAPIS_LAZULI)
                .requires(Items.LAPIS_LAZULI)
                .requires(RegItems.BRONZE_TOTEM)
                .requires(Items.LAPIS_LAZULI)
                .requires(Items.LAPIS_LAZULI)
                .unlockedBy("has_bronze_totem", has(RegItems.BRONZE_TOTEM))
                .save(consumer, lc("bronze_totem_lapis"));

        ItemStack emerald = RegItems.BRONZE_TOTEM.toStack();
        emerald.set(RegDataComponents.EMERALD_TOTEM.get(), Unit.INSTANCE);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, emerald)
                .requires(Items.EMERALD)
                .requires(RegItems.BRONZE_TOTEM)
                .requires(Items.EMERALD)
                .unlockedBy("has_bronze_totem", has(RegItems.BRONZE_TOTEM))
                .save(consumer, lc("bronze_totem_emerald"));

        ItemStack quartz = RegItems.BRONZE_TOTEM.toStack();
        quartz.set(RegDataComponents.QUARTZ_TOTEM.get(), Unit.INSTANCE);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, quartz)
                .requires(Items.QUARTZ)
                .requires(RegItems.BRONZE_TOTEM)
                .requires(Items.QUARTZ)
                .unlockedBy("has_bronze_totem", has(RegItems.BRONZE_TOTEM))
                .save(consumer, lc("bronze_totem_quartz"));

        ItemStack celestite = RegItems.BRONZE_TOTEM.toStack();
        celestite.set(RegDataComponents.CELESTITE_TOTEM.get(), Unit.INSTANCE);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, celestite)
                .requires(RegItems.CELESTITE_SHARD)
                .requires(RegItems.BRONZE_TOTEM).unlockedBy("has_bronze_totem", has(RegItems.BRONZE_TOTEM))
                .save(consumer, lc("bronze_totem_celestite"));

        ItemStack amethyst = RegItems.BRONZE_TOTEM.toStack();
        amethyst.set(RegDataComponents.AMETHYST_TOTEM.get(), Unit.INSTANCE);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, amethyst)
                .requires(Items.AMETHYST_SHARD)
                .requires(RegItems.BRONZE_TOTEM)
                .unlockedBy("has_bronze_totem", has(RegItems.BRONZE_TOTEM))
                .save(consumer, lc("bronze_totem_amethyst"));

        doubleSmithing(Spears.IRON_SPEAR, OrevolutionTags.Items.PLATINUM_INGOTS, Tags.Items.INGOTS_IRON, SBRegistry.PLATINUM_SPEAR.get(), "platinum", "iron", consumer);
        doubleSmithing(ModItems.IRON_KNIFE.get(), OrevolutionTags.Items.PLATINUM_INGOTS, Tags.Items.INGOTS_IRON, FDRegistry.PLATINUM_KNIFE.get(), "platinum", "iron", consumer);
        doubleSmithing(Items.IRON_SWORD, OrevolutionTags.Items.PLATINUM_INGOTS, Tags.Items.INGOTS_IRON, RegItems.PLATINUM_SWORD, "platinum", "iron", consumer);
        doubleSmithing(Items.IRON_PICKAXE, OrevolutionTags.Items.PLATINUM_INGOTS, Tags.Items.INGOTS_IRON, RegItems.PLATINUM_PICKAXE, "platinum", "iron", consumer);
        doubleSmithing(Items.IRON_SHOVEL, OrevolutionTags.Items.PLATINUM_INGOTS, Tags.Items.INGOTS_IRON, RegItems.PLATINUM_SHOVEL, "platinum", "iron", consumer);
        doubleSmithing(Items.IRON_HOE, OrevolutionTags.Items.PLATINUM_INGOTS, Tags.Items.INGOTS_IRON, RegItems.PLATINUM_HOE, "platinum", "iron", consumer);
        doubleSmithing(Items.IRON_AXE, OrevolutionTags.Items.PLATINUM_INGOTS, Tags.Items.INGOTS_IRON, RegItems.PLATINUM_AXE, "platinum", "iron", consumer);
        doubleSmithing(Items.IRON_HELMET, OrevolutionTags.Items.PLATINUM_INGOTS, Tags.Items.INGOTS_IRON, RegItems.PLATINUM_HELMET, "platinum", "iron", consumer);
        doubleSmithing(Items.IRON_CHESTPLATE, OrevolutionTags.Items.PLATINUM_INGOTS, Tags.Items.INGOTS_IRON, RegItems.PLATINUM_CHESTPLATE, "platinum", "iron", consumer);
        doubleSmithing(Items.IRON_LEGGINGS, OrevolutionTags.Items.PLATINUM_INGOTS, Tags.Items.INGOTS_IRON, RegItems.PLATINUM_LEGGINGS, "platinum", "iron", consumer);
        doubleSmithing(Items.IRON_BOOTS, OrevolutionTags.Items.PLATINUM_INGOTS, Tags.Items.INGOTS_IRON, RegItems.PLATINUM_BOOTS, "platinum", "iron", consumer);

        doubleSmithing(SBRegistry.TIN_SPEAR.get(), Tags.Items.INGOTS_IRON, OrevolutionTags.Items.TIN_INGOTS, Spears.IRON_SPEAR, "iron", "tin", consumer);
        doubleSmithing(FDRegistry.TIN_KNIFE.get(), Tags.Items.INGOTS_IRON, OrevolutionTags.Items.TIN_INGOTS, ModItems.IRON_KNIFE.get(), "iron", "tin", consumer);
        doubleSmithing(RegItems.TIN_SWORD, Tags.Items.INGOTS_IRON, OrevolutionTags.Items.TIN_INGOTS, Items.IRON_SWORD, "iron", "tin", consumer);
        doubleSmithing(RegItems.TIN_PICKAXE, Tags.Items.INGOTS_IRON, OrevolutionTags.Items.TIN_INGOTS, Items.IRON_PICKAXE, "iron", "tin", consumer);
        doubleSmithing(RegItems.TIN_SHOVEL, Tags.Items.INGOTS_IRON, OrevolutionTags.Items.TIN_INGOTS, Items.IRON_SHOVEL, "iron", "tin", consumer);
        doubleSmithing(RegItems.TIN_HOE, Tags.Items.INGOTS_IRON, OrevolutionTags.Items.TIN_INGOTS, Items.IRON_HOE, "iron", "tin", consumer);
        doubleSmithing(RegItems.TIN_AXE, Tags.Items.INGOTS_IRON, OrevolutionTags.Items.TIN_INGOTS, Items.IRON_AXE, "iron", "tin", consumer);
        doubleSmithing(Items.CHAINMAIL_HELMET, Tags.Items.INGOTS_IRON, OrevolutionTags.Items.TIN_INGOTS, Items.IRON_HELMET, "iron", "tin", consumer);
        doubleSmithing(Items.CHAINMAIL_CHESTPLATE, Tags.Items.INGOTS_IRON, OrevolutionTags.Items.TIN_INGOTS, Items.IRON_CHESTPLATE, "iron", "tin", consumer);
        doubleSmithing(Items.CHAINMAIL_LEGGINGS, Tags.Items.INGOTS_IRON, OrevolutionTags.Items.TIN_INGOTS, Items.IRON_LEGGINGS, "iron", "tin", consumer);
        doubleSmithing(Items.CHAINMAIL_BOOTS, Tags.Items.INGOTS_IRON, OrevolutionTags.Items.TIN_INGOTS, Items.IRON_BOOTS, "iron", "tin", consumer);

        doubleSmithing(SBRegistry.TIN_SPEAR.get(), OrevolutionTags.Items.CASSITERITE_INGOTS, OrevolutionTags.Items.TIN_INGOTS, SBRegistry.CASSITERITE_SPEAR.get(), "cassiterite", "tin", consumer);
        doubleSmithing(FDRegistry.TIN_KNIFE.get(), OrevolutionTags.Items.CASSITERITE_INGOTS, OrevolutionTags.Items.TIN_INGOTS , FDRegistry.CASSITERITE_KNIFE.get(), "cassiterite", "tin", consumer);
        doubleSmithing(RegItems.TIN_SWORD, OrevolutionTags.Items.CASSITERITE_INGOTS, OrevolutionTags.Items.TIN_INGOTS , RegItems.CASSITERITE_SWORD, "cassiterite", "tin", consumer);
        doubleSmithing(RegItems.TIN_PICKAXE, OrevolutionTags.Items.CASSITERITE_INGOTS, OrevolutionTags.Items.TIN_INGOTS , RegItems.CASSITERITE_PICKAXE, "cassiterite", "tin", consumer);
        doubleSmithing(RegItems.TIN_SHOVEL, OrevolutionTags.Items.CASSITERITE_INGOTS, OrevolutionTags.Items.TIN_INGOTS , RegItems.CASSITERITE_SHOVEL, "cassiterite", "tin", consumer);
        doubleSmithing(RegItems.TIN_HOE, OrevolutionTags.Items.CASSITERITE_INGOTS, OrevolutionTags.Items.TIN_INGOTS , RegItems.CASSITERITE_HOE, "cassiterite", "tin", consumer);
        doubleSmithing(RegItems.TIN_AXE, OrevolutionTags.Items.CASSITERITE_INGOTS, OrevolutionTags.Items.TIN_INGOTS , RegItems.CASSITERITE_AXE, "cassiterite", "tin", consumer);
        doubleSmithing(RegItems.TIN_HELMET, OrevolutionTags.Items.CASSITERITE_INGOTS, OrevolutionTags.Items.TIN_INGOTS , RegItems.CASSITERITE_HELMET, "cassiterite", "tin", consumer);
        doubleSmithing(RegItems.TIN_CHESTPLATE, OrevolutionTags.Items.CASSITERITE_INGOTS, OrevolutionTags.Items.TIN_INGOTS , RegItems.CASSITERITE_CHESTPLATE, "cassiterite", "tin", consumer);
        doubleSmithing(RegItems.TIN_LEGGINGS, OrevolutionTags.Items.CASSITERITE_INGOTS, OrevolutionTags.Items.TIN_INGOTS , RegItems.CASSITERITE_LEGGINGS, "cassiterite", "tin", consumer);
        doubleSmithing(RegItems.TIN_BOOTS, OrevolutionTags.Items.CASSITERITE_INGOTS, OrevolutionTags.Items.TIN_INGOTS , RegItems.CASSITERITE_BOOTS, "cassiterite", "tin", consumer);

        doubleSmithing(SBRegistry.PLATINUM_SPEAR.get(), Items.DIAMOND, OrevolutionTags.Items.PLATINUM_INGOTS, Spears.DIAMOND_SPEAR, "platinum", consumer);
        doubleSmithing(FDRegistry.PLATINUM_KNIFE.get(), Items.DIAMOND, OrevolutionTags.Items.PLATINUM_INGOTS, ModItems.DIAMOND_KNIFE.get(), "platinum", consumer);
        doubleSmithing(RegItems.PLATINUM_SWORD, Items.DIAMOND, OrevolutionTags.Items.PLATINUM_INGOTS, Items.DIAMOND_SWORD, "platinum", consumer);
        doubleSmithing(RegItems.PLATINUM_PICKAXE, Items.DIAMOND, OrevolutionTags.Items.PLATINUM_INGOTS, Items.DIAMOND_PICKAXE, "platinum", consumer);
        doubleSmithing(RegItems.PLATINUM_SHOVEL, Items.DIAMOND, OrevolutionTags.Items.PLATINUM_INGOTS, Items.DIAMOND_SHOVEL, "platinum", consumer);
        doubleSmithing(RegItems.PLATINUM_HOE, Items.DIAMOND, OrevolutionTags.Items.PLATINUM_INGOTS, Items.DIAMOND_HOE, "platinum", consumer);
        doubleSmithing(RegItems.PLATINUM_AXE, Items.DIAMOND, OrevolutionTags.Items.PLATINUM_INGOTS, Items.DIAMOND_AXE, "platinum", consumer);
        doubleSmithing(RegItems.PLATINUM_HELMET, Items.DIAMOND, OrevolutionTags.Items.PLATINUM_INGOTS, Items.DIAMOND_HELMET, "platinum", consumer);
        doubleSmithing(RegItems.PLATINUM_CHESTPLATE, Items.DIAMOND, OrevolutionTags.Items.PLATINUM_INGOTS, Items.DIAMOND_CHESTPLATE, "platinum", consumer);
        doubleSmithing(RegItems.PLATINUM_LEGGINGS, Items.DIAMOND, OrevolutionTags.Items.PLATINUM_INGOTS, Items.DIAMOND_LEGGINGS, "platinum", consumer);
        doubleSmithing(RegItems.PLATINUM_BOOTS, Items.DIAMOND, OrevolutionTags.Items.PLATINUM_INGOTS, Items.DIAMOND_BOOTS, "platinum", consumer);

        doubleSmithing(RegItems.STEEL_HEAVY_SHIELD, OrevolutionTags.Items.STEEL_INGOTS, Tags.Items.INGOTS_IRON, ItemsInit.IRON_SHIELD.get(), "steel", "iron", consumer);
        doubleSmithing(Items.IRON_SWORD, OrevolutionTags.Items.STEEL_INGOTS, Tags.Items.INGOTS_IRON, RegItems.STEEL_HEAVYWORK_SWORD, "steel", "iron", consumer);
        doubleSmithing(Items.IRON_PICKAXE, OrevolutionTags.Items.STEEL_INGOTS, Tags.Items.INGOTS_IRON, RegItems.STEEL_HEAVYWORK_PICKAXE, "steel", "iron", consumer);
        doubleSmithing(Items.IRON_AXE, OrevolutionTags.Items.STEEL_INGOTS, Tags.Items.INGOTS_IRON, RegItems.STEEL_HEAVYWORK_AXE, "steel", "iron", consumer);
        doubleSmithing(Items.IRON_SHOVEL, OrevolutionTags.Items.STEEL_INGOTS, Tags.Items.INGOTS_IRON, RegItems.STEEL_HEAVYWORK_SHOVEL, "steel", "iron", consumer);
        doubleSmithing(Items.IRON_HOE, OrevolutionTags.Items.STEEL_INGOTS, Tags.Items.INGOTS_IRON, RegItems.STEEL_HEAVYWORK_HOE, "steel", "iron", consumer);
        doubleSmithing(Items.ANVIL, OrevolutionTags.Items.STEEL_INGOTS, Tags.Items.INGOTS_IRON, RegBlocks.STEEL_ANVIL.get().asItem(), "steel", "iron", consumer);

        doubleSmithing(Items.LEATHER_HELMET, OrevolutionTags.Items.BRONZE_INGOTS, Items.LEATHER, RegItems.BRONZE_DIVING_HELMET, "bronze", consumer);
        doubleSmithing(Items.LEATHER_CHESTPLATE, OrevolutionTags.Items.BRONZE_INGOTS, Items.LEATHER, RegItems.BRONZE_DIVING_CHESTPLATE, "bronze", consumer);
        doubleSmithing(Items.LEATHER_LEGGINGS, OrevolutionTags.Items.BRONZE_INGOTS, Items.LEATHER, RegItems.BRONZE_DIVING_LEGGINGS, "bronze", consumer);
        doubleSmithing(Items.LEATHER_BOOTS, OrevolutionTags.Items.BRONZE_INGOTS, Items.LEATHER, RegItems.BRONZE_DIVING_BOOTS, "bronze", consumer);

        doubleSmithing(RegItems.TIN_HELMET, OrevolutionTags.Items.BRONZE_INGOTS, OrevolutionTags.Items.TIN_INGOTS , RegItems.BRONZE_HELMET, "bronze", "tin", consumer);
        doubleSmithing(RegItems.TIN_CHESTPLATE, OrevolutionTags.Items.BRONZE_INGOTS, OrevolutionTags.Items.TIN_INGOTS , RegItems.BRONZE_CHESTPLATE, "bronze", "tin", consumer);
        doubleSmithing(RegItems.TIN_LEGGINGS, OrevolutionTags.Items.BRONZE_INGOTS, OrevolutionTags.Items.TIN_INGOTS , RegItems.BRONZE_LEGGINGS, "bronze", "tin", consumer);
        doubleSmithing(RegItems.TIN_BOOTS, OrevolutionTags.Items.BRONZE_INGOTS, OrevolutionTags.Items.TIN_INGOTS , RegItems.BRONZE_BOOTS, "bronze", "tin", consumer);

        doubleSmithing(SBRegistry.PLATINUM_SPEAR.get(), RegBlocks.MOONSTONE, OrevolutionTags.Items.PLATINUM_INGOTS, SBRegistry.MOONSTONE_SPEAR.get(), "platinum", consumer);
        doubleSmithing(FDRegistry.PLATINUM_KNIFE.get(), RegBlocks.MOONSTONE, OrevolutionTags.Items.PLATINUM_INGOTS, FDRegistry.MOONSTONE_KNIFE.get(), "platinum", consumer);
        doubleSmithing(RegItems.PLATINUM_SHIELD.get(), RegBlocks.MOONSTONE, OrevolutionTags.Items.PLATINUM_INGOTS, RegItems.MOONSTONE_SHIELD, "platinum", consumer);
        doubleSmithing(RegItems.PLATINUM_SWORD, RegBlocks.MOONSTONE, OrevolutionTags.Items.PLATINUM_INGOTS, RegItems.MOONSTONE_SWORD, "platinum", consumer);
        doubleSmithing(RegItems.PLATINUM_PICKAXE, RegBlocks.MOONSTONE, OrevolutionTags.Items.PLATINUM_INGOTS, RegItems.MOONSTONE_PICKAXE, "platinum", consumer);
        doubleSmithing(RegItems.PLATINUM_SHOVEL, RegBlocks.MOONSTONE, OrevolutionTags.Items.PLATINUM_INGOTS, RegItems.MOONSTONE_SHOVEL, "platinum", consumer);
        doubleSmithing(RegItems.PLATINUM_HOE, RegBlocks.MOONSTONE, OrevolutionTags.Items.PLATINUM_INGOTS, RegItems.MOONSTONE_HOE, "platinum", consumer);
        doubleSmithing(RegItems.PLATINUM_AXE, RegBlocks.MOONSTONE, OrevolutionTags.Items.PLATINUM_INGOTS, RegItems.MOONSTONE_AXE, "platinum", consumer);
        doubleSmithing(RegItems.PLATINUM_HELMET, RegBlocks.MOONSTONE, OrevolutionTags.Items.PLATINUM_INGOTS, RegItems.MOONSTONE_HELMET, "platinum", consumer);
        doubleSmithing(RegItems.PLATINUM_CHESTPLATE, RegBlocks.MOONSTONE, OrevolutionTags.Items.PLATINUM_INGOTS, RegItems.MOONSTONE_CHESTPLATE, "platinum", consumer);
        doubleSmithing(RegItems.PLATINUM_LEGGINGS, RegBlocks.MOONSTONE, OrevolutionTags.Items.PLATINUM_INGOTS, RegItems.MOONSTONE_LEGGINGS, "platinum", consumer);
        doubleSmithing(RegItems.PLATINUM_BOOTS, RegBlocks.MOONSTONE, OrevolutionTags.Items.PLATINUM_INGOTS, RegItems.MOONSTONE_BOOTS, "platinum", consumer);

        doubleSmithing(ItemsInit.GOLDEN_SHIELD.get(), OrevolutionTags.Items.TUNGSTEN_INGOTS, Tags.Items.INGOTS_GOLD, SBRegistry.TUNGSTEN_SPEAR.get(), "tungsten", "gold", consumer);
        doubleSmithing(ModItems.GOLDEN_KNIFE.get(), OrevolutionTags.Items.TUNGSTEN_INGOTS, Tags.Items.INGOTS_GOLD, FDRegistry.TUNGSTEN_KNIFE.get(), "tungsten", "gold", consumer);
        doubleSmithing(Items.GOLDEN_SWORD, OrevolutionTags.Items.TUNGSTEN_INGOTS, Tags.Items.INGOTS_GOLD, RegItems.TUNGSTEN_SWORD, "tungsten", "gold", consumer);
        doubleSmithing(Items.GOLDEN_PICKAXE, OrevolutionTags.Items.TUNGSTEN_INGOTS, Tags.Items.INGOTS_GOLD, RegItems.TUNGSTEN_PICKAXE, "tungsten", "gold", consumer);
        doubleSmithing(Items.GOLDEN_SHOVEL, OrevolutionTags.Items.TUNGSTEN_INGOTS, Tags.Items.INGOTS_GOLD, RegItems.TUNGSTEN_SHOVEL, "tungsten", "gold", consumer);
        doubleSmithing(Items.GOLDEN_HOE, OrevolutionTags.Items.TUNGSTEN_INGOTS, Tags.Items.INGOTS_GOLD, RegItems.TUNGSTEN_HOE, "tungsten", "gold", consumer);
        doubleSmithing(Items.GOLDEN_AXE, OrevolutionTags.Items.TUNGSTEN_INGOTS, Tags.Items.INGOTS_GOLD, RegItems.TUNGSTEN_AXE, "tungsten", "gold", consumer);
        doubleSmithing(Items.GOLDEN_HELMET, OrevolutionTags.Items.TUNGSTEN_INGOTS, Tags.Items.INGOTS_GOLD, RegItems.TUNGSTEN_HELMET, "tungsten", "gold", consumer);
        doubleSmithing(Items.GOLDEN_CHESTPLATE, OrevolutionTags.Items.TUNGSTEN_INGOTS, Tags.Items.INGOTS_GOLD, RegItems.TUNGSTEN_CHESTPLATE, "tungsten", "gold", consumer);
        doubleSmithing(Items.GOLDEN_LEGGINGS, OrevolutionTags.Items.TUNGSTEN_INGOTS, Tags.Items.INGOTS_GOLD, RegItems.TUNGSTEN_LEGGINGS, "tungsten", "gold", consumer);
        doubleSmithing(Items.GOLDEN_BOOTS, OrevolutionTags.Items.TUNGSTEN_INGOTS, Tags.Items.INGOTS_GOLD, RegItems.TUNGSTEN_BOOTS, "tungsten", "gold", consumer);

        smithingAether(ModItems.NETHERITE_KNIFE.get(), FDRegistry.AETHERSTEEL_KNIFE.get()).save(consumer, lc("aethersteel_from_knife"));
        smithingAether(ItemsInit.NETHERITE_SHIELD.get(), RegItems.AETHERSTEEL_SHIELD).save(consumer, lc("aethersteel_from_shield"));
        smithingAether(Items.NETHERITE_SWORD, RegItems.AETHERSTEEL_SWORD).save(consumer, lc("aethersteel_from_sword"));
        smithingAether(Items.NETHERITE_PICKAXE, RegItems.AETHERSTEEL_PICKAXE).save(consumer, lc("aethersteel_from_pickaxe"));
        smithingAether(Items.NETHERITE_AXE, RegItems.AETHERSTEEL_AXE).save(consumer, lc("aethersteel_from_axe"));
        smithingAether(Items.NETHERITE_SHOVEL, RegItems.AETHERSTEEL_SHOVEL).save(consumer, lc("aethersteel_from_shovel"));
        smithingAether(Items.NETHERITE_HOE, RegItems.AETHERSTEEL_HOE).save(consumer, lc("aethersteel_from_hoe"));
        smithingAether(Items.NETHERITE_HELMET, RegItems.AETHERSTEEL_HELMET).save(consumer, lc("aethersteel_helmet_from_smithing"));
        smithingAether(Items.NETHERITE_CHESTPLATE, RegItems.AETHERSTEEL_CHESTPLATE).save(consumer, lc("aethersteel_chestplate_from_smithing"));
        smithingAether(Items.NETHERITE_LEGGINGS, RegItems.AETHERSTEEL_LEGGINGS).save(consumer, lc("aethersteel_leggings_from_smithing"));
        smithingAether(Items.NETHERITE_BOOTS, RegItems.AETHERSTEEL_BOOTS).save(consumer, lc("aethersteel_boots_from_smithing"));

        crushedToRaw("quartzolite", Ingredient.of(RegBlocks.QUARTZOLITE), RegItems.QUARTZ_CHIP, Blocks.DIORITE::asItem).build(consumer);
        processing(CrushingRecipe::new, "rhyolite")
                .require(Ingredient.of(RegBlocks.RHYOLITE.get()))
                .output(0.85f, RegItems.QUARTZ_CHIP, 2)
                .output(0.3f, Items.EMERALD, 1)
                .duration(200)
                .build(consumer);
        processing(CrushingRecipe::new, "chert")
                .require(Ingredient.of(RegBlocks.CHERT.get()))
                .output(RegItems.QUARTZ_CHIP, 2)
                .output(0.45F, RegItems.TIN_NUGGET, 1)
                .duration(200)
                .build(consumer);

        crushedToRaw("crushed_tungsten", Ingredient.of(OrevolutionTags.Items.TUNGSTEN_ORES), RegItems.CRUSHED_TUNGSTEN, Blocks.NETHERRACK::asItem).build(consumer);
        processing(CrushingRecipe::new, "raw_crushed_tungsten")
                .require(Ingredient.of(RegItems.RAW_TUNGSTEN.get()))
                .output(RegItems.CRUSHED_TUNGSTEN.get(), 1)
                .output(0.75f, AllItems.EXP_NUGGET.get(), 1)
                .duration(200)
                .build(consumer);
        processing(CrushingRecipe::new, "raw_block_crushed_tungsten")
                .require(Ingredient.of(RegBlocks.RAW_TUNGSTEN_BLOCK.get()))
                .output(RegItems.CRUSHED_TUNGSTEN.get(), 9)
                .output(0.75f, AllItems.EXP_NUGGET.get(), 9)
                .duration(300)
                .build(consumer);

        crushedToRaw("crushed_aethersteel", Ingredient.of(RegBlocks.PRIMITIVE_AETHERROCK.get()), RegItems.CRUSHED_AETHERSTEEL, Blocks.END_STONE::asItem).build(consumer);
        processing(CrushingRecipe::new, "aetherrock_crushed")
                .require(Ingredient.of(RegBlocks.AETHERROCK.get()))
                .output(0.30f, RegItems.CRUSHED_AETHERSTEEL.get(), 1)
                .output(0.15f, RegItems.CRUSHED_AETHERSTEEL.get(), 1)
                .output(0.1f, AllItems.EXP_NUGGET.get())
                .duration(300)
                .build(consumer);

        processing(CrushingRecipe::new, "end_xp_block_crushed")
                .require(RegBlocks.END_XP_ORE.get())
                .output(AllItems.EXP_NUGGET.get(), 3)
                .output(.75f, AllItems.EXP_NUGGET.get())
                .output(.35f, AllItems.EXP_NUGGET.get(), 2)
                .output(.12f, Blocks.END_STONE)
                .duration(200)
                .build(consumer);
        processing(CrushingRecipe::new, "nether_xp_block_crushed")
                .require(RegBlocks.NETHER_XP_ORE.get())
                .output(AllItems.EXP_NUGGET.get(), 2)
                .output(0.65f, AllItems.EXP_NUGGET.get())
                .output(0.25f, AllItems.EXP_NUGGET.get(), 2)
                .output(0.12f, Blocks.NETHERRACK)
                .duration(200)
                .build(consumer);

        processing(MixingRecipe::new, "aethersteel_ingot")
                .output(RegItems.AETHERSTEEL_INGOT.get(), 1)
                .require(OrevolutionTags.Items.PLATINUM_INGOTS)
                .require(OrevolutionTags.Items.PLATINUM_INGOTS)
                .require(OrevolutionTags.Items.PLATINUM_INGOTS)
                .require(OrevolutionTags.Items.PLATINUM_INGOTS)
                .require(RegItems.AETHERSTEEL_CHUNK.get())
                .require(RegItems.AETHERSTEEL_CHUNK.get())
                .require(RegItems.AETHERSTEEL_CHUNK.get())
                .require(RegItems.AETHERSTEEL_CHUNK.get())
                .requiresHeat(HeatCondition.SUPERHEATED)
                .build(consumer);

        processing(MixingRecipe::new, "aethersteel_ingot_crushed_aethersteel")
                .output(RegItems.AETHERSTEEL_INGOT.get(), 1)
                .require(OrevolutionTags.Items.PLATINUM_INGOTS)
                .require(OrevolutionTags.Items.PLATINUM_INGOTS)
                .require(OrevolutionTags.Items.PLATINUM_INGOTS)
                .require(OrevolutionTags.Items.PLATINUM_INGOTS)
                .require(RegItems.CRUSHED_AETHERSTEEL.get())
                .require(RegItems.CRUSHED_AETHERSTEEL.get())
                .require(RegItems.CRUSHED_AETHERSTEEL.get())
                .require(RegItems.CRUSHED_AETHERSTEEL.get())
                .requiresHeat(HeatCondition.SUPERHEATED)
                .build(consumer);

        processing(MixingRecipe::new, "aethersteel_ingot_crushed_platinum")
                .output(RegItems.AETHERSTEEL_INGOT.get(), 1)
                .require(AllItems.CRUSHED_PLATINUM.asItem())
                .require(AllItems.CRUSHED_PLATINUM.asItem())
                .require(AllItems.CRUSHED_PLATINUM.asItem())
                .require(AllItems.CRUSHED_PLATINUM.asItem())
                .require(RegItems.AETHERSTEEL_CHUNK.get())
                .require(RegItems.AETHERSTEEL_CHUNK.get())
                .require(RegItems.AETHERSTEEL_CHUNK.get())
                .require(RegItems.AETHERSTEEL_CHUNK.get())
                .requiresHeat(HeatCondition.SUPERHEATED)
                .build(consumer);

        processing(MixingRecipe::new, "aethersteel_ingot_both_crushed")
                .output(RegItems.AETHERSTEEL_INGOT.get(), 1)
                .require(AllItems.CRUSHED_PLATINUM.asItem())
                .require(AllItems.CRUSHED_PLATINUM.asItem())
                .require(AllItems.CRUSHED_PLATINUM.asItem())
                .require(AllItems.CRUSHED_PLATINUM.asItem())
                .require(RegItems.CRUSHED_AETHERSTEEL.get())
                .require(RegItems.CRUSHED_AETHERSTEEL.get())
                .require(RegItems.CRUSHED_AETHERSTEEL.get())
                .require(RegItems.CRUSHED_AETHERSTEEL.get())
                .requiresHeat(HeatCondition.SUPERHEATED)
                .build(consumer);

        processing(MixingRecipe::new, "steel_alloy")
                .output(RegItems.STEEL_ALLOY.get(), 1)
                .require(Tags.Items.INGOTS_IRON)
                .require(Tags.Items.INGOTS_IRON)
                .require(Tags.Items.INGOTS_IRON)
                .require(Items.COAL)
                .require(Items.COAL)
                .require(Items.COAL)
                .requiresHeat(HeatCondition.HEATED)
                .build(consumer.withConditions(RegConditionSerializer.Conditions.ALLOW_STEEL_ALLOY));

        processing(MixingRecipe::new, "bronze_alloy")
                .output(RegItems.BRONZE_ALLOY.get(), 1)
                .require(Tags.Items.INGOTS_COPPER)
                .require(OrevolutionTags.Items.TIN_INGOTS)
                .requiresHeat(HeatCondition.HEATED)
                .build(consumer.withConditions(RegConditionSerializer.Conditions.ALLOW_BRONZE_ALLOY));
    }

    public void ore(ItemLike result, Ingredient ingredients, int time, float xp, String group, RecipeOutput consumer) {
        oreSmeltingRecipe(result, ingredients, time, xp, group, consumer);
        oreBlastingRecipe(result, ingredients, time / 2, xp, group, consumer);
    }


    public <R extends StandardProcessingRecipe<?>> StandardProcessingRecipe.Builder<R> processing(StandardProcessingRecipe.Factory<R> factory, String id) {
        return new StandardProcessingRecipe.Builder<>(factory, lc(id));
    }

    protected StandardProcessingRecipe.Builder<CrushingRecipe> crushedToRaw(String id, Ingredient tag, Supplier<Item> result, Supplier<Item> residue) {
        return processing(CrushingRecipe::new, id)
                .require(tag)
                .output(result.get(), 1)
                .output(0.75f, result.get(), 1)
                .output(0.75f, AllItems.EXP_NUGGET.get())
                .output(0.12f, residue.get())
                .duration(200);
    }

    public void makeBlockSets(Supplier<? extends Block> blockIn, Supplier<? extends Block> slab, Supplier<? extends Block> stair, Supplier<? extends Block> wall, RecipeOutput consumer) {
        if(slab != null)
            makeSlabStonecutting(slab, blockIn, consumer);

        if(stair != null)
            makeStairsStonecutting(stair, blockIn, consumer);
        
        if(wall != null)
            makeWallStonecutting(wall, blockIn, consumer);
    }

    public void makeSlabStonecutting(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn, RecipeOutput consumer) {
        makeSlab(blockOut, blockIn).save(consumer);
        stonecutting(blockIn, blockOut.get(), 2).save(consumer, lc("stonecutting/" + getItemName(blockOut.get())));
    }

    public void makeStairsStonecutting(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn, RecipeOutput consumer) {
        makeStairs(blockOut, blockIn).save(consumer);
        stonecutting(blockIn, blockOut.get(), 1).save(consumer, lc("stonecutting/" + getItemName(blockOut.get())));
    }

    public void makeWallStonecutting(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn, RecipeOutput consumer) {
        makeWall(blockOut, blockIn).save(consumer);
        stonecutting(blockIn, blockOut.get(), 1).save(consumer, lc("stonecutting/" + getItemName(blockOut.get())));
    }

    public void makeChiseledStonecutting(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn, RecipeOutput consumer) {
        makeChiseled(blockOut, blockIn).save(consumer);
        stonecutting(blockIn, blockOut.get(), 1).save(consumer, lc("stonecutting/" + getItemName(blockOut.get())));
    }

    public SingleItemRecipeBuilder stonecutting(Supplier<? extends Block> input, ItemLike result, int resultAmount) {
        return SingleItemRecipeBuilder.stonecutting(Ingredient.of(input.get()), RecipeCategory.BUILDING_BLOCKS, result, resultAmount)
                .unlockedBy(getHasName(input.get()), has(input.get()));
    }


    public ShapedRecipeBuilder quadTransform(ItemLike blockOut, ItemLike itemIn) {
        return quadTransform(blockOut, itemIn, 4);
    }

    public ShapedRecipeBuilder quadTransform(ItemLike blockOut, ItemLike blockIn, int amount) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockOut, amount)
                .pattern("AA")
                .pattern("AA")
                .define('A', blockIn)
                .unlockedBy(getHasName(blockIn), has(blockIn));
    }

    public ShapedRecipeBuilder makeChiseled(Supplier<? extends Block> blockOut, Supplier<? extends Block> slabIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockOut.get())
                .pattern("A")
                .pattern("A")
                .define('A', slabIn.get())
                .unlockedBy(getHasName(slabIn.get()), has(slabIn.get()));
    }

    public ShapedRecipeBuilder makeSlab(Supplier<? extends Block> slabOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, slabOut.get(), 6)
                .pattern("AAA")
                .define('A', blockIn.get())
                .unlockedBy(getHasName(blockIn.get()), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeStairs(Supplier<? extends Block> stairsOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stairsOut.get(), 4)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .define('A', blockIn.get())
                .unlockedBy(getHasName(blockIn.get()), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makeWall(Supplier<? extends Block> wallOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, wallOut.get(), 6)
                .pattern("AAA")
                .pattern("AAA")
                .define('A', blockIn.get())
                .unlockedBy(getHasName(blockIn.get()), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makePillar(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockOut.get(), 2)
                .pattern("A")
                .pattern("A")
                .define('A', blockIn.get())
                .unlockedBy(getHasName(blockIn.get()), has(blockIn.get()));
    }

    public ShapedRecipeBuilder makePillarItem(Supplier<? extends Block> blockOut, ItemLike matIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockOut.get(), 2)
                .pattern("A")
                .pattern("A")
                .define('A', matIn)
                .unlockedBy(getHasName(matIn), has(matIn));
    }

    public void autoCompact(Item itemOut, Item itemIn, RecipeOutput consumer) {
        compact(itemOut, itemIn).save(consumer, lc(getItemName(itemOut) + "_from_" + getItemName(itemIn)));
        unCompact(itemIn, itemOut).save(consumer, lc(getItemName(itemIn) + "_from_" + getItemName(itemOut)));
    }

    public ShapedRecipeBuilder makeLantern(ItemLike itemOut, TagKey<Item> itemIn, String has, boolean soul) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, itemOut)
                .pattern("AAA")
                .pattern("AIA")
                .pattern("AAA")
                .define('I', soul? Items.SOUL_TORCH : Items.TORCH)
                .define('A', itemIn)
                .unlockedBy("has_" + has, has(itemIn));
    }

    public ShapedRecipeBuilder makeLanternSmall(ItemLike itemOut, TagKey<Item> itemIn, String has, boolean soul) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, itemOut)
                .pattern("A")
                .pattern("I")
                .define('I', soul? Items.SOUL_TORCH : Items.TORCH)
                .define('A', itemIn)
                .unlockedBy("has_" + has, has(itemIn));
    }

    public ShapedRecipeBuilder compact(Item itemOut, Item itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, itemOut)
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', itemIn)
                .unlockedBy(getHasName(itemIn), has(itemIn));
    }
    public ShapelessRecipeBuilder unCompact(Item itemOut, Item itemIn) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, itemOut, 9)
                .requires(itemIn)
                .unlockedBy(getHasName(itemIn), has(itemIn));
    }

    public ShapedRecipeBuilder makeBarsItem(Supplier<? extends Block> barsOut, ItemLike itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, barsOut.get(), 16)
                .pattern("AAA")
                .pattern("AAA")
                .define('A', itemIn)
                .unlockedBy(getHasName(itemIn), has(itemIn));
    }

    public void armorSet(String id, Item helmet, Item chestplate, Item leggings, Item boots, TagKey<Item> ingredient, RecipeOutput consumer, String... modLoaded) {
            boots(id, boots, ingredient).save(consumer);
            leggings(id, leggings, ingredient).save(consumer);
            chestplate(id, chestplate, ingredient).save(consumer);
            helmet(id, helmet, ingredient).save(consumer);
    }

    public ShapedRecipeBuilder helmet(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, itemOut)
                .pattern("AAA")
                .pattern("A A")
                .define('A', itemIn)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapedRecipeBuilder chestplate(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, itemOut)
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', itemIn)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapedRecipeBuilder leggings(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, itemOut)
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', itemIn)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapedRecipeBuilder boots(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, itemOut)
                .pattern("A A")
                .pattern("A A")
                .define('A', itemIn)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public SmithingTransformRecipeBuilder smithingRecipe(ItemLike input, ItemLike upgradeItem, ItemLike templateItem, ItemLike result) {
        return SmithingTransformRecipeBuilder.smithing(Ingredient.of(templateItem), Ingredient.of(input), Ingredient.of(upgradeItem), RecipeCategory.MISC, result.asItem())
                .unlocks(getHasName(upgradeItem), has(upgradeItem));
    }

    public SmithingTransformRecipeBuilder smithingAether(ItemLike input, ItemLike result) {
        return smithingRecipe(input, RegItems.AETHERSTEEL_INGOT, RegItems.AETHERSTEEL_TEMPLATE, result);
    }

    public void smithingIntoSteel(ItemLike input, ItemLike downgradeMaterial, ItemLike result, RecipeOutput consumer) {
        doubleSmithing(input, RegItems.STEEL_ALLOY, downgradeMaterial, result, consumer);
    }

    public void smithingIntoBronze(ItemLike input, ItemLike downgradeMaterial, ItemLike result, RecipeOutput consumer) {
        doubleSmithing(input, RegItems.BRONZE_ALLOY, downgradeMaterial, result, consumer);
    }

    public void smithingIntoMoonstone(ItemLike input, ItemLike downgradeMaterial, ItemLike result, RecipeOutput consumer) {
        doubleSmithing(input, RegBlocks.MOONSTONE.get(), downgradeMaterial, result, consumer);
    }

    public void doubleSmithing(ItemLike input, ItemLike material, ItemLike downgradeMaterial, ItemLike result, RecipeOutput consumer) {
        basicSmithing(input, material, result).save(consumer, lc(getItemName(result) + "_from_smithing"));
        basicDowngrade(result, downgradeMaterial, input).save(consumer, lc(getItemName(result) + "_downgrade_from_smithing"));
    }

    public void doubleSmithing(ItemLike input, TagKey<Item> material, TagKey<Item> downgradeMaterial, ItemLike result, String has, String hasDowngrade, RecipeOutput consumer) {
        basicSmithing(input, material, result, has).save(consumer, lc(getItemName(result) + "_from_smithing"));
        basicDowngrade(result, downgradeMaterial, input, hasDowngrade).save(consumer, lc(getItemName(result) + "_downgrade_from_smithing"));
    }

    public void doubleSmithing(ItemLike input, ItemLike material, TagKey<Item> downgradeMaterial, ItemLike result, String hasDowngrade, RecipeOutput consumer) {
        basicSmithing(input, material, result).save(consumer, lc(getItemName(result) + "_from_smithing"));
        basicDowngrade(result, downgradeMaterial, input, hasDowngrade).save(consumer, lc(getItemName(result) + "_downgrade_from_smithing"));
    }

    public void doubleSmithing(ItemLike input, TagKey<Item> material, ItemLike downgradeMaterial, ItemLike result, String has, RecipeOutput consumer) {
        basicSmithing(input, material, result, has).save(consumer, lc(getItemName(result) + "_from_smithing"));
        basicDowngrade(result, downgradeMaterial, input).save(consumer, lc(getItemName(result) + "_downgrade_from_smithing"));
    }

    public SmithingTransformRecipeBuilder basicSmithing(ItemLike input, TagKey<Item> material, ItemLike result, String has) {
        return SmithingTransformRecipeBuilder.smithing(Ingredient.of(RegItems.BASIC_TEMPLATE), Ingredient.of(input), Ingredient.of(material), RecipeCategory.MISC, result.asItem())
                .unlocks("has_" + has, has(material));
    }

    public SmithingTransformRecipeBuilder basicSmithing(ItemLike input, ItemLike material, ItemLike result) {
        return smithingRecipe(input, material, RegItems.BASIC_TEMPLATE, result);
    }

    public SmithingTransformRecipeBuilder basicDowngrade(ItemLike input, TagKey<Item> material, ItemLike result, String has) {
        return SmithingTransformRecipeBuilder.smithing(Ingredient.of(RegItems.DOWNGRADE_TEMPLATE), Ingredient.of(input), Ingredient.of(material), RecipeCategory.MISC, result.asItem())
                .unlocks("has_" + has, has(material));
    }

    public SmithingTransformRecipeBuilder basicDowngrade(ItemLike input, ItemLike material, ItemLike result) {
        return smithingRecipe(input, material, RegItems.DOWNGRADE_TEMPLATE, result);
    }

    private void makeToolsExtra(ItemLike sword, ItemLike pickaxe, ItemLike axe, ItemLike shovel, ItemLike hoe, ItemLike knife, ItemLike shield, ItemLike SPEAR_OVERHERE_JUSTDIEALREADY, ItemLike itemIn, ItemLike itemInS, RecipeOutput consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, hoe)
                .pattern("AA")
                .pattern(" S")
                .pattern(" S")
                .define('A', itemIn)
                .define('S', itemInS)
                .unlockedBy(getHasName(itemInS), has(itemInS)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, shovel)
                .pattern("A")
                .pattern("S")
                .pattern("S")
                .define('A', itemIn)
                .define('S', itemInS)
                .unlockedBy(getHasName(itemInS), has(itemInS)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, axe)
                .pattern("AA")
                .pattern("AS")
                .pattern(" S")
                .define('A', itemIn)
                .define('S', itemInS)
                .unlockedBy(getHasName(itemInS), has(itemInS)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pickaxe)
                .pattern("AAA")
                .pattern(" S ")
                .pattern(" S ")
                .define('A', itemIn)
                .define('S', itemInS)
                .unlockedBy(getHasName(itemInS), has(itemInS)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, sword)
                .pattern("A")
                .pattern("A")
                .pattern("S")
                .define('A', itemIn)
                .define('S', itemInS)
                .unlockedBy(getHasName(itemInS), has(itemInS)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, knife)
                .pattern("A")
                .pattern("S")
                .define('A', itemIn)
                .define('S', itemInS)
                .unlockedBy(getHasName(itemInS), has(itemInS)).save(consumer.withConditions(FD));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, shield)
                .pattern("AAA")
                .pattern("ASA")
                .pattern("AAA")
                .define('A', itemIn)
                .define('S', itemInS)
                .unlockedBy(getHasName(itemInS), has(itemInS)).save(consumer.withConditions(SE));

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, SPEAR_OVERHERE_JUSTDIEALREADY)
                .pattern("  A")
                .pattern(" S ")
                .pattern("S  ")
                .define('A', itemIn)
                .define('S', itemInS)
                .unlockedBy(getHasName(itemInS), has(itemInS)).save(consumer.withConditions(SB));
    }

    protected void oreSmeltingRecipe(ItemLike result, Ingredient ingredients, int time, float xp, String group, RecipeOutput consumer) {
        smeltingRecipe(result, ingredients, time, xp).group(group).save(consumer, lc(getItemName(result) + "_from_smelting_" + getItemName(Arrays.stream(ingredients.getItems()).findFirst().get().getItem())));
    }

    public SimpleCookingRecipeBuilder smeltingRecipe(ItemLike result, Ingredient ingredient, int time, float exp) {
        return SimpleCookingRecipeBuilder.smelting(ingredient, RecipeCategory.MISC, result, exp, time)
                .unlockedBy(getHasName(Arrays.stream(ingredient.getItems()).findFirst().get().getItem()), has(Arrays.stream(ingredient.getItems()).findFirst().get().getItem()));
    }

    protected void oreBlastingRecipe(ItemLike result, Ingredient ingredients, int time, float xp, String group, RecipeOutput consumer) {
        blastingRecipe(result, ingredients, time, xp, 1).group(group).save(consumer, lc(getItemName(result) + "_from_blasting_" + getItemName(Arrays.stream(ingredients.getItems()).findFirst().get().getItem())));
    }

    public SimpleCookingRecipeBuilder blastingRecipe(ItemLike result, Ingredient ingredient, int time, float exp, int count) {
        return SimpleCookingRecipeBuilder.blasting(ingredient, RecipeCategory.MISC, result, exp, time)
                .unlockedBy(getHasName(Arrays.stream(ingredient.getItems()).findFirst().get().getItem()), has(Arrays.stream(ingredient.getItems()).findFirst().get().getItem()));
    }

    public void toolSet(String id, Item sword, Item pickaxe, Item axe, Item shovel, Item hoe, Item knife, Item shield, Item SPEAR_OVERHERE_JUSTDIEALREADY, TagKey<Item> ingredient, RecipeOutput consumer) {
        vanillaSet(id, sword, pickaxe, axe, shovel, hoe, ingredient, consumer);
        knife(id, knife, ingredient).save(consumer.withConditions(FD));
        shield(id, shield, ingredient).save(consumer.withConditions(SE));
        spear(id, SPEAR_OVERHERE_JUSTDIEALREADY, ingredient).save(consumer.withConditions(SB));
    }

    public void vanillaSet(String id, Item sword, Item pickaxe, Item axe, Item shovel, Item hoe, TagKey<Item> ingredient, RecipeOutput consumer) {
        hoe(id, hoe, ingredient).save(consumer);
        shovel(id, shovel, ingredient).save(consumer);
        axe(id, axe, ingredient).save(consumer);
        pickaxe(id, pickaxe, ingredient).save(consumer);
        sword(id, sword, ingredient).save(consumer);
    }

    public ShapedRecipeBuilder spear(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, itemOut)
                .pattern("  A")
                .pattern(" S ")
                .pattern("S  ")
                .define('A', itemIn)
                .define('S', Items.STICK)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapedRecipeBuilder shield(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, itemOut)
                .pattern("AAA")
                .pattern("ASA")
                .pattern("AAA")
                .define('A', itemIn)
                .define('S', Items.STICK)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapedRecipeBuilder knife(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, itemOut)
                .pattern("A")
                .pattern("S")
                .define('A', itemIn)
                .define('S', Items.STICK)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapedRecipeBuilder hoe(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, itemOut)
                .pattern("AA")
                .pattern(" S")
                .pattern(" S")
                .define('A', itemIn)
                .define('S', Items.STICK)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapedRecipeBuilder shovel(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, itemOut)
                .pattern("A")
                .pattern("S")
                .pattern("S")
                .define('A', itemIn)
                .define('S', Items.STICK)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapedRecipeBuilder pickaxe(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, itemOut)
                .pattern("AAA")
                .pattern(" S ")
                .pattern(" S ")
                .define('A', itemIn)
                .define('S', Items.STICK)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapedRecipeBuilder axe(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, itemOut)
                .pattern("AA")
                .pattern("AS")
                .pattern(" S")
                .define('A', itemIn)
                .define('S', Items.STICK)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapedRecipeBuilder sword(String id, Item itemOut, TagKey<Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, itemOut)
                .pattern("A")
                .pattern("A")
                .pattern("S")
                .define('A', itemIn)
                .define('S', Items.STICK)
                .unlockedBy("has_" + id, has(itemIn));
    }

    public ShapelessRecipeBuilder alloyHigh(String has, Item itemOut, Item firstItem, TagKey<Item> secondItem) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, itemOut, 1)
                .requires(firstItem)
                .requires(firstItem)
                .requires(firstItem)
                .requires(firstItem)
                .requires(secondItem)
                .requires(secondItem)
                .requires(secondItem)
                .requires(secondItem)
                .unlockedBy("has_" + has, has(firstItem));
    }

    public ShapelessRecipeBuilder alloyHigh(String has, Item itemOut, TagKey<Item> firstItem, Item secondItem, Item requirement) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, itemOut, 1)
                .requires(firstItem)
                .requires(firstItem)
                .requires(firstItem)
                .requires(requirement)
                .requires(secondItem)
                .requires(secondItem)
                .requires(secondItem)
                .unlockedBy("has_" + has, has(firstItem));
    }

    public static final ICondition CREATE = new ModLoadedCondition("create");
    public static final ModLoadedCondition FD = new ModLoadedCondition("farmersdelight");
    public static final ModLoadedCondition SE = new ModLoadedCondition("shieldexp");
    public static final ModLoadedCondition SB = new ModLoadedCondition("spears");

}
