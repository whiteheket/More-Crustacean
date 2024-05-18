package white_heket.more_crustacean;

import white_heket.more_crustacean.effect.ModEffects;
import white_heket.more_crustacean.effect.ModPotion;
import white_heket.more_crustacean.entity.ModEntities;
import white_heket.more_crustacean.entity.ModEntitySpawn;
import white_heket.more_crustacean.event.VillagerTrade;
import white_heket.more_crustacean.item.ComposterItems;
import white_heket.more_crustacean.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

public class MoreCrustacean implements ModInitializer {
	public static final String MOD_ID = "more_crustacean_whiteheket";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		LOGGER.info("螃蟹螃蟹龙虾龙虾");
		GeckoLib.initialize();
		ModEntities.entityAttributes();
		ModItems.init();
		ModEntitySpawn.init();
		ModEffects.init();
		ModPotion.registerBrewingRecipe();
		VillagerTrade.init();
		ComposterItems.registerDefaultCompostableItems();
	}
}