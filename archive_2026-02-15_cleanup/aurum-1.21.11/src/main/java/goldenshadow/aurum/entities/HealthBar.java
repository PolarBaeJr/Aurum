/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.boss.BossBar
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 */
package goldenshadow.aurum.entities;

import goldenshadow.aurum.entities.EntityHandler;
import java.util.Objects;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
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
        Component healthSuffix = Component.text(" " + (int)displayHealth + " \u2764", NamedTextColor.DARK_RED);
        Component title;
        if (entity.getScoreboardTags().contains("aurum_training_dummy")) {
            title = Component.text("Training Dummy", NamedTextColor.YELLOW).append(healthSuffix);
        } else {
            Component entityName = entity.customName();
            title = (entityName != null ? entityName : Component.text("Unknown")).append(healthSuffix);
        }
        this.bossBar.setTitle(LegacyComponentSerializer.legacySection().serialize(title));
        this.bossBar.setProgress(Math.max(0.0, (entity.getHealth() - damage) / Objects.requireNonNull(entity.getAttribute(Attribute.MAX_HEALTH)).getValue()));
        this.bossBar.addPlayer(this.player);
        this.ttl = 3;
    }

    public void remove() {
        this.bossBar.removeAll();
    }
}

