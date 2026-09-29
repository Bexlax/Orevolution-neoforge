package net.bexla.orevolution.content.types.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.Tags;

public class StoneBushBlock extends BushBlock {
    public static final MapCodec<StoneBushBlock> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    propertiesCodec(),
                    Codec.DOUBLE.fieldOf("sizeXMin").forGetter(block -> block.sizeXMin),
                    Codec.DOUBLE.fieldOf("sizeYMin").forGetter(block -> block.sizeYMin),
                    Codec.DOUBLE.fieldOf("sizeZMin").forGetter(block -> block.sizeZMin),
                    Codec.DOUBLE.fieldOf("sizeXM").forGetter(block -> block.sizeXM),
                    Codec.DOUBLE.fieldOf("sizeYM").forGetter(block -> block.sizeYM),
                    Codec.DOUBLE.fieldOf("sizeZM").forGetter(block -> block.sizeZM)
            ).apply(instance, StoneBushBlock::new)
    );
    private final double sizeXM;
    private final double sizeYM;
    private final double sizeZM;

    private final double sizeXMin;
    private final double sizeYMin;
    private final double sizeZMin;
    
    public StoneBushBlock(BlockBehaviour.Properties properties,
                          double sizeXMin, double sizeYMin, double sizeZMin,
                          double sizeXM, double sizeYM, double sizeZM) {
        super(properties);
        this.sizeXM = sizeXM;
        this.sizeYM = sizeYM;
        this.sizeZM = sizeZM;
        this.sizeXMin = sizeXMin;
        this.sizeYMin = sizeYMin;
        this.sizeZMin = sizeZMin;
    }

    @Override
    public VoxelShape getShape(BlockState p_52297_, BlockGetter p_52298_, BlockPos p_52299_, CollisionContext p_52300_) {
        return Block.box(sizeXMin, sizeYMin, sizeZMin, sizeXM, sizeYM, sizeZM);
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.DEAD_BUSH_MAY_PLACE_ON) || state.is(Tags.Blocks.STONES);
    }
}
