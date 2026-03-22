/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.adventure.text.Component
 *  net.kyori.adventure.text.format.NamedTextColor
 *  net.kyori.adventure.text.format.TextColor
 *  net.kyori.adventure.text.format.TextDecoration
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeModifier
 *  org.bukkit.attribute.AttributeModifier$Operation
 *  org.bukkit.inventory.EquipmentSlotGroup
 *  org.bukkit.inventory.meta.ArmorMeta
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.inventory.meta.trim.ArmorTrim
 *  org.bukkit.inventory.meta.trim.TrimMaterial
 *  org.bukkit.inventory.meta.trim.TrimPattern
 *  org.bukkit.persistence.PersistentDataContainer
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 *  org.jetbrains.annotations.NotNull
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
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
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
            case LEGENDARY: 
            case ARTIFACT: 
            case ELDRITCH: {
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
            case LEGENDARY: 
            case ARTIFACT: 
            case ELDRITCH: {
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

    public ItemMeta addAttribute(ItemMeta meta, int roll, AttributeID attribute, List<Component> lore, ItemType itemType) {
        if (roll < 0) {
            if (attribute == AttributeID.HEALTH || attribute == AttributeID.JUMP_HEIGHT || attribute == AttributeID.ATTACK_SPEED) {
                lore.add(Component.text((String)String.valueOf(roll), (TextColor)NamedTextColor.RED).append((Component)Component.text((String)(" " + attribute.getName()), (TextColor)NamedTextColor.GRAY)));
            } else {
                lore.add(Component.text((String)(String.valueOf(roll) + "%"), (TextColor)NamedTextColor.RED).append((Component)Component.text((String)(" " + attribute.getName()), (TextColor)NamedTextColor.GRAY)));
            }
        } else if (attribute == AttributeID.HEALTH || attribute == AttributeID.JUMP_HEIGHT || attribute == AttributeID.ATTACK_SPEED) {
            lore.add(Component.text((String)("+" + roll), (TextColor)NamedTextColor.GREEN).append((Component)Component.text((String)(" " + attribute.getName()), (TextColor)NamedTextColor.GRAY)));
        } else {
            lore.add(Component.text((String)("+" + roll + "%"), (TextColor)NamedTextColor.GREEN).append((Component)Component.text((String)(" " + attribute.getName()), (TextColor)NamedTextColor.GRAY)));
        }
        if (attribute == AttributeID.HEALTH) {
            meta.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(NamespacedKey.fromString((String)"aurum:generic_max_health"), (double)roll + this.getAttributeSum(Attribute.MAX_HEALTH, meta), AttributeModifier.Operation.ADD_NUMBER, this.parseItemType(itemType)));
        } else if (attribute == AttributeID.WALK_SPEED) {
            double amount = (double)roll / 100.0;
            meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, new AttributeModifier(NamespacedKey.fromString((String)"aurum:generic_movement_speed"), 0.1 * amount, AttributeModifier.Operation.ADD_NUMBER, this.parseItemType(itemType)));
        }
        meta = this.addAttributeData(meta, attribute, roll);
        meta.lore(lore);
        return meta;
    }

    public ItemMeta insertAttribute(ItemMeta meta, int roll, AttributeID attribute, List<Component> lore, Material material) {
        if (roll < 0) {
            if (attribute == AttributeID.HEALTH || attribute == AttributeID.JUMP_HEIGHT || attribute == AttributeID.ATTACK_SPEED) {
                lore.add(4, Component.text((String)String.valueOf(roll), (TextColor)NamedTextColor.RED).append((Component)Component.text((String)(" " + attribute.getName()), (TextColor)NamedTextColor.GRAY)));
            } else {
                lore.add(4, Component.text((String)(String.valueOf(roll) + "%"), (TextColor)NamedTextColor.RED).append((Component)Component.text((String)(" " + attribute.getName()), (TextColor)NamedTextColor.GRAY)));
            }
        } else if (attribute == AttributeID.HEALTH || attribute == AttributeID.JUMP_HEIGHT || attribute == AttributeID.ATTACK_SPEED) {
            lore.add(4, Component.text((String)("+" + roll), (TextColor)NamedTextColor.GREEN).append((Component)Component.text((String)(" " + attribute.getName()), (TextColor)NamedTextColor.GRAY)));
        } else {
            lore.add(4, Component.text((String)("+" + roll + "%"), (TextColor)NamedTextColor.GREEN).append((Component)Component.text((String)(" " + attribute.getName()), (TextColor)NamedTextColor.GRAY)));
        }
        if (attribute == AttributeID.HEALTH) {
            meta.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(NamespacedKey.fromString((String)"aurum:generic_max_health"), (double)roll + this.getAttributeSum(Attribute.MAX_HEALTH, meta), AttributeModifier.Operation.ADD_NUMBER, this.parseMaterial(material)));
        } else if (attribute == AttributeID.WALK_SPEED) {
            double amount = (double)roll / 100.0;
            meta.addAttributeModifier(Attribute.MOVEMENT_SPEED, new AttributeModifier(NamespacedKey.fromString((String)"aurum:generic_movement_speed"), 0.1 * amount, AttributeModifier.Operation.ADD_NUMBER, this.parseMaterial(material)));
        }
        meta = this.addAttributeData(meta, attribute, roll);
        meta.lore(lore);
        return meta;
    }

    public ItemMeta addRuneAbility(ItemMeta meta, Rune rune, List<Component> lore, boolean isArmor) {
        if (rune == Rune.CHARGE) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Charge", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Right click to charge through the air.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Cooldown: 2 seconds", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Active", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.BLOOD_RUSH) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Blood Rush", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Right click to enter blood rush. While active,", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"every time you take damage, your attack damage is", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"increased to a max of 300% if at 1 HP. You cannot heal", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"while active. Right click again to exit.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Cooldown: 30 seconds", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Active", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.FIREBALL) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Fireball", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Right click to shoot a fireball towards your enemies.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Cooldown: 10 seconds", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Damage: 300% of Main Attack Damage", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.HEAL) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Heal", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Hold right click to heal yourself slowly.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Effect: 1.5 HP per second", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Active", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.SMITE) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Smite", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Right click to activate. For the next 5 seconds", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"every time you attack an enemy you will cause", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"a lightning bolt to strike your target.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Cooldown: 30 seconds", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Damage: 150% of Main Attack Damage", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Active", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.WIND_SLASH) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Wind Slash", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Right click to attack your enemies with", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"the speed of wind.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Cooldown: 5 seconds", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Damage: 140% of Main Attack Damage", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Active", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.RESURGENCE) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Resurgence", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"When you take lethal damage, your life will", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"be spared and you will regain some of your health.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Cooldown: 10 minutes", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Passive", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.SHOCK_WAVE) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Shock Wave", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Right click to push nearby enemies away from you.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Cooldown: 15 seconds", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Active", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.DISTORTION) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Distortion", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Right click to pull nearby enemies towards you.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Cooldown: 15 seconds", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Active", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.DRAGON_SKIN) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Dragon Skin", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Gain immunity to fire.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Passive", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.FISH_LUNG) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Fish Lung", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Gain immunity to drowning and speed underwater.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Passive", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.FALLING_STAR) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Falling Star", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Right click to cause a meteor to strike where you are aiming.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Cooldown: 20 seconds", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Damage: 200% of Main Attack Damage", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Active", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.DISPLACEMENT) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Displacement", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Right click to activate. For the next 10 seconds", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"every time you attack an enemy you will cause", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"you to switch places with your target.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Cooldown: 10 seconds", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Active", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.RIFT_STEP) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Rift Step", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Right click to charge through the air, but", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"doing this will cause you to lose 2 HP.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Active", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.SACRIFICE) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Sacrifice", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Every time you take damage, allies near you will", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"be healed by 50% of the damage taken. If there", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"are multiple allies near you, the amount healed", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"will be split.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Passive", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.TIME_LOCK) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Time Lock", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Right click to completely freeze all enemies", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"around you for 5 seconds.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Cooldown: 20 seconds", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Active", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.GOLD_PACT) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Gold Pact", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Increases the amount of loot you get from", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"treasure hoards but all damage you receive", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"is doubled.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Passive", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.LEECH_FOOT) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Leach Foot", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Right click to activate. Until you right click", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"again, your walk speed will be increased by", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"a very large amount but you will drain 4 HP", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"every second.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Active", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.SWIFTNESS) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Swiftness", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Gain a speed boost.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Passive", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.ARCANE_SHIELD) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Arcane Shield", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Gain an invisible shield that absorbs", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"20% of damage taken.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Passive", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.REGENERATION) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Regeneration", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Gain 1 HP every second.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Passive", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.GRACE) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Grace", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Gain the ability to glide through the air.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Passive", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.VITALITY) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Vitality", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Gain an extra 8 HP.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Passive", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.RESTORATION) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Restoration", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"If your health is below half, all health", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"you regain will be doubled.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Passive", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.AMOGUS) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Amogus", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
        }
        if (rune == Rune.ARCANE_RAY) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Arcane Ray", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Right click to channel arcane energy into a ray.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Cooldown: 20 seconds", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Damage: 250% of Main Attack Damage", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Active", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.GROUND_SLAM) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Ground Slam", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Right click to launch yourself into the air and slam", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"back into the ground, damaging all nearby enemies.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Cooldown: 8 seconds", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Damage: 50% of Main Attack Damage", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Active", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.PIROUETTE) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Pirouette", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Right click to damage all nearby enemies with a", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"deadly spin attack.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Cooldown: 5 seconds", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Damage: 120% of Main Attack Damage", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Active", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.RITUAL) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Ritual", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Right click to create a healing circle on", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"the ground for 20 seconds.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Cooldown: 60 seconds", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Effect: 2 HP per second", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Active", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.FROZEN_SPARK) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Frozen Spark", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Right click shoot a ray of ice. The closer you", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"are the the enemy, the more damage you deal.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Cooldown: 10 seconds", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Damage: 70% to 280% of Main Attack Damage", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Active", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.TAUNT) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, Component.text((String)"Taunt", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Right click cause all nearby enemies to", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"to target you.", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Cooldown: 15 seconds", (TextColor)NamedTextColor.AQUA));
            lore.add(lore.size() - 2, (Component)Component.text((String)"Type: Active", (TextColor)NamedTextColor.AQUA));
        }
        if (rune == Rune.EMPTY) {
            lore.add(lore.size() - 2, (Component)Component.empty());
            lore.add(lore.size() - 2, (Component)Component.text((String)"Empty Rune Slot", (TextColor)NamedTextColor.DARK_GRAY));
        }
        meta = this.addRuneData(meta, rune);
        if (isArmor && rune != Rune.EMPTY) {
            ArmorMeta armorMeta = (ArmorMeta)meta;
            armorMeta.setTrim(this.getRandomTrim(this.getRarity(lore.get(lore.size() - 1)), rune));
        }
        meta.lore(lore);
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

    private Rarity getRarity(Component c) {
        if (c.equals((Object)Rarity.RARE.getName())) {
            return Rarity.RARE;
        }
        if (c.equals((Object)Rarity.EPIC.getName())) {
            return Rarity.EPIC;
        }
        if (c.equals((Object)Rarity.LEGENDARY.getName())) {
            return Rarity.LEGENDARY;
        }
        if (c.equals((Object)Rarity.ARTIFACT.getName())) {
            return Rarity.ARTIFACT;
        }
        if (c.equals((Object)Rarity.ELDRITCH.getName())) {
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

    public ItemMeta addConsumableEffect(ItemMeta meta, List<Component> lore, String type, String displayName, int strength, int duration) {
        NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "consumableEffect");
        PersistentDataContainer container = meta.getPersistentDataContainer();
        String string = "";
        if (container.has(key, PersistentDataType.STRING)) {
            string = (String)container.get(key, PersistentDataType.STRING);
        }
        assert (string != null);
        string = ((String)string).isEmpty() ? type + "," + strength + "," + duration : (String)string + "\u2395" + type + "," + strength + "," + duration;
        string = ((String)string).trim();
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, string);
        if (type.equalsIgnoreCase("precise_health")) {
            lore.add(4, (Component)Component.text((String)("+" + strength + " Health"), (TextColor)NamedTextColor.YELLOW));
        } else if (type.equalsIgnoreCase("precise_damage")) {
            lore.add(4, (Component)Component.text((String)("-" + strength + " Health"), (TextColor)NamedTextColor.YELLOW));
        } else if (strength == 1) {
            lore.add(4, (Component)Component.text((String)(this.capitalize(displayName) + " for " + duration + " seconds"), (TextColor)NamedTextColor.YELLOW));
        } else {
            lore.add(4, (Component)Component.text((String)(this.capitalize(displayName) + " " + this.integerToRoman(strength) + " for " + duration + " seconds"), (TextColor)NamedTextColor.YELLOW));
        }
        meta.lore(lore);
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
        meta.getPersistentDataContainer().set(AttributeKeys.getKey(attribute), PersistentDataType.INTEGER,roll);
        return meta;
    }

    public ItemMeta addRuneData(@NotNull ItemMeta meta, Rune rune) {
        Integer i;
        if (rune == Rune.EMPTY && (i = (Integer)meta.getPersistentDataContainer().get(RuneKeys.getKey(Rune.EMPTY), PersistentDataType.INTEGER)) != null) {
            meta.getPersistentDataContainer().set(RuneKeys.getKey(Rune.EMPTY), PersistentDataType.INTEGER,(i + 1));
            return meta;
        }
        meta.getPersistentDataContainer().set(RuneKeys.getKey(rune), PersistentDataType.INTEGER,1);
        return meta;
    }

    public double getAttributeSum(Attribute attribute, ItemMeta meta) {
        Collection c;
        assert (meta != null);
        double d = 0.0;
        if (meta.getAttributeModifiers() != null && (c = meta.getAttributeModifiers(attribute)) != null) {
            for (AttributeModifier attributeModifier : (Collection<AttributeModifier>) c) {
                d += attributeModifier.getAmount();
            }
        }
        return d;
    }
}

