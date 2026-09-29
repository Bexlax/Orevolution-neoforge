package net.bexla.orevolution.datagen;

import com.google.common.collect.ImmutableList;
import com.simibubi.create.AllItems;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionKeys;
import net.bexla.orevolution.content.interfaces.IVinnelio;
import net.bexla.orevolution.content.types.ModLoadedLootConditionBuilder;
import net.bexla.orevolution.content.types.block.TallCorelioBlock;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
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
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
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
                new LootTableProvider.SubProviderEntry(ChestLoot::new, LootContextParamSets.CHEST),
                new LootTableProvider.SubProviderEntry(EntityLoot::new, LootContextParamSets.ENTITY),
        new LootTableProvider.SubProviderEntry(BarteringLoot::new, LootContextParamSets.PIGLIN_BARTER)
        ), provider);
    }

    @Override
    protected void validate(WritableRegistry<LootTable> writableregistry, ValidationContext validationcontext, ProblemReporter.Collector problemreporter$collector) {}

    public static class BlockLoot extends BlockLootSubProvider {
        protected BlockLoot(HolderLookup.Provider registries) {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
        }

        protected void generate() {
            this.add(RegBlocks.QUARTZOLITE.get(), (block) -> createOreDrop(block, RegItems.QUARTZ_CHIP.get(), 3F, 9F));
            this.add(RegBlocks.CELESTITE_CLUSTER.get(), (block) -> createOreDrop(block, RegItems.CELESTITE_SHARD.get(), 1F, 2F));

            ore(RegBlocks.TIN_ORE.get(), RegItems.RAW_TIN.get());
            ore(RegBlocks.DEEPSLATE_TIN_ORE.get(), RegItems.RAW_TIN.get());

            ore(RegBlocks.CASSITERITE_ORE.get(), RegItems.RAW_CASSITERITE.get());
            ore(RegBlocks.DEEPSLATE_CASSITERITE_ORE.get(), RegItems.RAW_CASSITERITE.get());
            ore(RegBlocks.NETHER_CASSITERITE_ORE.get(), RegItems.RAW_CASSITERITE.get());

//            this.add(RegBlocks.NICKEL_ORE.get(), (block) -> createOreDrop(block, RegItems.RAW_NICKEL.get(), 1F, 3F));
//            this.add(RegBlocks.DEEPSLATE_NICKEL_ORE.get(), (block) -> createOreDrop(block, RegItems.RAW_NICKEL.get(), 1F, 3F));

            ore(RegBlocks.PLATINUM_ORE.get(), RegItems.RAW_PLATINUM.get());
            ore(RegBlocks.DEEPSLATE_PLATINUM_ORE.get(), RegItems.RAW_PLATINUM.get());

            ore(RegBlocks.NETHER_TUNGSTEN_ORE.get(), RegItems.RAW_TUNGSTEN.get());

            ore(RegBlocks.RHYOLITE_EMERALD_ORE.get(), Items.EMERALD);
            this.add(RegBlocks.RHYOLITE_EMERALD_ORE_CLUMP.get(), (block) -> createOreDrop(block, Items.EMERALD, 2F, 4F));

            add(RegBlocks.NETHER_XP_ORE.get(), b -> createOreDrop("create", b, AllItems.EXP_NUGGET.asItem(), 1F, 3F));
            add(RegBlocks.END_XP_ORE.get(), b -> createOreDrop("create", b, AllItems.EXP_NUGGET.asItem(), 2F, 6F));

            add(RegBlocks.VINNELIO.get(), createVinnelioDrops());
            add(RegBlocks.VINNELIO_PLANT.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(RegItems.VINNELIO))));

            ore(RegBlocks.EMERALD_CLUSTER.get(), Items.EMERALD);

            this.add(RegBlocks.PRISMARINE_CLUSTER.get(), (block) -> createOreDrop(block, Items.PRISMARINE_SHARD, 2F, 7F));

            add(RegBlocks.LIVINGSTONE_CROP.get(), createCropDrops(RegBlocks.LIVINGSTONE_CROP.get(), RegItems.LIVINGSTONE_SHARD.get(), RegItems.PETRIFIED_SEED.get(), LootItemBlockStatePropertyCondition.hasBlockStateProperties(RegBlocks.LIVINGSTONE_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlockStateProperties.AGE_4, 4))));
            add(RegBlocks.VERDITE_CROP.get(), createCropDrops(RegBlocks.VERDITE_CROP.get(), RegItems.VERDITE_NUGGET.get(), RegItems.DEAD_SEED.get(), LootItemBlockStatePropertyCondition.hasBlockStateProperties(RegBlocks.VERDITE_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(AGE_6, 6))));

            add(RegBlocks.STEEL_DOOR.get(), this::createDoorDrops);

            add(RegBlocks.LARGE_CORELIO.get(), LootTable.lootTable()
                    .withPool(applyExplosionCondition(RegBlocks.LARGE_CORELIO.get(), LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(RegBlocks.LARGE_CORELIO.get())
                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(TallCorelioBlock.HALF, DoubleBlockHalf.LOWER)))
                            .add(LootItem.lootTableItem(RegBlocks.LARGE_CORELIO.get())))));

            add(RegBlocks.UNKNOWN_ROOTS.get(), createRootsDrops());

            add(RegBlocks.RHYOLITE_ENCRUSTED_MOONSTONE.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(
                    LootItem.lootTableItem(RegBlocks.RHYOLITE_ENCRUSTED_MOONSTONE.get()).when(hasSilkTouch()).otherwise(LootItem.lootTableItem(RegBlocks.MOONSTONE.get()))
            )));
            add(RegBlocks.BASALT_ENCRUSTED_MOONSTONE.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(
                    LootItem.lootTableItem(RegBlocks.BASALT_ENCRUSTED_MOONSTONE.get()).when(hasSilkTouch()).otherwise(LootItem.lootTableItem(RegBlocks.MOONSTONE.get()))
            )));


            this.add(RegBlocks.LARGE_CELESTITE_BUD.get(), createSilkTouchOnlyTable(RegBlocks.LARGE_CELESTITE_BUD.get()));
            this.add(RegBlocks.MEDIUM_CELESTITE_BUD.get(), createSilkTouchOnlyTable(RegBlocks.MEDIUM_CELESTITE_BUD.get()));
            this.add(RegBlocks.SMALL_CELESTITE_BUD.get(), createSilkTouchOnlyTable(RegBlocks.SMALL_CELESTITE_BUD.get()));
            this.add(RegBlocks.BUDDING_CELESTITE.get(), noDrop());

            dropSelf(RegBlocks.AETHERROCK_STAIR.get());
            dropSelf(RegBlocks.AETHERROCK_WALL.get());
            slab(RegBlocks.AETHERROCK_SLAB);
            dropSelf(RegBlocks.POLISHED_AETHERROCK_STAIR.get());
            dropSelf(RegBlocks.POLISHED_AETHERROCK_WALL.get());
            slab(RegBlocks.POLISHED_AETHERROCK_SLAB);
            dropSelf(RegBlocks.AETHERROCK_BRICKS_STAIR.get());
            dropSelf(RegBlocks.AETHERROCK_BRICKS_WALL.get());
            slab(RegBlocks.AETHERROCK_BRICKS_SLAB);

