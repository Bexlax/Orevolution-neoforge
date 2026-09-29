package net.bexla.orevolution.init.modcompat;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.OrevolutionTiers;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.common.registry.ModItems.knifeItem;

public class FDRegistry {
    public static final DeferredRegister<Item> FD_REG = DeferredRegister.create(Registries.ITEM, Orevolution.MODID);

    public static Supplier<Item> TIN_KNIFE = FD_REG.register("tin_knife", () -> new KnifeItem(OrevolutionTiers.ToolTiers.TIN, knifeItem(OrevolutionTiers.ToolTiers.TIN)));
//    public static Supplier<Item> NICKEL_KNIFE = FD_REG.register("nickel_knife", () -> new KnifeItem(OrevolutionTiers.ToolTiers.NICKEL, knifeItem(OrevolutionTiers.ToolTiers.NICKEL)));
    public static Supplier<Item> PLATINUM_KNIFE = FD_REG.register("platinum_knife", () -> new KnifeItem(OrevolutionTiers.ToolTiers.PLATINUM, knifeItem(OrevolutionTiers.ToolTiers.PLATINUM)));
    public static Supplier<Item> AETHERSTEEL_KNIFE = FD_REG.register("aethersteel_knife", () -> new KnifeItem(OrevolutionTiers.ToolTiers.AETHERSTEEL, knifeItem(OrevolutionTiers.ToolTiers.AETHERSTEEL).fireResistant()));
    public static Supplier<Item> LIVINGSTONE_KNIFE = FD_REG.register("livingstone_knife", () -> new KnifeItem(OrevolutionTiers.ToolTiers.LIVINGSTONE, knifeItem(OrevolutionTiers.ToolTiers.LIVINGSTONE)));
    public static Supplier<Item> VERDITE_KNIFE = FD_REG.register("verdite_knife", () -> new KnifeItem(OrevolutionTiers.ToolTiers.VERDITE, knifeItem(OrevolutionTiers.ToolTiers.VERDITE)));
    public static Supplier<Item> MOONSTONE_KNIFE = FD_REG.register("moonstone_knife", () -> new KnifeItem(OrevolutionTiers.ToolTiers.MOONSTONE, knifeItem(OrevolutionTiers.ToolTiers.MOONSTONE)));
}
