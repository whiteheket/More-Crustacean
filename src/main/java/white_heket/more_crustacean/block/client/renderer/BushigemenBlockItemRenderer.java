package white_heket.more_crustacean.block.client.renderer;

import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import white_heket.more_crustacean.block.blockitem.BushigemenBlockItem;
import white_heket.more_crustacean.block.client.model.BushigemenBlockItemModel;

public class BushigemenBlockItemRenderer extends GeoItemRenderer<BushigemenBlockItem> {
    public BushigemenBlockItemRenderer() {
        super(new BushigemenBlockItemModel());
    }
}
