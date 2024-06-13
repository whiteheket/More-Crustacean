package white_heket.more_crustacean.block.client.model;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import white_heket.more_crustacean.MoreCrustacean;
import white_heket.more_crustacean.block.entity.BushigemenBlockEntity;

public class BushigemenBlockModel extends GeoModel<BushigemenBlockEntity> {
    @Override
    public Identifier getModelResource(BushigemenBlockEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "geo/bushigemen.geo.json");
    }

    @Override
    public Identifier getTextureResource(BushigemenBlockEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "textures/block/bushigemen.png");
    }

    @Override
    public Identifier getAnimationResource(BushigemenBlockEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "animations/bushigemen.animation.json");
    }
}
