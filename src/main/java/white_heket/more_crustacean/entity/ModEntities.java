package white_heket.more_crustacean.entity;

import white_heket.more_crustacean.entity.crustacean.xia.ClawsterEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import white_heket.more_crustacean.MoreCrustacean;
import white_heket.more_crustacean.entity.crustacean.crab.*;
import white_heket.more_crustacean.entity.crustacean.xia.LobsterEntity;
import white_heket.more_crustacean.entity.crustacean.xia.PrawnEntity;

public class ModEntities {
    //注册生物类型
    public static final EntityType<BrownCrabEntity> BROWN_CRAB = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MoreCrustacean.MOD_ID,"brown_crab"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE,BrownCrabEntity::new).dimensions(EntityDimensions.fixed(0.7f,0.5f)).build());
    public static final EntityType<SwimmerCrabEntity> SWIMMER_CRAB =Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MoreCrustacean.MOD_ID,"swimmer_crab"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE,SwimmerCrabEntity::new).dimensions(EntityDimensions.fixed(0.7f,0.5f)).build());
    public static final EntityType<HairyCrabEntity> HAIRY_CRAB =Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MoreCrustacean.MOD_ID,"hairy_crab"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE,HairyCrabEntity::new).dimensions(EntityDimensions.fixed(0.7f,0.5f)).build());
    public static final EntityType<GiantMudCrabEntity> GIANT_MUD_CRAB =Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MoreCrustacean.MOD_ID,"giant_mud_crab"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE,GiantMudCrabEntity::new).dimensions(EntityDimensions.fixed(0.9f,0.6f)).build());
    public static final EntityType<KingCrabEntity> KING_CRAB =Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MoreCrustacean.MOD_ID,"king_crab"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, KingCrabEntity::new).dimensions(EntityDimensions.fixed(1.0f,0.8f)).build());
    public static final EntityType<CoconutCrabEntity> COCONUT_CRAB =Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MoreCrustacean.MOD_ID,"coconut_crab"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, CoconutCrabEntity::new).dimensions(EntityDimensions.fixed(0.9f,0.7f)).build());
    public static final EntityType<SandCrabEntity> SAND_CRAB =Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MoreCrustacean.MOD_ID,"sand_crab"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, SandCrabEntity::new).dimensions(EntityDimensions.fixed(0.4f,0.2f)).build());
    public static final EntityType<LandCrabEntity> LAND_CRAB =Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MoreCrustacean.MOD_ID,"land_crab"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, LandCrabEntity::new).dimensions(EntityDimensions.fixed(0.9f,0.5f)).build());
    public static final EntityType<ClawsterEntity> CLAWSTER =Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MoreCrustacean.MOD_ID,"clawster"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, ClawsterEntity::new).dimensions(EntityDimensions.fixed(0.8f,0.6f)).build());
    public static final EntityType<LobsterEntity> LOBSTER =Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MoreCrustacean.MOD_ID,"lobster"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, LobsterEntity::new).dimensions(EntityDimensions.fixed(0.7f,0.6f)).build());
    public static final EntityType<PrawnEntity> PRAWN =Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MoreCrustacean.MOD_ID,"prawn"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, PrawnEntity::new).dimensions(EntityDimensions.fixed(0.5f,0.3f)).build());
    //注册生物属性
    public static void entityAttributes(){
        FabricDefaultAttributeRegistry.register(ModEntities.BROWN_CRAB, BrownCrabEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.SWIMMER_CRAB, SwimmerCrabEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.HAIRY_CRAB, HairyCrabEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.GIANT_MUD_CRAB, GiantMudCrabEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.KING_CRAB, KingCrabEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.COCONUT_CRAB, CoconutCrabEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.SAND_CRAB, SandCrabEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.LAND_CRAB,LandCrabEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.CLAWSTER,ClawsterEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.LOBSTER,LobsterEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.PRAWN,PrawnEntity.setAttributes());
    }
}
