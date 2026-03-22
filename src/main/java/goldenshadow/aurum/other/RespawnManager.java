/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.adventure.text.Component
 *  net.kyori.adventure.text.TextComponent
 *  net.kyori.adventure.text.format.NamedTextColor
 *  net.kyori.adventure.text.format.TextColor
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 */
package goldenshadow.aurum.other;

import goldenshadow.aurum.entities.DataManager;
import goldenshadow.aurum.other.ExperienceManager;
import goldenshadow.aurum.other.RespawnLocation;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RespawnManager {
    public static void playerDeath(Player player) {
        int xp = ExperienceManager.getXP(player);
        double deduction = (double)xp * 0.1;
        ExperienceManager.removeXp(player, (int)Math.round(deduction));
        player.sendMessage(((TextComponent)((TextComponent)Component.text((String)"[", (TextColor)NamedTextColor.DARK_RED).append((Component)Component.text((String)"!", (TextColor)NamedTextColor.RED))).append((Component)Component.text((String)"] ", (TextColor)NamedTextColor.DARK_RED))).append((Component)Component.text((String)"You died...", (TextColor)NamedTextColor.GRAY)));
        player.sendMessage(((TextComponent)((TextComponent)Component.text((String)"[", (TextColor)NamedTextColor.DARK_RED).append((Component)Component.text((String)"!", (TextColor)NamedTextColor.RED))).append((Component)Component.text((String)"] ", (TextColor)NamedTextColor.DARK_RED))).append((Component)Component.text((String)("-" + (int)Math.round(deduction) + " XP"), (TextColor)NamedTextColor.GRAY)));
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

