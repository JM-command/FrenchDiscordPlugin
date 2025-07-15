package ch.jmcommand.frenchdiscordplugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class KeepInventoryListener implements Listener {

    private final HashMap<UUID, ItemStack[]> inventories = new HashMap<>();
    private final HashMap<UUID, ItemStack[]> armors = new HashMap<>();

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if (player.hasPermission("frenchdiscord.keepinv")) {
            inventories.put(player.getUniqueId(), player.getInventory().getContents());
            armors.put(player.getUniqueId(), player.getInventory().getArmorContents());
            event.getDrops().clear();
            event.setKeepLevel(true);
            event.setDroppedExp(0);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        if (inventories.containsKey(player.getUniqueId())) {
            Bukkit.getScheduler().runTaskLater(
                    ch.jmcommand.frenchdiscordplugin.FrenchDiscordPlugin.getInstance(),
                    () -> {
                        player.getInventory().setContents(inventories.get(player.getUniqueId()));
                        player.getInventory().setArmorContents(armors.get(player.getUniqueId()));
                        inventories.remove(player.getUniqueId());
                        armors.remove(player.getUniqueId());
                    },
                    1L
            );
        }
    }
}
