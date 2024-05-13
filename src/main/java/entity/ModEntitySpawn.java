package entity;

import entity.crustacean.crab.*;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawn {
    //注册生物的生成群系和生成条件
    public static void init(){
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.COLD_OCEAN,BiomeKeys.DEEP_COLD_OCEAN,BiomeKeys.OCEAN), SpawnGroup.WATER_AMBIENT,ModEntities.BROWN_CRAB,7,3,5);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.WARM_OCEAN,BiomeKeys.LUKEWARM_OCEAN),SpawnGroup.WATER_AMBIENT,ModEntities.SWIMMER_CRAB,7,2,5);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.RIVER),SpawnGroup.WATER_AMBIENT,ModEntities.HAIRY_CRAB,7,2,5);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.MANGROVE_SWAMP),SpawnGroup.WATER_AMBIENT,ModEntities.GIANT_MUD_CRAB,3,2,2);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.COLD_OCEAN,BiomeKeys.DEEP_COLD_OCEAN),SpawnGroup.WATER_AMBIENT,ModEntities.KING_CRAB,5,3,5);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.BEACH),SpawnGroup.CREATURE,ModEntities.COCONUT_CRAB,5,1,3);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.BEACH),SpawnGroup.WATER_CREATURE,ModEntities.SAND_CRAB,10,3,6);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.JUNGLE,BiomeKeys.SPARSE_JUNGLE),SpawnGroup.WATER_CREATURE,ModEntities.LAND_CRAB,3,2,3);
        SpawnRestriction.register(ModEntities.BROWN_CRAB, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, BrownCrabEntity::canSpawn);
        SpawnRestriction.register(ModEntities.SWIMMER_CRAB,SpawnRestriction.Location.IN_WATER,Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SwimmerCrabEntity::canSpawn);
        SpawnRestriction.register(ModEntities.HAIRY_CRAB,SpawnRestriction.Location.IN_WATER,Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HairyCrabEntity::canSpawn);
        SpawnRestriction.register(ModEntities.GIANT_MUD_CRAB,SpawnRestriction.Location.NO_RESTRICTIONS,Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GiantMudCrabEntity::canSpawn);
        SpawnRestriction.register(ModEntities.KING_CRAB,SpawnRestriction.Location.IN_WATER,Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, KingCrabEntity::canSpawn);
        SpawnRestriction.register(ModEntities.COCONUT_CRAB,SpawnRestriction.Location.NO_RESTRICTIONS,Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, CoconutCrabEntity::canSpawn);
        SpawnRestriction.register(ModEntities.SAND_CRAB,SpawnRestriction.Location.NO_RESTRICTIONS,Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SandCrabEntity::canSpawn);
        SpawnRestriction.register(ModEntities.LAND_CRAB,SpawnRestriction.Location.ON_GROUND,Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LandCrabEntity::canSpawn);
    }
}
