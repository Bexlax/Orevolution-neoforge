package net.bexla.orevolution.datagen;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class GENBiomeTags extends TagsProvider<Biome> {
    public GENBiomeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, Registries.BIOME, provider, Orevolution.MODID, helper);
    }

    @Override
    public @NotNull String getName() {
        return "Orevolution Biome Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(OrevolutionTags.Misc.HAS_MOONSTONE_NETHER)
                .add(
                        Biomes.BASALT_DELTAS
                );

    }
}
