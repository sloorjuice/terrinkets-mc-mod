package sloor.terrinkets.item;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseTrinketItem extends Item {
    public static final List<BaseTrinketItem> ALL_TRINKETS = new ArrayList<>();

    public BaseTrinketItem(Settings settings) {
        super(settings.maxCount(1)); // Trinkets shouldn't stack.

        ALL_TRINKETS.add(this);
    }

    // Called when the player HAS the item
    public abstract void grantBenefits(PlayerEntity player);

    // Called when the player DOES NOT have the item
    public abstract void revokeBenefits(PlayerEntity player);
}
