/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.World
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Listener
 */
package goldenshadow.aurum.entities;

import goldenshadow.aurum.entities.SpellManager;
import goldenshadow.aurum.entities.spells.RangedAttack;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class RangedAIManager
implements Listener {
    public static void loop() {
        ArrayList<World> worlds = new ArrayList<World>();
        for (World w : Bukkit.getWorlds()) {
            if (!Bukkit.getOnlinePlayers().stream().anyMatch(p -> p.getWorld().equals(w))) continue;
            worlds.add(w);
        }
        for (World world : worlds) {
            block2: for (LivingEntity entity : world.getLivingEntities()) {
                if (!entity.getScoreboardTags().contains("aurum_mob_ranged")) continue;
                for (Entity e : entity.getNearbyEntities(15.0, 15.0, 15.0)) {
                    if (!(e instanceof Player)) continue;
                    Player p2 = (Player)e;
                    if (!Bukkit.getOnlinePlayers().contains(p2)) continue;
                    SpellManager.cast(new RangedAttack(entity));
                    continue block2;
                }
            }
        }
    }
}

