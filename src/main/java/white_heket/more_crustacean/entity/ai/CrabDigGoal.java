package white_heket.more_crustacean.entity.ai;

import white_heket.more_crustacean.entity.crustacean.crab.AbstractCrabEntity;
import white_heket.more_crustacean.loot.MoreCrustaceanLoottables;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import white_heket.more_crustacean.tag.MoreCrustaceanBlockTags;

import java.util.List;
import java.util.Random;

public class CrabDigGoal extends Goal {
    private final AbstractCrabEntity crab;
    private final double chance;
    private final Random random =new Random();
    private BlockState diggableBlock;
    private int diggingTimer =0;

    public CrabDigGoal(AbstractCrabEntity crab, double chance) {
        this.crab = crab;
        this.chance = chance;
    }
    @Override
    public boolean canStart() {
        diggableBlock  = crab.getWorld().getBlockState(crab.getBlockPos().down());
        List<PlayerEntity> players = crab.getWorld().getEntitiesByType(EntityType.PLAYER, crab.getBoundingBox().expand(24),player -> true);
        boolean playerNearby = players.stream().anyMatch(player -> player.getPos().squaredDistanceTo(crab.getPos()) <= 576);
        return crab.getDiggingCooldown() <= 0 && random.nextDouble() <=chance &&  diggableBlock.isIn(MoreCrustaceanBlockTags.CRAB_DIGGABLE_BLOCKS) && crab.isCanDig() && playerNearby;
    }
    @Override
    public void start() {
        diggingTimer = 0;
        crab.setDigging(true);
        crab.getNavigation().stop();
        super.start();
    }
    @Override
    public void tick() {
            diggingTimer++;
            double x = crab.getX();
            double y = crab.getY();
            double z = crab.getZ();

            if (diggingTimer <= 20) {
                ((ServerWorld) crab.getWorld()).spawnParticles(ParticleTypes.POOF, x, y, z, 3, 0.0, 0.0, 0.0, 0.0);
            } else {
                diggingTimer = 100;
                LootContextParameterSet lootContextParameterSet = new LootContextParameterSet.Builder((ServerWorld) crab.getWorld())
                        .add(LootContextParameters.BLOCK_STATE, diggableBlock)
                        .add(LootContextParameters.ORIGIN, crab.getPos())
                        .add(LootContextParameters.TOOL, ItemStack.EMPTY)
                        .add(LootContextParameters.THIS_ENTITY, crab)
                        .build(LootContextTypes.BLOCK);
                LootTable lootTable = crab.getWorld().getServer().getLootManager().getLootTable(MoreCrustaceanLoottables.CRAB_DIG_LOOT);
                List<ItemStack> loot = lootTable.generateLoot(lootContextParameterSet);

                if (loot != null) {
                    for (ItemStack lootItem : loot) {
                        ItemEntity itemEntity = new ItemEntity(crab.getWorld(), x, y, z, lootItem);
                        crab.getWorld().spawnEntity(itemEntity);
                    }
                }
            }
        super.tick();
    }
    @Override
    public boolean shouldContinue() {
        BlockState blockUnderCrab = crab.getWorld().getBlockState(crab.getBlockPos().down());
        return blockUnderCrab.isIn(MoreCrustaceanBlockTags.CRAB_DIGGABLE_BLOCKS) &&
                crab.getDiggingCooldown() <= 0 && diggingTimer < 100;
    }

    @Override
    public void stop() {
        diggingTimer = 0;
        crab.setDigging(false);
        crab.setDiggingCooldown(random.nextInt(12000) + 1200);
        super.stop();
    }
}
