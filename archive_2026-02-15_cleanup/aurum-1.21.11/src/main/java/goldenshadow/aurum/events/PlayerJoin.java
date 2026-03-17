/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  net.md_5.bungee.api.chat.BaseComponent
 *  net.md_5.bungee.api.chat.ClickEvent
 *  net.md_5.bungee.api.chat.ClickEvent$Action
 *  net.md_5.bungee.api.chat.ComponentBuilder
 *  net.md_5.bungee.api.chat.TextComponent
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerJoinEvent
 */
package goldenshadow.aurum.events;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.items.BankManager;
import goldenshadow.aurum.other.ExperienceManager;
import java.util.concurrent.ThreadLocalRandom;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin
implements Listener {
    @EventHandler
    public void event(PlayerJoinEvent event) {
        if (Aurum.getPlugin().getConfig().getBoolean("advertisement")) {
            if (ThreadLocalRandom.current().nextBoolean()) {
                event.getPlayer().sendMessage(PlayerJoin.getAdvertisementMessage());
            } else {
                event.getPlayer().sendMessage(PlayerJoin.getInfoMessage());
            }
        }
        if (Aurum.getPlugin().getConfig().getBoolean("ExperienceSystem")) {
            ExperienceManager.joinEvent(event.getPlayer());
        }
        BankManager.join(event.getPlayer());
    }

    public static Component getAdvertisementMessage() {
        Component click = Component.text("[here]", NamedTextColor.YELLOW)
            .clickEvent(ClickEvent.openUrl("https://discord.gg/njRpZwKVaa"));
        return Component.text("------------------------------\n", NamedTextColor.AQUA)
            .append(Component.text("    TAq 3rd Anniversary\n\n", NamedTextColor.GOLD, TextDecoration.BOLD))
            .append(Component.text(" Join our discord ", NamedTextColor.YELLOW))
            .append(click)
            .append(Component.text(" to hang\n", NamedTextColor.YELLOW))
            .append(Component.text("     out with us in event hall!\n\n", NamedTextColor.YELLOW))
            .append(Component.text("------------------------------", NamedTextColor.AQUA));
    }

    public static Component getInfoMessage() {
        Component click = Component.text("[here]", NamedTextColor.YELLOW)
            .clickEvent(ClickEvent.openUrl("https://docs.google.com/document/d/1RXn68pv6mgQt4kWJRPcL0gqlCkc0LXArSIPXBhWpIPg/edit?usp=sharing"));
        return Component.text("------------------------------\n", NamedTextColor.AQUA)
            .append(Component.text("    TAq 3rd Anniversary\n\n", NamedTextColor.GOLD, TextDecoration.BOLD))
            .append(Component.text(" Read up on important information\n", NamedTextColor.YELLOW))
            .append(Component.text("      about the event ", NamedTextColor.YELLOW))
            .append(click)
            .append(Component.text("\n\n", NamedTextColor.YELLOW))
            .append(Component.text("------------------------------", NamedTextColor.AQUA));
    }
}

