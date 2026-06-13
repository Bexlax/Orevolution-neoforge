package net.bexla.orevolution;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

public class OrevolutionConfig {
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
        public final ConfigValue<Boolean> ironToolPowers;
        public final ConfigValue<Boolean> goldToolPowers;
        public final ConfigValue<Boolean> platinumToolPowers;
        public final ConfigValue<Boolean> diamondToolPowers;
        public final ConfigValue<Boolean> netheriteToolPowers;
        public final ConfigValue<Boolean> aethersteelToolPowers;
        public final ConfigValue<Boolean> livingstoneToolPowers;
        public final ConfigValue<Boolean> verditeToolPowers;
        public final ConfigValue<Boolean> steelToolPowers;

        public final ConfigValue<Boolean> tinWeaponPowers;
        public final ConfigValue<Boolean> ironWeaponPowers;
        public final ConfigValue<Boolean> goldWeaponPowers;
        public final ConfigValue<Boolean> platinumWeaponPowers;
        public final ConfigValue<Boolean> diamondWeaponPowers;
        public final ConfigValue<Boolean> netheriteWeaponPowers;
        public final ConfigValue<Boolean> aethersteelWeaponPowers;
        public final ConfigValue<Boolean> livingstoneWeaponPowers;
        public final ConfigValue<Boolean> verditeWeaponPowers;
        public final ConfigValue<Boolean> steelWeaponPowers;

        public final ConfigValue<Boolean> ironArmorPowers;
        public final ConfigValue<Boolean> goldArmorPowers;
        public final ConfigValue<Boolean> platinumArmorPowers;
        public final ConfigValue<Boolean> diamondArmorPowers;
        public final ConfigValue<Boolean> netheriteArmorPowers;
        public final ConfigValue<Boolean> reinforcedNetheriteArmorPowers;
        public final ConfigValue<Boolean> aethersteelArmorPowers;
        public final ConfigValue<Boolean> livingstoneArmorPowers;
        public final ConfigValue<Boolean> verditeArmorPowers;
        public final ConfigValue<Boolean> bronzeArmorPowers;
        public final ConfigValue<Boolean> tungstenArmorPowers;