//            dropSelf(RegBlocks.CUT_NICKEL_STAIR.get());
//            dropSelf(RegBlocks.CUT_NICKEL_WALL.get());
//            slab(RegBlocks.CUT_NICKEL_SLAB);
//            dropSelf(RegBlocks.NICKEL_BARS.get());
//            add(RegBlocks.NICKEL_DOOR.get(), this::createDoorDrops);
//            dropSelf(RegBlocks.NICKEL_TRAPDOOR.get());
//            dropSelf(RegBlocks.NICKEL_GRATE.get());
//            dropSelf(RegBlocks.NICKEL_BULB.get());
//            dropSelf(RegBlocks.CUT_NICKEL.get());

            dropSelf(RegBlocks.AETHERROCK_STAIR.get());
            dropSelf(RegBlocks.AETHERROCK_WALL.get());
            slab(RegBlocks.AETHERROCK_SLAB);

            dropSelf(RegBlocks.CHERT_STAIR.get());
            dropSelf(RegBlocks.CHERT_WALL.get());
            slab(RegBlocks.CHERT_SLAB);
            dropSelf(RegBlocks.POLISHED_CHERT_STAIR.get());
            dropSelf(RegBlocks.POLISHED_CHERT_WALL.get());
            slab(RegBlocks.POLISHED_CHERT_SLAB);
            dropSelf(RegBlocks.CHERT_BRICKS_STAIR.get());
            dropSelf(RegBlocks.CHERT_BRICKS_WALL.get());
            slab(RegBlocks.CHERT_BRICKS_SLAB);

            dropSelf(RegBlocks.RHYOLITE_STAIR.get());
            dropSelf(RegBlocks.RHYOLITE_WALL.get());
            slab(RegBlocks.RHYOLITE_SLAB);
            dropSelf(RegBlocks.POLISHED_RHYOLITE_STAIR.get());
            dropSelf(RegBlocks.POLISHED_RHYOLITE_WALL.get());
            slab(RegBlocks.POLISHED_RHYOLITE_SLAB);
            dropSelf(RegBlocks.RHYOLITE_BRICKS_STAIR.get());
            dropSelf(RegBlocks.RHYOLITE_BRICKS_WALL.get());
            slab(RegBlocks.RHYOLITE_BRICKS_SLAB);

            dropSelf(RegBlocks.LIVINGSTONE_STAIR.get());
            dropSelf(RegBlocks.LIVINGSTONE_WALL.get());
            slab(RegBlocks.LIVINGSTONE_SLAB);
            dropSelf(RegBlocks.POLISHED_LIVINGSTONE_STAIR.get());
            dropSelf(RegBlocks.POLISHED_LIVINGSTONE_WALL.get());
            slab(RegBlocks.POLISHED_LIVINGSTONE_SLAB);
            dropSelf(RegBlocks.LIVINGSTONE_BRICKS_STAIR.get());
            dropSelf(RegBlocks.LIVINGSTONE_BRICKS_WALL.get());
            slab(RegBlocks.LIVINGSTONE_BRICKS_SLAB);

            dropSelf(RegBlocks.POLISHED_CELESTITE_STAIR.get());
            dropSelf(RegBlocks.POLISHED_CELESTITE_WALL.get());
            slab(RegBlocks.POLISHED_CELESTITE_SLAB);
            dropSelf(RegBlocks.CELESTITE_BRICKS_STAIR.get());
            dropSelf(RegBlocks.CELESTITE_BRICKS_WALL.get());
            slab(RegBlocks.CELESTITE_BRICKS_SLAB);

            dropSelf(RegBlocks.POLISHED_AMETHYST_STAIR.get());
            dropSelf(RegBlocks.POLISHED_AMETHYST_WALL.get());
            slab(RegBlocks.POLISHED_AMETHYST_SLAB);
            dropSelf(RegBlocks.AMETHYST_BRICKS_STAIR.get());
            dropSelf(RegBlocks.AMETHYST_BRICKS_WALL.get());
            slab(RegBlocks.AMETHYST_BRICKS_SLAB);

            dropSelf(RegBlocks.CUT_STEEL_STAIR.get());
            slab(RegBlocks.CUT_STEEL_SLAB);

            dropSelf(RegBlocks.MOONSTONE.get());
            dropSelf(RegBlocks.PLATINUM_TILES.get());
            dropSelf(RegBlocks.CELESTITE_BLOCK.get());
            dropSelf(RegBlocks.PYRITE_BLOCK.get());
            dropSelf(RegBlocks.GOLD_TILES.get());
            dropSelf(RegBlocks.PLATINUM_BARS.get());
            dropSelf(RegBlocks.GOLD_BARS.get());
            dropSelf(RegBlocks.STEEL_BARS.get());
            dropSelf(RegBlocks.TIN_BARS.get());
            dropSelf(RegBlocks.BRONZE_BARS.get());
            dropSelf(RegBlocks.GOLD_PILLAR.get());
            dropSelf(RegBlocks.PLATINUM_PILLAR.get());
            dropSelf(RegBlocks.TIN_TILES.get());

