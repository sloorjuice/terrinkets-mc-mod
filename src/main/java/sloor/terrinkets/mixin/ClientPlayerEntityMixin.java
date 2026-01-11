package sloor.terrinkets.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sloor.terrinkets.item.ModItems;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Unique
    private boolean canDoubleJump = false;

    @Unique
    private boolean lastJumping = false;

    @Unique
    private int ticksInAir = 0; // NEW: Tracks how long we've been falling

    @Inject(method = "tickMovement", at = @At("HEAD"))
    private void handleDoubleJump(CallbackInfo ci) {
        ClientPlayerEntity player = (ClientPlayerEntity) (Object) this;

        // Get jump key state
        boolean isJumping = player.input.playerInput.jump();

        // 1. GROUND CHECK: Reset everything when touching the floor
        if (player.isOnGround() || player.isClimbing() || player.isTouchingWater()) {
            this.canDoubleJump = true;
            this.ticksInAir = 0; // Reset timer
        }
        else {
            // If in air, count up
            this.ticksInAir++;
        }

        // 2. DOUBLE JUMP CHECK
        // Requirements:
        // - In the air
        // - Has item
        // - Fresh key press (!lastJumping)
        // - COOLDOWN: Must be in air for at least 5 ticks (prevents instant double-activation)
        if (!player.isOnGround()
                && isJumping
                && !this.lastJumping
                && this.ticksInAir >= 5) {

            if (this.canDoubleJump && !player.getAbilities().flying) {
                if (player.getInventory().contains(ModItems.CLOUD_IN_A_BOTTLE.getDefaultStack())) {
                    this.performDoubleJump(player);
                }
            }
        }

        // Update history for next tick
        this.lastJumping = isJumping;
    }

    @Unique
    private void performDoubleJump(ClientPlayerEntity player) {
        // Set Velocity
        Vec3d velocity = player.getVelocity();
        player.setVelocity(velocity.x, 0.5, velocity.z);

        // Particles
        MinecraftClient.getInstance().particleManager.addParticle(
                ParticleTypes.CLOUD,
                player.getX(), player.getY(), player.getZ(),
                0.0, -0.1, 0.0
        );

        // Consume jump
        this.canDoubleJump = false;
    }
}