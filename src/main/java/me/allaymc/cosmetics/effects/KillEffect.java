package me.allaymc.cosmetics.effects;

import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class KillEffect implements CosmeticEffect {

    @Override
    public void start(Player p) {}

    @Override
    public void stop(Player p) {}

    @Override
    public String getName() {
        return "kill_effect";
    }

    public void trigger(Player p) {
        p.getWorld().spawnParticle(Particle.SOUL, p.getLocation(), 20);
    }
}
