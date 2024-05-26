package white_heket.more_crustacean.entity.client.model;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import white_heket.more_crustacean.MoreCrustacean;
import white_heket.more_crustacean.entity.crustacean.xia.LobsterEntity;

public class LobsterModel extends GeoModel<LobsterEntity> {
    @Override
    public Identifier getModelResource(LobsterEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "geo/lobster.geo.json");
    }

    @Override
    public Identifier getTextureResource(LobsterEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "textures/entity/lobster.png");
    }

    @Override
    public Identifier getAnimationResource(LobsterEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "animations/lobster.animation.json");
    }
}
