package net.bexla.orevolution.mixins;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import net.bexla.orevolution.OrevolutionClient;
import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.data.powers.tools.ToolExpandHarvestArea;
import net.bexla.orevolution.content.interfaces.IToolPower;
import net.bexla.orevolution.content.types.ItemPowerRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.BlockDestructionProgress;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.*;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {

    private static final Set<Integer> ACTIVE_FAKE_IDS = new HashSet<>();

    private static void clearFakeProgress(Long2ObjectMap<SortedSet<BlockDestructionProgress>> map) {
        for (SortedSet<BlockDestructionProgress> set : map.values()) {
            set.removeIf(progress ->
                    ACTIVE_FAKE_IDS.contains(progress.getId()));
        }

        map.values().removeIf(SortedSet::isEmpty);
        ACTIVE_FAKE_IDS.clear();
    }

    @Inject(
            method = "destroyBlockProgress",
            at = @At("TAIL")
    )
    private void orevolution$addAoECrackProgress(int breakerId, BlockPos centerPos, int progress, CallbackInfo ci) {
        if(!OrevolutionConfig.CLIENT.steel_outline.get()) return;

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        Level level = mc.level;

        if (player == null || level == null) return;

        if (breakerId != player.getId()) return;

        ItemStack stack = player.getMainHandItem();

        if (player.isCreative() || player.isSpectator()) return;
        if (!(stack.getItem() instanceof TieredItem tieredItem)) return;

        IToolPower tpower = ItemPowerRegistry.getPowerForItem(stack);

        // check if item's power should be affected by this visual change
        if (!(tpower instanceof ToolExpandHarvestArea)) return;

        Direction face = mc.hitResult instanceof BlockHitResult bhr ? bhr.getDirection() : Direction.UP;
        BoundingBox box = OrevolutionClient.getAreaOfEffect(centerPos, face);

        LevelRenderer renderer = (LevelRenderer)(Object)this;
        LevelRendererAccessor accessor = (LevelRendererAccessor) renderer;
        Long2ObjectMap<SortedSet<BlockDestructionProgress>> destructionMap = accessor.orevolution$getDestructionProgress();

        clearFakeProgress(destructionMap);

        for (BlockPos pos : BlockPos.betweenClosed(
                box.minX(), box.minY(), box.minZ(),
                box.maxX(), box.maxY(), box.maxZ()
        )) {
            var incorrectFor = tieredItem.getTier().getIncorrectBlocksForDrops();

            BlockState state = level.getBlockState(pos);

            if (state.is(incorrectFor)) continue;
            if (!stack.isCorrectToolForDrops(state)) continue;
            if (state.getDestroySpeed(level, pos) > level.getBlockState(centerPos).getDestroySpeed(level, centerPos)) continue;

            if (pos.equals(centerPos)) {
                continue;
            }

            int fakeId = Objects.hash(
                    breakerId,
                    pos.getX(),
                    pos.getY(),
                    pos.getZ()
            );

            ACTIVE_FAKE_IDS.add(fakeId);

            long key = pos.asLong();

            if (progress < 0) {
                SortedSet<BlockDestructionProgress> set = destructionMap.get(key);

                if (set != null) {
                    set.removeIf(p -> p.getId() == fakeId);

                    if (set.isEmpty()) {
                        destructionMap.remove(key);
                    }
                }

                continue;
            }

            SortedSet<BlockDestructionProgress> set = destructionMap.computeIfAbsent(key, k -> new TreeSet<>());
            set.removeIf(p -> p.getId() == fakeId);
            BlockDestructionProgress fake =
                    new BlockDestructionProgress(fakeId, pos);

            fake.setProgress(progress);
            set.add(fake);
        }
    }
}