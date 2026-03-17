/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.NamespacedKey
 *  org.bukkit.enchantments.Enchantment
 *  org.bukkit.enchantments.EnchantmentTarget
 *  org.bukkit.inventory.ItemStack
 */
package goldenshadow.aurum.enchants;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class EnchantmentWrapper
extends Enchantment {
    private final String name;
    private final int maxLevel;

    public EnchantmentWrapper(String namespace, String name, int level) {
        super(NamespacedKey.minecraft((String)namespace));
        this.name = name;
        this.maxLevel = level;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public int getMaxLevel() {
        return this.maxLevel;
    }

    public int getStartLevel() {
        return 0;
    }

    @NotNull
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ALL;
    }

    public boolean isTreasure() {
        return false;
    }

    public boolean isCursed() {
        return false;
    }

    public boolean conflictsWith(@NotNull Enchantment enchantment) {
        return false;
    }

    public boolean canEnchantItem(@NotNull ItemStack itemStack) {
        return true;
    }
}

