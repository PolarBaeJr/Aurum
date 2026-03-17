/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.GameMode
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.Sound
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package goldenshadow.aurum.entities.spells;

import goldenshadow.aurum.entities.spells.Spell;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Blizzard
extends Spell {
    Location castLocation = this.getEntity().getEyeLocation();
    List<Location> clouds;

    public Blizzard(LivingEntity entity) {
        super(entity);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTick() == 21) {
            this.clouds = this.getLocations();
        }
        if (this.getTick() > 20 && this.getTick() < 180) {
            for (Location loc : this.clouds) {
                assert (loc.getWorld() != null);
                loc.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, loc, 30, 1.0, 0.1, 1.0, 0.0);
            }
        }
        if (this.getTick() > 40 && this.getTick() < 200) {
            for (Location loc : this.clouds) {
                assert (loc.getWorld() != null);
                loc.getWorld().spawnParticle(Particle.SNOWFLAKE, loc, 30, 1.2, 0.1, 1.2, 0.0);
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPotionEffect(PotionEffectType.BLINDNESS) && p.hasPotionEffect(PotionEffectType.SLOW)) {
                        p.setFreezeTicks(200);
                    }
                    if (p.getGameMode() != GameMode.ADVENTURE && p.getGameMode() != GameMode.SURVIVAL || p.getWorld() != loc.getWorld()) continue;
                    Location l = new Location(loc.getWorld(), loc.getX(), loc.getY() - 2.0, loc.getZ());
                    if (!(p.getLocation().distance(l) < 4.0) || p.hasPotionEffect(PotionEffectType.BLINDNESS) || p.hasPotionEffect(PotionEffectType.SLOW)) continue;
                    this.triggered(p);
                }
            }
        }
        if (this.getTick() > 201) {
            this.spellFinished();
        }
    }

    private void triggered(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 140, 2, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 140, 0, false, false));
        player.playSound((Entity)player, Sound.ITEM_ELYTRA_FLYING, 100.0f, 1.0f);
    }

    private List<Location> getLocations() {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getGameMode() != GameMode.ADVENTURE && p.getGameMode() != GameMode.SURVIVAL || p.getWorld() != this.castLocation.getWorld() || !(p.getLocation().distance(this.castLocation) < 20.0)) continue;
            locs.add(p.getLocation().add(0.0, 4.0, 0.0));
        }
        return locs;
    }
}

