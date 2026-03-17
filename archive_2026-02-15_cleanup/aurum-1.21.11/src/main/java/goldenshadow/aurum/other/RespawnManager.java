/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.entity.Player
 */
package goldenshadow.aurum.other;

import goldenshadow.aurum.entities.DataManager;
import goldenshadow.aurum.other.ExperienceManager;
import goldenshadow.aurum.other.RespawnLocation;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class RespawnManager {
    public static void playerDeath(Player player) {
        int xp = ExperienceManager.getXP(player);
        double deduction = (double)xp * 0.1;
        ExperienceManager.removeXp(player, (int)Math.round(deduction));
        player.sendMessage(Component.text("[", NamedTextColor.DARK_RED).append(Component.text("!", NamedTextColor.RED)).append(Component.text("] ", NamedTextColor.DARK_RED)).append(Component.text("You died...", NamedTextColor.GRAY)));
        player.sendMessage(Component.text("[", NamedTextColor.DARK_RED).append(Component.text("!", NamedTextColor.RED)).append(Component.text("] ", NamedTextColor.DARK_RED)).append(Component.text("-" + (int)Math.round(deduction) + " XP", NamedTextColor.GRAY)));
    }

    public static void respawnNodeLoop() {
        for (RespawnLocation location : DataManager.getRespawnLocationList()) {
            location.scan();
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!player.getScoreboardTags().contains("aurum_debug_respawn")) continue;
                location.highlight(player);
            }
        }
    }
}

