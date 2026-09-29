package net.bexla.orevolution.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import net.bexla.orevolution.content.data.TrimArmorPatterns;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.armortrim.TrimPattern;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidArmorLayer.class)
public abstract class HumanoidArmorLayerMixin<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {

    public HumanoidArmorLayerMixin(RenderLayerParent<T, M> renderer) {
        super(renderer);
    }

    @Final
    @Shadow
    private A outerModel;

    @Shadow
    protected abstract boolean usesInnerModel(EquipmentSlot p_117129_);

    @Shadow
    protected abstract Model getArmorModelHook(T entity, ItemStack itemStack, EquipmentSlot slot, A model);

    @Shadow
    @Final
    public TextureAtlas armorTrimAtlas;
    @Shadow
    @Final
    private A innerModel;

    @Shadow
    protected abstract void renderTrim(Holder<ArmorMaterial> armorMaterial, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, ArmorTrim trim, A model, boolean innerTexture);

    @Shadow
    protected abstract void renderTrim(Holder<ArmorMaterial> p_323506_, PoseStack p_289687_, MultiBufferSource p_289643_, int p_289683_, ArmorTrim p_289692_, Model p_289663_, boolean p_289651_);

    // Took this from Caverns & Chasms
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;hasFoil()Z", shift = At.Shift.BEFORE), method = "renderArmorPiece(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;ILnet/minecraft/client/model/HumanoidModel;FFFFFF)V")
    public void renderSanguineTrim(PoseStack poseStack, MultiBufferSource source, T entity, EquipmentSlot slot, int num, A model, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        ItemStack stack = entity.getItemBySlot(slot);

        if (!(stack.getItem() instanceof ArmorItem armorItem)) return;

        ResourceKey<TrimPattern> pattern = getPattern(stack);
        if (pattern == null) return;

        ArmorTrim original = create(stack);
        if (original == null) return;

        RegistryAccess access = entity.level().registryAccess();

        ArmorTrim trim = new ArmorTrim(original.material(), access.registryOrThrow(Registries.TRIM_PATTERN).getHolderOrThrow(pattern));
        this.renderTrim(armorItem.getMaterial(), poseStack, source, num, trim, this.getArmorModelHook(entity, stack, slot, model), this.usesInnerModel(slot));
    }

    @Unique
    private static ResourceKey<TrimPattern> getPattern(ItemStack stack) {
        if (stack.is(RegItems.BRONZE_CHESTPLATE)) return TrimArmorPatterns.BRONZE;

        if (stack.is(RegItems.AETHERSTEEL_CHESTPLATE)) return TrimArmorPatterns.AETHERSTEEL;

        if (stack.is(RegItems.AETHERSTEEL_HELMET) ||stack.is(RegItems.AETHERSTEEL_BOOTS)) return TrimArmorPatterns.AETHERSTEEL_ALT;

        if (stack.is(RegItems.TUNGSTEN_CHESTPLATE) || stack.is(RegItems.TUNGSTEN_HELMET)) return TrimArmorPatterns.TUNGSTEN;

        if (stack.is(RegItems.LIVINGSTONE_CHESTPLATE)) return TrimArmorPatterns.LIVINGSTONE;

        return null;
    }

    @Unique
    private static ArmorTrim create(ItemStack stack) {
        return stack.get(DataComponents.TRIM);
    }
}
