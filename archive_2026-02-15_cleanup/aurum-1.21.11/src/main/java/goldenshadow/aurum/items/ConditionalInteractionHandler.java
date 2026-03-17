/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.Particle
 *  org.bukkit.command.CommandSender
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
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class ConditionalInteractionHandler {
    private static final Map<UUID, Map<UUID, Long>> eventCooldown = new HashMap<UUID, Map<UUID, Long>>();
    private static final ItemHelper itemHelper = new ItemHelper();

    public static void interact(Player player, Slime interaction) {
        assert (interaction.getEquipment() != null);
        if (interaction.getScoreboardTags().contains("aurum_conditional_interaction:single_use")) {
            NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "event_conditional_used");
            PersistentDataContainer container = interaction.getPersistentDataContainer();
            String value = "";
            if (container.has(key, PersistentDataType.STRING)) {
                value = (String)container.get(key, PersistentDataType.STRING);
            }
            assert (value != null);
            if (value.contains(player.getName())) {
                player.sendMessage(ChatColor.GRAY + "You can only do this once...");
            } else {
                ItemStack requiredItem = interaction.getEquipment().getHelmet();
                assert (requiredItem != null);
                if (ConditionalInteractionHandler.doesPlayerHaveRequiredItem(player.getInventory().getItemInMainHand(), requiredItem)) {
                    ConditionalInteractionHandler.triggerEvent(player, interaction.getPersistentDataContainer(), requiredItem.getAmount());
                    value = value.equals("") ? player.getName() : value + "," + player.getName();
                    container.set(key, PersistentDataType.STRING, value);
                } else {
                    assert (requiredItem.getItemMeta() != null);
                    player.sendMessage(ChatColor.GRAY + "Item required: " + requiredItem.getItemMeta().getDisplayName());
                }
            }
        } else if (interaction.getScoreboardTags().contains("aurum_conditional_interaction:unlimited_use")) {
            ItemStack requiredItem = interaction.getEquipment().getHelmet();
            assert (requiredItem != null);
            if (ConditionalInteractionHandler.doesPlayerHaveRequiredItem(player.getInventory().getItemInMainHand(), requiredItem)) {
                ConditionalInteractionHandler.triggerEvent(player, interaction.getPersistentDataContainer(), requiredItem.getAmount());
            } else {
                assert (requiredItem.getItemMeta() != null);
                player.sendMessage(ChatColor.GRAY + "Item required: " + requiredItem.getItemMeta().getDisplayName());
            }
        } else if (interaction.getScoreboardTags().contains("aurum_conditional_interaction:timed_use")) {
            if (eventCooldown.containsKey(player.getUniqueId()) && eventCooldown.get(player.getUniqueId()).containsKey(interaction.getUniqueId()) && eventCooldown.get(player.getUniqueId()).get(interaction.getUniqueId()) > System.currentTimeMillis()) {
                player.sendMessage(ChatColor.GRAY + "Wait " + itemHelper.parseCurrentMillis(eventCooldown.get(player.getUniqueId()).get(interaction.getUniqueId()) - System.currentTimeMillis()) + " before doing this again.");
                return;
            }
            Map<UUID, Long> inner = new HashMap<>();
            if (eventCooldown.containsKey(player.getUniqueId())) {
                inner = eventCooldown.get(player.getUniqueId());
            }
            NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "aurum_conditional_interaction_time");
            PersistentDataContainer container = interaction.getPersistentDataContainer();
            ItemStack requiredItem = interaction.getEquipment().getHelmet();
            assert (requiredItem != null);
            if (ConditionalInteractionHandler.doesPlayerHaveRequiredItem(player.getInventory().getItemInMainHand(), requiredItem)) {
                if (container.has(key, PersistentDataType.INTEGER)) {
                    Integer i = (Integer)container.get(key, PersistentDataType.INTEGER);
                    assert (i != null);
                    inner.put(interaction.getUniqueId(), System.currentTimeMillis() + (long)(i * 1000));
                }
                ConditionalInteractionHandler.triggerEvent(player, interaction.getPersistentDataContainer(), requiredItem.getAmount());
                eventCooldown.put(player.getUniqueId(), inner);
            } else {
                assert (requiredItem.getItemMeta() != null);
                player.sendMessage(ChatColor.GRAY + "Item required: " + requiredItem.getItemMeta().getDisplayName());
            }
        }
    }

    public static void eventInteractionLoop() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getNearbyEntities(10.0, 10.0, 10.0).stream().filter(x -> x.getScoreboardTags().contains("aurum_conditional_interaction")).forEach(x -> x.getWorld().spawnParticle(Particle.SCRAPE, x.getLocation().add(0.0, 0.5, 0.0), 10, 0.3, 0.3, 0.3));
        }
    }

    private static void triggerEvent(Player player, PersistentDataContainer container, int itemCount) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack.getAmount() > itemCount) {
            itemStack.setAmount(itemStack.getAmount() - itemCount);
        } else {
            itemStack.setType(Material.AIR);
        }
        player.getInventory().setItemInMainHand(itemStack);
        String command = (String)container.get(new NamespacedKey((Plugin)Aurum.getPlugin(), "aurum_conditional_interaction_command"), PersistentDataType.STRING);
        if (command != null) {
            command = command.replaceFirst("@s", player.getName());
            Aurum.getPlugin().getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), command);
        }
    }

    public static void resetCooldown(UUID uuid) {
        eventCooldown.remove(uuid);
    }

    private static boolean doesPlayerHaveRequiredItem(ItemStack playerHandItem, ItemStack requiredItem) {
        if (playerHandItem.getType() == requiredItem.getType() && playerHandItem.getItemMeta() != null && requiredItem.getItemMeta() != null && playerHandItem.getItemMeta().equals(requiredItem.getItemMeta())) {
            return playerHandItem.getAmount() >= requiredItem.getAmount();
        }
        return false;
    }
}

