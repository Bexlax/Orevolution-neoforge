package net.bexla.orevolution.datagen;

import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class GENDataMaps extends DataMapProvider {

    public GENDataMaps(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        this.builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(RegBlocks.PYRITE_BLOCK.getId(), new FurnaceFuel(8000), false)
                .add(RegItems.PYRITE.getId(), new FurnaceFuel(800), false);

        this.builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(RegBlocks.LIVINGSTONE_BLOCK.getId(), new Compostable(0.8F), false)
                .add(RegBlocks.VERDITE_BLOCK.getId(), new Compostable(0.9F), false)
                .add(RegItems.PETRIFIED_SEED.getId(), new Compostable(0.3F), false)
                .add(RegItems.DEAD_SEED.getId(), new Compostable(0.35F), false)
                .add(RegItems.PETRIFIED_SEED.getId(), new Compostable(0.3F), false)
                .add(RegItems.LIVINGSTONE_SHARD.getId(), new Compostable(0.45F), false)
                .add(RegItems.VERDITE_NUGGET.getId(), new Compostable(0.5F), false)
                .add(RegItems.LIVINGSTONE_SWORD.getId(), new Compostable(0.6F), false)
                .add(RegItems.LIVINGSTONE_PICKAXE.getId(), new Compostable(0.6F), false)
                .add(RegItems.LIVINGSTONE_AXE.getId(), new Compostable(0.6F), false)
                .add(RegItems.LIVINGSTONE_SHOVEL.getId(), new Compostable(0.6F), false)
                .add(RegItems.LIVINGSTONE_HOE.getId(), new Compostable(0.6F), false)
                .add(RegItems.LIVINGSTONE_HELMET.getId(), new Compostable(0.6F), false)
                .add(RegItems.LIVINGSTONE_CHESTPLATE.getId(), new Compostable(0.6F), false)
                .add(RegItems.LIVINGSTONE_LEGGINGS.getId(), new Compostable(0.6F), false)
                .add(RegItems.LIVINGSTONE_BOOTS.getId(), new Compostable(0.6F), false)
                .add(RegItems.VERDITE_SWORD.getId(), new Compostable(0.65F), false)
                .add(RegItems.VERDITE_PICKAXE.getId(), new Compostable(0.65F), false)
                .add(RegItems.VERDITE_AXE.getId(), new Compostable(0.65F), false)
                .add(RegItems.VERDITE_SHOVEL.getId(), new Compostable(0.65F), false)
                .add(RegItems.VERDITE_HOE.getId(), new Compostable(0.65F), false)
                .add(RegItems.VERDITE_HELMET.getId(), new Compostable(0.65F), false)
                .add(RegItems.VERDITE_CHESTPLATE.getId(), new Compostable(0.65F), false)
                .add(RegItems.VERDITE_LEGGINGS.getId(), new Compostable(0.65F), false)
                .add(RegItems.VERDITE_BOOTS.getId(), new Compostable(0.65F), false);

    }
}