package net.bexla.orevolution.mixins;

import net.bexla.orevolution.content.data.utility.OrevolutionKeys;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Husk.class)
public class HuskMixin {
    @Inject(method = "checkHuskSpawnRules", at = @At("RETURN"), cancellable = true)
    private static void orevolution$injectSpawnRules(EntityType<Husk> husk, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random, CallbackInfoReturnable<Boolean> cir) {
        if(level.getBiome(pos).is(OrevolutionKeys.Biomes.FORGOTTEN_CAVE)) {
            cir.setReturnValue(true);
        }
    }
}
