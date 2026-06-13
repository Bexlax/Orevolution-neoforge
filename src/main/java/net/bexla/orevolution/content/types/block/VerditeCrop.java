package net.bexla.orevolution.content.types.block;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class VerditeCrop extends OreCropBlock {
    public static final int MAX_AGE = 6;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);

    public VerditeCrop(Supplier<? extends Item> item, VoxelShape[] shapeByAge, Properties properties) {
        super(item, shapeByAge, properties);
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_52286_) {
        p_52286_.add(AGE);
    }
}
