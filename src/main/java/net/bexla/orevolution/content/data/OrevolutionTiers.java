package net.bexla.orevolution.content.data;

import com.teamabnormals.blueprint.core.api.BlueprintItemTier;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterial.Layer;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

import static net.bexla.orevolution.Orevolution.lc;

public class OrevolutionTiers {

    public static class ArmorMats {
        public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, Orevolution.MODID);

        public static final DeferredHolder<ArmorMaterial, ArmorMaterial> BRONZE = register("bronze",
                defense(1, 3, 5, 1, 8),
                17, SoundEvents.ARMOR_EQUIP_DIAMOND,
                0.0F    ,
                0.0F,
                () -> Ingredient.of(OrevolutionTags.Items.platIngots)
        );
        public static final DeferredHolder<ArmorMaterial, ArmorMaterial> TUNGSTEN = register("tungsten",
                defense(2, 4, 5, 2, 8),
                17, SoundEvents.ARMOR_EQUIP_DIAMOND,
                0.0F,
                0.0F,
                () -> Ingredient.of(OrevolutionTags.Items.platIngots)
        );
        public static final DeferredHolder<ArmorMaterial, ArmorMaterial> LIVINGSTONE = register("livingstone",
                defense(1, 3, 5, 1, 8),
                17, SoundEvents.ARMOR_EQUIP_DIAMOND,
                0.0F,
                0.0F,
                () -> Ingredient.of(OrevolutionTags.Items.platIngots)
        );
        public static final DeferredHolder<ArmorMaterial, ArmorMaterial> VERDITE = register("verdite",
                defense(2, 5, 6, 2, 8),
                17, SoundEvents.ARMOR_EQUIP_DIAMOND,
                0.0F,
                0.0F,
                () -> Ingredient.of(OrevolutionTags.Items.platIngots)
        );
        public static final DeferredHolder<ArmorMaterial, ArmorMaterial> PLATINUM = register("platinum",
                defense(2, 5, 6, 2, 8),
                17, SoundEvents.ARMOR_EQUIP_DIAMOND,
                0.0F,
                0.0F,
                () -> Ingredient.of(OrevolutionTags.Items.platIngots)
        );
        public static final DeferredHolder<ArmorMaterial, ArmorMaterial> REINFORCED_NT = register("reinforced_netherite",
                defense(3, 6, 8, 3, 11),
                17, SoundEvents.ARMOR_EQUIP_DIAMOND,
                0.0F,
                0.0F,
                () -> Ingredient.of(Tags.Items.INGOTS_NETHERITE)
        );
        public static final DeferredHolder<ArmorMaterial, ArmorMaterial> AETHERSTEEL = register("aethersteel",
                defense(4, 7, 8, 4, 12),
                17, SoundEvents.ARMOR_EQUIP_DIAMOND,
                0.0F,
                0.0F,
                () -> Ingredient.of(OrevolutionTags.Items.enderiteIngots)
        );

        public static EnumMap<Type, Integer> defense(int boots, int leggings, int chestplate, int helmet, int body) {
            return Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, boots);
                map.put(ArmorItem.Type.LEGGINGS, leggings);
                map.put(ArmorItem.Type.CHESTPLATE, chestplate);
                map.put(ArmorItem.Type.HELMET, helmet);
                map.put(ArmorItem.Type.BODY, body);
            });
        }

        private static DeferredHolder<ArmorMaterial, ArmorMaterial> register(String name, EnumMap<Type, Integer> defense, int enchantmentValue, Holder<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
            return register(name, defense, enchantmentValue, equipSound, toughness, knockbackResistance, repairIngredient, false);
        }

        private static DeferredHolder<ArmorMaterial, ArmorMaterial> register(String name, EnumMap<Type, Integer> defense, int enchantmentValue, Holder<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient, boolean dyeable) {
            List<Layer> list;
            if (dyeable) {
                list = List.of(new ArmorMaterial.Layer(lc(name), "", true), new ArmorMaterial.Layer(lc(name), "_overlay", false));
            } else {
                list = List.of(new ArmorMaterial.Layer(lc(name)));
            }
            return register(name, defense, enchantmentValue, equipSound, toughness, knockbackResistance, repairIngredient, list);
        }

        private static DeferredHolder<ArmorMaterial, ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> defense, int enchantmentValue, Holder<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngridient, List<ArmorMaterial.Layer> layers) {
            EnumMap<Type, Integer> map = new EnumMap<>(ArmorItem.Type.class);

            for (ArmorItem.Type type : ArmorItem.Type.values()) {
                map.put(type, defense.get(type));
            }

            return ARMOR_MATERIALS.register(name, () -> new ArmorMaterial(map, enchantmentValue, equipSound, repairIngridient, layers, toughness, knockbackResistance));
        }
    }

    public static class ToolTiers {
        public static final Tier TIN = new BlueprintItemTier(OrevolutionTags.Blocks.incorrectForTin, 256, 5.0F, 1.0F, 7 , () -> Ingredient.of(OrevolutionTags.Items.tinIngots));
        public static final Tier PLATINUM = new BlueprintItemTier(OrevolutionTags.Blocks.incorrectForPlatinum, 768, 7.0F, 2.0F, 18, () -> Ingredient.of(OrevolutionTags.Items.platIngots));
        public static final Tier AETHERSTEEL = new BlueprintItemTier(OrevolutionTags.Blocks.incorrectForAethersteel, 3520, 10.0F, 5.0F, 15, () -> Ingredient.of(OrevolutionTags.Items.enderiteIngots));
        public static final Tier LIVINGSTONE = new BlueprintItemTier(OrevolutionTags.Blocks.incorrectForTin, 192, 4.0F, 1.0F, 8, () -> Ingredient.of(OrevolutionTags.Items.livingstoneFragments));
        public static final Tier VERDITE = new BlueprintItemTier(OrevolutionTags.Blocks.incorrectForPlatinum, 448, 6.0F, 2.0F, 16, () -> Ingredient.of(OrevolutionTags.Items.verditeIngots));
        public static final Tier STEEL = new BlueprintItemTier(OrevolutionTags.Blocks.incorrectForPlatinum, 1152, 3.0F, 4.0F, 12, () -> Ingredient.of(OrevolutionTags.Items.platIngots));
    }
}