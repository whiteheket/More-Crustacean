package entity.client.renderer;

import entity.client.model.SwimmerCrabModel;
import entity.crustacean.crab.SwimmerCrabEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import white_heket.more_crustacean.MoreCrustacean;

public class SwimmerCrabRenderer extends GeoEntityRenderer<SwimmerCrabEntity> {
    public SwimmerCrabRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new SwimmerCrabModel());
        this.shadowRadius = 0.4F;
    }
    @Override
    public Identifier getTextureLocation(SwimmerCrabEntity swimmerCrabEntity){
        return new Identifier(MoreCrustacean.MOD_ID,"textures/entity/swimmer_crab.png");
    }
    @Override
    public void render(SwimmerCrabEntity animatable, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if (animatable.isBaby()) {
            poseStack.scale(0.5F,0.5F,0.5F);
        }
        else {
            poseStack.scale(1.0F,1.0F,1.0F);
        }
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
