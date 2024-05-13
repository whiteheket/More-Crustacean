package white_heket.more_crustacean;

import effect.ModEffects;
import effect.ModPotion;
import entity.ModEntities;
import entity.ModEntitySpawn;
import event.VillagerTrade;
import item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

public class MoreCrustacean implements ModInitializer {
	public static final String MOD_ID = "more_crustacean_whiteheket";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
		GeckoLib.initialize();
		ModEntities.entityAttributes();
		ModItems.init();
		ModEntitySpawn.init();
		ModEffects.init();
		ModPotion.registerBrewingRecipe();
		VillagerTrade.init();
	}
}