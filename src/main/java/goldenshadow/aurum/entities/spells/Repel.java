/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.GameMode
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 */
package goldenshadow.aurum.entities.spells;

import goldenshadow.aurum.entities.spells.Spell;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Repel
extends Spell {
    public Repel(LivingEntity entity) {
        super(entity);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTick() == 22) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.getLocation().getWorld() != this.getInitialLocation().getWorld() || !(player.getLocation().distance(this.getInitialLocation()) < 10.0) || player.getGameMode() != GameMode.ADVENTURE && player.getGameMode() != GameMode.SURVIVAL) continue;
                player.setVelocity(player.getLocation().getDirection().multiply(-2).setY(0.5));
            }
            this.spellFinished();
        }
    }
}

