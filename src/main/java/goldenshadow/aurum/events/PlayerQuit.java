/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerQuitEvent
 */
package goldenshadow.aurum.events;

import goldenshadow.aurum.entities.EntityHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit
implements Listener {
    @EventHandler
    public void event(PlayerQuitEvent event) {
        EntityHandler.onPlayerQuit(event.getPlayer());
    }
}

