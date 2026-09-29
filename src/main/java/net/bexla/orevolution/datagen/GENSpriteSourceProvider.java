package net.bexla.orevolution.datagen;

import com.teamabnormals.blueprint.core.api.BlueprintTrims;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.TrimArmorMaterials;
import net.bexla.orevolution.content.data.TrimArmorPatterns;
import net.minecraft.client.renderer.texture.atlas.sources.SingleFile;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SpriteSourceProvider;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static net.bexla.orevolution.Orevolution.lc;

public class GENSpriteSourceProvider extends SpriteSourceProvider {
    public GENSpriteSourceProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Orevolution.MODID, existingFileHelper);
    }

    @Override
    protected void gather() {
        this.atlas(BlueprintTrims.ARMOR_TRIMS_ATLAS)
                .addSource(BlueprintTrims.patternPermutationsOfVanillaMaterials(
                        TrimArmorPatterns.BRONZE,
                        TrimArmorPatterns.LIVINGSTONE,
                        TrimArmorPatterns.AETHERSTEEL,
                        TrimArmorPatterns.AETHERSTEEL_ALT,
                        TrimArmorPatterns.TUNGSTEN))
                .addSource(BlueprintTrims.materialPatternPermutations(
                    TrimArmorMaterials.TIN,
                    TrimArmorMaterials.PLATINUM,
                    TrimArmorMaterials.TUNGSTEN,
                    TrimArmorMaterials.AETHERSTEEL,
                    TrimArmorMaterials.LIVINGSTONE,
                    TrimArmorMaterials.VERDITE
        ));

        this.atlas(BLOCKS_ATLAS).addSource(BlueprintTrims.materialPermutationsForItemLayers(
                TrimArmorMaterials.TIN,
                TrimArmorMaterials.PLATINUM,
                TrimArmorMaterials.TUNGSTEN,
                TrimArmorMaterials.AETHERSTEEL,
                TrimArmorMaterials.LIVINGSTONE,
                TrimArmorMaterials.VERDITE
        ));

        this.atlas(SHIELD_PATTERNS_ATLAS)
                .addSource(new SingleFile(lc("entity/tungsten_reinforced_shield"), Optional.empty()))
                .addSource(new SingleFile(lc("entity/tungsten_coated_shield"), Optional.empty()));
    }
}
