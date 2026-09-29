package net.bexla.orevolution.datagen;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class GENDamageTypeTags extends TagsProvider<DamageType> {

    public GENDamageTypeTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, Registries.DAMAGE_TYPE, provider, Orevolution.MODID, helper);
    }

    @Override
    public @NotNull String getName() {
        return "Orevolution Damage Type Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
         tag(OrevolutionTags.Misc.IS_KINETIC)
                 .add(
                         DamageTypes.FALL,
                         DamageTypes.FALLING_ANVIL,
                         DamageTypes.FALLING_STALACTITE,
                         DamageTypes.FALLING_BLOCK,
                         DamageTypes.EXPLOSION,
                         DamageTypes.PLAYER_EXPLOSION
                 );

    }
}