package me.allaymc.cosmetics.manager;

import me.allaymc.cosmetics.effects.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class CosmeticManager {

    private final Map<String, CosmeticEffect> registry = new HashMap<>();
    private final Map<UUID, List<CosmeticEffect>> active = new HashMap<>();
    private final Set<UUID> disabled = new HashSet<>();
    private final RankManager rankManager;

    public CosmeticManager(Plugin plugin, RankManager rankManager) {
        this.rankManager = rankManager;

        register(new AuraEffect());
        register(new WingEffect());
        register(new TrailEffect());
        register(new KillEffect());
        register(new AllayPetEffect());
    }

    private void register(CosmeticEffect e) {
        registry.put(e.getName(), e);
    }

    public void apply(Player p) {
        if (disabled.contains(p.getUniqueId())) return;

        remove(p);

        List<String> effects = rankManager.getEffects(p);
        List<CosmeticEffect> running = new ArrayList<>();

        for (String name : effects) {
            CosmeticEffect e = registry.get(name);
            if (e != null) {
                e.start(p);
                running.add(e);
            }
        }

        active.put(p.getUniqueId(), running);
    }

    public void remove(Player p) {
        List<CosmeticEffect> list = active.remove(p.getUniqueId());
        if (list == null) return;

        for (CosmeticEffect e : list) {
            e.stop(p);
        }
    }

    public void enable(Player p) {
        disabled.remove(p.getUniqueId());
        apply(p);
    }

    public void disable(Player p) {
        disabled.add(p.getUniqueId());
        remove(p);
    }

    public void triggerKill(Player p) {
        CosmeticEffect e = registry.get("kill_effect");
        if (e instanceof KillEffect k) {
            k.trigger(p);
        }
    }
}
