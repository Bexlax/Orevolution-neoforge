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
public class ReinforcedNetheriteChestplateModel<T extends LivingEntity> extends HumanoidArmorModel<T> {

    public static final ReinforcedNetheriteChestplateModel<?> INSTANCE =
            new ReinforcedNetheriteChestplateModel<>(
                    createLayerDefinition(LayerDefinitions.OUTER_ARMOR_DEFORMATION).bakeRoot()
            );

    public ReinforcedNetheriteChestplateModel(ModelPart root) {
        super(root);
    }

    public static MeshDefinition createBodyLayer(CubeDeformation deformation) {
        MeshDefinition mesh = HumanoidArmorModel.createBodyLayer(deformation);
        PartDefinition root = mesh.getRoot();

        PartDefinition body = root.getChild("body");
        PartDefinition leftArm = root.getChild("left_arm");
        PartDefinition rightArm = root.getChild("right_arm");

        body.addOrReplaceChild(
                "reinforced_netherite_body",
                CubeListBuilder.create()
                        .texOffs(40, 0)
                        .addBox(-4.0F, 0.0F, -2.0F,
                                8.0F, 12.0F, 4.0F,
                                new CubeDeformation(0.85F))
                        .texOffs(0, 0)
                        .addBox(-4.0F, 0.0F, -2.0F,
                                8.0F, 12.0F, 4.0F,
                                new CubeDeformation(0.75F)),
                PartPose.ZERO
        );

        PartDefinition leftArmArmor = leftArm.addOrReplaceChild(
                "reinforced_netherite_left_arm",
                CubeListBuilder.create()
                        .texOffs(24, 0)
                        .mirror()
                        .addBox(-1.0F, -2.0F, -2.0F,
                                4.0F, 12.0F, 4.0F,
                                new CubeDeformation(0.75F))
                        .mirror(false),
                PartPose.ZERO
        );

        leftArmArmor.addOrReplaceChild(
                "shoulder_pad",
                CubeListBuilder.create()
                        .texOffs(0, 16)
                        .mirror()
                        .addBox(-3.0F, -2.0F, -2.5F,
                                7.0F, 5.0F, 5.0F,
                                new CubeDeformation(0.65F))
                        .mirror(false),
                PartPose.offsetAndRotation(
                        1.9672F, -1.078F, 0.0F,
                        0.0F, 0.0F, -0.1309F
                )
        );

        PartDefinition rightArmArmor = rightArm.addOrReplaceChild(
                "reinforced_netherite_right_arm",
                CubeListBuilder.create()
                        .texOffs(24, 0)
                        .addBox(-3.0F, -2.0F, -2.0F,
                                4.0F, 12.0F, 4.0F,
                                new CubeDeformation(0.75F)),
                PartPose.ZERO
        );

        rightArmArmor.addOrReplaceChild(
                "shoulder_pad",
                CubeListBuilder.create()
                        .texOffs(0, 16)
                        .addBox(-3.0F, -2.0F, -2.5F,
                                7.0F, 5.0F, 5.0F,
                                new CubeDeformation(0.65F)),
                PartPose.offsetAndRotation(
                        -3.0328F, -1.078F, 0.0F,
                        0.0F, 0.0F, 0.1309F
                )
        );

        return mesh;
    }

    public static LayerDefinition createLayerDefinition(CubeDeformation deformation) {
        return LayerDefinition.create(createBodyLayer(deformation), 64, 32);
    }
}