/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.adventure.text.Component
 *  net.kyori.adventure.text.TextComponent
 *  net.kyori.adventure.text.event.ClickEvent
 *  net.kyori.adventure.text.format.NamedTextColor
 *  net.kyori.adventure.text.format.TextColor
 *  net.kyori.adventure.text.format.TextDecoration
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
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
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
        Component click = Component.text((String)"[here]", (TextColor)NamedTextColor.YELLOW).clickEvent(ClickEvent.openUrl((String)"https://discord.gg/njRpZwKVaa"));
        return ((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.text((String)"------------------------------\n", (TextColor)NamedTextColor.AQUA).append((Component)Component.text((String)"    TAq 3rd Anniversary\n\n", (TextColor)NamedTextColor.GOLD, (TextDecoration[])new TextDecoration[]{TextDecoration.BOLD}))).append((Component)Component.text((String)" Join our discord ", (TextColor)NamedTextColor.YELLOW))).append(click)).append((Component)Component.text((String)" to hang\n", (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)"     out with us in event hall!\n\n", (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)"------------------------------", (TextColor)NamedTextColor.AQUA));
    }

    public static Component getInfoMessage() {
        Component click = Component.text((String)"[here]", (TextColor)NamedTextColor.YELLOW).clickEvent(ClickEvent.openUrl((String)"https://docs.google.com/document/d/1RXn68pv6mgQt4kWJRPcL0gqlCkc0LXArSIPXBhWpIPg/edit?usp=sharing"));
        return ((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.text((String)"------------------------------\n", (TextColor)NamedTextColor.AQUA).append((Component)Component.text((String)"    TAq 3rd Anniversary\n\n", (TextColor)NamedTextColor.GOLD, (TextDecoration[])new TextDecoration[]{TextDecoration.BOLD}))).append((Component)Component.text((String)" Read up on important information\n", (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)"      about the event ", (TextColor)NamedTextColor.YELLOW))).append(click)).append((Component)Component.text((String)"\n\n", (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)"------------------------------", (TextColor)NamedTextColor.AQUA));
    }
}

