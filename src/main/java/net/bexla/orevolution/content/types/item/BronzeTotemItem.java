package net.bexla.orevolution.content.types.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class BronzeTotemItem extends Item {
    private final Holder<MobEffect> effect;
    private final String id;
    private final ChatFormatting format;

    public BronzeTotemItem(Properties p_40524_, Holder<MobEffect> effect, String id, ChatFormatting format) {
        super(p_40524_);
        this.effect = effect;
        this.id = id;
        this.format = format;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.orevolution.totem_" + id, effect.value().getDisplayName().getString()).withStyle(format));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int idkwhat, boolean theseare) {
        if(!(entity instanceof Player player)) return;

        if(player.getOffhandItem().is(this) || player.getMainHandItem().is(this)) {
            if(player.hasEffect(effect)) {
                player.getEffect(effect).update(new MobEffectInstance(effect,20, 0));
            }
            else {
                player.addEffect(new MobEffectInstance(effect, 20, 0));
            }
        }
    }
}
