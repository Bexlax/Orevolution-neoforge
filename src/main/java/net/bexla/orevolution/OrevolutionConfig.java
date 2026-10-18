package net.bexla.orevolution;

import com.teamabnormals.blueprint.core.annotations.ConfigKey;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class OrevolutionConfig {
    public static final StartUp STARTUP;
    private static final ModConfigSpec STARTUP_SPEC;

    public static final Common COMMON;
    public static final Client CLIENT;
    public static final Powers POWERS;
    public static final ModCompat MODCOMPAT;
    public static final ToolStats TOOLSTATS;
    private static final ModConfigSpec COMMON_SPEC;
    private static final ModConfigSpec CLIENT_SPEC;
    private static final ModConfigSpec POWERS_SPEC;
    private static final ModConfigSpec MODCOMPAT_SPEC;
    private static final ModConfigSpec TOOLSTATS_SPEC;

    public static class Powers {
        public final ConfigValue<Boolean> tinToolPowers;
        public final ConfigValue<Double> tinDupeChance;
        public final ConfigValue<Integer> tinDupeDrops;

        public final ConfigValue<Boolean> tinWeaponPowers;
        public final ConfigValue<Double> tinPoisonChance;
        public final ConfigValue<Integer> tinPoisonTime;
        public final ConfigValue<Integer> tinPoisonAmplifier;

        public final ConfigValue<Boolean> tinArmorPowers;

        public final ConfigValue<Boolean> cassiteriteToolPowers;
        public final ConfigValue<Double> cassiteriteDupeChance;
        public final ConfigValue<Integer> cassiteriteDupeDrops;

        public final ConfigValue<Boolean> cassiteriteWeaponPowers;
        public final ConfigValue<Integer> cassiteritePoisonTime;
        public final ConfigValue<Integer> cassiteritePoisonAmplifier;

        public final ConfigValue<Boolean> cassiteriteArmorPowers;
        public final ConfigValue<Double> cassiteriteArmorAttribute;
        public final ConfigValue<Integer> cassiteriteAOETime;
        public final ConfigValue<Integer> cassiteriteAOEAmplifier;
        public final ConfigValue<Double> cassiteriteAOERange;

        public final ConfigValue<Boolean> goldToolPowers;
        public final ConfigValue<Double> goldDamageChance;

        public final ConfigValue<Boolean> goldWeaponPowers;
        public final ConfigValue<Integer> goldXPAmount;

        public final ConfigValue<Boolean> goldArmorPowers;
        public final ConfigValue<Double> goldResidualDamage;

        public final ConfigValue<Boolean> tungstenToolPowers;
        public final ConfigValue<Double> tungstenDamageChance;

        public final ConfigValue<Boolean> tungstenWeaponPowers;
        public final ConfigValue<Integer> tungstenXPAmount;

        public final ConfigValue<Boolean> tungstenArmorPowers;
        public final ConfigValue<Double> tungstenResidualDamage;

        public final ConfigValue<Boolean> ironToolPowers;
        public final ConfigValue<Double> ironMinSpeedMult;
        public final ConfigValue<Double> ironMaxSpeedMult;
        public final ConfigValue<Double> ironExponentSpeedMult;

        public final ConfigValue<Boolean> ironWeaponPowers;
        public final ConfigValue<Double> ironMinDamageMult;
        public final ConfigValue<Double> ironMaxDamageMult;
        public final ConfigValue<Double> ironExponentDamageMult;

        public final ConfigValue<Boolean> ironArmorPowers;
        public final ConfigValue<Double> ironIgnoreChance;

        public final ConfigValue<Boolean> steelToolPowers;
        public final ConfigValue<Integer> steelRadius;

        public final ConfigValue<Boolean> steelWeaponPowers;
        public final ModConfigSpec.DoubleValue steelCritMult;

        public final ConfigValue<Boolean> platinumToolPowers;
        public final ConfigValue<Integer> platinumXPMult;

        public final ConfigValue<Boolean> platinumWeaponPowers;
        public final ConfigValue<Boolean> platinumForceUndeadEffects;
        public final ConfigValue<Integer> platinumUndeadEffectsTime;
        public final ConfigValue<Integer> platinumUndeadEffectsAmplifier;

        public final ConfigValue<Boolean> platinumArmorPowers;
        public final ConfigValue<Integer> platinumBaseStrenghtTime;
        public final ConfigValue<Integer> platinumStrenghtAmplifier;

        public final ConfigValue<Boolean> moonstoneToolPowers;
        public final ConfigValue<Integer> moonstoneXPMult;

        public final ConfigValue<Boolean> moonstoneWeaponPowers;
        public final ConfigValue<Boolean> moonstoneForceMonsterEffects;
        public final ConfigValue<Integer> moonstoneMonsterEffectsTime;
        public final ConfigValue<Integer> moonstoneMonsterEffectsAmplifier;

        public final ConfigValue<Boolean> moonstoneArmorPowers;
        public final ConfigValue<Integer> moonstoneBaseStrenghtTime;
        public final ConfigValue<Integer> moonstoneStrenghtAmplifier;
        public final ConfigValue<Integer> moonstoneQuicknessAmplifier;

        public final ConfigValue<Boolean> diamondToolPowers;
        
        public final ConfigValue<Boolean> diamondWeaponPowers;
        public final ConfigValue<Double> diamondArmoredDamage;

        public final ConfigValue<Boolean> diamondArmorPowers;
        public final ConfigValue<Double> diamondResidualDamage;

        public final ConfigValue<Boolean> netheriteToolPowers;

        public final ConfigValue<Boolean> netheriteWeaponPowers;
        public final ConfigValue<Boolean> netheritePercentageLowerThan;
        public final ConfigValue<Double> netheritePercentage;
        public final ConfigValue<Double> netheriteDamageMult;

        public final ConfigValue<Boolean> netheriteArmorPowers;
        public final ConfigValue<Double> netheriteResidualDamage;

        public final ConfigValue<Boolean> aethersteelToolPowers;

        public final ConfigValue<Boolean> aethersteelWeaponPowers;
        public final ConfigValue<Boolean> aethersteelPercentageLowerThan;
        public final ConfigValue<Double> aethersteelPercentage;
        public final ConfigValue<Double> aethersteelDamageMult;

        public final ConfigValue<Boolean> aethersteelArmorPowers;
        public final ConfigValue<Double> aethersteelResidualDamage;
        public final ConfigValue<Integer> aethersteelHealthBoostAmplifier;

        public final ConfigValue<Boolean> aethersteelKeepOnDeath;

        public final ConfigValue<Boolean> livingstoneToolPowers;
        public final ConfigValue<Integer> livingstoneToolDuraRegenPerTick;
        public final ConfigValue<Integer> livingstoneToolDuraRegenCooldown;
        public final ConfigValue<Integer> livingstoneCropIncrease;
        public final ConfigValue<Double> livingstoneCropChance;

        public final ConfigValue<Boolean> livingstoneWeaponPowers;
        public final ConfigValue<Integer> livingstoneWeaponDuraRegenPerTick;
        public final ConfigValue<Integer> livingstoneWeaponDuraRegenCooldown;
        public final ConfigValue<Double> livingstonePetrifiedChance;
        public final ConfigValue<Integer> livingstonePetrifiedTime;
        public final ConfigValue<Integer> livingstonePetrifiedAmplifier;

        public final ConfigValue<Boolean> livingstoneArmorPowers;
        public final ConfigValue<Integer> livingstoneArmorDuraRegenPerTick;
        public final ConfigValue<Integer> livingstoneArmorDuraRegenCooldown;
        public final ConfigValue<Integer> livingstoneRegenerationAmplifier;

        public final ConfigValue<Boolean> verditeToolPowers;
        public final ConfigValue<Integer> verditeToolDuraRegenPerTick;
        public final ConfigValue<Integer> verditeToolDuraRegenCooldown;
        public final ConfigValue<Integer> verditeCropIncrease;
        public final ConfigValue<Double> verditeCropChance;

        public final ConfigValue<Boolean> verditeWeaponPowers;
        public final ConfigValue<Integer> verditeWeaponDuraRegenPerTick;
        public final ConfigValue<Integer> verditeWeaponDuraRegenCooldown;
        public final ConfigValue<Double> verditeSaturationChance;
        public final ConfigValue<Integer> verditeSaturationTime;
        public final ConfigValue<Integer> verditeSaturationAmplifier;

        public final ConfigValue<Boolean> verditeArmorPowers;
        public final ConfigValue<Integer> verditeDuraRegenPerTick;
        public final ConfigValue<Integer> verditeDuraRegenCooldown;
        public final ConfigValue<Integer> verditeRegenerationTime;
        public final ConfigValue<Integer> verditeRegenerationAmplifier;

        public final ConfigValue<Boolean> divingBronzeArmorPowers;
        public final ConfigValue<Double> divingBronzeExtraOxygenAttribute;

        public final ConfigValue<Boolean> bronzeArmorPowers;

        private Powers(ModConfigSpec.Builder builder) {
            builder.push("diving_bronze");

            divingBronzeArmorPowers = builder
                    .translation("orevolution.config.diving_bronze_armor_powers")
                    .define("diving_bronze_armor_powers", true);
            divingBronzeExtraOxygenAttribute = builder
                    .gameRestart()
                    .translation("orevolution.config.diving_bronze_extra_oxygen_attribute")
                    .defineInRange("diving_bronze_extra_oxygen_attribute", 8D, 0D, 1024D);

            builder.pop();

            builder.push("bronze");

            bronzeArmorPowers = builder
                    .translation("orevolution.config.bronze_armor_powers")
                    .define("diving_bronze_armor_powers", true);

            builder.pop();


            builder.push("tin");

            tinToolPowers = builder
                    .translation("orevolution.config.tin_tool_powers")
                    .define("tin_tool_powers", true);
            tinDupeChance = builder
                    .gameRestart()
                    .translation("orevolution.config.tin_dupe_chance")
                    .defineInRange("tin_dupe_chance", 0.15D, 0D, 1D);
            tinDupeDrops = builder
                    .gameRestart()
                    .translation("orevolution.config.tin_dupe_drops")
                    .defineInRange("tin_dupe_drops", 1, 1, Integer.MAX_VALUE);
            
            tinWeaponPowers = builder
                    .translation("orevolution.config.tin_weapon_powers")
                    .define("tin_weapon_powers", true);
            tinPoisonChance = builder
                    .gameRestart()
                    .translation("orevolution.config.tin_poison_chance")
                    .defineInRange("tin_poison_chance", 0.45D, 0D, 1D);
            tinPoisonTime = builder
                    .gameRestart()
                    .translation("orevolution.config.tin_poison_time")
                    .defineInRange("tin_poison_time", 240, 1, Integer.MAX_VALUE);
            tinPoisonAmplifier = builder
                    .gameRestart()
                    .translation("orevolution.config.tin_poison_amplifier")
                    .defineInRange("tin_poison_amplifier", 0, 0, 255);

            tinArmorPowers = builder
                    .translation("orevolution.config.tin_armor_powers")
                    .define("tin_armor_powers", true);

            builder.pop();

            builder.push("cassiterite");

            cassiteriteToolPowers = builder
                    .translation("orevolution.config.cassiterite_tool_powers")
                    .define("cassiterite_tool_powers", true);
            cassiteriteDupeChance = builder
                    .gameRestart()
                    .translation("orevolution.config.cassiterite_dupe_chance")
                    .defineInRange("cassiterite_dupe_chance", 0.3D, 0D, 1D);
            cassiteriteDupeDrops = builder
                    .gameRestart()
                    .translation("orevolution.config.cassiterite_dupe_drops")
                    .defineInRange("cassiterite_dupe_drops", 1, 1, Integer.MAX_VALUE);

            cassiteriteWeaponPowers = builder
                    .translation("orevolution.config.cassiterite_weapon_powers")
                    .define("cassiterite_weapon_powers", true);
            cassiteritePoisonTime = builder
                    .gameRestart()
                    .translation("orevolution.config.cassiterite_poison_time")
                    .defineInRange("cassiterite_poison_time", 120, 1, Integer.MAX_VALUE);
            cassiteritePoisonAmplifier = builder
                    .gameRestart()
                    .translation("orevolution.config.cassiterite_poison_amplifier")
                    .defineInRange("cassiterite_poison_amplifier", 1, 0, 255);

            cassiteriteArmorPowers = builder
                    .translation("orevolution.config.cassiterite_armor_powers")
                    .define("cassiterite_armor_powers", true);
            cassiteriteArmorAttribute = builder
                    .gameRestart()
                    .translation("orevolution.config.cassiterite_armor_attribute")
                    .defineInRange("cassiterite_armor_attribute", 4D, 0D, 30D);
            cassiteriteAOETime = builder
                    .gameRestart()
                    .translation("orevolution.config.cassiterite_aoe_time")
                    .defineInRange("cassiterite_aoe_time", 100, 1, Integer.MAX_VALUE);
            cassiteriteAOEAmplifier = builder
                    .gameRestart()
                    .translation("orevolution.config.cassiterite_aoe_amplifier")
                    .defineInRange("cassiterite_aoe_amplifier", 0, 1, Integer.MAX_VALUE);
            cassiteriteAOERange = builder
                    .gameRestart()
                    .translation("orevolution.config.cassiterite_aoe_range")
                    .defineInRange("cassiterite_aoe_range", 4D, 0D, Double.MAX_VALUE);

            builder.pop();

            builder.push("gold");

            goldToolPowers = builder
                    .translation("orevolution.config.gold_tool_powers")
                    .define("gold_tool_powers", true);
            goldDamageChance = builder
                    .gameRestart()
                    .translation("orevolution.config.gold_damage_chance")
                    .defineInRange("gold_damage_chance", 0.5D, 0D, 1D);

            goldWeaponPowers = builder
                    .translation("orevolution.config.gold_weapon_powers")
                    .define("gold_weapon_powers", true);
            goldXPAmount = builder
                    .gameRestart()
                    .translation("orevolution.config.gold_xp_amount")
                    .defineInRange("gold_xp_amount", 4, 1, Integer.MAX_VALUE);

            goldArmorPowers = builder
                    .translation("orevolution.config.gold_armor_powers")
                    .define("gold_armor_powers", true);
            goldResidualDamage = builder
                    .gameRestart()
                    .translation("orevolution.config.gold_residual_damage")
                    .defineInRange("gold_residual_damage", 0.6D, 0D, 1D);

            builder.pop();

            builder.push("tungsten");

            tungstenToolPowers = builder
                    .translation("orevolution.config.tungsten_tool_powers")
                    .define("tungsten_tool_powers", true);
            tungstenDamageChance = builder
                    .gameRestart()
                    .translation("orevolution.config.tungsten_damage_chance")
                    .defineInRange("tungsten_damage_chance", 0.85D, 0D, 1D);

            tungstenWeaponPowers = builder
                    .translation("orevolution.config.tungsten_weapon_powers")
                    .define("tungsten_weapon_powers", true);
            tungstenXPAmount = builder
                    .gameRestart()
                    .translation("orevolution.config.tungsten_xp_amount")
                    .defineInRange("tungsten_xp_amount", 8, 1, Integer.MAX_VALUE);

            tungstenArmorPowers = builder
                    .translation("orevolution.config.tungsten_armor_powers")
                    .define("tungsten_armor_powers", true);
            tungstenResidualDamage = builder
                    .gameRestart()
                    .translation("orevolution.config.tungsten_residual_damage")
                    .defineInRange("tungsten_residual_damage", 0.6D, 0D, 1D);

            builder.pop();

            builder.push("iron");

            ironToolPowers = builder
                    .translation("orevolution.config.iron_tool_powers")
                    .define("iron_tool_powers", true);
            ironMinSpeedMult = builder
                    .gameRestart()
                    .translation("orevolution.config.iron_min_speed_mult")
                    .defineInRange("iron_min_speed_mult", 0.75D, 0D, Double.MAX_VALUE);
            ironMaxSpeedMult = builder
                    .gameRestart()
                    .translation("orevolution.config.iron_max_speed_mult")
                    .defineInRange("iron_max_speed_mult", 2.5D, 0D, Double.MAX_VALUE);
            ironExponentSpeedMult = builder
                    .gameRestart()
                    .translation("orevolution.config.iron_exponent_speed_mult")
                    .defineInRange("iron_exponent_durability", 1.5D, 0D, Double.MAX_VALUE);

            ironWeaponPowers = builder
                    .translation("orevolution.config.iron_weapon_powers")
                    .define("iron_weapon_powers", true);
            ironMinDamageMult = builder
                    .gameRestart()
                    .translation("orevolution.config.iron_min_damage_mult")
                    .defineInRange("iron_min_damage_mult", 0.75D, 0D, Double.MAX_VALUE);
            ironMaxDamageMult = builder
                    .gameRestart()
                    .translation("orevolution.config.iron_max_damage_mult")
                    .defineInRange("iron_max_damage_mult", 2.5D, 0D, Double.MAX_VALUE);
            ironExponentDamageMult = builder
                    .gameRestart()
                    .translation("orevolution.config.iron_exponent_damage_mult")
                    .defineInRange("iron_exponent_damage_mult", 1.5D, 0D, Double.MAX_VALUE);

            ironArmorPowers = builder
                    .translation("orevolution.config.iron_armor_powers")
                    .define("iron_armor_powers", true);
            ironIgnoreChance = builder
                    .gameRestart()
                    .translation("orevolution.config.iron_ignore_chance")
                    .defineInRange("iron_ignore_chance", 0.355D, 0D, 1D);

            builder.pop();

            builder.push("steel");

            steelToolPowers = builder
                    .translation("orevolution.config.steel_tool_powers")
                    .define("steel_tool_powers", true);
            steelRadius = builder
                    .gameRestart()
                    .translation("orevolution.config.steel_radius")
                    .defineInRange("steel_radius", 1, 1, Integer.MAX_VALUE);

            steelWeaponPowers = builder
                    .translation("orevolution.config.steel_weapon_powers")
                    .define("steel_weapon_powers", true);
            steelCritMult = builder
                    .gameRestart()
                    .translation("orevolution.config.steel_crit_mult")
                    .defineInRange("steel_crit_mult", 1.65D, 1D, Float.MAX_VALUE);

            builder.pop();

            builder.push("platinum");

            platinumToolPowers = builder
                    .translation("orevolution.config.platinum_tool_powers")
                    .define("platinum_tool_powers", true);
            platinumXPMult = builder
                    .gameRestart()
                    .translation("orevolution.config.platinum_xp_mult")
                    .defineInRange("platinum_xp_mult", 2, 1, Integer.MAX_VALUE);

            platinumWeaponPowers = builder
                    .translation("orevolution.config.platinum_weapon_powers")
                    .define("platinum_weapon_powers", true);
            platinumForceUndeadEffects = builder
                    .gameRestart()
                    .translation("orevolution.config.platinum_force_undead_effects")
                    .define("platinum_force_undead_effects", true);
            platinumUndeadEffectsTime = builder
                    .gameRestart()
                    .translation("orevolution.config.platinum_undead_effects_time")
                    .defineInRange("platinum_undead_effects_time", 160, 1, Integer.MAX_VALUE);
            platinumUndeadEffectsAmplifier = builder
                    .gameRestart()
                    .translation("orevolution.config.platinum_undead_effects_amplifier")
                    .defineInRange("platinum_undead_effects_amplifier", 0, 0, 255);

            platinumArmorPowers = builder
                    .translation("orevolution.config.platinum_armor_powers")
                    .define("platinum_armor_powers", true);
            platinumBaseStrenghtTime = builder
                    .gameRestart()
                    .translation("orevolution.config.platinum_base_strenght_time")
                    .defineInRange("platinum_base_strenght_time", 100, 0, Integer.MAX_VALUE);
            platinumStrenghtAmplifier = builder
                    .gameRestart()
                    .translation("orevolution.config.platinum_strenght_amplifier")
                    .defineInRange("platinum_strenght_amplifier", 1, 0, Integer.MAX_VALUE);

            builder.pop();

            builder.push("moonstone");

            moonstoneToolPowers = builder
                    .translation("orevolution.config.moonstone_tool_powers")
                    .define("moonstone_tool_powers", true);
            moonstoneXPMult = builder
                    .gameRestart()
                    .translation("orevolution.config.moonstone_xp_mult")
                    .defineInRange("moonstone_xp_mult", 4, 1, Integer.MAX_VALUE);

            moonstoneWeaponPowers = builder
                    .translation("orevolution.config.moonstone_weapon_powers")
                    .define("moonstone_weapon_powers", true);
            moonstoneForceMonsterEffects = builder
                    .gameRestart()
                    .translation("orevolution.config.moonstone_force_monster_effects")
                    .define("moonstone_force_monster_effects", true);
            moonstoneMonsterEffectsTime = builder
                    .gameRestart()
                    .translation("orevolution.config.moonstone_monster_effects_time")
                    .defineInRange("moonstone_monster_effects_time", 160, 1, Integer.MAX_VALUE);
            moonstoneMonsterEffectsAmplifier = builder
                    .gameRestart()
                    .translation("orevolution.config.moonstone_monster_effects_amplifier")
                    .defineInRange("moonstone_monster_effects_amplifier", 1, 0, 255);

            moonstoneArmorPowers = builder
                    .translation("orevolution.config.moonstone_armor_powers")
                    .define("moonstone_armor_powers", true);
            moonstoneBaseStrenghtTime = builder
                    .gameRestart()
                    .translation("orevolution.config.moonstone_base_strenght_time")
                    .defineInRange("moonstone_base_strenght_time", 100, 0, Integer.MAX_VALUE);
            moonstoneStrenghtAmplifier = builder
                    .gameRestart()
                    .translation("orevolution.config.moonstone_strenght_amplifier")
                    .defineInRange("moonstone_strenght_amplifier", 1, 0, Integer.MAX_VALUE);
            moonstoneQuicknessAmplifier = builder
                    .gameRestart()
                    .translation("orevolution.config.moonstone_quickness_amplifier")
                    .defineInRange("moonstone_quickness_amplifier", 1, 0, Integer.MAX_VALUE);

            builder.pop();

            builder.push("diamond");

            diamondToolPowers = builder
                    .translation("orevolution.config.diamond_tool_powers")
                    .define("diamond_tool_powers", true);

            diamondWeaponPowers = builder
                    .translation("orevolution.config.diamond_weapon_powers")
                    .define("diamond_weapon_powers", true);
            diamondArmoredDamage = builder
                    .gameRestart()
                    .translation("orevolution.config.diamond_armored_damage")
                    .defineInRange("diamond_armored_damage", 3D, 1D, Double.MAX_VALUE);

            diamondArmorPowers = builder
                    .translation("orevolution.config.diamond_armor_powers")
                    .define("diamond_armor_powers", true);
            diamondResidualDamage = builder
                    .gameRestart()
                    .translation("orevolution.config.diamond_residual_damage")
                    .defineInRange("diamond_residual_damage", 0.4D, 0D, 1D);

            builder.pop();

            builder.push("netherite");

            netheriteToolPowers = builder
                    .translation("orevolution.config.netherite_tool_powers")
                    .define("netherite_tool_powers", true);

            netheriteWeaponPowers = builder
                    .translation("orevolution.config.netherite_weapon_powers")
                    .define("netherite_weapon_powers", true);

            netheritePercentageLowerThan = builder
                    .gameRestart()
                    .translation("orevolution.config.netherite_percentage_lower_than")
                    .define("netherite_percentage_lower_than", true);
            netheritePercentage = builder
                    .gameRestart()
                    .translation("orevolution.config.netherite_hp_percentage")
                    .defineInRange("netherite_hp_percentage", 0.65D, 0D, 1D);
            netheriteDamageMult = builder
                    .gameRestart()
                    .translation("orevolution.config.netherite_damage_mult")
                    .defineInRange("netherite_damage_mult", 1.5D, 1D, Double.MAX_VALUE);

            netheriteArmorPowers = builder
                    .gameRestart()
                    .translation("orevolution.config.netherite_armor_powers")
                    .define("netherite_armor_powers", true);
            netheriteResidualDamage = builder
                    .translation("orevolution.config.netherite_residual_damage")
                    .defineInRange("netherite_residual_damage", 0.5D, 0D, 1D);

            builder.pop();

            builder.push("aethersteel");

            aethersteelKeepOnDeath = builder
                    .translation("orevolution.config.aethersteel_keep_on_death")
                    .define("aethersteel_keep_on_death", true);

            aethersteelToolPowers = builder
                    .translation("orevolution.config.aethersteel_tool_powers")
                    .define("aethersteel_tool_powers", true);

            aethersteelWeaponPowers = builder
                    .translation("orevolution.config.aethersteel_weapon_powers")
                    .define("aethersteel_weapon_powers", true);
            aethersteelPercentageLowerThan = builder
                    .gameRestart()
                    .translation("orevolution.config.aethersteel_percentage_lower_than")
                    .define("aethersteel_percentage_lower_than", true);
            aethersteelPercentage = builder
                    .gameRestart()
                    .translation("orevolution.config.aethersteel_hp_percentage")
                    .defineInRange("aethersteel_hp_percentage", 0.65D, 0D, 1D);
            aethersteelDamageMult = builder
                    .gameRestart()
                    .translation("orevolution.config.aethersteel_damage_mult")
                    .defineInRange("aethersteel_damage_mult", 1.5D, 1D, Double.MAX_VALUE);

            aethersteelArmorPowers = builder
                    .translation("orevolution.config.aethersteel_armor_powers")
                    .define("aethersteel_armor_powers", true);
            aethersteelResidualDamage = builder
                    .gameRestart()
                    .translation("orevolution.config.aethersteel_residual_damage")
                    .defineInRange("aethersteel_residual_damage", 0.6D, 0D, 1D);
            aethersteelHealthBoostAmplifier = builder
                    .gameRestart()
                    .translation("orevolution.config.aethersteel_health_boost_amplifier")
                    .defineInRange("aethersteel_health_boost_amplifier", 1, 0, Integer.MAX_VALUE);

            builder.pop();

            builder.push("livingstone");

            livingstoneToolPowers = builder
                    .translation("orevolution.config.livingstone_tool_powers")
                    .define("livingstone_tool_powers", true);
            livingstoneToolDuraRegenPerTick = builder
                    .gameRestart()
                    .translation("orevolution.config.livingstone_tool_dura_regen_per_tick")
                    .defineInRange("livingstone_tool_dura_regen_per_tick", 1, 1, Integer.MAX_VALUE);
            livingstoneToolDuraRegenCooldown = builder
                    .gameRestart()
                    .translation("orevolution.config.livingstone_tool_dura_regen_cooldown")
                    .defineInRange("livingstone_tool_dura_regen_cooldown", 100, 0, Integer.MAX_VALUE);
            livingstoneCropIncrease = builder
                    .gameRestart()
                    .translation("orevolution.config.livingstone_crop_increase")
                    .defineInRange("livingstone_crop_increase", 1, 1, Integer.MAX_VALUE);
            livingstoneCropChance = builder
                    .gameRestart()
                    .translation("orevolution.config.livingstone_crop_chance")
                    .defineInRange("livingstone_crop_chance", 0.4D, 0D, 1D);

            livingstoneWeaponPowers = builder
                    .translation("orevolution.config.livingstone_weapon_powers")
                    .define("livingstone_weapon_powers", true);
            livingstoneWeaponDuraRegenPerTick = builder
                    .gameRestart()
                    .translation("orevolution.config.livingstone_weapon_dura_regen_per_tick")
                    .defineInRange("livingstone_weapon_dura_regen_per_tick", 1, 1, Integer.MAX_VALUE);
            livingstoneWeaponDuraRegenCooldown = builder
                    .gameRestart()
                    .translation("orevolution.config.livingstone_weapon_dura_regen_cooldown")
                    .defineInRange("livingstone_weapon_dura_regen_cooldown", 100, 0, Integer.MAX_VALUE);
            livingstonePetrifiedChance = builder
                    .gameRestart()
                    .translation("orevolution.config.livingstone_petrified_chance")
                    .defineInRange("livingstone_petrified_chance", 0.6D, 0D, 1D);
            livingstonePetrifiedTime = builder
                    .gameRestart()
                    .translation("orevolution.config.livingstone_petrified_time")
                    .defineInRange("livingstone_petrified_time", 160, 0, Integer.MAX_VALUE);
            livingstonePetrifiedAmplifier = builder
                    .gameRestart()
                    .translation("orevolution.config.livingstone_petrified_amplifier")
                    .defineInRange("livingstone_petrified_amplifier", 1, 0, Integer.MAX_VALUE);

            livingstoneArmorPowers = builder
                    .translation("orevolution.config.livingstone_armor_powers")
                    .define("livingstone_armor_powers", true);
            livingstoneArmorDuraRegenPerTick = builder
                    .gameRestart()
                    .translation("orevolution.config.livingstone_dura_regen_per_tick")
                    .defineInRange("livingstone_dura_regen_per_tick", 2, 1, Integer.MAX_VALUE);
            livingstoneArmorDuraRegenCooldown = builder
                    .gameRestart()
                    .translation("orevolution.config.livingstone_dura_regen_cooldown")
                    .defineInRange("livingstone_dura_regen_per_tick", 100, 0, Integer.MAX_VALUE);
            livingstoneRegenerationAmplifier = builder
                    .gameRestart()
                    .translation("orevolution.config.livingstone_regeneration_amplifier")
                    .defineInRange("livingstone_regeneration_amplifier", 0, 0, Integer.MAX_VALUE);

            builder.pop();

            builder.push("verdite");

            verditeToolPowers = builder
                    .translation("orevolution.config.verdite_tool_powers")
                    .define("verdite_tool_powers", true);
            verditeToolDuraRegenPerTick = builder
                    .gameRestart()
                    .translation("orevolution.config.verdite_tool_dura_regen_per_tick")
                    .defineInRange("verdite_tool_dura_regen_per_tick", 1, 1, Integer.MAX_VALUE);
            verditeToolDuraRegenCooldown = builder
                    .gameRestart()
                    .translation("orevolution.config.verdite_tool_dura_regen_cooldown")
                    .defineInRange("verdite_tool_dura_regen_cooldown", 60, 0, Integer.MAX_VALUE);
            verditeCropIncrease = builder
                    .gameRestart()
                    .translation("orevolution.config.verdite_crop_increase")
                    .defineInRange("verdite_crop_increase", 2, 1, Integer.MAX_VALUE);
            verditeCropChance = builder
                    .gameRestart()
                    .translation("orevolution.config.verdite_crop_chance")
                    .defineInRange("verdite_crop_chance", 0.6D, 0D, 1D);

            verditeWeaponPowers = builder
                    .translation("orevolution.config.verdite_weapon_powers")
                    .define("verdite_weapon_powers", true);
            verditeWeaponDuraRegenPerTick = builder
                    .gameRestart()
                    .translation("orevolution.config.verdite_weapon_dura_regen_per_tick")
                    .defineInRange("verdite_weapon_dura_regen_per_tick", 1, 1, Integer.MAX_VALUE);
            verditeWeaponDuraRegenCooldown = builder
                    .gameRestart()
                    .translation("orevolution.config.verdite_weapon_dura_regen_cooldown")
                    .defineInRange("verdite_weapon_dura_regen_cooldown", 100, 0, Integer.MAX_VALUE);
            verditeSaturationChance = builder
                    .gameRestart()
                    .translation("orevolution.config.verdite_saturation_chance")
                    .defineInRange("verdite_saturation_chance", 0.2D, 0D, 1D);
            verditeSaturationTime = builder
                    .gameRestart()
                    .translation("orevolution.config.verdite_saturation_time")
                    .defineInRange("verdite_saturation_time", 2, 0, Integer.MAX_VALUE);
            verditeSaturationAmplifier = builder
                    .gameRestart()
                    .translation("orevolution.config.verdite_saturation_amplifier")
                    .defineInRange("verdite_saturation_amplifier", 0, 0, Integer.MAX_VALUE);

            verditeArmorPowers = builder
                    .translation("orevolution.config.verdite_armor_powers")
                    .define("verdite_armor_powers", true);
            verditeDuraRegenPerTick = builder
                    .gameRestart()
                    .translation("orevolution.config.verdite_dura_regen_per_tick")
                    .defineInRange("verdite_dura_regen_per_tick", 2, 1, Integer.MAX_VALUE);
            verditeDuraRegenCooldown = builder
                    .gameRestart()
                    .translation("orevolution.config.verdite_dura_regen_cooldown")
                    .defineInRange("verdite_dura_regen_cooldown", 100, 0, Integer.MAX_VALUE);
            verditeRegenerationTime = builder
                    .gameRestart()
                    .translation("orevolution.config.verdite_regeneration_time")
                    .defineInRange("verdite_regeneration_time", 60, 1, Integer.MAX_VALUE);
            verditeRegenerationAmplifier = builder
                    .gameRestart()
                    .translation("orevolution.config.verdite_regeneration_amplifier")
                    .defineInRange("verdite_regeneration_amplifier", 0, 0, Integer.MAX_VALUE);

            builder.pop();
        }

    }

    public static class ToolStats {
        public final ConfigValue<Integer> woodMaxUses;
        public final ConfigValue<Integer> stoneMaxUses;
        public final ConfigValue<Integer> tinMaxUses;
        public final ConfigValue<Integer> goldMaxUses;
        public final ConfigValue<Integer> tungstenMaxUses;
        public final ConfigValue<Integer> cassiteriteMaxUses;
        public final ConfigValue<Integer> ironMaxUses;
        public final ConfigValue<Integer> platinumMaxUses;
        public final ConfigValue<Integer> diamondMaxUses;
        public final ConfigValue<Integer> moonstoneMaxUses;
        public final ConfigValue<Integer> netheriteMaxUses;
        public final ConfigValue<Integer> aethersteelMaxUses;
        public final ConfigValue<Integer> livingstoneMaxUses;
        public final ConfigValue<Integer> verditeMaxUses;
        public final ConfigValue<Integer> steelMaxUses;

        public final ConfigValue<List<? extends String>> woodHarvestRestrictions;
        public final ConfigValue<List<? extends String>> stoneHarvestRestrictions;
        public final ConfigValue<List<? extends String>> goldHarvestRestrictions;
        public final ConfigValue<List<? extends String>> tungstenHarvestRestrictions;
        public final ConfigValue<List<? extends String>> tinHarvestRestrictions;
        public final ConfigValue<List<? extends String>> cassiteriteHarvestRestrictions;
        public final ConfigValue<List<? extends String>> ironHarvestRestrictions;
        public final ConfigValue<List<? extends String>> steelHarvestRestrictions;
        public final ConfigValue<List<? extends String>> platinumHarvestRestrictions;
        public final ConfigValue<List<? extends String>> moonstoneHarvestRestrictions;
        public final ConfigValue<List<? extends String>> diamondHarvestRestrictions;
        public final ConfigValue<List<? extends String>> netheriteHarvestRestrictions;
        public final ConfigValue<List<? extends String>> aethersteelHarvestRestrictions;
        public final ConfigValue<List<? extends String>> livingstoneHarvestRestrictions;
        public final ConfigValue<List<? extends String>> verditeHarvestRestrictions;

        public final ConfigValue<List<? extends String>> woodTieredItems;
        public final ConfigValue<List<? extends String>> stoneTieredItems;
        public final ConfigValue<List<? extends String>> goldTieredItems;
        public final ConfigValue<List<? extends String>> tungstenTieredItems;
        public final ConfigValue<List<? extends String>> tinTieredItems;
        public final ConfigValue<List<? extends String>> cassiteriteTieredItems;
        public final ConfigValue<List<? extends String>> ironTieredItems;
        public final ConfigValue<List<? extends String>> steelTieredItems;
        public final ConfigValue<List<? extends String>> platinumTieredItems;
        public final ConfigValue<List<? extends String>> moonstoneTieredItems;
        public final ConfigValue<List<? extends String>> diamondTieredItems;
        public final ConfigValue<List<? extends String>> netheriteTieredItems;
        public final ConfigValue<List<? extends String>> aethersteelTieredItems;
        public final ConfigValue<List<? extends String>> livingstoneTieredItems;
        public final ConfigValue<List<? extends String>> verditeTieredItems;

        private ToolStats(ModConfigSpec.Builder builder) {
            builder.push("capability");

            woodHarvestRestrictions = builder
                    .translation("orevolution.config.wooden_harvest_restrictions")
                    .comment("Blocks that Wooden tools cannot harvest. Anything outside of this will be harvested just fine. Supports block IDs and tags prefixed with #")
                    .defineList("wooden_harvest_restrictions", List.of(
                            "#orevolution:wood_harvest_restriction_defaults",
                            "#orevolution:stone_harvest_restriction_defaults",
                            "#orevolution:tin_harvest_restriction_defaults",
                            "#orevolution:iron_harvest_restriction_defaults",
                            "#orevolution:gold_harvest_restriction_defaults",
                            "#orevolution:platinum_harvest_restriction_defaults",
                            "#orevolution:diamond_harvest_restriction_defaults",
                            "#orevolution:netherite_harvest_restriction_defaults",
                            "#orevolution:aethersteel_harvest_restriction_defaults"
                    ), () -> "", o -> o instanceof String);

            stoneHarvestRestrictions = builder
                    .translation("orevolution.config.stone_harvest_restrictions")
                    .comment("Blocks that Stone tools cannot harvest. anything outside of this will be harvested just fine. Supports block IDs and tags prefixed with #")
                    .defineList("stone_harvest_restrictions", List.of(
                            "#orevolution:stone_harvest_restriction_defaults",
                            "#orevolution:tin_harvest_restriction_defaults",
                            "#orevolution:iron_harvest_restriction_defaults",
                            "#orevolution:gold_harvest_restriction_defaults",
                            "#orevolution:platinum_harvest_restriction_defaults",
                            "#orevolution:diamond_harvest_restriction_defaults",
                            "#orevolution:netherite_harvest_restriction_defaults",
                            "#orevolution:aethersteel_harvest_restriction_defaults"
                    ), () -> "", o -> o instanceof String);

            tinHarvestRestrictions = builder
                    .translation("orevolution.config.tin_harvest_restrictions")
                    .comment("Blocks that Tin tools cannot harvest. anything outside of this will be harvested just fine. Supports block IDs and tags prefixed with #")
                    .defineList("tin_harvest_restrictions", List.of(
                            "#orevolution:tin_harvest_restriction_defaults",
                            "#orevolution:iron_harvest_restriction_defaults",
                            "#orevolution:gold_harvest_restriction_defaults",
                            "#orevolution:platinum_harvest_restriction_defaults",
                            "#orevolution:diamond_harvest_restriction_defaults",
                            "#orevolution:netherite_harvest_restriction_defaults",
                            "#orevolution:aethersteel_harvest_restriction_defaults"
                    ), () -> "", o -> o instanceof String);

            cassiteriteHarvestRestrictions = builder
                    .translation("orevolution.config.cassiterite_harvest_restrictions")
                    .comment("Blocks that cassiterite tools cannot harvest. anything outside of this will be harvested just fine. Supports block IDs and tags prefixed with #")
                    .defineList("cassiterite_harvest_restrictions", List.of(
                            "#orevolution:cassiterite_harvest_restriction_defaults",
                            "#orevolution:gold_harvest_restriction_defaults",
                            "#orevolution:platinum_harvest_restriction_defaults",
                            "#orevolution:diamond_harvest_restriction_defaults",
                            "#orevolution:netherite_harvest_restriction_defaults",
                            "#orevolution:aethersteel_harvest_restriction_defaults"
                    ), () -> "", o -> o instanceof String);

            ironHarvestRestrictions = builder
                    .translation("orevolution.config.iron_harvest_restrictions")
                    .comment("Blocks that Iron tools cannot harvest. anything outside of this will be harvested just fine. Supports block IDs and tags prefixed with #")
                    .defineList("iron_harvest_restrictions", List.of(
                            "#orevolution:iron_harvest_restriction_defaults",
                            "#orevolution:gold_harvest_restriction_defaults",
                            "#orevolution:platinum_harvest_restriction_defaults",
                            "#orevolution:diamond_harvest_restriction_defaults",
                            "#orevolution:netherite_harvest_restriction_defaults",
                            "#orevolution:aethersteel_harvest_restriction_defaults"
                    ), () -> "", o -> o instanceof String);

            goldHarvestRestrictions = builder
                    .translation("orevolution.config.gold_harvest_restrictions")
                    .comment("Blocks that Gold tools cannot harvest. anything outside of this will be harvested just fine. Supports block IDs and tags prefixed with #")
                    .defineList("gold_harvest_restrictions", List.of(
                            "#orevolution:gold_harvest_restriction_defaults",
                            "#orevolution:platinum_harvest_restriction_defaults",
                            "#orevolution:diamond_harvest_restriction_defaults",
                            "#orevolution:netherite_harvest_restriction_defaults",
                            "#orevolution:aethersteel_harvest_restriction_defaults"
                    ), () -> "", o -> o instanceof String);

            tungstenHarvestRestrictions = builder
                    .translation("orevolution.config.tungsten_harvest_restrictions")
                    .comment("Blocks that Tungsten tools cannot harvest. anything outside of this will be harvested just fine. Supports block IDs and tags prefixed with #")
                    .defineList("tungsten_harvest_restrictions", List.of(
                            "#orevolution:platinum_harvest_restriction_defaults",
                            "#orevolution:diamond_harvest_restriction_defaults",
                            "#orevolution:netherite_harvest_restriction_defaults",
                            "#orevolution:aethersteel_harvest_restriction_defaults"
                    ), () -> "", o -> o instanceof String);

            steelHarvestRestrictions = builder
                    .translation("orevolution.config.steel_harvest_restrictions")
                    .comment("Blocks that Steel tools cannot harvest. anything outside of this will be harvested just fine. Supports block IDs and tags prefixed with #")
                    .defineList("steel_harvest_restrictions", List.of(
                            "#orevolution:platinum_harvest_restriction_defaults",
                            "#orevolution:diamond_harvest_restriction_defaults",
                            "#orevolution:netherite_harvest_restriction_defaults",
                            "#orevolution:aethersteel_harvest_restriction_defaults"
                    ), () -> "", o -> o instanceof String);

            platinumHarvestRestrictions = builder
                    .translation("orevolution.config.platinum_harvest_restrictions")
                    .comment("Blocks that Platinum tools cannot harvest. anything outside of this will be harvested just fine. Supports block IDs and tags prefixed with #")
                    .defineList("platinum_harvest_restrictions", List.of(
                            "#orevolution:platinum_harvest_restriction_defaults",
                            "#orevolution:diamond_harvest_restriction_defaults",
                            "#orevolution:netherite_harvest_restriction_defaults",
                            "#orevolution:aethersteel_harvest_restriction_defaults"
                    ), () -> "", o -> o instanceof String);

            moonstoneHarvestRestrictions = builder
                    .translation("orevolution.config.moonstone_harvest_restrictions")
                    .comment("Blocks that Moonstone tools cannot harvest. anything outside of this will be harvested just fine. Supports block IDs and tags prefixed with #")
                    .defineList("moonstone_harvest_restrictions", List.of(
                            "#orevolution:diamond_harvest_restriction_defaults",
                            "#orevolution:netherite_harvest_restriction_defaults",
                            "#orevolution:aethersteel_harvest_restriction_defaults"
                    ), () -> "", o -> o instanceof String);

            diamondHarvestRestrictions = builder
                    .translation("orevolution.config.diamond_harvest_restrictions")
                    .comment("Blocks that Diamond tools cannot harvest. anything outside of this will be harvested just fine. Supports block IDs and tags prefixed with #")
                    .defineList("diamond_harvest_restrictions", List.of(
                            "#orevolution:diamond_harvest_restriction_defaults",
                            "#orevolution:netherite_harvest_restriction_defaults",
                            "#orevolution:aethersteel_harvest_restriction_defaults"
                    ), () -> "", o -> o instanceof String);

            netheriteHarvestRestrictions = builder
                    .translation("orevolution.config.netherite_harvest_restrictions")
                    .comment("Blocks that Netherite tools cannot harvest. anything outside of this will be harvested just fine. Supports block IDs and tags prefixed with #")
                    .defineList("netherite_harvest_restrictions", List.of(
                            "#orevolution:netherite_harvest_restriction_defaults",
                            "#orevolution:aethersteel_harvest_restriction_defaults"
                    ), () -> "", o -> o instanceof String);

            aethersteelHarvestRestrictions = builder
                    .translation("orevolution.config.aethersteel_harvest_restrictions")
                    .comment("Blocks that Aethersteel tools cannot harvest. anything outside of this will be harvested just fine. Supports block IDs and tags prefixed with #")
                    .defineList("aethersteel_harvest_restrictions", List.of(
                            "#orevolution:aethersteel_harvest_restriction_defaults"
                    ), () -> "", o -> o instanceof String);

            livingstoneHarvestRestrictions = builder
                    .translation("orevolution.config.livingstone_harvest_restrictions")
                    .comment("Blocks that Livingstone tools cannot harvest. anything outside of this will be harvested just fine. Supports block IDs and tags prefixed with #")
                    .defineList("livingstone_harvest_restrictions", List.of(
                            "#orevolution:tin_harvest_restriction_defaults",
                            "#orevolution:iron_harvest_restriction_defaults",
                            "#orevolution:gold_harvest_restriction_defaults",
                            "#orevolution:platinum_harvest_restriction_defaults",
                            "#orevolution:diamond_harvest_restriction_defaults",
                            "#orevolution:netherite_harvest_restriction_defaults",
                            "#orevolution:aethersteel_harvest_restriction_defaults"
                    ), () -> "", o -> o instanceof String);

            verditeHarvestRestrictions = builder
                    .translation("orevolution.config.verdite_harvest_restrictions")
                    .comment("Blocks that Verdite tools cannot harvest. anything outside of this will be harvested just fine. Supports block IDs and tags prefixed with #")
                    .defineList("verdite_harvest_restrictions", List.of(
                            "#orevolution:iron_harvest_restriction_defaults",
                            "#orevolution:gold_harvest_restriction_defaults",
                            "#orevolution:platinum_harvest_restriction_defaults",
                            "#orevolution:diamond_harvest_restriction_defaults",
                            "#orevolution:netherite_harvest_restriction_defaults",
                            "#orevolution:aethersteel_harvest_restriction_defaults"
                    ), () -> "", o -> o instanceof String);

            builder.pop();

            builder.push("durability");

            woodMaxUses = builder
                    .translation("orevolution.config.wood_max_uses")
                    .comment("Modifies the max uses of wood tools and weapons. vanilla is 59")
                    .defineInRange("wood_max_uses", 64, 0, Integer.MAX_VALUE);
            goldMaxUses = builder
                    .translation("orevolution.config.gold_max_uses")
                    .comment("Modifies the max uses of gold tools and weapons. vanilla is 32")
                    .defineInRange("gold_max_uses", 128, 0, Integer.MAX_VALUE);
            stoneMaxUses = builder
                    .translation("orevolution.config.stone_max_uses")
                    .comment("Modifies the max uses of stone tools and weapons. vanilla is 131")
                    .defineInRange("stone_max_uses", 192, 0, Integer.MAX_VALUE);
            tungstenMaxUses = builder
                    .translation("orevolution.config.tungsten_max_uses")
                    .comment("Modifies the max uses of tungsten tools and weapons. vanilla is 32")
                    .defineInRange("tungsten_max_uses", 256, 0, Integer.MAX_VALUE);
            tinMaxUses = builder
                    .translation("orevolution.config.tin_max_uses")
                    .comment("Modifies the max uses of tin tools and weapons. vanilla is 256")
                    .defineInRange("tin_max_uses", 256, 0, Integer.MAX_VALUE);
            ironMaxUses = builder
                    .translation("orevolution.config.iron_max_uses")
                    .comment("Modifies the max uses of iron tools and weapons. vanilla is 250")
                    .defineInRange("iron_max_uses", 448, 0, Integer.MAX_VALUE);
            cassiteriteMaxUses = builder
                    .translation("orevolution.config.tin_max_uses")
                    .comment("Modifies the max uses of tin tools and weapons. vanilla is 512")
                    .defineInRange("tin_max_uses", 512, 0, Integer.MAX_VALUE);
            platinumMaxUses = builder
                    .translation("orevolution.config.platinum_max_uses")
                    .comment("Modifies the max uses of platinum tools and weapons. vanilla is 768")
                    .defineInRange("platinum_max_uses", 768, 0, Integer.MAX_VALUE);
            steelMaxUses = builder
                    .translation("orevolution.config.steel_max_uses")
                    .comment("Modifies the max uses of steel tools. vanilla is 1024")
                    .defineInRange("steel_max_uses", 1024, 0, Integer.MAX_VALUE);
            moonstoneMaxUses = builder
                    .translation("orevolution.config.moonstone_max_uses")
                    .comment("Modifies the max uses of moonstone tools and weapons. vanilla is 1024")
                    .defineInRange("moonstone_max_uses", 1536, 0, Integer.MAX_VALUE);
            diamondMaxUses = builder
                    .translation("orevolution.config.diamond_max_uses")
                    .comment("Modifies the max uses of diamond tools and weapons. vanilla is 1561")
                    .defineInRange("diamond_max_uses", 1600, 0, Integer.MAX_VALUE);
            netheriteMaxUses = builder
                    .translation("orevolution.config.netherite_max_uses")
                    .comment("Modifies the max uses of netherite tools and weapons. vanilla is 2031")
                    .defineInRange("netherite_max_uses", 2368, 0, Integer.MAX_VALUE);
            aethersteelMaxUses = builder
                    .translation("orevolution.config.aethersteel_max_uses")
                    .comment("Modifies the max uses of aethersteel tools and weapons. vanilla is 3520")
                    .defineInRange("aethersteel_max_uses", 3200, 0, Integer.MAX_VALUE);
            livingstoneMaxUses = builder
                    .translation("orevolution.config.livingstone_max_uses")
                    .comment("Modifies the max uses of livingstone tools and weapons. vanilla is 192")
                    .defineInRange("livingstone_max_uses", 192, 0, Integer.MAX_VALUE);
            verditeMaxUses = builder
                    .translation("orevolution.config.verdite_max_uses")
                    .comment("Modifies the max uses of verdite tools and weapons. vanilla is 448")
                    .defineInRange("verdite_max_uses", 448, 0, Integer.MAX_VALUE);

            builder.pop();

            builder.push("tier_additions");

            woodTieredItems = builder
                    .translation("orevolution.config.wooden_tiered_items")
                    .comment("Items that should follow the Wooden tool tiered tools for mining progression. Supports item IDs and tags prefixed with #")
                    .defineList("wooden_tiered_items", List.of(), () -> "", o -> o instanceof String);

            stoneTieredItems = builder
                    .translation("orevolution.config.stone_tiered_items")
                    .comment("Items that should follow the Stone tiered tools for mining progression. Supports item IDs and tags prefixed with #")
                    .defineList("stone_tiered_items", List.of(), () -> "", o -> o instanceof String);

            tinTieredItems = builder
                    .translation("orevolution.config.tin_tiered_items")
                    .comment("Items that should follow the Tin tiered tools for mining progression. Supports item IDs and tags prefixed with #")
                    .defineList("tin_tiered_items", List.of(), () -> "", o -> o instanceof String);

            cassiteriteTieredItems = builder
                    .translation("orevolution.config.cassiterite_tiered_items")
                    .comment("Items that should follow the cassiterite tiered tools for mining progression. Supports item IDs and tags prefixed with #")
                    .defineList("cassiterite_tiered_items", List.of(), () -> "", o -> o instanceof String);

            ironTieredItems = builder
                    .translation("orevolution.config.iron_tiered_items")
                    .comment("Items that should follow the Iron tiered tools for mining progression. Supports item IDs and tags prefixed with #")
                    .defineList("iron_tiered_items", List.of(), () -> "", o -> o instanceof String);

            goldTieredItems = builder
                    .translation("orevolution.config.gold_tiered_items")
                    .comment("Items that should follow the Gold tiered tools for mining progression. Supports item IDs and tags prefixed with #")
                    .defineList("gold_tiered_items", List.of(), () -> "", o -> o instanceof String);

            tungstenTieredItems = builder
                    .translation("orevolution.config.tungsten_tiered_items")
                    .comment("Items that should follow the Tungsten tiered tools for mining progression. Supports item IDs and tags prefixed with #")
                    .defineList("tungsten_tiered_items", List.of(), () -> "", o -> o instanceof String);

            steelTieredItems = builder
                    .translation("orevolution.config.steel_tiered_items")
                    .comment("Items that should follow the Steel tiered tools for mining progression. Supports item IDs and tags prefixed with #")
                    .defineList("steel_tiered_items", List.of(), () -> "", o -> o instanceof String);

            platinumTieredItems = builder
                    .translation("orevolution.config.platinum_tiered_items")
                    .comment("Items that should follow the Platinum tiered tools for mining progression. Supports item IDs and tags prefixed with #")
                    .defineList("platinum_tiered_items", List.of(), () -> "", o -> o instanceof String);

            moonstoneTieredItems = builder
                    .translation("orevolution.config.moonstone_tiered_items")
                    .comment("Items that should follow the Moonstone tiered tools for mining progression. Supports item IDs and tags prefixed with #")
                    .defineList("moonstone_tiered_items", List.of(), () -> "", o -> o instanceof String);

            diamondTieredItems = builder
                    .translation("orevolution.config.diamond_tiered_items")
                    .comment("Items that should follow the Diamond tiered tools for mining progression. Supports item IDs and tags prefixed with #")
                    .defineList("diamond_tiered_items", List.of(), () -> "", o -> o instanceof String);

            netheriteTieredItems = builder
                    .translation("orevolution.config.netherite_tiered_items")
                    .comment("Items that should follow the Netherite tiered tools for mining progression. Supports item IDs and tags prefixed with #")
                    .defineList("netherite_tiered_items", List.of(), () -> "", o -> o instanceof String);

            aethersteelTieredItems = builder
                    .translation("orevolution.config.aethersteel_tiered_items")
                    .comment("Items that should follow the Aethersteel tiered tools for mining progression. Supports item IDs and tags prefixed with #")
                    .defineList("aethersteel_tiered_items", List.of(), () -> "", o -> o instanceof String);

            livingstoneTieredItems = builder
                    .translation("orevolution.config.livingstone_tiered_items")
                    .comment("Items that should follow the Livingstone tiered tools for mining progression. Supports item IDs and tags prefixed with #")
                    .defineList("livingstone_tiered_items", List.of(), () -> "", o -> o instanceof String);

            verditeTieredItems = builder
                    .translation("orevolution.config.verdite_tiered_items")
                    .comment("Items that should follow the Verdite tiered tools for mining progression. Supports item IDs and tags prefixed with #")
                    .defineList("verdite_tiered_items", List.of(), () -> "", o -> o instanceof String);

            builder.pop();

        }
    }

    public static class ModCompat {
        public final ConfigValue<Boolean> kineticDamage;
        public final ConfigValue<Boolean> speedPerArmorPiece;

        public final ConfigValue<Boolean> invincibilityPerArmorPiece;

        public final ConfigValue<Boolean> electrumToolPowers;
        public final ConfigValue<Integer> electrumMinBlocksHaste;
        public final ConfigValue<Integer> electrumHasteTime;
        public final ConfigValue<Integer> electrumHasteMaxAmplifier;

        public final ConfigValue<Boolean> electrumWeaponPowers;
        public final ConfigValue<Integer> electrumMaxHitsDamage;

        public final ConfigValue<Boolean> electrumArmorPowers;
        public final ConfigValue<Double> electrumMovementSpeedAttribute;
        public final ConfigValue<Double> electrumStepHeightAttribute;

        public final ConfigValue<Integer> electrumMaxUses;
        public final ConfigValue<List<? extends String>> electrumTieredItems;
        public final ConfigValue<List<? extends String>> electrumHarvestRestrictions;

        public final ConfigValue<Boolean> silverToolPowers;
        public final ConfigValue<Double> silverMinSpeedMult;
        public final ConfigValue<Double> silverMaxSpeedMult;
        public final ConfigValue<Double> silverExponentSpeedMult;

        public final ConfigValue<Boolean> silverWeaponPowers;
        public final ConfigValue<Double> silverMinDamageMult;
        public final ConfigValue<Double> silverMaxDamageMult;
        public final ConfigValue<Double> silverExponentDamageMult;

        public final ConfigValue<Boolean> silverArmorPowers;
        public final ConfigValue<Double> silverInvincibilityFramesAttribute;
        public final ConfigValue<Double> silverEntityInteractionRangeAttribute;
        public final ConfigValue<Double> silverSweepingDamageRatioPercentAttribute;

        public final ConfigValue<Integer> silverMaxUses;
        public final ConfigValue<List<? extends String>> silverTieredItems;
        public final ConfigValue<List<? extends String>> silverHarvestRestrictions;

        public final ConfigValue<Boolean> copperToolPowers;
        public final ConfigValue<Integer> copperMinBlocksHaste;
        public final ConfigValue<Integer> copperTimeHaste;
        public final ConfigValue<Integer> copperMaxHasteAmplifier;

        public final ConfigValue<Boolean> copperWeaponPowers;
        public final ConfigValue<Integer> copperMaxHitsDamage;

        public final ConfigValue<Boolean> copperArmorPowers;
        public final ConfigValue<Double> copperExtendPickupRangeAttribute;
        public final ConfigValue<Double> copperBlockInteractionRangeAttribute;

        public final ConfigValue<Integer> copperMaxUses;
        public final ConfigValue<List<? extends String>> copperTieredItems;
        public final ConfigValue<List<? extends String>> copperHarvestRestrictions;

        private ModCompat(ModConfigSpec.Builder builder) {
            builder.push("oreganized");

            kineticDamage = builder
                    .translation("orevolution.config.electrum_kinetic_damage")
                    .define("electrum_kinetic_damage", false);
            speedPerArmorPiece = builder
                    .translation("orevolution.config.electrum_speed_per_armor_piece")
                    .define("electrum_speed_per_armor_piece", false);
            invincibilityPerArmorPiece = builder
                    .translation("orevolution.config.silver_invincibility_per_armor_piece")
                    .define("silver_invincibility_per_armor_piece", false);

            builder.pop();

            builder.push("electrum");

            electrumToolPowers = builder
                    .translation("orevolution.config.electrum_tool_powers")
                    .define("electrum_tool_powers", true);
            electrumMinBlocksHaste = builder
                    .gameRestart()
                    .translation("orevolution.config.electrum_min_blocks_haste")
                    .defineInRange("electrum_min_blocks_haste", 6, 0, Integer.MAX_VALUE);
            electrumHasteTime = builder
                    .gameRestart()
                    .translation("orevolution.config.electrum_time_haste")
                    .defineInRange("electrum_time_haste", 60, 1, Integer.MAX_VALUE);
            electrumHasteMaxAmplifier = builder
                    .gameRestart()
                    .translation("orevolution.config.electrum_max_haste_amplifier")
                    .defineInRange("electrum_max_haste_amplifier", 2, 0, Integer.MAX_VALUE);

            electrumWeaponPowers = builder
                    .translation("orevolution.config.electrum_weapon_powers")
                    .define("electrum_weapon_powers", true);
            electrumMaxHitsDamage = builder
                    .gameRestart()
                    .translation("orevolution.config.electrum_max_hits_damage")
                    .defineInRange("electrum_max_hits_damage", 4, 0, Integer.MAX_VALUE);

            electrumArmorPowers = builder
                    .translation("orevolution.config.electrum_armor_powers")
                    .define("electrum_armor_powers", true);
            electrumMovementSpeedAttribute = builder
                    .gameRestart()
                    .translation("orevolution.config.electrum_movement_speed_attribute")
                    .defineInRange("electrum_movement_speed_attribute", 0.2D, 0D, 1D);
            electrumStepHeightAttribute = builder
                    .gameRestart()
                    .translation("orevolution.config.electrum_step_height_attribute")
                    .defineInRange("electrum_step_height_attribute", 1.5D, 0D, 10D);


            electrumHarvestRestrictions = builder
                    .translation("orevolution.config.electrum_harvest_restrictions")
                    .comment("Blocks that Electrum tools cannot harvest. anything outside of this will be harvested just fine. Supports block IDs and tags prefixed with #")
                    .defineList("electrum_harvest_restrictions", List.of(
                            "#orevolution:diamond_harvest_restriction_defaults",
                            "#orevolution:netherite_harvest_restriction_defaults",
                            "#orevolution:aethersteel_harvest_restriction_defaults"
                    ), () -> "", o -> o instanceof String);

            electrumMaxUses = builder
                    .translation("orevolution.config.electrum_max_uses")
                    .comment("Modifies the max uses of electrum tools and weapons. vanilla is 1561")
                    .defineInRange("electrum_max_uses", 1920, 0, Integer.MAX_VALUE);

            electrumTieredItems = builder
                    .translation("orevolution.config.electrum_tiered_items")
                    .comment("Items that should follow the Electrum tiered tools for mining progression. Supports item IDs and tags prefixed with #")
                    .defineList("electrum_tiered_items", List.of(), () -> "", o -> o instanceof String);

            builder.pop();

            builder.push("silver");

            silverToolPowers = builder
                    .translation("orevolution.config.silver_tool_powers")
                    .define("silver_tool_powers", true);
            silverMinSpeedMult = builder
                    .gameRestart()
                    .translation("orevolution.config.silver_min_speed_mult")
                    .defineInRange("silver_min_speed_mult", 0.75D, 0D, Double.MAX_VALUE);
            silverMaxSpeedMult = builder
                    .gameRestart()
                    .translation("orevolution.config.silver_max_speed_mult")
                    .defineInRange("silver_max_speed_mult", 2.5D, 0D, Double.MAX_VALUE);
            silverExponentSpeedMult = builder
                    .gameRestart()
                    .translation("orevolution.config.silver_exponent_speed_mult")
                    .defineInRange("silver_exponent_durability", 1.5D, 0D, Double.MAX_VALUE);

            silverWeaponPowers = builder
                    .translation("orevolution.config.silver_weapon_powers")
                    .define("silver_weapon_powers", true);
            silverMinDamageMult = builder
                    .gameRestart()
                    .translation("orevolution.config.silver_min_damage_mult")
                    .defineInRange("silver_min_damage_mult", 0.75D, 0D, Double.MAX_VALUE);
            silverMaxDamageMult = builder
                    .gameRestart()
                    .translation("orevolution.config.silver_max_damage_mult")
                    .defineInRange("silver_max_damage_mult", 2.5D, 0D, Double.MAX_VALUE);
            silverExponentDamageMult = builder
                    .gameRestart()
                    .translation("orevolution.config.silver_exponent_damage_mult")
                    .defineInRange("silver_exponent_damage_mult", 1.5D, 0D, Double.MAX_VALUE);

            silverArmorPowers = builder
                    .translation("orevolution.config.silver_armor_powers")
                    .define("silver_armor_powers", true);

            silverInvincibilityFramesAttribute = builder
                    .gameRestart()
                    .translation("orevolution.config.silver_invincibility_frames_attribute")
                    .comment("(Oreganized Mod)")
                    .defineInRange("silver_invincibility_frames", 0.8D, 0D, 60D);
            silverEntityInteractionRangeAttribute = builder
                    .gameRestart()
                    .translation("orevolution.config.silver_entity_interaction_range_attribute")
                    .defineInRange("silver_entity_interaction_range", 0.5D, 0D, 64D);
            silverSweepingDamageRatioPercentAttribute = builder
                    .gameRestart()
                    .translation("orevolution.config.silver_sweeping_damage_ratio_percent_attribute")
                    .defineInRange("silver_sweeping_damage_ratio_percent", 0.25D, 0D, 1D);

            silverHarvestRestrictions = builder
                    .translation("orevolution.config.silver_harvest_restrictions")
                    .comment("Blocks that Silver tools cannot harvest. anything outside of this will be harvested just fine. Supports block IDs and tags prefixed with #")
                    .defineList("silver_harvest_restrictions", List.of(
                            "#orevolution:platinum_harvest_restriction_defaults",
                            "#orevolution:diamond_harvest_restriction_defaults",
                            "#orevolution:netherite_harvest_restriction_defaults",
                            "#orevolution:aethersteel_harvest_restriction_defaults"
                    ), () -> "", o -> o instanceof String);

            silverMaxUses = builder
                    .translation("orevolution.config.silver_max_uses")
                    .comment("Modifies the max uses of silver tools and weapons. vanilla is 191")
                    .defineInRange("silver_max_uses", 384, 0, Integer.MAX_VALUE);

            silverTieredItems = builder
                    .translation("orevolution.config.silver_tiered_items")
                    .comment("Items that should follow the Silver tiered tools for mining progression. Supports item IDs and tags prefixed with #")
                    .defineList("silver_tiered_items", List.of(), () -> "", o -> o instanceof String);

            builder.pop();

            builder.push("copper");

            copperToolPowers = builder
                    .translation("orevolution.config.copper_tool_powers")
                    .define("copper_tool_powers", true);
            copperMinBlocksHaste = builder
                    .gameRestart()
                    .translation("orevolution.config.copper_min_blocks_haste")
                    .defineInRange("copper_min_blocks_haste", 6, 0, Integer.MAX_VALUE);
            copperTimeHaste = builder
                    .gameRestart()
                    .translation("orevolution.config.copper_time_haste")
                    .defineInRange("copper_time_haste", 60, 1, Integer.MAX_VALUE);
            copperMaxHasteAmplifier = builder
                    .gameRestart()
                    .translation("orevolution.config.copper_max_haste_amplifier")
                    .defineInRange("copper_max_haste_amplifier", 2, 0, Integer.MAX_VALUE);

            copperWeaponPowers = builder
                    .translation("orevolution.config.copper_weapon_powers")
                    .define("copper_weapon_powers", true);
            copperMaxHitsDamage = builder
                    .gameRestart()
                    .translation("orevolution.config.copper_max_hits_damage")
                    .defineInRange("copper_max_hits_damage", 4, 0, Integer.MAX_VALUE);

            copperArmorPowers = builder
                    .translation("orevolution.config.copper_armor_powers")
                    .define("copper_armor_powers", true);
            copperExtendPickupRangeAttribute = builder
                    .gameRestart()
                    .translation("orevolution.config.copper_extended_pickup_range_attribute")
                    .defineInRange("copper_extended_pickup_range", 6D, 0D, 255D);
            copperBlockInteractionRangeAttribute = builder
                    .gameRestart()
                    .translation("orevolution.config.copper_block_interaction_range_attribute")
                    .defineInRange("copper_block_interaction_range", 2D, 0D, 64D);

            copperHarvestRestrictions = builder
                    .translation("orevolution.config.copper_harvest_restrictions")
                    .comment("Blocks that Copper tools cannot harvest. anything outside of this will be harvested just fine. Supports block IDs and tags prefixed with #")
                    .defineList("tin_harvest_restrictions", List.of(
                            "#orevolution:tin_harvest_restriction_defaults",
                            "#orevolution:iron_harvest_restriction_defaults",
                            "#orevolution:gold_harvest_restriction_defaults",
                            "#orevolution:platinum_harvest_restriction_defaults",
                            "#orevolution:diamond_harvest_restriction_defaults",
                            "#orevolution:netherite_harvest_restriction_defaults",
                            "#orevolution:aethersteel_harvest_restriction_defaults"
                    ), () -> "", o -> o instanceof String);

            copperMaxUses = builder
                    .translation("orevolution.config.copper_max_uses")
                    .comment("Modifies the max uses of copper tools and weapons. vanilla is 190, modded is 320")
                    .defineInRange("copper_max_uses", 320, 0, Integer.MAX_VALUE);

            copperTieredItems = builder
                    .translation("orevolution.config.copper_tiered_items")
                    .comment("Items that should follow the Copper tiered tools for mining progression. Supports item IDs and tags prefixed with #")
                    .defineList("copper_tiered_items", List.of(), () -> "", o -> o instanceof String);

            builder.pop();
        }
    }

    public static class Common {
        public final ConfigValue<Boolean> toolsPowers;
        public final ConfigValue<Boolean> weaponsPowers;
        public final ConfigValue<Boolean> armorsPowers;

        public final ConfigValue<Boolean> generateLivingstoneOre;
        public final ConfigValue<Boolean> generateVerditeOre;

        public final ConfigValue<Boolean> prioritizeTerrablender;
        public final ConfigValue<Boolean> enableModdedBiomes;

        public final ConfigValue<Boolean> generateForgottenCaves;
        public final ConfigValue<Boolean> generateGeodeGardens;

        @ConfigKey("generate_tin")
        public final ConfigValue<Boolean> generateTinOre;
        @ConfigKey("generate_pyrite")
        public final ConfigValue<Boolean> generatePyriteOre;
        @ConfigKey("generate_celestite")
        public final ConfigValue<Boolean> generateCelestite;
        @ConfigKey("generate_xp")
        public final ConfigValue<Boolean> generateExperienceOre;
        @ConfigKey("generate_tungsten")
        public final ConfigValue<Boolean> generateTungstenOre;
        @ConfigKey("generate_platinum")
        public final ConfigValue<Boolean> generatePlatOre;
        public final ConfigValue<Boolean> generateMoonstonePillar;
        public final ConfigValue<Boolean> generateAethersteelOre;
        public final ConfigValue<Boolean> generateAetherrockMeteor;

        @ConfigKey("allow_bronze")
        public final ConfigValue<Boolean> bronzeRecipes;
        @ConfigKey("allow_steel")
        public final ConfigValue<Boolean> steelRecipes;

        public final ConfigValue<Boolean> safeOreBreaking;

        public final ConfigValue<Boolean> modProgression;

        public final ConfigValue<Boolean> mobEquipment;
        public final ConfigValue<Boolean> tungstenUniversalRepair;
        public final ConfigValue<Boolean> equipmentDurability;
        @ConfigKey("integrate_chest_loot")
        public final ConfigValue<Boolean> chestLoot;
        @ConfigKey("integrate_entity_loot")
        public final ConfigValue<Boolean> entityLoot;
        public final ConfigValue<Boolean> steelEfficiencyNerf;
        public final ConfigValue<Integer> geoScannerInterval;
        public final ConfigValue<Integer> geoScannerRange;
        public final ConfigValue<Integer> motionDetectorInterval;
        public final ConfigValue<Integer> motionDetectorRange;
        public final ConfigValue<Integer> bronzeRadarInterval;
        public final ConfigValue<Integer> bronzeRadarPlayerSearchTime;

        private Common(ModConfigSpec.Builder builder) {
            builder.push("powers");

            toolsPowers = builder
                    .translation("orevolution.config.tools_powers")
                    .comment("Defines if tools will have their special characteristic (aka power), like tin's drop duplication. check client configs to disable the tooltip too")
                    .define("tools_powers", true);
            weaponsPowers = builder
                    .translation("orevolution.config.weapons_powers")
                    .comment("Defines if weapons will have their special characteristic (aka power), like tin's drop duplication. check client configs to disable the tooltip too")
                    .define("weapons_powers", true);
            armorsPowers = builder
                    .translation("orevolution.config.armors_powers")
                    .comment("Defines if armors will have their special characteristic (aka power), like platinum's strength II effect. check client configs to disable the tooltip too")
                    .define("armors_powers", true);

            builder.pop();

            builder.push("worldgen");

            prioritizeTerrablender = builder
                    .gameRestart()
                    .translation("orevolution.config.prioritize_terrablender_over_biolith")
                    .define("prioritize_terrablender_over_biolith", true);
            enableModdedBiomes = builder
                    .gameRestart()
                    .translation("orevolution.config.enable_modded_biomes")
                    .define("enable_modded_biomes", true);

            generateForgottenCaves = builder
                    .gameRestart()
                    .translation("orevolution.config.generate_forgotten_cave_biome")
                    .comment("Defines if the Forgotten Cave biome will keep generating in the Overworld")
                    .define("generate_forgotten_cave_biome", true);
            generateGeodeGardens = builder
                    .gameRestart()
                    .translation("orevolution.config.generate_geode_gardens_biome")
                    .comment("Defines if the Geode Gardens biome will keep generating in the Overworld")
                    .define("generate_geode_gardens_biome", true);

            generatePyriteOre = builder
                    .worldRestart()
                    .translation("orevolution.config.generate_pyrite_ore")
                    .comment("Defines if Pyrite will keep generating in the Nether")
                    .define("generate_pyrite_ore", true);

            generateCelestite = builder
                    .worldRestart()
                    .translation("orevolution.config.generate_celestite_geode")
                    .comment("Defines if Celestine Geodes will keep generating in the Overworld")
                    .define("generate_celestite_geode", true);

            generateTinOre = builder
                    .worldRestart()
                    .translation("orevolution.config.generate_tin_ore")
                    .comment("Defines if Tin will keep generating in the Overworld")
                    .define("generate_tin_ore", true);

//            generateNickelOre = builder
//                    .worldRestart()
//                    .translation("orevolution.config.generate_nickel_ore")
//                    .comment("Defines if Nickel will keep generating in the Overworld")
//                    .define("generate_nickel_ore", false);

            generatePlatOre = builder
                    .worldRestart()
                    .translation("orevolution.config.generate_platinum_ore")
                    .comment("Defines if Platinum will keep generating in the Overworld")
                    .define("generate_platinum_ore", true);

            generateMoonstonePillar = builder
                    .worldRestart()
                    .translation("orevolution.config.generate_moonstone_pillar")
                    .comment("Defines if Moonstone Pillars will keep generating in the Overworld")
                    .define("generate_moonstone_pillar", true);

            generateTungstenOre = builder
                    .worldRestart()
                    .translation("orevolution.config.generate_tungsten_ore")
                    .comment("Defines if Tungsten will keep generating in the Nether")
                    .define("generate_tungsten_ore", true);

            generateExperienceOre = builder
                    .worldRestart()
                    .translation("orevolution.config.generate_experience_ore")
                    .comment("Defines if experience ore will generate in the Overworld")
                    .define("generate_experience_ore", true);

            generateLivingstoneOre = builder
                    .worldRestart()
                    .translation("orevolution.config.generate_livingstone_ore")
                    .comment("Defines if Livingstone is obtainable through farming")
                    .define("generate_livingstone_ore", true);

            generateVerditeOre = builder
                    .worldRestart()
                    .translation("orevolution.config.generate_verdite_ore")
                    .comment("Defines if Verdite is obtainable through composting")
                    .define("generate_verdite_ore", true);

            generateAethersteelOre = builder
                    .worldRestart()
                    .translation("orevolution.config.generate_aethersteel_ore")
                    .comment("Defines if Aethersteel will keep generating in the End")
                    .define("generate_aethersteel_ore", true);

            generateAetherrockMeteor = builder
                    .worldRestart()
                    .translation("orevolution.config.generate_aetherrock_meteor")
                    .comment("Defines if aetherrock meteor will keep generating in the End (this will also disable aethersteel)")
                    .define("generate_aetherrock_meteor", true);
            
            builder.pop();

            builder.push("other");

            safeOreBreaking = builder
                    .translation("orevolution.config.safe_ore_breaking")
                    .comment("Ores won't break if mined with the incorrect tool")
                    .define("safe_ore_breaking", true);

            modProgression = builder
                    .translation("orevolution.config.modded_progression")
                    .comment("Replaces the original ore progression of the game")
                    .define("modded_progression", true);

            bronzeRecipes = builder
                    .worldRestart()
                    .translation("orevolution.config.bronze_recipes")
                    .comment("Defines if Bronze Alloy is craftable")
                    .define("bronze_recipes", true);

            steelRecipes = builder
                    .worldRestart()
                    .translation("orevolution.config.steel_recipes")
                    .comment("Defines if Steel Alloy is craftable")
                    .define("steel_recipes", true);

            mobEquipment = builder
                    .translation("orevolution.config.mob_equipment")
                    .comment("Whether or not mobs will spawn with modded equipment such as tin swords")
                    .define("mob_equipment", true);

            tungstenUniversalRepair = builder
                    .translation("orevolution.config.tungsten_universal_repair")
                    .comment("Whether or not tungsten will be capable of repairing all items")
                    .define("tungsten_universal_repair", true);

            equipmentDurability = builder
                    .gameRestart()
                    .translation("orevolution.config.equipment_durability")
                    .comment("Defines if items can have their durability modified by orevolution. Disabling this makes Reinforcing templates useless and Coating templates unbalanced")
                    .define("equipment_durability", true);

            chestLoot = builder
                    .gameRestart()
                    .translation("orevolution.config.chest_loot")
                    .comment("Defines if vanilla loot found in chests from structures such as End Cities will be modified")
                    .define("chest_loot", true);

            entityLoot = builder
                    .gameRestart()
                    .translation("orevolution.config.entity_loot")
                    .comment("Defines if vanilla loot from entities such as piglins will be modified")
                    .define("entity_loot", true);

            steelEfficiencyNerf = builder
                    .translation("orevolution.config.steel_efficiency_nerf")
                    .comment("Defines if steel tools will increase their durability cost for each mined block per efficiency level")
                    .define("steel_efficiency_nerf", false);

            geoScannerInterval = builder
                    .translation("orevolution.config.geo_scanner_interval")
                    .comment("The amount of times per 20 ticks the geo scanner will search for ores")
                    .defineInRange("geo_scanner_interval", 2, 1, Integer.MAX_VALUE);

            geoScannerRange = builder
                    .translation("orevolution.config.geo_scanner_range")
                    .comment("The range (per axis) the geo scanner will search for ores")
                    .defineInRange("geo_scanner_range", 5, 1, Integer.MAX_VALUE);

            motionDetectorInterval = builder
                    .translation("orevolution.config.motion_detector_interval")
                    .comment("The amount of times per 20 ticks the motion detector will search for entities")
                    .defineInRange("motion_detector_interval", 2, 1, Integer.MAX_VALUE);

            motionDetectorRange = builder
                    .translation("orevolution.config.motion_detector_range")
                    .comment("The range (per axis) the motion detector will search for entities")
                    .defineInRange("motion_detector_range", 24, 1, Integer.MAX_VALUE);

            bronzeRadarPlayerSearchTime = builder
                    .translation("orevolution.config.bronze_radar_player_search_time")
                    .comment("The time (in ticks) you have to wait every time you search for a player")
                    .defineInRange("bronze_radar_player_search_time", 100, 1, Integer.MAX_VALUE);

            bronzeRadarInterval = builder
                    .translation("orevolution.config.bronze_radar_interval")
                    .comment("The amount of times per 20 ticks the bronze radar will search for entities")
                    .defineInRange("bronze_radar", 3, 1, Integer.MAX_VALUE);
        }
    }

    public static class Client {
        public final ConfigValue<Boolean> warnBreak;
        public final ConfigValue<Boolean> warnIncorrectToolType;

        public final ConfigValue<Boolean> displayReinforcedOrCoated;
        public final ConfigValue<Boolean> displayDurabilityModifier;
        public final ConfigValue<Boolean> displayParticleEffectsFromArmor;
        public final ConfigValue<Boolean> displayEffectsFromArmor;
        public final ConfigValue<Boolean> displayChanceTypeInBlock;

        public final ConfigValue<Boolean> steel_outline;

        public final ConfigValue<Integer> forgottenCaveFogMin;
        public final ConfigValue<Integer> forgottenCaveFogMax;

        private Client(ModConfigSpec.Builder builder) {
            warnBreak = builder
                    .translation("orevolution.config.warn_break")
                    .comment("Displays the text 'You can't harvest this block yet'")
                    .define("warn_break", true);

            warnIncorrectToolType = builder
                    .translation("orevolution.config.warn_incorrect_tool_type")
                    .comment("Displays the text 'This block requires a x tool'")
                    .define("warn_incorrect_tool_type", true);

            displayReinforcedOrCoated = builder
                    .translation("orevolution.config.display_reinforced_or_coated")
                    .comment("Displays the text 'Tungsten Reinforced/Coated' in the tooltip of the item")
                    .define("display_reinforced_or_coated", true);
            displayDurabilityModifier = builder
                    .translation("orevolution.config.display_durability_modifier")
                    .comment("Displays the text '+/- % Durability' in the tooltip of the item")
                    .define("display_durability_modifier", true);

            displayParticleEffectsFromArmor = builder
                    .translation("orevolution.config.display_particle_effects_from_armor")
                    .comment("Displays the particles that appear from effects applied by armor sets")
                    .define("display_particle_effects_from_armor", true);

            displayEffectsFromArmor = builder
                    .translation("orevolution.config.display_effects_from_armor")
                    .comment("Displays the effects applied by armor sets in your HUD/GUI")
                    .define("display_effects_from_armor", true);

            displayChanceTypeInBlock = builder
                    .translation("orevolution.config.display_chance_type_in_block")
                    .comment("Displays the chance type of every block in their tooltips")
                    .define("display_chance_type_in_block", true);

            steel_outline = builder
                    .translation("orevolution.config.steel_outline")
                    .comment("Display the 3x3 outline on all blocks while holding, for example, the steel hammer")
                    .define("steel_outline", true);

            forgottenCaveFogMin = builder
                    .translation("orevolution.config.forgotten_caves_min_fog")
                    .defineInRange("forgotten_cave_min_fog", -25, -1000, 1000);
            forgottenCaveFogMax = builder
                    .translation("orevolution.config.forgotten_caves_max_fog")
                    .defineInRange("forgotten_cave_max_fog", 50, -1000, 1000);
        }
    }

    public static class StartUp {
        public final ConfigValue<Boolean> debugMod;

        private StartUp(ModConfigSpec.Builder builder) {
            debugMod = builder
                    .translation("orevolution.config.debug_mode")
                    .define("debug_mode", false);
        }
    }

    static {
        final Pair<Common, ModConfigSpec> commonSpecPair = new ModConfigSpec.Builder().configure(Common::new);
        final Pair<Client, ModConfigSpec> clientSpecPair = new ModConfigSpec.Builder().configure(Client::new);
        final Pair<Powers, ModConfigSpec> powerSpecPair = new ModConfigSpec.Builder().configure(Powers::new);
        final Pair<ToolStats, ModConfigSpec> toolStatsSpecPair = new ModConfigSpec.Builder().configure(ToolStats::new);
        final Pair<ModCompat, ModConfigSpec> modCompatSpecPair = new ModConfigSpec.Builder().configure(ModCompat::new);

        final Pair<StartUp, ModConfigSpec> startUpSpecPair = new ModConfigSpec.Builder().configure(StartUp::new);
        STARTUP = startUpSpecPair.getLeft();
        STARTUP_SPEC = startUpSpecPair.getRight();

        MODCOMPAT = modCompatSpecPair.getLeft();
        POWERS = powerSpecPair.getLeft();
        TOOLSTATS = toolStatsSpecPair.getLeft();
        COMMON = commonSpecPair.getLeft();
        CLIENT = clientSpecPair.getLeft();
        MODCOMPAT_SPEC = modCompatSpecPair.getRight();
        POWERS_SPEC = powerSpecPair.getRight();
        TOOLSTATS_SPEC = toolStatsSpecPair.getRight();
        COMMON_SPEC = commonSpecPair.getRight();
        CLIENT_SPEC = clientSpecPair.getRight();
    }

    public static void register(ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, COMMON_SPEC);
        modContainer.registerConfig(ModConfig.Type.CLIENT, CLIENT_SPEC);
        modContainer.registerConfig(ModConfig.Type.COMMON, POWERS_SPEC, "orevolution-powers.toml");
        modContainer.registerConfig(ModConfig.Type.COMMON, TOOLSTATS_SPEC, "orevolution-toolstats.toml");
        modContainer.registerConfig(ModConfig.Type.COMMON, MODCOMPAT_SPEC, "orevolution-modcompat.toml");
        modContainer.registerConfig(ModConfig.Type.STARTUP, STARTUP_SPEC, "orevolution-startup.toml");

    }
}
