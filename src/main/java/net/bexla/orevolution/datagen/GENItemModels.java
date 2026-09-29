package net.bexla.orevolution.datagen;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.types.providers.ItemModelProvider;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.bexla.orevolution.init.modcompat.FDRegistry;
import net.bexla.orevolution.init.modcompat.SBRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
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

    private ItemModelBuilder misc(Supplier<? extends Item> item) {
        return normalItem(item, "misc");
    }

    private ItemModelBuilder blockitem(Supplier<? extends ItemLike> item) {
        return generated(item, itemTex(item.get(), "misc/blockitem"));
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

        spearItem(SBRegistry.TIN_SPEAR);
        spearItem(SBRegistry.PLATINUM_SPEAR);
        spearItem(SBRegistry.MOONSTONE_SPEAR);
        spearItem(SBRegistry.AETHERSTEEL_SPEAR);
        spearItem(SBRegistry.LIVINGSTONE_SPEAR);
        spearItem(SBRegistry.VERDITE_SPEAR);

        String fd = "farmersdelight";

        blockitem(RegItems.DEAD_SEED);
        blockitem(RegItems.PETRIFIED_SEED);
        blockitem(RegItems.VINNELIO);

        blockitem(RegBlocks.TIN_LANTERN);
        blockitem(RegBlocks.PLATINUM_LANTERN);
        blockitem(RegBlocks.BRONZE_LANTERN);
        blockitem(RegBlocks.GOLDEN_LANTERN);
        blockitem(RegBlocks.TIN_SOUL_LANTERN);
        blockitem(RegBlocks.PLATINUM_SOUL_LANTERN);
        blockitem(RegBlocks.BRONZE_SOUL_LANTERN);
        blockitem(RegBlocks.GOLDEN_SOUL_LANTERN);

        ingredient(RegItems.PYRITE);
        ingredient(RegItems.CELESTITE_SHARD);

        ingredient(RegItems.RAW_TIN);
        ingredient(RegItems.RAW_CASSITERITE);
        ingredient(RegItems.RAW_PLATINUM);
        ingredient(RegItems.RAW_TUNGSTEN);
        ingredient(RegItems.AETHERSTEEL_CHUNK);

        ingredient(RegItems.TIN_INGOT);
        ingredient(RegItems.CASSITERITE_INGOT);
        ingredient(RegItems.PLATINUM_INGOT);
        ingredient(RegItems.TUNGSTEN_INGOT);
        ingredient(RegItems.AETHERSTEEL_INGOT);
        ingredient(RegItems.VERDITE_INGOT);

        ingredient(RegItems.BRONZE_ALLOY);
        ingredient(RegItems.STEEL_ALLOY);

        ingredient(RegItems.TIN_NUGGET);
        ingredient(RegItems.CASSITERITE_NUGGET);
        ingredient(RegItems.PLATINUM_NUGGET);
        ingredient(RegItems.TUNGSTEN_NUGGET);
        ingredient(RegItems.VERDITE_NUGGET);
        ingredient(RegItems.LIVINGSTONE_SHARD);
        ingredient(RegItems.QUARTZ_CHIP);

        consumable(RegItems.PROFESSIONAL_FIREWORK_ROCKET);

        ingredient(RegItems.AETHERSTEEL_TEMPLATE);
        ingredient(RegItems.REINFORCED_TEMPLATE);
        ingredient(RegItems.COATING_TEMPLATE);
        ingredient(RegItems.BASIC_TEMPLATE);
        ingredient(RegItems.DOWNGRADE_TEMPLATE);

        consumable(RegItems.VERDITE_APPLE);
        consumable(RegItems.VERDITE_SPIDER_EYE);
        consumable(RegItems.PLATINUM_BERRIES);
        consumable(RegItems.PLATINUM_APPLE);
        consumable(RegItems.ANCIENT_FRUIT);
        consumable(RegItems.ANCIENT_STEW);
        consumable(RegItems.GLOWING_BOTTLE);
        consumable(RegItems.FIERCE_BOTTLE);
        consumable(RegItems.LIFE_BOTTLE);
        consumable(RegItems.LIGHTNING_BOTTLE);
        consumable(RegItems.FOOLS_APPLE);
        consumable(RegItems.FOOLS_CARROT);

        toolItem(RegItems.TIN_SWORD);
        toolItem(RegItems.TIN_PICKAXE);
        toolItem(RegItems.TIN_AXE);
        toolItem(RegItems.TIN_SHOVEL);
        toolItem(RegItems.TIN_HOE);
        compat(fd, FDRegistry.TIN_KNIFE);

        toolItem(RegItems.CASSITERITE_SWORD);
        toolItem(RegItems.CASSITERITE_PICKAXE);
        toolItem(RegItems.CASSITERITE_AXE);
        toolItem(RegItems.CASSITERITE_SHOVEL);
        toolItem(RegItems.CASSITERITE_HOE);

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

        trimArmorItem(RegItems.MOONSTONE_HELMET);
        trimArmorItem(RegItems.MOONSTONE_CHESTPLATE);
        trimArmorItem(RegItems.MOONSTONE_LEGGINGS);
        trimArmorItem(RegItems.MOONSTONE_BOOTS);
        toolItem(RegItems.MOONSTONE_SWORD);
        toolItem(RegItems.MOONSTONE_PICKAXE);
        toolItem(RegItems.MOONSTONE_AXE);
        toolItem(RegItems.MOONSTONE_SHOVEL);
        toolItem(RegItems.MOONSTONE_HOE);
        compat(fd, FDRegistry.MOONSTONE_KNIFE);

        trimArmorItem(RegItems.AETHERSTEEL_HELMET);
        trimArmorItem(RegItems.AETHERSTEEL_CHESTPLATE);
        trimArmorItem(RegItems.AETHERSTEEL_LEGGINGS);
        trimArmorItem(RegItems.AETHERSTEEL_BOOTS);
        toolItem(RegItems.AETHERSTEEL_SWORD);
        toolItem(RegItems.AETHERSTEEL_PICKAXE);
        toolItem(RegItems.AETHERSTEEL_AXE);
        toolItem(RegItems.AETHERSTEEL_SHOVEL);
        toolItem(RegItems.AETHERSTEEL_HOE);
        compat(fd, FDRegistry.AETHERSTEEL_KNIFE);

        trimArmorItem(RegItems.LIVINGSTONE_HELMET);
        trimArmorItem(RegItems.LIVINGSTONE_CHESTPLATE);
        trimArmorItem(RegItems.LIVINGSTONE_LEGGINGS);
        trimArmorItem(RegItems.LIVINGSTONE_BOOTS);
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

        toolItem(RegItems.STEEL_HEAVYWORK_SWORD);
        toolItem(RegItems.STEEL_HEAVYWORK_PICKAXE);
        toolItem(RegItems.STEEL_HEAVYWORK_AXE);
        toolItem(RegItems.STEEL_HEAVYWORK_SHOVEL);
        toolItem(RegItems.STEEL_HEAVYWORK_HOE);

        trimArmorItemDyeable(RegItems.BRONZE_HELMET);
        trimArmorItemDyeable(RegItems.BRONZE_CHESTPLATE);
        trimArmorItemDyeable(RegItems.BRONZE_LEGGINGS);
        trimArmorItemDyeable(RegItems.BRONZE_BOOTS);
        misc(RegItems.BRONZE_RADAR);

        trimArmorItem(RegItems.TUNGSTEN_HELMET);
        trimArmorItem(RegItems.TUNGSTEN_CHESTPLATE);
        trimArmorItem(RegItems.TUNGSTEN_LEGGINGS);
        trimArmorItem(RegItems.TUNGSTEN_BOOTS);
        toolItem(RegItems.TUNGSTEN_SWORD);
        toolItem(RegItems.TUNGSTEN_PICKAXE);
        toolItem(RegItems.TUNGSTEN_AXE);
        toolItem(RegItems.TUNGSTEN_SHOVEL);
        toolItem(RegItems.TUNGSTEN_HOE);

        misc(RegItems.BRONZE_HORSE_ARMOR);
        misc(RegItems.STEEL_HORSE_ARMOR);

        misc(RegItems.FIERY_ARROW);
        ingredient(RegItems.CRUSHED_AETHERSTEEL);
        ingredient(RegItems.CRUSHED_TUNGSTEN);

        shieldItem(RegItems.TIN_SHIELD, "small");
        shieldItem(RegItems.LIVINGSTONE_SHIELD, "small");
        shieldItem(RegItems.PLATINUM_SHIELD, "medium");
        shieldItem(RegItems.VERDITE_SHIELD, "medium");
        shieldItem(RegItems.MOONSTONE_SHIELD, "medium");
        shieldItem(RegItems.AETHERSTEEL_SHIELD, "big");

        block(RegBlocks.RAW_CASSITERITE_BLOCK);
        block(RegBlocks.CASSITERITE_ORE);
        block(RegBlocks.DEEPSLATE_CASSITERITE_ORE);
        block(RegBlocks.NETHER_CASSITERITE_ORE);
        block(RegBlocks.CASSITERITE_BLOCK);
        
        block(RegBlocks.END_XP_ORE);
        block(RegBlocks.NETHER_XP_ORE);
        block(RegBlocks.PRIMITIVE_AETHERROCK);
        
        block(RegBlocks.AETHERSTEEL_BLOCK);
        
        block(RegBlocks.BRONZE_BLOCK);
        block(RegBlocks.BRONZE_TILES);

        block(RegBlocks.STEEL_BLOCK);
        block(RegBlocks.CUT_STEEL_BLOCK);
        block(RegBlocks.STEEL_PILLAR);
        block(RegBlocks.CUT_STEEL_SLAB);
        block(RegBlocks.CUT_STEEL_STAIR);
        block(RegBlocks.STEEL_GRATE);
        trapdoorItem(RegBlocks.STEEL_TRAPDOOR);

        block(RegBlocks.RDX);
        
        block(RegBlocks.GOLD_TILES);
        block(RegBlocks.GOLD_PILLAR);

        block(RegBlocks.PLATINUM_ORE);
        block(RegBlocks.DEEPSLATE_PLATINUM_ORE);
        block(RegBlocks.RAW_PLATINUM_BLOCK);
        block(RegBlocks.PLATINUM_BLOCK);
        block(RegBlocks.PLATINUM_TILES);
        block(RegBlocks.PLATINUM_PILLAR);

        block(RegBlocks.NETHER_TUNGSTEN_ORE);
        block(RegBlocks.RAW_TUNGSTEN_BLOCK);
        
        block(RegBlocks.TUNGSTEN_BLOCK);
        block(RegBlocks.POLISHED_TUNGSTEN);
        block(RegBlocks.CUT_TUNGSTEN_BLOCK);
        block(RegBlocks.CHISELED_TUNGSTEN_BRICKS);
        block(RegBlocks.CHISELED_TUNGSTEN_BLOCK);
        block(RegBlocks.TUNGSTEN_BRICKS);

        block(RegBlocks.DECAYING_TUNGSTEN_BLOCK);
        block(RegBlocks.POLISHED_DECAYING_TUNGSTEN);
        block(RegBlocks.CUT_DECAYING_TUNGSTEN_BLOCK);
        block(RegBlocks.CHISELED_DECAYING_TUNGSTEN_BRICKS);
        block(RegBlocks.CHISELED_DECAYING_TUNGSTEN_BLOCK);
        block(RegBlocks.DECAYING_TUNGSTEN_BRICKS);

        block(RegBlocks.CORRODED_TUNGSTEN_BLOCK);
        block(RegBlocks.POLISHED_CORRODED_TUNGSTEN);
        block(RegBlocks.CUT_CORRODED_TUNGSTEN_BLOCK);
        block(RegBlocks.CHISELED_CORRODED_TUNGSTEN_BRICKS);
        block(RegBlocks.CHISELED_CORRODED_TUNGSTEN_BLOCK);
        block(RegBlocks.CORRODED_TUNGSTEN_BRICKS);
        
        block(RegBlocks.TUNGSTEN_SPONGE);
        block(RegBlocks.HOT_TUNGSTEN_SPONGE);

        block(RegBlocks.TIN_ORE);
        block(RegBlocks.DEEPSLATE_TIN_ORE);
        block(RegBlocks.RAW_TIN_BLOCK);
        block(RegBlocks.TIN_BLOCK);
        block(RegBlocks.TIN_TILES);
        block(RegBlocks.TIN_BRICKS);

        block(RegBlocks.UNKNOWN_ROOTS_BLOCK);
        block(RegBlocks.QUARTZOLITE);
        block(RegBlocks.BASALT_ENCRUSTED_MOONSTONE);
        block(RegBlocks.MOONSTONE);
        block(RegBlocks.PYRITE_BLOCK);

        block(RegBlocks.AETHERROCK);
        block(RegBlocks.POLISHED_AETHERROCK);
        block(RegBlocks.AETHERROCK_BRICKS);
        block(RegBlocks.AETHERROCK_TILES);
        block(RegBlocks.CRACKED_AETHERROCK_BRICKS);
        wall(RegBlocks.AETHERROCK_WALL, RegBlocks.AETHERROCK);
        block(RegBlocks.AETHERROCK_SLAB);
        block(RegBlocks.AETHERROCK_STAIR);
        wall(RegBlocks.POLISHED_AETHERROCK_WALL, RegBlocks.POLISHED_AETHERROCK);
        block(RegBlocks.POLISHED_AETHERROCK_SLAB);
        block(RegBlocks.POLISHED_AETHERROCK_STAIR);
        wall(RegBlocks.AETHERROCK_BRICKS_WALL, RegBlocks.AETHERROCK_BRICKS);
        block(RegBlocks.AETHERROCK_BRICKS_SLAB);
        block(RegBlocks.AETHERROCK_BRICKS_STAIR);

        block(RegBlocks.CHERT);
        block(RegBlocks.CHERT_BRICKS);
        block(RegBlocks.POLISHED_CHERT);
        block(RegBlocks.CHERT_PILLAR);
        wall(RegBlocks.CHERT_WALL, RegBlocks.CHERT);
        block(RegBlocks.CHERT_SLAB);
        block(RegBlocks.CHERT_STAIR);
        wall(RegBlocks.POLISHED_CHERT_WALL, RegBlocks.POLISHED_CHERT);
        block(RegBlocks.POLISHED_CHERT_SLAB);
        block(RegBlocks.POLISHED_CHERT_STAIR);
        wall(RegBlocks.CHERT_BRICKS_WALL, RegBlocks.CHERT_BRICKS);
        block(RegBlocks.CHERT_BRICKS_SLAB);
        block(RegBlocks.CHERT_BRICKS_STAIR);

        wall(RegBlocks.RHYOLITE_WALL, RegBlocks.RHYOLITE);
        block(RegBlocks.RHYOLITE_SLAB);
        block(RegBlocks.RHYOLITE_STAIR);
        wall(RegBlocks.POLISHED_RHYOLITE_WALL, RegBlocks.POLISHED_RHYOLITE);
        block(RegBlocks.POLISHED_RHYOLITE_SLAB);
        block(RegBlocks.POLISHED_RHYOLITE_STAIR);
        wall(RegBlocks.RHYOLITE_BRICKS_WALL, RegBlocks.RHYOLITE_BRICKS);
        block(RegBlocks.RHYOLITE_BRICKS_SLAB);
        block(RegBlocks.RHYOLITE_BRICKS_STAIR);
        block(RegBlocks.RHYOLITE_BRICKS);
        block(RegBlocks.RHYOLITE);
        block(RegBlocks.RHYOLITE_PILLAR);
        block(RegBlocks.POLISHED_RHYOLITE);

        block(RegBlocks.VERDITE_BLOCK);
        block(RegBlocks.VERDITE_BRICKS);

        block(RegBlocks.RHYOLITE_ENCRUSTED_MOONSTONE);
        block(RegBlocks.RHYOLITE_EMERALD_ORE);
        block(RegBlocks.RHYOLITE_EMERALD_ORE_CLUMP);

        block(RegBlocks.CELESTITE_BLOCK);
        block(RegBlocks.BUDDING_CELESTITE);
        block(RegBlocks.POLISHED_CELESTITE);
        block(RegBlocks.CELESTITE_BRICKS);
        wall(RegBlocks.POLISHED_CELESTITE_WALL, RegBlocks.POLISHED_CELESTITE);
        block(RegBlocks.POLISHED_CELESTITE_SLAB);
        block(RegBlocks.POLISHED_CELESTITE_STAIR);
        wall(RegBlocks.CELESTITE_BRICKS_WALL, RegBlocks.CELESTITE_BRICKS);
        block(RegBlocks.CELESTITE_BRICKS_SLAB);
        block(RegBlocks.CELESTITE_BRICKS_STAIR);

        block(RegBlocks.POLISHED_AMETHYST);
        block(RegBlocks.AMETHYST_BRICKS);
        wall(RegBlocks.POLISHED_AMETHYST_WALL, RegBlocks.POLISHED_AMETHYST);
        block(RegBlocks.POLISHED_AMETHYST_SLAB);
        block(RegBlocks.POLISHED_AMETHYST_STAIR);
        wall(RegBlocks.AMETHYST_BRICKS_WALL, RegBlocks.AMETHYST_BRICKS);
        block(RegBlocks.AMETHYST_BRICKS_SLAB);
        block(RegBlocks.AMETHYST_BRICKS_STAIR);

        block(RegBlocks.LIVINGSTONE_BLOCK);
        block(RegBlocks.LIVINGSTONE_BRICKS);
        block(RegBlocks.POLISHED_LIVINGSTONE);
        wall(RegBlocks.LIVINGSTONE_WALL, RegBlocks.LIVINGSTONE_BLOCK);
        block(RegBlocks.LIVINGSTONE_SLAB);
        block(RegBlocks.LIVINGSTONE_STAIR);
        wall(RegBlocks.POLISHED_LIVINGSTONE_WALL, RegBlocks.POLISHED_LIVINGSTONE);
        block(RegBlocks.POLISHED_LIVINGSTONE_SLAB);
        block(RegBlocks.POLISHED_LIVINGSTONE_STAIR);
        wall(RegBlocks.LIVINGSTONE_BRICKS_WALL, RegBlocks.LIVINGSTONE_BRICKS);
        block(RegBlocks.LIVINGSTONE_BRICKS_SLAB);
        block(RegBlocks.LIVINGSTONE_BRICKS_STAIR);
    }
}
