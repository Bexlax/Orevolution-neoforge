package net.bexla.orevolution.content.types.block;

import com.mojang.serialization.MapCodec;
import net.bexla.orevolution.content.interfaces.IVinnelio;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;

public class VinnelioBlock extends GrowingPlantHeadBlock implements BonemealableBlock, IVinnelio {
    public static final MapCodec<VinnelioBlock> CODEC = simpleCodec(VinnelioBlock::new);

    @Override
    public MapCodec<VinnelioBlock> codec() {
        return CODEC;
    }

    public VinnelioBlock(BlockBehaviour.Properties p_152959_) {
        super(p_152959_, Direction.DOWN, SHAPE, false, 0.1);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)).setValue(FRUIT, Boolean.valueOf(false)));
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity livEtit && entity.getType() != EntityType.FOX && entity.getType() != EntityType.BEE) {
            if(livEtit.getArmorValue() > 0) return;
            if (!level.isClientSide && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())) {
                double d0 = Math.abs(entity.getX() - entity.xOld);
                double d1 = Math.abs(entity.getZ() - entity.zOld);
                if (d0 >= 0.003F || d1 >= 0.003F) {
                    entity.hurt(level.damageSources().sweetBerryBush(), 1.0F);
                }
            }
        }
    }

    @Override
    protected int getBlocksToGrowWhenBonemealed(RandomSource p_220928_) {
        return 1;
    }

    @Override
    protected boolean canGrowInto(BlockState p_152998_) {
        return p_152998_.isAir();
    }

    @Override
    protected Block getBodyBlock() {
        return RegBlocks.VINNELIO_PLANT.get();
    }

    @Override
    protected BlockState getGrowIntoState(BlockState p_220935_, RandomSource p_220936_) {
        return super.getGrowIntoState(p_220935_, p_220936_).setValue(FRUIT, Boolean.valueOf(p_220936_.nextFloat() < 0.11F));
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader p_304590_, BlockPos p_152967_, BlockState p_152968_) {
        return new ItemStack(RegItems.VINNELIO.get());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState p_152980_, Level p_152981_, BlockPos p_152982_, Player p_152983_, BlockHitResult p_152985_) {
        return IVinnelio.use(p_152983_, p_152980_, p_152981_, p_152982_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_152993_) {
        super.createBlockStateDefinition(p_152993_);
        p_152993_.add(FRUIT);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader p_256026_, BlockPos p_152971_, BlockState p_152972_) {
        return !p_152972_.getValue(FRUIT);
    }

    @Override
    public boolean isBonemealSuccess(Level p_220930_, RandomSource p_220931_, BlockPos p_220932_, BlockState p_220933_) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel p_220923_, RandomSource p_220924_, BlockPos p_220925_, BlockState p_220926_) {
        p_220923_.setBlock(p_220925_, p_220926_.setValue(FRUIT, Boolean.valueOf(true)), 2);
    }

}