        private Powers(ModConfigSpec.Builder builder) {
            builder.push("powers.tools");

            tinToolPowers = builder
                    .translation("orevolution.config.tin_tool_powers")
                    .define("tin_tool_powers", true);
            ironToolPowers = builder
                    .translation("orevolution.config.iron_tool_powers")
                    .define("iron_tool_powers", true);
            goldToolPowers = builder
                    .translation("orevolution.config.gold_tool_powers")
                    .define("gold_tool_powers", true);
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

            builder.push("powers.weapons");

            tinWeaponPowers = builder
                    .translation("orevolution.config.tin_weapon_powers")
                    .define("tin_weapon_powers", true);
            ironWeaponPowers = builder
                    .translation("orevolution.config.iron_weapon_powers")
                    .define("iron_weapon_powers", true);
            goldWeaponPowers = builder
                    .translation("orevolution.config.gold_weapon_powers")
                    .define("gold_weapon_powers", true);
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

            builder.push("powers.armors");

            ironArmorPowers = builder
                    .translation("orevolution.config.iron_armor_powers")
                    .define("iron_armor_powers", true);
            goldArmorPowers = builder
                    .translation("orevolution.config.gold_armor_powers")
                    .define("gold_armor_powers", true);
            platinumArmorPowers = builder
                    .translation("orevolution.config.platinum_armor_powers")
                    .define("platinum_armor_powers", true);
            diamondArmorPowers = builder
                    .translation("orevolution.config.diamond_armor_powers")
                    .define("diamond_armor_powers", true);
            netheriteArmorPowers = builder
                    .translation("orevolution.config.netherite_armor_powers")
                    .define("netherite_armor_powers", true);
            reinforcedNetheriteArmorPowers = builder
                    .translation("orevolution.config.reinforced_netherite_armor_powers")
                    .define("reinforced_netherite_armor_powers", true);
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
            tungstenArmorPowers = builder
                    .translation("orevolution.config.tungsten_armor_powers")
                    .define("tungsten_armor_powers", true);

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
        public final ConfigValue<Integer> netheriteMaxUses;
        public final ConfigValue<Integer> aetherMaxUses;
        public final ConfigValue<Integer> livingstoneMaxUses;
        public final ConfigValue<Integer> verditeMaxUses;
        public final ConfigValue<Integer> steelMaxUses;

        private ToolStats(ModConfigSpec.Builder builder) {
            builder.push("durability");

            woodMaxUses = builder
                    .translation("orevolution.config.wood_max_uses")
                    .comment("Modifies the max uses of wood tools and weapons. vanilla is 59")
                    .defineInRange("wood_max_uses", 64, 1, Integer.MAX_VALUE);

            goldMaxUses = builder
                    .translation("orevolution.config.gold_max_uses")
                    .comment("Modifies the max uses of gold tools and weapons. vanilla is 32")
                    .defineInRange("gold_max_uses", 128, 1, Integer.MAX_VALUE);
            stoneMaxUses = builder
                    .translation("orevolution.config.stone_max_uses")
                    .comment("Modifies the max uses of stone tools and weapons. vanilla is 131")
                    .defineInRange("stone_max_uses", 192, 1, Integer.MAX_VALUE);
            tinMaxUses = builder
                    .translation("orevolution.config.tin_max_uses")
                    .comment("Modifies the max uses of tin tools and weapons. vanilla is 256")
                    .defineInRange("tin_max_uses", 256, 1, Integer.MAX_VALUE);
            ironMaxUses = builder
                    .translation("orevolution.config.iron_max_uses")
                    .comment("Modifies the max uses of iron tools and weapons. vanilla is 250")
                    .defineInRange("iron_max_uses", 448, 1, Integer.MAX_VALUE);
            platMaxUses = builder
                    .translation("orevolution.config.platinum_max_uses")
                    .comment("Modifies the max uses of platinum tools and weapons. vanilla is 768")
                    .defineInRange("platinum_max_uses", 768, 1, Integer.MAX_VALUE);
            diamondMaxUses = builder
                    .translation("orevolution.config.diamond_max_uses")
                    .comment("Modifies the max uses of diamond tools and weapons. vanilla is 1561")
                    .defineInRange("diamond_max_uses", 1600, 1, Integer.MAX_VALUE);
            netheriteMaxUses = builder
                    .translation("orevolution.config.netherite_max_uses")
                    .comment("Modifies the max uses of netherite tools and weapons. vanilla is 2031")
                    .defineInRange("netherite_max_uses", 2432, 1, Integer.MAX_VALUE);
            aetherMaxUses = builder
                    .translation("orevolution.config.aethersteel_max_uses")
                    .comment("Modifies the max uses of aethersteel tools and weapons. vanilla is 3520")
                    .defineInRange("aethersteel_max_uses", 3520, 1, Integer.MAX_VALUE);
            livingstoneMaxUses = builder
                    .translation("orevolution.config.livingstone_max_uses")
                    .comment("Modifies the max uses of livingstone tools and weapons. vanilla is 192")
                    .defineInRange("livingstone_max_uses", 192, 1, Integer.MAX_VALUE);
            verditeMaxUses = builder
                    .translation("orevolution.config.verdite_max_uses")
                    .comment("Modifies the max uses of verdite tools and weapons. vanilla is 448")
                    .defineInRange("verdite_max_uses", 448, 1, Integer.MAX_VALUE);
            steelMaxUses = builder
                    .translation("orevolution.config.steel_max_uses")
                    .comment("Modifies the max uses of steel tools. vanilla is 1152")
                    .defineInRange("steel_max_uses", 1152, 1, Integer.MAX_VALUE);

            builder.pop();
        }
    }

    public static class ModCompat {
        public final ConfigValue<Boolean> kineticDamage;
        public final ConfigValue<Boolean> speedPerArmorPiece;

        public final ConfigValue<Boolean> electrumToolPowers;
        public final ConfigValue<Boolean> electrumWeaponPowers;
        public final ConfigValue<Boolean> electrumArmorPowers;

        public final ConfigValue<Integer> electrumMaxUses;

        public final ConfigValue<Boolean> copperArmorPowers;

        public final ConfigValue<Integer> copperMaxUses;

