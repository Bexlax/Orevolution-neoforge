package net.bexla.orevolution.datagen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.bexla.orevolution.Orevolution.lc;

public class GENBiomeModifier {
    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        addFeature(context, "tin_ore", BiomeTags.IS_OVERWORLD, GenerationStep.Decoration.UNDERGROUND_ORES);
        addFeature(context, "platinum_ore", BiomeTags.IS_OVERWORLD, GenerationStep.Decoration.UNDERGROUND_ORES);

        addFeature(context, "tin_ore_extra", BiomeTags.IS_MOUNTAIN, GenerationStep.Decoration.UNDERGROUND_ORES);
        addFeature(context, "platinum_ore_extra", BiomeTags.IS_BADLANDS, GenerationStep.Decoration.UNDERGROUND_ORES);

        addFeature(context, "tungsten_ore", BiomeTags.IS_NETHER, GenerationStep.Decoration.UNDERGROUND_ORES);
        addFeature(context, "tungsten_ore_extra", BiomeTags.IS_NETHER, GenerationStep.Decoration.UNDERGROUND_ORES);

        addFeature(context, "end_experience_ore", BiomeTags.IS_END, GenerationStep.Decoration.UNDERGROUND_ORES);
        addFeature(context, "meteorite_low", BiomeTags.IS_END, Decoration.RAW_GENERATION);
        addFeature(context, "meteorite_high", BiomeTags.IS_END, Decoration.RAW_GENERATION);
    }

    private static void addFeature(BootstrapContext<BiomeModifier> context, String name, TagKey<Biome> biomes, Decoration step) {
        register(context, "add_feature/" + name, () -> new BiomeModifiers.AddFeaturesBiomeModifier(context.lookup(Registries.BIOME).getOrThrow(biomes), featureSet(context, ResourceKey.create(Registries.PLACED_FEATURE, lc(name))), step));
    }

    private static void register(BootstrapContext<BiomeModifier> context, String name, Supplier<? extends BiomeModifier> modifier) {
        context.register(ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, lc(name)), modifier.get());
    }

    private static HolderSet<PlacedFeature> featureSet(BootstrapContext<?> context, ResourceKey<PlacedFeature>... features) {
        return HolderSet.direct(Stream.of(features).map(placedFeatureKey -> context.lookup(Registries.PLACED_FEATURE).getOrThrow(placedFeatureKey)).collect(Collectors.toList()));
    }

}
