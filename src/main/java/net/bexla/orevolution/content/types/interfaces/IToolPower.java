package net.bexla.orevolution.content.types.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public interface IToolPower {
    IToolPower EMPTY = new IToolPower() {};

    /** Called when the entity attack an entity with the item.
     * @param stack The item stack.
     * @param target The target entity that's being attacked.
     * @param attacker The attacking entity that's attacking the target, usually the player.
     */
    default float onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker, DamageSource source, float dmgAmount) {return dmgAmount;}

    /** Called when the entity mines a block with the item.
     * @param stack The item stack.
     * @param level The level.
     * @param pos The block position.
     * @param entity The entity mining the block.
     * @param state The block state.
     */
    default boolean onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity entity, BlockState state) {return false;}

    default boolean onDropXPBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity entity, BlockState state, int xpToDrop) {return false;}

    /** Called when the entity has the item in inventory (including when held).
     * @param stack The item stack.
     * @param level The level.
     * @param entity The entity.
     * @param slot The slot index.
     * @param selected Whether the item is selected.
     */
    default void onInventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {}

    /** Called when the player hovers the item.
     * adds tooltip lines to the item.
     * @param stack The item stack.
     */
    default List<Component> appendTooltip(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        return List.of();
    }

    /** Called when the entity uses the item (mining or attacking).
     * return true to override the default behavior (like mining speed or damage).
     * @param stack The item stack.
     * @param level The level.
     * @param player The player using the item.
     * @return true to override the default behavior.
     */
    default boolean onUseOverride(ItemStack stack, Level level, LivingEntity player) {return false;}

    /**
     * Sets the destroy speed of the item.
     * @param stack The item stack.
     * @param state The block state being destroyed.
     * @param defaultSpeed The default destroy speed of the item.
     * @return The modified destroy speed.
     */
    default float setDestroySpeed(ItemStack stack, BlockState state, float defaultSpeed) {return defaultSpeed;}

    /**
     * Sets the max durability (uses) of the item.
     * @param stack The item stack.
     * @param defaultUses The default max durability (uses) of the item.
     * @return The modified durability (uses).
     */
    default int setMaxUses(ItemStack stack, int defaultUses) {return defaultUses;}

    /**
     * Sets the attack damage of the item.
     * @param stack The item stack.
     * @param defaultDamage The default attack damage of the item.
     * @return The modified attack damage.
     */
    default float setAttackDamage(ItemStack stack, float defaultDamage) {return defaultDamage;}
}