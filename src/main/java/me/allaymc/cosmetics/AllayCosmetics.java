package me.allaymc.cosmetics;

import me.allaymc.cosmetics.manager.CosmeticManager;
import me.allaymc.cosmetics.manager.RankManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AllayCosmetics extends JavaPlugin {

    private CosmeticManager cosmeticManager;

    @Override
    public void onEnable() {
        cosmeticManager = new CosmeticManager(this, new RankManager());
    }

    public CosmeticManager getCosmeticManager() {
        return cosmeticManager;
    }
}
