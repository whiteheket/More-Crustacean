package white_heket.more_crustacean.entity.client.renderer;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import white_heket.more_crustacean.MoreCrustacean;
import white_heket.more_crustacean.entity.client.model.CrayfishModel;
import white_heket.more_crustacean.entity.crustacean.xia.CrayfishEntity;

public class CrayfishRenderer extends GeoEntityRenderer<CrayfishEntity> {
    public CrayfishRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new CrayfishModel());
        this.shadowRadius = 0.3F;
    }
    @Override
    public Identifier getTextureLocation(CrayfishEntity crayfish){
        String string = Formatting.strip(crayfish.getName().getString());
        if ("Great Cook Xia".equals(string) || "虾大厨".equals(string)){
            return new Identifier(MoreCrustacean.MOD_ID,"textures/entity/crayfish_cook.png");
        } else {
            return new Identifier(MoreCrustacean.MOD_ID, "textures/entity/crayfish.png");
        }
    }
    @Override
    public void render(CrayfishEntity animatable, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if (animatable.isBaby()) {
            poseStack.scale(0.5F,0.5F,0.5F);
        }
        else {
            poseStack.scale(1.0F,1.0F,1.0F);
        }
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
