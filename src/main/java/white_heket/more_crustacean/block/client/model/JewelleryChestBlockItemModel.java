package white_heket.more_crustacean.block.client.model;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import white_heket.more_crustacean.MoreCrustacean;
import white_heket.more_crustacean.block.blockitem.BushigemenBlockItem;
import white_heket.more_crustacean.block.blockitem.JewelleryChestBlockItem;
import white_heket.more_crustacean.block.entity.JewelleryChestBlockEntity;

public class JewelleryChestBlockItemModel extends GeoModel<JewelleryChestBlockItem> {
    @Override
    public Identifier getModelResource(JewelleryChestBlockItem animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "geo/jewellery_chest.geo.json");
    }

    @Override
    public Identifier getTextureResource(JewelleryChestBlockItem animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "textures/block/jewellery_chest.png");
    }

    @Override
    public Identifier getAnimationResource(JewelleryChestBlockItem animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "animations/jewellery_chest.animation.json");
    }
}
