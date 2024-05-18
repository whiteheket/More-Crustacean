package white_heket.more_crustacean.tag;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import white_heket.more_crustacean.MoreCrustacean;

public class MoreCrustaceanItemTags {
    public static final TagKey<Item> CRAB_ITEMS = TagKey.of(RegistryKeys.ITEM, new Identifier(MoreCrustacean.MOD_ID, "crabs"));
}
