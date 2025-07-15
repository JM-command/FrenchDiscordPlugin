package ch.jmcommand.frenchdiscordplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements CommandExecutor {

    private final List<List<String>> pages = new ArrayList<>();

    public HelpCommand() {
        buildPages();
    }

    private void buildPages() {
        // Page 1 - Informations
        List<String> page1 = new ArrayList<>();
        page1.add("§b➤ §a[Page 1/5] Informations");
        page1.add("§e/discord §7- Affiche le lien du Discord");
        page1.add("§e/site §7- Affiche le site internet");
        page1.add("§e/rules §7- Affiche les règles");
        page1.add("§e/help §7- Affiche cette aide");
        page1.add("§e/aide §7- Alias de /help");
        pages.add(page1);

        // Page 2 - Boutique
        List<String> page2 = new ArrayList<>();
        page2.add("§b➤ §a[Page 2/5] Boutique");
        page2.add("§e/boutique §7- Ouvre la boutique pour acheter des permissions");
        pages.add(page2);

        // Page 3 - Utilitaires
        List<String> page3 = new ArrayList<>();
        page3.add("§b➤ §a[Page 3/5] Commandes Utilitaires");
        page3.add("§e/gms §7- Gamemode Survival");
        page3.add("§e/gmc §7- Gamemode Creative");
        page3.add("§e/gma §7- Gamemode Adventure");
        page3.add("§e/gmsp §7- Gamemode Spectator");
        page3.add("§e/heal §7- Soigne le joueur");
        page3.add("§e/feed §7- Nourrit le joueur");
        page3.add("§e/ec §7- Ouvre l'Ender Chest");
        page3.add("§e/craft §7- Ouvre une table de craft");
        page3.add("§e/furnace §7- Transforme l'item tenu en version cuite");
        page3.add("§e/anvil §7- Ouvre une enclume virtuelle");
        page3.add("§e/compress §7- Comprime les blocs (future fonctionnalité)");
        pages.add(page3);

        // Page 4 - Survie
        List<String> page4 = new ArrayList<>();
        page4.add("§b➤ §a[Page 4/5] Commandes Survie");
        page4.add("§e/spawn §7- Téléporte au spawn");
        page4.add("§e/sethome [nom] §7- Enregistre un home");
        page4.add("§e/home [nom] §7- Téléporte à ton home");
        page4.add("§e/back §7- Retourne à ta dernière position");
        pages.add(page4);

        // Page 5 - Staff
        List<String> page5 = new ArrayList<>();
        page5.add("§b➤ §a[Page 5/5] Commandes Staff");
        page5.add("§e/promoteplayer <pseudo> §7- Passe un visiteur en joueur");
        page5.add("§e/pluginupdate <tag> §7- Met à jour le plugin depuis GitHub");
        pages.add(page5);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("frenchdiscord.help")) {
            sender.sendMessage("§cTu n'as pas la permission d'utiliser cette commande.");
            return true;
        }

        int page = 1;
        if (args.length > 0) {
            try {
                page = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                sender.sendMessage("§cUsage : /help [page]");
                return true;
            }
        }

        int totalPages = sender.hasPermission("frenchdiscord.staff") ? pages.size() : pages.size() - 1;

        if (page < 1 || page > totalPages) {
            sender.sendMessage("§cPage invalide. Utilise /help [1-" + totalPages + "]");
            return true;
        }

        List<String> content = pages.get(page - 1);

        // Staff check
        if (page == 5 && !sender.hasPermission("frenchdiscord.staff")) {
            sender.sendMessage("§cTu n'as pas la permission d'accéder à cette page.");
            return true;
        }

        for (String line : content) {
            sender.sendMessage(line);
        }

        if (page < totalPages) {
            sender.sendMessage("§7➤ Tape §e/help " + (page + 1) + " §7pour la page suivante.");
        }

        return true;
    }
}
