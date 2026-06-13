package net.bexla.orevolution.content.types.datamods;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class AddItemsModifier extends LootModifier {
    public static final MapCodec<AddItemsModifier> CODEC = RecordCodecBuilder.mapCodec(builder ->
            codecStart(builder).and(
                    ItemStack.CODEC.fieldOf("item").forGetter((AddItemsModifier it) -> it.stack)
            ).apply(builder, AddItemsModifier::new)
    );

    private final ItemStack stack;

    public AddItemsModifier(LootItemCondition[] conditions, ItemStack field1) {
        super(conditions);
        this.stack = field1;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(stack);
        return generatedLoot;
    }
}
