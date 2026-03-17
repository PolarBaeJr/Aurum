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

public class Flame
extends Spell {
    private Location flameCenter;
    private final double damage;

    public Flame(LivingEntity entity) {
        super(entity);
        this.flameCenter = entity.getEyeLocation().add(this.getEntity().getLocation().getDirection());
        this.damage = Objects.requireNonNull(this.getEntity().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).getValue() * 2.5 * this.getSpellDamageMultiplier();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTick() == 21) {
            assert (this.flameCenter.getWorld() != null);
            this.flameCenter.getWorld().spawnParticle(Particle.FLAME, this.flameCenter, 20, 0.5, 0.5, 0.5, 0.0);
            this.flameCenter.getWorld().playSound(this.flameCenter, Sound.BLOCK_FIRE_EXTINGUISH, 1.0f, 1.0f);
            this.damageNearby(this.flameCenter, 1);
        }
        if (this.getTick() == 41) {
            this.flameCenter = this.getEntity().getEyeLocation().add(this.getEntity().getLocation().getDirection().multiply(1.5));
            assert (this.flameCenter.getWorld() != null);
            this.flameCenter.getWorld().spawnParticle(Particle.FLAME, this.flameCenter, 40, 0.75, 0.5, 0.75, 0.0);
            this.flameCenter.getWorld().playSound(this.flameCenter, Sound.BLOCK_FIRE_EXTINGUISH, 1.0f, 1.0f);
            this.damageNearby(this.flameCenter, 2);
        }
        if (this.getTick() == 61) {
            this.flameCenter = this.getEntity().getEyeLocation().add(this.getEntity().getLocation().getDirection().multiply(1.5));
            assert (this.flameCenter.getWorld() != null);
            this.flameCenter.getWorld().spawnParticle(Particle.FLAME, this.flameCenter, 60, 1.0, 0.5, 1.0, 0.0);
            this.flameCenter.getWorld().playSound(this.flameCenter, Sound.BLOCK_FIRE_EXTINGUISH, 1.0f, 1.0f);
            this.damageNearby(this.flameCenter, 3);
        }
        if (this.getTick() == 81) {
            this.flameCenter = this.getEntity().getEyeLocation().add(this.getEntity().getLocation().getDirection().multiply(1.5));
            assert (this.flameCenter.getWorld() != null);
            this.flameCenter.getWorld().spawnParticle(Particle.FLAME, this.flameCenter, 80, 1.25, 0.5, 1.25, 0.0);
            this.flameCenter.getWorld().playSound(this.flameCenter, Sound.BLOCK_FIRE_EXTINGUISH, 1.0f, 1.0f);
            this.damageNearby(this.flameCenter, 4);
            this.spellFinished();
        }
    }

    public void damageNearby(Location location, int range) {
        assert (location.getWorld() != null);
        for (Entity e : location.getWorld().getNearbyEntities(location, (double)range, (double)range, (double)range)) {
            if (!(e instanceof Player)) continue;
            Player p = (Player)e;
            if (!Bukkit.getOnlinePlayers().contains(p)) continue;
            p.damage(this.damage, (Entity)this.getEntity());
        }
    }
}

