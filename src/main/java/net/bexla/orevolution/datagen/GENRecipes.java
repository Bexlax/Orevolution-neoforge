package net.bexla.orevolution.datagen;

import com.simibubi.create.AllItems;
import com.simibubi.create.content.kinetics.crusher.CrushingRecipe;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe;
import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.init.FDRegistry;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static net.bexla.orevolution.Orevolution.lc;

public class GENRecipes extends BlueprintRecipeProvider {
    public GENRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(Orevolution.MODID, output, provider);
    }

    protected static final Ingredient TIN_TOOLS_SMELTABLES =
            Ingredient.of(
                    RegItems.TIN_SWORD.get(),
                    RegItems.TIN_PICKAXE.get(),
                    RegItems.TIN_AXE.get(),
                    RegItems.TIN_SHOVEL.get(),
                    RegItems.TIN_HOE.get()
            );
    protected static final Ingredient PLATINUM_TOOLS_SMELTABLES =
            Ingredient.of(
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

    @Override
    public void buildRecipes(RecipeOutput consumer, HolderLookup.Provider provider) {
        ore(RegItems.TIN_INGOT.get(), Ingredient.of(OrevolutionTags.Items.tinOres), 150, 0.4F, "orevolution:tin_ingot", consumer);
        ore(RegItems.PLATINUM_INGOT.get(), Ingredient.of(OrevolutionTags.Items.platOres), 200, 1.2F, "orevolution:platinum_ingot", consumer);
        ore(RegItems.TUNGSTEN_INGOT.get(), Ingredient.of(OrevolutionTags.Items.tungsOres), 300, 1.6F, "orevolution:tungsten_ingot", consumer);

        ore(RegItems.TIN_INGOT.get(), Ingredient.of(RegItems.RAW_TIN.get()), 150, 0.4F, "orevolution:tin_ingot", consumer);
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

        autoCompact(RegBlocks.TIN_BLOCK.get().asItem(), RegItems.TIN_INGOT.get(), consumer);
        autoCompact(RegBlocks.PLATINUM_BLOCK.get().asItem(), RegItems.PLATINUM_INGOT.get(), consumer);
        autoCompact(RegBlocks.TUNGSTEN_BLOCK.get().asItem(), RegItems.TUNGSTEN_INGOT.get(), consumer);
        autoCompact(RegBlocks.AETHERSTEEL_BLOCK.get().asItem(), RegItems.AETHERSTEEL_INGOT.get(), consumer);
        autoCompact(RegBlocks.BRONZE_BLOCK.get().asItem(), RegItems.BRONZE_ALLOY.get(), consumer);
        autoCompact(RegBlocks.STEEL_BLOCK.get().asItem(), RegItems.STEEL_ALLOY.get(), consumer);
        autoCompact(RegBlocks.VERDITE_BLOCK.get().asItem(), RegItems.VERDITE_INGOT.get(), consumer);

        autoCompact(RegBlocks.RAW_TIN_BLOCK.get().asItem(), RegItems.RAW_TIN.get(), consumer);
        autoCompact(RegBlocks.RAW_PLATINUM_BLOCK.get().asItem(), RegItems.RAW_PLATINUM.get(), consumer);
        autoCompact(RegBlocks.RAW_TUNGSTEN_BLOCK.get().asItem(), RegItems.RAW_TUNGSTEN.get(), consumer);

        autoCompact(RegItems.TIN_INGOT.get(), RegItems.TIN_NUGGET.get(), consumer);
        autoCompact(RegItems.PLATINUM_INGOT.get(), RegItems.PLATINUM_NUGGET.get(), consumer);
        autoCompact(RegItems.TUNGSTEN_INGOT.get(), RegItems.TUNGSTEN_NUGGET.get(), consumer);
        autoCompact(RegBlocks.LIVINGSTONE_BLOCK.get().asItem(), RegItems.LIVINGSTONE_SHARD.get(), consumer);
        autoCompact(RegItems.VERDITE_INGOT.get(), RegItems.VERDITE_NUGGET.get(), consumer);

        makeStairsStonecutting(RegBlocks.POLISHED_AETHERROCK_STAIR, RegBlocks.POLISHED_AETHERROCK, consumer);
        makeSlabStonecutting(RegBlocks.POLISHED_AETHERROCK_SLAB, RegBlocks.POLISHED_AETHERROCK, consumer);
        makeWallStonecutting(RegBlocks.POLISHED_AETHERROCK_WALL, RegBlocks.POLISHED_AETHERROCK, consumer);

        quadTransform(RegBlocks.POLISHED_AETHERROCK, RegBlocks.AETHERROCK).save(consumer);

        quadTransform(RegBlocks.LIVINGSTONE_BRICKS, RegBlocks.LIVINGSTONE_BLOCK).save(consumer);
        quadTransform(RegBlocks.VERDITE_BRICKS, RegBlocks.LIVINGSTONE_BLOCK).save(consumer);

        makePillar(RegBlocks.AETHERROCK_BRICKS, RegBlocks.AETHERROCK).save(consumer);

        quadTransform(RegBlocks.AETHERROCK_TILES, RegBlocks.POLISHED_AETHERROCK).save(consumer);

        quadTransformItem(RegBlocks.POLISHED_TUNGSTEN, RegItems.TUNGSTEN_INGOT, 4).save(consumer);
        quadTransform(RegBlocks.TUNGSTEN_BRICKS, RegBlocks.CUT_TUNGSTEN_BLOCK, 4).save(consumer);

        makePillarItem(RegBlocks.STEEL_PILLAR, RegItems.STEEL_ALLOY).save(consumer);

        quadTransformItem(RegBlocks.TIN_TILES, RegItems.TIN_INGOT, 4).save(consumer);
        quadTransform(RegBlocks.TIN_BRICKS, RegBlocks.TIN_TILES, 4).save(consumer);

        quadTransform(RegBlocks.CUT_TUNGSTEN_BLOCK, RegBlocks.POLISHED_TUNGSTEN, 4).save(consumer);
        quadTransformItem(RegBlocks.CUT_STEEL_BLOCK, RegItems.STEEL_ALLOY, 4).save(consumer);

        quadTransformItem(RegBlocks.PLATINUM_TILES, RegItems.PLATINUM_INGOT, 4).save(consumer);
        quadTransformItem(RegBlocks.GOLD_TILES, () -> Items.GOLD_INGOT, 4).save(consumer);
        makePillarItem(RegBlocks.PLATINUM_PILLAR, RegItems.PLATINUM_INGOT).save(consumer);
        makePillarItem(RegBlocks.GOLD_PILLAR, () -> Items.GOLD_INGOT).save(consumer);

        makeBarsItem(RegBlocks.PLATINUM_BARS, RegItems.PLATINUM_INGOT).save(consumer);
        makeBarsItem(RegBlocks.TUNGSTEN_BARS, RegItems.TUNGSTEN_INGOT).save(consumer);
        makeBarsItem(RegBlocks.BRONZE_BARS, RegItems.BRONZE_ALLOY).save(consumer);
        makeBarsItem(RegBlocks.STEEL_BARS, RegItems.STEEL_ALLOY).save(consumer);
        makeBarsItem(RegBlocks.TIN_BARS, RegItems.TIN_INGOT).save(consumer);
        makeBarsItem(RegBlocks.GOLD_BARS, () -> Items.GOLD_INGOT).save(consumer);

        makeChiseledStonecutting(RegBlocks.CHISELED_TUNGSTEN_BLOCK, RegBlocks.TUNGSTEN_BLOCK, consumer);
        stonecutting(RegBlocks.CHISELED_TUNGSTEN_BRICKS, RegBlocks.TUNGSTEN_BLOCK.get(), 1).save(consumer);
        makeChiseledStonecutting(RegBlocks.CHISELED_TUNGSTEN_BRICKS, RegBlocks.TUNGSTEN_BRICKS, consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RegItems.BRONZE_ALLOY.get(), 1)
                .requires(OrevolutionTags.Items.tinIngots)
                .requires(OrevolutionTags.Items.tinIngots)
                .requires(Tags.Items.INGOTS_COPPER)
                .requires(Tags.Items.INGOTS_COPPER)
                .unlockedBy("has_tin_ingot", has(OrevolutionTags.Items.tinIngots)).save(consumer);
        alloyHigh("iron_ingot", RegItems.STEEL_ALLOY.get(), Tags.Items.INGOTS_IRON, Items.COAL, Items.BLAZE_POWDER).save(consumer.withConditions(CREATE));
        alloyHigh("aethersteel_ingot", RegItems.AETHERSTEEL_INGOT.get(), RegItems.AETHERSTEEL_CHUNK.get(), OrevolutionTags.Items.platIngots).save(consumer.withConditions(CREATE));


        toolSet("tin_ingot",
                RegItems.TIN_SWORD.get(),
                RegItems.TIN_PICKAXE.get(),
                RegItems.TIN_AXE.get(),
                RegItems.TIN_SHOVEL.get(),
                RegItems.TIN_HOE.get(),
                FDRegistry.TIN_KNIFE.get(),
//                RegItems.TIN_SHIELD.get(),
                OrevolutionTags.Items.tinIngots, consumer);

        toolSet("platinum_ingot",
                RegItems.PLATINUM_SWORD.get(),
                RegItems.PLATINUM_PICKAXE.get(),
                RegItems.PLATINUM_AXE.get(),
                RegItems.PLATINUM_SHOVEL.get(),
                RegItems.PLATINUM_HOE.get(),
                FDRegistry.PLATINUM_KNIFE.get(),
//                RegItems.PLATINUM_SHIELD.get(),
                OrevolutionTags.Items.platIngots, consumer);
        armorSet("platinum_ingot",
                RegItems.PLATINUM_HELMET.get(),
                RegItems.PLATINUM_CHESTPLATE.get(),
                RegItems.PLATINUM_LEGGINGS.get(),
                RegItems.PLATINUM_BOOTS.get(),
                OrevolutionTags.Items.platIngots, consumer);

        makeToolsExtra(
                RegItems.LIVINGSTONE_SWORD.get(),
                RegItems.LIVINGSTONE_PICKAXE.get(),
                RegItems.LIVINGSTONE_AXE.get(),
                RegItems.LIVINGSTONE_SHOVEL.get(),
                RegItems.LIVINGSTONE_HOE.get(),
                FDRegistry.LIVINGSTONE_KNIFE.get(),
//                RegItems.LIVINGSTONE_SHIELD.get(),
                RegBlocks.LIVINGSTONE_BLOCK.get(), RegItems.LIVINGSTONE_SHARD.get(), consumer);
        armorSet("livingstone_block",
                RegItems.LIVINGSTONE_HELMET.get(),
                RegItems.LIVINGSTONE_CHESTPLATE.get(),
                RegItems.LIVINGSTONE_LEGGINGS.get(),
                RegItems.LIVINGSTONE_BOOTS.get(),
                OrevolutionTags.Items.livingstoneStorages, consumer);

        toolSet("verdite_ingot",
                RegItems.VERDITE_SWORD.get(),
                RegItems.VERDITE_PICKAXE.get(),
                RegItems.VERDITE_AXE.get(),
                RegItems.VERDITE_SHOVEL.get(),
                RegItems.VERDITE_HOE.get(),
                FDRegistry.VERDITE_KNIFE.get(),
//                RegItems.VERDITE_SHIELD.get(),
                OrevolutionTags.Items.verditeIngots, consumer);
        armorSet("verdite_ingot",
                RegItems.VERDITE_HELMET.get(),
                RegItems.VERDITE_CHESTPLATE.get(),
                RegItems.VERDITE_LEGGINGS.get(),
                RegItems.VERDITE_BOOTS.get(),
                OrevolutionTags.Items.verditeIngots, consumer);

        armorSet("bronze_ingot",
                RegItems.BRONZE_HELMET.get(),
                RegItems.BRONZE_CHESTPLATE.get(),
                RegItems.BRONZE_LEGGINGS.get(),
                RegItems.BRONZE_BOOTS.get(),
                OrevolutionTags.Items.bronzeIngots, consumer.withConditions(CREATE));

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, RegBlocks.STEEL_DOOR.get())
                .pattern("AA")
                .pattern("AA")
                .pattern("AA")
                .define('A', RegItems.STEEL_ALLOY.get())
                .unlockedBy("has_steel_alloy", has(RegItems.STEEL_ALLOY.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.BASIC_TEMPLATE.get())
                .pattern("ACA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.COPPER_INGOT)
                .define('B', Items.AMETHYST_SHARD)
                .define('C', RegItems.TIN_INGOT.get())
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.AETHERSTEEL_TEMPLATE.get(), 2)
                .pattern("ACA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', RegBlocks.AETHERROCK.get())
                .define('B', Items.DIAMOND)
                .define('C', RegItems.AETHERSTEEL_TEMPLATE.get())
                .unlockedBy("has_aethersteel_template", has(RegItems.AETHERSTEEL_TEMPLATE.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.REINFORCED_TEMPLATE.get(), 2)
                .pattern("ACA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.BLACKSTONE)
                .define('B', Items.DIAMOND)
                .define('C', RegItems.REINFORCED_TEMPLATE.get())
                .unlockedBy("has_reinforced_template", has(RegItems.REINFORCED_TEMPLATE.get())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegBlocks.TUNGSTEN_SPONGE.get(), 1)
                .pattern(" A ")
                .pattern("AXA")
                .pattern(" A ")
                .define('A', RegItems.TUNGSTEN_INGOT.get())
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
                .define('X', Items.APPLE)
                .unlockedBy("has_apple", has(Items.APPLE)).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, RegItems.BRONZE_TOTEM.get(), 1)
                .pattern("AAA")
                .pattern(" A ")
                .define('A', RegItems.BRONZE_ALLOY.get())
                .unlockedBy("has_bronze_alloy", has(RegItems.BRONZE_ALLOY.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RegItems.BRONZE_TOTEM_EMERALD.get(), 1)
                .requires(RegItems.BRONZE_TOTEM.get())
                .requires(Items.EMERALD)
                .requires(Items.EMERALD)
                .requires(Items.EMERALD)
                .unlockedBy("has_bronze_totem", has(RegItems.BRONZE_TOTEM.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RegItems.BRONZE_TOTEM_DIAMOND.get(), 1)
                .requires(RegItems.BRONZE_TOTEM.get())
                .requires(Items.DIAMOND)
                .requires(Items.DIAMOND)
                .requires(Items.DIAMOND)
                .unlockedBy("has_bronze_totem", has(RegItems.BRONZE_TOTEM.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RegItems.BRONZE_TOTEM_LAPIS_LAZULI.get(), 1)
                .requires(RegItems.BRONZE_TOTEM.get())
                .requires(Items.LAPIS_LAZULI)
                .requires(Items.LAPIS_LAZULI)
                .requires(Items.LAPIS_LAZULI)
                .unlockedBy("has_bronze_totem", has(RegItems.BRONZE_TOTEM.get())).save(consumer);

        smithingReinforced(() -> Items.NETHERITE_HELMET, RegItems.REINFORCED_NETHERITE_HELMET).save(consumer, lc("reinforced_netherite_helmet"));
        smithingReinforced(() -> Items.NETHERITE_CHESTPLATE, RegItems.REINFORCED_NETHERITE_CHESTPLATE).save(consumer, lc("reinforced_netherite_chestplate"));
        smithingReinforced(() -> Items.NETHERITE_LEGGINGS, RegItems.REINFORCED_NETHERITE_LEGGINGS).save(consumer, lc("reinforced_netherite_leggings"));
        smithingReinforced(() -> Items.NETHERITE_BOOTS, RegItems.REINFORCED_NETHERITE_BOOTS).save(consumer, lc("reinforced_netherite_boots"));

        smithingReinforced(RegItems.BRONZE_HELMET, RegItems.TUNGSTEN_HELMET).save(consumer.withConditions(CREATE), lc("tungsten_helmet"));
        smithingReinforced(RegItems.BRONZE_CHESTPLATE, RegItems.TUNGSTEN_CHESTPLATE).save(consumer.withConditions(CREATE), lc("tungsten_chestplate"));
        smithingReinforced(RegItems.BRONZE_LEGGINGS, RegItems.TUNGSTEN_LEGGINGS).save(consumer.withConditions(CREATE), lc("tungsten_leggings"));
        smithingReinforced(RegItems.BRONZE_BOOTS, RegItems.TUNGSTEN_BOOTS).save(consumer.withConditions(CREATE), lc("tungsten_boots"));

        smithingAether(() -> Items.NETHERITE_SWORD, RegItems.AETHERSTEEL_SWORD).save(consumer, lc("aethersteel_sword"));
        smithingAether(() -> Items.NETHERITE_PICKAXE, RegItems.AETHERSTEEL_PICKAXE).save(consumer, lc("aethersteel_pickaxe"));
        smithingAether(() -> Items.NETHERITE_AXE, RegItems.AETHERSTEEL_AXE).save(consumer, lc("aethersteel_axe"));
        smithingAether(() -> Items.NETHERITE_SHOVEL, RegItems.AETHERSTEEL_SHOVEL).save(consumer, lc("aethersteel_shovel"));
        smithingAether(() -> Items.NETHERITE_HOE, RegItems.AETHERSTEEL_HOE).save(consumer, lc("aethersteel_hoe"));
//        smithingAether(ModItems.NETHERITE_KNIFE, RegItems.AETHERSTEEL_KNIFE), ModCompat.fdelight()).save(consumer, lc("aethersteel_knife"));
//        smithingAether(ItemsInit.NETHERITE_SHIELD, RegItems.AETHERSTEEL_SHIELD), ModCompat.shield()).save(consumer, lc("aethersteel_shield"));

        smithingBasic(() -> Items.IRON_PICKAXE, RegItems.STEEL_HAMMER).save(consumer, lc("steel_hammer_from_smithing"));
        smithingBasic(() -> Items.IRON_SHOVEL, RegItems.STEEL_DIGGER).save(consumer, lc("steel_digger_from_smithing"));
        smithingBasic(() -> Items.IRON_HOE, RegItems.STEEL_SCYTHE).save(consumer, lc("steel_scythe_smithing"));
        smithingBasic(() -> Items.IRON_AXE, RegItems.STEEL_BROADAXE).save(consumer, lc("steel_broadaxe_smithing"));
        smithingBasic(() -> Items.ANVIL, () -> RegBlocks.STEEL_ANVIL.get().asItem()).save(consumer, lc("steel_anvil_from_smithing"));

        smithingAether(RegItems.REINFORCED_NETHERITE_HELMET, RegItems.AETHERSTEEL_HELMET).save(consumer, lc("aethersteel_helmet_from_smithing_smithing"));
        smithingAether(RegItems.REINFORCED_NETHERITE_CHESTPLATE, RegItems.AETHERSTEEL_CHESTPLATE).save(consumer, lc("aethersteel_chestplate_smithing"));
        smithingAether(RegItems.REINFORCED_NETHERITE_LEGGINGS, RegItems.AETHERSTEEL_LEGGINGS).save(consumer, lc("aethersteel_leggings_smithing"));
        smithingAether(RegItems.REINFORCED_NETHERITE_BOOTS, RegItems.AETHERSTEEL_BOOTS).save(consumer, lc("aethersteel_boots_smithing"));

        crushedToRaw("crushed_tungsten", Ingredient.of(OrevolutionTags.Items.tungsOres), RegItems.CRUSHED_TUNGSTEN, Blocks.NETHERRACK::asItem).build(consumer);
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
                .require(OrevolutionTags.Items.platIngots)
                .require(OrevolutionTags.Items.platIngots)
                .require(OrevolutionTags.Items.platIngots)
                .require(OrevolutionTags.Items.platIngots)
                .require(RegItems.AETHERSTEEL_CHUNK.get())
                .require(RegItems.AETHERSTEEL_CHUNK.get())
                .require(RegItems.AETHERSTEEL_CHUNK.get())
                .require(RegItems.AETHERSTEEL_CHUNK.get())
                .requiresHeat(HeatCondition.SUPERHEATED)
                .build(consumer);

        processing(MixingRecipe::new, "aethersteel_ingot_crushed_aethersteel")
                .output(RegItems.AETHERSTEEL_INGOT.get(), 1)
                .require(OrevolutionTags.Items.platIngots)
                .require(OrevolutionTags.Items.platIngots)
                .require(OrevolutionTags.Items.platIngots)
                .require(OrevolutionTags.Items.platIngots)
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
                .build(consumer);

        processing(MixingRecipe::new, "bronze_alloy")
                .output(RegItems.BRONZE_ALLOY.get(), 1)
                .require(Tags.Items.INGOTS_COPPER)
                .require(OrevolutionTags.Items.tinIngots)
                .requiresHeat(HeatCondition.HEATED)
                .build(consumer);
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

    public ShapedRecipeBuilder quadTransform(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn) {
        return quadTransform(blockOut, blockIn, 4);
    }

    public ShapedRecipeBuilder quadTransform(Supplier<? extends Block> blockOut, Supplier<? extends Block> blockIn, int amount) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockOut.get(), amount)
                .pattern("AA")
                .pattern("AA")
                .define('A', blockIn.get())
                .unlockedBy(getHasName(blockIn.get()), has(blockIn.get()));
    }

    public ShapedRecipeBuilder quadTransformItem(Supplier<? extends Block> blockOut, Supplier<? extends Item> blockIn, int amount) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockOut.get(), amount)
                .pattern("AA")
                .pattern("AA")
                .define('A', blockIn.get())
                .unlockedBy(getHasName(blockIn.get()), has(blockIn.get()));
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

    public ShapedRecipeBuilder makePillarItem(Supplier<? extends Block> blockOut, Supplier<? extends Item> matIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, blockOut.get(), 2)
                .pattern("A")
                .pattern("A")
                .define('A', matIn.get())
                .unlockedBy(getHasName(matIn.get()), has(matIn.get()));
    }

    public void autoCompact(Item itemOut, Item itemIn, RecipeOutput consumer) {
        compact(itemOut, itemIn).save(consumer, lc(getItemName(itemOut) + "_from_" + getItemName(itemIn)));
        unCompact(itemIn, itemOut).save(consumer, lc(getItemName(itemIn) + "_from_" + getItemName(itemOut)));
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

    public ShapedRecipeBuilder makeBarsItem(Supplier<? extends Block> barsOut, Supplier<? extends Item> itemIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, barsOut.get(), 16)
                .pattern("AAA")
                .pattern("AAA")
                .define('A', itemIn.get())
                .unlockedBy(getHasName(itemIn.get()), has(itemIn.get()));
    }

    public void armorSet(String id, Item helmet, Item chestplate, Item leggings, Item boots, TagKey<Item> ingredient, RecipeOutput consumer, String... modLoaded) {
//        if(modLoaded.length > 0) {
//            boots(id, boots, ingredient).save(consumer.withConditions());
//            leggings(id, leggings, ingredient).save(consumer);
//            chestplate(id, chestplate, ingredient).save(consumer);
//            helmet(id, helmet, ingredient).save(consumer);
//        }
//        else {
            boots(id, boots, ingredient);
            leggings(id, leggings, ingredient).save(consumer);
            chestplate(id, chestplate, ingredient).save(consumer);
            helmet(id, helmet, ingredient).save(consumer);
//        }
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

    public SmithingTransformRecipeBuilder smithingRecipe(Supplier<? extends Item> input, Supplier<? extends Item> upgradeItem, Supplier<? extends Item> templateItem, Supplier<? extends Item> result) {
        return SmithingTransformRecipeBuilder.smithing(Ingredient.of(templateItem.get()), Ingredient.of(input.get()), Ingredient.of(upgradeItem.get()), RecipeCategory.MISC, result.get())
                .unlocks(getHasName(upgradeItem.get()), has(upgradeItem.get()));
    }

    public SmithingTransformRecipeBuilder smithingAether(Supplier<? extends Item> input, Supplier<? extends Item> result) {
        return smithingRecipe(input, RegItems.AETHERSTEEL_INGOT, RegItems.AETHERSTEEL_TEMPLATE, result);
    }


    public SmithingTransformRecipeBuilder smithingReinforced(Supplier<? extends Item> input, Supplier<? extends Item> result) {
        return smithingRecipe(input, RegItems.TUNGSTEN_INGOT, RegItems.REINFORCED_TEMPLATE, result);
    }

    public SmithingTransformRecipeBuilder smithingBasic(Supplier<? extends Item> input, Supplier<? extends Item> result) {
        return smithingRecipe(input, () -> Items.IRON_INGOT, RegItems.BASIC_TEMPLATE, result);
    }

    private void makeToolsExtra(ItemLike sword, ItemLike pickaxe, ItemLike axe, ItemLike shovel, ItemLike hoe, ItemLike knife, ItemLike itemIn, ItemLike itemInS, RecipeOutput consumer) {
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
                .unlockedBy(getHasName(itemInS), has(itemInS)).save(consumer);
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

    public void toolSet(String id, Item sword, Item pickaxe, Item axe, Item shovel, Item hoe, Item knife, TagKey<Item> ingredient, RecipeOutput consumer) {
        hoe(id, hoe, ingredient).save(consumer);
        shovel(id, shovel, ingredient).save(consumer);
        axe(id, axe, ingredient).save(consumer);
        pickaxe(id, pickaxe, ingredient).save(consumer);
        sword(id, sword, ingredient).save(consumer);
        knife(id, knife, ingredient).save(consumer.withConditions(FD));
        //shield(id, shield, ingredient).save(consumer.withConditions());
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

    public static final ModLoadedCondition CREATE = new ModLoadedCondition("create");
    public static final ModLoadedCondition FD = new ModLoadedCondition("create");
}
