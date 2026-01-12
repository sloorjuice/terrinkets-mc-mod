package sloor.terrinkets.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

public class RegenNecklaceItem extends BaseTrinketItem {
    public RegenNecklaceItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void grantBenefits(PlayerEntity player) {
        // Only run if the player is alive and hurt
        if (player.getHealth() > 0 && player.getHealth() < player.getMaxHealth()) {

            int foodLevel = player.getHungerManager().getFoodLevel();
            float health = player.getHealth();
            float maxHealth = player.getMaxHealth();

            // NEW LOGIC:
            // If you are starving (under 10 food), you ONLY regen if you are below half health.
            // If you have food (above 10), you ALWAYS regen.
            boolean shouldRegen = (foodLevel > 10) || (health < maxHealth / 2);

            if (shouldRegen) {
                // SPEED BOOST:
                // Every 20 ticks = 1 second.
                // Every 10 ticks = 0.5 seconds.
                // Let's go with 15 ticks (~0.75 seconds) for a very fast "Terraria" feel.
                if (player.getWorld().getTime() % 15 == 0) {
                    player.heal(1.0f); // Heal half a heart

                    // Visual feedback so you know it's the necklace working
                    if (player.getWorld().getTime() % 30 == 0) {
                        player.getHungerManager().addExhaustion(0.05f);
                    }
                }
            }
        }
    }

    @Override
    public void revokeBenefits(PlayerEntity player) {
    }
}