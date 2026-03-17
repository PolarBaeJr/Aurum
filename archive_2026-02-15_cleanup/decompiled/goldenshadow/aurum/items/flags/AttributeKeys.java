/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.NamespacedKey
 *  org.bukkit.plugin.Plugin
 */
package goldenshadow.aurum.items.flags;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.items.flags.AttributeID;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

public class AttributeKeys {
    public static final NamespacedKey EXPLODING = new NamespacedKey((Plugin)Aurum.getPlugin(), "id_exploding");
    public static final NamespacedKey MAIN_ATTACK_DAMAGE = new NamespacedKey((Plugin)Aurum.getPlugin(), "id_main_attack_damage");
    public static final NamespacedKey HEALTH = new NamespacedKey((Plugin)Aurum.getPlugin(), "id_health");
    public static final NamespacedKey JUMP_HEIGHT = new NamespacedKey((Plugin)Aurum.getPlugin(), "id_jump_height");
    public static final NamespacedKey FREEZING = new NamespacedKey((Plugin)Aurum.getPlugin(), "id_freezing");
    public static final NamespacedKey LIFE_STEAL = new NamespacedKey((Plugin)Aurum.getPlugin(), "id_life_steal");
    public static final NamespacedKey REBOUND = new NamespacedKey((Plugin)Aurum.getPlugin(), "id_rebound");
    public static final NamespacedKey COMBUSTION = new NamespacedKey((Plugin)Aurum.getPlugin(), "id_combustion");
    public static final NamespacedKey REFLECTION = new NamespacedKey((Plugin)Aurum.getPlugin(), "id_reflection");
    public static final NamespacedKey WALK_SPEED = new NamespacedKey((Plugin)Aurum.getPlugin(), "id_walk_speed");
    public static final NamespacedKey DODGE = new NamespacedKey((Plugin)Aurum.getPlugin(), "id_dodge");
    public static final NamespacedKey CRITICAL_STRIKE = new NamespacedKey((Plugin)Aurum.getPlugin(), "id_critical_strike");
    public static final NamespacedKey LIGHT_FOOT = new NamespacedKey((Plugin)Aurum.getPlugin(), "id_light_foot");
    public static final NamespacedKey RESISTANCE = new NamespacedKey((Plugin)Aurum.getPlugin(), "id_resistance");
    public static final NamespacedKey RUNE_DAMAGE = new NamespacedKey((Plugin)Aurum.getPlugin(), "id_rune_damage");
    public static final NamespacedKey RUNE_AFFINITY = new NamespacedKey((Plugin)Aurum.getPlugin(), "id_rune_affinity");
    public static final NamespacedKey XP_BONUS = new NamespacedKey((Plugin)Aurum.getPlugin(), "id_xp_bonus");
    public static final NamespacedKey ATTACK_SPEED = new NamespacedKey((Plugin)Aurum.getPlugin(), "id_attack_speed");

    public static NamespacedKey getKey(AttributeID attribute) {
        return switch (attribute) {
            default -> throw new IncompatibleClassChangeError();
            case AttributeID.EXPLODING -> EXPLODING;
            case AttributeID.MAIN_ATTACK_DAMAGE -> MAIN_ATTACK_DAMAGE;
            case AttributeID.HEALTH -> HEALTH;
            case AttributeID.JUMP_HEIGHT -> JUMP_HEIGHT;
            case AttributeID.FREEZING -> FREEZING;
            case AttributeID.LIFE_STEAL -> LIFE_STEAL;
            case AttributeID.REBOUND -> REBOUND;
            case AttributeID.COMBUSTION -> COMBUSTION;
            case AttributeID.REFLECTION -> REFLECTION;
            case AttributeID.WALK_SPEED -> WALK_SPEED;
            case AttributeID.DODGE -> DODGE;
            case AttributeID.CRITICAL_STRIKE -> CRITICAL_STRIKE;
            case AttributeID.LIGHT_FOOT -> LIGHT_FOOT;
            case AttributeID.RESISTANCE -> RESISTANCE;
            case AttributeID.RUNE_DAMAGE -> RUNE_DAMAGE;
            case AttributeID.RUNE_AFFINITY -> RUNE_AFFINITY;
            case AttributeID.XP_BONUS -> XP_BONUS;
            case AttributeID.ATTACK_SPEED -> ATTACK_SPEED;
            case AttributeID.FISH_BAIT -> null;
        };
    }
}

