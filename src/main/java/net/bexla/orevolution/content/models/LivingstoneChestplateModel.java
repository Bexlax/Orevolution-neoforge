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
public class LivingstoneChestplateModel<T extends LivingEntity> extends HumanoidArmorModel<T> {

    public static final LivingstoneChestplateModel<?> INSTANCE =
            new LivingstoneChestplateModel<>(
                    createLayerDefinition(LayerDefinitions.OUTER_ARMOR_DEFORMATION).bakeRoot()
            );

    public LivingstoneChestplateModel(ModelPart root) {
        super(root);
    }

    public static MeshDefinition createBodyLayer(CubeDeformation deformation) {
        MeshDefinition mesh = HumanoidArmorModel.createBodyLayer(deformation);
        PartDefinition root = mesh.getRoot();

        PartDefinition body = root.getChild("body");
        PartDefinition leftArm = root.getChild("left_arm");
        PartDefinition rightArm = root.getChild("right_arm");

        body.addOrReplaceChild(
                "livingstone_body",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4,
                                new CubeDeformation(0.75F)),
                PartPose.ZERO
        );

        leftArm.addOrReplaceChild(
                "livingstone_left_arm",
                CubeListBuilder.create()
                        .texOffs(24, 0)
                        .mirror()
                        .addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4,
                                new CubeDeformation(0.75F))
                        .mirror(false)
                        .texOffs(0, 16)
                        .mirror()
                        .addBox(-1.25F, -2.0F, -2.0F, 4, 12, 4,
                                new CubeDeformation(0.53F))
                        .mirror(false),
                PartPose.ZERO
        );

        rightArm.addOrReplaceChild(
                "livingstone_right_arm",
                CubeListBuilder.create()
                        .texOffs(24, 0)
                        .addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4,
                                new CubeDeformation(0.75F))
                        .texOffs(0, 16)
                        .addBox(-2.75F, -2.0F, -2.0F, 4, 12, 4,
                                new CubeDeformation(0.53F)),
                PartPose.ZERO
        );

        return mesh;
    }

    public static LayerDefinition createLayerDefinition(CubeDeformation deformation) {
        return LayerDefinition.create(createBodyLayer(deformation), 64, 32);
    }
}