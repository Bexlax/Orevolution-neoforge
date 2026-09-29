package net.bexla.orevolution.content.worldgen;

import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegWorldCarvers;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;

import static net.bexla.orevolution.content.data.utility.OrevolutionKeys.ConfiguredWorldCarvers.QUARTZOLITE_CARVER;

public class OrevolutionCarvers {
    public static void bootstrap(BootstrapContext<ConfiguredWorldCarver<?>> context) {
        HolderGetter<Block> holdergetter = context.lookup(Registries.BLOCK);

        context.register(
                QUARTZOLITE_CARVER,
                RegWorldCarvers.BLOCK_CARVER.get()
                        .configured(
                                new BlockCarver.BlockCarverConfiguration(
                                        0.5F,
                                        UniformHeight.of(VerticalAnchor.aboveBottom(8), VerticalAnchor.absolute(180)),
                                        UniformFloat.of(0.08F, 0.2F),
                                        VerticalAnchor.aboveBottom(8),
                                        CarverDebugSettings.of(false, Blocks.CRIMSON_BUTTON.defaultBlockState()),
                                        holdergetter.getOrThrow(BlockTags.OVERWORLD_CARVER_REPLACEABLES),
                                        UniformFloat.of(0.35F, 0.55F),
                                        UniformFloat.of(0.7F, 1.0F),
                                        UniformFloat.of(-0.8F, -0.5F),
                                        RegBlocks.QUARTZOLITE.get()
                                )
                        )
        );
    }
}
