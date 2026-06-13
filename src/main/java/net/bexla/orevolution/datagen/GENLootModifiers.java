package net.bexla.orevolution.datagen;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.concurrent.CompletableFuture;

public class GENLootModifiers extends GlobalLootModifierProvider {
    public GENLootModifiers(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Orevolution.MODID);
    }

    @Override
    protected void start() {
        this.add("add_loot_abandoned_mineshaft", this.addNewLootPool(BuiltInLootTables.ABANDONED_MINESHAFT, OrevolutionUtils.LTKeys.ABANDONED_MINESHAFT));
        this.add("add_loot_bastion_hoglin_stable", this.addNewLootPool(BuiltInLootTables.BASTION_HOGLIN_STABLE, OrevolutionUtils.LTKeys.BASTION_HOGLIN_STABLE));
        this.add("add_loot_bastion_treasure", this.addNewLootPool(BuiltInLootTables.BASTION_TREASURE, OrevolutionUtils.LTKeys.BASTION_TREASURE));
        this.add("add_loot_end_city_treasure", this.addNewLootPool(BuiltInLootTables.END_CITY_TREASURE, OrevolutionUtils.LTKeys.END_CITY));
        this.add("add_loot_pillager_outpost", this.addNewLootPool(BuiltInLootTables.PILLAGER_OUTPOST, OrevolutionUtils.LTKeys.PILLAGER_OUTPOST));
        this.add("add_loot_ruined_portal", this.addNewLootPool(BuiltInLootTables.RUINED_PORTAL, OrevolutionUtils.LTKeys.RUINED_PORTAL));
        this.add("add_loot_shipwreck_supply", this.addNewLootPool(BuiltInLootTables.SHIPWRECK_SUPPLY, OrevolutionUtils.LTKeys.SHIPWRECK_SUPPLY));
        this.add("add_loot_simple_dungeon", this.addNewLootPool(BuiltInLootTables.SIMPLE_DUNGEON, OrevolutionUtils.LTKeys.SIMPLE_DUNGEON));
        this.add("add_loot_village_desert_house", this.addNewLootPool(BuiltInLootTables.VILLAGE_DESERT_HOUSE, OrevolutionUtils.LTKeys.VILLAGE_DESERT_HOUSE));
        this.add("add_loot_village_plains_house", this.addNewLootPool(BuiltInLootTables.VILLAGE_PLAINS_HOUSE, OrevolutionUtils.LTKeys.VILLAGE_PLAINS_HOUSE));
        this.add("add_loot_village_savanna_house", this.addNewLootPool(BuiltInLootTables.VILLAGE_SAVANNA_HOUSE, OrevolutionUtils.LTKeys.VILLAGE_SAVANNA_HOUSE));
        this.add("add_loot_village_snowy_house", this.addNewLootPool(BuiltInLootTables.VILLAGE_SNOWY_HOUSE, OrevolutionUtils.LTKeys.VILLAGE_SNOWY_HOUSE));
        this.add("add_loot_village_taiga_house", this.addNewLootPool(BuiltInLootTables.VILLAGE_TAIGA_HOUSE, OrevolutionUtils.LTKeys.VILLAGE_TAIGA_HOUSE));
    }

    private AddTableLootModifier addNewLootPool(ResourceKey<LootTable> lootToAddTo, ResourceKey<LootTable> newPool) {
        return new AddTableLootModifier(new LootItemCondition[]{LootTableIdCondition.builder(lootToAddTo.location()).build()}, newPool);
    }
}
