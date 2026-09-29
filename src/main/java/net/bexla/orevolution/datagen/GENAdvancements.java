package net.bexla.orevolution.datagen;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegDataComponents;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class GENAdvancements extends AdvancementProvider {
    public GENAdvancements(PackOutput output, CompletableFuture<HolderLookup.Provider> future, ExistingFileHelper helper) {
        super(output, future, helper, List.of(new OrevolutionAdvancements()));
    }

    static class OrevolutionAdvancements implements AdvancementGenerator {
        OrevolutionAdvancements() {}

        @Override
        public void generate(@Nullable HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, @Nullable ExistingFileHelper helper) {
            var tinUpgrade = Advancement.Builder.advancement()
                    .parent(getAdv("minecraft:story/upgrade_tools"))
                    .display(info(RegItems.TIN_PICKAXE.get(), "tin_upgrade", AdvancementType.TASK))
                    .addCriterion("has_tin_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(RegItems.TIN_PICKAXE.get()).build()))
                    .save(consumer, "orevolution:story/tin_upgrade");

            Advancement.Builder.advancement()
                    .parent(tinUpgrade)
                    .display(new DisplayInfo(
                            new ItemStack(Items.IRON_INGOT),
                            Component.translatable("advancements.story.smelt_iron.title"),
                            Component.translatable("advancements.story.smelt_iron.description"),
                            Optional.empty(),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    ))
                    .addCriterion(
                            "iron",
                            InventoryChangeTrigger.TriggerInstance.hasItems(
                                    ItemPredicate.Builder.item()
                                            .of(Items.IRON_INGOT)
                                            .build()
                            )
                    )
                    .save(consumer, "minecraft:story/smelt_iron");

            var obtainPlatinum = Advancement.Builder.advancement()
                    .parent(getAdv("minecraft:story/iron_tools"))
                    .display(info(RegItems.PLATINUM_INGOT.get(), "obtain_platinum", AdvancementType.TASK))
                    .addCriterion("has_platinum_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(OrevolutionTags.Items.PLATINUM_INGOTS).build()))
                    .save(consumer, "orevolution:story/obtain_platinum");

            Advancement.Builder.advancement()
                    .parent(obtainPlatinum)
                    .display(info(RegItems.PLATINUM_CHESTPLATE.get(), "platinum_armor", AdvancementType.TASK))
                    .addCriterion("has_all_platinum_armor", InventoryChangeTrigger.TriggerInstance.hasItems(
                            RegItems.PLATINUM_HELMET.get(), RegItems.PLATINUM_CHESTPLATE.get(), RegItems.PLATINUM_LEGGINGS.get(), RegItems.PLATINUM_BOOTS.get()
                    ))
                    .save(consumer, "orevolution:story/platinum_armor");

            var platinum_gear = Advancement.Builder.advancement()
                    .parent(obtainPlatinum)
                    .display(info(RegItems.PLATINUM_PICKAXE.get(), "platinum_gear", AdvancementType.TASK))
                    .addCriterion("has_platinum_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(RegItems.PLATINUM_PICKAXE.get()).build()))
                    .save(consumer, "orevolution:story/platinum_gear");

            Advancement.Builder.advancement()
                    .parent(platinum_gear)
                    .display(new DisplayInfo(
                            new ItemStack(Items.DIAMOND),
                            Component.translatable("advancements.story.mine_diamond.title"),
                            Component.translatable("advancements.story.mine_diamond.description"),
                            Optional.empty(),
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    ))
                    .addCriterion(
                            "diamond",
                            InventoryChangeTrigger.TriggerInstance.hasItems(
                                    ItemPredicate.Builder.item()
                                            .of(Items.DIAMOND)
                                            .build()
                            )
                    )
                    .save(consumer, "minecraft:story/mine_diamond");

            var tungsten = Advancement.Builder.advancement()
                    .parent(getAdv("minecraft:nether/root"))
                    .display(info(RegItems.TUNGSTEN_INGOT.get(), "obtain_tungsten", AdvancementType.TASK))
                    .addCriterion("has_tungsten_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(OrevolutionTags.Items.TUNGSTEN_INGOTS).build()))
                    .save(consumer, "orevolution:nether/obtain_tungsten");

            Advancement.Builder.advancement()
                    .parent(tungsten)
                    .display(info(RegItems.REINFORCED_TEMPLATE.get(), "reinforcement", AdvancementType.GOAL))
                    .addCriterion("has_reinforced", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().hasComponents(
                            DataComponentPredicate.builder().expect(RegDataComponents.REINFORCED.get(), true).build()).build()))
                    .save(consumer, "orevolution:nether/reinforcement");

            Advancement.Builder.advancement()
                    .parent(tungsten)
                    .display(info(RegItems.COATING_TEMPLATE.get(), "coating", AdvancementType.GOAL))
                    .addCriterion("has_coat", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().hasComponents(
                            DataComponentPredicate.builder().expect(RegDataComponents.COATED.get(), true).build()).build()))
                    .save(consumer, "orevolution:nether/coating");

            var primordialAetherrock = Advancement.Builder.advancement()
                    .parent(getAdv("minecraft:end/root"))
                    .display(info(RegBlocks.PRIMITIVE_AETHERROCK.get(), "obtain_primitive_aetherrock", AdvancementType.TASK))
                    .addCriterion("has_primitive_aetherrock", InventoryChangeTrigger.TriggerInstance.hasItems(RegBlocks.PRIMITIVE_AETHERROCK.get()))
                    .save(consumer, "orevolution:end/obtain_primitive_aetherrock");

            Advancement.Builder.advancement()
                    .parent(primordialAetherrock)
                    .display(info(RegItems.AETHERSTEEL_CHESTPLATE.get(), "aethersteel_armor", AdvancementType.CHALLENGE))
                    .addCriterion("has_all_aethersteel_armor", InventoryChangeTrigger.TriggerInstance.hasItems(
                            RegItems.AETHERSTEEL_HELMET.get(), RegItems.AETHERSTEEL_CHESTPLATE.get(), RegItems.AETHERSTEEL_LEGGINGS.get(), RegItems.AETHERSTEEL_BOOTS.get()
                    ))
                    .save(consumer, "orevolution:end/aethersteel_armor");

            Advancement.Builder.advancement()
                    .parent(getAdv("minecraft:husbandry/obtain_netherite_hoe"))
                    .display(info(RegItems.AETHERSTEEL_HOE.get(), "obtain_aethersteel_hoe", AdvancementType.CHALLENGE))
                    .addCriterion("has_all_aethersteel_armor", InventoryChangeTrigger.TriggerInstance.hasItems(
                            RegItems.AETHERSTEEL_HOE.get()
                    ))
                    .save(consumer, "orevolution:husbandry/obtain_aethersteel_hoe");
        }

        protected AdvancementHolder getAdv(String loc) {
            return Advancement.Builder.advancement().build(ResourceLocation.parse(loc));
        }

        protected DisplayInfo info(ItemLike icon, String id, AdvancementType type) {
            return info(new ItemStack(icon), id, type);
        }

        protected DisplayInfo info(ItemStack icon, String id, AdvancementType type) {
            var advancementId = Orevolution.MODID + "." + id;
            return new DisplayInfo(
                    icon,
                    Component.translatable("advancements.%s.title".formatted(advancementId)),
                    Component.translatable("advancements.%s.description".formatted(advancementId)),
                    Optional.empty(),
                    type,
                    true,
                    true,
                    false
            );
        }
    }
}
