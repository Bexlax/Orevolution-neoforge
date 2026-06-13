package net.bexla.orevolution.content.types.features;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class RandomPatchConditionalFeature extends RandomPatchFeature {
    private final Supplier<Boolean> config;

    public RandomPatchConditionalFeature(Codec<RandomPatchConfiguration> codec, Supplier<Boolean> config) {
        super(codec);
        this.config = config;
    }

    public boolean getCondition() {
        return config.get();
    }

    @Override
    public boolean place(@NotNull FeaturePlaceContext<RandomPatchConfiguration> context) {
        if (!getCondition()) {
            return false;
        }
        return super.place(context);
    }
}
