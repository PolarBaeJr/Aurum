/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.Sound
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Slime
 *  org.bukkit.event.player.PlayerFishEvent
 *  org.bukkit.event.player.PlayerFishEvent$State
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package goldenshadow.aurum.items;

import goldenshadow.aurum.items.ItemFactory;
import goldenshadow.aurum.items.ItemHelper;
import goldenshadow.aurum.items.Rarity;
import goldenshadow.aurum.items.flags.Rune;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Treasure {
    private static final Map<UUID, Map<UUID, Long>> chestCooldowns = new HashMap<UUID, Map<UUID, Long>>();
    private static final ItemFactory itemFactory = new ItemFactory();
    private static final ItemHelper itemHelper = new ItemHelper();

    public static void resetChestCooldown(UUID uuid) {
        chestCooldowns.remove(uuid);
    }

    public static Inventory small_treasure(boolean gold_pact, boolean group, int level, Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, Component.text("Small Treasure Hoard"));
        for (int i = 0; i < inventory.getSize(); ++i) {
            if (gold_pact) {
                if (ThreadLocalRandom.current().nextInt(0, 3) != 0) continue;
                inventory.setItem(i, Treasure.populateSlot(group, level, player));
                continue;
            }
            if (ThreadLocalRandom.current().nextInt(0, 5) != 0) continue;
            inventory.setItem(i, Treasure.populateSlot(group, level, player));
        }
        return inventory;
    }

    public static Inventory medium_treasure(boolean gold_pact, boolean group, int level, Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, Component.text("Medium Treasure Hoard"));
        for (int i = 0; i < inventory.getSize(); ++i) {
            if (gold_pact) {
                if (ThreadLocalRandom.current().nextInt(0, 100) > 50) continue;
                inventory.setItem(i, Treasure.populateSlot(group, level, player));
                continue;
            }
            if (ThreadLocalRandom.current().nextInt(0, 100) > 35) continue;
            inventory.setItem(i, Treasure.populateSlot(group, level, player));
        }
        return inventory;
    }

    public static Inventory large_treasure(boolean gold_pact, boolean group, int level, Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, Component.text("Large Treasure Hoard"));
        for (int i = 0; i < inventory.getSize(); ++i) {
            if (gold_pact) {
                if (ThreadLocalRandom.current().nextInt(0, 100) > 70) continue;
                inventory.setItem(i, Treasure.populateSlot(group, level, player));
                continue;
            }
            if (ThreadLocalRandom.current().nextInt(0, 100) > 50) continue;
            inventory.setItem(i, Treasure.populateSlot(group, level, player));
        }
        return inventory;
    }

    public static void fishing(PlayerFishEvent event) {
        Entity entity;
        event.setExpToDrop(0);
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH && (entity = event.getCaught()) instanceof Item) {
            Item item = (Item)entity;
            int roll = ThreadLocalRandom.current().nextInt(0, 100);
            if (roll <= 20) {
                item.setItemStack(Treasure.populateSlot(false, Treasure.shiftLevel(event.getPlayer().getLevel()), event.getPlayer()));
            } else if (roll <= 40) {
                ItemStack itemStack = new ItemStack(Material.COD);
                ItemMeta meta = itemStack.getItemMeta();
                assert (meta != null);
                List<Component> lore = new ArrayList<>();
                meta.displayName(Component.text("Cod", NamedTextColor.GRAY).decorate(TextDecoration.BOLD));
                lore.add(Component.empty());
                lore.add(Component.text("Junk Item", NamedTextColor.GRAY));
                meta.lore(lore);
                itemStack.setItemMeta(meta);
                item.setItemStack(itemStack);
            } else if (roll <= 60) {
                ItemStack itemStack = new ItemStack(Material.SALMON);
                ItemMeta meta = itemStack.getItemMeta();
                assert (meta != null);
                List<Component> lore = new ArrayList<>();
                meta.displayName(Component.text("Salmon", NamedTextColor.GRAY).decorate(TextDecoration.BOLD));
                lore.add(Component.empty());
                lore.add(Component.text("Junk Item", NamedTextColor.GRAY));
                meta.lore(lore);
                itemStack.setItemMeta(meta);
                item.setItemStack(itemStack);
            } else if (roll <= 80) {
                ItemStack itemStack = new ItemStack(Material.TROPICAL_FISH);
                ItemMeta meta = itemStack.getItemMeta();
                assert (meta != null);
                List<Component> lore = new ArrayList<>();
                meta.displayName(Component.text("Clownfish", NamedTextColor.GRAY).decorate(TextDecoration.BOLD));
                lore.add(Component.empty());
                lore.add(Component.text("Junk Item", NamedTextColor.GRAY));
                meta.lore(lore);
                itemStack.setItemMeta(meta);
                item.setItemStack(itemStack);
            } else if (roll <= 82) {
                ItemStack itemStack = new ItemStack(Material.BUNDLE);
                ItemMeta meta = itemStack.getItemMeta();
                assert (meta != null);
                List<Component> lore = new ArrayList<>();
                meta.displayName(Component.text("Bundle", NamedTextColor.AQUA).decorate(TextDecoration.BOLD));
                lore.add(Component.empty());
                lore.add(Component.text("Rare Item", NamedTextColor.AQUA));
                meta.lore(lore);
                itemStack.setItemMeta(meta);
                item.setItemStack(itemStack);
            } else if (roll <= 84) {
                ItemStack itemStack = new ItemStack(Material.PRISMARINE_CRYSTALS);
                ItemMeta meta = itemStack.getItemMeta();
                assert (meta != null);
                List<Component> lore = new ArrayList<>();
                meta.displayName(Component.text("Ocean Crystal", NamedTextColor.GRAY).decorate(TextDecoration.BOLD));
                lore.add(Component.empty());
                lore.add(Component.text("Junk Item", NamedTextColor.GRAY));
                meta.lore(lore);
                itemStack.setItemMeta(meta);
                item.setItemStack(itemStack);
            } else if (roll <= 86) {
                ItemStack itemStack = new ItemStack(Material.NAUTILUS_SHELL);
                ItemMeta meta = itemStack.getItemMeta();
                assert (meta != null);
                List<Component> lore = new ArrayList<>();
                meta.displayName(Component.text("Seashell", NamedTextColor.GRAY).decorate(TextDecoration.BOLD));
                lore.add(Component.empty());
                lore.add(Component.text("Junk Item", NamedTextColor.GRAY));
                meta.lore(lore);
                itemStack.setItemMeta(meta);
                item.setItemStack(itemStack);
            } else if (roll <= 90) {
                ItemStack itemStack = new ItemStack(Material.BOOK);
                ItemMeta meta = itemStack.getItemMeta();
                assert (meta != null);
                List<Component> lore = new ArrayList<>();
                meta.displayName(Component.text("Wet Book", NamedTextColor.GRAY).decorate(TextDecoration.BOLD));
                lore.add(Component.empty());
                lore.add(Component.text("Junk Item", NamedTextColor.GRAY));
                meta.lore(lore);
                itemStack.setItemMeta(meta);
                item.setItemStack(itemStack);
            } else if (roll <= 95) {
                ItemStack itemStack = new ItemStack(Material.BOWL);
                ItemMeta meta = itemStack.getItemMeta();
                assert (meta != null);
                List<Component> lore = new ArrayList<>();
                meta.displayName(Component.text("Driftwood Bowl", NamedTextColor.GRAY).decorate(TextDecoration.BOLD));
                lore.add(Component.empty());
                lore.add(Component.text("Junk Item", NamedTextColor.GRAY));
                meta.lore(lore);
                itemStack.setItemMeta(meta);
                item.setItemStack(itemStack);
            } else {
                ItemStack itemStack = new ItemStack(Material.BONE);
                ItemMeta meta = itemStack.getItemMeta();
                assert (meta != null);
                List<Component> lore = new ArrayList<>();
                meta.displayName(Component.text("Fish Bone", NamedTextColor.GRAY).decorate(TextDecoration.BOLD));
                lore.add(Component.empty());
                lore.add(Component.text("Junk Item", NamedTextColor.GRAY));
                meta.lore(lore);
                itemStack.setItemMeta(meta);
                item.setItemStack(itemStack);
            }
        }
    }

    public static void chestOpen(Slime chest, Player player) {
        if (chestCooldowns.containsKey(player.getUniqueId()) && chestCooldowns.get(player.getUniqueId()).containsKey(chest.getUniqueId()) && chestCooldowns.get(player.getUniqueId()).get(chest.getUniqueId()) > System.currentTimeMillis()) {
            player.sendMessage(Component.text("This chest is still on cooldown! Wait " + Treasure.parseCurrentMillis(chestCooldowns.get(player.getUniqueId()).get(chest.getUniqueId()) - System.currentTimeMillis()) + ".", NamedTextColor.GRAY));
            return;
        }
        if (chest.getScoreboardTags().contains("aurum_chest_small")) {
            player.openInventory(Treasure.small_treasure(itemHelper.isRuneEquipped(player, Rune.GOLD_PACT), player.getNearbyEntities(5.0, 5.0, 5.0).stream().anyMatch(x -> x instanceof Player), player.getLevel(), player));
        } else if (chest.getScoreboardTags().contains("aurum_chest_medium")) {
            player.openInventory(Treasure.medium_treasure(itemHelper.isRuneEquipped(player, Rune.GOLD_PACT), player.getNearbyEntities(5.0, 5.0, 5.0).stream().anyMatch(x -> x instanceof Player), player.getLevel(), player));
        } else if (chest.getScoreboardTags().contains("aurum_chest_large")) {
            player.openInventory(Treasure.large_treasure(itemHelper.isRuneEquipped(player, Rune.GOLD_PACT), player.getNearbyEntities(5.0, 5.0, 5.0).stream().anyMatch(x -> x instanceof Player), player.getLevel(), player));
        }
        Map<UUID, Long> inner = new HashMap<>();
        if (chestCooldowns.containsKey(player.getUniqueId())) {
            inner = chestCooldowns.get(player.getUniqueId());
        }
        if (chest.getScoreboardTags().contains("aurum_chest_small")) {
            inner.put(chest.getUniqueId(), System.currentTimeMillis() + 3600000L);
        } else if (chest.getScoreboardTags().contains("aurum_chest_medium")) {
            inner.put(chest.getUniqueId(), System.currentTimeMillis() + 0x6DDD00L);
        } else if (chest.getScoreboardTags().contains("aurum_chest_large")) {
            inner.put(chest.getUniqueId(), System.currentTimeMillis() + 10800000L);
        }
        chestCooldowns.put(player.getUniqueId(), inner);
    }

    public static void treasureGlint() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getNearbyEntities(15.0, 15.0, 15.0).stream().filter(x -> x.getScoreboardTags().contains("aurum_chest")).forEach(x -> x.getWorld().spawnParticle(Particle.FIREWORK, x.getLocation().add(0.0, 0.2, 0.0), 10, 0.3, 0.3, 0.3, 0.05));
        }
    }

    public static ItemStack getMobDrop(int levelAverage) {
        return Treasure.populateSlot(false, Treasure.shiftLevel(levelAverage), null);
    }

    private static ItemStack populateSlot(boolean group, int level, Player player) {
        int roll = ThreadLocalRandom.current().nextInt(0, 10000);
        if (group) {
            roll = Math.max(ThreadLocalRandom.current().nextInt(0, 10000), ThreadLocalRandom.current().nextInt(0, 10000));
        }
        if (roll <= 3000) {
            return itemFactory.createRandomItem(Rarity.COMMON, Treasure.shiftLevel(level));
        }
        if (roll <= 4500) {
            return itemFactory.createRandomItem(Rarity.RARE, Treasure.shiftLevel(level));
        }
        if (roll <= 5100) {
            return itemFactory.createRandomItem(Rarity.EPIC, Treasure.shiftLevel(level));
        }
        if (roll <= 5297) {
            return itemFactory.createRandomItem(Rarity.LEGENDARY, Treasure.shiftLevel(level));
        }
        if (roll <= 5299) {
            if (level >= 50) {
                if (player != null) {
                    player.playSound((Entity)player, Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
                }
                return itemFactory.createRandomItem(Rarity.ARTIFACT, 0);
            }
            return itemFactory.createRandomItem(Rarity.LEGENDARY, Treasure.shiftLevel(level));
        }
        if (roll == 5300) {
            if (level >= 50) {
                if (player != null) {
                    player.playSound((Entity)player, Sound.BLOCK_END_PORTAL_SPAWN, 1.0f, 1.0f);
                }
                return itemFactory.createRandomItem(Rarity.ELDRITCH, 0);
            }
            return itemFactory.createRandomItem(Rarity.LEGENDARY, Treasure.shiftLevel(level));
        }
        if (roll <= 8300) {
            ItemStack item = new ItemStack(Treasure.itemFactory.miscItems.coinBronze());
            item.setAmount(ThreadLocalRandom.current().nextInt(1, 17));
            return item;
        }
        if (roll <= 8800) {
            ItemStack item = new ItemStack(Treasure.itemFactory.miscItems.coinSilver());
            item.setAmount(ThreadLocalRandom.current().nextInt(1, 5));
            return item;
        }
        if (roll <= 8900) {
            return Treasure.itemFactory.miscItems.coinGold();
        }
        if (roll <= 9900) {
            int i = ThreadLocalRandom.current().nextInt(0, 5);
            return switch (i) {
                case 0 -> Treasure.itemFactory.miscItems.shardJungle();
                case 1 -> Treasure.itemFactory.miscItems.shardEnchantedForest();
                case 2 -> Treasure.itemFactory.miscItems.shardSnow();
                case 3 -> Treasure.itemFactory.miscItems.shardDesert();
                default -> Treasure.itemFactory.miscItems.shardLava();
            };
        }
        return Treasure.getRandomRune(level);
    }

    public static int shiftLevel(int level) {
        return Math.max(Math.min(ThreadLocalRandom.current().nextInt(level - 5, level + 5), 100), 1);
    }

    private static String parseCurrentMillis(long timeMillis) {
        double totalSeconds = (double)timeMillis / 1000.0;
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

    private static ItemStack getRandomRune(int level) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        if (level >= 20) {
            list.add(Treasure.itemFactory.miscItems.runeCharge());
            list.add(Treasure.itemFactory.miscItems.runeFishLung());
            list.add(Treasure.itemFactory.miscItems.runePirouette());
        }
        if (level >= 30) {
            list.add(Treasure.itemFactory.miscItems.runeShockWave());
            list.add(Treasure.itemFactory.miscItems.runeDistortion());
            list.add(Treasure.itemFactory.miscItems.runeSwiftness());
        }
        if (level >= 40) {
            list.add(Treasure.itemFactory.miscItems.runeHeal());
            list.add(Treasure.itemFactory.miscItems.runeFireball());
            list.add(Treasure.itemFactory.miscItems.runeArcaneRay());
        }
        if (level >= 50) {
            list.add(Treasure.itemFactory.miscItems.runeVitality());
            list.add(Treasure.itemFactory.miscItems.runeFallingStar());
            list.add(Treasure.itemFactory.miscItems.runeDragonSkin());
            list.add(Treasure.itemFactory.miscItems.runeGroundSlam());
        }
        if (level >= 60) {
            list.add(Treasure.itemFactory.miscItems.runeResurgence());
            list.add(Treasure.itemFactory.miscItems.runeSmite());
            list.add(Treasure.itemFactory.miscItems.runeGrace());
            list.add(Treasure.itemFactory.miscItems.runeRitual());
        }
        if (level >= 70) {
            list.add(Treasure.itemFactory.miscItems.runeRestoration());
            list.add(Treasure.itemFactory.miscItems.runeWindSlash());
            list.add(Treasure.itemFactory.miscItems.runeArcaneShield());
        }
        if (level >= 80) {
            list.add(Treasure.itemFactory.miscItems.runeBloodRush());
            list.add(Treasure.itemFactory.miscItems.runeRegeneration());
        }
        if (list.size() == 0) {
            return null;
        }
        return (ItemStack)list.get(ThreadLocalRandom.current().nextInt(0, list.size()));
    }
}
