package net.bexla.orevolution.content.types.block;

import com.mojang.serialization.MapCodec;
import net.bexla.orevolution.init.RegBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;

public class HotLavaSponge extends Block {
    public static final MapCodec<HotLavaSponge> CODEC = simpleCodec(HotLavaSponge::new);

    public HotLavaSponge(BlockBehaviour.Properties p_58222_) {
        super(p_58222_);
    }

    @Override
    public MapCodec<HotLavaSponge> codec() {
        return CODEC;
    }


    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldstateithink, boolean idkwhatthisdoesbutsure) {
        if (this.tryAbsorbWater(level, pos)) {
            level.setBlock(pos, RegBlocks.TUNGSTEN_SPONGE.get().defaultBlockState(), 3);
            level.levelEvent(2009, pos, 0);
            level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, (1.0F + level.getRandom().nextFloat() * 0.2F) * 0.7F);
        }
        else if (!level.dimensionType().ultraWarm() && level.getBiome(pos).is(Tags.Biomes.IS_COLD)) {
            level.setBlock(pos, RegBlocks.TUNGSTEN_SPONGE.get().defaultBlockState(), 3);
            level.levelEvent(2009, pos, 0);
            level.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, (1.0F + level.getRandom().nextFloat() * 0.2F) * 0.7F);
        }
    }

    private boolean tryAbsorbWater(Level level, BlockPos pos) {
        boolean foundwater = false;
        for(Direction direction : Direction.values()) {
            BlockPos blockpos = pos.relative(direction);
            BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.getFluidState().is(FluidTags.WATER)) {
                level.setBlock(blockpos, Blocks.STONE.defaultBlockState(), 3);
                foundwater = true;
            }
        }

        return foundwater;
    }

    public void animateTick(BlockState p_222682_, Level p_222683_, BlockPos p_222684_, RandomSource p_222685_) {
        Direction direction = Direction.getRandom(p_222685_);
        if (direction != Direction.UP) {
            BlockPos blockpos = p_222684_.relative(direction);
            BlockState blockstate = p_222683_.getBlockState(blockpos);
            if (!p_222682_.canOcclude() || !blockstate.isFaceSturdy(p_222683_, blockpos, direction.getOpposite())) {
                double d0 = p_222684_.getX();
                double d1 = p_222684_.getY();
                double d2 = p_222684_.getZ();
                if (direction == Direction.DOWN) {
                    d1 -= 0.05D;
                    d0 += p_222685_.nextDouble();
                    d2 += p_222685_.nextDouble();
                } else {
                    d1 += p_222685_.nextDouble() * 0.8D;
                    if (direction.getAxis() == Direction.Axis.X) {
                        d2 += p_222685_.nextDouble();
                        if (direction == Direction.EAST) {
                            ++d0;
                        } else {
                            d0 += 0.05D;
                        }
                    } else {
                        d0 += p_222685_.nextDouble();
                        if (direction == Direction.SOUTH) {
                            ++d2;
                        } else {
                            d2 += 0.05D;
                        }
                    }
                }

                p_222683_.addParticle(ParticleTypes.DRIPPING_LAVA, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}