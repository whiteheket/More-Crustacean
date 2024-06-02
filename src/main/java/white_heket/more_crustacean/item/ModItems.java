package white_heket.more_crustacean.item;

import net.minecraft.util.Nameable;
import white_heket.more_crustacean.block.ModBlocks;
import white_heket.more_crustacean.entity.ModEntities;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import white_heket.more_crustacean.MoreCrustacean;
import white_heket.more_crustacean.item.food.ShellFishItem;


import java.util.LinkedHashMap;
import java.util.Map;

public class ModItems {
    //存储项目的标识符和对应的项
    public static final Map<Identifier, Item> ITEMS = new LinkedHashMap<>();
    //注册物品组
    public static final RegistryKey<ItemGroup> MORE_CRUSTACEAN = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MoreCrustacean.MOD_ID,"item_group"));
    //注册物品
    //刷怪蛋
    public static final Item BROWN_CRAB_SPAWN_EGG = register("brown_crab_spawn_egg",new SpawnEggItem(ModEntities.BROWN_CRAB,0xa7684a,0xedcfc2,new FabricItemSettings()));
    public static final Item SWIMMER_CRAB_SPAWN_EGG = register("swimmer_crab_spawn_egg",new SpawnEggItem(ModEntities.SWIMMER_CRAB,0x6b6952,0x23529f,new FabricItemSettings()));
    public static final Item HAIRY_CRAB_SPAWN_EGG = register("hairy_crab_spawn_egg",new SpawnEggItem(ModEntities.HAIRY_CRAB,0x3d3b33,0x837751,new FabricItemSettings()));
    public static final Item GIANT_MUD_CRAB_SPAWN_EGG = register("giant_mud_crab_spawn_egg",new SpawnEggItem(ModEntities.GIANT_MUD_CRAB,0x444722,0x5f2d2d,new FabricItemSettings()));
    public static final Item KING_CRAB_SPAWN_EGG = register("king_crab_spawn_egg", new SpawnEggItem(ModEntities.KING_CRAB,0x40191b,0xf9f4e9,new FabricItemSettings()));
    public static final Item COCONUT_CRAB_SPAWN_EGG = register("coconut_crab_spawn_egg", new SpawnEggItem(ModEntities.COCONUT_CRAB,0x251e21,0x598fb6,new FabricItemSettings()));
    public static final Item SAND_CRAB_SPAWN_EGG = register("sand_crab_spawn_egg", new SpawnEggItem(ModEntities.SAND_CRAB,0xc6d2cb,0x291616,new FabricItemSettings()));
    public static final Item LAND_CRAB_SPAWN_EGG = register("land_crab_spawn_egg", new SpawnEggItem(ModEntities.LAND_CRAB,0x3f5589,0xf3e8e9,new FabricItemSettings()));
    public static final Item CLAWSTER_SPAWN_EGG = register("clawster_spawn_egg", new SpawnEggItem(ModEntities.CLAWSTER,0x9a4e26,0x583726,new FabricItemSettings()));
    public static final Item LOBSTER_SPAWN_EGG = register("lobster_spawn_egg", new SpawnEggItem(ModEntities.LOBSTER,0x6d3307,0x3c030f,new FabricItemSettings()));
    public static final Item PRAWN_SPAWN_EGG = register("prawn_spawn_egg", new SpawnEggItem(ModEntities.PRAWN,0xefecec,0xecd5d5,new FabricItemSettings()));
    public static final Item CRAYFISH_SPAWN_EGG = register("crayfish_spawn_egg", new SpawnEggItem(ModEntities.CRAYFISH,0x671620,0x2e181b,new FabricItemSettings()));

    //药水原料或其他用途
    public static final Item CRAB_SHELL = register("crab_shell",new Item(new Item.Settings()));
    public static final Item BLUE_LAND_CRAB_CLAW = register("blue_land_crab_claw",new Item(new Item.Settings()));
    public static final Item SHELL = register("shell",new Item(new Item.Settings()));
    public static final Item PEARL = register("pearl",new Item(new Item.Settings()));
    //食物
    public static final Item CRAB_MEAT = register("crab_meat",new Item((new Item.Settings()).food(ModFoodComponents.CRAB_MEAT)));
    public static final Item COOKED_CRAB_MEAT = register("cooked_crab_meat",new Item((new Item.Settings()).food(ModFoodComponents.COOKED_CRAB_MEAT)));
    public static final Item CRAB_BUTTER =register("crab_butter",new Item((new Item.Settings()).food(ModFoodComponents.CRAB_BUTTER)));
    public static final Item BROWN_CRAB = register("brown_crab",new Item((new Item.Settings()).food(ModFoodComponents.BROWN_CRAB)));
    public static final Item SWIMMER_CRAB = register("swimmer_crab",new Item((new Item.Settings()).food(ModFoodComponents.SWIMMER_CRAB)));
    public static final Item HAIRY_CRAB = register("hairy_crab",new Item((new Item.Settings()).food(ModFoodComponents.HAIRY_CRAB)));
    public static final Item GIANT_MUD_CRAB = register("giant_mud_crab",new Item((new Item.Settings()).food(ModFoodComponents.GIANT_MUD_CRAB)));
    public static final Item KING_CRAB = register("king_crab",new Item((new Item.Settings()).food(ModFoodComponents.KING_CRAB)));
    public static final Item CLAWSTER = register("clawster",new Item((new Item.Settings()).food(ModFoodComponents.CLAWSTER)));
    public static final Item LOBSTER = register("lobster",new Item((new Item.Settings()).food(ModFoodComponents.LOBSTER)));
    public static final Item PRAWN = register("prawn",new Item((new Item.Settings()).food(ModFoodComponents.CRAB_MEAT)));
    public static final Item CRAYFISH = register("crayfish",new Item((new Item.Settings()).food(ModFoodComponents.CRAB_MEAT)));
    public static final Item OYSTER = register("oyster",new ShellFishItem((new Item.Settings()).food(ModFoodComponents.OYSTER)));
    public static final Item CLAM = register("clam",new Item(new Item.Settings()));
    public static final Item COCKLE = register("cockle",new Item(new Item.Settings()));
    public static final Item COOKED_BROWN_CRAB = register("cooked_brown_crab",new Item((new Item.Settings()).food(ModFoodComponents.COOKED_BROWN_CRAB)));
    public static final Item COOKED_SWIMMER_CRAB = register("cooked_swimmer_crab",new Item((new Item.Settings()).food(ModFoodComponents.COOKED_SWIMMER_CRAB)));
    public static final Item COOKED_HAIRY_CRAB = register("cooked_hairy_crab",new Item((new Item.Settings()).food(ModFoodComponents.COOKED_HAIRY_CRAB)));
    public static final Item COOKED_GIANT_MUD_CRAB = register("cooked_giant_mud_crab",new Item((new Item.Settings()).food(ModFoodComponents.COOKED_GIANT_MUD_CRAB)));
    public static final Item COOKED_KING_CRAB = register("cooked_king_crab",new Item((new Item.Settings()).food(ModFoodComponents.COOKED_KING_CRAB)));
    public static final Item COOKED_CLAWSTER = register("cooked_clawster",new Item((new Item.Settings()).food(ModFoodComponents.COOKED_CLAWSTER)));
    public static final Item COOKED_LOBSTER = register("cooked_lobster",new Item((new Item.Settings()).food(ModFoodComponents.COOKED_LOBSTER)));
    public static final Item COOKED_PRAWN = register("cooked_prawn",new Item((new Item.Settings()).food(ModFoodComponents.COOKED_CRAB_MEAT)));
    public static final Item COOKED_CRAYFISH = register("cooked_crayfish",new Item((new Item.Settings()).food(ModFoodComponents.COOKED_CRAB_MEAT)));
    //菜谱
    public static final StewItem CLAM_CHOWDER = register("clam_chowder",new StewItem((new Item.Settings()).food(ModFoodComponents.CLAM_CHOWDER)));

    //桶装
    public static final BucketItem BROWN_CRAB_BUCKET = register("brown_crab_bucket", new EntityBucketItem(ModEntities.BROWN_CRAB, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH,(new Item.Settings()).maxCount(1)));
    public static final BucketItem SWIMMER_CRAB_BUCKET = register("swimmer_crab_bucket", new EntityBucketItem(ModEntities.SWIMMER_CRAB, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH,(new Item.Settings()).maxCount(1)));
    public static final BucketItem HAIRY_CRAB_BUCKET = register("hairy_crab_bucket", new EntityBucketItem(ModEntities.HAIRY_CRAB, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH,(new Item.Settings()).maxCount(1)));
    public static final BucketItem GIANT_MUD_CRAB_BUCKET = register("giant_mud_crab_bucket", new EntityBucketItem(ModEntities.GIANT_MUD_CRAB, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH,(new Item.Settings()).maxCount(1)));
    public static final BucketItem KING_CRAB_BUCKET = register("king_crab_bucket", new EntityBucketItem(ModEntities.KING_CRAB, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH,(new Item.Settings()).maxCount(1)));
   //已弃用，因为关氏圆轴蟹e和椰子蟹不会游泳
   // public static final BucketItem COCONUT_CRAB_BUCKET = register("coconut_crab_bucket", new EntityBucketItem(ModEntities.COCONUT_CRAB, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH,(new Item.Settings()).maxCount(1)));
    public static final BucketItem SAND_CRAB_BUCKET = register("sand_crab_bucket", new EntityBucketItem(ModEntities.SAND_CRAB, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH,(new Item.Settings()).maxCount(1)));
   //BANNED public static final BucketItem LAND_CRAB_BUCKET = register("land_crab_bucket", new EntityBucketItem(ModEntities.LAND_CRAB, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH,(new Item.Settings()).maxCount(1)));
    public static final BucketItem CLAWSTER_BUCKET = register("clawster_bucket", new EntityBucketItem(ModEntities.CLAWSTER, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH,(new Item.Settings()).maxCount(1)));
    public static final BucketItem LOBSTER_BUCKET = register("lobster_bucket", new EntityBucketItem(ModEntities.LOBSTER, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH,(new Item.Settings()).maxCount(1)));
    public static final BucketItem PRAWN_BUCKET = register("prawn_bucket", new EntityBucketItem(ModEntities.PRAWN, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH,(new Item.Settings()).maxCount(1)));
    public static final BucketItem CRAYFISH_BUCKET = register("crayfish_bucket", new EntityBucketItem(ModEntities.CRAYFISH, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH,(new Item.Settings()).maxCount(1)));
    //调用这个方法便可以将项目注册到map中
    public static <I extends Item> I register(String name, I item) {
        ITEMS.put(new Identifier(MoreCrustacean.MOD_ID, name), item);
        return item;
    }
    public static void init(){
            Registry.register(Registries.ITEM_GROUP,MORE_CRUSTACEAN, FabricItemGroup.builder().icon(()-> new ItemStack(ModItems.BROWN_CRAB)).displayName(Text.translatable("item.more_crustacean.item_group")).build());
        for (Identifier id : ITEMS.keySet()) {
            Registry.register(Registries.ITEM, id, ITEMS.get(id));
            ItemGroupEvents.modifyEntriesEvent(MORE_CRUSTACEAN).register(content ->content.add(ModBlocks.CRAB_POT_BLOCK_ITEM));
            ItemGroupEvents.modifyEntriesEvent(MORE_CRUSTACEAN).register(content ->content.add(ModBlocks.OYSTER_BLOCK_ITEM));
            ItemGroupEvents.modifyEntriesEvent(MORE_CRUSTACEAN).register(entries -> entries.add(ITEMS.get(id)));
        }
    }
}
