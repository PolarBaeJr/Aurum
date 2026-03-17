/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Particle
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
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Slash
extends Spell {
    private int rotation = 0;

    public Slash(LivingEntity entity) {
        super(entity);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTick() > 21) {
            this.getEntity().setRotation((float)this.rotation, 0.0f);
            Location loc = this.getEntity().getEyeLocation();
            for (int i = 0; i < 15; ++i) {
                loc = loc.add(this.getEntity().getEyeLocation().getDirection().multiply(0.3));
                assert (loc.getWorld() != null);
                loc.getWorld().spawnParticle(Particle.FLAME, loc, 0);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getLocation().getWorld() != loc.getWorld() || !(player.getEyeLocation().distance(loc) < 1.0)) continue;
                    player.damage(Objects.requireNonNull(this.getEntity().getAttribute(Attribute.ATTACK_DAMAGE)).getValue() * 2.0 * this.getSpellDamageMultiplier(), (Entity)this.getEntity());
                }
            }
        }
        if (this.getTick() > 41) {
            this.rotation += 10;
        }
        if (this.rotation > 360) {
            this.spellFinished();
        }
    }
}

