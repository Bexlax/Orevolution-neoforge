package net.bexla.orevolution.datagen;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.init.RegBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
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

        tag(BlockTags.GUARDED_BY_PIGLINS).add(RegBlocks.TUNGSTEN_BLOCK.get());

        tag(OrevolutionTags.Blocks.CRATER_PLACEMENT_BLACKLIST);

        tag(OrevolutionTags.Blocks.LARGE_PILLAR_REPLACEMENT)
                .addTags(Tags.Blocks.ORES, Tags.Blocks.STONES)
                .add(
                        Blocks.GRANITE,
                        Blocks.ANDESITE,
                        Blocks.DIORITE,
                        Blocks.TUFF,
                        Blocks.BLACKSTONE,
                        Blocks.DEEPSLATE,
                        Blocks.STONE,
                        RegBlocks.RHYOLITE.get(),
                        RegBlocks.CHERT.get()
                );

        tag(BlockTags.CLIMBABLE)
                .add(RegBlocks.VINNELIO.get(), RegBlocks.VINNELIO_PLANT.get());

        tag(BlockTags.WALLS)
                .add(
                        RegBlocks.POLISHED_AETHERROCK_WALL.get(),
                        RegBlocks.POLISHED_CHERT_WALL.get(),
                        RegBlocks.CELESTITE_BRICKS_WALL.get(),
                        RegBlocks.POLISHED_CELESTITE_WALL.get(),
                        RegBlocks.AMETHYST_BRICKS_WALL.get(),
                        RegBlocks.POLISHED_AMETHYST_WALL.get()
                );
        tag(BlockTags.DOORS).add(RegBlocks.STEEL_DOOR.get());
        tag(BlockTags.TRAPDOORS).add(RegBlocks.STEEL_TRAPDOOR.get());

        tag(OrevolutionTags.Blocks.TIN_BLOCKS).add(RegBlocks.TIN_BLOCK.get());
        tag(OrevolutionTags.Blocks.CASSITERITE_BLOCKS).add(RegBlocks.CASSITERITE_BLOCK.get());
        tag(OrevolutionTags.Blocks.PLATINUM_BLOCKS).add(RegBlocks.PLATINUM_BLOCK.get());
        tag(OrevolutionTags.Blocks.TUNGSTEN_BLOCKS).add(RegBlocks.TUNGSTEN_BLOCK.get());
        tag(OrevolutionTags.Blocks.ENDERITE_ADJACENT_BLOCKS).add(RegBlocks.AETHERSTEEL_BLOCK.get());
        tag(OrevolutionTags.Blocks.LIVINGSTONE_BLOCKS).add(RegBlocks.LIVINGSTONE_BLOCK.get());
        tag(OrevolutionTags.Blocks.VERDITE_BLOCKS).add(RegBlocks.VERDITE_BLOCK.get());

        tag(OrevolutionTags.Blocks.RAW_TIN_BLOCKS).add(RegBlocks.RAW_TIN_BLOCK.get());
        tag(OrevolutionTags.Blocks.RAW_CASSITERITE_BLOCKS).add(RegBlocks.RAW_CASSITERITE_BLOCK.get());
        tag(OrevolutionTags.Blocks.RAW_PLATINUM_BLOCKS).add(RegBlocks.RAW_PLATINUM_BLOCK.get());
        tag(OrevolutionTags.Blocks.RAW_TUNGSTEN_BLOCKS).add(RegBlocks.RAW_TUNGSTEN_BLOCK.get());

        tag(OrevolutionTags.Blocks.TUFFS).add(Blocks.TUFF);
        tag(OrevolutionTags.Blocks.ANDESITES).add(Blocks.ANDESITE);
        tag(OrevolutionTags.Blocks.DIORITES).add(Blocks.DIORITE);
        tag(OrevolutionTags.Blocks.GRANITES).add(Blocks.GRANITE);
        tag(OrevolutionTags.Blocks.BLACKSTONES).add(Blocks.BLACKSTONE);
        tag(OrevolutionTags.Blocks.BASALTS).add(Blocks.BASALT);

        tag(BlockTags.BEACON_BASE_BLOCKS).add(
                RegBlocks.AETHERSTEEL_BLOCK.get(),
                RegBlocks.STEEL_BLOCK.get(),
                RegBlocks.BRONZE_BLOCK.get(),
                RegBlocks.TIN_BLOCK.get(),
                RegBlocks.PLATINUM_BLOCK.get(),
                RegBlocks.TUNGSTEN_BLOCK.get(),
                RegBlocks.VERDITE_BLOCK.get(),
                RegBlocks.MOONSTONE.get()
        );

        tag(OrevolutionTags.Blocks.TIN_ORES).add(RegBlocks.TIN_ORE.get(), RegBlocks.DEEPSLATE_TIN_ORE.get());
        tag(OrevolutionTags.Blocks.CASSITERITE_ORES).add(RegBlocks.CASSITERITE_ORE.get(), RegBlocks.DEEPSLATE_CASSITERITE_ORE.get(), RegBlocks.NETHER_CASSITERITE_ORE.get());
        tag(OrevolutionTags.Blocks.TUNGSTEN_ORES).add(RegBlocks.NETHER_TUNGSTEN_ORE.get());
        tag(OrevolutionTags.Blocks.PLATINUM_ORES).add(RegBlocks.PLATINUM_ORE.get(), RegBlocks.DEEPSLATE_PLATINUM_ORE.get());
        tag(OrevolutionTags.Blocks.XP_ORES).add(RegBlocks.END_XP_ORE.get(), RegBlocks.NETHER_XP_ORE.get());
        tag(OrevolutionTags.Blocks.MOONSTONE_ORES).add(RegBlocks.MOONSTONE.get(), RegBlocks.RHYOLITE_ENCRUSTED_MOONSTONE.get(), RegBlocks.BASALT_ENCRUSTED_MOONSTONE.get());

        tag(BlockTags.EMERALD_ORES).add(RegBlocks.RHYOLITE_EMERALD_ORE.get(), RegBlocks.RHYOLITE_EMERALD_ORE_CLUMP.get());


        List<DeferredHolder<Block, ? extends Block>> SKIP = List.of(
                RegBlocks.RDX,
                RegBlocks.CORELIO,
                RegBlocks.LARGE_CORELIO,
                RegBlocks.UNKNOWN_ROOTS,
                RegBlocks.UNKNOWN_ROOTS_BLOCK,
                RegBlocks.VINNELIO,
                RegBlocks.VINNELIO_PLANT,
                RegBlocks.VERDITE_CROP,
                RegBlocks.LIVINGSTONE_CROP
        );

        List<ResourceKey<Block>> blocks = new ArrayList<>();

        for (DeferredHolder<Block, ? extends Block> block : RegBlocks.HELPER.getDeferredRegister().getEntries()) {
            if (!SKIP.contains(block))
                blocks.add(block.getKey());
        }

        tag(BlockTags.MINEABLE_WITH_PICKAXE).addAll(blocks);


        tag(OrevolutionTags.Blocks.UNCOMMON_DUPLICATE_CHANCE)
                .addTags(
                        BlockTags.NEEDS_STONE_TOOL,
                        BlockTags.NEEDS_IRON_TOOL,
                        Tags.Blocks.GRAVELS
                )
                .add(
                        RegBlocks.QUARTZOLITE.get(),
                        Blocks.CRAFTING_TABLE,
                        Blocks.FURNACE,
                        Blocks.SMITHING_TABLE,
                        Blocks.BLAST_FURNACE,
                        Blocks.SMOKER,
                        Blocks.STONECUTTER,
                        Blocks.BOOKSHELF,
                        Blocks.GRINDSTONE,
                        Blocks.LOOM,
                        Blocks.CARTOGRAPHY_TABLE,
                        Blocks.FLETCHING_TABLE,
                        Blocks.COMPOSTER,
                        Blocks.LECTERN,
                        Blocks.CAULDRON,
                        Blocks.DECORATED_POT,
                        Blocks.CRAFTER
                );
        tag(OrevolutionTags.Blocks.RARE_DUPLICATE_CHANCE)
                .addTags(
                        Tags.Blocks.STORAGE_BLOCKS,
                        BlockTags.ANVIL,
                        BlockTags.CROPS
                )
                .add(
                        RegBlocks.STEEL_ANVIL.get(),
                        Blocks.ENCHANTING_TABLE,
                        Blocks.BREWING_STAND,
                        Blocks.LODESTONE,
                        Blocks.HOPPER,
                        Blocks.DROPPER,
                        Blocks.DISPENSER
                );

        IntrinsicTagAppender<Block> add = tag(OrevolutionTags.Blocks.DOUBLE_DUPLICATE_CHANCE)
                .addTags(
                        OrevolutionTags.Blocks.ANDESITES,
                        OrevolutionTags.Blocks.GRANITES,
                        OrevolutionTags.Blocks.DIORITES,
                        OrevolutionTags.Blocks.TUFFS,
                        OrevolutionTags.Blocks.BLACKSTONES,
                        OrevolutionTags.Blocks.BASALTS,
                        BlockTags.STAIRS,
                        BlockTags.SLABS,
                        BlockTags.WALLS,
                        BlockTags.ALL_SIGNS,
                        BlockTags.ALL_HANGING_SIGNS,
                        BlockTags.BUTTONS,
                        BlockTags.PRESSURE_PLATES,
                        BlockTags.DOORS,
                        BlockTags.BANNERS,
                        BlockTags.BEDS,
                        Tags.Blocks.GLAZED_TERRACOTTAS,
                        BlockTags.WOOL_CARPETS,
                        BlockTags.CONCRETE_POWDER,
                        Tags.Blocks.CONCRETES,
                        BlockTags.CANDLES,
                        Tags.Blocks.SANDS,
                        Tags.Blocks.CHAINS,
                        BlockTags.FLOWERS,
                        BlockTags.TRAPDOORS,
                        BlockTags.FENCES,
                        BlockTags.FENCE_GATES,
                        BlockTags.REPLACEABLE,
                        BlockTags.TERRACOTTA,
                        Tags.Blocks.GLASS_BLOCKS,
                        Tags.Blocks.OBSIDIANS,
                        BlockTags.LEAVES,
                        BlockTags.SNOW,
                        BlockTags.ICE
                )
                .add(
                        Blocks.VINE,
                        Blocks.GLOW_LICHEN,
                        Blocks.MOSS_BLOCK,
                        Blocks.MOSS_CARPET,
                        Blocks.SPORE_BLOSSOM
                );

        tag(OrevolutionTags.Blocks.NEVER_DUPLICATE_CHANCE)
                .addTags(
                        Tags.Blocks.CHESTS,
                        BlockTags.SHULKER_BOXES,
                        Tags.Blocks.BARRELS
                )
                .add(
                        RegBlocks.AETHERSTEEL_BLOCK.get(),
                        Blocks.BEACON,
                        Blocks.CONDUIT,
                        Blocks.RESPAWN_ANCHOR
                );

        tag(BlockTags.STONE_ORE_REPLACEABLES).add(RegBlocks.CHERT.get());

        tag(BlockTags.DEEPSLATE_ORE_REPLACEABLES).add(RegBlocks.RHYOLITE.get());

        tag(Tags.Blocks.STONES).add(RegBlocks.CHERT.get(), RegBlocks.RHYOLITE.get());

        tag(BlockTags.NEEDS_STONE_TOOL).addTag(OrevolutionTags.Blocks.TIN_ORES)
                .add(
                        RegBlocks.LIVINGSTONE_CROP.get(),
                        RegBlocks.BRONZE_BLOCK.get(),
                        RegBlocks.BRONZE_TILES.get(),
                        RegBlocks.TIN_TILES.get(),
                        RegBlocks.TIN_BRICKS.get(),
                        RegBlocks.TIN_BARS.get(),
                        RegBlocks.BRONZE_BARS.get(),
                        RegBlocks.PLATINUM_BARS.get(),
                        RegBlocks.GOLD_BARS.get(),
                        RegBlocks.RAW_TIN_BLOCK.get(),
                        RegBlocks.TIN_BLOCK.get()
                );

        tag(OrevolutionTags.Blocks.NEEDS_TIN_TOOL).addTag(BlockTags.IRON_ORES)
                .add(
                        RegBlocks.STEEL_ANVIL.get(),
                        Blocks.IRON_BLOCK,
                        Blocks.RAW_IRON_BLOCK,
                        Blocks.IRON_BARS,
                        Blocks.IRON_DOOR,
                        Blocks.IRON_TRAPDOOR,
                        Blocks.ANVIL,
                        Blocks.IRON_DOOR,
                        Blocks.IRON_TRAPDOOR,
                        Blocks.HOPPER,
                        RegBlocks.RHYOLITE_EMERALD_ORE.get(),
                        RegBlocks.RHYOLITE_EMERALD_ORE_CLUMP.get(),
                        RegBlocks.EMERALD_CLUSTER.get(),
                        RegBlocks.PRISMARINE_CLUSTER.get(),
                        RegBlocks.AETHERROCK.get(),
                        RegBlocks.POLISHED_AETHERROCK.get(),
                        RegBlocks.AETHERROCK_BRICKS.get(),
                        RegBlocks.CRACKED_AETHERROCK_BRICKS.get()
                );

        tag(BlockTags.NEEDS_IRON_TOOL).addTag(OrevolutionTags.Blocks.PLATINUM_ORES)
                .add(
                        RegBlocks.VERDITE_CROP.get(),
                        RegBlocks.STEEL_TRAPDOOR.get(),
                        RegBlocks.STEEL_BLOCK.get(),
                        RegBlocks.STEEL_DOOR.get(),
                        RegBlocks.STEEL_PILLAR.get(),
                        RegBlocks.PLATINUM_TILES.get(),
                        RegBlocks.RAW_PLATINUM_BLOCK.get(),
                        RegBlocks.PLATINUM_BLOCK.get(),
                        RegBlocks.STEEL_ANVIL.get(),
                        RegBlocks.VERDITE_BLOCK.get(),
                        RegBlocks.NETHER_XP_ORE.get(),
                        RegBlocks.CASSITERITE_BLOCK.get(),
                        RegBlocks.CASSITERITE_ORE.get(),
                        RegBlocks.DEEPSLATE_CASSITERITE_ORE.get(),
                        RegBlocks.NETHER_CASSITERITE_ORE.get(),
                        RegBlocks.RAW_CASSITERITE_BLOCK.get()
                );

        tag(OrevolutionTags.Blocks.NEEDS_PLATINUM_TOOL).addTags(BlockTags.DIAMOND_ORES, BlockTags.LAPIS_ORES)
                .add(
                        Blocks.DIAMOND_BLOCK,
                        Blocks.REDSTONE_BLOCK
                );

        tag(BlockTags.NEEDS_DIAMOND_TOOL).addTag((OrevolutionTags.Blocks.TUNGSTEN_ORES))
                .add(
                        RegBlocks.TUNGSTEN_BLOCK.get(),
                        RegBlocks.RAW_TUNGSTEN_BLOCK.get(),
                        RegBlocks.POLISHED_TUNGSTEN.get(),
                        RegBlocks.CHISELED_TUNGSTEN_BLOCK.get(),
                        RegBlocks.CUT_TUNGSTEN_BLOCK.get(),
                        RegBlocks.MOONSTONE.get(),
                        RegBlocks.RHYOLITE_ENCRUSTED_MOONSTONE.get(),
                        RegBlocks.END_XP_ORE.get()
                );

        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(
                        RegBlocks.PRIMITIVE_AETHERROCK.get(),
                        RegBlocks.AETHERSTEEL_BLOCK.get()
                );

        tag(OrevolutionTags.Blocks.NEEDS_AETHERSTEEL_TOOL);

        tag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .addTags(
                        BlockTags.ANVIL,
                        OrevolutionTags.Blocks.NEEDS_TIN_TOOL,
                        OrevolutionTags.Blocks.NEEDS_PLATINUM_TOOL,
                        OrevolutionTags.Blocks.INCORRECT_FOR_TIN_TOOL
                );

        tag(OrevolutionTags.Blocks.INCORRECT_FOR_TIN_ALT)
                .addTags(
                        BlockTags.IRON_ORES,
                        BlockTags.NEEDS_IRON_TOOL,
                        BlockTags.INCORRECT_FOR_IRON_TOOL,
                        OrevolutionTags.Blocks.INCORRECT_FOR_TIN_TOOL
                );

        tag(OrevolutionTags.Blocks.INCORRECT_FOR_TIN_TOOL)
                .addTags(
                        BlockTags.NEEDS_IRON_TOOL,
                        BlockTags.INCORRECT_FOR_IRON_TOOL
                );

        tag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .addTags(
                        OrevolutionTags.Blocks.NEEDS_PLATINUM_TOOL,
                        OrevolutionTags.Blocks.INCORRECT_FOR_PLATINUM_TOOL
                );

        tag(OrevolutionTags.Blocks.INCORRECT_FOR_MOONSTONE_TOOL)
                .addTags(
                        OrevolutionTags.Blocks.NEEDS_PLATINUM_TOOL,
                        BlockTags.INCORRECT_FOR_DIAMOND_TOOL
                );

        tag(OrevolutionTags.Blocks.INCORRECT_FOR_PLATINUM_TOOL)
                .addTags(
                        BlockTags.INCORRECT_FOR_DIAMOND_TOOL
                );

        tag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .add(
                        RegBlocks.PRIMITIVE_AETHERROCK.get(),
                        RegBlocks.AETHERSTEEL_BLOCK.get()
                );

        tag(OrevolutionTags.Blocks.AUTOSMELT).addTags(
                Tags.Blocks.ORES,
                Tags.Blocks.SANDS,
                BlockTags.LOGS_THAT_BURN,
                BlockTags.CROPS
        );

        tag(Tags.Blocks.STORAGE_BLOCKS).addTags(
                OrevolutionTags.Blocks.TIN_BLOCKS,
                OrevolutionTags.Blocks.CASSITERITE_BLOCKS,
                OrevolutionTags.Blocks.PLATINUM_BLOCKS,
                OrevolutionTags.Blocks.TUNGSTEN_BLOCKS,
                OrevolutionTags.Blocks.VERDITE_BLOCKS,
                OrevolutionTags.Blocks.ENDERITE_ADJACENT_BLOCKS,
                OrevolutionTags.Blocks.LIVINGSTONE_BLOCKS,
                OrevolutionTags.Blocks.RAW_PLATINUM_BLOCKS,
                OrevolutionTags.Blocks.RAW_TIN_BLOCKS,
                OrevolutionTags.Blocks.RAW_CASSITERITE_BLOCKS,
                OrevolutionTags.Blocks.RAW_TUNGSTEN_BLOCKS
        ).add(RegBlocks.PYRITE_BLOCK.get());

        tag(OrevolutionTags.Blocks.PLANT_ORES).add(
                RegBlocks.LIVINGSTONE_CROP.get(),
                RegBlocks.VERDITE_CROP.get()
        );

        tag(Tags.Blocks.ORES).addTags(
                OrevolutionTags.Blocks.TIN_ORES,
                OrevolutionTags.Blocks.CASSITERITE_ORES,
                OrevolutionTags.Blocks.PLATINUM_ORES,
                OrevolutionTags.Blocks.TUNGSTEN_ORES,
                OrevolutionTags.Blocks.XP_ORES,
                OrevolutionTags.Blocks.PLANT_ORES,
                OrevolutionTags.Blocks.MOONSTONE_ORES
        ).add(RegBlocks.PRIMITIVE_AETHERROCK.get());
    }
}
