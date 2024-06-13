package white_heket.more_crustacean.entity.crustacean.crab;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.ai.pathing.SpiderNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import white_heket.more_crustacean.tag.MoreCrustaceanBlockTags;

import java.util.UUID;

public class CoconutCrabEntity extends AbstractCrabEntity implements Angerable,GeoEntity {
    private static final UniformIntProvider ANGER_TIME_RANGE;
    private int angerTime;
    @Nullable
    private UUID angryAt;
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    public CoconutCrabEntity(EntityType<? extends AbstractCrabEntity> entityType, World world) {
        super(entityType, world, false,true);
        this.setStepHeight(1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER,-1.0F);
    }
    public static DefaultAttributeContainer.Builder setAttributes(){
        return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH,15)
                .add(EntityAttributes.GENERIC_ARMOR,4)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,5.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED,0.8f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE,16.0f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK,0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.35f);
    }
    @Override
    public void initGoals(){
        super.initGoals();
        this.goalSelector.add(0, new CoconutCrabEscapeFromDangerGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*2));
        this.goalSelector.add(1,new MeleeAttackGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*2,false));
        this.goalSelector.add(3,new WanderAroundGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
        this.goalSelector.add(3,new LookAtEntityGoal(this, PlayerEntity.class,6.0F));
        this.goalSelector.add(3, new LookAroundGoal(this));
        this.targetSelector.add(2,new ActiveTargetGoal<>(this, TurtleEntity.class, false,(entity) -> entity.isBaby()));
        this.targetSelector.add(1,new RevengeGoal(this, new Class[0]));
    }
    @Override
    public boolean canBeLeashedBy(PlayerEntity player) {
        return !this.isLeashed();
    }
    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        return ActionResult.PASS;
    }
    //Banned
    /*@Override
    public ItemStack getBucketItem() {
        return new ItemStack(ModItems.COCONUT_CRAB_BUCKET);
    }*/
    @Override
    protected EntityNavigation createNavigation(World world) {
        return new SpiderNavigation(this, world);
    }
    @Override
    public boolean shouldSwimInFluids(){
        return false;
    }
    @Override
    public boolean canBreatheInWater() {
        return false;
    }
    @Override
    protected void tickWaterBreathingAir(int air) {
    }
    @Override
    public int getMaxAir() {
        return 600;
    }
    @Override
    protected int getNextAirOnLand(int air) {
        return this.getMaxAir();
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
    @Override
    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }
    @Nullable
    public UUID getAngryAt() {
        return this.angryAt;
    }
    @Override
    public void tick(){
        super.tick();
        if(!this.getWorld().isClient){
            this.setClimbingWall(this.horizontalCollision);
        }
    }
    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        this.setAir(this.getMaxAir());
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }
    public static boolean canSpawn(EntityType<? extends WaterCreatureEntity> type, WorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).isIn(MoreCrustaceanBlockTags.CRAB_SPAWN_BLOCKS) ;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"controller",10,this::predicate));
        controllers.add(new AnimationController<>(this,"attackcontroller",0,this::attackpredicate));
    }
    protected <E extends CoconutCrabEntity> PlayState predicate(final AnimationState<E> event) {
        if(event.isMoving()){
            event.getController().setAnimation(RawAnimation.begin().then("coconut_crab.model.walk", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(3.0D);
        } else{
            event.getController().setAnimation(RawAnimation.begin().then("coconut_crab.model.idle", Animation.LoopType.LOOP));
        }

        return PlayState.CONTINUE;
    }
    protected <E extends CoconutCrabEntity> PlayState attackpredicate(final AnimationState<E> event){
        if(this.handSwinging && event.getController().getAnimationState().equals(AnimationController.State.STOPPED)){
            event.getController().forceAnimationReset();
            event.getController().setAnimation(RawAnimation.begin().then("coconut_crab.model.attack",Animation.LoopType.PLAY_ONCE));
            this.handSwinging = false;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }
    static {
        ANGER_TIME_RANGE = TimeHelper.betweenSeconds(10, 20);
    }
    static class CoconutCrabEscapeFromDangerGoal extends EscapeDangerGoal {

        public CoconutCrabEscapeFromDangerGoal(PathAwareEntity mob, double speed) {
            super(mob, speed);
        }
        @Override
        protected boolean isInDanger() {
            return this.mob.shouldEscapePowderSnow() || this.mob.isOnFire() || (this.mob.getHealth()<this.mob.getMaxHealth()*0.5 && this.mob.getAttacker() != null );
        }
    }
}
