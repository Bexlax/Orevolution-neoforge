package net.bexla.orevolution.content.renders;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.types.entity.ExplosiveArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FieryArrowRender extends ArrowRenderer<ExplosiveArrow> {
    private static final ResourceLocation FIERY_ARROW = Orevolution.lc("textures/entity/projectiles/fiery_arrow.png");

    public FieryArrowRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(ExplosiveArrow entity) {
        return FIERY_ARROW;
    }
}