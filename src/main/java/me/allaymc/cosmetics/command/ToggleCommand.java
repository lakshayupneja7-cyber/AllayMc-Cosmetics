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

        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        if (args.length < 1) return true;

        String id = args[0];

        manager.toggle(player, id);

        return true;
    }
}
