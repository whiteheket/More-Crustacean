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
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import white_heket.more_crustacean.MoreCrustacean;
import white_heket.more_crustacean.block.block.BushigemenBlock;
import white_heket.more_crustacean.block.block.CrabPotBlock;
import white_heket.more_crustacean.block.block.JewelleryChestBlock;
import white_heket.more_crustacean.block.block.OysterBlock;
import white_heket.more_crustacean.block.blockitem.BushigemenBlockItem;
import white_heket.more_crustacean.block.blockitem.JewelleryChestBlockItem;
import white_heket.more_crustacean.block.entity.BushigemenBlockEntity;
import white_heket.more_crustacean.block.entity.CrabPotBlockEntity;
import white_heket.more_crustacean.block.entity.JewelleryChestBlockEntity;

public class ModBlocks {
    public static final Block CRAB_POT_BLOCK = registerBlock("crab_pot",new CrabPotBlock(FabricBlockSettings.copyOf(Blocks.CHEST)));
    public static final Block OYSTER_BLOCK = registerBlock("oyster_block",new OysterBlock());
    public static final Block BUSHIGEMEN_BLOCK = registerBlock("bushigemen", new BushigemenBlock(FabricBlockSettings.create().nonOpaque().strength(0.1F).noCollision().sounds(BlockSoundGroup.WOOL)));
    public static final Block JEWELLERY_CHEST_BLOCK = registerBlock("jewellery_chest", new JewelleryChestBlock(FabricBlockSettings.create().nonOpaque().strength(6.0F).sounds(BlockSoundGroup.WOOD)));
    public static final BlockItem CRAB_POT_BLOCK_ITEM =registerBlockItem("crab_pot",new BlockItem(CRAB_POT_BLOCK, new Item.Settings()));
    public static final BlockItem OYSTER_BLOCK_ITEM = registerBlockItem("oyster_block",new BlockItem(OYSTER_BLOCK,new Item.Settings()));
    public static final BlockItem BUSHIGEMEN_BLOCK_ITEM = registerBlockItem("bushigemen",new BushigemenBlockItem(BUSHIGEMEN_BLOCK,new Item.Settings()));
    public static final BlockItem JEWELLERY_CHEST_BLOCK_ITEM = registerBlockItem("jewellery_chest", new JewelleryChestBlockItem(JEWELLERY_CHEST_BLOCK,new Item.Settings()));
    public static final BlockEntityType<CrabPotBlockEntity> CRAB_POT_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MoreCrustacean.MOD_ID,"crab_pot"),FabricBlockEntityTypeBuilder.create(CrabPotBlockEntity::new,CRAB_POT_BLOCK).build());
    public static final BlockEntityType<BushigemenBlockEntity> BUSHIGEMEN_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MoreCrustacean.MOD_ID,"bushigemen"),FabricBlockEntityTypeBuilder.create(BushigemenBlockEntity::new,BUSHIGEMEN_BLOCK).build());
    public static final BlockEntityType<JewelleryChestBlockEntity> JEWELLERY_CHEST_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MoreCrustacean.MOD_ID,"jewellery_chest"),FabricBlockEntityTypeBuilder.create(JewelleryChestBlockEntity::new,JEWELLERY_CHEST_BLOCK).build());

    private static Block registerBlock(String name,Block block){
        return Registry.register(Registries.BLOCK,new Identifier(MoreCrustacean.MOD_ID,name),block);
    }
    private static BlockItem registerBlockItem(String name,BlockItem item){
        return Registry.register(Registries.ITEM,new Identifier(MoreCrustacean.MOD_ID,name),item);
    }
    public static void init(){

    }
}
