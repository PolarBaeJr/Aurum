/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.Sound
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.util.Vector
 */
package goldenshadow.aurum.entities.spells;

import goldenshadow.aurum.entities.spells.Spell;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Flash
extends Spell {
    private Location currentLocation = this.getInitialLocation();

    public Flash(LivingEntity entity) {
        super(entity);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTick() > 22 && this.getTick() < 33) {
            assert (this.currentLocation.getWorld() != null);
            this.currentLocation.getWorld().spawnParticle(Particle.END_ROD, this.currentLocation, 0);
            this.currentLocation = this.currentLocation.add(this.currentLocation.getDirection().multiply(0.5));
        }
        if (this.getTick() == 33) {
            this.explosion(this.currentLocation);
            this.currentLocation.setDirection(this.shiftRotation(this.currentLocation));
        }
        if (this.getTick() > 33 && this.getTick() < 44) {
            assert (this.currentLocation.getWorld() != null);
            this.currentLocation.getWorld().spawnParticle(Particle.END_ROD, this.currentLocation, 0);
            this.currentLocation = this.currentLocation.add(this.currentLocation.getDirection().multiply(0.5));
        }
        if (this.getTick() == 44) {
            this.explosion(this.currentLocation);
            this.currentLocation.setDirection(this.shiftRotation(this.currentLocation));
        }
        if (this.getTick() > 44 && this.getTick() < 55) {
            assert (this.currentLocation.getWorld() != null);
            this.currentLocation.getWorld().spawnParticle(Particle.END_ROD, this.currentLocation, 0);
            this.currentLocation = this.currentLocation.add(this.currentLocation.getDirection().multiply(0.5));
        }
        if (this.getTick() == 56) {
            this.explosion(this.currentLocation);
            this.currentLocation.setDirection(this.shiftRotation(this.currentLocation));
            this.spellFinished();
        }
    }

    private void explosion(Location location) {
        assert (location.getWorld() != null);
        location.getWorld().playSound(location, Sound.ENTITY_FIREWORK_ROCKET_BLAST, 10.0f, 1.0f);
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getLocation().getWorld() != location.getWorld() || !(player.getLocation().distance(location) < 4.0)) continue;
            player.damage(Objects.requireNonNull(this.getEntity().getAttribute(Attribute.ATTACK_DAMAGE)).getValue() * 1.5 * this.getSpellDamageMultiplier(), (Entity)this.getEntity());
        }
        for (double i = 0.0; i <= Math.PI; i += 0.3141592653589793) {
            double radius = Math.sin(i);
            double y = Math.cos(i);
            for (double a = 0.0; a < Math.PI * 2; a += 0.3141592653589793) {
                double x = Math.cos(a) * radius;
                double z = Math.sin(a) * radius;
                location.add(x, y, z);
                location.getWorld().spawnParticle(Particle.FIREWORK, location, 0);
                location.subtract(x, y, z);
            }
        }
    }

    private Vector shiftRotation(Location location) {
        Vector vector = location.getDirection();
        vector.setX(vector.getX() + ThreadLocalRandom.current().nextDouble(-0.5, 0.5));
        vector.setZ(vector.getZ() + ThreadLocalRandom.current().nextDouble(-0.5, 0.5));
        vector.setX(vector.getY() + ThreadLocalRandom.current().nextDouble(-0.5, 0.5));
        return vector;
    }
}

