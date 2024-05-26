package white_heket.more_crustacean.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import white_heket.more_crustacean.MoreCrustacean;
import white_heket.more_crustacean.block.entity.CrabPotBlockEntity;

public class ModBlocks {
    public static final Block CRAB_POT_BLOCK = Registry.register(Registries.BLOCK, new Identifier(MoreCrustacean.MOD_ID,"crab_pot"), new CrabPotBlock(FabricBlockSettings.copyOf(Blocks.CHEST)));;
    public static final BlockItem CRAB_POT_BLOCK_ITEM = Registry.register(Registries.ITEM, new Identifier(MoreCrustacean.MOD_ID,"crab_pot"), new BlockItem(CRAB_POT_BLOCK, new Item.Settings()));;
    public static final BlockEntityType<CrabPotBlockEntity> CRAB_POT_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MoreCrustacean.MOD_ID,"crab_pot"),FabricBlockEntityTypeBuilder.create(CrabPotBlockEntity::new,CRAB_POT_BLOCK).build());
    public static void init(){

    }
}
