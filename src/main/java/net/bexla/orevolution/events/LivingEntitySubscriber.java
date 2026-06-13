package net.bexla.orevolution.events;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.OrevolutionConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = Orevolution.MODID)
public class LivingEntitySubscriber {
    @SubscribeEvent
    public static void onInteractWithBlock(PlayerInteractEvent.RightClickBlock evt) {
        if(!OrevolutionConfig.COMMON.generateVerditeOre.get()) return;

        Level level = evt.getLevel();
        if (level.isClientSide) return;

        BlockPos pos = evt.getPos();
        BlockState state = level.getBlockState(pos);

//        if (state.getBlock() instanceof ComposterBlock && state.getValue(ComposterBlock.LEVEL) == 8) {
//            if (level.random.nextFloat() < 0.4) {
//                double x = (double)pos.getX() + 0.5D;
//                double y = (double)pos.getY() + 1.05D;
//                double z = (double)pos.getZ() + 0.5D;
//                ItemEntity itementity = new ItemEntity(level, x, y, z, new ItemStack(RegItems.DEAD_SEED.get()));
//                itementity.setDeltaMovement(level.random.nextGaussian() * 0.05D, level.random.nextGaussian() * 0.05D + 0.2D, level.random.nextGaussian() * 0.05D);
//                level.addFreshEntity(itementity);
//            }
//        }
    }
}
