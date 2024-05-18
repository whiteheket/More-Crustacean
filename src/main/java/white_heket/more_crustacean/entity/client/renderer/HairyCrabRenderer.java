package white_heket.more_crustacean.entity.client.renderer;

import white_heket.more_crustacean.entity.client.model.HairyCrabModel;
import white_heket.more_crustacean.entity.crustacean.crab.HairyCrabEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import white_heket.more_crustacean.MoreCrustacean;

public class HairyCrabRenderer extends GeoEntityRenderer<HairyCrabEntity> {
    public HairyCrabRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new HairyCrabModel());
        this.shadowRadius = 0.5F;
    }
    @Override
    public Identifier getTextureLocation(HairyCrabEntity brownCrabEntity){
        return new Identifier(MoreCrustacean.MOD_ID,"textures/entity/hairy_crab.png");
    }
    @Override
    public void render(HairyCrabEntity animatable, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if (animatable.isBaby()) {
            poseStack.scale(0.5F,0.5F,0.5F);
        }
        else {
            poseStack.scale(1.0F,1.0F,1.0F);
        }
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
