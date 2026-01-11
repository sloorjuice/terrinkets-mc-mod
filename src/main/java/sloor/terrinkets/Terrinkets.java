package sloor.terrinkets;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.map.MapDecorationTypes;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.gen.structure.Structure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sloor.terrinkets.item.ModItems;

public class Terrinkets implements ModInitializer {
	public static final String MOD_ID = "terrinkets";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final TagKey<Structure> TRINKET_SHRINE_TAG = TagKey.of(
			RegistryKeys.STRUCTURE,
			Identifier.of(MOD_ID, "trinket_shrine")
	);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Terrinkets Mod");

		ModItems.registerModItems();
		TrinketTickHandler.register();

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
			content.add(ModItems.HERMES_BOOTS);
			content.add(ModItems.BONE_EXTENSIONS);
			content.add(ModItems.HEART_CRYSTAL);
			content.add(ModItems.DRILL_BIT);
			content.add(ModItems.SUPER_RABBITS_FOOT);
		});

		LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
			if (source.isBuiltin() && (
					LootTables.VILLAGE_CARTOGRAPHER_CHEST.equals(key) ||
							LootTables.DESERT_PYRAMID_CHEST.equals(key) ||
							LootTables.ABANDONED_MINESHAFT_CHEST.equals(key))) {

				LootPool.Builder poolBuilder = LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0f))
						.conditionally(RandomChanceLootCondition.builder(0.2f))
						.with(LootTableEntry.builder(RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(MOD_ID, "trinket_shrine_map"))));

				tableBuilder.pool(poolBuilder);
			}
		});

		// Villager Trades - Corrected for 1.21.1
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.CARTOGRAPHER, 2, factories -> {
			factories.add(new TradeOffers.SellMapFactory(
					13,                              // Price (13 emeralds)
					TRINKET_SHRINE_TAG,              // Structure Tag
					"filled_map.trinket_shrine",      // Display name key
					MapDecorationTypes.TARGET_POINT, // Map Icon
					12,                              // Max uses
					5                                // Villager XP
			));
		});
	}
}