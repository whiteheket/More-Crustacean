package entity.client.renderer;

import entity.client.model.CoconutCrabModel;
import entity.crustacean.crab.BrownCrabEntity;
import entity.crustacean.crab.CoconutCrabEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import white_heket.more_crustacean.MoreCrustacean;

public class CoconutCrabRenderer extends GeoEntityRenderer<CoconutCrabEntity> {
    public CoconutCrabRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new CoconutCrabModel());
        this.shadowRadius = 0.6F;
    }
    @Override
    public Identifier getTextureLocation(CoconutCrabEntity coconutCrabEntity){
        return new Identifier(MoreCrustacean.MOD_ID,"textures/entity/coconut_crab.png");
    }
    @Override
    public void render(CoconutCrabEntity animatable, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if (animatable.isBaby()) {
            poseStack.scale(0.5F,0.5F,0.5F);
        }
        else {
            poseStack.scale(1.0F,1.0F,1.0F);
        }
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
