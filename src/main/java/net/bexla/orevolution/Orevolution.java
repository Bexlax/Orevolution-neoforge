package net.bexla.orevolution;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.MapCodec;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.bexla.orevolution.content.types.ConfigEnabledCondition;
import net.bexla.orevolution.content.types.OrevolutionItemRegistryHelper;
import net.bexla.orevolution.datagen.*;
import net.bexla.orevolution.init.*;
import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.slf4j.Logger;

import java.util.concurrent.CompletableFuture;

import static net.bexla.orevolution.content.data.OrevolutionTiers.ArmorMats.ARMOR_MATERIALS;

@Mod(Orevolution.MODID)
public class Orevolution {
    public static final String MODID = "orevolution";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MODID, helper -> {
        helper.putSubHelper(Registries.ITEM, new OrevolutionItemRegistryHelper(helper));
    });

    public static ResourceLocation lc(String name) {
        return ResourceLocation.fromNamespaceAndPath(Orevolution.MODID, name);
    }

    private static final DeferredRegister<MapCodec<? extends ICondition>> CONDITION_CODECS = DeferredRegister.create(NeoForgeRegistries.Keys.CONDITION_CODECS, MODID);
    public static final DeferredHolder<MapCodec<? extends ICondition>, MapCodec<ConfigEnabledCondition>> CONFIG_LOADED_CONDITION = CONDITION_CODECS.register("config_loaded", () -> ConfigEnabledCondition.CODEC);


    public Orevolution(IEventBus modEventBus, ModContainer modContainer) {
        RegBlocks.HELPER.register(modEventBus);
        RegItems.register(modEventBus);
        ARMOR_MATERIALS.register(modEventBus);
        RegDataComponents.DATA_COMPONENT_TYPES.register(modEventBus);
        RegMobEffects.MOB_EFFECTS.register(modEventBus);
        RegMenus.MENUS.register(modEventBus);

        CONDITION_CODECS.register(modEventBus);

        RegFeatures.FEATURES.register(modEventBus);

        modEventBus.addListener(this::dataSetup);

        OrevolutionConfig.register(modContainer);

        RegMisc.RegisterAll();
    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        boolean server = event.includeServer();

        GENDataProvider datapackEntries = new GENDataProvider(output, provider);
        generator.addProvider(server, datapackEntries);
        provider = datapackEntries.getRegistryProvider();

        generator.addProvider(server, new GENLootDrops(output, provider));
        generator.addProvider(server, new GENLootModifiers(output, provider));
        GENBlockTags blockTags = new GENBlockTags(output, provider, helper);
        generator.addProvider(server, blockTags);
        generator.addProvider(server, new GENItemTags(output, provider, blockTags.contentsGetter(), helper));
        generator.addProvider(server, new GENRecipes(output, provider));

        boolean client = event.includeClient();
        generator.addProvider(client, new GENBlockStateModels(output, helper));
        generator.addProvider(client, new GENItemModels(output, helper));

        var lang = new GENLangENUS(output);

        generator.addProvider(client, lang);
        generator.addProvider(client, new GENLangESAR(output));

        generator.addProvider(server, new PackMetadataGenerator(output).add(PackMetadataSection.TYPE,
            new PackMetadataSection(
                Component.literal("Orevolution resources"),
                DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES)
            )
        ));
    }
}
