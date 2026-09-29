package net.bexla.orevolution.datagen;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionKeys;
import net.bexla.orevolution.init.RegConditionSerializer;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
        this.add("add_loot_piglin", this.addNewLootPool(ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.withDefaultNamespace("entities/piglin")), OrevolutionKeys.LootTables.PIGLIN), RegConditionSerializer.Conditions.ENTITY_LOOT);
        this.add("add_loot_brute_piglin", this.addNewLootPool(ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.withDefaultNamespace("entities/piglin_brute")), OrevolutionKeys.LootTables.BRUTE_PIGLIN), RegConditionSerializer.Conditions.ENTITY_LOOT);

        this.add("add_loot_abandoned_mineshaft", this.addNewLootPool(BuiltInLootTables.ABANDONED_MINESHAFT, OrevolutionKeys.LootTables.ABANDONED_MINESHAFT), RegConditionSerializer.Conditions.CHEST_LOOT);
        this.add("add_loot_bastion_hoglin_stable", this.addNewLootPool(BuiltInLootTables.BASTION_HOGLIN_STABLE, OrevolutionKeys.LootTables.BASTION_HOGLIN_STABLE), RegConditionSerializer.Conditions.CHEST_LOOT);
        this.add("add_loot_bastion_treasure", this.addNewLootPool(BuiltInLootTables.BASTION_TREASURE, OrevolutionKeys.LootTables.BASTION_TREASURE), RegConditionSerializer.Conditions.CHEST_LOOT);
        this.add("add_loot_end_city_treasure", this.addNewLootPool(BuiltInLootTables.END_CITY_TREASURE, OrevolutionKeys.LootTables.END_CITY), RegConditionSerializer.Conditions.CHEST_LOOT);
        this.add("add_loot_pillager_outpost", this.addNewLootPool(BuiltInLootTables.PILLAGER_OUTPOST, OrevolutionKeys.LootTables.PILLAGER_OUTPOST), RegConditionSerializer.Conditions.CHEST_LOOT);
        this.add("add_loot_ruined_portal", this.addNewLootPool(BuiltInLootTables.RUINED_PORTAL, OrevolutionKeys.LootTables.RUINED_PORTAL), RegConditionSerializer.Conditions.CHEST_LOOT);
        this.add("add_loot_shipwreck_supply", this.addNewLootPool(BuiltInLootTables.SHIPWRECK_SUPPLY, OrevolutionKeys.LootTables.SHIPWRECK_SUPPLY), RegConditionSerializer.Conditions.CHEST_LOOT);
        this.add("add_loot_simple_dungeon", this.addNewLootPool(BuiltInLootTables.SIMPLE_DUNGEON, OrevolutionKeys.LootTables.SIMPLE_DUNGEON), RegConditionSerializer.Conditions.CHEST_LOOT);
        this.add("add_loot_village_desert_house", this.addNewLootPool(BuiltInLootTables.VILLAGE_DESERT_HOUSE, OrevolutionKeys.LootTables.VILLAGE_DESERT_HOUSE), RegConditionSerializer.Conditions.CHEST_LOOT);
        this.add("add_loot_village_plains_house", this.addNewLootPool(BuiltInLootTables.VILLAGE_PLAINS_HOUSE, OrevolutionKeys.LootTables.VILLAGE_PLAINS_HOUSE), RegConditionSerializer.Conditions.CHEST_LOOT);
        this.add("add_loot_village_savanna_house", this.addNewLootPool(BuiltInLootTables.VILLAGE_SAVANNA_HOUSE, OrevolutionKeys.LootTables.VILLAGE_SAVANNA_HOUSE), RegConditionSerializer.Conditions.CHEST_LOOT);
        this.add("add_loot_village_snowy_house", this.addNewLootPool(BuiltInLootTables.VILLAGE_SNOWY_HOUSE, OrevolutionKeys.LootTables.VILLAGE_SNOWY_HOUSE), RegConditionSerializer.Conditions.CHEST_LOOT);
        this.add("add_loot_village_taiga_house", this.addNewLootPool(BuiltInLootTables.VILLAGE_TAIGA_HOUSE, OrevolutionKeys.LootTables.VILLAGE_TAIGA_HOUSE), RegConditionSerializer.Conditions.CHEST_LOOT);
        this.add("add_loot_nether_bridge", this.addNewLootPool(BuiltInLootTables.NETHER_BRIDGE, OrevolutionKeys.LootTables.NETHER_BRIDGE), RegConditionSerializer.Conditions.CHEST_LOOT);
    }

    private AddTableLootModifier addNewLootPool(ResourceKey<LootTable> lootToAddTo, ResourceKey<LootTable> newPool) {
        return new AddTableLootModifier(new LootItemCondition[]{LootTableIdCondition.builder(lootToAddTo.location()).build()}, newPool);
    }
}
