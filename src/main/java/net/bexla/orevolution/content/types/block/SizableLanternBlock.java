package net.bexla.orevolution.content.types.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SizableLanternBlock extends LanternBlock {
    private final VoxelShape shape;
    private final VoxelShape shapeHanging;

    public SizableLanternBlock(Properties properties, VoxelShape shape, VoxelShape shapeHanging) {
        super(properties);
        this.shape = shape;
        this.shapeHanging = shapeHanging;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(HANGING) ? shapeHanging : shape;
    }

    public static VoxelShape createLanternShape(double[] body, double[] cap) {
        return Shapes.or(
                Block.box(body[0], body[1], body[2], body[3], body[4], body[5]),
                Block.box(cap [0], cap [1], cap [2], cap [3], cap [4], cap [5]));
    }
}
