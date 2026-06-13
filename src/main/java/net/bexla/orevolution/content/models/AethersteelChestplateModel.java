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
public class AethersteelChestplateModel<T extends LivingEntity> extends HumanoidArmorModel<T> {
    public static final AethersteelChestplateModel<?> INSTANCE =
            new AethersteelChestplateModel<>(
                    createLayerDefinition(LayerDefinitions.OUTER_ARMOR_DEFORMATION).bakeRoot()
            );

    public AethersteelChestplateModel(ModelPart root) {
        super(root);
    }

    public static MeshDefinition createBodyLayer(CubeDeformation deformation) {
        MeshDefinition meshDefinition = HumanoidArmorModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition root = meshDefinition.getRoot();

        // Chestplate body
        root.getChild("body").addOrReplaceChild(
                "aethersteel_body",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-4F, 0F, -2F, 8F, 12F, 4F, new CubeDeformation(0.75F)),
                PartPose.ZERO
        );

        // Left arm base
        PartDefinition leftArm = root.getChild("left_arm");

        leftArm.addOrReplaceChild(
                "aethersteel_left_arm",
                CubeListBuilder.create()
                        .texOffs(24, 0)
                        .mirror()
                        .addBox(-1F, -2F, -2F, 4F, 12F, 4F, new CubeDeformation(0.55F))
                        .mirror(false),
                PartPose.ZERO
        );

        leftArm.addOrReplaceChild(
                "aethersteel_left_pauldron",
                CubeListBuilder.create()
                        .texOffs(40, 0)
                        .mirror()
                        .addBox(0F,
                                -2.5F,
                                -2F,
                                5F,
                                14F,
                                4F,
                                new CubeDeformation(0.77F)
                        )
                        .mirror(false),
                PartPose.offsetAndRotation(
                        -1F,
                        -0.05F,
                        0F,
                        0F,
                        0F,
                        -0.1309F
                )
        );

        // Right arm base
        PartDefinition rightArm = root.getChild("right_arm");

        rightArm.addOrReplaceChild(
                "aethersteel_right_arm",
                CubeListBuilder.create()
                        .texOffs(24, 0)
                        .addBox(
                                -3F,
                                -2F,
                                -2F,
                                4F,
                                12F,
                                4F,
                                new CubeDeformation(0.55F)
                        ),
                PartPose.ZERO
        );

        rightArm.addOrReplaceChild(
                "aethersteel_right_pauldron",
                CubeListBuilder.create()
                        .texOffs(40, 0)
                        .addBox(
                                -5F,
                                -2.5F,
                                -2F,
                                5F,
                                14F,
                                4F,
                                new CubeDeformation(0.77F)
                        ),
                PartPose.offsetAndRotation(
                        1F,
                        -0.05F,
                        0F,
                        0F,
                        0F,
                        0.1309F
                )
        );

        return meshDefinition;
    }

    public static LayerDefinition createLayerDefinition(CubeDeformation deformation) {
        return LayerDefinition.create(createBodyLayer(deformation), 64, 32);
    }
}