package net.bexla.orevolution.content.types;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.content.types.interfaces.IToolPower;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ToolPowerRegistry {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final Map<Tier, ToolPowerPair> tierTagMap = new HashMap<>();

    public record ToolPowerPair(ModConfigSpec.ConfigValue<Boolean> toolsEnabled, ModConfigSpec.ConfigValue<Boolean> swordsEnabled, IToolPower toolPower, IToolPower swordPower) {}

    public static void register(Tier tier, ModConfigSpec.ConfigValue<Boolean> toolsEnabled, ModConfigSpec.ConfigValue<Boolean> swordsEnabled, IToolPower toolPower, IToolPower swordPower) {
        if (tierTagMap.containsKey(tier)) {
            LOGGER.warn("Overriding existing IToolPower registration for tier: {}", tier);
        }

        tierTagMap.put(tier, new ToolPowerPair(toolsEnabled, swordsEnabled, toolPower, swordPower));

        LOGGER.debug("Registered powers for tier {} -> Tool: {}, Sword: {}", tier, toolPower.getClass().getSimpleName(), swordPower.getClass().getSimpleName()
        );
    }

    public static IToolPower getToolPower(Tier tier) {
        ToolPowerPair pair = tierTagMap.get(tier);

        if (pair == null || !pair.toolsEnabled().get()) {
            return IToolPower.EMPTY;
        }

        return pair.toolPower();
    }

    public static IToolPower getWeaponPower(Tier tier) {
        ToolPowerPair pair = tierTagMap.get(tier);

        if (pair == null || !pair.swordsEnabled().get()) {
            return IToolPower.EMPTY;
        }

        return pair.swordPower();
    }
}
