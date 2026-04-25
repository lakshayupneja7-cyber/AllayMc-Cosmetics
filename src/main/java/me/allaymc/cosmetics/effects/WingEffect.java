package me.allaymc.cosmetics.effects;

import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class WingEffect implements CosmeticEffect {

    @Override
    public void start(Player p) {
        p.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, p.getLocation().add(0,1.2,0), 2);
    }

    @Override
    public void stop(Player p) {}

    @Override
    public String getName() {
        return "wings";
    }
}
