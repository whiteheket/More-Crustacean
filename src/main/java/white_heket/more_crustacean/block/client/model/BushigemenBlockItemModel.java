package white_heket.more_crustacean.block.client.model;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import white_heket.more_crustacean.MoreCrustacean;
import white_heket.more_crustacean.block.blockitem.BushigemenBlockItem;

public class BushigemenBlockItemModel extends GeoModel<BushigemenBlockItem> {
    @Override
    public Identifier getModelResource(BushigemenBlockItem animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "geo/bushigemen.geo.json");
    }

    @Override
    public Identifier getTextureResource(BushigemenBlockItem animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "textures/block/bushigemen.png");
    }

    @Override
    public Identifier getAnimationResource(BushigemenBlockItem animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "animations/bushigemen.animation.json");
    }
}
