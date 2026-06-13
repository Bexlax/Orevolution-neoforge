package net.bexla.orevolution.content.types;

import net.bexla.orevolution.content.types.interfaces.IArmorPower;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ArmorMaterial;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.HashMap;
import java.util.Map;

public class ArmorPowerRegistry {
    private static final Map<Holder<ArmorMaterial>, Entry> POWER_MAP = new HashMap<>();

    public static void register(ModConfigSpec.ConfigValue<Boolean> enabled, Holder<ArmorMaterial> material, IArmorPower power) {
        if (POWER_MAP.containsKey(material)) {
            throw new IllegalStateException("Duplicate registration for armor material: " + material);
        }

        POWER_MAP.put(material, new Entry(enabled, power));
    }

    public static IArmorPower getPower(Holder<ArmorMaterial> material) {
        Entry entry = POWER_MAP.get(material);

        if (entry == null) {
            return IArmorPower.EMPTY;
        }

        return entry.enabled.get() ? entry.power : IArmorPower.EMPTY;
    }

    private record Entry(ModConfigSpec.ConfigValue<Boolean> enabled, IArmorPower power) {}
}