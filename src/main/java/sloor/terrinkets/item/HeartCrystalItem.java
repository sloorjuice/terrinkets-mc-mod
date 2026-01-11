package sloor.terrinkets.item;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class HeartCrystalItem extends BaseTrinketItem {
    private static final Identifier HEART_CRYSTAL_HEALTH_ID = Identifier.of("terrinkets", "heart_crystal_health");

    public HeartCrystalItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void grantBenefits(PlayerEntity player) {
        EntityAttributeInstance maxHealth = player.getAttributeInstance(EntityAttributes.MAX_HEALTH);
        if (maxHealth != null && !maxHealth.hasModifier(HEART_CRYSTAL_HEALTH_ID)) {
            // Operation: ADD_MULTIPLIED_BASE
            // Value 1.0 = +100% of base health (20 + 20 = 40 HP)
            maxHealth.addTemporaryModifier(new EntityAttributeModifier(
                    HEART_CRYSTAL_HEALTH_ID, 1.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
            ));
        }
        player.setHealth(40.0f);
    }

    @Override
    public void revokeBenefits(PlayerEntity player) {
        EntityAttributeInstance maxHealth = player.getAttributeInstance(EntityAttributes.MAX_HEALTH);
        if (maxHealth != null && maxHealth.hasModifier(HEART_CRYSTAL_HEALTH_ID)) {
            maxHealth.removeModifier(HEART_CRYSTAL_HEALTH_ID);

            // Safety Check: If current health is now higher than the new max,
            // Minecraft usually handles this, but this ensures no glitchy health bars.
            if (player.getHealth() > player.getMaxHealth()) {
                player.setHealth(player.getMaxHealth());
            }
        }
    }
}