package net.bexla.orevolution.content.types.providers;

import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.types.block.OreCropBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

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

    public void simpleBlock(Supplier<? extends Block> block, String subfolder) {
        this.simpleBlock(block.get(), this.cubeAll(block.get(), subfolder));
    }

    public void cropModel(Supplier<? extends Block> block) {
        this.models().crop(name(block.get()), modLoc("block/crops/" + name(block.get()))).renderType("cutout");
    }

    public void complexBlock(Supplier<? extends Block> block, String subfolder, int weightF, int weightS) {
        Block b = block.get();
        ResourceLocation name = this.key(b);

        ModelFile model1 = this.models().cubeAll(name(b), blockTexture(b, subfolder));
        ModelFile model2 = this.models().cubeAll(name(b) + "_alt", ResourceLocation.fromNamespaceAndPath(name.getNamespace(), blockTexture(b, subfolder).getPath() + "_alt"));

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
                    .modelFile(this.models().crop(modelName + stage, modLoc("block/crops/" + textureName + stage)).renderType("cutout")).build();
        });
    }

    public ModelFile cubeAll(Block block, String subfolder) {
        return this.models().cubeAll(name(block), this.blockTexture(block, subfolder));
    }

    public void barsBlock(DeferredBlock<Block> bars) {
        this.ironBarsBlock(bars.get(), this.blockTexture(bars.get(), "decorative/bar"));
        this.generatedItem(bars.get(), "block/decorative/bar");
    }

    public void ironBarsBlock(Block block, ResourceLocation texture) {
        String name = name(block);
        ModelFile post = this.ironBarsBlock(name, "post", texture).texture("bars", texture).renderType("cutout");
        ModelFile postEnds = this.ironBarsBlock(name, "post_ends", texture).texture("edge", texture).renderType("cutout");
        ModelFile side = (this.ironBarsBlock(name, "side", texture).texture("bars", texture)).texture("edge", texture).renderType("cutout");
        ModelFile sideAlt = (this.ironBarsBlock(name, "side_alt", texture).texture("bars", texture)).texture("edge", texture).renderType("cutout");
        ModelFile cap = (this.ironBarsBlock(name, "cap", texture).texture("bars", texture)).texture("edge", texture).renderType("cutout");
        ModelFile capAlt = (this.ironBarsBlock(name, "cap_alt", texture).texture("bars", texture)).texture("edge", texture).renderType("cutout");
        this.paneBlock(block, post, postEnds, side, sideAlt, cap, capAlt);
    }


    public void doorBlock(Supplier<? extends Block> door) {
        Block block = door.get();

        if (block instanceof DoorBlock doorBlock) {
            this.doorBlock(doorBlock, suffix(this.blockTexture(block, "decorative/door"), "_bottom"), suffix(this.blockTexture(block, "decorative/door"), "_top"));
            this.generatedItem(block, "item/blockitem");
        }
    }

    public void trapdoorBlock(Supplier<? extends Block> trapdoor) {
        Block block = trapdoor.get();

        if (block instanceof TrapDoorBlock trapDoorBlock) {
            this.trapdoorBlock(trapDoorBlock, this.blockTexture(block, "decorative/trapdoor"), true);
        }
    }

    public ResourceLocation blockTexture(Block block, String subfolder) {
        ResourceLocation name = this.key(block);
        return ResourceLocation.fromNamespaceAndPath(name.getNamespace(), "block/" + subfolder + "/" + name.getPath());
    }

    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    public void stairsBlock(Supplier<? extends Block> block, Supplier<? extends Block> fullBlock) {
        if(block.get() instanceof StairBlock block1) {
            stairsBlock(block1, blockTexture(fullBlock.get(), "decorative"));
        }
    }

    public void slabBlock(Supplier<? extends Block> block, Supplier<? extends Block> fullBlock) {
        if(block.get() instanceof SlabBlock block1) {
            slabBlock(block1, texture(name(fullBlock)), blockTexture(fullBlock.get(), "decorative"));
        }
    }

    public void wallBlock(Supplier<? extends Block> wall, Supplier<? extends Block> fullBlock) {
        if(wall.get() instanceof WallBlock block) {
            wallBlock(block, blockTexture(fullBlock.get(), "decorative"));
        }
    }

    public void pillar(DeferredBlock<Block> block) {
        if (block.get() instanceof RotatedPillarBlock log)
            this.axisBlock(log, suffix(this.blockTexture(block.get(), "decorative"), "_side"), suffix(this.blockTexture(block.get(), "decorative"), "_top"));
    }

    public void cubeColumnBlock(DeferredBlock<Block> block, DeferredBlock<Block> topCopy) {
        this.cubeColumnBlock(block, this.blockTexture(block.get(), "decorative"), this.blockTexture(topCopy.get(), "decorative"));
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
