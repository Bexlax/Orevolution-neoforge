package net.bexla.orevolution.content.data.utility;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootTable;
import org.slf4j.Logger;

import javax.annotation.Nullable;

import static net.bexla.orevolution.Orevolution.lc;

public class OrevolutionUtils {
    protected static final Logger LOGGER = LogUtils.getLogger();

    public static boolean isWearingFullSet(LivingEntity entity, Holder<ArmorMaterial> material) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof ArmorItem head &&
                entity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof ArmorItem chest &&
                entity.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof ArmorItem legs &&
                entity.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof ArmorItem feet &&
                head.getMaterial().value() == material.value() &&
                chest.getMaterial().value() == material.value() &&
                legs.getMaterial().value() == material.value() &&
                feet.getMaterial().value() == material.value();
    }

    @Nullable
    public static Holder<ArmorMaterial> getCurrentFullSet(LivingEntity entity) {
        ItemStack helmet = entity.getItemBySlot(EquipmentSlot.HEAD);

        if (!(helmet.getItem() instanceof ArmorItem armorItem))
            return null;

        Holder<ArmorMaterial> material = armorItem.getMaterial();

        return isWearingFullSet(entity, material)
                ? material
                : null;
    }

    public static void simulateBlockBreaking(Player player, ItemStack stack, BlockPos pos, BlockState state, ItemStack itemToDrop, Level level) {
        player.awardStat(Stats.BLOCK_MINED.get(state.getBlock()));
        player.causeFoodExhaustion(0.005F);
        stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
        Block.popResource(level, pos, itemToDrop);
    }

    public static class LTKeys {
        public static final ResourceKey<LootTable> ABANDONED_MINESHAFT = register("chests/orevolution_abandoned_mineshaft");
        public static final ResourceKey<LootTable> END_CITY = register("chests/orevolution_end_city_treasure");
        public static final ResourceKey<LootTable> BASTION_HOGLIN_STABLE = register("chests/orevolution_bastion_hoglin_stable");
        public static final ResourceKey<LootTable> BASTION_TREASURE = register("chests/orevolution_bastion_treasure");
        public static final ResourceKey<LootTable> PILLAGER_OUTPOST = register("chests/orevolution_pillager_outpost");
        public static final ResourceKey<LootTable> RUINED_PORTAL = register("chests/orevolution_ruined_portal");
        public static final ResourceKey<LootTable> SHIPWRECK_SUPPLY = register("chests/orevolution_shipwreck_supply");
        public static final ResourceKey<LootTable> SIMPLE_DUNGEON = register("chests/orevolution_simple_dungeon");
        public static final ResourceKey<LootTable> VILLAGE_DESERT_HOUSE = register("chests/orevolution_village_desert_house");
        public static final ResourceKey<LootTable> VILLAGE_PLAINS_HOUSE = register("chests/orevolution_village_plains_house");
        public static final ResourceKey<LootTable> VILLAGE_SAVANNA_HOUSE = register("chests/orevolution_village_savanna_house");
        public static final ResourceKey<LootTable> VILLAGE_SNOWY_HOUSE = register("chests/orevolution_village_snowy_house");
        public static final ResourceKey<LootTable> VILLAGE_TAIGA_HOUSE = register("chests/orevolution_village_taiga_house");

        private static ResourceKey<LootTable> register(String id) {
            return ResourceKey.create(Registries.LOOT_TABLE, lc(id));
        }
    }
}
