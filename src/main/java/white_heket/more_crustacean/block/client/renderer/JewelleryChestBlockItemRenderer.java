package white_heket.more_crustacean.block.client.renderer;

import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import white_heket.more_crustacean.block.blockitem.JewelleryChestBlockItem;
import white_heket.more_crustacean.block.client.model.JewelleryChestBlockItemModel;

public class JewelleryChestBlockItemRenderer extends GeoItemRenderer<JewelleryChestBlockItem> {
    public JewelleryChestBlockItemRenderer() {
        super(new JewelleryChestBlockItemModel());
    }
}
