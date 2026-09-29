package net.bexla.orevolution.content.data.utility;

import com.google.common.collect.Lists;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

import static net.bexla.orevolution.Orevolution.lc;

public class OrevolutionKeys {
    public static class Biomes {
        public static final List<ResourceKey<Biome>> BIOMES = Lists.newArrayList();
        public static final ResourceKey<Biome> GEODE_GARDENS = createKey("geode_gardens");
        public static final ResourceKey<Biome> PRISMATIC_GROTTO = createKey("prismatic_grotto");
        public static final ResourceKey<Biome> FORGOTTEN_CAVE = createKey("forgotten_cave");

        public static ResourceKey<Biome> createKey(String name) {
            ResourceKey<Biome> key = ResourceKey.create(Registries.BIOME, lc(name));
            BIOMES.add(key);
            return key;
        }
    }

    public static class ConfiguredWorldCarvers {
        public static final ResourceKey<ConfiguredWorldCarver<?>> QUARTZOLITE_CARVER = createKey("quartzolite_carver");

        private static ResourceKey<ConfiguredWorldCarver<?>> createKey(String pName) {
            return ResourceKey.create(Registries.CONFIGURED_CARVER, lc(pName));
        }
    }


    public static class BiomeModifiers {
        public static final ResourceKey<BiomeModifier> ADD_FORGOTTEN_CAVES_TOP_LAYER_MODIFICATIONS = createKey("add_forgotten_cave_top_layer_modifications");
        public static final ResourceKey<BiomeModifier> ADD_FORGOTTEN_CAVES_VEGETAL_FEATURES = createKey("add_forgotten_cave_vegetal_features");
        public static final ResourceKey<BiomeModifier> ADD_FORGOTTEN_CAVES_RAW_GENERATION = createKey("add_forgotten_cave_raw_generation");
        public static final ResourceKey<BiomeModifier> ADD_FORGOTTEN_CAVES_LOCAL_MODIFICATIONS = createKey("add_forgotten_cave_local_modifications");
        public static final ResourceKey<BiomeModifier> ADD_FORGOTTEN_CAVES_ORES = createKey("add_forgotten_cave_ores");

        public static final ResourceKey<BiomeModifier> ADD_GEODE_GARDENS_TOP_LAYER_MODIFICATIONS = createKey("add_geode_gardens_top_layer_modifications");
        public static final ResourceKey<BiomeModifier> ADD_GEODE_GARDENS_VEGETAL_FEATURES = createKey("add_geode_gardens_vegetal_features");
        public static final ResourceKey<BiomeModifier> ADD_GEODE_GARDENS_RAW_GENERATION = createKey("add_geode_gardens_raw_generation");
        public static final ResourceKey<BiomeModifier> ADD_GEODE_GARDENS_LOCAL_MODIFICATIONS = createKey("add_geode_gardens_local_modifications");
        public static final ResourceKey<BiomeModifier> ADD_GEODE_GARDENS_ORES = createKey("add_geode_gardens_ores");

