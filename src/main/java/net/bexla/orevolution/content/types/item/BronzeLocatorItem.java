package net.bexla.orevolution.content.types.item;

import net.bexla.orevolution.init.RegDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class BronzeLocatorItem extends Item {
    public BronzeLocatorItem(Properties p_41383_) {
        super(p_41383_);
    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.orevolution.bronze_radar.tooltip").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (entity instanceof Player player) {
            if (stack == player.getMainHandItem() || stack == player.getOffhandItem()) {

                String value;

                if (getMode(stack).equals("normal_mode")) {
                    BlockPos pos = player.blockPosition();
                    value = " X: " + pos.getX() + " Z: " + pos.getZ();
                } else {

                    String targetName = stack.getOrDefault(RegDataComponents.LOCATOR_PLAYER.get(), "No Target");

                    Player target = level.players().stream()
                            .filter(p -> p.getName().getString().equals(targetName))
                            .findFirst()
                            .orElse(null);

                    if (target != null) {
                        BlockPos pos = target.blockPosition();
                        value = targetName + ": " + pos.getX() + " " + pos.getY() + " " + pos.getZ();
                    } else {
                        value = targetName;
                    }
                }

                player.displayClientMessage(
                        Component.translatable(
                                "actionbar.orevolution.bronze_radar_" + getMode(stack),
                                value
                        ),
                        true
                );
            }
        }
    }

    public String getMode(ItemStack stack) {
        return stack.getOrDefault(RegDataComponents.LOCATOR_MODE, false)? "friend_mode" : "normal_mode";
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (player.isShiftKeyDown()) {

            if (stack.getOrDefault(RegDataComponents.LOCATOR_MODE, false)) {
                stack.set(RegDataComponents.LOCATOR_MODE, true);
            } else {
                stack.set(RegDataComponents.LOCATOR_MODE, false);
            }

            return InteractionResultHolder.success(stack);
        }

        if (stack.getOrDefault(RegDataComponents.LOCATOR_MODE, true)) {
            playersInOrder(level, stack, player);
            return InteractionResultHolder.success(stack);
        }

        return InteractionResultHolder.fail(stack);
    }

    private void playersInOrder(Level level, ItemStack stack, Player player) {

        List<? extends Player> players = level.players().stream()
                .filter(p -> p != player)
                .toList();

        if (!players.isEmpty()) {
            int index = stack.getOrDefault(RegDataComponents.LOCATOR_PLAYER_INDEX, 0);

            if (index >= players.size()) {
                index = 0;
            }

            Player selected = players.get(index);

            stack.set(RegDataComponents.LOCATOR_PLAYER, selected.getGameProfile().getName());

            index++;

            if (index >= players.size()) {
                index = 0;
            }

            stack.set(RegDataComponents.LOCATOR_PLAYER_INDEX, index);

            if (!level.isClientSide) {
                player.sendSystemMessage(
                        Component.literal("Selected: " + selected.getGameProfile().getName())
                );
            }
        }
    }
}
