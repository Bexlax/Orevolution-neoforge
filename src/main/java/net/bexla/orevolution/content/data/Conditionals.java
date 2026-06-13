package net.bexla.orevolution.content.data;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.fluids.FluidType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/** A utility class providing various predefined conditionals for use in the Orevolution mod.
 * These conditionals can be used to determine whether certain effects or powers should activate
 * based on specific criteria such as chance, entity type, player health, and environmental conditions.
 */
public class Conditionals {
    /** IConditional that activates based on a given chance.
     *
     * @param chance The chance (between 0.0 and 1.0) for the conditional to activate.
     * @return A IConditional that activates based on the specified chance.
     */
    @Contract(pure = true)
    public static @NotNull IConditional byChance(double chance) {
        return (stack, state, level, player, possibleTarget) -> level.getRandom().nextDouble() < chance;
    }

    @Contract(pure = true)
    public static @NotNull IConditional config(ModConfigSpec.ConfigValue<Boolean> configValue) {
        return (stack, state, level, player, possibleTarget) -> configValue.get();
    }

    /** IConditional that activates if the target entity's current health percentage is less than or equal to the specified percentage.
     *
     * @param percentage The health percentage threshold (between 0.0 and 1.0).
     * @return A IConditional that activates if the target entity's health percentage is below or equal to the specified value.
     */
    @Contract(pure = true)
    public static @NotNull IConditional targetHPPercentage(float percentage) {
        return (stack, state, level, player, possibleTarget) -> possibleTarget != null && (possibleTarget.getHealth() / possibleTarget.getMaxHealth()) <= percentage;
    }

    @Contract(pure = true)
    public static @NotNull IConditional playerDimension(ResourceKey<Level> dimension) {
        return (stack, state, level, player, possibleTarget) -> {
            if(player != null) return false;
            ResourceKey<Level> dimensionKey = player.level().dimension();

            return dimensionKey == dimension;
        };
    }

    @Contract(pure = true)
    public static @NotNull IConditional targetHasArmor() {
        return (stack, state, level, player, possibleTarget) -> possibleTarget != null && possibleTarget.getArmorValue() > 0;
    }

    /** Returns a conditional that activates if the target entity's current health amount is less than or equal to the specified amount.
     *
     * @param amount The health amount threshold.
     * @return A IConditional that activates if the target entity's health amount is below or equal to the specified value.
     */
    @Contract(pure = true)
    public static @NotNull IConditional targetHPAmount(float amount) {
        return (stack, state, level, player, possibleTarget) -> possibleTarget != null && possibleTarget.getHealth() <= amount;
    }


    /** IConditional that activates if the target entity is of the specified MobType.
     *
     * @param type The MobType to check against the target entity.
     * @return A IConditional that activates if the target entity matches the specified MobType.
     */
    @Contract(pure = true)
    public static @NotNull IConditional targetMobType(TagKey<EntityType<?>> type) {
        return (stack, state, level, player, possibleTarget) -> possibleTarget != null && possibleTarget.getType().is(type);
    }

    /** IConditional that activates if the player is submerged in a fluid matching the specified fluid tag.
     *
     * @param fluidType The Type of fluid to check for submersion.
     * @return A IConditional that activates if the player is submerged in the specified fluid.
     */
    @Contract(pure = true)
    public static @NotNull IConditional isSubmergedInLiquid(Holder<FluidType> fluidType) {
        return (stack, state, level, player, target) -> player.isEyeInFluidType(fluidType.value());
    }

    /** IConditional that activates if the player is touching lava
     *
     * @return A IConditional that activates if the player is submerged in the specified fluid.
     */
    @Contract(pure = true)
    public static @NotNull IConditional isTouchingLava() {
        return (stack, state, level, player, target) -> player.isInLava();
    }

    /** IConditional that activates if the player's current health percentage is less than or equal to the specified percentage.
     *
     * @param percentage The health percentage threshold (between 0.0 and 1.0).
     * @return A IConditional that activates if the player's health percentage is below or equal to the specified value.
     */
    @Contract(pure = true)
    public static @NotNull IConditional currentHPPercentage(float percentage) {
        return (stack, state, level, player, possibleTarget) -> (player.getHealth() / player.getMaxHealth()) <= percentage;
    }

    /** IConditional that activates if the item has the specified enchantment
     *
     * @param enchantment The enchantment required
     * @return A IConditional that activates if the item has the specified enchantment
     */
    @Contract(pure = true)
    public static IConditional toolHasEnchantment(Holder<Enchantment> enchantment) {
        return (stack, state, level, player, possibleTarget) -> stack.getTagEnchantments().getLevel(enchantment) > 0;
    }

    /** IConditional that activates if the item has the specified enchantment
     */
    @Contract(pure = true)
    public static @NotNull IConditional toolHasAnyEnchantment() {
        return (stack, state, level, player, possibleTarget) -> !stack.getTagEnchantments().isEmpty();
    }

    /** Returns a conditional that activates if the player's current health amount is less than or equal to the specified amount.
     *
     * @param amount The health amount threshold.
     * @return A IConditional that activates if the player's health amount is below or equal to the specified value.
     */
    @Contract(pure = true)
    public static @NotNull IConditional currentHPAmount(int amount) {
        return (stack, state, level, player, possibleTarget) -> player.getHealth() <= amount;
    }

    /** Returns a conditional that activates if the player is currently receiving daylight
     *
     * @return A IConditional that activates if the player can see the sky and is currently daytime.
     */
    @Contract(pure = true)
    public static @NotNull IConditional isReceivingDayLight() {
        return (stack, state, level, player, possibleTarget) -> level.canSeeSky(player.blockPosition()) && level.isDay();
    }

    /** Returns a conditional that activates if the blockstate matches the specified block tag.
     *
     * @param blockTag The TagKey of the block to check for submersion.
     * @return A IConditional that activates if the player is submerged in the specified fluid.
     */
    @Contract(pure = true)
    public static @NotNull IConditional isBlockstateTaggedAs(TagKey<Block> blockTag) {
        return (stack, state, level, player, possibleTarget) -> state != null && state.is(blockTag);
    }

    @Contract(pure = true)
    public static @NotNull IConditional isBlockstateTaggedAs(Supplier<Block> block) {
        return (stack, state, level, player, possibleTarget) -> state != null && state.is(block.get());
    }

    @Contract(pure = true)
    public static @NotNull IConditional and(IConditional a, IConditional b) {
        return (stack, state, level, player, possibleTarget) -> a.shouldActivate(stack, state, level, player, possibleTarget) && b.shouldActivate(stack, state, level, player, possibleTarget);
    }

    @Contract(pure = true)
    public static @NotNull IConditional or(IConditional a, IConditional b) {
        return (stack, state, level, player, possibleTarget) -> a.shouldActivate(stack, state, level, player, possibleTarget) || b.shouldActivate(stack, state, level, player, possibleTarget);
    }

    @Contract(pure = true)
    public static @NotNull IConditional listConditionals(IConditional... conditionals) {
        for(IConditional conditional : conditionals) {
            return conditional;
        }
        return (stack, state, level, player, possibleTarget) -> false;
    }

    @Contract(pure = true)
    public static @NotNull IConditional not(IConditional a) {
        return (stack, state, level, player, possibleTarget) -> !a.shouldActivate(stack, state, level, player, possibleTarget);
    }

    /** IConditional that always activates.
     */
    @Contract(pure = true)
    public static @NotNull IConditional always() {
        return (stack, state, level, player, possibleTarget) -> true;
    }
}