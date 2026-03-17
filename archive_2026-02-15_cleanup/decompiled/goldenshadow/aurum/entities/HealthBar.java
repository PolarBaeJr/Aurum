/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.boss.BossBar
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 */
package goldenshadow.aurum.entities;

import goldenshadow.aurum.entities.EntityHandler;
import java.util.Objects;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class HealthBar {
    private int ttl = 3;
    private final BossBar bossBar;
    private final Player player;

    public HealthBar(BossBar bossBar, LivingEntity entity, Player player, double initialDamage) {
        this.bossBar = bossBar;
        this.player = player;
        this.update(entity, initialDamage);
    }

    public void tick() {
        if (this.ttl <= 0) {
            this.bossBar.removeAll();
            EntityHandler.removeBar(this.player);
        }
        --this.ttl;
    }

    public void update(LivingEntity entity, double damage) {
        double displayHealth = Math.max(0.0, entity.getHealth() - damage);
        displayHealth = (int)displayHealth;
        if (entity.getScoreboardTags().contains("aurum_training_dummy")) {
            this.bossBar.setTitle(ChatColor.YELLOW + "Training Dummy" + ChatColor.DARK_RED + " " + displayHealth + " \u2764");
        } else {
            this.bossBar.setTitle(entity.getCustomName() + ChatColor.DARK_RED + " " + displayHealth + " \u2764");
        }
        this.bossBar.setProgress(Math.max(0.0, (entity.getHealth() - damage) / Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue()));
        this.bossBar.addPlayer(this.player);
        this.ttl = 3;
    }

    public void remove() {
        this.bossBar.removeAll();
    }
}

