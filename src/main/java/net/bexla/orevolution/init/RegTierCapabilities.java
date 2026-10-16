package net.bexla.orevolution.init;

import com.github.smallinger.copperagebackport.item.tools.CopperTier;
import com.phantomwing.thesilverage.armor.ModArmorMaterials;
import com.phantomwing.thesilverage.tool.ModTiers;
import galena.oreganized.argentum.index.ArgentumAttributes;
import galena.oreganized.argentum.index.ArgentumItems;
import galena.oreganized.electrum.index.ElectrumItems;
import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.data.OrevolutionTiers;
import net.bexla.orevolution.content.data.powers.armors.*;
import net.bexla.orevolution.content.data.powers.tools.*;
import net.bexla.orevolution.content.data.utility.Operator;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.content.data.utility.OrevolutionUtils;
import net.bexla.orevolution.content.interfaces.IConditional;
import net.bexla.orevolution.content.types.ArmorPowerRegistry;
import net.bexla.orevolution.content.types.ItemPowerRegistry;
import net.bexla.orevolution.content.types.ToolModifiers;
import net.bexla.orevolution.content.types.power.armor.OrevolutionArmorPower;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Tiers;
import net.neoforged.fml.ModList;

import java.util.List;

import static net.bexla.orevolution.Orevolution.lc;

public class RegTierCapabilities {
    public static final List<Holder<MobEffect>> PLATINUM_TOOL_EFFECTS = List.of(
            RegMobEffects.PURIFICATION, MobEffects.WEAKNESS
    );

    public static final List<Holder<MobEffect>> TIN_ARMOR_IMMUNITY_EFFECTS = List.of(
            MobEffects.POISON, MobEffects.HUNGER
    );

