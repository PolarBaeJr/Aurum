/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.World
 *  org.bukkit.entity.Player
 */
package goldenshadow.aurum.other;

import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class RespawnLocation {
    private final double x;
    private final double y;
    private final double z;
    private final World world;
    private final UUID uuid;
    private final int range;

    public RespawnLocation(World world, double x, double y, double z, int range) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
        this.range = range;
        this.uuid = UUID.randomUUID();
    }

    public void scan() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getLocation().getWorld() == null || !player.getLocation().getWorld().equals(this.getLocation().getWorld()) || !(player.getLocation().distance(this.getLocation()) <= (double)this.range)) continue;
            player.setBedSpawnLocation(this.getLocation(), true);
        }
    }

    public void highlight(Player player) {
        player.spawnParticle(Particle.REDSTONE, this.getLocation(), 10, (Object)new Particle.DustOptions(Color.TEAL, 3.0f));
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public Location getLocation() {
        return new Location(this.world, this.x, this.y, this.z);
    }

    public int getRange() {
        return this.range;
    }

    public World getWorld() {
        return this.world;
    }
}

