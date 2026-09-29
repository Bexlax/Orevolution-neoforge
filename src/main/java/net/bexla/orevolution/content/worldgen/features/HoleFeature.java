package net.bexla.orevolution.content.worldgen.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class HoleFeature extends Feature<HoleFeature.HoleFeatureConfiguration> {
    public HoleFeature(Codec<HoleFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<HoleFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        HoleFeatureConfiguration config = context.config();

        BlockPos.MutableBlockPos floor = context.origin().mutable();

        // Find the cave floor.
        while (floor.getY() > level.getMinBuildHeight() && level.isEmptyBlock(floor)) {
            floor.move(Direction.DOWN);
        }

        // Didn't find a floor.
        if (floor.getY() <= level.getMinBuildHeight()) {
            return false;
        }

        // Don't carve into liquids.
        if (!level.getFluidState(floor).isEmpty()) {
            return false;
        }

        int radius = config.radius().sample(random);
        int depth = config.depth().sample(random);

        boolean carved = false;
        BlockPos center = floor.immutable();

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {

                double distance = (x * x + z * z) / (double) (radius * radius);
                distance += (random.nextFloat() - 0.5F) * config.edgeNoise();

                if (distance > 1.0D) {
                    continue;
                }

                double falloff = 1.0D - distance;
                falloff *= falloff;

                int localDepth = (int) (depth * falloff);

                for (int y = 0; y <= localDepth; y++) {
                    BlockPos pos = center.offset(x, -y, z);

                    BlockState state = level.getBlockState(pos);

                    if (state.isAir())
                        continue;

                    if (state.getDestroySpeed(level, pos) < 0)
                        continue;

                    if (!level.getFluidState(pos).isEmpty())
                        continue;

                    level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                    carved = true;
                }
            }
        }

        return carved;
    }

    public record HoleFeatureConfiguration(
            IntProvider radius,
            IntProvider depth,
            float edgeNoise
    ) implements FeatureConfiguration {

        public static final Codec<HoleFeatureConfiguration> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        IntProvider.codec(1, 64)
                                .fieldOf("radius")
                                .forGetter(HoleFeatureConfiguration::radius),

                        IntProvider.codec(1, 64)
                                .fieldOf("depth")
                                .forGetter(HoleFeatureConfiguration::depth),

                        Codec.FLOAT.optionalFieldOf("edge_noise", 0.15F)
                                .forGetter(HoleFeatureConfiguration::edgeNoise)
                ).apply(instance, HoleFeatureConfiguration::new)
        );
    }
}