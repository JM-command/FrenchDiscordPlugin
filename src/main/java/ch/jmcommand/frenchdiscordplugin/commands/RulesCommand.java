package ch.jmcommand.frenchdiscordplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RulesCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("§e➤ Règles du serveur :");
        sender.sendMessage("§7- Pas d'insultes ni de toxicité.");
        sender.sendMessage("§7- Pas de cheat ou de triche.");
        sender.sendMessage("§7- Respectez les autres joueurs.");
        sender.sendMessage("§7- Amusez-vous !");
        return true;
    }
}
