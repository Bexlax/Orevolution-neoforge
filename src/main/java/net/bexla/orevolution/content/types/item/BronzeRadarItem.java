package net.bexla.orevolution.content.types.item;

import net.bexla.orevolution.OrevolutionConfig;
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
import java.util.UUID;

public class BronzeRadarItem extends Item {
    public BronzeRadarItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.orevolution.bronze_radar.explanation")
                .withStyle(ChatFormatting.DARK_GRAY));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!(entity instanceof Player player))
            return;

        if (!isHeldBy(player, stack))
            return;

        int searchTime = stack.getOrDefault(RegDataComponents.RADAR_SEARCH_TIME.get(), 0);

        if (searchTime > 0) {
            stack.set(RegDataComponents.RADAR_SEARCH_TIME.get(), searchTime - 1);
            displaySearching(player, searchTime);

            if (searchTime > 1) return;

            playersInOrder(level, stack, player);
            return;
        }

        if (player.tickCount % OrevolutionConfig.COMMON.bronzeRadarInterval.get() != 0)
            return;

        if (isFriendMode(stack)) {
            displayTargetPosition(player, stack);
        } else {
            displayOwnPosition(player);
        }
    }

    private void displayOwnPosition(Player player) {
        BlockPos pos = player.blockPosition();

        player.displayClientMessage(
                Component.translatable(
                "actionbar.orevolution.bronze_radar.normal_mode",
                        " x" + pos.getX() + " z" + pos.getZ()
                ), true
        );
    }

    private void displaySearching(Player player, int searchTime) {
        int animation = (OrevolutionConfig.COMMON.bronzeRadarPlayerSearchTime.get() - searchTime) / 5 % 3;

        String dots = switch (animation) {
            case 0 -> ".";
            case 1 -> "..";
            default -> "...";
        };

        player.displayClientMessage(
                Component.translatable(
                        "actionbar.orevolution.bronze_radar.searching",
                        dots
                ),
                true
        );
    }

    private void displayTargetPosition(Player player, ItemStack stack) {
        UUID targetUUID = stack.get(RegDataComponents.RADAR_PLAYER.get());

        if (targetUUID == null) {
            player.displayClientMessage(
                    Component.translatable(
                            "actionbar.orevolution.bronze_radar.friend_mode.no_target"
                    ), true
            );
            return;
        }

        Player target = findPlayer(player.level(), targetUUID);

        if (target == null) {
            player.displayClientMessage(
                    Component.translatable(
                            "actionbar.orevolution.bronze_radar.friend_mode.no_target"
                    ), true
            );
            return;
        }

        BlockPos pos = target.blockPosition();

        String value = target.getGameProfile().getName()
                + ": x" + pos.getX() + " z" + pos.getZ();

        player.displayClientMessage(
                Component.translatable(
                        "actionbar.orevolution.bronze_radar.friend_mode",
                        value
                ), true
        );
    }

    private Player findPlayer(Level level, UUID uuid) {
        return level.players().stream()
                .filter(player -> player.getUUID().equals(uuid))
                .findFirst().orElse(null);
    }

    private boolean isHeldBy(Player player, ItemStack stack) {
        return player.getMainHandItem() == stack || player.getOffhandItem() == stack;
    }

    private boolean isFriendMode(ItemStack stack) {
        return stack.getOrDefault(RegDataComponents.RADAR_MODE.get(), false);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (player.isShiftKeyDown()) {
            stack.set(RegDataComponents.RADAR_MODE.get(), !isFriendMode(stack));
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
        }

        if (!isFriendMode(stack)) return InteractionResultHolder.fail(stack);

        if (stack.getOrDefault(RegDataComponents.RADAR_SEARCH_TIME.get(), 0) > 0) {
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
        }

        stack.set(RegDataComponents.RADAR_SEARCH_TIME.get(), OrevolutionConfig.COMMON.bronzeRadarPlayerSearchTime.get());

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    private void playersInOrder(Level level, ItemStack stack, Player player) {
        List<? extends Player> players = level.players().stream().filter(other -> other != player).toList();

        if (players.isEmpty()) {
            stack.remove(RegDataComponents.RADAR_PLAYER.get());
            stack.set(RegDataComponents.RADAR_PLAYER_INDEX.get(), 0);
            return;
        }

        int index = stack.getOrDefault(RegDataComponents.RADAR_PLAYER_INDEX.get(), 0);

        if (index < 0 || index >= players.size()) {
            index = 0;
        }

        Player target = players.get(index);

        stack.set(RegDataComponents.RADAR_PLAYER.get(), target.getUUID());
        stack.set(RegDataComponents.RADAR_PLAYER_INDEX.get(), (index + 1) % players.size());

        if (!level.isClientSide) {
            player.sendSystemMessage(
                    Component.translatable(
                            "message.orevolution.bronze_radar.selected",
                            target.getGameProfile().getName()
                    ));
        }
    }
}