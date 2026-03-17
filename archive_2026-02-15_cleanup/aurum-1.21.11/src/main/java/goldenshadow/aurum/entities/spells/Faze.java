/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.Particle$DustOptions
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
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Faze
extends Spell {
    private Location targetLoc;
    private final double damage = Objects.requireNonNull(this.getEntity().getAttribute(Attribute.ATTACK_DAMAGE)).getValue() * 2.5 * this.getSpellDamageMultiplier();
    private Vector directionVector;

    public Faze(LivingEntity entity) {
        super(entity);
    }

    @Override
    public void tick() {
        Player p;
        super.tick();
        if (this.getTick() == 21 && (p = Faze.getTarget(this.getInitialLocation())) != null) {
            this.targetLoc = p.getLocation();
            this.directionVector = this.getVector().normalize();
        }
        if (this.getTick() > 21 && this.getTick() < 30) {
            this.getEntity().getWorld().spawnParticle(Particle.WITCH, this.getEntity().getLocation(), 10, 0.4, 0.0, 0.4, 0.0);
            if (this.directionVector != null) {
                this.getEntity().teleport(this.getEntity().getLocation().setDirection(this.directionVector));
            }
        }
        if (this.getTick() == 30 && this.targetLoc != null) {
            this.getEntity().setVelocity(this.getNewVelocity());
            this.getEntity().getWorld().playSound(this.getEntity().getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.0f);
        }
        if (this.getTick() >= 30 && this.getTick() <= 35) {
            this.getEntity().getWorld().spawnParticle(Particle.DUST, this.getEntity().getEyeLocation(), 30, 0.3, 0.5, 0.3, new Particle.DustOptions(Color.fromRGB((int)235, (int)52, (int)186), 1.0f));
            this.damageNearby();
        }
        if (this.getTick() == 40 && (p = Faze.getTarget(this.getInitialLocation())) != null) {
            this.targetLoc = p.getLocation();
            this.directionVector = this.getVector().normalize();
        }
        if (this.getTick() > 40 && this.getTick() < 50) {
            this.getEntity().getWorld().spawnParticle(Particle.WITCH, this.getEntity().getLocation(), 10, 0.4, 0.0, 0.4, 0.0);
            if (this.directionVector != null) {
                this.getEntity().teleport(this.getEntity().getLocation().setDirection(this.directionVector));
            }
        }
        if (this.getTick() == 50 && this.targetLoc != null) {
            this.getEntity().setVelocity(this.getNewVelocity());
            this.getEntity().getWorld().playSound(this.getEntity().getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 2.0f, 1.0f);
        }
        if (this.getTick() >= 50 && this.getTick() <= 55) {
            this.getEntity().getWorld().spawnParticle(Particle.DUST, this.getEntity().getEyeLocation(), 30, 0.3, 0.5, 0.3, new Particle.DustOptions(Color.fromRGB((int)235, (int)52, (int)186), 1.0f));
            this.damageNearby();
        }
        if (this.getTick() == 60) {
            this.spellFinished();
        }
    }

    private Vector getVector() {
        return this.targetLoc.toVector().subtract(this.getEntity().getLocation().toVector());
    }

    private void damageNearby() {
        for (Entity e : this.getEntity().getNearbyEntities(1.0, 1.0, 1.0)) {
            if (!(e instanceof Player)) continue;
            Player p = (Player)e;
            if (!Bukkit.getOnlinePlayers().contains(p)) continue;
            p.damage(this.damage, (Entity)this.getEntity());
        }
    }

    private Vector getNewVelocity() {
        return this.getVector().normalize().multiply(this.getVector().length() / 2.2);
    }
}

