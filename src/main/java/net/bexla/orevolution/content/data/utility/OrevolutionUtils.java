package net.bexla.orevolution.content.data.utility;

import com.google.common.collect.ImmutableList;
import com.mojang.logging.LogUtils;
import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

import static net.bexla.orevolution.Orevolution.lc;

public class OrevolutionUtils {
    public static float durabilityPercentage(ItemStack stack) {
        float durabilityPercent = 1.0F - ((float) stack.getDamageValue() / stack.getMaxDamage());

        float missingDurability = 1.0F - durabilityPercent;

        return 1.0F + (float)Math.pow(missingDurability, 1.5F) * 3F;
    }

    public static final Logger LOGGER = LogUtils.getLogger();

    public static void displayDebug(Player player, String message, Object... args) {
        if (OrevolutionConfig.STARTUP.debugMod.get()) {
            player.displayClientMessage(Component.literal(message + Arrays.toString(args)), true);
            debug(message, args);
        }
    }

    public static void debug(String message, Object... args) {
        if (OrevolutionConfig.STARTUP.debugMod.get()) {
            LOGGER.info(message, args);
        }
    }

    public static void warn(String message, Object... args) {
        if (OrevolutionConfig.STARTUP.debugMod.get()) {
            LOGGER.warn(message, args);
        }
    }

    public static void error(String message, Object... args) {
        if (OrevolutionConfig.STARTUP.debugMod.get()) {
            LOGGER.error(message, args);
        }
    }

    public static void info(String message, Object... args) {
        if (OrevolutionConfig.STARTUP.debugMod.get()) {
            LOGGER.error(message, args);
        }
    }

    public static final SoundType MOONSTONE = new SoundType(
            1.0F, 1.0F, SoundEvents.COPPER_BULB_BREAK, SoundEvents.TUFF_BRICKS_STEP, SoundEvents.VAULT_PLACE, SoundEvents.VAULT_HIT, SoundEvents.COPPER_BULB_HIT
    );

    public static final SoundType CELESTITE_BLOCK = new SoundType(
            1.0F, 1.65F, SoundEvents.CHAIN_BREAK, SoundEvents.VAULT_STEP, SoundEvents.AMETHYST_BLOCK_PLACE, SoundEvents.ANCIENT_DEBRIS_HIT, SoundEvents.CHAIN_FALL
    );

    public static @Nullable TagKey<Block> getToolTag(ItemStack stack) {
        Tool tool = stack.get(DataComponents.TOOL);
        if (tool == null) return null;

        for (Tool.Rule rule : tool.rules()) {
            if (rule.blocks() instanceof HolderSet.Named<Block> named) {
                TagKey<Block> tag = named.key();

                if (tag.location().getPath().startsWith("mineable/")) {
                    return tag;
                }
            }
        }

        return null;
    }

    public static int searchForItem(Inventory inventory, ItemStack stack) {
        final List<NonNullList<ItemStack>> compartments = ImmutableList.of(inventory.items, inventory.armor, inventory.offhand);

        for (NonNullList<ItemStack> lists : compartments) {
            for (int i = 0; i < lists.size(); i++) {
                if (!inventory.items.get(i).isEmpty() && ItemStack.isSameItemSameComponents(stack, inventory.items.get(i))) {
                    displayDebug(inventory.player, "found item in slot: " + i);
                    return i;
                }
            }
        }

        return -1;
    }

    public static ItemStack totemInHotbar(Player player, DataComponentType<Unit> component) {
        for (int i = 0; i < 9; i++) {
            ItemStack stack = player.getInventory().items.get(i);

            if (stack.is(RegItems.BRONZE_TOTEM.get()) && stack.has(component)) {
                return stack;
            }
        }

        return ItemStack.EMPTY;
    }

    public static String getToolType(TagKey<Block> tag) {
        String path = tag.location().getPath();
        int slash = path.lastIndexOf('/');

        return slash == -1 ? path : path.substring(slash + 1);
    }

    public static Component getToolTypeName(TagKey<Block> tag) {
        return Component.translatable("tool." + tag.location().getNamespace() + "." + getToolType(tag));
    }

