package white_heket.more_crustacean.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.screen.Generic3x3ContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.BiomeAccess;
import org.jetbrains.annotations.Nullable;
import white_heket.more_crustacean.block.ImplementedInventory;
import white_heket.more_crustacean.block.ModBlocks;
import white_heket.more_crustacean.loot.MoreCrustaceanLoottables;

import java.util.List;

public class CrabPotBlockEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory {
    private int tickCounter = 0;
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);
    public CrabPotBlockEntity( BlockEntityType<?> blockEntityType,BlockPos pos, BlockState state) {
        super(blockEntityType,pos, state);
    }
    public CrabPotBlockEntity( BlockPos pos, BlockState state) {
        this(ModBlocks.CRAB_POT_BLOCK_ENTITY,pos, state);
    }
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }
    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new Generic3x3ContainerScreenHandler(syncId, playerInventory, this);
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.inventory);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
    }
    public static void serverTick(World world, BlockPos pos, BlockState blockState,CrabPotBlockEntity blockEntity) {
        if(blockEntity.tickCounter >= Random.create().nextInt(2400)+1200) {
            blockEntity.tickCounter = 0;
            if (isCanCatchPlace(world, pos)) {
                LootContextParameterSet lootContextParameterSet = new LootContextParameterSet.Builder((ServerWorld) world)
                        .add(LootContextParameters.ORIGIN, new Vec3d(pos.getX(), pos.getY(), pos.getZ()))
                        .add(LootContextParameters.TOOL, ItemStack.EMPTY)
                        .build(LootContextTypes.FISHING);
                    //暖水海洋产物
                    if (world.getBiome(pos).matchesKey(BiomeKeys.WARM_OCEAN)) {
                        LootTable lootTable = world.getServer().getLootManager().getLootTable(MoreCrustaceanLoottables.WARM_OCEAN_POT_LOOT);
                        List<ItemStack> loot = lootTable.generateLoot(lootContextParameterSet);
                        if (loot != null) {
                            for (ItemStack lootItem : loot) {
                                for (int i = 0; i < blockEntity.size(); i++) {
                                    if (blockEntity.getStack(i).isEmpty()) {
                                        blockEntity.setStack(i, lootItem);
                                        world.playSound(null, pos, SoundEvents.ENTITY_FISH_SWIM, SoundCategory.BLOCKS, 0.5F, 1.0F);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    //温水海洋产物
                   else if (world.getBiome(pos).matchesKey(BiomeKeys.LUKEWARM_OCEAN)|| world.getBiome(pos).matchesKey( BiomeKeys.OCEAN) ) {
                        LootTable lootTable = world.getServer().getLootManager().getLootTable(MoreCrustaceanLoottables.NORMAL_OCEAN_POT_LOOT);
                        List<ItemStack> loot = lootTable.generateLoot(lootContextParameterSet);
                        if (loot != null) {
                            for (ItemStack lootItem : loot) {
                                for (int i = 0; i < blockEntity.size(); i++) {
                                    if (blockEntity.getStack(i).isEmpty()) {
                                        blockEntity.setStack(i, lootItem);
                                        world.playSound(null, pos, SoundEvents.ENTITY_FISH_SWIM, SoundCategory.BLOCKS, 0.5F, 1.0F);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    //冻水或冷水海洋产物
                    else if (world.getBiome(pos).matchesKey(BiomeKeys.COLD_OCEAN)  || world.getBiome(pos).matchesKey(BiomeKeys.FROZEN_OCEAN)) {
                        LootTable lootTable = world.getServer().getLootManager().getLootTable(MoreCrustaceanLoottables.COLD_OCEAN_POT_LOOT);
                        List<ItemStack> loot = lootTable.generateLoot(lootContextParameterSet);
                        if (loot != null) {
                            for (ItemStack lootItem : loot) {
                                for (int i = 0; i < blockEntity.size(); i++) {
                                    if (blockEntity.getStack(i).isEmpty()) {
                                        blockEntity.setStack(i, lootItem);
                                        world.playSound(null, pos, SoundEvents.ENTITY_FISH_SWIM, SoundCategory.BLOCKS, 0.5F, 1.0F);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    //深水海洋产物
                    else if (world.getBiome(pos).isIn(BiomeTags.IS_DEEP_OCEAN)) {
                        LootTable lootTable = world.getServer().getLootManager().getLootTable(MoreCrustaceanLoottables.DEEP_OCEAN_POT_LOOT);
                        List<ItemStack> loot = lootTable.generateLoot(lootContextParameterSet);
                        if (loot != null) {
                            for (ItemStack lootItem : loot) {
                                for (int i = 0; i < blockEntity.size(); i++) {
                                    if (blockEntity.getStack(i).isEmpty()) {
                                        blockEntity.setStack(i, lootItem);
                                        world.playSound(null, pos, SoundEvents.ENTITY_FISH_SWIM, SoundCategory.BLOCKS, 0.5F, 1.0F);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    //河流产物
                    else if (world.getBiome(pos).matchesKey(BiomeKeys.RIVER)) {
                        LootTable lootTable = world.getServer().getLootManager().getLootTable(MoreCrustaceanLoottables.RIVER_POT_LOOT);
                        List<ItemStack> loot = lootTable.generateLoot(lootContextParameterSet);
                        if (loot != null) {
                            for (ItemStack lootItem : loot) {
                                for (int i = 0; i < blockEntity.size(); i++) {
                                    if (blockEntity.getStack(i).isEmpty()) {
                                        blockEntity.setStack(i, lootItem);
                                        world.playSound(null, pos, SoundEvents.ENTITY_FISH_SWIM, SoundCategory.BLOCKS, 0.5F, 1.0F);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    //红树林产物
                    else if (world.getBiome(pos).matchesKey(BiomeKeys.MANGROVE_SWAMP) ) {
                        LootTable lootTable = world.getServer().getLootManager().getLootTable(MoreCrustaceanLoottables.MANGROVE_POT_LOOT);
                        List<ItemStack> loot = lootTable.generateLoot(lootContextParameterSet);
                        if (loot != null) {
                            for (ItemStack lootItem : loot) {
                                for (int i = 0; i < blockEntity.size(); i++) {
                                    if (blockEntity.getStack(i).isEmpty()) {
                                        blockEntity.setStack(i, lootItem);
                                        world.playSound(null, pos, SoundEvents.ENTITY_FISH_SWIM, SoundCategory.BLOCKS, 0.5F, 1.0F);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                 else {
                    LootTable lootTable = world.getServer().getLootManager().getLootTable(MoreCrustaceanLoottables.NORMAL_POT_LOOT);
                    List<ItemStack> loot = lootTable.generateLoot(lootContextParameterSet);
                    if (loot != null) {
                        for (ItemStack lootItem : loot) {
                            for (int i = 0; i < blockEntity.size(); i++) {
                                if (blockEntity.getStack(i).isEmpty()) {
                                    blockEntity.setStack(i, lootItem);
                                    world.playSound(null, pos, SoundEvents.ENTITY_FISH_SWIM, SoundCategory.BLOCKS, 0.5F, 1.0F);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }else {
            blockEntity.tickCounter ++;
        }
    }
    private static boolean isCanCatchPlace(World world,BlockPos pos){
        for (Direction direction : Direction.values()) {
            if (world.getFluidState(pos).isIn(FluidTags.WATER)) {
                if (world.getFluidState(pos.offset(direction)).isIn(FluidTags.WATER)) {
                    return true;
                }
            }
        }
        return false;
    }

}