package white_heket.more_crustacean.entity.crustacean.xia;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import white_heket.more_crustacean.item.ModItems;
import white_heket.more_crustacean.tag.MoreCrustaceanBlockTags;

public class PrawnEntity extends FishEntity implements GeoEntity {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    public PrawnEntity(EntityType<? extends FishEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FishMoveControl(this);
    }
    public static DefaultAttributeContainer.Builder setAttributes(){
        return WaterCreatureEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH,3)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE,24.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.7f);
    }
    @Override
    public void initGoals(){
        super.initGoals();
        this.goalSelector.add(0,new EscapeDangerGoal(this,1.25F));
        this.goalSelector.add(1,new MoveIntoWaterGoal(this));
        this.goalSelector.add(1,new FleeEntityGoal<>(this,PlayerEntity.class,16.0F,1.0F,1.2F));
        this.goalSelector.add(3,new SwimAroundGoal(this,1,40));
        this.goalSelector.add(3, new LookAroundGoal(this));
    }
    public static boolean canSpawn(EntityType<? extends WaterCreatureEntity> entity, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        int topY = world.getSeaLevel() - 1;
        int bottomY = world.getSeaLevel() - 48;
        return pos.getY() >= bottomY && pos.getY() <= topY && world.getBlockState(pos.down()).isIn(MoreCrustaceanBlockTags.CRAB_SPAWN_BLOCKS) && (world.isWater(pos) || world.isAir(pos));
    }
    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_COD_FLOP;
    }

    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(ModItems.PRAWN_BUCKET);
    }
    @Override
    public void travel(Vec3d movementInput) {
        if (this.canMoveVoluntarily() && this.isTouchingWater()) {
            this.updateVelocity(0.01F, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9));
            if (this.getTarget() == null) {
                this.setVelocity(this.getVelocity().add(0.0, -0.005, 0.0));
            }
        } else {
            super.travel(movementInput);
        }
    }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"controller",10,this::predicate));
    }
    protected <E extends PrawnEntity> PlayState predicate(final AnimationState<E> event) {
        if(!this.isTouchingWater()){
            event.getController().setAnimation(RawAnimation.begin().then("prawn.model.flap", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(2.5D);
        } else if(this.isSwimming() && this.isLogicalSideForUpdatingMovement()){
            event.getController().setAnimation(RawAnimation.begin().then("prawn.model.swim", Animation.LoopType.LOOP));
            event.getController().setAnimationSpeed(3.0D);
        } else{
            event.getController().setAnimation(RawAnimation.begin().then("prawn.model.idle", Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }
    static class FishMoveControl extends MoveControl {
        private final FishEntity fish;

        FishMoveControl(FishEntity owner) {
            super(owner);
            this.fish = owner;
        }

        public void tick() {
            if (this.fish.isSubmergedIn(FluidTags.WATER)) {
                this.fish.setVelocity(this.fish.getVelocity().add(0.0, 0.005, 0.0));
            }

            if (this.state == State.MOVE_TO && !this.fish.getNavigation().isIdle()) {
                float f = (float)(this.speed * this.fish.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
                this.fish.setMovementSpeed(MathHelper.lerp(0.125F, this.fish.getMovementSpeed(), f));
                double d = this.targetX - this.fish.getX();
                double e = this.targetY - this.fish.getY();
                double g = this.targetZ - this.fish.getZ();
                if (e != 0.0) {
                    double h = Math.sqrt(d * d + e * e + g * g);
                    this.fish.setVelocity(this.fish.getVelocity().add(0.0, (double)this.fish.getMovementSpeed() * (e / h) * 0.1, 0.0));
                }

                if (d != 0.0 || g != 0.0) {
                    float i = (float)(MathHelper.atan2(g, d) * 57.2957763671875) - 90.0F;
                    this.fish.setYaw(this.wrapDegrees(this.fish.getYaw(), i, 90.0F));
                    this.fish.bodyYaw = this.fish.getYaw();
                }

            } else {
                this.fish.setMovementSpeed(0.0F);
            }
        }
    }
}