    public static @Nullable TagKey<Block> getRequiredToolTag(BlockState state) {
        if (state.is(BlockTags.MINEABLE_WITH_PICKAXE))
            return BlockTags.MINEABLE_WITH_PICKAXE;

        if (state.is(BlockTags.MINEABLE_WITH_AXE))
            return BlockTags.MINEABLE_WITH_AXE;

        if (state.is(BlockTags.MINEABLE_WITH_SHOVEL))
            return BlockTags.MINEABLE_WITH_SHOVEL;

        if (state.is(BlockTags.MINEABLE_WITH_HOE))
            return BlockTags.MINEABLE_WITH_HOE;

        return state.getTags()
                .filter(tag -> tag.location().getPath().startsWith("mineable/"))
                .findFirst()
                .orElse(null);
    }
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

    public static boolean isFullArmorTagged(LivingEntity entity, TagKey<Item> tag) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).is(tag) &&
                entity.getItemBySlot(EquipmentSlot.CHEST).is(tag) &&
                        entity.getItemBySlot(EquipmentSlot.LEGS).is(tag) &&
                        entity.getItemBySlot(EquipmentSlot.FEET).is(tag);
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

    private static int findComponent(List<Component> tooltip, String translationKey) {
        for (int i = 0; i < tooltip.size(); i++) {
            Component component = tooltip.get(i);

            if (component.getContents() instanceof TranslatableContents contents &&
                    contents.getKey().equals(translationKey)) {
                return i;
            }
        }

        return -1;
    }

    public static void simulateBlockBreaking(Player player, ItemStack stack, BlockPos pos, BlockState state, ItemStack itemToDrop, Level level) {
        player.awardStat(Stats.BLOCK_MINED.get(state.getBlock()));
        player.causeFoodExhaustion(0.005F);
        stack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(stack));
        Block.popResource(level, pos, itemToDrop);
    }

    public static ItemAttributeModifiers applyCoating(ItemStack stack, ItemAttributeModifiers modifiers) {
        boolean slotExists = stack.getEquipmentSlot() != null;
        boolean isArmor = slotExists && stack.getEquipmentSlot().isArmor();
        EquipmentSlotGroup slot = EquipmentSlotGroup.bySlot(slotExists ? stack.getEquipmentSlot() : EquipmentSlot.MAINHAND);

        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();

        for (ItemAttributeModifiers.Entry entry : modifiers.modifiers()) {
            AttributeModifier modifier = entry.modifier();
            double amount = modifier.amount();

            if (modifier.operation() == AttributeModifier.Operation.ADD_VALUE) {
                double baseValue = entry.attribute().value().getDefaultValue();
                double currentValue = baseValue + amount;
                double targetValue = currentValue * 1.5D;

                amount = targetValue - baseValue;
            } else {
                amount *= 1.5D;
            }

            builder.add(
                    entry.attribute(),
                    new AttributeModifier(
                            modifier.id(),
                            amount,
                            modifier.operation()
                    ),
                    entry.slot()
            );
        }

        if (isArmor) {
            boolean hasBurningTime = modifiers.modifiers().stream()
                    .anyMatch(entry -> entry.attribute().is(Attributes.BURNING_TIME));

            if (!hasBurningTime) {
                builder.add(
                        Attributes.BURNING_TIME,
                        new AttributeModifier(
                                lc("coated_burning_time"),
                                -0.4D,
                                AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                        ),
                        slot
                );
            }
        }

        if (stack.is(ItemTags.MINING_ENCHANTABLE)) {
            boolean hasEfficiency = modifiers.modifiers().stream()
                    .anyMatch(entry -> entry.attribute().is(Attributes.MINING_EFFICIENCY));

            if (!hasEfficiency) {
                builder.add(
                        Attributes.MINING_EFFICIENCY,
                        new AttributeModifier(
                                lc("coated_mining_efficiency"),
                                2D,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        slot
                );
            }
        }

        if (stack.is(ItemTags.WEAPON_ENCHANTABLE)) {
            boolean hasKnockback = modifiers.modifiers().stream()
                    .anyMatch(entry -> entry.attribute().is(Attributes.ATTACK_KNOCKBACK));

            if (!hasKnockback) {
                builder.add(
                        Attributes.ATTACK_KNOCKBACK,
                        new AttributeModifier(
                                lc("coated_attack_knockback"),
                                0.5D,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        slot
                );
            }
        }

        return builder.build();
    }
}
