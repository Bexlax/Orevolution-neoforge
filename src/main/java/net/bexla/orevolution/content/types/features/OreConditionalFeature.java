package net.bexla.orevolution.content.types.features;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class OreConditionalFeature extends OreFeature {
    private final Supplier<Boolean> config;

    public OreConditionalFeature(Codec<OreConfiguration> codec, Supplier<Boolean> config) {
        super(codec);
        this.config = config;
    }

    public boolean getCondition() {
        return config.get();
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<OreConfiguration> context) {
        if (!getCondition()) {
            return false;
        }
        return super.place(context);
    }
}