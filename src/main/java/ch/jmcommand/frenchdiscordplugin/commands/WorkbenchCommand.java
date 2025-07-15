package ch.jmcommand.frenchdiscordplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorkbenchCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cSeuls les joueurs peuvent utiliser cette commande.");
            return true;
        }

        if (!player.hasPermission("frenchdiscord.craft")) {
            player.sendMessage("§cTu n'as pas la permission d'utiliser cette commande.");
            return true;
        }

        player.openWorkbench(null, true);
        player.sendMessage("§aTable de craft ouverte !");
        return true;
    }
}
