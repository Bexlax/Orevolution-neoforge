package net.bexla.orevolution.content.types.entity;

import net.bexla.orevolution.init.RegEntityTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;

public class ExplosiveArrow extends AbstractArrow {
    public ExplosiveArrow(EntityType<? extends ExplosiveArrow> entityType, Level level) {
        super(entityType, level);
    }

    public ExplosiveArrow(Level level, LivingEntity owner, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(RegEntityTypes.FIERY_ARROW.get(), owner, level, pickupItemStack, firedFromWeapon);
    }

    public ExplosiveArrow(Level level, double x, double y, double z, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(RegEntityTypes.FIERY_ARROW.get(), x, y, z, level, pickupItemStack, firedFromWeapon);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(Items.ARROW);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        if(this.isOnFire()) {
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 1F, Level.ExplosionInteraction.MOB);
            this.discard();
        }
        this.setPickupItemStack(new ItemStack(Items.ARROW));
        super.onHitBlock(result);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        result.getEntity().igniteForSeconds(3);

        super.onHitEntity(result);
    }

    @Override
    protected void doPostHurtEffects(LivingEntity living) {
        if(this.isOnFire()) {
            this.level().explode(this, living.getX(), living.getY(), living.getZ(), 1.7F, Level.ExplosionInteraction.MOB);
        }
        super.doPostHurtEffects(living);
    }
}
