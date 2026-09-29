package net.bexla.orevolution.init;

import com.mojang.serialization.Codec;
import net.bexla.orevolution.Orevolution;
import net.minecraft.core.UUIDUtil;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Unit;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.UUID;

public class RegDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENT_TYPES = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Orevolution.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> WIP =
            DATA_COMPONENT_TYPES.registerComponentType("wip", builder ->
                    builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL));


    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> EMERALD_TOTEM =
            DATA_COMPONENT_TYPES.registerComponentType("emerald_totem", builder ->
                    builder.persistent(Unit.CODEC).networkSynchronized(StreamCodec.unit(Unit.INSTANCE)));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> DIAMOND_TOTEM =
            DATA_COMPONENT_TYPES.registerComponentType("diamond_totem", builder ->
                    builder.persistent(Unit.CODEC).networkSynchronized(StreamCodec.unit(Unit.INSTANCE)));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> AMETHYST_TOTEM =
            DATA_COMPONENT_TYPES.registerComponentType("amethyst_totem", builder ->
                    builder.persistent(Unit.CODEC).networkSynchronized(StreamCodec.unit(Unit.INSTANCE)));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> CELESTITE_TOTEM =
            DATA_COMPONENT_TYPES.registerComponentType("celestite_totem", builder ->
                    builder.persistent(Unit.CODEC).networkSynchronized(StreamCodec.unit(Unit.INSTANCE)));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> QUARTZ_TOTEM =
            DATA_COMPONENT_TYPES.registerComponentType("quartz_totem", builder ->
                    builder.persistent(Unit.CODEC).networkSynchronized(StreamCodec.unit(Unit.INSTANCE)));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> LAPIS_TOTEM =
            DATA_COMPONENT_TYPES.registerComponentType("lapis_totem", builder ->
                    builder.persistent(Unit.CODEC).networkSynchronized(StreamCodec.unit(Unit.INSTANCE)));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> STAR_TOTEM =
            DATA_COMPONENT_TYPES.registerComponentType("star_totem", builder ->
                    builder.persistent(Unit.CODEC).networkSynchronized(StreamCodec.unit(Unit.INSTANCE)));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> REINFORCED =
            DATA_COMPONENT_TYPES.registerComponentType("reinforced", builder ->
                    builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> COATED =
            DATA_COMPONENT_TYPES.registerComponentType("coated", builder ->
                    builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Double>> MAX_DAMAGE_MULTIPLIER =
            DATA_COMPONENT_TYPES.registerComponentType("max_damage_multiplier", builder ->
                    builder.persistent(Codec.DOUBLE).networkSynchronized(ByteBufCodecs.DOUBLE));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> SOUL_BOUND =
            DATA_COMPONENT_TYPES.registerComponentType("soul_bound", builder ->
                    builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> RADAR_MODE =
            DATA_COMPONENT_TYPES.registerComponentType("radar_mode", builder ->
                    builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> RADAR_PLAYER_INDEX =
            DATA_COMPONENT_TYPES.registerComponentType("radar_player_index", builder ->
                    builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<UUID>> RADAR_PLAYER =
            DATA_COMPONENT_TYPES.registerComponentType("radar_player", builder ->
                    builder.persistent(UUIDUtil.CODEC).networkSynchronized(UUIDUtil.STREAM_CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> RADAR_SEARCH_TIME =
            DATA_COMPONENT_TYPES.registerComponentType("radar_search_time", builder ->
                    builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> CONSECUTIVE_BLOCKS =
            DATA_COMPONENT_TYPES.registerComponentType("consecutive_blocks", builder ->
                    builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> CONSECUTIVE_HITS =
            DATA_COMPONENT_TYPES.registerComponentType("consecutive_hits", builder ->
                    builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> SCANNED_ORES =
            DATA_COMPONENT_TYPES.registerComponentType("scanned_ores", builder ->
                    builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> MOTION_STATE =
            DATA_COMPONENT_TYPES.registerComponentType("motion_state", builder ->
                    builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));

}