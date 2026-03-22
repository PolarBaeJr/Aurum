/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Zombie
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntitySpawnEvent
 *  org.bukkit.event.entity.EntitySpellCastEvent
 *  org.bukkit.event.entity.EntityTeleportEvent
 *  org.bukkit.event.entity.EntityTransformEvent
 *  org.bukkit.event.entity.EntityTransformEvent$TransformReason
 *  org.bukkit.event.entity.VillagerCareerChangeEvent
 *  org.bukkit.event.player.PlayerDropItemEvent
 *  org.bukkit.event.player.PlayerItemHeldEvent
 *  org.bukkit.event.player.PlayerSwapHandItemsEvent
 *  org.bukkit.event.world.ChunkUnloadEvent
 */
package goldenshadow.aurum.events;

import goldenshadow.aurum.entities.EntityHandler;
import goldenshadow.aurum.items.AttributeHandler;
import goldenshadow.aurum.items.RuneAbilityHandler;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntitySpellCastEvent;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.entity.EntityTransformEvent;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

public class MiscEvents
implements Listener {
    @EventHandler
    public void heldEvent(PlayerItemHeldEvent event) {
        RuneAbilityHandler.deactivateAbilities(event.getPlayer());
    }

    @EventHandler
    public void swapEvent(PlayerSwapHandItemsEvent event) {
        RuneAbilityHandler.deactivateAbilities(event.getPlayer());
    }

    @EventHandler
    public void switchEvent(PlayerItemHeldEvent event) {
        AttributeHandler.itemSwitched(event.getPlayer());
    }

    @EventHandler
    public void dropEvent(PlayerDropItemEvent event) {
        RuneAbilityHandler.deactivateAbilities(event.getPlayer());
    }

    @EventHandler
    public void spellCancel(EntitySpellCastEvent event) {
        if (event.getEntity().getType() == EntityType.EVOKER && event.getEntity().getScoreboardTags().contains("aurum_mob")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void transform(EntityTransformEvent event) {
        if (event.getTransformReason() == EntityTransformEvent.TransformReason.DROWNED && event.getEntity().getScoreboardTags().contains("aurum_mob")) {
            event.setCancelled(true);
            if (event.getEntity() instanceof Zombie) {
                ((Zombie)event.getEntity()).setConversionTime(-1);
            }
        }
        if (event.getTransformReason() == EntityTransformEvent.TransformReason.LIGHTNING && event.getEntity().getScoreboardTags().contains("aurum_mob")) {
            event.setCancelled(true);
        }
        if ((event.getEntityType() == EntityType.SLIME || event.getEntityType() == EntityType.MAGMA_CUBE) && event.getTransformReason() == EntityTransformEvent.TransformReason.SPLIT && event.getEntity().getScoreboardTags().contains("aurum_mob")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void endermanTeleport(EntityTeleportEvent event) {
        if ((event.getEntityType() == EntityType.ENDERMAN || event.getEntityType() == EntityType.SHULKER) && event.getEntity().getScoreboardTags().contains("aurum_mob")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void unload(ChunkUnloadEvent event) {
        EntityHandler.unload(event);
    }

    @EventHandler
    public void jockeyRemover(EntitySpawnEvent event) {
        if (event.getEntity().getScoreboardTags().contains("aurum_mob") && event.getEntity().getVehicle() != null) {
            event.getEntity().getVehicle().remove();
        }
    }

    @EventHandler
    public void villagerProfessionChange(VillagerCareerChangeEvent event) {
        if (event.getEntity().getScoreboardTags().contains("aurum_mob")) {
            event.setCancelled(true);
        }
    }
}

