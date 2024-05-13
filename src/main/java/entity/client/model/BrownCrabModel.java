package entity.client.model;

import entity.crustacean.crab.BrownCrabEntity;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import white_heket.more_crustacean.MoreCrustacean;

public class BrownCrabModel extends GeoModel<BrownCrabEntity> {
    @Override
    public Identifier getModelResource(BrownCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "geo/brown_crab.geo.json");
    }

    @Override
    public Identifier getTextureResource(BrownCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "textures/entity/brown_crab.png");
    }

    @Override
    public Identifier getAnimationResource(BrownCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID,"animations/brown_crab.animation.json");
    }


}
