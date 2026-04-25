package me.allaymc.cosmetics;

import me.allaymc.cosmetics.command.ToggleCommand;
import me.allaymc.cosmetics.listener.JoinListener;
import me.allaymc.cosmetics.listener.KillListener;
import me.allaymc.cosmetics.manager.CosmeticManager;
import me.allaymc.cosmetics.manager.RankManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AllayCosmetics extends JavaPlugin {

    private static AllayCosmetics instance;
    private CosmeticManager manager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        RankManager rankManager = new RankManager(this);
        manager = new CosmeticManager(this, rankManager);

        getServer().getPluginManager().registerEvents(
                new JoinListener(manager), this);

        getServer().getPluginManager().registerEvents(
                new KillListener(manager), this);

        getCommand("allaycosmetics")
                .setExecutor(new ToggleCommand(manager));
    }

    public static AllayCosmetics get() {
        return instance;
    }
}
