package ch.jmcommand.frenchdiscordplugin.listeners;

import ch.jmcommand.frenchdiscordplugin.utils.LuckPermsHook;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        LuckPerms api = LuckPermsHook.getLuckPerms();
        if (api == null) {
            player.sendMessage("§c[FrenchDiscordPlugin] LuckPerms API indisponible.");
            return;
        }

        User user = api.getUserManager().getUser(player.getUniqueId());
        if (user == null) {
            player.sendMessage("§c[FrenchDiscordPlugin] Erreur lors de la récupération LuckPerms.");
            return;
        }

        boolean isVisitor = user.getCachedData().getPermissionData().checkPermission("frenchdiscord.visiteur").asBoolean();

        if (isVisitor) {
            player.setGameMode(GameMode.ADVENTURE);
            player.sendMessage("§e[FrenchDiscord] Tu es en mode Visiteur (Adventure).");
            player.sendMessage("§e[FrenchDiscord] Tu peux rejoindre le serveur en mode Survie en utilisant la commande /link.");
        } else {
            player.setGameMode(GameMode.SURVIVAL);
        }
    }
}
