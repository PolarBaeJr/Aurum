/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.NamespacedKey
 *  org.bukkit.Particle
 *  org.bukkit.Sound
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Slime
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.persistence.PersistentDataContainer
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 */
package goldenshadow.aurum.items;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.items.ItemHelper;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class PickupInteractionHandler {
    private static final Map<UUID, Map<UUID, Long>> itemCooldown = new HashMap<UUID, Map<UUID, Long>>();
    private static final ItemHelper itemHelper = new ItemHelper();

    public static void interact(Player player, Slime interaction) {
        assert (interaction.getEquipment() != null);
        if (interaction.getScoreboardTags().contains("aurum_pickup_interaction:single_use")) {
            NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "pickup_interaction_used");
            PersistentDataContainer container = interaction.getPersistentDataContainer();
            String value = "";
            if (container.has(key, PersistentDataType.STRING)) {
                value = (String)container.get(key, PersistentDataType.STRING);
            }
            assert (value != null);
            if (value.contains(player.getName())) {
                player.sendMessage(Component.text("This item can only be picked up once...", NamedTextColor.GRAY));
            } else {
                PickupInteractionHandler.giveItem(player, interaction.getEquipment().getHelmet());
                value = value.equals("") ? player.getName() : value + "," + player.getName();
                container.set(key, PersistentDataType.STRING, value);
            }
        } else if (interaction.getScoreboardTags().contains("aurum_pickup_interaction:unlimited_use")) {
            PickupInteractionHandler.giveItem(player, interaction.getEquipment().getHelmet());
        } else if (interaction.getScoreboardTags().contains("aurum_pickup_interaction:timed_use")) {
            Integer i;
            if (itemCooldown.containsKey(player.getUniqueId()) && itemCooldown.get(player.getUniqueId()).containsKey(interaction.getUniqueId()) && itemCooldown.get(player.getUniqueId()).get(interaction.getUniqueId()) > System.currentTimeMillis()) {
                player.sendMessage(Component.text("Wait ", NamedTextColor.GRAY)
                    .append(Component.text(itemHelper.parseCurrentMillis(itemCooldown.get(player.getUniqueId()).get(interaction.getUniqueId()) - System.currentTimeMillis()), NamedTextColor.GRAY))
                    .append(Component.text(" before picking up this item again.", NamedTextColor.GRAY)));
                return;
            }
            Map<UUID, Long> inner = new HashMap<>();
            if (itemCooldown.containsKey(player.getUniqueId())) {
                inner = itemCooldown.get(player.getUniqueId());
            }
            NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "aurum_pickup_interaction_time");
            PersistentDataContainer container = interaction.getPersistentDataContainer();
            if (container.has(key, PersistentDataType.INTEGER) && (i = (Integer)container.get(key, PersistentDataType.INTEGER)) != null) {
                inner.put(interaction.getUniqueId(), System.currentTimeMillis() + (long)(i * 1000));
            }
            PickupInteractionHandler.giveItem(player, interaction.getEquipment().getHelmet());
            itemCooldown.put(player.getUniqueId(), inner);
        }
    }

    public static void pickUpInteractionLoop() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getNearbyEntities(10.0, 10.0, 10.0).stream().filter(x -> x.getScoreboardTags().contains("aurum_pickup_interaction")).forEach(x -> x.getWorld().spawnParticle(Particle.WAX_OFF, x.getLocation().add(0.0, 0.5, 0.0), 10, 0.3, 0.3, 0.3));
        }
    }

    private static void giveItem(Player player, ItemStack itemStack) {
        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(Component.text("Your inventory is full...", NamedTextColor.GRAY));
        } else {
            player.getInventory().addItem(new ItemStack[]{itemStack});
            player.playSound((Entity)player, Sound.ENTITY_ITEM_PICKUP, 1.0f, 1.0f);
        }
    }

    public static void resetCooldown(UUID uuid) {
        itemCooldown.remove(uuid);
    }
}

