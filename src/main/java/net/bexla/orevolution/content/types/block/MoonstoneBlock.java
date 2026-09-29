package net.bexla.orevolution.content.types.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class MoonstoneBlock extends RotatedPillarBlock {
    public MoonstoneBlock(Properties properties) {
        super(properties);
    }

    @Override
    public float getEnchantPowerBonus(BlockState state, LevelReader level, BlockPos pos) {
        return 3;
    }
}
