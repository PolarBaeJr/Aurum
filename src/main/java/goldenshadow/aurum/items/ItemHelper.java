/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataContainer
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 */
package goldenshadow.aurum.items;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.items.flags.AttributeID;
import goldenshadow.aurum.items.flags.AttributeKeys;
import goldenshadow.aurum.items.flags.Rune;
import goldenshadow.aurum.items.flags.RuneKeys;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class ItemHelper {
    public boolean hasAttribute(ItemMeta meta, AttributeID attributeID) {
        return meta.getPersistentDataContainer().has(AttributeKeys.getKey(attributeID), PersistentDataType.INTEGER);
    }

    public String parseCurrentMillis(double timeMillis) {
        double totalSeconds = timeMillis / 1000.0;
        int hours = (int)(totalSeconds / 3600.0);
        int minutes = (int)(totalSeconds % 3600.0 / 60.0);
        int seconds = (int)(totalSeconds % 60.0);
        StringBuilder sb = new StringBuilder();
        if (hours > 0) {
            sb.append(hours).append(" hour");
            if (hours > 1) {
                sb.append("s");
            }
            sb.append(", ");
        }
        if (minutes > 0) {
            sb.append(minutes).append(" minute");
            if (minutes > 1) {
                sb.append("s");
            }
            sb.append(" and ");
        }
        sb.append(seconds).append(" second");
        if (seconds > 1) {
            sb.append("s");
        }
        return sb.toString();
    }

    public double getAttributeRoll(Player player, AttributeID attribute, boolean asPercentage) {
        double amount = 0.0;
        if (player.getInventory().getItemInMainHand().getItemMeta() != null) {
            Integer level = (Integer)player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(AttributeKeys.getKey(attribute), PersistentDataType.INTEGER);
            level = level != null ? level : 0;
            amount += (double)level.intValue();
        }
        for (ItemStack i : player.getInventory().getArmorContents()) {
            if (i == null || i.getItemMeta() == null) continue;
            Integer level = (Integer)i.getItemMeta().getPersistentDataContainer().get(AttributeKeys.getKey(attribute), PersistentDataType.INTEGER);
            level = level != null ? level : 0;
            amount += (double)level.intValue();
        }
        if (asPercentage) {
            amount = 1.0 + amount / 100.0;
        }
        return amount;
    }

    public boolean isAttributeEquipped(Player player, AttributeID attribute) {
        if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(AttributeKeys.getKey(attribute), PersistentDataType.INTEGER) && this.isNotArmor(player.getInventory().getItemInMainHand()) && this.isHighEnoughLevel(player, player.getInventory().getItemInMainHand()) && player.getInventory().getItemInMainHand().getType() != Material.CLAY_BALL) {
            return true;
        }
        for (ItemStack i : player.getInventory().getArmorContents()) {
            if (i == null || i.getItemMeta() == null || !i.getItemMeta().getPersistentDataContainer().has(AttributeKeys.getKey(attribute), PersistentDataType.INTEGER) || !this.isHighEnoughLevel(player, i)) continue;
            return true;
        }
        return false;
    }

    public boolean isRuneEquipped(Player player, Rune rune) {
        if (player.getInventory().getItemInMainHand().getItemMeta() != null && player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(RuneKeys.getKey(rune), PersistentDataType.INTEGER) && this.isNotArmor(player.getInventory().getItemInMainHand()) && this.isHighEnoughLevel(player, player.getInventory().getItemInMainHand()) && player.getInventory().getItemInMainHand().getType() != Material.CLAY_BALL) {
            return true;
        }
        for (ItemStack i : player.getInventory().getArmorContents()) {
            if (i == null || i.getItemMeta() == null || !i.getItemMeta().getPersistentDataContainer().has(RuneKeys.getKey(rune), PersistentDataType.INTEGER) || !this.isHighEnoughLevel(player, i)) continue;
            return true;
        }
        return false;
    }

    public boolean isAttackablePlayer(Entity entity) {
        Player p;
        if (entity instanceof Player && !(p = (Player)entity).isInvulnerable()) {
            return Aurum.getPlugin().getConfig().getBoolean("pvp");
        }
        return false;
    }

    public boolean isHighEnoughLevel(Player player, ItemStack itemStack) {
        if (itemStack.getItemMeta() != null) {
            NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "minLevel");
            ItemMeta meta = itemStack.getItemMeta();
            assert (meta != null);
            PersistentDataContainer container = meta.getPersistentDataContainer();
            if (container.has(key, PersistentDataType.INTEGER)) {
                Integer minLevel = (Integer)container.get(key, PersistentDataType.INTEGER);
                if (minLevel == null) {
                    return true;
                }
                return minLevel <= player.getLevel();
            }
            return true;
        }
        return true;
    }

    public boolean isNotArmor(ItemStack itemStack) {
        return itemStack.getType() != Material.LEATHER_BOOTS && itemStack.getType() != Material.LEATHER_LEGGINGS && itemStack.getType() != Material.LEATHER_CHESTPLATE && itemStack.getType() != Material.LEATHER_HELMET && itemStack.getType() != Material.CHAINMAIL_BOOTS && itemStack.getType() != Material.CHAINMAIL_LEGGINGS && itemStack.getType() != Material.CHAINMAIL_CHESTPLATE && itemStack.getType() != Material.CHAINMAIL_HELMET && itemStack.getType() != Material.GOLDEN_BOOTS && itemStack.getType() != Material.GOLDEN_LEGGINGS && itemStack.getType() != Material.GOLDEN_CHESTPLATE && itemStack.getType() != Material.GOLDEN_HELMET && itemStack.getType() != Material.IRON_BOOTS && itemStack.getType() != Material.IRON_LEGGINGS && itemStack.getType() != Material.IRON_CHESTPLATE && itemStack.getType() != Material.IRON_HELMET && itemStack.getType() != Material.DIAMOND_BOOTS && itemStack.getType() != Material.DIAMOND_LEGGINGS && itemStack.getType() != Material.DIAMOND_CHESTPLATE && itemStack.getType() != Material.DIAMOND_HELMET && itemStack.getType() != Material.NETHERITE_BOOTS && itemStack.getType() != Material.NETHERITE_LEGGINGS && itemStack.getType() != Material.NETHERITE_CHESTPLATE && itemStack.getType() != Material.NETHERITE_HELMET && itemStack.getType() != Material.CARVED_PUMPKIN && itemStack.getType() != Material.DRAGON_HEAD;
    }

    public double getSpellDamageMultiplier(Player player) {
        if (this.isAttributeEquipped(player, AttributeID.RUNE_DAMAGE)) {
            return this.getAttributeRoll(player, AttributeID.RUNE_DAMAGE, true);
        }
        return 1.0;
    }

    public double getCooldownReduction(Player player) {
        if (this.isAttributeEquipped(player, AttributeID.RUNE_AFFINITY)) {
            double raw = this.getAttributeRoll(player, AttributeID.RUNE_AFFINITY, false);
            if (raw <= 100.0) {
                return 1.0 - raw * 0.5 / 100.0;
            }
            return Math.max(0.25, 0.5 - (raw - 100.0) * 0.1 / 100.0);
        }
        return 1.0;
    }
}

