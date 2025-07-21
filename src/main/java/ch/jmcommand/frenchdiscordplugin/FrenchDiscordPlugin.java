package ch.jmcommand.frenchdiscordplugin;

import ch.jmcommand.frenchdiscordplugin.commands.*;
import ch.jmcommand.frenchdiscordplugin.listeners.*;
import ch.jmcommand.frenchdiscordplugin.managers.*;
import ch.jmcommand.frenchdiscordplugin.utils.*;
import org.bukkit.plugin.java.JavaPlugin;

public class FrenchDiscordPlugin extends JavaPlugin {

    private static FrenchDiscordPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("FrenchDiscordPlugin enabled!");

        // Load configuration
        LuckPermsHook.setup();

        // Register commands
        registerCommands();

        // Register listeners
        registerListeners();
    }

    @Override
    public void onDisable() {
        getLogger().info("FrenchDiscordPlugin disabled!");
    }

    private void registerCommands() {
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("rules").setExecutor(new RulesCommand());
        getCommand("regles").setExecutor(new RulesCommand());
        getCommand("aide").setExecutor(new HelpCommand());
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("site").setExecutor(new SiteCommand());

        BoutiqueCommand boutiqueCommand = new BoutiqueCommand(LuckPermsHook.getLuckPerms());
        getCommand("boutique").setExecutor(boutiqueCommand);
        getServer().getPluginManager().registerEvents(boutiqueCommand, this);

        getCommand("gms").setExecutor(new GamemodeCommand());
        getCommand("gmc").setExecutor(new GamemodeCommand());
        getCommand("gma").setExecutor(new GamemodeCommand());
        getCommand("gmsp").setExecutor(new GamemodeCommand());

        HomeManager homeManager = new HomeManager(getDataFolder());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand(homeManager));
        getCommand("home").setExecutor(new HomeCommand(homeManager));
        getCommand("delhome").setExecutor(new DelHomeCommand(homeManager));
        getCommand("back").setExecutor(new BackCommand());


        getCommand("heal").setExecutor(new HealCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("ec").setExecutor(new EnderChestCommand());
        getCommand("craft").setExecutor(new WorkbenchCommand());
        getCommand("furnace").setExecutor(new FurnaceCommand());
        getCommand("compress").setExecutor(new CompressCommand());
        getCommand("anvil").setExecutor(new AnvilCommand());
        getCommand("promoteplayer").setExecutor(new PromotePlayerCommand());
        getCommand("pluginupdate").setExecutor(new PluginUpdateCommand(this));
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new VisitorProtectionListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new BackListener(), this);
        getServer().getPluginManager().registerEvents(new KeepInventoryListener(), this);

    }

    public static FrenchDiscordPlugin getInstance() {
        return instance;
    }
}
