package white_heket.more_crustacean.entity.client.renderer;

import white_heket.more_crustacean.entity.client.model.ClawsterModel;
import white_heket.more_crustacean.entity.crustacean.xia.ClawsterEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import white_heket.more_crustacean.MoreCrustacean;

public class ClawsterRenderer extends GeoEntityRenderer<ClawsterEntity> {
    public ClawsterRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new ClawsterModel());
        this.shadowRadius = 0.4F;
    }
    @Override
    public Identifier getTextureLocation(ClawsterEntity clawsterEntity){
        return new Identifier(MoreCrustacean.MOD_ID,"textures/entity/clawster.png");
    }
    @Override
    public boolean isShaking(ClawsterEntity clawster){
        return clawster.isCrazy() || super.isShaking(clawster);
    }
    @Override
    public void render(ClawsterEntity animatable, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if (animatable.isBaby()) {
            poseStack.scale(0.5F,0.5F,0.5F);
        }
        else {
            poseStack.scale(1.7F,1.7F,1.7F);
        }
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
