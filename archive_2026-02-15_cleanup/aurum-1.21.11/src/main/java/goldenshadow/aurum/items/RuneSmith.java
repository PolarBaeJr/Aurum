/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryHolder
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataType
 */
package goldenshadow.aurum.items;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.items.ItemFactory;
import goldenshadow.aurum.items.flags.Rune;
import goldenshadow.aurum.items.flags.RuneKeys;
import goldenshadow.aurum.other.ExperienceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class RuneSmith {
    public static Inventory createRuneSmithGUI(Player player) {
        Inventory inv = Bukkit.createInventory((InventoryHolder)player, 27, Component.text("       ", NamedTextColor.LIGHT_PURPLE).append(Component.text("H", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append(Component.text(" Rune Smith Table ", NamedTextColor.DARK_PURPLE).decorate(TextDecoration.BOLD)).append(Component.text("H", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        ItemStack item = new ItemStack(Material.GLASS);
        ItemMeta meta = item.getItemMeta();
        List<Component> lore = new ArrayList<>();
        for (int i = 0; i < 27; ++i) {
            assert (meta != null);
            meta.displayName(Component.text(" "));
            if (i == 0 || i == 1 || i == 9 || i == 18 || i == 19) {
                item.setType(Material.RED_STAINED_GLASS_PANE);
            } else if (i == 7 || i == 8 || i == 17 || i == 25 || i == 26) {
                item.setType(Material.LIME_STAINED_GLASS_PANE);
            } else if (i == 2 || i == 3 || i == 5 || i == 6 || i == 11 || i == 13 || i == 15 || i == 20 || i == 21 || i == 23 || i == 24) {
                item.setType(Material.GRAY_STAINED_GLASS_PANE);
            } else if (i == 10 || i == 16) {
                item.setType(Material.END_CRYSTAL);
                if (i == 10) {
                    meta.displayName(Component.text("Cancel", NamedTextColor.RED).decorate(TextDecoration.BOLD));
                    lore.add(Component.text("Click here to exit!", NamedTextColor.GRAY));
                } else {
                    meta.displayName(Component.text("Apply", NamedTextColor.GREEN).decorate(TextDecoration.BOLD));
                    lore.add(Component.text("Click here to apply runes to item!", NamedTextColor.GRAY));
                    lore.add(Component.text("Cost: ???", NamedTextColor.GRAY));
                    lore.add(Component.text("Runes can not be removed once applied!", NamedTextColor.DARK_GRAY));
                }
            } else {
                if (i != 12 && i != 14 && i != 22) continue;
                item.setType(Material.BARRIER);
                meta.displayName(Component.text("Rune slot not available!", NamedTextColor.DARK_RED).decorate(TextDecoration.BOLD));
            }
            meta.lore(lore);
            item.setItemMeta(meta);
            inv.setItem(i, item);
            lore.clear();
        }
        return inv;
    }

    public static void invExit(Player player) {
        if (player.getOpenInventory().getItem(4) != null) {
            RuneSmith.returnItem(player, player.getOpenInventory().getItem(4));
        }
        if (player.getOpenInventory().getItem(12) != null && Objects.requireNonNull(player.getOpenInventory().getItem(12)).getType() != Material.BARRIER) {
            RuneSmith.returnItem(player, player.getOpenInventory().getItem(12));
        }
        if (player.getOpenInventory().getItem(14) != null && Objects.requireNonNull(player.getOpenInventory().getItem(14)).getType() != Material.BARRIER) {
            RuneSmith.returnItem(player, player.getOpenInventory().getItem(14));
        }
        if (player.getOpenInventory().getItem(22) != null && Objects.requireNonNull(player.getOpenInventory().getItem(22)).getType() != Material.BARRIER) {
            RuneSmith.returnItem(player, player.getOpenInventory().getItem(22));
        }
    }

    public static boolean isRuneSmithGUI(String s) {
        return s.contains("Rune Smith Table");
    }

    public static void invClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        if (event.getClickedInventory() == player.getInventory()) {
            if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
                if (event.getCurrentItem().getType() == Material.CLAY_BALL) {
                    if (player.getOpenInventory().getItem(12) == null) {
                        if (RuneSmith.isClickedRuneAllowed(player, event.getCurrentItem(), 1)) {
                            if (event.getCurrentItem().getAmount() == 1) {
                                player.getOpenInventory().setItem(12, event.getCurrentItem());
                                player.getInventory().setItem(event.getSlot(), new ItemStack(Material.AIR));
                            } else {
                                ItemStack reducedToOne = event.getCurrentItem();
                                int reducedByOne = event.getCurrentItem().getAmount() - 1;
                                reducedToOne.setAmount(1);
                                player.getOpenInventory().setItem(12, reducedToOne);
                                event.getCurrentItem().setAmount(reducedByOne);
                            }
                            player.playSound((Entity)player, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1.0f, 1.0f);
                            player.getOpenInventory().setItem(16, RuneSmith.increaseCost(player));
                        }
                    } else if (player.getOpenInventory().getItem(14) == null) {
                        if (RuneSmith.isClickedRuneAllowed(player, event.getCurrentItem(), 2)) {
                            if (event.getCurrentItem().getAmount() == 1) {
                                player.getOpenInventory().setItem(14, event.getCurrentItem());
                                player.getInventory().setItem(event.getSlot(), new ItemStack(Material.AIR));
                            } else {
                                ItemStack reducedToOne = event.getCurrentItem();
                                int reducedByOne = event.getCurrentItem().getAmount() - 1;
                                reducedToOne.setAmount(1);
                                player.getOpenInventory().setItem(14, reducedToOne);
                                event.getCurrentItem().setAmount(reducedByOne);
                            }
                            player.playSound((Entity)player, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1.0f, 1.0f);
                            player.getOpenInventory().setItem(16, RuneSmith.increaseCost(player));
                        }
                    } else if (player.getOpenInventory().getItem(22) == null) {
                        if (RuneSmith.isClickedRuneAllowed(player, event.getCurrentItem(), 3)) {
                            if (event.getCurrentItem().getAmount() == 1) {
                                player.getOpenInventory().setItem(22, event.getCurrentItem());
                                player.getInventory().setItem(event.getSlot(), new ItemStack(Material.AIR));
                            } else {
                                ItemStack reducedToOne = event.getCurrentItem();
                                int reducedByOne = event.getCurrentItem().getAmount() - 1;
                                reducedToOne.setAmount(1);
                                player.getOpenInventory().setItem(22, reducedToOne);
                                event.getCurrentItem().setAmount(reducedByOne);
                            }
                            player.playSound((Entity)player, Sound.BLOCK_RESPAWN_ANCHOR_CHARGE, 1.0f, 1.0f);
                            player.getOpenInventory().setItem(16, RuneSmith.increaseCost(player));
                        }
                    } else {
                        player.playSound((Entity)player, Sound.ENTITY_CAT_HISS, 1.0f, 1.0f);
                    }
                } else if (event.getCurrentItem().hasItemMeta()) {
                    if (player.getOpenInventory().getItem(4) == null) {
                        assert (event.getCurrentItem().getItemMeta() != null);
                        if (event.getCurrentItem().getItemMeta().getPersistentDataContainer().has(RuneKeys.EMPTY, PersistentDataType.INTEGER)) {
                            Integer i = (Integer)event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(RuneKeys.EMPTY, PersistentDataType.INTEGER);
                            assert (i != null);
                            if (i >= 1) {
                                player.getOpenInventory().setItem(12, null);
                            }
                            if (i >= 2) {
                                player.getOpenInventory().setItem(14, null);
                            }
                            if (i >= 3) {
                                player.getOpenInventory().setItem(22, null);
                            }
                            if (event.getCurrentItem().getAmount() == 1) {
                                player.getOpenInventory().setItem(4, event.getCurrentItem());
                                player.getInventory().setItem(event.getSlot(), new ItemStack(Material.AIR));
                            } else {
                                ItemStack reducedToOne = event.getCurrentItem();
                                int reducedByOne = event.getCurrentItem().getAmount() - 1;
                                reducedToOne.setAmount(1);
                                player.getOpenInventory().setItem(4, reducedToOne);
                                event.getCurrentItem().setAmount(reducedByOne);
                            }
                            player.playSound((Entity)player, Sound.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, 1.0f, 1.0f);
                        }
                    } else {
                        player.playSound((Entity)player, Sound.ENTITY_CAT_HISS, 1.0f, 1.0f);
                    }
                }
            }
        } else {
            if (event.getSlot() == 10) {
                player.closeInventory();
                event.setCancelled(true);
                return;
            }
            if (event.getSlot() == 4) {
                if (player.getOpenInventory().getItem(4) != null) {
                    RuneSmith.returnItem(player, player.getOpenInventory().getItem(4));
                    player.getOpenInventory().setItem(4, new ItemStack(Material.AIR));
                }
                if (player.getOpenInventory().getItem(12) != null && Objects.requireNonNull(player.getOpenInventory().getItem(12)).getType() != Material.BARRIER) {
                    RuneSmith.returnItem(player, player.getOpenInventory().getItem(12));
                    player.getOpenInventory().setItem(12, new ItemStack(Material.AIR));
                }
                if (player.getOpenInventory().getItem(14) != null && Objects.requireNonNull(player.getOpenInventory().getItem(14)).getType() != Material.BARRIER) {
                    RuneSmith.returnItem(player, player.getOpenInventory().getItem(14));
                    player.getOpenInventory().setItem(14, new ItemStack(Material.AIR));
                }
                if (player.getOpenInventory().getItem(22) != null && Objects.requireNonNull(player.getOpenInventory().getItem(22)).getType() != Material.BARRIER) {
                    RuneSmith.returnItem(player, player.getOpenInventory().getItem(22));
                    player.getOpenInventory().setItem(22, new ItemStack(Material.AIR));
                }
                player.getOpenInventory().setItem(12, RuneSmith.barrierItem());
                player.getOpenInventory().setItem(14, RuneSmith.barrierItem());
                player.getOpenInventory().setItem(22, RuneSmith.barrierItem());
                player.getOpenInventory().setItem(16, RuneSmith.resetCost(player));
            }
            if (event.getSlot() == 12 && player.getOpenInventory().getItem(12) != null && Objects.requireNonNull(player.getOpenInventory().getItem(12)).getType() != Material.BARRIER) {
                RuneSmith.returnItem(player, player.getOpenInventory().getItem(12));
                player.getOpenInventory().setItem(12, new ItemStack(Material.AIR));
                player.getOpenInventory().setItem(16, RuneSmith.decreaseCost(player));
            }
            if (event.getSlot() == 14 && player.getOpenInventory().getItem(14) != null && Objects.requireNonNull(player.getOpenInventory().getItem(14)).getType() != Material.BARRIER) {
                RuneSmith.returnItem(player, player.getOpenInventory().getItem(14));
                player.getOpenInventory().setItem(14, new ItemStack(Material.AIR));
                player.getOpenInventory().setItem(16, RuneSmith.decreaseCost(player));
            }
            if (event.getSlot() == 22 && player.getOpenInventory().getItem(22) != null && Objects.requireNonNull(player.getOpenInventory().getItem(22)).getType() != Material.BARRIER) {
                RuneSmith.returnItem(player, player.getOpenInventory().getItem(22));
                player.getOpenInventory().setItem(22, new ItemStack(Material.AIR));
                player.getOpenInventory().setItem(16, RuneSmith.decreaseCost(player));
            }
            if (event.getSlot() == 16) {
                ItemStack rune1 = null;
                ItemStack rune2 = null;
                ItemStack rune3 = null;
                if (player.getOpenInventory().getItem(12) != null && Objects.requireNonNull(player.getOpenInventory().getItem(12)).getType() != Material.BARRIER) {
                    rune1 = player.getOpenInventory().getItem(12);
                }
                if (player.getOpenInventory().getItem(14) != null && Objects.requireNonNull(player.getOpenInventory().getItem(14)).getType() != Material.BARRIER) {
                    rune2 = player.getOpenInventory().getItem(14);
                }
                if (player.getOpenInventory().getItem(22) != null && Objects.requireNonNull(player.getOpenInventory().getItem(22)).getType() != Material.BARRIER) {
                    rune3 = player.getOpenInventory().getItem(22);
                }
                if (rune1 == null && rune2 == null && rune3 == null) {
                    player.playSound((Entity)player, Sound.ENTITY_CAT_HISS, 1.0f, 1.0f);
                } else {
                    int count = 0;
                    if (rune1 != null) {
                        ++count;
                    }
                    if (rune2 != null) {
                        ++count;
                    }
                    if (rune3 != null) {
                        ++count;
                    }
                    if (player.getLevel() > count || player.getLevel() != 1) {
                        player.getOpenInventory().setItem(12, new ItemStack(Material.AIR));
                        player.getOpenInventory().setItem(14, new ItemStack(Material.AIR));
                        player.getOpenInventory().setItem(22, new ItemStack(Material.AIR));
                        if (Aurum.getPlugin().getConfig().getBoolean("ExperienceSystem")) {
                            ExperienceManager.setLevel(player, ExperienceManager.getLevel(player) - count);
                        } else {
                            player.setLevel(player.getLevel() - count);
                        }
                        ItemStack i = player.getOpenInventory().getItem(4);
                        assert (i != null);
                        player.getOpenInventory().setItem(4, RuneSmith.addRunes(i, rune1, rune2, rune3));
                        player.playSound((Entity)player, Sound.BLOCK_ANVIL_USE, 1.0f, 1.0f);
                        player.getOpenInventory().setItem(16, RuneSmith.resetCost(player));
                        player.getOpenInventory().setItem(12, RuneSmith.barrierItem());
                        player.getOpenInventory().setItem(14, RuneSmith.barrierItem());
                        player.getOpenInventory().setItem(22, RuneSmith.barrierItem());
                    } else {
                        player.playSound((Entity)player, Sound.ENTITY_CAT_HISS, 1.0f, 1.0f);
                        player.sendMessage(Component.text("[", NamedTextColor.DARK_PURPLE).append(Component.text("!", NamedTextColor.LIGHT_PURPLE)).append(Component.text("] ", NamedTextColor.DARK_PURPLE)).append(Component.text("You don't have enough levels to apply these runes!", NamedTextColor.LIGHT_PURPLE)));
                    }
                }
            }
        }
        event.setCancelled(true);
    }

    public static void interact(Player player) {
        player.openInventory(RuneSmith.createRuneSmithGUI(player));
    }

    private static ItemStack addRunes(ItemStack itemStack, ItemStack rune1, ItemStack rune2, ItemStack rune3) {
        if (itemStack.hasItemMeta()) {
            int j;
            Integer runeSlots;
            Rune runeToAdd;
            int i;
            ItemMeta meta = itemStack.getItemMeta();
            ItemFactory itemFactory = new ItemFactory();
            assert (meta != null);
            List<Component> lore = meta.lore();
            if (rune1 != null && rune1.getItemMeta() != null && !Rune.getRunesOnItem(rune1).isEmpty()) {
                assert (lore != null);
                for (i = lore.size() - 1; i > -1; --i) {
                    if (!PlainTextComponentSerializer.plainText().serialize(lore.get(i)).equalsIgnoreCase("Empty Rune Slot")) continue;
                    lore.remove(i);
                    lore.remove(i - 1);
                    runeToAdd = RuneSmith.getRuneAbility(rune1);
                    meta = itemFactory.itemCreationHelper.addRuneAbility(meta, runeToAdd, lore, RuneSmith.isArmor(itemStack));
                    runeSlots = (Integer)meta.getPersistentDataContainer().get(RuneKeys.EMPTY, PersistentDataType.INTEGER);
                    meta.getPersistentDataContainer().remove(RuneKeys.EMPTY);
                    if (runeSlots == null) break;
                    for (j = 0; j < runeSlots - 1; ++j) {
                        meta = itemFactory.itemCreationHelper.addRuneData(meta, Rune.EMPTY);
                    }
                    break;
                }
            }
            if (rune2 != null && rune2.getItemMeta() != null && rune2.getItemMeta().hasEnchants()) {
                assert (lore != null);
                for (i = lore.size() - 1; i > -1; --i) {
                    if (!PlainTextComponentSerializer.plainText().serialize(lore.get(i)).equalsIgnoreCase("Empty Rune Slot")) continue;
                    lore.remove(i);
                    lore.remove(i - 1);
                    runeToAdd = RuneSmith.getRuneAbility(rune2);
                    meta = itemFactory.itemCreationHelper.addRuneAbility(meta, runeToAdd, lore, RuneSmith.isArmor(itemStack));
                    runeSlots = (Integer)meta.getPersistentDataContainer().get(RuneKeys.EMPTY, PersistentDataType.INTEGER);
                    meta.getPersistentDataContainer().remove(RuneKeys.EMPTY);
                    if (runeSlots == null) break;
                    for (j = 0; j < runeSlots - 1; ++j) {
                        meta = itemFactory.itemCreationHelper.addRuneData(meta, Rune.EMPTY);
                    }
                    break;
                }
            }
            if (rune3 != null && rune3.getItemMeta() != null && rune3.getItemMeta().hasEnchants()) {
                assert (lore != null);
                for (i = lore.size() - 1; i > -1; --i) {
                    if (!PlainTextComponentSerializer.plainText().serialize(lore.get(i)).equalsIgnoreCase("Empty Rune Slot")) continue;
                    lore.remove(i);
                    lore.remove(i - 1);
                    runeToAdd = RuneSmith.getRuneAbility(rune3);
                    meta = itemFactory.itemCreationHelper.addRuneAbility(meta, runeToAdd, lore, RuneSmith.isArmor(itemStack));
                    runeSlots = (Integer)meta.getPersistentDataContainer().get(RuneKeys.EMPTY, PersistentDataType.INTEGER);
                    meta.getPersistentDataContainer().remove(RuneKeys.EMPTY);
                    if (runeSlots == null) break;
                    for (j = 0; j < runeSlots - 1; ++j) {
                        meta = itemFactory.itemCreationHelper.addRuneData(meta, Rune.EMPTY);
                    }
                    break;
                }
            }
            meta.lore(lore);
            itemStack.setItemMeta(meta);
        }
        return itemStack;
    }

    private static boolean isActive(ItemStack rune) {
        if (rune != null) {
            List<Rune> list = Rune.getRunesOnItem(rune);
            return list.contains((Object)Rune.BLOOD_RUSH) || list.contains((Object)Rune.CHARGE) || list.contains((Object)Rune.DISTORTION) || list.contains((Object)Rune.SHOCK_WAVE) || list.contains((Object)Rune.FALLING_STAR) || list.contains((Object)Rune.FIREBALL) || list.contains((Object)Rune.SMITE) || list.contains((Object)Rune.HEAL) || list.contains((Object)Rune.WIND_SLASH) || list.contains((Object)Rune.ARCANE_RAY) || list.contains((Object)Rune.GROUND_SLAM) || list.contains((Object)Rune.PIROUETTE) || list.contains((Object)Rune.RITUAL) || list.contains((Object)Rune.FROZEN_SPARK) || list.contains((Object)Rune.TAUNT);
        }
        return false;
    }

    private static boolean areSameRune(ItemStack rune1, ItemStack rune2) {
        if (rune1 != null && rune2 != null) {
            return RuneSmith.getRuneAbility(rune1) == RuneSmith.getRuneAbility(rune2);
        }
        return false;
    }

    private static boolean isClickedRuneAllowed(Player player, ItemStack clickedItem, int runeSlot) {
        ItemStack itemStack = player.getOpenInventory().getItem(4);
        assert (itemStack != null);
        for (Rune rune : Rune.getRunesOnItem(itemStack)) {
            if (rune != RuneSmith.getRuneAbility(clickedItem)) continue;
            return false;
        }
        if (RuneSmith.isArmor(itemStack) && RuneSmith.isActive(clickedItem)) {
            return false;
        }
        if (runeSlot >= 1) {
            if (RuneSmith.isActive(clickedItem) && RuneSmith.isActive(player.getOpenInventory().getItem(14))) {
                return false;
            }
            if (RuneSmith.areSameRune(clickedItem, player.getOpenInventory().getItem(14))) {
                return false;
            }
            if (RuneSmith.isActive(clickedItem) && RuneSmith.isActive(player.getOpenInventory().getItem(22))) {
                return false;
            }
            if (RuneSmith.areSameRune(clickedItem, player.getOpenInventory().getItem(22))) {
                return false;
            }
            if (runeSlot == 1) {
                return true;
            }
        }
        if (runeSlot >= 2) {
            if (RuneSmith.isActive(clickedItem) && RuneSmith.isActive(player.getOpenInventory().getItem(12))) {
                return false;
            }
            if (RuneSmith.areSameRune(clickedItem, player.getOpenInventory().getItem(12))) {
                return false;
            }
            if (RuneSmith.isActive(clickedItem) && RuneSmith.isActive(player.getOpenInventory().getItem(22))) {
                return false;
            }
            if (RuneSmith.areSameRune(clickedItem, player.getOpenInventory().getItem(22))) {
                return false;
            }
            if (runeSlot == 2) {
                return true;
            }
        }
        if (runeSlot == 3) {
            if (RuneSmith.isActive(clickedItem) && RuneSmith.isActive(player.getOpenInventory().getItem(14))) {
                return false;
            }
            if (RuneSmith.areSameRune(clickedItem, player.getOpenInventory().getItem(14))) {
                return false;
            }
            if (RuneSmith.isActive(clickedItem) && RuneSmith.isActive(player.getOpenInventory().getItem(12))) {
                return false;
            }
            return !RuneSmith.areSameRune(clickedItem, player.getOpenInventory().getItem(12));
        }
        return false;
    }

    private static ItemStack increaseCost(Player player) {
        ItemStack item = player.getOpenInventory().getItem(16);
        assert (item != null);
        ItemMeta meta = item.getItemMeta();
        assert (meta != null);
        List<Component> lore = meta.lore();
        assert (lore != null);
        String loreText = PlainTextComponentSerializer.plainText().serialize(lore.get(1));
        int newCost = loreText.equals("Cost: 1 Level") ? 2 : (loreText.equals("Cost: 2 Levels") ? 3 : (loreText.equals("Cost: 3 Levels") ? 3 : 1));
        if (newCost == 1) {
            lore.set(1, Component.text("Cost: " + newCost + " Level", NamedTextColor.GRAY));
        } else {
            lore.set(1, Component.text("Cost: " + newCost + " Levels", NamedTextColor.GRAY));
        }
        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack decreaseCost(Player player) {
        ItemStack item = player.getOpenInventory().getItem(16);
        assert (item != null);
        ItemMeta meta = item.getItemMeta();
        assert (meta != null);
        List<Component> lore = meta.lore();
        assert (lore != null);
        int newCost = 0;
        String loreText = PlainTextComponentSerializer.plainText().serialize(lore.get(1));
        if (loreText.equals("Cost: 1 Level")) {
            lore.set(1, Component.text("Cost: ???", NamedTextColor.GRAY));
        } else if (loreText.equals("Cost: 2 Levels")) {
            newCost = 1;
        } else if (loreText.equals("Cost: 3 Levels")) {
            newCost = 2;
        }
        if (newCost == 1) {
            lore.set(1, Component.text("Cost: " + newCost + " Level", NamedTextColor.GRAY));
        } else if (newCost != 0) {
            lore.set(1, Component.text("Cost: " + newCost + " Levels", NamedTextColor.GRAY));
        }
        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack resetCost(Player player) {
        ItemStack item = player.getOpenInventory().getItem(16);
        assert (item != null);
        ItemMeta meta = item.getItemMeta();
        assert (meta != null);
        List<Component> lore = meta.lore();
        assert (lore != null);
        lore.set(1, Component.text("Cost: ???", NamedTextColor.GRAY));
        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private static void returnItem(Player player, ItemStack item) {
        if (player.getInventory().firstEmpty() == -1) {
            player.getWorld().dropItem(player.getLocation(), item);
        } else {
            player.getInventory().addItem(new ItemStack[]{item});
        }
    }

    private static ItemStack barrierItem() {
        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.displayName(Component.text("Rune slot not available!", NamedTextColor.DARK_RED).decorate(TextDecoration.BOLD));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private static Rune getRuneAbility(ItemStack rune) {
        List<Rune> list = Rune.getRunesOnItem(rune);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private static boolean isArmor(ItemStack item) {
        if (item.getType() == Material.LEATHER_BOOTS) {
            return true;
        }
        if (item.getType() == Material.LEATHER_LEGGINGS) {
            return true;
        }
        if (item.getType() == Material.LEATHER_CHESTPLATE) {
            return true;
        }
        if (item.getType() == Material.LEATHER_HELMET) {
            return true;
        }
        if (item.getType() == Material.GOLDEN_BOOTS) {
            return true;
        }
        if (item.getType() == Material.GOLDEN_LEGGINGS) {
            return true;
        }
        if (item.getType() == Material.GOLDEN_CHESTPLATE) {
            return true;
        }
        if (item.getType() == Material.GOLDEN_HELMET) {
            return true;
        }
        if (item.getType() == Material.CHAINMAIL_BOOTS) {
            return true;
        }
        if (item.getType() == Material.CHAINMAIL_LEGGINGS) {
            return true;
        }
        if (item.getType() == Material.CHAINMAIL_CHESTPLATE) {
            return true;
        }
        if (item.getType() == Material.CHAINMAIL_HELMET) {
            return true;
        }
        if (item.getType() == Material.IRON_BOOTS) {
            return true;
        }
        if (item.getType() == Material.IRON_LEGGINGS) {
            return true;
        }
        if (item.getType() == Material.IRON_CHESTPLATE) {
            return true;
        }
        if (item.getType() == Material.IRON_HELMET) {
            return true;
        }
        if (item.getType() == Material.DIAMOND_BOOTS) {
            return true;
        }
        if (item.getType() == Material.DIAMOND_LEGGINGS) {
            return true;
        }
        if (item.getType() == Material.DIAMOND_CHESTPLATE) {
            return true;
        }
        if (item.getType() == Material.DIAMOND_HELMET) {
            return true;
        }
        if (item.getType() == Material.NETHERITE_BOOTS) {
            return true;
        }
        if (item.getType() == Material.NETHERITE_LEGGINGS) {
            return true;
        }
        if (item.getType() == Material.NETHERITE_CHESTPLATE) {
            return true;
        }
        if (item.getType() == Material.NETHERITE_HELMET) {
            return true;
        }
        if (item.getType() == Material.ZOMBIE_HEAD) {
            return true;
        }
        if (item.getType() == Material.SKELETON_SKULL) {
            return true;
        }
        if (item.getType() == Material.WITHER_SKELETON_SKULL) {
            return true;
        }
        if (item.getType() == Material.DRAGON_HEAD) {
            return true;
        }
        if (item.getType() == Material.CREEPER_HEAD) {
            return true;
        }
        if (item.getType() == Material.PLAYER_HEAD) {
            return true;
        }
        return item.getType() == Material.CARVED_PUMPKIN;
    }
}

