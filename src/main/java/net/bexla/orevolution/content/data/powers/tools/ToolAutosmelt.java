package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.types.interfaces.IConditional;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

import java.util.List;

import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.simulateBlockBreaking;

public class ToolAutosmelt extends OrevolutionToolPower {

    public ToolAutosmelt(String tooltipId, IConditional conditional) {
        super(tooltipId, conditional);
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
    public MutableComponent ctrlTooltip() {
        return Component.translatable("tooltip.orevolution.autosmelt_explanation");
    }

    @Override
    public boolean onMineBlock(ItemStack stack, Level level, BlockPos pos, LivingEntity entity, BlockState state) {
        Holder<Enchantment> silkTouch = level.registryAccess()
                .registryOrThrow(Registries.ENCHANTMENT)
                .getHolderOrThrow(Enchantments.SILK_TOUCH);

        if(!(entity instanceof Player player)) return super.onMineBlock(stack, level, pos, entity, state);
        if(!getCBoolean(stack, state, level, entity, null)) return super.onMineBlock(stack, level, pos, entity, state);
        if(EnchantmentHelper.getTagEnchantmentLevel(silkTouch, stack) > 0) return super.onMineBlock(stack, level, pos, entity, state);
        if(player.isShiftKeyDown()) return super.onMineBlock(stack, level, pos, entity, state);
        if(player.isCreative()) return super.onMineBlock(stack, level, pos, entity, state);
        if(stack.getItem() instanceof TieredItem && !stack.isCorrectToolForDrops(state)) return super.onMineBlock(stack, level, pos, entity, state);

        BlockEntity blockEntity = level.getBlockEntity(pos);

        LootParams.Builder builder = new LootParams.Builder((ServerLevel) level)
                .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                .withParameter(LootContextParams.TOOL, stack)
                .withOptionalParameter(LootContextParams.THIS_ENTITY, entity)
                .withOptionalParameter(LootContextParams.BLOCK_ENTITY, blockEntity);

        List<ItemStack> drops = state.getDrops(builder);

        boolean smeltedAnything = super.onMineBlock(stack, level, pos, entity, state);

        for(ItemStack drop : drops) {
            ItemStack smelted = getSmeltStack(level, drop);

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

            smeltedAnything = true;
        }

        return smeltedAnything;
    }
}