        private ModCompat(ModConfigSpec.Builder builder) {
            builder.push("oreganized");

            kineticDamage = builder
                    .translation("orevolution.config.electrum_kinetic_damage")
                    .define("electrum_kinetic_damage", false);
            speedPerArmorPiece = builder
                    .translation("orevolution.config.speed_per_armor_piece")
                    .define("speed_per_armor_piece", false);

            electrumToolPowers = builder
                    .gameRestart()
                    .translation("orevolution.config.electrum_tool_powers")
                    .define("electrum_tool_powers", true);
            electrumWeaponPowers = builder
                    .gameRestart()
                    .translation("orevolution.config.electrum_weapon_powers")
                    .define("electrum_weapon_powers", true);
            electrumArmorPowers = builder
                    .gameRestart()
                    .translation("orevolution.config.electrum_armor_powers")
                    .define("electrum_armor_powers", true);

            electrumMaxUses = builder
                    .gameRestart()
                    .translation("orevolution.config.electrum_max_uses")
                    .comment("Modifies the max uses of electrum tools and weapons. vanilla is 1561, modded is 1920")
                    .defineInRange("electrum_max_uses", 1920, 1, Integer.MAX_VALUE);

            builder.pop();

            builder.push("copperagebackport");

            copperArmorPowers = builder
                    .gameRestart()
                    .translation("orevolution.config.copper_armor_powers")
                    .define("copper_armor_powers", true);

            copperMaxUses = builder
                    .gameRestart()
                    .translation("orevolution.config.copper_max_uses")
                    .comment("Modifies the max uses of electrum tools and weapons. vanilla is 190, modded is 320")
                    .defineInRange("copper_max_uses", 320, 1, Integer.MAX_VALUE);

            builder.pop();
        }
    }

    public static class Common {
        public final ConfigValue<Boolean> toolsPowers;
        public final ConfigValue<Boolean> weaponsPowers;
        public final ConfigValue<Boolean> armorsPowers;

        public final ConfigValue<Boolean> generateLivingstoneOre;
        public final ConfigValue<Boolean> generateVerditeOre;

        public final ConfigValue<Boolean> generateTinOre;
        public final ConfigValue<Boolean> generateExperienceOre;
        public final ConfigValue<Boolean> generateTungstenOre;
        public final ConfigValue<Boolean> generatePlatOre;
        public final ConfigValue<Boolean> generateLimestone;
        public final ConfigValue<Boolean> generateAethersteelOre;
        public final ConfigValue<Boolean> generateAetherrockMeteor;

        public final ConfigValue<Boolean> safeOreBreaking;

        public final ConfigValue<Boolean> modProgression;

        public final ConfigValue<Boolean> equipmentDurability;
        public final ConfigValue<Boolean> steelEfficiencyNerf;

