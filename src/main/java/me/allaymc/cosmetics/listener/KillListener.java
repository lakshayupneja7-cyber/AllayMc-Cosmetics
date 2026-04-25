package me.allaymc.cosmetics.listener;

import me.allaymc.cosmetics.manager.CosmeticManager;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillListener implements Listener {

    private final CosmeticManager manager;

    public KillListener(CosmeticManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        if (killer != null) {
            manager.triggerKill(killer);
        }
    }
}
