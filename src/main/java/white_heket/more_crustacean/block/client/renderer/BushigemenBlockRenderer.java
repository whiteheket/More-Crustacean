package white_heket.more_crustacean.block.client.renderer;


import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import white_heket.more_crustacean.block.client.model.BushigemenBlockModel;
import white_heket.more_crustacean.block.entity.BushigemenBlockEntity;

public class BushigemenBlockRenderer extends GeoBlockRenderer<BushigemenBlockEntity> {
    public BushigemenBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new BushigemenBlockModel());
    }
}
