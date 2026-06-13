package net.bexla.orevolution.datagen;

import com.google.common.collect.ImmutableList;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionUtils;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GENLootDrops extends LootTableProvider {
    public GENLootDrops(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, BuiltInLootTables.all(), ImmutableList.of(
                new LootTableProvider.SubProviderEntry(BlockLoot::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(ChestLoot::new, LootContextParamSets.CHEST)
        ), provider);
    }

    @Override
    protected void validate(WritableRegistry<LootTable> writableregistry, ValidationContext validationcontext, ProblemReporter.Collector problemreporter$collector)
    {
    }

    public static class BlockLoot extends BlockLootSubProvider {
        protected BlockLoot(HolderLookup.Provider registries) {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
        }

        protected void generate() {
            ore(RegBlocks.TIN_ORE.get(), RegItems.RAW_TIN.get());
            ore(RegBlocks.DEEPSLATE_TIN_ORE.get(), RegItems.RAW_TIN.get());

            ore(RegBlocks.PLATINUM_ORE.get(), RegItems.RAW_PLATINUM.get());
            ore(RegBlocks.DEEPSLATE_PLATINUM_ORE.get(), RegItems.RAW_PLATINUM.get());

            ore(RegBlocks.NETHER_TUNGSTEN_ORE.get(), RegItems.RAW_TUNGSTEN.get());

            add(RegBlocks.NETHER_XP_ORE.get(), noDrop());
            add(RegBlocks.END_XP_ORE.get(), noDrop());

            add(RegBlocks.LIVINGSTONE_CROP.get(), createCropDrops(RegBlocks.LIVINGSTONE_CROP.get(), RegItems.LIVINGSTONE_SHARD.get(), RegItems.PETRIFIED_SEED.get(), LootItemBlockStatePropertyCondition.hasBlockStateProperties(RegBlocks.LIVINGSTONE_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.AGE_4, 4))));
            add(RegBlocks.VERDITE_CROP.get(), createCropDrops(RegBlocks.VERDITE_CROP.get(), RegItems.VERDITE_NUGGET.get(), RegItems.DEAD_SEED.get(), LootItemBlockStatePropertyCondition.hasBlockStateProperties(RegBlocks.VERDITE_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AGE_6, 6))));

            add(RegBlocks.STEEL_DOOR.get(), LootTable.lootTable()
                    .withPool(applyExplosionCondition(RegBlocks.STEEL_DOOR.get(), LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(RegBlocks.STEEL_DOOR.get())
                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF, DoubleBlockHalf.LOWER)))
                            .add(LootItem.lootTableItem(RegBlocks.STEEL_DOOR.get())))));

            slab(RegBlocks.POLISHED_AETHERROCK_SLAB);
            slab(RegBlocks.POLISHED_LIMESTONE_SLAB);

            dropSelf(RegBlocks.TUNGSTEN_SPONGE.get());
            dropSelf(RegBlocks.HOT_TUNGSTEN_SPONGE.get());
            dropSelf(RegBlocks.PLATINUM_TILES.get());
            dropSelf(RegBlocks.GOLD_TILES.get());
            dropSelf(RegBlocks.PLATINUM_BARS.get());
            dropSelf(RegBlocks.GOLD_BARS.get());
            dropSelf(RegBlocks.TUNGSTEN_BARS.get());
            dropSelf(RegBlocks.STEEL_BARS.get());
            dropSelf(RegBlocks.TIN_BARS.get());
            dropSelf(RegBlocks.BRONZE_BARS.get());
            dropSelf(RegBlocks.TUNGSTEN_BRICKS.get());
            dropSelf(RegBlocks.GOLD_PILLAR.get());
            dropSelf(RegBlocks.PLATINUM_PILLAR.get());
            dropSelf(RegBlocks.CHISELED_TUNGSTEN_BLOCK.get());
            dropSelf(RegBlocks.CHISELED_TUNGSTEN_BRICKS.get());
            dropSelf(RegBlocks.TIN_TILES.get());
            dropSelf(RegBlocks.TIN_BRICKS.get());
            dropSelf(RegBlocks.LIMESTONE.get());
            dropSelf(RegBlocks.LIMESTONE_PILLAR.get());
            dropSelf(RegBlocks.POLISHED_LIMESTONE.get());
            dropSelf(RegBlocks.STEEL_ANVIL.get());
            dropSelf(RegBlocks.POLISHED_LIMESTONE_STAIR.get());
            dropSelf(RegBlocks.POLISHED_LIMESTONE_WALL.get());
            dropSelf(RegBlocks.STEEL_TRAPDOOR.get());
            dropSelf(RegBlocks.POLISHED_AETHERROCK_STAIR.get());
            dropSelf(RegBlocks.POLISHED_AETHERROCK_WALL.get());
            dropSelf(RegBlocks.AETHERSTEEL_BLOCK.get());
            dropSelf(RegBlocks.TIN_BLOCK.get());
            dropSelf(RegBlocks.TUNGSTEN_BLOCK.get());
            dropSelf(RegBlocks.LIVINGSTONE_BLOCK.get());
            dropSelf(RegBlocks.VERDITE_BLOCK.get());
            dropSelf(RegBlocks.PLATINUM_BLOCK.get());
            dropSelf(RegBlocks.AETHERROCK.get());
            dropSelf(RegBlocks.POLISHED_AETHERROCK.get());
            dropSelf(RegBlocks.POLISHED_TUNGSTEN.get());
            dropSelf(RegBlocks.CRACKED_AETHERROCK_BRICKS.get());
            dropSelf(RegBlocks.CUT_TUNGSTEN_BLOCK.get());
            dropSelf(RegBlocks.BRONZE_BLOCK.get());
            dropSelf(RegBlocks.STEEL_BLOCK.get());
            dropSelf(RegBlocks.PRIMITIVE_AETHERROCK.get());
            dropSelf(RegBlocks.RAW_TIN_BLOCK.get());
            dropSelf(RegBlocks.RAW_PLATINUM_BLOCK.get());
            dropSelf(RegBlocks.RAW_TUNGSTEN_BLOCK.get());
            dropSelf(RegBlocks.AETHERROCK_TILES.get());
            dropSelf(RegBlocks.AETHERROCK_BRICKS.get());
            dropSelf(RegBlocks.CUT_STEEL_BLOCK.get());
            dropSelf(RegBlocks.STEEL_PILLAR.get());
            dropSelf(RegBlocks.BRONZE_TILES.get());
            dropSelf(RegBlocks.LIVINGSTONE_BRICKS.get());
            dropSelf(RegBlocks.VERDITE_BRICKS.get());
        }

        public void slab(Supplier<? extends Block> slab) {
            this.add(slab.get(), this::createSlabItemTable);
        }

        protected LootTable.Builder createCropDrops(Block block, Item drop, Item seed, LootItemCondition.Builder b) {
            return this.applyExplosionDecay(block, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(drop).when(b).otherwise(LootItem.lootTableItem(seed)))).withPool(LootPool.lootPool().when(b).add(LootItem.lootTableItem(seed).when(hasFortune()))));
        }

        protected LootItemCondition.Builder hasSilkTouch() {
            HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
            return MatchTool.toolMatches(ItemPredicate.Builder.item().withSubPredicate(ItemSubPredicates.ENCHANTMENTS, ItemEnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(enchantments.getOrThrow(Enchantments.SILK_TOUCH), MinMaxBounds.Ints.atLeast(1))))));
        }

        protected LootItemCondition.Builder hasFortune() {
            HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
            return MatchTool.toolMatches(ItemPredicate.Builder.item().withSubPredicate(ItemSubPredicates.ENCHANTMENTS, ItemEnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(enchantments.getOrThrow(Enchantments.FORTUNE), MinMaxBounds.Ints.atLeast(1))))));
        }

        protected void ore(Block ore, Item drop) {
            this.add(ore, (block) -> createOreDrop(block, drop));
        }

        private static final IntegerProperty AGE_6 = IntegerProperty.create("age", 0, 6);

        @Override
        public Iterable<Block> getKnownBlocks() {
            return BuiltInRegistries.BLOCK.stream().filter(block -> BuiltInRegistries.BLOCK.getKey(block).getNamespace().equals(Orevolution.MODID)).collect(Collectors.toSet());
        }
    }

    public static class ChestLoot implements LootTableSubProvider {
        protected final HolderLookup.Provider registries;

        public ChestLoot(HolderLookup.Provider registries) {
            this.registries = registries;
        }

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
            consumer.accept(OrevolutionUtils.LTKeys.END_CITY, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(RegItems.AETHERSTEEL_TEMPLATE.get()))
                            .add(EmptyLootItem.emptyItem().setWeight(9))));

            consumer.accept(OrevolutionUtils.LTKeys.ABANDONED_MINESHAFT, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(RegItems.BASIC_TEMPLATE))
                            .add(LootItem.lootTableItem(RegItems.TIN_PICKAXE)
                                    .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15F, 0.8F))))
                            .add(EmptyLootItem.emptyItem().setWeight(6)))
                    .withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(1.0F, 4.0F))
                            .add(LootItem.lootTableItem(RegItems.LIVINGSTONE_SHARD)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                            .add(LootItem.lootTableItem(RegItems.TIN_INGOT)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                            .add(EmptyLootItem.emptyItem().setWeight(2))
                    ));

            consumer.accept(OrevolutionUtils.LTKeys.BASTION_HOGLIN_STABLE, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(RegItems.PLATINUM_AXE.get())
                                    .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15F, 0.8F)))
                                    .apply(EnchantRandomlyFunction.randomApplicableEnchantment(registries)))
                            .add(LootItem.lootTableItem(RegItems.TUNGSTEN_INGOT).setWeight(2)
                                    .apply(EnchantRandomlyFunction.randomApplicableEnchantment(registries)))
                            .add(EmptyLootItem.emptyItem().setWeight(4))

                    ).withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(1.0F, 2.0F))
                            .add(LootItem.lootTableItem(RegItems.TUNGSTEN_NUGGET.get()).setWeight(4)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))))
                            .add(EmptyLootItem.emptyItem().setWeight(2))
                    ));

            consumer.accept(OrevolutionUtils.LTKeys.PILLAGER_OUTPOST, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(1.0F, 2.0F))
                            .add(LootItem.lootTableItem(RegItems.PLATINUM_NUGGET.get()).setWeight(5)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 8.0F))))
                            .add(LootItem.lootTableItem(RegItems.PLATINUM_INGOT.get()).setWeight(2)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                            .add(EmptyLootItem.emptyItem().setWeight(3))
                    ).withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(1.0F, 2.0F))
                            .add(LootItem.lootTableItem(RegItems.PLATINUM_SWORD.get())
                                    .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15F, 0.8F))))
                            .add(LootItem.lootTableItem(RegItems.PLATINUM_AXE.get()).setWeight(3)
                                    .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15F, 0.8F))))
                            .add(EmptyLootItem.emptyItem())));

            consumer.accept(OrevolutionUtils.LTKeys.RUINED_PORTAL, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(RegItems.TUNGSTEN_NUGGET.get()))
                            .add(EmptyLootItem.emptyItem().setWeight(2))
                    ));

            consumer.accept(OrevolutionUtils.LTKeys.SHIPWRECK_SUPPLY, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(1.0F, 2.0F))
                            .add(LootItem.lootTableItem(RegItems.PLATINUM_NUGGET.get()).setWeight(6)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                            .add(LootItem.lootTableItem(RegItems.TIN_INGOT.get()).setWeight(6)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                    ));

            consumer.accept(OrevolutionUtils.LTKeys.SIMPLE_DUNGEON, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(1.0F, 4.0F))
                            .add(LootItem.lootTableItem(RegItems.PLATINUM_AXE.get())
                                    .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15F, 0.8F)))
                                    .apply(EnchantRandomlyFunction.randomApplicableEnchantment(registries)))
                            .add(LootItem.lootTableItem(RegItems.PLATINUM_INGOT.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                            .add(EmptyLootItem.emptyItem().setWeight(2))
                    ));

            consumer.accept(OrevolutionUtils.LTKeys.VILLAGE_PLAINS_HOUSE, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(1.0F, 3.0F))
                            .add(LootItem.lootTableItem(RegItems.TIN_NUGGET.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                            ));

            consumer.accept(OrevolutionUtils.LTKeys.VILLAGE_SAVANNA_HOUSE, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(1.0F, 3.0F))
                            .add(LootItem.lootTableItem(RegItems.PLATINUM_NUGGET.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                            ));
        }
    }
}
