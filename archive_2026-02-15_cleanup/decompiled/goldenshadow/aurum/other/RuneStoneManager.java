/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Slime
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package goldenshadow.aurum.other;

import goldenshadow.aurum.other.ExperienceManager;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RuneStoneManager {
    public static Inventory createGUI(Player player) {
        Inventory inventory = Bukkit.createInventory(null, (int)9, (String)(ChatColor.BOLD + "Rune Stone"));
        int playerLevel = ExperienceManager.getLevel(player);
        ItemStack item = new ItemStack(Material.ENDER_PEARL);
        ItemMeta meta = item.getItemMeta();
        assert (meta != null);
        ArrayList<Object> lore = new ArrayList<Object>();
        for (int i = 0; i < 9; ++i) {
            if (i == 0 || i == 8) {
                inventory.setItem(i, RuneStoneManager.fillerElement());
            }
            if (i == 1) {
                meta.setDisplayName(ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Esper");
                lore.add(ChatColor.DARK_GRAY + "Click to teleport");
                lore.add("");
                if (playerLevel >= 5) {
                    item.setType(Material.ENDER_EYE);
                    lore.add(ChatColor.GREEN + "\u2714 " + ChatColor.GRAY + "Level Req: 5");
                } else {
                    lore.add(ChatColor.RED + "\u2716 " + ChatColor.GRAY + "Level Req: 5");
                }
                lore.add("");
                lore.add(ChatColor.AQUA + "Cost: 1 Silver Coin");
                meta.setLore(lore);
                item.setItemMeta(meta);
                inventory.setItem(i, item);
                item.setType(Material.ENDER_PEARL);
                lore.clear();
            }
            if (i == 2) {
                meta.setDisplayName(ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Arbidel");
                lore.add(ChatColor.DARK_GRAY + "Click to teleport");
                lore.add("");
                if (playerLevel >= 35) {
                    item.setType(Material.ENDER_EYE);
                    lore.add(ChatColor.GREEN + "\u2714 " + ChatColor.GRAY + "Level Req: 35");
                } else {
                    lore.add(ChatColor.RED + "\u2716 " + ChatColor.GRAY + "Level Req: 35");
                }
                lore.add("");
                lore.add(ChatColor.AQUA + "Cost: 2 Silvers Coin");
                meta.setLore(lore);
                item.setItemMeta(meta);
                inventory.setItem(i, item);
                item.setType(Material.ENDER_PEARL);
                lore.clear();
            }
            if (i == 3) {
                meta.setDisplayName(ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Runesmith Tower");
                lore.add(ChatColor.DARK_GRAY + "Click to teleport");
                lore.add("");
                if (playerLevel >= 45) {
                    item.setType(Material.ENDER_EYE);
                    lore.add(ChatColor.GREEN + "\u2714 " + ChatColor.GRAY + "Level Req: 45");
                } else {
                    lore.add(ChatColor.RED + "\u2716 " + ChatColor.GRAY + "Level Req: 45");
                }
                lore.add("");
                lore.add(ChatColor.AQUA + "Cost: 4 Silver Coins");
                meta.setLore(lore);
                item.setItemMeta(meta);
                inventory.setItem(i, item);
                item.setType(Material.ENDER_PEARL);
                lore.clear();
            }
            if (i == 4) {
                meta.setDisplayName(ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Hearth");
                lore.add(ChatColor.DARK_GRAY + "Click to teleport");
                lore.add("");
                if (playerLevel >= 60) {
                    item.setType(Material.ENDER_EYE);
                    lore.add(ChatColor.GREEN + "\u2714 " + ChatColor.GRAY + "Level Req: 60");
                } else {
                    lore.add(ChatColor.RED + "\u2716 " + ChatColor.GRAY + "Level Req: 60");
                }
                lore.add("");
                lore.add(ChatColor.AQUA + "Cost: 6 Silver Coins");
                meta.setLore(lore);
                item.setItemMeta(meta);
                inventory.setItem(i, item);
                item.setType(Material.ENDER_PEARL);
                lore.clear();
            }
            if (i == 5) {
                meta.setDisplayName(ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Scavenger Camp");
                lore.add(ChatColor.DARK_GRAY + "Click to teleport");
                lore.add("");
                if (playerLevel >= 75) {
                    item.setType(Material.ENDER_EYE);
                    lore.add(ChatColor.GREEN + "\u2714 " + ChatColor.GRAY + "Level Req: 75");
                } else {
                    lore.add(ChatColor.RED + "\u2716 " + ChatColor.GRAY + "Level Req: 75");
                }
                lore.add("");
                lore.add(ChatColor.AQUA + "Cost: 10 Silver Coins");
                meta.setLore(lore);
                item.setItemMeta(meta);
                inventory.setItem(i, item);
                item.setType(Material.ENDER_PEARL);
                lore.clear();
            }
            if (i == 6) {
                meta.setDisplayName(ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Waya's Antiques and Oddities");
                lore.add(ChatColor.DARK_GRAY + "Click to teleport");
                lore.add("");
                if (playerLevel >= 82) {
                    item.setType(Material.ENDER_EYE);
                    lore.add(ChatColor.GREEN + "\u2714 " + ChatColor.GRAY + "Level Req: 82");
                } else {
                    lore.add(ChatColor.RED + "\u2716 " + ChatColor.GRAY + "Level Req: 82");
                }
                lore.add("");
                lore.add(ChatColor.AQUA + "Cost: 12 Silver Coins");
                meta.setLore(lore);
                item.setItemMeta(meta);
                inventory.setItem(i, item);
                item.setType(Material.ENDER_PEARL);
                lore.clear();
            }
            if (i != 7) continue;
            meta.setDisplayName(ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Venir");
            lore.add(ChatColor.DARK_GRAY + "Click to teleport");
            lore.add("");
            if (playerLevel >= 90) {
                item.setType(Material.ENDER_EYE);
                lore.add(ChatColor.GREEN + "\u2714 " + ChatColor.GRAY + "Level Req: 90");
            } else {
                lore.add(ChatColor.RED + "\u2716 " + ChatColor.GRAY + "Level Req: 90");
            }
            lore.add("");
            lore.add(ChatColor.AQUA + "Cost: 16 Silver Coins");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inventory.setItem(i, item);
            item.setType(Material.ENDER_PEARL);
            lore.clear();
        }
        return inventory;
    }

    public static void createRuneStone(Location location) {
        assert (location.getWorld() != null);
        ArmorStand display = (ArmorStand)location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        display.setMarker(true);
        display.addScoreboardTag("aurum_rune_stone");
        display.setInvulnerable(true);
        display.setInvisible(true);
        ItemStack item = new ItemStack(Material.STICK);
        ItemMeta meta = item.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(100085));
        item.setItemMeta(meta);
        assert (display.getEquipment() != null);
        display.getEquipment().setHelmet(item);
        for (int i = 0; i < 2; ++i) {
            if (i == 1) {
                location = location.add(0.0, 1.0, 0.0);
            }
            assert (location.getWorld() != null);
            Slime hitbox = (Slime)location.getWorld().spawnEntity(location, EntityType.SLIME);
            hitbox.setInvulnerable(true);
            hitbox.setGravity(false);
            hitbox.setRemoveWhenFarAway(false);
            hitbox.setSize(2);
            hitbox.setInvisible(true);
            hitbox.setAI(false);
            hitbox.addScoreboardTag("aurum_rune_stone");
        }
    }

    public static void interact(Player player) {
        player.openInventory(RuneStoneManager.createGUI(player));
    }

    public static void inventoryClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        if (event.getClickedInventory() != player.getInventory()) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null) {
                if (event.getCurrentItem().getType() == Material.ENDER_PEARL) {
                    player.sendMessage(ChatColor.DARK_RED + "[" + ChatColor.RED + "!" + ChatColor.DARK_RED + "] " + ChatColor.GRAY + "You are not high enough level to travel to that location!");
                    player.playSound((Entity)player, Sound.ENTITY_CAT_HISS, 1.0f, 1.0f);
                    return;
                }
                if (event.getCurrentItem().getType() == Material.ENDER_EYE) {
                    assert (event.getCurrentItem().getItemMeta() != null);
                    int cost = RuneStoneManager.getCostFromName(event.getCurrentItem().getItemMeta().getDisplayName());
                    if (RuneStoneManager.hasEnoughCoins(player, cost)) {
                        RuneStoneManager.removeCoins(player, cost);
                        player.teleport(RuneStoneManager.getLocationByCost(cost));
                        player.playSound((Entity)player, Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.0f);
                    } else {
                        player.sendMessage(ChatColor.DARK_RED + "[" + ChatColor.RED + "!" + ChatColor.DARK_RED + "] " + ChatColor.GRAY + "You don't have enough silver coins to travel to that location");
                        player.playSound((Entity)player, Sound.ENTITY_CAT_HISS, 1.0f, 1.0f);
                    }
                }
            }
        }
    }

    private static ItemStack fillerElement() {
        ItemStack itemStack = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setDisplayName(" ");
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private static boolean hasEnoughCoins(Player player, int amount) {
        int count = 0;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item == null || item.getType() != Material.GOLD_NUGGET) continue;
            assert (item.getItemMeta() != null);
            if (item.getItemMeta().getCustomModelData() != 2) continue;
            count += item.getAmount();
        }
        return count >= amount;
    }

    private static void removeCoins(Player player, int amount) {
        int count = 0;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == Material.GOLD_NUGGET) {
                assert (item.getItemMeta() != null);
                if (item.getItemMeta().getCustomModelData() == 2) {
                    if (item.getAmount() <= amount - count) {
                        count += item.getAmount();
                        item.setAmount(0);
                    } else {
                        item.setAmount(item.getAmount() - (amount - count));
                        count = amount;
                    }
                }
            }
            if (count >= amount) break;
        }
    }

    private static int getCostFromName(String name) {
        if (name.contains("Esper")) {
            return 1;
        }
        if (name.contains("Arbidel")) {
            return 2;
        }
        if (name.contains("Runesmith Tower")) {
            return 4;
        }
        if (name.contains("Hearth")) {
            return 6;
        }
        if (name.contains("Scavenger Camp")) {
            return 10;
        }
        if (name.contains("Waya's Antiques and Oddities")) {
            return 12;
        }
        if (name.contains("Venir")) {
            return 16;
        }
        return 1;
    }

    private static Location getLocationByCost(int cost) {
        return switch (cost) {
            case 1 -> new Location(Bukkit.getWorld((String)"WynnRipoff"), 379.0, 30.0, 323.0, -45.0f, 0.0f);
            case 2 -> new Location(Bukkit.getWorld((String)"WynnRipoff"), -83.0, 20.0, 473.0, 180.0f, 0.0f);
            case 4 -> new Location(Bukkit.getWorld((String)"WynnRipoff"), 207.0, 41.0, -88.0, 160.0f, 0.0f);
            case 6 -> new Location(Bukkit.getWorld((String)"WynnRipoff"), 375.0, 40.0, -228.0, -160.0f, 0.0f);
            case 10 -> new Location(Bukkit.getWorld((String)"WynnRipoff"), 107.0, 40.0, -273.0, -45.0f, 0.0f);
            case 12 -> new Location(Bukkit.getWorld((String)"WynnRipoff"), -156.0, 51.0, -68.0, 150.0f, 0.0f);
            case 16 -> new Location(Bukkit.getWorld((String)"WynnRipoff"), -547.0, 48.0, 3.0, 160.0f, 0.0f);
            default -> new Location(Bukkit.getWorld((String)"WynnRipoff"), 0.0, 100.0, 0.0);
        };
    }
}

