package net.bexla.orevolution.datagen;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.types.block.OreCropBlock;
import net.bexla.orevolution.content.types.providers.BlockStateModelProvider;
import net.bexla.orevolution.init.RegBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class GENBlockStateModels extends BlockStateModelProvider {
    public GENBlockStateModels(PackOutput output, ExistingFileHelper helper) {
        super(output, helper);
    }

    @Override
    public @NotNull String getName() {
        return Orevolution.MODID + " Block States";
    }

    private void ore(Supplier<? extends Block> block) {
        simpleBlock(block, "ore");
    }

    private void storage(Supplier<? extends Block> block) {
        simpleBlock(block, "storage");
    }

    private void decorative(Supplier<? extends Block> block) {
        simpleBlock(block, "decorative");
    }

    private void functional(Supplier<? extends Block> block) {
        simpleBlock(block, "functional");
    }

    private void compat(String modid, Supplier<? extends Block> block) {
        simpleBlock(block, "compat/" + modid);
    }

    public void makeCrop(DeferredBlock<Block> block, String modelName, String textureName) {
        makeCrop((OreCropBlock) block.get(), modelName, textureName);
    }

    @Override
    protected void registerStatesAndModels() {
        String sk = "spelunkery";

        ore(RegBlocks.TIN_ORE);
        ore(RegBlocks.DEEPSLATE_TIN_ORE);
        ore(RegBlocks.PLATINUM_ORE);
        ore(RegBlocks.DEEPSLATE_PLATINUM_ORE);
        ore(RegBlocks.NETHER_TUNGSTEN_ORE);
        ore(RegBlocks.END_XP_ORE);
        ore(RegBlocks.NETHER_XP_ORE);
        ore(RegBlocks.PRIMITIVE_AETHERROCK);

        storage(RegBlocks.TIN_BLOCK);
        storage(RegBlocks.PLATINUM_BLOCK);
        storage(RegBlocks.TUNGSTEN_BLOCK);
        storage(RegBlocks.AETHERSTEEL_BLOCK);
        storage(RegBlocks.BRONZE_BLOCK);
        storage(RegBlocks.STEEL_BLOCK);
        storage(RegBlocks.LIVINGSTONE_BLOCK);
        storage(RegBlocks.VERDITE_BLOCK);
        storage(RegBlocks.RAW_TIN_BLOCK);
        storage(RegBlocks.RAW_PLATINUM_BLOCK);
        storage(RegBlocks.RAW_TUNGSTEN_BLOCK);

        decorative(RegBlocks.VERDITE_BRICKS);
        decorative(RegBlocks.LIVINGSTONE_BRICKS);
        decorative(RegBlocks.TUNGSTEN_BRICKS);
        complexBlock(RegBlocks.LIMESTONE, "decorative", 1, 1);
        decorative(RegBlocks.AETHERROCK);
        decorative(RegBlocks.POLISHED_AETHERROCK);
        decorative(RegBlocks.AETHERROCK_BRICKS);
        decorative(RegBlocks.AETHERROCK_TILES);
        decorative(RegBlocks.BRONZE_TILES);
        decorative(RegBlocks.POLISHED_TUNGSTEN);
        decorative(RegBlocks.CUT_STEEL_BLOCK);
        decorative(RegBlocks.CUT_TUNGSTEN_BLOCK);
        decorative(RegBlocks.CRACKED_AETHERROCK_BRICKS);
        decorative(RegBlocks.POLISHED_LIMESTONE);
        pillar(RegBlocks.STEEL_PILLAR);
        pillar(RegBlocks.LIMESTONE_PILLAR);
        decorative(RegBlocks.PLATINUM_TILES);
        decorative(RegBlocks.GOLD_TILES);
        cubeColumnBlock(RegBlocks.CHISELED_TUNGSTEN_BLOCK, RegBlocks.POLISHED_TUNGSTEN);
        cubeColumnBlock(RegBlocks.CHISELED_TUNGSTEN_BRICKS, RegBlocks.TUNGSTEN_BRICKS);
        decorative(RegBlocks.TIN_TILES);
        decorative(RegBlocks.TIN_BRICKS);
        pillar(RegBlocks.PLATINUM_PILLAR);
        pillar(RegBlocks.GOLD_PILLAR);

        functional(RegBlocks.TUNGSTEN_SPONGE);
        functional(RegBlocks.HOT_TUNGSTEN_SPONGE);

        barsBlock(RegBlocks.GOLD_BARS);
        barsBlock(RegBlocks.PLATINUM_BARS);
        barsBlock(RegBlocks.TUNGSTEN_BARS);
        barsBlock(RegBlocks.BRONZE_BARS);
        barsBlock(RegBlocks.TIN_BARS);
        barsBlock(RegBlocks.STEEL_BARS);

        stairsBlock(RegBlocks.POLISHED_AETHERROCK_STAIR, RegBlocks.POLISHED_AETHERROCK);
        slabBlock(RegBlocks.POLISHED_AETHERROCK_SLAB, RegBlocks.POLISHED_AETHERROCK);
        wallBlock(RegBlocks.POLISHED_AETHERROCK_WALL, RegBlocks.POLISHED_AETHERROCK);
        stairsBlock(RegBlocks.POLISHED_LIMESTONE_STAIR, RegBlocks.POLISHED_LIMESTONE);
        slabBlock(RegBlocks.POLISHED_LIMESTONE_SLAB, RegBlocks.POLISHED_LIMESTONE);
        wallBlock(RegBlocks.POLISHED_LIMESTONE_WALL, RegBlocks.POLISHED_LIMESTONE);
        
        doorBlock(RegBlocks.STEEL_DOOR);
        trapdoorBlock(RegBlocks.STEEL_TRAPDOOR);

        makeCrop(RegBlocks.VERDITE_CROP, "verdite_crop_stage", "verdite_crop_stage");
        makeCrop(RegBlocks.LIVINGSTONE_CROP, "livingstone_crop_stage", "livingstone_crop_stage");
    }

    public void pillarDeco(String id, Supplier<Block> block) {
        if (block.get() instanceof RotatedPillarBlock log)
            this.axisBlock(log, suffix(this.blockTexture(block.get(), "compat/" + id), "_side"), suffix(this.blockTexture(block.get(), "compat/" + id), "_top"));
    }
}
