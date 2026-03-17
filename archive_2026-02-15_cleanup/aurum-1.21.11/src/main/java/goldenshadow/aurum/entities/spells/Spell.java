/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.bukkit.Bukkit
 *  org.bukkit.GameMode
 *  org.bukkit.Location
 *  org.bukkit.NamespacedKey
 *  org.bukkit.Particle
 *  org.bukkit.Sound
 *  org.bukkit.SoundCategory
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 */
package goldenshadow.aurum.entities.spells;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.entities.SpellManager;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nullable;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public abstract class Spell {
    private int tick = 0;
    private final LivingEntity entity;
    private final Location initialLocation;
    private final UUID uuid;
    private final double spellDamageMultiplier;
    private Location particleCastLocation;

    public Spell(LivingEntity entity) {
        Double d;
        this.entity = entity;
        this.initialLocation = entity.getLocation();
        this.uuid = UUID.randomUUID();
        this.particleCastLocation = this.initialLocation;
        this.spellDamageMultiplier = entity.getPersistentDataContainer().has(new NamespacedKey((Plugin)Aurum.getPlugin(), "spellMultiplier"), PersistentDataType.DOUBLE) ? ((d = (Double)entity.getPersistentDataContainer().get(new NamespacedKey((Plugin)Aurum.getPlugin(), "spellMultiplier"), PersistentDataType.DOUBLE)) != null ? d : 1.0) : 1.0;
    }

    public void tick() {
        ++this.tick;
        if (this.tick < 21) {
            this.castPrepare();
        }
    }

    public UUID getUuid() {
        return this.uuid;
    }

    private void castPrepare() {
        assert (this.initialLocation.getWorld() != null);
        if (this.tick == 1) {
            this.initialLocation.getWorld().playSound(this.initialLocation, Sound.BLOCK_PORTAL_TRAVEL, SoundCategory.HOSTILE, 0.1f, 2.0f);
        }
        if (this.tick % 2 == 0) {
            for (double angle = 0.0; angle < Math.PI * 2; angle += 0.08726646259971647) {
                double x = Math.cos(angle);
                double z = Math.sin(angle);
                this.particleCastLocation.add(x, 0.0, z);
                assert (this.particleCastLocation.getWorld() != null);
                this.particleCastLocation.getWorld().spawnParticle(Particle.ENCHANT, this.particleCastLocation, 0);
                this.particleCastLocation.subtract(x, 0.0, z);
            }
            this.particleCastLocation = this.particleCastLocation.add(0.0, 0.2, 0.0);
        }
    }

    protected void spellFinished() {
        SpellManager.removeSpell(this.uuid);
    }

    protected int getTick() {
        return this.tick;
    }

    protected LivingEntity getEntity() {
        return this.entity;
    }

    public UUID getCasterUUID() {
        return this.entity.getUniqueId();
    }

    protected Location getInitialLocation() {
        return this.initialLocation;
    }

    protected double getSpellDamageMultiplier() {
        return this.spellDamageMultiplier;
    }

    @Nullable
    protected static Player getTarget(Location location) {
        ArrayList<Player> possibleTargets = new ArrayList<Player>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.getWorld().equals(location.getWorld()) || !(p.getLocation().distance(location) <= 20.0) || p.getGameMode() != GameMode.ADVENTURE && p.getGameMode() != GameMode.SURVIVAL) continue;
            possibleTargets.add(p);
        }
        if (possibleTargets.size() > 0) {
            return (Player)possibleTargets.get(ThreadLocalRandom.current().nextInt(0, possibleTargets.size()));
        }
        return null;
    }
}

