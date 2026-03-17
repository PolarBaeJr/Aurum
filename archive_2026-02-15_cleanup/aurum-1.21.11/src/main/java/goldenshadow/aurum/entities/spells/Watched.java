/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.GameMode
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.Sound
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.entity.EnderCrystal
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 */
package goldenshadow.aurum.entities.spells;

import goldenshadow.aurum.entities.spells.Spell;
import java.util.ArrayList;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Watched
extends Spell {
    ArrayList<EnderCrystal> crystals = new ArrayList<>();

    public Watched(LivingEntity entity) {
        super(entity);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTick() == 22) {
            this.getEntity().setGravity(false);
            this.getEntity().setInvulnerable(true);
            this.getEntity().teleport(this.getInitialLocation().add(0.0, 10.0, 0.0));
        }
        if (this.getTick() == 42 || this.getTick() == 62 || this.getTick() == 82) {
            ArrayList<Player> targetedPlayers = new ArrayList<Player>();
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getLocation().getWorld() != this.getInitialLocation().getWorld() || !(player.getLocation().distance(this.getInitialLocation()) < 20.0) || player.getGameMode() != GameMode.ADVENTURE && player.getGameMode() != GameMode.SURVIVAL || targetedPlayers.size() >= 4) continue;
                targetedPlayers.add(player);
            }
            targetedPlayers.forEach(p -> this.spawnCrystal(p.getLocation()));
        }
        if (this.getTick() == 102) {
            for (EnderCrystal crystal : this.crystals) {
                crystal.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, crystal.getLocation(), 0);
                crystal.getWorld().playSound(crystal.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getLocation().getWorld() != crystal.getLocation().getWorld() || !(player.getLocation().distance(crystal.getLocation()) < 3.0)) continue;
                    player.damage(Objects.requireNonNull(this.getEntity().getAttribute(Attribute.ATTACK_DAMAGE)).getValue() * 2.0 * this.getSpellDamageMultiplier(), (Entity)this.getEntity());
                }
                crystal.remove();
            }
        }
        if (this.getTick() == 112) {
            this.getEntity().setGravity(true);
        }
        if (this.getTick() == 132) {
            this.getEntity().setInvulnerable(false);
            this.spellFinished();
        }
    }

    private void spawnCrystal(Location location) {
        assert (location.getWorld() != null);
        EnderCrystal crystal = (EnderCrystal)location.getWorld().spawnEntity(location, EntityType.END_CRYSTAL);
        crystal.setBeamTarget(this.getEntity().getLocation());
        crystal.setShowingBottom(false);
        crystal.setInvulnerable(true);
        crystal.setGravity(false);
        this.crystals.add(crystal);
        location.getWorld().playSound(location, Sound.ITEM_TRIDENT_THUNDER, 1.0f, 1.0f);
    }
}

