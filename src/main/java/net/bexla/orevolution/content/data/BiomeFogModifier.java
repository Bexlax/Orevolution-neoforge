package net.bexla.orevolution.content.data;

import com.mojang.blaze3d.shaders.FogShape;
import net.bexla.orevolution.content.data.utility.Color;
import net.bexla.orevolution.content.interfaces.IFogModifier;
import net.bexla.orevolution.content.worldgen.OrevolutionBiomeFogs;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FogType;
import net.neoforged.neoforge.client.event.ViewportEvent;

// Credits to Darker Depths
// https://github.com/futurenp/DarkerDepths/blob/1.21.1/src/main/java/com/naterbobber/darkerdepths/client/fog/modifiers/BiomeFogModifier.java
public class BiomeFogModifier implements IFogModifier {
    private static final float BIOME_TRANSITION_SECONDS = 3.5f;

    @Override
    public int getPriority() { return 10; }

    @Override
    public boolean isActive(LocalPlayer player) {
        return !player.hasEffect(MobEffects.BLINDNESS)
                && !player.hasEffect(MobEffects.DARKNESS)
                && player.getEyeInFluidType() != Fluids.LAVA.getFluidType();
    }

    @Override
    public void tick(LocalPlayer player) {
        float biomeStep = 1.0f / (BIOME_TRANSITION_SECONDS * 20.0f);
        var currentBiome = player.level().getBiome(player.getOnPos());

        for (OrevolutionBiomeFogs.BiomeFog biome : OrevolutionBiomeFogs.BIOME_FOGS) {
            boolean isInBiome = currentBiome.is(biome.getBiomeKey());
            biome.setWeight(Math.max(0.0f, Math.min(1.0f, biome.getWeight() + (isInBiome ? biomeStep : -biomeStep))));
        }
    }

    @Override
    public void modifyColor(ViewportEvent.ComputeFogColor event, LocalPlayer player, float partialTick) {
        float totalWeight = Math.min(1.0F, getTotalBiomeWeight());
        if (totalWeight <= 0.0F) return;

        long time = player.level().getGameTime();

        float targetR = 0.0F;
        float targetG = 0.0F;
        float targetB = 0.0F;

        for (OrevolutionBiomeFogs.BiomeFog biome : OrevolutionBiomeFogs.BIOME_FOGS) {
            if (biome.getWeight() <= 0.0F) continue;

            Color color = biome.getAnimatedColor(time, partialTick);
            float normalizedWeight = biome.getWeight() / totalWeight;

            targetR += color.getRedFloat() * normalizedWeight;
            targetG += color.getGreenFloat() * normalizedWeight;
            targetB += color.getBlueFloat() * normalizedWeight;
        }

        event.setRed(lerp(event.getRed(), targetR, totalWeight));
        event.setGreen(lerp(event.getGreen(), targetG, totalWeight));
        event.setBlue(lerp(event.getBlue(), targetB, totalWeight));
    }

    @Override
    public void modifyRender(ViewportEvent.RenderFog event, LocalPlayer player, float partialTick) {
        float totalWeight = Math.min(1.0f, getTotalBiomeWeight());
        if (totalWeight <= 0.0f) return;

        float targetNear = 0;
        float targetFar = 0;
        for (OrevolutionBiomeFogs.BiomeFog biome : OrevolutionBiomeFogs.BIOME_FOGS) {
            if (biome.getWeight() <= 0) continue;

            float normalizedWeight = biome.getWeight() / totalWeight;

            boolean isUnderwater = event.getCamera().getFluidInCamera() == FogType.WATER;

            float underwaterNear = isUnderwater? -8f : 0f;
            float underwaterFar = isUnderwater? -43f : 0f;

            targetNear += biome.getMinDist() * normalizedWeight + underwaterNear;
            targetFar += biome.getMaxDist() * normalizedWeight + underwaterFar;
        }

        float vanillaNear = event.getNearPlaneDistance();
        float vanillaFar = event.getFarPlaneDistance();

        event.setNearPlaneDistance(lerp(vanillaNear, targetNear, totalWeight));
        event.setFarPlaneDistance(lerp(vanillaFar, targetFar, totalWeight));

        event.setFogShape(FogShape.SPHERE);
        event.setCanceled(true);
    }

    private float lerp(float start, float end, float factor) {
        return start + factor * (end - start);
    }

    private float getTotalBiomeWeight() {
        float total = 0;
        for (OrevolutionBiomeFogs.BiomeFog biome : OrevolutionBiomeFogs.BIOME_FOGS) total += biome.getWeight();
        return total;
    }


}