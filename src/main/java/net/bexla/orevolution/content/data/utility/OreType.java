package net.bexla.orevolution.content.data.utility;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.neoforge.common.Tags;

import java.util.Arrays;
import java.util.List;

public record OreType(List<RuleTest> targets) {
    public static final OreType OVERWORLD = new OreType(List.of(
            new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
            new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES)
    ));

    public static final OreType NETHER = new OreType(List.of(
            new TagMatchTest(Tags.Blocks.NETHERRACKS)
    ));

    public static final OreType END = new OreType(List.of(
            new TagMatchTest(Tags.Blocks.END_STONES)
    ));

    public OreType(List<RuleTest> targets) {
        this.targets = List.copyOf(targets);
    }

    public OreType(OreType... types) {
        this(Arrays.stream(types)
                .flatMap(type -> type.targets.stream())
                .toList());
    }
}