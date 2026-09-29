package net.bexla.orevolution.datagen;

import com.teamabnormals.blueprint.core.other.tags.BlueprintTrimMaterialTags;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.TrimArmorMaterials;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class GENTrimMaterialTags extends TagsProvider<TrimMaterial> {

    public GENTrimMaterialTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, Registries.TRIM_MATERIAL, provider, Orevolution.MODID, helper);
    }

    @Override
    public void addTags(HolderLookup.Provider provider) {
        this.tag(BlueprintTrimMaterialTags.GENERATES_OVERRIDES).add(
                TrimArmorMaterials.TIN,
                TrimArmorMaterials.VERDITE,
                TrimArmorMaterials.LIVINGSTONE,
                TrimArmorMaterials.PLATINUM,
                TrimArmorMaterials.AETHERSTEEL,
                TrimArmorMaterials.TUNGSTEN
        );
    }
}