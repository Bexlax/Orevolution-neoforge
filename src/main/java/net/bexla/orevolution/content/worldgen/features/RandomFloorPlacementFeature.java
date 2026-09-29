package net.bexla.orevolution.content.worldgen.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.bexla.orevolution.init.RegBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

public class RandomFloorPlacementFeature extends Feature<RandomFloorPlacementFeature.RandomFloorPlacementConfiguration> {

    public RandomFloorPlacementFeature(Codec<RandomFloorPlacementConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<RandomFloorPlacementConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        RandomFloorPlacementConfiguration config = context.config();
        int maxPlacement = context.config().placeCount();
        int placed = 0;
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        for (int i = 0; i < config.tries(); i++) {
            int dx = random.nextInt(config.xzRadius() * 2 + 1) - config.xzRadius();
            int dy = random.nextInt(config.yHeight() * 2 + 1) - config.yHeight();
            int dz = random.nextInt(config.xzRadius() * 2 + 1) - config.xzRadius();

            mutablePos.setWithOffset(origin, dx, dy, dz);

            if (level.isEmptyBlock(mutablePos)) {

                BlockState floorState = level.getBlockState(mutablePos.below());

                if (config.validFloorBlocks().contains(floorState.getBlock().builtInRegistryHolder())) {
                    BlockState stateToPlace = config.stateProvider().getState(random, mutablePos);
                    BlockPos above = mutablePos.above();

                    if (stateToPlace.getBlock() instanceof DoublePlantBlock) {
                        if (level.isEmptyBlock(above)) {
                            level.setBlock(
                                    mutablePos,
                                    stateToPlace.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER),
                                    2
                            );

                            level.setBlock(
                                    above,
                                    stateToPlace.setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER),
                                    2
                            );

                            placed++;
                        }
                    }
                    else if(stateToPlace.is(RegBlocks.UNKNOWN_ROOTS_BLOCK)) {
                        level.setBlock(
                                mutablePos,
                                stateToPlace,
                                2
                        );
                        if (level.isEmptyBlock(above)) {
                            level.setBlock(
                                    above,
                                    BlockStateProvider.simple(RegBlocks.UNKNOWN_ROOTS.get()).getState(random, mutablePos),
                                    2
                            );
                            placed++;
                        }
                    }
                    else {
                        level.setBlock(mutablePos, stateToPlace, 2);
                        placed++;
                    }
                }
            }
        }

        return placed > 0;
    }

    public static record RandomFloorPlacementConfiguration(
            WeightedStateProvider stateProvider,
            HolderSet<Block> validFloorBlocks,
            int tries,
            int xzRadius,
            int yHeight,
            int placeCount
    ) implements FeatureConfiguration {
        public static final Codec<RandomFloorPlacementConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                WeightedStateProvider.CODEC.fieldOf("state_provider").forGetter(RandomFloorPlacementConfiguration::stateProvider),
                RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("valid_floor_blocks").forGetter(RandomFloorPlacementConfiguration::validFloorBlocks),
                Codec.INT.fieldOf("tries").forGetter(RandomFloorPlacementConfiguration::tries),
                Codec.INT.fieldOf("xz_radius").forGetter(RandomFloorPlacementConfiguration::xzRadius),
                Codec.INT.fieldOf("y_height").forGetter(RandomFloorPlacementConfiguration::yHeight),
                Codec.INT.fieldOf("placement_count").forGetter(RandomFloorPlacementConfiguration::placeCount)
        ).apply(instance, RandomFloorPlacementConfiguration::new));
    }
}