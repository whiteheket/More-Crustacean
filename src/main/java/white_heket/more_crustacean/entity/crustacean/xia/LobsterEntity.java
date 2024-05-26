package white_heket.more_crustacean.entity.crustacean.xia;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import white_heket.more_crustacean.entity.crustacean.crab.AbstractCrabEntity;
import white_heket.more_crustacean.item.ModItems;
import white_heket.more_crustacean.tag.MoreCrustaceanBlockTags;

public class LobsterEntity extends AbstractCrabEntity implements GeoEntity {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    public LobsterEntity(EntityType<? extends AbstractCrabEntity> entityType, World world) {
        super(entityType, world, false);
        this.setStepHeight(1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
    }
    public static DefaultAttributeContainer.Builder setAttributes(){
        return WaterCreatureEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH,9)
                .add(EntityAttributes.GENERIC_ARMOR,2)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE,24.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.35f);
    }
    @Override
    public void initGoals(){
        super.initGoals();
        this.goalSelector.add(0,new EscapeDangerGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*2.5));
        this.goalSelector.add(1,new MoveIntoWaterGoal(this));
        this.goalSelector.add(3,new WanderAroundGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
        this.goalSelector.add(3,new LookAtEntityGoal(this, PlayerEntity.class,6.0F));
        this.goalSelector.add(3, new LookAroundGoal(this));
    }
    public static boolean canSpawn(EntityType<? extends WaterCreatureEntity> entity, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        int topY = world.getSeaLevel() - 1;
        int bottomY = world.getSeaLevel() - 48;
        return pos.getY() >= bottomY && pos.getY() <= topY && world.getBlockState(pos.down()).isIn(MoreCrustaceanBlockTags.CRAB_SPAWN_BLOCKS) && (world.isWater(pos) || world.isAir(pos));
    }
    @Override
    public boolean shouldSwimInFluids(){return false;}
    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(ModItems.LOBSTER_BUCKET);
    }
    @Override
    public void tickWaterBreathingAir(int air) {
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"controller",10,this::predicate));
    }
    protected <E extends LobsterEntity> PlayState predicate(final AnimationState<E> event) {
        if(!this.isOnGround() && this.isTouchingWater()){
            event.getController().setAnimation(RawAnimation.begin().then("lobster.model.escape2", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(2.5D);
        } else if(event.isMoving()){
            event.getController().setAnimation(RawAnimation.begin().then("lobster.model.walk2", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(3.0D);
        } else{
            event.getController().setAnimation(RawAnimation.begin().then("lobster.model.idle2", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }
}
