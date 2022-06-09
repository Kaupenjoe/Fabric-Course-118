package net.kaupenjoe.mccourse.entity.client;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.entity.custom.RaccoonEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RaccoonModel extends AnimatedGeoModel<RaccoonEntity> {
    @Override
    public Identifier getModelResource(RaccoonEntity entity) {
        return new Identifier(MCCourseMod.MOD_ID, "geo/raccoon.geo.json");
    }

    @Override
    public Identifier getTextureResource(RaccoonEntity entity) {
        return RaccoonRenderer.LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public Identifier getAnimationResource(RaccoonEntity entity) {
        return new Identifier(MCCourseMod.MOD_ID, "animations/raccoon.animation.json");
    }
}
