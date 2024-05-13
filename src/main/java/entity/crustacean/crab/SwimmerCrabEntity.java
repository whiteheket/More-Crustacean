package entity.crustacean.crab;

import item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import tag.MoreCrustaceanBlockTags;

import java.util.UUID;

public class SwimmerCrabEntity extends AbstractCrabEntity implements Angerable, GeoEntity {
    private static final UniformIntProvider ANGER_TIME_RANGE;
    private int angerTime;
    @Nullable
    private UUID angryAt;
    protected final SwimNavigation waterNavigation;
    protected final MobNavigation landNavigation;
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    public SwimmerCrabEntity(EntityType<? extends AbstractCrabEntity> entityType, World world) {
        super(entityType, world, false);
        this.setStepHeight(1.0F);
        this.moveControl = new SwimmerCrabMoveControl(this);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
        this.waterNavigation = new SwimNavigation(this, world);
        this.landNavigation = new MobNavigation(this, world);
    }
    public static DefaultAttributeContainer.Builder setAttributes(){
        return WaterCreatureEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH,12)
                .add(EntityAttributes.GENERIC_ARMOR,3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,4.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED,1.2f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE,24.0f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK,0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.45f);
    }
    @Override
    public void initGoals(){
        super.initGoals();
        this.goalSelector.add(0,new SwimmerCrabEscapeFromDangerGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*1.5));
        this.goalSelector.add(1,new MeleeAttackGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*1.2,false));
        this.goalSelector.add(1,new MoveIntoWaterGoal(this));
        this.goalSelector.add(3,new WanderAroundGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
        this.goalSelector.add(3,new LookAtEntityGoal(this, PlayerEntity.class,6.0F));
        this.goalSelector.add(3, new LookAroundGoal(this));
        this.goalSelector.add(5,new SwimAroundGoal(this,1,1));
        this.targetSelector.add(1,new ActiveTargetGoal<>(this, DrownedEntity.class, true));
        this.targetSelector.add(2,new RevengeGoal(this, new Class[0]));
    }
    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(ModItems.SWIMMER_CRAB_BUCKET);
    }
    public static boolean canSpawn(EntityType<? extends WaterCreatureEntity> entity, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        int topY = world.getSeaLevel() - 1;
        int bottomY = world.getSeaLevel() - 48;
        return pos.getY() >= bottomY && pos.getY() <= topY && world.getBlockState(pos.down()).isIn(MoreCrustaceanBlockTags.CRAB_SPAWN_BLOCKS) && (world.isWater(pos) || world.isAir(pos));
    }
    @Override
    public void tickWaterBreathingAir(int air) {
    }
    @Override
    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }
    @Override
    public void setAngerTime(int angerTime) {
        this.angerTime = angerTime;
    }
    @Override
    public int getAngerTime() {
        return this.angerTime;
    }
    @Override
    public void setAngryAt(@Nullable UUID angryAt) {
        this.angryAt = angryAt;
    }

    @Nullable
    public UUID getAngryAt() {
        return this.angryAt;
    }
    boolean isTargetingUnderwater() {
        LivingEntity livingEntity = this.getTarget();
        return livingEntity != null && livingEntity.isTouchingWater();
    }
    @Override
    public void updateSwimming(){
        if (!this.getWorld().isClient) {
            if (this.canMoveVoluntarily() && this.isTouchingWater() && this.isTargetingUnderwater()) {
                this.navigation = this.waterNavigation;
                this.setSwimming(true);
            } else {
                this.navigation = this.landNavigation;
                this.setSwimming(false);
            }
        }
    }
    @Override
    public void travel(Vec3d movementInput) {
        if (this.isLogicalSideForUpdatingMovement() && this.isTouchingWater()) {
            this.updateVelocity(0.01F, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(1.0));
        }else {
            super.travel(movementInput);
        }
    }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"controller",10,this::predicate));
        controllers.add(new AnimationController<>(this,"attackcontroller",0,this::attackpredicate));
    }
    protected <E extends SwimmerCrabEntity> PlayState predicate(final AnimationState<E> event) {
        if(this.isTouchingWater() && this.isSwimming()){
            event.getController().setAnimation(RawAnimation.begin().then("swimmer_crab.model.swim", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(2.5D);
        } else if(event.isMoving()){
            event.getController().setAnimation(RawAnimation.begin().then("swimmer_crab.model.walk", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(3.0D);
        } else{
            event.getController().setAnimation(RawAnimation.begin().then("swimmer_crab.model.idle", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }
    protected <E extends SwimmerCrabEntity> PlayState attackpredicate(final AnimationState<E> event){
        if(this.handSwinging && event.getController().getAnimationState().equals(AnimationController.State.STOPPED)){
            event.getController().forceAnimationReset();
            event.getController().setAnimation(RawAnimation.begin().then("swimmer_crab.model.attack",Animation.LoopType.PLAY_ONCE));
            this.handSwinging = false;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }
    static {
        ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 40);
    }
    static class SwimmerCrabEscapeFromDangerGoal extends EscapeDangerGoal {

        public SwimmerCrabEscapeFromDangerGoal(PathAwareEntity mob, double speed) {
            super(mob, speed);
        }
        @Override
        protected boolean isInDanger() {
            return this.mob.shouldEscapePowderSnow() || this.mob.isOnFire() || (this.mob.getHealth()<this.mob.getMaxHealth()*0.4 && this.mob.getAttacker() != null );
        }
    }
    private static class SwimmerCrabMoveControl extends MoveControl {
        private final SwimmerCrabEntity crab;
        public SwimmerCrabMoveControl(SwimmerCrabEntity crab) {
            super(crab);
            this.crab = crab;
        }
        @Override
        public void tick() {
            LivingEntity livingEntity = this.crab.getTarget();
            if (this.crab.isTargetingUnderwater() && this.crab.isTouchingWater() ) {
                if (livingEntity != null && livingEntity.getY() > this.crab.getY()) {
                    this.crab.setVelocity(this.crab.getVelocity().add(0.0, 0.002, 0.0));
                }
                if (this.state != State.MOVE_TO || this.crab.getNavigation().isIdle()) {
                    this.crab.setMovementSpeed(0.0F);
                    return;
                }
                double d = this.targetX - this.crab.getX();
                double e = this.targetY - this.crab.getY();
                double f = this.targetZ - this.crab.getZ();
                double g = Math.sqrt(d * d + e * e + f * f);
                e /= g;
                float h = (float)(MathHelper.atan2(f, d) * 57.2957763671875) - 90.0F;
                this.crab.setYaw(this.wrapDegrees(this.crab.getYaw(), h, 90.0F));
                this.crab.bodyYaw = this.crab.getYaw();
                float i = (float)(this.speed * this.crab.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
                float j = MathHelper.lerp(0.125F, this.crab.getMovementSpeed(), i);
                this.crab.setMovementSpeed(j);
                this.crab.setVelocity(this.crab.getVelocity().add((double)j * d * 0.005, (double)j * e * 0.1, (double)j * f * 0.005));
            } else {
                if (!this.crab.isOnGround()) {
                    this.crab.setVelocity(this.crab.getVelocity().add(0.0, -0.01, 0.0));
                }
                super.tick();
            }
        }
    }
}
