package me.allaymc.cosmetics.manager;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.*;

public class RankManager {

    private final Map<String, List<String>> effects = new HashMap<>();
    private final Map<String, String> perms = new HashMap<>();

    public RankManager(Plugin plugin) {
        ConfigurationSection sec = plugin.getConfig().getConfigurationSection("ranks");

        for (String key : sec.getKeys(false)) {
            perms.put(key, sec.getString(key + ".permission"));
            effects.put(key, sec.getStringList(key + ".effects"));
        }
    }

    public List<String> getEffects(Player player) {
        for (String rank : perms.keySet()) {
            if (player.hasPermission(perms.get(rank))) {
                return effects.get(rank);
            }
        }
        return Collections.emptyList();
    }
}
