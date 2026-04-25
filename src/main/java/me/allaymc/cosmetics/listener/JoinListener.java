package me.allaymc.cosmetics.listener;

import me.allaymc.cosmetics.manager.CosmeticManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final CosmeticManager manager;

    public JoinListener(CosmeticManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        manager.apply(e.getPlayer());
    }
}
