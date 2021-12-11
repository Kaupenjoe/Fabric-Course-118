package net.kaupenjoe.mccourse.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {
    public static BlockEntityType<OrichalcumBlasterEntity> ORICHALCUM_BLASTER;

    public static void registerAllEntities() {
        ORICHALCUM_BLASTER = Registry.register(Registry.BLOCK_ENTITY_TYPE,
                new Identifier(MCCourseMod.MOD_ID, "orichalcum_blaster"),
                        FabricBlockEntityTypeBuilder.create(OrichalcumBlasterEntity::new,
                                ModBlocks.ORICHALCUM_BLASTER).build(null));
    }
}
