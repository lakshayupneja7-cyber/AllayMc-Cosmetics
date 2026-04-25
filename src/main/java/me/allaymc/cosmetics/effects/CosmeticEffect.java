package me.allaymc.cosmetics.effects;

import org.bukkit.entity.Player;

public interface CosmeticEffect {

    void enable(Player player);

    void disable(Player player);
}
