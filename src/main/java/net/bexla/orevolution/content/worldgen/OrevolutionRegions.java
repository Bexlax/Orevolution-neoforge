package net.bexla.orevolution.content.worldgen;

import com.mojang.datafixers.util.Pair;
import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.data.utility.ClimateParameterUtils.*;
import net.bexla.orevolution.content.data.utility.OrevolutionKeys;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.Regions;

import java.util.function.Consumer;

import static net.bexla.orevolution.Orevolution.lc;

public class OrevolutionRegions extends Region {
    public OrevolutionRegions(ResourceLocation name, int weight)
    {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper)
    {
        addModifiedVanillaOverworldBiomes(mapper, b -> {});

        if(OrevolutionConfig.COMMON.generateForgottenCaves.get()) {
            this.addBiome(mapper,
                    Temperature.span(Temperature.WARM, Temperature.HOT),
                    Humidity.span(Humidity.DRY, Humidity.ARID),
                    Continentalness.span(Continentalness.COAST, Continentalness.FAR_INLAND),
                    Erosion.FULL_RANGE.parameter(),
                    Climate.Parameter.span(-0.2F, 0.1F),
                    Depth.UNDERGROUND.parameter(),
                    0.0f,
                    OrevolutionKeys.Biomes.FORGOTTEN_CAVE);
        }

        if(OrevolutionConfig.COMMON.generateGeodeGardens.get()) {
            this.addBiome(mapper,
                    Temperature.FULL_RANGE.parameter(),
                    Humidity.span(Humidity.DRY, Humidity.WET),
                    Continentalness.span(Continentalness.NEAR_INLAND, Continentalness.MID_INLAND),
                    Erosion.span(Erosion.EROSION_0, Erosion.EROSION_2),
                    Climate.Parameter.span(0.15F, 0.4F),
                    Depth.UNDERGROUND.parameter(),
                    0.0f,
                    OrevolutionKeys.Biomes.GEODE_GARDENS);
        }
    }

    public static void region() {
        Regions.register(new OrevolutionRegions(lc("overworld"), 1));
    }
}
