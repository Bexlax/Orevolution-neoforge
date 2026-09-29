package net.bexla.orevolution.content.types.block;

import com.mojang.serialization.MapCodec;
import net.bexla.orevolution.init.RegBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class BuddingCelestiteBlock extends CelestiteBlock {
    public static final MapCodec<BuddingCelestiteBlock> CODEC = simpleCodec(BuddingCelestiteBlock::new);
    public static final int GROWTH_CHANCE = 5;
    private static final Direction[] DIRECTIONS = Direction.values();

    @Override
    public MapCodec<BuddingCelestiteBlock> codec() {
        return CODEC;
    }

    public BuddingCelestiteBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.randomTick(state, level, pos, random);

        if (random.nextInt(GROWTH_CHANCE) == 0) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = pos.relative(direction);
            BlockState blockstate = level.getBlockState(blockpos);
            Block block = null;
            if (canClusterGrowAtState(blockstate)) {
                block = RegBlocks.SMALL_CELESTITE_BUD.get();
            } else if (blockstate.is(RegBlocks.SMALL_CELESTITE_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = RegBlocks.MEDIUM_CELESTITE_BUD.get();
            } else if (blockstate.is(RegBlocks.MEDIUM_CELESTITE_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = RegBlocks.LARGE_CELESTITE_BUD.get();
            } else if (blockstate.is(RegBlocks.LARGE_CELESTITE_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = RegBlocks.CELESTITE_CLUSTER.get();
            }

            if (block != null) {
                BlockState blockstate1 = block.defaultBlockState()
                        .setValue(AmethystClusterBlock.FACING, direction)
                        .setValue(AmethystClusterBlock.WATERLOGGED, blockstate.getFluidState().getType() == Fluids.WATER);
                level.setBlockAndUpdate(blockpos, blockstate1);
            }
        }
    }

    public static boolean canClusterGrowAtState(BlockState state) {
        return state.isAir() || state.is(Blocks.WATER) && state.getFluidState().getAmount() == 8;
    }
}
