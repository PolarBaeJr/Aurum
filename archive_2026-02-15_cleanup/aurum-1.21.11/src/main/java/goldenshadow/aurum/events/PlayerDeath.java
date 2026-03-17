/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.PlayerDeathEvent
 */
package goldenshadow.aurum.events;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.items.RuneAbilityHandler;
import goldenshadow.aurum.other.RespawnManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath
implements Listener {
    @EventHandler
    public void event(PlayerDeathEvent event) {
        if (Aurum.getPlugin().getConfig().getBoolean("RespawnSystem")) {
            RespawnManager.playerDeath(event.getEntity());
        }
        RuneAbilityHandler.onDeath(event.getEntity());
        event.getEntity().getScoreboardTags().removeIf(x -> x.contains("bossChest_"));
    }
}

