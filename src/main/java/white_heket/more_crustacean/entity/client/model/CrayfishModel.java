package white_heket.more_crustacean.entity.client.model;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import white_heket.more_crustacean.MoreCrustacean;
import white_heket.more_crustacean.entity.crustacean.xia.CrayfishEntity;

public class CrayfishModel extends GeoModel<CrayfishEntity> {
    @Override
    public Identifier getModelResource(CrayfishEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID,"geo/crayfish.geo.json");
    }

    @Override
    public Identifier getTextureResource(CrayfishEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID,"textures/entity/crayfish.png");
    }

    @Override
    public Identifier getAnimationResource(CrayfishEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID,"animations/crayfish.animation.json");
    }
}
