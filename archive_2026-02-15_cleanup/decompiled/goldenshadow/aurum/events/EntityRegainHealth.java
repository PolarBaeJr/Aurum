/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityRegainHealthEvent
 */
package goldenshadow.aurum.events;

import goldenshadow.aurum.items.RuneAbilityHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class EntityRegainHealth
implements Listener {
    @EventHandler
    public void event(EntityRegainHealthEvent event) {
        RuneAbilityHandler.onRegenHealth(event);
    }
}

