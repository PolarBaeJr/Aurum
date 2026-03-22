/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.Sound
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.ArmorStand$LockType
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.EquipmentSlot
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.util.Vector
 */
package goldenshadow.aurum.entities.spells;

import goldenshadow.aurum.entities.spells.Spell;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class VenomSpit
extends Spell {
    private final List<ArmorStand> spitList = new ArrayList<ArmorStand>();
    private final List<Location> venomPoolLost = new ArrayList<Location>();

    public VenomSpit(LivingEntity entity) {
        super(entity);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTick() == 21 || this.getTick() == 31 || this.getTick() == 41 || this.getTick() == 51 || this.getTick() == 61) {
            this.spit();
        }
        if (this.getTick() > 21) {
            Iterator<ArmorStand> iterator = this.spitList.iterator();
            while (iterator.hasNext()) {
                ArmorStand spit = iterator.next();
                spit.getWorld().spawnParticle(Particle.DUST, spit.getLocation(), 0,new Particle.DustOptions(Color.LIME, 2.0f));
                if (spit.getVelocity().getX() != 0.0 || !(spit.getVelocity().getY() < -0.07) || !(spit.getVelocity().getY() > -0.08) || spit.getVelocity().getZ() != 0.0) continue;
                this.venomPoolLost.add(spit.getLocation());
                iterator.remove();
                spit.remove();
            }
            for (Location location : this.venomPoolLost) {
                assert (location.getWorld() != null);
                location.getWorld().spawnParticle(Particle.SNEEZE, location, 10, 0.5, 0.0, 0.5, 0.0);
                for (Entity e : location.getWorld().getNearbyEntities(location, 2.0, 0.2, 2.0)) {
                    if (!(e instanceof Player)) continue;
                    Player p = (Player)e;
                    p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 2, false, true));
                }
            }
        }
        if (this.getTick() == 200) {
            for (ArmorStand s : this.spitList) {
                s.remove();
            }
            Location loc = this.getInitialLocation();
            assert (loc.getWorld() != null);
            for (Entity e : loc.getWorld().getNearbyEntities(this.getInitialLocation(), 10.0, 10.0, 10.0)) {
                if (!(e instanceof ArmorStand) || !e.getScoreboardTags().contains("aurum_spell_spit")) continue;
                e.remove();
            }
            this.spellFinished();
        }
    }

    private void spit() {
        Location loc = this.getInitialLocation();
        assert (loc.getWorld() != null);
        ArmorStand spit = (ArmorStand)loc.getWorld().spawnEntity(this.getEntity().getEyeLocation(), EntityType.ARMOR_STAND);
        spit.setInvisible(true);
        spit.setInvulnerable(true);
        spit.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.ADDING_OR_CHANGING);
        spit.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.ADDING_OR_CHANGING);
        spit.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.ADDING_OR_CHANGING);
        spit.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.ADDING_OR_CHANGING);
        spit.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.ADDING_OR_CHANGING);
        spit.addEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.ADDING_OR_CHANGING);
        spit.addScoreboardTag("aurum_spell_spit");
        spit.setVelocity(new Vector(ThreadLocalRandom.current().nextDouble(-1.0, 1.0), 1.0, ThreadLocalRandom.current().nextDouble(-1.0, 1.0)));
        loc.getWorld().playSound(this.getEntity().getEyeLocation(), Sound.ENTITY_LLAMA_SPIT, 2.0f, 1.0f);
        this.spitList.add(spit);
    }
}

