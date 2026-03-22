/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.inventory.InventoryCloseEvent
 */
package goldenshadow.aurum.events;

import goldenshadow.aurum.items.BankManager;
import goldenshadow.aurum.items.ItemBuyer;
import goldenshadow.aurum.items.RuneSmith;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClose
implements Listener {
    @EventHandler
    public void event(InventoryCloseEvent event) {
        if (RuneSmith.isRuneSmithGUI(PlainTextComponentSerializer.plainText().serialize(event.getPlayer().getOpenInventory().title()))) {
            RuneSmith.invExit((Player)event.getPlayer());
        }
        if (ItemBuyer.isItemBuyerGUI(PlainTextComponentSerializer.plainText().serialize(event.getPlayer().getOpenInventory().title()))) {
            ItemBuyer.invExit((Player)event.getPlayer());
        }
        BankManager.closed(event);
    }
}

