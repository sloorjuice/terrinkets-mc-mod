package sloor.terrinkets.item;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class SuperRabbitsFootItem extends BaseTrinketItem {
    private static final Identifier RABBITS_FOOT_ID = Identifier.of("terrinkets", "rabbits_foot");

    public SuperRabbitsFootItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void grantBenefits(PlayerEntity player) {
        EntityAttributeInstance jumpHeight = player.getAttributeInstance(EntityAttributes.JUMP_STRENGTH);
        if (jumpHeight != null && !jumpHeight.hasModifier(RABBITS_FOOT_ID)) {
            jumpHeight.addTemporaryModifier(new EntityAttributeModifier(
                    RABBITS_FOOT_ID, 0.45, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
            ));
        }
    }

    @Override
    public void revokeBenefits(PlayerEntity player) {
        EntityAttributeInstance jumpHeight = player.getAttributeInstance(EntityAttributes.JUMP_STRENGTH);
        if (jumpHeight != null && jumpHeight.hasModifier(RABBITS_FOOT_ID)) {
            jumpHeight.removeModifier(RABBITS_FOOT_ID);
        }
    }
}
