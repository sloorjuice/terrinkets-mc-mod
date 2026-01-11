package sloor.terrinkets.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

public class CloudInABottleItem extends BaseTrinketItem {
    public CloudInABottleItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void grantBenefits(PlayerEntity player) {
        // We handle the logic in a Mixin, but you could set a custom
        // "canDoubleJump" variable here if using a custom component.
    }

    @Override
    public void revokeBenefits(PlayerEntity player) {
    }
}