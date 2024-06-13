package white_heket.more_crustacean.block.client.renderer;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import white_heket.more_crustacean.block.client.model.JewelleryChestBlockModel;
import white_heket.more_crustacean.block.entity.JewelleryChestBlockEntity;

public class JewelleryChestBlockRenderer extends GeoBlockRenderer<JewelleryChestBlockEntity> {
    public JewelleryChestBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new JewelleryChestBlockModel());
    }
}
