package net.bexla.orevolution.content.data;

import net.bexla.orevolution.init.RegItems;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;

import java.util.Map;

import static net.bexla.orevolution.Orevolution.lc;

public class TrimArmorMaterials {
    public static final ResourceKey<TrimMaterial> TIN = createKey("tin");
    public static final ResourceKey<TrimMaterial> PLATINUM = createKey("platinum");
    public static final ResourceKey<TrimMaterial> TUNGSTEN = createKey("tungsten");
    public static final ResourceKey<TrimMaterial> AETHERSTEEL = createKey("aethersteel");
    public static final ResourceKey<TrimMaterial> LIVINGSTONE = createKey("livingstone");
    public static final ResourceKey<TrimMaterial> VERDITE = createKey("verdite");

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, TIN, RegItems.TIN_INGOT, Style.EMPTY.withColor(16777045), Map.of());
        register(context, PLATINUM, RegItems.PLATINUM_INGOT, Style.EMPTY.withColor(5636095), Map.of());
        register(context, TUNGSTEN, RegItems.TUNGSTEN_INGOT, Style.EMPTY.withColor(43520), Map.of());
        register(context, AETHERSTEEL, RegItems.AETHERSTEEL_INGOT, Style.EMPTY.withColor(11141290), Map.of());
        register(context, LIVINGSTONE, RegItems.LIVINGSTONE_SHARD, Style.EMPTY.withColor(11184810), Map.of());
        register(context, VERDITE, RegItems.VERDITE_INGOT, Style.EMPTY.withColor(5635925), Map.of());
    }

    private static ResourceKey<TrimMaterial> createKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, lc(name));
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> key, Holder<Item> item, Style style, Map<Holder<ArmorMaterial>, String> overrides) {
        ResourceLocation location = key.location();
        context.register(key, new TrimMaterial(location.getNamespace() + "_" + location.getPath(), item, -1.0F, overrides, Component.translatable(Util.makeDescriptionId("trim_material", location)).withStyle(style)));
    }
}
