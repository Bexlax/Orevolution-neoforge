package net.bexla.orevolution.content.types.item;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.init.RegDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;

public class GeoScannerItem extends Item {
    public GeoScannerItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return slotChanged || oldStack.getItem() != newStack.getItem();
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);

        if (level.isClientSide || !(entity instanceof Player player))
            return;

        if(player.tickCount % OrevolutionConfig.COMMON.geoScannerInterval.get() != 0) return;

        int range = OrevolutionConfig.COMMON.geoScannerRange.get();

        BlockPos center = player.blockPosition();

        int ores = 0;

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int x = -range; x <= range; x++) {
            for (int y = -range; y <= range; y++) {
                for (int z = -range; z <= range; z++) {

                    pos.set(center.getX() + x,
                            center.getY() + y,
                            center.getZ() + z);

                    BlockState state = level.getBlockState(pos);

                    if (isOre(state))
                        ores++;
                }
            }
        }

        int previous = stack.getOrDefault(RegDataComponents.SCANNED_ORES.get(), 0);

        if (previous != ores) {
            stack.set(RegDataComponents.SCANNED_ORES.get(), ores);
        }
    }

    private static boolean isOre(BlockState state) {
        return state.is(Tags.Blocks.ORES);
    }

    public static int getOreCount(ItemStack stack) {
        return stack.getOrDefault(RegDataComponents.SCANNED_ORES.get(), 0);
    }

    public static int getScannerStage(ItemStack stack) {
        int ores = getOreCount(stack);

        if (ores == 0)
            return 0;

        if (ores < 8)
            return 1;

        if (ores < 16)
            return 2;

        if (ores < 24)
            return 3;

        if (ores < 32)
            return 4;

        if (ores < 40)
            return 5;

        return 6;
    }
}