package net.bexla.orevolution.content.data.utility;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.neoforge.common.Tags;

import java.util.List;

public enum OreType {
    OVERWORLD(List.of(
            new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
            new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES)
    )),
    NETHER(List.of(
            new TagMatchTest(Tags.Blocks.NETHERRACKS)
    )),
    END(List.of(
            new TagMatchTest(Tags.Blocks.END_STONES)
    )),
    STONE(List.of(
            new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES)
    ));

    private final List<? extends RuleTest> targets;

    OreType(List<? extends RuleTest> targets) {
        this.targets = targets;
    }

    public List<? extends RuleTest> getTargets() {
        return targets;
    }
}
