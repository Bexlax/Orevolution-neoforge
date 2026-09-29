package net.bexla.orevolution;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.bexla.orevolution.content.data.utility.OrevolutionUtils;
import net.bexla.orevolution.content.types.OrevolutionItemRegistryHelper;
import net.bexla.orevolution.datagen.*;
import net.bexla.orevolution.init.*;
import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.stats.Stats;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.bexla.orevolution.content.data.OrevolutionTiers.ArmorMats.ARMOR_MATERIALS;

@Mod(Orevolution.MODID)
public class Orevolution {
    public static final String MODID = "orevolution";

    public static final RegistryHelper REGISTRY_HELPER = RegistryHelper.create(MODID, helper -> {
        helper.putSubHelper(Registries.ITEM, new OrevolutionItemRegistryHelper(helper));
    });

    public static ResourceLocation lc(String name) {
        return ResourceLocation.fromNamespaceAndPath(Orevolution.MODID, name);
    }

    public Orevolution(IEventBus modEventBus, ModContainer modContainer) {
        RegBlocks.HELPER.register(modEventBus);
        RegItems.register(modEventBus);
        RegEntityTypes.ENTITY_TYPES.register(modEventBus);
        RegItems.POTIONS.register(modEventBus);
        RegRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
        RegParticleTypes.PARTICLE_TYPES.register(modEventBus);
        ARMOR_MATERIALS.register(modEventBus);
        RegDataComponents.DATA_COMPONENT_TYPES.register(modEventBus);
        RegMobEffects.MOB_EFFECTS.register(modEventBus);
        RegMenus.MENUS.register(modEventBus);
        RegLootConditions.LOOT_CONDITIONS.register(modEventBus);
        RegConditionSerializer.CONDITION_SERIALIZERS.register(modEventBus);
        RegStructureRepaletters.registerRepaletters();

        RegFeatures.FEATURES.register(modEventBus);

        RegWorldCarvers.WORLD_CARVERS.register(modEventBus);

        modEventBus.addListener(this::dataSetup);
        modEventBus.addListener(this::commonSetup);

        OrevolutionConfig.register(modContainer);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        boolean hasBiolith = ModList.get().isLoaded("biolith");
        boolean hasTerrablender = ModList.get().isLoaded("terrablender");
        boolean hasBoth = hasBiolith && hasTerrablender;
        boolean hasNone = !hasBiolith && !hasTerrablender;

        event.enqueueWork(() -> {
            RegOthers.miscRegister();

            FireBlock fire = (FireBlock) Blocks.FIRE;
            fire.setFlammable(RegBlocks.RDX.get(), 15, 100);

            List<DeferredItem<Item>> bronze = List.of(
                    RegItems.BRONZE_BOOTS,
                    RegItems.BRONZE_CHESTPLATE,
                    RegItems.BRONZE_LEGGINGS,
                    RegItems.BRONZE_HELMET
            );

            bronze.forEach(i ->
                    CauldronInteraction.WATER.map().put(
                            i.get(),
                            (state, level, pos, player, hand, stack) -> {
                                if (!level.isClientSide()) {
                                    stack.remove(DataComponents.DYED_COLOR);
                                    player.awardStat(Stats.CLEAN_ARMOR);
                                    LayeredCauldronBlock.lowerFillLevel(state, level, pos);

                                    player.setItemInHand(hand, stack);
                                    level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));

                                    return ItemInteractionResult.SUCCESS;
                                }

                                return ItemInteractionResult.sidedSuccess(level.isClientSide());
                            }
                    ));

            if (hasNone || !OrevolutionConfig.COMMON.enableModdedBiomes.get()) {
                OrevolutionUtils.warn("Neither Biolith nor Terrablender are loaded, skipping biome generation for Orevolution.");
            } else if(hasBoth) {
                if(OrevolutionConfig.COMMON.prioritizeTerrablender.get()) {
                    OrevolutionUtils.info("Terrablender priority enabled, registering biome regions and surface rules for Orevolution with Terrablender.");

                    RegBiomes.safeInitTerrablender();
                } else {
                    OrevolutionUtils.info("Terrablender priority disabled, registering biome regions and surface rules for Orevolution with Biolith.");

                    RegBiomes.safeInitBiolith();
                }
            } else if(hasTerrablender) {
                OrevolutionUtils.info("Terrablender detected, registering biome regions and surface rules for Orevolution with Terrablender.");

                RegBiomes.safeInitTerrablender();
            } else {
                OrevolutionUtils.info("Biolith detected, registering biome regions and surface rules for Orevolution with Biolith.");

                RegBiomes.safeInitBiolith();
            }
        });
    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        boolean client = event.includeClient();
        boolean server = event.includeServer();

        GENDataProvider datapackEntries = new GENDataProvider(output, provider);
        generator.addProvider(server, datapackEntries);
        provider = datapackEntries.getRegistryProvider();

        generator.addProvider(client, new GENBlockStateModels(output, helper));
        generator.addProvider(client, new GENItemModels(output, helper));
        generator.addProvider(client, new GENSpriteSourceProvider(output, provider, helper));

        var lang = new GENLangENUS(output);

        generator.addProvider(client, lang);
        generator.addProvider(server, new GENAdvancements(output, provider, helper));
        generator.addProvider(client, new GENLangESAR(output));

        generator.addProvider(server, new GENLootDrops(output, provider));
        generator.addProvider(server, new GENLootModifiers(output, provider));
        GENBlockTags blockTags = new GENBlockTags(output, provider, helper);
        generator.addProvider(server, blockTags);
        generator.addProvider(server, new GENBiomeTags(output, provider, helper));
        generator.addProvider(server, new GENItemTags(output, provider, blockTags.contentsGetter(), helper));
        generator.addProvider(server, new GENDamageTypeTags(output, provider, helper));
        generator.addProvider(server, new GENDataMaps(output, provider));
        generator.addProvider(server, new GENTrimMaterialTags(output, provider, helper));
        generator.addProvider(server, new GENRecipes(output, provider));

        generator.addProvider(server, new PackMetadataGenerator(output).add(PackMetadataSection.TYPE,
            new PackMetadataSection(
                Component.literal("Orevolution resources"),
                DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES)
            )
        ));
    }
}
