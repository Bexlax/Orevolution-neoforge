package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.armor.OrevolutionArmorPower;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArmorExtendPickUp extends OrevolutionArmorPower {
    private final double reach;

    public ArmorExtendPickUp(String tooltipId, @NotNull IConditional conditional, double reach) {
        super(tooltipId, conditional);
        this.reach = reach;
    }

    @Override
    public Object addTooltipValue() {
        return "+" + (int)reach;
    }

    @Override
    public void onTickWhileWorn(ItemStack stack, LivingEntity wearer, EquipmentSlot slot) {
        if (!(wearer instanceof Player player)) return;

        Level level = player.level();

        if (level.isClientSide) return;

        AABB area = player.getBoundingBox().inflate(reach);

        List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class, area);

        for (ItemEntity item : items) {
            if (!item.isAlive()) continue;

            if (item.hasPickUpDelay()) continue;

            Vec3 motion = player.position()
                    .add(0, 0.75D, 0)
                    .subtract(item.position());

            double distance = motion.length();

            if (distance < 0.001D) continue;

            Vec3 velocity = motion.normalize().scale(0.15D);

            item.setDeltaMovement(
                    item.getDeltaMovement().add(velocity)
            );

            item.hurtMarked = true;
        }
    }
}
