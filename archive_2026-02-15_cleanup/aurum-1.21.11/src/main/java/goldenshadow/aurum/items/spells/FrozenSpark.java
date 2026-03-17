/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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
package goldenshadow.aurum.items.spells;

import goldenshadow.aurum.items.spells.PlayerSpell;
import goldenshadow.aurum.items.spells.PlayerSpellManager;
import java.util.Objects;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class FrozenSpark
extends PlayerSpell {
    private final double damage;
    private final BlockData block = Material.ICE.createBlockData();

    public FrozenSpark(Player player) {
        super(player);
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 1.0f);
        this.damage = Math.max(0.0, Objects.requireNonNull(player.getAttribute(Attribute.ATTACK_DAMAGE)).getValue() * 0.7 * this.getDamageMultiplier());
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTick() == 1) {
            Location loc = this.getPlayer().getEyeLocation();
            for (int i = 0; i < 5; ++i) {
                loc = loc.add(this.getPlayer().getEyeLocation().getDirection().multiply(1.2));
                assert (loc.getWorld() != null);
                loc.getWorld().spawnParticle(Particle.GLOW, loc, 0);
                loc.getWorld().spawnParticle(Particle.BLOCK, loc, 20, 0.2, 0.2, 0.2, this.block);
                if (!loc.getBlock().isEmpty() && !loc.getBlock().isPassable()) break;
                for (Entity e : loc.getWorld().getNearbyEntities(loc, 1.0, 1.0, 1.0)) {
                    if (!(e instanceof LivingEntity)) continue;
                    LivingEntity entity = (LivingEntity)e;
                    if (!e.getScoreboardTags().contains("aurum_mob") && !PlayerSpellManager.itemHelper.isAttackablePlayer(e) || e.getUniqueId().equals(this.getPlayer().getUniqueId())) continue;
                    if (e.getLocation().distance(this.getPlayer().getLocation()) < 1.0) {
                        PlayerSpellManager.applyDamage(entity, this.damage * 4.0, this.getPlayer());
                        continue;
                    }
                    if (e.getLocation().distance(this.getPlayer().getLocation()) < 2.0) {
                        PlayerSpellManager.applyDamage(entity, this.damage * 3.5, this.getPlayer());
                        continue;
                    }
                    if (e.getLocation().distance(this.getPlayer().getLocation()) < 3.0) {
                        PlayerSpellManager.applyDamage(entity, this.damage * 3.0, this.getPlayer());
                        continue;
                    }
                    if (e.getLocation().distance(this.getPlayer().getLocation()) < 4.0) {
                        PlayerSpellManager.applyDamage(entity, this.damage * 2.5, this.getPlayer());
                        continue;
                    }
                    if (e.getLocation().distance(this.getPlayer().getLocation()) < 5.0) {
                        PlayerSpellManager.applyDamage(entity, this.damage * 2.0, this.getPlayer());
                        continue;
                    }
                    if (e.getLocation().distance(this.getPlayer().getLocation()) < 6.0) {
                        PlayerSpellManager.applyDamage(entity, this.damage * 1.5, this.getPlayer());
                        continue;
                    }
                    PlayerSpellManager.applyDamage(entity, this.damage, this.getPlayer());
                }
            }
            this.spellFinished();
        }
    }
}

