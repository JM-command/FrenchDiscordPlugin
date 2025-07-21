package ch.jmcommand.frenchdiscordplugin.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HomeManager {

    private final File file;
    private final YamlConfiguration config;

    public HomeManager(File dataFolder) {
        file = new File(dataFolder, "homes.yml");
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void setHome(UUID uuid, String name, Location loc) {
        String path = "homes." + uuid + "." + name;
        config.set(path + ".world", loc.getWorld().getName());
        config.set(path + ".x", loc.getX());
        config.set(path + ".y", loc.getY());
        config.set(path + ".z", loc.getZ());
        config.set(path + ".yaw", loc.getYaw());
        config.set(path + ".pitch", loc.getPitch());
        save();
    }

    public Location getHome(UUID uuid, String name) {
        String path = "homes." + uuid + "." + name;
        if (!config.contains(path)) return null;

        String world = config.getString(path + ".world");
        double x = config.getDouble(path + ".x");
        double y = config.getDouble(path + ".y");
        double z = config.getDouble(path + ".z");
        float yaw = (float) config.getDouble(path + ".yaw");
        float pitch = (float) config.getDouble(path + ".pitch");

        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    public Map<String, Location> getHomes(UUID uuid) {
        Map<String, Location> homes = new HashMap<>();
        if (!config.contains("homes." + uuid)) return homes;

        for (String name : config.getConfigurationSection("homes." + uuid).getKeys(false)) {
            homes.put(name, getHome(uuid, name));
        }
        return homes;
    }

    public boolean removeHome(UUID uuid, String name) {
        String path = "homes." + uuid + "." + name;
        if (!config.contains(path)) return false;

        config.set(path, null);
        save();
        return true;
    }

    private void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
