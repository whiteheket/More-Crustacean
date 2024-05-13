package tag;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import white_heket.more_crustacean.MoreCrustacean;

public class MoreCrustaceanBlockTags {
    public static final TagKey<Block> CRAB_DIGGABLE_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(MoreCrustacean.MOD_ID, "crab_diggable_blocks"));
    public static final TagKey<Block> CRAB_SPAWN_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier(MoreCrustacean.MOD_ID, "crab_spawn_blocks"));
}
