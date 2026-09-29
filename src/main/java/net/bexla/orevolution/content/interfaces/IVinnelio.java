package net.bexla.orevolution.content.interfaces;

import net.bexla.orevolution.init.RegItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.function.ToIntFunction;

public interface IVinnelio {
    VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);
    BooleanProperty FRUIT = BooleanProperty.create("fruit");

    static InteractionResult use(@Nullable Entity entity, BlockState state, Level level, BlockPos pos) {
        if (state.getValue(FRUIT)) {
            Block.popResource(level, pos, new ItemStack(RegItems.ANCIENT_FRUIT.get(), 1));
            float f = Mth.randomBetween(level.random, 0.8F, 1.2F);
            level.playSound(null, pos, SoundEvents.CAVE_VINES_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, f);
            BlockState blockstate = state.setValue(FRUIT, Boolean.valueOf(false));
            level.setBlock(pos, blockstate, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(entity, blockstate));
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }

    static boolean hasGlowBerries(BlockState state) {
        return state.hasProperty(FRUIT) && state.getValue(FRUIT);
    }

    static ToIntFunction<BlockState> it_snew(int what) {
        return p_181216_ -> p_181216_.getValue(FRUIT) ? what : 0;
    }
}
