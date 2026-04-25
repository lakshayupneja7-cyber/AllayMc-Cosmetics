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

    public void enable(Player player) {
        enabled.add(player.getUniqueId());
    }

    public void disable(Player player) {
        enabled.remove(player.getUniqueId());

        // disable all effects
        for (CosmeticEffect effect : effects.values()) {
            effect.disable(player);
        }
    }

    public boolean isEnabled(Player player) {
        return enabled.contains(player.getUniqueId());
    }

    public void applyAll(Player player, List<String> ids) {
        if (!isEnabled(player)) return;

        for (String id : ids) {
            CosmeticEffect effect = effects.get(id);
            if (effect != null) effect.enable(player);
        }
    }

    public void triggerKill(Player player) {
        CosmeticEffect effect = effects.get("kill_effect");
        if (effect != null) effect.enable(player);
    }

    public Collection<CosmeticEffect> getEffects() {
        return effects.values();
    }
}
