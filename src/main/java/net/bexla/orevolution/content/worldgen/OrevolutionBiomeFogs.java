package net.bexla.orevolution.content.worldgen;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.data.utility.Color;
import net.bexla.orevolution.content.data.utility.OrevolutionKeys;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

// Credits to Darker Depths for the original BiomeFog(s) class
// https://github.com/futurenp/DarkerDepths/blob/1.21.1/src/main/java/com/naterbobber/darkerdepths/client/fog/DDBiomeFogs.java
// https://github.com/futurenp/DarkerDepths/blob/1.21.1/src/main/java/com/naterbobber/darkerdepths/client/fog/BiomeFog.java
public class OrevolutionBiomeFogs {
    public static final List<BiomeFog> BIOME_FOGS = new ArrayList<>();

    public static final BiomeFog FORGOTTEN_CAVE = create(
            OrevolutionKeys.Biomes.FORGOTTEN_CAVE,
            0,
            OrevolutionConfig.CLIENT.forgottenCaveFogMin,
            OrevolutionConfig.CLIENT.forgottenCaveFogMax,
            Color.ofRGB(0.22F, 0.13F, 0.10F)
    );

    private static BiomeFog create(ResourceKey<Biome> biome, int cycleLength, Supplier<Integer> minDist, Supplier<Integer> maxDist, Color... colors) {
        BiomeFog fog = new BiomeFog(biome, cycleLength, minDist, maxDist, colors);
        BIOME_FOGS.add(fog);
        return fog;
    }

    public static class BiomeFog {
        private ResourceKey<Biome> biomeKey;
        private final List<Color> colors;
        private final int cycleLength;

        private float weight;

        private Supplier<Integer> minDist;
        private Supplier<Integer> maxDist;

        public BiomeFog(ResourceKey<Biome> biome, int cycleLength, Supplier<Integer> minDist, Supplier<Integer> maxDist, Color... colors) {
            this.biomeKey = biome;
            this.cycleLength = cycleLength;
            this.minDist = minDist;
            this.maxDist = maxDist;
            this.colors = List.of(colors);
        }

        public Color getAnimatedColor(long gameTime, float partialTick) {
            if (colors.size() == 1) {
                return colors.getFirst();
            }

            float t = ((gameTime + partialTick) % cycleLength) / (float) cycleLength;

            float scaled = t * colors.size();

            int from = (int) Math.floor(scaled);
            int to = (from + 1) % colors.size();

            float local = scaled - from;

            Color a = colors.get(from);
            Color b = colors.get(to);

            return Color.ofRGB(
                    lerp(a.getRedFloat(), b.getRedFloat(), local),
                    lerp(a.getGreenFloat(), b.getGreenFloat(), local),
                    lerp(a.getBlueFloat(), b.getBlueFloat(), local)
            );
        }

        private static float lerp(float a, float b, float t) {
            return a + (b - a) * t;
        }

        public ResourceKey<Biome> getBiomeKey() {
            return biomeKey;
        }

        public Color getColor() {
            return colors.getFirst();
        }

        public float getWeight() {
            return weight;
        }

        public void setWeight(float weight) {
            this.weight = weight;
        }

        public float getWeightedMin() {
            return weight * minDist.get();
        }

        public float getWeightedMax() {
            return weight * maxDist.get();
        }

        public int getMinDist() {
            return minDist.get();
        }

        public int getMaxDist() {
            return maxDist.get();
        }
    }
}