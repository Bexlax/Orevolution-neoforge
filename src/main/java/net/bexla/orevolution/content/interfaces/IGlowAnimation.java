package net.bexla.orevolution.content.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.world.level.block.Block.UPDATE_ALL;
import static net.minecraft.world.level.block.Block.UPDATE_CLIENTS;

public interface IGlowAnimation {
     IntegerProperty GLOW = IntegerProperty.create("glow", 0, 9);
     EnumProperty<GlowMode> MODE = EnumProperty.create("mode", GlowMode.class);
     EnumProperty<GlowPhase> PHASE = EnumProperty.create("phase", GlowPhase.class);

     static int glow(BlockState state) {
         return state.hasProperty(GLOW)
                 ? state.getValue(GLOW)
                 : 0;
     }

     default BlockState axeInkOff(BlockState state, ItemAbility ability) {
         if (ability == ItemAbilities.AXE_WAX_OFF && state.getValue(MODE) != GlowMode.NORMAL) {
             return state.setValue(MODE, GlowMode.NORMAL)
                     .setValue(PHASE, GlowPhase.UP)
                     .setValue(GLOW, 0);
         }
         return null;
     }

     default ItemInteractionResult useInk(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player) {
         GlowMode mode = state.getValue(MODE);

         if (stack.is(Items.INK_SAC) && mode != GlowMode.INKED) {
             if (!level.isClientSide) {
                 level.setBlock(pos,
                         state.setValue(MODE, GlowMode.INKED)
                                 .setValue(PHASE, GlowPhase.HOLD)
                                 .setValue(GLOW, 0),
                         UPDATE_ALL);

                 level.levelEvent(player, 3003, pos, 0);

                 if (!player.getAbilities().instabuild)
                     stack.shrink(1);
             }

             return ItemInteractionResult.sidedSuccess(level.isClientSide);
         }

         if (stack.is(Items.GLOW_INK_SAC) && mode != GlowMode.GLOW_INKED) {

             if (!level.isClientSide) {
                 level.setBlock(pos,
                         state.setValue(MODE, GlowMode.GLOW_INKED)
                                 .setValue(PHASE, GlowPhase.HOLD)
                                 .setValue(GLOW, 9),
                         UPDATE_ALL);

                 if (!player.getAbilities().instabuild)
                     stack.shrink(1);
             }

             return ItemInteractionResult.sidedSuccess(level.isClientSide);
         }
         
         return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
     }
     
     default void forceStartGlow(Level level, BlockPos pos, BlockState state) {
         if (level.isClientSide) return;

         if (state.getValue(MODE) != GlowMode.NORMAL || state.getValue(GLOW) != 0)
             return;

         if (level.random.nextInt(2) != 0) return;

         level.setBlock(pos,
                 state.setValue(PHASE, GlowPhase.UP),
                 UPDATE_CLIENTS);

         level.scheduleTick(pos, state.getBlock(), 2);
     }
     
     default void randomGlowTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
         if (state.getValue(MODE) != GlowMode.NORMAL || state.getValue(GLOW) != 0)
             return;

         if (random.nextInt(4) != 0) return;

         level.setBlock(pos,
                 state.setValue(PHASE, GlowPhase.UP),
                 UPDATE_CLIENTS);

         level.scheduleTick(pos, state.getBlock(), 2);
     }
     
     default void glowTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
         if (state.getValue(MODE) == GlowMode.GLOW_INKED) {
             if (state.getValue(GLOW) != 9) {
                 level.setBlock(pos,
                         state.setValue(GLOW, 9),
                         UPDATE_CLIENTS);
             }
             return;
         }

         if (state.getValue(MODE) != GlowMode.NORMAL)
             return;

         GlowPhase phase = state.getValue(PHASE);
         int glow = state.getValue(GLOW);

         switch (phase) {

             case UP -> {
                 glow++;

                 if (glow >= 9) {
                     glow = 9;
                     phase = GlowPhase.HOLD;
                 }
             }

             case HOLD -> {
                 if (random.nextInt(4) == 0) {
                     phase = GlowPhase.DOWN;
                 }
             }

             case DOWN -> {
                 glow--;

                 if (glow <= 0) {
                     glow = 0;

                     level.setBlock(pos,
                             state.setValue(GLOW, 0)
                                     .setValue(PHASE, GlowPhase.UP),
                             UPDATE_CLIENTS);

                     return;
                 }
             }
         }

         level.setBlock(pos,
                 state.setValue(GLOW, glow)
                         .setValue(PHASE, phase),
                 UPDATE_CLIENTS);

         level.scheduleTick(pos, state.getBlock(), 2);
     }

    public enum GlowMode implements StringRepresentable {
        NORMAL("normal"),
        INKED("inked"),
        GLOW_INKED("glow_inked");

        private final String name;

        GlowMode(String name) {
            this.name = name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return name;
        }
    }

    public enum GlowPhase implements StringRepresentable {
        UP("up"),
        HOLD("hold"),
        DOWN("down");

        private final String name;

        GlowPhase(String name) {
            this.name = name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return name;
        }
    }
}
