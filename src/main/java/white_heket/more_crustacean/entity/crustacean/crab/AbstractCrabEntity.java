package white_heket.more_crustacean.entity.crustacean.crab;

import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AbstractCrabEntity extends WaterCreatureEntity implements Bucketable {
    //蜕壳功能正在制作
    private int diggingCooldown;
    private int moltingCooldown;
    private final boolean canDig;
    protected AbstractCrabEntity(EntityType<? extends AbstractCrabEntity> entityType, World world, boolean canDig) {
        super(entityType, world);
        this.canDig = canDig;
    }
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.getDataTracker().startTracking(IS_DIGGING, false);
        this.getDataTracker().startTracking(IS_MOLTING,false);
        this.dataTracker.startTracking(CRAB_FLAGS, (byte)0);
        this.dataTracker.startTracking(FROM_BUCKET, false);
    }
    @Nullable
    @Override
    public  EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        if (entityData == null) {
            entityData = new PassiveEntity.PassiveData(5.0F);
        }
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt(DIGGING_COOLDOWN_KEY, diggingCooldown);
        nbt.putInt(MOLTING_COOLDOWN_KEY,moltingCooldown);
        nbt.putBoolean("FromBucket", this.isFromBucket());
    }
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        diggingCooldown = nbt.getInt(DIGGING_COOLDOWN_KEY);
        moltingCooldown = nbt.getInt(MOLTING_COOLDOWN_KEY);
        this.setFromBucket(nbt.getBoolean("FromBucket"));
    }
    @Override
    public void tick(){
        super.tick();
        if(this.getDiggingCooldown()>0){
            setDiggingCooldown(getDiggingCooldown()-1);
            if(this.isDigging()){
                this.setMovementSpeed(0.0f);
            }
        }
        if(!this.getWorld().isClient && this.isTouchingWater() && !this.isSwimming()){
            this.setClimbingWall(this.horizontalCollision);
        }
    }
    @Override
    public EntityGroup getGroup(){
        return EntityGroup.ARTHROPOD;
    }
    @Override
    public boolean isSneaking(){
        return true;
    }
    @Override
    public boolean isClimbing() {
        return this.isClimbingWall();
    }


    public boolean isClimbingWall() {
        return (this.dataTracker.get(CRAB_FLAGS) & 1) != 0;
    }
    public void setClimbingWall(boolean climbing) {
        byte b = this.dataTracker.get(CRAB_FLAGS);
        if (climbing) {
            b = (byte)(b | 1);
        } else {
            b &= -2;
        }

        this.dataTracker.set(CRAB_FLAGS, b);
    }
    public boolean isDigging() {
        return dataTracker.get(IS_DIGGING);
    }
    public boolean isMolting(){return dataTracker.get(IS_MOLTING);}
    public boolean isCanDig() {
        return canDig;
    }
    public int getDiggingCooldown(){return this.diggingCooldown; }
    public int setDiggingCooldown(int cooldown){return this.diggingCooldown = cooldown;}
    public int getMoltingCooldown(){return this.moltingCooldown;}
    public int setMoltingCooldown(int cooldown){return this.moltingCooldown =cooldown;}
    public void setDigging(boolean bool) {dataTracker.set(IS_DIGGING, bool);}
    public void setIsMolting(boolean bool) {dataTracker.set(IS_MOLTING, bool);}
    private static final TrackedData<Boolean> IS_DIGGING = DataTracker.registerData(AbstractCrabEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> IS_MOLTING = DataTracker.registerData(AbstractCrabEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Byte> CRAB_FLAGS = DataTracker.registerData(AbstractCrabEntity.class, TrackedDataHandlerRegistry.BYTE);
    private static final TrackedData<Boolean> FROM_BUCKET = DataTracker.registerData(AbstractCrabEntity .class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final String DIGGING_COOLDOWN_KEY = "DiggingCooldown";
    public static final String MOLTING_COOLDOWN_KEY = "MoltingCooldown";

    @Override
    public boolean isFromBucket() {
        return this.dataTracker.get(FROM_BUCKET);
    }
    @Override
    public void setFromBucket(boolean fromBucket) {
        this.dataTracker.set(FROM_BUCKET, fromBucket);
    }
    @Override
    public void copyDataToStack(ItemStack stack) {
        Bucketable.copyDataToStack(this, stack);
    }
    @Override
    public void copyDataFromNbt(NbtCompound nbt) {
        Bucketable.copyDataFromNbt(this, nbt);
    }
    @Override
    public ItemStack getBucketItem() {
        return null;
    }
    @Override
    public SoundEvent getBucketFillSound() {
        return SoundEvents.ITEM_BUCKET_FILL_FISH;
    }
    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        return Bucketable.tryBucket(player, hand, this).orElse(super.interactMob(player, hand));
    }
    @Override
    public boolean cannotDespawn() {
        return super.cannotDespawn() || this.isFromBucket();
    }
}