    public static void registerArmorPowers() {
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.tinArmorPowers, OrevolutionTiers.ArmorMaterials.TIN,
                new ArmorGrantImmunityEffects("armor_immunity", IConditional.always(), TIN_ARMOR_IMMUNITY_EFFECTS)
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.cassiteriteArmorPowers, OrevolutionTiers.ArmorMaterials.CASSITERITE,
                new ArmorMultiPower(List.of(
                        new ArmorGrantImmunityEffects("armor_immunity", IConditional.always(), TIN_ARMOR_IMMUNITY_EFFECTS),
                        new ArmorModifyAttribute("attribute_increase", IConditional.always(),
                                Attributes.ARMOR,
                                lc("cassiterite_armor_orevolution"),
                                OrevolutionConfig.POWERS.cassiteriteArmorAttribute.get(),
                                AttributeModifier.Operation.ADD_VALUE
                        )
                ))
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.goldArmorPowers, ArmorMaterials.GOLD,
                new ArmorReduceDamageType("gold_armor", IConditional.always(), DamageTypeTags.BYPASSES_ARMOR, OrevolutionConfig.POWERS.goldResidualDamage.get())
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.tungstenArmorPowers, OrevolutionTiers.ArmorMaterials.TUNGSTEN,
                new ArmorMultiPower(List.of(
                        new ArmorReduceDamageType("gold_armor", IConditional.always(), DamageTypeTags.BYPASSES_ARMOR, OrevolutionConfig.POWERS.tungstenResidualDamage.get()),
                        new ArmorGrantEffects("armor_wearer_grants", IConditional.always(), 80, 0, MobEffects.FIRE_RESISTANCE)
                ))
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.bronzeArmorPowers, OrevolutionTiers.ArmorMaterials.BRONZE_HARD,
                new ArmorGrantImmunityEffects("armor_immunity.all_harmful", IConditional.always(), MobEffectCategory.HARMFUL)
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.divingBronzeArmorPowers, OrevolutionTiers.ArmorMaterials.BRONZE_SOFT,
                new ArmorModifyAttribute("attribute_increase", IConditional.always(),
                        Attributes.OXYGEN_BONUS,
                        lc("bronze_oxygen_bonus_orevolution"),
                        OrevolutionConfig.POWERS.divingBronzeExtraOxygenAttribute.get(),
                        AttributeModifier.Operation.ADD_VALUE
                )
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.ironArmorPowers, ArmorMaterials.IRON,
                new ArmorReduceDamageType("iron_armor", IConditional.byChance(OrevolutionConfig.POWERS.ironIgnoreChance.get()),
                        DamageTypeTags.IS_PROJECTILE, 0)
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.platinumArmorPowers, OrevolutionTiers.ArmorMaterials.PLATINUM,
                new ArmorCauseEffectsOnAttacked("", "armor_wearer_on_attacked_wearer", IConditional.always(),
                        OrevolutionConfig.POWERS.platinumBaseStrenghtTime.get(), OrevolutionConfig.POWERS.platinumStrenghtAmplifier.get(), MobEffects.DAMAGE_BOOST, null)
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.moonstoneArmorPowers, OrevolutionTiers.ArmorMaterials.MOONSTONE,
                new ArmorMultiPower(List.of(
                        new ArmorCauseEffectsOnAttacked("", "armor_wearer_on_attacked_wearer", IConditional.always(),
                                OrevolutionConfig.POWERS.moonstoneBaseStrenghtTime.get(), OrevolutionConfig.POWERS.moonstoneStrenghtAmplifier.get(), MobEffects.DAMAGE_BOOST, null),
                        new ArmorGrantEffects("armor_wearer_grants", IConditional.always(),
                                80, OrevolutionConfig.POWERS.moonstoneQuicknessAmplifier.get(), RegMobEffects.QUICKNESS)
                ))
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.diamondArmorPowers, ArmorMaterials.DIAMOND,
                new ArmorReduceDamageType("diamond_armor", IConditional.always(), OrevolutionTags.Misc.IS_KINETIC, OrevolutionConfig.POWERS.diamondResidualDamage.get())
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.netheriteArmorPowers, ArmorMaterials.NETHERITE,
                new ArmorReduceDamageType("diamond_armor", IConditional.always(), OrevolutionTags.Misc.IS_KINETIC, OrevolutionConfig.POWERS.netheriteResidualDamage.get())
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.aethersteelArmorPowers, OrevolutionTiers.ArmorMaterials.AETHERSTEEL,
                new ArmorMultiPower(List.of(
                        new ArmorReduceDamageType("diamond_armor", IConditional.always(), OrevolutionTags.Misc.IS_KINETIC, OrevolutionConfig.POWERS.aethersteelResidualDamage.get()),
                        new ArmorGrantEffects("armor_wearer_grants", IConditional.always(), 80, OrevolutionConfig.POWERS.aethersteelHealthBoostAmplifier.get(), MobEffects.HEALTH_BOOST),
                        new OrevolutionArmorPower("aethersteel", IConditional.always()) // Power is handled in net.bexla.orevolution.events.MiscSubscriber
                ))
        );

        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.livingstoneArmorPowers, OrevolutionTiers.ArmorMaterials.LIVINGSTONE,
                new ArmorMultiPower(List.of(
                        new ArmorRegeneration("regenerates_daylight", IConditional.isReceivingDayLight(),
                                OrevolutionConfig.POWERS.livingstoneArmorDuraRegenPerTick.get(), OrevolutionConfig.POWERS.livingstoneArmorDuraRegenCooldown.get()),
                        new ArmorGrantEffects("armor_wearer_grants_daylight", IConditional.isReceivingDayLight(),
                                60, OrevolutionConfig.POWERS.livingstoneRegenerationAmplifier.get(), MobEffects.REGENERATION)
                ))
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.verditeArmorPowers, OrevolutionTiers.ArmorMaterials.VERDITE,
                new ArmorMultiPower(List.of(
                        new ArmorRegeneration("regenerates_daylight", IConditional.isReceivingDayLight(),
                                OrevolutionConfig.POWERS.verditeDuraRegenPerTick.get(), OrevolutionConfig.POWERS.verditeDuraRegenCooldown.get()),
                        new ArmorGrantEffects("armor_wearer_grants_daylight", IConditional.isReceivingDayLight(),
                                OrevolutionConfig.POWERS.verditeRegenerationTime.get(), OrevolutionConfig.POWERS.verditeRegenerationAmplifier.get(), MobEffects.REGENERATION)
                ))
        );


        if(ModList.get().isLoaded("oreganized")) {
            ArmorPowerRegistry.register(OrevolutionConfig.MODCOMPAT.electrumArmorPowers, ElectrumItems.ELECTRUM_MATERIAL,
                    new ArmorMultiPower(List.of(
                            new ArmorModifyAttribute("attribute_increase", IConditional.always(),
                                    Attributes.MOVEMENT_SPEED,
                                    lc("electrum_speed_orevolution"),
                                    OrevolutionConfig.MODCOMPAT.electrumMovementSpeedAttribute.get(),
                                    AttributeModifier.Operation.ADD_MULTIPLIED_BASE
                            ),
                            new ArmorModifyAttribute("attribute_increase", IConditional.always(),
                                    Attributes.STEP_HEIGHT,
                                    lc("electrum_step_height_orevolution"),
                                    OrevolutionConfig.MODCOMPAT.electrumStepHeightAttribute.get(),
                                    AttributeModifier.Operation.ADD_VALUE
                            )
                    ))
            );

            ArmorPowerRegistry.register(OrevolutionConfig.MODCOMPAT.silverArmorPowers, ArgentumItems.SILVER_MATERIAL,
                    new ArmorMultiPower(List.of(
                            new ArmorModifyAttribute("attribute_increase", IConditional.always(),
                                    ArgentumAttributes.INVINCIBILITY_FRAMES,
                                    lc("silver_invincibility_orevolution"),
                                    OrevolutionConfig.MODCOMPAT.silverInvincibilityFramesAttribute.get(),
                                    AttributeModifier.Operation.ADD_VALUE
                            ),
                            new ArmorModifyAttribute("attribute_increase", IConditional.always(),
                                    Attributes.SWEEPING_DAMAGE_RATIO,
                                    lc("electrum_sweep_damage_ratio_orevolution"),
                                    OrevolutionConfig.MODCOMPAT.silverSweepingDamageRatioPercentAttribute.get(),
                                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                            )
                    ))
            );
        }

        if(ModList.get().isLoaded("thesilverage")) {
            ArmorPowerRegistry.register(OrevolutionConfig.MODCOMPAT.silverArmorPowers, ModArmorMaterials.SILVER_ARMOR_MATERIAL,
                    new ArmorMultiPower(List.of(
                            new ArmorModifyAttribute("attribute_increase", IConditional.always(),
                                    Attributes.ENTITY_INTERACTION_RANGE,
                                    lc("silver_invincibility_orevolution"),
                                    OrevolutionConfig.MODCOMPAT.silverEntityInteractionRangeAttribute.get(),
                                    AttributeModifier.Operation.ADD_VALUE
                            ),
                            new ArmorModifyAttribute("attribute_increase", IConditional.always(),
                                    Attributes.SWEEPING_DAMAGE_RATIO,
                                    lc("electrum_sweep_damage_ratio_orevolution"),
                                    OrevolutionConfig.MODCOMPAT.silverSweepingDamageRatioPercentAttribute.get(),
                                    AttributeModifier.Operation.ADD_VALUE
                            )
                    ))
            );
        }

        var copperArmor = BuiltInRegistries.ARMOR_MATERIAL.getHolder(ResourceKey.create(Registries.ARMOR_MATERIAL, ResourceLocation.withDefaultNamespace("copper"))).orElse(null);

        if(copperArmor != null && ModList.get().isLoaded("copperagebackport")) {
            ArmorPowerRegistry.register(OrevolutionConfig.MODCOMPAT.copperArmorPowers, copperArmor,
                    new ArmorMultiPower(List.of(
                            new ArmorExtendPickUp("armor_extended_pickup", IConditional.always(),
                                    OrevolutionConfig.MODCOMPAT.copperExtendPickupRangeAttribute.get()),
                            new ArmorModifyAttribute("attribute_increase", IConditional.always(),
                                    Attributes.BLOCK_INTERACTION_RANGE,
                                    lc("copper_reach_orevolution"),
                                    OrevolutionConfig.MODCOMPAT.copperBlockInteractionRangeAttribute.get(),
                                    AttributeModifier.Operation.ADD_VALUE
                            )
                    ))
            );
        }

        OrevolutionUtils.debug("Registered armor powers");
    }

    public static void registerToolsPowers() {
        ItemPowerRegistry.register(OrevolutionTiers.ToolTiers.TIN, OrevolutionConfig.POWERS.tinToolPowers, OrevolutionConfig.POWERS.tinWeaponPowers,
                new ToolIncreaseDrops("duplication", IConditional.always(),
                        OrevolutionConfig.POWERS.tinDupeDrops.get(), OrevolutionConfig.POWERS.tinDupeChance.get()),
                new ToolCauseEffectOnHit("on_hit_effect", "", IConditional.byChance(OrevolutionConfig.POWERS.tinPoisonChance.get()),
                        OrevolutionConfig.POWERS.tinPoisonTime.get(), OrevolutionConfig.POWERS.tinPoisonAmplifier.get(), MobEffects.POISON, null)
        );
        ItemPowerRegistry.register(OrevolutionTiers.ToolTiers.CASSITERITE, OrevolutionConfig.POWERS.cassiteriteToolPowers, OrevolutionConfig.POWERS.cassiteriteWeaponPowers,
                new ToolIncreaseDrops("duplication", IConditional.always(),
                        OrevolutionConfig.POWERS.cassiteriteDupeDrops.get(), OrevolutionConfig.POWERS.cassiteriteDupeChance.get()),
                new ToolCauseEffectOnHit("on_hit_effect", "", IConditional.always(),
                        OrevolutionConfig.POWERS.cassiteritePoisonTime.get(), OrevolutionConfig.POWERS.cassiteritePoisonAmplifier.get(), MobEffects.POISON, null)
        );

        ItemPowerRegistry.register(Tiers.GOLD, OrevolutionConfig.POWERS.goldToolPowers, OrevolutionConfig.POWERS.goldWeaponPowers,
                new ToolAvoidDurabilityLose("avoid_damage", IConditional.byChance(OrevolutionConfig.POWERS.goldDamageChance.get())),
                new ToolAddXPOnKill("xp_looting", IConditional.always(), OrevolutionConfig.POWERS.goldXPAmount.get())
        );
        ItemPowerRegistry.register(OrevolutionTiers.ToolTiers.TUNGSTEN, OrevolutionConfig.POWERS.goldToolPowers, OrevolutionConfig.POWERS.goldWeaponPowers,
                new ToolAvoidDurabilityLose("avoid_damage", IConditional.byChance(OrevolutionConfig.POWERS.tungstenDamageChance.get())),
                new ToolAddXPOnKill("xp_looting", IConditional.always(), OrevolutionConfig.POWERS.tungstenXPAmount.get())
        );

        ItemPowerRegistry.register(Tiers.IRON, OrevolutionConfig.POWERS.ironToolPowers, OrevolutionConfig.POWERS.ironWeaponPowers,
                new ToolSpeedDurabilityRelative("durability_speed", IConditional.always(), 
                        OrevolutionConfig.POWERS.ironMinSpeedMult.get().floatValue(),
                        OrevolutionConfig.POWERS.ironMaxSpeedMult.get().floatValue(),
                        OrevolutionConfig.POWERS.ironExponentSpeedMult.get().floatValue()),
                new ToolDamageDurabilityRelative("durability_damage", IConditional.always(), 
                        OrevolutionConfig.POWERS.ironMinDamageMult.get().floatValue(),
                        OrevolutionConfig.POWERS.ironMaxDamageMult.get().floatValue(),
                        OrevolutionConfig.POWERS.ironExponentDamageMult.get().floatValue())
        );
        ItemPowerRegistry.register(OrevolutionTiers.ToolTiers.STEEL, OrevolutionConfig.POWERS.steelToolPowers, OrevolutionConfig.POWERS.steelWeaponPowers,
                new ToolExpandHarvestArea("multi_break", IConditional.always(), OrevolutionConfig.POWERS.steelRadius.get()),
                new ToolModifyCritDamage("crit_damage", IConditional.always(), Operator.MULTIPLY,  OrevolutionConfig.POWERS.steelCritMult.get().floatValue())
        );

        ItemPowerRegistry.register(OrevolutionTiers.ToolTiers.PLATINUM, OrevolutionConfig.POWERS.platinumToolPowers, OrevolutionConfig.POWERS.platinumWeaponPowers,
                new ToolIncreaseBlockXP("xp_increase", IConditional.always(), OrevolutionConfig.POWERS.platinumXPMult.get()),
                new ToolCauseEffectOnHit("on_hit_effect", "", IConditional.targetMobType(EntityTypeTags.UNDEAD, "undead"),
                        OrevolutionConfig.POWERS.platinumForceUndeadEffects.get(),
                        OrevolutionConfig.POWERS.platinumUndeadEffectsTime.get(),
                        OrevolutionConfig.POWERS.platinumUndeadEffectsAmplifier.get(),
                        PLATINUM_TOOL_EFFECTS, null)
        );
        ItemPowerRegistry.register(OrevolutionTiers.ToolTiers.MOONSTONE, OrevolutionConfig.POWERS.moonstoneToolPowers, OrevolutionConfig.POWERS.moonstoneWeaponPowers,
                new ToolIncreaseBlockXP("xp_increase", IConditional.always(), OrevolutionConfig.POWERS.moonstoneXPMult.get()),
                new ToolCauseEffectOnHit("on_hit_effect", "", IConditional.targetIsHostile(),
                        OrevolutionConfig.POWERS.moonstoneForceMonsterEffects.get(),
                        OrevolutionConfig.POWERS.moonstoneMonsterEffectsTime.get(),
                        OrevolutionConfig.POWERS.moonstoneMonsterEffectsAmplifier.get(),
                        PLATINUM_TOOL_EFFECTS, null)
        );

        ItemPowerRegistry.register(Tiers.DIAMOND, OrevolutionConfig.POWERS.diamondToolPowers, OrevolutionConfig.POWERS.diamondWeaponPowers,
                new ToolModifyMiningSpeed("hardness_speed", IConditional.always(),
                        (stack, state, s) -> s + state.getBlock().defaultDestroyTime(), "funny value"),
                new ToolModifyDealtDamage("extra_damage_dealt", IConditional.targetHasArmor(), Operator.ADD, OrevolutionConfig.POWERS.diamondArmoredDamage.get().floatValue())
        );

        ItemPowerRegistry.register(Tiers.NETHERITE, OrevolutionConfig.POWERS.netheriteToolPowers, OrevolutionConfig.POWERS.netheriteWeaponPowers,
                new ToolAutosmelt("autosmelt", IConditional.oneOf(IConditional.isBlockstateTaggedAs(OrevolutionTags.Blocks.AUTOSMELT))),
                new ToolModifyDealtDamage("extra_damage_dealt", IConditional.targetHPPercentage(OrevolutionConfig.POWERS.netheritePercentage.get().floatValue(), OrevolutionConfig.POWERS.netheritePercentageLowerThan.get()),
                        Operator.MULTIPLY, OrevolutionConfig.POWERS.netheriteDamageMult.get().floatValue())
        );
        ItemPowerRegistry.register(OrevolutionTiers.ToolTiers.AETHERSTEEL, OrevolutionConfig.POWERS.aethersteelToolPowers, OrevolutionConfig.POWERS.aethersteelWeaponPowers,
                new ToolMultiPower(List.of(
                        new ToolAutosmelt("autosmelt", IConditional.oneOf(IConditional.isBlockstateTaggedAs(OrevolutionTags.Blocks.AUTOSMELT))),
                        new OrevolutionToolPower("aethersteel", IConditional.always()) // Power is handled in net.bexla.orevolution.events.MiscSubscriber
                )),
                new ToolMultiPower(List.of(
                        new ToolModifyDealtDamage("extra_damage_dealt", IConditional.targetHPPercentage(0.65f, true),
                                Operator.MULTIPLY, OrevolutionConfig.POWERS.aethersteelDamageMult.get().floatValue()),
                        new OrevolutionToolPower("aethersteel", IConditional.always()) // Power is handled in net.bexla.orevolution.events.MiscSubscriber
                ))
        );

        ItemPowerRegistry.register(OrevolutionTiers.ToolTiers.LIVINGSTONE, OrevolutionConfig.POWERS.livingstoneToolPowers, OrevolutionConfig.POWERS.livingstoneWeaponPowers,
                new ToolMultiPower(List.of(
                        new ToolRegenerateDurability("regenerates_daylight", IConditional.isReceivingDayLight(),
                                OrevolutionConfig.POWERS.livingstoneToolDuraRegenPerTick.get(), OrevolutionConfig.POWERS.livingstoneToolDuraRegenCooldown.get()),
                        new ToolCropIncrease("duplication_crops", IConditional.always(),
                                OrevolutionConfig.POWERS.livingstoneCropIncrease.get(), OrevolutionConfig.POWERS.livingstoneCropChance.get())
                )),
                new ToolMultiPower(List.of(
                        new ToolRegenerateDurability("regenerates_daylight", IConditional.isReceivingDayLight(),
                                OrevolutionConfig.POWERS.livingstoneWeaponDuraRegenPerTick.get(), OrevolutionConfig.POWERS.livingstoneWeaponDuraRegenCooldown.get()),
                        new ToolCauseEffectOnHit("on_hit_effect", "", IConditional.byChance(OrevolutionConfig.POWERS.livingstonePetrifiedChance.get()),
                                OrevolutionConfig.POWERS.livingstonePetrifiedTime.get(), OrevolutionConfig.POWERS.livingstonePetrifiedAmplifier.get(), RegMobEffects.PETRIFIED, null)
                ))
        );
        ItemPowerRegistry.register(OrevolutionTiers.ToolTiers.VERDITE, OrevolutionConfig.POWERS.verditeToolPowers, OrevolutionConfig.POWERS.verditeWeaponPowers,
                new ToolMultiPower(List.of(
                        new ToolRegenerateDurability("regenerates_daylight", IConditional.isReceivingDayLight(),
                                OrevolutionConfig.POWERS.verditeToolDuraRegenPerTick.get(), OrevolutionConfig.POWERS.verditeToolDuraRegenCooldown.get()),
                        new ToolCropIncrease("duplication_crops", IConditional.always(),
                                OrevolutionConfig.POWERS.verditeCropIncrease.get(), OrevolutionConfig.POWERS.verditeCropChance.get())
                )),
                new ToolMultiPower(List.of(
                        new ToolRegenerateDurability("regenerates_daylight", IConditional.isReceivingDayLight(),
                                OrevolutionConfig.POWERS.verditeWeaponDuraRegenPerTick.get(), OrevolutionConfig.POWERS.verditeWeaponDuraRegenCooldown.get()),
                        new ToolCauseEffectOnHit("", "attacker_on_hit_effect", IConditional.byChance(OrevolutionConfig.POWERS.verditeSaturationChance.get()),
                                OrevolutionConfig.POWERS.verditeSaturationTime.get(), OrevolutionConfig.POWERS.verditeSaturationAmplifier.get(), null, MobEffects.SATURATION)
                ))
        );

        if(ModList.get().isLoaded("oreganized")) {
            ItemPowerRegistry.register(ElectrumItems.ELECTRUM_TIER, OrevolutionConfig.MODCOMPAT.electrumToolPowers, OrevolutionConfig.MODCOMPAT.electrumWeaponPowers,
                    new ToolAddEffectPerBlockMined("consecutive_blocks_grants", IConditional.always(),
                            MobEffects.DIG_SPEED, OrevolutionConfig.MODCOMPAT.electrumMinBlocksHaste.get(), OrevolutionConfig.MODCOMPAT.electrumHasteTime.get(), OrevolutionConfig.MODCOMPAT.electrumHasteMaxAmplifier.get()),
                    new ToolDamageConsecutiveHits("consecutive_hits_damage", IConditional.always(),
                            OrevolutionConfig.MODCOMPAT.electrumMaxHitsDamage.get(), true)
            );

            ItemPowerRegistry.register(ArgentumItems.SILVER_TIER, OrevolutionConfig.MODCOMPAT.silverToolPowers, OrevolutionConfig.MODCOMPAT.silverWeaponPowers,
                    new ToolSpeedDurabilityRelative("speed_durability", IConditional.always(), true,
                            OrevolutionConfig.MODCOMPAT.silverMinSpeedMult.get().floatValue(),
                            OrevolutionConfig.MODCOMPAT.silverMaxSpeedMult.get().floatValue(),
                            OrevolutionConfig.MODCOMPAT.silverExponentSpeedMult.get().floatValue()),
                    new ToolDamageDurabilityRelative("damage_durability", IConditional.always(), true,
                            OrevolutionConfig.MODCOMPAT.silverMinDamageMult.get().floatValue(),
                            OrevolutionConfig.MODCOMPAT.silverMaxDamageMult.get().floatValue(),
                            OrevolutionConfig.MODCOMPAT.silverExponentDamageMult.get().floatValue())
            );
        }

        if(ModList.get().isLoaded("thesilverage")) {
            ItemPowerRegistry.register(ModTiers.SILVER, OrevolutionConfig.MODCOMPAT.silverToolPowers, OrevolutionConfig.MODCOMPAT.silverWeaponPowers,
                    new ToolSpeedDurabilityRelative("speed_durability", IConditional.always(), true,
                            OrevolutionConfig.MODCOMPAT.silverMinSpeedMult.get().floatValue(),
                            OrevolutionConfig.MODCOMPAT.silverMaxSpeedMult.get().floatValue(),
                            OrevolutionConfig.MODCOMPAT.silverExponentSpeedMult.get().floatValue()),
                    new ToolDamageDurabilityRelative("damage_durability", IConditional.always(), true,
                            OrevolutionConfig.MODCOMPAT.silverMinDamageMult.get().floatValue(),
                            OrevolutionConfig.MODCOMPAT.silverMaxDamageMult.get().floatValue(),
                            OrevolutionConfig.MODCOMPAT.silverExponentDamageMult.get().floatValue())
            );
        }

        if(ModList.get().isLoaded("copperagebackport")) {
            ItemPowerRegistry.register(CopperTier.INSTANCE, OrevolutionConfig.MODCOMPAT.copperToolPowers, OrevolutionConfig.MODCOMPAT.copperWeaponPowers,
                    new ToolAddEffectPerBlockMined("consecutive_blocks_grants", IConditional.always(),
                            MobEffects.DIG_SPEED, OrevolutionConfig.MODCOMPAT.copperMinBlocksHaste.get(), OrevolutionConfig.MODCOMPAT.copperTimeHaste.get(), OrevolutionConfig.MODCOMPAT.copperMaxHasteAmplifier.get()),
                    new ToolDamageConsecutiveHits("consecutive_hits_damage", IConditional.always(),
                            OrevolutionConfig.MODCOMPAT.copperMaxHitsDamage.get(), true)
            );
        }

        OrevolutionUtils.debug("Registered tool powers");
    }

    public static void registerToolStatModifier() {
        ToolModifiers.registerDefaults(Tiers.WOOD,
                OrevolutionConfig.TOOLSTATS.woodMaxUses,
                OrevolutionConfig.TOOLSTATS.woodHarvestRestrictions,
                OrevolutionConfig.TOOLSTATS.woodTieredItems);

        ToolModifiers.registerDefaults(Tiers.STONE,
                OrevolutionConfig.TOOLSTATS.stoneMaxUses,
                OrevolutionConfig.TOOLSTATS.stoneHarvestRestrictions,
                OrevolutionConfig.TOOLSTATS.stoneTieredItems);

        ToolModifiers.registerDefaults(Tiers.IRON,
                OrevolutionConfig.TOOLSTATS.ironMaxUses,
                OrevolutionConfig.TOOLSTATS.ironHarvestRestrictions,
                OrevolutionConfig.TOOLSTATS.ironTieredItems);

        ToolModifiers.registerDefaults(Tiers.GOLD,
                OrevolutionConfig.TOOLSTATS.goldMaxUses,
                OrevolutionConfig.TOOLSTATS.goldHarvestRestrictions,
                OrevolutionConfig.TOOLSTATS.goldTieredItems);

        ToolModifiers.registerDefaults(Tiers.DIAMOND,
                OrevolutionConfig.TOOLSTATS.diamondMaxUses,
                OrevolutionConfig.TOOLSTATS.diamondHarvestRestrictions,
                OrevolutionConfig.TOOLSTATS.diamondTieredItems);

        ToolModifiers.registerDefaults(Tiers.NETHERITE,
                OrevolutionConfig.TOOLSTATS.netheriteMaxUses,
                OrevolutionConfig.TOOLSTATS.netheriteHarvestRestrictions,
                OrevolutionConfig.TOOLSTATS.netheriteTieredItems);

        ToolModifiers.registerDefaults(OrevolutionTiers.ToolTiers.TIN,
                OrevolutionConfig.TOOLSTATS.tinMaxUses,
                OrevolutionConfig.TOOLSTATS.tinHarvestRestrictions,
                OrevolutionConfig.TOOLSTATS.tinTieredItems);

        ToolModifiers.registerDefaults(OrevolutionTiers.ToolTiers.CASSITERITE,
                OrevolutionConfig.TOOLSTATS.cassiteriteMaxUses,
                OrevolutionConfig.TOOLSTATS.cassiteriteHarvestRestrictions,
                OrevolutionConfig.TOOLSTATS.cassiteriteTieredItems);

        ToolModifiers.registerDefaults(OrevolutionTiers.ToolTiers.PLATINUM,
                OrevolutionConfig.TOOLSTATS.platinumMaxUses,
                OrevolutionConfig.TOOLSTATS.platinumHarvestRestrictions,
                OrevolutionConfig.TOOLSTATS.platinumTieredItems);

        ToolModifiers.registerDefaults(OrevolutionTiers.ToolTiers.MOONSTONE,
                OrevolutionConfig.TOOLSTATS.moonstoneMaxUses,
                OrevolutionConfig.TOOLSTATS.moonstoneHarvestRestrictions,
                OrevolutionConfig.TOOLSTATS.platinumTieredItems);

        ToolModifiers.registerDefaults(OrevolutionTiers.ToolTiers.STEEL,
                OrevolutionConfig.TOOLSTATS.steelMaxUses,
                OrevolutionConfig.TOOLSTATS.steelHarvestRestrictions,
                OrevolutionConfig.TOOLSTATS.steelTieredItems);

        ToolModifiers.registerDefaults(OrevolutionTiers.ToolTiers.TUNGSTEN,
                OrevolutionConfig.TOOLSTATS.tungstenMaxUses,
                OrevolutionConfig.TOOLSTATS.tungstenHarvestRestrictions,
                OrevolutionConfig.TOOLSTATS.tungstenTieredItems);

        ToolModifiers.registerDefaults(OrevolutionTiers.ToolTiers.AETHERSTEEL,
                OrevolutionConfig.TOOLSTATS.aethersteelMaxUses,
                OrevolutionConfig.TOOLSTATS.aethersteelHarvestRestrictions,
                OrevolutionConfig.TOOLSTATS.aethersteelTieredItems);

        ToolModifiers.registerDefaults(OrevolutionTiers.ToolTiers.LIVINGSTONE,
                OrevolutionConfig.TOOLSTATS.livingstoneMaxUses,
                OrevolutionConfig.TOOLSTATS.livingstoneHarvestRestrictions,
                OrevolutionConfig.TOOLSTATS.livingstoneTieredItems);

        ToolModifiers.registerDefaults(OrevolutionTiers.ToolTiers.VERDITE,
                OrevolutionConfig.TOOLSTATS.verditeMaxUses,
                OrevolutionConfig.TOOLSTATS.verditeHarvestRestrictions,
                OrevolutionConfig.TOOLSTATS.verditeTieredItems);


        if(ModList.get().isLoaded("oreganized")) {
            ToolModifiers.registerDefaults(ElectrumItems.ELECTRUM_TIER,
                    OrevolutionConfig.MODCOMPAT.electrumMaxUses,
                    OrevolutionConfig.MODCOMPAT.electrumHarvestRestrictions,
                    OrevolutionConfig.MODCOMPAT.electrumTieredItems);

            ToolModifiers.registerDefaults(ArgentumItems.SILVER_TIER,
                    OrevolutionConfig.MODCOMPAT.silverMaxUses,
                    OrevolutionConfig.MODCOMPAT.silverHarvestRestrictions,
                    OrevolutionConfig.MODCOMPAT.silverTieredItems);
        }

        if(ModList.get().isLoaded("thesilverage")) {
            ToolModifiers.registerDefaults(ModTiers.SILVER,
                    OrevolutionConfig.MODCOMPAT.silverMaxUses,
                    OrevolutionConfig.MODCOMPAT.silverHarvestRestrictions,
                    OrevolutionConfig.MODCOMPAT.silverTieredItems);
        }

        if(ModList.get().isLoaded("copperagebackport")) {
            ToolModifiers.registerDefaults(CopperTier.INSTANCE,
                    OrevolutionConfig.MODCOMPAT.copperMaxUses,
                    OrevolutionConfig.MODCOMPAT.copperHarvestRestrictions,
                    OrevolutionConfig.MODCOMPAT.copperTieredItems);
        }

        OrevolutionUtils.debug("Registered tool stat modifiers");
    }

    public static void miscRegister() {
        registerToolStatModifier();
        registerArmorPowers();
        registerToolsPowers();
    }
}