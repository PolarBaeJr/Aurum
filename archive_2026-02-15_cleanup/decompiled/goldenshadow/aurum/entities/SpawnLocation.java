/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Location
 *  org.bukkit.World
 */
package goldenshadow.aurum.entities;

import goldenshadow.aurum.entities.SpawnInterval;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;

public class SpawnLocation {
    private final String entity;
    private final SpawnInterval spawn_interval;
    private final int respawn_tolerance;
    private final double x;
    private final double y;
    private final double z;
    private final World world;
    private final UUID uuid;

    public SpawnLocation(World world, double x, double y, double z, String entity, SpawnInterval spawnInterval, int respawnTolerance) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
        this.entity = entity;
        this.spawn_interval = spawnInterval;
        this.respawn_tolerance = respawnTolerance;
        this.uuid = UUID.randomUUID();
    }

    public String getEntity() {
        return this.entity;
    }

    public SpawnInterval getSpawn_interval() {
        return this.spawn_interval;
    }

    public int getRespawn_tolerance() {
        return this.respawn_tolerance;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public Location getLocation() {
        return new Location(this.world, this.x, this.y, this.z);
    }

    public World getWorld() {
        return this.world;
    }

    public double getY() {
        return this.y;
    }

    public double getX() {
        return this.x;
    }

    public double getZ() {
        return this.z;
    }

    public String toString() {
        String string = "";
        string = string.concat(ChatColor.DARK_AQUA + "=======================================\n");
        string = string.concat(ChatColor.YELLOW + "UUID: " + ChatColor.AQUA + this.uuid.toString() + ChatColor.YELLOW + ", Entity: " + ChatColor.AQUA + this.entity + "\n");
        string = string.concat(ChatColor.YELLOW + "Spawn Interval: " + ChatColor.AQUA + this.spawn_interval.toString() + ChatColor.YELLOW + ", Respawn Tolerance: " + ChatColor.AQUA + this.respawn_tolerance + "\n");
        string = string.concat(ChatColor.YELLOW + "X: " + ChatColor.AQUA + this.x + ChatColor.YELLOW + ", Y: " + ChatColor.AQUA + this.y + ChatColor.YELLOW + ", Z: " + ChatColor.AQUA + this.z + "\n");
        string = string.concat(ChatColor.DARK_AQUA + "=======================================");
        return string;
    }
}

