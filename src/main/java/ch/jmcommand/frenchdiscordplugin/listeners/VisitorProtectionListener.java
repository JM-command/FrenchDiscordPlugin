package ch.jmcommand.frenchdiscordplugin.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.scheduler.BukkitRunnable;
import ch.jmcommand.frenchdiscordplugin.FrenchDiscordPlugin;

public class VisitorProtectionListener implements Listener {

    private final FrenchDiscordPlugin plugin;

    public VisitorProtectionListener(FrenchDiscordPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("frenchdiscord.visiteur")) {
            player.setGameMode(GameMode.ADVENTURE);

            // Heal + reset food
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (!player.isOnline()) {
                        cancel();
                        return;
                    }
                    if (player.hasPermission("frenchdiscord.visiteur")) {
                        player.setHealth(player.getMaxHealth());
                        player.setFoodLevel(20);
                        player.setSaturation(20f);
                        player.setFireTicks(0);
                    } else {
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 0L, 20L);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.hasPermission("frenchdiscord.visiteur")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.hasPermission("frenchdiscord.visiteur")) {
                event.setCancelled(true);
                player.setFoodLevel(20);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("frenchdiscord.visiteur")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("frenchdiscord.visiteur")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("frenchdiscord.visiteur")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("frenchdiscord.visiteur")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("frenchdiscord.visiteur")) {
            event.setCancelled(true);
        }
    }
}
