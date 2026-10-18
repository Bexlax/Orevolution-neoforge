package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ToolAutosmelt extends OrevolutionToolPower {

    public ToolAutosmelt(String tooltipId, IConditional conditional) {
        super(tooltipId, conditional);
    }

    @Override
    public List<MutableComponent> ctrlTooltip() {
        return List.of(Component.translatable("power.orevolution.explanation.autosmelt"));
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

    protected float getSmeltExperience(Level level, ItemStack stack) {
        return level.getRecipeManager().getRecipeFor(
                RecipeType.SMELTING,
                new SingleRecipeInput(stack),
                level
        ).map(recipe -> recipe.value().getExperience()).orElse(0.0F);
    }

    @Override
    public List<ItemEntity> onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity entity, BlockState state, List<ItemEntity> drops) {
        if (!(entity instanceof Player player)) return super.onMineBlock(stack, level, pos, entity, state, drops);
        if (player.isShiftKeyDown() == getCondition(stack, state, level, entity, null)) return super.onMineBlock(stack, level, pos, entity, state, drops);
        if (stack.getItem() instanceof TieredItem && !stack.isCorrectToolForDrops(state)) return super.onMineBlock(stack, level, pos, entity, state, drops);

        for (ItemEntity drop : drops) {
            ItemStack original = drop.getItem();
            ItemStack smelted = getSmeltStack(level, original);

            if (ItemStack.isSameItemSameComponents(original, smelted)) continue;

            smelted.setCount(original.getCount());
            drop.setItem(smelted);

            if (level instanceof ServerLevel serverLevel) {
                serverLevel.addFreshEntity(new ExperienceOrb(
                        serverLevel,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        1
                ));
            }

        }

        return super.onMineBlock(stack, level, pos, entity, state, drops);
    }

    @Override
    public int onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity entity, BlockState state, int xpToDrop) {
        Holder<Enchantment> silkTouch = level.registryAccess()
                .registryOrThrow(Registries.ENCHANTMENT)
                .getHolderOrThrow(Enchantments.SILK_TOUCH);

        if (!(entity instanceof Player player)) return super.onMineBlock(stack, level, pos, entity, state, xpToDrop);
        if (player.isShiftKeyDown() == getCondition(stack, state, level, entity, null)) return super.onMineBlock(stack, level, pos, entity, state, xpToDrop);
        if (EnchantmentHelper.getTagEnchantmentLevel(silkTouch, stack) > 0) return super.onMineBlock(stack, level, pos, entity, state, xpToDrop);
        if (stack.getItem() instanceof TieredItem && !stack.isCorrectToolForDrops(state)) return super.onMineBlock(stack, level, pos, entity, state, xpToDrop);

        List<ItemStack> drops = state.getDrops(
                new LootParams.Builder((ServerLevel) level)
                        .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                        .withParameter(LootContextParams.TOOL, stack)
                        .withOptionalParameter(LootContextParams.THIS_ENTITY, entity)
                        .withOptionalParameter(
                                LootContextParams.BLOCK_ENTITY,
                                level.getBlockEntity(pos)
                        )
        );

        int totalExperience = 0;

        for (ItemStack drop : drops) {
            if (level.getRecipeManager().getRecipeFor(
                    RecipeType.SMELTING,
                    new SingleRecipeInput(drop),
                    level
            ).isEmpty()) {
                continue;
            }

            float recipeExperience = getSmeltExperience(level, drop);
            float experience = (drop.getCount() - 1) * recipeExperience;

            int amount = Mth.floor(experience);
            float fraction = Mth.frac(experience);

            if (fraction != 0.0F && level.random.nextFloat() < fraction) {
                amount--;
            }

            totalExperience += amount;
        }

        return totalExperience;
    }
}
