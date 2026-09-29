package net.bexla.orevolution.content.interfaces;

import net.minecraft.client.player.LocalPlayer;
import net.neoforged.neoforge.client.event.ViewportEvent;

// Credits to Darker Depths
// https://github.com/futurenp/DarkerDepths/blob/1.21.1/src/main/java/com/naterbobber/darkerdepths/client/fog/FogModifier.java
public interface IFogModifier {
    int getPriority();

    boolean isActive(LocalPlayer player);

    default void tick(LocalPlayer player) {}

    default void modifyColor(ViewportEvent.ComputeFogColor event, LocalPlayer player, float partialTick) {}

    default void modifyRender(ViewportEvent.RenderFog event, LocalPlayer player, float partialTick) {}

    default void modifyFov(ViewportEvent.ComputeFov event, LocalPlayer player, float partialTick) {}
}