package white_heket.more_crustacean.event;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import white_heket.more_crustacean.block.ModBlocks;

public class LootModifier {
    private static final Identifier ELDER_GUARDIAN_ID = EntityType.ELDER_GUARDIAN.getLootTableId();
    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && ELDER_GUARDIAN_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .with(ItemEntry.builder(ModBlocks.JEWELLERY_CHEST_BLOCK_ITEM));
                tableBuilder.pool(poolBuilder);
            }
        });
    }

}
