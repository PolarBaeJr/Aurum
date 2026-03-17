/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.NamespacedKey
 *  org.bukkit.Sound
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.persistence.PersistentDataContainer
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 */
package goldenshadow.aurum.other;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.items.ItemHelper;
import goldenshadow.aurum.items.flags.AttributeID;
import java.util.Objects;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class ExperienceManager {
    private static final NamespacedKey combatLevel = new NamespacedKey((Plugin)Aurum.getPlugin(), "combatLevel");
    private static final NamespacedKey combatXP = new NamespacedKey((Plugin)Aurum.getPlugin(), "combatXP");
    private static final ItemHelper itemHelper = new ItemHelper();

    public static void joinEvent(Player player) {
        PersistentDataContainer data = player.getPersistentDataContainer();
        if (!data.has(combatLevel, PersistentDataType.INTEGER)) {
            ExperienceManager.resetLevelAndXP(player);
        }
    }

    public static void resetLevelAndXP(Player player) {
        PersistentDataContainer data = player.getPersistentDataContainer();
        data.set(combatXP, PersistentDataType.INTEGER, 0);
        data.set(combatLevel, PersistentDataType.INTEGER, 1);
        ExperienceManager.visualise(player);
    }

    public static void hardReset(Player player) {
        PersistentDataContainer data = player.getPersistentDataContainer();
        data.remove(combatXP);
        data.remove(combatLevel);
    }

    public static int getXP(Player player) {
        Integer i = (Integer)player.getPersistentDataContainer().get(combatXP, PersistentDataType.INTEGER);
        return i != null ? i : -1;
    }

    public static int getLevel(Player player) {
        Integer i = (Integer)player.getPersistentDataContainer().get(combatLevel, PersistentDataType.INTEGER);
        return i != null ? i : -1;
    }

    public static void setXP(Player player, int value) {
        if (player.getPersistentDataContainer().has(combatXP, PersistentDataType.INTEGER)) {
            player.getPersistentDataContainer().set(combatXP, PersistentDataType.INTEGER, value);
            ExperienceManager.visualise(player);
        }
    }

    public static void setLevel(Player player, int value) {
        if (player.getPersistentDataContainer().has(combatLevel, PersistentDataType.INTEGER)) {
            player.getPersistentDataContainer().set(combatLevel, PersistentDataType.INTEGER, value);
            ExperienceManager.setXP(player, 0);
            ExperienceManager.visualise(player);
        }
    }

    public static void addXP(Player player, int value, boolean applyAttribute) {
        if (player.getPersistentDataContainer().has(combatXP, PersistentDataType.INTEGER)) {
            player.getWorld().playSound((Entity)player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
            Integer xp = (Integer)player.getPersistentDataContainer().get(combatXP, PersistentDataType.INTEGER);
            Integer level = (Integer)player.getPersistentDataContainer().get(combatLevel, PersistentDataType.INTEGER);
            if (xp != null && level != null) {
                int xpForNextLevel = ExperienceManager.xpToNextLevel(level);
                if (applyAttribute && itemHelper.isAttributeEquipped(player, AttributeID.XP_BONUS)) {
                    value = (int)((double)value * itemHelper.getAttributeRoll(player, AttributeID.XP_BONUS, true));
                }
                if ((xp = Integer.valueOf(xp + value)) >= xpForNextLevel) {
                    Integer n = level;
                    level = level + 1;
                    xp = xp - xpForNextLevel;
                    player.getPersistentDataContainer().set(combatXP, PersistentDataType.INTEGER, 0);
                    player.getPersistentDataContainer().set(combatLevel, PersistentDataType.INTEGER, level);
                    ExperienceManager.levelUp(player, level);
                    ExperienceManager.addXP(player, xp, false);
                } else {
                    player.getPersistentDataContainer().set(combatXP, PersistentDataType.INTEGER, xp);
                }
                ExperienceManager.visualise(player);
            }
        }
    }

    public static void removeXp(Player player, int value) {
        Integer xp;
        if (player.getPersistentDataContainer().has(combatXP, PersistentDataType.INTEGER) && (xp = (Integer)player.getPersistentDataContainer().get(combatXP, PersistentDataType.INTEGER)) != null) {
            xp = Math.max(xp - value, 0);
            player.getPersistentDataContainer().set(combatXP, PersistentDataType.INTEGER, xp);
            ExperienceManager.visualise(player);
        }
    }

    private static void levelUp(Player player, int newLevel) {
        player.sendMessage(Component.text("[", NamedTextColor.GOLD).append(Component.text("!", NamedTextColor.YELLOW)).append(Component.text("] ", NamedTextColor.GOLD)).append(Component.text("Level Up!     ", NamedTextColor.GREEN, TextDecoration.BOLD)).append(Component.text((newLevel - 1) + " -> " + newLevel, NamedTextColor.GRAY)));
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        player.setHealth(Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue());
    }

    public static int xpToNextLevel(int level) {
        return (int)((double)(level * 25 + 100) + Math.pow(level * 3, 2.0));
    }

    private static void visualise(Player player) {
        PersistentDataContainer data = player.getPersistentDataContainer();
        if (data.has(combatXP, PersistentDataType.INTEGER)) {
            Integer xp = (Integer)data.get(combatXP, PersistentDataType.INTEGER);
            Integer level = (Integer)data.get(combatLevel, PersistentDataType.INTEGER);
            if (xp != null && level != null) {
                float xpToNext = ExperienceManager.xpToNextLevel(level);
                player.setLevel(level.intValue());
                player.setExp((float)xp.intValue() / xpToNext);
            }
        }
    }
}

