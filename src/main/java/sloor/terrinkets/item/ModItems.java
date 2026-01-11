package sloor.terrinkets.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import sloor.terrinkets.Terrinkets;

public class ModItems {
    // Add srhink ray to make player half size and add rocket boots from terraria and lucky horseshoe
    // Register Hermes Boots
    public static final Item HERMES_BOOTS = registerItem("hermes_boots", HermesBootsItem::new);
    public static final Item HEART_CRYSTAL = registerItem("heart_crystal", HeartCrystalItem::new);
    public static final Item BONE_EXTENSIONS = registerItem("bone_extensions", BoneExtensionsItem::new);
    public static final Item DRILL_BIT = registerItem("drill_bit", DrillBitItem::new);
    public static final Item SUPER_RABBITS_FOOT = registerItem("super_rabbits_foot", SuperRabbitsFootItem::new);
    public static final Item CLOUD_IN_A_BOTTLE = registerItem("cloud_in_a_bottle", CloudInABottleItem::new);

    private static Item registerItem(String name, java.util.function.Function<Item.Settings, Item> itemFactory) {
        // Fix: Use Registries.ITEM and Identifier.of()
        Identifier id = Identifier.of(Terrinkets.MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);

        // Create settings with the required Key
        Item.Settings settings = new Item.Settings().registryKey(key);

        return Registry.register(Registries.ITEM, key, itemFactory.apply(settings));
    }

    public static void registerModItems() {
        Terrinkets.LOGGER.info("Registering Mod Items for " + Terrinkets.MOD_ID);
    }
}
