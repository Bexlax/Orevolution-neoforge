package net.bexla.orevolution.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.bexla.orevolution.init.RegDataComponents;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

import static net.bexla.orevolution.Orevolution.lc;

@Mixin(BlockEntityWithoutLevelRenderer.class)
public abstract class BlockEntityWithoutLevelRendererMixin {

    @Shadow
    private ShieldModel shieldModel;

    @Unique
    private static final Material OREVOLUTION_REINFORCED_SHIELD =
            new Material(Sheets.SHIELD_SHEET, lc("entity/tungsten_reinforced_shield"));

    @Unique
    private static final Material OREVOLUTION_COATED_SHIELD =
            new Material(Sheets.SHIELD_SHEET, lc("entity/tungsten_coated_shield"));

    @Inject(method = "renderByItem", at = @At("HEAD"), cancellable = true)
    private void orevolution$renderTungstenShield(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay, CallbackInfo ci) {
        if (!stack.is(Items.SHIELD)) return;

        boolean reinforced = stack.getOrDefault(RegDataComponents.REINFORCED.get(), false);
        boolean coated = stack.getOrDefault(RegDataComponents.COATED.get(), false);

        if (!reinforced && !coated) return;

        BannerPatternLayers bannerPatterns = stack.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY);
        DyeColor baseColor = stack.get(DataComponents.BASE_COLOR);

        boolean hasPatterns = !bannerPatterns.layers().isEmpty() || baseColor != null;

        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);

        Material material = reinforced? OREVOLUTION_REINFORCED_SHIELD : OREVOLUTION_COATED_SHIELD;
        VertexConsumer vertexConsumer = material.sprite()
                .wrap(ItemRenderer.getFoilBufferDirect(buffer, shieldModel.renderType(material.atlasLocation()), true, stack.hasFoil()));
        shieldModel.handle().render(poseStack, vertexConsumer, packedLight, packedOverlay);

        if (hasPatterns) {
            BannerRenderer.renderPatterns(poseStack, buffer, packedLight, packedOverlay, shieldModel.plate(), material, false, Objects.requireNonNullElse(baseColor, DyeColor.WHITE), bannerPatterns, stack.hasFoil());
        } else {
            shieldModel.plate().render(poseStack, vertexConsumer, packedLight, packedOverlay);
        }

        poseStack.popPose();
        ci.cancel();
    }
}