//            dropSelf(RegBlocks.RAW_NICKEL_BLOCK.get());
//            dropSelf(RegBlocks.NICKEL_BLOCK.get());

            dropSelf(RegBlocks.RDX.get());

            dropSelf(RegBlocks.AMETHYST_BRICKS.get());
            dropSelf(RegBlocks.POLISHED_AMETHYST.get());

            dropSelf(RegBlocks.TIN_LANTERN.get());
            dropSelf(RegBlocks.PLATINUM_LANTERN.get());
            dropSelf(RegBlocks.BRONZE_LANTERN.get());
            dropSelf(RegBlocks.GOLDEN_LANTERN.get());
            dropSelf(RegBlocks.TIN_SOUL_LANTERN.get());
            dropSelf(RegBlocks.PLATINUM_SOUL_LANTERN.get());
            dropSelf(RegBlocks.BRONZE_SOUL_LANTERN.get());
            dropSelf(RegBlocks.GOLDEN_SOUL_LANTERN.get());
//            dropSelf(RegBlocks.NICKEL_LANTERN.get());
//            dropSelf(RegBlocks.NICKEL_SOUL_LANTERN.get());

            dropSelf(RegBlocks.TIN_BRICKS.get());
            dropSelf(RegBlocks.CHERT_PILLAR.get());
            dropSelf(RegBlocks.POLISHED_CHERT.get());
            dropSelf(RegBlocks.STEEL_ANVIL.get());
            dropSelf(RegBlocks.STEEL_TRAPDOOR.get());
            dropSelf(RegBlocks.RHYOLITE.get());
            dropSelf(RegBlocks.CHERT.get());
            dropSelf(RegBlocks.AETHERSTEEL_BLOCK.get());
            dropSelf(RegBlocks.TIN_BLOCK.get());
            dropSelf(RegBlocks.CASSITERITE_BLOCK.get());
            dropSelf(RegBlocks.RAW_CASSITERITE_BLOCK.get());
            dropSelf(RegBlocks.LIVINGSTONE_BLOCK.get());
            dropSelf(RegBlocks.VERDITE_BLOCK.get());
            dropSelf(RegBlocks.PLATINUM_BLOCK.get());
            dropSelf(RegBlocks.AETHERROCK.get());
            dropSelf(RegBlocks.POLISHED_AETHERROCK.get());
            
            dropSelf(RegBlocks.RAW_TUNGSTEN_BLOCK.get());
            
            dropSelf(RegBlocks.TUNGSTEN_BLOCK.get());
            dropSelf(RegBlocks.POLISHED_TUNGSTEN.get());
            dropSelf(RegBlocks.CUT_TUNGSTEN_BLOCK.get());
            dropSelf(RegBlocks.TUNGSTEN_BARS.get());
            dropSelf(RegBlocks.TUNGSTEN_BRICKS.get());
            dropSelf(RegBlocks.CHISELED_TUNGSTEN_BLOCK.get());
            dropSelf(RegBlocks.CHISELED_TUNGSTEN_BRICKS.get());

            dropSelf(RegBlocks.DECAYING_TUNGSTEN_BLOCK.get());
            dropSelf(RegBlocks.POLISHED_DECAYING_TUNGSTEN.get());
            dropSelf(RegBlocks.CUT_DECAYING_TUNGSTEN_BLOCK.get());
            dropSelf(RegBlocks.DECAYING_TUNGSTEN_BARS.get());
            dropSelf(RegBlocks.DECAYING_TUNGSTEN_BRICKS.get());
            dropSelf(RegBlocks.CHISELED_DECAYING_TUNGSTEN_BLOCK.get());
            dropSelf(RegBlocks.CHISELED_DECAYING_TUNGSTEN_BRICKS.get());

            dropSelf(RegBlocks.CORRODED_TUNGSTEN_BLOCK.get());
            dropSelf(RegBlocks.POLISHED_CORRODED_TUNGSTEN.get());
            dropSelf(RegBlocks.CUT_CORRODED_TUNGSTEN_BLOCK.get());
            dropSelf(RegBlocks.CORRODED_TUNGSTEN_BARS.get());
            dropSelf(RegBlocks.CORRODED_TUNGSTEN_BRICKS.get());
            dropSelf(RegBlocks.CHISELED_CORRODED_TUNGSTEN_BLOCK.get());
            dropSelf(RegBlocks.CHISELED_CORRODED_TUNGSTEN_BRICKS.get());
            
            dropSelf(RegBlocks.TUNGSTEN_SPONGE.get());
            dropSelf(RegBlocks.HOT_TUNGSTEN_SPONGE.get());
            
            dropSelf(RegBlocks.CRACKED_AETHERROCK_BRICKS.get());
            dropSelf(RegBlocks.BRONZE_BLOCK.get());
            dropSelf(RegBlocks.CHERT_BRICKS.get());
            dropSelf(RegBlocks.RHYOLITE_BRICKS.get());
            dropSelf(RegBlocks.CELESTITE_BRICKS.get());
            dropSelf(RegBlocks.POLISHED_CELESTITE.get());
            dropSelf(RegBlocks.STEEL_BLOCK.get());
            dropSelf(RegBlocks.PRIMITIVE_AETHERROCK.get());
            dropSelf(RegBlocks.RAW_TIN_BLOCK.get());
            dropSelf(RegBlocks.RAW_PLATINUM_BLOCK.get());
            dropSelf(RegBlocks.AETHERROCK_TILES.get());
            dropSelf(RegBlocks.AETHERROCK_BRICKS.get());
            dropSelf(RegBlocks.CUT_STEEL_BLOCK.get());
            dropSelf(RegBlocks.STEEL_PILLAR.get());
            dropSelf(RegBlocks.BRONZE_TILES.get());
            dropSelf(RegBlocks.LIVINGSTONE_BRICKS.get());
            dropSelf(RegBlocks.VERDITE_BRICKS.get());
            dropSelf(RegBlocks.CORELIO.get());
            dropSelf(RegBlocks.RHYOLITE_PILLAR.get());
            dropSelf(RegBlocks.POLISHED_RHYOLITE.get());
            dropSelf(RegBlocks.POLISHED_LIVINGSTONE.get());
            dropSelf(RegBlocks.UNKNOWN_ROOTS_BLOCK.get());
            dropSelf(RegBlocks.STEEL_GRATE.get());
        }

        public void slab(Supplier<? extends Block> slab) {
            this.add(slab.get(), this::createSlabItemTable);
        }

        protected LootTable.Builder createCropDrops(Block block, Item drop, Item seed, LootItemCondition.Builder b) {
            return this.applyExplosionDecay(block, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(drop).when(b).otherwise(LootItem.lootTableItem(seed)))).withPool(LootPool.lootPool().when(b).add(LootItem.lootTableItem(seed).when(hasFortune()))));
        }

        protected LootTable.Builder createDoorDrops(Block block) {
            return LootTable.lootTable()
                    .withPool(applyExplosionCondition(block, LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                    .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF, DoubleBlockHalf.LOWER)))
                            .add(LootItem.lootTableItem(block))));
        }

        protected LootTable.Builder createVinnelioDrops() {
            return LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool().add(LootItem.lootTableItem(RegItems.ANCIENT_FRUIT)
                                    .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(RegBlocks.VINNELIO.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(IVinnelio.FRUIT, true))).otherwise(LootItem.lootTableItem(RegItems.VINNELIO))
                            )
                    );
        }

        protected LootTable.Builder createRootsDrops() {
            HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

            return LootTable.lootTable()
                    .withPool(
                            LootPool.lootPool().add(LootItem.lootTableItem(RegBlocks.UNKNOWN_ROOTS)
                                    .when(HAS_SHEARS.or(hasSilkTouch())).otherwise(LootItem.lootTableItem(Items.STICK)
                                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5)))
                                            .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE))))
                            )
                    );

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

        protected LootTable.Builder createOreDrop(Block block, Item item, float min, float max) {
            HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

            return this.createSilkTouchDispatchTable(
                    block,
                    this.applyExplosionDecay(
                            block,
                            LootItem.lootTableItem(item)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                    .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                    )
            );
        }

        protected LootTable.Builder createOreDrop(String modid, Block block, Item item, float min, float max) {
            HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

            return this.createSilkTouchDispatchTable(
                    block,
                    this.applyExplosionDecay(
                            block,
                            LootItem.lootTableItem(item)
                                    .when(ModLoadedLootConditionBuilder.any(modid))
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                                    .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                    )
            );
        }

        private static final IntegerProperty AGE_6 = IntegerProperty.create("age", 0, 6);

        @Override
        public Iterable<Block> getKnownBlocks() {
            return BuiltInRegistries.BLOCK.stream().filter(block -> BuiltInRegistries.BLOCK.getKey(block).getNamespace().equals(Orevolution.MODID)).collect(Collectors.toSet());
        }
    }

    public record ChestLoot(HolderLookup.Provider registries) implements LootTableSubProvider {

        @Override
            public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
                consumer.accept(OrevolutionKeys.LootTables.END_CITY, LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(RegItems.AETHERSTEEL_TEMPLATE.get()))
                                .add(EmptyLootItem.emptyItem().setWeight(9))));

                consumer.accept(OrevolutionKeys.LootTables.ABANDONED_MINESHAFT, LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(RegItems.BASIC_TEMPLATE))
                                .add(LootItem.lootTableItem(RegItems.TIN_PICKAXE)
                                        .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15F, 0.8F))))
                                .add(EmptyLootItem.emptyItem().setWeight(9)))
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 4.0F))
                                .add(LootItem.lootTableItem(RegItems.LIVINGSTONE_SHARD)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                                .add(LootItem.lootTableItem(RegItems.FOOLS_APPLE.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(RegItems.TIN_INGOT)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                                .add(EmptyLootItem.emptyItem().setWeight(4))
                        ));

                consumer.accept(OrevolutionKeys.LootTables.BASTION_HOGLIN_STABLE, LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(RegItems.FOOLS_APPLE.get()))
                                .add(LootItem.lootTableItem(RegItems.PLATINUM_AXE.get())
                                        .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15F, 0.8F)))
                                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment(registries)))
                                .add(LootItem.lootTableItem(RegItems.TUNGSTEN_INGOT).setWeight(2)
                                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment(registries)))
                                .add(EmptyLootItem.emptyItem().setWeight(6))

                        ).withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 2.0F))
                                .add(LootItem.lootTableItem(RegItems.TUNGSTEN_NUGGET.get()).setWeight(4)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))))
                                .add(EmptyLootItem.emptyItem().setWeight(2))
                        ));

                consumer.accept(OrevolutionKeys.LootTables.PILLAGER_OUTPOST, LootTable.lootTable()
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
                                .add(EmptyLootItem.emptyItem())
                        ));

                consumer.accept(OrevolutionKeys.LootTables.RUINED_PORTAL, LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(RegItems.FOOLS_APPLE.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(RegItems.TUNGSTEN_NUGGET.get()))
                                .add(EmptyLootItem.emptyItem().setWeight(2))
                        ));

                consumer.accept(OrevolutionKeys.LootTables.SHIPWRECK_SUPPLY, LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 2.0F))
                                .add(LootItem.lootTableItem(RegItems.PLATINUM_NUGGET.get()).setWeight(6)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                                .add(LootItem.lootTableItem(RegItems.TIN_INGOT.get()).setWeight(6)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))))
                        ));

                consumer.accept(OrevolutionKeys.LootTables.SIMPLE_DUNGEON, LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 2.0F))
                                .add(LootItem.lootTableItem(RegItems.FOOLS_APPLE.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .add(LootItem.lootTableItem(RegItems.TIN_AXE.get())
                                        .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15F, 0.8F)))
                                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment(registries)))
                                .add(LootItem.lootTableItem(RegItems.TIN_SWORD.get())
                                        .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15F, 0.8F)))
                                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment(registries)))
                                .add(LootItem.lootTableItem(RegItems.PLATINUM_SWORD.get())
                                        .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.1F, 0.95F))))
                                .add(LootItem.lootTableItem(RegItems.TIN_INGOT.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F))).setWeight(9))
                                .add(LootItem.lootTableItem(RegItems.PLATINUM_INGOT.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .add(EmptyLootItem.emptyItem().setWeight(9))
                        ));

                consumer.accept(OrevolutionKeys.LootTables.VILLAGE_PLAINS_HOUSE, LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 3.0F))
                                .add(LootItem.lootTableItem(RegItems.TIN_NUGGET.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        ));

                consumer.accept(OrevolutionKeys.LootTables.VILLAGE_SAVANNA_HOUSE, LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 3.0F))
                                .add(LootItem.lootTableItem(RegItems.PLATINUM_NUGGET.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        ));

                consumer.accept(OrevolutionKeys.LootTables.VILLAGE_SNOWY_HOUSE, LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 3.0F))
                                .add(LootItem.lootTableItem(RegItems.TIN_NUGGET.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        ));

                consumer.accept(OrevolutionKeys.LootTables.VILLAGE_TAIGA_HOUSE, LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 3.0F))
                                .add(LootItem.lootTableItem(RegItems.PLATINUM_NUGGET.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        ));

                consumer.accept(OrevolutionKeys.LootTables.VILLAGE_DESERT_HOUSE, LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(1.0F, 3.0F))
                                .add(LootItem.lootTableItem(RegItems.PLATINUM_NUGGET.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        ));

                consumer.accept(OrevolutionKeys.LootTables.NETHER_BRIDGE, LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(UniformGenerator.between(0.0F, 3.0F))
                                .add(LootItem.lootTableItem(RegItems.CASSITERITE_NUGGET.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        )
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(RegItems.REINFORCED_TEMPLATE.get()))
                                .add(LootItem.lootTableItem(RegItems.COATING_TEMPLATE.get()))
                                .add(EmptyLootItem.emptyItem().setWeight(9))
                        ));

            consumer.accept(OrevolutionKeys.LootTables.BASTION_TREASURE, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(0.0F, 3.0F))
                            .add(LootItem.lootTableItem(RegItems.TUNGSTEN_NUGGET.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                    )
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(RegItems.CASSITERITE_INGOT.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                            .add(EmptyLootItem.emptyItem().setWeight(9))
                    ));
            }
        }

    private static class EntityLoot extends EntityLootSubProvider {
        protected EntityLoot(HolderLookup.Provider provider) {
            super(FeatureFlags.REGISTRY.allFlags(), provider);
        }

        @Override
        public void generate() {}

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
            output.accept(OrevolutionKeys.LootTables.PIGLIN, LootTable.lootTable()
                    .withPool(LootPool.lootPool().when(LootItemRandomChanceCondition.randomChance(0.34F))
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(RegItems.TUNGSTEN_NUGGET.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                    )));

            output.accept(OrevolutionKeys.LootTables.BRUTE_PIGLIN, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(RegItems.TUNGSTEN_NUGGET.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 8.0F))))
                            .add(LootItem.lootTableItem(RegItems.TUNGSTEN_INGOT.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                    )
                    .withPool(LootPool.lootPool().when(LootItemRandomChanceCondition.randomChance(0.34F))
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(RegItems.TUNGSTEN_INGOT.get())
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                    ));
        }
    }

    private record BarteringLoot(HolderLookup.Provider registries) implements LootTableSubProvider {
        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
            HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
            consumer.accept(OrevolutionKeys.LootTables.PIGLIN_TUNGSTEN_BARTERING, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(Items.COOKED_PORKCHOP)
                                    .setWeight(9)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 8.0F))))
                            .add(LootItem.lootTableItem(Items.ENDER_PEARL)
                                    .setWeight(8)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))))
                            .add(LootItem.lootTableItem(Items.POTION)
                                    .setWeight(7)
                                    .apply(SetPotionFunction.setPotion(Potions.FIRE_RESISTANCE)))
                            .add(LootItem.lootTableItem(Items.BONE_BLOCK)
                                    .setWeight(6)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F))))
                            .add(LootItem.lootTableItem(Items.CROSSBOW)
                                    .setWeight(5)
                                    .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.1F, 0.9F)))
                                    .apply(new EnchantRandomlyFunction.Builder().withOneOf(HolderSet.direct(
                                            registrylookup.getOrThrow(Enchantments.QUICK_CHARGE),
                                            registrylookup.getOrThrow(Enchantments.UNBREAKING)))))
                            .add(LootItem.lootTableItem(Items.SPECTRAL_ARROW)
                                    .setWeight(6)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0F, 16.0F))))
                            .add(LootItem.lootTableItem(RegItems.FIERY_ARROW)
                                    .setWeight(6)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 8.0F))))
                            .add(LootItem.lootTableItem(Items.QUARTZ)
                                    .setWeight(8)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0F, 20.0F))))
                            .add(LootItem.lootTableItem(Items.GLOWSTONE_DUST)
                                    .setWeight(6)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0F, 16.0F))))
                            .add(LootItem.lootTableItem(Items.MAGMA_CREAM)
                                    .setWeight(5)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 6.0F))))
                            .add(LootItem.lootTableItem(Items.GOLDEN_SWORD)
                                    .setWeight(5)
                                    .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15F, 0.45F)))
                                    .apply(new EnchantRandomlyFunction.Builder().withOneOf(HolderSet.direct(
                                            registrylookup.getOrThrow(Enchantments.LOOTING),
                                            registrylookup.getOrThrow(Enchantments.KNOCKBACK),
                                            registrylookup.getOrThrow(Enchantments.MENDING)))))
                            .add(LootItem.lootTableItem(Items.IRON_SWORD)
                                    .setWeight(4)
                                    .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.10F, 0.80F)))
                                    .apply(new EnchantRandomlyFunction.Builder().withOneOf(HolderSet.direct(
                                            registrylookup.getOrThrow(Enchantments.SHARPNESS),
                                            registrylookup.getOrThrow(Enchantments.SMITE),
                                            registrylookup.getOrThrow(Enchantments.SWEEPING_EDGE)))))
                            .add(LootItem.lootTableItem(Items.BOOK)
                                    .setWeight(3)
                                    .apply(new EnchantRandomlyFunction.Builder().withEnchantment(registrylookup.getOrThrow(Enchantments.SOUL_SPEED))))
                            .add(LootItem.lootTableItem(Items.IRON_BOOTS)
                                    .setWeight(4)
                                    .apply(new EnchantRandomlyFunction.Builder().withEnchantment(registrylookup.getOrThrow(Enchantments.SOUL_SPEED))))
                            .add(LootItem.lootTableItem(Items.IRON_LEGGINGS)
                                    .setWeight(4)
                                    .apply(new EnchantRandomlyFunction.Builder().withEnchantment(registrylookup.getOrThrow(Enchantments.VANISHING_CURSE)))
                                    .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15F, 0.45F))))
                            .add(LootItem.lootTableItem(Items.GOLDEN_CHESTPLATE)
                                    .setWeight(4)
                                    .apply(new EnchantRandomlyFunction.Builder()
                                            .withOneOf(HolderSet.direct(
                                                    registrylookup.getOrThrow(Enchantments.THORNS),
                                                    registrylookup.getOrThrow(Enchantments.UNBREAKING))))
                                    .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15F, 0.45F))))
                            .add(LootItem.lootTableItem(Items.GOLDEN_HELMET)
                                    .setWeight(3)
                                    .apply(new EnchantRandomlyFunction.Builder() .withOneOf(HolderSet.direct(
                                            registrylookup.getOrThrow(Enchantments.MENDING),
                                            registrylookup.getOrThrow(Enchantments.RESPIRATION)))))
                            .add(LootItem.lootTableItem(Items.DIAMOND_HORSE_ARMOR)
                                    .setWeight(3)))
                    .withPool(LootPool.lootPool().when(LootItemRandomChanceCondition.randomChance(0.65F))
                            .setRolls(ConstantValue.exactly(1.0F))
                            .add(LootItem.lootTableItem(RegItems.REINFORCED_TEMPLATE.get())
                                    .setWeight(5))
                            .add(LootItem.lootTableItem(RegItems.COATING_TEMPLATE.get())
                                    .setWeight(5))
                            .add(LootItem.lootTableItem(Items.DIAMOND)
                                    .setWeight(1)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 7.0F))))
                            .add(LootItem.lootTableItem(Items.GOLDEN_PICKAXE)
                                    .setWeight(2)
                                    .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15F, 0.45F)))
                                    .apply(new EnchantRandomlyFunction.Builder()
                                            .withOneOf(HolderSet.direct(
                                                    registrylookup.getOrThrow(Enchantments.EFFICIENCY),
                                                    registrylookup.getOrThrow(Enchantments.SILK_TOUCH),
                                                    registrylookup.getOrThrow(Enchantments.UNBREAKING)))))
                            .add(LootItem.lootTableItem(Items.GOLDEN_APPLE)
                                    .setWeight(10)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                            .add(LootItem.lootTableItem(RegItems.STEEL_HORSE_ARMOR)
                                    .setWeight(10))
                            .add(LootItem.lootTableItem(Items.BOOK)
                                    .setWeight(5)
                                    .apply(new EnchantRandomlyFunction.Builder().withEnchantment(registrylookup.getOrThrow(Enchantments.SWEEPING_EDGE))))
                            .add(LootItem.lootTableItem(Items.BOOK)
                                    .setWeight(1)
                                    .apply(new EnchantRandomlyFunction.Builder().withEnchantment(registrylookup.getOrThrow(Enchantments.MENDING))))
                    ));
        }
    }
}
