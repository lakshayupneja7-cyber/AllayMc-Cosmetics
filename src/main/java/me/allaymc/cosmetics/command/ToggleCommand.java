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

        if (args.length == 0) return false;

        if (args[0].equalsIgnoreCase("on")) {
            manager.enable(player);
            player.sendMessage("§aCosmetics enabled.");
        } else if (args[0].equalsIgnoreCase("off")) {
            manager.disable(player);
            player.sendMessage("§cCosmetics disabled.");
        }

        return true;
    }
}
