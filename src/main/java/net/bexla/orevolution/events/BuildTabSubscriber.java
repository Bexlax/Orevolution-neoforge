package net.bexla.orevolution.events;

import com.simibubi.create.AllItems;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.init.FDRegistry;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Supplier;

@EventBusSubscriber(modid = Orevolution.MODID)
public class BuildTabSubscriber {
    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();

        if(tab == CreativeModeTabs.COMBAT) {
            putBefore(event, Items.TOTEM_OF_UNDYING, RegItems.BRONZE_TOTEM_EMERALD);
            putAfter(event, RegItems.BRONZE_TOTEM_EMERALD.get(), RegItems.BRONZE_TOTEM_LAPIS_LAZULI);
            putAfter(event, RegItems.BRONZE_TOTEM_LAPIS_LAZULI.get(), RegItems.BRONZE_TOTEM_DIAMOND);

            putAfter(event, Items.STONE_SWORD, RegItems.TIN_SWORD);
            putBefore(event, Items.WOODEN_SWORD, RegItems.LIVINGSTONE_SWORD);
            putAfter(event, RegItems.LIVINGSTONE_SWORD.get(), RegItems.VERDITE_SWORD);
            putAfter(event, Items.IRON_SWORD, RegItems.STEEL_SCYTHE);
            putBefore(event, Items.DIAMOND_SWORD, RegItems.PLATINUM_SWORD);
            putAfter(event, Items.NETHERITE_SWORD, RegItems.AETHERSTEEL_SWORD);

            putAfter(event, Items.STONE_AXE, RegItems.TIN_AXE);
            putBefore(event, Items.WOODEN_AXE, RegItems.LIVINGSTONE_AXE);
            putAfter(event, RegItems.LIVINGSTONE_AXE.get(), RegItems.VERDITE_AXE);
            putBefore(event, Items.DIAMOND_AXE, RegItems.PLATINUM_AXE);
            putAfter(event, Items.NETHERITE_AXE, RegItems.AETHERSTEEL_AXE);

            putBefore(event, Items.LEATHER_HELMET, RegItems.LIVINGSTONE_HELMET);
            putAfter(event, RegItems.LIVINGSTONE_HELMET.get(), RegItems.LIVINGSTONE_CHESTPLATE);
            putAfter(event, RegItems.LIVINGSTONE_CHESTPLATE.get(), RegItems.LIVINGSTONE_LEGGINGS);
            putAfter(event, RegItems.LIVINGSTONE_LEGGINGS.get(), RegItems.LIVINGSTONE_BOOTS);

            putAfter(event, RegItems.LIVINGSTONE_BOOTS.get(), RegItems.VERDITE_HELMET);
            putAfter(event, RegItems.VERDITE_HELMET.get(), RegItems.VERDITE_CHESTPLATE);
            putAfter(event, RegItems.VERDITE_CHESTPLATE.get(), RegItems.VERDITE_LEGGINGS);
            putAfter(event, RegItems.VERDITE_LEGGINGS.get(), RegItems.VERDITE_BOOTS);

            if(!ModList.get().isLoaded("create")) {
                putAfter(event, Items.CHAINMAIL_BOOTS, RegItems.BRONZE_HELMET);
                putAfter(event, RegItems.BRONZE_HELMET.get(), RegItems.BRONZE_CHESTPLATE);
                putAfter(event, RegItems.BRONZE_CHESTPLATE.get(), RegItems.BRONZE_LEGGINGS);
                putAfter(event, RegItems.BRONZE_LEGGINGS.get(), RegItems.BRONZE_BOOTS);

                putAfter(event, RegItems.BRONZE_BOOTS.get(), RegItems.TUNGSTEN_HELMET);
                putAfter(event, RegItems.TUNGSTEN_HELMET.get(), RegItems.TUNGSTEN_CHESTPLATE);
                putAfter(event, RegItems.TUNGSTEN_CHESTPLATE.get(), RegItems.TUNGSTEN_LEGGINGS);
                putAfter(event, RegItems.TUNGSTEN_LEGGINGS.get(), RegItems.TUNGSTEN_BOOTS);
            }

            putAfter(event, Items.IRON_BOOTS, RegItems.PLATINUM_HELMET);
            putAfter(event, RegItems.PLATINUM_HELMET.get(), RegItems.PLATINUM_CHESTPLATE);
            putAfter(event, RegItems.PLATINUM_CHESTPLATE.get(), RegItems.PLATINUM_LEGGINGS);
            putAfter(event, RegItems.PLATINUM_LEGGINGS.get(), RegItems.PLATINUM_BOOTS);

            putAfter(event, Items.NETHERITE_BOOTS, RegItems.REINFORCED_NETHERITE_HELMET);
            putAfter(event, RegItems.REINFORCED_NETHERITE_HELMET.get(), RegItems.REINFORCED_NETHERITE_CHESTPLATE);
            putAfter(event, RegItems.REINFORCED_NETHERITE_CHESTPLATE.get(), RegItems.REINFORCED_NETHERITE_LEGGINGS);
            putAfter(event, RegItems.REINFORCED_NETHERITE_LEGGINGS.get(), RegItems.REINFORCED_NETHERITE_BOOTS);

            putAfter(event, RegItems.REINFORCED_NETHERITE_BOOTS.get(), RegItems.AETHERSTEEL_HELMET);
            putAfter(event, RegItems.AETHERSTEEL_HELMET.get(), RegItems.AETHERSTEEL_CHESTPLATE);
            putAfter(event, RegItems.AETHERSTEEL_CHESTPLATE.get(), RegItems.AETHERSTEEL_LEGGINGS);
            putAfter(event, RegItems.AETHERSTEEL_LEGGINGS.get(), RegItems.AETHERSTEEL_BOOTS);

//            if(ModCompat.isModLoaded(ModCompat.shield())) {
//                putAfter(event, ItemsInit.NETHERITE_SHIELD.get(), RegItems.AETHERSTEEL_SHIELD);
//                putBefore(event, ItemsInit.DIAMOND_SHIELD.get(), RegItems.PLATINUM_SHIELD);
//                putAfter(event, ItemsInit.WOODEN_SHIELD.get(), RegItems.TIN_SHIELD);
//
//                putBefore(event, ItemsInit.WOODEN_SHIELD.get(), RegItems.LIVINGSTONE_SHIELD);
//                putAfter(event, RegItems.LIVINGSTONE_SHIELD.get(), RegItems.VERDITE_SHIELD);
//                putAfter(event, RegItems.VERDITE_SHIELD.get(), RegItems.AMBER_SHIELD);
//            }

//            if(ModCompat.isModLoaded(ModCompat.archery())) {
//                putAfter(event, ArcheryItems.NETHERITE_BOW.get(), RegItemsAE.AETHERSTEEL_BOW);
//                putBefore(event, ArcheryItems.IRON_BOW.get(), RegItemsAE.TIN_BOW);
//                putAfter(event, ArcheryItems.IRON_BOW.get(), RegItemsAE.PLATINUM_BOW);
//
//                putAfter(event, ArcheryItems.NETHERITE_ARROW.get(), RegItemsAE.AETHERSTEEL_ARROW);
//                putBefore(event, ArcheryItems.IRON_ARROW.get(), RegItemsAE.TIN_BOW);
//                putAfter(event, ArcheryItems.IRON_ARROW.get(), RegItemsAE.PLATINUM_ARROW);
//            }
        }
        else if(tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            putBefore(event, Items.WOODEN_SHOVEL, RegItems.LIVINGSTONE_SHOVEL);
            putAfter(event, RegItems.LIVINGSTONE_SHOVEL.get(), RegItems.LIVINGSTONE_PICKAXE);
            putAfter(event, RegItems.LIVINGSTONE_PICKAXE.get(), RegItems.LIVINGSTONE_AXE);
            putAfter(event, RegItems.LIVINGSTONE_AXE.get(), RegItems.LIVINGSTONE_HOE);

            putAfter(event, RegItems.LIVINGSTONE_HOE.get(), RegItems.VERDITE_SHOVEL);
            putAfter(event, RegItems.VERDITE_SHOVEL.get(), RegItems.VERDITE_PICKAXE);
            putAfter(event, RegItems.VERDITE_PICKAXE.get(), RegItems.VERDITE_AXE);
            putAfter(event, RegItems.VERDITE_AXE.get(), RegItems.VERDITE_HOE);

            putAfter(event, Items.STONE_HOE, RegItems.TIN_SHOVEL);
            putAfter(event, RegItems.TIN_SHOVEL.get(), RegItems.TIN_PICKAXE);
            putAfter(event, RegItems.TIN_PICKAXE.get(), RegItems.TIN_AXE);
            putAfter(event, RegItems.TIN_AXE.get(), RegItems.TIN_HOE);

            putAfter(event, Items.IRON_HOE, RegItems.STEEL_DIGGER);
            putAfter(event, RegItems.STEEL_DIGGER.get(), RegItems.STEEL_HAMMER);
            putAfter(event, RegItems.STEEL_HAMMER.get(), RegItems.STEEL_BROADAXE);
            putAfter(event, RegItems.STEEL_BROADAXE.get(), RegItems.STEEL_SCYTHE);

            putBefore(event, Items.DIAMOND_SHOVEL, RegItems.PLATINUM_SHOVEL);
            putAfter(event, RegItems.PLATINUM_SHOVEL.get(), RegItems.PLATINUM_PICKAXE);
            putAfter(event, RegItems.PLATINUM_PICKAXE.get(), RegItems.PLATINUM_AXE);
            putAfter(event, RegItems.PLATINUM_AXE.get(), RegItems.PLATINUM_HOE);

            putAfter(event, Items.NETHERITE_HOE, RegItems.AETHERSTEEL_SHOVEL);
            putAfter(event, RegItems.AETHERSTEEL_SHOVEL.get(), RegItems.AETHERSTEEL_PICKAXE);
            putAfter(event, RegItems.AETHERSTEEL_PICKAXE.get(), RegItems.AETHERSTEEL_AXE);
            putAfter(event, RegItems.AETHERSTEEL_AXE.get(), RegItems.AETHERSTEEL_HOE);

            putAfter(event, Items.MILK_BUCKET, RegBlocks.TUNGSTEN_SPONGE);
            putAfter(event, RegBlocks.TUNGSTEN_SPONGE.get(), RegBlocks.HOT_TUNGSTEN_SPONGE);

            putBefore(event, Items.COMPASS, RegItems.BRONZE_RADAR);
        }
        else if(tab == CreativeModeTabs.INGREDIENTS) {
            putBefore(event, Items.IRON_INGOT, RegItems.TIN_INGOT);
            putBefore(event, RegItems.TIN_INGOT.get(), RegItems.VERDITE_INGOT);
            putBefore(event, RegItems.VERDITE_INGOT.get(), RegItems.LIVINGSTONE_SHARD);
            putAfter(event, Items.IRON_INGOT, RegItems.PLATINUM_INGOT);
            putAfter(event, RegItems.PLATINUM_INGOT.get(), RegItems.TUNGSTEN_INGOT);
            putAfter(event, RegItems.TUNGSTEN_INGOT.get(), RegItems.BRONZE_ALLOY);
            putAfter(event, Items.NETHERITE_INGOT, RegItems.AETHERSTEEL_INGOT);
            putAfter(event, RegItems.BRONZE_ALLOY.get(), RegItems.STEEL_ALLOY);

            putBefore(event, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, RegItems.BASIC_TEMPLATE);
            putAfter(event, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, RegItems.REINFORCED_TEMPLATE);
            putAfter(event, RegItems.REINFORCED_TEMPLATE.get(), RegItems.AETHERSTEEL_TEMPLATE);

            putAfter(event, Items.NETHERITE_SCRAP, RegItems.AETHERSTEEL_CHUNK);

            putBefore(event, Items.IRON_NUGGET, RegItems.TIN_NUGGET);
            putAfter(event, Items.IRON_NUGGET, RegItems.PLATINUM_NUGGET);
            putAfter(event, Items.GOLD_NUGGET, RegItems.TUNGSTEN_NUGGET);
            putBefore(event, Items.GOLD_NUGGET, RegItems.VERDITE_NUGGET);

            putAfter(event, Items.EXPERIENCE_BOTTLE, RegItems.BRONZE_TOTEM);

            putBefore(event, Items.RAW_IRON, RegItems.RAW_TIN);
            putAfter(event, Items.RAW_IRON, RegItems.RAW_PLATINUM);
            putAfter(event, RegItems.RAW_PLATINUM.get(), RegItems.RAW_TUNGSTEN);
        }
        else if (tab == CreativeModeTabs.BUILDING_BLOCKS) {
            putBefore(event, Items.IRON_BLOCK, RegBlocks.TIN_BLOCK);
            putBefore(event, RegBlocks.TIN_BLOCK.get(), RegBlocks.VERDITE_BLOCK);
            putBefore(event, RegBlocks.VERDITE_BLOCK.get(), RegBlocks.LIVINGSTONE_BLOCK);
            putAfter(event, RegBlocks.LIVINGSTONE_BLOCK.get(), RegBlocks.LIVINGSTONE_BRICKS);
            putAfter(event, RegBlocks.VERDITE_BLOCK.get(), RegBlocks.VERDITE_BRICKS);
            putAfter(event, Items.IRON_BLOCK, RegBlocks.PLATINUM_BLOCK);
            putAfter(event, RegBlocks.PLATINUM_BLOCK.get(), RegBlocks.TUNGSTEN_BLOCK);
            putAfter(event, Items.NETHERITE_BLOCK, RegBlocks.AETHERSTEEL_BLOCK);
            putAfter(event, RegBlocks.AETHERSTEEL_BLOCK.get(), RegBlocks.BRONZE_BLOCK);
            putAfter(event, RegBlocks.BRONZE_BLOCK.get(), RegBlocks.BRONZE_TILES);
            putAfter(event, RegBlocks.BRONZE_BLOCK.get(), RegBlocks.STEEL_BLOCK);

            putAfter(event, RegBlocks.PLATINUM_BLOCK.get(), RegBlocks.PLATINUM_PILLAR);
            putAfter(event, RegBlocks.PLATINUM_PILLAR.get(), RegBlocks.PLATINUM_TILES);
            putAfter(event, RegBlocks.PLATINUM_TILES.get(), RegBlocks.PLATINUM_BARS);
            putAfter(event, Blocks.GOLD_BLOCK, RegBlocks.GOLD_PILLAR);
            putAfter(event, RegBlocks.GOLD_PILLAR.get(), RegBlocks.GOLD_TILES);
            putAfter(event, RegBlocks.GOLD_TILES.get(), RegBlocks.GOLD_BARS);
            putAfter(event, RegBlocks.TIN_BLOCK.get(), RegBlocks.TIN_BRICKS);
            putAfter(event, RegBlocks.TIN_BRICKS.get(), RegBlocks.TIN_TILES);
            putAfter(event, RegBlocks.TIN_TILES.get(), RegBlocks.TIN_BARS);
            putAfter(event, RegBlocks.BRONZE_BLOCK.get(), RegBlocks.BRONZE_BARS);
            putAfter(event, Blocks.RED_NETHER_BRICK_WALL, RegBlocks.AETHERROCK);
            putAfter(event, RegBlocks.AETHERROCK.get(), RegBlocks.POLISHED_AETHERROCK);
            putAfter(event, RegBlocks.POLISHED_AETHERROCK.get(), RegBlocks.POLISHED_AETHERROCK_STAIR);
            putAfter(event, RegBlocks.POLISHED_AETHERROCK_STAIR.get(), RegBlocks.POLISHED_AETHERROCK_SLAB);
            putAfter(event, RegBlocks.POLISHED_AETHERROCK_SLAB.get(), RegBlocks.POLISHED_AETHERROCK_WALL);
            putAfter(event, RegBlocks.POLISHED_AETHERROCK_WALL.get(), RegBlocks.AETHERROCK_BRICKS);
            putAfter(event, RegBlocks.AETHERROCK_BRICKS.get(), RegBlocks.CRACKED_AETHERROCK_BRICKS);
            putAfter(event, RegBlocks.CRACKED_AETHERROCK_BRICKS.get(), RegBlocks.AETHERROCK_TILES);
            putAfter(event, RegBlocks.TUNGSTEN_BLOCK.get(), RegBlocks.POLISHED_TUNGSTEN);
            putAfter(event, RegBlocks.POLISHED_TUNGSTEN.get(), RegBlocks.TUNGSTEN_BRICKS);
            putAfter(event, RegBlocks.TUNGSTEN_BRICKS.get(), RegBlocks.CUT_TUNGSTEN_BLOCK);
            putAfter(event, RegBlocks.CUT_TUNGSTEN_BLOCK.get(), RegBlocks.CHISELED_TUNGSTEN_BLOCK);
            putAfter(event, RegBlocks.CHISELED_TUNGSTEN_BLOCK.get(), RegBlocks.CHISELED_TUNGSTEN_BRICKS);
            putAfter(event, RegBlocks.CHISELED_TUNGSTEN_BRICKS.get(), RegBlocks.TUNGSTEN_BARS);
            putAfter(event, RegBlocks.STEEL_BLOCK.get(), RegBlocks.CUT_STEEL_BLOCK);
            putAfter(event, RegBlocks.CUT_STEEL_BLOCK.get(), RegBlocks.STEEL_PILLAR);
            putAfter(event, RegBlocks.STEEL_PILLAR.get(), RegBlocks.STEEL_DOOR);
            putAfter(event, RegBlocks.STEEL_DOOR.get(), RegBlocks.STEEL_TRAPDOOR);
            putAfter(event, RegBlocks.STEEL_TRAPDOOR.get(), RegBlocks.STEEL_BARS);
        }
        else if(tab == CreativeModeTabs.NATURAL_BLOCKS) {
            putAfter(event, Items.WHEAT_SEEDS, RegItems.PETRIFIED_SEED);
            putAfter(event, RegItems.PETRIFIED_SEED.get(), RegItems.DEAD_SEED);

            putBefore(event, Items.IRON_ORE, RegBlocks.DEEPSLATE_TIN_ORE);
            putBefore(event, RegBlocks.DEEPSLATE_TIN_ORE.get(), RegBlocks.TIN_ORE);
            putBefore(event, Items.DIAMOND_ORE, RegBlocks.PLATINUM_ORE);
            putAfter(event, RegBlocks.PLATINUM_ORE.get(), RegBlocks.DEEPSLATE_PLATINUM_ORE);
            putBefore(event, Items.COAL_ORE, RegBlocks.NETHER_XP_ORE);
            putBefore(event, RegBlocks.NETHER_XP_ORE.get(), RegBlocks.END_XP_ORE);
            putAfter(event, Items.NETHER_QUARTZ_ORE, RegBlocks.NETHER_TUNGSTEN_ORE);
            putAfter(event, Blocks.ANCIENT_DEBRIS, RegBlocks.PRIMITIVE_AETHERROCK);
            putAfter(event, Blocks.CALCITE, RegBlocks.LIMESTONE);
            putAfter(event, RegBlocks.LIMESTONE.get(), RegBlocks.POLISHED_LIMESTONE);
            putAfter(event, RegBlocks.LIMESTONE.get(), RegBlocks.LIMESTONE_PILLAR);

            putBefore(event, Items.RAW_IRON_BLOCK, RegBlocks.RAW_TIN_BLOCK);
            putAfter(event, Items.RAW_IRON_BLOCK, RegBlocks.RAW_PLATINUM_BLOCK);
            putAfter(event, RegBlocks.RAW_PLATINUM_BLOCK.get(), RegBlocks.RAW_TUNGSTEN_BLOCK);
        }
        else if(tab == CreativeModeTabs.FOOD_AND_DRINKS) {
            putAfter(event, Items.GOLDEN_APPLE, RegItems.VERDITE_APPLE);
            putAfter(event, Items.SPIDER_EYE, RegItems.VERDITE_SPIDER_EYE);
            putAfter(event, Items.SWEET_BERRIES, RegItems.PLATINUM_BERRIES);
        }

