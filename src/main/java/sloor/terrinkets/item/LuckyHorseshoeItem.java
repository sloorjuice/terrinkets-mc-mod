package sloor.terrinkets.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

public class LuckyHorseshoeItem extends BaseTrinketItem {
    public LuckyHorseshoeItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void grantBenefits(PlayerEntity player) {
        // Reset the fall distance every tick.
        // This makes the player immune to fall damage.
        if (player.fallDistance > 0) {
            player.fallDistance = 0;
        }
    }

    @Override
    public void revokeBenefits(PlayerEntity player) {
        // Nothing needed here
    }
}