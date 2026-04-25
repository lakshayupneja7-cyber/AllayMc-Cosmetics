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

        if (!(sender instanceof Player p)) {
            sender.sendMessage("Only players");
            return true;
        }

        if (args.length == 0) {
            p.sendMessage("§b/cosmetic toggle");
            p.sendMessage("§b/cosmetic preview <effect>");
            return true;
        }

        switch (args[0].toLowerCase()) {

            case "toggle" -> {
                manager.toggle(p);
                p.sendMessage("§aCosmetics toggled");
            }

            case "preview" -> {
                if (args.length < 2) {
                    p.sendMessage("§cUsage: /cosmetic preview <effect>");
                    return true;
                }

                manager.preview(p, args[1]);
                p.sendMessage("§bPreviewing: " + args[1]);
            }
        }

        return true;
    }
}
