package net.bexla.orevolution.content.types.item;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

import static net.bexla.orevolution.Orevolution.lc;

public class OrevolutionSmithingTemplate extends SmithingTemplateItem {
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;

    private static final ResourceLocation EMPTY_SLOT_HELMET = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = ResourceLocation.withDefaultNamespace("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_HOE = ResourceLocation.withDefaultNamespace("item/empty_slot_hoe");
    private static final ResourceLocation EMPTY_SLOT_AXE = ResourceLocation.withDefaultNamespace("item/empty_slot_axe");
    private static final ResourceLocation EMPTY_SLOT_SWORD = ResourceLocation.withDefaultNamespace("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL = ResourceLocation.withDefaultNamespace("item/empty_slot_shovel");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = ResourceLocation.withDefaultNamespace("item/empty_slot_pickaxe");
    private static final ResourceLocation EMPTY_SLOT_INGOT = ResourceLocation.withDefaultNamespace("item/empty_slot_ingot");

    private static final Component AETHERSTEEL_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", lc("aethersteel_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component AETHERSTEEL_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", lc("smithing_template.aethersteel_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component AETHERSTEEL_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", lc("smithing_template.aethersteel_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component AETHERSTEEL_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", lc("smithing_template.aethersteel_upgrade.base_slot_description")));
    private static final Component AETHERSTEEL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", lc("smithing_template.aethersteel_upgrade.additions_slot_description")));
    
    private static final Component REINFORCED_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", lc("reinforced_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component REINFORCED_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", lc("smithing_template.reinforced_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component REINFORCED_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", lc("smithing_template.reinforced_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component REINFORCED_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", lc("smithing_template.reinforced_upgrade.base_slot_description")));
    private static final Component REINFORCED_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", lc("smithing_template.reinforced_upgrade.additions_slot_description")));

    private static final Component COATING_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", lc("coating_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component COATING_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", lc("smithing_template.coating_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component COATING_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", lc("smithing_template.coating_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component COATING_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", lc("smithing_template.coating_upgrade.base_slot_description")));
    private static final Component COATING_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", lc("smithing_template.coating_upgrade.additions_slot_description")));

    private static final Component BASIC_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", lc("basic_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component BASIC_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", lc("smithing_template.basic_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component BASIC_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", lc("smithing_template.basic_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component BASIC_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", lc("smithing_template.basic_upgrade.base_slot_description")));
    private static final Component BASIC_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", lc("smithing_template.basic_upgrade.additions_slot_description")));

    private static final Component DOWNGRADE = Component.translatable(Util.makeDescriptionId("upgrade", lc("downgrade"))).withStyle(TITLE_FORMAT);
    private static final Component DOWNGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", lc("smithing_template.downgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component DOWNGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", lc("smithing_template.downgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component DOWNGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", lc("smithing_template.downgrade.base_slot_description")));
    private static final Component DOWNGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", lc("smithing_template.downgrade.additions_slot_description")));

    public OrevolutionSmithingTemplate(Component applies_to, Component ingredients, Component title_description, Component base_slot_description, Component additions_slot_description, List<ResourceLocation> armor_icon_list, List<ResourceLocation> material_icon_list) {
        super(applies_to, ingredients, title_description, base_slot_description, additions_slot_description, armor_icon_list, material_icon_list);
    }

    public static SmithingTemplateItem createAethersteelUpgradeTemplate() {
        return new SmithingTemplateItem(AETHERSTEEL_UPGRADE_APPLIES_TO, AETHERSTEEL_UPGRADE_INGREDIENTS, AETHERSTEEL_UPGRADE, AETHERSTEEL_UPGRADE_BASE_SLOT_DESCRIPTION, AETHERSTEEL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createUpgradeIconList(), createUpgradeMaterialList());
    }

    public static SmithingTemplateItem createReinforcedUpgradeTemplate() {
        return new SmithingTemplateItem(REINFORCED_UPGRADE_APPLIES_TO, REINFORCED_UPGRADE_INGREDIENTS, REINFORCED_UPGRADE, REINFORCED_UPGRADE_BASE_SLOT_DESCRIPTION, REINFORCED_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createUpgradeIconList(), createUpgradeMaterialList());
    }

    public static SmithingTemplateItem createCoatingUpgradeTemplate() {
        return new SmithingTemplateItem(COATING_UPGRADE_APPLIES_TO, COATING_UPGRADE_INGREDIENTS, COATING_UPGRADE, COATING_UPGRADE_BASE_SLOT_DESCRIPTION, COATING_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createUpgradeIconList(), createUpgradeMaterialList());
    }

    public static SmithingTemplateItem createBasicUpgradeTemplate() {
        return new SmithingTemplateItem(BASIC_UPGRADE_APPLIES_TO, BASIC_UPGRADE_INGREDIENTS, BASIC_UPGRADE, BASIC_UPGRADE_BASE_SLOT_DESCRIPTION, BASIC_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createArmorlessIconList(), createUpgradeMaterialList());
    }

    public static SmithingTemplateItem createDowngradeTemplate() {
        return new SmithingTemplateItem(DOWNGRADE_APPLIES_TO, DOWNGRADE_INGREDIENTS, DOWNGRADE, DOWNGRADE_BASE_SLOT_DESCRIPTION, DOWNGRADE_ADDITIONS_SLOT_DESCRIPTION, createArmorlessIconList(), createUpgradeMaterialList());
    }

    protected static List<ResourceLocation> createUpgradeIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
    }

    protected static List<ResourceLocation> createArmorlessIconList() {
        return List.of(EMPTY_SLOT_SWORD, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_AXE, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
    }


    protected static List<ResourceLocation> createUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }
}
