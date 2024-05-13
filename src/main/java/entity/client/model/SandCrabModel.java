package entity.client.model;

import entity.crustacean.crab.SandCrabEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import white_heket.more_crustacean.MoreCrustacean;

public class SandCrabModel extends GeoModel<SandCrabEntity> {
    @Override
    public Identifier getModelResource(SandCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "geo/sand_crab.geo.json");
    }

    @Override
    public Identifier getTextureResource(SandCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID, "textures/entity/sand_crab.png");
    }

    @Override
    public Identifier getAnimationResource(SandCrabEntity animatable) {
        return new Identifier(MoreCrustacean.MOD_ID,"animations/sand_crab.animation.json");
    }
}
