package ch.jmcommand.frenchdiscordplugin.commands;

import ch.jmcommand.frenchdiscordplugin.utils.LuckPermsHook;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BoutiqueCommand implements CommandExecutor, Listener {

    private final LuckPerms luckPerms;

    public BoutiqueCommand(LuckPerms luckPerms) {
        this.luckPerms = luckPerms;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cCommande uniquement pour les joueurs.");
            return true;
        }

        Inventory gui = Bukkit.createInventory(null, 27, "§8Boutique");

        // KeepInventory
        ItemStack keepinv = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta meta = keepinv.getItemMeta();
        meta.setDisplayName("§aKeep Inventory");
        meta.setLore(List.of(
                "§7Prix : 35 Totems",
                "§eClique pour acheter"
        ));
        keepinv.setItemMeta(meta);
        gui.setItem(10, keepinv);

        // /spawn
        ItemStack spawn = new ItemStack(Material.ENDER_PEARL);
        meta = spawn.getItemMeta();
        meta.setDisplayName("§bCommande /spawn");
        meta.setLore(List.of(
                "§7Prix : 10 Diamants",
                "§eClique pour acheter"
        ));
        spawn.setItemMeta(meta);
        gui.setItem(12, spawn);

        player.openInventory(gui);
        return true;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        HumanEntity clicker = event.getWhoClicked();
        if (!(clicker instanceof Player player)) return;

        if (event.getView().getTitle().equals("§8Boutique")) {
            event.setCancelled(true);

            ItemStack clicked = event.getCurrentItem();
            if (clicked == null || clicked.getType() == Material.AIR) return;

            switch (clicked.getType()) {
                case TOTEM_OF_UNDYING -> buyKeepInv(player);
                case ENDER_PEARL -> buySpawn(player);
            }
            player.closeInventory();
        }
    }

    private void buyKeepInv(Player player) {
        int totemsNeeded = 35;
        if (countItems(player, Material.TOTEM_OF_UNDYING) < totemsNeeded) {
            player.sendMessage("§cTu n'as pas assez de Totems !");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1, 1);
            return;
        }

        removeItems(player, Material.TOTEM_OF_UNDYING, totemsNeeded);
        LuckPermsHook.givePermission(player, "frenchdiscord.keepinv");
        player.sendMessage("§aTu as débloqué le KeepInventory !");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
    }

    private void buySpawn(Player player) {
        int diamondsNeeded = 10;
        if (countItems(player, Material.DIAMOND) < diamondsNeeded) {
            player.sendMessage("§cTu n'as pas assez de Diamants !");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1, 1);
            return;
        }

        removeItems(player, Material.DIAMOND, diamondsNeeded);
        LuckPermsHook.givePermission(player, "frenchdiscord.spawn");
        player.sendMessage("§aTu as débloqué la commande /spawn !");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
    }

    private int countItems(Player player, Material mat) {
        int total = 0;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == mat) {
                total += item.getAmount();
            }
        }
        return total;
    }

    private void removeItems(Player player, Material mat, int amount) {
        int remaining = amount;
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            ItemStack item = player.getInventory().getItem(i);
            if (item != null && item.getType() == mat) {
                int amt = item.getAmount();
                if (amt <= remaining) {
                    player.getInventory().setItem(i, null);
                    remaining -= amt;
                } else {
                    item.setAmount(amt - remaining);
                    player.getInventory().setItem(i, item);
                    break;
                }
            }
            if (remaining <= 0) break;
        }
    }
}
