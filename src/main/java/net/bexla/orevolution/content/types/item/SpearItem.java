package net.bexla.orevolution.content.types.item;

import com.notunanancyowen.spears.components.AttackRange;
import com.notunanancyowen.spears.components.KineticWeapon;
import com.notunanancyowen.spears.components.UseEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;
import java.util.Optional;

import static com.notunanancyowen.spears.Spears.*;

public class SpearItem extends TieredItem {
    public SpearItem(Tier tier, SpearStats stats, Properties properties) {
        super(tier, buildProperties(tier, stats, properties));
    }

    private static Properties buildProperties(Tier tier, SpearStats stats, Properties properties) {

        ItemAttributeModifiers attributes =
                ItemAttributeModifiers.builder()
                        .add(
                                Attributes.ATTACK_DAMAGE,
                                new AttributeModifier(
                                        Item.BASE_ATTACK_DAMAGE_ID,
                                        tier.getAttackDamageBonus(),
                                        AttributeModifier.Operation.ADD_VALUE
                                ),
                                EquipmentSlotGroup.MAINHAND
                        )
                        .add(
                                Attributes.ATTACK_SPEED,
                                new AttributeModifier(
                                        Item.BASE_ATTACK_SPEED_ID,
                                        (1.0D / stats.swingAnimationSeconds) - 4.0D,
                                        AttributeModifier.Operation.ADD_VALUE
                                ),
                                EquipmentSlotGroup.MAINHAND
                        )
                        .build();

        boolean chargeAttacksEnabled = config.getOrDefault("spear_charge_attacks", true);

        boolean wood = tier == Tiers.WOOD;

        Item.Properties props = properties
                .component(USE_EFFECTS,
                        new UseEffects(1.0F, true, false))
                .component(ATTACK_RANGE,
                        new AttackRange(2.0F, 4.5F))
                .component(MINIMUM_ATTACK_CHARGE,
                        1.0F)
                .attributes(attributes);

        if(chargeAttacksEnabled)
            props.component(KINETIC_WEAPON,
                        new KineticWeapon(
                                0.125F,
                                10,
                                (int)(stats.chargeDelaySeconds * 20.0F),
                                KineticWeapon.Condition.ofMinSpeed((int)(stats.maxDurationForDismountSeconds * 20.0F), stats.minSpeedForDismount),
                                KineticWeapon.Condition.ofMinSpeed((int)(stats.maxDurationForChargeKnockbackSeconds * 20.0F), stats.minSpeedForChargeKnockback),
                                KineticWeapon.Condition.ofMinRelativeSpeed((int)(stats.maxDurationForChargeDamageSeconds * 20.0F), stats.minRelativeSpeedForChargeDamage),
                                0.38F,
                                stats.chargeDamageMultiplier,
                                Optional.of(Holder.direct(Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(wood ? SPEAR_WOOD_USE.getLocation() : SPEAR_USE.getLocation())))),
                                Optional.of(Holder.direct(Objects.requireNonNull(BuiltInRegistries.SOUND_EVENT.get(wood ? SPEAR_WOOD_HIT.getLocation() : SPEAR_HIT.getLocation()))))));

        return props;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        stack.setEntityRepresentation(selected ? entity : null);

        super.inventoryTick(stack, level, entity, slot, selected);
    }

    @Override
    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        return false;
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(
                1,
                attacker,
                EquipmentSlot.MAINHAND
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        if (!config.getOrDefault("spear_charge_attacks", true)) {
            return super.use(level, player, hand);
        }

        player.startUsingItem(hand);
        return InteractionResultHolder.consume(
                player.getItemInHand(hand)
        );
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return config.getOrDefault("spear_charge_attacks", true)
                ? 72000
                : super.getUseDuration(stack, entity);
    }

    public record SpearStats(
            float swingAnimationSeconds,
            float chargeDamageMultiplier,
            float chargeDelaySeconds,
            float maxDurationForDismountSeconds,
            float minSpeedForDismount,
            float maxDurationForChargeKnockbackSeconds,
            float minSpeedForChargeKnockback,
            float maxDurationForChargeDamageSeconds,
            float minRelativeSpeedForChargeDamage
    ) {}
}