package white_heket.more_crustacean.entity.client.model;

import white_heket.more_crustacean.entity.crustacean.xia.ClawsterEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import white_heket.more_crustacean.MoreCrustacean;

public class ClawsterModel extends GeoModel<ClawsterEntity> {
    @Override
    public Identifier getModelResource(ClawsterEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "geo/clawster.geo.json");
    }

    @Override
    public Identifier getTextureResource(ClawsterEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "textures/entity/clawster.png");
    }

    @Override
    public Identifier getAnimationResource(ClawsterEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "animations/clawster.animation.json");
    }
}
