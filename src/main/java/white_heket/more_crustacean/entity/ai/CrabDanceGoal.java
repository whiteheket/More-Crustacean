package white_heket.more_crustacean.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import white_heket.more_crustacean.block.ModBlocks;
import white_heket.more_crustacean.block.block.JewelleryChestBlock;
import white_heket.more_crustacean.block.entity.JewelleryChestBlockEntity;
import white_heket.more_crustacean.entity.crustacean.crab.AbstractCrabEntity;

import java.util.List;

public class CrabDanceGoal extends Goal {
    private final AbstractCrabEntity crab;
    public CrabDanceGoal(AbstractCrabEntity crab) {
        this.crab = crab;
    }
    @Override
    public boolean canStart() {
        int startX = (int) (crab.getX() - 5);
        int startY = (int) (crab.getY()-5);
        int startZ = (int) (crab.getZ() - 5);
        int endX = (int) (crab.getX() + 5);
        int endY = (int) (crab.getY() + 5);
        int endZ = (int) (crab.getZ() + 5);
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                for (int z = startZ; z <= endZ; z++) {
                    BlockPos nearbyPos = new BlockPos(x, y, z);
                    Block chest = crab.getWorld().getBlockState(nearbyPos).getBlock();
                    BlockEntity blockEntity = crab.getWorld().getBlockEntity(nearbyPos);
                    if (chest instanceof JewelleryChestBlock && blockEntity.getCachedState().get(JewelleryChestBlock.IS_OPEN) ) {
                       return true;
                    }
                }
            }
        }
        return false;
    }
    @Override
    public void start() {
        crab.setIsDancing(true);
        crab.getNavigation().stop();
        super.start();
    }
    @Override
    public void stop(){
        super.stop();
        crab.setIsDancing(false);
    }
}
