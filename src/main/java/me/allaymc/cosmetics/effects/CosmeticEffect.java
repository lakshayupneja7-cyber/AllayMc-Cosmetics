package me.allaymc.cosmetics.effects;

import org.bukkit.entity.Player;

public interface CosmeticEffect {

    String id();

    void onEnable(Player player);

    void onDisable(Player player);
}
