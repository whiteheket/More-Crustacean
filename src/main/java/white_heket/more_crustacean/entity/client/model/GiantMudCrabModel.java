package white_heket.more_crustacean.entity.client.model;

import white_heket.more_crustacean.entity.crustacean.crab.GiantMudCrabEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import white_heket.more_crustacean.MoreCrustacean;

public class GiantMudCrabModel extends GeoModel<GiantMudCrabEntity> {
    @Override
    public Identifier getModelResource(GiantMudCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID,"geo/giant_mud_crab.geo.json");
    }

    @Override
    public Identifier getTextureResource(GiantMudCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID,"textures/entity/giant_mud_crab.png");
    }

    @Override
    public Identifier getAnimationResource(GiantMudCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID,"animations/giant_mud_crab.animation.json");
    }
}
