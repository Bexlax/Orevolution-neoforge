package net.bexla.orevolution.content.worldgen.features;

import com.mojang.serialization.Codec;
import net.bexla.orevolution.init.RegBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.DripstoneUtils;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.function.Predicate;

// Credits to Darker Depths
public class BoulderFeature extends Feature<NoneFeatureConfiguration> {

    public BoulderFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        BlockPos origin = context.origin();
        WorldGenLevel world = context.level();
        RandomSource random = context.random();

        if (!world.isEmptyBlock(origin) || !world.getBlockState(origin.below()).is(RegBlocks.RHYOLITE)) {
            return false;
        }

        boolean flag = false;
        int radius = 7;
        int height = Mth.nextInt(random, 8, 10);

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                for (int y = 0; y <= height; y++) {
                    BlockPos pos = origin.offset(x, -3 + y, z);

                    if (y > 1) {
                        flag = generateBoulder(world, flag, radius, x, z, y, pos);
                    }
                }
            }
        }

        if (flag && random.nextFloat() > 0.238F) {
            generateEmeraldOre(world, random, origin, height);
        }

        return flag;
    }

    private boolean generateBoulder(WorldGenLevel world, boolean flag, int radius, int x, int z, int y, BlockPos pos) {
        if (y * (x * x) + ((y * y) / 4) + y * (z * z) <= radius * radius) {
            if (world.isStateAtPosition(pos.below(), DripstoneUtils::isEmptyOrWater)) {
                if (pos.below().getY() < world.getMinBuildHeight()) {
                    return false;
                }
                return generateBoulder(world, flag, radius, x / 2, z / 2, y, pos.below());
            }

            replaceLavaColumn(world, pos.below());

            Predicate<BlockState> canBeReplaced = state ->
                    DripstoneUtils.isEmptyOrWaterOrLava(state)
                            || state.is(BlockTags.BASE_STONE_OVERWORLD)
                            || state.is(Blocks.GLOW_LICHEN);

            if (canBeReplaced.test(world.getBlockState(pos))) {
                Block block = y % 4 == 0 ? Blocks.TUFF : RegBlocks.RHYOLITE.get();
                world.setBlock(pos, block.defaultBlockState(), 2);
                flag = true;
            }
        }

        return flag;
    }

    private void generateEmeraldOre(WorldGenLevel world, RandomSource random, BlockPos origin, int height) {
        BlockPos center = origin.offset(
                Mth.nextInt(random, -1, 1),
                height / 2 - 2 + Mth.nextInt(random, -1, 1),
                Mth.nextInt(random, -1, 1)
        );

        int oreRadius = 1; // 3x3x3 sphere

        for (int x = -oreRadius; x <= oreRadius; x++) {
            for (int y = -oreRadius; y <= oreRadius; y++) {
                for (int z = -oreRadius; z <= oreRadius; z++) {
                    if (x * x + y * y + z * z > oreRadius * oreRadius) {
                        continue;
                    }

                    BlockPos pos = center.offset(x, y, z);

                    if(random.nextFloat() < 0.15F) {
                        continue;
                    }

                    if (world.getBlockState(pos).is(RegBlocks.RHYOLITE.get())) {
                        Block emerald = random.nextFloat() < 0.35F? RegBlocks.RHYOLITE_EMERALD_ORE_CLUMP.get() : RegBlocks.RHYOLITE_EMERALD_ORE.get();
                        world.setBlock(pos, emerald.defaultBlockState(), 2);
                    }
                }
            }
        }
    }

    private void replaceLavaColumn(WorldGenLevel world, BlockPos pos) {
        if (pos.getY() < world.getMinBuildHeight()) {
            return;
        }

        if (world.getBlockState(pos).is(Blocks.LAVA)) {
            world.setBlock(pos, Blocks.DEEPSLATE.defaultBlockState(), 2);
            replaceLavaColumn(world, pos.below());
        }
    }
}