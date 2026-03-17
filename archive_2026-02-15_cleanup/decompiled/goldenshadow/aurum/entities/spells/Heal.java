/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Particle
 *  org.bukkit.Sound
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.entity.LivingEntity
 */
package goldenshadow.aurum.entities.spells;

import goldenshadow.aurum.entities.spells.Spell;
import java.util.Objects;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

public class Heal
extends Spell {
    public Heal(LivingEntity entity) {
        super(entity);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTick() == 21) {
            double maxHealth = Objects.requireNonNull(this.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
            this.getEntity().setHealth(Math.min(this.getEntity().getHealth() + maxHealth * 0.1, maxHealth));
            this.getEntity().getWorld().spawnParticle(Particle.VILLAGER_HAPPY, this.getEntity().getEyeLocation(), 20, 0.3, 0.5, 0.3, 0.0);
            this.getEntity().getWorld().playSound(this.getInitialLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 1.0f, 1.0f);
            this.spellFinished();
        }
    }
}

