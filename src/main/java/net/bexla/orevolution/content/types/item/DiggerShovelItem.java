package net.bexla.orevolution.content.types.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;

public class DiggerShovelItem extends ShovelItem {
    public DiggerShovelItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        Level level = ctx.getLevel();
        BlockPos origin = ctx.getClickedPos();

        if (ctx.getClickedFace() == Direction.DOWN) {
            return InteractionResult.PASS;
        }

        Player player = ctx.getPlayer();
        ItemStack stack = ctx.getItemInHand();

        int blocksAffected = 0;
        boolean playedSound = false;

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos pos = origin.offset(x, 0, z);
                BlockState state = level.getBlockState(pos);

                BlockState flattened = state.getToolModifiedState(
                        ctx, ItemAbilities.SHOVEL_FLATTEN, false
                );

                BlockState resultState = null;

                if (flattened != null && level.isEmptyBlock(pos.above())) {
                    resultState = flattened;

                    if (!playedSound) {
                        level.playSound(player, origin, SoundEvents.SHOVEL_FLATTEN,
                                SoundSource.BLOCKS, 1.0F, 1.0F);
                        playedSound = true;
                    }

                } else if (state.getBlock() instanceof CampfireBlock
                        && state.getValue(CampfireBlock.LIT)) {

                    if (!level.isClientSide()) {
                        level.levelEvent(null, 1009, pos, 0);
                    }

                    CampfireBlock.dowse(player, level, pos, state);
                    resultState = state.setValue(CampfireBlock.LIT, false);
                }

                if (resultState != null) {
                    if (!level.isClientSide) {
                        level.setBlock(pos, resultState, 11);
                        level.gameEvent(GameEvent.BLOCK_CHANGE, pos,
                                GameEvent.Context.of(player, resultState));
                    }
                    blocksAffected++;
                }
            }
        }

        if (blocksAffected > 0 && player != null) {
            stack.hurtAndBreak(blocksAffected, player, player.getEquipmentSlotForItem(stack));
        }

        return blocksAffected > 0
                ? InteractionResult.sidedSuccess(level.isClientSide)
                : InteractionResult.PASS;
    }
}