package net.bexla.orevolution.init;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.types.mobeffect.PetrifiedEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RegMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Orevolution.MODID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, Orevolution.MODID);

    public static final DeferredHolder<MobEffect, PetrifiedEffect> PETRIFIED = MOB_EFFECTS.register("petrified", PetrifiedEffect::new);
}
