package me.allaymc.cosmetics.effects;

import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class TrailEffect implements CosmeticEffect {

    @Override
    public void start(Player p) {
        if (!p.isOnGround()) return;
        p.getWorld().spawnParticle(Particle.SPELL, p.getLocation(), 2);
    }

    @Override
    public void stop(Player p) {}

    @Override
    public String getName() {
        return "trail";
    }
}
