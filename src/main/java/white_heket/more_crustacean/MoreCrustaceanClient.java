package white_heket.more_crustacean;

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
    }
}
