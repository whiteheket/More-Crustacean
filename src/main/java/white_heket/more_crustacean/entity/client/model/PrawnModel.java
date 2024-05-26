package white_heket.more_crustacean.entity.client.model;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import white_heket.more_crustacean.MoreCrustacean;
import white_heket.more_crustacean.entity.crustacean.xia.PrawnEntity;

public class PrawnModel extends GeoModel<PrawnEntity> {
    @Override
    public Identifier getModelResource(PrawnEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "geo/prawn.geo.json");
    }

    @Override
    public Identifier getTextureResource(PrawnEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "textures/entity/prawn.png");
    }

    @Override
    public Identifier getAnimationResource(PrawnEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "animations/prawn.animation.json");
    }
}
