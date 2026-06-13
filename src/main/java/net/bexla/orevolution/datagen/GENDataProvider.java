package net.bexla.orevolution.datagen;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.init.RegFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class GENDataProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, GENBiomeModifier::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, RegFeatures.Configured::bootstrap)
            .add(Registries.PLACED_FEATURE, RegFeatures.Placed::bootstrap);

    public GENDataProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, BUILDER, Set.of(Orevolution.MODID, "minecraft"));
    }

}
