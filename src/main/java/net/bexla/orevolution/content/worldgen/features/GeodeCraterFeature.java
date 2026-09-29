package net.bexla.orevolution.content.worldgen.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.material.FluidState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeodeCraterFeature extends Feature<GeodeCraterFeature.CraterConfiguration> {

    public GeodeCraterFeature(Codec<CraterConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<CraterConfiguration> context) {

        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        CraterConfiguration config = context.config();

        int radius = config.radius().sample(random);
        int depth = config.depth().sample(random);

        int middleThickness = Mth.nextInt(random, 2, 4);

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        boolean carved = false;

        List<BlockPos> clusterPositions = new ArrayList<>();

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {

                double nx = x / (double) radius;
                double nz = z / (double) radius;
                double d = nx * nx + nz * nz;

                if (d > 1.0)
                    continue;

                int lowerDepth = (int) (depth * Math.sqrt(1.0 - d));
                int upperHeight = (int) (depth * Math.sqrt(1.0 - d));

                for (int y = 1; y <= upperHeight; y++) {

                    pos.set(
                            origin.getX() + x,
                            origin.getY() + y,
                            origin.getZ() + z
                    );

                    BlockState state = level.getBlockState(pos);

                    if (config.replaceable().test(state, random))
                        continue;

                    level.setBlock(pos, Blocks.CAVE_AIR.defaultBlockState(), Block.UPDATE_ALL);
                    carved = true;
                }

                for (int y = 0; y <= lowerDepth + middleThickness + 1; y++) {

                    pos.set(
                            origin.getX() + x,
                            origin.getY() - y,
                            origin.getZ() + z
                    );

                    BlockState state = level.getBlockState(pos);

                    if (config.replaceable().test(state, random))
                        continue;

                    if (y < lowerDepth) {
                        level.setBlock(pos, Blocks.CAVE_AIR.defaultBlockState(), Block.UPDATE_ALL);
                    } else if (y == lowerDepth) {
                        BlockStateProvider provider;

                        if (random.nextFloat() < config.innerChance().sample(random) && !config.innerLayer().isEmpty()) {
                            provider = config.innerLayer().get(random.nextInt(config.innerLayer().size()));
                        } else {
                            provider = config.outerLayer();
                        }

                        level.setBlock(pos, provider.getState(random, pos), Block.UPDATE_ALL);

                        clusterPositions.add(pos.immutable());
                    } else if (y <= lowerDepth + middleThickness) {
                        level.setBlock(pos, config.middleLayer().getState(random, pos), Block.UPDATE_ALL);
                    } else {
                        level.setBlock(pos, config.outerLayer().getState(random, pos), Block.UPDATE_ALL);
                    }

                    carved = true;
                }
            }
        }

        if (!config.clusters().isEmpty()) {

            Direction[] directions = Direction.values();

            for (BlockPos shellPos : clusterPositions) {

                if (random.nextFloat() >= config.clusterChance().sample(random)) {
                    continue;
                }

                Util.shuffle(Arrays.asList(directions), random);

                for (Direction direction : directions) {

                    BlockPos placePos = shellPos.relative(direction);

                    if (!level.getBlockState(placePos).isAir()) {
                        continue;
                    }

                    BlockStateProvider provider =
                            config.clusters().get(random.nextInt(config.clusters().size()));

                    BlockState state = provider.getState(random, placePos);

                    if (state.hasProperty(BlockStateProperties.FACING)) {
                        state = state.setValue(BlockStateProperties.FACING, direction);
                    }

                    if (state.canSurvive(level, placePos)) {
                        level.setBlock(placePos, state, Block.UPDATE_ALL);
                        break;
                    }
                }
            }
        }

        scheduleNeighborFluids(level, origin, radius + depth);

        return carved;
    }

    private static void scheduleNeighborFluids(WorldGenLevel level, BlockPos center, int radius) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int x = -radius - 2; x <= radius + 2; x++) {
            for (int y = -radius - 2; y <= radius + 2; y++) {
                for (int z = -radius - 2; z <= radius + 2; z++) {

                    pos.set(center.getX() + x, center.getY() + y, center.getZ() + z);

                    FluidState fluid = level.getFluidState(pos);
                    if (!fluid.isEmpty()) {
                        level.scheduleTick(pos, fluid.getType(), 1);
                    }
                }
            }
        }
    }

    public record CraterConfiguration(
            IntProvider radius,
            IntProvider depth,
            FloatProvider irregularity,
            FloatProvider innerChance,
            FloatProvider clusterChance,
            RuleTest replaceable,
            BlockStateProvider outerLayer,
            BlockStateProvider middleLayer,
            List<BlockStateProvider> innerLayer,
            List<BlockStateProvider> clusters
    ) implements FeatureConfiguration {
        public static final Codec<CraterConfiguration> CODEC =
                RecordCodecBuilder.create(instance ->
                        instance.group(
                                IntProvider.codec(1, 32).fieldOf("radius").forGetter(CraterConfiguration::radius),
                                IntProvider.codec(1, 32).fieldOf("depth").forGetter(CraterConfiguration::depth),
                                FloatProvider.codec(0F, 2F).fieldOf("irregularity").forGetter(CraterConfiguration::irregularity),
                                FloatProvider.codec(0F, 1F).fieldOf("innerChance").forGetter(CraterConfiguration::innerChance),
                                FloatProvider.codec(0F, 1F).fieldOf("cluster_chance").forGetter(CraterConfiguration::clusterChance),
                                RuleTest.CODEC.fieldOf("replaceable").forGetter(CraterConfiguration::replaceable),
                                BlockStateProvider.CODEC.fieldOf("outer_layer").forGetter(CraterConfiguration::outerLayer),
                                BlockStateProvider.CODEC.fieldOf("middle_layer").forGetter(CraterConfiguration::middleLayer),
                                BlockStateProvider.CODEC.listOf().fieldOf("inner_layer").forGetter(CraterConfiguration::innerLayer),
                                BlockStateProvider.CODEC.listOf().optionalFieldOf("clusters", List.of()).forGetter(CraterConfiguration::clusters)
                        ).apply(instance, CraterConfiguration::new)
                );
    }
}