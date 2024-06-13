package white_heket.more_crustacean.entity.ai;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import white_heket.more_crustacean.entity.crustacean.crab.AbstractCrabEntity;
import white_heket.more_crustacean.item.ModItems;
import white_heket.more_crustacean.tag.MoreCrustaceanBlockTags;

import java.util.Random;

public class CrabMoltGoal extends Goal {
    private final AbstractCrabEntity crab;
    private final double chance;
    private final Random random =new Random();
    private BlockState comfortBlock;
    private int moltingtimer =0;
    public CrabMoltGoal(AbstractCrabEntity crab,double chance){
        this.crab = crab;
        this.chance = chance;
    }
    @Override
    public boolean canStart() {
        comfortBlock = crab.getWorld().getBlockState(crab.getBlockPos().down());
        return crab.getMoltingCooldown() <= 0 && random.nextDouble() <= chance && comfortBlock.isIn(MoreCrustaceanBlockTags.CRAB_COMFORT_BLOCKS) && crab.isCanMolt();
    }
    @Override
    public void start(){
        moltingtimer = 0;
        crab.setIsMolting(true);
        crab.getNavigation().stop();
        super.start();
    }
    @Override
    public void tick(){
        moltingtimer++;
        double x = crab.getX();
        double y = crab.getY();
        double z = crab.getZ();
        crab.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,20,5,false,false),crab);
        if (moltingtimer <= 20) {
            ((ServerWorld) crab.getWorld()).spawnParticles(ParticleTypes.HAPPY_VILLAGER, x, y+0.4, z, 5, random.nextGaussian() * 0.02, random.nextGaussian() * 0.02, random.nextGaussian() * 0.02, 0.1);
        } else {
            moltingtimer = 100;
            ItemEntity itemEntity = new ItemEntity(crab.getWorld(),x, y,z, new ItemStack(ModItems.CRAB_SHELL));
            crab.getWorld().spawnEntity(itemEntity);
        }
        super.tick();
    }
    @Override
    public boolean shouldContinue() {
        BlockState blockUnderCrab = crab.getWorld().getBlockState(crab.getBlockPos().down());
        return blockUnderCrab.isIn(MoreCrustaceanBlockTags.CRAB_COMFORT_BLOCKS) &&
                crab.getMoltingCooldown() == 0 && moltingtimer < 100;
    }
    @Override
    public void stop() {
        moltingtimer = 0;
        crab.setIsMolting(false);
        crab.setMoltingCooldown(random.nextInt(288000) + 14400);
        super.stop();
    }
}
