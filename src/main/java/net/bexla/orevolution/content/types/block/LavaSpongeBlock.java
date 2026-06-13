package net.bexla.orevolution.content.types.block;

import com.mojang.serialization.MapCodec;
import net.bexla.orevolution.init.RegBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class LavaSpongeBlock extends Block {
    public static final MapCodec<LavaSpongeBlock> CODEC = simpleCodec(LavaSpongeBlock::new);
    public static final int MAX_DEPTH = 6;
    public static final int MAX_COUNT = 64;
    private static final Direction[] ALL_DIRECTIONS = Direction.values();

    @Override
    public MapCodec<LavaSpongeBlock> codec() {
        return CODEC;
    }

    public LavaSpongeBlock(BlockBehaviour.Properties p_56796_) {
        super(p_56796_);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState prev, boolean idk) {
        if (!prev.is(state.getBlock())) {
            this.tryAbsorbLava(level, pos);
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos pos2morepositionedthanever, boolean idkeither) {
        this.tryAbsorbLava(level, pos);
        super.neighborChanged(state, level, pos, block, pos2morepositionedthanever, idkeither);
    }

    protected void tryAbsorbLava(Level level, BlockPos pos) {
        if (this.removeLava(level, pos)) {
            level.setBlock(pos, RegBlocks.HOT_TUNGSTEN_SPONGE.get().defaultBlockState(), 2);
            level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }

    private boolean removeLava(Level level, BlockPos pos) {
        return BlockPos.breadthFirstTraversal(
                pos,
                MAX_DEPTH,
                MAX_COUNT,
                (current, consumer) -> {
                    for (Direction direction : ALL_DIRECTIONS) {
                        consumer.accept(current.relative(direction));
                    }
                },
                currentPos -> {
                    if (currentPos.equals(pos)) {
                        return true;
                    }

                    FluidState fluidState = level.getFluidState(currentPos);
                    if (!fluidState.is(FluidTags.LAVA)) return false;

                    BlockState blockState = level.getBlockState(currentPos);

                    if (blockState.getBlock() instanceof BucketPickup bucketPickup) return !bucketPickup.pickupBlock(null, level, currentPos, blockState).isEmpty();

                    if (blockState.getBlock() instanceof LiquidBlock) {
                        level.setBlock(currentPos, Blocks.AIR.defaultBlockState(), 3);
                        return true;
                    }

                    return false;
                }
        ) > 1;
    }
}