package net.bexla.orevolution.content.types.interfaces;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * Interface for defining special powers or behaviors for armor items.
 * Default methods allow reacting to various game events when the armor is worn by a living entity.
 */
public interface IArmorPower {
    IArmorPower EMPTY = new IArmorPower() {};

    /**
     * Called every tick while the armor is equipped.
     * @param stack The ItemStack of the armor.
     * @param wearer The entity wearing the armor.
     * @param slot The equipment slot where the armor is equipped.
     */
    default void onTickWhileWorn(ItemStack stack, LivingEntity wearer, EquipmentSlot slot) {}

    /**
     * Called when the wearer is attacked.
     * @param wearer The entity being attacked.
     * @param source The source of damage.
     * @param amount Amount of damage received.
     */
    default float onDamaged(LivingEntity wearer, DamageSource source, float amount) {return amount;}

    /**
     * Called when the wearer attacks another entity.
     * @param wearer The attacking entity.
     * @param target The target entity.
     */
    default void onAttackTarget(LivingEntity wearer, LivingEntity target) {}

    /**
     * Called when the wearer receives knockback.
     * @param wearer The affected entity.
     * @param strength Knockback strength.
     * @param ratioX Knockback ratio on the X axis.
     * @param ratioZ Knockback ratio on the Z axis.
     */
    default void onKnockback(LivingEntity wearer, float strength, double ratioX, double ratioZ) {}

    /**
     * Called when the wearer falls.
     * @param wearer The entity that falls.
     * @param distance Fall distance.
     * @param damageMultiplier Fall damage multiplier.
     */
    default boolean onFall(LivingEntity wearer, float distance, float damageMultiplier) {return false;}

    /**
     * Called when the wearer dies.
     * @param wearer The entity that died.
     * @param source The damage soruce
     */
    default boolean onDeath(LivingEntity wearer, DamageSource source) {return false;}

    default void onEquip(LivingEntity wearer) {}

    default void onUnequip(LivingEntity wearer) {}

    /**
     * Allows adding extra information to the item's tooltip.
     * @param stack The ItemStack of the armor.
     * @param level The current world level.
     * @param lines List of tooltip components.
     */
    default List<Component> appendTooltip(ItemStack stack, Level level, List<Component> lines) {return List.of();}
}
