package sloor.terrinkets.item;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class HermesBootsItem extends BaseTrinketItem {
    private static final Identifier HERMES_SPEED_ID = Identifier.of("terrinkets", "hermes_speed");

    // Define the modifier once so we can reuse it for removal
    // (Note: We recreate it in the method because 1.21 modifiers are simple data objects now)

    public HermesBootsItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void grantBenefits(PlayerEntity player) {
        EntityAttributeInstance speed = player.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
        if (speed != null && !speed.hasModifier(HERMES_SPEED_ID)) {
            speed.addTemporaryModifier(new EntityAttributeModifier(
                    HERMES_SPEED_ID, 0.1, EntityAttributeModifier.Operation.ADD_VALUE
            ));
        }
    }

    @Override
    public void revokeBenefits(PlayerEntity player) {
        EntityAttributeInstance speed = player.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
        // If the player still has the speed boost, REMOVE it
        if (speed != null && speed.hasModifier(HERMES_SPEED_ID)) {
            speed.removeModifier(HERMES_SPEED_ID);
        }
    }
}