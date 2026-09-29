package net.bexla.orevolution.content.types.item;

import net.bexla.orevolution.events.BronzeTotemEvents;
import net.bexla.orevolution.init.RegDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.Unit;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class BronzeTotemItem extends Item {
    public static final List<DataComponentType<Unit>> COMPONENTS = List.of(
            RegDataComponents.AMETHYST_TOTEM.get(),
            RegDataComponents.CELESTITE_TOTEM.get(),
            RegDataComponents.EMERALD_TOTEM.get(),
            RegDataComponents.QUARTZ_TOTEM.get(),
            RegDataComponents.LAPIS_TOTEM.get(),
            RegDataComponents.DIAMOND_TOTEM.get()
    );

    public BronzeTotemItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        MutableComponent component = Component.translatable("item.orevolution.totem.socket.empty")
                .withStyle(ChatFormatting.DARK_GRAY);

        boolean hide = false;

        if (stack.has(RegDataComponents.DIAMOND_TOTEM.get())) {
            component = Component.translatable("item.orevolution.totem.socket.diamond")
                    .withStyle(ChatFormatting.AQUA);
        }

        if (stack.has(RegDataComponents.LAPIS_TOTEM.get())) {
            component = Component.translatable("item.orevolution.totem.socket.lapis")
                    .withStyle(ChatFormatting.BLUE);
        }

        if (stack.has(RegDataComponents.EMERALD_TOTEM.get())) {
            component = Component.translatable("item.orevolution.totem.socket.emerald")
                    .withStyle(ChatFormatting.GREEN);
        }

        if (stack.has(RegDataComponents.QUARTZ_TOTEM.get())) {
            component = Component.translatable("item.orevolution.totem.socket.quartz")
                    .withStyle(ChatFormatting.WHITE);
        }

        if (stack.has(RegDataComponents.CELESTITE_TOTEM.get())) {
            component = Component.translatable("item.orevolution.totem.socket.celestite")
                    .withStyle(ChatFormatting.AQUA);
            hide = true;
        }

        if (stack.has(RegDataComponents.AMETHYST_TOTEM.get())) {
            component = Component.translatable("item.orevolution.totem.socket.amethyst")
                    .withStyle(ChatFormatting.LIGHT_PURPLE);
            hide = true;
        }

        tooltipComponents.add(component);
        if(!hide) {
            tooltipComponents.add(Component.translatable("item.orevolution.totem.hotbar").withStyle(ChatFormatting.DARK_GRAY));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        boolean used = false;

        if (stack.has(RegDataComponents.CELESTITE_TOTEM.get())) {
            player.addEffect(new MobEffectInstance(
                    MobEffects.NIGHT_VISION,
                    4800,
                    0
            ));

            used = true;
        }

        if (stack.has(RegDataComponents.AMETHYST_TOTEM.get())) {
            player.addEffect(new MobEffectInstance(
                    MobEffects.DIG_SPEED,
                    4800,
                    0
            ));

            used = true;
        }

        if (used) {
            BronzeTotemEvents.damageTotem(50, player, stack, hand);
        }

        return used ? InteractionResultHolder.sidedSuccess(stack, level.isClientSide()) : InteractionResultHolder.pass(stack);
    }

    @Override
    public void onDestroyed(ItemEntity entity, DamageSource damageSource) {
        ItemStack stack = entity.getItem();

        if (getTotemSocket(stack) != -1) {
            stack.remove(COMPONENTS.get(getTotemSocket(stack)));
        }
    }

    public static int getTotemSocket(ItemStack stack) {
        for (int i = 0; i < COMPONENTS.size(); i++) {
            if (stack.has(COMPONENTS.get(i))) {
                return i;
            }
        }

        return -1;
    }
}