/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.inventory.InventoryCloseEvent
 */
package goldenshadow.aurum.events;

import goldenshadow.aurum.items.BankManager;
import goldenshadow.aurum.items.ItemBuyer;
import goldenshadow.aurum.items.RuneSmith;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClose
implements Listener {
    @EventHandler
    public void event(InventoryCloseEvent event) {
        if (RuneSmith.isRuneSmithGUI(event.getPlayer().getOpenInventory().getTitle())) {
            RuneSmith.invExit((Player)event.getPlayer());
        }
        if (ItemBuyer.isItemBuyerGUI(event.getPlayer().getOpenInventory().getTitle())) {
            ItemBuyer.invExit((Player)event.getPlayer());
        }
        BankManager.closed(event);
    }
}

