/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Skeleton
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityDamageEvent
 */
package goldenshadow.aurum.events;

import goldenshadow.aurum.entities.EntityHandler;
import goldenshadow.aurum.items.AttributeHandler;
import goldenshadow.aurum.items.RuneAbilityHandler;
import goldenshadow.aurum.other.TrainingDummy;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage
implements Listener {
    public static List<UUID> spellCasters = new ArrayList<UUID>();

    @EventHandler
    public void event(EntityDamageEvent event) {
        Skeleton s;
        Entity entity;
        if (event instanceof EntityDamageByEntityEvent) {
            Player player;
            Entity entity2;
            EntityDamageByEntityEvent damageByEntityEvent = (EntityDamageByEntityEvent)event;
            if (damageByEntityEvent.getEntity().getScoreboardTags().contains("aurum_mob") && (entity2 = damageByEntityEvent.getDamager()) instanceof Player) {
                Player p = (Player)entity2;
                if (spellCasters.contains(p.getUniqueId())) {
                    do {
                        spellCasters.remove(p.getUniqueId());
                    } while (spellCasters.contains(p.getUniqueId()));
                } else {
                    AttributeHandler.meleeHit(damageByEntityEvent);
                }
            }
            RuneAbilityHandler.entityDamageEntity(damageByEntityEvent);
            EntityHandler.damage(damageByEntityEvent);
            entity2 = damageByEntityEvent.getDamager();
            if (entity2 instanceof Player && (player = (Player)entity2).getScoreboardTags().contains("aurum_debug_damage")) {
                player.sendMessage(ChatColor.DARK_AQUA + "[Aurum] Debug: Damage dealt: " + damageByEntityEvent.getFinalDamage());
            }
        }
        if (event.getEntity() instanceof Player) {
            AttributeHandler.playerDamageTaken(event);
            RuneAbilityHandler.onDamageEvent(event);
        }
        if ((entity = event.getEntity()) instanceof Skeleton && (s = (Skeleton)entity).getScoreboardTags().contains("aurum_training_dummy")) {
            TrainingDummy.hit(s, event.getDamage());
        }
    }
}

