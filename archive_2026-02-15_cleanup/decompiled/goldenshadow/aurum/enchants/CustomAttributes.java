/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.enchantments.Enchantment
 */
package goldenshadow.aurum.enchants;

import goldenshadow.aurum.enchants.EnchantmentWrapper;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.enchantments.Enchantment;

@Deprecated
public class CustomAttributes {
    public static final Enchantment EXPLODING = new EnchantmentWrapper("exploding", "Exploding", Integer.MAX_VALUE);
    public static final Enchantment MAIN_ATTACK_DAMAGE = new EnchantmentWrapper("main_attack_damage", "Main Attack Damage", Integer.MAX_VALUE);
    public static final Enchantment HEALTH = new EnchantmentWrapper("health", "Health", Integer.MAX_VALUE);
    public static final Enchantment JUMP_HEIGHT = new EnchantmentWrapper("jump_height", "Jump Height", Integer.MAX_VALUE);
    public static final Enchantment FREEZING = new EnchantmentWrapper("freezing", "Freezing", Integer.MAX_VALUE);
    public static final Enchantment LIFE_STEAL = new EnchantmentWrapper("life_steal", "Life Steal", Integer.MAX_VALUE);
    public static final Enchantment REBOUND = new EnchantmentWrapper("rebound", "Rebound", Integer.MAX_VALUE);
    public static final Enchantment COMBUSTION = new EnchantmentWrapper("combustion", "Combustion", Integer.MAX_VALUE);
    public static final Enchantment REFLECTION = new EnchantmentWrapper("reflection", "Reflection", Integer.MAX_VALUE);
    public static final Enchantment WALK_SPEED = new EnchantmentWrapper("walk_speed", "Walk Speed", Integer.MAX_VALUE);
    public static final Enchantment DODGE = new EnchantmentWrapper("dodge", "Dodge", Integer.MAX_VALUE);
    public static final Enchantment CRITICAL_STRIKE = new EnchantmentWrapper("critical_strike", "Critical Strike", Integer.MAX_VALUE);
    public static final Enchantment LIGHT_FOOT = new EnchantmentWrapper("light_foot", "Light Foot", Integer.MAX_VALUE);
    public static final Enchantment RESISTANCE = new EnchantmentWrapper("resistance", "Resistance", Integer.MAX_VALUE);
    public static final Enchantment RUNE_DAMAGE = new EnchantmentWrapper("rune_damage", "Rune Damage", Integer.MAX_VALUE);
    public static final Enchantment RUNE_AFFINITY = new EnchantmentWrapper("rune_affinity", "Rune Affinity", Integer.MAX_VALUE);
    public static final Enchantment XP_BONUS = new EnchantmentWrapper("xp_bonus", "XP Bonus", Integer.MAX_VALUE);
    public static final Enchantment EMPTY_RUNE_SLOT = new EnchantmentWrapper("empty_rune_slot", "Empty Rune Slot", Integer.MAX_VALUE);
    public static final Enchantment RUNE_DISPLACEMENT = new EnchantmentWrapper("rune_displacement", "Displacement", Integer.MAX_VALUE);
    public static final Enchantment RUNE_BLOOD_RUSH = new EnchantmentWrapper("rune_blood_rush", "Blood Rush", Integer.MAX_VALUE);
    public static final Enchantment RUNE_CHARGE = new EnchantmentWrapper("rune_charge", "Charge", Integer.MAX_VALUE);
    public static final Enchantment RUNE_SMITE = new EnchantmentWrapper("rune_smite", "Smite", Integer.MAX_VALUE);
    public static final Enchantment RUNE_FIREBALL = new EnchantmentWrapper("rune_fireball", "Fireball", Integer.MAX_VALUE);
    public static final Enchantment RUNE_WIND_SLASH = new EnchantmentWrapper("rune_wind_slash", "Wind Slash", Integer.MAX_VALUE);
    public static final Enchantment RUNE_HEAL = new EnchantmentWrapper("rune_heal", "Heal", Integer.MAX_VALUE);
    public static final Enchantment RUNE_RIFT_STEP = new EnchantmentWrapper("rune_rift_step", "Rift Step", Integer.MAX_VALUE);
    public static final Enchantment RUNE_RESURGENCE = new EnchantmentWrapper("rune_resurgence", "Resurgence", Integer.MAX_VALUE);
    public static final Enchantment RUNE_SACRIFICE = new EnchantmentWrapper("rune_sacrifice", "Sacrifice", Integer.MAX_VALUE);
    public static final Enchantment RUNE_TIME_LOCK = new EnchantmentWrapper("rune_time_lock", "Time Lock", Integer.MAX_VALUE);
    public static final Enchantment RUNE_GOLD_PACT = new EnchantmentWrapper("rune_gold_pact", "Gold Pact", Integer.MAX_VALUE);
    public static final Enchantment RUNE_SHOCK_WAVE = new EnchantmentWrapper("rune_shock_wave", "Shock Wave", Integer.MAX_VALUE);
    public static final Enchantment RUNE_LEECH_FOOT = new EnchantmentWrapper("rune_leech_foot", "Leech Foot", Integer.MAX_VALUE);
    public static final Enchantment RUNE_DISTORTION = new EnchantmentWrapper("rune_distortion", "Distortion", Integer.MAX_VALUE);
    public static final Enchantment RUNE_DRAGON_SKIN = new EnchantmentWrapper("rune_dragon_skin", "Dragon Skin", Integer.MAX_VALUE);
    public static final Enchantment RUNE_FISH_LUNG = new EnchantmentWrapper("rune_fish_lung", "Fish Lung", Integer.MAX_VALUE);
    public static final Enchantment RUNE_FALLING_STAR = new EnchantmentWrapper("rune_falling_star", "Falling Star", Integer.MAX_VALUE);
    public static final Enchantment RUNE_SWIFTNESS = new EnchantmentWrapper("rune_swiftness", "Swiftness", Integer.MAX_VALUE);
    public static final Enchantment RUNE_ARCANE_SHIELD = new EnchantmentWrapper("rune_arcane_shield", "Arcane Shield", Integer.MAX_VALUE);
    public static final Enchantment RUNE_REGENERATION = new EnchantmentWrapper("rune_regeneration", "Regeneration", Integer.MAX_VALUE);
    public static final Enchantment RUNE_GRACE = new EnchantmentWrapper("rune_grace", "Grace", Integer.MAX_VALUE);
    public static final Enchantment RUNE_VITALITY = new EnchantmentWrapper("rune_vitality", "Vitality", Integer.MAX_VALUE);
    public static final Enchantment RUNE_RESTORATION = new EnchantmentWrapper("rune_restoration", "Restoration", Integer.MAX_VALUE);
    public static final Enchantment RUNE_AMOGUS = new EnchantmentWrapper("rune_amogus", "Amogus", Integer.MAX_VALUE);
    public static final Enchantment RUNE_ARCANE_RAY = new EnchantmentWrapper("rune_arcane_ray", "Arcane Ray", Integer.MAX_VALUE);
    public static final Enchantment RUNE_GROUND_SLAM = new EnchantmentWrapper("rune_ground_slam", "Ground Slam", Integer.MAX_VALUE);
    public static final Enchantment RUNE_PIROUETTE = new EnchantmentWrapper("rune_pirouette", "Pirouette", Integer.MAX_VALUE);
    public static final Enchantment RUNE_RITUAL = new EnchantmentWrapper("rune_ritual", "Ritual", Integer.MAX_VALUE);
    public static final Enchantment ATTACK_SPEED = new EnchantmentWrapper("attack_speed", "Attack Speed", Integer.MAX_VALUE);
    public static final Enchantment RUNE_FROZEN_SPARK = new EnchantmentWrapper("rune_frozen_spark", "Frozen Spark", Integer.MAX_VALUE);
    public static final Enchantment RUNE_TAUNT = new EnchantmentWrapper("rune_taunt", "Taunt", Integer.MAX_VALUE);

