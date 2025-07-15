package ch.jmcommand.frenchdiscordplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cSeuls les joueurs peuvent utiliser cette commande.");
            return true;
        }

        if (!player.hasPermission("frenchdiscord.heal")) {
            player.sendMessage("§cTu n'as pas la permission d'utiliser cette commande.");
            return true;
        }

        if (args.length == 0) {
            player.setHealth(player.getMaxHealth());
            player.sendMessage("§aTu as été soigné !");
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage("§cLe joueur " + args[0] + " n'est pas connecté.");
                return true;
            }
            target.setHealth(target.getMaxHealth());
            target.sendMessage("§aTu as été soigné par " + player.getName());
            player.sendMessage("§aTu as soigné " + target.getName());
        }

        return true;
    }
}
