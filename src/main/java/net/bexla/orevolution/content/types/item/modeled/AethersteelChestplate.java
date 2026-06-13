package net.bexla.orevolution.content.types.item.modeled;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.models.AethersteelChestplateModel;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.Nullable;

import static net.bexla.orevolution.Orevolution.lc;

@EventBusSubscriber(modid = Orevolution.MODID, value = Dist.CLIENT)
public class AethersteelChestplate extends ArmorItem {
    private static final ResourceLocation chestplate = lc("textures/models/armor/aethersteel_chestplate.png");

    public AethersteelChestplate(Holder<ArmorMaterial> material, ArmorItem.Type type, Item.Properties properties) {
        super(material, type, properties);
    }

    @Nullable
    @Override
    public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return stack.is(RegItems.AETHERSTEEL_CHESTPLATE)? chestplate : super.getArmorTexture(stack, entity, slot, layer, innerModel);
    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> properties) {
                if (stack.is(RegItems.AETHERSTEEL_CHESTPLATE)) {
                    return AethersteelChestplateModel.INSTANCE;
                }
                return properties;
            }
        }, RegItems.AETHERSTEEL_CHESTPLATE);
    }
}
