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

public class Explosion
extends Spell {
    private final Location location = this.getInitialLocation();

    public Explosion(LivingEntity entity) {
        super(entity);
    }

    @Override
    public void tick() {
        assert (this.location.getWorld() != null);
        super.tick();
        if (this.getTick() == 21 || this.getTick() == 31 || this.getTick() == 41) {
            this.location.getWorld().playSound(this.getEntity().getLocation(), Sound.BLOCK_DISPENSER_FAIL, 1.0f, 1.0f);
            this.location.getWorld().spawnParticle(Particle.CRIT, this.getEntity().getEyeLocation(), 20, 0.2, 2.0, 0.2);
        }
        if (this.getTick() == 51) {
            this.location.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, this.getEntity().getLocation(), 0);
            this.location.getWorld().playSound(this.getEntity().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getLocation().getWorld() != this.getEntity().getLocation().getWorld() || !(player.getLocation().distance(this.getEntity().getLocation()) < 6.0)) continue;
                player.damage(Objects.requireNonNull(this.getEntity().getAttribute(Attribute.ATTACK_DAMAGE)).getValue() * 3.0 * this.getSpellDamageMultiplier(), (Entity)this.getEntity());
                player.setVelocity(player.getLocation().getDirection().multiply(-1.1).setY(0.5));
            }
            this.spellFinished();
        }
    }
}

