package white_heket.more_crustacean.entity.crustacean.crab;

import white_heket.more_crustacean.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.ai.pathing.SpiderNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import white_heket.more_crustacean.tag.MoreCrustaceanBlockTags;


public class SandCrabEntity extends AbstractCrabEntity implements GeoEntity {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    public SandCrabEntity(EntityType<? extends AbstractCrabEntity> entityType, World world) {
        super(entityType, world, false);
        this.setStepHeight(1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER,-1.0F);
    }
    public static DefaultAttributeContainer.Builder setAttributes(){
        return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH,5)
                .add(EntityAttributes.GENERIC_ARMOR,1)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,1.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED,1.0f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE,16.0f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK,0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.35f);
    }
    @Override
    public void initGoals(){
        super.initGoals();
        this.goalSelector.add(0, new EscapeDangerGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*2));
        this.goalSelector.add(1,new FleeEntityGoal<>(this,PlayerEntity.class,16.0F,1.0F,1.2F));
        this.goalSelector.add(1,new MeleeAttackGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*1.5,false));
        this.goalSelector.add(3,new WanderAroundGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
        this.goalSelector.add(3,new LookAtEntityGoal(this, PlayerEntity.class,6.0F));
        this.goalSelector.add(3, new LookAroundGoal(this));
        this.targetSelector.add(1,new ActiveTargetGoal<>(this, TurtleEntity.class, false,(entity) -> entity.isBaby()));
    }
    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand){
        ItemStack itemStack = player.getStackInHand(hand);
        if(itemStack.isOf(Items.GOLD_INGOT) && random.nextInt()<= 0.333){
            this.playSound(SoundEvents.ENTITY_GHAST_AMBIENT, 0.5F, 0.5F);
            itemStack.decrement(1);
            ItemEntity itemEntity = new ItemEntity(this.getWorld(), this.getX(), this.getY(), this.getZ(), Items.GHAST_TEAR.getDefaultStack());
            this.getWorld().spawnEntity(itemEntity);
            return ActionResult.success(this.getWorld().isClient);
        }else {
            return super.interactMob(player, hand);
        }
    }
    public static boolean canSpawn(EntityType<? extends WaterCreatureEntity> type, WorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
        return pos.getY() > world.getSeaLevel() -2 && pos.getY() < world.getSeaLevel() + 5 && world.getBlockState(pos.down()).isIn(MoreCrustaceanBlockTags.CRAB_SPAWN_BLOCKS) ;
    }
    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(ModItems.SAND_CRAB_BUCKET);
    }
    @Override
    protected EntityNavigation createNavigation(World world) {
        return new SpiderNavigation(this, world);
    }
    @Override
    public void tickWaterBreathingAir(int air) {
    }
    @Override
    public boolean shouldSwimInFluids(){
        return false;
    }
    @Override
    public void tick(){
        super.tick();
        if(!this.getWorld().isClient){
            this.setClimbingWall(this.horizontalCollision);
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"controller",10,this::predicate));
        controllers.add(new AnimationController<>(this,"attackcontroller",0,this::attackpredicate));
    }
    protected <E extends SandCrabEntity> PlayState predicate(final AnimationState<E> event) {
        if(event.isMoving()){
            event.getController().setAnimation(RawAnimation.begin().then("sand_crab.model.walk", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(3.0D);
        } else{
            event.getController().setAnimation(RawAnimation.begin().then("sand_crab.model.idle", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }
    protected <E extends SandCrabEntity> PlayState attackpredicate(final AnimationState<E> event) {
        if (this.handSwinging && event.getController().getAnimationState().equals(AnimationController.State.STOPPED)) {
            event.getController().forceAnimationReset();
            event.getController().setAnimation(RawAnimation.begin().then("sand_crab.model.attack", Animation.LoopType.PLAY_ONCE));
            this.handSwinging = false;
        }
        return PlayState.CONTINUE;
    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }
}
