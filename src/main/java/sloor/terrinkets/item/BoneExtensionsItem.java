package sloor.terrinkets.item;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class BoneExtensionsItem extends BaseTrinketItem {
    private static final Identifier EXTENSION_RANGE_ID = Identifier.of("terrinkets", "extension_range");

    // Define the modifier once so we can reuse it for removal
    // (Note: We recreate it in the method because 1.21 modifiers are simple data objects now)

    public BoneExtensionsItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void grantBenefits(PlayerEntity player) {
        EntityAttributeInstance blockRange = player.getAttributeInstance(EntityAttributes.BLOCK_INTERACTION_RANGE);
        EntityAttributeInstance entityRange = player.getAttributeInstance(EntityAttributes.ENTITY_INTERACTION_RANGE);

        // Increase by 3.0 blocks to make it feel powerful
        if (blockRange != null && !blockRange.hasModifier(EXTENSION_RANGE_ID)) {
            blockRange.addTemporaryModifier(new EntityAttributeModifier(
                    EXTENSION_RANGE_ID, 3.0, EntityAttributeModifier.Operation.ADD_VALUE
            ));
        }
        if (entityRange != null && !entityRange.hasModifier(EXTENSION_RANGE_ID)) {
            entityRange.addTemporaryModifier(new EntityAttributeModifier(
                    EXTENSION_RANGE_ID, 3.0, EntityAttributeModifier.Operation.ADD_VALUE
            ));
        }
    }

    @Override
    public void revokeBenefits(PlayerEntity player) {
        EntityAttributeInstance blockRange = player.getAttributeInstance(EntityAttributes.BLOCK_INTERACTION_RANGE);
        EntityAttributeInstance entityRange = player.getAttributeInstance(EntityAttributes.ENTITY_INTERACTION_RANGE);

        if (blockRange != null && blockRange.hasModifier(EXTENSION_RANGE_ID)) {
            blockRange.removeModifier(EXTENSION_RANGE_ID);
        }
        if (entityRange != null && entityRange.hasModifier(EXTENSION_RANGE_ID)) {
            entityRange.removeModifier(EXTENSION_RANGE_ID);
        }
    }
}
