package white_heket.more_crustacean;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import white_heket.more_crustacean.block.ModBlocks;
import white_heket.more_crustacean.block.client.renderer.BushigemenBlockRenderer;
import white_heket.more_crustacean.block.client.renderer.JewelleryChestBlockRenderer;
import white_heket.more_crustacean.block.entity.BushigemenBlockEntity;
import white_heket.more_crustacean.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import white_heket.more_crustacean.entity.client.renderer.*;

public class MoreCrustaceanClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.BROWN_CRAB, BrownCrabRenderer::new);
        EntityRendererRegistry.register(ModEntities.SWIMMER_CRAB, SwimmerCrabRenderer::new);
        EntityRendererRegistry.register(ModEntities.HAIRY_CRAB, HairyCrabRenderer::new);
        EntityRendererRegistry.register(ModEntities.GIANT_MUD_CRAB, GiantMudCrabRenderer::new);
        EntityRendererRegistry.register(ModEntities.KING_CRAB, KingCrabRenderer::new);
        EntityRendererRegistry.register(ModEntities.COCONUT_CRAB,CoconutCrabRenderer::new);
        EntityRendererRegistry.register(ModEntities.SAND_CRAB, SandCrabRenderer::new);
        EntityRendererRegistry.register(ModEntities.LAND_CRAB,LandCrabRenderer::new);
        EntityRendererRegistry.register(ModEntities.CLAWSTER, ClawsterRenderer::new);
        EntityRendererRegistry.register(ModEntities.LOBSTER,LobsterRenderer::new);
        EntityRendererRegistry.register(ModEntities.PRAWN,PrawnRenderer::new);
        EntityRendererRegistry.register(ModEntities.CRAYFISH,CrayfishRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRAB_POT_BLOCK, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BUSHIGEMEN_BLOCK, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JEWELLERY_CHEST_BLOCK, RenderLayer.getCutoutMipped());
        BlockEntityRendererFactories.register(ModBlocks.BUSHIGEMEN_BLOCK_ENTITY,BushigemenBlockRenderer::new);
        BlockEntityRendererFactories.register(ModBlocks.JEWELLERY_CHEST_BLOCK_ENTITY, JewelleryChestBlockRenderer::new);
    }
}
