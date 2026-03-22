/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.adventure.text.Component
 *  net.kyori.adventure.text.TextComponent
 *  net.kyori.adventure.text.format.NamedTextColor
 *  net.kyori.adventure.text.format.TextColor
 *  net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
 *  org.bukkit.Bukkit
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
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
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
            p.sendMessage(((TextComponent)((TextComponent)Component.text((String)"[", (TextColor)NamedTextColor.DARK_AQUA).append((Component)Component.text((int)this.getInterval(), (TextColor)NamedTextColor.AQUA))).append((Component)Component.text((String)"] Iterating through list of size ", (TextColor)NamedTextColor.DARK_AQUA))).append((Component)Component.text((int)this.rootList.get(this.index).size(), (TextColor)NamedTextColor.AQUA)));
        }
        for (SpawnLocation location : this.rootList.get(this.index)) {
            this.spawnMob(location);
            ++attemptedSpawns;
        }
        DiagnosticLogger.add("[" + String.valueOf(LocalTime.now()) + "] Interval " + this.getInterval() + " -> Attempted: " + attemptedSpawns + ", Successful: " + this.successfulSpawns + " Spawned mobs: " + String.valueOf(this.spawnedThisTick));
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
            Component entityName = entity.customName();
            this.spawnedThisTick.add(entityName != null ? PlainTextComponentSerializer.plainText().serialize(entityName) : "Unknown");
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!p.getScoreboardTags().contains("aurum_debug_precise_mob_spawns")) continue;
                p.sendMessage(((TextComponent)((TextComponent)Component.text((String)"Spawned new ", (TextColor)NamedTextColor.DARK_AQUA).append((Component)(entityName != null ? entityName : Component.text((String)"Unknown", (TextColor)NamedTextColor.AQUA)))).append((Component)Component.text((String)" at ", (TextColor)NamedTextColor.DARK_AQUA))).append((Component)Component.text((String)((int)location.getLocation().getX() + " " + (int)location.getLocation().getY() + " " + (int)location.getLocation().getZ()), (TextColor)NamedTextColor.AQUA)));
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

    public Component toComponent() {
        return ((TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.text((String)"------------------\n", (TextColor)NamedTextColor.DARK_AQUA).append((Component)Component.text((String)"Interval: ", (TextColor)NamedTextColor.DARK_AQUA))).append((Component)Component.text((String)(this.getInterval() + "s\n"), (TextColor)NamedTextColor.AQUA))).append((Component)Component.text((String)"Total Size: ", (TextColor)NamedTextColor.DARK_AQUA))).append((Component)Component.text((String)(this.totalSize + " locations\n"), (TextColor)NamedTextColor.AQUA))).append((Component)Component.text((String)"------------------", (TextColor)NamedTextColor.DARK_AQUA));
    }

    public static void listLoadedWorlds() {
        loadedWorlds.clear();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (loadedWorlds.contains(p.getWorld())) continue;
            loadedWorlds.add(p.getWorld());
        }
    }
}

