package net.bexla.orevolution.events;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.types.menu.SteelAnvilScreen;
import net.bexla.orevolution.init.RegMenus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = Orevolution.MODID)
public class ClientSubscriber {

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(RegMenus.STEEL_ANVIL.get(), SteelAnvilScreen::new);
    }

//    private static void bowProp(Item item) {
//        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("drawing"), (stack, world, entity, seed) ->
//                entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1 : 0);
//
//        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("draw"), (stack, world, entity, seed) -> {
//            if (entity == null || entity.getUseItem() != stack) {
//                return 0;
//            }
//
//            IBowProperties properties = (IBowProperties) stack.getItem();
//
//            return BowUtil.getPowerForDrawTime(stack.getUseDuration() - entity.getUseItemRemainingTicks(), properties);
//        });
//    }
}
