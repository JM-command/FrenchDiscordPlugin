package ch.jmcommand.frenchdiscordplugin.commands;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ch.jmcommand.frenchdiscordplugin.utils.LuckPermsHook;

import java.util.UUID;

public class PromotePlayerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length != 1) {
            sender.sendMessage("§cUsage : /promoteplayer <pseudo>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("§cLe joueur " + args[0] + " n'est pas connecté.");
            return true;
        }

        LuckPerms api = LuckPermsHook.getLuckPerms();
        if (api == null) {
            sender.sendMessage("§cLuckPerms API non disponible !");
            return true;
        }

        User user = api.getUserManager().getUser(target.getUniqueId());
        if (user == null) {
            sender.sendMessage("§cImpossible de récupérer les données LuckPerms du joueur.");
            return true;
        }

        // Supprimer la permission frenchdiscord.visiteur
        Node node = PermissionNode.builder("frenchdiscord.visiteur").build();
        user.data().remove(node);

        // Sauvegarder les changements LuckPerms
        api.getUserManager().saveUser(user);

        // Changer gamemode
        target.setGameMode(GameMode.SURVIVAL);

        // Message au joueur
        target.sendMessage("§aTu es désormais un joueur. Bon jeu !");

        // Message au staff
        sender.sendMessage("§aLe joueur " + target.getName() + " est passé en mode joueur.");

        return true;
    }
}
