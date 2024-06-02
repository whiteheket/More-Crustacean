package white_heket.more_crustacean.entity.crustacean.xia;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import white_heket.more_crustacean.entity.crustacean.crab.AbstractCrabEntity;
import white_heket.more_crustacean.item.ModItems;

public class CrayfishEntity extends ClawsterEntity {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    public CrayfishEntity(EntityType<? extends AbstractCrabEntity> entityType, World world) {
        super(entityType, world);
        this.setStepHeight(1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
    }
    public static DefaultAttributeContainer.Builder setAttributes(){
        return WaterCreatureEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH,5)
                .add(EntityAttributes.GENERIC_ARMOR,1)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,2.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED,0.7f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE,24.0f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK,0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.30f);
    }
    @Override
    public void initGoals(){
        super.initGoals();
        this.goalSelector.add(0,new MoonlightConfusion(this,1.0F));
        this.goalSelector.add(0,new ClawsterEscapeFromDangerGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*2.5));
        this.goalSelector.add(2,new MeleeAttackGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*1.2,false));
        this.goalSelector.add(1,new MoveIntoWaterGoal(this));
        this.goalSelector.add(3,new WanderAroundGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
        this.goalSelector.add(3,new LookAtEntityGoal(this, PlayerEntity.class,6.0F));
        this.goalSelector.add(3, new LookAroundGoal(this));
        this.targetSelector.add(1,new RevengeGoal(this, new Class[0]));
    }
    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(ModItems.CRAYFISH_BUCKET);
    }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"controller",10,this::predicate1));
        controllers.add(new AnimationController<>(this,"attackcontroller",0,this::attackpredicate1));
    }
    protected <E extends ClawsterEntity> PlayState predicate1(final AnimationState<E> event) {
         if(event.isMoving()){
            event.getController().setAnimation(RawAnimation.begin().then("crayfish.model.walk", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(3.0D);
        } else{
            event.getController().setAnimation(RawAnimation.begin().then("crayfish.model.idle", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }
    protected <E extends ClawsterEntity> PlayState attackpredicate1(final AnimationState<E> event){
        if(this.handSwinging && event.getController().getAnimationState().equals(AnimationController.State.STOPPED)){
            event.getController().forceAnimationReset();
            event.getController().setAnimation(RawAnimation.begin().then("crayfish.model.attack",Animation.LoopType.PLAY_ONCE));
            this.handSwinging = false;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }
    static class MoonlightConfusion extends MoveToTargetPosGoal {
        private final CrayfishEntity crayfish;
        public MoonlightConfusion(CrayfishEntity crayfish, double speed) {
            super(crayfish, speed, 16, 2);
            this.crayfish = crayfish;
        }

        @Override
        public boolean canStart() {
            return this.crayfish.getWorld().isNight() && this.crayfish.isTouchingWater() && this.crayfish.getWorld().getMoonSize() > 0.9F;
        }
        @Override
        public void start(){
            this.crayfish.setCrazy(true);
            super.start();
        }
        public boolean shouldContinue() {
            return super.shouldContinue();
        }
        @Override
        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            BlockPos blockPos = pos.up();
            return world.isAir(blockPos)  ? world.getBlockState(pos).hasSolidTopSurface(world, pos, this.crayfish) : false;
        }

        @Override
        public void stop(){
            this.crayfish.setCrazy(false);
            super.stop();
        }
    }
}
