/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
 */
package goldenshadow.aurum.entities.spells;

import goldenshadow.aurum.entities.spells.Spell;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class Fangs
extends Spell {
    private Location fangLoc = this.getEntity().getLocation().add(this.getEntity().getLocation().getDirection());

    public Fangs(LivingEntity entity) {
        super(entity);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTick() == 22) {
            for (int i = 0; i < 10; ++i) {
                this.fangLoc = this.fangLoc.add(this.fangLoc.getDirection());
                this.spawnFang(this.fangLoc);
            }
            this.spellFinished();
        }
    }

    private void spawnFang(Location location) {
        assert (location.getWorld() != null);
        location.getWorld().spawnEntity(location, EntityType.EVOKER_FANGS);
    }
}

