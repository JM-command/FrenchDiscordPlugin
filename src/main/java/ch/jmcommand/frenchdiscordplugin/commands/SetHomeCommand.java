package ch.jmcommand.frenchdiscordplugin.commands;

import ch.jmcommand.frenchdiscordplugin.managers.HomeManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class SetHomeCommand implements CommandExecutor {

    private final HomeManager homeManager;

    public SetHomeCommand(HomeManager homeManager) {
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
            player.sendMessage("§cTu dois d'abord acheter un slot de home via la boutique !");
            return true;
        }
        Map<String, ?> homes = homeManager.getHomes(player.getUniqueId());
        if (homes.size() >= maxHomes) {
            player.sendMessage("§cTu as atteint la limite de " + maxHomes + " homes !");
            return true;
        }

        String name = (args.length > 0) ? args[0] : "home";
        homeManager.setHome(player.getUniqueId(), name, player.getLocation());
        player.sendMessage("§aHome §e" + name + " §acréé !");
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
