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
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin
implements Listener {
    @EventHandler
    public void event(PlayerJoinEvent event) {
        if (Aurum.getPlugin().getConfig().getBoolean("advertisement")) {
            if (ThreadLocalRandom.current().nextBoolean()) {
                event.getPlayer().spigot().sendMessage(PlayerJoin.getAdvertisementMessage());
            } else {
                event.getPlayer().spigot().sendMessage(PlayerJoin.getInfoMessage());
            }
        }
        if (Aurum.getPlugin().getConfig().getBoolean("ExperienceSystem")) {
            ExperienceManager.joinEvent(event.getPlayer());
        }
        BankManager.join(event.getPlayer());
    }

    public static BaseComponent[] getAdvertisementMessage() {
        TextComponent click = new TextComponent("[here]");
        click.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/njRpZwKVaa"));
        return new ComponentBuilder("------------------------------\n").color(ChatColor.AQUA).append("    TAq 3rd Anniversary\n\n").bold(true).color(ChatColor.GOLD).append(" Join our discord ").bold(false).color(ChatColor.YELLOW).append((BaseComponent)click).append(" to hang\n").append("     out with us in event hall!\n\n").append("------------------------------").color(ChatColor.AQUA).create();
    }

    public static BaseComponent[] getInfoMessage() {
        TextComponent click = new TextComponent("[here]");
        click.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://docs.google.com/document/d/1RXn68pv6mgQt4kWJRPcL0gqlCkc0LXArSIPXBhWpIPg/edit?usp=sharing"));
        return new ComponentBuilder("------------------------------\n").color(ChatColor.AQUA).append("    TAq 3rd Anniversary\n\n").bold(true).color(ChatColor.GOLD).append(" Read up on important information\n").bold(false).color(ChatColor.YELLOW).append("      about the event ").append((BaseComponent)click).append("\n\n").append("------------------------------").color(ChatColor.AQUA).create();
    }
}