        private Common(ModConfigSpec.Builder builder) {
            builder.push("powers");

            toolsPowers = builder
                    .gameRestart()
                    .translation("orevolution.config.tools_powers")
                    .comment("Defines if tools will have their special characteristic (aka power), like tin's drop duplication. check client configs to disable the tooltip too")
                    .define("tools_powers", true);
            weaponsPowers = builder
                    .gameRestart()
                    .translation("orevolution.config.weapons_powers")
                    .comment("Defines if weapons will have their special characteristic (aka power), like tin's drop duplication. check client configs to disable the tooltip too")
                    .define("weapons_powers", true);
            armorsPowers = builder
                    .translation("orevolution.config.armors_powers")
                    .gameRestart()
                    .comment("Defines if armors will have their special characteristic (aka power), like platinum's strength II effect. check client configs to disable the tooltip too")
                    .define("armors_powers", true);

            builder.pop();

            builder.push("general");

            generateTinOre = builder
                    .worldRestart()
                    .translation("orevolution.config.generate_tin_ore")
                    .comment("Defines if Tin will be available in your world")
                    .define("generate_tin_ore", false);

            generatePlatOre = builder
                    .worldRestart()
                    .translation("orevolution.config.generate_platinum_ore")
                    .comment("Defines if Platinum will be available in your world")
                    .define("generate_platinum_ore", true);

            generateLimestone = builder
                    .worldRestart()
                    .translation("orevolution.config.generate_limestone")
                    .comment("Defines if Limestone will generate in your world")
                    .define("generate_limestone", true);


            generateTungstenOre = builder
                    .worldRestart()
                    .translation("orevolution.config.generate_tungsten_ore")
                    .comment("Defines if Tungsten will be available in your world")
                    .define("generate_tungsten_ore", true);


            generateExperienceOre = builder
                    .worldRestart()
                    .translation("orevolution.config.generate_experience_ore")
                    .comment("Defines if experience ore will generate in your world")
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
                    .comment("Defines if Aethersteel will be available in your world")
                    .define("generate_aethersteel_ore", true);

            generateAetherrockMeteor = builder
                    .worldRestart()
                    .translation("orevolution.config.generate_aetherrock_meteor")
                    .comment("Defines if aetherrock meteor will generate in your world (this will also disable aethersteel)")
                    .define("generate_aetherrock_meteor", true);

            safeOreBreaking = builder
                    .translation("orevolution.config.safe_ore_breaking")
                    .comment("Ores won't break if mined with the incorrect tool")
                    .define("safe_ore_breaking", true);

            modProgression = builder
                    .translation("orevolution.config.modded_progression")
                    .comment("Replaces the original ore progression of the game")
                    .define("modded_progression", true);

            builder.pop();

            builder.push("other");

            equipmentDurability = builder
                    .gameRestart()
                    .translation("orevolution.config.equipment_durability")
                    .comment("Defines if tools and weapons will have their durability replaced")
                    .define("equipment_durability", true);

            steelEfficiencyNerf = builder
                    .translation("orevolution.config.steel_efficiency_nerf")
                    .comment("Defines if steel tools will increase their durability cost for each mined block per efficiency level")
                    .define("steel_efficiency_nerf", true);
        }
    }

    public static class Client {
        public final ConfigValue<Boolean> warnBreak;
        public final ConfigValue<Boolean> harvestTip;
        public final ConfigValue<Boolean> toolsPowersTip;
        public final ConfigValue<Boolean> weaponsPowersTip;
        public final ConfigValue<Boolean> armorsPowersTip;
        public final ConfigValue<Boolean> tinProgTip;
        public final ConfigValue<Boolean> platProgTip;

        private Client(ModConfigSpec.Builder builder) {
            builder.push("gameplay");

            warnBreak = builder
                    .translation("orevolution.config.equipment_durability")
                    .comment("Display the text 'You can't harvest this block yet!'")
                    .define("warn_break", true);

            builder.pop();


            builder.push("tooltips");

            harvestTip = builder
                    .translation("orevolution.config.equipment_durability")
                    .comment("Display the 'Harvest tier' tooltip on all tiered tools")
                    .define("harvest_tip", true);
            toolsPowersTip = builder
                    .comment("Display the 'power' tooltip on all tools that have one")
                    .define("tools_powers_tip", true);
            weaponsPowersTip = builder
                    .comment("Display the 'power' tooltip on all weapons that have one")
                    .define("weapons_powers_tip", true);
            armorsPowersTip = builder
                    .comment("Display the 'power' tooltip on all armors that have one")
                    .define("armors_powers_tip", true);
            tinProgTip = builder
                    .comment("Display the 'Tin tier' tooltip on all stone-tiered tools")
                    .define("tin_prog_tip", true);
            platProgTip = builder
                    .comment("Display the 'Platinum tier' tooltip on all iron-tiered tools")
                    .define("plat_prog_tip", true);

            builder.pop();
        }
    }

    static {
        final Pair<Common, ModConfigSpec> commonSpecPair = new ModConfigSpec.Builder().configure(Common::new);
        final Pair<Client, ModConfigSpec> clientSpecPair = new ModConfigSpec.Builder().configure(Client::new);
        final Pair<Powers, ModConfigSpec> powerSpecPair = new ModConfigSpec.Builder().configure(Powers::new);
        final Pair<ToolStats, ModConfigSpec> toolStatsSpecPair = new ModConfigSpec.Builder().configure(ToolStats::new);
        final Pair<ModCompat, ModConfigSpec> modCompatSpecPair = new ModConfigSpec.Builder().configure(ModCompat::new);

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
    }
}
