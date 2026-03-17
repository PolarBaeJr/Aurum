package goldenshadow.aurum.items.spells;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

public class Taunt extends PlayerSpell {
    public Taunt(Player player) {
        super(player);
        player.getWorld().playSound(this.getInitialLocation(), Sound.ENTITY_RAVAGER_ROAR, 1.0f, 1.0f);
        player.getWorld().spawnParticle(Particle.LARGE_SMOKE, player.getEyeLocation(), 10, 0.2, 0.2, 0.2, 0.4);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTick() == 1 || this.getTick() == 10 || this.getTick() == 15 || this.getTick() == 20) {
            for (Entity e : this.getPlayer().getNearbyEntities(10.0, 10.0, 10.0)) {
                if (!e.getScoreboardTags().contains("aurum_mob") || !(e instanceof LivingEntity)) {
                    continue;
                }
                tauntMob((LivingEntity)e, this.getPlayer());
            }
        }
        if (this.getTick() > 20) {
            this.spellFinished();
        }
    }

    private static void tauntMob(LivingEntity entity, Player player) {
        if (entity instanceof Mob mob) {
            mob.setTarget(player);
        }
    }
}
