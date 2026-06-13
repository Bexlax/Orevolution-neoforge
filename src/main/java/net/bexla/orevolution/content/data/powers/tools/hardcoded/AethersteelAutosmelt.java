package net.bexla.orevolution.content.data.powers.tools.hardcoded;

import net.bexla.orevolution.content.data.Conditionals;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.simulateBlockBreaking;

public class AethersteelAutosmelt extends OrevolutionToolPower {
    private final double baseChance = 0.4;

    public AethersteelAutosmelt() {
        super("", Conditionals.always());
    }

    @Override
    public @NotNull Object addTooltipValue() {
        return (int)(baseChance * 100) + "%";
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        Object tooltipval = addTooltipValue();
        List<Component> tips = new ArrayList<>();


        tips.add(Component.translatable("tooltip.orevolution.duplication", tooltipval).withStyle(ChatFormatting.GREEN));

        if(Screen.hasControlDown()) {
            tips.add(Component.translatable("tooltip.orevolution.duplication_explanation").withStyle(ChatFormatting.DARK_GRAY));
        }
        else {
            tips.add(Component.translatable("tooltip.orevolution.press_key", Component.translatable("key.keyboard.left.control").getString()).withStyle(ChatFormatting.DARK_GRAY));
        }
        tips.add(Component.literal(""));


        tips.add(Component.translatable("tooltip.orevolution.autosmelt", tooltipval).withStyle(ChatFormatting.GREEN));

        if(Screen.hasControlDown()) {
            tips.add(Component.translatable("tooltip.orevolution.autosmelt_explanation").withStyle(ChatFormatting.DARK_GRAY));
        }
        else {
            tips.add(Component.translatable("tooltip.orevolution.press_key", Component.translatable("key.keyboard.left.control").getString()).withStyle(ChatFormatting.DARK_GRAY));
        }
        tips.add(Component.literal(""));

        return tips;
    }

    protected ItemStack getSmeltStack(Level level, ItemStack stack) {
        return level.getRecipeManager().getRecipeFor(
                RecipeType.SMELTING,
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
        if(!getCBoolean(stack, state, level, player, null)) return super.onMineBlock(stack, level, pos, entity, state);
        if(player.isCreative()) return super.onMineBlock(stack, level, pos, entity, state);

        double chance = baseChance;
        Item item = stack.getItem();

        BlockEntity blockEntity = level.getBlockEntity(pos);

        LootParams.Builder builder = new LootParams.Builder((ServerLevel) level)
                .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                .withParameter(LootContextParams.TOOL, stack)
                .withOptionalParameter(LootContextParams.THIS_ENTITY, player)
                .withOptionalParameter(LootContextParams.BLOCK_ENTITY, blockEntity);

        List<ItemStack> drops = state.getDrops(builder);

        boolean smeltedAnything = false;

        if (item instanceof TieredItem tieredItem && stack.isCorrectToolForDrops(state)) {
            if(state.is(OrevolutionTags.Blocks.alwaysDuplicateChance)) {
                chance = 1;
            }
            else if(state.is(OrevolutionTags.Blocks.neverDuplicateChance)) {
                chance = 0;
            }
            else if(state.is(OrevolutionTags.Blocks.uncommonDuplicateChance)) {
                chance = baseChance / 2;
            }
            else if(state.is(Tags.Blocks.ORES)) {
                chance = baseChance / 5;
            }
            else if(state.is(OrevolutionTags.Blocks.rareDuplicateChance)) {
                chance = baseChance / 8;
            }

            boolean oneOfThese = state.is(Tags.Blocks.ORES) || state.is(Tags.Blocks.SANDS) || state.is(BlockTags.LOGS_THAT_BURN) || state.is(BlockTags.CROPS);

            Holder<Enchantment> silkTouch = level.registryAccess()
                    .registryOrThrow(Registries.ENCHANTMENT)
                    .getHolderOrThrow(Enchantments.SILK_TOUCH);

            for(ItemStack drop : drops) {
                ItemStack smelted = getSmeltStack(level, drop);

                if(!player.isShiftKeyDown() && oneOfThese && !(EnchantmentHelper.getTagEnchantmentLevel(silkTouch, stack) > 0)) {
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
