package net.kaupenjoe.mccourse.entity.custom;

import net.kaupenjoe.mccourse.entity.ModEntities;
import net.kaupenjoe.mccourse.item.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.scoreboard.AbstractTeam;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class TigerEntity extends TameableEntity implements Mount, IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public TigerEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.setTamed(false);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return TameableEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 28.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.tiger.walk", true));
            return PlayState.CONTINUE;
        }

        if (this.isSitting()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.tiger.sitting", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.tiger.idle", true));
        return PlayState.CONTINUE;
    }

    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new SitGoal(this)); // Important that the Sit Goal is higher than WanderGoal!
        this.targetSelector.add(2, new TrackOwnerAttackerGoal(this));
        this.targetSelector.add(3, new AnimalMateGoal(this, 1d));
        this.goalSelector.add(2, new WanderAroundPointOfInterestGoal(this, 0.75f, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.75f, 1));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == Items.BEEF;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<TigerEntity>(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.TIGER.create(world);
    }

    /* TAMEABLE */
    private static final TrackedData<Boolean> SITTING =
            DataTracker.registerData(TigerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getStackInHand(hand);
        Item item = itemstack.getItem();
        Item tameableItem = ModItems.TURNIP;

        if (this.world.isClient()) {
            boolean flag = this.isOwner(player) || this.isTamed() || item == tameableItem
                    && !this.isTamed();
            return flag ? ActionResult.CONSUME : ActionResult.PASS;
        } else {
            if (this.isTamed()) {
                if (player.isSneaking() && hand == Hand.MAIN_HAND) {
                    setSitting(!isSitting());
                }

                if (this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
                    if (!player.getAbilities().creativeMode) {
                        itemstack.decrement(1);
                    }

                    this.heal((float) item.getFoodComponent().getHunger());
                    return ActionResult.SUCCESS;
                }
                player.startRiding(this);
            } else if (item == tameableItem && !this.isOnFire()) {
                if (!player.getAbilities().creativeMode) {
                    itemstack.decrement(1);
                }

                if (this.random.nextInt(3) == 0) {
                    super.setOwner(player);
                    this.navigation.recalculatePath();
                    this.setTarget(null);
                    this.world.sendEntityStatus(this, (byte) 7);
                    setSitting(true);
                } else {
                    this.world.sendEntityStatus(this, (byte) 6);
                }
                return ActionResult.SUCCESS;
            }

            return super.interactMob(player, hand);
        }
    }

    public void setSitting(boolean sitting) {
        this.dataTracker.set(SITTING, sitting);
        super.setSitting(sitting);
    }

    public boolean isSitting() {
        return this.dataTracker.get(SITTING);
    }

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);
        if (tamed) {
            getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(60.0D);
            getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(4D);
            getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35D);
        } else {
            getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(30.0D);
            getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(2D);
            getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.25D);
        }
    }

    @Override
    public AbstractTeam getScoreboardTeam() {
        return super.getScoreboardTeam();
    }

    public boolean canBeLeashedBy(PlayerEntity player) {
        return false;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SITTING, false);
    }

    /* RIDEABLE */
    @Override
    public boolean canBeControlledByRider() {
        return this.getPrimaryPassenger() instanceof LivingEntity;
    }

    @Override
    @Nullable
    public Entity getPrimaryPassenger() {
        return this.getFirstPassenger();
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (!this.isAlive()) {
            return;
        }

        if (!(this.hasPassengers() && this.canBeControlledByRider())) {
            this.airStrafingSpeed = 0.02f;
            super.travel(movementInput);
            return;
        }

        LivingEntity livingEntity = (LivingEntity) this.getPrimaryPassenger();
        this.setYaw(livingEntity.getYaw());
        this.prevYaw = this.getYaw();
        this.setPitch(livingEntity.getPitch() * 0.5f);
        this.setRotation(this.getYaw(), this.getPitch());
        this.headYaw = this.bodyYaw = this.getYaw();
        float f = livingEntity.sidewaysSpeed * 0.5f;
        float g = livingEntity.forwardSpeed;
        if (g <= 0.0f) {
            g *= 0.25f;
        }

        if (this.isLogicalSideForUpdatingMovement()) {
            this.setMovementSpeed((float) this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
            super.travel(new Vec3d(f, movementInput.y, g));
        } else if (livingEntity instanceof PlayerEntity) {
            this.setVelocity(Vec3d.ZERO);
        }

        this.updateLimbs(this, false);
        this.tryCheckBlockCollision();
    }

    @Override
    public Vec3d updatePassengerForDismount(LivingEntity passenger) {
        Vec3d vec3d = getPassengerDismountOffset(this.getWidth(), passenger.getWidth(),
                this.getYaw() + (passenger.getMainArm() == Arm.RIGHT ? 90.0f : -90.0f));
        Vec3d vec3d2 = this.locateSafeDismountingPos(vec3d, passenger);
        if (vec3d2 != null) {
            return vec3d2;
        }
        Vec3d vec3d3 = getPassengerDismountOffset(this.getWidth(), passenger.getWidth(),
                this.getYaw() + (passenger.getMainArm() == Arm.LEFT ? 90.0f : -90.0f));
        Vec3d vec3d4 = this.locateSafeDismountingPos(vec3d3, passenger);
        if (vec3d4 != null) {
            return vec3d4;
        }
        return this.getPos();
    }

    @Nullable
    private Vec3d locateSafeDismountingPos(Vec3d offset, LivingEntity passenger) {
        double d = this.getX() + offset.x;
        double e = this.getBoundingBox().minY;
        double f = this.getZ() + offset.z;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        block0:
        for (EntityPose entityPose : passenger.getPoses()) {
            mutable.set(d, e, f);
            double g = this.getBoundingBox().maxY + 0.75;
            do {
                Vec3d vec3d;
                Box box;
                double h = this.world.getDismountHeight(mutable);
                if ((double) mutable.getY() + h > g) continue block0;
                if (Dismounting.canDismountInBlock(h) && Dismounting.canPlaceEntityAt(this.world, passenger, (box = passenger.getBoundingBox(entityPose)).offset(vec3d = new Vec3d(d, (double) mutable.getY() + h, f)))) {
                    passenger.setPose(entityPose);
                    return vec3d;
                }
                mutable.move(Direction.UP);
            } while ((double) mutable.getY() < g);
        }
        return null;
    }
}
