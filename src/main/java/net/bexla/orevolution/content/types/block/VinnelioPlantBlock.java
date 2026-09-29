package net.bexla.orevolution.content.types.block;

import com.mojang.serialization.MapCodec;
import net.bexla.orevolution.content.interfaces.IVinnelio;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class VinnelioPlantBlock extends GrowingPlantBodyBlock implements BonemealableBlock, IVinnelio {
    public static final MapCodec<VinnelioPlantBlock> CODEC = simpleCodec(VinnelioPlantBlock::new);

    @Override
    public MapCodec<VinnelioPlantBlock> codec() {
        return CODEC;
    }

    public VinnelioPlantBlock(BlockBehaviour.Properties p_153000_) {
        super(p_153000_, Direction.DOWN, SHAPE, false);
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) RegBlocks.VINNELIO.get();
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader p_304590_, BlockPos p_152967_, BlockState p_152968_) {
        return new ItemStack(RegItems.VINNELIO.get());
    }

    private Optional<BlockPos> getHeadPos(BlockGetter level, BlockPos pos, Block block) {
        return BlockUtil.getTopConnectedBlock(level, pos, block, this.growthDirection, this.getHeadBlock());
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
    public void performBonemeal(ServerLevel level, RandomSource source, BlockPos pos, BlockState state) {
        if(getHeadPos(level, pos, state.getBlock()).isEmpty()) return;

        BlockPos headPos = getHeadPos(level, pos, state.getBlock()).get();

        level.setBlock(headPos, level.getBlockState(headPos).setValue(FRUIT, Boolean.valueOf(true)), 2);
    }
}
