package net.bexla.orevolution.datagen;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.init.FDRegistry;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.bexla.orevolution.init.RegMobEffects;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class GENLangENUS extends LanguageProvider {
    public GENLangENUS(PackOutput output) {
        super(output, Orevolution.MODID, "en_us");
    }

    public void addCondition(String tooltipID, String name) {
        add("conditional.orevolution." + tooltipID, name);
    }

    public void addTooltip(String tooltipID, String name) {
        add("tooltip.orevolution." + tooltipID, name);
    }

    public void addTier(String tooltipID, String name) {
        add("tiers.orevolution." + tooltipID, name);
    }

    public void addSmithingTemplateTips(String nameID, String name, String appliesTo, String ingredients, String baseSlotDescription, String additionsSlotDescription) {
        add("upgrade.orevolution." + nameID + "_upgrade", name);
        add("item.orevolution.smithing_template." + nameID + "_upgrade.applies_to", appliesTo);
        add("item.orevolution.smithing_template." + nameID + "_upgrade.ingredients", ingredients);
        add("item.orevolution.smithing_template." + nameID + "_upgrade.base_slot_description", baseSlotDescription);
        add("item.orevolution.smithing_template." + nameID + "_upgrade.additions_slot_description", additionsSlotDescription);
    }

    @Override
    public void addTranslations() {
        add("orevolution.configuration.gameplay", "Gameplay options");
        add("orevolution.configuration.general", "General options");
        add("orevolution.configuration.other", "General options");

        add("orevolution.configuration.oreganized", "Oreganized options");
        add("orevolution.configuration.copperagebackport", "Copper Age Backport options");
        add("orevolution.configuration.tooltips", "Tooltip options");

        add("orevolution.configuration.weapons", "Weapon options");
        add("orevolution.configuration.durability", "Durability options");
        add("orevolution.configuration.tools", "Tool options");
        add("orevolution.configuration.powers", "Power options");
        add("orevolution.configuration.armors", "Armor options");

        add("orevolution.config.verdite_armor_powers", "Verdite armor powers");
        add("orevolution.config.bronze_armor_powers", "Bronze armor powers");
        add("orevolution.config.platinum_armor_powers", "Platinum armor powers");
        add("orevolution.config.diamond_armor_powers", "Diamond armor powers");
        add("orevolution.config.iron_armor_powers", "Iron armor powers");
        add("orevolution.config.aethersteel_armor_powers", "Aethersteel armor powers");
        add("orevolution.config.netherite_armor_powers", "Netherite armor powers");
        add("orevolution.config.tungsten_armor_powers", "Tungsten armor powers");
        add("orevolution.config.copper_armor_powers", "Copper armor powers");
        add("orevolution.config.reinforced_netherite_armor_powers", "Reinforced Netherite armor powers");
        add("orevolution.config.electrum_armor_powers", "Electrum armor powers");
        add("orevolution.config.gold_armor_powers", "Gold armor powers");
        add("orevolution.config.Livingstone_armor_powers", "Livingstone armor powers");

        add("orevolution.config.verdite_tool_powers", "Verdite tool powers");
        add("orevolution.config.bronze_tool_powers", "Bronze tool powers");
        add("orevolution.config.platinum_tool_powers", "Platinum tool powers");
        add("orevolution.config.diamond_tool_powers", "Diamond tool powers");
        add("orevolution.config.iron_tool_powers", "Iron tool powers");
        add("orevolution.config.steel_tool_powers", "Steel tool powers");
        add("orevolution.config.aethersteel_tool_powers", "Aethersteel tool powers");
        add("orevolution.config.netherite_tool_powers", "Netherite tool powers");
        add("orevolution.config.tin_tool_powers", "Tungsten tool powers");
        add("orevolution.config.copper_tool_powers", "Copper tool powers");
        add("orevolution.config.electrum_tool_powers", "Electrum tool powers");
        add("orevolution.config.gold_tool_powers", "Gold tool powers");
        add("orevolution.config.Livingstone_tool_powers", "Livingstone tool powers");

        add("orevolution.config.verdite_weapon_powers", "Verdite weapon powers");
        add("orevolution.config.iron_weapon_powers", "Iron weapon powers");
        add("orevolution.config.steel_weapon_powers", "Steel weapon powers");
        add("orevolution.config.bronze_weapon_powers", "Bronze weapon powers");
        add("orevolution.config.platinum_weapon_powers", "Platinum weapon powers");
        add("orevolution.config.diamond_weapon_powers", "Diamond weapon powers");
        add("orevolution.config.aethersteel_weapon_powers", "Aethersteel weapon powers");
        add("orevolution.config.netherite_weapon_powers", "Netherite weapon powers");
        add("orevolution.config.tin_weapon_powers", "Tungsten weapon powers");
        add("orevolution.config.copper_weapon_powers", "Copper weapon powers");
        add("orevolution.config.electrum_weapon_powers", "Electrum weapon powers");
        add("orevolution.config.gold_weapon_powers", "Gold weapon powers");
        add("orevolution.config.livingstone_weapon_powers", "Livingstone weapon powers");

        add("orevolution.config.verdite_max_uses", "Verdite max uses");
        add("orevolution.config.iron_max_uses", "Iron max uses");
        add("orevolution.config.steel_max_uses", "Steel max uses");
        add("orevolution.config.bronze_max_uses", "Bronze max uses");
        add("orevolution.config.platinum_max_uses", "Platinum max uses");
        add("orevolution.config.diamond_max_uses", "Diamond max uses");
        add("orevolution.config.aethersteel_max_uses", "Aethersteel max uses");
        add("orevolution.config.netherite_max_uses", "Netherite max uses");
        add("orevolution.config.tin_max_uses", "Tungsten max uses");
        add("orevolution.config.copper_max_uses", "Copper max uses");
        add("orevolution.config.electrum_max_uses", "Electrum max uses");
        add("orevolution.config.gold_max_uses", "Gold max uses");
        add("orevolution.config.livingstone_max_uses", "Livingstone max uses");

        add("orevolution.config.generate_tin_ore", "Enable Tin Ore");
        add("orevolution.config.generate_platinum_ore", "Enable Platinum Ore");
        add("orevolution.config.generate_limestone", "Enable Limestone");
        add("orevolution.config.generate_tungsten_ore", "Enable Tungsten Ore");
        add("orevolution.config.generate_experience_ore", "Enable Experience Ore(s)");
        add("orevolution.config.generate_livingstone_ore", "Enable Livingstone");
        add("orevolution.config.generate_verdite_ore", "Enable Verdite");
        add("orevolution.config.generate_aethersteel_ore", "Enable Aethersteel");
        add("orevolution.config.generate_aetherrock_meteor", "Enable Aetherrock Meteor(s)");
        add("orevolution.config.safe_ore_breaking", "Enable Safe Ore Breaking");
        add("orevolution.config.modded_progression", "Enable Modded Progression");

        add("orevolution.configuration.section.orevolution.toolstats.toml", "Tool Stat settings");
        add("orevolution.configuration.section.orevolution.toolstats.toml.title", "Tool Stat settings");

        add("orevolution.configuration.section.orevolution.powers.toml", "Power settings");
        add("orevolution.configuration.section.orevolution.powers.toml.title", "Power settings");

        add("orevolution.configuration.section.orevolution.modcompat.toml", "Mod Compat settings");
        add("orevolution.configuration.section.orevolution.modcompat.toml.title", "Mod Compat settings");

        add("orevolution.itemgroup.orevolution", "Orevolution!");

        addTooltip("regenerates_daylight", "Heals it's durability every %s second(s) while receiving daylight");

        addTooltip("duplication", "Has a chance (Max of %s) to duplicate most block drops");
        addTooltip("triplication", "Has a chance (Max of %s) to triplicate most block drops");

        addTooltip("duplication_explanation",
                "List of chances depending on block types:\n" +
                        " - Always duplicated (eg. Oak Leaves) -> 100% chance\n" +
                        " - Uncommon blocks (eg. Anvil) -> half of Max\n" +
                        " - Any Ore (eg. Coal Ore) -> fifth of Max\n" +
                        " - Rare blocks (eg. Iron Block) -> tenth of Max\n" +
                        " - 'Never' blocks (eg. Aethersteel Block) -> 0% chance"
        );

        addTooltip("autosmelt_explanation",
                "This effect is disabled while Crouching");

        addTooltip("aethersteel_autosmelt_explanation",
                "Autosmelt is disabled while Crouching\n" +
                        "List of chances depending on block types:\n" +
                        " - Always duplicated (eg. Oak Leaves) -> 100% chance\n" +
                        " - Uncommon blocks (eg. Anvil) -> half of Max\n" +
                        " - Any Ore (eg. Coal Ore) -> fifth of Max\n" +
                        " - Rare blocks (eg. Iron Block) -> tenth of Max\n" +
                        " - 'Never' blocks (eg. Aethersteel Block) -> 0% chance"
        );

        addTooltip("duplication_crops", "Has a %s chance to duplicate crops");
        addTooltip("triplication_crops", "Has a %s chance to triplicate crops");

        addTooltip("on_hit_effect", "Inflicts the following effect(s) to targeted mobs:");
        addTooltip("attacker_on_hit_effect", "Grants the following effect(s) to you when attacking mobs:");

        addTooltip("on_hit_effect_chance", "Has a chance to inflict the following effect(s) to targeted mobs:");
        addTooltip("attacker_on_hit_effect_chance", "Has a chance to grant the following effect(s) to you when attacking:");

        addTooltip("undead_on_hit", "Has a chance to inflict the following effect(s) to targeted undead mobs:");

        addTooltip("xp_duplicate", "Blocks give twice the amount of Experience Points");
        addTooltip("xp_looting", "Mobs give %s Experience Points after dying");

        addTooltip("avoid_damage_chance", "Has a chance to avoid durability damage on use");

        addTooltip("durability_speed", "Mining speed increases depending on the tool's durability");
        addTooltip("on_hit_effect_armored", "Damage to Armored targets increases by %s");

        addTooltip("electrum", "Inflicts kinetic damage when attacking while moving. The more momentum you have, the more damage you'll deal");
        addTooltip("durability_speed_explanation", "Durability is treated as a percentage for mining speed \nThe lower it is, the higher mining speed you'll have");

        addTooltip("full_set_bonus", "Full set bonus:");

        addTooltip("armor_wearer_grants", "Grants the following effect(s):");
        addTooltip("armor_wearer_grants_daylight", "Grants the following effect(s) while receiving daylight:");
        addTooltip("armor_wearer_grants_on_hit_wearer_daylight", "Grants the following effect(s) to you when attacking and receiving daylight:");
        addTooltip("armor_wearer_on_attacked_wearer", "Grants the following effect(s) to you when you receive damage:");
        addTooltip("armor_wearer_on_attacked_target", "Causes the following effect(s) to the attacker when you receive damage:");

        addTooltip("armor_wearer_on_hit_wearer", "Grants the following effect(s) to you when attacking:");
        addTooltip("armor_wearer_on_hit_target", "Causes the following effect(s) to the target when attacking:");

        addTooltip("armor_immunity", "Grants immunity to the following effect(s):");
        addTooltip("armor_immunity_daylight", "Grants immunity to the following effect(s) when receiving daylight:");

        addTooltip("armor_extended_pickup", "Increases item Pick-Up range by %s blocks");
        addTooltip("copper_armor", "Increases Block placement range by %s blocks");

        addTooltip("netherite_armor", "When your health is below 50%, inflicts the following effect(s) on hit:");
        addTooltip("reinforced_netherite_armor", "While not submerged in lava, grants the following effect(s):");
        addTooltip("iron_armor", "Has a 25% chance to ignore arrow damage");
        addTooltip("diamond_armor", "Reduces damage from falling, explosions and falling blocks by %s");
        addTooltip("bronze_armor", "While not submerged in water, grants the following effect(s):");
        addTooltip("tungsten_armor", "While not submerged in lava, grants the following effect(s):");

        addTooltip("electrum_armor", "After traveling a certain amount of blocks without receiving damage, grants:");
        addTooltip("electrum_armor_explanation",
                "Jumping or flying doesn't increase the traveled distance\n" +
                        "List of required distance:\n" +
                        " - 50 blocks -> Speed I\n" +
                        " - 90 blocks -> Speed II\n" +
                        " - 160 blocks -> Speed III"
        );

        addTooltip("necromium_armor", "Allows you to come back from the death. \nEach time you die, a 5 minute cooldown is applied");

        addTooltip("grant_on_mine", "Each %s amount of blocks mined, grants and increases the following effect(s):");

        addTooltip("tool_cause_effect_on_hits", "Each %s amount of hits, causes the following effect(s) to the target:");
        addTooltip("tool_grant_effect_on_hits", "Each %s amount of hits, grants the following effect(s):");

        addTooltip("autosmelt", "Automatically smelts most ores, sands, logs and crops");
        addTooltip("fire_on_hit", "Sets targeted mobs on fire for %s seconds");

        addTooltip("aethersteel", "After dying, returns to your inventory");

        addTooltip("multi_break", "Breaks blocks in a 3x3 area");
        addTooltip("multi_break_explanation",
                "Each block you mine will reduce durability by 1\n" +
                        "Breaking 9 blocks results in losing 9 points of durability\n" +
                        "Loses 4 extra points of durability per efficiency level\n" +
                        "Breaking 9 blocks with efficiency I results in losing 36 points of durability"
        );

        addTooltip("steel_durability", "Has infinite Durability at the cost of less Damage and Attack Speed");
        addTooltip("steel_scythe",
                "Tills blocks in a 3x3 area\n" +
                        "Has a chance to inflict the following effect(s) to targeted mobs:");

        add("item.orevolution.bronze_radar.tooltip",
                "Right-click while Crouching to change between friends/personal mode\n" +
                        "While on friends mode, Right-click to change shown player"
        );

        add("item.orevolution.totem_diamond", "(Diamond) - (%s)");
        add("item.orevolution.totem_emerald", "(Emerald) - (%s)");
        add("item.orevolution.totem_lapis_lazuli", "(Lapis Lazuli) - (%s)");

        add("actionbar.orevolution.cant_harvest_ore", "You can't harvest this block yet");

        add("actionbar.orevolution.bronze_radar_normal_mode", "Current position: %s");
        add("actionbar.orevolution.bronze_radar_friends_mode", "Current position of %s");

        add("trim_material.orevolution.platinum", "Platinum Material");
        add("trim_material.orevolution.tin", "Tin Material");
        add("trim_material.orevolution.tungsten", "Tungsten Material");

        addTooltip("press_key", "Hold %s to view more information");

        addTooltip("harvest_tier", "Mining tier:");
        addTier("wood", "Stone");
        addTier("stone", "Tin");
        addTier("tin", "Iron");
        addTier("iron", "Platinum");
        addTier("platinum", "Diamond");
        addTier("diamond", "Netherite");
        addTier("netherite", "Aethersteel");
        addTier("aethersteel", "Aethersteel");

        addEffect(RegMobEffects.PETRIFIED, "Petrified");
//        addEffect(RegMobEffects.WEAK_SOUL, "Weakened Soul");

        addBlock(RegBlocks.TUNGSTEN_SPONGE, "Tungsten Sponge");
        addBlock(RegBlocks.HOT_TUNGSTEN_SPONGE, "Hot Tungsten Sponge");

        addItem(RegItems.BRONZE_TOTEM, "Bronze Totem");
        addItem(RegItems.BRONZE_TOTEM_EMERALD, "Bronze Totem");
        addItem(RegItems.BRONZE_TOTEM_DIAMOND, "Bronze Totem");
        addItem(RegItems.BRONZE_TOTEM_LAPIS_LAZULI, "Bronze Totem");
        addItem(RegItems.BRONZE_RADAR, "Radar");

        addItem(RegItems.DEAD_SEED, "Dead Seed");
        addBlock(RegBlocks.VERDITE_CROP, "Verdite Crop");

        addBlock(RegBlocks.LIVINGSTONE_BLOCK, "Livingstone Block");
        addItem(RegItems.PETRIFIED_SEED, "Petrified Seed");
        addBlock(RegBlocks.LIVINGSTONE_CROP, "Livingstone Crop");

//        addItem(RegItems.AMBER_SEED, "Amber Seed");
//        addBlock(RegBlocks.AMBER_CROP, "Amber Crop");
//        addBlock(RegBlocks.AMBER_BLOCK, "Amber Block");
//        addItem(RegItems.AMBER, "Amber");
//        addItem(RegItems.ROUGH_AMBER, "Rough Amber");

        addBlock(RegBlocks.LIMESTONE, "Limestone");
        addBlock(RegBlocks.LIMESTONE_PILLAR, "Limestone Pillar");
        addBlock(RegBlocks.POLISHED_LIMESTONE, "Polished Limestone");

//        addItem(RegItems.CRUSHED_TUNGSTEN, "Crushed Raw Tungsten");
//        addItem(RegItems.CRUSHED_AETHERSTEEL, "Crushed Raw Aethersteel");

//        addItem(RegItems.PLATINUM_SHIELD, "Platinum Shield");
        addItem(FDRegistry.PLATINUM_KNIFE, "Platinum Knife");
        addItem(RegItems.PLATINUM_SWORD, "Platinum Sword");
        addItem(RegItems.PLATINUM_SHOVEL, "Platinum Shovel");
        addItem(RegItems.PLATINUM_PICKAXE, "Platinum Pickaxe");
        addItem(RegItems.PLATINUM_AXE, "Platinum Axe");
        addItem(RegItems.PLATINUM_HOE, "Platinum Hoe");
        addItem(RegItems.PLATINUM_HELMET, "Platinum Helmet");
        addItem(RegItems.PLATINUM_CHESTPLATE, "Platinum Chestplate");
        addItem(RegItems.PLATINUM_LEGGINGS, "Platinum Leggings");
        addItem(RegItems.PLATINUM_BOOTS, "Platinum Boots");

//        addItem(RegItems.TIN_SHIELD, "Tin Shield");
        addItem(FDRegistry.TIN_KNIFE, "Tin Knife");
        addItem(RegItems.TIN_SWORD, "Tin Sword");
        addItem(RegItems.TIN_SHOVEL, "Tin Shovel");
        addItem(RegItems.TIN_PICKAXE, "Tin Pickaxe");
        addItem(RegItems.TIN_AXE, "Tin Axe");
        addItem(RegItems.TIN_HOE, "Tin Hoe");

//        addItem(RegItems.AETHERSTEEL_SHIELD, "Aethersteel Shield");
        addItem(FDRegistry.AETHERSTEEL_KNIFE, "Aethersteel Knife");
        addItem(RegItems.AETHERSTEEL_SWORD, "Aethersteel Sword");
        addItem(RegItems.AETHERSTEEL_SHOVEL, "Aethersteel Shovel");
        addItem(RegItems.AETHERSTEEL_PICKAXE, "Aethersteel Pickaxe");
        addItem(RegItems.AETHERSTEEL_AXE, "Aethersteel Axe");
        addItem(RegItems.AETHERSTEEL_HOE, "Aethersteel Hoe");
        addItem(RegItems.AETHERSTEEL_HELMET, "Aethersteel Helmet");
        addItem(RegItems.AETHERSTEEL_CHESTPLATE, "Aethersteel Chestplate");
        addItem(RegItems.AETHERSTEEL_LEGGINGS, "Aethersteel Leggings");
        addItem(RegItems.AETHERSTEEL_BOOTS, "Aethersteel Boots");

        addItem(RegItems.REINFORCED_NETHERITE_HELMET, "Reinforced Netherite Helmet");
        addItem(RegItems.REINFORCED_NETHERITE_CHESTPLATE, "Reinforced Netherite Chestplate");
        addItem(RegItems.REINFORCED_NETHERITE_LEGGINGS, "Reinforced Netherite Leggings");
        addItem(RegItems.REINFORCED_NETHERITE_BOOTS, "Reinforced Netherite Boots");

//        addItem(RegItems.LIVINGSTONE_SHIELD, "Livingstone Shield");
        addItem(FDRegistry.LIVINGSTONE_KNIFE, "Livingstone Knife");
        addItem(RegItems.LIVINGSTONE_SWORD, "Livingstone Sword");
        addItem(RegItems.LIVINGSTONE_SHOVEL, "Livingstone Shovel");
        addItem(RegItems.LIVINGSTONE_PICKAXE, "Livingstone Pickaxe");
        addItem(RegItems.LIVINGSTONE_AXE, "Livingstone Axe");
        addItem(RegItems.LIVINGSTONE_HOE, "Livingstone Hoe");
        addItem(RegItems.LIVINGSTONE_HELMET, "Livingstone Helmet");
        addItem(RegItems.LIVINGSTONE_CHESTPLATE, "Livingstone Chestplate");
        addItem(RegItems.LIVINGSTONE_LEGGINGS, "Livingstone Leggings");
        addItem(RegItems.LIVINGSTONE_BOOTS, "Livingstone Boots");

//        addItem(RegItems.VERDITE_SHIELD, "Verdite Shield");
        addItem(FDRegistry.VERDITE_KNIFE, "Verdite Knife");
        addItem(RegItems.VERDITE_SWORD, "Verdite Sword");
        addItem(RegItems.VERDITE_SHOVEL, "Verdite Shovel");
        addItem(RegItems.VERDITE_PICKAXE, "Verdite Pickaxe");
        addItem(RegItems.VERDITE_AXE, "Verdite Axe");
        addItem(RegItems.VERDITE_HOE, "Verdite Hoe");
        addItem(RegItems.VERDITE_HELMET, "Verdite Helmet");
        addItem(RegItems.VERDITE_CHESTPLATE, "Verdite Chestplate");
        addItem(RegItems.VERDITE_LEGGINGS, "Verdite Leggings");
        addItem(RegItems.VERDITE_BOOTS, "Verdite Boots");

        addItem(RegItems.STEEL_DIGGER, "Steel Digger");
        addItem(RegItems.STEEL_HAMMER, "Steel Hammer");
        addItem(RegItems.STEEL_SCYTHE, "Steel Scythe");
        addItem(RegItems.STEEL_BROADAXE, "Steel Broadaxe");
        addBlock(RegBlocks.STEEL_ANVIL, "Heavy Anvil");

        addItem(RegItems.BRONZE_HELMET, "Bronze Diving Mask");
        addItem(RegItems.BRONZE_CHESTPLATE, "Bronze Diving Chestplate");
        addItem(RegItems.BRONZE_LEGGINGS, "Bronze Diving Leggings");
        addItem(RegItems.BRONZE_BOOTS, "Bronze Diving Boots");

        addItem(RegItems.TUNGSTEN_HELMET, "Tungsten Diving Mask");
        addItem(RegItems.TUNGSTEN_CHESTPLATE, "Tungsten Diving Chestplate");
        addItem(RegItems.TUNGSTEN_LEGGINGS, "Tungsten Diving Leggings");
        addItem(RegItems.TUNGSTEN_BOOTS, "Tungsten Diving Boots");

        addItem(RegItems.TIN_INGOT, "Tin Ingot");
        addItem(RegItems.RAW_TIN, "Raw Tin");
        addBlock(RegBlocks.TIN_BLOCK, "Block of Tin");
        addBlock(RegBlocks.RAW_TIN_BLOCK, "Block of Raw Tin");

        addItem(RegItems.PLATINUM_INGOT, "Platinum Ingot");
        addItem(RegItems.RAW_PLATINUM, "Raw Platinum");
        addBlock(RegBlocks.PLATINUM_BLOCK, "Block of Platinum");
        addBlock(RegBlocks.RAW_PLATINUM_BLOCK, "Block of Raw Platinum");

        addItem(RegItems.TUNGSTEN_INGOT, "Tungsten Ingot");
        addItem(RegItems.RAW_TUNGSTEN, "Raw Tungsten");
        addBlock(RegBlocks.TUNGSTEN_BLOCK, "Block of Tungsten");
        addBlock(RegBlocks.RAW_TUNGSTEN_BLOCK, "Block of Raw Tungsten");

        addItem(RegItems.BRONZE_ALLOY, "Bronze Alloy");
        addBlock(RegBlocks.BRONZE_BLOCK, "Block of Bronze");

        addItem(RegItems.STEEL_ALLOY, "Steel Alloy");
        addBlock(RegBlocks.STEEL_BLOCK, "Block of Steel");

        addItem(RegItems.VERDITE_INGOT, "Verdite Ingot");
        addBlock(RegBlocks.VERDITE_BLOCK, "Verdite Block");

        addItem(RegItems.AETHERSTEEL_INGOT, "Aethersteel Ingot");
        addItem(RegItems.AETHERSTEEL_CHUNK, "Aethersteel Chunk");
        addBlock(RegBlocks.AETHERSTEEL_BLOCK, "Block of Aethersteel");

        addSmithingTemplateTips("basic", "Basic Upgrade", "Iron Equipment", "Steel Alloy", "Add Iron armor, weapon, or tool", "Add Steel Alloy");
        addSmithingTemplateTips("tungsten", "Reinforcement Upgrade", "Netherite Equipment", "Tungsten Ingot", "Add Netherite armor, weapon, or tool", "Add Tungsten Ingot");
        addSmithingTemplateTips("aethersteel", "Aether Upgrade", "Netherite and Reinforced Netherite Equipment", "Aethersteel Ingot", "Add Reinforced Netherite armor, netherite weapon, or tool", "Add Aethersteel Ingot");

        addItem(RegItems.TIN_NUGGET, "Tin Nugget");
        addItem(RegItems.PLATINUM_NUGGET, "Platinum Nugget");
        addItem(RegItems.TUNGSTEN_NUGGET, "Tungsten Nugget");
        addItem(RegItems.VERDITE_NUGGET, "Verdite Nugget");
        addItem(RegItems.LIVINGSTONE_SHARD, "Livingstone Shard");

        addItem(RegItems.VERDITE_APPLE, "Verdite Apple");
        addItem(RegItems.VERDITE_SPIDER_EYE, "Verdite Spider Eye");

        addItem(RegItems.PLATINUM_BERRIES, "Platinum Berries");

        addBlock(RegBlocks.STEEL_BARS, "Steel Bars");
        addBlock(RegBlocks.VERDITE_BRICKS, "Verdite Block");
        addBlock(RegBlocks.LIVINGSTONE_BRICKS, "Livingstone Bricks");
        addBlock(RegBlocks.TIN_ORE, "Tin Ore");
        addBlock(RegBlocks.DEEPSLATE_TIN_ORE, "Deepslate Tin Ore");
        addBlock(RegBlocks.PLATINUM_ORE, "Platinum Ore");
        addBlock(RegBlocks.DEEPSLATE_PLATINUM_ORE, "Deepslate Platinum Ore");
        addBlock(RegBlocks.NETHER_TUNGSTEN_ORE, "Nether Tungsten Ore");
        addBlock(RegBlocks.NETHER_XP_ORE, "Nether Experience Ore");
        addBlock(RegBlocks.END_XP_ORE, "End Experience Ore");
        addBlock(RegBlocks.AETHERROCK, "Aetherrock");
        addBlock(RegBlocks.AETHERROCK_TILES, "Aetherrock Tiles");
        addBlock(RegBlocks.POLISHED_AETHERROCK, "Polished Aetherrock");
        addBlock(RegBlocks.POLISHED_TUNGSTEN, "Polished Tungsten Block");
        addBlock(RegBlocks.AETHERROCK_BRICKS, "Aetherrock Bricks");
        addBlock(RegBlocks.CRACKED_AETHERROCK_BRICKS, "Cracked Aetherrock Bricks");
        addBlock(RegBlocks.PRIMITIVE_AETHERROCK, "Primitive Aetherrock");
        addBlock(RegBlocks.CUT_STEEL_BLOCK, "Cut Steel Block");
        addBlock(RegBlocks.CUT_TUNGSTEN_BLOCK, "Cut Tungsten Block");
        addBlock(RegBlocks.BRONZE_TILES, "Bronze Tiles");
        addBlock(RegBlocks.STEEL_PILLAR, "Steel Pillar");
        addBlock(RegBlocks.STEEL_DOOR, "Steel Door");
        addBlock(RegBlocks.STEEL_TRAPDOOR, "Steel Trapdoor");
        addBlock(RegBlocks.CHISELED_TUNGSTEN_BLOCK, "Chiseled Tungsten Block");
        addBlock(RegBlocks.CHISELED_TUNGSTEN_BRICKS, "Chiseled Tungsten Bricks");
        addBlock(RegBlocks.TUNGSTEN_BRICKS, "Tungsten Bricks");
        addBlock(RegBlocks.TIN_BRICKS, "Tin Bricks");

        addItem(RegItems.CRUSHED_TUNGSTEN, "Crushed Raw Tungsten");
        addItem(RegItems.CRUSHED_AETHERSTEEL, "Crushed Raw Aethersteel");

        addBlock(RegBlocks.TIN_TILES, "Tin Tiles");
        addBlock(RegBlocks.PLATINUM_TILES, "Platinum Tiles");
        addBlock(RegBlocks.GOLD_TILES, "Gold Tiles");
        addBlock(RegBlocks.PLATINUM_PILLAR, "Platinum Pillar");
        addBlock(RegBlocks.GOLD_PILLAR, "Gold Pillar");
        addBlock(RegBlocks.BRONZE_BARS, "Bronze Bars");
        addBlock(RegBlocks.GOLD_BARS, "Gold Bars");
        addBlock(RegBlocks.TUNGSTEN_BARS, "Tungsten Bars");
        addBlock(RegBlocks.TIN_BARS, "Tin Bars");
        addBlock(RegBlocks.PLATINUM_BARS, "Platinum Bars");

        addBlock(RegBlocks.POLISHED_AETHERROCK_WALL, "Polished Aetherrock Wall");
        addBlock(RegBlocks.POLISHED_AETHERROCK_STAIR, "Polished Aetherrock Stair");
        addBlock(RegBlocks.POLISHED_AETHERROCK_SLAB, "Polished Aetherrock Slab");

//        addItem(RegItemsAE.TIN_ARROW, "Tin-Coated Arrow");
//        addItem(RegItemsAE.PLATINUM_ARROW, "Platinum-Coated Arrow");
//        addItem(RegItemsAE.AETHERSTEEL_ARROW, "Aethersteel-Coated Arrow");
//        addItem(RegItemsAE.TIN_BOW, "Tin-Reinforced Bow");
//        addItem(RegItemsAE.PLATINUM_BOW, "Platinum-Reinforced Bow");
//        addItem(RegItemsAE.AETHERSTEEL_BOW, "Aethersteel-Reinforced Bow");
//
//        addEntityType(RegEntityTypesAE.TIN_ARROW, "Tin-Coated Arrow");
//        addEntityType(RegEntityTypesAE.PLATINUM_ARROW, "Platinum-Coated Arrow");
//        addEntityType(RegEntityTypesAE.AETHERSTEEL_ARROW, "Aethersteel-Coated Arrow");
    }
}
