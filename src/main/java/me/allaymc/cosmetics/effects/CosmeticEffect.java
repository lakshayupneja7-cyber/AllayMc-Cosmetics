package me.allaymc.cosmetics.effects;

import org.bukkit.entity.Player;

public interface CosmeticEffect {

    String getId();

    void enable(Player player);

    void disable(Player player);
}
