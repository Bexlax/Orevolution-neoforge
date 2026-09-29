package net.bexla.orevolution.init;

import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionUtils;
import net.bexla.orevolution.content.interfaces.IGlowAnimation;
import net.bexla.orevolution.content.types.block.*;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.fml.ModList;
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

    public static final DeferredBlock<Block> NETHER_XP_ORE = reg("nether_experience_ore", () -> new DropExperienceBlock(ModList.get().isLoaded("create") ? ConstantInt.of(0) : UniformInt.of(4, 12), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_QUARTZ_ORE)));
    public static final DeferredBlock<Block> END_XP_ORE = reg("end_experience_ore", () -> new DropExperienceBlock(ModList.get().isLoaded("create") ? ConstantInt.of(0) : UniformInt.of(12, 25), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_QUARTZ_ORE).sound(SoundType.STONE).strength(5.0F, 4.0F)));

    public static final DeferredBlock<Block> RHYOLITE_EMERALD_ORE = reg("rhyolite_emerald_ore", () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE)));
    public static final DeferredBlock<Block> RHYOLITE_EMERALD_ORE_CLUMP = reg("rhyolite_emerald_ore_clump", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE).strength(7.0F, 2.0F)));

    public static final DeferredBlock<Block> PYRITE_BLOCK = reg("pyrite_block", () -> new PyriteBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_GOLD_BLOCK).mapColor(MapColor.GOLD)));

    public static final DeferredBlock<Block> QUARTZOLITE = reg("quartzolite", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COAL_BLOCK).mapColor(MapColor.QUARTZ).strength(7.0F, 2.0F)));
    public static final DeferredBlock<Block> MOONSTONE = reg("moonstone", () -> new MoonstoneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).strength(9.5F, 12.0F).sound(OrevolutionUtils.MOONSTONE).lightLevel((b) -> 11)));
    public static final DeferredBlock<Block> RHYOLITE_ENCRUSTED_MOONSTONE = reg("rhyolite_encrusted_moonstone", () -> new EncrustedMoonstoneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).strength(9.6F, 12.0F).sound(OrevolutionUtils.MOONSTONE).lightLevel((b) -> 11)));
    public static final DeferredBlock<Block> BASALT_ENCRUSTED_MOONSTONE = reg("basalt_encrusted_moonstone", () -> new EncrustedMoonstoneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE).strength(9.6F, 12.0F).sound(OrevolutionUtils.MOONSTONE).lightLevel((b) -> 11)));

    // Tin
    public static final DeferredBlock<Block> TIN_ORE = reg("tin_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
    public static final DeferredBlock<Block> DEEPSLATE_TIN_ORE = reg("deepslate_tin_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)));

    public static final DeferredBlock<Block> TIN_BLOCK = reg("tin_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK)));
    public static final DeferredBlock<Block> RAW_TIN_BLOCK = reg("raw_tin_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK)));
    public static final DeferredBlock<Block> TIN_BRICKS = reg("tin_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(TIN_BLOCK.get())));
    public static final DeferredBlock<Block> TIN_TILES = reg("tin_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(TIN_BLOCK.get())));
    public static final DeferredBlock<Block> TIN_BARS = reg("tin_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(TIN_BLOCK.get()).noOcclusion()));

    static VoxelShape tin = SizableLanternBlock.createLanternShape(
            new double[]{5, 0, 5, 11, 5, 11},
            new double[]{6, 5, 6, 10, 6, 10}
    );
    static VoxelShape tin_hanging = SizableLanternBlock.createLanternShape(
            new double[]{5, 3, 5, 11, 8, 11},
            new double[]{6, 8, 6, 10, 10, 10}
    );

    public static final DeferredBlock<Block> TIN_LANTERN = reg("tin_lantern", () -> new SizableLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN),
            tin, tin_hanging
    ));
    public static final DeferredBlock<Block> TIN_SOUL_LANTERN = reg("tin_soul_lantern", () -> new SizableLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN),
            tin, tin_hanging
    ));

    // Cassiterite
    public static final DeferredBlock<Block> CASSITERITE_ORE = reg("cassiterite_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_ORE)));
    public static final DeferredBlock<Block> DEEPSLATE_CASSITERITE_ORE = reg("deepslate_cassiterite_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE)));
    public static final DeferredBlock<Block> NETHER_CASSITERITE_ORE = reg("nether_cassiterite_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_IRON_ORE).sound(SoundType.NETHER_ORE).mapColor(MapColor.NETHER)));

    public static final DeferredBlock<Block> CASSITERITE_BLOCK = reg("cassiterite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK)));
    public static final DeferredBlock<Block> RAW_CASSITERITE_BLOCK = reg("raw_cassiterite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK)));

    // Platnum
    public static final DeferredBlock<Block> PLATINUM_ORE = reg("platinum_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_ORE)));
    public static final DeferredBlock<Block> DEEPSLATE_PLATINUM_ORE = reg("deepslate_platinum_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_GOLD_ORE)));



    public static final DeferredBlock<Block> PLATINUM_BLOCK = reg("platinum_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(4.5F)));
    public static final DeferredBlock<Block> RAW_PLATINUM_BLOCK = reg("raw_platinum_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).strength(4.5F)));
    public static final DeferredBlock<Block> PLATINUM_TILES = reg("platinum_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(PLATINUM_BLOCK.get())));
    public static final DeferredBlock<Block> PLATINUM_PILLAR = reg("platinum_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(PLATINUM_BLOCK.get())));
    public static final DeferredBlock<Block> PLATINUM_BARS = reg("platinum_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(PLATINUM_BLOCK.get()).noOcclusion()));

    static VoxelShape platinum = SizableLanternBlock.createLanternShape(
            new double[]{4, 0, 4, 12, 8, 12},
            new double[]{5, 8, 5, 11, 10, 11}
    );
    static VoxelShape platinum_hanging = SizableLanternBlock.createLanternShape(
            new double[]{4, 1, 4, 12, 9, 12},
            new double[]{5, 9, 5, 11, 11, 11}
    );

    public static final DeferredBlock<Block> PLATINUM_LANTERN = reg("platinum_lantern", () -> new SizableLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN),
            platinum, platinum_hanging
    ));
    public static final DeferredBlock<Block> PLATINUM_SOUL_LANTERN = reg("platinum_soul_lantern", () -> new SizableLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN),
            platinum, platinum_hanging
    ));

    // Tungsten
    public static final DeferredBlock<Block> NETHER_TUNGSTEN_ORE = reg("nether_tungsten_ore", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_QUARTZ_ORE)));

    public static final DeferredBlock<Block> RAW_TUNGSTEN_BLOCK = reg("raw_tungsten_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));
    public static final DeferredBlock<Block> TUNGSTEN_BLOCK = reg("tungsten_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));
    public static final DeferredBlock<Block> POLISHED_TUNGSTEN = reg("polished_tungsten_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(TUNGSTEN_BLOCK.get())));
    public static final DeferredBlock<Block> TUNGSTEN_BRICKS = reg("tungsten_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(TUNGSTEN_BLOCK.get())));
    public static final DeferredBlock<Block> CUT_TUNGSTEN_BLOCK = reg("cut_tungsten_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(TUNGSTEN_BLOCK.get())));
    public static final DeferredBlock<Block> CHISELED_TUNGSTEN_BLOCK = reg("chiseled_tungsten_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(TUNGSTEN_BLOCK.get())));
    public static final DeferredBlock<Block> CHISELED_TUNGSTEN_BRICKS = reg("chiseled_tungsten_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(TUNGSTEN_BLOCK.get())));
    public static final DeferredBlock<Block> TUNGSTEN_BARS = reg("tungsten_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(TUNGSTEN_BLOCK.get()).noOcclusion()));

    public static final DeferredBlock<Block> DECAYING_TUNGSTEN_BLOCK = reg("decaying_tungsten_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));
    public static final DeferredBlock<Block> POLISHED_DECAYING_TUNGSTEN = reg("decaying_polished_tungsten_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(DECAYING_TUNGSTEN_BLOCK.get())));
    public static final DeferredBlock<Block> DECAYING_TUNGSTEN_BRICKS = reg("decaying_tungsten_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(DECAYING_TUNGSTEN_BLOCK.get())));
    public static final DeferredBlock<Block> CUT_DECAYING_TUNGSTEN_BLOCK = reg("decaying_cut_tungsten_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(DECAYING_TUNGSTEN_BLOCK.get())));
    public static final DeferredBlock<Block> CHISELED_DECAYING_TUNGSTEN_BLOCK = reg("decaying_chiseled_tungsten_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(DECAYING_TUNGSTEN_BLOCK.get())));
    public static final DeferredBlock<Block> CHISELED_DECAYING_TUNGSTEN_BRICKS = reg("decaying_chiseled_tungsten_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(DECAYING_TUNGSTEN_BLOCK.get())));
    public static final DeferredBlock<Block> DECAYING_TUNGSTEN_BARS = reg("decaying_tungsten_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(DECAYING_TUNGSTEN_BLOCK.get()).noOcclusion()));

    public static final DeferredBlock<Block> CORRODED_TUNGSTEN_BLOCK = reg("corroded_tungsten_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));
    public static final DeferredBlock<Block> POLISHED_CORRODED_TUNGSTEN = reg("corroded_polished_tungsten_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CORRODED_TUNGSTEN_BLOCK.get())));
    public static final DeferredBlock<Block> CORRODED_TUNGSTEN_BRICKS = reg("corroded_tungsten_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CORRODED_TUNGSTEN_BLOCK.get())));
    public static final DeferredBlock<Block> CUT_CORRODED_TUNGSTEN_BLOCK = reg("corroded_cut_tungsten_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CORRODED_TUNGSTEN_BLOCK.get())));
    public static final DeferredBlock<Block> CHISELED_CORRODED_TUNGSTEN_BLOCK = reg("corroded_chiseled_tungsten_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CORRODED_TUNGSTEN_BLOCK.get())));
    public static final DeferredBlock<Block> CHISELED_CORRODED_TUNGSTEN_BRICKS = reg("corroded_chiseled_tungsten_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CORRODED_TUNGSTEN_BLOCK.get())));
    public static final DeferredBlock<Block> CORRODED_TUNGSTEN_BARS = reg("corroded_tungsten_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(CORRODED_TUNGSTEN_BLOCK.get()).noOcclusion()));

    public static final DeferredBlock<Block> TUNGSTEN_SPONGE = reg("tungsten_sponge", () -> new LavaSpongeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPONGE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> HOT_TUNGSTEN_SPONGE = reg("hot_tungsten_sponge", () -> new HotLavaSponge(BlockBehaviour.Properties.ofFullCopy(TUNGSTEN_SPONGE.get()).lightLevel((p_152684_) -> 6)));

    // Aethersteel
    public static final DeferredBlock<Block> AETHERSTEEL_BLOCK = reg("aethersteel_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).strength(135F, 60F)));
    public static final DeferredBlock<Block> PRIMITIVE_AETHERROCK = reg("primitive_aetherrock", () -> new DropExperienceBlock(ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(Blocks.ANCIENT_DEBRIS).hasPostProcess((blkState, reader, pos) -> true).emissiveRendering((blkState, reader, pos) -> true).lightLevel((p_152684_) -> 8).strength(40F, 2200F)));

    // Bronze
    public static final DeferredBlock<Block> BRONZE_BLOCK = reg("bronze_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK)));
    public static final DeferredBlock<Block> BRONZE_TILES = reg("bronze_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(BRONZE_BLOCK.get())));
    public static final DeferredBlock<Block> BRONZE_BARS = reg("bronze_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(BRONZE_BLOCK.get()).noOcclusion()));
    public static final DeferredBlock<Block> BRONZE_LANTERN = reg("bronze_lantern", () -> new SizableLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN),
            tin, tin_hanging
    ));
    public static final DeferredBlock<Block> BRONZE_SOUL_LANTERN = reg("bronze_soul_lantern", () -> new SizableLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN),
            tin, tin_hanging
    ));

    // Steel
    public static final DeferredBlock<Block> STEEL_BLOCK = reg("steel_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).mapColor(MapColor.COLOR_BLACK)));
    public static final DeferredBlock<Block> CUT_STEEL_BLOCK = reg("cut_steel_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(STEEL_BLOCK.get())));
    public static final DeferredBlock<Block> STEEL_PILLAR = reg("steel_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(STEEL_BLOCK.get())));
    public static final DeferredBlock<Block> STEEL_DOOR = reg("steel_door", () -> new DoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.ofFullCopy(STEEL_BLOCK.get()).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> STEEL_BARS = reg("steel_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(STEEL_BLOCK.get()).noOcclusion()));
    public static final DeferredBlock<Block> STEEL_TRAPDOOR = reg("steel_trapdoor", () -> new TrapDoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.ofFullCopy(STEEL_BLOCK.get()).noOcclusion().pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> STEEL_ANVIL = reg("steel_anvil", () -> new SteelAnvilBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ANVIL).noOcclusion()));
    public static final DeferredBlock<Block> STEEL_GRATE = reg("steel_grate", () -> new WaterloggedTransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_GRATE).mapColor(MapColor.COLOR_BLACK)));
    public static final DeferredBlock<Block> CUT_STEEL_STAIR = reg("cut_steel_stair", () -> new StairBlock(CUT_STEEL_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CUT_STEEL_BLOCK.get())));
    public static final DeferredBlock<Block> CUT_STEEL_SLAB = reg("cut_steel_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(CUT_STEEL_BLOCK.get())));

    // Livingstone
    public static final DeferredBlock<Block> LIVINGSTONE_CROP = HELPER.createBlockNoItem("livingstone_crop", () -> new LivingstoneCrop(RegItems.PETRIFIED_SEED,
            new VoxelShape[]{
                    cropHeight(2.0D),
                    cropHeight(5.0D),
                    cropHeight(8.0D),
                    cropHeight(13.0D),
                    cropHeight(16.0D)
            }, BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> LIVINGSTONE_BLOCK = reg("livingstone_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK)));
    public static final DeferredBlock<Block> POLISHED_LIVINGSTONE = reg("polished_livingstone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(LIVINGSTONE_BLOCK.get())));
    public static final DeferredBlock<Block> LIVINGSTONE_BRICKS = reg("livingstone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(LIVINGSTONE_BLOCK.get())));
    public static final DeferredBlock<Block> LIVINGSTONE_STAIR = reg("livingstone_stair", () -> new StairBlock(LIVINGSTONE_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(LIVINGSTONE_BLOCK.get())));
    public static final DeferredBlock<Block> LIVINGSTONE_SLAB = reg("livingstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(LIVINGSTONE_BLOCK.get())));
    public static final DeferredBlock<Block> LIVINGSTONE_WALL = reg("livingstone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(LIVINGSTONE_BLOCK.get())));
    public static final DeferredBlock<Block> POLISHED_LIVINGSTONE_STAIR = reg("polished_livingstone_stair", () -> new StairBlock(POLISHED_LIVINGSTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(POLISHED_LIVINGSTONE.get())));
    public static final DeferredBlock<Block> POLISHED_LIVINGSTONE_SLAB = reg("polished_livingstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_LIVINGSTONE.get())));
    public static final DeferredBlock<Block> POLISHED_LIVINGSTONE_WALL = reg("polished_livingstone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_LIVINGSTONE.get())));
    public static final DeferredBlock<Block> LIVINGSTONE_BRICKS_STAIR = reg("livingstone_bricks_stair", () -> new StairBlock(LIVINGSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(LIVINGSTONE_BRICKS.get())));
    public static final DeferredBlock<Block> LIVINGSTONE_BRICKS_SLAB = reg("livingstone_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(LIVINGSTONE_BRICKS.get())));
    public static final DeferredBlock<Block> LIVINGSTONE_BRICKS_WALL = reg("livingstone_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(LIVINGSTONE_BRICKS.get())));

    // Verdite
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
    public static final DeferredBlock<Block> VERDITE_BLOCK = reg("verdite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));
    public static final DeferredBlock<Block> VERDITE_BRICKS = reg("verdite_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(VERDITE_BLOCK.get())));

    // Celestite & Amethyst
    public static final DeferredBlock<Block> CELESTITE_BLOCK = reg("celestite_block", () -> new CelestiteBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LODESTONE).sound(OrevolutionUtils.CELESTITE_BLOCK).lightLevel(IGlowAnimation::glow).randomTicks().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)));
    public static final DeferredBlock<Block> POLISHED_CELESTITE = reg("polished_celestite", () -> new AmethystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LODESTONE).sound(OrevolutionUtils.CELESTITE_BLOCK).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).lightLevel(s -> 7)));
    public static final DeferredBlock<Block> CELESTITE_BRICKS = reg("celestite_bricks", () -> new AmethystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LODESTONE).sound(OrevolutionUtils.CELESTITE_BLOCK).mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).lightLevel(s -> 7)));
    public static final DeferredBlock<Block> BUDDING_CELESTITE = reg("budding_celestite", () -> new BuddingCelestiteBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).randomTicks().lightLevel(IGlowAnimation::glow).strength(1.5F).sound(OrevolutionUtils.CELESTITE_BLOCK).pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<Block> POLISHED_CELESTITE_STAIR = reg("polished_celestite_stair", () -> new AmethystStairBlock(POLISHED_CELESTITE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(POLISHED_CELESTITE.get())));
    public static final DeferredBlock<Block> POLISHED_CELESTITE_SLAB = reg("polished_celestite_slab", () -> new AmethystSlabBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_CELESTITE.get())));
    public static final DeferredBlock<Block> POLISHED_CELESTITE_WALL = reg("polished_celestite_wall", () -> new AmethystWallBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_CELESTITE.get())));
    public static final DeferredBlock<Block> CELESTITE_BRICKS_STAIR = reg("celestite_bricks_stair", () -> new AmethystStairBlock(CELESTITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CELESTITE_BRICKS.get())));
    public static final DeferredBlock<Block> CELESTITE_BRICKS_SLAB = reg("celestite_bricks_slab", () -> new AmethystSlabBlock(BlockBehaviour.Properties.ofFullCopy(CELESTITE_BRICKS.get())));
    public static final DeferredBlock<Block> CELESTITE_BRICKS_WALL = reg("celestite_bricks_wall", () -> new AmethystWallBlock(BlockBehaviour.Properties.ofFullCopy(CELESTITE_BRICKS.get())));

    public static final DeferredBlock<Block> CELESTITE_CLUSTER = reg("celestite_cluster", () -> new CelestiteClusterBlock(
            7.0F, 3.0F,
            BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).forceSolidOn().noOcclusion().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).randomTicks().lightLevel(IGlowAnimation::glow).pushReaction(PushReaction.DESTROY))
    );
    public static final DeferredBlock<Block> LARGE_CELESTITE_BUD = reg("large_celestite_bud", () -> new CelestiteClusterBlock(
            5.0F, 3.0F,
            BlockBehaviour.Properties.ofFullCopy(CELESTITE_CLUSTER.get()).sound(SoundType.LARGE_AMETHYST_BUD).randomTicks().lightLevel(IGlowAnimation::glow))
    );
    public static final DeferredBlock<Block> MEDIUM_CELESTITE_BUD = reg("medium_celestite_bud", () -> new CelestiteClusterBlock(
            4.0F, 3.0F,
            BlockBehaviour.Properties.ofFullCopy(CELESTITE_CLUSTER.get()).sound(SoundType.MEDIUM_AMETHYST_BUD).randomTicks().lightLevel(IGlowAnimation::glow))
    );
    public static final DeferredBlock<Block> SMALL_CELESTITE_BUD = reg("small_celestite_bud", () -> new CelestiteClusterBlock(
            3.0F, 4.0F,
            BlockBehaviour.Properties.ofFullCopy(CELESTITE_CLUSTER.get()).sound(SoundType.SMALL_AMETHYST_BUD).randomTicks().lightLevel(IGlowAnimation::glow))
    );


    public static final DeferredBlock<Block> POLISHED_AMETHYST = reg("polished_amethyst", () -> new AmethystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)));
    public static final DeferredBlock<Block> AMETHYST_BRICKS = reg("amethyst_bricks", () -> new AmethystBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)));
    public static final DeferredBlock<Block> POLISHED_AMETHYST_STAIR = reg("polished_amethyst_stair", () -> new AmethystStairBlock(POLISHED_AMETHYST.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(POLISHED_AMETHYST.get())));
    public static final DeferredBlock<Block> POLISHED_AMETHYST_SLAB = reg("polished_amethyst_slab", () -> new AmethystSlabBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_AMETHYST.get())));
    public static final DeferredBlock<Block> POLISHED_AMETHYST_WALL = reg("polished_amethyst_wall", () -> new AmethystWallBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_AMETHYST.get())));
    public static final DeferredBlock<Block> AMETHYST_BRICKS_STAIR = reg("amethyst_bricks_stair", () -> new AmethystStairBlock(AMETHYST_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(AMETHYST_BRICKS.get())));
    public static final DeferredBlock<Block> AMETHYST_BRICKS_SLAB = reg("amethyst_bricks_slab", () -> new AmethystSlabBlock(BlockBehaviour.Properties.ofFullCopy(AMETHYST_BRICKS.get())));
    public static final DeferredBlock<Block> AMETHYST_BRICKS_WALL = reg("amethyst_bricks_wall", () -> new AmethystWallBlock(BlockBehaviour.Properties.ofFullCopy(AMETHYST_BRICKS.get())));

    // Chert
    public static final DeferredBlock<Block> CHERT = reg("chert", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE).mapColor(MapColor.COLOR_ORANGE)));
    public static final DeferredBlock<Block> POLISHED_CHERT = reg("polished_chert", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE)));
    public static final DeferredBlock<Block> CHERT_BRICKS = reg("chert_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CHERT.get())));
    public static final DeferredBlock<Block> CHERT_PILLAR = reg("chert_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE)));
    public static final DeferredBlock<Block> CHERT_STAIR = reg("chert_stair", () -> new StairBlock(CHERT.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CHERT.get())));
    public static final DeferredBlock<Block> CHERT_SLAB = reg("chert_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(CHERT.get())));
    public static final DeferredBlock<Block> CHERT_WALL = reg("chert_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(CHERT.get())));
    public static final DeferredBlock<Block> POLISHED_CHERT_STAIR = reg("polished_chert_stair", () -> new StairBlock(POLISHED_CHERT.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(POLISHED_CHERT.get())));
    public static final DeferredBlock<Block> POLISHED_CHERT_SLAB = reg("polished_chert_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_CHERT.get())));
    public static final DeferredBlock<Block> POLISHED_CHERT_WALL = reg("polished_chert_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_CHERT.get())));
    public static final DeferredBlock<Block> CHERT_BRICKS_STAIR = reg("chert_bricks_stair", () -> new StairBlock(CHERT_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CHERT_BRICKS.get())));
    public static final DeferredBlock<Block> CHERT_BRICKS_SLAB = reg("chert_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(CHERT_BRICKS.get())));
    public static final DeferredBlock<Block> CHERT_BRICKS_WALL = reg("chert_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(CHERT_BRICKS.get())));

    // Rhyolite
    public static final DeferredBlock<Block> RHYOLITE = reg("rhyolite", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).sound(SoundType.POLISHED_TUFF).mapColor(MapColor.COLOR_PINK)));
    public static final DeferredBlock<Block> POLISHED_RHYOLITE = reg("polished_rhyolite", () -> new Block(BlockBehaviour.Properties.ofFullCopy(RHYOLITE.get())));
    public static final DeferredBlock<Block> RHYOLITE_BRICKS = reg("rhyolite_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(RHYOLITE.get())));
    public static final DeferredBlock<Block> RHYOLITE_PILLAR = reg("rhyolite_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(RHYOLITE.get())));
    public static final DeferredBlock<Block> RHYOLITE_STAIR = reg("rhyolite_stair", () -> new StairBlock(RHYOLITE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(RHYOLITE.get())));
    public static final DeferredBlock<Block> RHYOLITE_SLAB = reg("rhyolite_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(RHYOLITE.get())));
    public static final DeferredBlock<Block> RHYOLITE_WALL = reg("rhyolite_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(RHYOLITE.get())));
    public static final DeferredBlock<Block> POLISHED_RHYOLITE_STAIR = reg("polished_rhyolite_stair", () -> new StairBlock(POLISHED_RHYOLITE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(POLISHED_RHYOLITE.get())));
    public static final DeferredBlock<Block> POLISHED_RHYOLITE_SLAB = reg("polished_rhyolite_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_RHYOLITE.get())));
    public static final DeferredBlock<Block> POLISHED_RHYOLITE_WALL = reg("polished_rhyolite_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_RHYOLITE.get())));
    public static final DeferredBlock<Block> RHYOLITE_BRICKS_STAIR = reg("rhyolite_bricks_stair", () -> new StairBlock(RHYOLITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(RHYOLITE_BRICKS.get())));
    public static final DeferredBlock<Block> RHYOLITE_BRICKS_SLAB = reg("rhyolite_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(RHYOLITE_BRICKS.get())));
    public static final DeferredBlock<Block> RHYOLITE_BRICKS_WALL = reg("rhyolite_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(RHYOLITE_BRICKS.get())));

    // Aetherrock
    public static final DeferredBlock<Block> AETHERROCK = reg("aetherrock", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE).mapColor(MapColor.COLOR_BLUE).isValidSpawn((a, b, c, d) -> false).strength(10F, 30F).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> POLISHED_AETHERROCK = reg("polished_aetherrock", () -> new Block(BlockBehaviour.Properties.ofFullCopy(AETHERROCK.get())));
    public static final DeferredBlock<Block> AETHERROCK_BRICKS = reg("aetherrock_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(AETHERROCK.get())));
    public static final DeferredBlock<Block> CRACKED_AETHERROCK_BRICKS = reg("cracked_aetherrock_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(AETHERROCK.get())));
    public static final DeferredBlock<Block> AETHERROCK_TILES = reg("aetherrock_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(AETHERROCK.get())));
    public static final DeferredBlock<Block> AETHERROCK_STAIR = reg("aetherrock_stair", () -> new StairBlock(AETHERROCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(AETHERROCK.get())));
    public static final DeferredBlock<Block> AETHERROCK_SLAB = reg("aetherrock_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(AETHERROCK.get())));
    public static final DeferredBlock<Block> AETHERROCK_WALL = reg("aetherrock_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(AETHERROCK.get())));
    public static final DeferredBlock<Block> POLISHED_AETHERROCK_STAIR = reg("polished_aetherrock_stair", () -> new StairBlock(POLISHED_AETHERROCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(POLISHED_AETHERROCK.get())));
    public static final DeferredBlock<Block> POLISHED_AETHERROCK_SLAB = reg("polished_aetherrock_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_AETHERROCK.get())));
    public static final DeferredBlock<Block> POLISHED_AETHERROCK_WALL = reg("polished_aetherrock_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_AETHERROCK.get())));
    public static final DeferredBlock<Block> AETHERROCK_BRICKS_STAIR = reg("aetherrock_bricks_stair", () -> new StairBlock(AETHERROCK_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(AETHERROCK_BRICKS.get())));
    public static final DeferredBlock<Block> AETHERROCK_BRICKS_SLAB = reg("aetherrock_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(AETHERROCK_BRICKS.get())));
    public static final DeferredBlock<Block> AETHERROCK_BRICKS_WALL = reg("aetherrock_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(AETHERROCK_BRICKS.get())));

    // Gold
    public static final DeferredBlock<Block> GOLD_PILLAR = reg("gold_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK)));
    public static final DeferredBlock<Block> GOLD_BARS = reg("gold_bars", () -> new IronBarsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).noOcclusion()));
    public static final DeferredBlock<Block> GOLD_TILES = reg("gold_tiles", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK)));
    public static final DeferredBlock<Block> GOLDEN_LANTERN = reg("golden_lantern", () -> new SizableLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN),
            platinum, platinum_hanging
    ));
    public static final DeferredBlock<Block> GOLDEN_SOUL_LANTERN = reg("golden_soul_lantern", () -> new SizableLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN),
            platinum, platinum_hanging
    ));

    // Misc
    public static final DeferredBlock<Block> RDX = reg("rdx", () -> new RdxBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instabreak().sound(SoundType.CORAL_BLOCK).ignitedByLava().isRedstoneConductor((a, b, c) -> false)));
    public static final DeferredBlock<Block> UNKNOWN_ROOTS_BLOCK = reg("unknown_roots_block", () -> new MangroveRootsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_ROOTS)));
    public static final DeferredBlock<Block> CORELIO = reg("corelio", () -> new CorelioBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.FERN)));
    public static final DeferredBlock<Block> LARGE_CORELIO = reg("large_corelio", () -> new TallCorelioBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_FERN)));
    public static final DeferredBlock<Block> UNKNOWN_ROOTS = reg("unknown_roots", () -> new StoneBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEAD_BUSH),
            2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D));
    public static final DeferredBlock<Block> EMERALD_CLUSTER = reg("emerald_cluster", () -> new EmeraldSpikeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POINTED_DRIPSTONE).requiresCorrectToolForDrops()));
    public static final DeferredBlock<Block> PRISMARINE_CLUSTER = reg("prismarine_cluster", () -> new EmeraldSpikeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POINTED_DRIPSTONE).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> VINNELIO = HELPER.createBlockNoItem(
            "vinnelio",
            () -> new VinnelioBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .randomTicks()
                            .noCollission()
                            .instabreak()
                            .sound(SoundType.CAVE_VINES)
                            .pushReaction(PushReaction.DESTROY)
            )
    );
    public static final DeferredBlock<Block> VINNELIO_PLANT = HELPER.createBlockNoItem(
            "vinnelio_plant",
            () -> new VinnelioPlantBlock(
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .noCollission()
                            .instabreak()
                            .sound(SoundType.CAVE_VINES)
                            .pushReaction(PushReaction.DESTROY)
            )
    );
}