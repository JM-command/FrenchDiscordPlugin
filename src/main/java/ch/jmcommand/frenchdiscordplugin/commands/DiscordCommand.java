package ch.jmcommand.frenchdiscordplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("§b➤ Voici le Discord du serveur : §9https://discord.gg/baguette");
        return true;
    }
}
