package entity.client.renderer;

import entity.client.model.KingCrabModel;
import entity.crustacean.crab.KingCrabEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import white_heket.more_crustacean.MoreCrustacean;

public class KingCrabRenderer extends GeoEntityRenderer<KingCrabEntity> {
    public KingCrabRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new KingCrabModel());
        this.shadowRadius = 0.8F;
    }
    @Override
    public Identifier getTextureLocation(KingCrabEntity kingCrabEntity){
        return new Identifier(MoreCrustacean.MOD_ID,"textures/entity/king_crab.png");
    }
    @Override
    public void render(KingCrabEntity animatable, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if (animatable.isBaby()) {
            poseStack.scale(0.5F,0.5F,0.5F);
        }
        else {
            poseStack.scale(1.0F,1.0F,1.0F);
        }
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
