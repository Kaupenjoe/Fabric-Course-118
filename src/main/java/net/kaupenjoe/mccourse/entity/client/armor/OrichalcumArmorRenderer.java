package net.kaupenjoe.mccourse.entity.client.armor;

import net.kaupenjoe.mccourse.item.custom.OrichalcumArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class OrichalcumArmorRenderer extends GeoArmorRenderer<OrichalcumArmorItem> {
    public OrichalcumArmorRenderer() {
        super(new OrichalcumArmorModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorLeftLeg";
        this.leftLegBone = "armorRightLeg";
        this.rightBootBone = "armorLeftBoot";
        this.leftBootBone = "armorRightBoot";
    }
}
