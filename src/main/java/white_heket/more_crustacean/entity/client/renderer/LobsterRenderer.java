package white_heket.more_crustacean.entity.client.renderer;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import white_heket.more_crustacean.MoreCrustacean;
import white_heket.more_crustacean.entity.client.model.LobsterModel;
import white_heket.more_crustacean.entity.crustacean.xia.LobsterEntity;

public class LobsterRenderer extends GeoEntityRenderer<LobsterEntity> {
    public LobsterRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new LobsterModel());
        this.shadowRadius = 0.35F;
    }
    @Override
    public Identifier getTextureLocation(LobsterEntity lobsterEntity){
        return new Identifier(MoreCrustacean.MOD_ID,"textures/entity/lobster.png");
    }
    @Override
    public void render(LobsterEntity animatable, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if (animatable.isBaby()) {
            poseStack.scale(0.5F,0.5F,0.5F);
        }
        else {
            poseStack.scale(1.3F,1.3F,1.3F);
        }
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
