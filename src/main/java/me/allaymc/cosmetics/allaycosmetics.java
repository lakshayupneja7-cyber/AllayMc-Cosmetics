package me.allaymc.cosmetics;

import me.allaymc.cosmetics.command.ToggleCommand;
import me.allaymc.cosmetics.listener.JoinListener;
import me.allaymc.cosmetics.listener.KillListener;
import me.allaymc.cosmetics.manager.CosmeticManager;
import me.allaymc.cosmetics.manager.RankManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AllayCosmetics extends JavaPlugin {

    private static AllayCosmetics instance;
    private CosmeticManager cosmeticManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        RankManager rankManager = new RankManager(this);
        cosmeticManager = new CosmeticManager(this, rankManager);

        getServer().getPluginManager().registerEvents(new JoinListener(cosmeticManager), this);
        getServer().getPluginManager().registerEvents(new KillListener(cosmeticManager), this);

        getCommand("allaycosmetics").setExecutor(new ToggleCommand(cosmeticManager));
    }

    public static AllayCosmetics get() {
        return instance;
    }
}
