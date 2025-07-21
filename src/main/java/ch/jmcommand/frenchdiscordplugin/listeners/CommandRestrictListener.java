package ch.jmcommand.frenchdiscordplugin.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.entity.Player;

public class CommandRestrictListener implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String fullMessage = event.getMessage();
        String[] parts = fullMessage.split(" ");
        if (parts.length == 0) return;

        String commandLabel = parts[0].toLowerCase();
        String baseCommand = commandLabel.startsWith("/") ? commandLabel.substring(1) : commandLabel;

        // Vérifie si le joueur a la permission pour cette commande (plugin:commande ou juste commande)
        if (!player.hasPermission(baseCommand) && !player.isOp()) {
            event.setCancelled(true);
            player.sendMessage("§cCommande interdite sur ce serveur.");
            // Optionnel : log l'utilisation de la commande interdite
            System.out.println("Player " + player.getName() + " tried to use restricted command: " + baseCommand);
        }
    }
}
