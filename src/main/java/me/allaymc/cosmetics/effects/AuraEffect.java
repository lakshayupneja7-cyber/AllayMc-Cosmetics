package me.allaymc.cosmetics.effects;

import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class AuraEffect implements CosmeticEffect {

    @Override
    public void start(Player p) {
        p.getWorld().spawnParticle(Particle.END_ROD, p.getLocation().add(0,1,0), 2);
    }

    @Override
    public void stop(Player p) {}

    @Override
    public String getName() {
        return "aura";
    }
}
