/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerFishEvent
 */
package goldenshadow.aurum.events;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.items.Treasure;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class PlayerFish
implements Listener {
    @EventHandler
    public void event(PlayerFishEvent event) {
        if (Aurum.getPlugin().getConfig().getBoolean("custom-fishing")) {
            Treasure.fishing(event);
        }
    }
}

