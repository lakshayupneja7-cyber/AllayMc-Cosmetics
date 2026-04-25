package me.allaymc.cosmetics.effects;

import org.bukkit.entity.Player;

public class AuraEffect implements CosmeticEffect {

    @Override
    public String id() {
        return "aura";
    }

    @Override
    public void onEnable(Player player) {
        player.sendMessage("Aura enabled");
    }

    @Override
    public void onDisable(Player player) {
        player.sendMessage("Aura disabled");
    }
}
