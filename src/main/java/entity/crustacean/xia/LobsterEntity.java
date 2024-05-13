package entity.crustacean.xia;

import entity.crustacean.crab.AbstractCrabEntity;
import entity.crustacean.crab.SwimmerCrabEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.UUID;
//WIP
/*
public class LobsterEntity extends AbstractCrabEntity implements GeoEntity, Angerable {
    private static final UniformIntProvider ANGER_TIME_RANGE;
    private int angerTime;
    private float swimX;
    private float swimY;
    private float swimZ;
    @Nullable
    private UUID angryAt;
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
    protected LobsterEntity(EntityType<? extends AbstractCrabEntity> entityType, World world) {
        super(entityType, world, false);
        this.setStepHeight(1.0F);
        this.setPathfindingPenalty(PathNodeType.WATER, 1.0F);
    }
    public static DefaultAttributeContainer.Builder setAttributes(){
        return WaterCreatureEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH,20)
                .add(EntityAttributes.GENERIC_ARMOR,3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,6.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED,0.8f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE,24.0f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK,0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.45f);
    }
    @Override
    public void initGoals(){
        this.goalSelector.add(0,new LobsterEntityEscapeFromDangerGoal(this));
        this.goalSelector.add(1,new EscapeDangerGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*1.5));
        this.goalSelector.add(1,new MeleeAttackGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)*1.2,false));
        this.goalSelector.add(1,new MoveIntoWaterGoal(this));
        this.goalSelector.add(3,new WanderAroundGoal(this,this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
        this.goalSelector.add(3,new LookAtEntityGoal(this, PlayerEntity.class,6.0F));
        this.goalSelector.add(3, new LookAroundGoal(this));
        this.targetSelector.add(1,new RevengeGoal(this, new Class[0]));
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

    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }
    public void setSwimmingVector(float x, float y, float z) {
        this.swimX = x;
        this.swimY = y;
        this.swimZ = z;
    }
    static {
        ANGER_TIME_RANGE = TimeHelper.betweenSeconds(10, 20);
    }
    static class LobsterEntityEscapeFromDangerGoal extends Goal{
        private int timer;
        private final LobsterEntity  lobster;
        LobsterEntityEscapeFromDangerGoal(LobsterEntity lobster){
            this.lobster = lobster;
        }
        public boolean canStart() {
            LivingEntity livingEntity = lobster.getAttacker();
            if (lobster.isTouchingWater() && livingEntity != null) {
                return lobster.squaredDistanceTo(livingEntity) < 100.0;
            } else {
                return false;
            }
        }

        public void start() {
            this.timer = 0;
        }

        public boolean shouldRunEveryTick() {
            return true;
        }

        public void tick() {
            ++this.timer;
            LivingEntity livingEntity = lobster.getAttacker();
            if (livingEntity != null) {
                Vec3d vec3d = new Vec3d(lobster.getX() - livingEntity.getX(), lobster.getY() - livingEntity.getY(), lobster.getZ() - livingEntity.getZ());
                BlockState blockState = lobster.getWorld().getBlockState(BlockPos.ofFloored(lobster.getX() + vec3d.x, lobster.getY() + vec3d.y, lobster.getZ() + vec3d.z));
                FluidState fluidState = lobster.getWorld().getFluidState(BlockPos.ofFloored(lobster.getX() + vec3d.x, lobster.getY() + vec3d.y, lobster.getZ() + vec3d.z));
                if (fluidState.isIn(FluidTags.WATER) || blockState.isAir()) {
                    double d = vec3d.length();
                    if (d > 0.0) {
                        vec3d.normalize();
                        double e = 3.0;
                        if (d > 5.0) {
                            e -= (d - 5.0) / 5.0;
                        }

                        if (e > 0.0) {
                            vec3d = vec3d.multiply(e);
                        }
                    }

                    if (blockState.isAir()) {
                        vec3d = vec3d.subtract(0.0, vec3d.y, 0.0);
                    }

                    lobster.setSwimmingVector((float)vec3d.x / 20.0F, (float)vec3d.y / 20.0F, (float)vec3d.z / 20.0F);
                }

                if (this.timer % 10 == 5) {
                    lobster.getWorld().addParticle(ParticleTypes.BUBBLE, lobster.getX(), lobster.getY(), lobster.getZ(), 0.0, 0.0, 0.0);
                }
                this.lobster.lookAtEntity(livingEntity,90,90);
                this.lobster.bodyYaw = this.lobster.getYaw();
            }
        }
    }
}*/
