/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.adventure.bossbar.BossBar
 *  net.kyori.adventure.bossbar.BossBar$Color
 *  net.kyori.adventure.bossbar.BossBar$Overlay
 *  net.kyori.adventure.text.Component
 *  net.kyori.adventure.text.format.NamedTextColor
 *  net.kyori.adventure.text.format.TextColor
 *  net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
 *  net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.Particle
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.entity.Allay
 *  org.bukkit.entity.AreaEffectCloud
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Slime
 *  org.bukkit.entity.Villager
 *  org.bukkit.entity.WanderingTrader
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityDeathEvent
 *  org.bukkit.event.player.PlayerInteractEntityEvent
 *  org.bukkit.event.world.ChunkUnloadEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.persistence.PersistentDataContainer
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffectType
 */
package goldenshadow.aurum.entities;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.entities.CustomEntity;
import goldenshadow.aurum.entities.HealthBar;
import goldenshadow.aurum.items.ItemHelper;
import goldenshadow.aurum.items.RuneAbilityHandler;
import goldenshadow.aurum.items.Treasure;
import goldenshadow.aurum.items.flags.AttributeID;
import goldenshadow.aurum.other.ExperienceManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Allay;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Villager;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

public class EntityHandler {
    private static final Map<UUID, HealthBar> bossBars = new HashMap<UUID, HealthBar>();
    private static final ArrayList<UUID> toRemove = new ArrayList();
    private static final ItemHelper itemHelper = new ItemHelper();

    public static void interact(PlayerInteractEntityEvent event) {
        if ((event.getRightClicked() instanceof Villager || event.getRightClicked() instanceof WanderingTrader) && event.getRightClicked().getScoreboardTags().contains("aurum_mob")) {
            event.setCancelled(true);
        }
        if (event.getRightClicked() instanceof Allay && event.getRightClicked().getScoreboardTags().contains("aurum_mob")) {
            event.setCancelled(true);
        }
    }

    public static void unload(ChunkUnloadEvent event) {
        Arrays.stream(event.getChunk().getEntities()).filter(x -> x.getScoreboardTags().contains("aurum_door_display")).forEach(x -> {
            String name;
            Component customName = x.customName();
            if (customName != null && !(name = LegacyComponentSerializer.legacySection().serialize(customName)).matches("\u00a77Get \u00a7e\\[\\d+/\\d+ Tokens")) {
                name = name.replaceAll("(?<=\\[)\\d+", "0");
                x.customName((Component)LegacyComponentSerializer.legacySection().deserialize(name));
            }
        });
    }

