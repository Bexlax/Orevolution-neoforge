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
public class ReinforcedNetheriteLeggingsModel<T extends LivingEntity> extends HumanoidArmorModel<T> {

    public static final ReinforcedNetheriteLeggingsModel<?> INSTANCE =
            new ReinforcedNetheriteLeggingsModel<>(
                    ReinforcedNetheriteLeggingsModel.createLayerDefinition(LayerDefinitions.INNER_ARMOR_DEFORMATION).bakeRoot()
            );

    public ReinforcedNetheriteLeggingsModel(ModelPart root) {
        super(root);
    }

    public static MeshDefinition createBodyLayer(CubeDeformation deformation) {
        MeshDefinition meshDefinition = HumanoidArmorModel.createBodyLayer(deformation);
        PartDefinition root = meshDefinition.getRoot();

        PartDefinition body = root.getChild("body");
        PartDefinition leftLeg = root.getChild("left_leg");
        PartDefinition rightLeg = root.getChild("right_leg");

        body.addOrReplaceChild(
                "waist",
                CubeListBuilder.create()
                        .texOffs(0, 16)
                        .addBox(
                                -4.0F, 0.0F, -2.0F,
                                8.0F, 12.0F, 4.0F,
                                new CubeDeformation(0.5F)
                        ),
                PartPose.ZERO
        );

        leftLeg.addOrReplaceChild(
                "reinforced_left_leg",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .mirror()
                        .addBox(
                                -2.0F, 0.0F, -2.0F,
                                4.0F, 12.0F, 4.0F,
                                new CubeDeformation(0.5F)
                        )
                        .mirror(false),
                PartPose.ZERO
        );

        leftLeg.getChild("reinforced_left_leg").addOrReplaceChild(
                "plate",
                CubeListBuilder.create()
                        .texOffs(16, 0)
                        .mirror()
                        .addBox(
                                -2.0F, -6.0F, -3.0F,
                                4.0F, 8.0F, 5.0F,
                                new CubeDeformation(0.25F)
                        )
                        .mirror(false),
                PartPose.offsetAndRotation(
                        1.0F, 5.4F, 0.5F,
                        0.0F, 0.0F, -0.0873F
                )
        );

        rightLeg.addOrReplaceChild(
                "reinforced_right_leg",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(
                                -2.0F, 0.0F, -2.0F,
                                4.0F, 12.0F, 4.0F,
                                new CubeDeformation(0.5F)
                        ),
                PartPose.ZERO
        );

        rightLeg.getChild("reinforced_right_leg").addOrReplaceChild(
                "plate",
                CubeListBuilder.create()
                        .texOffs(16, 0)
                        .addBox(
                                -2.0F, -6.0F, -3.0F,
                                4.0F, 8.0F, 5.0F,
                                new CubeDeformation(0.25F)
                        ),
                PartPose.offsetAndRotation(
                        -1.0F, 5.4F, 0.5F,
                        0.0F, 0.0F, 0.0873F
                )
        );

        return meshDefinition;
    }

    public static LayerDefinition createLayerDefinition(CubeDeformation deformation) {
        return LayerDefinition.create(createBodyLayer(deformation), 64, 32);
    }
}