/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Sound
 *  org.bukkit.entity.LivingEntity
 */
package goldenshadow.aurum.entities.spells;

import goldenshadow.aurum.entities.spells.Spell;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;

public class Charge
extends Spell {
    public Charge(LivingEntity entity) {
        super(entity);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTick() == 22) {
            Location loc = this.getInitialLocation();
            assert (loc.getWorld() != null);
            loc.getWorld().playSound(this.getInitialLocation(), Sound.ITEM_TRIDENT_RIPTIDE_3, 1.0f, 1.0f);
            this.getEntity().setVelocity(this.getEntity().getLocation().getDirection().multiply(2).setY(1));
            this.spellFinished();
        }
    }
}

