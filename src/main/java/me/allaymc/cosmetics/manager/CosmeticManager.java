package me.allaymc.cosmetics.manager;

import me.allaymc.cosmetics.effects.CosmeticEffect;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class CosmeticManager {

    private final Plugin plugin;
    private final Map<String, CosmeticEffect> effects = new HashMap<>();
    private final Map<UUID, Set<String>> enabled = new HashMap<>();

    public CosmeticManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void register(CosmeticEffect effect) {
        effects.put(effect.getId(), effect);
    }

    public boolean isEnabled(Player player, String id) {
        return enabled.getOrDefault(player.getUniqueId(), new HashSet<>()).contains(id);
    }

    public void enable(Player player, String id) {
        CosmeticEffect effect = effects.get(id);
        if (effect == null) return;

        effect.enable(player);
        enabled.computeIfAbsent(player.getUniqueId(), k -> new HashSet<>()).add(id);
    }

    public void disable(Player player, String id) {
        CosmeticEffect effect = effects.get(id);
        if (effect == null) return;

        effect.disable(player);
        enabled.computeIfAbsent(player.getUniqueId(), k -> new HashSet<>()).remove(id);
    }

    public void toggle(Player player, String id) {
        if (isEnabled(player, id)) disable(player, id);
        else enable(player, id);
    }

    public void preview(Player player, String id) {
        enable(player, id);

        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
            disable(player, id);
        }, 60L);
    }

    public void apply(Player player, List<String> ids) {
        for (String id : ids) {
            enable(player, id);
        }
    }

    public void triggerKill(Player player) {
        // called by kill listener
    }
}
