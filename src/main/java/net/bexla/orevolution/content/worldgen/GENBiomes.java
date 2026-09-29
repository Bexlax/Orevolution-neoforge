package net.bexla.orevolution.content.worldgen;

import net.bexla.orevolution.content.data.utility.Color;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static net.bexla.orevolution.content.data.utility.OrevolutionKeys.Biomes.FORGOTTEN_CAVE;
import static net.bexla.orevolution.content.data.utility.OrevolutionKeys.Biomes.GEODE_GARDENS;

public class GENBiomes {
    public static void bootstrap(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> holdergetter = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> holdergetter1 = context.lookup(Registries.CONFIGURED_CARVER);
        context.register(FORGOTTEN_CAVE, createForgottenCave(holdergetter, holdergetter1));
        context.register(GEODE_GARDENS, createGeodeGardens(holdergetter, holdergetter1));
    }

    public static Biome createForgottenCave(HolderGetter<PlacedFeature> holderGetter, HolderGetter<ConfiguredWorldCarver<?>> holderGetter1) {
        MobSpawnSettings.Builder mobBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.caveSpawns(mobBuilder);
        mobBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 100, 4, 4));
        mobBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.HUSK, 110, 7, 7));
        mobBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE_VILLAGER, 20, 1, 2));
        mobBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 110, 3, 4));
        mobBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 110, 4, 4));
        mobBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 5, 1, 4));
        mobBuilder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 15, 1, 3));
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(holderGetter, holderGetter1);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIORITE_UPPER);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIORITE_LOWER);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_ANDESITE_UPPER);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_ANDESITE_LOWER);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_DIRT);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        Music music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_MEADOW);
        int fogColor = OrevolutionBiomeFogs.FORGOTTEN_CAVE.getColor().getColorInt();

        return new BiomeBuilder(biomeBuilder)
                .mobBuilder(mobBuilder)
                .music(music)
                .waterColor(Color.ofRGB(227, 168, 120).getColorInt())
                .waterFogColor(Color.ofRGB(163, 80, 47).getColorInt())
                .grassColor(Color.ofRGB(140, 107, 66).getColorInt())
                .fogColor(fogColor)
                .build();
    }

    public static Biome createGeodeGardens(HolderGetter<PlacedFeature> holderGetter, HolderGetter<ConfiguredWorldCarver<?>> holderGetter1) {
        MobSpawnSettings.Builder mobBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.caveSpawns(mobBuilder);
        BiomeDefaultFeatures.commonSpawns(mobBuilder, 148);
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(holderGetter, holderGetter1);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        Music music = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DRIPSTONE_CAVES);
        int fogColor = Color.ofRGB(37, 176, 157).getColorInt();

        return new BiomeBuilder(biomeBuilder)
                .mobBuilder(mobBuilder)
                .music(music)
                .waterColor(Color.ofRGB(147, 219, 205).getColorInt())
                .waterFogColor(Color.ofRGB(65, 145, 176).getColorInt())
                .grassColor(Color.ofRGB(108, 93, 128).getColorInt())
                .fogColor(fogColor)
                .build();
    }

    private static class BiomeBuilder {
        private int waterColor = 4159204;
        private int waterFogColor = 329011;
        private int fogColor = 12638463;
        private int grassColor = 5478752;
        private float temperature = 0.5F;
        private float downfall = 0.5F;
        private int skyColor = calculateSkyColor(temperature);
        private boolean precipitation = true;
        private MobSpawnSettings.Builder mobBuilder = new MobSpawnSettings.Builder();
        private final BiomeGenerationSettings.Builder biomeBuilder;
        private Music music;

        public BiomeBuilder(BiomeGenerationSettings.Builder biomeBuilder) {
            this.biomeBuilder = biomeBuilder;
        }

        public BiomeBuilder waterColor(int waterColor) {
            this.waterColor = waterColor;
            return this;
        }

        public BiomeBuilder waterFogColor(int waterFogColor) {
            this.waterFogColor = waterFogColor;
            return this;
        }

        public BiomeBuilder fogColor(int fogColor) {
            this.fogColor = fogColor;
            return this;
        }

        public BiomeBuilder grassColor(int grassColor) {
            this.grassColor = grassColor;
            return this;
        }

        public BiomeBuilder music(Music music) {
            this.music = music;
            return this;
        }

        public BiomeBuilder temperature(float temperature) {
            this.temperature = temperature;
            return this;
        }

        public BiomeBuilder downfall(float downfall) {
            this.downfall = downfall;
            return this;
        }

        public BiomeBuilder skyColor(int skyColor) {
            this.skyColor = skyColor;
            return this;
        }

        public BiomeBuilder precipitation(boolean precipitation) {
            this.precipitation = precipitation;
            return this;
        }

        public BiomeBuilder mobBuilder(MobSpawnSettings.Builder mobBuilder) {
            this.mobBuilder = mobBuilder;
            return this;
        }

        public Biome build() {
            var biomeSpecialEffects = new BiomeSpecialEffects.Builder()
                    .waterColor(waterColor)
                    .waterFogColor(waterFogColor)
                    .fogColor(fogColor)
                    .grassColorOverride(grassColor)
                    .skyColor(skyColor)
                    .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                    .backgroundMusic(music)
                    .build();

            return (new Biome.BiomeBuilder())
                    .hasPrecipitation(precipitation)
                    .temperature(temperature)
                    .downfall(downfall)
                    .specialEffects(biomeSpecialEffects)
                    .mobSpawnSettings(mobBuilder.build())
                    .generationSettings(biomeBuilder.build())
                    .build();
        }

        private static int calculateSkyColor(float temperature) {
            float $$1 = temperature / 3.0F;
            $$1 = Mth.clamp($$1, -1.0F, 1.0F);
            return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
        }

    }

}
