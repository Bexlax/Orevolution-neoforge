package net.bexla.orevolution.content.data.powers.tools.hardcoded;

import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;

import java.util.ArrayList;
import java.util.List;

import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.simulateBlockBreaking;

public class AethersteelAutosmelt extends OrevolutionToolPower {
    private final double baseChance = 0.3;

    public AethersteelAutosmelt() {
        super("", IConditional.always());
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        List<Component> tips = new ArrayList<>();

        tips.add(Component.translatable("power.orevolution.duplication", displayChance(0.1), displayChance(2)).withStyle(ChatFormatting.GREEN));

        if(Screen.hasControlDown()) {
            tips.add(Component.translatable("power.orevolution.explanation.duplication").withStyle(ChatFormatting.DARK_GRAY));
            tips.add(Component.translatable("power.orevolution.explanation.double_chance", displayChance(2)).withStyle(ChatFormatting.DARK_GRAY));
            tips.add(Component.translatable("power.orevolution.explanation.normal_chance", displayChance(1)).withStyle(ChatFormatting.DARK_GRAY));
            tips.add(Component.translatable("power.orevolution.explanation.uncommon_chance", displayChance(0.5)).withStyle(ChatFormatting.DARK_GRAY));
            tips.add(Component.translatable("power.orevolution.explanation.ore_chance", displayChance(0.2)).withStyle(ChatFormatting.DARK_GRAY));
            tips.add(Component.translatable("power.orevolution.explanation.rare_chance", displayChance(0.1)).withStyle(ChatFormatting.DARK_GRAY));
            tips.add(Component.translatable("power.orevolution.explanation.no_chance").withStyle(ChatFormatting.DARK_GRAY));
        }
        else {
            tips.add(Component.translatable("power.orevolution.press_key", Component.translatable("key.keyboard.left.control").getString()).withStyle(ChatFormatting.DARK_GRAY));
        }

        tips.add(Component.empty());

        tips.add(Component.translatable("power.orevolution.autosmelt").withStyle(ChatFormatting.GREEN));

        if(Screen.hasControlDown()) {
            tips.add(Component.translatable("power.orevolution.explanation.autosmelt").withStyle(ChatFormatting.DARK_GRAY));
        }
        else {
            tips.add(Component.translatable("power.orevolution.press_key", Component.translatable("key.keyboard.left.control").getString()).withStyle(ChatFormatting.DARK_GRAY));
        }

        return tips;
    }

    private Object displayChance(double op) {
        double val = baseChance * 100;
        return (int)(val * op) + "%";
    }

    protected ItemStack getSmeltStack(Level level, ItemStack stack) {
        return level.getRecipeManager().getRecipeFor(
                RecipeType.SMELTING,
                new SingleRecipeInput(stack),
                level
        ).map(recipe -> recipe.value().assemble(
                new SingleRecipeInput(stack),
                level.registryAccess()
        )).orElse(getBlastedStack(level, stack));
    }

    protected ItemStack getBlastedStack(Level level, ItemStack stack) {
        return level.getRecipeManager().getRecipeFor(
                RecipeType.BLASTING,
                new SingleRecipeInput(stack),
                level
        ).map(recipe -> recipe.value().assemble(
                new SingleRecipeInput(stack),
                level.registryAccess()
        )).orElse(stack);
    }

    @Override
    public boolean onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity entity, BlockState state) {
        if(!(entity instanceof Player player)) return super.onMineBlock(stack, level, pos, entity, state);
        if(player.isCreative()) return super.onMineBlock(stack, level, pos, entity, state);

        double chance = baseChance;

        BlockEntity blockEntity = level.getBlockEntity(pos);

        LootParams.Builder builder = new LootParams.Builder((ServerLevel) level)
                .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                .withParameter(LootContextParams.TOOL, stack)
                .withOptionalParameter(LootContextParams.THIS_ENTITY, player)
                .withOptionalParameter(LootContextParams.BLOCK_ENTITY, blockEntity);

        List<ItemStack> drops = state.getDrops(builder);

        boolean smeltedAnything = false;

        if (!state.requiresCorrectToolForDrops() || stack.isCorrectToolForDrops(state)) {
            if(state.is(OrevolutionTags.Blocks.DOUBLE_DUPLICATE_CHANCE)) {
                chance = baseChance * 2;
            } else if(state.is(OrevolutionTags.Blocks.NEVER_DUPLICATE_CHANCE)) {
                chance = 0;
            } else if(state.is(OrevolutionTags.Blocks.UNCOMMON_DUPLICATE_CHANCE)) {
                chance = baseChance / 2;
            } else if(state.is(Tags.Blocks.ORES)) {
                chance = baseChance / 5;
            } else if(state.is(OrevolutionTags.Blocks.RARE_DUPLICATE_CHANCE)) {
                chance = baseChance / 8;
            }

            Holder<Enchantment> silkTouch = level.registryAccess()
                    .registryOrThrow(Registries.ENCHANTMENT)
                    .getHolderOrThrow(Enchantments.SILK_TOUCH);

            for(ItemStack drop : drops) {
                ItemStack smelted = getSmeltStack(level, drop);

                if((player.isShiftKeyDown() != state.is(OrevolutionTags.Blocks.AUTOSMELT)) && !(EnchantmentHelper.getTagEnchantmentLevel(silkTouch, stack) > 0)) {
                    smelted.setCount(drop.getCount());

                    level.removeBlock(pos, false);

                    simulateBlockBreaking(player, stack, pos, state, smelted, level);

                    level.addFreshEntity(new ExperienceOrb(
                            level,
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5,
                            1
                    ));

                    if(Math.random() < chance) {
                        for(int i = 0; i < 1; i++) {
                            Block.popResource(level, pos, smelted);

                            level.addFreshEntity(new ExperienceOrb(
                                    level,
                                    pos.getX() + 0.5,
                                    pos.getY() + 0.5,
                                    pos.getZ() + 0.5,
                                    1
                            ));
                        }
                    }
                    smeltedAnything = true;
                }
                else {
                    if(Math.random() < chance) {
                        for(int i = 0; i < 1; i++) {
                            Block.popResource(level, pos, drop);
                        }
                    }
                }
            }
        }

        return smeltedAnything;
    }
}
