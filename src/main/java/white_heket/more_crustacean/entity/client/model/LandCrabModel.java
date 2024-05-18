package white_heket.more_crustacean.entity.client.model;

import white_heket.more_crustacean.entity.crustacean.crab.LandCrabEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import white_heket.more_crustacean.MoreCrustacean;

public class LandCrabModel extends GeoModel<LandCrabEntity> {
    @Override
    public Identifier getModelResource(LandCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "geo/land_crab.geo.json");
    }

    @Override
    public Identifier getTextureResource(LandCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "textures/entity/land_crab.png");
    }

    @Override
    public Identifier getAnimationResource(LandCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "animations/land_crab.animation.json");
    }
}
