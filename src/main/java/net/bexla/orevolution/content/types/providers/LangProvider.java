package net.bexla.orevolution.content.types.providers;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

// Credits to Oreganized (Team Galena)
public abstract class LangProvider implements DataProvider {
    private final Map<String, String> data = new TreeMap<>();
    private final PackOutput output;
    private final String modid;
    private final String locale;

    private final List<Runnable> subProviders = Lists.newArrayList();

    public LangProvider(PackOutput output, String modid, String locale) {
        this.output = output;
        this.modid = modid;
        this.locale = locale;
    }

    public void addSubProvider(Runnable runnable) {
        this.subProviders.add(runnable);
    }

    protected abstract void addTranslations();

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        this.subProviders.forEach(Runnable::run);
        addTranslations();
        if (!data.isEmpty())
            return save(cache, this.output.getOutputFolder(PackOutput.Target.RESOURCE_PACK).resolve(this.modid).resolve("lang").resolve(this.locale + ".json"));

        return CompletableFuture.allOf();
    }

    @Override
    public String getName() {
        return modid + " Languages: " + locale;
    }

    private CompletableFuture<?> save(CachedOutput cache, Path target) {
        JsonObject json = new JsonObject();
        this.data.forEach(json::addProperty);

        return DataProvider.saveStable(cache, json, target);
    }

    public void addBlock(Supplier<? extends Block> key, String name) {
        add(key.get(), name);
    }

    public void add(Block key, String name) {
        add(key.getDescriptionId(), name);
    }

    public void addItem(Supplier<? extends Item> key, String name) {
        add(key.get(), name);
    }

    public void add(Item key, String name) {
        add(key.getDescriptionId(), name);
    }

    public void addPotion(Supplier<? extends Potion> potion, String name) {
        add("item.minecraft.potion.effect." + BuiltInRegistries.POTION.getKey(potion.get()).getPath(), "Potion of " + name);
        add("item.minecraft.splash_potion.effect." + BuiltInRegistries.POTION.getKey(potion.get()).getPath(), "Splash Potion of " + name);
        add("item.minecraft.lingering_potion.effect." + BuiltInRegistries.POTION.getKey(potion.get()).getPath(), "Lingering Potion of " + name);
        add("item.minecraft.tipped_arrow.effect." + BuiltInRegistries.POTION.getKey(potion.get()).getPath(), "Arrow of " + name);
    }

    public void addAdvTitle(String advancementTitle, String name) {
        data.putIfAbsent("advancements." + advancementTitle + ".title", name);
    }

    public void addAdvDesc(String advancementTitle, String name) {
        data.putIfAbsent("advancements." + advancementTitle + ".description", name);
    }

    public void addSubtitle(String category, String subtitleName, String name) {
        add("subtitles." + category + "." + subtitleName, name);
    }

    public void addDeath(String deathName, String name) {
        add("death.attack." + deathName, name);
    }

    public void addItemStack(Supplier<ItemStack> key, String name) {
        add(key.get(), name);
    }

    public void add(ItemStack key, String name) {
        add(key.getDescriptionId(), name);
    }

    /*
    public void addBiome(Supplier<? extends Biome> key, String name) {
        add(key.get(), name);
    }

    public void add(Biome key, String name) {
        add(key.getTranslationKey(), name);
    }
    */

    public void addEffect(Supplier<? extends MobEffect> key, String name) {
        add(key.get(), name);
    }

    public void add(MobEffect key, String name) {
        add(key.getDescriptionId(), name);
    }

    public void addEntityType(Supplier<? extends EntityType<?>> key, String name) {
        add(key.get(), name);
    }

    public void add(EntityType<?> key, String name) {
        add(key.getDescriptionId(), name);
    }

    public void add(String key, String value) {
        if (data.put(key, value) != null)
            throw new IllegalStateException("Duplicate translation key " + key);
    }

    public void tryBlock(Supplier<? extends Block> block) {
        String key = block.get().getDescriptionId();
        String value = formatString(BuiltInRegistries.BLOCK.getKey(block.get()).getPath());
        data.putIfAbsent(key, value);
    }

    public void tryItem(Supplier<? extends Item> item) {
        String key = item.get().getDescriptionId();
        String value = formatString(BuiltInRegistries.ITEM.getKey(item.get()).getPath());
        data.putIfAbsent(key, value);
    }

    public void tryFluid(Supplier<? extends Fluid> fluid) {
        String key = Util.makeDescriptionId("fluid", BuiltInRegistries.FLUID.getKey(fluid.get()));
        String value = formatString(BuiltInRegistries.FLUID.getKey(fluid.get()).getPath());
        data.putIfAbsent(key, value);
    }

    public void tryEntity(Supplier<? extends EntityType<?>> entity) {
        String key = entity.get().getDescriptionId();
        String value = formatString(BuiltInRegistries.ENTITY_TYPE.getKey(entity.get()).getPath());
        data.putIfAbsent(key, value);
    }

    private String formatString(String key) {
        String[] strArr = key.split("_");
        StringBuffer res = new StringBuffer();
        for (String str : strArr) {
            char[] stringArray = str.trim().toCharArray();
            stringArray[0] = Character.toUpperCase(stringArray[0]);
            str = new String(stringArray);

            res.append(str).append(" ");
        }
        return res.toString().trim();
    }
}