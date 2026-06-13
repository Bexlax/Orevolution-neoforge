package net.bexla.orevolution.init;

import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.types.block.*;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class RegBlocks {
    public static final BlockSubRegistryHelper HELPER = Orevolution.REGISTRY_HELPER.getBlockSubHelper();

    public static <T extends Block> DeferredBlock<T> baseRegister(String name, Supplier<? extends T> block, Function<DeferredBlock<T>, Supplier<? extends Item>> item) {
        DeferredBlock<T> register = HELPER.createBlockNoItem(name, block);
        RegItems.HELPER.createItem(name, item.apply(register));
        return register;
    }

    public static <B extends Block> DeferredBlock<B> reg(String name, Supplier<? extends Block> block) {
        return (DeferredBlock<B>) baseRegister(name, block, RegBlocks::registerBlockItem);
    }

    private static <T extends Block> Supplier<BlockItem> registerBlockItem(final DeferredBlock<T> block) {
        return () -> new BlockItem(Objects.requireNonNull(block.get()), new Item.Properties());
    }

    public static VoxelShape cropSize(double width, double height, double lenght) {
        return Shapes.box(0.0D / 16.0D, 0.0D / 16.0D, 0.0D / 16.0D, width / 16.0D, height / 16.0D, lenght / 16.0D);
    }

    public static VoxelShape cropHeight(double height) {
        return cropSize(16.0D, height, 16.0D);
    }

    //~//~~ Ores ~~//~//
    public static final DeferredBlock<Block> TIN_ORE = reg("tin_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
    public static final DeferredBlock<Block> PLATINUM_ORE = reg("platinum_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_ORE)));
    public static final DeferredBlock<Block> NETHER_TUNGSTEN_ORE = reg("nether_tungsten_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_QUARTZ_ORE)));
    public static final DeferredBlock<Block> NETHER_XP_ORE = reg("nether_experience_ore", () -> new DropExperienceBlock(UniformInt.of(4, 12), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_QUARTZ_ORE)));
    public static final DeferredBlock<Block> END_XP_ORE = reg("end_experience_ore", () -> new DropExperienceBlock(UniformInt.of(12, 25), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_QUARTZ_ORE).sound(SoundType.STONE).strength(5.0F, 4.0F)));
    public static final DeferredBlock<Block> DEEPSLATE_TIN_ORE = reg("deepslate_tin_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)));
    public static final DeferredBlock<Block> DEEPSLATE_PLATINUM_ORE = reg("deepslate_platinum_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_GOLD_ORE)));
    public static final DeferredBlock<Block> PRIMITIVE_AETHERROCK = reg("primitive_aetherrock", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.ANCIENT_DEBRIS).hasPostProcess((blkState, reader, pos) -> true).emissiveRendering((blkState, reader, pos) -> true).lightLevel((p_152684_) -> 8).strength(40F, 2200F)));

    public static final DeferredBlock<Block> LIVINGSTONE_CROP = HELPER.createBlockNoItem("livingstone_crop", () -> new LivingstoneCrop(RegItems.PETRIFIED_SEED,
            new VoxelShape[]{
                    cropHeight(2.0D),
                    cropHeight(5.0D),
                    cropHeight(8.0D),
                    cropHeight(13.0D),
                    cropHeight(16.0D)
            }, BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> VERDITE_CROP = HELPER.createBlockNoItem("verdite_crop", () -> new VerditeCrop(RegItems.DEAD_SEED,
            new VoxelShape[]{
                    cropHeight(2.0D),
                    cropHeight(4.0D),
                    cropHeight(6.0D),
                    cropHeight(9.0D),
                    cropHeight(12.0D),
                    cropHeight(13.0D),
                    cropHeight(14.0D)
            }, BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).requiresCorrectToolForDrops()));

    //~//~~ Storage Blocks ~~//~//
    /* Ore Blocks */
    public static final DeferredBlock<Block> TIN_BLOCK = reg("tin_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK)));
    public static final DeferredBlock<Block> PLATINUM_BLOCK = reg("platinum_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(4.5F)));
    public static final DeferredBlock<Block> TUNGSTEN_BLOCK = reg("tungsten_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));
    public static final DeferredBlock<Block> AETHERSTEEL_BLOCK = reg("aethersteel_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).strength(135F, 60F)));
    public static final DeferredBlock<Block> BRONZE_BLOCK = reg("bronze_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK)));
    public static final DeferredBlock<Block> STEEL_BLOCK = reg("steel_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));
    public static final DeferredBlock<Block> LIVINGSTONE_BLOCK = reg("livingstone_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK)));
    public static final DeferredBlock<Block> VERDITE_BLOCK = reg("verdite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    //    public static final DeferredBlock<Block> AMBER_BLOCK = register("amber_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));
    /* Raw Ore Blocks */
    public static final DeferredBlock<Block> RAW_TIN_BLOCK = reg("raw_tin_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK)));
    public static final DeferredBlock<Block> RAW_PLATINUM_BLOCK = reg("raw_platinum_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(4.5F)));
    public static final DeferredBlock<Block> RAW_TUNGSTEN_BLOCK = reg("raw_tungsten_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));


    //~//~~ Decorative Blocks ~~//~//
    public static final DeferredBlock<Block> LIMESTONE = reg("limestone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE)));
    public static final DeferredBlock<Block> AETHERROCK = reg("aetherrock", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE).isValidSpawn((p_187421_, p_187422_, p_187423_, p_187424_) -> false).strength(10F, 30F).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> POLISHED_AETHERROCK = reg("polished_aetherrock", () -> new Block(BlockBehaviour.Properties.ofFullCopy(AETHERROCK.get())));
    public static final DeferredBlock<Block> POLISHED_TUNGSTEN = reg("polished_tungsten_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(TUNGSTEN_BLOCK.get())));
    public static final DeferredBlock<Block> POLISHED_LIMESTONE = reg("polished_limestone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE)));

    public static final DeferredBlock<Block> TUNGSTEN_BRICKS = reg("tungsten_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(TUNGSTEN_BLOCK.get())));
    public static final DeferredBlock<Block> AETHERROCK_BRICKS = reg("aetherrock_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(AETHERROCK.get())));
    public static final DeferredBlock<Block> TIN_BRICKS = reg("tin_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(TIN_BLOCK.get())));
    public static final DeferredBlock<Block> LIVINGSTONE_BRICKS = reg("livingstone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(LIVINGSTONE_BLOCK.get())));
    public static final DeferredBlock<Block> VERDITE_BRICKS = reg("verdite_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(VERDITE_BLOCK.get())));
    public static final DeferredBlock<Block> CRACKED_AETHERROCK_BRICKS = reg("cracked_aetherrock_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(AETHERROCK.get())));

    public static final DeferredBlock<Block> BRONZE_TILES = reg("bronze_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(BRONZE_BLOCK.get())));
    public static final DeferredBlock<Block> AETHERROCK_TILES = reg("aetherrock_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(AETHERROCK.get())));
    public static final DeferredBlock<Block> TIN_TILES = reg("tin_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(TIN_BLOCK.get())));
    public static final DeferredBlock<Block> PLATINUM_TILES = reg("platinum_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(PLATINUM_BLOCK.get())));
    public static final DeferredBlock<Block> GOLD_TILES = reg("gold_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK)));

    public static final DeferredBlock<Block> CUT_TUNGSTEN_BLOCK = reg("cut_tungsten_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(TUNGSTEN_BLOCK.get())));
    public static final DeferredBlock<Block> CUT_STEEL_BLOCK = reg("cut_steel_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(STEEL_BLOCK.get())));

    public static final DeferredBlock<Block> STEEL_PILLAR = reg("steel_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(STEEL_BLOCK.get())));
    public static final DeferredBlock<Block> PLATINUM_PILLAR = reg("platinum_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(PLATINUM_BLOCK.get())));
    public static final DeferredBlock<Block> GOLD_PILLAR = reg("gold_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK)));
    public static final DeferredBlock<Block> LIMESTONE_PILLAR = reg("limestone_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE)));

    public static final DeferredBlock<Block> CHISELED_TUNGSTEN_BLOCK = reg("chiseled_tungsten_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(TUNGSTEN_BLOCK.get())));
    public static final DeferredBlock<Block> CHISELED_TUNGSTEN_BRICKS = reg("chiseled_tungsten_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(TUNGSTEN_BLOCK.get())));

    public static final DeferredBlock<Block> POLISHED_AETHERROCK_STAIR = reg("polished_aetherrock_stair", () -> new StairBlock(POLISHED_AETHERROCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(POLISHED_AETHERROCK.get())));
    public static final DeferredBlock<Block> POLISHED_LIMESTONE_STAIR = reg("polished_limestone_stair", () -> new StairBlock(POLISHED_LIMESTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(POLISHED_LIMESTONE.get())));

    public static final DeferredBlock<Block> POLISHED_AETHERROCK_SLAB = reg("polished_aetherrock_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_AETHERROCK.get())));
    public static final DeferredBlock<Block> POLISHED_LIMESTONE_SLAB = reg("polished_limestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_LIMESTONE.get())));

    public static final DeferredBlock<Block> POLISHED_AETHERROCK_WALL = reg("polished_aetherrock_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_AETHERROCK.get())));
    public static final DeferredBlock<Block> POLISHED_LIMESTONE_WALL = reg("polished_limestone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_LIMESTONE.get())));

    public static final DeferredBlock<Block> STEEL_DOOR = reg("steel_door", () -> new DoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.ofFullCopy(STEEL_BLOCK.get()).noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> STEEL_BARS = reg("steel_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(STEEL_BLOCK.get()).noOcclusion()));
    public static final DeferredBlock<Block> BRONZE_BARS = reg("bronze_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(BRONZE_BLOCK.get()).noOcclusion()));
    public static final DeferredBlock<Block> TIN_BARS = reg("tin_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(TIN_BLOCK.get()).noOcclusion()));
    public static final DeferredBlock<Block> PLATINUM_BARS = reg("platinum_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(PLATINUM_BLOCK.get()).noOcclusion()));
    public static final DeferredBlock<Block> TUNGSTEN_BARS = reg("tungsten_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(TUNGSTEN_BLOCK.get()).noOcclusion()));
    public static final DeferredBlock<Block> GOLD_BARS = reg("gold_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).noOcclusion()));

    public static final DeferredBlock<Block> STEEL_TRAPDOOR = reg("steel_trapdoor", () -> new TrapDoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.ofFullCopy(STEEL_BLOCK.get()).noOcclusion().pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> STEEL_ANVIL = reg("steel_anvil", () -> new SteelAnvilBlock(BlockBehaviour.Properties.ofFullCopy(STEEL_BLOCK.get()).noOcclusion()));
    //public static final DeferredBlock<Block> TUNGSTEN_FURNACE = register("tungsten_furnace", () -> new SteelAnvilBlock(BlockBehaviour.Properties.ofFullCopy(TUNGSTEN_BLOCK.get())));

    public static final DeferredBlock<Block> TUNGSTEN_SPONGE = reg("tungsten_sponge", () -> new LavaSpongeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> HOT_TUNGSTEN_SPONGE = reg("hot_tungsten_sponge", () -> new HotLavaSponge(BlockBehaviour.Properties.ofFullCopy(TUNGSTEN_SPONGE.get()).lightLevel((p_152684_) -> 6)));
}