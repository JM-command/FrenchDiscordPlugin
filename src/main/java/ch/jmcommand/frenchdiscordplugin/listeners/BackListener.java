package ch.jmcommand.frenchdiscordplugin.listeners;

import ch.jmcommand.frenchdiscordplugin.commands.BackCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.entity.Player;

public class BackListener implements Listener {

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (event.getFrom() != null && !event.getFrom().equals(event.getTo())) {
            BackCommand.saveLastLocation(player);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        BackCommand.saveLastLocation(player);
    }
}
