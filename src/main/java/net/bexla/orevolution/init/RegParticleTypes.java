package net.bexla.orevolution.init;

import net.bexla.orevolution.Orevolution;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RegParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, Orevolution.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> YELLOW_SMOKE =
            PARTICLE_TYPES.register("yellow_smoke",
                    () -> new SimpleParticleType(true));

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GAS =
            PARTICLE_TYPES.register("gas",
                    () -> new SimpleParticleType(true));
}
