package me.allaymc.cosmetics.effects;

import org.bukkit.entity.Player;

public interface CosmeticEffect {
    void start(Player player);
    void stop(Player player);
    String getName();
}
