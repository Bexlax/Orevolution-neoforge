package net.bexla.orevolution.content.types;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.data.powers.tools.ToolMultiPower;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.content.data.utility.OrevolutionUtils;
import net.bexla.orevolution.content.interfaces.IToolPower;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemPowerRegistry {
    private static final Map<Tier, ToolPowerPair> tierTagMap = new HashMap<>();

    public record ToolPowerPair(ModConfigSpec.ConfigValue<Boolean> toolsEnabled, ModConfigSpec.ConfigValue<Boolean> swordsEnabled, IToolPower toolPower, IToolPower swordPower) {
        private static final IToolPower EMPTY = IToolPower.EMPTY;

        public IToolPower combinedPower() {
            return new ToolMultiPower(List.of(swordPower, toolPower));
        }
    }

    public static void register(Tier tier, ModConfigSpec.ConfigValue<Boolean> toolsEnabled, ModConfigSpec.ConfigValue<Boolean> swordsEnabled, IToolPower toolPower, IToolPower swordPower) {
        if (tierTagMap.containsKey(tier)) {
            OrevolutionUtils.warn("Overriding existing IToolPower registration for tier: {}", tier);
        }

        tierTagMap.put(tier, new ToolPowerPair(toolsEnabled, swordsEnabled, toolPower, swordPower));

        OrevolutionUtils.debug("Registered powers for tier {} -> Tool: {}, Sword: {}", tier, toolPower.getClass().getSimpleName(), swordPower.getClass().getSimpleName()
        );
    }

    public static IToolPower getPowerForItem(ItemStack stack) {
        if (!(stack.getItem() instanceof TieredItem item)) return IToolPower.EMPTY;

        ToolPowerPair pair = tierTagMap.get(item.getTier());
        if (pair == null) return IToolPower.EMPTY;

        boolean weapon = stack.is(OrevolutionTags.Items.WEAPON_POWERS);
        boolean tool = stack.is(OrevolutionTags.Items.TOOL_POWERS);

        if (!weapon && !tool) return IToolPower.EMPTY;

        boolean globalWeaponsEnabled = OrevolutionConfig.COMMON.weaponsPowers.get();
        boolean globalToolsEnabled = OrevolutionConfig.COMMON.toolsPowers.get();

        IToolPower weaponPower = weapon && globalWeaponsEnabled && pair.swordsEnabled().get() ? pair.swordPower() : IToolPower.EMPTY;
        IToolPower toolPower = tool && globalToolsEnabled && pair.toolsEnabled().get() ? pair.toolPower() : IToolPower.EMPTY;

        if (weaponPower != IToolPower.EMPTY && toolPower != IToolPower.EMPTY) return pair.combinedPower();
        if (weaponPower != IToolPower.EMPTY) return weaponPower;

        return toolPower;
    }
}
