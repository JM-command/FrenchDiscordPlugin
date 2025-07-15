package ch.jmcommand.frenchdiscordplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cSeuls les joueurs peuvent utiliser cette commande.");
            return true;
        }

        if (!player.hasPermission("frenchdiscord.gamemode")) {
            player.sendMessage("§cTu n'as pas la permission d'utiliser cette commande.");
            return true;
        }

        GameMode gm = switch (label.toLowerCase()) {
            case "gms" -> GameMode.SURVIVAL;
            case "gmc" -> GameMode.CREATIVE;
            case "gma" -> GameMode.ADVENTURE;
            case "gmsp" -> GameMode.SPECTATOR;
            default -> null;
        };

        if (gm == null) {
            player.sendMessage("§cGamemode inconnu.");
            return true;
        }

        if (args.length == 0) {
            player.setGameMode(gm);
            player.sendMessage("§aTon gamemode a été changé en §e" + gm.name());
        } else if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage("§cLe joueur " + args[0] + " n'est pas connecté.");
                return true;
            }
            target.setGameMode(gm);
            target.sendMessage("§aTon gamemode a été changé en §e" + gm.name() + " §apar " + player.getName());
            player.sendMessage("§aGamemode de §e" + target.getName() + " §achangé en §e" + gm.name());
        } else {
            player.sendMessage("§cUsage : /" + label + " [pseudo]");
        }

        return true;
    }
}
