package net.bexla.orevolution.content.data.powers.tools.hardcoded;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.data.Conditionals;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class SteelTools extends OrevolutionToolPower {

    public SteelTools() {
        super("", Conditionals.always());
    }

    @Override
    public MutableComponent ctrlTooltip() {
        return OrevolutionConfig.COMMON.steelEfficiencyNerf.get() ? Component.translatable("tooltip.orevolution.multi_break_explanation") : null;
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        MutableComponent shiftComponent = ctrlTooltip();
        List<Component> tips = new ArrayList<>();

        if(stack.is(RegItems.STEEL_BROADAXE.get())) {
            tips.add(Component.translatable("tooltip.orevolution.steel_durability").withStyle(ChatFormatting.GREEN));
        }
        else if(stack.is(RegItems.STEEL_HAMMER.get()) || stack.is(RegItems.STEEL_DIGGER.get())) {
            tips.add(Component.translatable("tooltip.orevolution.multi_break").withStyle(ChatFormatting.GREEN));

            if(Screen.hasControlDown()) {
                tips.add(shiftComponent.withStyle(ChatFormatting.DARK_GRAY));
            }
            else {
                tips.add(Component.translatable("tooltip.orevolution.press_key", Component.translatable("key.keyboard.left.control").getString()).withStyle(ChatFormatting.DARK_GRAY));
            }

            tips.add(Component.literal(""));
        }

        return tips;
    }

    @Override
    public boolean onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity entity, BlockState state) {
        if(!(entity instanceof Player player)) return super.onMineBlock(stack, level, pos, entity, state);

        if(stack.is(RegItems.STEEL_HAMMER.get()) || stack.is(RegItems.STEEL_DIGGER.get())) {
            Direction facing = player.getDirection();
            boolean vertical = Math.abs(player.getXRot()) > 36;

            BlockPos.MutableBlockPos offsetPos = new BlockPos.MutableBlockPos();

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
        }

        if(!stack.is(RegItems.STEEL_BROADAXE.get())) return super.onMineBlock(stack, level, pos, entity, state);
        if(player.isCreative()) return super.onMineBlock(stack, level, pos, entity, state);
        if(!stack.isCorrectToolForDrops(state)) return super.onMineBlock(stack, level, pos, entity, state);

        level.removeBlock(pos, false);
        state.getBlock().playerDestroy(level, player, pos, state, null, stack);

        return true;
    }
}
