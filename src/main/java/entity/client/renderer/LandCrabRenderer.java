package entity.client.renderer;

import entity.client.model.LandCrabModel;
import entity.crustacean.crab.BrownCrabEntity;
import entity.crustacean.crab.LandCrabEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import white_heket.more_crustacean.MoreCrustacean;

public class LandCrabRenderer extends GeoEntityRenderer<LandCrabEntity> {
    public LandCrabRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new LandCrabModel());
        this.shadowRadius = 0.55F;
    }
    @Override
    public Identifier getTextureLocation(LandCrabEntity landCrabEntity){
        if (landCrabEntity.isCanCutClaw() && landCrabEntity.getRegenTime() == 0){
            return new Identifier(MoreCrustacean.MOD_ID,"textures/entity/big_claw_land_crab.png");
        } else {
            return new Identifier(MoreCrustacean.MOD_ID, "textures/entity/land_crab.png");
        }
    }
    @Override
    public void render(LandCrabEntity animatable, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if (animatable.isBaby()) {
            poseStack.scale(0.5F,0.5F,0.5F);
        }
        else {
            poseStack.scale(1.0F,1.0F,1.0F);
        }
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
