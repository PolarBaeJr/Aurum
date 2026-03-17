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

public class GroundSlam
extends PlayerSpell {
    private final double damage;

    public GroundSlam(Player player) {
        super(player);
        this.damage = Math.max(0.0, Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).getValue() * 0.5 * this.getDamageMultiplier());
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTick() == 1) {
            this.getPlayer().setVelocity(new Vector(0, 1, 0));
            Location loc = this.getInitialLocation();
            assert (loc.getWorld() != null);
            loc.getWorld().playSound(this.getInitialLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1.0f, 1.0f);
        }
        if (this.getTick() > 20) {
            this.getPlayer().setVelocity(new Vector(0, -1, 0));
            if (!this.getPlayer().getWorld().getBlockAt(this.getPlayer().getLocation().add(0.0, -0.1, 0.0)).isPassable()) {
                this.getPlayer().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, this.getPlayer().getLocation(), 0);
                this.getPlayer().getWorld().playSound(this.getPlayer().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
                for (Entity e : this.getPlayer().getWorld().getNearbyEntities(this.getPlayer().getLocation(), 3.0, 3.0, 3.0)) {
                    LivingEntity lv;
                    if (!(e instanceof LivingEntity) || !(lv = (LivingEntity)e).getScoreboardTags().contains("aurum_mob") && !PlayerSpellManager.itemHelper.isAttackablePlayer(e) || e.getUniqueId().equals(this.getPlayer().getUniqueId())) continue;
                    PlayerSpellManager.applyDamage(lv, this.damage, this.getPlayer());
                    if (lv.getScoreboardTags().contains("aurum_training_dummy")) continue;
                    lv.setVelocity(new Vector(0.0, 0.5, 0.0));
                }
                this.spellFinished();
            }
        }
    }
}

