package white_heket.more_crustacean.entity.client.model;

import white_heket.more_crustacean.entity.crustacean.crab.SwimmerCrabEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import white_heket.more_crustacean.MoreCrustacean;

public class SwimmerCrabModel extends GeoModel<SwimmerCrabEntity> {

    @Override
    public Identifier getModelResource(SwimmerCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID,"geo/swimmer_crab.geo.json");
    }

    @Override
    public Identifier getTextureResource(SwimmerCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID,"textures/entity/swimmer_crab.png");
    }

    @Override
    public Identifier getAnimationResource(SwimmerCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID,"animations/swimmer_crab.animation.json");
    }
}
