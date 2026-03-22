/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.adventure.text.Component
 *  net.kyori.adventure.text.TextComponent
 *  net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.Registry
 *  org.bukkit.Sound
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataContainer
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package goldenshadow.aurum.items;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.items.ItemHelper;
import goldenshadow.aurum.items.flags.Rune;
import java.util.Objects;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ConsumableHandler {
    private static final ItemHelper itemHelper = new ItemHelper();

    public static void rightClick(Player player) {
        ItemStack item;
        if (itemHelper.isHighEnoughLevel(player, player.getInventory().getItemInMainHand()) && (item = player.getInventory().getItemInMainHand()).getItemMeta() != null) {
            ItemMeta meta = item.getItemMeta();
            NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "consumableEffect");
            NamespacedKey usesKey = new NamespacedKey((Plugin)Aurum.getPlugin(), "consumableUses");
            PersistentDataContainer container = meta.getPersistentDataContainer();
            String data = (String)container.get(key, PersistentDataType.STRING);
            if (container.has(usesKey, PersistentDataType.INTEGER)) {
                Integer uses = (Integer)container.get(usesKey, PersistentDataType.INTEGER);
                assert (uses != null);
                if (data != null) {
                    String[] upper = data.split("\u2395");
                    String[][] parsedData = new String[upper.length][];
                    for (int i = 0; i < parsedData.length; ++i) {
                        parsedData[i] = upper[i].split(",");
                    }
                    for (String[] parsedDatum : parsedData) {
                        if (Registry.EFFECT.get(NamespacedKey.minecraft((String)parsedDatum[0].toLowerCase())) != null) {
                            int duration = Integer.parseInt(parsedDatum[2]) * 20;
                            PotionEffectType potionEffectType = (PotionEffectType)Registry.EFFECT.get(NamespacedKey.minecraft((String)parsedDatum[0].toLowerCase()));
                            assert (potionEffectType != null);
                            player.addPotionEffect(new PotionEffect(potionEffectType, duration, Integer.parseInt(parsedDatum[1]) - 1, false, false, true));
                            continue;
                        }
                        if (parsedDatum[0].equalsIgnoreCase("PRECISE_HEALTH")) {
                            int amount = Integer.parseInt(parsedDatum[1]);
                            if (itemHelper.isRuneEquipped(player, Rune.RESTORATION) && player.getHealth() < Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue() / 2.0) {
                                amount *= 2;
                            }
                            player.setHealth(Math.min(player.getHealth() + (double)amount, Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue()));
                        }
                        if (!parsedDatum[0].equalsIgnoreCase("PRECISE_DAMAGE")) continue;
                        player.damage((double)Integer.parseInt(parsedDatum[1]), (Entity)player);
                    }
                    player.playSound((Entity)player, Sound.ENTITY_PLAYER_BURP, 1.0f, 1.0f);
                    if (uses > 1) {
                        Component name = meta.displayName();
                        name = ConsumableHandler.consumeUse(name, uses - 1);
                        meta.displayName(name);
                        container.set(usesKey, PersistentDataType.INTEGER,(uses - 1));
                        item.setItemMeta(meta);
                        player.getInventory().setItemInMainHand(item);
                    } else if (item.getAmount() == 1) {
                        player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                    } else {
                        item.setAmount(item.getAmount() - 1);
                        player.getInventory().setItemInMainHand(item);
                    }
                }
            }
        }
    }

    private static Component consumeUse(Component input, int newNumber) {
        String plainText = PlainTextComponentSerializer.plainText().serialize(input);
        String regex = "\\[(\\d+)/";
        String replacement = "[" + newNumber + "/";
        String updated = plainText.replaceFirst(regex, replacement);
        return ((TextComponent)Component.text((String)updated).color(input.color())).decorations(input.decorations());
    }
}

