package net.bexla.orevolution.content.models;

import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TungstenArmorModel<T extends LivingEntity> extends HumanoidArmorModel<T> {

    public static final TungstenArmorModel<?> INSTANCE =
            new TungstenArmorModel<>(
                    createLayerDefinition(LayerDefinitions.OUTER_ARMOR_DEFORMATION).bakeRoot()
            );

    public TungstenArmorModel(ModelPart root) {
        super(root);
    }

    public static MeshDefinition createBodyLayer(CubeDeformation deformation) {
        MeshDefinition mesh = HumanoidArmorModel.createBodyLayer(deformation);
        PartDefinition root = mesh.getRoot();

        PartDefinition head = root.getChild("head");

        head.addOrReplaceChild(
                "tungsten_helmet_left_decoration",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .mirror()
                        .addBox(0.0F, -0.25F, -1.5F, 1.0F, 5.0F, 3.0F,
                                new CubeDeformation(0.0F))
                        .mirror(false),
                PartPose.offsetAndRotation(4.75F, -5.75F, 0.5F, 0.0F, 0.0F, -0.1745F)
        );

        head.addOrReplaceChild(
                "tungsten_helmet_right_decoration",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-1.0F, -0.25F, -1.5F, 1.0F, 5.0F, 3.0F,
                                new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-4.75F, -5.75F, 0.5F, 0.0F, 0.0F, 0.1745F)
        );

        return mesh;
    }


    public static LayerDefinition createLayerDefinition(CubeDeformation deformation) {
        return LayerDefinition.create(createBodyLayer(deformation), 64, 32);
    }
}