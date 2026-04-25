package me.allaymc.cosmetics.effects;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.java.JavaPlugin;

public class TrailEffect implements CosmeticEffect {

    private BukkitRunnable task;

    @Override
    public String getName() {
        return "trail";
    }

    @Override
    public void start(Player player) {

        stop(player);

        JavaPlugin plugin = JavaPlugin.getProvidingPlugin(getClass());

        task = new BukkitRunnable() {
            @Override
            public void run() {

                if (!player.isOnline()) {
                    cancel();
                    return;
                }

                player.getWorld().spawnParticle(
                        Particle.END_ROD,   // ✅ FIXED (replaces SPELL)
                        player.getLocation().add(0, 0.1, 0),
                        3,
                        0.2, 0.2, 0.2,
                        0.01
                );
            }
        };

        task.runTaskTimer(plugin, 0L, 5L);
    }

    @Override
    public void stop(Player player) {
        if (task != null) {
            task.cancel();
            task = null;
        }
    }
}
