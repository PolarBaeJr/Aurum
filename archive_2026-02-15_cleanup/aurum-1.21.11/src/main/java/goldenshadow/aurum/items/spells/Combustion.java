/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Particle
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 */
package goldenshadow.aurum.items.spells;

import goldenshadow.aurum.items.spells.PlayerSpell;
import goldenshadow.aurum.items.spells.PlayerSpellManager;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Combustion
extends PlayerSpell {
    private final LivingEntity entity;
    private final double damage;

    public Combustion(Player player, LivingEntity entity, double damage) {
        super(player);
        this.damage = damage * 0.2;
        this.entity = entity;
        if (entity.getScoreboardTags().contains("aurum_burning")) {
            this.spellFinished();
            return;
        }
        entity.addScoreboardTag("aurum_burning");
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTick() == 1 || this.getTick() == 20 || this.getTick() == 40) {
            this.entity.getWorld().spawnParticle(Particle.FLAME, this.entity.getLocation().add(0.0, 1.5, 0.0), 20, 0.3, 0.3, 0.3, 0.0);
            PlayerSpellManager.applyDamage(this.entity, this.damage, this.getPlayer());
        }
        if (this.getTick() == 40) {
            this.entity.getScoreboardTags().remove("aurum_burning");
            this.spellFinished();
        }
    }
}

