/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.inventory.InventoryClickEvent
 */
package goldenshadow.aurum.events;

import goldenshadow.aurum.items.ItemBuyer;
import goldenshadow.aurum.items.RuneAbilityHandler;
import goldenshadow.aurum.items.RuneSmith;
import goldenshadow.aurum.other.RuneStoneManager;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick
implements Listener {
    @EventHandler
    public void event(InventoryClickEvent event) {
        String title = PlainTextComponentSerializer.plainText().serialize(event.getWhoClicked().getOpenInventory().title());
        if (title.equals("Rune Stone")) {
            RuneStoneManager.inventoryClick(event);
        }
        if (RuneSmith.isRuneSmithGUI(title)) {
            RuneSmith.invClick(event);
        }
        if (ItemBuyer.isItemBuyerGUI(title)) {
            ItemBuyer.invClick(event);
        }
        Player player = (Player)event.getWhoClicked();
        if (event.getSlot() == player.getInventory().getHeldItemSlot()) {
            RuneAbilityHandler.deactivateAbilities(player);
        }
    }
}

