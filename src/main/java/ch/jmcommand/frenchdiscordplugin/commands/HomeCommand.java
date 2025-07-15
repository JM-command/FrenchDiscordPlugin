package ch.jmcommand.frenchdiscordplugin.commands;

import ch.jmcommand.frenchdiscordplugin.managers.HomeManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {

    private final HomeManager homeManager;

    public HomeCommand(HomeManager homeManager) {
        this.homeManager = homeManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cCommande joueurs uniquement !");
            return true;
        }

        int maxHomes = getMaxHomes(player);
        if (maxHomes == 0) {
            player.sendMessage("§cTu n'as aucun slot de home. Achète-en un dans la boutique !");
            return true;
        }

        String name = (args.length > 0) ? args[0] : "home";

        var loc = homeManager.getHome(player.getUniqueId(), name);
        if (loc == null) {
            player.sendMessage("§cHome introuvable !");
            return true;
        }

        player.teleport(loc);
        player.sendMessage("§aTéléporté au home §e" + name);
        return true;
    }

    private int getMaxHomes(Player player) {
        for (int i = 10; i >= 1; i--) {
            if (player.hasPermission("frenchdiscord.home.max." + i)) {
                return i;
            }
        }
        return 0;
    }
}
