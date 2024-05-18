package white_heket.more_crustacean.entity.client.renderer;

import white_heket.more_crustacean.entity.client.model.BrownCrabModel;
import white_heket.more_crustacean.entity.crustacean.crab.BrownCrabEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import white_heket.more_crustacean.MoreCrustacean;

public class BrownCrabRenderer extends GeoEntityRenderer<BrownCrabEntity> {
    public BrownCrabRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new BrownCrabModel());
        this.shadowRadius = 0.4F;
    }
    @Override
    public Identifier getTextureLocation(BrownCrabEntity brownCrabEntity){
        String string = Formatting.strip(brownCrabEntity.getName().getString());
        if ("Mr.Krab".equals(string) || "蟹老板".equals(string)){
            return new Identifier(MoreCrustacean.MOD_ID,"textures/entity/brown_crab_mr_krab.png");
        } else {
            return new Identifier(MoreCrustacean.MOD_ID, "textures/entity/brown_crab.png");
        }
    }
    @Override
    public void render(BrownCrabEntity animatable, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if (animatable.isBaby()) {
            poseStack.scale(0.5F,0.5F,0.5F);
        }
        else {
            poseStack.scale(1.0F,1.0F,1.0F);
        }
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
