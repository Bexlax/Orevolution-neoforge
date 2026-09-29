package net.bexla.orevolution.content.types.providers;

import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.interfaces.IVinnelio;
import net.bexla.orevolution.content.types.block.EncrustedMoonstoneBlock;
import net.bexla.orevolution.content.types.block.OreCropBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.Supplier;

import static net.neoforged.neoforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

// Credits to Oreganized (Team Galena)
public abstract class BlockStateModelProvider extends BlueprintBlockStateProvider {

    public BlockStateModelProvider(PackOutput output, ExistingFileHelper help) {
        super(output, Orevolution.MODID, help);
    }

    protected ResourceLocation texture(String name) {
        return modLoc(BLOCK_FOLDER + "/" + name);
    }

    protected String name(Supplier<? extends Block> block) {
        return name(block.get());
    }

    public void simpleBlock(Supplier<? extends Block> block, String subfolder, String renderType) {
        simpleBlock(block.get(), cubeAll(block.get(), subfolder, renderType));
    }

    public void simpleBlock(Supplier<? extends Block> block, String subfolder) {
        simpleBlock(block.get(), cubeAll(block.get(), subfolder));
    }

    public void altBlock(Supplier<? extends Block> block, String subfolder, int weightF, int weightS) {
        Block b = block.get();
        ResourceLocation name = key(b);

        ModelFile model1 = models().cubeAll(name(b), blockTexture(b, subfolder));
        ModelFile model2 = models().cubeAll(name(b) + "_alt", ResourceLocation.fromNamespaceAndPath(name.getNamespace(), blockTexture(b, subfolder).getPath() + "_alt"));

        getVariantBuilder(b)
                .partialState()
                .addModels(
                        new ConfiguredModel(model1, 0, 0, false, weightF),
                        new ConfiguredModel(model2, 0, 0, false, weightS)
                );
    }

    public void makeCrop(OreCropBlock block, String modelName, String textureName) {
        getVariantBuilder(block).forAllStates(state -> {
            IntegerProperty ageProperty = block.getAgeProperty();
            int age = state.getValue(ageProperty);
            String stage = "_" + age;
            return ConfiguredModel.builder()
                    .modelFile(models().crop(modelName + stage, modLoc("block/crops/" + textureName + stage)).renderType("cutout")).build();
        });
    }

    public void crossBlock(DeferredHolder<Block, ?> cross, String subfolder) {
        simpleBlock(cross.get(), models().cross(name(cross.get()), blockTexture(cross.get(), subfolder)).renderType("cutout"));
        generatedItem(cross.get(), "block/" + subfolder);
    }

    public void clusterBlock(DeferredHolder<Block, ?> block, String subfolder) {
        Block b = block.get();

        ModelFile model = models()
                .cross(name(b), blockTexture(b, subfolder))
                .renderType("cutout");

        getVariantBuilder(b)
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(model)
                        .rotationX(getClusterXRotation(state))
                        .rotationY(getClusterYRotation(state))
                        .build());

