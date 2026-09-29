package net.bexla.orevolution.content.types.block;

import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.init.RegParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class PyriteBlock extends Block {
    public PyriteBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        Holder<Enchantment> silkTouch = level.registryAccess()
                .registryOrThrow(Registries.ENCHANTMENT)
                .getHolderOrThrow(Enchantments.SILK_TOUCH);

        RandomSource random = level.getRandom();

        if(random.nextFloat() < 0.13f) {
            if(tool.getTagEnchantments().getLevel(silkTouch) < 0 || !tool.is(OrevolutionTags.Items.NEGATES_PYRITE_FIRE)) {
                player.igniteForSeconds(5);
            }
        }

        super.playerDestroy(level, player, pos, state, blockEntity, tool);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        super.fallOn(level, state, pos, entity, fallDistance);

        if (!level.isClientSide || fallDistance < 0.5F) return;

        RandomSource random = level.getRandom();

        int particles = Math.min(40, 10 + (int)(fallDistance * 2));

        for (int i = 0; i < particles; i++) {
            level.addParticle(
                    RegParticleTypes.GAS.get(),
                    pos.getX() + random.nextDouble(),
                    pos.getY() + 1.05D,
                    pos.getZ() + random.nextDouble(),
                    (random.nextDouble() - 0.5) * 0.2,
                    random.nextDouble() * (fallDistance * 0.15F),
                    (random.nextDouble() - 0.5) * 0.2
            );
        }
    }
}
