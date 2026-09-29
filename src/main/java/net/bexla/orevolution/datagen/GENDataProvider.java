package net.bexla.orevolution.datagen;

import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.TrimArmorMaterials;
import net.bexla.orevolution.content.data.TrimArmorPatterns;
import net.bexla.orevolution.content.data.utility.OrevolutionKeys;
import net.bexla.orevolution.content.worldgen.GENBiomeModifier;
import net.bexla.orevolution.content.worldgen.GENBiomes;
import net.bexla.orevolution.content.worldgen.OrevolutionCarvers;
import net.bexla.orevolution.init.RegConditionSerializer;
import net.bexla.orevolution.init.RegStructureRepaletters;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static net.bexla.orevolution.Orevolution.lc;
import static net.bexla.orevolution.init.RegFeatures.Configured;
import static net.bexla.orevolution.init.RegFeatures.Placed;

public class GENDataProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.BIOME, GENBiomes::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, GENBiomeModifier::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, Configured::bootstrap)
            .add(Registries.PLACED_FEATURE, Placed::bootstrap)
            .add(Registries.TRIM_MATERIAL, TrimArmorMaterials::bootstrap)
            .add(Registries.TRIM_PATTERN, TrimArmorPatterns::bootstrap)
            .add(Registries.CONFIGURED_CARVER, OrevolutionCarvers::bootstrap)
            .add(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, RegStructureRepaletters::bootstrap);

    public static void applyConditions(BiConsumer<ResourceKey<?>, ICondition> builder) {
        builder.accept(RegStructureRepaletters.BASTION_ADDITIONS, RegConditionSerializer.Conditions.GENERATE_TUNGSTEN);

        builder.accept(Configured.TIN_ORE, RegConditionSerializer.Conditions.GENERATE_TIN);
        builder.accept(Configured.TIN_ORE_SMALL, RegConditionSerializer.Conditions.GENERATE_TIN);
        builder.accept(Configured.TIN_ORE_EXTRA, RegConditionSerializer.Conditions.GENERATE_TIN);

        builder.accept(Configured.CASSITERITE_ORE, RegConditionSerializer.Conditions.GENERATE_TIN);
        builder.accept(Configured.CASSITERITE_ORE_SMALL, RegConditionSerializer.Conditions.GENERATE_TIN);
        builder.accept(Configured.CASSITERITE_ORE_EXTRA, RegConditionSerializer.Conditions.GENERATE_TIN);

        builder.accept(Configured.PLATINUM_ORE_SMALL, RegConditionSerializer.Conditions.GENERATE_PLATINUM);
        builder.accept(Configured.PLATINUM_ORE_MEDIUM, RegConditionSerializer.Conditions.GENERATE_PLATINUM);
        builder.accept(Configured.PLATINUM_ORE_LARGE, RegConditionSerializer.Conditions.GENERATE_PLATINUM);
        builder.accept(Configured.PLATINUM_ORE_BURIED, RegConditionSerializer.Conditions.GENERATE_PLATINUM);
        builder.accept(Configured.PLATINUM_ORE_EXTRA, RegConditionSerializer.Conditions.GENERATE_PLATINUM);

        builder.accept(Configured.TUNGSTEN_ORE_SMALL, RegConditionSerializer.Conditions.GENERATE_TUNGSTEN);
        builder.accept(Configured.TUNGSTEN_ORE, RegConditionSerializer.Conditions.GENERATE_TUNGSTEN);
        builder.accept(Configured.TUNGSTEN_ORE_EXTRA, RegConditionSerializer.Conditions.GENERATE_TUNGSTEN);

        builder.accept(Configured.PYRITE_ORE, RegConditionSerializer.Conditions.GENERATE_PYRITE);
        builder.accept(Configured.PYRITE_ORE_EXTRA, RegConditionSerializer.Conditions.GENERATE_PYRITE);

        builder.accept(Configured.NETHER_EXPERIENCE_ORE, RegConditionSerializer.Conditions.GENERATE_XP);
        builder.accept(Configured.END_EXPERIENCE_ORE, RegConditionSerializer.Conditions.GENERATE_XP);

        builder.accept(Placed.TIN_ORE_MIDDLE, RegConditionSerializer.Conditions.GENERATE_TIN);
        builder.accept(Placed.TIN_ORE_UPPER, RegConditionSerializer.Conditions.GENERATE_TIN);
        builder.accept(Placed.TIN_ORE_SMALL, RegConditionSerializer.Conditions.GENERATE_TIN);
        builder.accept(Placed.TIN_ORE_EXTRA, RegConditionSerializer.Conditions.GENERATE_TIN);

        builder.accept(Placed.CASSITERITE_ORE_OVERWORLD, RegConditionSerializer.Conditions.GENERATE_TIN);
        builder.accept(Placed.CASSITERITE_ORE_NETHER, RegConditionSerializer.Conditions.GENERATE_TIN);
        builder.accept(Placed.CASSITERITE_ORE_EXTRA, RegConditionSerializer.Conditions.GENERATE_TIN);

        builder.accept(Placed.PLATINUM_ORE_SMALL, RegConditionSerializer.Conditions.GENERATE_PLATINUM);
        builder.accept(Placed.PLATINUM_ORE_MEDIUM, RegConditionSerializer.Conditions.GENERATE_PLATINUM);
        builder.accept(Placed.PLATINUM_ORE_LARGE, RegConditionSerializer.Conditions.GENERATE_PLATINUM);
        builder.accept(Placed.PLATINUM_ORE_BURIED, RegConditionSerializer.Conditions.GENERATE_PLATINUM);
        builder.accept(Placed.PLATINUM_ORE_EXTRA, RegConditionSerializer.Conditions.GENERATE_PLATINUM);

        builder.accept(Placed.TUNGSTEN_ORE_LOWER, RegConditionSerializer.Conditions.GENERATE_TUNGSTEN);
        builder.accept(Placed.TUNGSTEN_ORE_HIGHER, RegConditionSerializer.Conditions.GENERATE_TUNGSTEN);
        builder.accept(Placed.TUNGSTEN_ORE_EXTRA, RegConditionSerializer.Conditions.GENERATE_TUNGSTEN);

        builder.accept(Placed.PYRITE_ORE, RegConditionSerializer.Conditions.GENERATE_PYRITE);
        builder.accept(Placed.PYRITE_ORE_EXTRA, RegConditionSerializer.Conditions.GENERATE_PYRITE);

        builder.accept(Placed.NETHER_EXPERIENCE_ORE, RegConditionSerializer.Conditions.GENERATE_XP);
        builder.accept(Placed.END_EXPERIENCE_ORE, RegConditionSerializer.Conditions.GENERATE_XP);

        builder.accept(OrevolutionKeys.ConfiguredFeatures.CELESTITE_GEODE, RegConditionSerializer.Conditions.GENERATE_CELESTITE_GEODE);
        builder.accept(OrevolutionKeys.PlacedFeatures.CELESTITE_GEODE, RegConditionSerializer.Conditions.GENERATE_CELESTITE_GEODE);
        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/celestite_geode")), RegConditionSerializer.Conditions.GENERATE_CELESTITE_GEODE);

        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/tin_ore_upper")), RegConditionSerializer.Conditions.GENERATE_TIN);
        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/tin_ore_middle")), RegConditionSerializer.Conditions.GENERATE_TIN);
        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/tin_ore_small")), RegConditionSerializer.Conditions.GENERATE_TIN);
        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/tin_ore_extra")), RegConditionSerializer.Conditions.GENERATE_TIN);

        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/cassiterite_ore_overworld")), RegConditionSerializer.Conditions.GENERATE_TIN);
        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/cassiterite_ore_nether")), RegConditionSerializer.Conditions.GENERATE_TIN);
        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/cassiterite_ore_extra")), RegConditionSerializer.Conditions.GENERATE_TIN);

        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/platinum_ore_large")), RegConditionSerializer.Conditions.GENERATE_PLATINUM);
        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/platinum_ore_medium")), RegConditionSerializer.Conditions.GENERATE_PLATINUM);
        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/platinum_ore_small")), RegConditionSerializer.Conditions.GENERATE_PLATINUM);
        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/platinum_ore_buried")), RegConditionSerializer.Conditions.GENERATE_PLATINUM);
        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/platinum_ore_extra")), RegConditionSerializer.Conditions.GENERATE_PLATINUM);

        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/tungsten_ore_small")), RegConditionSerializer.Conditions.GENERATE_TUNGSTEN);
        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/tungsten_ore_lower")), RegConditionSerializer.Conditions.GENERATE_TUNGSTEN);
        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/tungsten_ore_higher")), RegConditionSerializer.Conditions.GENERATE_TUNGSTEN);
        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/tungsten_ore_extra")), RegConditionSerializer.Conditions.GENERATE_TUNGSTEN);

        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/pyrite_ore")), RegConditionSerializer.Conditions.GENERATE_PYRITE);
        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/pyrite_ore_extra")), RegConditionSerializer.Conditions.GENERATE_PYRITE);
        
        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/nether_experience_ore")), RegConditionSerializer.Conditions.GENERATE_XP);
        builder.accept(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc("add_feature/end_experience_ore")), RegConditionSerializer.Conditions.GENERATE_XP);
    }

    public GENDataProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, BUILDER, GENDataProvider::applyConditions, Set.of(Orevolution.MODID, "minecraft"));
    }

}
