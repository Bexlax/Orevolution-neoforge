package net.bexla.orevolution.mixins;

//import net.bexla.orevolution.content.types.menu.SteelAnvilMenu;

import net.bexla.orevolution.content.types.menu.SteelAnvilMenu;
import net.minecraft.network.protocol.game.ServerboundRenameItemPacket;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerGamePacketListenerImplMixin {
    @Inject(method = "handleRenameItem", at = @At("HEAD"), cancellable = true)
    private void orevolution$handleSteelAnvilRename(ServerboundRenameItemPacket packet, CallbackInfo ci) {
        ServerGamePacketListenerImpl handler = (ServerGamePacketListenerImpl)(Object)this;

        if (handler.player.containerMenu instanceof SteelAnvilMenu anvilmenu) {
            if (!anvilmenu.stillValid(handler.player)) {
                return;
            }

            anvilmenu.setItemName(packet.getName());
        }
    }
}