        if (ModList.get().isLoaded("farmersdelight") && tab.location().equals(FD_TAB)) {
            putAfter(event, ModItems.FLINT_KNIFE.get(), FDRegistry.TIN_KNIFE);
            putAfter(event, FDRegistry.TIN_KNIFE.get(), FDRegistry.LIVINGSTONE_KNIFE);
            putAfter(event, FDRegistry.LIVINGSTONE_KNIFE.get(), FDRegistry.VERDITE_KNIFE);
            putAfter(event, ModItems.IRON_KNIFE.get(), FDRegistry.PLATINUM_KNIFE);
            putAfter(event, ModItems.NETHERITE_KNIFE.get(), FDRegistry.AETHERSTEEL_KNIFE);
        }

        if (ModList.get().isLoaded("create") && tab.location().equals(CREATE_TAB)) {
            putAfter(event, AllItems.CRUSHED_TIN, RegItems.CRUSHED_TUNGSTEN);
            putAfter(event, RegItems.CRUSHED_TUNGSTEN.get(), RegItems.CRUSHED_AETHERSTEEL);
        }
    }

    private static final ResourceLocation FD_TAB = ResourceLocation.fromNamespaceAndPath("farmersdelight", "farmersdelight");
    private static final ResourceLocation CREATE_TAB = ResourceLocation.fromNamespaceAndPath("create", "base");

    @SafeVarargs
    private static void putAfter(BuildCreativeModeTabContentsEvent event, ItemLike after, Supplier<? extends ItemLike>... supplier) {
        for (int i = supplier.length - 1; i >= 0; i--) {
            ItemLike key = supplier[i].get();
            event.insertAfter(new ItemStack(after), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

    @SafeVarargs
    private static void putBefore(BuildCreativeModeTabContentsEvent event, ItemLike before, Supplier<? extends ItemLike>... supplier) {
        for (Supplier<? extends ItemLike> supplier1 : supplier) {
            ItemLike key = supplier1.get();
            event.insertBefore(new ItemStack(before), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }

}
