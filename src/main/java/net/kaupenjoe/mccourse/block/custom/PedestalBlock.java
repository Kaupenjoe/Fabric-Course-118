package net.kaupenjoe.mccourse.block.custom;

import net.kaupenjoe.mccourse.block.entity.PedestalBlockEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PedestalBlock extends BlockWithEntity {
    public PedestalBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PedestalBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player,
                              Hand hand, BlockHitResult result) {
        BlockEntity tile = world.getBlockEntity(pos);
        if (tile instanceof PedestalBlockEntity pedestal) {
            ItemStack itemInPedestal = pedestal.inventory.get(0);

            if (itemInPedestal.isEmpty()) {
                ItemStack heldItem = player.getStackInHand(hand);

                if (!heldItem.isEmpty()) {
                    setPedestalToPlayerItem(player.getStackInHand(hand).copy(), pedestal.inventory);
                    player.getStackInHand(hand).decrement(1);

                    world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                }
            }
            else {
                spawnItemFromPedestal(world, player, itemInPedestal);
                deletePedestalItem(pedestal.inventory);
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof PedestalBlockEntity) {
                ItemScatterer.spawn(world, pos, (PedestalBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    private void spawnItemFromPedestal(World world, PlayerEntity player, ItemStack itemInPedestal) {
        ItemEntity item = new ItemEntity(world, player.getX(), player.getY(), player.getZ(), itemInPedestal.copy());
        item.resetPickupDelay();
        world.spawnEntity(item);
    }

    private void setPedestalToPlayerItem(ItemStack stack, DefaultedList<ItemStack> pedestalInventory) {
        stack.setCount(1);
        pedestalInventory.set(0, stack);
    }

    private void deletePedestalItem(DefaultedList<ItemStack> pedestalInventory) {
        setPedestalToPlayerItem(ItemStack.EMPTY, pedestalInventory);
    }
}