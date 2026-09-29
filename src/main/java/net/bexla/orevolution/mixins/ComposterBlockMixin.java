package net.bexla.orevolution.mixins;

import net.bexla.orevolution.init.RegItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ComposterBlock.class)
public class ComposterBlockMixin {

    @Inject(method = "useWithoutItem", at = @At("RETURN"))
    private void orevolution$giveBonusItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult, CallbackInfoReturnable<InteractionResult> cir) {
        if (level.isClientSide) return;
        if (state.getValue(ComposterBlock.LEVEL) != 8) return;
        if (!cir.getReturnValue().consumesAction()) return;
        if (level.random.nextFloat() >= 0.3F) return;

        Vec3 vec3 = Vec3.atLowerCornerWithOffset(pos, 0.5, 1.01, 0.5).offsetRandom(level.random, 0.7F);

        ItemEntity itemEntity = new ItemEntity(level, vec3.x(), vec3.y(), vec3.z(), new ItemStack(RegItems.DEAD_SEED.get()));
        itemEntity.setDefaultPickUpDelay();
        level.addFreshEntity(itemEntity);
}
}
