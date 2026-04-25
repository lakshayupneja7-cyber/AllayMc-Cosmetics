package me.allaymc.cosmetics.listener;

import me.allaymc.cosmetics.manager.CosmeticManager;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class JoinListener implements Listener {

    private final CosmeticManager manager;

    public JoinListener(CosmeticManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        manager.apply(event.getPlayer(), List.of());
    }
}
