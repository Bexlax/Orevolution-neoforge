package net.bexla.orevolution.datagen;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.bexla.orevolution.init.RegMobEffects;
import net.bexla.orevolution.init.modcompat.FDRegistry;
import net.bexla.orevolution.init.modcompat.SBRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Supplier;

public class GENLangENUS extends LanguageProvider {
    public GENLangENUS(PackOutput output) {
        super(output, Orevolution.MODID, "en_us");
    }

    public void addAdvTitle(String advancementTitle, String name) {
        add("advancements.orevolution." + advancementTitle + ".title", name);
    }

    public void addAdvDesc(String advancementTitle, String name) {
        add("advancements.orevolution." + advancementTitle + ".description", name);
    }

    public void addCondition(String tooltipID, String name) {
        add("condition.orevolution." + tooltipID, name);
    }

    public void addPower(String tooltipID, String name) {
        add("power.orevolution." + tooltipID, name);
    }

    public void colors(String id, String name) {
        add("item.orevolution." + id, name);
        for(DyeColor color : DyeColor.values()) {
            add("item.orevolution." + id + "." + color.getName(), StringUtils.capitalize(color.getName()) + " " + name);
        }
    }

    public void addSmithingTemplateTips(Supplier<Item> item, String nameID, String name, String appliesTo, String ingredients, String baseSlotDescription, String additionsSlotDescription) {
        add("upgrade.orevolution." + nameID + "_upgrade", name);
        addItem(item, "Smithing Template");
        add("item.orevolution.smithing_template." + nameID + "_upgrade.applies_to", appliesTo);
        add("item.orevolution.smithing_template." + nameID + "_upgrade.ingredients", ingredients);
        add("item.orevolution.smithing_template." + nameID + "_upgrade.base_slot_description", baseSlotDescription);
        add("item.orevolution.smithing_template." + nameID + "_upgrade.additions_slot_description", additionsSlotDescription);
    }

    public void addEffect(Supplier<? extends MobEffect> key, String potionkey, String name) {
        add(key.get(), name);
        addPotions(potionkey, name);
    }

    public void addPotions(String key, String name) {
        add("item.minecraft.potion.effect." + key, "Potion of " + name);
        add("item.minecraft.splash_potion.effect." + key, "Splash Potion of " + name);
        add("item.minecraft.lingering_potion.effect." + key, "Lingering Potion of " + name);

        add("item.minecraft.potion.effect." + key + "_long", "Potion of " + name);
        add("item.minecraft.splash_potion.effect." + key + "_long", "Splash Potion of " + name);
        add("item.minecraft.lingering_potion.effect." + key + "_long", "Lingering Potion of " + name);

        add("item.minecraft.potion.effect." + key + "_strong", "Potion of " + name);
        add("item.minecraft.splash_potion.effect." + key + "_strong", "Splash Potion of " + name);
        add("item.minecraft.lingering_potion.effect." + key + "_strong", "Lingering Potion of " + name);

        add("item.minecraft.tipped_arrow.effect." + key, "Arrow of " + name);
        add("item.minecraft.tipped_arrow.effect." + key + "_strong", "Arrow of " + name);
        add("item.minecraft.tipped_arrow.effect." + key + "_long", "Arrow of " + name);
    }

