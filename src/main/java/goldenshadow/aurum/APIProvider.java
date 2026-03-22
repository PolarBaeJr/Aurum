/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Slime
 */
package goldenshadow.aurum;

import goldenshadow.aurum.entities.CustomEntity;
import goldenshadow.aurum.entities.DataManager;
import goldenshadow.aurum.items.RuneAbilityHandler;
import goldenshadow.aurum.other.Door;
import goldenshadow.aurum.other.DoorHandler;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;

public class APIProvider {
    public static void spawnMobAtLocation(Location location, String mobName, UUID uuid) {
        assert (location.getWorld() != null);
        if (DataManager.getListOfMobKeys().contains(mobName.toLowerCase())) {
            for (Entity e : location.getWorld().getNearbyEntities(location, 10.0, 10.0, 10.0)) {
                if (!e.getUniqueId().equals(uuid) || !(e instanceof LivingEntity)) continue;
                CustomEntity.deserialize((LivingEntity)e, DataManager.getMobByName(mobName.toLowerCase()), null);
                break;
            }
        }
    }

    public static void openDoorAtLocation(Location location) {
        assert (location.getWorld() != null);
        for (Entity e : location.getWorld().getNearbyEntities(location, 3.0, 3.0, 3.0)) {
            Slime s;
            if (!(e instanceof Slime) || !(s = (Slime)e).getScoreboardTags().contains("aurum_door")) continue;
            DoorHandler.addDoor(DoorHandler.buildDoor(s));
            for (Door d : DoorHandler.getActiveDoors()) {
                if (!d.getRoot().equals((Object)s)) continue;
                d.addTokens(d.getRequiredTokens());
            }
        }
    }

    public static void clearTokensFromPlayer(Player player) {
        DoorHandler.clearTokens(player);
    }

    public static void disableAbilities(Player player) {
        RuneAbilityHandler.isBloodrushNotActive(player);
    }
}

