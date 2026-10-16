package net.bexla.orevolution.content.interfaces;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.function.Supplier;

@FunctionalInterface
public interface IConditional {
    boolean shouldActivate(ItemStack stack, BlockState state, Level level, LivingEntity player, LivingEntity target);

    default MutableComponent value() {
        return null;
    }

    static IConditional byChance(double chance) {
        return new IConditional() {
            @Override
            public boolean shouldActivate(ItemStack stack, BlockState state, Level level, LivingEntity player, LivingEntity target) {
                return level.getRandom().nextDouble() < chance;
            }

            @Override
            public MutableComponent value() {
                return Component.translatable("condition.orevolution.chance", (int)(chance * 100) + "%");
            }
        };
    }

    static IConditional config(ModConfigSpec.ConfigValue<Boolean> configValue) {
        return (stack, state, level, player, possibleTarget) -> configValue.get();
    }

    static IConditional targetHasArmor() {
        return new IConditional() {
            @Override
            public boolean shouldActivate(ItemStack stack, BlockState state, Level level, LivingEntity player, LivingEntity target) {
                return target != null && target.getArmorValue() > 0;
            }

            @Override
            public MutableComponent value() {
                return Component.translatable("condition.orevolution.target_armored");
            }
        };
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

    static IConditional targetMobType(TagKey<EntityType<?>> type, String tooltip) {
        return new IConditional() {
            @Override
            public boolean shouldActivate(ItemStack stack, BlockState state, Level level, LivingEntity player, LivingEntity target) {
                return target !=null && target.getType().is(type);
            }

            @Override
            public MutableComponent value() {
                return Component.translatable("condition.orevolution.target_" + tooltip);
            }
        };
    }

    static IConditional targetIsHostile() {
        return new IConditional() {
            @Override
            public boolean shouldActivate(ItemStack stack, BlockState state, Level level, LivingEntity player, LivingEntity target) {
                return target != null && !target.getType().getCategory().isFriendly();
            }

            @Override
            public MutableComponent value() {
                return Component.translatable("condition.orevolution.target_hostile");
            }
        };
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