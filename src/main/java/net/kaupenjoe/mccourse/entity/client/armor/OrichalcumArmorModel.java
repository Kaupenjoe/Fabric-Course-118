package net.kaupenjoe.mccourse.entity.client.armor;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.item.custom.OrichalcumArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class OrichalcumArmorModel extends AnimatedGeoModel<OrichalcumArmorItem> {
    @Override
    public Identifier getModelLocation(OrichalcumArmorItem object) {
        return new Identifier(MCCourseMod.MOD_ID, "geo/orichalcum_armor.geo.json");
    }

    @Override
    public Identifier getTextureLocation(OrichalcumArmorItem object) {
        return new Identifier(MCCourseMod.MOD_ID, "textures/models/armor/orichalcum_armor_texture.png");
    }

    @Override
    public Identifier getAnimationFileLocation(OrichalcumArmorItem animatable) {
        return new Identifier(MCCourseMod.MOD_ID, "animations/armor_animation.json");
    }
}
