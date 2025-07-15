package ch.jmcommand.frenchdiscordplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class FrenchDiscordPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("FrenchDiscordPlugin enabled!");

        // Load configuration
        ch.jmcommand.frenchdiscordplugin.utils.LuckPermsHook.setup();


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
        getCommand("discord").setExecutor(new ch.jmcommand.frenchdiscordplugin.commands.DiscordCommand());
        getCommand("rules").setExecutor(new ch.jmcommand.frenchdiscordplugin.commands.RulesCommand());
        getCommand("regles").setExecutor(new ch.jmcommand.frenchdiscordplugin.commands.RulesCommand());
        getCommand("aide").setExecutor(new ch.jmcommand.frenchdiscordplugin.commands.HelpCommand());
        getCommand("help").setExecutor(new ch.jmcommand.frenchdiscordplugin.commands.HelpCommand());
        getCommand("site").setExecutor(new ch.jmcommand.frenchdiscordplugin.commands.SiteCommand());

        getCommand("gms").setExecutor(new ch.jmcommand.frenchdiscordplugin.commands.GamemodeCommand());
        getCommand("gmc").setExecutor(new ch.jmcommand.frenchdiscordplugin.commands.GamemodeCommand());
        getCommand("gma").setExecutor(new ch.jmcommand.frenchdiscordplugin.commands.GamemodeCommand());
        getCommand("gmsp").setExecutor(new ch.jmcommand.frenchdiscordplugin.commands.GamemodeCommand());

        getCommand("heal").setExecutor(new ch.jmcommand.frenchdiscordplugin.commands.HealCommand());
        getCommand("feed").setExecutor(new ch.jmcommand.frenchdiscordplugin.commands.FeedCommand());
        getCommand("ec").setExecutor(new ch.jmcommand.frenchdiscordplugin.commands.EnderChestCommand());
        getCommand("craft").setExecutor(new ch.jmcommand.frenchdiscordplugin.commands.WorkbenchCommand());
        getCommand("furnace").setExecutor(new ch.jmcommand.frenchdiscordplugin.commands.FurnaceCommand());
        getCommand("compress").setExecutor(new ch.jmcommand.frenchdiscordplugin.commands.CompressCommand());
        getCommand("anvil").setExecutor(new ch.jmcommand.frenchdiscordplugin.commands.AnvilCommand());

        getCommand("promoteplayer").setExecutor(new ch.jmcommand.frenchdiscordplugin.commands.PromotePlayerCommand());
        getCommand("pluginupdate").setExecutor(new ch.jmcommand.frenchdiscordplugin.commands.PluginUpdateCommand(this));

    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(
                new ch.jmcommand.frenchdiscordplugin.listeners.ChatListener(), this
        );
        getServer().getPluginManager().registerEvents(
                new ch.jmcommand.frenchdiscordplugin.listeners.VisitorProtectionListener(this), this
        );
        getServer().getPluginManager().registerEvents(
                new ch.jmcommand.frenchdiscordplugin.listeners.PlayerJoinListener(), this
        );

    }
}

