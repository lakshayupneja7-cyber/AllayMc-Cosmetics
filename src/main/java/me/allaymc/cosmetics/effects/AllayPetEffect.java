package me.allaymc.cosmetics.effects;

import org.bukkit.entity.Allay;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class AllayPetEffect implements CosmeticEffect {

    private final Map<Player, Allay> pets = new HashMap<>();

    @Override
    public void start(Player p) {
        Allay a = p.getWorld().spawn(p.getLocation(), Allay.class);
        a.setAI(false);
        a.setInvulnerable(true);
        pets.put(p, a);
    }

    @Override
    public void stop(Player p) {
        if (pets.containsKey(p)) {
            pets.get(p).remove();
            pets.remove(p);
        }
    }

    @Override
    public String getName() {
        return "allay_pet";
    }
}
