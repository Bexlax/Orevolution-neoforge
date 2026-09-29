package net.bexla.orevolution.init;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.worldgen.BlockCarver;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RegWorldCarvers {
    public static final DeferredRegister<WorldCarver<?>> WORLD_CARVERS = DeferredRegister.create(Registries.CARVER, Orevolution.MODID);

    public static final DeferredHolder<WorldCarver<?>, BlockCarver> BLOCK_CARVER = WORLD_CARVERS.register("block_carver", () -> new BlockCarver(BlockCarver.BlockCarverConfiguration.CODEC));

}
