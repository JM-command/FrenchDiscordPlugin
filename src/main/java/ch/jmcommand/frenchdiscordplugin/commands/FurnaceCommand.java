package ch.jmcommand.frenchdiscordplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

public class FurnaceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cSeuls les joueurs peuvent utiliser cette commande.");
            return true;
        }

        if (!player.hasPermission("frenchdiscord.furnace")) {
            player.sendMessage("§cTu n'as pas la permission d'utiliser cette commande.");
            return true;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item == null || item.getType() == Material.AIR) {
            player.sendMessage("§cTu ne tiens rien dans ta main !");
            return true;
        }

        Material cooked = findSmeltedMaterial(item.getType());
        if (cooked == null) {
            player.sendMessage("§cCet objet ne peut pas être cuit !");
            return true;
        }

        item.setType(cooked);
        player.sendMessage("§aTon objet a été cuit en §e" + cooked.name() + "§a !");
        return true;
    }

    private Material findSmeltedMaterial(Material input) {
        for (Recipe recipe : Bukkit.getServer().getRecipesFor(new ItemStack(input))) {
            if (recipe instanceof FurnaceRecipe furnaceRecipe) {
                ItemStack result = furnaceRecipe.getResult();
                if (result != null && result.getType() != Material.AIR) {
                    return result.getType();
                }
            }
        }
        return null;
    }
}
