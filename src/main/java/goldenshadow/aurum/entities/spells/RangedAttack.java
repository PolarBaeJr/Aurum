/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.GameMode
 *  org.bukkit.Location
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.entity.AbstractArrow$PickupStatus
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.projectiles.ProjectileSource
 *  org.bukkit.util.Vector
 */
package goldenshadow.aurum.entities.spells;

import goldenshadow.aurum.entities.spells.Spell;
import goldenshadow.aurum.items.spells.PlayerSpellManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public class RangedAttack
extends Spell {
    private int tick;
    private double damage;
    private Vector vector;
    private final List<Arrow> arrows = new ArrayList<Arrow>();

    public RangedAttack(LivingEntity entity) {
        super(entity);
        Player player = RangedAttack.getTarget(entity.getEyeLocation());
        if (player != null) {
            this.vector = RangedAttack.calculateVector(entity.getEyeLocation().clone().add(0.0, 0.3, 0.0), player.getEyeLocation());
            this.damage = Objects.requireNonNull(entity.getAttribute(Attribute.ATTACK_DAMAGE)).getValue();
        }
    }

    @Override
    public void tick() {
        ++this.tick;
        if (this.tick == 1 || this.tick == 5 || this.tick == 10 || this.tick == 15 || this.tick == 19) {
            this.spawnProjectile();
        }
        if (this.tick == 20) {
            for (Entity e : this.getEntity().getNearbyEntities(20.0, 20.0, 20.0)) {
                Arrow a;
                if (!(e instanceof Arrow) || !(a = (Arrow)e).isInBlock()) continue;
                a.remove();
            }
            this.spellFinished();
        }
        Iterator<Arrow> it = this.arrows.iterator();
        while (it.hasNext()) {
            Arrow a = it.next();
            if (!a.isValid() || a.isInBlock()) {
                it.remove();
            }
            a.setVelocity(a.getVelocity().multiply(1.2));
        }
    }

    private void spawnProjectile() {
        Arrow arrow = this.getEntity().getWorld().spawnArrow(this.getEntity().getEyeLocation(), this.vector.normalize().multiply(3), 1.0f, 1.0f);
        arrow.setDamage(this.damage / 2.0);
        arrow.setCritical(false);
        arrow.setShooter((ProjectileSource)this.getEntity());
        arrow.setPickupStatus(AbstractArrow.PickupStatus.CREATIVE_ONLY);
        this.arrows.add(arrow);
    }

    protected static Player getTarget(Location location) {
        ArrayList<Player> possibleTargets = new ArrayList<Player>();
        List<Player> tauntingPlayers = PlayerSpellManager.getTauntingPlayers();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.getWorld().equals((Object)location.getWorld()) || !(p.getLocation().distance(location) < 15.0) || p.getGameMode() != GameMode.ADVENTURE && p.getGameMode() != GameMode.SURVIVAL) continue;
            if (tauntingPlayers.contains(p)) {
                return p;
            }
            possibleTargets.add(p);
        }
        if (possibleTargets.size() == 0) {
            return null;
        }
        return (Player)possibleTargets.get(ThreadLocalRandom.current().nextInt(0, possibleTargets.size()));
    }

    private static Vector calculateVector(Location source, Location target) {
        return target.toVector().subtract(source.toVector()).multiply(new Vector(1.0, 1.4, 1.0));
    }
}

