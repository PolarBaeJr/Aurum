/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.NamespacedKey
 *  org.bukkit.World
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 */
package goldenshadow.aurum.entities;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.entities.CustomEntity;
import goldenshadow.aurum.entities.DataManager;
import goldenshadow.aurum.entities.DiagnosticLogger;
import goldenshadow.aurum.entities.EntityData;
import goldenshadow.aurum.entities.SpawnLocation;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class IntervalSpawner {
    private static final List<World> loadedWorlds = new ArrayList<World>();
    private final List<List<SpawnLocation>> rootList = new ArrayList<List<SpawnLocation>>();
    private final int interval;
    private int index = 0;
    private final int totalSize;
    private final List<String> spawnedThisTick = new ArrayList<String>();
    private int successfulSpawns = 0;

    public IntervalSpawner(List<SpawnLocation> locationList, int interval) {
        this.interval = interval * 2;
        this.totalSize = locationList.size();
        int size = locationList.size();
        if (this.interval > size) {
            for (SpawnLocation location : locationList) {
                ArrayList<SpawnLocation> chunk = new ArrayList<SpawnLocation>();
                chunk.add(location);
                this.rootList.add(chunk);
            }
            for (int i = 0; i < this.interval - size; ++i) {
                this.rootList.add(new ArrayList());
            }
        } else {
            int chunkSize = size / this.interval;
            int start = 0;
            int end = chunkSize;
            for (int i = 0; i < this.interval; ++i) {
                if (i == this.interval - 1) {
                    end = size;
                }
                List<SpawnLocation> chunk = locationList.subList(start, end);
                this.rootList.add(chunk);
                start = end;
                end += chunkSize;
            }
        }
    }

    public int getInterval() {
        return this.interval / 2;
    }

    public void ticker() {
        this.successfulSpawns = 0;
        int attemptedSpawns = 0;
        this.spawnedThisTick.clear();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.getScoreboardTags().contains("aurum_debug_spawning")) continue;
            p.sendMessage(Component.text("[", NamedTextColor.DARK_AQUA)
                    .append(Component.text(this.getInterval(), NamedTextColor.AQUA))
                    .append(Component.text("] Iterating through list of size ", NamedTextColor.DARK_AQUA))
                    .append(Component.text(this.rootList.get(this.index).size(), NamedTextColor.AQUA)));
        }
        for (SpawnLocation location : this.rootList.get(this.index)) {
            this.spawnMob(location);
            ++attemptedSpawns;
        }
        DiagnosticLogger.add("[" + LocalTime.now() + "] Interval " + this.getInterval() + " -> Attempted: " + attemptedSpawns + ", Successful: " + this.successfulSpawns + " Spawned mobs: " + this.spawnedThisTick);
        ++this.index;
        if (this.index >= this.rootList.size()) {
            this.index = 0;
        }
    }

    private void spawnMob(SpawnLocation location) {
        EntityData mob;
        if (this.isLocationLoaded(location) && this.canNodeSpawnMobs(location) && (mob = DataManager.getMobByName(location.getEntity())) != null) {
            LivingEntity entity = (LivingEntity)location.getWorld().spawnEntity(location.getLocation(), mob.getType(), false);
            CustomEntity.deserialize(entity, DataManager.getMobByName(location.getEntity()), location.getUuid());
            ++this.successfulSpawns;
            this.spawnedThisTick.add(entity.getCustomName());
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!p.getScoreboardTags().contains("aurum_debug_precise_mob_spawns")) continue;
                p.sendMessage(ChatColor.DARK_AQUA + "Spawned new " + ChatColor.AQUA + entity.getCustomName() + ChatColor.DARK_AQUA + " at " + ChatColor.AQUA + (int)location.getLocation().getX() + " " + (int)location.getLocation().getY() + " " + (int)location.getLocation().getZ());
            }
        }
    }

    private boolean isLocationLoaded(SpawnLocation location) {
        if (location.getWorld() != null && loadedWorlds.contains(location.getWorld())) {
            return location.getWorld().getNearbyEntities(location.getLocation(), 64.0, 64.0, 64.0).stream().anyMatch(x -> {
                if (x instanceof Player) {
                    return Bukkit.getOnlinePlayers().contains(x);
                }
                return false;
            });
        }
        return false;
    }

    private boolean canNodeSpawnMobs(SpawnLocation location) {
        if (location.getWorld() != null) {
            int count = 0;
            NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "NodeUUID");
            for (Entity e : location.getWorld().getNearbyEntities(location.getLocation(), 50.0, 50.0, 50.0)) {
                String uuid;
                if (!e.getPersistentDataContainer().has(key, PersistentDataType.STRING) || (uuid = (String)e.getPersistentDataContainer().get(key, PersistentDataType.STRING)) == null || !uuid.equals(location.getUuid().toString())) continue;
                ++count;
            }
            return count < location.getRespawn_tolerance();
        }
        return false;
    }

    public String toString() {
        String s = "";
        s = s.concat(ChatColor.DARK_AQUA + "------------------\n");
        s = s.concat(ChatColor.DARK_AQUA + "Interval: " + ChatColor.AQUA + this.getInterval() + "s\n");
        s = s.concat(ChatColor.DARK_AQUA + "Total Size: " + ChatColor.AQUA + this.totalSize + " locations\n");
        s = s.concat(ChatColor.DARK_AQUA + "------------------");
        return s;
    }

    public static void listLoadedWorlds() {
        loadedWorlds.clear();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (loadedWorlds.contains(p.getWorld())) continue;
            loadedWorlds.add(p.getWorld());
        }
    }
}

