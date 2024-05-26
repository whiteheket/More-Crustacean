package white_heket.more_crustacean.entity.crustacean.xia;

import white_heket.more_crustacean.entity.crustacean.crab.AbstractCrabEntity;
import white_heket.more_crustacean.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
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
import white_heket.more_crustacean.tag.MoreCrustaceanBlockTags;

import java.util.List;
import java.util.UUID;

public class ClawsterEntity extends AbstractCrabEntity implements GeoEntity, Angerable {
    private static final UniformIntProvider ANGER_TIME_RANGE;
    private int angerTime;
    @Nullable
    private UUID angryAt;
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    public ClawsterEntity(EntityType<? extends AbstractCrabEntity> entityType, World world) {
        super(entityType, world, false);
        this.setStepHeight(1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
    }
    public static DefaultAttributeContainer.Builder setAttributes(){
        return WaterCreatureEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH,15)
                .add(EntityAttributes.GENERIC_ARMOR,5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,6.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED,0.5f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE,24.0f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK,0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.35f);
    }
    @Override
    public void initGoals(){
        super.initGoals();
        this.goalSelector.add(0,new MoonlightCrazy(this));
        this.goalSelector.add(0,new ClawsterEscapeFromDangerGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*2.5));
        this.goalSelector.add(2,new MeleeAttackGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*1.2,false));
        this.goalSelector.add(1,new MoveIntoWaterGoal(this));
        this.goalSelector.add(3,new WanderAroundGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
        this.goalSelector.add(3,new LookAtEntityGoal(this, PlayerEntity.class,6.0F));
        this.goalSelector.add(3, new LookAroundGoal(this));
        this.targetSelector.add(1,new RevengeGoal(this, new Class[0]));
    }
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.getDataTracker().startTracking(IS_CRAZY, false);
    }
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("IsCrazy",this.isCrazy());
    }
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setCrazy(nbt.getBoolean("IsCrazy"));
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
        return new ItemStack(ModItems.CLAWSTER_BUCKET);
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
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"controller",10,this::predicate));
        controllers.add(new AnimationController<>(this,"attackcontroller",0,this::attackpredicate));
    }
    protected <E extends ClawsterEntity> PlayState predicate(final AnimationState<E> event) {
        if(!this.isOnGround() && this.isTouchingWater()){
            event.getController().setAnimation(RawAnimation.begin().then("clawster.model.escape", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(2.5D);
        } else if(event.isMoving()){
            event.getController().setAnimation(RawAnimation.begin().then("clawster.model.walk", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(3.0D);
        } else{
            event.getController().setAnimation(RawAnimation.begin().then("clawster.model.idle", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }
    protected <E extends ClawsterEntity> PlayState attackpredicate(final AnimationState<E> event){
        if(this.handSwinging && event.getController().getAnimationState().equals(AnimationController.State.STOPPED)){
            event.getController().forceAnimationReset();
            event.getController().setAnimation(RawAnimation.begin().then("clawster.model.attack",Animation.LoopType.PLAY_ONCE));
            this.handSwinging = false;
        }
        return PlayState.CONTINUE;
    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }
    public boolean isCrazy(){return dataTracker.get(IS_CRAZY);}
    public void setCrazy(boolean bl){dataTracker.set(IS_CRAZY, bl);}
    static {
        ANGER_TIME_RANGE = TimeHelper.betweenSeconds(10, 20);
    }
    private static final TrackedData<Boolean> IS_CRAZY = DataTracker.registerData(ClawsterEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    static class ClawsterEscapeFromDangerGoal extends EscapeDangerGoal {
        public ClawsterEscapeFromDangerGoal(PathAwareEntity mob, double speed) {
            super(mob, speed);
        }
        @Override
        public boolean isInDanger() {
            return this.mob.shouldEscapePowderSnow() || this.mob.isOnFire() || (this.mob.getHealth()<this.mob.getMaxHealth()*0.5 && this.mob.getAttacker() != null );
        }
    }
    static class MoonlightCrazy extends Goal {
        private final ClawsterEntity clawster;
        public MoonlightCrazy(ClawsterEntity clawster) {
            super();
            this.clawster = clawster;
        }

        @Override
        public boolean canStart() {
            return this.clawster.getWorld().isNight() && this.clawster.isTouchingWater() && this.clawster.getWorld().getMoonSize() > 0.9F;
        }
        @Override
        public void start(){
            this.clawster.setCrazy(true);
            super.start();
        }
        @Override
        public void tick(){
            this.clawster.setMovementSpeed((float) (this.clawster.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*2.0));
            List<LivingEntity> nearbyEntities = this.clawster.getWorld().getEntitiesByClass(LivingEntity.class, clawster.getBoundingBox().expand(8.0), entity -> true);
            for (LivingEntity entity : nearbyEntities) {
                if (entity != clawster) {
                    clawster.setTarget(entity);
                }
            }
        }
        @Override
        public void stop(){
            this.clawster.setCrazy(false);
            super.stop();
        }
    }
}
