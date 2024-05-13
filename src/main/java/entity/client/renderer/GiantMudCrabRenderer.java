package entity.client.renderer;

import entity.client.model.GiantMudCrabModel;
import entity.crustacean.crab.GiantMudCrabEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import white_heket.more_crustacean.MoreCrustacean;

public class GiantMudCrabRenderer extends GeoEntityRenderer<GiantMudCrabEntity> {
    public GiantMudCrabRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new GiantMudCrabModel());
        this.shadowRadius = 0.8F;
    }
    @Override
    public Identifier getTextureLocation(GiantMudCrabEntity giantMudCrabEntity){
        return new Identifier(MoreCrustacean.MOD_ID,"textures/entity/giant_mud_crab.png");
    }
    @Override
    public void render(GiantMudCrabEntity animatable, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if (animatable.isBaby()) {
            poseStack.scale(0.5F,0.5F,0.5F);
        }
        else {
            poseStack.scale(1.0F,1.0F,1.0F);
        }
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
