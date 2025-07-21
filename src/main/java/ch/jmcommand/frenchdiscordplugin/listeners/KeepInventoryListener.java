package ch.jmcommand.frenchdiscordplugin.listeners;

import ch.jmcommand.frenchdiscordplugin.FrenchDiscordPlugin;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KeepInventoryListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        // Si le joueur a la permission, on empêche la perte d'inventaire
        if (player.hasPermission("frenchdiscord.keepinv") && player.getGameMode() == GameMode.SURVIVAL) {
            event.setKeepInventory(true);
            event.getDrops().clear();

            event.setKeepLevel(true);
            event.setDroppedExp(0);
        }
    }
}
