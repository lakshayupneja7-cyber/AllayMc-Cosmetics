public class CosmeticManager {

    private final Plugin plugin;

    public CosmeticManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void toggle(Player player, String cosmetic) {
        if (isEnabled(player, cosmetic)) {
            disable(player, cosmetic);
        } else {
            enable(player, cosmetic);
        }
    }

    public void enable(Player player, String cosmetic) {
        // apply cosmetic
    }

    public void disable(Player player, String cosmetic) {
        // remove cosmetic
    }

    public boolean isEnabled(Player player, String cosmetic) {
        return false; // your storage logic
    }

    public void apply(Player player, List<String> cosmetics) {
        for (String c : cosmetics) {
            enable(player, c);
        }
    }

    public void triggerKill(Player player) {
        // kill effects
    }

    public void preview(Player player, String cosmetic) {
        // preview logic
    }
}
