package ch.jmcommand.frenchdiscordplugin.commands;

import ch.jmcommand.frenchdiscordplugin.FrenchDiscordPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.*;

public class PluginUpdateCommand implements CommandExecutor {

    private final FrenchDiscordPlugin plugin;
    private final String GITHUB_REPO = "JM-command/FrenchDiscordPlugin";

    public PluginUpdateCommand(FrenchDiscordPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("frenchdiscord.update")) {
            sender.sendMessage("§cTu n'as pas la permission !");
            return true;
        }

        if (args.length == 0) {
            listVersions(sender);
            return true;
        }

        String tag = args[0];
        downloadAndReplaceJar(sender, tag);
        return true;
    }

    private void listVersions(CommandSender sender) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                URL url = new URL("https://api.github.com/repos/" + GITHUB_REPO + "/tags");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse tags (très simple parsing JSON)
                String json = response.toString();
                json = json.replace("[", "").replace("]", "");
                String[] tags = json.split("\\},\\{");

                sender.sendMessage("§aVersions disponibles :");
                for (String t : tags) {
                    int idx = t.indexOf("\"name\":\"");
                    if (idx > -1) {
                        String tag = t.substring(idx + 8);
                        tag = tag.substring(0, tag.indexOf("\""));
                        sender.sendMessage(" - §e" + tag);
                    }
                }

            } catch (Exception e) {
                sender.sendMessage("§cErreur lors de la récupération des versions.");
                e.printStackTrace();
            }
        });
    }

    private void downloadAndReplaceJar(CommandSender sender, String tag) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try {
                sender.sendMessage("§7Recherche de la release " + tag + "...");

                URL url = new URL("https://api.github.com/repos/" + GITHUB_REPO + "/releases/tags/" + tag);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String json = in.readLine();
                in.close();

                if (json == null || !json.contains("browser_download_url")) {
                    sender.sendMessage("§cImpossible de trouver le téléchargement pour ce tag.");
                    return;
                }

                String urlStart = "\"browser_download_url\":\"";
                int idx = json.indexOf(urlStart);
                String downloadUrl = json.substring(idx + urlStart.length());
                downloadUrl = downloadUrl.substring(0, downloadUrl.indexOf("\""));

                sender.sendMessage("§aTéléchargement depuis : §e" + downloadUrl);

                // Télécharge le nouveau JAR
                InputStream inStream = new URL(downloadUrl).openStream();
                String fileName = "FrenchDiscordPlugin-" + tag.replace("v", "") + ".jar";
                Path target = Paths.get("plugins", fileName);
                Files.copy(inStream, target, StandardCopyOption.REPLACE_EXISTING);

                sender.sendMessage("§aPlugin téléchargé avec succès : §e" + fileName);
                sender.sendMessage("§cRedémarrage dans 5 secondes...");

                startRestartCountdown();

            } catch (Exception e) {
                sender.sendMessage("§cErreur lors de la mise à jour.");
                e.printStackTrace();
            }
        });
    }

    private void startRestartCountdown() {
        for (int i = 5; i > 0; i--) {
            int finalI = i;
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                Bukkit.broadcastMessage("§cRedémarrage dans " + finalI + " secondes !");
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendTitle("§cREDÉMARRAGE", "§eDans " + finalI + " secondes", 10, 20, 10);
                }
            }, (5 - i) * 20L);
        }
        Bukkit.getScheduler().runTaskLater(plugin, Bukkit::shutdown, 100L);
    }
}
