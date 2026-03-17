/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.Sound
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 */
package goldenshadow.aurum.items.spells;

import goldenshadow.aurum.items.spells.PlayerSpell;
import goldenshadow.aurum.items.spells.PlayerSpellManager;
import java.util.Objects;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Pirouette
extends PlayerSpell {
    Location location;
    int inc = 0;
    int p = 1;
    double damage;

    public Pirouette(Player player) {
        super(player);
        this.location = player.getEyeLocation().add(player.getEyeLocation().getDirection().normalize()).add(0.0, -0.5, 0.0);
        this.damage = Math.max(0.0, Objects.requireNonNull(player.getAttribute(Attribute.ATTACK_DAMAGE)).getValue() * 1.2 * this.getDamageMultiplier());
    }

    @Override
    public void tick() {
        super.tick();
        ++this.inc;
        assert (this.location.getWorld() != null);
        if (this.inc == 2) {
            double increment = 0.7853981633974483;
            double theta = increment * (double)this.p;
            double x = this.location.getX() + 1.5 * Math.cos(theta);
            double z = this.location.getZ() + 1.5 * Math.sin(theta);
            Location point = new Location(this.location.getWorld(), x, this.location.getY(), z);
            this.location.getWorld().spawnParticle(Particle.SWEEP_ATTACK, point, 3, 0.1, 0.1, 0.1, 0.0);
            this.inc = 0;
            ++this.p;
            assert (point.getWorld() != null);
            point.getWorld().playSound(point, Sound.ENTITY_SHEEP_SHEAR, 1.0f, 1.0f);
            for (Entity e : point.getWorld().getNearbyEntities(point, 1.5, 2.0, 1.5)) {
                if (!(e instanceof LivingEntity) || !e.getScoreboardTags().contains("aurum_mob") && !PlayerSpellManager.itemHelper.isAttackablePlayer(e) || e.getUniqueId().equals(this.getPlayer().getUniqueId())) continue;
                PlayerSpellManager.applyDamage((LivingEntity)e, this.damage, this.getPlayer());
            }
        }
        if (this.getTick() == 20) {
            this.spellFinished();
        }
    }
}

