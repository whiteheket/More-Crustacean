package white_heket.more_crustacean.entity.client.model;

import white_heket.more_crustacean.entity.crustacean.crab.KingCrabEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import white_heket.more_crustacean.MoreCrustacean;

public class KingCrabModel extends GeoModel<KingCrabEntity> {
    @Override
    public Identifier getModelResource(KingCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID,"geo/king_crab.geo.json");
    }

    @Override
    public Identifier getTextureResource(KingCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID,"textures/entity/king_crab.png");
    }

    @Override
    public Identifier getAnimationResource(KingCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID,"animations/king_crab.animation.json");
    }
}
