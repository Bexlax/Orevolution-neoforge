package net.bexla.orevolution.content.worldgen.features;

import com.mojang.serialization.Codec;
import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.init.RegBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class MeteoriteFeature extends Feature<NoneFeatureConfiguration> {

    public MeteoriteFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        if (!OrevolutionConfig.COMMON.generateAetherrockMeteor.get()) return false;

        LevelAccessor level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        int r = 3 + random.nextInt(6);
        double r2 = r * r;
        int inner = Math.max(1, r / 2);

        BlockState meteor = RegBlocks.AETHERROCK.get().defaultBlockState();
        BlockState meteorCore = OrevolutionConfig.COMMON.generateAethersteelOre.get()
                ? RegBlocks.PRIMITIVE_AETHERROCK.get().defaultBlockState()
                : meteor;

        boolean placedAny = false;
        double maxDistSq = r2 * 1.3;

        for (int dx = -r; dx <= r; dx++) {
            for (int dz = -r; dz <= r; dz++) {
                double xzDistSq = dx * dx + dz * dz;
                if (xzDistSq > maxDistSq) continue;

                int yLimit = (int) Math.sqrt(maxDistSq - xzDistSq);
                int yMax = Math.min(r, yLimit);

                for (int dy = -yMax; dy <= yMax; dy++) {
                    int distSq = dx * dx + dy * dy + dz * dz;
                    if (distSq > maxDistSq) continue;

                    double nx = dx + (random.nextDouble() - 0.5) * 0.6;
                    double ny = dy + (random.nextDouble() - 0.5) * 0.6;
                    double nz = dz + (random.nextDouble() - 0.5) * 0.6;

                    double dist = nx * nx + ny * ny * 0.49 + nz * nz;
                    if (dist > r2 * (1.0 + (random.nextDouble() - 0.5) * 0.25)) continue;

                    BlockPos pos = origin.offset(dx, dy, dz);
                    int y = pos.getY();
                    if (y <= 4 || y >= level.getMaxBuildHeight() - 1) continue;

                    BlockState toPlace;
                    if (distSq <= inner * inner) {
                        toPlace = random.nextFloat() < 0.175f ? meteorCore : meteor;
                    } else if (distSq <= (inner + 1) * (inner + 1)
                            && random.nextFloat() < 0.1f) {
                        toPlace = meteorCore;
                    } else {
                        toPlace = meteor;
                    }

                    level.setBlock(pos, toPlace, 2);
                    placedAny = true;
                }
            }
        }

        if (!placedAny) return false;

        int minOffset = r + 1 + random.nextInt(3);
        int maxOffset = r + 6 + random.nextInt(4);
        int floatCount = 4 + random.nextInt(Math.max(1, r * 2));

        for (int i = 0; i < floatCount * 8 && floatCount > 0; i++) {
            int dx = -maxOffset + random.nextInt(maxOffset * 2 + 1);
            int dy = -maxOffset + random.nextInt(maxOffset * 2 + 1);
            int dz = -maxOffset + random.nextInt(maxOffset * 2 + 1);

            int distSq = dx * dx + dy * dy + dz * dz;
            if (distSq < minOffset * minOffset || distSq > maxOffset * maxOffset) continue;

            BlockPos p = origin.offset(dx, dy, dz);
            int y = p.getY();
            if (y <= 4 || y >= level.getMaxBuildHeight() - 1) continue;
            if (!level.getBlockState(p).isAir()) continue;

            boolean nearMeteor = false;
            for (BlockPos n : new BlockPos[]{
                    p.north(), p.south(), p.east(), p.west(), p.above(), p.below()
            }) {
                BlockState ns = level.getBlockState(n);
                if (ns.is(RegBlocks.AETHERROCK.get())
                        || ns.is(RegBlocks.PRIMITIVE_AETHERROCK.get())) {
                    nearMeteor = true;
                    break;
                }
            }
            if (nearMeteor) continue;

            if (random.nextFloat() >= 0.8f) continue;

            int sizeRoll = random.nextInt(100);
            int radius;

            if (sizeRoll < 10) {
                radius = 3 + random.nextInt(2);
            } else if (sizeRoll < 35) {
                radius = 2;
            } else {
                radius = 1;
            }

            double scaleX = 0.65 + random.nextDouble() * 0.8;
            double scaleY = 0.55 + random.nextDouble() * 0.9;
            double scaleZ = 0.65 + random.nextDouble() * 0.8;

            if (random.nextFloat() < 0.3f) {
                int axis = random.nextInt(3);
                if (axis == 0) scaleX *= 1.7;
                else if (axis == 1) scaleY *= 1.7;
                else scaleZ *= 1.7;
            }

            double shapeRadiusSq = radius * radius;

            for (int sx = -radius - 1; sx <= radius + 1; sx++) {
                for (int sy = -radius - 1; sy <= radius + 1; sy++) {
                    for (int sz = -radius - 1; sz <= radius + 1; sz++) {
                        double nx = sx / scaleX;
                        double ny = sy / scaleY;
                        double nz = sz / scaleZ;

                        double distance = nx * nx + ny * ny + nz * nz;
                        double irregularity = 1.0 + (random.nextDouble() - 0.5) * 0.45;

                        if (distance > shapeRadiusSq * irregularity) continue;

                        BlockPos chunkPos = p.offset(sx, sy, sz);
                        int chunkY = chunkPos.getY();

                        if (chunkY <= 4 || chunkY >= level.getMaxBuildHeight() - 1) continue;
                        if (!level.getBlockState(chunkPos).isAir()) continue;

                        level.setBlock(chunkPos, meteor, 2);
                    }
                }
            }

            floatCount--;
        }

        return true;
    }
}