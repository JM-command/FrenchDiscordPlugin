package ch.jmcommand.frenchdiscordplugin.commands;

import ch.jmcommand.frenchdiscordplugin.managers.HomeManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelHomeCommand implements CommandExecutor {

    private final HomeManager homeManager;

    public DelHomeCommand(HomeManager homeManager) {
        this.homeManager = homeManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cCommande joueurs uniquement !");
            return true;
        }

        String name = (args.length > 0) ? args[0] : "home";

        if (homeManager.removeHome(player.getUniqueId(), name)) {
            player.sendMessage("§aHome §e" + name + " §asupprimé !");
        } else {
            player.sendMessage("§cAucun home nommé §e" + name + " §ctrouvé !");
        }

        return true;
    }
}
