package net.bexla.orevolution.content.interfaces;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.function.Supplier;

@FunctionalInterface
public interface IConditional {
    boolean shouldActivate(ItemStack stack, BlockState state, Level level, LivingEntity player, LivingEntity possibleTarget);

    default MutableComponent value() {
        return null;
    }

    static IConditional byChance(double chance) {
        return new IConditional() {
            @Override
            public boolean shouldActivate(ItemStack stack, BlockState state, Level level, LivingEntity player, LivingEntity possibleTarget) {
                return level.getRandom().nextDouble() < chance;
            }

            @Override
            public MutableComponent value() {
                return Component.translatable("condition.orevolution.chance", (int)((1 - chance) * 100) + "%");
            }
        };
    }

    static IConditional config(ModConfigSpec.ConfigValue<Boolean> configValue) {
        return (stack, state, level, player, possibleTarget) -> configValue.get();
    }

    static IConditional targetHasArmor() {
        return (stack, state, level, player, target) ->
                target != null && target.getArmorValue() > 0;
    }

    static IConditional targetHPAmount(float amount, boolean lowerThanAmount) {
        return new IConditional() {
            @Override
            public boolean shouldActivate(ItemStack stack, BlockState state, Level level, LivingEntity player, LivingEntity target) {
                return target != null && (lowerThanAmount? target.getHealth() <= amount : target.getHealth() >= amount);
            }

            @Override
            public MutableComponent value() {
                return Component.translatable("condition.orevolution.target_hp_amount", (int)(amount / 2));
            }
        };
    }

    static IConditional targetHPPercentage(float percentage, boolean lowerThanPercent) {
        return new IConditional() {
            @Override
            public boolean shouldActivate(ItemStack stack, BlockState state, Level level, LivingEntity player, LivingEntity target) {
                if (target == null)
                    return false;

                float hp = target.getHealth() / target.getMaxHealth();
                return lowerThanPercent ? hp <= percentage : hp >= percentage;
            }

            @Override
            public MutableComponent value() {
                return Component.translatable("condition.orevolution.target_hp_percent."
                        + (lowerThanPercent? "lower_than" : "higher_than"), (int)(percentage * 100) + "%");
            }
        };
    }

    static IConditional targetMobType(TagKey<EntityType<?>> type) {
        return (stack, state, level, player, target) ->
                target != null && target.getType().is(type);
    }

    static IConditional targetIsHostile() {
        return (stack, state, level, player, target) ->
                target != null && !target.getType().getCategory().isFriendly();
    }

    static IConditional isSubmergedInLiquid(Holder<FluidType> fluidType) {
        return (stack, state, level, player, target) ->
                player != null && player.isEyeInFluidType(fluidType.value());
    }

    static IConditional isTouchingLiquid(Holder<FluidType> fluidType) {
        return (stack, state, level, player, target) ->
                player != null && player.isInFluidType(fluidType.value());
    }

    static IConditional isTouchingLava() {
        return (stack, state, level, player, target) ->
                player != null && player.isInLava();
    }

    static IConditional playerHPPercentage(float percentage, boolean lowerThanPercent) {
        return new IConditional() {
            @Override
            public boolean shouldActivate(ItemStack stack, BlockState state, Level level, LivingEntity player, LivingEntity target) {
                if (target == null)
                    return false;

                float hp = target.getHealth() / target.getMaxHealth();
                return lowerThanPercent ? hp <= percentage : hp >= percentage;
            }

            @Override
            public MutableComponent value() {
                return Component.translatable("condition.orevolution.player_hp_percent."
                        + (lowerThanPercent? "lower_than" : "higher_than"), (int)(percentage * 100) + "%");
            }
        };
    }

    static IConditional toolHasEnchantment(Holder<Enchantment> enchantment) {
        return (stack, state, level, player, target) ->
                stack.getTagEnchantments().getLevel(enchantment) > 0;
    }

    static IConditional toolHasAnyEnchantment() {
        return (stack, state, level, player, target) ->
                !stack.getTagEnchantments().isEmpty();
    }

    static IConditional playerHPAmount(float amount) {
        return new IConditional() {
            @Override
            public boolean shouldActivate(ItemStack stack, BlockState state, Level level, LivingEntity player, LivingEntity target) {
                return player != null && player.getHealth() <= amount;
            }

            @Override
            public MutableComponent value() {
                return Component.translatable("condition.orevolution.player_hp_amount", (int)(amount / 2));
            }
        };
    }

    static IConditional isReceivingDayLight() {
        return (stack, state, level, player, target) ->
                player != null && level.canSeeSky(player.blockPosition()) && level.isDay();
    }

    static IConditional isBlockstateTaggedAs(TagKey<Block> blockTag) {
        return (stack, state, level, player, target) ->
                state != null && state.is(blockTag);
    }

    static IConditional isBlockstateTaggedAs(Supplier<Block> block) {
        return (stack, state, level, player, target) ->
                state != null && state.is(block.get());
    }

    static IConditional and(IConditional a, IConditional b) {
        return (stack, state, level, player, target) ->
                a.shouldActivate(stack, state, level, player, target)
                        && b.shouldActivate(stack, state, level, player, target);
    }

    static IConditional or(IConditional a, IConditional b) {
        return (stack, state, level, player, target) ->
                a.shouldActivate(stack, state, level, player, target)
                        || b.shouldActivate(stack, state, level, player, target);
    }

    static IConditional oneOf(IConditional... conditionals) {
        return (stack, state, level, player, target) -> {
            for (IConditional conditional : conditionals) {
                if (conditional.shouldActivate(stack, state, level, player, target))
                    return true;
            }
            return false;
        };
    }

    static IConditional not(IConditional a) {
        return (stack, state, level, player, target) ->
                !a.shouldActivate(stack, state, level, player, target);
    }

    static IConditional always() {
        return (stack, state, level, player, target) -> true;
    }
}