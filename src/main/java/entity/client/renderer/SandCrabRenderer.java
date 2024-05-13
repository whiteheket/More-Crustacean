package entity.client.renderer;

import entity.client.model.SandCrabModel;
import entity.crustacean.crab.CoconutCrabEntity;
import entity.crustacean.crab.SandCrabEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import white_heket.more_crustacean.MoreCrustacean;

public class SandCrabRenderer extends GeoEntityRenderer<SandCrabEntity> {
    public SandCrabRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager,new SandCrabModel());
        this.shadowRadius = 0.2f;
    }
    @Override
    public Identifier getTextureLocation(SandCrabEntity sandCrabEntity){
        return new Identifier(MoreCrustacean.MOD_ID,"textures/entity/sand_crab.png");
    }
    @Override
    public void render(SandCrabEntity animatable, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if (animatable.isBaby()) {
            poseStack.scale(0.5F,0.5F,0.5F);
        }
        else {
            poseStack.scale(1.0F,1.0F,1.0F);
        }
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
