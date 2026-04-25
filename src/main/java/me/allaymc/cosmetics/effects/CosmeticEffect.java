package me.allaymc.cosmetics.effects;

import org.bukkit.entity.Player;

public interface CosmeticEffect {

    String getId();

    void start(Player player);

    default void stop(Player player) {}

    default void startPreview(Player player) {
        start(player);
    }
}
