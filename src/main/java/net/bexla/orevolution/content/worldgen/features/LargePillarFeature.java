package net.bexla.orevolution.content.worldgen.features;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Column;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.DripstoneUtils;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Optional;

public class LargePillarFeature extends Feature<LargePillarFeature.LargePillarConfiguration> {
    public LargePillarFeature(Codec<LargePillarConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<LargePillarConfiguration> context) {
        WorldGenLevel worldgenlevel = context.level();
        BlockPos blockpos = context.origin();
        LargePillarConfiguration LargePillarConfiguration = context.config();
        RandomSource randomsource = context.random();
        if (!isEmptyOrWater(worldgenlevel, blockpos)) {
            return false;
        } else {
            Optional<Column> optional = Column.scan(
                    worldgenlevel,
                    blockpos,
                    LargePillarConfiguration.floorToCeilingSearchRange,
                    DripstoneUtils::isEmptyOrWater,
                    LargePillarFeature::isBaseOrLava
            );
            if (!optional.isEmpty() && optional.get() instanceof Column.Range) {
                Column.Range column$range = (Column.Range)optional.get();
                if (column$range.height() < 4) {
                    return false;
                } else {
                    int i = (int)((float)column$range.height() * LargePillarConfiguration.maxColumnRadiusToCaveHeightRatio);
                    int j = Mth.clamp(i, LargePillarConfiguration.columnRadius.getMinValue(), LargePillarConfiguration.columnRadius.getMaxValue());
                    int k = Mth.randomBetweenInclusive(randomsource, LargePillarConfiguration.columnRadius.getMinValue(), j);
                    LargePillar LargePillarFeature$largepillar = makePillar(
                            blockpos.atY(column$range.ceiling() - 1),
                            false,
                            randomsource,
                            k,
                            LargePillarConfiguration.stalactiteBluntness,
                            LargePillarConfiguration.heightScale
                    );
                    LargePillar LargePillarFeature$largepillar1 = makePillar(
                            blockpos.atY(column$range.floor() + 1),
                            true,
                            randomsource,
                            k,
                            LargePillarConfiguration.stalagmiteBluntness,
                            LargePillarConfiguration.heightScale
                    );
                    LargePillarFeature.WindOffsetter LargePillarFeature$windoffsetter;
                    if (LargePillarFeature$largepillar.isSuitableForWind(LargePillarConfiguration)
                            && LargePillarFeature$largepillar1.isSuitableForWind(LargePillarConfiguration)) {
                        LargePillarFeature$windoffsetter = new LargePillarFeature.WindOffsetter(
                                blockpos.getY(), randomsource, LargePillarConfiguration.windSpeed
                        );
                    } else {
                        LargePillarFeature$windoffsetter = LargePillarFeature.WindOffsetter.noWind();
                    }

                    boolean flag = LargePillarFeature$largepillar.moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(
                            worldgenlevel, LargePillarFeature$windoffsetter
                    );
                    boolean flag1 = LargePillarFeature$largepillar1.moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(
                            worldgenlevel, LargePillarFeature$windoffsetter
                    );
                    if (flag) {
                        LargePillarFeature$largepillar.placeBlocks(worldgenlevel, randomsource, LargePillarFeature$windoffsetter, LargePillarConfiguration);
                    }

                    if (flag1) {
                        LargePillarFeature$largepillar1.placeBlocks(worldgenlevel, randomsource, LargePillarFeature$windoffsetter, LargePillarConfiguration);
                    }

                    return true;
                }
            } else {
                return false;
            }
        }
    }

    public static boolean isBaseOrLava(BlockState state) {
        return state.is(OrevolutionTags.Blocks.LARGE_PILLAR_REPLACEMENT) || state.is(Blocks.LAVA);
    }

    private static LargePillar makePillar(
            BlockPos root, boolean pointingUp, RandomSource random, int radius, FloatProvider bluntnessBase, FloatProvider scaleBase
    ) {
        return new LargePillar(
                root, pointingUp, radius, (double)bluntnessBase.sample(random), (double)scaleBase.sample(random)
        );
    }

    protected static boolean isEmptyOrWater(LevelAccessor level, BlockPos pos) {
        return level.isStateAtPosition(pos, DripstoneUtils::isEmptyOrWater);
    }

