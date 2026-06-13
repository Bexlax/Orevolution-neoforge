package net.bexla.orevolution.content.types.item.modeled;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.models.ReinforcedNetheriteChestplateModel;
import net.bexla.orevolution.content.models.ReinforcedNetheriteLeggingsModel;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.Nullable;

import static net.bexla.orevolution.Orevolution.lc;

@EventBusSubscriber(modid = Orevolution.MODID, value = Dist.CLIENT)
public class ReinforcedArmor extends ArmorItem {
    private static final ResourceLocation chestplate = lc("textures/models/armor/reinforced_netherite_chestplate.png");
    private static final ResourceLocation leggings = lc("textures/models/armor/reinforced_netherite_leggings.png");

    public ReinforcedArmor(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Nullable
    @Override
    public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        if(stack.is(RegItems.REINFORCED_NETHERITE_CHESTPLATE))
            return chestplate;
        if(stack.is(RegItems.REINFORCED_NETHERITE_LEGGINGS))
            return leggings;
        return super.getArmorTexture(stack, entity, slot, layer, innerModel);
    }

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> properties) {
                if (stack.is(RegItems.REINFORCED_NETHERITE_LEGGINGS)) {
                    return ReinforcedNetheriteLeggingsModel.INSTANCE;
                }
                if (stack.is(RegItems.REINFORCED_NETHERITE_CHESTPLATE)) {
                    return ReinforcedNetheriteChestplateModel.INSTANCE;
                }
                return properties;
            }
        }, RegItems.REINFORCED_NETHERITE_LEGGINGS, RegItems.REINFORCED_NETHERITE_CHESTPLATE);
    }
}
