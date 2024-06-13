package white_heket.more_crustacean.item;

import net.minecraft.item.ItemConvertible;
import white_heket.more_crustacean.MoreCrustacean;

import static net.minecraft.block.ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE;

public class ComposterItems {
    private static void registerCompostableItem(float levelIncreaseChance, ItemConvertible item) {
        ITEM_TO_LEVEL_INCREASE_CHANCE.put(item.asItem(), levelIncreaseChance);
    }

    public static void registerDefaultCompostableItems() {
        registerCompostableItem(0.3F, ModItems.BROWN_CRAB);
        registerCompostableItem(0.3F, ModItems.SWIMMER_CRAB);
        registerCompostableItem(0.6F, ModItems.KING_CRAB);
        registerCompostableItem(0.5F, ModItems.GIANT_MUD_CRAB);
        registerCompostableItem(0.3F, ModItems.HAIRY_CRAB);
        registerCompostableItem(0.5F, ModItems.CLAWSTER);
        registerCompostableItem(0.4F, ModItems.LOBSTER);
        registerCompostableItem(0.1F, ModItems.PRAWN);
        registerCompostableItem(0.2F,ModItems.CRAYFISH);
        registerCompostableItem(0.3F, ModItems.COOKED_BROWN_CRAB);
        registerCompostableItem(0.3F, ModItems.COOKED_SWIMMER_CRAB);
        registerCompostableItem(0.6F, ModItems.COOKED_KING_CRAB);
        registerCompostableItem(0.5F, ModItems.COOKED_GIANT_MUD_CRAB);
        registerCompostableItem(0.3F, ModItems.COOKED_HAIRY_CRAB);
        registerCompostableItem(0.5F, ModItems.COOKED_CLAWSTER);
        registerCompostableItem(0.4F, ModItems.COOKED_LOBSTER);
        registerCompostableItem(0.1F, ModItems.COOKED_PRAWN);
        registerCompostableItem(0.2F,ModItems.COOKED_CRAYFISH);
        registerCompostableItem(0.1F, ModItems.CRAB_MEAT);
        registerCompostableItem(0.1F, ModItems.COOKED_CRAB_MEAT);

    }
}
