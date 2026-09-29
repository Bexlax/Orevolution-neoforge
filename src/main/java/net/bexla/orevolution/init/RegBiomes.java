package net.bexla.orevolution.init;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.data.utility.ClimateParameterUtils;
import net.bexla.orevolution.content.data.utility.OrevolutionKeys;
import net.bexla.orevolution.content.worldgen.OrevolutionRegions;
import net.bexla.orevolution.content.worldgen.OrevolutionSurfaceRules;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.SurfaceRuleManager;

import static net.bexla.orevolution.Orevolution.lc;

public class RegBiomes {
    public static void safeInitTerrablender() {
        OrevolutionRegions.region();
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Orevolution.MODID, OrevolutionSurfaceRules.makeRules());
    }

    public static void safeInitBiolith() {
        if(OrevolutionConfig.COMMON.generateForgottenCaves.get()) {
            BiomePlacement.addOverworld(
                    OrevolutionKeys.Biomes.FORGOTTEN_CAVE,
                    Climate.parameters(
                            Climate.Parameter.span(0.89F, 1.0F),
                            ClimateParameterUtils.Humidity.span(
                                    ClimateParameterUtils.Humidity.ARID,
                                    ClimateParameterUtils.Humidity.DRY
                            ),
                            ClimateParameterUtils.Continentalness.NEAR_INLAND.parameter(),
                            Climate.Parameter.span(-0.2F, 0.2F),
                            ClimateParameterUtils.Depth.UNDERGROUND.parameter(),
                            Climate.Parameter.span(-0.05F, 0.05F),
                            0.0F
                    )
            );
        }
        if(OrevolutionConfig.COMMON.generateGeodeGardens.get()) {
            BiomePlacement.addOverworld(
                    OrevolutionKeys.Biomes.GEODE_GARDENS,
                    Climate.parameters(
                            Climate.Parameter.span(-0.45F, -0.3F),
                            ClimateParameterUtils.Humidity.span(ClimateParameterUtils.Humidity.DRY, ClimateParameterUtils.Humidity.NEUTRAL),
                            ClimateParameterUtils.Continentalness.FAR_INLAND.parameter(),
                            Climate.Parameter.span(0.25F, 0.30F),
                            ClimateParameterUtils.Depth.UNDERGROUND.parameter(),
                            Climate.Parameter.span(0.12F, 0.16F),
                            0.0F
                    )
            );
        }

        SurfaceGeneration.addOverworldSurfaceRules(lc("rules/overworld"), OrevolutionSurfaceRules.makeRules());
    }
}
