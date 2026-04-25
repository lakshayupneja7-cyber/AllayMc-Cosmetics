package me.allaymc.cosmetics.command;

import me.allaymc.cosmetics.manager.CosmeticManager;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class ToggleCommand implements CommandExecutor {

    private final CosmeticManager manager;

    public ToggleCommand(CosmeticManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player player)) return true;

        if (args.length < 1) return true;

        String cosmetic = args[0];

        if (manager.isEnabled(player, cosmetic)) {
            manager.disable(player, cosmetic);
        } else {
            manager.enable(player, cosmetic);
        }

        return true;
    }
}
