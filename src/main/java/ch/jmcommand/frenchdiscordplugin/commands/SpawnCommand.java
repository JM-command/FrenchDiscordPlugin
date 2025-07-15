package ch.jmcommand.frenchdiscordplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cCommande uniquement pour les joueurs !");
            return true;
        }

        if (!player.hasPermission("frenchdiscord.spawn")) {
            player.sendMessage("§cTu n'as pas la permission d'utiliser /spawn !");
            return true;
        }

        World world = Bukkit.getWorld("world");
        if (world == null) {
            player.sendMessage("§cMonde introuvable !");
            return true;
        }

        Location spawn = world.getSpawnLocation();
        player.teleport(spawn);
        player.sendMessage("§aTu as été téléporté au spawn !");
        return true;
    }
}
