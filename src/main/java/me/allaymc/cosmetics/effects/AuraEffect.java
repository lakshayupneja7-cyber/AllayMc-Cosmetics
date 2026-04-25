package me.allaymc.cosmetics.effects;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AuraEffect implements CosmeticEffect {

    private BukkitRunnable task;

    @Override
    public String getId() {
        return "aura";
    }

    @Override
    public void enable(Player player) {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                player.getWorld().spawnParticle(
                        Particle.PORTAL,
                        player.getLocation(),
                        20,
                        0.5, 1, 0.5
                );
            }
        };
        task.runTaskTimerAsynchronously(
                player.getServer().getPluginManager().getPlugin("AllayCosmetics"),
                0L, 10L
        );
    }

    @Override
    public void disable(Player player) {
        if (task != null) task.cancel();
    }
}
