package net.bexla.orevolution.datagen;

import com.teamabnormals.blueprint.core.data.server.tags.BlueprintItemTagsProvider;
import com.teamabnormals.blueprint.core.util.TagUtil;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

import static net.bexla.orevolution.Orevolution.lc;


public class GENItemTags extends BlueprintItemTagsProvider {
    public GENItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagsProvider.TagLookup<Block>> lookup, ExistingFileHelper helper) {
        super(Orevolution.MODID, output, provider, lookup, helper);
    }

    @Override
    public @NotNull String getName() {
        return "Orevolution Item Tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.ARROWS).add(RegItems.FIERY_ARROW.get());

        tag(ItemTags.PIGLIN_LOVED)
                .add(
                        RegItems.TUNGSTEN_INGOT.get(),
                        RegItems.TUNGSTEN_HELMET.get(),
                        RegItems.TUNGSTEN_CHESTPLATE.get(),
                        RegItems.TUNGSTEN_LEGGINGS.get(),
                        RegItems.TUNGSTEN_BOOTS.get(),
                        RegItems.TUNGSTEN_SWORD.get(),
                        RegItems.TUNGSTEN_PICKAXE.get(),
                        RegItems.TUNGSTEN_AXE.get(),
                        RegItems.TUNGSTEN_SHOVEL.get(),
                        RegItems.TUNGSTEN_HOE.get()
                ).addTags(OrevolutionTags.Items.TUNGSTEN_BLOCKS).addTags(OrevolutionTags.Items.TUNGSTEN_ORES);

        tag(ItemTags.DYEABLE)
                .add(
                        RegItems.BRONZE_HELMET.get(),
                        RegItems.BRONZE_CHESTPLATE.get(),
                        RegItems.BRONZE_LEGGINGS.get(),
                        RegItems.BRONZE_BOOTS.get()
                );

        tag(OrevolutionTags.Items.REINFORCE_BLACKLIST)
                .add(
                        RegItems.TUNGSTEN_HELMET.get(),
                        RegItems.TUNGSTEN_CHESTPLATE.get(),
                        RegItems.TUNGSTEN_LEGGINGS.get(),
                        RegItems.TUNGSTEN_BOOTS.get(),
                        RegItems.TUNGSTEN_SWORD.get(),
                        RegItems.TUNGSTEN_PICKAXE.get(),
                        RegItems.TUNGSTEN_AXE.get(),
                        RegItems.TUNGSTEN_SHOVEL.get(),
                        RegItems.TUNGSTEN_HOE.get()
                );

        tag(OrevolutionTags.Items.COAT_BLACKLIST)
                .add(
                        RegItems.TUNGSTEN_HELMET.get(),
                        RegItems.TUNGSTEN_CHESTPLATE.get(),
                        RegItems.TUNGSTEN_LEGGINGS.get(),
                        RegItems.TUNGSTEN_BOOTS.get(),
                        RegItems.TUNGSTEN_SWORD.get(),
                        RegItems.TUNGSTEN_PICKAXE.get(),
                        RegItems.TUNGSTEN_AXE.get(),
                        RegItems.TUNGSTEN_SHOVEL.get(),
                        RegItems.TUNGSTEN_HOE.get()
                );

        tag(OrevolutionTags.Items.NEGATES_PYRITE_FIRE)
                .add(
                        Items.NETHERITE_HELMET,
                        Items.NETHERITE_CHESTPLATE,
                        Items.NETHERITE_LEGGINGS,
                        Items.NETHERITE_BOOTS,
                        RegItems.MOONSTONE_PICKAXE.get(),
                        Items.GOLDEN_PICKAXE
                );

        tag(OrevolutionTags.Items.ACCEPTS_COAT)
                .addTags(
                        Tags.Items.TOOLS_SHIELD,
                        ItemTags.AXES,
                        ItemTags.PICKAXES,
                        ItemTags.SHOVELS,
                        ItemTags.HOES,
                        ItemTags.SWORDS,
                        ItemTags.HEAD_ARMOR,
                        ItemTags.CHEST_ARMOR,
                        ItemTags.LEG_ARMOR,
                        ItemTags.FOOT_ARMOR
                ).add(Items.ELYTRA)
                .addOptionalTag(ResourceLocation.fromNamespaceAndPath("farmersdelight", "tools/knives"))
                .addOptionalTag(ResourceLocation.fromNamespaceAndPath("c", "tools/knives"));

        tag(OrevolutionTags.Items.ACCEPTS_REINFORCEMENT).addTags(
                        ItemTags.DURABILITY_ENCHANTABLE
                );

        tag(OrevolutionTags.Items.TUNGSTEN_REPAIR_BLACKLIST).add(
                RegItems.AETHERSTEEL_HELMET.get(),
                RegItems.AETHERSTEEL_CHESTPLATE.get(),
                RegItems.AETHERSTEEL_LEGGINGS.get(),
                RegItems.AETHERSTEEL_BOOTS.get(),
                RegItems.AETHERSTEEL_SWORD.get(),
                RegItems.AETHERSTEEL_PICKAXE.get(),
                RegItems.AETHERSTEEL_AXE.get(),
                RegItems.AETHERSTEEL_SHOVEL.get(),
                RegItems.AETHERSTEEL_HOE.get()
        );

        tag(OrevolutionTags.Items.TOOL_POWERS)
                .addTags(
                        ItemTags.AXES,
                        ItemTags.PICKAXES,
                        ItemTags.SHOVELS,
                        ItemTags.HOES
                )
                .addOptionalTag(ResourceLocation.fromNamespaceAndPath("farmersdelight", "tools/knives"))
                .addOptionalTag(ResourceLocation.fromNamespaceAndPath("c", "tools/knives"));
        tag(OrevolutionTags.Items.WEAPON_POWERS)
                .addTags(
                        ItemTags.SWORDS
                ).addOptionalTag(ResourceLocation.withDefaultNamespace("spears"));

        tag(Tags.Items.SEEDS)
                .add(
                        RegItems.DEAD_SEED.get(),
                        RegItems.PETRIFIED_SEED.get()
                );

        tag(Tags.Items.GEMS)
                .add(
                        RegItems.CELESTITE_SHARD.get()
                );


        tag(OrevolutionTags.Items.SHIELDS)
                .add(
                        RegItems.AETHERSTEEL_SHIELD.get(),
                        RegItems.TIN_SHIELD.get(),
                        RegItems.PLATINUM_SHIELD.get(),
                        RegItems.VERDITE_SHIELD.get(),
                        RegItems.LIVINGSTONE_SHIELD.get()
                );

        tag(Tags.Items.TOOLS_SHIELD)
                .add(
                        RegItems.AETHERSTEEL_SHIELD.get(),
                        RegItems.TIN_SHIELD.get(),
                        RegItems.PLATINUM_SHIELD.get(),
                        RegItems.VERDITE_SHIELD.get(),
                        RegItems.LIVINGSTONE_SHIELD.get()
                );


        tag(OrevolutionTags.Items.SHIELDS_SHIELD_EXPANSION)
                .add(
                        RegItems.AETHERSTEEL_SHIELD.get(),
                        RegItems.TIN_SHIELD.get(),
                        RegItems.PLATINUM_SHIELD.get(),
                        RegItems.VERDITE_SHIELD.get(),
                        RegItems.LIVINGSTONE_SHIELD.get()
                );

        tag(ItemTags.TRIM_MATERIALS)
                .add(
                        RegItems.TIN_INGOT.get(),
                        RegItems.PLATINUM_INGOT.get(),
                        RegItems.TUNGSTEN_INGOT.get(),
                        RegItems.AETHERSTEEL_INGOT.get(),
                        RegItems.LIVINGSTONE_SHARD.get(),
                        RegItems.VERDITE_INGOT.get()
                );

        tag(TagUtil.itemTag("minecraft", "spears"))
                .addOptional(lc("tin_spear")).addOptional(lc("platinum_spear")).addOptional(lc("aethersteel_spear"))
                .addOptional(lc("livingstone_spear")).addOptional(lc("verdite_spear")).addOptional(lc("moonstone_spear"));

        tag(OrevolutionTags.Items.KNIFE).addOptional(lc("tin_knife")).addOptional(lc("platinum_knife")).addOptional(lc("moonstone_knife"))
                .addOptional(lc("aethersteel_knife")).addOptional(lc("livingstone_knife")).addOptional(lc("verdite_knife"));

        tag(OrevolutionTags.Items.KNIVES_FARMERS_DELIGHT).addOptional(lc("tin_knife")).addOptional(lc("platinum_knife")).addOptional(lc("moonstone_knife"))
                .addOptional(lc("aethersteel_knife")).addOptional(lc("livingstone_knife")).addOptional(lc("verdite_knife"));

        tag(ItemTags.SWORDS)
                .add(
                        RegItems.TIN_SWORD.get(),
                        RegItems.CASSITERITE_SWORD.get(),
                        RegItems.PLATINUM_SWORD.get(),
                        RegItems.MOONSTONE_SWORD.get(),
                        RegItems.AETHERSTEEL_SWORD.get(),
                        RegItems.LIVINGSTONE_SWORD.get(),
                        RegItems.VERDITE_SWORD.get(),
                        RegItems.STEEL_HEAVYWORK_SWORD.get(),
                        RegItems.TUNGSTEN_SWORD.get()
                );
        tag(ItemTags.PICKAXES)
                .add(
                        RegItems.TIN_PICKAXE.get(),
                        RegItems.CASSITERITE_PICKAXE.get(),
                        RegItems.PLATINUM_PICKAXE.get(),
                        RegItems.MOONSTONE_PICKAXE.get(),
                        RegItems.AETHERSTEEL_PICKAXE.get(),
                        RegItems.LIVINGSTONE_PICKAXE.get(),
                        RegItems.VERDITE_PICKAXE.get(),
                        RegItems.STEEL_HEAVYWORK_PICKAXE.get(),
                        RegItems.TUNGSTEN_PICKAXE.get()
                );
        tag(ItemTags.AXES)
                .add(
                        RegItems.TIN_AXE.get(),
                        RegItems.CASSITERITE_AXE.get(),
                        RegItems.PLATINUM_AXE.get(),
                        RegItems.MOONSTONE_AXE.get(),
                        RegItems.AETHERSTEEL_AXE.get(),
                        RegItems.LIVINGSTONE_AXE.get(),
                        RegItems.VERDITE_AXE.get(),
                        RegItems.STEEL_HEAVYWORK_AXE.get(),
                        RegItems.TUNGSTEN_AXE.get()
                );
        tag(ItemTags.SHOVELS)
                .add(
                        RegItems.TIN_SHOVEL.get(),
                        RegItems.CASSITERITE_SHOVEL.get(),
                        RegItems.PLATINUM_SHOVEL.get(),
                        RegItems.MOONSTONE_SHOVEL.get(),
                        RegItems.AETHERSTEEL_SHOVEL.get(),
                        RegItems.LIVINGSTONE_SHOVEL.get(),
                        RegItems.VERDITE_SHOVEL.get(),
                        RegItems.STEEL_HEAVYWORK_SHOVEL.get(),
                        RegItems.TUNGSTEN_SHOVEL.get()
                );
        tag(ItemTags.HOES)
                .add(
                        RegItems.TIN_HOE.get(),
                        RegItems.CASSITERITE_HOE.get(),
                        RegItems.PLATINUM_HOE.get(),
                        RegItems.MOONSTONE_HOE.get(),
                        RegItems.AETHERSTEEL_HOE.get(),
                        RegItems.LIVINGSTONE_HOE.get(),
                        RegItems.VERDITE_HOE.get(),
                        RegItems.STEEL_HEAVYWORK_HOE.get(),
                        RegItems.TUNGSTEN_HOE.get()
                );
        
        tag(ItemTags.HEAD_ARMOR)
                .add(
                        RegItems.PLATINUM_HELMET.get(),
                        RegItems.MOONSTONE_HELMET.get(),
                        RegItems.BRONZE_HELMET.get(),
                        RegItems.TUNGSTEN_HELMET.get(),
                        RegItems.AETHERSTEEL_HELMET.get(),
                        RegItems.LIVINGSTONE_HELMET.get(),
                        RegItems.VERDITE_HELMET.get()
                );
        tag(ItemTags.CHEST_ARMOR)
                .add(
                        RegItems.PLATINUM_CHESTPLATE.get(),
                        RegItems.MOONSTONE_CHESTPLATE.get(),
                        RegItems.BRONZE_CHESTPLATE.get(),
                        RegItems.TUNGSTEN_CHESTPLATE.get(),
                        RegItems.AETHERSTEEL_CHESTPLATE.get(),
                        RegItems.LIVINGSTONE_CHESTPLATE.get(),
                        RegItems.VERDITE_CHESTPLATE.get()
                );
        tag(ItemTags.LEG_ARMOR)
                .add(
                        RegItems.PLATINUM_LEGGINGS.get(),
                        RegItems.MOONSTONE_LEGGINGS.get(),
                        RegItems.BRONZE_LEGGINGS.get(),
                        RegItems.TUNGSTEN_LEGGINGS.get(),
                        RegItems.AETHERSTEEL_LEGGINGS.get(),
                        RegItems.LIVINGSTONE_LEGGINGS.get(),
                        RegItems.VERDITE_LEGGINGS.get()
                );
        tag(ItemTags.FOOT_ARMOR)
                .add(
                        RegItems.PLATINUM_BOOTS.get(),
                        RegItems.MOONSTONE_BOOTS.get(),
                        RegItems.BRONZE_BOOTS.get(),
                        RegItems.TUNGSTEN_BOOTS.get(),
                        RegItems.AETHERSTEEL_BOOTS.get(),
                        RegItems.LIVINGSTONE_BOOTS.get(),
                        RegItems.VERDITE_BOOTS.get()
                );
        
        tag(OrevolutionTags.Items.TIN_INGOTS)
                .add(RegItems.TIN_INGOT.get());
        tag(OrevolutionTags.Items.CASSITERITE_INGOTS)
                .add(RegItems.CASSITERITE_INGOT.get());
        tag(OrevolutionTags.Items.PLATINUM_INGOTS)
                .add(RegItems.PLATINUM_INGOT.get());
        tag(OrevolutionTags.Items.TUNGSTEN_INGOTS)
                .add(RegItems.TUNGSTEN_INGOT.get());
        tag(OrevolutionTags.Items.ENDERITE_ADJACENT)
                .add(RegItems.AETHERSTEEL_INGOT.get());
        tag(OrevolutionTags.Items.VERDITE_INGOTS)
                .add(RegItems.VERDITE_INGOT.get());
        tag(OrevolutionTags.Items.BRONZE_INGOTS)
                .add(RegItems.BRONZE_ALLOY.get());
        tag(OrevolutionTags.Items.STEEL_INGOTS)
                .add(RegItems.STEEL_ALLOY.get());

        tag(OrevolutionTags.Items.TIN_NUGGETS)
                .add(RegItems.TIN_NUGGET.get());
        tag(OrevolutionTags.Items.CASSITERITE_NUGGETS)
                .add(RegItems.CASSITERITE_NUGGET.get());
        tag(OrevolutionTags.Items.PLATINUM_NUGGETS)
                .add(RegItems.PLATINUM_NUGGET.get());
        tag(OrevolutionTags.Items.TUNGSTEN_NUGGETS)
                .add(RegItems.TUNGSTEN_NUGGET.get());
        tag(OrevolutionTags.Items.VERDITE_NUGGETS)
                .add(RegItems.VERDITE_NUGGET.get());
        tag(OrevolutionTags.Items.LIVINGSTONE_FRAGMENTS)
                .add(RegItems.LIVINGSTONE_SHARD.get());

        tag(OrevolutionTags.Items.TIN_RAWS)
                .add(RegItems.RAW_TIN.get());
        tag(OrevolutionTags.Items.CASSITERITE_RAWS)
                .add(RegItems.RAW_CASSITERITE.get());
        tag(OrevolutionTags.Items.PLATINUM_RAWS)
                .add(RegItems.RAW_PLATINUM.get());
        tag(OrevolutionTags.Items.TUNGSTEN_RAWS)
                .add(RegItems.RAW_TUNGSTEN.get());

        tag(Tags.Items.INGOTS).addTags(
                OrevolutionTags.Items.TIN_INGOTS,
                OrevolutionTags.Items.CASSITERITE_INGOTS,
                OrevolutionTags.Items.PLATINUM_INGOTS,
                OrevolutionTags.Items.TUNGSTEN_INGOTS,
                OrevolutionTags.Items.ENDERITE_ADJACENT,
                OrevolutionTags.Items.VERDITE_INGOTS,
                OrevolutionTags.Items.BRONZE_INGOTS,
                OrevolutionTags.Items.STEEL_INGOTS
        );
        tag(Tags.Items.NUGGETS).addTags(
                OrevolutionTags.Items.TIN_NUGGETS,
                OrevolutionTags.Items.CASSITERITE_NUGGETS,
                OrevolutionTags.Items.PLATINUM_NUGGETS,
                OrevolutionTags.Items.TUNGSTEN_NUGGETS,
                OrevolutionTags.Items.VERDITE_NUGGETS
        );
        tag(Tags.Items.RAW_MATERIALS).addTags(
                OrevolutionTags.Items.TIN_RAWS,
                OrevolutionTags.Items.CASSITERITE_RAWS,
                OrevolutionTags.Items.PLATINUM_RAWS,
                OrevolutionTags.Items.TUNGSTEN_RAWS
        );

        tag(ItemTags.BEACON_PAYMENT_ITEMS)
            .add(
                    RegItems.TIN_INGOT.get(),
                    RegItems.CASSITERITE_INGOT.get(),
                    RegItems.PLATINUM_INGOT.get(),
                    RegItems.TUNGSTEN_INGOT.get(),
                    RegItems.VERDITE_INGOT.get()
            );

        copy(OrevolutionTags.Blocks.TIN_BLOCKS, OrevolutionTags.Items.TIN_BLOCKS);
        copy(OrevolutionTags.Blocks.CASSITERITE_BLOCKS, OrevolutionTags.Items.CASSITERITE_BLOCKS);
        copy(OrevolutionTags.Blocks.PLATINUM_BLOCKS, OrevolutionTags.Items.PLATINUM_BLOCKS);
        copy(OrevolutionTags.Blocks.TUNGSTEN_BLOCKS, OrevolutionTags.Items.TUNGSTEN_BLOCKS);
        copy(OrevolutionTags.Blocks.VERDITE_BLOCKS, OrevolutionTags.Items.VERDITE_BLOCKS);
        copy(OrevolutionTags.Blocks.ENDERITE_ADJACENT_BLOCKS, OrevolutionTags.Items.ENDERITE_ADJACENT_BLOCKS);
        copy(OrevolutionTags.Blocks.LIVINGSTONE_BLOCKS, OrevolutionTags.Items.LIVINGSTONE_BLOCKS);

        copy(OrevolutionTags.Blocks.TIN_ORES, OrevolutionTags.Items.TIN_ORES);
        copy(OrevolutionTags.Blocks.CASSITERITE_ORES, OrevolutionTags.Items.CASSITERITE_ORES);
        copy(OrevolutionTags.Blocks.PLATINUM_ORES, OrevolutionTags.Items.PLATINUM_ORES);
        copy(OrevolutionTags.Blocks.TUNGSTEN_ORES, OrevolutionTags.Items.TUNGSTEN_ORES);
        copy(OrevolutionTags.Blocks.XP_ORES, OrevolutionTags.Items.XP_ORES);

        copy(OrevolutionTags.Blocks.RAW_TIN_BLOCKS, OrevolutionTags.Items.RAW_TIN_BLOCKS);
        copy(OrevolutionTags.Blocks.RAW_CASSITERITE_BLOCKS, OrevolutionTags.Items.RAW_CASSITERITE_BLOCKS);
        copy(OrevolutionTags.Blocks.RAW_PLATINUM_BLOCKS, OrevolutionTags.Items.RAW_PLATINUM_BLOCKS);
        copy(OrevolutionTags.Blocks.RAW_TUNGSTEN_BLOCKS, OrevolutionTags.Items.RAW_TUNGSTEN_BLOCKS);

        copy(BlockTags.EMERALD_ORES, ItemTags.EMERALD_ORES);

        tag(Tags.Items.STORAGE_BLOCKS)
            .addTags(
                    OrevolutionTags.Items.TIN_BLOCKS,
                    OrevolutionTags.Items.PLATINUM_BLOCKS,
                    OrevolutionTags.Items.TUNGSTEN_BLOCKS,
                    OrevolutionTags.Items.VERDITE_BLOCKS,
                    OrevolutionTags.Items.ENDERITE_ADJACENT_BLOCKS,
                    OrevolutionTags.Items.LIVINGSTONE_BLOCKS
            );
    }
}
