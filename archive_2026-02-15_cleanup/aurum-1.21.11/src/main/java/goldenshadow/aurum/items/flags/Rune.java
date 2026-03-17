/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataType
 */
package goldenshadow.aurum.items.flags;

import goldenshadow.aurum.items.flags.RuneKeys;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public enum Rune {
    AMOGUS,
    ARCANE_RAY,
    ARCANE_SHIELD,
    CHARGE,
    BLOOD_RUSH,
    DISPLACEMENT,
    DISTORTION,
    DRAGON_SKIN,
    FALLING_STAR,
    FIREBALL,
    FISH_LUNG,
    FROZEN_SPARK,
    GOLD_PACT,
    GRACE,
    GROUND_SLAM,
    HEAL,
    RITUAL,
    SACRIFICE,
    SHOCK_WAVE,
    SMITE,
    SWIFTNESS,
    TAUNT,
    TIME_LOCK,
    VITALITY,
    WIND_SLASH,
    LEECH_FOOT,
    PIROUETTE,
    REGENERATION,
    RESTORATION,
    RESURGENCE,
    RIFT_STEP,
    EMPTY;


    public static List<Rune> getRunesOnItem(ItemStack itemStack) {
        ArrayList<Rune> list = new ArrayList<Rune>();
        if (itemStack.getItemMeta() != null) {
            ItemMeta meta = itemStack.getItemMeta();
            if (meta.getPersistentDataContainer().has(RuneKeys.DISPLACEMENT, PersistentDataType.INTEGER)) {
                list.add(DISPLACEMENT);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.AMOGUS, PersistentDataType.INTEGER)) {
                list.add(AMOGUS);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.CHARGE, PersistentDataType.INTEGER)) {
                list.add(CHARGE);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.ARCANE_RAY, PersistentDataType.INTEGER)) {
                list.add(ARCANE_RAY);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.ARCANE_SHIELD, PersistentDataType.INTEGER)) {
                list.add(ARCANE_SHIELD);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.BLOOD_RUSH, PersistentDataType.INTEGER)) {
                list.add(BLOOD_RUSH);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.DISTORTION, PersistentDataType.INTEGER)) {
                list.add(DISTORTION);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.DRAGON_SKIN, PersistentDataType.INTEGER)) {
                list.add(DRAGON_SKIN);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.FALLING_STAR, PersistentDataType.INTEGER)) {
                list.add(FALLING_STAR);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.FIREBALL, PersistentDataType.INTEGER)) {
                list.add(FIREBALL);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.FISH_LUNG, PersistentDataType.INTEGER)) {
                list.add(FISH_LUNG);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.FROZEN_SPARK, PersistentDataType.INTEGER)) {
                list.add(FROZEN_SPARK);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.GOLD_PACT, PersistentDataType.INTEGER)) {
                list.add(GOLD_PACT);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.GRACE, PersistentDataType.INTEGER)) {
                list.add(GRACE);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.GROUND_SLAM, PersistentDataType.INTEGER)) {
                list.add(GROUND_SLAM);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.HEAL, PersistentDataType.INTEGER)) {
                list.add(HEAL);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.LEECH_FOOT, PersistentDataType.INTEGER)) {
                list.add(LEECH_FOOT);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.PIROUETTE, PersistentDataType.INTEGER)) {
                list.add(PIROUETTE);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.REGENERATION, PersistentDataType.INTEGER)) {
                list.add(REGENERATION);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.RESTORATION, PersistentDataType.INTEGER)) {
                list.add(RESTORATION);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.RESURGENCE, PersistentDataType.INTEGER)) {
                list.add(RESURGENCE);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.RIFT_STEP, PersistentDataType.INTEGER)) {
                list.add(RIFT_STEP);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.RITUAL, PersistentDataType.INTEGER)) {
                list.add(RITUAL);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.SACRIFICE, PersistentDataType.INTEGER)) {
                list.add(SACRIFICE);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.SHOCK_WAVE, PersistentDataType.INTEGER)) {
                list.add(SHOCK_WAVE);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.SMITE, PersistentDataType.INTEGER)) {
                list.add(SMITE);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.SWIFTNESS, PersistentDataType.INTEGER)) {
                list.add(SWIFTNESS);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.TAUNT, PersistentDataType.INTEGER)) {
                list.add(TAUNT);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.TIME_LOCK, PersistentDataType.INTEGER)) {
                list.add(TIME_LOCK);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.VITALITY, PersistentDataType.INTEGER)) {
                list.add(VITALITY);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.WIND_SLASH, PersistentDataType.INTEGER)) {
                list.add(WIND_SLASH);
            }
            if (meta.getPersistentDataContainer().has(RuneKeys.EMPTY, PersistentDataType.INTEGER)) {
                list.add(EMPTY);
            }
        }
        return list;
    }

    public static boolean isValidEnum(String s) {
        try {
            Rune.valueOf(s);
            return true;
        }
        catch (IllegalArgumentException e) {
            return false;
        }
    }
}

