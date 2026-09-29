package net.bexla.orevolution.content.worldgen.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.Optional;
import java.util.function.Supplier;

public class PillarFeature extends Feature<PillarFeature.PillarConfiguration> {
    private final Supplier<Boolean> config;

    public PillarFeature(Codec<PillarConfiguration> codec, Supplier<Boolean> config) {
        super(codec);
        this.config = config;
    }

    @Override
    public boolean place(FeaturePlaceContext<PillarConfiguration> context) {
        if(!config.get()) return false;

        WorldGenLevel level = context.level();
        RandomSource random = context.random();
        BlockPos origin = context.origin();

        Optional<PillarSize> optional = context.config().sizes().getRandomValue(random);

        if (optional.isEmpty()) {
            return false;
        }

        PillarSize size = optional.get();

        int width = size.width().sample(random);
        int height = size.height().sample(random);

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        int startX = -(width / 2);
        int startZ = -(width / 2);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                for (int z = 0; z < width; z++) {

                    pos.set(
                            origin.getX() + startX + x,
                            origin.getY() + y,
                            origin.getZ() + startZ + z
                    );

                    BlockStateProvider state =
                            y == 0 ? context.config().supportStateProvider :
                                    y == 1 ? context.config().baseStateProvider
                                            : context.config().aboveStateProvider;

                    level.setBlock(
                            pos,
                            state.getState(random, pos),
                            2
                    );
                }
            }
        }

        return true;
    }

    public record PillarConfiguration(
            SimpleWeightedRandomList<PillarSize> sizes,
            BlockStateProvider supportStateProvider,
            BlockStateProvider baseStateProvider,
            BlockStateProvider aboveStateProvider) implements FeatureConfiguration {
        public static final Codec<PillarConfiguration> CODEC =
                RecordCodecBuilder.create(instance ->
                        instance.group(
                                SimpleWeightedRandomList.wrappedCodec(PillarSize.CODEC)
                                        .fieldOf("sizes")
                                        .forGetter(PillarConfiguration::sizes),
                                BlockStateProvider.CODEC
                                        .fieldOf("support_state_provider")
                                        .forGetter(PillarConfiguration::supportStateProvider),
                                BlockStateProvider.CODEC
                                        .fieldOf("base_state_provider")
                                        .forGetter(PillarConfiguration::baseStateProvider),
                                BlockStateProvider.CODEC
                                        .fieldOf("above_state_provider")
                                        .forGetter(PillarConfiguration::aboveStateProvider)
                        ).apply(instance, PillarConfiguration::new)
                );
    }

    public record PillarSize(IntProvider width, IntProvider height) {
        public static final Codec<PillarSize> CODEC =
                RecordCodecBuilder.create(instance ->
                        instance.group(
                                IntProvider.CODEC.fieldOf("width").forGetter(PillarSize::width),
                                IntProvider.CODEC.fieldOf("height").forGetter(PillarSize::height)
                        ).apply(instance, PillarSize::new)
                );
    }
}
