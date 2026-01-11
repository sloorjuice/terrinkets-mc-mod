package sloor.terrinkets.item;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class DrillBitItem extends BaseTrinketItem {
    private static final Identifier DRILL_BIT_ID = Identifier.of("terrinkets", "drill_bit");

    public DrillBitItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void grantBenefits(PlayerEntity player) {
        EntityAttributeInstance drillSpeed = player.getAttributeInstance(EntityAttributes.BLOCK_BREAK_SPEED);
        if (drillSpeed != null && !drillSpeed.hasModifier(DRILL_BIT_ID)) {
            drillSpeed.addTemporaryModifier(new EntityAttributeModifier(
                    DRILL_BIT_ID, 5.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
            ));
        }
    }

    @Override
    public void revokeBenefits(PlayerEntity player) {
        EntityAttributeInstance drillSpeed = player.getAttributeInstance(EntityAttributes.BLOCK_BREAK_SPEED);
        if (drillSpeed != null && drillSpeed.hasModifier(DRILL_BIT_ID)) {
            drillSpeed.removeModifier(DRILL_BIT_ID);
        }
    }
}
