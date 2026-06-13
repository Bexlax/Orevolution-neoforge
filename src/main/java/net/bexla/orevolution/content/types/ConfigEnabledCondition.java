package net.bexla.orevolution.content.types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.bexla.orevolution.OrevolutionConfig;
import net.neoforged.neoforge.common.conditions.ICondition;

public record ConfigEnabledCondition(String configName) implements ICondition {

    public static final MapCodec<ConfigEnabledCondition> CODEC =
            RecordCodecBuilder.mapCodec(builder ->
                    builder.group(
                            Codec.STRING.fieldOf("config_name")
                                    .forGetter(ConfigEnabledCondition::configName)
                    ).apply(builder, ConfigEnabledCondition::new)
            );

    @Override
    public boolean test(ICondition.IContext context) {
        return switch (configName) {
            case "mod_progression" -> OrevolutionConfig.COMMON.modProgression.get();
            default -> false;
        };
    }

    @Override
    public MapCodec<? extends ICondition> codec() {
        return CODEC;
    }

    @Override
    public String toString() {
        return "config_loaded(\"" + configName + "\")";
    }
}