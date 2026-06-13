package net.bexla.orevolution.content.types.item;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import org.jetbrains.annotations.NotNull;

public class ScytheItem extends SwordItem {
    public ScytheItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext ctx) {
        Level level = ctx.getLevel();
        BlockPos center = ctx.getClickedPos();
        Player player = ctx.getPlayer();

        int blocksTilled = 0;

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos pos = center.offset(x, 0, z);
                BlockState state = level.getBlockState(pos);

                if (!level.isEmptyBlock(pos.above())) continue;

                BlockState modified = state.getToolModifiedState(
                        ctx,
                        ItemAbilities.HOE_TILL,
                        false
                );

                if (modified != null) {
                    if (!level.isClientSide) {
                        level.setBlock(pos, modified, 11);
                        level.gameEvent(
                                GameEvent.BLOCK_CHANGE,
                                pos,
                                GameEvent.Context.of(player, modified)
                        );
                    }
                    blocksTilled++;
                }
            }
        }

        if (blocksTilled > 0) {
            level.playSound(player, center, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);

            if (!level.isClientSide && player != null) {
                ctx.getItemInHand().hurtAndBreak(blocksTilled, player, player.getEquipmentSlotForItem(player.getMainHandItem()));
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.PASS;
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.neoforged.neoforge.common.ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_HOE_ACTIONS.contains(itemAbility);
    }
}