    static final class LargePillar {
        private BlockPos root;
        private final boolean pointingUp;
        private int radius;
        private final double bluntness;
        private final double scale;

        LargePillar(BlockPos root, boolean pointingUp, int radius, double bluntness, double scale) {
            this.root = root;
            this.pointingUp = pointingUp;
            this.radius = radius;
            this.bluntness = bluntness;
            this.scale = scale;
        }

        private int getHeight() {
            return this.getHeightAtRadius(0.0F);
        }

        private int getMinY() {
            return this.pointingUp ? this.root.getY() : this.root.getY() - this.getHeight();
        }

        private int getMaxY() {
            return !this.pointingUp ? this.root.getY() : this.root.getY() + this.getHeight();
        }

        boolean moveBackUntilBaseIsInsideStoneAndShrinkRadiusIfNecessary(WorldGenLevel level, LargePillarFeature.WindOffsetter windOffsetter) {
            while (this.radius > 1) {
                BlockPos.MutableBlockPos blockpos$mutableblockpos = this.root.mutable();
                int i = Math.min(10, this.getHeight());

                for (int j = 0; j < i; j++) {
                    if (level.getBlockState(blockpos$mutableblockpos).is(Blocks.LAVA)) {
                        return false;
                    }

                    if (isCircleMostlyEmbeddedInStone(level, windOffsetter.offset(blockpos$mutableblockpos), this.radius)) {
                        this.root = blockpos$mutableblockpos;
                        return true;
                    }

                    blockpos$mutableblockpos.move(this.pointingUp ? Direction.DOWN : Direction.UP);
                }

                this.radius /= 2;
            }

            return false;
        }

        protected static boolean isCircleMostlyEmbeddedInStone(WorldGenLevel level, BlockPos pos, int radius) {
            if (isEmptyOrWaterOrLava(level, pos)) {
                return false;
            } else {
                float f = 6.0F;
                float f1 = 6.0F / (float)radius;

                for (float f2 = 0.0F; f2 < (float) (Math.PI * 2); f2 += f1) {
                    int i = (int)(Mth.cos(f2) * (float)radius);
                    int j = (int)(Mth.sin(f2) * (float)radius);
                    if (isEmptyOrWaterOrLava(level, pos.offset(i, 0, j))) {
                        return false;
                    }
                }

                return true;
            }
        }

        protected static boolean isEmptyOrWaterOrLava(LevelAccessor level, BlockPos pos) {
            return level.isStateAtPosition(pos, DripstoneUtils::isEmptyOrWaterOrLava);
        }

        private int getHeightAtRadius(float radius) {
            return (int)getPillarHeight((double)radius, (double)this.radius, this.scale, this.bluntness);
        }

        protected static double getPillarHeight(double radius, double maxRadius, double scale, double minRadius) {
            if (radius < minRadius) {
                radius = minRadius;
            }

            double d0 = 0.384;
            double d1 = radius / maxRadius * 0.384;
            double d2 = 0.75 * Math.pow(d1, 1.3333333333333333);
            double d3 = Math.pow(d1, 0.6666666666666666);
            double d4 = 0.3333333333333333 * Math.log(d1);
            double d5 = scale * (d2 - d3 - d4);
            d5 = Math.max(d5, 0.0);
            return d5 / 0.384 * maxRadius;
        }

        void placeBlocks(WorldGenLevel level, RandomSource random, LargePillarFeature.WindOffsetter windOffsetter, LargePillarFeature.LargePillarConfiguration configuration) {
            for (int i = -this.radius; i <= this.radius; i++) {
                for (int j = -this.radius; j <= this.radius; j++) {
                    float f = Mth.sqrt((float)(i * i + j * j));
                    if (!(f > (float)this.radius)) {
                        int k = this.getHeightAtRadius(f);
                        if (k > 0) {
                            if ((double)random.nextFloat() < 0.2) {
                                k = (int)((float)k * Mth.randomBetween(random, 0.8F, 1.0F));
                            }

                            BlockPos.MutableBlockPos blockpos$mutableblockpos = this.root.offset(i, 0, j).mutable();
                            boolean flag = false;
                            int l = this.pointingUp
                                    ? level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, blockpos$mutableblockpos.getX(), blockpos$mutableblockpos.getZ())
                                    : Integer.MAX_VALUE;

                            for (int i1 = 0; i1 < k && blockpos$mutableblockpos.getY() < l; i1++) {
                                BlockPos blockpos = windOffsetter.offset(blockpos$mutableblockpos);
                                if (isEmptyOrWaterOrLava(level, blockpos)) {
                                    flag = true;
                                    level.setBlock(blockpos, configuration.blockStateProvider.getState(random, blockpos), 2);
                                } else if (flag && level.getBlockState(blockpos).is(BlockTags.BASE_STONE_OVERWORLD)) {
                                    break;
                                }

                                blockpos$mutableblockpos.move(this.pointingUp ? Direction.UP : Direction.DOWN);
                            }
                        }
                    }
                }
            }
        }

