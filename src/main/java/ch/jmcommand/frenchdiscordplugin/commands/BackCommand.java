package ch.jmcommand.frenchdiscordplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class BackCommand implements CommandExecutor {

    public static final HashMap<UUID, Location> lastLocations = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cCommande uniquement pour les joueurs !");
            return true;
        }

        if (!player.hasPermission("frenchdiscord.back")) {
            player.sendMessage("§cTu n'as pas la permission d'utiliser /back !");
            return true;
        }

        Location loc = lastLocations.get(player.getUniqueId());
        if (loc == null) {
            player.sendMessage("§cAucune position enregistrée !");
            return true;
        }

        player.teleport(loc);
        player.sendMessage("§aTu as été téléporté à ta dernière position.");
        return true;
    }

    public static void saveLastLocation(Player player) {
        lastLocations.put(player.getUniqueId(), player.getLocation());
    }
}
