package me.allaymc.cosmetics;

import me.allaymc.cosmetics.command.CosmeticCommand;
import me.allaymc.cosmetics.listener.JoinListener;
import me.allaymc.cosmetics.manager.CosmeticManager;
import me.allaymc.cosmetics.manager.RankManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AllayCosmetics extends JavaPlugin {

    private static AllayCosmetics instance;

    private CosmeticManager cosmeticManager;
    private RankManager rankManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        rankManager = new RankManager(this);
        cosmeticManager = new CosmeticManager(this, rankManager);

        getServer().getPluginManager().registerEvents(new JoinListener(cosmeticManager), this);

        getCommand("cosmetic").setExecutor(new CosmeticCommand(cosmeticManager));

        getLogger().info("AllayCosmetics v3 enabled");
    }

    public static AllayCosmetics getInstance() {
        return instance;
    }
}
