package net.bexla.orevolution.content.types.item;

import net.bexla.orevolution.content.types.entity.ExplosiveArrow;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class FieryArrowItem extends ArrowItem {
    public FieryArrowItem(Properties properties) {
        super(properties);
    }

    public AbstractArrow createArrow(Level level, ItemStack ammo, LivingEntity shooter, @Nullable ItemStack weapon) {
        return new ExplosiveArrow(level, shooter, ammo.copyWithCount(1), weapon);
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        ExplosiveArrow arrow = new ExplosiveArrow(level, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1), null);
        return arrow;
    }
}
