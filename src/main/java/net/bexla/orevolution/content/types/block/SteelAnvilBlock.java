package net.bexla.orevolution.content.types.block;

import com.mojang.serialization.MapCodec;
import net.bexla.orevolution.content.types.menu.SteelAnvilMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class SteelAnvilBlock extends FallingBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    private static final VoxelShape BASE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 4.0D, 14.0D);
    private static final VoxelShape X_LEG1 = Block.box(4.0D, 4.0D, 6.0D, 12.0D, 9.0D, 10.0D);
    private static final VoxelShape X_TOP = Block.box(0.0D, 8.0D, 3.0D, 16.0D, 18.0D, 13.0D);
    private static final VoxelShape Z_LEG1 = Block.box(6.0D, 4.0D, 4.0D, 10.0D, 9.0D, 12.0D);
    private static final VoxelShape Z_TOP = Block.box(3.0D, 8.0D, 0.0D, 13.0D, 18.0D, 16.0D);

    private static final VoxelShape X_AXIS_AABB = Shapes.or(BASE, X_LEG1, X_TOP);
    private static final VoxelShape Z_AXIS_AABB = Shapes.or(BASE, Z_LEG1, Z_TOP);

    private static final Component CONTAINER_TITLE = Component.translatable("container.repair");

    public static final MapCodec<SteelAnvilBlock> CODEC = simpleCodec(SteelAnvilBlock::new);

    public SteelAnvilBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<SteelAnvilBlock> codec() {
        return CODEC;
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getClockWise());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.sidedSuccess(true);
        }

        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(
                    state.getMenuProvider(level, pos),
                    buf -> buf.writeBlockPos(pos)
            );
        }

        player.awardStat(Stats.INTERACT_WITH_ANVIL);
        return InteractionResult.CONSUME;
    }

    @Override
    @Nullable
    protected MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new SimpleMenuProvider(
                (containerId, inventory, player) ->
                        new SteelAnvilMenu(
                                containerId,
                                inventory,
                                ContainerLevelAccess.create(level, pos)
                        ),
                CONTAINER_TITLE
        );
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        return direction.getAxis() == Direction.Axis.X ? X_AXIS_AABB : Z_AXIS_AABB;
    }

    @Override
    protected void falling(FallingBlockEntity fallingBlockEntity) {
        fallingBlockEntity.setHurtsEntities(4.0F, 80);
    }

    @Override
    public void onLand(Level level, BlockPos pos, BlockState fallingState, BlockState hitState, FallingBlockEntity fallingBlockEntity) {
        if (!fallingBlockEntity.isSilent()) {
            level.levelEvent(1031, pos, 0);
        }
    }

    @Override
    public DamageSource getFallDamageSource(Entity entity) {
        return entity.damageSources().anvil(entity);
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected boolean isPathfindable(
            BlockState state,
            PathComputationType pathComputationType
    ) {
        return false;
    }

    @Override
    public int getDustColor(BlockState state, BlockGetter level, BlockPos pos) {
        return state.getMapColor(level, pos).col;
    }
}