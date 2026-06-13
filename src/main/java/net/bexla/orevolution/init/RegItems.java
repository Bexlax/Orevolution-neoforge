package net.bexla.orevolution.init;

import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.OrevolutionTiers;
import net.bexla.orevolution.content.types.item.*;
import net.bexla.orevolution.content.types.item.modeled.*;
import net.minecraft.ChatFormatting;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredItem;

public class RegItems {
    public static final ItemSubRegistryHelper HELPER = Orevolution.REGISTRY_HELPER.getItemSubHelper();

    public static void register(IEventBus modEventBus) {
        HELPER.register(modEventBus);

        if (ModList.get().isLoaded("farmersdelight")) {
            FDRegistry.FD_REG.register(modEventBus);
        }
    }

    public static DeferredItem<Item> normalItem(String name) {
        return HELPER.createItem(name, () -> new Item(new Item.Properties()));
    }

    //~//~~Crafting materials~~//~//
    /*Raw ores*/
    public static final DeferredItem<Item> RAW_TIN = normalItem("raw_tin");
    public static final DeferredItem<Item> RAW_PLATINUM = normalItem("raw_platinum");
    public static final DeferredItem<Item> RAW_TUNGSTEN = normalItem("raw_tungsten");
    public static final DeferredItem<Item> AETHERSTEEL_CHUNK = normalItem("aethersteel_chunk");
    /*Ingots*/
    public static final DeferredItem<Item> TIN_INGOT = normalItem("tin_ingot");
    public static final DeferredItem<Item> PLATINUM_INGOT = normalItem("platinum_ingot");
    public static final DeferredItem<Item> TUNGSTEN_INGOT = normalItem("tungsten_ingot");
    public static final DeferredItem<Item> AETHERSTEEL_INGOT = normalItem("aethersteel_ingot");
    public static final DeferredItem<Item> VERDITE_INGOT = normalItem("verdite_ingot");
    /*Alloys*/
    public static final DeferredItem<Item> BRONZE_ALLOY = normalItem("bronze_ingot");
    public static final DeferredItem<Item> STEEL_ALLOY = normalItem("steel_ingot");
    /*Nuggets*/
    public static final DeferredItem<Item> TIN_NUGGET = normalItem("tin_nugget");
    public static final DeferredItem<Item> PLATINUM_NUGGET = normalItem("platinum_nugget");
    public static final DeferredItem<Item> TUNGSTEN_NUGGET = normalItem("tungsten_nugget");
    public static final DeferredItem<Item> VERDITE_NUGGET = normalItem("verdite_nugget");
    public static final DeferredItem<Item> LIVINGSTONE_SHARD = normalItem("livingstone_shard");
    /*Crushed*/
    public static final DeferredItem<Item> CRUSHED_TUNGSTEN = normalItem("crushed_raw_tungsten");
    public static final DeferredItem<Item> CRUSHED_AETHERSTEEL = normalItem("crushed_raw_aethersteel");

    public static final DeferredItem<Item> AETHERSTEEL_TEMPLATE = HELPER.createItem("aethersteel_smithing_template", OrevolutionSmithingTemplate::createAethersteelUpgradeTemplate);
    public static final DeferredItem<Item> REINFORCED_TEMPLATE = HELPER.createItem("reinforced_smithing_template", OrevolutionSmithingTemplate::createReinforcedUpgradeTemplate);
    public static final DeferredItem<Item> BASIC_TEMPLATE = HELPER.createItem("basic_smithing_template", OrevolutionSmithingTemplate::createBasicUpgradeTemplate);


    //~//~~Armors, Tools and Weapons~~//~//
    //public static final DeferredItem<Item> TIN_SHIELD = HELPER.createItem("tin_shield", () -> new ShieldItem(new Item.Properties().durability(98)));
    public static final DeferredItem<Item> TIN_SWORD = registerSword("tin", OrevolutionTiers.ToolTiers.TIN, 3f, -2.4f, new Item.Properties());
    public static final DeferredItem<Item> TIN_PICKAXE = registerPickaxe("tin", OrevolutionTiers.ToolTiers.TIN, 1f, -2.6f, new Item.Properties());
    public static final DeferredItem<Item> TIN_AXE = registerAxe("tin", OrevolutionTiers.ToolTiers.TIN, 7f, -3.2f, new Item.Properties());
    public static final DeferredItem<Item> TIN_SHOVEL = registerShovel("tin", OrevolutionTiers.ToolTiers.TIN, 1.5f, -3f, new Item.Properties());
    public static final DeferredItem<Item> TIN_HOE = registerHoe("tin", OrevolutionTiers.ToolTiers.TIN, -2f, new Item.Properties());

