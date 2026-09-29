package net.bexla.orevolution.content.types;

import net.bexla.orevolution.content.interfaces.IFogModifier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ViewportEvent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Credits to Darker Depths
// https://github.com/futurenp/DarkerDepths/blob/1.21.1/src/main/java/com/naterbobber/darkerdepths/client/fog/FogManager.java
@EventBusSubscriber(value = Dist.CLIENT)
public class FogManager {

    private static final List<IFogModifier> MODIFIERS = new ArrayList<>();

    public static void register(IFogModifier modifier) {
        MODIFIERS.add(modifier);
        MODIFIERS.sort(Comparator.comparingInt(IFogModifier::getPriority));
    }

    public static void onClientTick() {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null || Minecraft.getInstance().isPaused()) return;

        for (IFogModifier modifier : MODIFIERS) {
            if (modifier.isActive(player)) {
                modifier.tick(player);
            }
        }
    }

    @SubscribeEvent
    public static void onComputeFogColor(ViewportEvent.ComputeFogColor event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;

        float pTick = event.getCamera().getPartialTickTime();
        for (IFogModifier modifier : MODIFIERS) {
            if (modifier.isActive(player)) {
                modifier.modifyColor(event, player, pTick);
            }
        }
    }

    @SubscribeEvent
    public static void onRenderFog(ViewportEvent.RenderFog event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;

        float pTick = event.getCamera().getPartialTickTime();
        for (IFogModifier modifier : MODIFIERS) {
            if (modifier.isActive(player)) {
                modifier.modifyRender(event, player, pTick);
            }
        }
    }

    @SubscribeEvent
    public static void onComputeFov(ViewportEvent.ComputeFov event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;

        float pTick = event.getCamera().getPartialTickTime();
        for (IFogModifier modifier : MODIFIERS) {
            if (modifier.isActive(player)) {
                modifier.modifyFov(event, player, pTick);
            }
        }
    }
}