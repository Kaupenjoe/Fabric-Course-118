package net.kaupenjoe.mccourse.entity.client;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.entity.custom.TigerEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class TigerModel extends AnimatedGeoModel<TigerEntity> {
    @Override
    public Identifier getModelResource(TigerEntity entity) {
        return new Identifier(MCCourseMod.MOD_ID, "geo/tiger.geo.json");
    }

    @Override
    public Identifier getTextureResource(TigerEntity entity) {
        return new Identifier(MCCourseMod.MOD_ID, "textures/entity/tiger/tiger.png");
    }

    @Override
    public Identifier getAnimationResource(TigerEntity entity) {
        return new Identifier(MCCourseMod.MOD_ID, "animations/tiger.animation.json");
    }
}