    @Override
    public void addTranslations() {
        add("orevolution.configuration.gameplay", "Gameplay options");
        add("orevolution.configuration.general", "General options");
        add("orevolution.configuration.worldgen", "World Generation options");
        add("orevolution.configuration.data", "Global Data options");
        add("orevolution.configuration.other", "Other options");

        add("orevolution.configuration.oreganized", "Oreganized options");
        add("orevolution.configuration.copperagebackport", "Copper Age Backport options");
        add("orevolution.configuration.tooltips", "Tooltip options");

        add("orevolution.configuration.weapons", "Weapon options");
        add("orevolution.configuration.durability", "Durability options");
        add("orevolution.configuration.tools", "Tool options");
        add("orevolution.configuration.powers", "Power options");
        add("orevolution.configuration.armors", "Armor options");

        add("orevolution.config.weapons_powers", "Enable Powers for Weapons");
        add("orevolution.config.tools_powers", "Enable Powers for Tools");
        add("orevolution.config.armors_powers", "Enable powers for Armor-sets");

        add("orevolution.config.verdite_armor_powers", "Verdite armor powers");
        add("orevolution.config.bronze_armor_powers", "Bronze armor powers");
        add("orevolution.config.platinum_armor_powers", "Platinum armor powers");
        add("orevolution.config.diamond_armor_powers", "Diamond armor powers");
        add("orevolution.config.moonstone_armor_powers", "Moonstone armor powers");
        add("orevolution.config.iron_armor_powers", "Iron armor powers");
        add("orevolution.config.aethersteel_armor_powers", "Aethersteel armor powers");
        add("orevolution.config.netherite_armor_powers", "Netherite armor powers");
        add("orevolution.config.tungsten_armor_powers", "Tungsten armor powers");
        add("orevolution.config.copper_armor_powers", "Copper armor powers");
        add("orevolution.config.reinforced_netherite_armor_powers", "Reinforced Netherite armor powers");
        add("orevolution.config.electrum_armor_powers", "Electrum armor powers");
        add("orevolution.config.gold_armor_powers", "Gold armor powers");
        add("orevolution.config.livingstone_armor_powers", "Livingstone armor powers");

        add("orevolution.config.verdite_tool_powers", "Verdite tool powers");
        add("orevolution.config.bronze_tool_powers", "Bronze tool powers");
        add("orevolution.config.platinum_tool_powers", "Platinum tool powers");
        add("orevolution.config.diamond_tool_powers", "Diamond tool powers");
        add("orevolution.config.moonstone_tool_powers", "Moonstone tool powers");
        add("orevolution.config.iron_tool_powers", "Iron tool powers");
        add("orevolution.config.steel_tool_powers", "Steel tool powers");
        add("orevolution.config.aethersteel_tool_powers", "Aethersteel tool powers");
        add("orevolution.config.netherite_tool_powers", "Netherite tool powers");
        add("orevolution.config.tin_tool_powers", "Tin tool powers");
        add("orevolution.config.copper_tool_powers", "Copper tool powers");
        add("orevolution.config.electrum_tool_powers", "Electrum tool powers");
        add("orevolution.config.gold_tool_powers", "Gold tool powers");
        add("orevolution.config.livingstone_tool_powers", "Livingstone tool powers");

        add("orevolution.config.verdite_weapon_powers", "Verdite weapon powers");
        add("orevolution.config.iron_weapon_powers", "Iron weapon powers");
        add("orevolution.config.steel_weapon_powers", "Steel weapon powers");
        add("orevolution.config.bronze_weapon_powers", "Bronze weapon powers");
        add("orevolution.config.platinum_weapon_powers", "Platinum weapon powers");
        add("orevolution.config.diamond_weapon_powers", "Diamond weapon powers");
        add("orevolution.config.moonstone_weapon_powers", "Moonstone weapon powers");
        add("orevolution.config.aethersteel_weapon_powers", "Aethersteel weapon powers");
        add("orevolution.config.netherite_weapon_powers", "Netherite weapon powers");
        add("orevolution.config.tin_weapon_powers", "Tin weapon powers");
        add("orevolution.config.copper_weapon_powers", "Copper weapon powers");
        add("orevolution.config.electrum_weapon_powers", "Electrum weapon powers");
        add("orevolution.config.gold_weapon_powers", "Gold weapon powers");
        add("orevolution.config.livingstone_weapon_powers", "Livingstone weapon powers");

        add("orevolution.config.electrum_kinetic_damage", "Enable Kinetic Damage for Electrum");
        add("orevolution.config.electrum_speed_per_armor_piece", "Enable Speed boost per Electrum Armor piece");

        add("orevolution.config.wood_max_uses", "Verdite max uses");
        add("orevolution.config.stone_max_uses", "Verdite max uses");
        add("orevolution.config.verdite_max_uses", "Verdite max uses");
        add("orevolution.config.iron_max_uses", "Iron max uses");
        add("orevolution.config.steel_max_uses", "Steel max uses");
        add("orevolution.config.bronze_max_uses", "Bronze max uses");
        add("orevolution.config.platinum_max_uses", "Platinum max uses");
        add("orevolution.config.diamond_max_uses", "Diamond max uses");
        add("orevolution.config.moonstone_max_uses", "Moonstone max uses");
        add("orevolution.config.aethersteel_max_uses", "Aethersteel max uses");
        add("orevolution.config.netherite_max_uses", "Netherite max uses");
        add("orevolution.config.tin_max_uses", "Tungsten max uses");
        add("orevolution.config.copper_max_uses", "Copper max uses");
        add("orevolution.config.electrum_max_uses", "Electrum max uses");
        add("orevolution.config.gold_max_uses", "Gold max uses");
        add("orevolution.config.livingstone_max_uses", "Livingstone max uses");

        add("orevolution.config.prioritize_terrablender_over_biolith", "Prioritize TerraBlender over Biolith for Biome Gen.");
        add("orevolution.config.generate_forgotten_cave_biome", "Enable Forgotten Cave Biomes");

        add("orevolution.config.generate_tin_ore", "Enable Tin Ore");
        add("orevolution.config.generate_platinum_ore", "Enable Platinum Ore");
        add("orevolution.config.generate_moonstone_pillar", "Enable Moonstone Pillar");
        add("orevolution.config.generate_limestone", "Enable Limestone");
        add("orevolution.config.generate_tungsten_ore", "Enable Tungsten Ore");
        add("orevolution.config.generate_experience_ore", "Enable Experience Ore(s)");
        add("orevolution.config.generate_livingstone_ore", "Enable Livingstone");
        add("orevolution.config.generate_verdite_ore", "Enable Verdite");
        add("orevolution.config.generate_aethersteel_ore", "Enable Aethersteel");
        add("orevolution.config.generate_aetherrock_meteor", "Enable Aetherrock Meteor(s)");

        add("orevolution.config.safe_ore_breaking", "Enable Safe Ore Breaking");
        add("orevolution.config.modded_progression", "Enable Modded Progression");
        add("orevolution.config.equipment_durability", "Equipment Durability");
        add("orevolution.config.steel_efficiency_nerf", "Nerf Efficiency for Steel");

        add("orevolution.config.steel_outline", "3x3 Block Outline");

        add("orevolution.configuration.section.orevolution.toolstats.toml", "Tool Stat settings");
        add("orevolution.configuration.section.orevolution.toolstats.toml.title", "Tool Stat settings");

        add("orevolution.configuration.section.orevolution.powers.toml", "Power settings");
        add("orevolution.configuration.section.orevolution.powers.toml.title", "Power settings");

        add("orevolution.configuration.section.orevolution.modcompat.toml", "Mod Compat settings");
        add("orevolution.configuration.section.orevolution.modcompat.toml.title", "Mod Compat settings");

        addPower("regenerates_daylight", "Heals it's durability every %s second(s) while receiving daylight");

        addPower("duplication", "Has a chance (%s-%s) to duplicate most block drops");
        addPower("explanation.duplication", "List of chances depending on block types:");
        addPower("explanation.double_chance", " - Double Chance (E.g. Oak Leaves) -> %s");
        addPower("explanation.normal_chance", " - Normal Blocks (E.g. Stone Blocks) -> %s");
        addPower("explanation.uncommon_chance", " - Uncommon blocks (E.g. Anvil) -> %s");
        addPower("explanation.ore_chance", " - Any Ore (E.g. Coal Ore) -> %s");
        addPower("explanation.rare_chance", " - Rare blocks (E.g. Iron Block) -> %s");
        addPower("explanation.no_chance", " - 'Never' blocks (E.g. Aethersteel Block) -> 0%");

        addPower("duplication_crops", "Has a %s chance to duplicate crops");
        addPower("triplication_crops", "Has a %s chance to triplicate crops");

        addPower("on_hit_effect", "Inflicts the following effect(s) to targeted mobs:");
        addPower("attacker_on_hit_effect", "Grants the following effect(s) when attacking mobs:");

        addPower("on_hit_effect_chance", "Has a chance to inflict the following effect(s) to targeted mobs:");
        addPower("attacker_on_hit_effect_chance", "Has a chance to grant the following effect(s) when attacking:");

        addPower("undead_on_hit", "Inflicts the following effect(s) to targeted Undead mobs:");
        addPower("monster_on_hit", "Inflicts the following effect(s) to targeted Monsters:");

        addPower("xp_increase", "Blocks give %sx the amount of Experience Points");
        addPower("xp_looting", "Mobs give +%s Experience Points after dying");

        addPower("consecutive_hits_damage", "Attacking the same target repeatedly increase damage by 1 up to %s times");
        addPower("consecutive_blocks_grants", "Each %s blocks mined, grants and increases the following effect(s):");

        addPower("avoid_damage", "Avoids durability damage on use");

        addPower("durability_speed", "Mining speed increases depending on the tool's durability");
        addPower("durability_atkspeed", "Attack speed increases depending on the tool's durability");
        addPower("durability_damage", "Attack damage increases depending on the tool's durability");

        addPower("speed_durability", "Mining speed decreases depending on the tool's durability");
        addPower("atkspeed_durability", "Attack speed decreases depending on the tool's durability");
        addPower("damage_durability", "Attack damage decreases depending on the tool's durability");

        addPower("crit_damage", "Critical strikes deal %s damage");

        addPower("on_hit_armored", "Armored Mobs receive %s damage");
        addPower("hardness_speed", "Mining speed increases depending on the block's hardness");

        addPower("on_hit_weakened", "Targeted mobs receive %s damage");

        addPower("full_set_bonus", "Full set bonus: %s");
        addPower("equipped_set", "(%s/4)");

        addPower("silver_armor", "Increases Invincibility by %s seconds");
        addPower("silver_armor_aoe", "Undead mobs at a radius of %s blocks are inflicted with the following effect(s):");

        addPower("gold_armor", "Reduces damage that bypasses armor by %s");

        addPower("armor_wearer_grants", "Grants the following effect(s):");
        addPower("armor_wearer_grants_daylight", "Grants the following effect(s) while receiving daylight:");
        addPower("armor_wearer_grants_on_hit_wearer_daylight", "Grants the following effect(s) to you when attacking and receiving daylight:");
        addPower("armor_wearer_on_attacked_wearer", "Grants the following effect(s) to you when you receive damage:");
        addPower("armor_wearer_on_attacked_target", "Causes the following effect(s) to the attacker when you receive damage:");

        addPower("armor_wearer_on_hit_wearer", "Grants the following effect(s) to you when attacking:");
        addPower("armor_wearer_on_hit_target", "Causes the following effect(s) to the target when attacking:");

        addPower("armor_immunity", "Grants immunity to the following effect(s):");
        addPower("armor_immunity_daylight", "Grants immunity to the following effect(s) when receiving daylight:");

        addPower("armor_extended_pickup", "Increases item Pick-Up range by %s blocks");
        addPower("copper_armor", "Increases Block placement range by %s blocks");

        addPower("netherite_armor", "When your health is below 50%, inflicts the following effect(s) on hit:");
        addPower("reinforced_netherite_armor", "While not submerged in lava, grants the following effect(s):");
        addPower("iron_armor", "Has a 30% chance to ignore projectile damage");
        addPower("diamond_armor", "Reduces damage from falling, explosions and falling blocks by %s");
        addPower("bronze_armor", "Increases Oxygen Time by %s seconds");
        addPower("tungsten_armor", "Reduces damage from fire sources by %s");

        addPower("attribute_increase", "%s");

        addPower("tool_cause_effect_on_hits", "Each %s hits, causes the following effect(s) to the target:");
        addPower("tool_grant_effect_on_hits", "Each %s hits, grants the following effect(s):");

        addPower("autosmelt", "Automatically smelts most ores, sands, logs and crops");
        addPower("explanation.autosmelt", "This effect is disabled while Crouching");

        addPower("fire_on_hit", "Sets targeted mobs on fire for %s seconds");

        addPower("aethersteel", "After dying, returns to your inventory");

        addPower("multi_break", "Breaks blocks in a 3x3 area");
        addPower("explanation.multi_break",
                "Each block you mine will reduce durability by 1\n" +
                        "Breaking 9 blocks results in losing 9 points of durability\n" +
                        "Loses 4 extra points of durability per efficiency level\n" +
                        "Breaking 9 blocks with efficiency I results in losing 36 points of durability"
        );

        addPower("steel_durability", "Has infinite Durability at the cost of less Damage and Attack Speed");
        addPower("steel_scythe",
                "Tills blocks in a 3x3 area\n\n" +
                        "Has a chance to inflict the following effect(s) to targeted mobs:");

        add("item.orevolution.bronze_radar.explanation",
                "Right-click while Crouching to change between friends/personal mode\n" +
                        "While on friends mode, Right-click to change shown player"
        );

        add("actionbar.orevolution.bronze_radar.normal_mode", "My coordinates: %s");
        add("actionbar.orevolution.bronze_radar.friend_mode", "Current coordinates of %s");
        add("actionbar.orevolution.bronze_radar.friend_mode.no_target", "Couldn't find any players...");
        add("actionbar.orevolution.bronze_radar.searching", "Searching%s");

        add("message.orevolution.bronze_radar_selected", "Selected: %s");

        add("item.durability_multiplier", "Durability");

        add("item.orevolution.totem.socket.empty", "Empty Socket");
        add("item.orevolution.totem.socket.diamond", "Diamond - Beneficial effects last 2x longer (min. time of 3s)");
        add("item.orevolution.totem.socket.emerald", "Emerald - Doubles regeneration");
        add("item.orevolution.totem.socket.lapis", "Lapis Lazuli - Harmful effects are 50% shorter (min. time of 3s)");
        add("item.orevolution.totem.socket.quartz", "Quartz - Prevents hunger depletion");
        add("item.orevolution.totem.socket.celestite", "Celestine - Use to gain Night Vision for 2 minutes");
        add("item.orevolution.totem.socket.amethyst", "Amethyst - Use to gain Haste for 2 minutes");
        add("item.orevolution.totem.socket.star", "Nether Star - Doubles the effects of two other totems");
        add("item.orevolution.totem.hotbar", "Only works while in your Hotbar");

        add("item.orevolution.tungsten_reinforced", "Tungsten Reinforced");
        add("item.orevolution.tungsten_coated", "Tungsten Coated");

        add("actionbar.orevolution.cant_harvest_ore", "Your %s is too weak for this block");
        add("actionbar.orevolution.cant_harvest_block", "This block requires %s");

        add("tool.minecraft.pickaxe", "a Pickaxe");
        add("tool.minecraft.shovel", "a Shovel");
        add("tool.minecraft.hoe", "a Hoe");
        add("tool.minecraft.axe", "an Axe");
        add("tool.c.sword", "a Sword");
        add("tool.orevolution.unknown_tool", "an Unknown tool");

        add("trim_material.orevolution.platinum", "Platinum Material");
        add("trim_material.orevolution.tin", "Tin Material");
        add("trim_material.orevolution.tungsten", "Tungsten Material");
        add("trim_material.orevolution.aethersteel", "Aethersteel Material");
        add("trim_material.orevolution.livingstone", "Livingstone Material");
        add("trim_material.orevolution.verdite", "Verdite Material");

        addPower("press_key", "Hold %s to view more information");
        addPower("press_key_power", "Hold %s to view listed powers");

        addCondition("chance", " - %s Chance to not happen");

        addCondition("target_hp_percent.lower_than", " - Targeted mob's health must be equal or lower than %s");
        addCondition("target_hp_percent.higher_than", " - Targeted mob's health must be equal or higher than %s");
        addCondition("target_hp_amount", " - Targeted mob's health must be equal or lower than %s Hearts");

        addCondition("player_hp_percent.lower_than", " - Your health must be equal or lower than %s");
        addCondition("player_hp_percent.higher_than", " - Your health must be equal or higher than %s");
        addCondition("player_hp_amount", " - Your health must be equal or higher than %s Hearts");

        addAdvTitle("tin_upgrade", "Truly a Tin Pickaxe");
        addAdvDesc("tin_upgrade", "Craft a tin pickaxe");

        addAdvTitle("obtain_platinum", "The metal of Kings");
        addAdvDesc("obtain_platinum", "Smelt a Platinum Ingot");

        addAdvTitle("platinum_armor", "Royal Attire");
        addAdvDesc("platinum_armor", "Craft a full set of Platinum Armor");

        addAdvTitle("platinum_gear", "Gaining Experience");
        addAdvDesc("platinum_gear", "Craft a Platinum Pickaxe");

        addAdvTitle("obtain_tungsten", "Ol' Reliable");
        addAdvDesc("obtain_tungsten", "Smelt or find a Tungsten Ingot");

        addAdvTitle("reinforcement", "Built to Last");
        addAdvDesc("reinforcement", "Reinforce an item with Tungsten");

        addAdvTitle("coating", "The Finishing Touch");
        addAdvDesc("coating", "Coat an item with Tungsten");

        addAdvTitle("obtain_primitive_aetherrock", "Found in the Void");
        addAdvDesc("obtain_primitive_aetherrock", "Obtain Primitive Aetherrock from a meteorite");

        addAdvTitle("aethersteel_armor", "Unstoppable Force");
        addAdvDesc("aethersteel_armor", "Get a full suit of Aethersteel armor");

        addAdvTitle("obtain_aethersteel_hoe", "Tool Without Reason");
        addAdvDesc("obtain_aethersteel_hoe", "Waste 1 Aethersteel Ingot to upgrade your Netherite Hoe");

        add(RegItems.GLOWING_BOTTLE.get(), "Spectral Bottle");
        add(RegItems.FIERCE_BOTTLE.get(), "Fierce Bottle");
        add(RegItems.LIFE_BOTTLE.get(), "Life Bottle");
        add(RegItems.LIGHTNING_BOTTLE.get(), "Lightning Bottle");

        addEffect(RegMobEffects.PETRIFIED, "petrification", "Petrified");
        addEffect(RegMobEffects.QUICKNESS, "quickness", "Quickness");
        addEffect(RegMobEffects.PURIFICATION, "purification", "Purification");
        addEffect(RegMobEffects.LESSER_PURIFICATION, "lesser_purification", "Lesser Purification");

        add("item.orevolution.reinforced", "Tungsten-Reinforced %s");
        add("item.orevolution.coated", "Tungsten-Coated %s");

        addPotions("intoxication", "Intoxication");

//        addEffect(RegMobEffects.WEAK_SOUL, "Weakened Soul");
        
        add(RegItems.BRONZE_HORSE_ARMOR.get(), "Bronze Horse Armor");
        add(RegItems.STEEL_HORSE_ARMOR.get(), "Steel Horse Armor");

        addItem(RegItems.BRONZE_TOTEM, "Bronze Totem");
        addItem(RegItems.BRONZE_RADAR, "Radar");

        addItem(RegItems.DEAD_SEED, "Dead Seed");
        addBlock(RegBlocks.VERDITE_CROP, "Verdite Crop");

        addBlock(RegBlocks.LIVINGSTONE_BLOCK, "Block of Livingstone");
        addItem(RegItems.PETRIFIED_SEED, "Petrified Seed");
        addBlock(RegBlocks.LIVINGSTONE_CROP, "Livingstone Crop");

        addBlock(RegBlocks.CHERT, "Chert");
        addBlock(RegBlocks.CHERT_PILLAR, "Chert Pillar");
        addBlock(RegBlocks.POLISHED_CHERT, "Polished Chert");

        addItem(RegItems.CRUSHED_TUNGSTEN, "Crushed Raw Tungsten");
        addItem(RegItems.CRUSHED_AETHERSTEEL, "Crushed Raw Aethersteel");

        addItem(RegItems.FIERY_ARROW, "Fiery Arrow");

        addItem(RegItems.FOOLS_APPLE, "Golden Apple");
        addItem(RegItems.FOOLS_CARROT, "Golden Carrot");

        addItem(RegItems.PLATINUM_SHIELD, "Platinum Shield");
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

        addItem(RegItems.TIN_SHIELD, "Tin Shield");
        addItem(FDRegistry.TIN_KNIFE, "Tin Knife");
        addItem(RegItems.TIN_SWORD, "Tin Sword");
        addItem(RegItems.TIN_SHOVEL, "Tin Shovel");
        addItem(RegItems.TIN_PICKAXE, "Tin Pickaxe");
        addItem(RegItems.TIN_AXE, "Tin Axe");
        addItem(RegItems.TIN_HOE, "Tin Hoe");

        addItem(RegItems.CASSITERITE_SWORD, "Cassiterite Sword");
        addItem(RegItems.CASSITERITE_SHOVEL, "Cassiterite Shovel");
        addItem(RegItems.CASSITERITE_PICKAXE, "Cassiterite Pickaxe");
        addItem(RegItems.CASSITERITE_AXE, "Cassiterite Axe");
        addItem(RegItems.CASSITERITE_HOE, "Cassiterite Hoe");

        addItem(RegItems.MOONSTONE_SHIELD, "Moonstone Shield");
        addItem(FDRegistry.MOONSTONE_KNIFE, "Moonstone Knife");
        addItem(RegItems.MOONSTONE_SWORD, "Moonstone Sword");
        addItem(RegItems.MOONSTONE_SHOVEL, "Moonstone Shovel");
        addItem(RegItems.MOONSTONE_PICKAXE, "Moonstone Pickaxe");
        addItem(RegItems.MOONSTONE_AXE, "Moonstone Axe");
        addItem(RegItems.MOONSTONE_HOE, "Moonstone Hoe");
        addItem(RegItems.MOONSTONE_HELMET, "Moonstone Helmet");
        addItem(RegItems.MOONSTONE_CHESTPLATE, "Moonstone Chestplate");
        addItem(RegItems.MOONSTONE_LEGGINGS, "Moonstone Leggings");
        addItem(RegItems.MOONSTONE_BOOTS, "Moonstone Boots");

        addItem(RegItems.AETHERSTEEL_SHIELD, "Aethersteel Shield");
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

        addItem(RegItems.LIVINGSTONE_SHIELD, "Livingstone Shield");
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

        addItem(RegItems.VERDITE_SHIELD, "Verdite Shield");
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

        addItem(RegItems.STEEL_HEAVYWORK_SWORD, "Steel Heavy-Work Sword");
        addItem(RegItems.STEEL_HEAVYWORK_PICKAXE, "Steel Heavy-Work Pickaxe");
        addItem(RegItems.STEEL_HEAVYWORK_AXE, "Steel Heavy-Work Axe");
        addItem(RegItems.STEEL_HEAVYWORK_SHOVEL, "Steel Heavy-Work Shovel");
        addItem(RegItems.STEEL_HEAVYWORK_HOE, "Steel Heavy-Work Hoe");
        addBlock(RegBlocks.STEEL_ANVIL, "Heavy Anvil");

        addItem(RegItems.BRONZE_HELMET, "Bronze Diving Mask");
        addItem(RegItems.BRONZE_CHESTPLATE, "Bronze Diving Chestplate");
        addItem(RegItems.BRONZE_LEGGINGS, "Bronze Diving Leggings");
        addItem(RegItems.BRONZE_BOOTS, "Bronze Diving Boots");

        addItem(RegItems.TUNGSTEN_HELMET, "Tungsten Helmet");
        addItem(RegItems.TUNGSTEN_CHESTPLATE, "Tungsten Chestplate");
        addItem(RegItems.TUNGSTEN_LEGGINGS, "Tungsten Leggings");
        addItem(RegItems.TUNGSTEN_BOOTS, "Tungsten Boots");

        addItem(RegItems.TUNGSTEN_SWORD, "Tungsten Sword");
        addItem(RegItems.TUNGSTEN_PICKAXE, "Tungsten Pickaxe");
        addItem(RegItems.TUNGSTEN_AXE, "Tungsten Axe");
        addItem(RegItems.TUNGSTEN_SHOVEL, "Tungsten Shovel");
        addItem(RegItems.TUNGSTEN_HOE, "Tungsten Hoe");

        addItem(RegItems.PYRITE, "Pyrite");
        addItem(RegItems.CELESTITE_SHARD, "Celestine Shard");

        addItem(RegItems.TIN_INGOT, "Tin Ingot");
        addItem(RegItems.RAW_TIN, "Raw Tin");
        addBlock(RegBlocks.TIN_BLOCK, "Block of Tin");
        addBlock(RegBlocks.RAW_TIN_BLOCK, "Block of Raw Tin");

        addItem(RegItems.CASSITERITE_INGOT, "Cassiterite Ingot");
        addItem(RegItems.RAW_CASSITERITE, "Raw Cassiterite");
        addBlock(RegBlocks.CASSITERITE_BLOCK, "Block of Cassiterite");
        addBlock(RegBlocks.RAW_CASSITERITE_BLOCK, "Block of Raw Cassiterite");

        addItem(RegItems.PLATINUM_INGOT, "Platinum Ingot");
        addItem(RegItems.RAW_PLATINUM, "Raw Platinum");
        addBlock(RegBlocks.PLATINUM_BLOCK, "Block of Platinum");
        addBlock(RegBlocks.RAW_PLATINUM_BLOCK, "Block of Raw Platinum");

        addItem(RegItems.TUNGSTEN_INGOT, "Tungsten Ingot");
        addItem(RegItems.RAW_TUNGSTEN, "Raw Tungsten");

        addItem(RegItems.BRONZE_ALLOY, "Bronze Alloy");
        addBlock(RegBlocks.BRONZE_BLOCK, "Block of Bronze");

        addItem(RegItems.STEEL_ALLOY, "Steel Alloy");
        addBlock(RegBlocks.STEEL_BLOCK, "Block of Steel");

        addItem(RegItems.VERDITE_INGOT, "Verdite Ingot");
        addBlock(RegBlocks.VERDITE_BLOCK, "Block of Verdite");

        addItem(RegItems.AETHERSTEEL_INGOT, "Aethersteel Ingot");
        addItem(RegItems.AETHERSTEEL_CHUNK, "Aethersteel Chunk");
        addBlock(RegBlocks.AETHERSTEEL_BLOCK, "Block of Aethersteel");

        addItem(RegItems.QUARTZ_CHIP, "Quartz Chip");

        addItem(RegItems.PROFESSIONAL_FIREWORK_ROCKET, "Professional Firework Rocket");

        addItem(RegItems.MOTION_DETECTOR, "Motion Detector");
        addItem(RegItems.GEO_SCANNER, "Geo Scanner");

        addSmithingTemplateTips(RegItems.BASIC_TEMPLATE, "basic",
                "Basic Upgrade",
                "Any Equipment",
                "Any stronger ingredient",
                "Add Any armor, weapon, or tool",
                "Add a stronger ingredient");

        add("upgrade.orevolution.downgrade", "Equipment Downgrade");
        addItem(RegItems.DOWNGRADE_TEMPLATE, "Smithing Template");
        add("item.orevolution.smithing_template.downgrade.applies_to", "Any Equipment");
        add("item.orevolution.smithing_template.downgrade.ingredients", "Any weaker ingredient");
        add("item.orevolution.smithing_template.downgrade.base_slot_description", "Add Any armor, weapon, or tool");
        add("item.orevolution.smithing_template.downgrade.additions_slot_description", "Add a weaker ingredient");

        addSmithingTemplateTips(RegItems.REINFORCED_TEMPLATE, "reinforced",
                "Reinforcing Upgrade",
                "Any item with durability",
                "Tungsten Ingot",
                "Add any item with durability",
                "Add Tungsten Ingot");
        addSmithingTemplateTips(RegItems.COATING_TEMPLATE, "coating",
                "Coating Upgrade",
                "Any Equipment",
                "Tungsten Ingot",
                "Add any armor, weapon, or tool",
                "Add Tungsten Ingot");
        addSmithingTemplateTips(RegItems.AETHERSTEEL_TEMPLATE, "aethersteel",
                "Aether Upgrade",
                "Netherite Equipment",
                "Aethersteel Ingot",
                "Add Netherite armor, weapon, or tool",
                "Add Aethersteel Ingot");

        addItem(RegItems.VINNELIO, "Vinnelio");
        addItem(RegItems.ANCIENT_FRUIT, "Ancient Fruit");
        addItem(RegItems.ANCIENT_STEW, "Ancient Stew");

        addItem(RegItems.TIN_NUGGET, "Tin Nugget");
        addItem(RegItems.CASSITERITE_NUGGET, "Cassiterite Nugget");
        addItem(RegItems.PLATINUM_NUGGET, "Platinum Nugget");
        addItem(RegItems.TUNGSTEN_NUGGET, "Tungsten Nugget");
        addItem(RegItems.VERDITE_NUGGET, "Verdite Nugget");
        addItem(RegItems.LIVINGSTONE_SHARD, "Livingstone Shard");

        addItem(RegItems.VERDITE_APPLE, "Verdite Apple");
        addItem(RegItems.VERDITE_SPIDER_EYE, "Verdite Spider Eye");

        addItem(RegItems.PLATINUM_BERRIES, "Platinum Berries");
        addItem(RegItems.PLATINUM_APPLE, "Platinum Apple");

        addBlock(RegBlocks.BUDDING_CELESTITE, "Budding Celestine");
        addBlock(RegBlocks.CELESTITE_CLUSTER, "Celestine Cluster");
        addBlock(RegBlocks.SMALL_CELESTITE_BUD, "Small Celestine Bud");
        addBlock(RegBlocks.MEDIUM_CELESTITE_BUD, "Medium Celestine Bud");
        addBlock(RegBlocks.LARGE_CELESTITE_BUD, "Large Celestine Bud");
        addBlock(RegBlocks.CELESTITE_BLOCK, "Block of Celestine");
        addBlock(RegBlocks.PYRITE_BLOCK, "Block of Pyrite");
        addBlock(RegBlocks.QUARTZOLITE, "Quartzolite");
        addBlock(RegBlocks.STEEL_BARS, "Steel Bars");
        addBlock(RegBlocks.VERDITE_BRICKS, "Block of Verdite");
        addBlock(RegBlocks.LIVINGSTONE_BRICKS, "Livingstone Bricks");
        addBlock(RegBlocks.TIN_ORE, "Tin Ore");
        addBlock(RegBlocks.DEEPSLATE_TIN_ORE, "Deepslate Tin Ore");
        addBlock(RegBlocks.CASSITERITE_ORE, "Cassiterite Ore");
        addBlock(RegBlocks.DEEPSLATE_CASSITERITE_ORE, "Deepslate Cassiterite Ore");
        addBlock(RegBlocks.NETHER_CASSITERITE_ORE, "Nether Cassiterite Ore");
        addBlock(RegBlocks.PLATINUM_ORE, "Platinum Ore");
        addBlock(RegBlocks.DEEPSLATE_PLATINUM_ORE, "Deepslate Platinum Ore");
        addBlock(RegBlocks.NETHER_TUNGSTEN_ORE, "Nether Tungsten Ore");
        addBlock(RegBlocks.RAW_TUNGSTEN_BLOCK, "Block of Raw Tungsten");
        
        addBlock(RegBlocks.TUNGSTEN_BLOCK, "Block of Tungsten");
        addBlock(RegBlocks.POLISHED_TUNGSTEN, "Polished Tungsten Block");
        addBlock(RegBlocks.CUT_TUNGSTEN_BLOCK, "Cut Tungsten");
        addBlock(RegBlocks.CHISELED_TUNGSTEN_BLOCK, "Chiseled Tungsten");
        addBlock(RegBlocks.CHISELED_TUNGSTEN_BRICKS, "Chiseled Tungsten Bricks");
        addBlock(RegBlocks.TUNGSTEN_BRICKS, "Tungsten Bricks");
        addBlock(RegBlocks.TUNGSTEN_BARS, "Tungsten Bars");

        addBlock(RegBlocks.DECAYING_TUNGSTEN_BLOCK, "Decaying Block of Tungsten");
        addBlock(RegBlocks.POLISHED_DECAYING_TUNGSTEN, "Decaying Polished Tungsten Block");
        addBlock(RegBlocks.CUT_DECAYING_TUNGSTEN_BLOCK, "Decaying Cut Tungsten");
        addBlock(RegBlocks.CHISELED_DECAYING_TUNGSTEN_BLOCK, "Decaying Chiseled Tungsten");
        addBlock(RegBlocks.CHISELED_DECAYING_TUNGSTEN_BRICKS, "Decaying Chiseled Tungsten Bricks");
        addBlock(RegBlocks.DECAYING_TUNGSTEN_BRICKS, "Decaying Tungsten Bricks");
        addBlock(RegBlocks.DECAYING_TUNGSTEN_BARS, "Decaying Tungsten Bars");

        addBlock(RegBlocks.CORRODED_TUNGSTEN_BLOCK, "Corroded Block of Tungsten");
        addBlock(RegBlocks.POLISHED_CORRODED_TUNGSTEN, "Corroded Polished Tungsten Block");
        addBlock(RegBlocks.CUT_CORRODED_TUNGSTEN_BLOCK, "Corroded Cut Tungsten");
        addBlock(RegBlocks.CHISELED_CORRODED_TUNGSTEN_BLOCK, "Corroded Chiseled Tungsten");
        addBlock(RegBlocks.CHISELED_CORRODED_TUNGSTEN_BRICKS, "Corroded Chiseled Tungsten Bricks");
        addBlock(RegBlocks.CORRODED_TUNGSTEN_BRICKS, "Corroded Tungsten Bricks");
        addBlock(RegBlocks.CORRODED_TUNGSTEN_BARS, "Corroded Tungsten Bars");

        addBlock(RegBlocks.TUNGSTEN_SPONGE, "Tungsten Sponge");
        addBlock(RegBlocks.HOT_TUNGSTEN_SPONGE, "Hot Tungsten Sponge");


        addBlock(RegBlocks.NETHER_XP_ORE, "Nether Experience Ore");
        addBlock(RegBlocks.END_XP_ORE, "End Experience Ore");
        addBlock(RegBlocks.RHYOLITE_EMERALD_ORE, "Rhyolite Emerald Ore");
        addBlock(RegBlocks.RHYOLITE_EMERALD_ORE_CLUMP, "Rhyolite Emerald Ore Deposit");
        addBlock(RegBlocks.AETHERROCK, "Aetherrock");
        addBlock(RegBlocks.AETHERROCK_TILES, "Aetherrock Tiles");
        addBlock(RegBlocks.POLISHED_AETHERROCK, "Polished Aetherrock");
        addBlock(RegBlocks.AETHERROCK_BRICKS, "Aetherrock Bricks");
        addBlock(RegBlocks.CRACKED_AETHERROCK_BRICKS, "Cracked Aetherrock Bricks");
        addBlock(RegBlocks.CHERT_BRICKS, "Chert Bricks");
        addBlock(RegBlocks.RHYOLITE_BRICKS, "Rhyolite Bricks");
        addBlock(RegBlocks.CELESTITE_BRICKS, "Celestine Bricks");
        addBlock(RegBlocks.POLISHED_CELESTITE, "Polished Celestine");
        addBlock(RegBlocks.PRIMITIVE_AETHERROCK, "Primitive Aetherrock");
        addBlock(RegBlocks.CUT_STEEL_BLOCK, "Cut Steel");
        addBlock(RegBlocks.BRONZE_TILES, "Bronze Tiles");
        addBlock(RegBlocks.STEEL_PILLAR, "Steel Pillar");
        addBlock(RegBlocks.STEEL_DOOR, "Steel Door");
        addBlock(RegBlocks.STEEL_TRAPDOOR, "Steel Trapdoor");
        addBlock(RegBlocks.TIN_BRICKS, "Tin Bricks");
        addBlock(RegBlocks.MOONSTONE, "Moonstone");
        addBlock(RegBlocks.RHYOLITE_ENCRUSTED_MOONSTONE, "Rhyolite Encrusted Moonstone");
        addBlock(RegBlocks.BASALT_ENCRUSTED_MOONSTONE, "Basalt Encrusted Moonstone");
        addBlock(RegBlocks.STEEL_GRATE, "Steel Grate");

        addBlock(RegBlocks.TIN_LANTERN, "Tin Lantern");
        addBlock(RegBlocks.PLATINUM_LANTERN, "Platinum Lantern");
        addBlock(RegBlocks.BRONZE_LANTERN, "Bronze Lantern");
        addBlock(RegBlocks.GOLDEN_LANTERN, "Golden Lantern");
        addBlock(RegBlocks.TIN_SOUL_LANTERN, "Tin Soul Lantern");
        addBlock(RegBlocks.PLATINUM_SOUL_LANTERN, "Platinum Soul Lantern");
        addBlock(RegBlocks.BRONZE_SOUL_LANTERN, "Bronze Soul Lantern");
        addBlock(RegBlocks.GOLDEN_SOUL_LANTERN, "Golden Soul Lantern");

        addBlock(RegBlocks.TIN_TILES, "Tin Tiles");
        addBlock(RegBlocks.PLATINUM_TILES, "Platinum Tiles");
        addBlock(RegBlocks.GOLD_TILES, "Golden Tiles");
        addBlock(RegBlocks.PLATINUM_PILLAR, "Platinum Pillar");
        addBlock(RegBlocks.GOLD_PILLAR, "Golden Pillar");
        addBlock(RegBlocks.BRONZE_BARS, "Bronze Bars");
        addBlock(RegBlocks.GOLD_BARS, "Golden Bars");
        addBlock(RegBlocks.TIN_BARS, "Tin Bars");
        addBlock(RegBlocks.PLATINUM_BARS, "Platinum Bars");
        addBlock(RegBlocks.POLISHED_AETHERROCK_WALL, "Polished Aetherrock Wall");
        addBlock(RegBlocks.POLISHED_AETHERROCK_STAIR, "Polished Aetherrock Stair");
        addBlock(RegBlocks.POLISHED_AETHERROCK_SLAB, "Polished Aetherrock Slab");
        addBlock(RegBlocks.AETHERROCK_WALL, "Aetherrock Wall");
        addBlock(RegBlocks.AETHERROCK_STAIR, "Aetherrock Stair");
        addBlock(RegBlocks.AETHERROCK_SLAB, "Aetherrock Slab");
        addBlock(RegBlocks.AETHERROCK_BRICKS_WALL, "Aetherrock Bricks Wall");
        addBlock(RegBlocks.AETHERROCK_BRICKS_STAIR, "Aetherrock Bricks Stair");
        addBlock(RegBlocks.AETHERROCK_BRICKS_SLAB, "Aetherrock Bricks Slab");

        addBlock(RegBlocks.CHERT_WALL, "Chert Wall");
        addBlock(RegBlocks.CHERT_STAIR, "Chert Stair");
        addBlock(RegBlocks.CHERT_SLAB, "Chert Slab");
        addBlock(RegBlocks.POLISHED_CHERT_WALL, "Polished Chert Wall");
        addBlock(RegBlocks.POLISHED_CHERT_STAIR, "Polished Chert Stair");
        addBlock(RegBlocks.POLISHED_CHERT_SLAB, "Polished Chert Slab");
        addBlock(RegBlocks.CHERT_BRICKS_WALL, "Chert Bricks Wall");
        addBlock(RegBlocks.CHERT_BRICKS_STAIR, "Chert Bricks Stair");
        addBlock(RegBlocks.CHERT_BRICKS_SLAB, "Chert Bricks Slab");

        addBlock(RegBlocks.RHYOLITE_WALL, "Rhyolite Wall");
        addBlock(RegBlocks.RHYOLITE_STAIR, "Rhyolite Stair");
        addBlock(RegBlocks.RHYOLITE_SLAB, "Rhyolite Slab");
        addBlock(RegBlocks.POLISHED_RHYOLITE_WALL, "Polished Rhyolite Wall");
        addBlock(RegBlocks.POLISHED_RHYOLITE_STAIR, "Polished Rhyolite Stair");
        addBlock(RegBlocks.POLISHED_RHYOLITE_SLAB, "Polished Rhyolite Slab");
        addBlock(RegBlocks.RHYOLITE_BRICKS_WALL, "Rhyolite Bricks Wall");
        addBlock(RegBlocks.RHYOLITE_BRICKS_STAIR, "Rhyolite Bricks Stair");
        addBlock(RegBlocks.RHYOLITE_BRICKS_SLAB, "Rhyolite Bricks Slab");

        addBlock(RegBlocks.POLISHED_LIVINGSTONE_WALL, "Polished Livingstone Wall");
        addBlock(RegBlocks.POLISHED_LIVINGSTONE_STAIR, "Polished Livingstone Stair");
        addBlock(RegBlocks.POLISHED_LIVINGSTONE_SLAB, "Polished Livingstone Slab");
        addBlock(RegBlocks.LIVINGSTONE_BRICKS_WALL, "Livingstone Bricks Wall");
        addBlock(RegBlocks.LIVINGSTONE_BRICKS_STAIR, "Livingstone Bricks Stair");
        addBlock(RegBlocks.LIVINGSTONE_BRICKS_SLAB, "Livingstone Bricks Slab");

        addBlock(RegBlocks.CUT_STEEL_SLAB, "Cut Steel Slab");
        addBlock(RegBlocks.CUT_STEEL_STAIR, "Cut Steel Stair");

        addBlock(RegBlocks.AMETHYST_BRICKS_WALL, "Amethyst Bricks Wall");
        addBlock(RegBlocks.AMETHYST_BRICKS_STAIR, "Amethyst Bricks Stair");
        addBlock(RegBlocks.AMETHYST_BRICKS_SLAB, "Amethyst Bricks Slab");
        addBlock(RegBlocks.POLISHED_AMETHYST_WALL, "Polished Amethyst Wall");
        addBlock(RegBlocks.POLISHED_AMETHYST_STAIR, "Polished Amethyst Stair");
        addBlock(RegBlocks.POLISHED_AMETHYST_SLAB, "Polished Amethyst Slab");

        addBlock(RegBlocks.CELESTITE_BRICKS_WALL, "Celestine Bricks Wall");
        addBlock(RegBlocks.CELESTITE_BRICKS_STAIR, "Celestine Bricks Stair");
        addBlock(RegBlocks.CELESTITE_BRICKS_SLAB, "Celestine Bricks Slab");
        addBlock(RegBlocks.POLISHED_CELESTITE_WALL, "Polished Celestine Wall");
        addBlock(RegBlocks.POLISHED_CELESTITE_STAIR, "Polished Celestine Stair");
        addBlock(RegBlocks.POLISHED_CELESTITE_SLAB, "Polished Celestine Slab");

        addBlock(RegBlocks.POLISHED_AMETHYST, "Polished Amethyst");
        addBlock(RegBlocks.AMETHYST_BRICKS, "Amethyst Bricks");

        addBlock(RegBlocks.CORELIO, "Corelio");
        addBlock(RegBlocks.LARGE_CORELIO, "Large Corelio");
        addBlock(RegBlocks.UNKNOWN_ROOTS, "Unknown Roots");
        addBlock(RegBlocks.UNKNOWN_ROOTS_BLOCK, "Block of Unknown Roots");
        addBlock(RegBlocks.EMERALD_CLUSTER, "Emerald Cluster");
        addBlock(RegBlocks.PRISMARINE_CLUSTER, "Prismarine Cluster");
        addBlock(RegBlocks.VINNELIO, "Vinnelio");
        addBlock(RegBlocks.VINNELIO_PLANT, "Vinnelio");
        addBlock(RegBlocks.RHYOLITE, "Rhyolite");
        addBlock(RegBlocks.RHYOLITE_PILLAR, "Rhyolite Pillar");
        addBlock(RegBlocks.POLISHED_RHYOLITE, "Polished Rhyolite");
        addBlock(RegBlocks.POLISHED_LIVINGSTONE, "Polished Livingstone");

        addBlock(RegBlocks.RDX, "RDX");

        addItem(SBRegistry.AETHERSTEEL_SPEAR, "Aethersteel Spear");
        addItem(SBRegistry.TIN_SPEAR, "Tin Spear");
        addItem(SBRegistry.VERDITE_SPEAR, "Verdite Spear");
        addItem(SBRegistry.PLATINUM_SPEAR, "Platinum Spear");
        addItem(SBRegistry.LIVINGSTONE_SPEAR, "Livingstone Spear");
        addItem(SBRegistry.MOONSTONE_SPEAR, "Moonstone Spear");

        add("tooltip.orevolution.lunar_lantern_tooltip",
                "Causes nearby Monsters to Glow \n" +
                "Right-click to enable/disable the effect");

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
