package net.bexla.orevolution.content.types.interfaces;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

/**
 * An interface that allows more advanced and dynamic modifications of item properties such as attack damage, attack speed, durability, and more.
 * <br>
 * <br>
 * E.g. you can make every diamond sword have 10 attack damage instead of 7, make every iron tiered item have a 50% chance to increase their max uses, etc.
 */
public interface ItemPropertiesModifiers {
    /**
     * Sets the destroy speed of the item.
     * @param stack The item stack.
     * @param state The block state being destroyed.
     * @param defaultSpeed The default destroy speed of the item.
     * @return The modified destroy speed.
     */
    default float setDestroySpeed(ItemStack stack, BlockState state, float defaultSpeed) {return defaultSpeed;}

    /**
     * Sets the toughness of the item.
     * @param stack The item stack.
     * @param defaultToughness The default toughness of the item.
     * @return The modified toughness.
     */
    default float setToughness(ItemStack stack, float defaultToughness) {return defaultToughness;}

    /**
     * Sets the attack damage of the item.
     * @param stack The item stack.
     * @param defaultDamage The default attack damage of the item.
     * @return The modified attack damage.
     */
    default float setAttackDamage(ItemStack stack, float defaultDamage) {return defaultDamage;}

    /**
     * Sets the max durability (uses) of the item.
     * @param stack The item stack.
     * @param defaultUses The default max durability (uses) of the item.
     * @return The modified durability (uses).
     */
    default int setMaxUses(ItemStack stack, int defaultUses) {return defaultUses;}

    /**
     * Sets the max defense of the item.
     * @param stack The item stack.
     * @param defaultDefense The default defense of the item.
     * @return The modified defense.
     */
    default int setMaxDefense(ItemStack stack, int defaultDefense) {return defaultDefense;}

    /**
     * Sets the valid repair of the item. (Tiered/armor item only)
     * @param stack The item stack.
     * @param ingredient The item used to repair this item.
     * @param defaultRepairItem The default repair item.
     * @return The modified repair item.
     */
    default boolean setValidRepairItem(ItemStack stack, ItemStack ingredient, boolean defaultRepairItem) {return defaultRepairItem;}

    /**
     * Sets the correct tool required to harvest a block
     * @param stack The item stack.
     * @param state The state of the mined block.
     * @return The modified attributes.
     */
    default boolean setCorrectToolForDrops(ItemStack stack, BlockState state, boolean defaultCorrectTool) {return defaultCorrectTool;}

    /**
     * Sets the default attributes of an item (Tiered/armor item only)
     * @param slot The armor slot.
     * @param defaultAttributes The default attributes.
     * @return The modified attributes.
     */
    default Multimap<Attribute, AttributeModifier> setDefaultAttributeModifiers(EquipmentSlot slot, Multimap<Attribute, AttributeModifier> defaultAttributes) {return defaultAttributes;}
}
