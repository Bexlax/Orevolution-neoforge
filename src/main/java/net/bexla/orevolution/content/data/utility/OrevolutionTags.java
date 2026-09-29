package net.bexla.orevolution.content.data.utility;

import com.teamabnormals.blueprint.core.util.TagUtil;
import net.bexla.orevolution.Orevolution;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class OrevolutionTags {
    public static class Items {
        public static final TagKey<Item> NEGATES_PYRITE_FIRE = tag("negates_pyrite_fire");

        public static final TagKey<Item> ACCEPTS_COAT = tag("accepts_coat");
        public static final TagKey<Item> ACCEPTS_REINFORCEMENT = tag("accepts_reinforcement");

        public static final TagKey<Item> TUNGSTEN_REPAIR_BLACKLIST = tag("tungsten_repair_blacklist");
        public static final TagKey<Item> REINFORCE_BLACKLIST = tag("reinforce_blacklist");
        public static final TagKey<Item> COAT_BLACKLIST = tag("coat_blacklist");

        public static final TagKey<Item> WEAPON_POWERS = tag("weapon_powers");
        public static final TagKey<Item> TOOL_POWERS = tag("tool_powers");

        public static final TagKey<Item> TIN_ORES = universalTag("ores/tin");
        public static final TagKey<Item> CASSITERITE_ORES = universalTag("ores/cassiterite");
        public static final TagKey<Item> PLATINUM_ORES = universalTag("ores/platinum");
        public static final TagKey<Item> TUNGSTEN_ORES = universalTag("ores/tungsten");
        public static final TagKey<Item> XP_ORES = universalTag("ores/experience");

        public static final TagKey<Item> RAW_TIN_BLOCKS = universalTag("storage_blocks/raw_tin");
        public static final TagKey<Item> RAW_CASSITERITE_BLOCKS = universalTag("storage_blocks/raw_cassiterite");
        public static final TagKey<Item> RAW_PLATINUM_BLOCKS = universalTag("storage_blocks/raw_platinum");
        public static final TagKey<Item> RAW_TUNGSTEN_BLOCKS = universalTag("storage_blocks/raw_tungsten");

        public static final TagKey<Item> CASSITERITE_INGOTS = universalTag("ingots/cassiterite");
        public static final TagKey<Item> TIN_INGOTS = universalTag("ingots/tin");
        public static final TagKey<Item> PLATINUM_INGOTS = universalTag("ingots/platinum");
        public static final TagKey<Item> TUNGSTEN_INGOTS = universalTag("ingots/tungsten");
        public static final TagKey<Item> ENDERITE_ADJACENT = universalTag("ingots/enderite");
        public static final TagKey<Item> VERDITE_INGOTS = universalTag("ingots/verdite");

        public static final TagKey<Item> BRONZE_INGOTS = universalTag("ingots/bronze");
        public static final TagKey<Item> STEEL_INGOTS = universalTag("ingots/steel");

        public static final TagKey<Item> TIN_NUGGETS = universalTag("nuggets/tin");
        public static final TagKey<Item> CASSITERITE_NUGGETS = universalTag("nuggets/cassiterite");
        public static final TagKey<Item> PLATINUM_NUGGETS = universalTag("nuggets/platinum");
        public static final TagKey<Item> TUNGSTEN_NUGGETS = universalTag("nuggets/tungsten");
        public static final TagKey<Item> LIVINGSTONE_FRAGMENTS = universalTag("nuggets/livingstone");
        public static final TagKey<Item> VERDITE_NUGGETS = universalTag("nuggets/verdite");

        public static final TagKey<Item> TIN_RAWS = universalTag("raw_materials/tin");
        public static final TagKey<Item> CASSITERITE_RAWS = universalTag("raw_materials/cassiterite");
        public static final TagKey<Item> PLATINUM_RAWS = universalTag("raw_materials/platinum");
        public static final TagKey<Item> TUNGSTEN_RAWS = universalTag("raw_materials/tungsten");

        public static final TagKey<Item> TIN_BLOCKS = universalTag("storage_blocks/tin");
        public static final TagKey<Item> CASSITERITE_BLOCKS = universalTag("storage_blocks/cassiterite");
        public static final TagKey<Item> PLATINUM_BLOCKS = universalTag("storage_blocks/platinum");
        public static final TagKey<Item> TUNGSTEN_BLOCKS = universalTag("storage_blocks/tungsten");
        public static final TagKey<Item> ENDERITE_ADJACENT_BLOCKS = universalTag("storage_blocks/enderite");
        public static final TagKey<Item> LIVINGSTONE_BLOCKS = universalTag("storage_blocks/livingstone");
        public static final TagKey<Item> VERDITE_BLOCKS = universalTag("storage_blocks/verdite");

        public static final TagKey<Item> KNIFE = universalTag("tools/knife");
        public static final TagKey<Item> KNIVES_FARMERS_DELIGHT = ItemTags.create(ResourceLocation.fromNamespaceAndPath("farmersdelight", "tools/knives"));
        public static final TagKey<Item> SHIELDS = universalTag("shields");
        public static final TagKey<Item> SHIELDS_SHIELD_EXPANSION = ItemTags.create(ResourceLocation.fromNamespaceAndPath("shieldexp", "shields"));

//        public static final TagKey<Item> bows = forgeTag("bows");
//        public static final TagKey<Item> bowsAE = ItemTags.create(new ResourceLocation(ModCompat.archery(), "ae_bows"));

        private static TagKey<Item> tag(String name) {
            return TagUtil.itemTag(Orevolution.MODID, name);
        }

        private static TagKey<Item> universalTag(String name) {
            return TagUtil.itemTag("c", name);
        }
    }

    public static class Blocks {
        public static final TagKey<Block> PLANT_ORES = universalTag("ores/plant");
        public static final TagKey<Block> AUTOSMELT = tag("autosmelt");

        public static final TagKey<Block> TIN_ORES = universalTag("ores/tin");
        public static final TagKey<Block> CASSITERITE_ORES = universalTag("ores/cassiterite");
        public static final TagKey<Block> PLATINUM_ORES = universalTag("ores/platinum");
        public static final TagKey<Block> TUNGSTEN_ORES = universalTag("ores/tungsten");
        public static final TagKey<Block> XP_ORES = universalTag("ores/experience");
        public static final TagKey<Block> MOONSTONE_ORES = universalTag("ores/moonstone");

        public static final TagKey<Block> NEVER_DUPLICATE_CHANCE = tag("never_duplicate");
        public static final TagKey<Block> RARE_DUPLICATE_CHANCE = tag("rare_duplicate_chance");
        public static final TagKey<Block> UNCOMMON_DUPLICATE_CHANCE = tag("uncommon_duplicate_chance");
        public static final TagKey<Block> DOUBLE_DUPLICATE_CHANCE = tag("double_duplicate_chance");

        public static final TagKey<Block> LARGE_PILLAR_REPLACEMENT = tag("large_pillar_replacement");

        public static final TagKey<Block> CRATER_PLACEMENT_BLACKLIST = tag("crater_placement_blacklist");

        public static final TagKey<Block> TUFFS = universalTag("tuffs");
        public static final TagKey<Block> ANDESITES = universalTag("andesites");
        public static final TagKey<Block> DIORITES = universalTag("diorites");
        public static final TagKey<Block> GRANITES = universalTag("granites");
        public static final TagKey<Block> DEEPSLATES = universalTag("deepslates");
        public static final TagKey<Block> BLACKSTONES = universalTag("blackstones");
        public static final TagKey<Block> BASALTS = universalTag("basalts");

        public static final TagKey<Block> TIN_BLOCKS = universalTag("storage_blocks/tin");
        public static final TagKey<Block> CASSITERITE_BLOCKS = universalTag("storage_blocks/cassiterite");
        public static final TagKey<Block> PLATINUM_BLOCKS = universalTag("storage_blocks/platinum");
        public static final TagKey<Block> TUNGSTEN_BLOCKS = universalTag("storage_blocks/tungsten");
        public static final TagKey<Block> ENDERITE_ADJACENT_BLOCKS = universalTag("storage_blocks/enderite");
        public static final TagKey<Block> LIVINGSTONE_BLOCKS = universalTag("storage_blocks/livingstone");
        public static final TagKey<Block> VERDITE_BLOCKS = universalTag("storage_blocks/verdite");

        public static final TagKey<Block> RAW_TIN_BLOCKS = universalTag("storage_blocks/raw_tin");
        public static final TagKey<Block> RAW_CASSITERITE_BLOCKS = universalTag("storage_blocks/raw_cassiterite");
        public static final TagKey<Block> RAW_PLATINUM_BLOCKS = universalTag("storage_blocks/raw_platinum");
        public static final TagKey<Block> RAW_TUNGSTEN_BLOCKS = universalTag("storage_blocks/raw_tungsten");

        public static final TagKey<Block> NEEDS_TIN_TOOL = tag("needs_tin_tool");
        public static final TagKey<Block> NEEDS_PLATINUM_TOOL = tag("needs_platinum_tool");
        public static final TagKey<Block> NEEDS_AETHERSTEEL_TOOL = tag("needs_aethersteel_tool");

        public static final TagKey<Block> INCORRECT_FOR_TIN_ALT = tag("alt_progress/incorrect_for_tin_tool_copper_progress");

        public static final TagKey<Block> INCORRECT_FOR_TIN_TOOL = tag("incorrect_for_tin_tool");
        public static final TagKey<Block> INCORRECT_FOR_PLATINUM_TOOL = tag("incorrect_for_platinum_tool");
        public static final TagKey<Block> INCORRECT_FOR_MOONSTONE_TOOL = tag("incorrect_for_moonstone_tool");
        public static final TagKey<Block> INCORRECT_FOR_AETHERSTEEL_TOOL = tag("incorrect_for_aethersteel_tool");

        private static TagKey<Block> tag(String name) {
            return TagUtil.blockTag(Orevolution.MODID, name);
        }

        private static TagKey<Block> universalTag(String name) {
            return TagUtil.blockTag("c", name);
        }
    }
    public static class Misc {
        public static final TagKey<DamageType> IS_KINETIC = damageType("is_kinetic");

        private static TagKey<DamageType> damageType(String name) {
            return TagUtil.damageTypeTag(Orevolution.MODID, name);
        }

        public static final TagKey<Biome> HAS_MOONSTONE_NETHER = biome("is_basalt_biome");

        private static TagKey<Biome> biome(String name) {
            return TagUtil.biomeTag(Orevolution.MODID, name);
        }
    }
}
