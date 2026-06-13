package net.bexla.orevolution.content.types.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class VerditeApple extends Item {
    public VerditeApple(Properties p_41383_) {
        super(p_41383_.food((new FoodProperties.Builder()).nutrition(3).saturationModifier(0.1F).alwaysEdible().build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        super.finishUsingItem(stack.copy(), level, entity);

        if (entity instanceof Player player) {
            player.getFoodData().eat(this.getFoodProperties(stack, entity));
        }

        return stack;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }
}
