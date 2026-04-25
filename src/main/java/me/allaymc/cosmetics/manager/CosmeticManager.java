package me.allaymc.cosmetics.manager;

import me.allaymc.cosmetics.effects.CosmeticEffect;
import me.allaymc.cosmetics.effects.AuraEffect;
import me.allaymc.cosmetics.effects.WingEffect;
import me.allaymc.cosmetics.effects.TrailEffect;
import me.allaymc.cosmetics.effects.KillEffect;
import me.allaymc.cosmetics.effects.AllayPetEffect;
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

        // Register effects
        register(new AuraEffect());
        register(new WingEffect());
        register(new TrailEffect());
        register(new KillEffect());
        register(new AllayPetEffect());
    }

    private void register(CosmeticEffect effect) {
        effects.put(effect.getId(), effect);
    }

    public void apply(Player player) {
        if (disabled.contains(player.getUniqueId())) return;

        for (String id : rankManager.getEffects(player)) {
            CosmeticEffect effect = effects.get(id);
            if (effect != null) {
                effect.start(player);
            }
        }
    }

    public void toggle(Player player) {
        UUID uuid = player.getUniqueId();

        if (disabled.contains(uuid)) {
            disabled.remove(uuid);
            apply(player);
        } else {
            disabled.add(uuid);
            stopAll(player);
        }
    }

    public void preview(Player player, String id) {
        CosmeticEffect effect = effects.get(id);

        if (effect != null) {
            effect.startPreview(player);
        } else {
            player.sendMessage("§cUnknown effect: " + id);
        }
    }

    public void stopAll(Player player) {
        for (CosmeticEffect effect : effects.values()) {
            effect.stop(player);
        }
    }
}
