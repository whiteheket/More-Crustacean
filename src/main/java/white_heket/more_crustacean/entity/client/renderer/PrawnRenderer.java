package white_heket.more_crustacean.entity.client.renderer;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import white_heket.more_crustacean.MoreCrustacean;
import white_heket.more_crustacean.entity.client.model.PrawnModel;
import white_heket.more_crustacean.entity.crustacean.xia.PrawnEntity;

public class PrawnRenderer extends GeoEntityRenderer<PrawnEntity> {
    public PrawnRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new PrawnModel());
        this.shadowRadius = 0.15F;
    }
    @Override
    public Identifier getTextureLocation(PrawnEntity prawnEntity){
        return new Identifier(MoreCrustacean.MOD_ID,"textures/entity/prawn.png");
    }
    @Override
    public void render(PrawnEntity animatable, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if (animatable.isBaby()) {
            poseStack.scale(0.5F,0.5F,0.5F);
        }
        else {
            poseStack.scale(1.0F,1.0F,1.0F);
        }
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
