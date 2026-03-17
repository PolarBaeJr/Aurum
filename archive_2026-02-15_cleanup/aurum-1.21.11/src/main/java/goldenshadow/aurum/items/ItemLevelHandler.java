/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.NamespacedKey
 *  org.bukkit.Sound
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataContainer
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 */
package goldenshadow.aurum.items;

import goldenshadow.aurum.Aurum;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class ItemLevelHandler {
    private static final Plugin plugin = Aurum.getPlugin();

    public static void armorLoop() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            for (ItemStack i : player.getInventory().getArmorContents()) {
                Integer minLevel;
                if (i == null || !i.hasItemMeta()) continue;
                NamespacedKey key = new NamespacedKey(plugin, "minLevel");
                ItemMeta meta = i.getItemMeta();
                assert (meta != null);
                PersistentDataContainer container = meta.getPersistentDataContainer();
                if (!container.has(key, PersistentDataType.INTEGER) || (minLevel = (Integer)container.get(key, PersistentDataType.INTEGER)) == null || minLevel <= player.getLevel()) continue;
                player.sendMessage(Component.text("[", NamedTextColor.RED)
                    .append(Component.text("!", NamedTextColor.YELLOW))
                    .append(Component.text("] ", NamedTextColor.RED))
                    .append(Component.text("You're not high enough level to use this!", NamedTextColor.DARK_RED)));
                player.playSound((Entity)player, Sound.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
                if (player.getInventory().firstEmpty() == -1) {
                    player.sendMessage(Component.empty());
                    player.sendMessage(Component.text("[", NamedTextColor.RED)
                        .append(Component.text("!", NamedTextColor.YELLOW))
                        .append(Component.text("] ", NamedTextColor.RED))
                        .append(Component.text("Item was dropped because your inventory was full!", NamedTextColor.DARK_RED)));
                    player.getWorld().dropItem(player.getLocation(), i);
                    i.setAmount(0);
                }
                if (player.getInventory().firstEmpty() == -1) continue;
                player.getInventory().addItem(new ItemStack[]{i});
                i.setAmount(0);
            }
        }
    }

    public static void onWeaponRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().hasItemMeta()) {
            Integer minLevel;
            ItemStack i = player.getInventory().getItemInMainHand();
            NamespacedKey key = new NamespacedKey(plugin, "minLevel");
            ItemMeta meta = i.getItemMeta();
            assert (meta != null);
            PersistentDataContainer container = meta.getPersistentDataContainer();
            if (container.has(key, PersistentDataType.INTEGER) && (minLevel = (Integer)container.get(key, PersistentDataType.INTEGER)) != null && minLevel > player.getLevel() && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                event.setCancelled(true);
                player.sendMessage(Component.text("[", NamedTextColor.RED)
                    .append(Component.text("!", NamedTextColor.YELLOW))
                    .append(Component.text("] ", NamedTextColor.RED))
                    .append(Component.text("You're not high enough level to use this!", NamedTextColor.DARK_RED)));
                player.playSound((Entity)player, Sound.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
            }
        }
    }
}

