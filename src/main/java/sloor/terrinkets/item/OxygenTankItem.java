package sloor.terrinkets.item;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class OxygenTankItem extends BaseTrinketItem{
    private static final Identifier OXYGEN_TANK_ID = Identifier.of("terrinkets", "oxygen_tank");

    public OxygenTankItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void grantBenefits(PlayerEntity player) {
        EntityAttributeInstance oxygenBoost = player.getAttributeInstance(EntityAttributes.OXYGEN_BONUS);
        if (oxygenBoost != null && !oxygenBoost.hasModifier(OXYGEN_TANK_ID)) {
            oxygenBoost.addTemporaryModifier(new EntityAttributeModifier(
                    OXYGEN_TANK_ID, 8, EntityAttributeModifier.Operation.ADD_VALUE
            ));
        }
    }

    @Override
    public void revokeBenefits(PlayerEntity player) {
        EntityAttributeInstance oxygenBoost = player.getAttributeInstance(EntityAttributes.OXYGEN_BONUS);
        if (oxygenBoost != null && oxygenBoost.hasModifier(OXYGEN_TANK_ID)) {
            oxygenBoost.removeModifier(OXYGEN_TANK_ID);
        }
    }
}
