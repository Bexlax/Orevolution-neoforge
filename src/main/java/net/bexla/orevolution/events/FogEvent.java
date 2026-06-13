package net.bexla.orevolution.events;

import net.bexla.orevolution.content.data.OrevolutionTiers;
import net.minecraft.client.Camera;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.FogType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ViewportEvent;

@EventBusSubscriber(value = Dist.CLIENT)
public class FogEvent {
    @SubscribeEvent
    public static void onRenderFog(ViewportEvent.RenderFog event) {
        Camera camera = event.getCamera();

        if (!(camera.getEntity() instanceof Player player))
            return;

        if (camera.getFluidInCamera() != FogType.LAVA)
            return;

        if (isWearingMyPRECIOUSTungstenArmor(player)) {

            event.setNearPlaneDistance(-8.0F);
            event.setFarPlaneDistance(48.0F);

            event.setCanceled(true);
        }
    }

    private static boolean isWearingMyPRECIOUSTungstenArmor(Player player) {

        for (ItemStack stack : player.getArmorSlots()) {

            if (!(stack.getItem() instanceof ArmorItem armor))
                return false;

            if (armor.getMaterial() != OrevolutionTiers.ArmorMats.TUNGSTEN)
                return false;
        }

        return true;
    }
}