        boolean isSuitableForWind(LargePillarConfiguration config) {
            return this.radius >= config.minRadiusForWind && this.bluntness >= (double)config.minBluntnessForWind;
        }
    }

    static final class WindOffsetter {
        private final int originY;
        @Nullable
        private final Vec3 windSpeed;

        WindOffsetter(int originY, RandomSource random, FloatProvider magnitude) {
            this.originY = originY;
            float f = magnitude.sample(random);
            float f1 = Mth.randomBetween(random, 0.0F, (float) Math.PI);
            this.windSpeed = new Vec3((double)(Mth.cos(f1) * f), 0.0, (double)(Mth.sin(f1) * f));
        }

        private WindOffsetter() {
            this.originY = 0;
            this.windSpeed = null;
        }

        static LargePillarFeature.WindOffsetter noWind() {
            return new LargePillarFeature.WindOffsetter();
        }

        BlockPos offset(BlockPos pos) {
            if (this.windSpeed == null) {
                return pos;
            } else {
                int i = this.originY - pos.getY();
                Vec3 vec3 = this.windSpeed.scale((double)i);
                return pos.offset(Mth.floor(vec3.x), 0, Mth.floor(vec3.z));
            }
        }
    }

    public record LargePillarConfiguration(int floorToCeilingSearchRange, IntProvider columnRadius,
                                           FloatProvider heightScale, float maxColumnRadiusToCaveHeightRatio,
                                           FloatProvider stalactiteBluntness, FloatProvider stalagmiteBluntness,
                                           FloatProvider windSpeed, int minRadiusForWind,
                                           float minBluntnessForWind, BlockStateProvider blockStateProvider) implements FeatureConfiguration {
            public static final Codec<LargePillarConfiguration> CODEC = RecordCodecBuilder.create(
                    p_160966_ -> p_160966_.group(
                                    Codec.intRange(1, 512).fieldOf("floor_to_ceiling_search_range").orElse(30).forGetter(p_160984_ -> p_160984_.floorToCeilingSearchRange),
                                    IntProvider.codec(1, 60).fieldOf("column_radius").forGetter(p_160982_ -> p_160982_.columnRadius),
                                    FloatProvider.codec(0.0F, 20.0F).fieldOf("height_scale").forGetter(p_160980_ -> p_160980_.heightScale),
                                    Codec.floatRange(0.1F, 1.0F)
                                            .fieldOf("max_column_radius_to_cave_height_ratio")
                                            .forGetter(p_160978_ -> p_160978_.maxColumnRadiusToCaveHeightRatio),
                                    FloatProvider.codec(0.1F, 10.0F).fieldOf("stalactite_bluntness").forGetter(p_160976_ -> p_160976_.stalactiteBluntness),
                                    FloatProvider.codec(0.1F, 10.0F).fieldOf("stalagmite_bluntness").forGetter(p_160974_ -> p_160974_.stalagmiteBluntness),
                                    FloatProvider.codec(0.0F, 2.0F).fieldOf("wind_speed").forGetter(p_160972_ -> p_160972_.windSpeed),
                                    Codec.intRange(0, 100).fieldOf("min_radius_for_wind").forGetter(p_160970_ -> p_160970_.minRadiusForWind),
                                    Codec.floatRange(0.0F, 5.0F).fieldOf("min_bluntness_for_wind").forGetter(p_160968_ -> p_160968_.minBluntnessForWind),
                                    BlockStateProvider.CODEC.fieldOf("blockstate_provider").forGetter(p_158323_ -> p_158323_.blockStateProvider)
                            )
                            .apply(p_160966_, LargePillarConfiguration::new)
            );
    }
}