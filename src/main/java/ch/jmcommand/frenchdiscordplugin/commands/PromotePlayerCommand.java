package ch.jmcommand.frenchdiscordplugin.commands;

import ch.jmcommand.frenchdiscordplugin.utils.LuckPermsHook;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PromotePlayerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("frenchdiscord.staff")) {
            sender.sendMessage("§cTu n'as pas la permission d'utiliser cette commande.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("§cUsage : /promoteplayer <pseudo>");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            sender.sendMessage("§cJoueur introuvable !");
            return true;
        }

        // Vérifie que LuckPerms est chargé
        if (LuckPermsHook.getLuckPerms() == null) {
            sender.sendMessage("§cErreur : LuckPerms n’est pas chargé !");
            return true;
        }

        // Applique les changements
        LuckPermsHook.setGroup(target, "joueur");
        target.setGameMode(GameMode.SURVIVAL);
        target.sendMessage("§aTu es passé joueur !");
        sender.sendMessage("§a" + target.getName() + " est maintenant joueur !");
        return true;
    }
}
