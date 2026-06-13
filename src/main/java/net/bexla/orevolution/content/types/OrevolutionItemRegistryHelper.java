package net.bexla.orevolution.content.types;

import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;

public class OrevolutionItemRegistryHelper extends ItemSubRegistryHelper {
    protected final HashMap<DeferredHolder<? extends Item, ?>, IClientItemExtensions> clientItemExtensions = new HashMap<>();

    public OrevolutionItemRegistryHelper(RegistryHelper parent) {
        super(parent, (DeferredRegister.Items) parent.getItemSubHelper().getDeferredRegister());
    }

    @Override
    public void register(IEventBus eventBus) {
        super.register(eventBus);
        if (FMLEnvironment.dist == Dist.CLIENT) {
            eventBus.addListener((RegisterClientExtensionsEvent event) -> {
                this.clientItemExtensions.forEach((holder, extensions) -> {
                    event.registerItem(extensions, holder.get());
                });
                this.clientItemExtensions.clear();
            });
        }
    }
}
