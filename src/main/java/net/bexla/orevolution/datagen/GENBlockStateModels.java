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
        simpleBlock(block, "ore", "");
    }

    private void storage(Supplier<? extends Block> block) {
        simpleBlock(block, "storage");
    }

    private void decorative(Supplier<? extends Block> block) {
        simpleBlock(block, "decorative");
    }

    private void decorative(Supplier<? extends Block> block, String renderType) {
        simpleBlock(block, "decorative", renderType);
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

    public void pillar(DeferredBlock<Block> block) {
        pillar(block, "decorative");
    }

    public void plant(DeferredBlock<Block> block) {
        crossBlock(block, "decorative/plant");
    }

    @Override
    protected void registerStatesAndModels() {
        crossBlock(RegBlocks.EMERALD_CLUSTER, "ore");
        crossBlock(RegBlocks.PRISMARINE_CLUSTER, "ore");

        plant(RegBlocks.CORELIO);
        plant(RegBlocks.UNKNOWN_ROOTS);
        crossBlockNoItem(RegBlocks.VINNELIO_PLANT, "decorative/plant");
        vinnelio(RegBlocks.VINNELIO);

        doubleCrossBlock(RegBlocks.LARGE_CORELIO, "decorative/plant");


        ore(RegBlocks.TIN_ORE);
        ore(RegBlocks.DEEPSLATE_TIN_ORE);
        storage(RegBlocks.RAW_TIN_BLOCK);
        decorative(RegBlocks.TIN_TILES);
        decorative(RegBlocks.TIN_BRICKS);
        storage(RegBlocks.TIN_BLOCK);
        barsBlock(RegBlocks.TIN_BARS);

        ore(RegBlocks.CASSITERITE_ORE);
        ore(RegBlocks.DEEPSLATE_CASSITERITE_ORE);
        ore(RegBlocks.NETHER_CASSITERITE_ORE);
        storage(RegBlocks.RAW_CASSITERITE_BLOCK);
        storage(RegBlocks.CASSITERITE_BLOCK);

        ore(RegBlocks.PLATINUM_ORE);
        ore(RegBlocks.DEEPSLATE_PLATINUM_ORE);
        storage(RegBlocks.RAW_PLATINUM_BLOCK);
        storage(RegBlocks.PLATINUM_BLOCK);
        decorative(RegBlocks.PLATINUM_TILES);
        pillar(RegBlocks.PLATINUM_PILLAR);
        barsBlock(RegBlocks.PLATINUM_BARS);
        
        ore(RegBlocks.END_XP_ORE);
        ore(RegBlocks.NETHER_XP_ORE);
        ore(RegBlocks.QUARTZOLITE);

        pillar(RegBlocks.MOONSTONE, "ore");
        pillarWithBottom(RegBlocks.RHYOLITE_ENCRUSTED_MOONSTONE, "ore");
        pillarWithBottom(RegBlocks.BASALT_ENCRUSTED_MOONSTONE, "ore");
        ore(RegBlocks.RHYOLITE_EMERALD_ORE);
        ore(RegBlocks.RHYOLITE_EMERALD_ORE_CLUMP);

        ore(RegBlocks.PRIMITIVE_AETHERROCK);
        storage(RegBlocks.AETHERSTEEL_BLOCK);

        storage(RegBlocks.BRONZE_BLOCK);
        decorative(RegBlocks.BRONZE_TILES);
        barsBlock(RegBlocks.BRONZE_BARS);

        storage(RegBlocks.STEEL_BLOCK);
        decorative(RegBlocks.CUT_STEEL_BLOCK);
        pillar(RegBlocks.STEEL_PILLAR);
        decorative(RegBlocks.STEEL_GRATE, "cutout");
        barsBlock(RegBlocks.STEEL_BARS);
        blockSet(
                RegBlocks.CUT_STEEL_BLOCK,
                RegBlocks.CUT_STEEL_SLAB,
                RegBlocks.CUT_STEEL_STAIR,
                null,
                "decorative"
        );
        doorBlock(RegBlocks.STEEL_DOOR);
        trapdoorBlock(RegBlocks.STEEL_TRAPDOOR);

        makeCrop(RegBlocks.LIVINGSTONE_CROP, "livingstone_crop_stage", "livingstone_crop_stage");
        storage(RegBlocks.LIVINGSTONE_BLOCK);
        decorative(RegBlocks.LIVINGSTONE_BRICKS);
        decorative(RegBlocks.POLISHED_LIVINGSTONE);
        blockSet(
                RegBlocks.LIVINGSTONE_BLOCK,
                RegBlocks.LIVINGSTONE_SLAB,
                RegBlocks.LIVINGSTONE_STAIR,
                RegBlocks.LIVINGSTONE_WALL,
                "storage"
        );
        blockSet(
                RegBlocks.POLISHED_LIVINGSTONE,
                RegBlocks.POLISHED_LIVINGSTONE_SLAB,
                RegBlocks.POLISHED_LIVINGSTONE_STAIR,
                RegBlocks.POLISHED_LIVINGSTONE_WALL,
                "decorative"
        );
        blockSet(
                RegBlocks.LIVINGSTONE_BRICKS,
                RegBlocks.LIVINGSTONE_BRICKS_SLAB,
                RegBlocks.LIVINGSTONE_BRICKS_STAIR,
                RegBlocks.LIVINGSTONE_BRICKS_WALL,
                "decorative"
        );

        storage(RegBlocks.PYRITE_BLOCK);
        
        storage(RegBlocks.VERDITE_BLOCK);
        decorative(RegBlocks.VERDITE_BRICKS);
        makeCrop(RegBlocks.VERDITE_CROP, "verdite_crop_stage", "verdite_crop_stage");

        ore(RegBlocks.NETHER_TUNGSTEN_ORE);
        storage(RegBlocks.RAW_TUNGSTEN_BLOCK);
        storage(RegBlocks.TUNGSTEN_BLOCK);
        decorative(RegBlocks.TUNGSTEN_BRICKS);
        decorative(RegBlocks.POLISHED_TUNGSTEN);
        decorative(RegBlocks.CUT_TUNGSTEN_BLOCK);
        cubeColumnBlock(RegBlocks.CHISELED_TUNGSTEN_BLOCK, RegBlocks.POLISHED_TUNGSTEN);
        cubeColumnBlock(RegBlocks.CHISELED_TUNGSTEN_BRICKS, RegBlocks.TUNGSTEN_BRICKS);
        barsBlock(RegBlocks.TUNGSTEN_BARS);
        storage(RegBlocks.DECAYING_TUNGSTEN_BLOCK);
        decorative(RegBlocks.DECAYING_TUNGSTEN_BRICKS);
        decorative(RegBlocks.POLISHED_DECAYING_TUNGSTEN);
        decorative(RegBlocks.CUT_DECAYING_TUNGSTEN_BLOCK);
        cubeColumnBlock(RegBlocks.CHISELED_DECAYING_TUNGSTEN_BLOCK, RegBlocks.POLISHED_DECAYING_TUNGSTEN);
        cubeColumnBlock(RegBlocks.CHISELED_DECAYING_TUNGSTEN_BRICKS, RegBlocks.DECAYING_TUNGSTEN_BRICKS);
        barsBlock(RegBlocks.DECAYING_TUNGSTEN_BARS);
        storage(RegBlocks.CORRODED_TUNGSTEN_BLOCK);
        decorative(RegBlocks.CORRODED_TUNGSTEN_BRICKS);
        decorative(RegBlocks.POLISHED_CORRODED_TUNGSTEN);
        decorative(RegBlocks.CUT_CORRODED_TUNGSTEN_BLOCK);
        cubeColumnBlock(RegBlocks.CHISELED_CORRODED_TUNGSTEN_BLOCK, RegBlocks.POLISHED_CORRODED_TUNGSTEN);
        cubeColumnBlock(RegBlocks.CHISELED_CORRODED_TUNGSTEN_BRICKS, RegBlocks.CORRODED_TUNGSTEN_BRICKS);
        barsBlock(RegBlocks.CORRODED_TUNGSTEN_BARS);
        functional(RegBlocks.TUNGSTEN_SPONGE);
        functional(RegBlocks.HOT_TUNGSTEN_SPONGE);

        decorative(RegBlocks.AETHERROCK);
        decorative(RegBlocks.POLISHED_AETHERROCK);
        decorative(RegBlocks.AETHERROCK_BRICKS);
        decorative(RegBlocks.AETHERROCK_TILES);
        decorative(RegBlocks.CRACKED_AETHERROCK_BRICKS);
        blockSet(
                RegBlocks.AETHERROCK,
                RegBlocks.AETHERROCK_SLAB,
                RegBlocks.AETHERROCK_STAIR,
                RegBlocks.AETHERROCK_WALL,
                "decorative"
        );
        blockSet(
                RegBlocks.POLISHED_AETHERROCK,
                RegBlocks.POLISHED_AETHERROCK_SLAB,
                RegBlocks.POLISHED_AETHERROCK_STAIR,
                RegBlocks.POLISHED_AETHERROCK_WALL,
                "decorative"
        );
        blockSet(
                RegBlocks.AETHERROCK_BRICKS,
                RegBlocks.AETHERROCK_BRICKS_SLAB,
                RegBlocks.AETHERROCK_BRICKS_STAIR,
                RegBlocks.AETHERROCK_BRICKS_WALL,
                "decorative"
        );


        decorative(RegBlocks.RHYOLITE_BRICKS);
        mirroredPillar(RegBlocks.RHYOLITE, "", "_top", "decorative");
        decorative(RegBlocks.POLISHED_RHYOLITE);
        pillar(RegBlocks.RHYOLITE_PILLAR);
        blockSet(
                RegBlocks.RHYOLITE,
                RegBlocks.RHYOLITE_SLAB,
                RegBlocks.RHYOLITE_STAIR,
                RegBlocks.RHYOLITE_WALL,
                "decorative"
        );
        blockSet(
                RegBlocks.POLISHED_RHYOLITE,
                RegBlocks.POLISHED_RHYOLITE_SLAB,
                RegBlocks.POLISHED_RHYOLITE_STAIR,
                RegBlocks.POLISHED_RHYOLITE_WALL,
                "decorative"
        );
        blockSet(
                RegBlocks.RHYOLITE_BRICKS,
                RegBlocks.RHYOLITE_BRICKS_SLAB,
                RegBlocks.RHYOLITE_BRICKS_STAIR,
                RegBlocks.RHYOLITE_BRICKS_WALL,
                "decorative"
        );

        clusterBlock(RegBlocks.CELESTITE_CLUSTER, "ore");
        clusterBlock(RegBlocks.LARGE_CELESTITE_BUD, "ore");
        clusterBlock(RegBlocks.MEDIUM_CELESTITE_BUD, "ore");
        clusterBlock(RegBlocks.SMALL_CELESTITE_BUD, "ore");
        ore(RegBlocks.BUDDING_CELESTITE);
        storage(RegBlocks.CELESTITE_BLOCK);
        decorative(RegBlocks.CELESTITE_BRICKS);
        decorative(RegBlocks.POLISHED_CELESTITE);
        blockSet(
                RegBlocks.POLISHED_CELESTITE,
                RegBlocks.POLISHED_CELESTITE_SLAB,
                RegBlocks.POLISHED_CELESTITE_STAIR,
                RegBlocks.POLISHED_CELESTITE_WALL,
                "decorative"
        );
        blockSet(
                RegBlocks.CELESTITE_BRICKS,
                RegBlocks.CELESTITE_BRICKS_SLAB,
                RegBlocks.CELESTITE_BRICKS_STAIR,
                RegBlocks.CELESTITE_BRICKS_WALL,
                "decorative"
        );

        altBlock(RegBlocks.CHERT, "decorative", 1, 3);
        decorative(RegBlocks.CHERT_BRICKS);
        decorative(RegBlocks.POLISHED_CHERT);
        pillar(RegBlocks.CHERT_PILLAR);
        blockSet(
                RegBlocks.CHERT,
                RegBlocks.CHERT_SLAB,
                RegBlocks.CHERT_STAIR,
                RegBlocks.CHERT_WALL,
                "decorative"
        );
        blockSet(
                RegBlocks.POLISHED_CHERT,
                RegBlocks.POLISHED_CHERT_SLAB,
                RegBlocks.POLISHED_CHERT_STAIR,
                RegBlocks.POLISHED_CHERT_WALL,
                "decorative"
        );
        blockSet(
                RegBlocks.CHERT_BRICKS,
                RegBlocks.CHERT_BRICKS_SLAB,
                RegBlocks.CHERT_BRICKS_STAIR,
                RegBlocks.CHERT_BRICKS_WALL,
                "decorative"
        );

        decorative(RegBlocks.GOLD_TILES);
        pillar(RegBlocks.GOLD_PILLAR);
        simpleBlock(RegBlocks.UNKNOWN_ROOTS_BLOCK.get(), models().withExistingParent(name(RegBlocks.UNKNOWN_ROOTS_BLOCK), mcLoc("mangrove_roots"))
                .texture("side", modLoc("block/decorative/unknown_roots_block_side"))
                .texture("top", modLoc("block/decorative/unknown_roots_block_top"))
                .renderType("cutout"));
        simpleBlock(RegBlocks.RDX.get(), models().withExistingParent(name(RegBlocks.RDX), mcLoc("cube_bottom_top"))
                .texture("bottom", modLoc("block/functional/rdx_bottom"))
                .texture("side", modLoc("block/functional/rdx_side"))
                .texture("top", modLoc("block/functional/rdx_top")));

        barsBlock(RegBlocks.GOLD_BARS);

        decorative(RegBlocks.POLISHED_AMETHYST);
        decorative(RegBlocks.AMETHYST_BRICKS);
        blockSet(
                RegBlocks.POLISHED_AMETHYST,
                RegBlocks.POLISHED_AMETHYST_SLAB,
                RegBlocks.POLISHED_AMETHYST_STAIR,
                RegBlocks.POLISHED_AMETHYST_WALL,
                "decorative"
        );
        blockSet(
                RegBlocks.AMETHYST_BRICKS,
                RegBlocks.AMETHYST_BRICKS_SLAB,
                RegBlocks.AMETHYST_BRICKS_STAIR,
                RegBlocks.AMETHYST_BRICKS_WALL,
                "decorative"
        );


    }

    public void pillarDeco(String id, Supplier<Block> block) {
        if (block.get() instanceof RotatedPillarBlock log)
            this.axisBlock(log, suffix(this.blockTexture(block.get(), "compat/" + id), "_side"), suffix(this.blockTexture(block.get(), "compat/" + id), "_top"));
    }
}
