package white_heket.more_crustacean.block.client.model;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import white_heket.more_crustacean.MoreCrustacean;
import white_heket.more_crustacean.block.entity.JewelleryChestBlockEntity;

public class JewelleryChestBlockModel extends GeoModel<JewelleryChestBlockEntity> {
    @Override
    public Identifier getModelResource(JewelleryChestBlockEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "geo/jewellery_chest.geo.json");
    }

    @Override
    public Identifier getTextureResource(JewelleryChestBlockEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "textures/block/jewellery_chest.png");
    }

    @Override
    public Identifier getAnimationResource(JewelleryChestBlockEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "animations/jewellery_chest.animation.json");
    }
}