        generatedItem(b, "block/" + subfolder);
    }

    private static int getClusterXRotation(BlockState state) {
        return switch (state.getValue(AmethystClusterBlock.FACING)) {
            case DOWN -> 180;
            case UP -> 0;
            default -> 90;
        };
    }

    private static int getClusterYRotation(BlockState state) {
        return switch (state.getValue(AmethystClusterBlock.FACING)) {
            case SOUTH -> 180;
            case WEST -> 270;
            case EAST -> 90;
            default -> 0;
        };
    }

    public void crossBlockNoItem(DeferredHolder<Block, ?> cross, String subfolder) {
        simpleBlock(cross.get(), models().cross(name(cross.get()), blockTexture(cross.get(), subfolder)).renderType("cutout"));
    }

    public void vinnelio(DeferredHolder<Block, ?> block) {
        String name = name(block.get());

        ModelFile normal = models()
                .cross(name + "_head", modLoc("block/decorative/plant/" + name + "_head"))
                .renderType("cutout");

        ModelFile fruit = models()
                .cross(name + "_head_fruit", modLoc("block/decorative/plant/" + name + "_head_fruit"))
                .renderType("cutout");

        getVariantBuilder(block.get())
                .partialState()
                .with(IVinnelio.FRUIT, false)
                .modelForState()
                .modelFile(normal)
                .addModel()

                .partialState()
                .with(IVinnelio.FRUIT, true)
                .modelForState()
                .modelFile(fruit)
                .addModel();
    }

    public void bulb(DeferredHolder<Block, ?> block) {
        String name = name(block.get());

        ModelFile normal = models().cubeAll(name, modLoc("block/decorative/" + name));
        ModelFile lit = models().cubeAll(name + "_lit", modLoc("block/decorative/" + name + "_lit"));
        ModelFile powered = models().cubeAll(name + "_powered", modLoc("block/decorative/" + name + "_powered"));
        ModelFile poweredLit = models().cubeAll(name + "_lit_powered", modLoc("block/decorative/" + name + "_lit_powered"));

        getVariantBuilder(block.get())
                .partialState()
                .with(CopperBulbBlock.LIT, false)
                .with(CopperBulbBlock.POWERED, false)
                .modelForState()
                .modelFile(normal)
                .addModel()

                .partialState()
                .with(CopperBulbBlock.LIT, true)
                .with(CopperBulbBlock.POWERED, true)
                .modelForState()
                .modelFile(poweredLit)
                .addModel()

                .partialState()
                .with(CopperBulbBlock.LIT, true)
                .with(CopperBulbBlock.POWERED, false)
                .modelForState()
                .modelFile(lit)
                .addModel()

                .partialState()
                .with(CopperBulbBlock.LIT, false)
                .with(CopperBulbBlock.POWERED, true)
                .modelForState()
                .modelFile(powered)
                .addModel();
    }

    public void doubleCrossBlock(DeferredHolder<Block, ?> plant, String subfolder) {
        String name = name(plant.get());
        ResourceLocation key = key(plant.get());

        ModelFile bottom = models()
                .cross(name + "_bottom", ResourceLocation.fromNamespaceAndPath(key.getNamespace(), "block/" + subfolder + "/" + key.getPath() + "_bottom"))
                .renderType("cutout");

        ModelFile top = models()
                .cross(name + "_top", ResourceLocation.fromNamespaceAndPath(key.getNamespace(), "block/" + subfolder + "/" + key.getPath() + "_top"))
                .renderType("cutout");

        getVariantBuilder(plant.get())
                .partialState()
                .with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
                .modelForState()
                .modelFile(bottom)
                .addModel()

                .partialState()
                .with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
                .modelForState()
                .modelFile(top)
                .addModel();

        generatedItem(plant.get(), ResourceLocation.fromNamespaceAndPath(key.getNamespace(), "block/" + subfolder + "/" + key.getPath() + "_top"));
    }

    public void crossBlockWithPot(DeferredHolder<Block, ?> cross, String subfolder, DeferredHolder<Block, ?> flowerPot, ResourceLocation potTexture) {
        crossBlock(cross, subfolder);
        simpleBlock(flowerPot.get(), models().singleTexture(name(flowerPot.get()), ResourceLocation.withDefaultNamespace("block/flower_pot_cross"), "plant", potTexture));
    }

    public void crossBlockWithPot(DeferredHolder<Block, ?> cross, DeferredHolder<Block, ?> flowerPot, String subfolder) {
        crossBlockWithPot(cross, subfolder, flowerPot, blockTexture(cross.get(), subfolder));
    }

    public void crossBlockWithCustomPot(DeferredHolder<Block, ?> cross, DeferredHolder<Block, ?> flowerPot, String subfolder) {
        crossBlockWithPot(cross, subfolder, flowerPot, blockTexture(flowerPot.get(), subfolder));
    }

    public ModelFile cubeAll(Block block, String subfolder, String renderType) {
        return models().cubeAll(name(block), blockTexture(block, subfolder)).renderType(renderType);
    }

    public ModelFile cubeAll(Block block, String subfolder) {
        return models().cubeAll(name(block), blockTexture(block, subfolder));
    }

    public void barsBlock(DeferredBlock<Block> bars) {
        ironBarsBlock(bars.get(), blockTexture(bars.get(), "decorative/bar"));
        generatedItem(bars.get(), "block/decorative/bar");
    }

    public void ironBarsBlock(Block block, ResourceLocation texture) {
        String name = name(block);
        ModelFile post = ironBarsBlock(name, "post", texture).texture("bars", texture).renderType("cutout");
        ModelFile postEnds = ironBarsBlock(name, "post_ends", texture).texture("edge", texture).renderType("cutout");
        ModelFile side = (ironBarsBlock(name, "side", texture).texture("bars", texture)).texture("edge", texture).renderType("cutout");
        ModelFile sideAlt = (ironBarsBlock(name, "side_alt", texture).texture("bars", texture)).texture("edge", texture).renderType("cutout");
        ModelFile cap = (ironBarsBlock(name, "cap", texture).texture("bars", texture)).texture("edge", texture).renderType("cutout");
        ModelFile capAlt = (ironBarsBlock(name, "cap_alt", texture).texture("bars", texture)).texture("edge", texture).renderType("cutout");
        paneBlock(block, post, postEnds, side, sideAlt, cap, capAlt);
    }


    public void doorBlock(Supplier<? extends Block> door) {
        Block block = door.get();

        if (block instanceof DoorBlock doorBlock) {
            this.doorBlock(doorBlock, suffix(blockTexture(block, "decorative/door"), "_bottom"), suffix(blockTexture(block, "decorative/door"), "_top"));
            generatedItem(block, "item/misc/blockitem");
        }
    }

    public void doorBlock(DoorBlock block, ResourceLocation bottom, ResourceLocation top) {
        this.doorBlockInternal(block, key(block).toString(), bottom, top);
    }

    private void doorBlockInternal(DoorBlock block, String baseName, ResourceLocation bottom, ResourceLocation top) {
        ModelFile bottomLeft = models().doorBottomLeft(baseName + "_bottom_left", bottom, top).renderType("cutout");
        ModelFile bottomLeftOpen = models().doorBottomLeftOpen(baseName + "_bottom_left_open", bottom, top).renderType("cutout");
        ModelFile bottomRight = models().doorBottomRight(baseName + "_bottom_right", bottom, top).renderType("cutout");
        ModelFile bottomRightOpen = models().doorBottomRightOpen(baseName + "_bottom_right_open", bottom, top).renderType("cutout");
        ModelFile topLeft = models().doorTopLeft(baseName + "_top_left", bottom, top).renderType("cutout");
        ModelFile topLeftOpen = models().doorTopLeftOpen(baseName + "_top_left_open", bottom, top).renderType("cutout");
        ModelFile topRight = models().doorTopRight(baseName + "_top_right", bottom, top).renderType("cutout");
        ModelFile topRightOpen = models().doorTopRightOpen(baseName + "_top_right_open", bottom, top).renderType("cutout");
        doorBlock(block, bottomLeft, bottomLeftOpen, bottomRight, bottomRightOpen, topLeft, topLeftOpen, topRight, topRightOpen);
    }

    public void trapdoorBlock(Supplier<? extends Block> trapdoor) {
        Block block = trapdoor.get();

        if (block instanceof TrapDoorBlock trapDoorBlock) {
            trapdoorBlock(trapDoorBlock, blockTexture(block, "decorative/trapdoor"), true);
        }
    }

    public ResourceLocation blockTexture(Block block, String subfolder) {
        ResourceLocation name = key(block);
        return ResourceLocation.fromNamespaceAndPath(name.getNamespace(), "block/" + subfolder + "/" + name.getPath());
    }

    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    public void blockSet(Supplier<? extends Block> fullBlock, Supplier<? extends Block> slab, Supplier<? extends Block> stair, Supplier<? extends Block> wall, String subfolder) {
        slabBlock(slab, fullBlock, subfolder);
        stairsBlock(stair, fullBlock, subfolder);

        if(wall == null) return;
        wallBlock(wall, fullBlock, subfolder);
    }

    public void stairsBlock(Supplier<? extends Block> block, Supplier<? extends Block> fullBlock, String subfolder) {
        if(block.get() instanceof StairBlock block1) {
            stairsBlock(block1, blockTexture(fullBlock.get(), subfolder));
        }
    }

    public void slabBlock(Supplier<? extends Block> block, Supplier<? extends Block> fullBlock, String subfolder) {
        if(block.get() instanceof SlabBlock block1) {
            slabBlock(block1, texture(name(fullBlock)), blockTexture(fullBlock.get(), subfolder));
        }
    }

    public void wallBlock(Supplier<? extends Block> wall, Supplier<? extends Block> fullBlock, String subfolder) {
        if(wall.get() instanceof WallBlock block) {
            wallBlock(block, blockTexture(fullBlock.get(), subfolder));
        }
    }

    public void pillar(DeferredBlock<Block> block, String subfolder) {
        if (block.get() instanceof RotatedPillarBlock log)
            axisBlock(log, suffix(blockTexture(block.get(), subfolder), "_side"), suffix(blockTexture(block.get(), subfolder), "_top"));
    }

    public void pillar(DeferredBlock<Block> block, String sideSuffix, String topSuffix, String subfolder) {
        if (block.get() instanceof RotatedPillarBlock log)
            axisBlock(log, suffix(blockTexture(block.get(), subfolder), sideSuffix), suffix(blockTexture(block.get(), subfolder), topSuffix));
    }

    public void mirroredPillar(DeferredBlock<Block> block, String sideSuffix, String topSuffix, String subfolder) {
        if (!(block.get() instanceof RotatedPillarBlock pillar))
            return;

        ResourceLocation sideTexture = suffix(blockTexture(block.get(), subfolder), sideSuffix);
        ResourceLocation topTexture = suffix(blockTexture(block.get(), subfolder), topSuffix);

        ModelFile normal = models().cubeColumn(
                name(block.get()),
                sideTexture,
                topTexture
        );

        ModelFile mirrored = models().cubeColumn(
                name(block.get()) + "_mirrored",
                sideTexture,
                topTexture
        );

        getVariantBuilder(pillar)
                .partialState()
                .with(RotatedPillarBlock.AXIS, Direction.Axis.X)
                .addModels(
                        new ConfiguredModel(normal, 90, 90, false),
                        new ConfiguredModel(mirrored, 90, 90, false),
                        new ConfiguredModel(normal, 90, 90, false),
                        new ConfiguredModel(mirrored, 90, 90, false)
                )
                .partialState()
                .with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
                .addModels(
                        new ConfiguredModel(normal),
                        new ConfiguredModel(mirrored),
                        new ConfiguredModel(normal, 0, 180, false),
                        new ConfiguredModel(mirrored, 0, 180, false)
                )
                .partialState()
                .with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                .addModels(
                        new ConfiguredModel(normal, 90, 0, false),
                        new ConfiguredModel(mirrored, 90, 0, false),
                        new ConfiguredModel(normal, 90, 180, false),
                        new ConfiguredModel(mirrored, 90, 180, false)
                );
    }

    public void pillarWithBottom(DeferredBlock<Block> block, String subfolder) {
        if (!(block.get() instanceof EncrustedMoonstoneBlock log)) return;

        ResourceLocation side = suffix(blockTexture(block.get(), subfolder), "_side");
        ResourceLocation top = suffix(blockTexture(block.get(), subfolder), "_top");
        ResourceLocation bottom = suffix(blockTexture(block.get(), subfolder), "_bottom");

        ModelFile model = models()
                .withExistingParent(name(log), modLoc("block/column_bottom_top"))
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);

        ModelFile horizontal = models()
                .withExistingParent(name(log), modLoc("block/column_bottom_top_horizontal"))
                .texture("side", side)
                .texture("top", top)
                .texture("bottom", bottom);

        logBlock(log, model, horizontal);
    }

    private void logBlock(EncrustedMoonstoneBlock block, ModelFile model, ModelFile horizontal) {
        getVariantBuilder(block)
                .partialState().with(EncrustedMoonstoneBlock.FACING, Direction.UP)
                .modelForState().modelFile(model).addModel()
                .partialState().with(EncrustedMoonstoneBlock.FACING, Direction.DOWN)
                .modelForState().modelFile(model).rotationX(180).addModel()
                .partialState().with(EncrustedMoonstoneBlock.FACING, Direction.NORTH)
                .modelForState().modelFile(horizontal).rotationX(90).addModel()
                .partialState().with(EncrustedMoonstoneBlock.FACING, Direction.SOUTH)
                .modelForState().modelFile(horizontal).rotationX(90).rotationY(180).addModel()
                .partialState().with(EncrustedMoonstoneBlock.FACING, Direction.EAST)
                .modelForState().modelFile(horizontal).rotationX(90).rotationY(90).addModel()
                .partialState().with(EncrustedMoonstoneBlock.FACING, Direction.WEST)
                .modelForState().modelFile(horizontal).rotationX(90).rotationY(270).addModel();
    }

    public void cubeColumnBlock(DeferredBlock<Block> block, DeferredBlock<Block> topCopy) {
        cubeColumnBlock(block, blockTexture(block.get(), "decorative"), blockTexture(topCopy.get(), "decorative"));
    }

    public ModelFile directionalBlockModel(Supplier<? extends Block> block, String name, String side, String front, String back, String top) {
        return models().withExistingParent(name, BLOCK_FOLDER + "/observer")
                .texture("bottom", texture(back))
                .texture("side", texture(side))
                .texture("top", texture(top))
                .texture("front", texture(front))
                .texture("particle", texture(front));
    }
}
