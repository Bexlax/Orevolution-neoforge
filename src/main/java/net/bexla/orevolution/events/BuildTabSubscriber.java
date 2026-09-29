package net.bexla.orevolution.events;

import com.notunanancyowen.spears.Spears;
import com.simibubi.create.AllItems;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegDataComponents;
import net.bexla.orevolution.init.RegItems;
import net.bexla.orevolution.init.modcompat.FDRegistry;
import net.bexla.orevolution.init.modcompat.SBRegistry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.infernalstudios.shieldexp.init.ItemsInit;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.List;
import java.util.function.Supplier;

@EventBusSubscriber(modid = Orevolution.MODID)
public class BuildTabSubscriber {
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tab = event.getTabKey();

        if(tab == CreativeModeTabs.REDSTONE_BLOCKS) {
            putAfter(event, Items.TNT, RegBlocks.RDX);
        }
        if(tab == CreativeModeTabs.COMBAT) {
            putAfter(event, Items.TNT, RegBlocks.RDX);

            putAfter(event, Items.IRON_HORSE_ARMOR,
                    RegItems.BRONZE_HORSE_ARMOR,
                    RegItems.STEEL_HORSE_ARMOR);

            putAfter(event, Items.SPECTRAL_ARROW, RegItems.FIERY_ARROW);

            putAfter(event, Items.STONE_SWORD, RegItems.TIN_SWORD);
            putAfter(event, RegItems.TIN_SWORD, RegItems.CASSITERITE_SWORD);
            putBefore(event, Items.WOODEN_SWORD, RegItems.LIVINGSTONE_SWORD);
            putAfter(event, RegItems.LIVINGSTONE_SWORD, RegItems.VERDITE_SWORD);
            putAfter(event, Items.IRON_SWORD, RegItems.STEEL_HEAVYWORK_SWORD);
            putBefore(event, Items.DIAMOND_SWORD, RegItems.PLATINUM_SWORD);
            putAfter(event, RegItems.PLATINUM_SWORD, RegItems.MOONSTONE_SWORD);
            putAfter(event, Items.GOLDEN_SWORD, RegItems.TUNGSTEN_SWORD);
            putAfter(event, Items.NETHERITE_SWORD, RegItems.AETHERSTEEL_SWORD);

            putAfter(event, Items.STONE_AXE, RegItems.TIN_AXE);
            putAfter(event, RegItems.TIN_AXE, RegItems.CASSITERITE_AXE);
            putBefore(event, Items.WOODEN_AXE, RegItems.LIVINGSTONE_AXE);
            putAfter(event, RegItems.LIVINGSTONE_AXE, RegItems.VERDITE_AXE);
            putBefore(event, Items.DIAMOND_AXE, RegItems.PLATINUM_AXE);
            putBefore(event, RegItems.PLATINUM_AXE, RegItems.STEEL_HEAVYWORK_AXE);
            putAfter(event, RegItems.PLATINUM_AXE, RegItems.MOONSTONE_AXE);
            putAfter(event, Items.GOLDEN_AXE, RegItems.TUNGSTEN_AXE);
            putAfter(event, Items.NETHERITE_AXE, RegItems.AETHERSTEEL_AXE);

            putBefore(event, Items.LEATHER_HELMET,
                    RegItems.LIVINGSTONE_HELMET,
                    RegItems.LIVINGSTONE_CHESTPLATE,
                    RegItems.LIVINGSTONE_LEGGINGS,
                    RegItems.LIVINGSTONE_BOOTS);

            putAfter(event, RegItems.LIVINGSTONE_BOOTS,
                    RegItems.VERDITE_HELMET,
                    RegItems.VERDITE_CHESTPLATE,
                    RegItems.VERDITE_LEGGINGS,
                    RegItems.VERDITE_BOOTS);

            putAfter(event, Items.LEATHER_BOOTS,
                    RegItems.BRONZE_HELMET,
                    RegItems.BRONZE_CHESTPLATE,
                    RegItems.BRONZE_LEGGINGS,
                    RegItems.BRONZE_BOOTS);

            putAfter(event, Items.IRON_BOOTS,
                    RegItems.PLATINUM_HELMET,
                    RegItems.PLATINUM_CHESTPLATE,
                    RegItems.PLATINUM_LEGGINGS,
                    RegItems.PLATINUM_BOOTS);

            putAfter(event, RegItems.PLATINUM_BOOTS,
                    RegItems.MOONSTONE_HELMET,
                    RegItems.MOONSTONE_CHESTPLATE,
                    RegItems.MOONSTONE_LEGGINGS,
                    RegItems.MOONSTONE_BOOTS);

            putAfter(event, Items.GOLDEN_BOOTS,
                    RegItems.TUNGSTEN_HELMET,
                    RegItems.TUNGSTEN_CHESTPLATE,
                    RegItems.TUNGSTEN_LEGGINGS,
                    RegItems.TUNGSTEN_BOOTS);

            putAfter(event, Items.NETHERITE_BOOTS,
                    RegItems.AETHERSTEEL_HELMET,
                    RegItems.AETHERSTEEL_CHESTPLATE,
                    RegItems.AETHERSTEEL_LEGGINGS,
                    RegItems.AETHERSTEEL_BOOTS);

            if(ModList.get().isLoaded("shieldexp")) {
                putAfter(event, ItemsInit.NETHERITE_SHIELD.get(), RegItems.AETHERSTEEL_SHIELD);
                putBefore(event, ItemsInit.DIAMOND_SHIELD.get(), RegItems.PLATINUM_SHIELD);
                putAfter(event, ItemsInit.WOODEN_SHIELD.get(), RegItems.TIN_SHIELD);
                putBefore(event, RegItems.PLATINUM_SHIELD, RegItems.MOONSTONE_SHIELD);

                putBefore(event, ItemsInit.WOODEN_SHIELD.get(), RegItems.LIVINGSTONE_SHIELD);
                putAfter(event, RegItems.LIVINGSTONE_SHIELD, RegItems.VERDITE_SHIELD);
            }
            if(ModList.get().isLoaded("spears")) {
                putAfter(event, Spears.STONE_SPEAR, SBRegistry.TIN_SPEAR);
                putBefore(event, Spears.IRON_SPEAR, SBRegistry.PLATINUM_SPEAR);
                putAfter(event, SBRegistry.PLATINUM_SPEAR.get(), SBRegistry.MOONSTONE_SPEAR);
                putAfter(event, Spears.NETHERITE_SPEAR, SBRegistry.AETHERSTEEL_SPEAR);

                putBefore(event, Spears.WOODEN_SPEAR, SBRegistry.LIVINGSTONE_SPEAR);
                putAfter(event, SBRegistry.LIVINGSTONE_SPEAR.get(), SBRegistry.VERDITE_SPEAR);
            }

//            if(ModCompat.isModLoaded(ModCompat.archery())) {
//                putAfter(event, ArcheryItems.NETHERITE_BOW, RegItemsAE.AETHERSTEEL_BOW);
//                putBefore(event, ArcheryItems.IRON_BOW, RegItemsAE.TIN_BOW);
//                putAfter(event, ArcheryItems.IRON_BOW, RegItemsAE.PLATINUM_BOW);
//
//                putAfter(event, ArcheryItems.NETHERITE_ARROW, RegItemsAE.AETHERSTEEL_ARROW);
//                putBefore(event, ArcheryItems.IRON_ARROW, RegItemsAE.TIN_BOW);
//                putAfter(event, ArcheryItems.IRON_ARROW, RegItemsAE.PLATINUM_ARROW);
//            }

            for (byte b0 : new byte[]{6, 5, 4, 3, 2, 1}) {
                ItemStack itemstack = new ItemStack(RegItems.PROFESSIONAL_FIREWORK_ROCKET.get());
                itemstack.set(DataComponents.FIREWORKS, new Fireworks(b0 + 2, List.of()));

                ItemStack rocket = new ItemStack(Items.FIREWORK_ROCKET);
                rocket.set(DataComponents.FIREWORKS, new Fireworks(3, List.of()));

                event.insertAfter(rocket, itemstack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }
        }
        else if(tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            putBefore(event, Items.WOODEN_SHOVEL,
                    RegItems.LIVINGSTONE_SHOVEL,
                    RegItems.LIVINGSTONE_PICKAXE,
                    RegItems.LIVINGSTONE_AXE,
                    RegItems.LIVINGSTONE_HOE);

            putAfter(event, RegItems.LIVINGSTONE_HOE,
                    RegItems.VERDITE_SHOVEL,
                    RegItems.VERDITE_PICKAXE,
                    RegItems.VERDITE_AXE,
                    RegItems.VERDITE_HOE);

            putAfter(event, Items.STONE_HOE,
                    RegItems.TIN_SHOVEL,
                    RegItems.TIN_PICKAXE,
                    RegItems.TIN_AXE,
                    RegItems.TIN_HOE);

            putAfter(event, RegItems.TIN_HOE,
                    RegItems.CASSITERITE_SHOVEL,
                    RegItems.CASSITERITE_PICKAXE,
                    RegItems.CASSITERITE_AXE,
                    RegItems.CASSITERITE_HOE);

            putAfter(event, Items.GOLDEN_HOE,
                    RegItems.TUNGSTEN_SHOVEL,
                    RegItems.TUNGSTEN_PICKAXE,
                    RegItems.TUNGSTEN_AXE,
                    RegItems.TUNGSTEN_HOE);

            putAfter(event, Items.IRON_HOE,
                    RegItems.STEEL_HEAVYWORK_SHOVEL,
                    RegItems.STEEL_HEAVYWORK_PICKAXE,
                    RegItems.STEEL_HEAVYWORK_AXE,
                    RegItems.STEEL_HEAVYWORK_HOE);

            putBefore(event, Items.DIAMOND_SHOVEL,
                    RegItems.PLATINUM_SHOVEL,
                    RegItems.PLATINUM_PICKAXE,
                    RegItems.PLATINUM_AXE,
                    RegItems.PLATINUM_HOE);

            putAfter(event, RegItems.PLATINUM_HOE,
                    RegItems.MOONSTONE_SHOVEL,
                    RegItems.MOONSTONE_PICKAXE,
                    RegItems.MOONSTONE_AXE,
                    RegItems.MOONSTONE_HOE);

            putAfter(event, Items.NETHERITE_HOE,
                    RegItems.AETHERSTEEL_SHOVEL,
                    RegItems.AETHERSTEEL_PICKAXE,
                    RegItems.AETHERSTEEL_AXE,
                    RegItems.AETHERSTEEL_HOE);

            putAfter(event, Items.MILK_BUCKET, RegBlocks.TUNGSTEN_SPONGE, RegBlocks.HOT_TUNGSTEN_SPONGE);

            putBefore(event, Items.COMPASS, RegItems.BRONZE_RADAR, RegItems.GEO_SCANNER, RegItems.MOTION_DETECTOR);

            for (byte b0 : new byte[]{6, 5, 4, 3, 2, 1}) {
                ItemStack itemstack = new ItemStack(RegItems.PROFESSIONAL_FIREWORK_ROCKET.get());
                itemstack.set(DataComponents.FIREWORKS, new Fireworks(b0 + 2, List.of()));

                ItemStack rocket = new ItemStack(Items.FIREWORK_ROCKET);
                rocket.set(DataComponents.FIREWORKS, new Fireworks(3, List.of()));

                event.insertAfter(rocket, itemstack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }

            List<DataComponentType<Unit>> components = List.of(
                    RegDataComponents.AMETHYST_TOTEM.get(),
                    RegDataComponents.CELESTITE_TOTEM.get(),
                    RegDataComponents.EMERALD_TOTEM.get(),
                    RegDataComponents.QUARTZ_TOTEM.get(),
                    RegDataComponents.LAPIS_TOTEM.get(),
                    RegDataComponents.DIAMOND_TOTEM.get()
                    );

            for (DataComponentType<Unit> component : components) {
                ItemStack itemstack = new ItemStack(RegItems.BRONZE_TOTEM.get());
                itemstack.set(component, Unit.INSTANCE);

                event.insertAfter(Items.SADDLE.getDefaultInstance(), itemstack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }
        }
        else if(tab == CreativeModeTabs.INGREDIENTS) {
            putBefore(event, Items.IRON_INGOT, RegItems.TIN_INGOT);
            putBefore(event, RegItems.TIN_INGOT, RegItems.VERDITE_INGOT);
            putBefore(event, RegItems.VERDITE_INGOT, RegItems.LIVINGSTONE_SHARD);
            putAfter(event, RegItems.TIN_INGOT, RegItems.CASSITERITE_INGOT);
            putAfter(event, Items.IRON_INGOT, RegItems.PLATINUM_INGOT);
            putAfter(event, RegItems.PLATINUM_INGOT, RegItems.TUNGSTEN_INGOT);
            putAfter(event, RegItems.TUNGSTEN_INGOT, RegItems.BRONZE_ALLOY);
            putAfter(event, Items.NETHERITE_INGOT, RegItems.AETHERSTEEL_CHUNK, RegItems.AETHERSTEEL_INGOT);
            putAfter(event, RegItems.BRONZE_ALLOY, RegItems.STEEL_ALLOY);
            putAfter(event, Items.DIAMOND, RegBlocks.MOONSTONE);
            putAfter(event, Items.ANCIENT_DEBRIS, RegBlocks.PRIMITIVE_AETHERROCK);

            putBefore(event, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, RegItems.DOWNGRADE_TEMPLATE, RegItems.BASIC_TEMPLATE);

            putAfter(event, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE,
                    RegItems.AETHERSTEEL_TEMPLATE,
                    RegItems.REINFORCED_TEMPLATE,
                    RegItems.COATING_TEMPLATE);

            putBefore(event, Items.AMETHYST_SHARD, RegItems.CELESTITE_SHARD);
            putBefore(event, RegItems.CELESTITE_SHARD, RegItems.PYRITE);

            putBefore(event, Items.IRON_NUGGET, RegItems.TIN_NUGGET);
            putAfter(event, RegItems.TIN_NUGGET, RegItems.CASSITERITE_NUGGET);
            putAfter(event, Items.IRON_NUGGET, RegItems.PLATINUM_NUGGET);
            putAfter(event, Items.GOLD_NUGGET, RegItems.TUNGSTEN_NUGGET);
            putBefore(event, Items.GOLD_NUGGET, RegItems.VERDITE_NUGGET);
            putAfter(event, Items.GOLD_NUGGET, RegItems.QUARTZ_CHIP);

            putAfter(event, Items.EXPERIENCE_BOTTLE, RegItems.BRONZE_TOTEM);

            putBefore(event, Items.RAW_IRON, RegItems.RAW_TIN);
            putAfter(event, RegItems.RAW_TIN, RegItems.RAW_CASSITERITE);
            putAfter(event, Items.RAW_IRON, RegItems.RAW_PLATINUM);
            putAfter(event, RegItems.RAW_PLATINUM, RegItems.RAW_TUNGSTEN);
        }
        else if (tab == CreativeModeTabs.BUILDING_BLOCKS) {
            putBefore(event, Items.AMETHYST_BLOCK,
                    RegBlocks.POLISHED_AMETHYST,
                    RegBlocks.POLISHED_AMETHYST_SLAB,
                    RegBlocks.POLISHED_AMETHYST_STAIR,
                    RegBlocks.POLISHED_AMETHYST_WALL,
                    RegBlocks.AMETHYST_BRICKS,
                    RegBlocks.AMETHYST_BRICKS_SLAB,
                    RegBlocks.AMETHYST_BRICKS_STAIR,
                    RegBlocks.AMETHYST_BRICKS_WALL);

            putBefore(event, Items.IRON_BLOCK, RegBlocks.TIN_BLOCK);
            putAfter(event, RegBlocks.TIN_BLOCK, RegBlocks.CASSITERITE_BLOCK);
            putBefore(event, RegBlocks.TIN_BLOCK,
                    RegBlocks.VERDITE_BLOCK,
                    RegBlocks.VERDITE_BRICKS);
            putBefore(event, RegBlocks.VERDITE_BLOCK,
                    RegBlocks.LIVINGSTONE_BLOCK,
                    RegBlocks.POLISHED_LIVINGSTONE,
                    RegBlocks.POLISHED_LIVINGSTONE_SLAB,
                    RegBlocks.POLISHED_LIVINGSTONE_STAIR,
                    RegBlocks.POLISHED_LIVINGSTONE_WALL,
                    RegBlocks.LIVINGSTONE_BRICKS,
                    RegBlocks.LIVINGSTONE_BRICKS_SLAB,
                    RegBlocks.LIVINGSTONE_BRICKS_STAIR,
                    RegBlocks.LIVINGSTONE_BRICKS_WALL);
            putBefore(event, Items.IRON_BLOCK, RegBlocks.PLATINUM_BLOCK);
            putAfter(event, RegBlocks.PLATINUM_BLOCK, RegBlocks.TUNGSTEN_BLOCK);
            putAfter(event, Items.NETHERITE_BLOCK, RegBlocks.AETHERSTEEL_BLOCK);
            putAfter(event, RegBlocks.AETHERSTEEL_BLOCK, RegBlocks.BRONZE_BLOCK);
            putAfter(event, RegBlocks.BRONZE_BLOCK, RegBlocks.BRONZE_TILES);
            putAfter(event, RegBlocks.BRONZE_BLOCK, RegBlocks.STEEL_BLOCK);

            putAfter(event, Items.AMETHYST_BLOCK,
                    RegBlocks.CELESTITE_BLOCK,
                    RegBlocks.POLISHED_CELESTITE,
                    RegBlocks.POLISHED_CELESTITE_SLAB,
                    RegBlocks.POLISHED_CELESTITE_STAIR,
                    RegBlocks.POLISHED_CELESTITE_WALL,
                    RegBlocks.CELESTITE_BRICKS,
                    RegBlocks.CELESTITE_BRICKS_STAIR,
                    RegBlocks.CELESTITE_BRICKS_WALL);

            putAfter(event, RegBlocks.PLATINUM_BLOCK, RegBlocks.PLATINUM_PILLAR);
            putAfter(event, RegBlocks.PLATINUM_PILLAR, RegBlocks.PLATINUM_TILES);
            putAfter(event, RegBlocks.PLATINUM_TILES, RegBlocks.PLATINUM_BARS);
            putAfter(event, Items.GOLD_BLOCK, RegBlocks.GOLD_PILLAR);
            putAfter(event, RegBlocks.GOLD_PILLAR, RegBlocks.GOLD_TILES);
            putAfter(event, RegBlocks.GOLD_TILES, RegBlocks.GOLD_BARS);
            putAfter(event, RegBlocks.TIN_BLOCK, RegBlocks.TIN_BRICKS);
            putAfter(event, RegBlocks.TIN_BRICKS, RegBlocks.TIN_TILES);
            putAfter(event, RegBlocks.TIN_TILES, RegBlocks.TIN_BARS);
            putAfter(event, RegBlocks.BRONZE_BLOCK, RegBlocks.BRONZE_BARS);
            putAfter(event, Items.RED_NETHER_BRICK_WALL, RegBlocks.AETHERROCK);
            putAfter(event, RegBlocks.AETHERROCK,
                    RegBlocks.AETHERROCK_STAIR,
                    RegBlocks.AETHERROCK_SLAB,
                    RegBlocks.AETHERROCK_WALL);
            putAfter(event, RegBlocks.AETHERROCK_WALL, RegBlocks.POLISHED_AETHERROCK);
            putAfter(event, RegBlocks.POLISHED_AETHERROCK,
                    RegBlocks.POLISHED_AETHERROCK_STAIR,
                    RegBlocks.POLISHED_AETHERROCK_SLAB,
                    RegBlocks.POLISHED_AETHERROCK_WALL);
            putAfter(event, RegBlocks.POLISHED_AETHERROCK_WALL, RegBlocks.AETHERROCK_BRICKS);
            putAfter(event, RegBlocks.AETHERROCK_BRICKS,
                    RegBlocks.AETHERROCK_BRICKS_STAIR,
                    RegBlocks.AETHERROCK_BRICKS_SLAB,
                    RegBlocks.AETHERROCK_BRICKS_WALL,
                    RegBlocks.CRACKED_AETHERROCK_BRICKS,
                    RegBlocks.AETHERROCK_TILES);
            putAfter(event, RegBlocks.TUNGSTEN_BLOCK, RegBlocks.POLISHED_TUNGSTEN, RegBlocks.TUNGSTEN_BRICKS, RegBlocks.CUT_TUNGSTEN_BLOCK, RegBlocks.CHISELED_TUNGSTEN_BLOCK, RegBlocks.CHISELED_TUNGSTEN_BRICKS, RegBlocks.TUNGSTEN_BARS);
            putAfter(event, RegBlocks.STEEL_BLOCK,
                    RegBlocks.CUT_STEEL_BLOCK,
                    RegBlocks.CUT_STEEL_STAIR,
                    RegBlocks.CUT_STEEL_SLAB,
                    RegBlocks.STEEL_PILLAR,
                    RegBlocks.STEEL_DOOR, RegBlocks.STEEL_TRAPDOOR, RegBlocks.STEEL_BARS);

            putAfter(event, Items.TUFF, RegBlocks.CHERT);
            putAfter(event, RegBlocks.CHERT,
                    RegBlocks.CHERT_STAIR,
                    RegBlocks.CHERT_SLAB,
                    RegBlocks.CHERT_WALL);
            putAfter(event, RegBlocks.CHERT_WALL, RegBlocks.POLISHED_CHERT);
            putAfter(event, RegBlocks.POLISHED_CHERT,
                    RegBlocks.POLISHED_CHERT_STAIR,
                    RegBlocks.POLISHED_CHERT_SLAB,
                    RegBlocks.POLISHED_CHERT_WALL);
            putAfter(event, RegBlocks.POLISHED_CHERT_WALL, RegBlocks.CHERT_BRICKS);
            putAfter(event, RegBlocks.CHERT_BRICKS,
                    RegBlocks.CHERT_BRICKS_STAIR,
                    RegBlocks.CHERT_BRICKS_SLAB,
                    RegBlocks.CHERT_BRICKS_WALL);
            putAfter(event, RegBlocks.CHERT_BRICKS_WALL, RegBlocks.CHERT_PILLAR);
            putAfter(event, RegBlocks.CHERT_PILLAR, RegBlocks.RHYOLITE);
            putAfter(event, RegBlocks.RHYOLITE,
                    RegBlocks.RHYOLITE_STAIR,
                    RegBlocks.RHYOLITE_SLAB,
                    RegBlocks.RHYOLITE_WALL);
            putAfter(event, RegBlocks.RHYOLITE_WALL, RegBlocks.POLISHED_RHYOLITE);
            putAfter(event, RegBlocks.POLISHED_RHYOLITE,
                    RegBlocks.POLISHED_RHYOLITE_STAIR,
                    RegBlocks.POLISHED_RHYOLITE_SLAB,
                    RegBlocks.POLISHED_RHYOLITE_WALL);
            putAfter(event, RegBlocks.POLISHED_RHYOLITE_WALL, RegBlocks.RHYOLITE_BRICKS);
            putAfter(event, RegBlocks.RHYOLITE_BRICKS,
                    RegBlocks.RHYOLITE_BRICKS_STAIR,
                    RegBlocks.RHYOLITE_BRICKS_SLAB,
                    RegBlocks.RHYOLITE_BRICKS_WALL);
            putAfter(event, RegBlocks.RHYOLITE_BRICKS_WALL, RegBlocks.RHYOLITE_PILLAR);
        }
        else if(tab == CreativeModeTabs.NATURAL_BLOCKS) {
            putAfter(event, Items.WHEAT_SEEDS, RegItems.PETRIFIED_SEED);
            putAfter(event, RegItems.PETRIFIED_SEED, RegItems.DEAD_SEED);

            putAfter(event, Items.DEEPSLATE_EMERALD_ORE, RegBlocks.RHYOLITE_EMERALD_ORE);
            putAfter(event, RegBlocks.RHYOLITE_EMERALD_ORE, RegBlocks.RHYOLITE_EMERALD_ORE_CLUMP);

            putBefore(event, Items.IRON_ORE, RegBlocks.TIN_ORE, RegBlocks.DEEPSLATE_TIN_ORE);
            putAfter(event, RegBlocks.DEEPSLATE_TIN_ORE, RegBlocks.CASSITERITE_ORE, RegBlocks.DEEPSLATE_CASSITERITE_ORE, RegBlocks.NETHER_CASSITERITE_ORE);
            putBefore(event, Items.DIAMOND_ORE, RegBlocks.PLATINUM_ORE, RegBlocks.DEEPSLATE_PLATINUM_ORE);
            putBefore(event, Items.COAL_ORE, RegBlocks.NETHER_XP_ORE);
            putAfter(event, Items.COAL_ORE, RegBlocks.QUARTZOLITE);
            putAfter(event, RegBlocks.QUARTZOLITE, RegBlocks.RHYOLITE_ENCRUSTED_MOONSTONE, RegBlocks.BASALT_ENCRUSTED_MOONSTONE);
            putAfter(event, RegBlocks.RHYOLITE_ENCRUSTED_MOONSTONE, RegBlocks.MOONSTONE);
            putBefore(event, RegBlocks.NETHER_XP_ORE, RegBlocks.END_XP_ORE);
            putAfter(event, Items.NETHER_QUARTZ_ORE, RegBlocks.NETHER_TUNGSTEN_ORE);
            putAfter(event, Items.ANCIENT_DEBRIS, RegBlocks.PRIMITIVE_AETHERROCK);

            putAfter(event, Items.TUFF, RegBlocks.CHERT);
            putAfter(event, RegBlocks.CHERT, RegBlocks.RHYOLITE);

            putAfter(event, Items.TWISTING_VINES, RegItems.VINNELIO);

            putAfter(event, Items.DEAD_BUSH, RegBlocks.UNKNOWN_ROOTS);
            putAfter(event, RegBlocks.UNKNOWN_ROOTS, RegBlocks.UNKNOWN_ROOTS_BLOCK);
            putAfter(event, Items.POINTED_DRIPSTONE, RegBlocks.EMERALD_CLUSTER, RegBlocks.PRISMARINE_CLUSTER);
            putAfter(event, RegBlocks.UNKNOWN_ROOTS_BLOCK, RegBlocks.CORELIO);
            putBefore(event, Items.LILAC, RegBlocks.LARGE_CORELIO);

            putBefore(event, Items.RAW_IRON_BLOCK, RegBlocks.RAW_TIN_BLOCK);
            putAfter(event, RegBlocks.RAW_TIN_BLOCK, RegBlocks.RAW_CASSITERITE_BLOCK);
            putAfter(event, Items.RAW_IRON_BLOCK, RegBlocks.RAW_PLATINUM_BLOCK);
            putAfter(event, RegBlocks.RAW_PLATINUM_BLOCK, RegBlocks.RAW_TUNGSTEN_BLOCK);
        }
        else if(tab == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            putBefore(event, Items.LANTERN,
                    RegBlocks.TIN_LANTERN, RegBlocks.TIN_SOUL_LANTERN,
                    RegBlocks.BRONZE_LANTERN, RegBlocks.BRONZE_SOUL_LANTERN,
                    RegBlocks.PLATINUM_LANTERN, RegBlocks.PLATINUM_SOUL_LANTERN,
                    RegBlocks.GOLDEN_LANTERN, RegBlocks.GOLDEN_SOUL_LANTERN);

            putAfter(event, Items.DAMAGED_ANVIL, RegBlocks.STEEL_ANVIL);
        }
        else if(tab == CreativeModeTabs.FOOD_AND_DRINKS) {
            putAfter(event, Items.HONEY_BOTTLE, RegItems.GLOWING_BOTTLE);
            event.insertAfter(BrewingSubscriber.potionIngredient(Potions.STRONG_SWIFTNESS).getItems()[0], new ItemStack(RegItems.LIGHTNING_BOTTLE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(BrewingSubscriber.potionIngredient(Potions.STRONG_STRENGTH).getItems()[0], new ItemStack(RegItems.FIERCE_BOTTLE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(BrewingSubscriber.potionIngredient(Potions.STRONG_REGENERATION).getItems()[0], new ItemStack(RegItems.LIFE_BOTTLE.get()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            putBefore(event, Items.GOLDEN_APPLE, RegItems.PLATINUM_APPLE);
            putAfter(event, Items.ENCHANTED_GOLDEN_APPLE, RegItems.VERDITE_APPLE);
            putAfter(event, Items.SPIDER_EYE, RegItems.VERDITE_SPIDER_EYE);
            putAfter(event, Items.SWEET_BERRIES, RegItems.PLATINUM_BERRIES);

            putBefore(event, Items.GOLDEN_APPLE, RegItems.FOOLS_APPLE);
            putBefore(event, Items.GOLDEN_CARROT, RegItems.FOOLS_CARROT);

            putAfter(event, Items.GLOW_BERRIES, RegItems.ANCIENT_FRUIT);
            putAfter(event, Items.MUSHROOM_STEW, RegItems.ANCIENT_STEW);
        }

        if (ModList.get().isLoaded("farmersdelight") && tab.location().equals(FD_TAB)) {
            putAfter(event, ModItems.FLINT_KNIFE.get(), FDRegistry.TIN_KNIFE);
            putAfter(event, FDRegistry.TIN_KNIFE.get(), FDRegistry.LIVINGSTONE_KNIFE);
            putAfter(event, FDRegistry.LIVINGSTONE_KNIFE.get(), FDRegistry.VERDITE_KNIFE);
            putAfter(event, ModItems.IRON_KNIFE.get(), FDRegistry.PLATINUM_KNIFE);
            putAfter(event, FDRegistry.PLATINUM_KNIFE.get(), FDRegistry.MOONSTONE_KNIFE);
            putAfter(event, ModItems.NETHERITE_KNIFE.get(), FDRegistry.AETHERSTEEL_KNIFE);
        }

        if (ModList.get().isLoaded("create") && tab.location().equals(CREATE_TAB)) {
            putAfter(event, AllItems.CRUSHED_TIN, RegItems.CRUSHED_TUNGSTEN, RegItems.CRUSHED_AETHERSTEEL);
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
