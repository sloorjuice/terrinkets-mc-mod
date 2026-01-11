package sloor.terrinkets;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import sloor.terrinkets.item.BaseTrinketItem;

public class TrinketTickHandler {

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(TrinketTickHandler::onServerTick);
    }

    private static void onServerTick(MinecraftServer server) {
        // Loop through every player on the server
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {

            // Check every single trinket you created
            for (BaseTrinketItem trinket : BaseTrinketItem.ALL_TRINKETS) {
                if (hasItemInInventory(player, trinket)) {
                    trinket.grantBenefits(player);
                } else {
                    trinket.revokeBenefits(player);
                }
            }
        }
    }

    // Helper method to scan the player's inventory
    private static boolean hasItemInInventory(ServerPlayerEntity player, BaseTrinketItem item) {
        // This scans main inventory and offhand
        return player.getInventory().contains(item.getDefaultStack());

        // Note: .contains() works well for items without custom NBT data.
        // If you add complex NBT later, use a loop like:
        // return player.getInventory().main.stream().anyMatch(stack -> stack.isOf(item))
        //     || player.getInventory().offHand.stream().anyMatch(stack -> stack.isOf(item));
    }
}
