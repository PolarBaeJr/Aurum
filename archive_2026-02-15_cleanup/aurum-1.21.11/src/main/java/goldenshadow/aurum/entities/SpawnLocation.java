/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.World
 */
package goldenshadow.aurum.entities;

import goldenshadow.aurum.entities.SpawnInterval;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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

    public Component toComponent() {
        return Component.text("=======================================\n", NamedTextColor.DARK_AQUA)
                .append(Component.text("UUID: ", NamedTextColor.YELLOW))
                .append(Component.text(this.uuid.toString(), NamedTextColor.AQUA))
                .append(Component.text(", Entity: ", NamedTextColor.YELLOW))
                .append(Component.text(this.entity + "\n", NamedTextColor.AQUA))
                .append(Component.text("Spawn Interval: ", NamedTextColor.YELLOW))
                .append(Component.text(this.spawn_interval.toString(), NamedTextColor.AQUA))
                .append(Component.text(", Respawn Tolerance: ", NamedTextColor.YELLOW))
                .append(Component.text(this.respawn_tolerance + "\n", NamedTextColor.AQUA))
                .append(Component.text("X: ", NamedTextColor.YELLOW))
                .append(Component.text(String.valueOf(this.x), NamedTextColor.AQUA))
                .append(Component.text(", Y: ", NamedTextColor.YELLOW))
                .append(Component.text(String.valueOf(this.y), NamedTextColor.AQUA))
                .append(Component.text(", Z: ", NamedTextColor.YELLOW))
                .append(Component.text(this.z + "\n", NamedTextColor.AQUA))
                .append(Component.text("=======================================", NamedTextColor.DARK_AQUA));
    }
}

