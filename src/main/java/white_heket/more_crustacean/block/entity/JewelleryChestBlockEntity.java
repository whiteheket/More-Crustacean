package white_heket.more_crustacean.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import white_heket.more_crustacean.block.ModBlocks;
import white_heket.more_crustacean.block.block.JewelleryChestBlock;

public class JewelleryChestBlockEntity extends BlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public JewelleryChestBlockEntity( BlockPos pos, BlockState state) {
        super(ModBlocks.JEWELLERY_CHEST_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, this::deployAnimController));
    }
    protected <E extends JewelleryChestBlockEntity> PlayState deployAnimController(final AnimationState<E> state) {
        if (this.getCachedState().get(JewelleryChestBlock.IS_OPEN) == true) {
            state.getController().setAnimation(RawAnimation.begin().then("jewellery_chest.model.open", Animation.LoopType.HOLD_ON_LAST_FRAME));
        }else {
            state.getController().setAnimation(RawAnimation.begin().then("jewellery_chest.model.close", Animation.LoopType.HOLD_ON_LAST_FRAME));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return geoCache;
    }
}
