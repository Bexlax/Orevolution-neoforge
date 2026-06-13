package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ToolMultiBreaking extends OrevolutionToolPower {

    public ToolMultiBreaking(String tooltip_id, IConditional conditional) {
        super(tooltip_id, conditional);
    }

    @Override
    public MutableComponent ctrlTooltip() {
        return OrevolutionConfig.COMMON.steelEfficiencyNerf.get() ? Component.translatable("tooltip.orevolution." + getTooltipID() + "_explanation") : null;
    }

    @Override
    public boolean onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity player, BlockState state) {
        if (!getCBoolean(stack, state, level, player, null)) return super.onMineBlock(stack, level, pos, player, state);

        Direction facing = player.getDirection();
        boolean vertical = Math.abs(player.getXRot()) > 36;

        BlockPos.MutableBlockPos offsetPos = new BlockPos.MutableBlockPos();

        if(!(stack.getItem() instanceof TieredItem tieredItem)) return super.onMineBlock(stack, level, pos, player, state);

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;

                int ox = 0, oy = 0, oz = 0;

                if (vertical) {
                    ox = dx;
                    oz = dy;
                }
                else if (facing.getAxis() == Direction.Axis.X) {
                    oy = dy;
                    oz = dx;
                }
                else if (facing.getAxis() == Direction.Axis.Z) {
                    oy = dy;
                    ox = dx;
                }

                offsetPos.set(pos.getX() + ox, pos.getY() + oy, pos.getZ() + oz);
                BlockState targetState = level.getBlockState(offsetPos);

                if (!targetState.isAir() && stack.isCorrectToolForDrops(targetState)) { // Check if the tool can break this block
                    Holder<Enchantment> efficiency = level.registryAccess()
                            .registryOrThrow(Registries.ENCHANTMENT)
                            .getHolderOrThrow(Enchantments.EFFICIENCY);

                    if (!offsetPos.equals(pos)) {
                        int efficLevel = stack.getEnchantmentLevel(efficiency);
                        int extradamage = OrevolutionConfig.COMMON.steelEfficiencyNerf.get() && efficLevel > 0 ? (efficLevel * 4) : 1; // if tool has efficiency, increase durability damage by 4 per level
                        level.destroyBlock(offsetPos, true, player);
                        stack.setDamageValue(stack.getDamageValue() + extradamage);
                    }
                }
            }
        }
        return super.onMineBlock(stack, level, pos, player, state);
    }
}
