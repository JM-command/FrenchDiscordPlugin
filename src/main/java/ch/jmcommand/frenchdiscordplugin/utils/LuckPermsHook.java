package ch.jmcommand.frenchdiscordplugin.utils;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class LuckPermsHook {

    private static LuckPerms luckPerms;

    public static void setup() {
        RegisteredServiceProvider<LuckPerms> provider =
                Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            luckPerms = provider.getProvider();
        }
    }

    public static LuckPerms getLuckPerms() {
        return luckPerms;
    }

    public static String getPrefix(Player player) {
        if (luckPerms == null) return "";
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        if (user == null) return "";
        String prefix = user.getCachedData().getMetaData().getPrefix();
        return prefix != null ? prefix : "";
    }

    public static String getSuffix(Player player) {
        if (luckPerms == null) return "";
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        if (user == null) return "";
        String suffix = user.getCachedData().getMetaData().getSuffix();
        return suffix != null ? suffix : "";
    }

    public static void givePermission(Player player, String permission) {
        if (luckPerms == null) return;
        User user = luckPerms.getUserManager().loadUser(player.getUniqueId()).join();
        user.data().add(Node.builder(permission).build());
        luckPerms.getUserManager().saveUser(user);
    }

    public static void removePermission(Player player, String permission) {
        if (luckPerms == null) return;
        User user = luckPerms.getUserManager().loadUser(player.getUniqueId()).join();
        user.data().remove(Node.builder(permission).build());
        luckPerms.getUserManager().saveUser(user);
    }

    public static void setGroup(Player player, String group) {
        if (luckPerms == null) return;
        User user = luckPerms.getUserManager().loadUser(player.getUniqueId()).join();
        user.data().clear();
        user.data().add(Node.builder("group." + group).build());
        luckPerms.getUserManager().saveUser(user);
    }
}