    public static void register() {
        ArrayList<Enchantment> list = new ArrayList<Enchantment>(List.of(EXPLODING, MAIN_ATTACK_DAMAGE, HEALTH, JUMP_HEIGHT, FREEZING, LIFE_STEAL, REBOUND, COMBUSTION, REFLECTION, WALK_SPEED, DODGE, CRITICAL_STRIKE, LIGHT_FOOT, RESISTANCE, EMPTY_RUNE_SLOT, RUNE_AMOGUS, RUNE_AFFINITY, RUNE_ARCANE_RAY, RUNE_ARCANE_SHIELD, RUNE_CHARGE, RUNE_BLOOD_RUSH, RUNE_DAMAGE, RUNE_DISPLACEMENT, RUNE_DISTORTION, RUNE_DRAGON_SKIN, RUNE_FALLING_STAR, RUNE_FIREBALL, RUNE_FISH_LUNG, RUNE_FROZEN_SPARK, RUNE_GOLD_PACT, RUNE_GRACE, RUNE_GROUND_SLAM, RUNE_HEAL, RUNE_LEECH_FOOT, RUNE_PIROUETTE, RUNE_REGENERATION, RUNE_RESTORATION, RUNE_RESURGENCE, RUNE_RIFT_STEP, RUNE_RITUAL, RUNE_SACRIFICE, RUNE_SHOCK_WAVE, RUNE_SMITE, RUNE_SWIFTNESS, RUNE_TAUNT, RUNE_TIME_LOCK, RUNE_VITALITY, RUNE_WIND_SLASH));
        for (Enchantment enchantment : list) {
            if (Arrays.stream(Enchantment.values()).toList().contains(enchantment)) continue;
            CustomAttributes.registerEnchantment(enchantment);
        }
    }

    public static void registerEnchantment(Enchantment enchantment) {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment((Enchantment)enchantment);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

