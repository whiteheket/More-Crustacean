package white_heket.more_crustacean.entity.crustacean.crab;

import white_heket.more_crustacean.entity.ai.CrabDigGoal;
import white_heket.more_crustacean.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
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

public class BrownCrabEntity extends AbstractCrabEntity implements Angerable, GeoEntity {
    private static final UniformIntProvider ANGER_TIME_RANGE;
    private int angerTime;
    @Nullable
    private UUID angryAt;
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public BrownCrabEntity(EntityType<? extends AbstractCrabEntity> entityType, World world) {
        super(entityType, world,true);
        this.setStepHeight(1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
    }
    public static DefaultAttributeContainer.Builder setAttributes(){
        return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH,8)
                .add(EntityAttributes.GENERIC_ARMOR,4)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,4.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED,1.0f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE,16.0f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK,0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.35f);
    }
    @Override
    public void initGoals(){
        super.initGoals();
        this.goalSelector.add(1,new MeleeAttackGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*1.5,false));
        this.goalSelector.add(1,new MoveIntoWaterGoal(this));
        this.goalSelector.add(0,new BrownCrabEscapeFromDangerGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*2));
        this.goalSelector.add(3,new WanderAroundGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
        this.goalSelector.add(3,new LookAtEntityGoal(this, PlayerEntity.class,6.0F));
        this.goalSelector.add(3, new LookAroundGoal(this));
        this.goalSelector.add(2,new CrabDigGoal(this,0.60));
        this.targetSelector.add(1,new RevengeGoal(this, new Class[0]));
    }
    @Override
    public void onDeath(DamageSource damageSource){
        if(this.getName().getString().equals("Mr.Krab") || this.getName().getString().equals("蟹老板")){
           if(damageSource.getAttacker() instanceof PlayerEntity){
               PlayerEntity player = (PlayerEntity) damageSource.getAttacker();
               player.sendMessage(Text.of("本模组，完全免费开源，且禁止商用，一经发现，后果自负！如果你是在收费整合包中看到这段话，很抱歉你被作者骗取了钱财…请向社区曝光他的行径，讨回你的钱财!"), false);
           }
        }
        super.onDeath(damageSource);
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
    public ItemStack getBucketItem() {
        return new ItemStack(ModItems.BROWN_CRAB_BUCKET);
    }
    public static boolean canSpawn(EntityType<? extends WaterCreatureEntity> entity, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        int topY = world.getSeaLevel() - 1;
        int bottomY = world.getSeaLevel() - 48;
        return pos.getY() >= bottomY && pos.getY() <= topY && world.getBlockState(pos.down()).isIn(MoreCrustaceanBlockTags.CRAB_SPAWN_BLOCKS) && (world.isWater(pos) || world.isAir(pos));
    }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"controller",10,this::predicate));
        controllers.add(new AnimationController<>(this,"attackcontroller",0,this::attackpredicate));
    }
    protected <E extends BrownCrabEntity> PlayState predicate(final AnimationState<E> event) {
        if(this.isDigging() && this.isCanDig()){
            event.getController().setAnimation(RawAnimation.begin().then("brown_crab.model.dig", Animation.LoopType.PLAY_ONCE));
        } else if(event.isMoving()){
            event.getController().setAnimation(RawAnimation.begin().then("brown_crab.model.walk", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(3.0D);
        } else{
            event.getController().setAnimation(RawAnimation.begin().then("brown_crab.model.idle", Animation.LoopType.LOOP));
        }

        return PlayState.CONTINUE;
    }
    protected <E extends BrownCrabEntity> PlayState attackpredicate(final AnimationState<E> event){
        if(this.handSwinging && event.getController().getAnimationState().equals(AnimationController.State.STOPPED)){
            event.getController().forceAnimationReset();
            event.getController().setAnimation(RawAnimation.begin().then("brown_crab.model.attack",Animation.LoopType.PLAY_ONCE));
            this.handSwinging = false;
        }
        return PlayState.CONTINUE;
    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }
    static {
        ANGER_TIME_RANGE = TimeHelper.betweenSeconds(10, 20);
    }
    static class BrownCrabEscapeFromDangerGoal extends EscapeDangerGoal {

        public BrownCrabEscapeFromDangerGoal(PathAwareEntity mob, double speed) {
            super(mob, speed);
        }
        @Override
        protected boolean isInDanger() {
            return this.mob.shouldEscapePowderSnow() || this.mob.isOnFire() || (this.mob.getHealth()<this.mob.getMaxHealth()*0.5 && this.mob.getAttacker() != null );
        }
    }
}
