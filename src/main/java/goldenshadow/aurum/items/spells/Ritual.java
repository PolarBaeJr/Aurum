/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.Sound
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 */
package goldenshadow.aurum.items.spells;

import goldenshadow.aurum.items.RuneAbilityHandler;
import goldenshadow.aurum.items.spells.PlayerSpell;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Ritual
extends PlayerSpell {
    private int counter = 20;
    private int drawCounter = 5;

    public Ritual(Player player) {
        super(player);
        Location loc = this.getInitialLocation();
        assert (loc.getWorld() != null);
        loc.getWorld().playSound(this.getInitialLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 1.0f, 1.0f);
    }

    @Override
    public void tick() {
        super.tick();
        ++this.counter;
        ++this.drawCounter;
        if (this.drawCounter > 5) {
            this.drawCircle(this.getPlayer().getLocation().add(0.0, 0.2, 0.0));
            this.drawCounter = 0;
        }
        if (this.counter > 20) {
            this.counter = 0;
            for (Player p : Bukkit.getOnlinePlayers()) {
                double newHealth;
                if (this.getPlayer().getWorld() != p.getWorld() || !(this.getPlayer().getLocation().distance(p.getLocation()) < 6.0) || !RuneAbilityHandler.isBloodrushNotActive(p) || (newHealth = Math.min(p.getHealth() + 2.0, Objects.requireNonNull(p.getAttribute(Attribute.MAX_HEALTH)).getValue())) == p.getHealth()) continue;
                p.playSound((Entity)p, Sound.ENTITY_WITCH_DRINK, 1.0f, 1.0f);
                p.setHealth(newHealth);
            }
        }
        if (this.getTick() == 400) {
            this.spellFinished();
        }
    }

    private void drawCircle(Location location) {
        for (double angle = 0.0; angle < Math.PI * 2; angle += 0.08726646259971647) {
            double x = Math.cos(angle) * 5.0;
            double z = Math.sin(angle) * 5.0;
            location.add(x, 0.0, z);
            assert (location.getWorld() != null);
            location.getWorld().spawnParticle(Particle.DUST, location, 0,new Particle.DustOptions(Color.fromRGB((int)215, (int)79, (int)219), 2.0f));
            location.subtract(x, 0.0, z);
        }
    }
}

