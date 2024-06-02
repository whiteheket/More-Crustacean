package white_heket.more_crustacean.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import white_heket.more_crustacean.MoreCrustacean;

public class ModFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> OYSTER_FEATURE_CONFIGURED = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(MoreCrustacean.MOD_ID, "oyster_feature"));
    public static RegistryKey<PlacedFeature> OYSTER_FEATURE_PLACED = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MoreCrustacean.MOD_ID, "oyster_feature"));
    public static void init(){
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.OCEAN, BiomeKeys.WARM_OCEAN, BiomeKeys.LUKEWARM_OCEAN), GenerationStep.Feature.VEGETAL_DECORATION, OYSTER_FEATURE_PLACED);
    }
}
