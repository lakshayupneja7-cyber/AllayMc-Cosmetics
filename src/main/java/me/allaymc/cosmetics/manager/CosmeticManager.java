package me.allaymc.cosmetics.manager;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class CosmeticManager {

    private final Plugin plugin;

    // player -> enabled cosmetics
    private final Map<UUID, Set<String>> enabled = new HashMap<>();

    public CosmeticManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void toggle(Player player, String cosmetic) {
        if (isEnabled(player, cosmetic)) {
            disable(player, cosmetic);
        } else {
            enable(player, cosmetic);
        }
    }

    public void enable(Player player, String cosmetic) {
        enabled.computeIfAbsent(player.getUniqueId(), k -> new HashSet<>()).add(cosmetic);
    }

    public void disable(Player player, String cosmetic) {
        Set<String> set = enabled.get(player.getUniqueId());
        if (set != null) set.remove(cosmetic);
    }

    public boolean isEnabled(Player player, String cosmetic) {
        return enabled.getOrDefault(player.getUniqueId(), Collections.emptySet()).contains(cosmetic);
    }

    public void apply(Player player, List<String> cosmetics) {
        for (String c : cosmetics) {
            enable(player, c);
        }
    }

    public void preview(Player player, String cosmetic) {
        enable(player, cosmetic);
    }

    public void triggerKill(Player player) {
        // placeholder for kill effects
    }
}
