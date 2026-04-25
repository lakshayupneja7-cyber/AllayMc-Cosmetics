package me.allaymc.cosmetics.command;

import me.allaymc.cosmetics.manager.CosmeticManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleCommand implements CommandExecutor {

    private final CosmeticManager manager;

    public ToggleCommand(CosmeticManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) return true;

        if (manager.isEnabled(player)) {
            manager.disable(player);
            player.sendMessage("§cCosmetics disabled");
        } else {
            manager.enable(player);
            player.sendMessage("§aCosmetics enabled");
        }

        return true;
    }
}
