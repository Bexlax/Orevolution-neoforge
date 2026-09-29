package net.bexla.orevolution.content.worldgen;

import net.bexla.orevolution.content.data.utility.OrevolutionKeys;
import net.bexla.orevolution.init.RegBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class OrevolutionSurfaceRules {
    private static final SurfaceRules.RuleSource RHYOLITE =
            SurfaceRules.state(RegBlocks.RHYOLITE.get().defaultBlockState());
    private static final SurfaceRules.RuleSource TUFF =
            SurfaceRules.state(Blocks.TUFF.defaultBlockState());

    private static final SurfaceRules.RuleSource COBBLED_DEEPSLATE =
            SurfaceRules.state(Blocks.COBBLED_DEEPSLATE.defaultBlockState());


    public static final SurfaceRules.RuleSource FORGOTTEN_CAVE_FILL = SurfaceRules.ifTrue(
            SurfaceRules.isBiome(OrevolutionKeys.Biomes.FORGOTTEN_CAVE),
            SurfaceRules.sequence(
                    SurfaceRules.ifTrue(
                            SurfaceRules.stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
                            RHYOLITE
                    ),
                    SurfaceRules.ifTrue(
                            SurfaceRules.stoneDepthCheck(3, false, 0, CaveSurface.FLOOR),
                            TUFF
                    )
            )

    );

    public static final SurfaceRules.RuleSource GEODE_GARDENS_FILL = SurfaceRules.ifTrue(
            SurfaceRules.isBiome(OrevolutionKeys.Biomes.GEODE_GARDENS),
            SurfaceRules.sequence(
                    SurfaceRules.ifTrue(
                            SurfaceRules.verticalGradient("randomcobbled", VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(50)),
                            SurfaceRules.ifTrue(
                                    SurfaceRules.stoneDepthCheck(0, true, 2, CaveSurface.FLOOR),
                                    COBBLED_DEEPSLATE
                            )
                    ),
                    SurfaceRules.ifTrue(
                            SurfaceRules.verticalGradient("randomcobbledslate", VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(50)),
                            SurfaceRules.ifTrue(
                                    SurfaceRules.stoneDepthCheck(0, true, 2, CaveSurface.FLOOR),
                                    COBBLED_DEEPSLATE
                            )
                    )
            )
    );

    private static final SurfaceRules.RuleSource BEDROCK_BOTTOM = SurfaceRules.ifTrue(
            SurfaceRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)),
            SurfaceRules.state(Blocks.BEDROCK.defaultBlockState())
    );

    public static final SurfaceRules.RuleSource FORGOTTEN_CAVE_RULES = SurfaceRules.sequence(
            BEDROCK_BOTTOM,
            FORGOTTEN_CAVE_FILL
    );

    public static final SurfaceRules.RuleSource GEODE_GARDENS_RULES = SurfaceRules.sequence(
            BEDROCK_BOTTOM,
            GEODE_GARDENS_FILL
    );

    public static SurfaceRules.RuleSource makeRules() {
        return SurfaceRules.sequence(FORGOTTEN_CAVE_RULES, BEDROCK_BOTTOM);
    }
}
