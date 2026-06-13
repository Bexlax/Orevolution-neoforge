package net.bexla.orevolution.content.data.utility;

import com.teamabnormals.blueprint.core.util.TagUtil;
import net.bexla.orevolution.Orevolution;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class OrevolutionTags {

    public static class Items {
        public static final TagKey<Item> tinOres = forgeTag("ores/tin");
        public static final TagKey<Item> platOres = forgeTag("ores/platinum");
        public static final TagKey<Item> tungsOres = forgeTag("ores/tungsten");
        public static final TagKey<Item> XPOres = forgeTag("ores/experience");

        public static final TagKey<Item> rawTinStorages = forgeTag("storage_blocks/raw_tin");
        public static final TagKey<Item> rawPlatStorages = forgeTag("storage_blocks/raw_platinum");
        public static final TagKey<Item> rawTungsStorages = forgeTag("storage_blocks/raw_tungsten");

        public static final TagKey<Item> tinProgFollow = tag("tools/tin_progress_followers");
        public static final TagKey<Item> platProgFollow = tag("tools/platinum_progress_followers");
        public static final TagKey<Item> tinProgExcept = tag("tools/tin_progress_exceptions");
        public static final TagKey<Item> platProgExcept = tag("tools/platinum_progress_exceptions");

        public static final TagKey<Item> tinIngots = forgeTag("ingots/tin");
        public static final TagKey<Item> platIngots = forgeTag("ingots/platinum");
        public static final TagKey<Item> tungsIngots = forgeTag("ingots/tungsten");
        public static final TagKey<Item> enderiteIngots = forgeTag("ingots/enderite");
        public static final TagKey<Item> verditeIngots = forgeTag("ingots/verdite");

        public static final TagKey<Item> bronzeIngots = forgeTag("ingots/bronze");
        public static final TagKey<Item> steelIngots = forgeTag("ingots/steel");

//        public static final TagKey<Item> amber = forgeTag("gems/amber");

        public static final TagKey<Item> tinNuggets = forgeTag("nuggets/tin");
        public static final TagKey<Item> platNuggets = forgeTag("nuggets/platinum");
        public static final TagKey<Item> tungsNuggets = forgeTag("nuggets/tungsten");
        public static final TagKey<Item> livingstoneFragments = forgeTag("nuggets/livingstone");
        public static final TagKey<Item> verditeNuggets = forgeTag("nuggets/verdite");

        public static final TagKey<Item> tinRaws = forgeTag("raw_materials/tin");
        public static final TagKey<Item> platRaws = forgeTag("raw_materials/platinum");
        public static final TagKey<Item> tungsRaws = forgeTag("raw_materials/tungsten");

        public static final TagKey<Item> tinStorages = forgeTag("storage_blocks/tin");
        public static final TagKey<Item> platStorages = forgeTag("storage_blocks/platinum");
        public static final TagKey<Item> tungsStorages = forgeTag("storage_blocks/tungsten");
        public static final TagKey<Item> enderiteStorages = forgeTag("storage_blocks/enderite");
        public static final TagKey<Item> livingstoneStorages = forgeTag("storage_blocks/livingstone");
        public static final TagKey<Item> verditeStorages = forgeTag("storage_blocks/verdite");

        public static final TagKey<Item> repairableTin = tag("repairable_with_tin");

        public static final TagKey<Item> toolsKnives = forgeTag("tools/knives");
        public static final TagKey<Item> toolsKnivesFD = ItemTags.create(ResourceLocation.fromNamespaceAndPath("farmersdelight", "tools/knives"));
//        public static final TagKey<Item> shields = forgeTag("shields");
//        public static final TagKey<Item> shieldsSE = ItemTags.create(new ResourceLocation(ModCompat.shield(), "shields"));
//
//        public static final TagKey<Item> bows = forgeTag("bows");
//        public static final TagKey<Item> bowsAE = ItemTags.create(new ResourceLocation(ModCompat.archery(), "ae_bows"));

        private static TagKey<Item> tag(String name) {
            return TagUtil.itemTag(Orevolution.MODID, name);
        }

        private static TagKey<Item> forgeTag(String name) {
            return TagUtil.itemTag("c", name);
        }
    }

    public static class Blocks {
        public static final TagKey<Block> tinOres = forgeTag("ores/tin");
        public static final TagKey<Block> platOres = forgeTag("ores/platinum");
        public static final TagKey<Block> tungsOres = forgeTag("ores/tungsten");
        public static final TagKey<Block> XPOres = forgeTag("ores/experience");

        public static final TagKey<Block> spelunkeryOres = tag("spelunkery/ores");

        public static final TagKey<Block> neverDuplicateChance = tag("never_duplicate");
        public static final TagKey<Block> rareDuplicateChance = tag("rare_duplicate_chance");
        public static final TagKey<Block> uncommonDuplicateChance = tag("uncommon_duplicate_chance");
        public static final TagKey<Block> alwaysDuplicateChance = tag("always_duplicate");

        public static final TagKey<Block> tuffs = forgeTag("tuffs");
        public static final TagKey<Block> andesites = forgeTag("andesites");
        public static final TagKey<Block> diorites = forgeTag("diorites");
        public static final TagKey<Block> granites = forgeTag("granites");
        public static final TagKey<Block> deepslates = forgeTag("deepslates");
        public static final TagKey<Block> blackstones = forgeTag("blackstones");

        public static final TagKey<Block> tinStorages = forgeTag("storage_blocks/tin");
        public static final TagKey<Block> platStorages = forgeTag("storage_blocks/platinum");
        public static final TagKey<Block> tungsStorages = forgeTag("storage_blocks/tungsten");
        public static final TagKey<Block> enderiteStorages = forgeTag("storage_blocks/enderite");
        public static final TagKey<Block> livingstoneStorages = forgeTag("storage_blocks/livingstone");
        public static final TagKey<Block> verditeStorages = forgeTag("storage_blocks/verdite");

        public static final TagKey<Block> rawTinStorages = forgeTag("storage_blocks/raw_tin");
        public static final TagKey<Block> rawPlatStorages = forgeTag("storage_blocks/raw_platinum");
        public static final TagKey<Block> rawTungsStorages = forgeTag("storage_blocks/raw_tungsten");

        public static final TagKey<Block> needsTinTool = forgeTag("needs_tin_tool");
        public static final TagKey<Block> needsPlatinumTool = forgeTag("needs_platinum_tool");
        public static final TagKey<Block> needsAethersteelTool = forgeTag("needs_aethersteel_tool");

        public static final TagKey<Block> incorrectForTin = forgeTag("incorrect_for_tin_tool");
        public static final TagKey<Block> incorrectForPlatinum = forgeTag("incorrect_for_platinum_tool");
        public static final TagKey<Block> incorrectForAethersteel = forgeTag("incorrect_for_aethersteel_tool");

        private static TagKey<Block> tag(String name) {
            return TagUtil.blockTag(Orevolution.MODID, name);
        }

        private static TagKey<Block> forgeTag(String name) {
            return TagUtil.blockTag("c", name);
        }
    }
}
