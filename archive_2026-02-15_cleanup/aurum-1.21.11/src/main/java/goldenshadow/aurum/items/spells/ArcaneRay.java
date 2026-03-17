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
 *  org.bukkit.util.Vector
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
import org.bukkit.util.Vector;

public class ArcaneRay
extends PlayerSpell {
    Vector direction;
    Location rayLoc;
    double damage;

    public ArcaneRay(Player player) {
        super(player);
        this.direction = player.getEyeLocation().getDirection().normalize();
        this.rayLoc = player.getEyeLocation().add(this.direction);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WARDEN_SONIC_BOOM, 1.0f, 1.0f);
        this.damage = Math.max(0.0, Objects.requireNonNull(player.getAttribute(Attribute.ATTACK_DAMAGE)).getValue() * 2.5 * this.getDamageMultiplier());
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTick() < 11) {
            assert (this.rayLoc.getWorld() != null);
            this.rayLoc.getWorld().spawnParticle(Particle.SONIC_BOOM, this.rayLoc, 0);
            this.rayLoc = this.rayLoc.add(this.direction);
            assert (this.rayLoc.getWorld() != null);
            for (Entity e : this.rayLoc.getWorld().getNearbyEntities(this.rayLoc, 2.0, 2.0, 2.0)) {
                if (!(e instanceof LivingEntity) || !e.getScoreboardTags().contains("aurum_mob") && !PlayerSpellManager.itemHelper.isAttackablePlayer(e) || e.getUniqueId().equals(this.getPlayer().getUniqueId())) continue;
                PlayerSpellManager.applyDamage((LivingEntity)e, this.damage, this.getPlayer());
            }
        }
        if (this.getTick() == 11) {
            this.spellFinished();
        }
    }
}

