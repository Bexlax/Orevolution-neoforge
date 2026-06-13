package net.bexla.orevolution.events;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.types.ArmorPowerRegistry;
import net.bexla.orevolution.content.types.interfaces.IArmorPower;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import net.neoforged.neoforge.event.entity.living.LivingKnockBackEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.slf4j.Logger;

import java.util.*;
import java.util.function.Consumer;

import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.getCurrentFullSet;
import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.isWearingFullSet;

@EventBusSubscriber(modid = Orevolution.MODID)
public class OrevolutionArmorPowersSubscriber {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final Map<UUID, Holder<ArmorMaterial>> LAST_SET = new HashMap<>();

    private static void withArmorPower(LivingEntity entity, Consumer<IArmorPower> action) {
        if (!OrevolutionConfig.COMMON.armorsPowers.get()) return;

        ItemStack helmet = entity.getItemBySlot(EquipmentSlot.HEAD);

        Item helItem = helmet.getItem();

        if (!(helItem instanceof ArmorItem armorItem)) return;

        Holder<ArmorMaterial> material = armorItem.getMaterial();
        if (!isWearingFullSet(entity, material)) return;

        IArmorPower power = ArmorPowerRegistry.getPower(material);
        if (power != IArmorPower.EMPTY) {
            action.accept(power);
        }
    }

    @SubscribeEvent
    public static void onEntityTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if(player.level().isClientSide()) return;

        Holder<ArmorMaterial> previous = LAST_SET.get(player.getUUID());
        Holder<ArmorMaterial> current = getCurrentFullSet(player);

        if (previous != null && previous != current) {
            IArmorPower oldPower = ArmorPowerRegistry.getPower(previous);

            if (oldPower != IArmorPower.EMPTY) {
                oldPower.onUnequip(player);
            }
        }

        if (current != null && previous != current) {
            IArmorPower newPower = ArmorPowerRegistry.getPower(current);

            if (newPower != IArmorPower.EMPTY) {
                newPower.onEquip(player);
            }
        }

        withArmorPower(player, power ->
                power.onTickWhileWorn(
                        player.getItemBySlot(EquipmentSlot.HEAD),
                        player,
                        EquipmentSlot.HEAD
                )
        );

        if (current != null) {
            LAST_SET.put(player.getUUID(), current);
        } else {
            LAST_SET.remove(player.getUUID());
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingDamageEvent.Pre event) {
        withArmorPower(event.getEntity(), power -> {
                float newdmg = power.onDamaged(event.getEntity(), event.getSource(), event.getOriginalDamage());
                event.setNewDamage(event.getOriginalDamage() * newdmg);
            }
        );
    }

    @SubscribeEvent
    public static void onLivingAttack(AttackEntityEvent event) {
        withArmorPower(event.getEntity(), power -> {
            if (event.getTarget() instanceof LivingEntity attacker)
                power.onAttackTarget(event.getEntity(), attacker);
        });
    }

    @SubscribeEvent
    public static void onFall(LivingFallEvent event) {
        withArmorPower(event.getEntity(), power ->
                power.onFall(event.getEntity(), event.getDistance(), event.getDamageMultiplier())
        );
    }

    @SubscribeEvent
    public static void onKnockback(LivingKnockBackEvent event) {
        withArmorPower(event.getEntity(), power ->
                power.onKnockback(event.getEntity(), event.getStrength(), event.getRatioX(), event.getRatioZ())
        );
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {
        withArmorPower(event.getEntity(), power -> {
            event.setCanceled(power.onDeath(event.getEntity(), event.getSource()));
        });
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        if(!OrevolutionConfig.CLIENT.armorsPowersTip.get()) return;

        List<Component> tip = new ArrayList<>();

        LivingEntity entity = event.getEntity();

        if (entity == null) return;

        List<Component> tooltip = event.getToolTip();
        ItemStack stack = event.getItemStack();

        if(!(stack.getItem() instanceof ArmorItem item)) return;

        Holder<ArmorMaterial> material = item.getMaterial();

        if (!(item.getType() == ArmorItem.Type.HELMET
                || item.getType() == ArmorItem.Type.CHESTPLATE
                || item.getType() == ArmorItem.Type.LEGGINGS
                || item.getType() == ArmorItem.Type.BOOTS)) return;

        IArmorPower power = ArmorPowerRegistry.getPower(material);

        if(power != IArmorPower.EMPTY) {
            tip.add(0, Component.translatable("tooltip.orevolution.full_set_bonus"));
            tip.addAll(power.appendTooltip(stack, entity.level(), tooltip));
        }

        tooltip.addAll(1, tip);
    }
}
