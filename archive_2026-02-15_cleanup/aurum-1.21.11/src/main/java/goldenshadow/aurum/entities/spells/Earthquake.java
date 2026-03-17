/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.GameMode
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.Sound
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.block.data.BlockData
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 */
package goldenshadow.aurum.entities.spells;

import goldenshadow.aurum.entities.spells.Spell;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Earthquake
extends Spell {
    List<Location> targetedLocations = new ArrayList<Location>();
    BlockData dirt = Material.DIRT.createBlockData();

    public Earthquake(LivingEntity entity) {
        super(entity);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTick() == 21) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getLocation().getWorld() != this.getEntity().getLocation().getWorld() || !(player.getLocation().distance(this.getEntity().getLocation()) < 21.0) || player.getGameMode() != GameMode.ADVENTURE && player.getGameMode() != GameMode.SURVIVAL) continue;
                this.targetedLocations.add(player.getLocation());
            }
        }
        if (this.getTick() > 21) {
            for (Location location : this.targetedLocations) {
                assert (location.getWorld() != null);
                location.getWorld().spawnParticle(Particle.BLOCK, location, 20, 1.0, 0.0, 1.0, this.dirt);
                if (this.getTick() == 22 || this.getTick() == 28 || this.getTick() == 32 || this.getTick() == 37 || this.getTick() == 42 || this.getTick() == 47) {
                    location.getWorld().playSound(location, Sound.BLOCK_ROOTED_DIRT_BREAK, 1.0f, 1.0f);
                }
                if (this.getTick() != 50) continue;
                location.getWorld().spawnParticle(Particle.EXPLOSION, location, 0);
                location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
                for (Entity e : location.getWorld().getNearbyEntities(location, 2.0, 2.0, 2.0)) {
                    if (!(e instanceof Player)) continue;
                    ((Player)e).damage(Objects.requireNonNull(this.getEntity().getAttribute(Attribute.ATTACK_DAMAGE)).getValue() * 2.0 * this.getSpellDamageMultiplier(), (Entity)this.getEntity());
                }
                this.spellFinished();
            }
        }
    }
}

