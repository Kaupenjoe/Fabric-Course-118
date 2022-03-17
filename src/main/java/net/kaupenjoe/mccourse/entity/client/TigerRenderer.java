package net.kaupenjoe.mccourse.entity.client;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.entity.custom.TigerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class TigerRenderer extends GeoEntityRenderer<TigerEntity> {
    public TigerRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new TigerModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public Identifier getTextureLocation(TigerEntity entity) {
        return new Identifier(MCCourseMod.MOD_ID, "textures/entity/tiger/tiger.png");
    }

    @Override
    public RenderLayer getRenderType(TigerEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(0.4f, 0.4f, 0.4f);
        } else {
            stack.scale(1f, 1f, 1f);
        }

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}
