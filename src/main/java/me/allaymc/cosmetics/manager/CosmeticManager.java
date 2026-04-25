package me.allaymc.cosmetics.manager;

import me.allaymc.cosmetics.effects.CosmeticEffect;
import me.allaymc.cosmetics.effects.impl.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class CosmeticManager {

    private final Plugin plugin;
    private final RankManager rankManager;

    private final Map<String, CosmeticEffect> effects = new HashMap<>();
    private final Set<UUID> disabled = new HashSet<>();

    public CosmeticManager(Plugin plugin, RankManager rankManager) {
        this.plugin = plugin;
        this.rankManager = rankManager;

        register(new AuraEffect());
        register(new WingEffect());
        register(new TrailEffect());
        register(new KillEffect());
        register(new AllayPetEffect());
    }

    private void register(CosmeticEffect effect) {
        effects.put(effect.getId(), effect);
    }

    public void apply(Player p) {
        if (disabled.contains(p.getUniqueId())) return;

        for (String id : rankManager.getEffects(p)) {
            CosmeticEffect effect = effects.get(id);
            if (effect != null) {
                effect.start(p);
            }
        }
    }

    public void toggle(Player p) {
        if (disabled.contains(p.getUniqueId())) {
            disabled.remove(p.getUniqueId());
            apply(p);
        } else {
            disabled.add(p.getUniqueId());
            stopAll(p);
        }
    }

    public void preview(Player p, String id) {
        CosmeticEffect effect = effects.get(id);
        if (effect != null) {
            effect.startPreview(p);
        }
    }

    public void stopAll(Player p) {
        for (CosmeticEffect e : effects.values()) {
            e.stop(p);
        }
    }
}
