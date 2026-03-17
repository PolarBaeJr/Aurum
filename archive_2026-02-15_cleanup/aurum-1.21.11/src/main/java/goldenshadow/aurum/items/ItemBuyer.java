/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.Sound
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.WanderingTrader
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.event.inventory.InventoryType
 *  org.bukkit.event.player.PlayerInteractEntityEvent
 *  org.bukkit.inventory.EquipmentSlot
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 */
package goldenshadow.aurum.items;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.items.ItemFactory;
import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class ItemBuyer {
    private static final ItemFactory itemFactory = new ItemFactory();

    public static Inventory createInventory() {
        Inventory inventory = Bukkit.createInventory(null, InventoryType.CHEST, Component.text("Item Hoarder").decorate(TextDecoration.BOLD));
        ItemStack item = new ItemStack(Material.GLASS);
        ItemMeta meta = item.getItemMeta();
        List<Component> lore = new ArrayList<>();
        for (int i = 0; i < 27; ++i) {
            assert (meta != null);
            meta.displayName(Component.text(" "));
            if (i == 5 || i == 7 || i >= 14 && i <= 16 || i == 23 || i == 25) {
                item.setType(Material.GRAY_STAINED_GLASS_PANE);
            } else if (i == 8 || i == 17 || i == 26) {
                item.setType(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
            } else if (i == 6) {
                item.setType(Material.LIME_STAINED_GLASS_PANE);
                meta.displayName(Component.text("Confirm", NamedTextColor.GREEN).decorate(TextDecoration.BOLD));
                lore.add(Component.text("Click here to sell your items!", NamedTextColor.GRAY));
            } else {
                if (i != 24) continue;
                item.setType(Material.RED_STAINED_GLASS_PANE);
                meta.displayName(Component.text("Cancel", NamedTextColor.RED).decorate(TextDecoration.BOLD));
                lore.add(Component.text("Click here to exit!", NamedTextColor.GRAY));
            }
            meta.lore(lore);
            item.setItemMeta(meta);
            inventory.setItem(i, item);
            lore.clear();
        }
        return inventory;
    }

    public static void spawnNPC(Location location) {
        assert (location.getWorld() != null);
        WanderingTrader npc = (WanderingTrader)location.getWorld().spawnEntity(location, EntityType.WANDERING_TRADER);
        npc.setAI(false);
        npc.setInvulnerable(true);
        npc.addScoreboardTag("aurum_item_buyer");
        npc.setRemoveWhenFarAway(false);
        npc.setDespawnDelay(0);
    }

    public static void interact(PlayerInteractEntityEvent event) {
        if (event.getHand() == EquipmentSlot.HAND && event.getRightClicked().getScoreboardTags().contains("aurum_item_buyer")) {
            event.setCancelled(true);
            event.getPlayer().openInventory(ItemBuyer.createInventory());
        }
    }

    public static void invClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        if (event.getCurrentItem() != null) {
            if (event.getClickedInventory() == player.getInventory()) {
                if (event.getCurrentItem().getItemMeta() != null && event.getCurrentItem().getItemMeta().lore() != null && event.getCurrentItem().getItemMeta().lore().stream().anyMatch(x -> PlainTextComponentSerializer.plainText().serialize(x).contains("Common Item") || PlainTextComponentSerializer.plainText().serialize(x).contains("Rare Item") || PlainTextComponentSerializer.plainText().serialize(x).contains("Epic Item") || PlainTextComponentSerializer.plainText().serialize(x).contains("Legendary Item"))) {
                    for (int i = 0; i < 27; ++i) {
                        if (player.getOpenInventory().getItem(i) == null) {
                            player.getOpenInventory().setItem(i, event.getCurrentItem());
                            event.setCancelled(true);
                            player.getInventory().setItem(event.getSlot(), null);
                            ItemBuyer.recalculateCoins(player);
                            break;
                        }
                        if (i != 26) continue;
                        event.setCancelled(true);
                        player.playSound((Entity)player, Sound.ENTITY_CAT_HISS, 1.0f, 1.0f);
                    }
                    return;
                }
                event.setCancelled(true);
                player.playSound((Entity)player, Sound.ENTITY_CAT_HISS, 1.0f, 1.0f);
            } else {
                event.setCancelled(true);
                if (event.getSlot() < 5 || event.getSlot() >= 9 && event.getSlot() <= 13 || event.getSlot() >= 18 && event.getSlot() <= 22) {
                    if (player.getOpenInventory().getItem(event.getSlot()) != null) {
                        ItemBuyer.returnItem(player, player.getOpenInventory().getItem(event.getSlot()));
                        player.getOpenInventory().setItem(event.getSlot(), null);
                    }
                    ItemBuyer.recalculateCoins(player);
                } else if (event.getSlot() == 24) {
                    player.getOpenInventory().close();
                } else if (event.getSlot() == 6) {
                    for (int i = 0; i < 27; ++i) {
                        if (i >= 5 && (i < 9 || i > 13) && (i < 18 || i > 22) || player.getOpenInventory().getItem(i) == null) continue;
                        player.getOpenInventory().setItem(i, null);
                    }
                    if (player.getOpenInventory().getItem(8) != null) {
                        ItemStack itemStack = player.getOpenInventory().getItem(8);
                        assert (itemStack != null);
                        if (itemStack.getType() == Material.GOLD_NUGGET) {
                            ItemBuyer.returnItem(player, player.getOpenInventory().getItem(8));
                            player.getOpenInventory().setItem(8, ItemBuyer.getEmptyCoinElement());
                        }
                    }
                    if (player.getOpenInventory().getItem(17) != null) {
                        ItemStack itemStack = player.getOpenInventory().getItem(17);
                        assert (itemStack != null);
                        if (itemStack.getType() == Material.GOLD_NUGGET) {
                            ItemBuyer.returnItem(player, player.getOpenInventory().getItem(17));
                            player.getOpenInventory().setItem(17, ItemBuyer.getEmptyCoinElement());
                        }
                    }
                    if (player.getOpenInventory().getItem(26) != null) {
                        ItemStack itemStack = player.getOpenInventory().getItem(26);
                        assert (itemStack != null);
                        if (itemStack.getType() == Material.GOLD_NUGGET) {
                            ItemBuyer.returnItem(player, player.getOpenInventory().getItem(26));
                            player.getOpenInventory().setItem(26, ItemBuyer.getEmptyCoinElement());
                        }
                    }
                    player.playSound((Entity)player, Sound.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
                }
            }
        }
    }

    private static void recalculateCoins(Player player) {
        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        for (int k = 0; k < 27; ++k) {
            if (k >= 5 && (k < 9 || k > 13) && (k < 18 || k > 22) || player.getOpenInventory().getItem(k) == null) continue;
            items.add(player.getOpenInventory().getItem(k));
        }
        List<ItemStack> coins = ItemBuyer.calculateCoins(ItemBuyer.calculateWorth(items));
        if (coins.size() == 0) {
            player.getOpenInventory().setItem(8, ItemBuyer.getEmptyCoinElement());
            player.getOpenInventory().setItem(17, ItemBuyer.getEmptyCoinElement());
            player.getOpenInventory().setItem(26, ItemBuyer.getEmptyCoinElement());
        }
        if (coins.size() == 1) {
            player.getOpenInventory().setItem(8, coins.get(0));
            player.getOpenInventory().setItem(17, ItemBuyer.getEmptyCoinElement());
            player.getOpenInventory().setItem(26, ItemBuyer.getEmptyCoinElement());
        }
        if (coins.size() == 2) {
            player.getOpenInventory().setItem(8, coins.get(0));
            player.getOpenInventory().setItem(17, coins.get(1));
            player.getOpenInventory().setItem(26, ItemBuyer.getEmptyCoinElement());
        }
        if (coins.size() == 3) {
            player.getOpenInventory().setItem(8, coins.get(0));
            player.getOpenInventory().setItem(17, coins.get(1));
            player.getOpenInventory().setItem(26, coins.get(2));
        }
    }

    public static void invExit(Player player) {
        for (int i = 0; i < 27; ++i) {
            if (i >= 5 && (i < 9 || i > 13) && (i < 18 || i > 22) || player.getOpenInventory().getItem(i) == null) continue;
            ItemBuyer.returnItem(player, player.getOpenInventory().getItem(i));
        }
    }

    public static boolean isItemBuyerGUI(String title) {
        return title.contains("Item Hoarder");
    }

    private static void returnItem(Player player, ItemStack item) {
        if (player.getInventory().firstEmpty() == -1) {
            player.getWorld().dropItem(player.getLocation(), item);
        } else {
            player.getInventory().addItem(new ItemStack[]{item});
        }
    }

    private static int calculateWorth(List<ItemStack> list) {
        int count = 0;
        for (ItemStack item : list) {
            Integer temp;
            int levelMultiplier;
            if (item.getItemMeta() == null || item.getItemMeta().lore() == null) continue;
            if (item.getItemMeta().lore().stream().anyMatch(x -> PlainTextComponentSerializer.plainText().serialize(x).contains("Common Item"))) {
                levelMultiplier = 1;
                if (item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey((Plugin)Aurum.getPlugin(), "minLevel"), PersistentDataType.INTEGER)) {
                    temp = (Integer)item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey((Plugin)Aurum.getPlugin(), "minLevel"), PersistentDataType.INTEGER);
                    levelMultiplier = temp != null ? temp : 1;
                }
                count += levelMultiplier;
            }
            if (item.getItemMeta().lore().stream().anyMatch(x -> PlainTextComponentSerializer.plainText().serialize(x).contains("Rare Item"))) {
                levelMultiplier = 1;
                if (item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey((Plugin)Aurum.getPlugin(), "minLevel"), PersistentDataType.INTEGER)) {
                    temp = (Integer)item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey((Plugin)Aurum.getPlugin(), "minLevel"), PersistentDataType.INTEGER);
                    levelMultiplier = temp != null ? temp : 1;
                }
                count += 2 * levelMultiplier;
            }
            if (item.getItemMeta().lore().stream().anyMatch(x -> PlainTextComponentSerializer.plainText().serialize(x).contains("Epic Item"))) {
                levelMultiplier = 1;
                if (item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey((Plugin)Aurum.getPlugin(), "minLevel"), PersistentDataType.INTEGER)) {
                    temp = (Integer)item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey((Plugin)Aurum.getPlugin(), "minLevel"), PersistentDataType.INTEGER);
                    levelMultiplier = temp != null ? temp : 1;
                }
                count += 3 * levelMultiplier;
            }
            if (!item.getItemMeta().lore().stream().anyMatch(x -> PlainTextComponentSerializer.plainText().serialize(x).contains("Legendary Item"))) continue;
            levelMultiplier = 1;
            if (item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey((Plugin)Aurum.getPlugin(), "minLevel"), PersistentDataType.INTEGER)) {
                temp = (Integer)item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey((Plugin)Aurum.getPlugin(), "minLevel"), PersistentDataType.INTEGER);
                levelMultiplier = temp != null ? temp : 1;
            }
            count += 4 * levelMultiplier;
        }
        return count;
    }

    private static List<ItemStack> calculateCoins(int count) {
        ItemStack item;
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        int silverCoins = count / 64;
        int bronzeCoins = count % 64;
        int goldCoins = silverCoins / 64;
        silverCoins %= 64;
        if (goldCoins > 0) {
            item = ItemBuyer.itemFactory.miscItems.coinGold();
            item.setAmount(goldCoins);
            list.add(item);
        }
        if (silverCoins > 0) {
            item = ItemBuyer.itemFactory.miscItems.coinSilver();
            item.setAmount(silverCoins);
            list.add(item);
        }
        if (bronzeCoins > 0) {
            item = ItemBuyer.itemFactory.miscItems.coinBronze();
            item.setAmount(bronzeCoins);
            list.add(item);
        }
        return list;
    }

    private static ItemStack getEmptyCoinElement() {
        ItemStack itemStack = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.displayName(Component.text(" "));
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}

