/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.NamespacedKey
 *  org.bukkit.plugin.Plugin
 */
package goldenshadow.aurum.items.flags;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.items.flags.Rune;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

public class RuneKeys {
    public static final NamespacedKey EMPTY = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_empty");
    public static final NamespacedKey AMOGUS = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_amogus");
    public static final NamespacedKey ARCANE_RAY = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_arcane_ray");
    public static final NamespacedKey ARCANE_SHIELD = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_arcane_shield");
    public static final NamespacedKey CHARGE = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_charge");
    public static final NamespacedKey BLOOD_RUSH = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_blood_rush");
    public static final NamespacedKey DISPLACEMENT = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_displacement");
    public static final NamespacedKey DISTORTION = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_distortion");
    public static final NamespacedKey DRAGON_SKIN = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_dragon_skin");
    public static final NamespacedKey FALLING_STAR = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_falling_star");
    public static final NamespacedKey FIREBALL = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_fireball");
    public static final NamespacedKey FISH_LUNG = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_fish_lung");
    public static final NamespacedKey FROZEN_SPARK = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_frozen_spark");
    public static final NamespacedKey GOLD_PACT = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_gold_pact");
    public static final NamespacedKey GRACE = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_grace");
    public static final NamespacedKey GROUND_SLAM = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_ground_slam");
    public static final NamespacedKey HEAL = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_heal");
    public static final NamespacedKey LEECH_FOOT = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_leech_foot");
    public static final NamespacedKey PIROUETTE = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_pirouette");
    public static final NamespacedKey REGENERATION = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_regeneration");
    public static final NamespacedKey RESTORATION = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_restoration");
    public static final NamespacedKey RESURGENCE = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_resurgence");
    public static final NamespacedKey RIFT_STEP = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_rift_step");
    public static final NamespacedKey RITUAL = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_ritual");
    public static final NamespacedKey SACRIFICE = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_sacrifice");
    public static final NamespacedKey SHOCK_WAVE = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_shock_wave");
    public static final NamespacedKey SMITE = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_smite");
    public static final NamespacedKey SWIFTNESS = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_swiftness");
    public static final NamespacedKey TAUNT = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_taunt");
    public static final NamespacedKey TIME_LOCK = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_time_lock");
    public static final NamespacedKey VITALITY = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_vitality");
    public static final NamespacedKey WIND_SLASH = new NamespacedKey((Plugin)Aurum.getPlugin(), "rune_wind_slash");

    public static NamespacedKey getKey(Rune rune) {
        return switch (rune) {
            default -> throw new IncompatibleClassChangeError();
            case Rune.AMOGUS -> AMOGUS;
            case Rune.ARCANE_RAY -> ARCANE_RAY;
            case Rune.ARCANE_SHIELD -> ARCANE_SHIELD;
            case Rune.CHARGE -> CHARGE;
            case Rune.BLOOD_RUSH -> BLOOD_RUSH;
            case Rune.DISPLACEMENT -> DISPLACEMENT;
            case Rune.DISTORTION -> DISTORTION;
            case Rune.DRAGON_SKIN -> DRAGON_SKIN;
            case Rune.FALLING_STAR -> FALLING_STAR;
            case Rune.FIREBALL -> FIREBALL;
            case Rune.FISH_LUNG -> FISH_LUNG;
            case Rune.FROZEN_SPARK -> FROZEN_SPARK;
            case Rune.GOLD_PACT -> GOLD_PACT;
            case Rune.GRACE -> GRACE;
            case Rune.GROUND_SLAM -> GROUND_SLAM;
            case Rune.HEAL -> HEAL;
            case Rune.RITUAL -> RITUAL;
            case Rune.SACRIFICE -> SACRIFICE;
            case Rune.SHOCK_WAVE -> SHOCK_WAVE;
            case Rune.SMITE -> SMITE;
            case Rune.SWIFTNESS -> SWIFTNESS;
            case Rune.TAUNT -> TAUNT;
            case Rune.TIME_LOCK -> TIME_LOCK;
            case Rune.VITALITY -> VITALITY;
            case Rune.WIND_SLASH -> WIND_SLASH;
            case Rune.PIROUETTE -> PIROUETTE;
            case Rune.RIFT_STEP -> RIFT_STEP;
            case Rune.RESURGENCE -> RESURGENCE;
            case Rune.RESTORATION -> RESTORATION;
            case Rune.REGENERATION -> REGENERATION;
            case Rune.LEECH_FOOT -> LEECH_FOOT;
            case Rune.EMPTY -> EMPTY;
        };
    }
}

