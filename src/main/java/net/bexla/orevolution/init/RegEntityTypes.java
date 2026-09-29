package net.bexla.orevolution.init;

import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.types.entity.ExplosiveArrow;
import net.bexla.orevolution.content.types.entity.PrimedRdx;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;

public class RegEntityTypes {
    public static final EntitySubRegistryHelper ENTITY_TYPES = Orevolution.REGISTRY_HELPER.getEntitySubHelper();

    public static final DeferredHolder<EntityType<?>, EntityType<ExplosiveArrow>> FIERY_ARROW = ENTITY_TYPES.createEntity("blunt_arrow", ExplosiveArrow::new, MobCategory.MISC, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.13F).clientTrackingRange(4).updateInterval(20));
    public static final DeferredHolder<EntityType<?>, EntityType<PrimedRdx>> PRIMED_RDX = ENTITY_TYPES.createEntity("primed_rdx", PrimedRdx::new, MobCategory.MISC, builder -> builder.fireImmune().sized(0.98F, 0.98F).eyeHeight(0.15F).clientTrackingRange(10).updateInterval(10));

}
