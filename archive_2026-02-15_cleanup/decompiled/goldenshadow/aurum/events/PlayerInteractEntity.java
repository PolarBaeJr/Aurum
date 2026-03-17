/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Slime
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerInteractEntityEvent
 *  org.bukkit.inventory.EquipmentSlot
 */
package goldenshadow.aurum.events;

import goldenshadow.aurum.entities.EntityHandler;
import goldenshadow.aurum.items.BossLootManager;
import goldenshadow.aurum.items.ConditionalInteractionHandler;
import goldenshadow.aurum.items.EventInteractionHandler;
import goldenshadow.aurum.items.ItemBuyer;
import goldenshadow.aurum.items.PickupInteractionHandler;
import goldenshadow.aurum.items.RuneSmith;
import goldenshadow.aurum.items.Treasure;
import goldenshadow.aurum.other.BlockInteractionDenier;
import goldenshadow.aurum.other.DoorHandler;
import goldenshadow.aurum.other.RuneStoneManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteractEntity
implements Listener {
    @EventHandler
    public void event(PlayerInteractEntityEvent event) {
        EntityHandler.interact(event);
        BlockInteractionDenier.entityInteract(event);
        if (event.getHand() == EquipmentSlot.HAND) {
            Entity entity;
            if (event.getRightClicked().getScoreboardTags().contains("aurum_rune_stone")) {
                RuneStoneManager.interact(event.getPlayer());
            }
            if (event.getRightClicked().getScoreboardTags().contains("aurum_item_buyer")) {
                ItemBuyer.interact(event);
            }
            if ((entity = event.getRightClicked()) instanceof Slime) {
                Slime slime = (Slime)entity;
                if (slime.getScoreboardTags().contains("aurum_door")) {
                    DoorHandler.rightClick(slime, event.getPlayer());
                }
                if (slime.getScoreboardTags().contains("aurum_chest")) {
                    Treasure.chestOpen(slime, event.getPlayer());
                }
                if (slime.getScoreboardTags().contains("aurum_runesmith")) {
                    RuneSmith.interact(event.getPlayer());
                }
                if (slime.getScoreboardTags().contains("aurum_pickup_interaction")) {
                    PickupInteractionHandler.interact(event.getPlayer(), slime);
                }
                if (slime.getScoreboardTags().contains("aurum_event_interaction")) {
                    EventInteractionHandler.interact(event.getPlayer(), slime);
                }
                if (slime.getScoreboardTags().contains("aurum_conditional_interaction")) {
                    ConditionalInteractionHandler.interact(event.getPlayer(), slime);
                }
                if (slime.getScoreboardTags().contains("aurum_boss_chest")) {
                    BossLootManager.interact(event.getPlayer(), slime);
                }
            }
        }
    }
}

