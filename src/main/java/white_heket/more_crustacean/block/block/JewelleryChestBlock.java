package white_heket.more_crustacean.block.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import white_heket.more_crustacean.block.entity.JewelleryChestBlockEntity;

public class JewelleryChestBlock extends Block implements Waterloggable,BlockEntityProvider {
    public static final BooleanProperty IS_OPEN;
    public static final DirectionProperty FACING;
    public static final BooleanProperty WATERLOGGED;
    public JewelleryChestBlock(Settings settings) {
        super(settings);
        this.setDefaultState(((((this.stateManager.getDefaultState()).with(FACING, Direction.NORTH))).with(WATERLOGGED, false).with(IS_OPEN,false)));
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (state.get(IS_OPEN) == false && isCanOpen(world,pos)) {
           world.setBlockState(pos,state.with(IS_OPEN,true));
            world.playSound(null,pos, SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, 1.0F);
        }else if (state.get(IS_OPEN) == true){
            world.setBlockState(pos,state.with(IS_OPEN, false));
            world.playSound(null,pos, SoundEvents.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, 1.0F);
        }
        return ActionResult.SUCCESS;
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        Direction direction = context.getHorizontalPlayerFacing().getOpposite();
        FluidState fluidState = context.getWorld().getFluidState(context.getBlockPos());
        return this.getDefaultState().with(FACING, direction).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER).with(IS_OPEN,false);
    }
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, WorldAccess world, BlockPos currentPos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, facing, neighborState, world, currentPos, neighborPos);
    }
    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }
    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, WATERLOGGED,IS_OPEN});
    }
    static {
        FACING = HorizontalFacingBlock.FACING;
        WATERLOGGED = Properties.WATERLOGGED;
        IS_OPEN = BooleanProperty.of("is_open");
    }
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new JewelleryChestBlockEntity(pos,state);
    }
    private static boolean isCanOpen(BlockView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        return !world.getBlockState(blockPos).isSolidBlock(world, blockPos);
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context){
        Direction direction = state.get(FACING);
        if (direction == Direction.NORTH || direction == Direction.SOUTH) {
            return VoxelShapes.cuboid(0.0625D, 0.0D, 0.125D, 0.9375D, 0.8125D, 0.875D);
        }
        return VoxelShapes.cuboid(0.125D, 0.0D, 0.0625D, 0.875D, 0.8125D, 0.9375D);
    }
}
