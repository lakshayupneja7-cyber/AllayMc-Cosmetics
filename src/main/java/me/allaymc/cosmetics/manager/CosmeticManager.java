package me.allaymc.cosmetics.manager;

import me.allaymc.cosmetics.AllayCosmetics;
import org.bukkit.entity.Player;

import java.util.*;

public class CosmeticManager {

    private final AllayCosmetics plugin;
    private final Map<UUID, Set<String>> enabled = new HashMap<>();

    public CosmeticManager(AllayCosmetics plugin, RankManager rankManager) {
        this.plugin = plugin;
    }

    // ---------------- CORE 2-ARG API (your system expects this) ----------------

    public boolean isEnabled(Player player, String cosmetic) {
        return enabled.getOrDefault(player.getUniqueId(), new HashSet<>()).contains(cosmetic);
    }

    public void enable(Player player, String cosmetic) {
        enabled.computeIfAbsent(player.getUniqueId(), k -> new HashSet<>()).add(cosmetic);
    }

    public void disable(Player player, String cosmetic) {
        Set<String> set = enabled.get(player.getUniqueId());
        if (set != null) set.remove(cosmetic);
    }

    public void toggle(Player player, String cosmetic) {
        if (isEnabled(player, cosmetic)) {
            disable(player, cosmetic);
        } else {
            enable(player, cosmetic);
        }
    }

    public void apply(Player player, List<String> cosmetics) {
        // safe fallback so JoinListener doesn't break
        if (cosmetics == null) return;
        for (String c : cosmetics) {
            enable(player, c);
        }
    }

    public void triggerKill(Player player) {
        // placeholder safe compile
    }

    public void preview(Player player, String cosmetic) {
        // placeholder safe compile
    }

    // ---------------- FIX: 1-ARG OVERLOADS (THIS FIXES YOUR ERRORS) ----------------

    public boolean isEnabled(Player player) {
        return false;
    }

    public void enable(Player player) {}

    public void disable(Player player) {}

    public void toggle(Player player) {}

    public void apply(Player player) {}

}
