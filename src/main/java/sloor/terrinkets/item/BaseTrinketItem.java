package sloor.terrinkets.item;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class BaseTrinketItem extends Item {
    public static final List<BaseTrinketItem> ALL_TRINKETS = new ArrayList<>();

    public BaseTrinketItem(Settings settings) {
        super(settings.maxCount(1)); // Trinkets shouldn't stack.

        ALL_TRINKETS.add(this);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, java.util.function.Consumer<Text> textConsumer, TooltipType type) {
        // 1. Add the specific item description (from en_us.json)
        textConsumer.accept(Text.translatable(this.getTranslationKey() + ".tooltip")
                .formatted(Formatting.GRAY));

        // 2. Add a flavor text line for all trinkets
        textConsumer.accept(Text.translatable("tooltip.terrinkets.equippable")
                .formatted(Formatting.DARK_PURPLE, Formatting.ITALIC));
    }

    // Called when the player HAS the item
    public abstract void grantBenefits(PlayerEntity player);

    // Called when the player DOES NOT have the item
    public abstract void revokeBenefits(PlayerEntity player);
}
