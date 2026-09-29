package net.bexla.orevolution;

import com.teamabnormals.blueprint.core.annotations.ConfigKey;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

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
        public final ConfigValue<Boolean> tinWeaponPowers;

        public final ConfigValue<Boolean> cassiteriteToolPowers;
        public final ConfigValue<Boolean> cassiteriteWeaponPowers;
        public final ConfigValue<Boolean> cassiteriteArmorPowers;

        public final ConfigValue<Boolean> ironToolPowers;
        public final ConfigValue<Boolean> ironWeaponPowers;
        public final ConfigValue<Boolean> ironArmorPowers;

        public final ConfigValue<Boolean> goldToolPowers;
        public final ConfigValue<Boolean> goldWeaponPowers;
        public final ConfigValue<Boolean> goldArmorPowers;

        public final ConfigValue<Boolean> platinumToolPowers;
        public final ConfigValue<Boolean> platinumWeaponPowers;
        public final ConfigValue<Boolean> platinumArmorPowers;

        public final ConfigValue<Boolean> tungstenToolPowers;
        public final ConfigValue<Boolean> tungstenWeaponPowers;
        public final ConfigValue<Boolean> tungstenArmorPowers;

        public final ConfigValue<Boolean> diamondToolPowers;
        public final ConfigValue<Boolean> diamondWeaponPowers;
        public final ConfigValue<Boolean> diamondArmorPowers;

        public final ConfigValue<Boolean> moonstoneToolPowers;
        public final ConfigValue<Boolean> moonstoneWeaponPowers;
        public final ConfigValue<Boolean> moonstoneArmorPowers;

        public final ConfigValue<Boolean> netheriteToolPowers;
        public final ConfigValue<Boolean> netheriteWeaponPowers;
        public final ConfigValue<Boolean> netheriteArmorPowers;

        public final ConfigValue<Boolean> aethersteelToolPowers;
        public final ConfigValue<Boolean> aethersteelWeaponPowers;
        public final ConfigValue<Boolean> aethersteelArmorPowers;

        public final ConfigValue<Boolean> livingstoneToolPowers;
        public final ConfigValue<Boolean> livingstoneWeaponPowers;
        public final ConfigValue<Boolean> livingstoneArmorPowers;
        public final ConfigValue<Boolean> verditeArmorPowers;

        public final ConfigValue<Boolean> verditeToolPowers;
        public final ConfigValue<Boolean> verditeWeaponPowers;

        public final ConfigValue<Boolean> steelToolPowers;
        public final ConfigValue<Boolean> steelWeaponPowers;

        public final ConfigValue<Boolean> bronzeArmorPowers;


        private Powers(ModConfigSpec.Builder builder) {
            builder.push("tools");

            tinToolPowers = builder
                    .translation("orevolution.config.tin_tool_powers")
                    .define("tin_tool_powers", true);
            cassiteriteToolPowers = builder
                    .translation("orevolution.config.cassiterite_tool_powers")
                    .define("cassiterite_tool_powers", true);
            ironToolPowers = builder
                    .translation("orevolution.config.iron_tool_powers")
                    .define("iron_tool_powers", true);
            goldToolPowers = builder
                    .translation("orevolution.config.gold_tool_powers")
                    .define("gold_tool_powers", true);
            moonstoneToolPowers = builder
                    .translation("orevolution.config.moonstone_tool_powers")
                    .define("moonstone_tool_powers", true);
            tungstenToolPowers = builder
                    .translation("orevolution.config.tungsten_tool_powers")
                    .define("tungsten_tool_powers", true);
            platinumToolPowers = builder
                    .translation("orevolution.config.platinum_tool_powers")
                    .define("platinum_tool_powers", true);
            diamondToolPowers = builder
                    .translation("orevolution.config.diamond_tool_powers")
                    .define("diamond_tool_powers", true);
            netheriteToolPowers = builder
                    .translation("orevolution.config.netherite_tool_powers")
                    .define("netherite_tool_powers", true);
            aethersteelToolPowers = builder
                    .translation("orevolution.config.aethersteel_tool_powers")
                    .define("aethersteel_tool_powers", true);
            livingstoneToolPowers = builder
                    .translation("orevolution.config.livingstone_tool_powers")
                    .define("livingstone_tool_powers", true);
            verditeToolPowers = builder
                    .translation("orevolution.config.verdite_tool_powers")
                    .define("verdite_tool_powers", true);
            steelToolPowers = builder
                    .translation("orevolution.config.steel_tool_powers")
                    .define("steel_tool_powers", true);

            builder.pop();

            builder.push("weapons");

            tinWeaponPowers = builder
                    .translation("orevolution.config.tin_weapon_powers")
                    .define("tin_weapon_powers", true);
            cassiteriteWeaponPowers = builder
                    .translation("orevolution.config.cassiterite_weapon_powers")
                    .define("cassiterite_weapon_powers", true);
            ironWeaponPowers = builder
                    .translation("orevolution.config.iron_weapon_powers")
                    .define("iron_weapon_powers", true);
            goldWeaponPowers = builder
                    .translation("orevolution.config.gold_weapon_powers")
                    .define("gold_weapon_powers", true);
            moonstoneWeaponPowers = builder
                    .translation("orevolution.config.moonstone_weapon_powers")
                    .define("moonstone_weapon_powers", true);
            tungstenWeaponPowers = builder
                    .translation("orevolution.config.tungsten_weapon_powers")
                    .define("tungsten_weapon_powers", true);
            platinumWeaponPowers = builder
                    .translation("orevolution.config.platinum_weapon_powers")
                    .define("platinum_weapon_powers", true);
            diamondWeaponPowers = builder
                    .translation("orevolution.config.diamond_weapon_powers")
                    .define("diamond_weapon_powers", true);
            netheriteWeaponPowers = builder
                    .translation("orevolution.config.netherite_weapon_powers")
                    .define("netherite_weapon_powers", true);
            aethersteelWeaponPowers = builder
                    .translation("orevolution.config.aethersteel_weapon_powers")
                    .define("aethersteel_weapon_powers", true);
            livingstoneWeaponPowers = builder
                    .translation("orevolution.config.livingstone_weapon_powers")
                    .define("livingstone_weapon_powers", true);
            verditeWeaponPowers = builder
                    .translation("orevolution.config.verdite_weapon_powers")
                    .define("verdite_weapon_powers", true);
            steelWeaponPowers = builder
                    .translation("orevolution.config.steel_weapon_powers")
                    .define("steel_weapon_powers", true);

            builder.pop();

            builder.push("armors");

            cassiteriteArmorPowers = builder
                    .translation("orevolution.config.cassiterite_armor_powers")
                    .define("cassiterite_armor_powers", true);
            ironArmorPowers = builder
                    .translation("orevolution.config.iron_armor_powers")
                    .define("iron_armor_powers", true);
            goldArmorPowers = builder
                    .translation("orevolution.config.gold_armor_powers")
                    .define("gold_armor_powers", true);
            moonstoneArmorPowers = builder
                    .translation("orevolution.config.moonstone_armor_powers")
                    .define("moonstone_armor_powers", true);
            platinumArmorPowers = builder
                    .translation("orevolution.config.platinum_armor_powers")
                    .define("platinum_armor_powers", true);
            diamondArmorPowers = builder
                    .translation("orevolution.config.diamond_armor_powers")
                    .define("diamond_armor_powers", true);
            netheriteArmorPowers = builder
                    .translation("orevolution.config.netherite_armor_powers")
                    .define("netherite_armor_powers", true);
            tungstenArmorPowers = builder
                    .translation("orevolution.config.tungsten_armor_powers")
                    .define("tungsten_armor_powers", true);
            aethersteelArmorPowers = builder
                    .translation("orevolution.config.aethersteel_armor_powers")
                    .define("aethersteel_armor_powers", true);
            livingstoneArmorPowers = builder
                    .translation("orevolution.config.livingstone_armor_powers")
                    .define("livingstone_armor_powers", true);
            verditeArmorPowers = builder
                    .translation("orevolution.config.verdite_armor_powers")
                    .define("verdite_armor_powers", true);
            bronzeArmorPowers = builder
                    .translation("orevolution.config.bronze_armor_powers")
                    .define("bronze_armor_powers", true);

            builder.pop();
        }

    }

    public static class ToolStats {
        public final ConfigValue<Integer> woodMaxUses;
        public final ConfigValue<Integer> stoneMaxUses;
        public final ConfigValue<Integer> tinMaxUses;
        public final ConfigValue<Integer> goldMaxUses;
        public final ConfigValue<Integer> ironMaxUses;
        public final ConfigValue<Integer> platMaxUses;
        public final ConfigValue<Integer> diamondMaxUses;
        public final ConfigValue<Integer> moonstoneMaxUses;
        public final ConfigValue<Integer> netheriteMaxUses;
        public final ConfigValue<Integer> aetherMaxUses;
        public final ConfigValue<Integer> livingstoneMaxUses;
        public final ConfigValue<Integer> verditeMaxUses;
        public final ConfigValue<Integer> steelMaxUses;

        public final ConfigValue<Boolean> stoneFollowsTin;
        public final ConfigValue<Boolean> ironFollowsPlatinum;
        public final ConfigValue<Boolean> goldBuff;

        private ToolStats(ModConfigSpec.Builder builder) {
            builder.push("capability");

            stoneFollowsTin = builder
                    .translation("orevolution.config.stone_follows_tin")
                    .comment("Stone tools won't mine iron and must instead be used to get Tin tools")
                    .define("stone_follows_tin", true);

            goldBuff = builder
                    .translation("orevolution.config.gold_buff")
                    .comment("Gold tools will be capable of harvesting tin and iron tiered blocks (Also affects Tungsten tools)")
                    .define("gold_buff", true);

            ironFollowsPlatinum = builder
                    .translation("orevolution.config.iron_follows_platinum")
                    .comment("Iron tools won't mine diamond and must instead be used to get Platinum tools")
                    .define("iron_follows_platinum", true);

            builder.pop();

            builder.push("durability");

            woodMaxUses = builder
                    .translation("orevolution.config.wood_max_uses")
                    .comment("Modifies the max uses of wood tools and weapons. vanilla is 59")
                    .defineInRange("wood_max_uses", 64, -1, Integer.MAX_VALUE);

            goldMaxUses = builder
                    .translation("orevolution.config.gold_max_uses")
                    .comment("Modifies the max uses of gold tools and weapons. vanilla is 32")
                    .defineInRange("gold_max_uses", 128, -1, Integer.MAX_VALUE);
            stoneMaxUses = builder
                    .translation("orevolution.config.stone_max_uses")
                    .comment("Modifies the max uses of stone tools and weapons. vanilla is 131")
                    .defineInRange("stone_max_uses", 192, -1, Integer.MAX_VALUE);
            tinMaxUses = builder
                    .translation("orevolution.config.tin_max_uses")
                    .comment("Modifies the max uses of tin tools and weapons. vanilla is 256")
                    .defineInRange("tin_max_uses", 256, -1, Integer.MAX_VALUE);
            ironMaxUses = builder
                    .translation("orevolution.config.iron_max_uses")
                    .comment("Modifies the max uses of iron tools and weapons. vanilla is 250")
                    .defineInRange("iron_max_uses", 448, -1, Integer.MAX_VALUE);
            platMaxUses = builder
                    .translation("orevolution.config.platinum_max_uses")
                    .comment("Modifies the max uses of platinum tools and weapons. vanilla is 768")
                    .defineInRange("platinum_max_uses", 768, -1, Integer.MAX_VALUE);
            diamondMaxUses = builder
                    .translation("orevolution.config.diamond_max_uses")
                    .comment("Modifies the max uses of diamond tools and weapons. vanilla is 1561")
                    .defineInRange("diamond_max_uses", 1600, -1, Integer.MAX_VALUE);
            moonstoneMaxUses = builder
                    .translation("orevolution.config.moonstone_max_uses")
                    .comment("Modifies the max uses of moonstone tools and weapons. vanilla is 1024")
                    .defineInRange("moonstone_max_uses", 1024, -1, Integer.MAX_VALUE);
            netheriteMaxUses = builder
                    .translation("orevolution.config.netherite_max_uses")
                    .comment("Modifies the max uses of netherite tools and weapons. vanilla is 2031")
                    .defineInRange("netherite_max_uses", 2432, -1, Integer.MAX_VALUE);
            aetherMaxUses = builder
                    .translation("orevolution.config.aethersteel_max_uses")
                    .comment("Modifies the max uses of aethersteel tools and weapons. vanilla is 3520")
                    .defineInRange("aethersteel_max_uses", 3520, -1, Integer.MAX_VALUE);
            livingstoneMaxUses = builder
                    .translation("orevolution.config.livingstone_max_uses")
                    .comment("Modifies the max uses of livingstone tools and weapons. vanilla is 192")
                    .defineInRange("livingstone_max_uses", 192, -1, Integer.MAX_VALUE);
            verditeMaxUses = builder
                    .translation("orevolution.config.verdite_max_uses")
                    .comment("Modifies the max uses of verdite tools and weapons. vanilla is 448")
                    .defineInRange("verdite_max_uses", 448, -1, Integer.MAX_VALUE);
            steelMaxUses = builder
                    .translation("orevolution.config.steel_max_uses")
                    .comment("Modifies the max uses of steel tools. vanilla is 896")
                    .defineInRange("steel_max_uses", 896, -1, Integer.MAX_VALUE);

            builder.pop();
        }
    }

    public static class ModCompat {
        public final ConfigValue<Boolean> kineticDamage;
        public final ConfigValue<Boolean> speedPerArmorPiece;

        public final ConfigValue<Boolean> invincibilityPerArmorPiece;

        public final ConfigValue<Boolean> electrumToolPowers;
        public final ConfigValue<Boolean> electrumWeaponPowers;
        public final ConfigValue<Boolean> electrumArmorPowers;

        public final ConfigValue<Boolean> silverToolPowers;
        public final ConfigValue<Boolean> silverWeaponPowers;
        public final ConfigValue<Boolean> silverArmorPowers;

        public final ConfigValue<Boolean> electrumFollowsPlatinum;

        public final ConfigValue<Integer> electrumMaxUses;

        public final ConfigValue<Boolean> copperArmorPowers;
        public final ConfigValue<Boolean> copperFollowsTin;
        public final ConfigValue<Boolean> tinFollowsCopper;

        public final ConfigValue<Integer> copperMaxUses;

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

            electrumToolPowers = builder
                    .translation("orevolution.config.electrum_tool_powers")
                    .define("electrum_tool_powers", true);
            electrumWeaponPowers = builder
                    .translation("orevolution.config.electrum_weapon_powers")
                    .define("electrum_weapon_powers", true);
            electrumArmorPowers = builder
                    .translation("orevolution.config.electrum_armor_powers")
                    .define("electrum_armor_powers", true);
            
            silverToolPowers = builder
                    .translation("orevolution.config.silver_tool_powers")
                    .define("silver_tool_powers", true);
            silverWeaponPowers = builder
                    .translation("orevolution.config.silver_weapon_powers")
                    .define("silver_weapon_powers", true);
            silverArmorPowers = builder
                    .translation("orevolution.config.silver_armor_powers")
                    .define("silver_armor_powers", true);

            electrumFollowsPlatinum = builder
                    .translation("orevolution.config.electrum_follows_platinum")
                    .comment("Electrum tools won't mine diamond and must instead be used to get Platinum tools")
                    .define("electrum_follows_platinum", true);

            electrumMaxUses = builder
                    .translation("orevolution.config.electrum_max_uses")
                    .comment("Modifies the max uses of electrum tools and weapons. vanilla is 1561, modded is 1920")
                    .defineInRange("electrum_max_uses", 1920, -1, Integer.MAX_VALUE);

            builder.pop();

            builder.push("copperagebackport");

            copperArmorPowers = builder
                    .translation("orevolution.config.copper_armor_powers")
                    .define("copper_armor_powers", true);

            copperFollowsTin = builder
                    .translation("orevolution.config.copper_follows_tin")
                    .comment("Copper tools won't mine iron and must instead be used to get Tin tools")
                    .define("copper_follows_tin", true);
            tinFollowsCopper = builder
                    .translation("orevolution.config.tin_follows_copper")
                    .comment("Tin tools won't mine iron and must instead be used to get Copper tools")
                    .define("tin_follows_copper", false);


            copperMaxUses = builder
                    .translation("orevolution.config.copper_max_uses")
                    .comment("Modifies the max uses of electrum tools and weapons. vanilla is 190, modded is 320")
                    .defineInRange("copper_max_uses", 320, -1, Integer.MAX_VALUE);

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
//        @ConfigKey("generate_nickel")
//        public final ConfigValue<Boolean> generateNickelOre;
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
                    .comment("Defines if tools and weapons will have their durability replaced")
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
        public final ConfigValue<Boolean> displayFireResistant;

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
            displayFireResistant = builder
                    .translation("orevolution.config.display_fire_resistant")
                    .comment("Displays the text 'Fire Resistant' in the tooltip of the item")
                    .define("display_fire_resistant", true);

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
