/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.adventure.bossbar.BossBar
 *  net.kyori.adventure.text.Component
 *  net.kyori.adventure.text.TextComponent
 *  net.kyori.adventure.text.format.NamedTextColor
 *  net.kyori.adventure.text.format.TextColor
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 */
package goldenshadow.aurum.entities;

import goldenshadow.aurum.entities.EntityHandler;
import java.util.Objects;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.attribute.Attribute;
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
            this.player.hideBossBar(this.bossBar);
            EntityHandler.removeBar(this.player);
        }
        --this.ttl;
    }

    public void update(LivingEntity entity, double damage) {
        Component entityName;
        double displayHealth = Math.max(0.0, entity.getHealth() - damage);
        displayHealth = (int)displayHealth;
        TextComponent healthSuffix = Component.text((String)(" " + (int)displayHealth + " \u2764"), (TextColor)NamedTextColor.DARK_RED);
        Component title = entity.getScoreboardTags().contains("aurum_training_dummy") ? Component.text((String)"Training Dummy", (TextColor)NamedTextColor.YELLOW).append((Component)healthSuffix) : ((entityName = entity.customName()) != null ? entityName : Component.text((String)"Unknown")).append((Component)healthSuffix);
        this.bossBar.name(title);
        this.bossBar.progress((float)Math.max(0.0, Math.min(1.0, (entity.getHealth() - damage) / Objects.requireNonNull(entity.getAttribute(Attribute.MAX_HEALTH)).getValue())));
        this.player.showBossBar(this.bossBar);
        this.ttl = 3;
    }

    public void remove() {
        this.player.hideBossBar(this.bossBar);
    }
}

