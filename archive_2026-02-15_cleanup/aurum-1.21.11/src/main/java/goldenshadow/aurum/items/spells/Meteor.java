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

public class Meteor
extends PlayerSpell {
    Location missileLoc;
    double damage;

    public Meteor(Player player) {
        super(player);
        this.damage = Math.max(0.0, Objects.requireNonNull(player.getAttribute(Attribute.ATTACK_DAMAGE)).getValue() * 2.0 * this.getDamageMultiplier());
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTick() == 1) {
            Location target = this.rayCastFromPlayer();
            this.missileLoc = target.add(0.0, 20.0, 4.0);
        }
        if (this.getTick() > 10 && this.getTick() < 20) {
            assert (this.missileLoc.getWorld() != null);
            this.missileLoc.getWorld().spawnParticle(Particle.FLAME, this.missileLoc, 0);
            this.missileLoc = this.missileLoc.add(0.0, -2.0, -0.4);
        }
        if (this.getTick() == 25) {
            assert (this.missileLoc.getWorld() != null);
            this.missileLoc.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, this.missileLoc, 0);
            this.missileLoc.getWorld().playSound(this.missileLoc, Sound.ENTITY_GENERIC_EXPLODE, 5.0f, 1.0f);
            for (Entity e : this.missileLoc.getWorld().getNearbyEntities(this.missileLoc, 4.0, 4.0, 4.0)) {
                LivingEntity entity;
                if (!(e instanceof LivingEntity) || !(entity = (LivingEntity)e).getScoreboardTags().contains("aurum_mob") && !PlayerSpellManager.itemHelper.isAttackablePlayer(e) || entity.getUniqueId().equals(this.getPlayer().getUniqueId())) continue;
                PlayerSpellManager.applyDamage(entity, this.damage, this.getPlayer());
            }
            this.spellFinished();
        }
    }
}

