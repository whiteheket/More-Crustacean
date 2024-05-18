package white_heket.more_crustacean.entity.client.model;

import white_heket.more_crustacean.entity.crustacean.crab.HairyCrabEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import white_heket.more_crustacean.MoreCrustacean;

public class HairyCrabModel extends GeoModel<HairyCrabEntity> {
    @Override
    public Identifier getModelResource(HairyCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID,"geo/hairy_crab.geo.json");
    }

    @Override
    public Identifier getTextureResource(HairyCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID,"textures/entity/hairy_crab.png");
    }

    @Override
    public Identifier getAnimationResource(HairyCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID,"animations/hairy_crab.animation.json");
    }
}
