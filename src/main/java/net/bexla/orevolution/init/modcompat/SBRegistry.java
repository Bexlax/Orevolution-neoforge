package net.bexla.orevolution.init.modcompat;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.OrevolutionTiers;
import net.bexla.orevolution.content.types.item.SpearItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SBRegistry {
    public static final DeferredRegister<Item> SB_REG = DeferredRegister.create(Registries.ITEM, Orevolution.MODID);

    public static Supplier<Item> TIN_SPEAR = SB_REG.register("tin_spear",
            () -> new SpearItem(
                    OrevolutionTiers.ToolTiers.TIN,
                    new SpearItem.SpearStats(
                            0.85F,
                            0.88F,
                            0.67F,
                            4.0F,
                            9.0F,
                            5.0F,
                            5.1F,
                            12.5F,
                            4.6F
                    ), new Item.Properties()));
//    public static Supplier<Item> NICKEL_SPEAR = SB_REG.register("nickel_spear",
//            () -> new SpearItem(
//                    OrevolutionTiers.ToolTiers.NICKEL,
//                    new SpearItem.SpearStats(
//                            0.85F,
//                            0.88F,
//                            0.67F,
//                            4.0F,
//                            9.0F,
//                            5.0F,
//                            5.1F,
//                            12.5F,
//                            4.6F
//                    ), new Item.Properties()));
    public static Supplier<Item> PLATINUM_SPEAR = SB_REG.register("platinum_spear",
            () -> new SpearItem(
                    OrevolutionTiers.ToolTiers.PLATINUM,
                    new SpearItem.SpearStats(
                            1.00F,
                            1.02F,
                            0.55F,
                            2.75F,
                            7.75F,
                            4.25F,
                            5.1F,
                            10.6F,
                            4.6F
                    ), new Item.Properties()));
    public static Supplier<Item> MOONSTONE_SPEAR = SB_REG.register("moonstone_spear",
            () -> new SpearItem(
                    OrevolutionTiers.ToolTiers.MOONSTONE,
                    new SpearItem.SpearStats(
                            1.00F,
                            1.06F,
                            0.55F,
                            2.75F,
                            7.75F,
                            4.25F,
                            5.1F,
                            10.6F,
                            4.6F
                    ), new Item.Properties()));
    public static Supplier<Item> AETHERSTEEL_SPEAR = SB_REG.register("aethersteel_spear",
            () -> new SpearItem(
                    OrevolutionTiers.ToolTiers.AETHERSTEEL,
                    new SpearItem.SpearStats(
                            1.22F,
                            1.28F,
                            0.32F,
                            2.0F,
                            6.5F,
                            3.0F,
                            5.1F,
                            8.0F,
                            4.6F
                    ), new Item.Properties()));
    public static Supplier<Item> LIVINGSTONE_SPEAR = SB_REG.register("livingstone_spear",
            () -> new SpearItem(
                    OrevolutionTiers.ToolTiers.LIVINGSTONE,
                    new SpearItem.SpearStats(
                            0.82F,
                            0.86F,
                            0.68F,
                            4.25F,
                            9.25F,
                            5.15F,
                            5.1F,
                            12.8F,
                            4.6F
                    ), new Item.Properties()));
    public static Supplier<Item> VERDITE_SPEAR = SB_REG.register("verdite_spear",
            () -> new SpearItem(
                    OrevolutionTiers.ToolTiers.VERDITE,
                    new SpearItem.SpearStats(
                            1.02F,
                            1.00F,
                            0.52F,
                            2.5F,
                            7.5F,
                            4.15F,
                            5.1F,
                            10.4F,
                            4.6F
                    ), new Item.Properties()));
}
