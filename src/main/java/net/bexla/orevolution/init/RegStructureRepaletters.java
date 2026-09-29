package net.bexla.orevolution.init;

import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterEntry;
import com.teamabnormals.blueprint.common.world.modification.structure.StructureRepaletterManager;
import com.teamabnormals.blueprint.core.registry.BlueprintDataPackRegistries;
import net.bexla.orevolution.content.types.ChanceStructureRepaletter;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.bexla.orevolution.Orevolution.lc;

public class RegStructureRepaletters {
    public static final ResourceKey<StructureRepaletterEntry> BASTION_ADDITIONS = create("bastion_additions");

    public static void bootstrap(BootstrapContext<StructureRepaletterEntry> context) {
        HolderGetter<Structure> structures = context.lookup(Registries.STRUCTURE);

        context.register(BASTION_ADDITIONS, new StructureRepaletterEntry.Builder().repaletters(
                        new ChanceStructureRepaletter(Blocks.GOLD_BLOCK, RegBlocks.TUNGSTEN_BLOCK.get().defaultBlockState(), 0.1015F))
                .select(HolderSet.direct(Stream.of(BuiltinStructures.BASTION_REMNANT).map(structures::getOrThrow).collect(Collectors.toList()))));

    }

    private static ResourceKey<StructureRepaletterEntry> create(String name) {
        return ResourceKey.create(BlueprintDataPackRegistries.STRUCTURE_REPALETTERS, lc(name));
    }

    public static void registerRepaletters() {
        StructureRepaletterManager.registerRepalleter(lc("chance"), ChanceStructureRepaletter.CODEC, ChanceStructureRepaletter.CODEC);
    }
}
