package net.bexla.orevolution.content.data;

import net.bexla.orevolution.init.RegItems;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimPattern;

import static net.bexla.orevolution.Orevolution.lc;

public class TrimArmorPatterns {
    public static final ResourceKey<TrimPattern> AETHERSTEEL = createKey("aethersteel");
    public static final ResourceKey<TrimPattern> AETHERSTEEL_ALT = createKey("aethersteel_alt");
    public static final ResourceKey<TrimPattern> LIVINGSTONE = createKey("livingstone");
    public static final ResourceKey<TrimPattern> BRONZE = createKey("bronze");
    public static final ResourceKey<TrimPattern> TUNGSTEN = createKey("tungsten");

    public static void bootstrap(BootstrapContext<TrimPattern> context) {
        register(context, AETHERSTEEL, RegItems.AETHERSTEEL_INGOT.get());
        register(context, AETHERSTEEL_ALT, RegItems.AETHERSTEEL_CHUNK.get());
        register(context, LIVINGSTONE, RegItems.LIVINGSTONE_SHARD.get());
        register(context, BRONZE, RegItems.BRONZE_ALLOY.get());
        register(context, TUNGSTEN, RegItems.TUNGSTEN_INGOT.get());
    }

    public static ResourceKey<TrimPattern> createKey(String name) {
        return ResourceKey.create(Registries.TRIM_PATTERN, lc(name));
    }

    private static void register(BootstrapContext<TrimPattern> context, ResourceKey<TrimPattern> key, Item item) {
        context.register(key, new TrimPattern(key.location(), item.builtInRegistryHolder(), Component.translatable(Util.makeDescriptionId("trim_pattern", key.location())), false));
    }

}
