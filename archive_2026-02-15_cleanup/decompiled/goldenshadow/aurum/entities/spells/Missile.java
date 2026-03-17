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

public class Missile
extends Spell {
    Location missileLoc;

    public Missile(LivingEntity entity) {
        super(entity);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTick() == 22) {
            Player p = Missile.getTarget(this.getInitialLocation());
            Location target = p != null ? p.getLocation() : this.getInitialLocation();
            this.drawCircle(target);
            this.missileLoc = target.add(0.0, 20.0, 4.0);
        }
        if (this.getTick() > 32 && this.getTick() < 44) {
            assert (this.missileLoc.getWorld() != null);
            this.missileLoc.getWorld().spawnParticle(Particle.FLAME, this.missileLoc, 0);
            this.missileLoc = this.missileLoc.add(0.0, -2.0, -0.4);
        }
        if (this.getTick() == 45) {
            assert (this.missileLoc.getWorld() != null);
            this.missileLoc.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, this.missileLoc, 0);
            this.missileLoc.getWorld().playSound(this.missileLoc, Sound.ENTITY_GENERIC_EXPLODE, 5.0f, 1.0f);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getLocation().getWorld() != this.missileLoc.getWorld() || !(player.getLocation().distance(this.missileLoc) < 3.0)) continue;
                player.damage(Objects.requireNonNull(this.getEntity().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).getValue() * 2.0 * this.getSpellDamageMultiplier(), (Entity)this.getEntity());
            }
            this.spellFinished();
        }
    }

    private void drawCircle(Location location) {
        for (double angle = 0.0; angle < Math.PI * 2; angle += 0.08726646259971647) {
            double x = Math.cos(angle);
            double z = Math.sin(angle);
            location.add(x, 0.0, z);
            assert (location.getWorld() != null);
            location.getWorld().spawnParticle(Particle.FLAME, location, 0);
            location.subtract(x, 0.0, z);
        }
    }
}

