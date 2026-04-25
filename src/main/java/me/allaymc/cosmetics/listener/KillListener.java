package me.allaymc.cosmetics.listener;

import me.allaymc.cosmetics.manager.CosmeticManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillListener implements Listener {

    private final CosmeticManager manager;

    public KillListener(CosmeticManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onKill(PlayerDeathEvent e) {
        if (e.getEntity().getKiller() != null) {
            manager.triggerKill(e.getEntity().getKiller());
        }
    }
}
