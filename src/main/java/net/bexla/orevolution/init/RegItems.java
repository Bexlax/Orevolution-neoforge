package net.bexla.orevolution.init;

import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.OrevolutionTiers;
import net.bexla.orevolution.content.types.item.*;
import net.bexla.orevolution.content.types.item.modeled.AethersteelArmor;
import net.bexla.orevolution.content.types.item.modeled.BronzeArmor;
import net.bexla.orevolution.content.types.item.modeled.LivingstoneArmor;
import net.bexla.orevolution.content.types.item.modeled.TungstenArmor;
import net.bexla.orevolution.init.modcompat.FDRegistry;
import net.bexla.orevolution.init.modcompat.SBRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.component.Fireworks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class RegItems {
    public static final ItemSubRegistryHelper HELPER = Orevolution.REGISTRY_HELPER.getItemSubHelper();
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, Orevolution.MODID);

    public static void register(IEventBus modEventBus) {
        HELPER.register(modEventBus);

        if (ModList.get().isLoaded("farmersdelight")) {
            FDRegistry.FD_REG.register(modEventBus);
        }

        if(ModList.get().isLoaded("spears")) {
            SBRegistry.SB_REG.register(modEventBus);
        }
    }

    public static DeferredItem<Item> normalItem(String name) {
        return HELPER.createItem(name, () -> new Item(new Item.Properties()));
    }

    public static DeferredItem<Item> normalItem(String name, Item.Properties properties) {
        return HELPER.createItem(name, () -> new Item(properties));
    }

    // LEGACY ITEMS, THESE GET REPLACED WHEN DETECTED
    public static final DeferredItem<Item> R_HELMET = normalItem("reinforced_netherite_helmet");
    public static final DeferredItem<Item> R_CHESTPLATE = normalItem("reinforced_netherite_chestplate");
    public static final DeferredItem<Item> R_LEGGINGS = normalItem("reinforced_netherite_leggings");
    public static final DeferredItem<Item> R_BOOTS = normalItem("reinforced_netherite_boots");

    //~//~~Crafting materials~~//~//
    /*Raw ores*/
    public static final DeferredItem<Item> RAW_TIN = normalItem("raw_tin");
    public static final DeferredItem<Item> RAW_CASSITERITE = normalItem("raw_cassiterite");
    public static final DeferredItem<Item> RAW_PLATINUM = normalItem("raw_platinum");
    public static final DeferredItem<Item> RAW_TUNGSTEN = normalItem("raw_tungsten", new Item.Properties().fireResistant());
    public static final DeferredItem<Item> AETHERSTEEL_CHUNK = normalItem("aethersteel_chunk", new Item.Properties().fireResistant());
    /*Gems*/
    public static final DeferredItem<Item> CELESTITE_SHARD = normalItem("celestite_shard");
    /*Ingots*/
    public static final DeferredItem<Item> TIN_INGOT = normalItem("tin_ingot");
    public static final DeferredItem<Item> CASSITERITE_INGOT = normalItem("cassiterite_ingot");
    public static final DeferredItem<Item> PLATINUM_INGOT = normalItem("platinum_ingot");
    public static final DeferredItem<Item> TUNGSTEN_INGOT = HELPER.createItem("tungsten_ingot", () -> new BarterItem(new Item.Properties().fireResistant()));
    public static final DeferredItem<Item> AETHERSTEEL_INGOT = normalItem("aethersteel_ingot", new Item.Properties().fireResistant());
    public static final DeferredItem<Item> VERDITE_INGOT = normalItem("verdite_ingot");
    /*Alloys*/
    public static final DeferredItem<Item> BRONZE_ALLOY = normalItem("bronze_ingot");
    public static final DeferredItem<Item> STEEL_ALLOY = normalItem("steel_ingot");
    /*Nuggets*/
    public static final DeferredItem<Item> TIN_NUGGET = normalItem("tin_nugget");
    public static final DeferredItem<Item> CASSITERITE_NUGGET = normalItem("cassiterite_nugget");
    public static final DeferredItem<Item> PLATINUM_NUGGET = normalItem("platinum_nugget");
    public static final DeferredItem<Item> TUNGSTEN_NUGGET = normalItem("tungsten_nugget", new Item.Properties().fireResistant());
    public static final DeferredItem<Item> VERDITE_NUGGET = normalItem("verdite_nugget");
    public static final DeferredItem<Item> LIVINGSTONE_SHARD = normalItem("livingstone_shard");
    public static final DeferredItem<Item> QUARTZ_CHIP = normalItem("quartz_chip");
    /*Others*/
    public static final DeferredItem<Item> PROFESSIONAL_FIREWORK_ROCKET = HELPER.createItem("professional_firework_rocket", () -> new FireworkRocketItem(new Item.Properties().component(DataComponents.FIREWORKS, new Fireworks(3, List.of()))));
    public static final DeferredItem<Item> PYRITE = normalItem("pyrite");

    public static final DeferredItem<Item> CRUSHED_TUNGSTEN = normalItem("crushed_raw_tungsten", new Item.Properties().fireResistant());
    public static final DeferredItem<Item> CRUSHED_AETHERSTEEL = normalItem("crushed_raw_aethersteel", new Item.Properties().fireResistant());

    public static final DeferredItem<Item> FIERY_ARROW = HELPER.createItem("fiery_arrow", () -> new FieryArrowItem(new Item.Properties()));

    public static final DeferredItem<Item> AETHERSTEEL_TEMPLATE = HELPER.createItem("aethersteel_smithing_template", OrevolutionSmithingTemplate::createAethersteelUpgradeTemplate);
    public static final DeferredItem<Item> REINFORCED_TEMPLATE = HELPER.createItem("reinforced_smithing_template", OrevolutionSmithingTemplate::createReinforcedUpgradeTemplate);
    public static final DeferredItem<Item> COATING_TEMPLATE = HELPER.createItem("coating_smithing_template", OrevolutionSmithingTemplate::createCoatingUpgradeTemplate);
    public static final DeferredItem<Item> BASIC_TEMPLATE = HELPER.createItem("basic_smithing_template", OrevolutionSmithingTemplate::createBasicUpgradeTemplate);
    public static final DeferredItem<Item> DOWNGRADE_TEMPLATE = HELPER.createItem("downgrade_smithing_template", OrevolutionSmithingTemplate::createDowngradeTemplate);

    /*Potions*/
    public static final Holder<Potion> QUICKNESS = POTIONS.register("quickness", () -> new Potion(new MobEffectInstance(RegMobEffects.QUICKNESS, 3600)));
    public static final Holder<Potion> QUICKNESS_LONG = POTIONS.register("quickness_long", () -> new Potion(new MobEffectInstance(RegMobEffects.QUICKNESS, 9600)));
    public static final Holder<Potion> QUICKNESS_STRONG = POTIONS.register("quickness_strong", () -> new Potion(new MobEffectInstance(RegMobEffects.QUICKNESS, 1800, 1)));

    public static final Holder<Potion> PURIFICATION = POTIONS.register("purification", () -> new Potion(new MobEffectInstance(RegMobEffects.PURIFICATION, 900)));
    public static final Holder<Potion> PURIFICATION_LONG = POTIONS.register("purification_long", () -> new Potion(new MobEffectInstance(RegMobEffects.PURIFICATION, 1800)));
    public static final Holder<Potion> PURIFICATION_STRONG = POTIONS.register("purification_strong", () -> new Potion(new MobEffectInstance(RegMobEffects.PURIFICATION, 432, 1)));

    public static final Holder<Potion> INTOXICATION = POTIONS.register("intoxication", () -> new Potion(
            new MobEffectInstance(MobEffects.CONFUSION, 1800),
            new MobEffectInstance(MobEffects.POISON, 1800, 1),
            new MobEffectInstance(MobEffects.HUNGER, 1800, 1)
    ));
    public static final Holder<Potion> INTOXICATION_LONG = POTIONS.register("intoxication_long", () -> new Potion(
            new MobEffectInstance(MobEffects.CONFUSION, 4300),
            new MobEffectInstance(MobEffects.POISON, 4300, 1),
            new MobEffectInstance(MobEffects.HUNGER, 4300, 1)
    ));
    public static final Holder<Potion> INTOXICATION_STRONG = POTIONS.register("intoxication_strong", () -> new Potion(
            new MobEffectInstance(MobEffects.CONFUSION, 800),
            new MobEffectInstance(MobEffects.POISON, 800, 2),
            new MobEffectInstance(MobEffects.HUNGER, 800, 2)
    ));

    public static final DeferredItem<Item> GLOWING_BOTTLE = HELPER.createItem("glowing_bottle", () -> new SpecialBottleItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).build()),
            0, 32000, MobEffects.GLOWING));

    public static final DeferredItem<Item> LIFE_BOTTLE = HELPER.createItem("life_bottle", () -> new SpecialBottleItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).build()),
            2, 1400, MobEffects.REGENERATION));
    public static final DeferredItem<Item> FIERCE_BOTTLE = HELPER.createItem("fierce_bottle", () -> new SpecialBottleItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).build()),
            2, 1400, MobEffects.DAMAGE_BOOST));
    public static final DeferredItem<Item> LIGHTNING_BOTTLE = HELPER.createItem("lightning_bottle", () -> new SpecialBottleItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).build()),
            2, 1400, MobEffects.MOVEMENT_SPEED));

    public static final DeferredItem<Item> BRONZE_HORSE_ARMOR = HELPER.createItem("bronze_horse_armor", () -> new AnimalArmorItem(OrevolutionTiers.ArmorMats.BRONZE, AnimalArmorItem.BodyType.EQUESTRIAN, false, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> STEEL_HORSE_ARMOR = HELPER.createItem("steel_horse_armor", () -> new AnimalArmorItem(OrevolutionTiers.ArmorMats.STEEL, AnimalArmorItem.BodyType.EQUESTRIAN, false, new Item.Properties().stacksTo(1)));

    //~//~~Armors, Tools and Weapons~~//~//
    public static final DeferredItem<Item> TIN_SHIELD = HELPER.createItem("tin_shield", () -> new ShieldItem(new Item.Properties().durability(98)));
    public static final DeferredItem<Item> TIN_SWORD = registerSword("tin", OrevolutionTiers.ToolTiers.TIN, new Item.Properties());
    public static final DeferredItem<Item> TIN_PICKAXE = registerPickaxe("tin", OrevolutionTiers.ToolTiers.TIN, new Item.Properties());
    public static final DeferredItem<Item> TIN_AXE = registerAxe("tin", OrevolutionTiers.ToolTiers.TIN, 7f, new Item.Properties());
    public static final DeferredItem<Item> TIN_SHOVEL = registerShovel("tin", OrevolutionTiers.ToolTiers.TIN, new Item.Properties());
    public static final DeferredItem<Item> TIN_HOE = registerHoe("tin", OrevolutionTiers.ToolTiers.TIN, new Item.Properties());
    public static final DeferredItem<Item> GEO_SCANNER = HELPER.createItem("geoscanner", () -> new GeoScannerItem(new Item.Properties()));

    public static final DeferredItem<Item> CASSITERITE_SWORD = registerSword("cassiterite", OrevolutionTiers.ToolTiers.CASSITERITE, new Item.Properties());
    public static final DeferredItem<Item> CASSITERITE_PICKAXE = registerPickaxe("cassiterite", OrevolutionTiers.ToolTiers.CASSITERITE, new Item.Properties());
    public static final DeferredItem<Item> CASSITERITE_AXE = registerAxe("cassiterite", OrevolutionTiers.ToolTiers.CASSITERITE, 7f, new Item.Properties());
    public static final DeferredItem<Item> CASSITERITE_SHOVEL = registerShovel("cassiterite", OrevolutionTiers.ToolTiers.CASSITERITE, new Item.Properties());
    public static final DeferredItem<Item> CASSITERITE_HOE = registerHoe("cassiterite", OrevolutionTiers.ToolTiers.CASSITERITE, new Item.Properties());

    public static final DeferredItem<Item> PLATINUM_HELMET = HELPER.createItem("platinum_helmet", () -> new ArmorItem(OrevolutionTiers.ArmorMats.PLATINUM, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(26)).stacksTo(1)));
    public static final DeferredItem<Item> PLATINUM_CHESTPLATE = HELPER.createItem("platinum_chestplate", () -> new ArmorItem(OrevolutionTiers.ArmorMats.PLATINUM, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(26)).stacksTo(1)));
    public static final DeferredItem<Item> PLATINUM_LEGGINGS = HELPER.createItem("platinum_leggings", () -> new ArmorItem(OrevolutionTiers.ArmorMats.PLATINUM, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(26)).stacksTo(1)));
    public static final DeferredItem<Item> PLATINUM_BOOTS = HELPER.createItem("platinum_boots", () -> new ArmorItem(OrevolutionTiers.ArmorMats.PLATINUM, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(26)).stacksTo(1)));

    public static final DeferredItem<Item> PLATINUM_SHIELD = HELPER.createItem("platinum_shield", () -> new ShieldItem(new Item.Properties().durability(229)));
    public static final DeferredItem<Item> PLATINUM_SWORD = registerSword("platinum", OrevolutionTiers.ToolTiers.PLATINUM, new Item.Properties());
    public static final DeferredItem<Item> PLATINUM_PICKAXE = registerPickaxe("platinum", OrevolutionTiers.ToolTiers.PLATINUM, new Item.Properties());
    public static final DeferredItem<Item> PLATINUM_AXE = registerAxe("platinum", OrevolutionTiers.ToolTiers.PLATINUM, 6F, new Item.Properties());
    public static final DeferredItem<Item> PLATINUM_SHOVEL = registerShovel("platinum", OrevolutionTiers.ToolTiers.PLATINUM, new Item.Properties());
    public static final DeferredItem<Item> PLATINUM_HOE = registerHoe("platinum", OrevolutionTiers.ToolTiers.PLATINUM, new Item.Properties());
    public static final DeferredItem<Item> MOTION_DETECTOR = HELPER.createItem("motion_detector", () -> new MotionDetectorItem(new Item.Properties()));

    public static final DeferredItem<Item> AETHERSTEEL_HELMET = HELPER.createItem("aethersteel_helmet", () -> new ArmorItem(OrevolutionTiers.ArmorMats.AETHERSTEEL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(42)).stacksTo(1)));
    public static final DeferredItem<Item> AETHERSTEEL_CHESTPLATE = HELPER.createItem("aethersteel_chestplate", () -> new AethersteelArmor(OrevolutionTiers.ArmorMats.AETHERSTEEL, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(42)).stacksTo(1)));
    public static final DeferredItem<Item> AETHERSTEEL_LEGGINGS = HELPER.createItem("aethersteel_leggings", () -> new ArmorItem(OrevolutionTiers.ArmorMats.AETHERSTEEL, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(42)).stacksTo(1)));
    public static final DeferredItem<Item> AETHERSTEEL_BOOTS = HELPER.createItem("aethersteel_boots", () -> new ArmorItem(OrevolutionTiers.ArmorMats.AETHERSTEEL, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(42)).stacksTo(1)));

    public static final DeferredItem<Item> AETHERSTEEL_SHIELD = HELPER.createItem("aethersteel_shield", () -> new ShieldItem(new Item.Properties().durability(841)));
    public static final DeferredItem<Item> AETHERSTEEL_SWORD = registerSword("aethersteel", OrevolutionTiers.ToolTiers.AETHERSTEEL, new Item.Properties());
    public static final DeferredItem<Item> AETHERSTEEL_PICKAXE = registerPickaxe("aethersteel", OrevolutionTiers.ToolTiers.AETHERSTEEL, new Item.Properties());
    public static final DeferredItem<Item> AETHERSTEEL_AXE = registerAxe("aethersteel", OrevolutionTiers.ToolTiers.AETHERSTEEL, 5F, new Item.Properties());
    public static final DeferredItem<Item> AETHERSTEEL_SHOVEL = registerShovel("aethersteel", OrevolutionTiers.ToolTiers.AETHERSTEEL, new Item.Properties());
    public static final DeferredItem<Item> AETHERSTEEL_HOE = registerHoe("aethersteel", OrevolutionTiers.ToolTiers.AETHERSTEEL, new Item.Properties());

    public static final DeferredItem<Item> MOONSTONE_HELMET = HELPER.createItem("moonstone_helmet", () -> new ArmorItem(OrevolutionTiers.ArmorMats.MOONSTONE, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(21)).stacksTo(1)));
    public static final DeferredItem<Item> MOONSTONE_CHESTPLATE = HELPER.createItem("moonstone_chestplate", () -> new ArmorItem(OrevolutionTiers.ArmorMats.MOONSTONE, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(21)).stacksTo(1)));
    public static final DeferredItem<Item> MOONSTONE_LEGGINGS = HELPER.createItem("moonstone_leggings", () -> new ArmorItem(OrevolutionTiers.ArmorMats.MOONSTONE, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(21)).stacksTo(1)));
    public static final DeferredItem<Item> MOONSTONE_BOOTS = HELPER.createItem("moonstone_boots", () -> new ArmorItem(OrevolutionTiers.ArmorMats.MOONSTONE, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(21)).stacksTo(1)));

    public static final DeferredItem<Item> MOONSTONE_SHIELD = HELPER.createItem("moonstone_shield", () -> new ShieldItem(new Item.Properties().durability(633)));
    public static final DeferredItem<Item> MOONSTONE_SWORD = registerSword("moonstone", OrevolutionTiers.ToolTiers.MOONSTONE, new Item.Properties());
    public static final DeferredItem<Item> MOONSTONE_PICKAXE = registerPickaxe("moonstone", OrevolutionTiers.ToolTiers.MOONSTONE, new Item.Properties());
    public static final DeferredItem<Item> MOONSTONE_AXE = registerAxe("moonstone", OrevolutionTiers.ToolTiers.MOONSTONE, 6F, new Item.Properties());
    public static final DeferredItem<Item> MOONSTONE_SHOVEL = registerShovel("moonstone", OrevolutionTiers.ToolTiers.MOONSTONE, new Item.Properties());
    public static final DeferredItem<Item> MOONSTONE_HOE = registerHoe("moonstone", OrevolutionTiers.ToolTiers.MOONSTONE, new Item.Properties());

    public static final DeferredItem<Item> LIVINGSTONE_HELMET = HELPER.createItem("livingstone_helmet", () -> new ArmorItem(OrevolutionTiers.ArmorMats.LIVINGSTONE, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(13)).stacksTo(1)));
    public static final DeferredItem<Item> LIVINGSTONE_CHESTPLATE = HELPER.createItem("livingstone_chestplate", () -> new LivingstoneArmor(OrevolutionTiers.ArmorMats.LIVINGSTONE, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(13)).stacksTo(1)));
    public static final DeferredItem<Item> LIVINGSTONE_LEGGINGS = HELPER.createItem("livingstone_leggings", () -> new ArmorItem(OrevolutionTiers.ArmorMats.LIVINGSTONE, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(13)).stacksTo(1)));
    public static final DeferredItem<Item> LIVINGSTONE_BOOTS = HELPER.createItem("livingstone_boots", () -> new ArmorItem(OrevolutionTiers.ArmorMats.LIVINGSTONE, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(13)).stacksTo(1)));

    public static final DeferredItem<Item> LIVINGSTONE_SHIELD = HELPER.createItem("livingstone_shield", () -> new ShieldItem(new Item.Properties().durability(98)));
    public static final DeferredItem<Item> LIVINGSTONE_SWORD = registerSword("livingstone", OrevolutionTiers.ToolTiers.LIVINGSTONE, new Item.Properties());
    public static final DeferredItem<Item> LIVINGSTONE_PICKAXE = registerPickaxe("livingstone", OrevolutionTiers.ToolTiers.LIVINGSTONE, new Item.Properties());
    public static final DeferredItem<Item> LIVINGSTONE_AXE = registerAxe("livingstone", OrevolutionTiers.ToolTiers.LIVINGSTONE, 5F, new Item.Properties());
    public static final DeferredItem<Item> LIVINGSTONE_SHOVEL = registerShovel("livingstone", OrevolutionTiers.ToolTiers.LIVINGSTONE, new Item.Properties());
    public static final DeferredItem<Item> LIVINGSTONE_HOE = registerHoe("livingstone", OrevolutionTiers.ToolTiers.LIVINGSTONE, new Item.Properties());

    public static final DeferredItem<Item> VERDITE_HELMET = HELPER.createItem("verdite_helmet", () -> new ArmorItem(OrevolutionTiers.ArmorMats.VERDITE, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(24)).stacksTo(1)));
    public static final DeferredItem<Item> VERDITE_CHESTPLATE = HELPER.createItem("verdite_chestplate", () -> new ArmorItem(OrevolutionTiers.ArmorMats.VERDITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(24)).stacksTo(1)));
    public static final DeferredItem<Item> VERDITE_LEGGINGS = HELPER.createItem("verdite_leggings", () -> new ArmorItem(OrevolutionTiers.ArmorMats.VERDITE, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(24)).stacksTo(1)));
    public static final DeferredItem<Item> VERDITE_BOOTS = HELPER.createItem("verdite_boots", () -> new ArmorItem(OrevolutionTiers.ArmorMats.VERDITE, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(24)).stacksTo(1)));

    public static final DeferredItem<Item> VERDITE_SHIELD = HELPER.createItem("verdite_shield", () -> new ShieldItem(new Item.Properties().durability(153)));
    public static final DeferredItem<Item> VERDITE_SWORD = registerSword("verdite", OrevolutionTiers.ToolTiers.VERDITE, new Item.Properties());
    public static final DeferredItem<Item> VERDITE_PICKAXE = registerPickaxe("verdite", OrevolutionTiers.ToolTiers.VERDITE, new Item.Properties());
    public static final DeferredItem<Item> VERDITE_AXE = registerAxe("verdite", OrevolutionTiers.ToolTiers.VERDITE, 6F, new Item.Properties());
    public static final DeferredItem<Item> VERDITE_SHOVEL = registerShovel("verdite", OrevolutionTiers.ToolTiers.VERDITE, new Item.Properties());
    public static final DeferredItem<Item> VERDITE_HOE = registerHoe("verdite", OrevolutionTiers.ToolTiers.VERDITE, new Item.Properties());

    /*Bronze set*/
    public static final DeferredItem<Item> BRONZE_TOTEM = HELPER.createItem("bronze_totem",
            () -> new BronzeTotemItem(new Item.Properties().durability(100)));

    public static final DeferredItem<Item> BRONZE_RADAR = HELPER.createItem("radar", () -> new BronzeRadarItem(new Item.Properties()));

    public static final DeferredItem<Item> BRONZE_HELMET = HELPER.createItem("bronze_helmet", () -> new ArmorItem(OrevolutionTiers.ArmorMats.BRONZE, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(11)).stacksTo(1)));
    public static final DeferredItem<Item> BRONZE_CHESTPLATE = HELPER.createItem("bronze_chestplate", () -> new BronzeArmor(OrevolutionTiers.ArmorMats.BRONZE, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(11)).stacksTo(1)));
    public static final DeferredItem<Item> BRONZE_LEGGINGS = HELPER.createItem("bronze_leggings", () -> new ArmorItem(OrevolutionTiers.ArmorMats.BRONZE, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(11)).stacksTo(1)));
    public static final DeferredItem<Item> BRONZE_BOOTS = HELPER.createItem("bronze_boots", () -> new ArmorItem(OrevolutionTiers.ArmorMats.BRONZE, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(11)).stacksTo(1)));

    /*Steel set*/
    public static final DeferredItem<Item> STEEL_HEAVYWORK_SWORD = registerSword("steel_heavywork", OrevolutionTiers.ToolTiers.STEEL, -2.8f, new Item.Properties());
    public static final DeferredItem<Item> STEEL_HEAVYWORK_PICKAXE = registerPickaxe("steel_heavywork", OrevolutionTiers.ToolTiers.STEEL, -3.2f, new Item.Properties());
    public static final DeferredItem<Item> STEEL_HEAVYWORK_AXE = registerAxe("steel_heavywork", OrevolutionTiers.ToolTiers.STEEL, 6f, -3.6f, new Item.Properties());
    public static final DeferredItem<Item> STEEL_HEAVYWORK_SHOVEL = registerShovel("steel_heavywork", OrevolutionTiers.ToolTiers.STEEL, -3.4f, new Item.Properties());
    public static final DeferredItem<Item> STEEL_HEAVYWORK_HOE = registerHoe("steel_heavywork", OrevolutionTiers.ToolTiers.STEEL, -2.4f, new Item.Properties());

    /*Tungsten set*/
    public static final DeferredItem<Item> TUNGSTEN_HELMET = HELPER.createItem("tungsten_helmet", () -> new TungstenArmor(OrevolutionTiers.ArmorMats.TUNGSTEN, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(41)).stacksTo(1).fireResistant()));
    public static final DeferredItem<Item> TUNGSTEN_CHESTPLATE = HELPER.createItem("tungsten_chestplate", () -> new TungstenArmor(OrevolutionTiers.ArmorMats.TUNGSTEN, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(41)).stacksTo(1).fireResistant()));
    public static final DeferredItem<Item> TUNGSTEN_LEGGINGS = HELPER.createItem("tungsten_leggings", () -> new TungstenArmor(OrevolutionTiers.ArmorMats.TUNGSTEN, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(41)).stacksTo(1).fireResistant()));
    public static final DeferredItem<Item> TUNGSTEN_BOOTS = HELPER.createItem("tungsten_boots", () -> new TungstenArmor(OrevolutionTiers.ArmorMats.TUNGSTEN, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(41)).stacksTo(1).fireResistant()));

    public static final DeferredItem<Item> TUNGSTEN_SWORD = registerSword("tungsten", OrevolutionTiers.ToolTiers.TUNGSTEN, new Item.Properties().fireResistant());
    public static final DeferredItem<Item> TUNGSTEN_PICKAXE = registerPickaxe("tungsten", OrevolutionTiers.ToolTiers.TUNGSTEN, new Item.Properties().fireResistant());
    public static final DeferredItem<Item> TUNGSTEN_AXE = registerAxe("tungsten", OrevolutionTiers.ToolTiers.TUNGSTEN, 6F, new Item.Properties().fireResistant());
    public static final DeferredItem<Item> TUNGSTEN_SHOVEL = registerShovel("tungsten", OrevolutionTiers.ToolTiers.TUNGSTEN, new Item.Properties().fireResistant());
    public static final DeferredItem<Item> TUNGSTEN_HOE = registerHoe("tungsten", OrevolutionTiers.ToolTiers.TUNGSTEN, new Item.Properties().fireResistant());

    //~//~~Consumables~~//~//
    public static final DeferredItem<Item> PLATINUM_APPLE = HELPER.createItem("platinum_apple", () -> new Item(new Item.Properties().rarity(Rarity.RARE)
            .food((new FoodProperties.Builder()).nutrition(2).saturationModifier(1.6F)
                    .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2000, 0), 1.0F)
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1400, 0), 1.0F)
                    .alwaysEdible()
            .build())));

    public static final DeferredItem<Item> VERDITE_APPLE = HELPER.createItem("verdite_apple", () -> new VerditeApple(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> VERDITE_SPIDER_EYE = HELPER.createItem("verdite_spider_eye", () -> new Item(new Item.Properties()
            .food((new FoodProperties.Builder()).nutrition(2).saturationModifier(0.8F)
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 80, 0), 1.0F)
            .build())));
    public static final DeferredItem<Item> PLATINUM_BERRIES = HELPER.createItem("platinum_berries", () -> new Item(new Item.Properties()
            .food((new FoodProperties.Builder()).nutrition(4).saturationModifier(0.3F)
                    .effect(() -> new MobEffectInstance(MobEffects.LUCK, 380, 0), 1.0F)
                    .effect(() -> new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 380, 0), 1.0F)
                    .alwaysEdible()
            .build())));
    public static final DeferredItem<Item> ANCIENT_FRUIT = HELPER.createItem("ancient_fruit", () -> new Item(new Item.Properties()
            .food((new FoodProperties.Builder()).nutrition(3).saturationModifier(0.4F)
                    .effect(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 380, 0), 1.0F)
                    .alwaysEdible()
            .build())));
    public static final DeferredItem<Item> ANCIENT_STEW = HELPER.createItem("ancient_stew", () -> new Item(new Item.Properties().stacksTo(1)
            .food((new FoodProperties.Builder()).nutrition(8)
                    .effect(() -> new MobEffectInstance(MobEffects.INVISIBILITY, 1520, 0), 1.0F)
            .build())));

    public static final DeferredItem<Item> FOOLS_APPLE = HELPER.createItem("fools_apple", () -> new Item(new Item.Properties()
            .food((new FoodProperties.Builder()).nutrition(5).saturationModifier(0.35F)
                    .effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING, 300, 0), 1.0F)
                    .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 160, 1), 1.0F)
                    .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 80, 1), 0.75F)
            .build())));
    public static final DeferredItem<Item> FOOLS_CARROT = HELPER.createItem("fools_carrot", () -> new Item(new Item.Properties()
            .food((new FoodProperties.Builder()).nutrition(4).saturationModifier(0.65F)
                    .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 160, 1), 1.0F)
                    .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 80, 1), 0.75F)
            .build())));

    public static final DeferredItem<Item> PETRIFIED_SEED = HELPER.createItem("petrified_seed", () -> new ItemNameBlockItem(RegBlocks.LIVINGSTONE_CROP.get(), new Item.Properties()));
    public static final DeferredItem<Item> DEAD_SEED = HELPER.createItem("dead_seed", () -> new ItemNameBlockItem(RegBlocks.VERDITE_CROP.get(), new Item.Properties()));
    public static final DeferredItem<Item> VINNELIO = HELPER.createItem("vinnelio_vines", () -> new ItemNameBlockItem(RegBlocks.VINNELIO.get(), new Item.Properties()));

    private static DeferredItem<Item> registerSword(String name, Tier tooltier, Item.Properties itemProp) {
        return HELPER.createItem(name + "_sword",
                () -> new SwordItem(tooltier, itemProp.attributes(
                        SwordItem.createAttributes(tooltier, 3.0f, -2.4f)
                )));
    }

    private static DeferredItem<Item> registerSword(String name, Tier tooltier, float attspeed, Item.Properties itemProp) {
        return HELPER.createItem(name + "_sword",
                () -> new SwordItem(tooltier, itemProp.attributes(
                        SwordItem.createAttributes(tooltier, 3.0f, attspeed)
                )));
    }

    private static DeferredItem<Item> registerPickaxe(String name, Tier tooltier, Item.Properties itemProp) {
        return HELPER.createItem(name + "_pickaxe",
                () -> new PickaxeItem(tooltier, itemProp.attributes(
                        PickaxeItem.createAttributes(tooltier, 1.0f, -2.8f)
                )));
    }

    private static DeferredItem<Item> registerPickaxe(String name, Tier tooltier, float attspeed, Item.Properties itemProp) {
        return HELPER.createItem(name + "_pickaxe",
                () -> new PickaxeItem(tooltier, itemProp.attributes(
                        PickaxeItem.createAttributes(tooltier, 1.0f, attspeed
                ))));
    }

    private static DeferredItem<Item> registerAxe(String name, Tier tooltier, float damage, Item.Properties itemProp) {
        return HELPER.createItem(name + "_axe",
                () -> new AxeItem(tooltier, itemProp.attributes(
                        AxeItem.createAttributes(tooltier, damage, -3.2f)
                )));
    }

    private static DeferredItem<Item> registerAxe(String name, Tier tooltier, float damage, float attspeed, Item.Properties itemProp) {
        return HELPER.createItem(name + "_axe",
                () -> new AxeItem(tooltier, itemProp.attributes(
                        AxeItem.createAttributes(tooltier, damage, attspeed)
                )));
    }

    private static DeferredItem<Item> registerShovel(String name, Tier tooltier, Item.Properties itemProp) {
        return HELPER.createItem(name + "_shovel",
                () -> new ShovelItem(tooltier, itemProp.attributes(
                        ShovelItem.createAttributes(tooltier, 1.5f,  -3.0f)
                )));
    }

    private static DeferredItem<Item> registerShovel(String name, Tier tooltier, float attspeed, Item.Properties itemProp) {
        return HELPER.createItem(name + "_shovel",
                () -> new ShovelItem(tooltier, itemProp.attributes(
                        ShovelItem.createAttributes(tooltier, 1.5f,  attspeed)
                )));
    }

    private static DeferredItem<Item> registerHoe(String name, Tier tooltier, Item.Properties itemProp) {
        return HELPER.createItem(name + "_hoe",
                () -> new HoeItem(tooltier, itemProp.attributes(
                        HoeItem.createAttributes(tooltier, 1, -2.0f)
                )));
    }

    private static DeferredItem<Item> registerHoe(String name, Tier tooltier, float attspeed, Item.Properties itemProp) {
        return HELPER.createItem(name + "_hoe",
                () -> new HoeItem(tooltier, itemProp.attributes(
                        HoeItem.createAttributes(tooltier, 1, attspeed)
                )));
    }
}
