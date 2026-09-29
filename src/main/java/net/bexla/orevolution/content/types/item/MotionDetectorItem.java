package net.bexla.orevolution.content.types.item;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.init.RegDataComponents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class MotionDetectorItem extends Item {
    public MotionDetectorItem(Properties properties) {
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

        if (player.tickCount % OrevolutionConfig.COMMON.motionDetectorInterval.get() != 0)
            return;

        int range = OrevolutionConfig.COMMON.motionDetectorRange.get();

        AABB box = player.getBoundingBox().inflate(range);

        List<Mob> mobs = level.getEntitiesOfClass(
                Mob.class,
                box,
                LivingEntity::isAlive
        );

        boolean detectedMotion = !mobs.isEmpty();
        boolean danger = false;

        for (Mob mob : mobs) {
            LivingEntity target = mob.getTarget();

            if (target == player) {
                danger = true;
                break;
            }
        }

        stack.set(RegDataComponents.MOTION_STATE.get(), danger ? 2 : detectedMotion ? 1 : 0);
    }

    public static int getMotionState(ItemStack stack) {
        return stack.getOrDefault(RegDataComponents.MOTION_STATE.get(), 0);
    }
}