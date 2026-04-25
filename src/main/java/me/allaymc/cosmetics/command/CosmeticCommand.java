package me.allaymc.cosmetics.command;

import me.allaymc.cosmetics.manager.CosmeticManager;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class CosmeticCommand implements CommandExecutor {

    private final CosmeticManager manager;

    public CosmeticCommand(CosmeticManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player player)) return true;

        if (args.length < 2) return true;

        String action = args[0];
        String cosmetic = args[1];

        switch (action.toLowerCase()) {
            case "toggle":
                manager.toggle(player, cosmetic);
                break;

            case "preview":
                manager.preview(player, cosmetic);
                break;
        }

        return true;
    }
}
