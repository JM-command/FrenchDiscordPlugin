package ch.jmcommand.frenchdiscordplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class AnvilCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cSeuls les joueurs peuvent utiliser cette commande.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("frenchdiscord.anvil")) {
            player.sendMessage("§cTu n'as pas la permission d'utiliser cette commande.");
            return true;
        }

        Inventory anvil = Bukkit.createInventory(null, InventoryType.ANVIL, "Enclume");
        player.openInventory(anvil);
        player.sendMessage("§aEnclume ouverte !");
        return true;
    }
}
