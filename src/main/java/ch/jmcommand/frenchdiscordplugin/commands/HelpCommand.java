package ch.jmcommand.frenchdiscordplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("frenchdiscord.help")) {
            sender.sendMessage("§cTu n'as pas la permission d'utiliser cette commande.");
            return true;
        }

        sender.sendMessage("§b➤ §aFrenchDiscordPlugin - Liste des commandes :");
        sender.sendMessage("§e/discord §7- Affiche le Discord");
        sender.sendMessage("§e/site §7- Affiche le site");
        sender.sendMessage("§e/rules §7- Affiche les règles");
        sender.sendMessage("§e/gms §7- Gamemode Survival");
        sender.sendMessage("§e/gmc §7- Gamemode Creative");
        sender.sendMessage("§e/gma §7- Gamemode Adventure");
        sender.sendMessage("§e/gmsp §7- Gamemode Spectator");
        sender.sendMessage("§e/heal §7- Soigne le joueur");
        sender.sendMessage("§e/feed §7- Nourrit le joueur");
        sender.sendMessage("§e/ec §7- Ouvre l'Ender Chest");
        sender.sendMessage("§e/craft §7- Ouvre une table de craft");
        sender.sendMessage("§e/furnace §7- Ouvre un four virtuel");
        sender.sendMessage("§e/anvil §7- Ouvre une enclume virtuelle");
        sender.sendMessage("§e/compress §7- Fonction à venir !");
        sender.sendMessage("§e/promoteplayer <pseudo> §7- Passe un visiteur en joueur");

        return true;
    }
}
