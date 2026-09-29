package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class ToolExpandHarvestArea extends OrevolutionToolPower {
    private final int radius;

    public ToolExpandHarvestArea(String tooltip_id, IConditional conditional, int radius) {
        super(tooltip_id, conditional);
        this.radius = radius;
    }

    @Override
    public List<MutableComponent> ctrlTooltip() {
        return OrevolutionConfig.COMMON.steelEfficiencyNerf.get() ? List.of(Component.translatable("power.orevolution." + tooltip_id + "_explanation")) : null;
    }

    @Override
    public boolean onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity entity, BlockState state) {
        if (!(entity instanceof Player player)) return super.onMineBlock(stack, level, pos, entity, state);
        if (player.isCreative()) return super.onMineBlock(stack, level, pos, player, state);
        if (!getCondition(stack, state, level, player, null)) return super.onMineBlock(stack, level, pos, player, state);

        if (!(stack.getItem() instanceof TieredItem)) return super.onMineBlock(stack, level, pos, player, state);

        HitResult hit = player.pick(20.0D, 0.0F, false);
        if (!(hit instanceof BlockHitResult blockHit)) {
            return super.onMineBlock(stack, level, pos, player, state);
        }

        Direction face = blockHit.getDirection();

        BlockPos.MutableBlockPos targetPos = new BlockPos.MutableBlockPos();

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                if (dx == 0 && dy == 0) continue;

                int ox = 0, oy = 0, oz = 0;

                switch (face) {
                    case UP, DOWN -> {
                        ox = dx;
                        oz = dy;
                    }
                    case NORTH, SOUTH -> {
                        ox = dx;
                        oy = dy;
                    }
                    case EAST, WEST -> {
                        oz = dx;
                        oy = dy;
                    }
                }

                targetPos.set(pos.getX() + ox, pos.getY() + oy, pos.getZ() + oz);
                BlockState targetState = level.getBlockState(targetPos);

                if (!targetState.isAir() && stack.isCorrectToolForDrops(targetState) && targetState.getDestroySpeed(level, targetPos) <= state.getDestroySpeed(level, pos)) {
                    Holder<Enchantment> efficiency = level.registryAccess()
                            .registryOrThrow(Registries.ENCHANTMENT)
                            .getHolderOrThrow(Enchantments.EFFICIENCY);

                    int efficLevel = stack.getEnchantmentLevel(efficiency);
                    int extradamage = OrevolutionConfig.COMMON.steelEfficiencyNerf.get() && efficLevel > 0
                            ? (efficLevel * 4)
                            : 1;

                    level.destroyBlock(targetPos, false, player);
                    targetState.getBlock().playerDestroy(level, player, targetPos, targetState, null, stack);
                    stack.setDamageValue(stack.getDamageValue() + extradamage);
                }
            }
        }

        return super.onMineBlock(stack, level, pos, player, state);
    }
}