    public static void damage(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            Player p = (Player)entity;
            if (event.getDamager().getType() == EntityType.HUSK) {
                ((Player)event.getEntity()).removePotionEffect(PotionEffectType.HUNGER);
            }
            if (event.getDamager().getType() == EntityType.SHULKER_BULLET) {
                ((Player)event.getEntity()).damage(event.getDamage(), event.getDamager());
                event.setCancelled(true);
            }
            if (PlainTextComponentSerializer.plainText().serialize(p.getOpenInventory().title()).contains("Treasure Hoard")) {
                event.setCancelled(true);
            }
        }
        if ((entity = event.getDamager()) instanceof Player) {
            Player player = (Player)entity;
            Entity entity2 = event.getEntity();
            if (entity2 instanceof LivingEntity) {
                LivingEntity entity3 = (LivingEntity)entity2;
                if (itemHelper.isHighEnoughLevel(player, player.getInventory().getItemInMainHand()) && entity3.getScoreboardTags().contains("aurum_mob")) {
                    if (bossBars.containsKey(player.getUniqueId())) {
                        bossBars.get(player.getUniqueId()).update(entity3, event.getFinalDamage());
                    } else {
                        bossBars.put(player.getUniqueId(), new HealthBar(BossBar.bossBar((Component)Component.text((String)" "), (float)1.0f, (BossBar.Color)BossBar.Color.RED, (BossBar.Overlay)BossBar.Overlay.PROGRESS), entity3, player, event.getFinalDamage()));
                    }
                }
                if (entity3 instanceof Slime && (entity3.getScoreboardTags().contains("aurum_door") || entity3.getScoreboardTags().contains("aurum_conditional_interaction") || entity3.getScoreboardTags().contains("aurum_event_interaction") || entity3.getScoreboardTags().contains("aurum_pickup_interaction") || entity3.getScoreboardTags().contains("aurum_boss_chest") || entity3.getScoreboardTags().contains("aurum_chest") || entity3.getScoreboardTags().contains("aurum_runesmith") || entity3.getScoreboardTags().contains("aurum_rune_stone"))) {
                    event.setCancelled(true);
                }
            }
        }
    }

    public static void barLoop() {
        for (UUID uuid : toRemove) {
            if (!bossBars.containsKey(uuid) || bossBars.get(uuid) == null) continue;
            bossBars.get(uuid).remove();
            bossBars.remove(uuid);
        }
        toRemove.clear();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!bossBars.containsKey(p.getUniqueId())) continue;
            bossBars.get(p.getUniqueId()).tick();
        }
    }

    public static void removeBar(Player player) {
        toRemove.add(player.getUniqueId());
    }

    public static void onPlayerQuit(Player player) {
        toRemove.add(player.getUniqueId());
    }

    public static void mobDeath(EntityDeathEvent event) {
        if (event.getEntity().getScoreboardTags().contains("aurum_mob")) {
            event.setDroppedExp(0);
            ItemStack loot = new ItemStack(Material.AIR);
            LivingEntity entity = event.getEntity();
            NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "customLoot");
            PersistentDataContainer data = entity.getPersistentDataContainer();
            if (data.has(key, PersistentDataType.STRING)) {
                String encoded = (String)data.get(key, PersistentDataType.STRING);
                Double d = (Double)data.get(new NamespacedKey((Plugin)Aurum.getPlugin(), "lootChance"), PersistentDataType.DOUBLE);
                if (encoded != null && d != null) {
                    try {
                        if (ThreadLocalRandom.current().nextDouble(0.0, 1.0) < d) {
                            loot = CustomEntity.decodeItem(encoded);
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (!event.getEntity().getScoreboardTags().contains("aurum_friendly") && ThreadLocalRandom.current().nextInt(0, 5) == 0) {
                Integer level;
                NamespacedKey levelKey = new NamespacedKey((Plugin)Aurum.getPlugin(), "mobLevel");
                level = data.has(levelKey, PersistentDataType.INTEGER) ? Integer.valueOf((level = (Integer)data.get(levelKey, PersistentDataType.INTEGER)) != null ? level : 1) : Integer.valueOf(1);
                loot = Treasure.getMobDrop(level);
            }
            event.getDrops().clear();
            event.getDrops().add(loot);
            if (Aurum.getPlugin().getConfig().getBoolean("ExperienceSystem") && entity.getKiller() != null) {
                Player player = entity.getKiller();
                int xp = EntityHandler.calculateXP(player, entity);
                if (xp > 0) {
                    ExperienceManager.addXP(player, xp, true);
                }
                AreaEffectCloud display = (AreaEffectCloud)entity.getWorld().spawnEntity(entity.getLocation().add(0.0, 1.0, 0.0), EntityType.AREA_EFFECT_CLOUD, false);
                display.clearCustomEffects();
                display.setRadius(0.0f);
                display.setDuration(20);
                display.setParticle(Particle.BLOCK,Material.AIR.createBlockData());
                display.customName((Component)Component.text((String)("[" + player.getName() + " +" + (int)((double)xp * itemHelper.getAttributeRoll(player, AttributeID.XP_BONUS, true)) + " XP]"), (TextColor)NamedTextColor.GRAY));
                display.setCustomNameVisible(true);
            }
        }
    }

    private static int calculateXP(Player player, LivingEntity mob) {
        String s;
        int playerLevel = ExperienceManager.getLevel(player);
        Integer mobLevel = 1;
        if (mob.getPersistentDataContainer().has(new NamespacedKey((Plugin)Aurum.getPlugin(), "mobLevel"), PersistentDataType.INTEGER)) {
            mobLevel = (Integer)mob.getPersistentDataContainer().get(new NamespacedKey((Plugin)Aurum.getPlugin(), "mobLevel"), PersistentDataType.INTEGER);
            mobLevel = mobLevel != null ? mobLevel : 1;
        }
        if (Math.abs(mobLevel - playerLevel) > 9) {
            return 0;
        }
        double xpMultiplier = 1.0;
        if (mob.getPersistentDataContainer().has(new NamespacedKey((Plugin)Aurum.getPlugin(), "xpType"), PersistentDataType.STRING) && (s = (String)mob.getPersistentDataContainer().get(new NamespacedKey((Plugin)Aurum.getPlugin(), "xpType"), PersistentDataType.STRING)) != null) {
            switch (s) {
                case "LOW": {
                    xpMultiplier = 0.5;
                    break;
                }
                case "HIGH": {
                    xpMultiplier = 2.0;
                    break;
                }
                case "BOSS": {
                    xpMultiplier = 5.0;
                    break;
                }
                case "NONE": {
                    xpMultiplier = 0.0;
                }
            }
        }
        double m = Aurum.getPlugin().getConfig().getDouble("xp-multiplier");
        double rawXp = m * xpMultiplier * Math.floor(Math.pow(mobLevel.intValue(), 1.1) + 7.0);
        return (int)(rawXp * Math.cos(Math.abs((double)mobLevel.intValue() - (double)playerLevel) * 0.15915494309189535));
    }

    public static void playerPassiveRegen() {
        if (Aurum.getPlugin().getConfig().getBoolean("passive-regen")) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!RuneAbilityHandler.isBloodrushNotActive(p)) continue;
                p.setHealth(Math.min(p.getHealth() + 2.0, Objects.requireNonNull(p.getAttribute(Attribute.MAX_HEALTH)).getValue()));
            }
        }
    }
}

