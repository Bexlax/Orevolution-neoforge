package net.bexla.orevolution.content.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public interface IToolPower {
    IToolPower EMPTY = new IToolPower() {};

    /** Called when the entity successfully damages it's target.
     * @param stack The tool's ItemStack.
     * @param target The target entity that's being attacked.
     * @param attacker The attacking entity that's attacking the target, usually the player.
     */
    default float onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker, DamageSource source, float dmgAmount) {return dmgAmount;}

    /** Called when the player successfully damages it's target WITH a critical strike.
     * @param stack The tool's ItemStack.
     * @param target The target entity that's being attacked.
     * @param player The attacking entity that's attacking the target
     */
    default float onCriticalHit(ItemStack stack, LivingEntity target, Player player, float dmgMultiplier, boolean isCrit) {return dmgMultiplier;}

    /** Called when the entity mines a block with this tool.
     * @param stack The tool's ItemStack.
     * @param level The level.
     * @param pos The block position.
     * @param entity The entity mining the block.
     * @param state The block state.
     * @return false, set it to true to cancel the drops
     */
    default boolean onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity entity, BlockState state) {return false;}

    /** Called when the entity mines a block with this tool.
     * @param stack The tool's ItemStack.
     * @param level The level.
     * @param pos The block position.
     * @param entity The entity mining the block.
     * @param state The block state.
     * @return the drops of the block
     */
    default List<ItemEntity> onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity entity, BlockState state, List<ItemEntity> drops) {return drops;}

    /** Called when the entity mines a block with this tool.
     * @param stack The tool's ItemStack.
     * @param level The level.
     * @param pos The block position.
     * @param entity The entity mining the block.
     * @param state The block state.
     * @return the xp dropped by this block
     */
    default int onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity entity, BlockState state, int xpToDrop) {return xpToDrop;}

    /** Called when the entity has this tool in inventory (including when held).
     * @param stack The tool's ItemStack.
     * @param level The level.
     * @param entity The entity.
     * @param slot The slot index.
     * @param selected Whether this tool is selected.
     */
    default void onInventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {}

    /** Called when the player hovers this tool.
     * adds tooltip lines to this tool.
     * @param stack The tool's ItemStack.
     */
    default List<Component> appendTooltip(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        return List.of();
    }

    /** Called when the entity uses this tool (mining or attacking).
     * return true to override the default behavior.
     * @param stack The tool's ItemStack.
     * @param level The level.
     * @param player The player using this tool.
     */
    default boolean onUse(ItemStack stack, Level level, LivingEntity player) {return false;}

    /** Called when the entity uses this tool on something (ie axes being used to strip wood).
     * return true to override the default behavior.
     * @param stack The tool's ItemStack.
     * @param hand The player's hand that interacted.
     * @param hitResult The hit vector of the block
     * @param player The player using this tool.
     * @param result The original result from the context.
     */
    default InteractionResult useOnContext(ItemStack stack, Player player, InteractionHand hand, BlockHitResult hitResult, InteractionResult result) {return result;}

    /**
     * Sets the destroy speed of this tool.
     * @param stack The tool's ItemStack.
     * @param state The block state being destroyed.
     * @param defaultSpeed The default destroy speed of this tool.
     */
    default float setDestroySpeed(ItemStack stack, BlockState state, float defaultSpeed) {return defaultSpeed;}

    @FunctionalInterface
    interface DamageModifier {
        float modify(ItemStack stack, LivingEntity target, LivingEntity attacker, DamageSource source, float originalDamage);
    }

    @FunctionalInterface
    interface CriticalModifier {
        float modify(ItemStack stack, LivingEntity target, LivingEntity attacker, float originalMultiplier, boolean isCrit);
    }

    @FunctionalInterface
    interface DestroySpeedModifier {
        float modify(ItemStack stack, BlockState state, float originalSpeed);
    }
}