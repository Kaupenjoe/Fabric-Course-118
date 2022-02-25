package net.kaupenjoe.mccourse.mixin;

import net.kaupenjoe.mccourse.world.structure.ModStructures;
import net.kaupenjoe.mccourse.world.structure.RunDownHouseStructure;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoiseChunkGenerator.class)
public class NoiseChunkGeneratorMixin {

    @Inject(
            method = "getEntitySpawnList(Lnet/minecraft/world/biome/Biome;Lnet/minecraft/world/gen/StructureAccessor;Lnet/minecraft/entity/SpawnGroup;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/collection/Pool;",
            at = @At(value = "HEAD"),
            cancellable = true
    )
    private void structureMobs(Biome biome, StructureAccessor accessor, SpawnGroup group, BlockPos pos, CallbackInfoReturnable<Pool<SpawnSettings.SpawnEntry>> cir) {

        // Check if in our structure and grab mob list if so
        Pool<SpawnSettings.SpawnEntry> pool = getStructureSpawns(biome, accessor, group, pos);

        // If not null, it was in our structure. Return the mob list and exit the method now.
        if(pool != null) cir.setReturnValue(pool);
    }

    /**
     * This mixin hooks into NoiseChunkGenerator's getEntitySpawnList which is where vanilla does the
     * mob spawning in structures over time. We have to check what the spawn group is for the structure
     * and then see if it is inside the structure. Then we grab the list of mobs from the structure that can spawn.
     *
     * This way of doing structure mob spawning will prevent biome's mobs from spawning in the structure.
     */
    private static Pool<SpawnSettings.SpawnEntry> getStructureSpawns(Biome biome, StructureAccessor accessor, SpawnGroup group, BlockPos pos){

        if (group == SpawnGroup.MONSTER) {
            if (accessor.getStructureAt(pos, ModStructures.RUN_DOWN_HOUSE).hasChildren()) {
                return RunDownHouseStructure.STRUCTURE_MONSTERS;
            }
        }
        else if (group == SpawnGroup.CREATURE) {
            if (accessor.getStructureAt(pos, ModStructures.RUN_DOWN_HOUSE).hasChildren()) {
                return RunDownHouseStructure.STRUCTURE_CREATURES;
            }
        }

        return null;
    }
}