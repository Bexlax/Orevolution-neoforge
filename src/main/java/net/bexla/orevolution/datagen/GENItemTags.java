package net.bexla.orevolution.datagen;

import com.teamabnormals.blueprint.core.data.server.tags.BlueprintItemTagsProvider;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
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
//        tag(ItemTags.ARROWS).add(RegItemsAE.TIN_ARROW.get()).add(RegItemsAE.PLATINUM_ARROW.get()).add(RegItemsAE.AETHERSTEEL_ARROW.get());
//        tag(OrevolutionTags.Items.bows).add(RegItemsAE.TIN_BOW.get()).add(RegItemsAE.PLATINUM_BOW.get()).add(RegItemsAE.AETHERSTEEL_BOW.get());
//        tag(OrevolutionTags.Items.bowsAE).add(RegItemsAE.TIN_BOW.get()).add(RegItemsAE.PLATINUM_BOW.get()).add(RegItemsAE.AETHERSTEEL_BOW.get());

        tag(Tags.Items.SEEDS).add(RegItems.DEAD_SEED.get()).add(RegItems.PETRIFIED_SEED.get());

//        tag(OrevolutionTags.Items.shields).add(RegItems.AETHERSTEEL_SHIELD.get()).add(RegItems.TIN_SHIELD.get()).add(RegItems.PLATINUM_SHIELD.get())
//                .add(RegItems.VERDITE_SHIELD.get()).add(RegItems.LIVINGSTONE_SHIELD.get());
//
//        tag(OrevolutionTags.Items.shieldsSE).add(RegItems.AETHERSTEEL_SHIELD.get()).add(RegItems.TIN_SHIELD.get()).add(RegItems.PLATINUM_SHIELD.get())
//                .add(RegItems.VERDITE_SHIELD.get()).add(RegItems.LIVINGSTONE_SHIELD.get());
//
        tag(OrevolutionTags.Items.toolsKnives).addOptional(lc("tin_knife")).addOptional(lc("platinum_knife"))
                .addOptional(lc("aethersteel_knife")).addOptional(lc("livingstone_knife")).addOptional(lc("verdite_knife"));

        tag(OrevolutionTags.Items.toolsKnivesFD).addOptional(lc("tin_knife")).addOptional(lc("platinum_knife"))
                .addOptional(lc("aethersteel_knife")).addOptional(lc("livingstone_knife")).addOptional(lc("verdite_knife"));

        tag(OrevolutionTags.Items.tinProgFollow).add(Items.STONE_SWORD).add(Items.STONE_PICKAXE).add(Items.STONE_SHOVEL).add(Items.STONE_HOE).add(Items.STONE_AXE);
        tag(OrevolutionTags.Items.platProgFollow).add(Items.IRON_SWORD).add(Items.IRON_PICKAXE).add(Items.IRON_SHOVEL).add(Items.IRON_HOE).add(Items.IRON_AXE);

        tag(OrevolutionTags.Items.tinIngots).add(RegItems.TIN_INGOT.get());
        tag(OrevolutionTags.Items.platIngots).add(RegItems.PLATINUM_INGOT.get());
        tag(OrevolutionTags.Items.tungsIngots).add(RegItems.TUNGSTEN_INGOT.get());
        tag(OrevolutionTags.Items.enderiteIngots).add(RegItems.AETHERSTEEL_INGOT.get());
        tag(OrevolutionTags.Items.verditeIngots).add(RegItems.VERDITE_INGOT.get());
        tag(OrevolutionTags.Items.bronzeIngots).add(RegItems.BRONZE_ALLOY.get());
        tag(OrevolutionTags.Items.steelIngots).add(RegItems.STEEL_ALLOY.get());

        tag(OrevolutionTags.Items.tinNuggets).add(RegItems.TIN_NUGGET.get());
        tag(OrevolutionTags.Items.platNuggets).add(RegItems.PLATINUM_NUGGET.get());
        tag(OrevolutionTags.Items.tungsNuggets).add(RegItems.TUNGSTEN_NUGGET.get());
        tag(OrevolutionTags.Items.verditeNuggets).add(RegItems.VERDITE_NUGGET.get());
        tag(OrevolutionTags.Items.livingstoneFragments).add(RegItems.LIVINGSTONE_SHARD.get());

        tag(OrevolutionTags.Items.tinRaws).add(RegItems.RAW_TIN.get());
        tag(OrevolutionTags.Items.platRaws).add(RegItems.RAW_PLATINUM.get());
        tag(OrevolutionTags.Items.tungsRaws).add(RegItems.RAW_TUNGSTEN.get());

        tag(Tags.Items.INGOTS).addTags(
                OrevolutionTags.Items.tinIngots,
                OrevolutionTags.Items.platIngots,
                OrevolutionTags.Items.tungsIngots,
                OrevolutionTags.Items.enderiteIngots,
                OrevolutionTags.Items.verditeIngots,
                OrevolutionTags.Items.bronzeIngots,
                OrevolutionTags.Items.steelIngots
        );
        tag(Tags.Items.NUGGETS).addTags(
                OrevolutionTags.Items.tinNuggets,
                OrevolutionTags.Items.platNuggets,
                OrevolutionTags.Items.tungsNuggets,
                OrevolutionTags.Items.verditeNuggets
        );
        tag(Tags.Items.RAW_MATERIALS).addTags(
                OrevolutionTags.Items.tinRaws,
                OrevolutionTags.Items.platRaws,
                OrevolutionTags.Items.tungsRaws
        );

        tag(ItemTags.BEACON_PAYMENT_ITEMS).add(
                RegItems.TIN_INGOT.get(),
                RegItems.PLATINUM_INGOT.get(),
                RegItems.TUNGSTEN_INGOT.get(),
                RegItems.VERDITE_INGOT.get()
        );
        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(RegItems.PLATINUM_HELMET.get()).add(RegItems.PLATINUM_CHESTPLATE.get())
                .add(RegItems.PLATINUM_LEGGINGS.get()).add(RegItems.PLATINUM_BOOTS.get())
                .add(RegItems.VERDITE_HELMET.get()).add(RegItems.VERDITE_CHESTPLATE.get())
                .add(RegItems.VERDITE_LEGGINGS.get()).add(RegItems.VERDITE_BOOTS.get());

        copy(OrevolutionTags.Blocks.tinStorages, OrevolutionTags.Items.tinStorages);
        copy(OrevolutionTags.Blocks.platStorages, OrevolutionTags.Items.platStorages);
        copy(OrevolutionTags.Blocks.tungsStorages, OrevolutionTags.Items.tungsStorages);
        copy(OrevolutionTags.Blocks.verditeStorages, OrevolutionTags.Items.verditeStorages);
        copy(OrevolutionTags.Blocks.enderiteStorages, OrevolutionTags.Items.enderiteStorages);
        copy(OrevolutionTags.Blocks.livingstoneStorages, OrevolutionTags.Items.livingstoneStorages);

        copy(OrevolutionTags.Blocks.platOres, OrevolutionTags.Items.platOres);
        copy(OrevolutionTags.Blocks.tinOres, OrevolutionTags.Items.tinOres);
        copy(OrevolutionTags.Blocks.tungsOres, OrevolutionTags.Items.tungsOres);
        copy(OrevolutionTags.Blocks.XPOres, OrevolutionTags.Items.XPOres);

        copy(OrevolutionTags.Blocks.rawTinStorages, OrevolutionTags.Items.rawTinStorages);
        copy(OrevolutionTags.Blocks.rawPlatStorages, OrevolutionTags.Items.rawPlatStorages);
        copy(OrevolutionTags.Blocks.rawTungsStorages, OrevolutionTags.Items.rawTungsStorages);

        tag(Tags.Items.STORAGE_BLOCKS).addTags(
                OrevolutionTags.Items.tinStorages,
                OrevolutionTags.Items.platStorages,
                OrevolutionTags.Items.tungsStorages,
                OrevolutionTags.Items.verditeStorages,
                OrevolutionTags.Items.enderiteStorages,
                OrevolutionTags.Items.livingstoneStorages
        );
    }
}
