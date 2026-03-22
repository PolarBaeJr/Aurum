/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.inventory.EquipmentSlot
 */
package goldenshadow.aurum.events;

import goldenshadow.aurum.items.AttributeHandler;
import goldenshadow.aurum.items.BankManager;
import goldenshadow.aurum.items.ConsumableHandler;
import goldenshadow.aurum.items.ItemLevelHandler;
import goldenshadow.aurum.items.RuneAbilityHandler;
import goldenshadow.aurum.other.BlockInteractionDenier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteract
implements Listener {
    @EventHandler
    public void event(PlayerInteractEvent event) {
        BlockInteractionDenier.interaction(event);
        ItemLevelHandler.onWeaponRightClick(event);
        if (event.getHand() == EquipmentSlot.HAND) {
            if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                AttributeHandler.entityAttackEvent(event);
            }
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                RuneAbilityHandler.onClick(event);
            }
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                ConsumableHandler.rightClick(event.getPlayer());
            }
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                BankManager.opened(event);
            }
        }
    }
}

