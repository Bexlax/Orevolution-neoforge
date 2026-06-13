package net.bexla.orevolution.content.types.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class OreCropBlock extends CropBlock {
    public static final int MAX_AGE = 7;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);
    private final VoxelShape[] shapeByAge;
    private final Supplier<? extends Item> item;

    public OreCropBlock(Supplier<? extends Item> item, VoxelShape[] shapeByAge, Properties properties) {
        super(properties);
        this.shapeByAge = shapeByAge;
        this.item = item;
    }

    @Override
    public VoxelShape getShape(BlockState p_52297_, BlockGetter p_52298_, BlockPos p_52299_, CollisionContext p_52300_) {
        return shapeByAge[this.getAge(p_52297_)];
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    protected ItemLike getBaseSeedId() {
        return item.get();
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected int getBonemealAgeIncrease(Level p_52262_) {
        return Mth.nextInt(p_52262_.random, 1, 3);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_52286_) {
        p_52286_.add(AGE);
    }
}
