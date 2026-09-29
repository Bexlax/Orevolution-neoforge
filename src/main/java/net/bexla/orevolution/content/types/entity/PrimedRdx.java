package net.bexla.orevolution.content.types.entity;

import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.portal.DimensionTransition;

import javax.annotation.Nullable;
import java.util.Optional;

public class PrimedRdx extends Entity implements TraceableEntity {
    private static final EntityDataAccessor<Integer> DATA_FUSE_ID = SynchedEntityData.defineId(PrimedRdx.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<BlockState> DATA_BLOCK_STATE_ID = SynchedEntityData.defineId(PrimedRdx.class, EntityDataSerializers.BLOCK_STATE);
    private static final int DEFAULT_FUSE_TIME = 100;
    private static final String TAG_BLOCK_STATE = "block_state";
    public static final String TAG_FUSE = "fuse";
    private static final ExplosionDamageCalculator USED_PORTAL_DAMAGE_CALCULATOR = new ExplosionDamageCalculator() {
        @Override
        public boolean shouldBlockExplode(Explosion p_353087_, BlockGetter p_353096_, BlockPos p_353092_, BlockState p_353086_, float p_353094_) {
            return p_353086_.is(Blocks.NETHER_PORTAL) ? false : super.shouldBlockExplode(p_353087_, p_353096_, p_353092_, p_353086_, p_353094_);
        }

        @Override
        public Optional<Float> getBlockExplosionResistance(
                Explosion p_353090_, BlockGetter p_353088_, BlockPos p_353091_, BlockState p_353093_, FluidState p_353095_
        ) {
            return p_353093_.is(Blocks.NETHER_PORTAL)
                    ? Optional.empty()
                    : super.getBlockExplosionResistance(p_353090_, p_353088_, p_353091_, p_353093_, p_353095_);
        }
    };
    @javax.annotation.Nullable
    private LivingEntity owner;
    private boolean usedPortal;

    public PrimedRdx(EntityType<? extends PrimedRdx> entityType, Level level) {
        super(entityType, level);
        this.blocksBuilding = true;
    }

    public PrimedRdx(Level level, double x, double y, double z, @javax.annotation.Nullable LivingEntity owner) {
        this(RegEntityTypes.PRIMED_RDX.get(), level);
        this.setPos(x, y, z);
        double d0 = level.random.nextDouble() * (float) (Math.PI * 2);
        this.setDeltaMovement(-Math.sin(d0) * 0.02, 0.2F, -Math.cos(d0) * 0.02);
        this.setFuse(DEFAULT_FUSE_TIME);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.owner = owner;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_FUSE_ID, DEFAULT_FUSE_TIME);
        builder.define(DATA_BLOCK_STATE_ID, RegBlocks.RDX.get().defaultBlockState());
    }

    @Override
    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.NONE;
    }

    @Override
    public boolean isPickable() {
        return !this.isRemoved();
    }

    @Override
    protected double getDefaultGravity() {
        return 0.04;
    }

    @Override
    public void tick() {
        this.handlePortal();
        this.applyGravity();
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98));
        if (this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.7, -0.5, 0.7));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.level().isClientSide) {
                this.explode();
            }
        } else {
            this.updateInWaterStateAndDoFluidPushing();
            if (this.level().isClientSide) {
                this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    protected void explode() {
        float f = 6.0F;
        this.level()
                .explode(
                        this,
                        Explosion.getDefaultDamageSource(this.level(), this),
                        this.usedPortal ? USED_PORTAL_DAMAGE_CALCULATOR : null,
                        this.getX(),
                        this.getY(0.0625),
                        this.getZ(),
                        f,
                        true,
                        Level.ExplosionInteraction.TNT
                );
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putShort("fuse", (short)this.getFuse());
        compound.put("block_state", NbtUtils.writeBlockState(this.getBlockState()));
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        this.setFuse(compound.getShort("fuse"));
        if (compound.contains("block_state", 10)) {
            this.setBlockState(NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), compound.getCompound("block_state")));
        }
    }

    @javax.annotation.Nullable
    public LivingEntity getOwner() {
        return this.owner;
    }

    @Override
    public void restoreFrom(Entity entity) {
        super.restoreFrom(entity);
        if (entity instanceof PrimedRdx rdx) {
            this.owner = rdx.owner;
        }
    }

    public void setFuse(int life) {
        this.entityData.set(DATA_FUSE_ID, life);
    }

    public int getFuse() {
        return this.entityData.get(DATA_FUSE_ID);
    }

    public void setBlockState(BlockState blockState) {
        this.entityData.set(DATA_BLOCK_STATE_ID, blockState);
    }

    public BlockState getBlockState() {
        return this.entityData.get(DATA_BLOCK_STATE_ID);
    }

    private void setUsedPortal(boolean usedPortal) {
        this.usedPortal = usedPortal;
    }

    @Nullable
    @Override
    public Entity changeDimension(DimensionTransition transition) {
        Entity entity = super.changeDimension(transition);
        if (entity instanceof PrimedRdx rdx) {
            rdx.setUsedPortal(true);
        }

        return entity;
    }
}
