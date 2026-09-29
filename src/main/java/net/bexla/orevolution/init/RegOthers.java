package net.bexla.orevolution.init;

import com.github.smallinger.copperagebackport.item.tools.CopperTier;
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
import net.bexla.orevolution.content.interfaces.IProgressRule;
import net.bexla.orevolution.content.types.ArmorPowerRegistry;
import net.bexla.orevolution.content.types.ItemPowerRegistry;
import net.bexla.orevolution.content.types.ToolModifiers;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Tiers;
import net.neoforged.fml.ModList;

import java.util.List;

import static net.bexla.orevolution.Orevolution.lc;

public class RegOthers {
    // Effects
    public static final List<Holder<MobEffect>> PLATINUM_TOOL_EFFECTS = List.of(
            RegMobEffects.PURIFICATION, MobEffects.WEAKNESS
    );

    public static void registerArmorPowers() {
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.goldArmorPowers, ArmorMaterials.GOLD,
                new ArmorReduceDamageType("gold_armor", IConditional.always(), DamageTypeTags.BYPASSES_ARMOR, 0.6F));
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.tungstenArmorPowers, OrevolutionTiers.ArmorMats.TUNGSTEN,
                new ArmorMultiPower(List.of(
                        new ArmorReduceDamageType("gold_armor", IConditional.always(), DamageTypeTags.BYPASSES_ARMOR, 0.6F),
                        new ArmorGrantEffects("armor_wearer_grants", IConditional.always(), 20, 0, MobEffects.FIRE_RESISTANCE)
                ))
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.ironArmorPowers, ArmorMaterials.IRON,
                new ArmorReduceDamageType("iron_armor", IConditional.byChance(0.3),
                        DamageTypeTags.IS_PROJECTILE, 0)
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.bronzeArmorPowers, OrevolutionTiers.ArmorMats.BRONZE,
                new ArmorModifyAttribute("attribute_increase", IConditional.always(),
                        Attributes.OXYGEN_BONUS,
                        lc("bronze_oxygen_bonus_orevolution"),
                        8D,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.platinumArmorPowers, OrevolutionTiers.ArmorMats.PLATINUM,
                new ArmorCauseEffectsOnAttacked("", "armor_wearer_on_attacked_wearer", IConditional.always(),
                        100, 1, MobEffects.DAMAGE_BOOST, null)
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.moonstoneArmorPowers, OrevolutionTiers.ArmorMats.MOONSTONE,
                new ArmorMultiPower(List.of(
                        new ArmorCauseEffectsOnAttacked("", "armor_wearer_on_attacked_wearer", IConditional.always(),
                                100, 1, MobEffects.DAMAGE_BOOST, null),
                        new ArmorGrantEffects("armor_wearer_grants", IConditional.always(),
                                100, 1, RegMobEffects.QUICKNESS)
                ))
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.diamondArmorPowers, ArmorMaterials.DIAMOND,
                new ArmorReduceDamageType("diamond_armor", IConditional.always(), OrevolutionTags.Misc.IS_KINETIC, 0.4F)
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.netheriteArmorPowers, ArmorMaterials.NETHERITE,
                new ArmorReduceDamageType("diamond_armor", IConditional.always(), OrevolutionTags.Misc.IS_KINETIC, 0.5F)
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.aethersteelArmorPowers, OrevolutionTiers.ArmorMats.AETHERSTEEL,
                new ArmorMultiPower(List.of(
                        new ArmorReduceDamageType("diamond_armor", IConditional.always(), OrevolutionTags.Misc.IS_KINETIC, 0.6F),
                        new ArmorGrantEffects("armor_wearer_grants", IConditional.always(), 20, 1, MobEffects.HEALTH_BOOST)
                ))
        );

        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.livingstoneArmorPowers, OrevolutionTiers.ArmorMats.LIVINGSTONE,
                new ArmorMultiPower(List.of(
                        new ArmorRegeneration("regenerates_daylight", IConditional.isReceivingDayLight(),
                                2, 100),
                        new ArmorGrantEffects("armor_wearer_grants_daylight", IConditional.isReceivingDayLight(),
                                20, 0, MobEffects.REGENERATION)
                ))
        );
        ArmorPowerRegistry.register(OrevolutionConfig.POWERS.verditeArmorPowers, OrevolutionTiers.ArmorMats.VERDITE,
                new ArmorMultiPower(List.of(
                        new ArmorRegeneration("regenerates_daylight", IConditional.isReceivingDayLight(),
                                2, 80),
                        new ArmorGrantEffects("armor_wearer_grants_daylight", IConditional.isReceivingDayLight(),
                                20, 0, MobEffects.REGENERATION)
                ))
        );


        if(ModList.get().isLoaded("oreganized")) {
            ArmorPowerRegistry.register(OrevolutionConfig.MODCOMPAT.electrumArmorPowers, ElectrumItems.ELECTRUM_MATERIAL,
                    new ArmorMultiPower(List.of(
                        new ArmorGrantEffects("armor_wearer_grants", IConditional.not(IConditional.config(OrevolutionConfig.MODCOMPAT.speedPerArmorPiece)),
                                20, 0, MobEffects.MOVEMENT_SPEED),
                        new ArmorModifyAttribute("attribute_increase", IConditional.always(),
                                Attributes.STEP_HEIGHT,
                                lc("electrum_step_height_orevolution"),
                                1.5D,
                                AttributeModifier.Operation.ADD_VALUE
                        )
                    ))
            );

            ArmorPowerRegistry.register(OrevolutionConfig.MODCOMPAT.silverArmorPowers, ArgentumItems.SILVER_MATERIAL,
                    new ArmorMultiPower(List.of(
                            new ArmorCauseEffectsNearby("silver_armor_aoe", IConditional.targetMobType(EntityTypeTags.UNDEAD),
                                    100, 0, RegMobEffects.LESSER_PURIFICATION, 4D),
                            new ArmorModifyAttribute("attribute_increase", IConditional.always(),
                                    ArgentumAttributes.INVINCIBILITY_FRAMES,
                                    lc("silver_invincibility_orevolution"),
                                    0.8D,
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
                                    6.0D),
                            new ArmorModifyAttribute("attribute_increase", IConditional.always(),
                                    Attributes.BLOCK_INTERACTION_RANGE,
                                    lc("copper_reach_orevolution"),
                                    2.0D,
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
                        1, 0.15),
                new ToolCauseEffectOnHit("on_hit_effect_chance", "", IConditional.byChance(0.3),
                        120, 0, MobEffects.POISON, null)
        );
        ItemPowerRegistry.register(OrevolutionTiers.ToolTiers.CASSITERITE, OrevolutionConfig.POWERS.cassiteriteToolPowers, OrevolutionConfig.POWERS.cassiteriteWeaponPowers,
                new ToolIncreaseDrops("duplication", IConditional.always(),
                        1, 0.3),
                new ToolCauseEffectOnHit("on_hit_effect", "", IConditional.always(),
                        180, 0, MobEffects.POISON, null)
        );
        ItemPowerRegistry.register(Tiers.GOLD, OrevolutionConfig.POWERS.goldToolPowers, OrevolutionConfig.POWERS.goldWeaponPowers,
                new ToolAvoidDurabilityLose("avoid_damage", IConditional.byChance(0.5)),
                new ToolAddXPOnKill("xp_looting", IConditional.always(), 4)
        );
        ItemPowerRegistry.register(Tiers.IRON, OrevolutionConfig.POWERS.ironToolPowers, OrevolutionConfig.POWERS.ironWeaponPowers,
                new ToolsSpeedDurabilityRelative("durability_speed", IConditional.always()),
                new ToolDamageDurabilityRelative("durability_damage", IConditional.always())
        );
        ItemPowerRegistry.register(OrevolutionTiers.ToolTiers.TUNGSTEN, OrevolutionConfig.POWERS.goldToolPowers, OrevolutionConfig.POWERS.goldWeaponPowers,
                new ToolAvoidDurabilityLose("avoid_damage", IConditional.byChance(0.85)),
                new ToolAddXPOnKill("xp_looting", IConditional.always(), 8)
        );
        ItemPowerRegistry.register(OrevolutionTiers.ToolTiers.STEEL, OrevolutionConfig.POWERS.steelToolPowers, OrevolutionConfig.POWERS.steelWeaponPowers,
                new ToolExpandHarvestArea("multi_break", IConditional.always(), 1),
                new ToolModifyCritDamage("crit_damage", IConditional.always(), Operator.MULTIPLY, 1.65f)
        );
        ItemPowerRegistry.register(OrevolutionTiers.ToolTiers.PLATINUM, OrevolutionConfig.POWERS.platinumToolPowers, OrevolutionConfig.POWERS.platinumWeaponPowers,
                new ToolIncreaseBlockXP("xp_increase", IConditional.always(), 1),
                new ToolCauseEffectOnHit("undead_on_hit", "", IConditional.targetMobType(EntityTypeTags.UNDEAD),
                        true, 160, 0, PLATINUM_TOOL_EFFECTS, null)
        );
        ItemPowerRegistry.register(OrevolutionTiers.ToolTiers.MOONSTONE, OrevolutionConfig.POWERS.moonstoneToolPowers, OrevolutionConfig.POWERS.moonstoneWeaponPowers,
                new ToolIncreaseBlockXP("xp_increase", IConditional.always(), 3),
                new ToolCauseEffectOnHit("monster_on_hit", "", IConditional.targetIsHostile(),
                        true, 160, 1, PLATINUM_TOOL_EFFECTS, null)
        );
        ItemPowerRegistry.register(Tiers.DIAMOND, OrevolutionConfig.POWERS.diamondToolPowers, OrevolutionConfig.POWERS.diamondWeaponPowers,
                new ToolModifyMiningSpeed("hardness_speed", IConditional.always(),
                        (stack, state, s) -> s + state.getBlock().defaultDestroyTime(), "funny value"),
                new ToolModifyDealtDamage("on_hit_armored", IConditional.targetHasArmor(), Operator.ADD, 2F)
        );
        ItemPowerRegistry.register(Tiers.NETHERITE, OrevolutionConfig.POWERS.netheriteToolPowers, OrevolutionConfig.POWERS.netheriteWeaponPowers,
                new ToolAutosmelt("autosmelt", IConditional.oneOf(IConditional.isBlockstateTaggedAs(OrevolutionTags.Blocks.AUTOSMELT))),
                new ToolModifyDealtDamage("on_hit_weakened", IConditional.targetHPPercentage(0.5f, true),
                        Operator.MULTIPLY, 1.5f)
        );
        ItemPowerRegistry.register(OrevolutionTiers.ToolTiers.AETHERSTEEL, OrevolutionConfig.POWERS.aethersteelToolPowers, OrevolutionConfig.POWERS.aethersteelWeaponPowers,
                new ToolMultiPower(List.of(
                        new ToolAutosmelt("autosmelt", IConditional.oneOf(IConditional.isBlockstateTaggedAs(OrevolutionTags.Blocks.AUTOSMELT))),
                        new OrevolutionToolPower("aethersteel", IConditional.always()) // Power is handled in net.bexla.orevolution.events.MiscSubscriber
                )),
                new ToolMultiPower(List.of(
                        new ToolModifyDealtDamage("on_hit_weakened", IConditional.targetHPPercentage(0.65f, true),
                                Operator.MULTIPLY, 1.5f),
                        new OrevolutionToolPower("aethersteel", IConditional.always()) // Power is handled in net.bexla.orevolution.events.MiscSubscriber
                ))
        );
        ItemPowerRegistry.register(OrevolutionTiers.ToolTiers.LIVINGSTONE, OrevolutionConfig.POWERS.livingstoneToolPowers, OrevolutionConfig.POWERS.livingstoneWeaponPowers,
                new ToolMultiPower(List.of(
                        new ToolRegenerateDurability("regenerates_daylight", IConditional.isReceivingDayLight(),
                                1, 100),
                        new ToolCropIncrease("duplication_crops", IConditional.always(),
                                1, 0.4)
                )),
                new ToolMultiPower(List.of(
                        new ToolRegenerateDurability("regenerates_daylight", IConditional.isReceivingDayLight(),
                                1, 100),
                        new ToolCauseEffectOnHit("on_hit_effect_chance", "", IConditional.byChance(0.1),
                                1, 0, RegMobEffects.PETRIFIED, null)
                ))
        );
        ItemPowerRegistry.register(OrevolutionTiers.ToolTiers.VERDITE, OrevolutionConfig.POWERS.verditeToolPowers, OrevolutionConfig.POWERS.verditeWeaponPowers,
                new ToolMultiPower(List.of(
                        new ToolRegenerateDurability("regenerates_daylight", IConditional.isReceivingDayLight(),
                                1, 80),
                        new ToolCropIncrease("duplication_crops", IConditional.always(),
                                2, 0.6)
                )),
                new ToolMultiPower(List.of(
                        new ToolRegenerateDurability("regenerates_daylight", IConditional.isReceivingDayLight(),
                                1, 80),
                        new ToolCauseEffectOnHit("", "attacker_on_hit_effect_chance", IConditional.byChance(0.2),
                                2, 0, null, MobEffects.SATURATION)
                ))
        );

        if(ModList.get().isLoaded("oreganized")) {
            ItemPowerRegistry.register(ElectrumItems.ELECTRUM_TIER, OrevolutionConfig.MODCOMPAT.electrumToolPowers, OrevolutionConfig.MODCOMPAT.electrumWeaponPowers,
                    new ToolAddEffectPerBlockMined("consecutive_blocks_grants", IConditional.not(IConditional.config(OrevolutionConfig.MODCOMPAT.kineticDamage)), MobEffects.DIG_SPEED, 6, 60, 2),
                    new ToolDamageConsecutiveHits("consecutive_hits_damage", IConditional.not(IConditional.config(OrevolutionConfig.MODCOMPAT.kineticDamage)), 4, true)
            );

            ItemPowerRegistry.register(ArgentumItems.SILVER_TIER, OrevolutionConfig.MODCOMPAT.silverToolPowers, OrevolutionConfig.MODCOMPAT.silverWeaponPowers,
                    new ToolModifyMiningSpeed("speed_durability", IConditional.always(),
                            (stack, state, speed) -> (speed * 2) / OrevolutionUtils.durabilityPercentage(stack), "%s"),
                    new ToolModifyDealtDamage("damage_durability", IConditional.always(),
                            (stack, target, attacker, source, damage) -> (damage * 2) / OrevolutionUtils.durabilityPercentage(stack), "%s")
            );
        }

        if(ModList.get().isLoaded("copperagebackport")) {
            ItemPowerRegistry.register(CopperTier.INSTANCE, OrevolutionConfig.MODCOMPAT.electrumToolPowers, OrevolutionConfig.MODCOMPAT.electrumWeaponPowers,
                    new ToolAddEffectPerBlockMined("consecutive_blocks_grants", IConditional.always(), MobEffects.DIG_SPEED, 4, 60, 1),
                    new ToolDamageConsecutiveHits("consecutive_hits_damage", IConditional.always(), 2, true)
            );
        }

        OrevolutionUtils.debug("Registered tool powers");
    }

    public static void registerToolStatModifier() {
        ToolModifiers.registerRule(Tiers.STONE,
                IProgressRule.configDisabled(OrevolutionConfig.TOOLSTATS.stoneFollowsTin, OrevolutionTags.Blocks.NEEDS_TIN_TOOL));
        ToolModifiers.registerRule(Tiers.IRON,
                IProgressRule.configDisabled(OrevolutionConfig.TOOLSTATS.ironFollowsPlatinum, OrevolutionTags.Blocks.NEEDS_PLATINUM_TOOL));
        ToolModifiers.registerRule(Tiers.GOLD, IProgressRule.incorrectTag(OrevolutionConfig.TOOLSTATS.goldBuff, BlockTags.INCORRECT_FOR_IRON_TOOL));

        ToolModifiers.registerRule(OrevolutionTiers.ToolTiers.TUNGSTEN, IProgressRule.incorrectTag(OrevolutionConfig.TOOLSTATS.goldBuff, OrevolutionTags.Blocks.NEEDS_PLATINUM_TOOL));

        ToolModifiers.registerDurability(Tiers.WOOD, OrevolutionConfig.TOOLSTATS.woodMaxUses);
        ToolModifiers.registerDurability(Tiers.STONE, OrevolutionConfig.TOOLSTATS.stoneMaxUses);
        ToolModifiers.registerDurability(Tiers.IRON, OrevolutionConfig.TOOLSTATS.ironMaxUses);
        ToolModifiers.registerDurability(Tiers.GOLD, OrevolutionConfig.TOOLSTATS.goldMaxUses);
        ToolModifiers.registerDurability(Tiers.DIAMOND, OrevolutionConfig.TOOLSTATS.diamondMaxUses);
        ToolModifiers.registerDurability(Tiers.NETHERITE, OrevolutionConfig.TOOLSTATS.netheriteMaxUses);
        ToolModifiers.registerDurability(OrevolutionTiers.ToolTiers.TIN, OrevolutionConfig.TOOLSTATS.tinMaxUses);
        ToolModifiers.registerDurability(OrevolutionTiers.ToolTiers.PLATINUM, OrevolutionConfig.TOOLSTATS.platMaxUses);
        ToolModifiers.registerDurability(OrevolutionTiers.ToolTiers.STEEL, OrevolutionConfig.TOOLSTATS.steelMaxUses);
        ToolModifiers.registerDurability(OrevolutionTiers.ToolTiers.AETHERSTEEL, OrevolutionConfig.TOOLSTATS.aetherMaxUses);
        ToolModifiers.registerDurability(OrevolutionTiers.ToolTiers.LIVINGSTONE, OrevolutionConfig.TOOLSTATS.livingstoneMaxUses);
        ToolModifiers.registerDurability(OrevolutionTiers.ToolTiers.VERDITE, OrevolutionConfig.TOOLSTATS.verditeMaxUses);
        ToolModifiers.registerDurability(OrevolutionTiers.ToolTiers.MOONSTONE, OrevolutionConfig.TOOLSTATS.moonstoneMaxUses);

        if(ModList.get().isLoaded("oreganized")) {
            ToolModifiers.registerRule(ElectrumItems.ELECTRUM_TIER,
                    IProgressRule.configDisabled(OrevolutionConfig.MODCOMPAT.electrumFollowsPlatinum, OrevolutionTags.Blocks.NEEDS_PLATINUM_TOOL));
            ToolModifiers.registerDurability(ElectrumItems.ELECTRUM_TIER, OrevolutionConfig.MODCOMPAT.electrumMaxUses);
        }

        if(ModList.get().isLoaded("copperagebackport")) {
            ToolModifiers.registerRule(OrevolutionTiers.ToolTiers.TIN,
                    IProgressRule.configEnabled(OrevolutionConfig.MODCOMPAT.tinFollowsCopper, OrevolutionTags.Blocks.INCORRECT_FOR_TIN_ALT).not());
            ToolModifiers.registerRule(CopperTier.INSTANCE,
                    IProgressRule.configEnabled(OrevolutionConfig.MODCOMPAT.tinFollowsCopper, OrevolutionTags.Blocks.NEEDS_TIN_TOOL));

            ToolModifiers.registerRule(CopperTier.INSTANCE,
                    IProgressRule.configDisabled(OrevolutionConfig.MODCOMPAT.copperFollowsTin, OrevolutionTags.Blocks.NEEDS_TIN_TOOL));
            ToolModifiers.registerDurability(CopperTier.INSTANCE, OrevolutionConfig.MODCOMPAT.copperMaxUses);
        }

        OrevolutionUtils.debug("Registered tool stat modifiers");
    }

    public static void miscRegister() {
        registerToolStatModifier();
        registerArmorPowers();
        registerToolsPowers();
    }
}