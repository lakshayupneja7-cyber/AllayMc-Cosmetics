package me.allaymc.cosmetics.manager;

import me.allaymc.cosmetics.effects.CosmeticEffect;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class CosmeticManager {

    private final Plugin plugin;

    private final Map<String, CosmeticEffect> effects = new HashMap<>();
    private final Set<UUID> enabled = new HashSet<>();

    public CosmeticManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void register(CosmeticEffect effect) {
        effects.put(effect.getId(), effect);
    }

    // FIX: used by commands
    public void toggle(Player player) {
        if (enabled.contains(player.getUniqueId())) {
            disable(player);
        } else {
            enable(player);
        }
    }

    public void enable(Player player) {
        enabled.add(player.getUniqueId());
        apply(player);
    }

    public void disable(Player player) {
        enabled.remove(player.getUniqueId());

        for (CosmeticEffect effect : effects.values()) {
            effect.disable(player);
        }
    }

    // FIX: used by JoinListener
    public void apply(Player player) {
        if (!enabled.contains(player.getUniqueId())) return;

        for (CosmeticEffect effect : effects.values()) {
            effect.enable(player);
        }
    }

    // FIX: preview system
    public void preview(Player player, String id) {
        CosmeticEffect effect = effects.get(id);
        if (effect != null) {
            effect.enable(player);
        }
    }

    // FIX: kill trigger
    public void triggerKill(Player player) {
        CosmeticEffect effect = effects.get("kill_effect");
        if (effect != null) effect.enable(player);
    }

    public boolean isEnabled(Player player) {
        return enabled.contains(player.getUniqueId());
    }
}
