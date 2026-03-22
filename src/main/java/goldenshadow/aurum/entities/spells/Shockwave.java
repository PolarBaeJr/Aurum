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
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Shockwave
extends Spell {
    private final Location center;
    private final double damage;

    public Shockwave(LivingEntity entity) {
        super(entity);
        this.center = entity.getLocation().clone().add(0.0, 0.5, 0.0);
        this.damage = Objects.requireNonNull(this.getEntity().getAttribute(Attribute.ATTACK_DAMAGE)).getValue() * 2.0 * this.getSpellDamageMultiplier();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTick() == 21) {
            assert (this.center.getWorld() != null);
            this.center.getWorld().playSound(this.center, Sound.ENTITY_WARDEN_SONIC_BOOM, 10.0f, 1.0f);
        }
        if (this.getTick() > 20 && this.getTick() < 50 && this.getTick() % 2 == 0) {
            this.drawCircle(this.center, (this.getTick() - 20) / 2);
        }
        if (this.getTick() == 60) {
            this.spellFinished();
        }
    }

    private void drawCircle(Location location, int size) {
        double maxAngle = Math.PI * 2;
        double angleIncrement = 0.19634954084936207;
        Particle particle = Particle.CRIT;
        for (double angle = 0.0; angle < Math.PI * 2; angle += 0.19634954084936207) {
            double x = Math.cos(angle) * (double)size;
            double z = Math.sin(angle) * (double)size;
            location.add(x, 0.0, z);
            assert (location.getWorld() != null);
            location.getWorld().spawnParticle(particle, location, 5, 0.2, 0.2, 0.2, 0.0);
            this.damageNearby(location);
            location.subtract(x, 0.0, z);
        }
    }

    private void damageNearby(Location loc) {
        assert (loc.getWorld() != null);
        for (Entity e : loc.getWorld().getNearbyEntities(loc, 1.0, 0.2, 1.0)) {
            if (!(e instanceof Player)) continue;
            Player p = (Player)e;
            if (!Bukkit.getOnlinePlayers().contains(p)) continue;
            p.damage(this.damage, (Entity)this.getEntity());
            p.setVelocity(Shockwave.calculateKnockbackVector(this.getEntity().getLocation(), p.getLocation()));
        }
    }

    private static Vector calculateKnockbackVector(Location source, Location target) {
        Vector v = target.toVector().subtract(source.toVector()).normalize();
        v.setX(v.getX() * 0.4);
        v.setZ(v.getZ() * 0.4);
        v.setY(0.5);
        return v;
    }
}

