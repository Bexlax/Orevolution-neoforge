package net.bexla.orevolution.datagen;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.types.providers.ItemModelProvider;
import net.bexla.orevolution.init.FDRegistry;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class GENItemModels extends ItemModelProvider {
    public GENItemModels(PackOutput output, ExistingFileHelper help) {
        super(output, help);
    }

    @Override
    public @NotNull String getName() {
        return Orevolution.MODID + " Item Models";
    }

    private ItemModelBuilder ingredient(Supplier<? extends Item> item) {
        return normalItem(item, "ingredient");
    }

    private ItemModelBuilder consumable(Supplier<? extends Item> item) {
        return normalItem(item, "consumable");
    }

    private ItemModelBuilder armor(Supplier<? extends Item> item) {
        return normalItem(item, "armor");
    }

    private ItemModelBuilder blockitem(Supplier<? extends Item> item) {
        return normalItem(item, "blockitem");
    }

    private ItemModelBuilder compat(String modid, Supplier<? extends Item> item) {
        return normalItem(item, "compat/" + modid);
    }

    @Override
    protected void registerModels() {
//        compat(ModCompat.archery(), RegItemsAE.TIN_ARROW);
//        bowItem(RegItemsAE.TIN_BOW);
//        compat(ModCompat.archery(), RegItemsAE.PLATINUM_ARROW);
//        bowItem(RegItemsAE.PLATINUM_BOW);
//        compat(ModCompat.archery(), RegItemsAE.AETHERSTEEL_ARROW);
//        bowItem(RegItemsAE.AETHERSTEEL_BOW);
        String fd = "farmersdelight";

        blockitem(RegItems.DEAD_SEED);
        blockitem(RegItems.PETRIFIED_SEED);

        ingredient(RegItems.RAW_TIN);
        ingredient(RegItems.RAW_PLATINUM);
        ingredient(RegItems.RAW_TUNGSTEN);
        ingredient(RegItems.AETHERSTEEL_CHUNK);

        ingredient(RegItems.TIN_INGOT);
        ingredient(RegItems.PLATINUM_INGOT);
        ingredient(RegItems.TUNGSTEN_INGOT);
        ingredient(RegItems.AETHERSTEEL_INGOT);
        ingredient(RegItems.VERDITE_INGOT);

        ingredient(RegItems.BRONZE_ALLOY);
        ingredient(RegItems.STEEL_ALLOY);

        ingredient(RegItems.TIN_NUGGET);
        ingredient(RegItems.PLATINUM_NUGGET);
        ingredient(RegItems.TUNGSTEN_NUGGET);
        ingredient(RegItems.VERDITE_NUGGET);
        ingredient(RegItems.LIVINGSTONE_SHARD);

        ingredient(RegItems.AETHERSTEEL_TEMPLATE);
        ingredient(RegItems.REINFORCED_TEMPLATE);
        ingredient(RegItems.BASIC_TEMPLATE);

        consumable(RegItems.VERDITE_APPLE);
        consumable(RegItems.VERDITE_SPIDER_EYE);
        consumable(RegItems.PLATINUM_BERRIES);

        toolItem(RegItems.TIN_SWORD);
        toolItem(RegItems.TIN_PICKAXE);
        toolItem(RegItems.TIN_AXE);
        toolItem(RegItems.TIN_SHOVEL);
        toolItem(RegItems.TIN_HOE);
        compat(fd, FDRegistry.TIN_KNIFE);

        trimArmorItem(RegItems.PLATINUM_HELMET);
        trimArmorItem(RegItems.PLATINUM_CHESTPLATE);
        trimArmorItem(RegItems.PLATINUM_LEGGINGS);
        trimArmorItem(RegItems.PLATINUM_BOOTS);
        toolItem(RegItems.PLATINUM_SWORD);
        toolItem(RegItems.PLATINUM_PICKAXE);
        toolItem(RegItems.PLATINUM_AXE);
        toolItem(RegItems.PLATINUM_SHOVEL);
        toolItem(RegItems.PLATINUM_HOE);
        compat(fd, FDRegistry.PLATINUM_KNIFE);

        armor(RegItems.REINFORCED_NETHERITE_HELMET);
        armor(RegItems.REINFORCED_NETHERITE_CHESTPLATE);
        armor(RegItems.REINFORCED_NETHERITE_LEGGINGS);
        armor(RegItems.REINFORCED_NETHERITE_BOOTS);

        armor(RegItems.AETHERSTEEL_HELMET);
        armor(RegItems.AETHERSTEEL_CHESTPLATE);
        armor(RegItems.AETHERSTEEL_LEGGINGS);
        armor(RegItems.AETHERSTEEL_BOOTS);
        toolItem(RegItems.AETHERSTEEL_SWORD);
        toolItem(RegItems.AETHERSTEEL_PICKAXE);
        toolItem(RegItems.AETHERSTEEL_AXE);
        toolItem(RegItems.AETHERSTEEL_SHOVEL);
        toolItem(RegItems.AETHERSTEEL_HOE);
        compat(fd, FDRegistry.AETHERSTEEL_KNIFE);

        armor(RegItems.LIVINGSTONE_HELMET);
        armor(RegItems.LIVINGSTONE_CHESTPLATE);
        armor(RegItems.LIVINGSTONE_LEGGINGS);
        armor(RegItems.LIVINGSTONE_BOOTS);
        toolItem(RegItems.LIVINGSTONE_SWORD);
        toolItem(RegItems.LIVINGSTONE_PICKAXE);
        toolItem(RegItems.LIVINGSTONE_AXE);
        toolItem(RegItems.LIVINGSTONE_SHOVEL);
        toolItem(RegItems.LIVINGSTONE_HOE);
        compat(fd, FDRegistry.LIVINGSTONE_KNIFE);

        trimArmorItem(RegItems.VERDITE_HELMET);
        trimArmorItem(RegItems.VERDITE_CHESTPLATE);
        trimArmorItem(RegItems.VERDITE_LEGGINGS);
        trimArmorItem(RegItems.VERDITE_BOOTS);
        toolItem(RegItems.VERDITE_SWORD);
        toolItem(RegItems.VERDITE_PICKAXE);
        toolItem(RegItems.VERDITE_AXE);
        toolItem(RegItems.VERDITE_SHOVEL);
        toolItem(RegItems.VERDITE_HOE);
        compat(fd, FDRegistry.VERDITE_KNIFE);

        toolItem(RegItems.STEEL_DIGGER);
        toolItem(RegItems.STEEL_HAMMER);
        toolItem(RegItems.STEEL_SCYTHE);
        toolItem(RegItems.STEEL_BROADAXE);

        armor(RegItems.BRONZE_HELMET);
        armor(RegItems.BRONZE_CHESTPLATE);
        armor(RegItems.BRONZE_LEGGINGS);
        armor(RegItems.BRONZE_BOOTS);
        toolItem(RegItems.BRONZE_RADAR);
        toolItem(RegItems.BRONZE_TOTEM);
        toolItem(RegItems.BRONZE_TOTEM_DIAMOND);
        toolItem(RegItems.BRONZE_TOTEM_EMERALD);
        toolItem(RegItems.BRONZE_TOTEM_LAPIS_LAZULI);

        armor(RegItems.TUNGSTEN_HELMET);
        armor(RegItems.TUNGSTEN_CHESTPLATE);
        armor(RegItems.TUNGSTEN_LEGGINGS);
        armor(RegItems.TUNGSTEN_BOOTS);

        ingredient(RegItems.CRUSHED_AETHERSTEEL);
        ingredient(RegItems.CRUSHED_TUNGSTEN);

//        shieldItem(RegItems.TIN_SHIELD, "small");
//        shieldItem(RegItems.PLATINUM_SHIELD, "medium");
//        shieldItem(RegItems.AETHERSTEEL_SHIELD, "big");
//        shieldItem(RegItems.LIVINGSTONE_SHIELD, "small");
//        shieldItem(RegItems.VERDITE_SHIELD, "medium");

        block(RegBlocks.TIN_ORE);
        block(RegBlocks.DEEPSLATE_TIN_ORE);
        block(RegBlocks.PLATINUM_ORE);
        block(RegBlocks.DEEPSLATE_PLATINUM_ORE);
        block(RegBlocks.NETHER_TUNGSTEN_ORE);
        block(RegBlocks.END_XP_ORE);
        block(RegBlocks.NETHER_XP_ORE);
        block(RegBlocks.PRIMITIVE_AETHERROCK);

        block(RegBlocks.TIN_BLOCK);
        block(RegBlocks.PLATINUM_BLOCK);
        block(RegBlocks.TUNGSTEN_BLOCK);
        block(RegBlocks.AETHERSTEEL_BLOCK);
        block(RegBlocks.BRONZE_BLOCK);
        block(RegBlocks.STEEL_BLOCK);
        block(RegBlocks.LIVINGSTONE_BLOCK);
        block(RegBlocks.VERDITE_BLOCK);
        block(RegBlocks.RAW_TIN_BLOCK);
        block(RegBlocks.RAW_PLATINUM_BLOCK);
        block(RegBlocks.RAW_TUNGSTEN_BLOCK);

        block(RegBlocks.VERDITE_BRICKS);
        block(RegBlocks.LIVINGSTONE_BRICKS);
        block(RegBlocks.AETHERROCK);
        block(RegBlocks.POLISHED_AETHERROCK);
        block(RegBlocks.AETHERROCK_BRICKS);
        block(RegBlocks.AETHERROCK_TILES);
        block(RegBlocks.BRONZE_TILES);
        block(RegBlocks.LIMESTONE);
        block(RegBlocks.POLISHED_LIMESTONE);
        block(RegBlocks.LIMESTONE_PILLAR);
        block(RegBlocks.POLISHED_TUNGSTEN);
        block(RegBlocks.CUT_TUNGSTEN_BLOCK);
        block(RegBlocks.CUT_STEEL_BLOCK);
        block(RegBlocks.CRACKED_AETHERROCK_BRICKS);
        block(RegBlocks.STEEL_PILLAR);
        block(RegBlocks.PLATINUM_TILES);
        block(RegBlocks.GOLD_TILES);
        block(RegBlocks.GOLD_PILLAR);
        block(RegBlocks.PLATINUM_PILLAR);
        block(RegBlocks.CHISELED_TUNGSTEN_BRICKS);
        block(RegBlocks.CHISELED_TUNGSTEN_BLOCK);
        block(RegBlocks.TIN_TILES);
        block(RegBlocks.TIN_BRICKS);
        block(RegBlocks.TUNGSTEN_BRICKS);

        block(RegBlocks.TUNGSTEN_SPONGE);
        block(RegBlocks.HOT_TUNGSTEN_SPONGE);

        wall(RegBlocks.POLISHED_AETHERROCK_WALL, RegBlocks.POLISHED_AETHERROCK);
        block(RegBlocks.POLISHED_AETHERROCK_SLAB);
        block(RegBlocks.POLISHED_AETHERROCK_STAIR);
        trapdoorItem(RegBlocks.STEEL_TRAPDOOR);

        wall(RegBlocks.POLISHED_LIMESTONE_WALL, RegBlocks.POLISHED_LIMESTONE);
        block(RegBlocks.POLISHED_LIMESTONE_SLAB);
        block(RegBlocks.POLISHED_LIMESTONE_STAIR);
    }
}
