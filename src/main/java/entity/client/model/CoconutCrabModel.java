package entity.client.model;

import entity.crustacean.crab.CoconutCrabEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import white_heket.more_crustacean.MoreCrustacean;

public class CoconutCrabModel extends GeoModel<CoconutCrabEntity> {
    @Override
    public Identifier getModelResource(CoconutCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID,"geo/coconut_crab.geo.json");
    }

    @Override
    public Identifier getTextureResource(CoconutCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID,"textures/entity/coconut_crab.png");
    }

    @Override
    public Identifier getAnimationResource(CoconutCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID,"animations/coconut_crab.animation.json");
    }
}
