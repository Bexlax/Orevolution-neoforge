package net.bexla.orevolution.mixins;

import net.bexla.orevolution.content.data.utility.OrevolutionKeys;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(PiglinAi.class)
public class PiglinAiMixin {
    @Shadow
    private static void throwItems(Piglin piglin, List<ItemStack> stacks) {}

    @Inject(method = "isBarterCurrency", at = @At("HEAD"), cancellable = true)
    private static void orevolution$allowTungsten(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (stack.is(RegItems.TUNGSTEN_INGOT.get())) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "stopHoldingOffHandItem", at = @At("HEAD"), cancellable = true)
    private static void orevolution$tungstenBarter(Piglin piglin, boolean shouldBarter, CallbackInfo ci) {
        ItemStack stack = piglin.getItemInHand(InteractionHand.OFF_HAND);

        if (!stack.is(RegItems.TUNGSTEN_INGOT.get())) return;
        if (!piglin.isAdult()) return;

        piglin.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);

        if (shouldBarter) {
            LootTable lootTable = piglin.level()
                    .getServer()
                    .reloadableRegistries()
                    .getLootTable(OrevolutionKeys.LootTables.PIGLIN_TUNGSTEN_BARTERING);

            List<ItemStack> items = lootTable.getRandomItems(
                    new LootParams.Builder((ServerLevel) piglin.level())
                            .withParameter(LootContextParams.THIS_ENTITY, piglin)
                            .create(LootContextParamSets.PIGLIN_BARTER)
            );

            throwItems(piglin, items);

            ci.cancel();
        }
    }
}