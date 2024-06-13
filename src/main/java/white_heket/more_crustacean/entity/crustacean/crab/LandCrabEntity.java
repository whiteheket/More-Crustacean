package white_heket.more_crustacean.entity.crustacean.crab;

import white_heket.more_crustacean.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
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

import java.util.UUID;

public class LandCrabEntity extends AbstractCrabEntity implements Angerable, GeoEntity {
    private static final UniformIntProvider ANGER_TIME_RANGE;
    private int angerTime;
    @Nullable
    private UUID angryAt;
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    private int regenTime;
    public LandCrabEntity(EntityType<? extends AbstractCrabEntity> entityType, World world) {
        super(entityType, world, false,true);
        this.setStepHeight(1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER,-1.0F);

    }
    public static DefaultAttributeContainer.Builder setAttributes(){
            return createLivingAttributes()
                    .add(EntityAttributes.GENERIC_MAX_HEALTH,10)
                    .add(EntityAttributes.GENERIC_ARMOR,5)
                    .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,5.0f)
                    .add(EntityAttributes.GENERIC_ATTACK_SPEED,1.0f)
                    .add(EntityAttributes.GENERIC_FOLLOW_RANGE,16.0f)
                    .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK,0.1f)
                    .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.4f);
    }
    @Override
    public void initGoals(){
        super.initGoals();
        this.goalSelector.add(1,new MeleeAttackGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*1.5,false));
        this.goalSelector.add(0,new LandCrabEscapeFromDangerGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*2));
        this.goalSelector.add(2,new WanderAroundGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
        this.goalSelector.add(3,new LookAtEntityGoal(this, PlayerEntity.class,6.0F));
        this.goalSelector.add(3, new LookAroundGoal(this));
        this.targetSelector.add(1,new RevengeGoal(this, new Class[0]));
    }
    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand){
        ItemStack itemStack = player.getStackInHand(hand);
        if(itemStack.isOf(Items.SHEARS)) {
            if (!this.getWorld().isClient && this.isCanCutClaw()) {
                itemStack.damage(1, player, (playerx) -> {
                    playerx.sendToolBreakStatus(hand);
                });
                ItemEntity itemEntity = new ItemEntity(this.getWorld(), this.getX(), this.getY(), this.getZ(), ModItems.BLUE_LAND_CRAB_CLAW.getDefaultStack());
                this.getWorld().spawnEntity(itemEntity);
                this.setCanCutClaw(false);
                this.setRegenTime(28800);
                this.damage(player.getRecentDamageSource(), 1.0F);
                return ActionResult.SUCCESS;
            } else {
                return ActionResult.CONSUME;
            }
        } else {
            //return super.interactMob(player, hand);
            return ActionResult.PASS;
        }
    }
    @Override
    public void tick(){
        if (!this.isCanCutClaw() && this.getRegenTime() > 0) {
            this.setRegenTime(getRegenTime()-1);
            if (this.getRegenTime() ==0 ) {
                this.setCanCutClaw(true);
            }
        }
        if(!this.isCanCutClaw()){
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS ,10,0,false,false),this);
        }
        super.tick();
    }
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.getDataTracker().startTracking(CAN_CUT_CLAW, true);
    }
    public static boolean canSpawn(EntityType<? extends WaterCreatureEntity> type, WorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).isIn(MoreCrustaceanBlockTags.CRAB_SPAWN_BLOCKS) ;
    }
    public boolean isCanCutClaw(){return dataTracker.get(CAN_CUT_CLAW);}
    public void setCanCutClaw(boolean bool){dataTracker.set(CAN_CUT_CLAW, bool);}
    public int getRegenTime(){return this.regenTime; }
    public int setRegenTime(int time){return this.regenTime = time;}
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("CanCutClaw",this.isCanCutClaw());
        nbt.putInt("RegenTime",this.getRegenTime());
    }
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setCanCutClaw(nbt.getBoolean("CanCutClaw"));
        regenTime = nbt.getInt("RegenTime");
    }
    @Override
    public boolean canBeLeashedBy(PlayerEntity player) {
        return !this.isLeashed();
    }
    @Override
    public void tickWaterBreathingAir(int air) {
    }
    @Override
    public boolean shouldSwimInFluids(){
        return false;
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
    protected <E extends LandCrabEntity> PlayState predicate(final AnimationState<E> event) {
        if(event.isMoving()){
            event.getController().setAnimation(RawAnimation.begin().then("land_crab.model.walk", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(3.0D);
        } else{
            event.getController().setAnimation(RawAnimation.begin().then("land_crab.model.idle", Animation.LoopType.LOOP));
        }

        return PlayState.CONTINUE;
    }
    protected <E extends LandCrabEntity> PlayState attackpredicate(final AnimationState<E> event){
        if(this.handSwinging && event.getController().getAnimationState().equals(AnimationController.State.STOPPED)){
            if(!this.isCanCutClaw()){
                event.getController().forceAnimationReset();
                event.getController().setAnimation(RawAnimation.begin().then("land_crab.model.attack2", Animation.LoopType.PLAY_ONCE));
            }else {
                event.getController().forceAnimationReset();
                event.getController().setAnimation(RawAnimation.begin().then("land_crab.model.attack1", Animation.LoopType.PLAY_ONCE));
            }
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
    private static final TrackedData<Boolean> CAN_CUT_CLAW = DataTracker.registerData(LandCrabEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    static class LandCrabEscapeFromDangerGoal extends EscapeDangerGoal {

        public LandCrabEscapeFromDangerGoal(PathAwareEntity mob, double speed) {
            super(mob, speed);
        }
        @Override
        protected boolean isInDanger() {
            return this.mob.shouldEscapePowderSnow() || this.mob.isOnFire() || (this.mob.getHealth()<this.mob.getMaxHealth()*0.55 && this.mob.getAttacker() != null );
        }
    }
}
