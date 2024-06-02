package white_heket.more_crustacean.event;

import white_heket.more_crustacean.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public class VillagerTrade{
    public static void init() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 2), new ItemStack(ModItems.PRAWN_BUCKET, 1), 16, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3), new ItemStack(ModItems.CRAYFISH_BUCKET, 1), 16, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 8), new ItemStack(ModItems.LOBSTER_BUCKET, 1), 16, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 6), new ItemStack(ModItems.BROWN_CRAB_BUCKET, 1), 16, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 6), new ItemStack(ModItems.SWIMMER_CRAB_BUCKET, 1), 16, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 6), new ItemStack(ModItems.HAIRY_CRAB_BUCKET, 1), 16, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),new ItemStack(ModItems.BROWN_CRAB,6), new ItemStack(ModItems.COOKED_BROWN_CRAB, 6), 16, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),new ItemStack(ModItems.SWIMMER_CRAB,6), new ItemStack(ModItems.COOKED_SWIMMER_CRAB, 6), 16, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),new ItemStack(ModItems.HAIRY_CRAB,6), new ItemStack(ModItems.COOKED_HAIRY_CRAB, 6), 16, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 2),new ItemStack(ModItems.LOBSTER,6), new ItemStack(ModItems.COOKED_LOBSTER, 6), 16, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),new ItemStack(ModItems.PRAWN,20), new ItemStack(ModItems.COOKED_PRAWN, 20), 16, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 2),new ItemStack(ModItems.LOBSTER,6), new ItemStack(ModItems.COOKED_LOBSTER, 6), 16, 5, 0.05f));
                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 2,
                factories -> {

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 15), new ItemStack(ModItems.KING_CRAB_BUCKET, 1), 16, 15, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 15), new ItemStack(ModItems.GIANT_MUD_CRAB, 1), 16, 15, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 10), new ItemStack(ModItems.SAND_CRAB_BUCKET, 1), 16, 15, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 5), new ItemStack(ModItems.CLAWSTER, 30),  new ItemStack(ModItems.COOKED_CLAWSTER, 30),16, 15, 0.05f));
                });
    }
}
