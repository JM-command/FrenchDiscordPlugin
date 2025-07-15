package ch.jmcommand.frenchdiscordplugin.listeners;

import ch.jmcommand.frenchdiscordplugin.utils.LuckPermsHook;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        String prefixMini = LuckPermsHook.getPrefix(player);
        String suffixMini = LuckPermsHook.getSuffix(player);
        String rawMessage = translateHexColors(event.getMessage());

        MiniMessage mm = MiniMessage.miniMessage();

        String prefix = LegacyComponentSerializer.legacySection().serialize(
                mm.deserialize(prefixMini)
        );
        String suffix = LegacyComponentSerializer.legacySection().serialize(
                mm.deserialize(suffixMini)
        );

        String finalMessage = prefix + player.getName() + suffix + " » " + rawMessage;

        event.setFormat(finalMessage);
    }

    private String translateHexColors(String message) {
        Pattern pattern = Pattern.compile("(?i)&#([0-9a-f]{6})");
        Matcher matcher = pattern.matcher(message);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String hex = matcher.group(1);
            StringBuilder replacement = new StringBuilder("§x");
            for (char c : hex.toCharArray()) {
                replacement.append('§').append(c);
            }
            matcher.appendReplacement(sb, replacement.toString());
        }
        matcher.appendTail(sb);

        return sb.toString();
    }
}
