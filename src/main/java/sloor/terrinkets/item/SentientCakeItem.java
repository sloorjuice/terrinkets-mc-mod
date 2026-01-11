package sloor.terrinkets.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class SentientCakeItem extends BaseTrinketItem {
    public SentientCakeItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void grantBenefits(PlayerEntity player) {
        // 1. We now check every half-second (10 ticks) instead of every second
        if (player.getWorld().getTime() % 10 == 0) {

            // 2. We adjusted the chance to 1 in 150.
            // Since we check twice a second, this averages to once every 75 seconds.
            if (player.getRandom().nextInt(150) == 0) {

                // 3. Broaden the check: Feed if player is missing ANY hunger OR ANY saturation
                // isNotFull() only checks the visible 20 points, not the hidden saturation.
                if (player.getHungerManager().getFoodLevel() < 20) {

                    player.getHungerManager().add(5, 0.8f); // 2.5 shanks

                    player.sendMessage(Text.literal("The Sentient Cake shared a bite with you!")
                            .formatted(Formatting.LIGHT_PURPLE, Formatting.ITALIC), true);

                    player.playSound(net.minecraft.sound.SoundEvents.ENTITY_GENERIC_EAT.value(), 0.5f, 1.0f);

                    // 4. ADD PARTICLES: This is the best way to see it's working!
                    if (player.getWorld() instanceof net.minecraft.server.world.ServerWorld serverWorld) {
                        serverWorld.spawnParticles(
                                net.minecraft.particle.ParticleTypes.HEART,
                                player.getX(), player.getY() + 1.5, player.getZ(),
                                3, 0.2, 0.2, 0.2, 0.1
                        );
                    }
                }
            }
        }
    }

    @Override
    public void revokeBenefits(PlayerEntity player) {
    }
}