        private static ResourceKey<BiomeModifier> createKey(String name) {
            return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc(name));
        }
    }

    public static class ConfiguredFeatures {
        public static final ResourceKey<ConfiguredFeature<?, ?>> BOULDER = createKey("boulder");
        public static final ResourceKey<ConfiguredFeature<?, ?>> FORGOTTEN_SPRING = createKey("forgotten_spring");
        public static final ResourceKey<ConfiguredFeature<?, ?>> CHERT_PATCH = createKey("chert_patch");
        public static final ResourceKey<ConfiguredFeature<?, ?>> QUARTZOLITE_DISK = createKey("quartzolite_disk");
        public static final ResourceKey<ConfiguredFeature<?, ?>> CHERT_DISK = createKey("chert_disk");
        public static final ResourceKey<ConfiguredFeature<?, ?>> FORGOTTEN_VEGETATION = createKey("forgotten_vegetation");
        public static final ResourceKey<ConfiguredFeature<?, ?>> MOONSTONE_PILLAR = createKey("moonstone_pillar");
        public static final ResourceKey<ConfiguredFeature<?, ?>> MOONSTONE_PILLAR_NETHER = createKey("moonstone_pillar_nether");
        public static final ResourceKey<ConfiguredFeature<?, ?>> BLACKSTONE_PILLAR = createKey("blackstone_pillar");
        public static final ResourceKey<ConfiguredFeature<?, ?>> RHYOLITE_PILLAR = createKey("rhyolite_pillar");

        public static final ResourceKey<ConfiguredFeature<?, ?>> VINNELIO_VINES = createKey("vinnelio_vines");

        public static final ResourceKey<ConfiguredFeature<?, ?>> AMETHYST_CRATER = createKey("amethyst_crater");
        public static final ResourceKey<ConfiguredFeature<?, ?>> GOLD_CRATER = createKey("gold_crater");
        public static final ResourceKey<ConfiguredFeature<?, ?>> PLATINUM_CRATER = createKey("platinum_crater");
        public static final ResourceKey<ConfiguredFeature<?, ?>> CELESTITE_CRATER = createKey("celestite_crater");

        public static final ResourceKey<ConfiguredFeature<?, ?>> CELESTITE_GEODE = createKey("celestite_geode");

        public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
            return ResourceKey.create(Registries.CONFIGURED_FEATURE, lc(name));
        }
    }

    public static class PlacedFeatures {
        public static final ResourceKey<PlacedFeature> BOULDER = createKey("boulder");
        public static final ResourceKey<PlacedFeature> FORGOTTEN_SPRING = createKey("forgotten_spring");
        public static final ResourceKey<PlacedFeature> CHERT_PATCH = createKey("chert_patch");
        public static final ResourceKey<PlacedFeature> QUARTZOLITE_DISK = createKey("quartzolite_disk");
        public static final ResourceKey<PlacedFeature> CHERT_DISK = createKey("chert_disk");
        public static final ResourceKey<PlacedFeature> FORGOTTEN_VEGETATION = createKey("forgotten_vegetation");
        public static final ResourceKey<PlacedFeature> MOONSTONE_PILLAR = createKey("moonstone_pillar");
        public static final ResourceKey<PlacedFeature> MOONSTONE_PILLAR_NETHER = createKey("moonstone_pillar_nether");
        public static final ResourceKey<PlacedFeature> BLACKSTONE_PILLAR = createKey("blackstone_pillar");
        public static final ResourceKey<PlacedFeature> RHYOLITE_PILLAR = createKey("rhyolite_pillar");

        public static final ResourceKey<PlacedFeature> MOONSTONE_PILLAR_LIQUID = createKey("moonstone_pillar_liquid");
        public static final ResourceKey<PlacedFeature> VINNELIO_VINES = createKey("vinnelio_vines");

        public static final ResourceKey<PlacedFeature> AMETHYST_CRATER = createKey("amethyst_crater");
        public static final ResourceKey<PlacedFeature> GOLD_CRATER = createKey("gold_crater");
        public static final ResourceKey<PlacedFeature> PLATINUM_CRATER = createKey("platinum_crater");
        public static final ResourceKey<PlacedFeature> CELESTITE_CRATER = createKey("celestite_crater");

        public static final ResourceKey<PlacedFeature> CELESTITE_GEODE = createKey("celestite_geode");

        public static final ResourceKey<PlacedFeature> MOONSTONE_PILLAR_RARE = createKey("moonstone_pillar_rare");

        public static ResourceKey<PlacedFeature> createKey(String name) {
            return ResourceKey.create(Registries.PLACED_FEATURE, lc(name));
        }
    }

    public static class LootTables {
        public static final ResourceKey<LootTable> ABANDONED_MINESHAFT = createKey("chests/orevolution_abandoned_mineshaft");
        public static final ResourceKey<LootTable> END_CITY = createKey("chests/orevolution_end_city_treasure");
        public static final ResourceKey<LootTable> BASTION_HOGLIN_STABLE = createKey("chests/orevolution_bastion_hoglin_stable");
        public static final ResourceKey<LootTable> BASTION_TREASURE = createKey("chests/orevolution_bastion_treasure");
        public static final ResourceKey<LootTable> PILLAGER_OUTPOST = createKey("chests/orevolution_pillager_outpost");
        public static final ResourceKey<LootTable> RUINED_PORTAL = createKey("chests/orevolution_ruined_portal");
        public static final ResourceKey<LootTable> SHIPWRECK_SUPPLY = createKey("chests/orevolution_shipwreck_supply");
        public static final ResourceKey<LootTable> SIMPLE_DUNGEON = createKey("chests/orevolution_simple_dungeon");
        public static final ResourceKey<LootTable> VILLAGE_DESERT_HOUSE = createKey("chests/orevolution_village_desert_house");
        public static final ResourceKey<LootTable> VILLAGE_PLAINS_HOUSE = createKey("chests/orevolution_village_plains_house");
        public static final ResourceKey<LootTable> VILLAGE_SAVANNA_HOUSE = createKey("chests/orevolution_village_savanna_house");
        public static final ResourceKey<LootTable> VILLAGE_SNOWY_HOUSE = createKey("chests/orevolution_village_snowy_house");
        public static final ResourceKey<LootTable> VILLAGE_TAIGA_HOUSE = createKey("chests/orevolution_village_taiga_house");
        public static final ResourceKey<LootTable> NETHER_BRIDGE = createKey("chests/orevolution_nether_bridge");

        public static final ResourceKey<LootTable> PIGLIN = createKey("entities/orevolution_piglin");
        public static final ResourceKey<LootTable> BRUTE_PIGLIN = createKey("entities/orevolution_brute_piglin");

        public static final ResourceKey<LootTable> PIGLIN_TUNGSTEN_BARTERING = createKey("gameplay/piglin_tungsten_bartering");

        private static ResourceKey<LootTable> createKey(String id) {
            return ResourceKey.create(Registries.LOOT_TABLE, lc(id));
        }
    }
}
