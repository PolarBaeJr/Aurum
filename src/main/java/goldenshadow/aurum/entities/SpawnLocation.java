/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.adventure.text.Component
 *  net.kyori.adventure.text.TextComponent
 *  net.kyori.adventure.text.format.NamedTextColor
 *  net.kyori.adventure.text.format.TextColor
 *  org.bukkit.Location
 *  org.bukkit.World
 */
package goldenshadow.aurum.entities;

import goldenshadow.aurum.entities.SpawnInterval;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
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
        return ((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.text((String)"=======================================\n", (TextColor)NamedTextColor.DARK_AQUA).append((Component)Component.text((String)"UUID: ", (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)this.uuid.toString(), (TextColor)NamedTextColor.AQUA))).append((Component)Component.text((String)", Entity: ", (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)(this.entity + "\n"), (TextColor)NamedTextColor.AQUA))).append((Component)Component.text((String)"Spawn Interval: ", (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)this.spawn_interval.toString(), (TextColor)NamedTextColor.AQUA))).append((Component)Component.text((String)", Respawn Tolerance: ", (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)(this.respawn_tolerance + "\n"), (TextColor)NamedTextColor.AQUA))).append((Component)Component.text((String)"X: ", (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)String.valueOf(this.x), (TextColor)NamedTextColor.AQUA))).append((Component)Component.text((String)", Y: ", (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)String.valueOf(this.y), (TextColor)NamedTextColor.AQUA))).append((Component)Component.text((String)", Z: ", (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)(this.z + "\n"), (TextColor)NamedTextColor.AQUA))).append((Component)Component.text((String)"=======================================", (TextColor)NamedTextColor.DARK_AQUA));
    }
}

