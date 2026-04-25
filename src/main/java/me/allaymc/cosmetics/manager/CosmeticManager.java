package me.allaymc.cosmetics.manager;

import me.allaymc.cosmetics.effects.CosmeticEffect;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class CosmeticManager {

    private final Plugin plugin;

    private final Map<String, CosmeticEffect> effects = new HashMap<>();
    private final Map<UUID, Set<String>> active = new HashMap<>();

    public CosmeticManager(Plugin plugin) {
        this.plugin = plugin;
    }

    // register effect
    public void register(CosmeticEffect effect) {
        effects.put(effect.id(), effect);
    }

    // enable effect
    public void enable(Player player, String id) {
        CosmeticEffect effect = effects.get(id);
        if (effect == null) return;

        effect.onEnable(player);
        active.computeIfAbsent(player.getUniqueId(), k -> new HashSet<>()).add(id);
    }

    // disable effect
    public void disable(Player player, String id) {
        CosmeticEffect effect = effects.get(id);
        if (effect == null) return;

        effect.onDisable(player);

        Set<String> set = active.get(player.getUniqueId());
        if (set != null) set.remove(id);
    }

    // toggle
    public void toggle(Player player, String id) {
        Set<String> set = active.getOrDefault(player.getUniqueId(), new HashSet<>());

        if (set.contains(id)) disable(player, id);
        else enable(player, id);
    }

    // apply list (ranks)
    public void apply(Player player, List<String> ids) {
        for (String id : ids) {
            enable(player, id);
        }
    }

    public Set<String> getActive(Player player) {
        return active.getOrDefault(player.getUniqueId(), new HashSet<>());
    }
}
