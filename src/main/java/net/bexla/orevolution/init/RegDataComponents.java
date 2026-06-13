package net.bexla.orevolution.init;

import com.mojang.serialization.Codec;
import net.bexla.orevolution.Orevolution;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RegDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, Orevolution.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> SOUL_BOUND =
            DATA_COMPONENT_TYPES.register("soul_bound", () ->
                    DataComponentType.<Boolean>builder()
                            .persistent(Codec.BOOL)
                            .networkSynchronized(ByteBufCodecs.BOOL)
                            .build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> LOCATOR_MODE =
            DATA_COMPONENT_TYPES.register("locator_mode", () ->
                    DataComponentType.<Boolean>builder()
                            .persistent(Codec.BOOL)
                            .networkSynchronized(ByteBufCodecs.BOOL)
                            .build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> LOCATOR_PLAYER_INDEX =
            DATA_COMPONENT_TYPES.register("locator_player_index", () ->
                    DataComponentType.<Integer>builder()
                            .persistent(Codec.INT)
                            .networkSynchronized(ByteBufCodecs.INT)
                            .build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> LOCATOR_PLAYER =
            DATA_COMPONENT_TYPES.register("locator_player", () ->
                    DataComponentType.<String>builder()
                            .persistent(Codec.STRING)
                            .networkSynchronized(ByteBufCodecs.STRING_UTF8)
                            .build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> BLOCKS_MINED =
            DATA_COMPONENT_TYPES.register("blocks_mined", () ->
                    DataComponentType.<Integer>builder()
                            .persistent(Codec.INT)
                            .networkSynchronized(ByteBufCodecs.INT)
                            .build());
}