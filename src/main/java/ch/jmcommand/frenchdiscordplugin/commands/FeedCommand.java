package ch.jmcommand.frenchdiscordplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cSeuls les joueurs peuvent utiliser cette commande.");
            return true;
        }

        if (!player.hasPermission("frenchdiscord.feed")) {
            player.sendMessage("§cTu n'as pas la permission d'utiliser cette commande.");
            return true;
        }

        if (args.length == 0) {
            player.setFoodLevel(20);
            player.setSaturation(20f);
            player.sendMessage("§aTu as été nourri !");
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage("§cLe joueur " + args[0] + " n'est pas connecté.");
                return true;
            }
            target.setFoodLevel(20);
            target.setSaturation(20f);
            target.sendMessage("§aTu as été nourri par " + player.getName());
            player.sendMessage("§aTu as nourri " + target.getName());
        }

        return true;
    }
}
