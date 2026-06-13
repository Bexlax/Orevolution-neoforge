package net.bexla.orevolution.datagen;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.init.RegBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class GENBlockTags extends BlockTagsProvider {
    public GENBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, provider, Orevolution.MODID, helper);
    }

    @Override
    public @NotNull String getName() {
        return "Orevolution Block Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.CROPS).add(RegBlocks.LIVINGSTONE_CROP.get()).add(RegBlocks.VERDITE_CROP.get());

        tag(BlockTags.WALLS).add(RegBlocks.POLISHED_AETHERROCK_WALL.get()).add(RegBlocks.POLISHED_LIMESTONE_WALL.get());
        tag(BlockTags.DOORS).add(RegBlocks.STEEL_DOOR.get());
        tag(BlockTags.TRAPDOORS).add(RegBlocks.STEEL_TRAPDOOR.get());

        tag(OrevolutionTags.Blocks.tinStorages).add(RegBlocks.TIN_BLOCK.get());
        tag(OrevolutionTags.Blocks.platStorages).add(RegBlocks.PLATINUM_BLOCK.get());
        tag(OrevolutionTags.Blocks.tungsStorages).add(RegBlocks.TUNGSTEN_BLOCK.get());
        tag(OrevolutionTags.Blocks.enderiteStorages).add(RegBlocks.AETHERSTEEL_BLOCK.get());
        tag(OrevolutionTags.Blocks.livingstoneStorages).add(RegBlocks.LIVINGSTONE_BLOCK.get());
        tag(OrevolutionTags.Blocks.verditeStorages).add(RegBlocks.VERDITE_BLOCK.get());

        tag(OrevolutionTags.Blocks.rawTinStorages).add(RegBlocks.RAW_TIN_BLOCK.get());
        tag(OrevolutionTags.Blocks.rawPlatStorages).add(RegBlocks.RAW_PLATINUM_BLOCK.get());
        tag(OrevolutionTags.Blocks.rawTungsStorages).add(RegBlocks.RAW_TUNGSTEN_BLOCK.get());

        tag(OrevolutionTags.Blocks.tuffs).add(Blocks.TUFF);
        tag(OrevolutionTags.Blocks.andesites).add(Blocks.ANDESITE);
        tag(OrevolutionTags.Blocks.diorites).add(Blocks.DIORITE);
        tag(OrevolutionTags.Blocks.granites).add(Blocks.GRANITE);
        tag(OrevolutionTags.Blocks.blackstones).add(Blocks.BLACKSTONE);

        tag(BlockTags.NEEDS_STONE_TOOL).addTag(OrevolutionTags.Blocks.tinOres).add(RegBlocks.LIVINGSTONE_CROP.get())
                .add(RegBlocks.AETHERROCK.get()).add(RegBlocks.POLISHED_AETHERROCK.get()).add(RegBlocks.AETHERROCK_BRICKS.get())
                .add(RegBlocks.CRACKED_AETHERROCK_BRICKS.get()).add(RegBlocks.BRONZE_BLOCK.get()).add(RegBlocks.BRONZE_TILES.get())
                .add(RegBlocks.TIN_TILES.get()).add(RegBlocks.TIN_BRICKS.get()).add(RegBlocks.RAW_TIN_BLOCK.get()).add(RegBlocks.TIN_BLOCK.get());

        tag(BlockTags.NEEDS_IRON_TOOL).addTag(OrevolutionTags.Blocks.platOres).add(RegBlocks.VERDITE_CROP.get())
                .add(RegBlocks.STEEL_TRAPDOOR.get()).add(RegBlocks.STEEL_BLOCK.get()).add(RegBlocks.STEEL_DOOR.get()).add(RegBlocks.STEEL_PILLAR.get())
                .add(RegBlocks.PLATINUM_TILES.get()).add(RegBlocks.RAW_PLATINUM_BLOCK.get()).add(RegBlocks.PLATINUM_BLOCK.get()).add(RegBlocks.STEEL_ANVIL.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL).addTag((OrevolutionTags.Blocks.tungsOres)).add(RegBlocks.TUNGSTEN_BLOCK.get()).add(RegBlocks.RAW_TUNGSTEN_BLOCK.get())
                .add(RegBlocks.POLISHED_TUNGSTEN.get()).add(RegBlocks.CHISELED_TUNGSTEN_BLOCK.get()).add(RegBlocks.CUT_TUNGSTEN_BLOCK.get());

        tag(OrevolutionTags.Blocks.tinOres).add(RegBlocks.TIN_ORE.get()).add(RegBlocks.DEEPSLATE_TIN_ORE.get());
        tag(OrevolutionTags.Blocks.tungsOres).add(RegBlocks.NETHER_TUNGSTEN_ORE.get());
        tag(OrevolutionTags.Blocks.platOres).add(RegBlocks.PLATINUM_ORE.get()).add(RegBlocks.DEEPSLATE_PLATINUM_ORE.get());
        tag(OrevolutionTags.Blocks.XPOres).add(RegBlocks.END_XP_ORE.get()).add(RegBlocks.NETHER_XP_ORE.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTags(OrevolutionTags.Blocks.tinOres, OrevolutionTags.Blocks.tungsOres, OrevolutionTags.Blocks.platOres, OrevolutionTags.Blocks.XPOres)
                .add(RegBlocks.PRIMITIVE_AETHERROCK.get()).add(RegBlocks.TIN_BLOCK.get()).add(RegBlocks.PLATINUM_BLOCK.get()).add(RegBlocks.TUNGSTEN_BLOCK.get())
                .add(RegBlocks.AETHERSTEEL_BLOCK.get()).add(RegBlocks.BRONZE_BLOCK.get()).add(RegBlocks.STEEL_BLOCK.get()).add(RegBlocks.STEEL_DOOR.get())
                .add(RegBlocks.LIVINGSTONE_BLOCK.get()).add(RegBlocks.VERDITE_BLOCK.get()).add(RegBlocks.RAW_TIN_BLOCK.get()).add(RegBlocks.RAW_PLATINUM_BLOCK.get())
                .add(RegBlocks.RAW_TUNGSTEN_BLOCK.get()).add(RegBlocks.AETHERROCK.get()).add(RegBlocks.POLISHED_AETHERROCK.get()).add(RegBlocks.POLISHED_TUNGSTEN.get())
                .add(RegBlocks.AETHERROCK_BRICKS.get()).add(RegBlocks.CRACKED_AETHERROCK_BRICKS.get()).add(RegBlocks.AETHERROCK_TILES.get()).add(RegBlocks.BRONZE_TILES.get())
                .add(RegBlocks.CUT_TUNGSTEN_BLOCK.get()).add(RegBlocks.CUT_STEEL_BLOCK.get()).add(RegBlocks.STEEL_ANVIL.get()).add(RegBlocks.STEEL_PILLAR.get()).add(RegBlocks.POLISHED_AETHERROCK_STAIR.get())
                .add(RegBlocks.POLISHED_AETHERROCK_WALL.get()).add(RegBlocks.POLISHED_AETHERROCK_SLAB.get()).add(RegBlocks.VERDITE_CROP.get()).add(RegBlocks.LIVINGSTONE_CROP.get())
                .add(RegBlocks.PLATINUM_PILLAR.get()).add(RegBlocks.PLATINUM_BARS.get()).add(RegBlocks.PLATINUM_TILES.get()).add(RegBlocks.GOLD_BARS.get()).add(RegBlocks.GOLD_TILES.get())
                .add(RegBlocks.GOLD_PILLAR.get()).add(RegBlocks.TUNGSTEN_BARS.get()).add(RegBlocks.STEEL_BARS.get()).add(RegBlocks.TIN_BARS.get()).add(RegBlocks.BRONZE_BARS.get())
                .add(RegBlocks.TUNGSTEN_BRICKS.get());

        tag(OrevolutionTags.Blocks.uncommonDuplicateChance).addTags(
                        BlockTags.NEEDS_STONE_TOOL,
                        BlockTags.NEEDS_IRON_TOOL,
                        Tags.Blocks.GRAVELS
                ).add(Blocks.CRAFTING_TABLE).add(Blocks.FURNACE).add(Blocks.SMITHING_TABLE).add(Blocks.BLAST_FURNACE).add(Blocks.SMOKER)
                .add(Blocks.STONECUTTER).add(Blocks.BOOKSHELF);
        tag(OrevolutionTags.Blocks.rareDuplicateChance).addTags(
                Tags.Blocks.STORAGE_BLOCKS,
                BlockTags.ANVIL,
                BlockTags.CROPS
        ).add(Blocks.ENCHANTING_TABLE).add(Blocks.ENDER_CHEST);
        tag(OrevolutionTags.Blocks.alwaysDuplicateChance).addTags(
                Tags.Blocks.OBSIDIANS,
                OrevolutionTags.Blocks.tuffs,
                OrevolutionTags.Blocks.andesites,
                OrevolutionTags.Blocks.diorites,
                OrevolutionTags.Blocks.granites,
                OrevolutionTags.Blocks.blackstones,
                BlockTags.LEAVES
        );
        tag(OrevolutionTags.Blocks.neverDuplicateChance).addTags(
                Tags.Blocks.CHESTS
        );

        tag(BlockTags.ANVIL).add(RegBlocks.STEEL_ANVIL.get());

        tag(BlockTags.INCORRECT_FOR_STONE_TOOL).add(Blocks.IRON_BLOCK).add(Blocks.IRON_ORE).add(Blocks.DEEPSLATE_IRON_ORE).add(Blocks.RAW_IRON_BLOCK)
                .add(Blocks.IRON_BARS).add(Blocks.IRON_DOOR).add(Blocks.IRON_TRAPDOOR).add(Blocks.HOPPER)
                .addTag(BlockTags.ANVIL).addTag(OrevolutionTags.Blocks.incorrectForTin);

        tag(OrevolutionTags.Blocks.incorrectForTin).add(RegBlocks.VERDITE_BLOCK.get()).add(RegBlocks.PLATINUM_ORE.get()).add(RegBlocks.DEEPSLATE_PLATINUM_ORE.get()).
                add(RegBlocks.RAW_PLATINUM_BLOCK.get()).add(RegBlocks.NETHER_XP_ORE.get()).add(RegBlocks.STEEL_BLOCK.get()).add(RegBlocks.PLATINUM_BLOCK.get()).
                addTag(BlockTags.INCORRECT_FOR_IRON_TOOL);

        tag(BlockTags.INCORRECT_FOR_IRON_TOOL).add(Blocks.DIAMOND_BLOCK).add(Blocks.DIAMOND_ORE).add(Blocks.DEEPSLATE_DIAMOND_ORE)
                .add(Blocks.REDSTONE_BLOCK).add(Blocks.REDSTONE_ORE).add(Blocks.DEEPSLATE_REDSTONE_ORE).addTag(OrevolutionTags.Blocks.incorrectForPlatinum);

        tag(OrevolutionTags.Blocks.incorrectForPlatinum).add(RegBlocks.NETHER_TUNGSTEN_ORE.get()).add(RegBlocks.RAW_TUNGSTEN_BLOCK.get()).add(RegBlocks.END_XP_ORE.get())
                .add(RegBlocks.TUNGSTEN_BLOCK.get()).addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);

        tag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL).add(RegBlocks.PRIMITIVE_AETHERROCK.get()).add(RegBlocks.AETHERSTEEL_BLOCK.get());

        tag(Tags.Blocks.STORAGE_BLOCKS).addTags(
                OrevolutionTags.Blocks.tinStorages,
                OrevolutionTags.Blocks.platStorages,
                OrevolutionTags.Blocks.tungsStorages,
                OrevolutionTags.Blocks.verditeStorages,
                OrevolutionTags.Blocks.enderiteStorages,
                OrevolutionTags.Blocks.livingstoneStorages,
                OrevolutionTags.Blocks.rawPlatStorages,
                OrevolutionTags.Blocks.rawTinStorages,
                OrevolutionTags.Blocks.rawTungsStorages
        );

        tag(Tags.Blocks.ORES).addTags(
                OrevolutionTags.Blocks.tinOres,
                OrevolutionTags.Blocks.platOres,
                OrevolutionTags.Blocks.tungsOres,
                OrevolutionTags.Blocks.XPOres
        ).add(RegBlocks.PRIMITIVE_AETHERROCK.get());
    }
}
