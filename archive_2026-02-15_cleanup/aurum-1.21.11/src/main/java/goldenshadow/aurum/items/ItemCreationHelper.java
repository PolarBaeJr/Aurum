/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeModifier
 *  org.bukkit.attribute.AttributeModifier$Operation
 *  org.bukkit.inventory.EquipmentSlot
 *  org.bukkit.inventory.meta.ArmorMeta
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.inventory.meta.trim.ArmorTrim
 *  org.bukkit.inventory.meta.trim.TrimMaterial
 *  org.bukkit.inventory.meta.trim.TrimPattern
 *  org.bukkit.persistence.PersistentDataContainer
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 */
package goldenshadow.aurum.items;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.items.ItemType;
import goldenshadow.aurum.items.Rarity;
import goldenshadow.aurum.items.flags.AttributeID;
import goldenshadow.aurum.items.flags.AttributeKeys;
import goldenshadow.aurum.items.flags.Rune;
import goldenshadow.aurum.items.flags.RuneKeys;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class ItemCreationHelper {
    public String capitalize(String input) {
        int colonIndex = input.indexOf(":");
        String substring = input.substring(colonIndex + 1);
        substring = substring.replace("_", " ");
        CharSequence[] words = substring.split(" ");
        for (int i = 0; i < words.length; ++i) {
            words[i] = ((String)words[i]).substring(0, 1).toUpperCase() + ((String)words[i]).substring(1);
        }
        return String.join((CharSequence)" ", words);
    }

    public AttributeID getRandomAttribute(ItemMeta meta) {
        int roll = ThreadLocalRandom.current().nextInt(0, 100);
        AttributeID attribute = roll < 5 ? AttributeID.EXPLODING : (roll < 14 ? AttributeID.MAIN_ATTACK_DAMAGE : (roll < 18 ? AttributeID.HEALTH : (roll < 27 ? AttributeID.WALK_SPEED : (roll < 34 ? AttributeID.JUMP_HEIGHT : (roll < 43 ? AttributeID.FREEZING : (roll < 48 ? AttributeID.LIFE_STEAL : (roll < 57 ? AttributeID.REBOUND : (roll < 59 ? AttributeID.COMBUSTION : (roll < 64 ? AttributeID.REFLECTION : (roll < 73 ? AttributeID.DODGE : (roll < 80 ? AttributeID.CRITICAL_STRIKE : (roll < 89 ? AttributeID.LIGHT_FOOT : (roll < 94 ? AttributeID.RESISTANCE : (roll < 97 ? AttributeID.RUNE_DAMAGE : (roll < 99 ? AttributeID.RUNE_AFFINITY : AttributeID.XP_BONUS)))))))))))))));
        if (meta.getPersistentDataContainer().has(AttributeKeys.getKey(attribute), PersistentDataType.INTEGER)) {
            return this.getRandomAttribute(meta);
        }
        return attribute;
    }

    public int getRandomRoll(AttributeID attribute, Rarity rarity, ItemType itemType, int level) {
        double roll = 0.0;
        if (attribute == AttributeID.EXPLODING) {
            switch (itemType) {
                case SPEAR: {
                    roll = (int)this.addRarityMultiplier(0.3 * (double)level + 5.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case SWORD: {
                    roll = (int)this.addRarityMultiplier(0.2 * (double)level - 3.0 + (double)ThreadLocalRandom.current().nextInt(-10, 11), rarity);
                    break;
                }
                case WAND: {
                    roll = (int)this.addRarityMultiplier(0.4 * (double)level - 5.0 + (double)ThreadLocalRandom.current().nextInt(-10, 11), rarity);
                    break;
                }
                case HELMET: {
                    roll = (int)this.addRarityMultiplier(0.2 * (double)level + 8.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case CHESTPLATE: {
                    roll = (int)this.addRarityMultiplier(0.35 * (double)level - 3.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case LEGGINGS: {
                    roll = (int)this.addRarityMultiplier(0.25 * (double)level + (double)ThreadLocalRandom.current().nextInt(-8, 9), rarity);
                    break;
                }
                case BOOTS: {
                    roll = (int)this.addRarityMultiplier(0.15 * (double)level + 10.0 + (double)ThreadLocalRandom.current().nextInt(-3, 4), rarity);
                }
            }
        }
        if (attribute == AttributeID.MAIN_ATTACK_DAMAGE) {
            switch (itemType) {
                case SPEAR: {
                    roll = (int)this.addRarityMultiplier(0.2 * (double)level + 3.0 + (double)ThreadLocalRandom.current().nextInt(-3, 4), rarity);
                    break;
                }
                case SWORD: {
                    roll = (int)this.addRarityMultiplier(0.25 * (double)level + 7.0 + (double)ThreadLocalRandom.current().nextInt(-6, 7), rarity);
                    break;
                }
                case WAND: {
                    roll = (int)this.addRarityMultiplier(0.2 * (double)level + (double)ThreadLocalRandom.current().nextInt(-10, 11), rarity);
                    break;
                }
                case HELMET: {
                    roll = (int)this.addRarityMultiplier(0.15 * (double)level + 2.0 + (double)ThreadLocalRandom.current().nextInt(-3, 4), rarity);
                    break;
                }
                case CHESTPLATE: {
                    roll = (int)this.addRarityMultiplier(0.2 * (double)level - 3.0 + (double)ThreadLocalRandom.current().nextInt(-3, 4), rarity);
                    break;
                }
                case LEGGINGS: {
                    roll = (int)this.addRarityMultiplier(0.2 * (double)level + 7.0 + (double)ThreadLocalRandom.current().nextInt(-8, 9), rarity);
                    break;
                }
                case BOOTS: {
                    roll = (int)this.addRarityMultiplier(0.2 * (double)level + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                }
            }
        }
        if (attribute == AttributeID.HEALTH) {
            switch (itemType) {
                case SPEAR: {
                    roll = (int)this.addRarityMultiplier(Math.floor(0.09 * (double)level) + 10.0 + (double)ThreadLocalRandom.current().nextInt(-10, 11), rarity);
                    break;
                }
                case SWORD: {
                    roll = (int)this.addRarityMultiplier(Math.floor(0.11 * (double)level) - 3.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case WAND: {
                    roll = (int)this.addRarityMultiplier(Math.floor(0.12 * (double)level) + 3.0 + (double)ThreadLocalRandom.current().nextInt(-6, 7), rarity);
                    break;
                }
                case HELMET: {
                    roll = (int)this.addRarityMultiplier(0.05 * (double)level + 1.0 + (double)ThreadLocalRandom.current().nextInt(-6, 7), rarity);
                    break;
                }
                case CHESTPLATE: {
                    roll = (int)this.addRarityMultiplier(0.1 * (double)level + 2.0 + (double)ThreadLocalRandom.current().nextInt(-2, 3), rarity);
                    break;
                }
                case LEGGINGS: {
                    roll = (int)this.addRarityMultiplier(0.08 * (double)level + 3.0 + (double)ThreadLocalRandom.current().nextInt(-2, 3), rarity);
                    break;
                }
                case BOOTS: {
                    roll = (int)this.addRarityMultiplier(0.05 * (double)level + 1.0, rarity);
                }
            }
        }
        if (attribute == AttributeID.WALK_SPEED) {
            switch (itemType) {
                case SPEAR: {
                    roll = (int)this.addRarityMultiplier(0.15 * (double)level - 3.0 + (double)ThreadLocalRandom.current().nextInt(-6, 7), rarity);
                    break;
                }
                case SWORD: {
                    roll = (int)this.addRarityMultiplier(0.35 * (double)level + 5.0 + (double)ThreadLocalRandom.current().nextInt(-10, 11), rarity);
                    break;
                }
                case WAND: {
                    roll = (int)this.addRarityMultiplier(0.2 * (double)level + 5.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case HELMET: {
                    roll = (int)this.addRarityMultiplier(0.1 * (double)level + 5.0 + (double)ThreadLocalRandom.current().nextInt(-3, 4), rarity);
                    break;
                }
                case CHESTPLATE: {
                    roll = (int)this.addRarityMultiplier(0.08 * (double)level - 4.0 + (double)ThreadLocalRandom.current().nextInt(-3, 4), rarity);
                    break;
                }
                case LEGGINGS: {
                    roll = (int)this.addRarityMultiplier(0.15 * (double)level + (double)ThreadLocalRandom.current().nextInt(-3, 4), rarity);
                    break;
                }
                case BOOTS: {
                    roll = (int)this.addRarityMultiplier(0.3 * (double)level + 10.0 + (double)ThreadLocalRandom.current().nextInt(-3, 4), rarity);
                }
            }
        }
        if (attribute == AttributeID.JUMP_HEIGHT) {
            switch (itemType) {
                case SPEAR: {
                    roll = (int)Math.floor(0.18 * Math.sqrt(level) - 0.1 + ThreadLocalRandom.current().nextDouble(-0.5, 0.5));
                    break;
                }
                case SWORD: {
                    roll = (int)Math.floor(0.3 * Math.sqrt(level) - 0.1 + (double)ThreadLocalRandom.current().nextInt(-1, 2));
                    break;
                }
                case WAND: {
                    roll = (int)Math.floor(0.2 * Math.sqrt(level) - 0.6 + (double)ThreadLocalRandom.current().nextInt(-1, 2));
                    break;
                }
                case HELMET: {
                    roll = (int)Math.floor(0.02 * (double)level + ThreadLocalRandom.current().nextDouble(-0.5, 0.6));
                    break;
                }
                case CHESTPLATE: {
                    roll = (int)Math.floor(0.01 * (double)level + ThreadLocalRandom.current().nextDouble(-0.5, 0.6));
                    break;
                }
                case LEGGINGS: {
                    roll = (int)Math.floor(0.01 * (double)level + 1.0 + (double)ThreadLocalRandom.current().nextInt(-1, 2));
                    break;
                }
                case BOOTS: {
                    roll = (int)Math.floor(0.01 * (double)level + (double)ThreadLocalRandom.current().nextInt(-1, 2));
                }
            }
        }
        if (attribute == AttributeID.FREEZING) {
            switch (itemType) {
                case SPEAR: {
                    roll = (int)this.addRarityMultiplier(0.2 * (double)level + 5.0 + (double)ThreadLocalRandom.current().nextInt(-3, 4), rarity);
                    break;
                }
                case SWORD: {
                    roll = (int)this.addRarityMultiplier(0.17 * (double)level - 3.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case WAND: {
                    roll = (int)this.addRarityMultiplier(0.22 * (double)level - 5.0 + (double)ThreadLocalRandom.current().nextInt(-6, 7), rarity);
                    break;
                }
                case HELMET: {
                    roll = (int)this.addRarityMultiplier(0.2 * (double)level + 5.0 + (double)ThreadLocalRandom.current().nextInt(-6, 7), rarity);
                    break;
                }
                case CHESTPLATE: {
                    roll = (int)this.addRarityMultiplier(0.07 * (double)level + (double)ThreadLocalRandom.current().nextInt(-3, 4), rarity);
                    break;
                }
                case LEGGINGS: {
                    roll = (int)this.addRarityMultiplier(0.11 * (double)level + 3.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case BOOTS: {
                    roll = (int)this.addRarityMultiplier(0.1 * (double)level + 3.0 + (double)ThreadLocalRandom.current().nextInt(-3, 4), rarity);
                }
            }
        }
        if (attribute == AttributeID.LIFE_STEAL) {
            switch (itemType) {
                case SPEAR: 
                case CHESTPLATE: {
                    roll = (int)this.addRarityMultiplier(0.07 * (double)level + (double)ThreadLocalRandom.current().nextInt(-3, 4), rarity);
                    break;
                }
                case SWORD: 
                case HELMET: {
                    roll = (int)this.addRarityMultiplier(0.1 * (double)level + 5.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case WAND: {
                    roll = (int)this.addRarityMultiplier(0.11 * (double)level + 3.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case LEGGINGS: 
                case BOOTS: {
                    roll = (int)this.addRarityMultiplier(0.08 * (double)level + 5.0 + (double)ThreadLocalRandom.current().nextInt(-3, 4), rarity);
                }
            }
        }
        if (attribute == AttributeID.REBOUND) {
            switch (itemType) {
                case SPEAR: {
                    roll = (int)this.addRarityMultiplier(0.15 * (double)level + 5.0, rarity);
                    break;
                }
                case SWORD: {
                    roll = (int)this.addRarityMultiplier(0.12 * (double)level - 3.0 + (double)ThreadLocalRandom.current().nextInt(-4, 5), rarity);
                    break;
                }
                case WAND: {
                    roll = (int)this.addRarityMultiplier(0.1 * (double)level + (double)ThreadLocalRandom.current().nextInt(-7, 8), rarity);
                    break;
                }
                case HELMET: {
                    roll = (int)this.addRarityMultiplier(0.12 * (double)level - 5.0 + (double)ThreadLocalRandom.current().nextInt(-6, 7), rarity);
                    break;
                }
                case CHESTPLATE: {
                    roll = (int)this.addRarityMultiplier(0.1 * (double)level + 3.0 + (double)ThreadLocalRandom.current().nextInt(-2, 3), rarity);
                    break;
                }
                case LEGGINGS: {
                    roll = (int)this.addRarityMultiplier(0.1 * (double)level + 7.0 + (double)ThreadLocalRandom.current().nextInt(-2, 3), rarity);
                    break;
                }
                case BOOTS: {
                    roll = (int)this.addRarityMultiplier(0.1 * (double)level + 7.0 + (double)ThreadLocalRandom.current().nextInt(-3, 4), rarity);
                }
            }
        }
        if (attribute == AttributeID.COMBUSTION) {
            switch (itemType) {
                case SPEAR: {
                    roll = (int)this.addRarityMultiplier(0.08 * (double)level + 2.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case SWORD: {
                    roll = (int)this.addRarityMultiplier(0.03 * (double)level - 1.0 + (double)ThreadLocalRandom.current().nextInt(-3, 4), rarity);
                    break;
                }
                case WAND: 
                case LEGGINGS: {
                    roll = (int)this.addRarityMultiplier(0.13 * (double)level + 4.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case HELMET: {
                    roll = (int)this.addRarityMultiplier(0.08 * (double)level + 2.0 + (double)ThreadLocalRandom.current().nextInt(-4, 5), rarity);
                    break;
                }
                case CHESTPLATE: {
                    roll = (int)this.addRarityMultiplier(0.23 * (double)level - 10.0 + (double)ThreadLocalRandom.current().nextInt(-13, 14), rarity);
                    break;
                }
                case BOOTS: {
                    roll = (int)this.addRarityMultiplier(0.1 * (double)level, rarity);
                }
            }
        }
        if (attribute == AttributeID.REFLECTION) {
            switch (itemType) {
                case SPEAR: 
                case CHESTPLATE: {
                    roll = (int)this.addRarityMultiplier(0.15 * (double)level + 15.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case SWORD: 
                case HELMET: {
                    roll = (int)this.addRarityMultiplier(0.05 * (double)level + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case WAND: 
                case LEGGINGS: {
                    roll = (int)this.addRarityMultiplier(0.1 * (double)level - 3.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case BOOTS: {
                    roll = (int)this.addRarityMultiplier(0.08 * (double)level - 2.0 + (double)ThreadLocalRandom.current().nextInt(-4, 5), rarity);
                }
            }
        }
        if (attribute == AttributeID.DODGE) {
            switch (itemType) {
                case SPEAR: 
                case HELMET: {
                    roll = (int)this.addRarityMultiplier(0.1 * (double)level + 2.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case SWORD: 
                case BOOTS: {
                    roll = (int)this.addRarityMultiplier(0.2 * (double)level + 10.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case WAND: {
                    roll = (int)this.addRarityMultiplier(0.15 * (double)level + (double)ThreadLocalRandom.current().nextInt(-4, 5), rarity);
                    break;
                }
                case CHESTPLATE: {
                    roll = (int)this.addRarityMultiplier(0.15 * (double)level + 3.0 + (double)ThreadLocalRandom.current().nextInt(-4, 5), rarity);
                    break;
                }
                case LEGGINGS: {
                    roll = (int)this.addRarityMultiplier(0.15 * (double)level + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                }
            }
        }
        if (attribute == AttributeID.CRITICAL_STRIKE) {
            switch (itemType) {
                case SPEAR: {
                    roll = (int)this.addRarityMultiplier(0.1 * (double)level + 3.0 + (double)ThreadLocalRandom.current().nextInt(-4, 5), rarity);
                    break;
                }
                case SWORD: 
                case LEGGINGS: {
                    roll = (int)this.addRarityMultiplier(0.17 * (double)level + 4.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case WAND: 
                case BOOTS: {
                    roll = (int)this.addRarityMultiplier(0.11 * (double)level - 2.0 + (double)ThreadLocalRandom.current().nextInt(-3, 4), rarity);
                    break;
                }
                case HELMET: {
                    roll = (int)this.addRarityMultiplier(0.12 * (double)level + 3.0 + (double)ThreadLocalRandom.current().nextInt(-4, 5), rarity);
                    break;
                }
                case CHESTPLATE: {
                    roll = (int)this.addRarityMultiplier(0.1 * (double)level + 5.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                }
            }
        }
        if (attribute == AttributeID.LIGHT_FOOT) {
            switch (itemType) {
                case SPEAR: {
                    roll = (int)this.addRarityMultiplier(0.07 * (double)level - 1.0 + (double)ThreadLocalRandom.current().nextInt(-2, 3), rarity);
                    break;
                }
                case SWORD: 
                case BOOTS: {
                    roll = (int)this.addRarityMultiplier(0.22 * (double)level + 8.0 + (double)ThreadLocalRandom.current().nextInt(-3, 4), rarity);
                    break;
                }
                case WAND: 
                case LEGGINGS: {
                    roll = (int)this.addRarityMultiplier(0.13 * (double)level - 4.0 + (double)ThreadLocalRandom.current().nextInt(-6, 7), rarity);
                    break;
                }
                case HELMET: {
                    roll = (int)this.addRarityMultiplier(0.07 * (double)level - 1.0 + (double)ThreadLocalRandom.current().nextInt(-4, 5), rarity);
                    break;
                }
                case CHESTPLATE: {
                    roll = (int)this.addRarityMultiplier(0.05 * (double)level + 1.0 + (double)ThreadLocalRandom.current().nextInt(-3, 4), rarity);
                }
            }
        }
        if (attribute == AttributeID.RESISTANCE) {
            switch (itemType) {
                case SPEAR: {
                    roll = (int)this.addRarityMultiplier(0.25 * (double)level + 10.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case SWORD: 
                case HELMET: {
                    roll = (int)this.addRarityMultiplier(0.12 * (double)level - 5.0 + (double)ThreadLocalRandom.current().nextInt(-7, 8), rarity);
                    break;
                }
                case WAND: 
                case LEGGINGS: {
                    roll = (int)this.addRarityMultiplier(0.12 * (double)level + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case CHESTPLATE: {
                    roll = (int)this.addRarityMultiplier(0.2 * (double)level + 5.0 + (double)ThreadLocalRandom.current().nextInt(-10, 11), rarity);
                    break;
                }
                case BOOTS: {
                    roll = (int)this.addRarityMultiplier(0.1 * (double)level + 1.0 + (double)ThreadLocalRandom.current().nextInt(-3, 4), rarity);
                }
            }
        }
        if (attribute == AttributeID.RUNE_DAMAGE) {
            switch (itemType) {
                case SPEAR: {
                    roll = (int)this.addRarityMultiplier(0.2 * (double)level + 8.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case SWORD: {
                    roll = (int)this.addRarityMultiplier(0.15 * (double)level + 2.0 + (double)ThreadLocalRandom.current().nextInt(-4, 5), rarity);
                    break;
                }
                case HELMET: {
                    roll = (int)this.addRarityMultiplier(0.3 * (double)level + 6.0 + (double)ThreadLocalRandom.current().nextInt(-8, 9), rarity);
                    break;
                }
                case WAND: {
                    roll = (int)this.addRarityMultiplier(0.25 * (double)level + 7.0 + (double)ThreadLocalRandom.current().nextInt(-6, 7), rarity);
                    break;
                }
                case LEGGINGS: {
                    roll = (int)this.addRarityMultiplier(0.2 * (double)level + 8.0 + (double)ThreadLocalRandom.current().nextInt(-8, 9), rarity);
                    break;
                }
                case CHESTPLATE: {
                    roll = (int)this.addRarityMultiplier(0.25 * (double)level + 5.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case BOOTS: {
                    roll = (int)this.addRarityMultiplier(0.35 * (double)level + 5.0 + (double)ThreadLocalRandom.current().nextInt(-10, 11), rarity);
                }
            }
        }
        if (attribute == AttributeID.RUNE_AFFINITY) {
            switch (itemType) {
                case SPEAR: {
                    roll = (int)this.addRarityMultiplier(0.15 * (double)level + 10.0 + (double)ThreadLocalRandom.current().nextInt(-10, 11), rarity);
                    break;
                }
                case SWORD: {
                    roll = (int)this.addRarityMultiplier(0.1 * (double)level + 5.0 + (double)ThreadLocalRandom.current().nextInt(-9, 10), rarity);
                    break;
                }
                case HELMET: {
                    roll = (int)this.addRarityMultiplier(0.2 * (double)level + 10.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case WAND: {
                    roll = (int)this.addRarityMultiplier(0.2 * (double)level + 15.0 + (double)ThreadLocalRandom.current().nextInt(-10, 11), rarity);
                    break;
                }
                case LEGGINGS: {
                    roll = (int)this.addRarityMultiplier(0.1 * (double)level + 3.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case CHESTPLATE: {
                    roll = (int)this.addRarityMultiplier(0.15 * (double)level + 5.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case BOOTS: {
                    roll = (int)this.addRarityMultiplier(0.2 * (double)level + 6.0 + (double)ThreadLocalRandom.current().nextInt(-8, 9), rarity);
                }
            }
        }
        if (attribute == AttributeID.XP_BONUS) {
            switch (itemType) {
                case SPEAR: {
                    roll = (int)this.addRarityMultiplier(0.15 * (double)level + 8.0 + (double)ThreadLocalRandom.current().nextInt(-8, 9), rarity);
                    break;
                }
                case SWORD: {
                    roll = (int)this.addRarityMultiplier(0.15 * (double)level + 5.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case HELMET: 
                case CHESTPLATE: 
                case LEGGINGS: 
                case BOOTS: {
                    roll = (int)this.addRarityMultiplier(0.1 * (double)level + 10.0 + (double)ThreadLocalRandom.current().nextInt(-5, 6), rarity);
                    break;
                }
                case WAND: {
                    roll = (int)this.addRarityMultiplier(0.1 * (double)level + 15.0 + (double)ThreadLocalRandom.current().nextInt(-8, 9), rarity);
                }
            }
        }
        return roll != 0.0 ? (int)roll : 1;
    }

    private double addRarityMultiplier(double roll, Rarity rarity) {
        if (roll > 0.0) {
            return switch (rarity) {
                case Rarity.COMMON -> roll * 0.95;
                case Rarity.RARE -> roll * 0.98 + (double)ThreadLocalRandom.current().nextInt(1, 3);
                case Rarity.EPIC -> roll + (double)ThreadLocalRandom.current().nextInt(1, 4);
                case Rarity.LEGENDARY -> roll * 1.02 + (double)ThreadLocalRandom.current().nextInt(3, 8);
                default -> 1.0;
            };
        }
        return switch (rarity) {
            case Rarity.COMMON -> roll * 1.05;
            case Rarity.RARE -> roll;
            case Rarity.EPIC -> roll * 0.98;
            case Rarity.LEGENDARY -> roll * 0.95;
            default -> -1.0;
        };
    }

    public int getBaseHealth(Rarity rarity, ItemType itemType, int level) {
        double health = 0.0;
        switch (rarity) {
            case COMMON: {
                health = 0.45 * (double)level + 3.0 + ThreadLocalRandom.current().nextDouble(-0.11, 0.12) * (double)level;
                break;
            }
            case RARE: {
                health = 0.5 * (double)level + 3.0 + ThreadLocalRandom.current().nextDouble(-0.09, 0.1) * (double)level;
                break;
            }
            case EPIC: {
                health = 0.53 * (double)level + 4.0 + ThreadLocalRandom.current().nextDouble(-0.08, 0.09) * (double)level;
                break;
            }
            case LEGENDARY: {
                health = 0.55 * (double)level + 6.0 + ThreadLocalRandom.current().nextDouble(-0.06, 0.07) * (double)level;
            }
        }
        return switch (itemType) {
            case ItemType.HELMET -> (int)(0.2 * health);
            case ItemType.CHESTPLATE -> (int)(0.4 * health);
            case ItemType.LEGGINGS -> (int)(0.25 * health);
            case ItemType.BOOTS -> (int)(0.15 * health);
            default -> 0;
        };
    }

    public int getBaseDamage(Rarity rarity, ItemType itemType, int level) {
        switch (rarity) {
            case COMMON: {
                switch (itemType) {
                    case WAND: {
                        return (int)(1.6 * (double)level + 1.0 + ThreadLocalRandom.current().nextDouble(-0.22, 0.23) * (double)level);
                    }
                    case SPEAR: {
                        return (int)(1.9 * (double)level + 5.0 + ThreadLocalRandom.current().nextDouble(-0.15, 0.16) * (double)level);
                    }
                    case SWORD: {
                        return (int)(2.1 * (double)level + 10.0 + ThreadLocalRandom.current().nextDouble(-0.2, 0.21) * (double)level);
                    }
                }
            }
            case RARE: {
                switch (itemType) {
                    case WAND: {
                        return (int)(1.6 * (double)level + 3.0 + ThreadLocalRandom.current().nextDouble(-0.22, 0.23) * (double)level);
                    }
                    case SPEAR: {
                        return (int)(1.9 * (double)level + 7.0 + ThreadLocalRandom.current().nextDouble(-0.14, 0.15) * (double)level);
                    }
                    case SWORD: {
                        return (int)(2.2 * (double)level + 6.0 + ThreadLocalRandom.current().nextDouble(-0.21, 0.22) * (double)level);
                    }
                }
            }
            case EPIC: {
                switch (itemType) {
                    case WAND: {
                        return (int)(1.6 * (double)level + 4.0 + ThreadLocalRandom.current().nextDouble(-0.23, 0.24) * (double)level);
                    }
                    case SPEAR: {
                        return (int)(2.0 * (double)level + 7.0 + ThreadLocalRandom.current().nextDouble(-0.14, 0.15) * (double)level);
                    }
                    case SWORD: {
                        return (int)(2.2 * (double)level + 8.0 + ThreadLocalRandom.current().nextDouble(-0.19, 0.2) * (double)level);
                    }
                }
            }
            case LEGENDARY: {
                switch (itemType) {
                    case WAND: {
                        return (int)(1.6 * (double)level + 5.0 + ThreadLocalRandom.current().nextDouble(-0.25, 0.26) * (double)level);
                    }
                    case SPEAR: {
                        return (int)(2.0 * (double)level + 10.0 + ThreadLocalRandom.current().nextDouble(-0.15, 0.16) * (double)level);
                    }
                    case SWORD: {
                        return (int)(2.2 * (double)level + 10.0 + ThreadLocalRandom.current().nextDouble(-0.2, 0.21) * (double)level);
                    }
                }
            }
        }
        return 0;
    }

    public ItemMeta addAttribute(ItemMeta meta, int roll, AttributeID attribute, List<String> lore, ItemType itemType) {
        if (roll < 0) {
            if (attribute == AttributeID.HEALTH || attribute == AttributeID.JUMP_HEIGHT || attribute == AttributeID.ATTACK_SPEED) {
                lore.add(ChatColor.RED + String.valueOf(roll) + ChatColor.GRAY + " " + attribute.getName());
            } else {
                lore.add(ChatColor.RED + String.valueOf(roll) + "%" + ChatColor.GRAY + " " + attribute.getName());
            }
        } else if (attribute == AttributeID.HEALTH || attribute == AttributeID.JUMP_HEIGHT || attribute == AttributeID.ATTACK_SPEED) {
            lore.add(ChatColor.GREEN + "+" + roll + ChatColor.GRAY + " " + attribute.getName());
        } else {
            lore.add(ChatColor.GREEN + "+" + roll + "%" + ChatColor.GRAY + " " + attribute.getName());
        }
        if (attribute == AttributeID.HEALTH) {
            meta.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(NamespacedKey.fromString("aurum:generic_max_health"), (double)roll + this.getAttributeSum(Attribute.MAX_HEALTH, meta), AttributeModifier.Operation.ADD_NUMBER, this.parseItemType(itemType)));
        } else if (attribute == AttributeID.WALK_SPEED) {
            double amount = (double)roll / 100.0;
            meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, new AttributeModifier(NamespacedKey.fromString("aurum:generic_movement_speed"), 0.1 * amount, AttributeModifier.Operation.ADD_NUMBER, this.parseItemType(itemType)));
        }
        meta = this.addAttributeData(meta, attribute, roll);
        meta.setLore(lore);
        return meta;
    }

    public ItemMeta insertAttribute(ItemMeta meta, int roll, AttributeID attribute, List<String> lore, Material material) {
        if (roll < 0) {
            if (attribute == AttributeID.HEALTH || attribute == AttributeID.JUMP_HEIGHT || attribute == AttributeID.ATTACK_SPEED) {
                lore.add(4, ChatColor.RED + String.valueOf(roll) + ChatColor.GRAY + " " + attribute.getName());
            } else {
                lore.add(4, ChatColor.RED + String.valueOf(roll) + "%" + ChatColor.GRAY + " " + attribute.getName());
            }
        } else if (attribute == AttributeID.HEALTH || attribute == AttributeID.JUMP_HEIGHT || attribute == AttributeID.ATTACK_SPEED) {
            lore.add(4, ChatColor.GREEN + "+" + roll + ChatColor.GRAY + " " + attribute.getName());
        } else {
            lore.add(4, ChatColor.GREEN + "+" + roll + "%" + ChatColor.GRAY + " " + attribute.getName());
        }
        if (attribute == AttributeID.HEALTH) {
            meta.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(NamespacedKey.fromString("aurum:generic_max_health"), (double)roll + this.getAttributeSum(Attribute.MAX_HEALTH, meta), AttributeModifier.Operation.ADD_NUMBER, this.parseMaterial(material)));
        } else if (attribute == AttributeID.WALK_SPEED) {
            double amount = (double)roll / 100.0;
            meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, new AttributeModifier(NamespacedKey.fromString("aurum:generic_movement_speed"), 0.1 * amount, AttributeModifier.Operation.ADD_NUMBER, this.parseMaterial(material)));
        }
        meta = this.addAttributeData(meta, attribute, roll);
        meta.setLore(lore);
        return meta;
    }

    public ItemMeta addRuneAbility(ItemMeta meta, Rune rune, List<String> lore, boolean isArmor) {
        if (rune == Rune.CHARGE) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Charge");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Right click to charge through the air.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Cooldown: 2 seconds");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Active");
        }
        if (rune == Rune.BLOOD_RUSH) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Blood Rush");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Right click to enter blood rush. While active,");
            lore.add(lore.size() - 2, ChatColor.AQUA + "every time you take damage, your attack damage is");
            lore.add(lore.size() - 2, ChatColor.AQUA + "increased to a max of 300% if at 1 HP. You cannot heal");
            lore.add(lore.size() - 2, ChatColor.AQUA + "while active. Right click again to exit.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Cooldown: 30 seconds");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Active");
        }
        if (rune == Rune.FIREBALL) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Fireball");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Right click to shoot a fireball towards your enemies.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Cooldown: 10 seconds");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Damage: 300% of Main Attack Damage");
        }
        if (rune == Rune.HEAL) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Heal");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Hold right click to heal yourself slowly.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Effect: 1.5 HP per second");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Active");
        }
        if (rune == Rune.SMITE) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Smite");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Right click to activate. For the next 5 seconds");
            lore.add(lore.size() - 2, ChatColor.AQUA + "every time you attack an enemy you will cause");
            lore.add(lore.size() - 2, ChatColor.AQUA + "a lightning bolt to strike your target.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Cooldown: 30 seconds");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Damage: 150% of Main Attack Damage");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Active");
        }
        if (rune == Rune.WIND_SLASH) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Wind Slash");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Right click to attack your enemies with");
            lore.add(lore.size() - 2, ChatColor.AQUA + "the speed of wind.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Cooldown: 5 seconds");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Damage: 140% of Main Attack Damage");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Active");
        }
        if (rune == Rune.RESURGENCE) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Resurgence");
            lore.add(lore.size() - 2, ChatColor.AQUA + "When you take lethal damage, your life will");
            lore.add(lore.size() - 2, ChatColor.AQUA + "be spared and you will regain some of your health.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Cooldown: 10 minutes");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Passive");
        }
        if (rune == Rune.SHOCK_WAVE) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Shock Wave");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Right click to push nearby enemies away from you.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Cooldown: 15 seconds");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Active");
        }
        if (rune == Rune.DISTORTION) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Distortion");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Right click to pull nearby enemies towards you.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Cooldown: 15 seconds");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Active");
        }
        if (rune == Rune.DRAGON_SKIN) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Dragon Skin");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Gain immunity to fire.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Passive");
        }
        if (rune == Rune.FISH_LUNG) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Fish Lung");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Gain immunity to drowning and speed underwater.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Passive");
        }
        if (rune == Rune.FALLING_STAR) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Falling Star");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Right click to cause a meteor to strike where you are aiming.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Cooldown: 20 seconds");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Damage: 200% of Main Attack Damage");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Active");
        }
        if (rune == Rune.DISPLACEMENT) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Displacement");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Right click to activate. For the next 10 seconds");
            lore.add(lore.size() - 2, ChatColor.AQUA + "every time you attack an enemy you will cause");
            lore.add(lore.size() - 2, ChatColor.AQUA + "you to switch places with your target.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Cooldown: 10 seconds");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Active");
        }
        if (rune == Rune.RIFT_STEP) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Rift Step");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Right click to charge through the air, but");
            lore.add(lore.size() - 2, ChatColor.AQUA + "doing this will cause you to lose 2 HP.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Active");
        }
        if (rune == Rune.SACRIFICE) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Sacrifice");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Every time you take damage, allies near you will");
            lore.add(lore.size() - 2, ChatColor.AQUA + "be healed by 50% of the damage taken. If there");
            lore.add(lore.size() - 2, ChatColor.AQUA + "are multiple allies near you, the amount healed");
            lore.add(lore.size() - 2, ChatColor.AQUA + "will be split.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Passive");
        }
        if (rune == Rune.TIME_LOCK) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Time Lock");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Right click to completely freeze all enemies");
            lore.add(lore.size() - 2, ChatColor.AQUA + "around you for 5 seconds.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Cooldown: 20 seconds");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Active");
        }
        if (rune == Rune.GOLD_PACT) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Gold Pact");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Increases the amount of loot you get from");
            lore.add(lore.size() - 2, ChatColor.AQUA + "treasure hoards but all damage you receive");
            lore.add(lore.size() - 2, ChatColor.AQUA + "is doubled.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Passive");
        }
        if (rune == Rune.LEECH_FOOT) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Leach Foot");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Right click to activate. Until you right click");
            lore.add(lore.size() - 2, ChatColor.AQUA + "again, your walk speed will be increased by");
            lore.add(lore.size() - 2, ChatColor.AQUA + "a very large amount but you will drain 4 HP");
            lore.add(lore.size() - 2, ChatColor.AQUA + "every second.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Active");
        }
        if (rune == Rune.SWIFTNESS) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Swiftness");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Gain a speed boost.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Passive");
        }
        if (rune == Rune.ARCANE_SHIELD) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Arcane Shield");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Gain an invisible shield that absorbs");
            lore.add(lore.size() - 2, ChatColor.AQUA + "20% of damage taken.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Passive");
        }
        if (rune == Rune.REGENERATION) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Regeneration");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Gain 1 HP every second.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Passive");
        }
        if (rune == Rune.GRACE) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Grace");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Gain the ability to glide through the air.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Passive");
        }
        if (rune == Rune.VITALITY) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Vitality");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Gain an extra 8 HP.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Passive");
        }
        if (rune == Rune.RESTORATION) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Restoration");
            lore.add(lore.size() - 2, ChatColor.AQUA + "If your health is below half, all health");
            lore.add(lore.size() - 2, ChatColor.AQUA + "you regain will be doubled.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Passive");
        }
        if (rune == Rune.AMOGUS) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Amogus");
        }
        if (rune == Rune.ARCANE_RAY) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Arcane Ray");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Right click to channel arcane energy into a ray.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Cooldown: 20 seconds");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Damage: 250% of Main Attack Damage");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Active");
        }
        if (rune == Rune.GROUND_SLAM) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Ground Slam");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Right click to launch yourself into the air and slam");
            lore.add(lore.size() - 2, ChatColor.AQUA + "back into the ground, damaging all nearby enemies.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Cooldown: 8 seconds");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Damage: 50% of Main Attack Damage");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Active");
        }
        if (rune == Rune.PIROUETTE) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Pirouette");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Right click to damage all nearby enemies with a");
            lore.add(lore.size() - 2, ChatColor.AQUA + "deadly spin attack.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Cooldown: 5 seconds");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Damage: 120% of Main Attack Damage");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Active");
        }
        if (rune == Rune.RITUAL) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Ritual");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Right click to create a healing circle on");
            lore.add(lore.size() - 2, ChatColor.AQUA + "the ground for 20 seconds.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Cooldown: 60 seconds");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Effect: 2 HP per second");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Active");
        }
        if (rune == Rune.FROZEN_SPARK) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Frozen Spark");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Right click shoot a ray of ice. The closer you");
            lore.add(lore.size() - 2, ChatColor.AQUA + "are the the enemy, the more damage you deal.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Cooldown: 10 seconds");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Damage: 70% to 280% of Main Attack Damage");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Active");
        }
        if (rune == Rune.TAUNT) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Taunt");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Right click cause all nearby enemies to");
            lore.add(lore.size() - 2, ChatColor.AQUA + "to target you.");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Cooldown: 15 seconds");
            lore.add(lore.size() - 2, ChatColor.AQUA + "Type: Active");
        }
        if (rune == Rune.EMPTY) {
            lore.add(lore.size() - 2, " ");
            lore.add(lore.size() - 2, ChatColor.DARK_GRAY + "Empty Rune Slot");
        }
        meta = this.addRuneData(meta, rune);
        if (isArmor && rune != Rune.EMPTY) {
            ArmorMeta armorMeta = (ArmorMeta)meta;
            armorMeta.setTrim(this.getRandomTrim(this.getRarity(lore.get(lore.size() - 1)), rune));
        }
        meta.setLore(lore);
        return meta;
    }

    private ArmorTrim getRandomTrim(Rarity rarity, Rune rune) {
        TrimMaterial trimMaterial = switch (rarity) {
            default -> throw new IncompatibleClassChangeError();
            case Rarity.COMMON -> TrimMaterial.LAPIS;
            case Rarity.RARE -> TrimMaterial.DIAMOND;
            case Rarity.EPIC -> TrimMaterial.AMETHYST;
            case Rarity.LEGENDARY -> TrimMaterial.REDSTONE;
            case Rarity.ARTIFACT -> TrimMaterial.GOLD;
            case Rarity.ELDRITCH -> TrimMaterial.EMERALD;
        };
        TrimPattern trimPattern = switch (rune) {
            case Rune.AMOGUS -> TrimPattern.RIB;
            case Rune.ARCANE_SHIELD -> TrimPattern.DUNE;
            case Rune.DRAGON_SKIN -> TrimPattern.RAISER;
            case Rune.FISH_LUNG -> TrimPattern.WAYFINDER;
            case Rune.GOLD_PACT -> TrimPattern.VEX;
            case Rune.GRACE -> TrimPattern.SNOUT;
            case Rune.SACRIFICE -> TrimPattern.SILENCE;
            case Rune.SWIFTNESS -> TrimPattern.SHAPER;
            case Rune.VITALITY -> TrimPattern.SENTRY;
            case Rune.REGENERATION -> TrimPattern.TIDE;
            case Rune.RESTORATION -> TrimPattern.WARD;
            case Rune.RESURGENCE -> TrimPattern.EYE;
            default -> TrimPattern.COAST;
        };
        return new ArmorTrim(trimMaterial, trimPattern);
    }

    private Rarity getRarity(String s) {
        if (s.equals(Rarity.RARE.getName())) {
            return Rarity.RARE;
        }
        if (s.equals(Rarity.EPIC.getName())) {
            return Rarity.EPIC;
        }
        if (s.equals(Rarity.LEGENDARY.getName())) {
            return Rarity.LEGENDARY;
        }
        if (s.equals(Rarity.ARTIFACT.getName())) {
            return Rarity.ARTIFACT;
        }
        if (s.equals(Rarity.ELDRITCH.getName())) {
            return Rarity.ELDRITCH;
        }
        return Rarity.COMMON;
    }

    public EquipmentSlotGroup parseItemType(ItemType itemType) {
        return switch (itemType) {
            case ItemType.HELMET -> EquipmentSlotGroup.HEAD;
            case ItemType.CHESTPLATE -> EquipmentSlotGroup.CHEST;
            case ItemType.LEGGINGS -> EquipmentSlotGroup.LEGS;
            case ItemType.BOOTS -> EquipmentSlotGroup.FEET;
            default -> EquipmentSlotGroup.MAINHAND;
        };
    }

    public EquipmentSlotGroup parseMaterial(Material material) {
        if (material == Material.CHAINMAIL_BOOTS || material == Material.LEATHER_BOOTS || material == Material.IRON_BOOTS || material == Material.GOLDEN_BOOTS || material == Material.DIAMOND_BOOTS || material == Material.NETHERITE_BOOTS) {
            return EquipmentSlotGroup.FEET;
        }
        if (material == Material.CHAINMAIL_LEGGINGS || material == Material.LEATHER_LEGGINGS || material == Material.IRON_LEGGINGS || material == Material.GOLDEN_LEGGINGS || material == Material.DIAMOND_LEGGINGS || material == Material.NETHERITE_LEGGINGS) {
            return EquipmentSlotGroup.LEGS;
        }
        if (material == Material.CHAINMAIL_CHESTPLATE || material == Material.LEATHER_CHESTPLATE || material == Material.IRON_CHESTPLATE || material == Material.GOLDEN_CHESTPLATE || material == Material.DIAMOND_CHESTPLATE || material == Material.NETHERITE_CHESTPLATE) {
            return EquipmentSlotGroup.CHEST;
        }
        if (material == Material.CHAINMAIL_HELMET || material == Material.LEATHER_HELMET || material == Material.IRON_HELMET || material == Material.GOLDEN_HELMET || material == Material.DIAMOND_HELMET || material == Material.NETHERITE_HELMET || material == Material.CARVED_PUMPKIN || material == Material.PLAYER_HEAD || material == Material.SKELETON_SKULL || material == Material.CREEPER_HEAD || material == Material.ZOMBIE_HEAD || material == Material.WITHER_SKELETON_SKULL) {
            return EquipmentSlotGroup.HEAD;
        }
        return EquipmentSlotGroup.MAINHAND;
    }

    public ItemMeta addConsumableEffect(ItemMeta meta, List<String> lore, String type, String displayName, int strength, int duration) {
        NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "consumableEffect");
        PersistentDataContainer container = meta.getPersistentDataContainer();
        String string = "";
        if (container.has(key, PersistentDataType.STRING)) {
            string = (String)container.get(key, PersistentDataType.STRING);
        }
        assert (string != null);
        string = string.isEmpty() ? type + "," + strength + "," + duration : string + "\u2395" + type + "," + strength + "," + duration;
        string = string.trim();
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, string);
        if (type.equalsIgnoreCase("precise_health")) {
            lore.add(4, ChatColor.YELLOW + "+" + strength + " Health");
        } else if (type.equalsIgnoreCase("precise_damage")) {
            lore.add(4, ChatColor.YELLOW + "-" + strength + " Health");
        } else if (strength == 1) {
            lore.add(4, ChatColor.YELLOW + this.capitalize(displayName) + " for " + duration + " seconds");
        } else {
            lore.add(4, ChatColor.YELLOW + this.capitalize(displayName) + " " + this.integerToRoman(strength) + " for " + duration + " seconds");
        }
        meta.setLore(lore);
        return meta;
    }

    private String integerToRoman(int num) {
        int[] values = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanLiterals = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length; ++i) {
            while (num >= values[i]) {
                num -= values[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman.toString();
    }

    public int makePositive(double d) {
        return (int)(d < 0.0 ? d * -1.0 : d);
    }

    public ItemMeta addAttributeData(@NotNull ItemMeta meta, AttributeID attribute, int roll) {
        meta.getPersistentDataContainer().set(AttributeKeys.getKey(attribute), PersistentDataType.INTEGER, roll);
        return meta;
    }

    public ItemMeta addRuneData(@NotNull ItemMeta meta, Rune rune) {
        Integer i;
        if (rune == Rune.EMPTY && (i = (Integer)meta.getPersistentDataContainer().get(RuneKeys.getKey(Rune.EMPTY), PersistentDataType.INTEGER)) != null) {
            meta.getPersistentDataContainer().set(RuneKeys.getKey(Rune.EMPTY), PersistentDataType.INTEGER, (i + 1));
            return meta;
        }
        meta.getPersistentDataContainer().set(RuneKeys.getKey(rune), PersistentDataType.INTEGER, 1);
        return meta;
    }

    public double getAttributeSum(Attribute attribute, ItemMeta meta) {
        Collection<AttributeModifier> c;
        assert (meta != null);
        double d = 0.0;
        if (meta.getAttributeModifiers() != null && (c = meta.getAttributeModifiers(attribute)) != null) {
            for (AttributeModifier attributeModifier : c) {
                d += attributeModifier.getAmount();
            }
        }
        return d;
    }
}