    public static final DeferredItem<Item> PLATINUM_HELMET = HELPER.createItem("platinum_helmet", () -> new ArmorItem(OrevolutionTiers.ArmorMats.PLATINUM, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> PLATINUM_CHESTPLATE = HELPER.createItem("platinum_chestplate", () -> new ArmorItem(OrevolutionTiers.ArmorMats.PLATINUM, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> PLATINUM_LEGGINGS = HELPER.createItem("platinum_leggings", () -> new ArmorItem(OrevolutionTiers.ArmorMats.PLATINUM, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> PLATINUM_BOOTS = HELPER.createItem("platinum_boots", () -> new ArmorItem(OrevolutionTiers.ArmorMats.PLATINUM, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1)));

    //public static final DeferredItem<Item> PLATINUM_SHIELD = HELPER.createItem("platinum_shield", () -> new ShieldItem(new Item.Properties().durability(229)));
    public static final DeferredItem<Item> PLATINUM_SWORD = registerSword("platinum", OrevolutionTiers.ToolTiers.PLATINUM, 3f, -2.4f, new Item.Properties());
    public static final DeferredItem<Item> PLATINUM_PICKAXE = registerPickaxe("platinum", OrevolutionTiers.ToolTiers.PLATINUM, 1f, -2.6f, new Item.Properties());
    public static final DeferredItem<Item> PLATINUM_AXE = registerAxe("platinum", OrevolutionTiers.ToolTiers.PLATINUM, 6F, -3.2f, new Item.Properties());
    public static final DeferredItem<Item> PLATINUM_SHOVEL = registerShovel("platinum", OrevolutionTiers.ToolTiers.PLATINUM, 1.5f, -3f, new Item.Properties());
    public static final DeferredItem<Item> PLATINUM_HOE = registerHoe("platinum", OrevolutionTiers.ToolTiers.PLATINUM, -2f, new Item.Properties());

    public static final DeferredItem<Item> REINFORCED_NETHERITE_HELMET = HELPER.createItem("reinforced_netherite_helmet", () -> new ArmorItem(OrevolutionTiers.ArmorMats.REINFORCED_NT, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> REINFORCED_NETHERITE_CHESTPLATE = HELPER.createItem("reinforced_netherite_chestplate", () -> new ReinforcedArmor(OrevolutionTiers.ArmorMats.REINFORCED_NT, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> REINFORCED_NETHERITE_LEGGINGS = HELPER.createItem("reinforced_netherite_leggings", () -> new ReinforcedArmor(OrevolutionTiers.ArmorMats.REINFORCED_NT, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> REINFORCED_NETHERITE_BOOTS = HELPER.createItem("reinforced_netherite_boots", () -> new ArmorItem(OrevolutionTiers.ArmorMats.REINFORCED_NT, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> AETHERSTEEL_HELMET = HELPER.createItem("aethersteel_helmet", () -> new ArmorItem(OrevolutionTiers.ArmorMats.AETHERSTEEL, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> AETHERSTEEL_CHESTPLATE = HELPER.createItem("aethersteel_chestplate", () -> new AethersteelChestplate(OrevolutionTiers.ArmorMats.AETHERSTEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> AETHERSTEEL_LEGGINGS = HELPER.createItem("aethersteel_leggings", () -> new ArmorItem(OrevolutionTiers.ArmorMats.AETHERSTEEL, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> AETHERSTEEL_BOOTS = HELPER.createItem("aethersteel_boots", () -> new ArmorItem(OrevolutionTiers.ArmorMats.AETHERSTEEL, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1)));

    //public static final DeferredItem<Item> AETHERSTEEL_SHIELD = HELPER.createItem("aethersteel_shield", () -> new ShieldItem(new Item.Properties().durability(841)));
    public static final DeferredItem<Item> AETHERSTEEL_SWORD = registerSword("aethersteel", OrevolutionTiers.ToolTiers.AETHERSTEEL, 3f, -2.4f, new Item.Properties());
    public static final DeferredItem<Item> AETHERSTEEL_PICKAXE = registerPickaxe("aethersteel", OrevolutionTiers.ToolTiers.AETHERSTEEL, 1f, -2.6f, new Item.Properties());
    public static final DeferredItem<Item> AETHERSTEEL_AXE = registerAxe("aethersteel", OrevolutionTiers.ToolTiers.AETHERSTEEL, 5F, -3.2f, new Item.Properties());
    public static final DeferredItem<Item> AETHERSTEEL_SHOVEL = registerShovel("aethersteel", OrevolutionTiers.ToolTiers.AETHERSTEEL, 1.5f, -3f, new Item.Properties());
    public static final DeferredItem<Item> AETHERSTEEL_HOE = registerHoe("aethersteel", OrevolutionTiers.ToolTiers.AETHERSTEEL, -2f, new Item.Properties());

    public static final DeferredItem<Item> LIVINGSTONE_HELMET = HELPER.createItem("livingstone_helmet", () -> new ArmorItem(OrevolutionTiers.ArmorMats.LIVINGSTONE, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> LIVINGSTONE_CHESTPLATE = HELPER.createItem("livingstone_chestplate", () -> new LivingstoneChestplate(OrevolutionTiers.ArmorMats.LIVINGSTONE, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> LIVINGSTONE_LEGGINGS = HELPER.createItem("livingstone_leggings", () -> new ArmorItem(OrevolutionTiers.ArmorMats.LIVINGSTONE, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> LIVINGSTONE_BOOTS = HELPER.createItem("livingstone_boots", () -> new ArmorItem(OrevolutionTiers.ArmorMats.LIVINGSTONE, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1)));

    //public static final DeferredItem<Item> LIVINGSTONE_SHIELD = HELPER.createItem("livingstone_shield", () -> new ShieldItem(new Item.Properties().durability(98)));
    public static final DeferredItem<Item> LIVINGSTONE_SWORD = registerSword("livingstone", OrevolutionTiers.ToolTiers.LIVINGSTONE, 3f, -2.4f, new Item.Properties());
    public static final DeferredItem<Item> LIVINGSTONE_PICKAXE = registerPickaxe("livingstone", OrevolutionTiers.ToolTiers.LIVINGSTONE, 1f, -2.6f, new Item.Properties());
    public static final DeferredItem<Item> LIVINGSTONE_AXE = registerAxe("livingstone", OrevolutionTiers.ToolTiers.LIVINGSTONE, 5F, -3.2f, new Item.Properties());
    public static final DeferredItem<Item> LIVINGSTONE_SHOVEL = registerShovel("livingstone", OrevolutionTiers.ToolTiers.LIVINGSTONE, 1.5f, -3f, new Item.Properties());
    public static final DeferredItem<Item> LIVINGSTONE_HOE = registerHoe("livingstone", OrevolutionTiers.ToolTiers.LIVINGSTONE, -2f, new Item.Properties());

    public static final DeferredItem<Item> VERDITE_HELMET = HELPER.createItem("verdite_helmet", () -> new ArmorItem(OrevolutionTiers.ArmorMats.VERDITE, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> VERDITE_CHESTPLATE = HELPER.createItem("verdite_chestplate", () -> new ArmorItem(OrevolutionTiers.ArmorMats.VERDITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> VERDITE_LEGGINGS = HELPER.createItem("verdite_leggings", () -> new ArmorItem(OrevolutionTiers.ArmorMats.VERDITE, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> VERDITE_BOOTS = HELPER.createItem("verdite_boots", () -> new ArmorItem(OrevolutionTiers.ArmorMats.VERDITE, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1)));

    //public static final DeferredItem<Item> VERDITE_SHIELD = HELPER.createItem("verdite_shield", () -> new ShieldItem(new Item.Properties().durability(153)));
    public static final DeferredItem<Item> VERDITE_SWORD = registerSword("verdite", OrevolutionTiers.ToolTiers.VERDITE, 3f, -2.4f, new Item.Properties());
    public static final DeferredItem<Item> VERDITE_PICKAXE = registerPickaxe("verdite", OrevolutionTiers.ToolTiers.VERDITE, 1f, -2.6f, new Item.Properties());
    public static final DeferredItem<Item> VERDITE_AXE = registerAxe("verdite", OrevolutionTiers.ToolTiers.VERDITE, 6F, -3.2f, new Item.Properties());
    public static final DeferredItem<Item> VERDITE_SHOVEL = registerShovel("verdite", OrevolutionTiers.ToolTiers.VERDITE, 1.5f, -3f, new Item.Properties());
    public static final DeferredItem<Item> VERDITE_HOE = registerHoe("verdite", OrevolutionTiers.ToolTiers.VERDITE, -2f, new Item.Properties());

    /*Bronze set*/
    public static final DeferredItem<Item> BRONZE_TOTEM = normalItem("bronze_totem");
    public static final DeferredItem<Item> BRONZE_TOTEM_DIAMOND = HELPER.createItem("bronze_totem_diamond", () -> new BronzeTotemItem(new Item.Properties().stacksTo(1), MobEffects.REGENERATION, "diamond", ChatFormatting.AQUA));
    public static final DeferredItem<Item> BRONZE_TOTEM_LAPIS_LAZULI = HELPER.createItem("bronze_totem_lapis_lazuli", () -> new BronzeTotemItem(new Item.Properties().stacksTo(1), MobEffects.NIGHT_VISION, "lapis_lazuli", ChatFormatting.BLUE));
    public static final DeferredItem<Item> BRONZE_TOTEM_EMERALD = HELPER.createItem("bronze_totem_emerald", () -> new BronzeTotemItem(new Item.Properties().stacksTo(1), MobEffects.DIG_SPEED, "emerald", ChatFormatting.GREEN));

    public static final DeferredItem<Item> BRONZE_RADAR = HELPER.createItem("radar", () -> new BronzeLocatorItem(new Item.Properties()));

    public static final DeferredItem<Item> BRONZE_HELMET = HELPER.createItem("bronze_helmet", () -> new ArmorItem(OrevolutionTiers.ArmorMats.BRONZE, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> BRONZE_CHESTPLATE = HELPER.createItem("bronze_chestplate", () -> new BronzeChestplate(OrevolutionTiers.ArmorMats.BRONZE, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> BRONZE_LEGGINGS = HELPER.createItem("bronze_leggings", () -> new ArmorItem(OrevolutionTiers.ArmorMats.BRONZE, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> BRONZE_BOOTS = HELPER.createItem("bronze_boots", () -> new ArmorItem(OrevolutionTiers.ArmorMats.BRONZE, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1)));
    /*Steel set*/
    public static final DeferredItem<Item> STEEL_SCYTHE = HELPER.createItem("steel_scythe", () -> new ScytheItem(OrevolutionTiers.ToolTiers.STEEL, new Item.Properties().attributes(ScytheItem.createAttributes(OrevolutionTiers.ToolTiers.STEEL, 3f, -2.4f))));
    public static final DeferredItem<Item> STEEL_HAMMER = HELPER.createItem("steel_hammer", () -> new PickaxeItem(OrevolutionTiers.ToolTiers.STEEL, new Item.Properties().attributes(PickaxeItem.createAttributes(OrevolutionTiers.ToolTiers.STEEL, 5f, -2.2f))));
    public static final DeferredItem<Item> STEEL_DIGGER = HELPER.createItem("steel_digger", () -> new DiggerShovelItem(OrevolutionTiers.ToolTiers.STEEL, new Item.Properties().attributes(DiggerShovelItem.createAttributes(OrevolutionTiers.ToolTiers.STEEL, 2f, -2.4f))));
    public static final DeferredItem<Item> STEEL_BROADAXE = HELPER.createItem("steel_broadaxe", () -> new AxeItem(OrevolutionTiers.ToolTiers.STEEL, new Item.Properties().attributes(AxeItem.createAttributes(OrevolutionTiers.ToolTiers.STEEL, 3f, -2.9f))));
    /*Tungsten set*/
    public static final DeferredItem<Item> TUNGSTEN_HELMET = HELPER.createItem("tungsten_helmet", () -> new ArmorItem(OrevolutionTiers.ArmorMats.TUNGSTEN, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1).fireResistant()));
    public static final DeferredItem<Item> TUNGSTEN_CHESTPLATE = HELPER.createItem("tungsten_chestplate", () -> new TungstenChestplate(OrevolutionTiers.ArmorMats.TUNGSTEN, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1).fireResistant()));
    public static final DeferredItem<Item> TUNGSTEN_LEGGINGS = HELPER.createItem("tungsten_leggings", () -> new ArmorItem(OrevolutionTiers.ArmorMats.TUNGSTEN, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1).fireResistant()));
    public static final DeferredItem<Item> TUNGSTEN_BOOTS = HELPER.createItem("tungsten_boots", () -> new ArmorItem(OrevolutionTiers.ArmorMats.TUNGSTEN, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1).fireResistant()));

    //~//~~Consumables~~//~//
    public static final DeferredItem<Item> VERDITE_APPLE = HELPER.createItem("verdite_apple", () -> new VerditeApple(new Item.Properties()));
    public static final DeferredItem<Item> VERDITE_SPIDER_EYE = HELPER.createItem("verdite_spider_eye", () -> new Item(new Item.Properties().food((new FoodProperties.Builder()).nutrition(2).saturationModifier(0.8F).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 80, 0), 1.0F).build())));
    public static final DeferredItem<Item> PLATINUM_BERRIES = HELPER.createItem("platinum_berries", () -> new Item(new Item.Properties().food((new FoodProperties.Builder()).nutrition(4).saturationModifier(0.2F).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 380, 0), 1.0F).effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 380, 0), 1.0F).build())));

    public static final DeferredItem<Item> PETRIFIED_SEED = HELPER.createItem("petrified_seed", () -> new ItemNameBlockItem(RegBlocks.LIVINGSTONE_CROP.get(), new Item.Properties()));
    public static final DeferredItem<Item> DEAD_SEED = HELPER.createItem("dead_seed", () -> new ItemNameBlockItem(RegBlocks.VERDITE_CROP.get(), new Item.Properties()));

    private static DeferredItem<Item> registerSword(String name, Tier tooltier, float damage, float speed, Item.Properties itemProp) {
        return HELPER.createItem(name + "_sword",
                () -> new SwordItem(tooltier, itemProp.attributes(
                        SwordItem.createAttributes(tooltier, damage, speed)
                )));
    }

    private static DeferredItem<Item> registerPickaxe(String name, Tier tooltier, float damage, float speed, Item.Properties itemProp) {
        return HELPER.createItem(name + "_pickaxe",
                () -> new PickaxeItem(tooltier, itemProp.attributes(
                        PickaxeItem.createAttributes(tooltier, damage, speed)
                )));
    }

    private static DeferredItem<Item> registerAxe(String name, Tier tooltier, float damage, float speed, Item.Properties itemProp) {
        return HELPER.createItem(name + "_axe",
                () -> new AxeItem(tooltier, itemProp.attributes(
                        AxeItem.createAttributes(tooltier, damage, speed)
                )));
    }

    private static DeferredItem<Item> registerShovel(String name, Tier tooltier, float damage, float speed, Item.Properties itemProp) {
        return HELPER.createItem(name + "_shovel",
                () -> new ShovelItem(tooltier, itemProp.attributes(
                        ShovelItem.createAttributes(tooltier, damage, speed)
                )));
    }

    private static DeferredItem<Item> registerHoe(String name, Tier tooltier, float speed, Item.Properties itemProp) {
        return HELPER.createItem(name + "_hoe",
                () -> new HoeItem(tooltier, itemProp.attributes(
                        HoeItem.createAttributes(tooltier, 1, speed)
                )));
    }
}
