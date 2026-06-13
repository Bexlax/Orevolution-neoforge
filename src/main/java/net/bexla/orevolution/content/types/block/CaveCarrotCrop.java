package net.bexla.orevolution.content.types.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;

public class CaveCarrotCrop extends BushBlock {
    public static final MapCodec<CaveCarrotCrop> CODEC = simpleCodec(CaveCarrotCrop::new);

    public CaveCarrotCrop(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<CaveCarrotCrop> codec() {
        return CODEC;
    }

    protected boolean mayPlaceOn(BlockState p_52302_, BlockGetter p_52303_, BlockPos p_52304_) {
        return p_52302_.is(Blocks.DEEPSLATE);
    }
}
