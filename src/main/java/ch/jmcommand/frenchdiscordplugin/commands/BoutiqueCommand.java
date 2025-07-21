package ch.jmcommand.frenchdiscordplugin.commands;

import ch.jmcommand.frenchdiscordplugin.utils.LuckPermsHook;
import net.luckperms.api.LuckPerms;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.*;
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

        Inventory gui = Bukkit.createInventory(null, 54, "§8Boutique");

        gui.setItem(10, createItem(Material.TOTEM_OF_UNDYING, "§aKeep Inventory", "§7Prix : 35 Totems", "§eClique pour acheter"));
        gui.setItem(12, createItem(Material.DIAMOND, "§bCommande /spawn", "§7Prix : 10 Diamants"));
        gui.setItem(14, createItem(Material.REDSTONE, "§cCommande /back", "§7Prix : 5 stacks de redstone"));
        gui.setItem(19, createItem(Material.COOKED_BEEF, "§6Commande /feed", "§7Prix : 32 steaks"));
        gui.setItem(21, createItem(Material.CRAFTING_TABLE, "§6Commande /craft", "§7Prix : 32 planches"));
        gui.setItem(23, createItem(Material.FURNACE, "§6Commande /furnace", "§7Prix : 32 cobblestone"));
        gui.setItem(25, createItem(Material.ENDER_CHEST, "§6Commande /ec", "§7Prix : 16 obsidian"));
        gui.setItem(28, createItem(Material.ANVIL, "§6Commande /anvil", "§7Prix : 20 blocs de fer"));

        // Homes
        gui.setItem(30, createItem(Material.EMERALD_BLOCK, "§2+1 Home (max 10)", "§7Prix : 3 stacks de blocs d'émeraude"));

        player.openInventory(gui);
        return true;
    }

    private ItemStack createItem(Material mat, String name, String... lore) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(List.of(lore));
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        if (!event.getView().getTitle().equals("§8Boutique")) return;

        event.setCancelled(true);
        ItemStack clicked = event.getCurrentItem();
        if (clicked == null || clicked.getType() == Material.AIR) return;

        switch (clicked.getType()) {
            case TOTEM_OF_UNDYING -> tryBuy(player, Material.TOTEM_OF_UNDYING, 35, "frenchdiscord.keepinv", "KeepInventory");
            case DIAMOND -> tryBuy(player, Material.DIAMOND, 10, "frenchdiscord.spawn", "/spawn");
            case REDSTONE -> tryBuy(player, Material.REDSTONE, 5 * 64, "frenchdiscord.back", "/back");
            case COOKED_BEEF -> tryBuy(player, Material.COOKED_BEEF, 32, "frenchdiscord.feed", "/feed");
            case CRAFTING_TABLE -> tryBuy(player, Material.OAK_PLANKS, 32, "frenchdiscord.craft", "/craft");
            case FURNACE -> tryBuy(player, Material.COBBLESTONE, 32, "frenchdiscord.furnace", "/furnace");
            case ENDER_CHEST -> tryBuy(player, Material.OBSIDIAN, 16, "frenchdiscord.ec", "/ec");
            case ANVIL -> tryBuy(player, Material.IRON_BLOCK, 20, "frenchdiscord.anvil", "/anvil");
            case EMERALD_BLOCK -> buyHomeSlot(player);
        }

        player.closeInventory();
    }

    private void tryBuy(Player player, Material material, int cost, String permission, String name) {
        if (countItems(player, material) < cost) {
            player.sendMessage("§cPas assez de " + material.name().toLowerCase() + " pour débloquer " + name + " !");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1, 1);
            return;
        }
        removeItems(player, material, cost);
        LuckPermsHook.givePermission(player, permission);
        player.sendMessage("§aTu as débloqué " + name + " !");
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
    }

    private void buyHomeSlot(Player player) {
        int currentMax = getCurrentHomeLimit(player);
        if (currentMax >= 10) {
            player.sendMessage("§cTu as déjà le maximum de homes !");
            return;
        }
        if (countItems(player, Material.EMERALD_BLOCK) < 3 * 64) {
            player.sendMessage("§cTu n'as pas assez de blocs d’émeraude !");
            return;
        }
        removeItems(player, Material.EMERALD_BLOCK, 3 * 64);
        String perm = "frenchdiscord.home.max." + (currentMax + 1);
        LuckPermsHook.givePermission(player, perm);
        player.sendMessage("§aTu as maintenant droit à §e" + (currentMax + 1) + " §ahomes !");
    }

    private int getCurrentHomeLimit(Player player) {
        for (int i = 10; i >= 1; i--) {
            if (player.hasPermission("frenchdiscord.home.max." + i)) return i;
        }
        return 0;
    }

    private int countItems(Player player, Material mat) {
        int total = 0;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == mat) total += item.getAmount();
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
                    break;
                }
            }
        }
    }
}
