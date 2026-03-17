/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.Sound
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Slime
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.util.Vector
 */
package goldenshadow.aurum.items;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.items.ItemFactory;
import goldenshadow.aurum.items.Rarity;
import goldenshadow.aurum.items.Treasure;
import goldenshadow.aurum.other.ExperienceManager;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class BossLootManager {
    private static final ItemFactory itemFactory = new ItemFactory();

    public static void createBossChest(Location location, int level) {
        assert (location.getWorld() != null);
        Slime hitbox = (Slime)location.getWorld().spawnEntity(location, EntityType.SLIME);
        hitbox.setInvulnerable(true);
        hitbox.setGravity(false);
        hitbox.setRemoveWhenFarAway(false);
        hitbox.setSize(2);
        hitbox.setInvisible(true);
        hitbox.setAI(false);
        hitbox.addScoreboardTag("aurum_boss_chest");
        hitbox.getPersistentDataContainer().set(new NamespacedKey((Plugin)Aurum.getPlugin(), "bossChestLevel"), PersistentDataType.INTEGER, (Object)level);
        ArmorStand display = (ArmorStand)location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        display.setMarker(true);
        display.addScoreboardTag("aurum_boss_chest");
        display.setInvulnerable(true);
        display.setInvisible(true);
        ItemStack item = new ItemStack(Material.STICK);
        ItemMeta meta = item.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(100084));
        item.setItemMeta(meta);
        assert (display.getEquipment() != null);
        display.getEquipment().setHelmet(item);
    }

    public static void interact(Player player, Slime slime) {
        Integer temp;
        int level = slime.getPersistentDataContainer().has(new NamespacedKey((Plugin)Aurum.getPlugin(), "bossChestLevel"), PersistentDataType.INTEGER) ? ((temp = (Integer)slime.getPersistentDataContainer().get(new NamespacedKey((Plugin)Aurum.getPlugin(), "bossChestLevel"), PersistentDataType.INTEGER)) != null ? temp : 1) : 1;
        if (!player.getScoreboardTags().contains("bossChest_" + level)) {
            player.addScoreboardTag("bossChest_" + level);
            float pitch = 1.0f;
            for (int i = 0; i < 16; i += 2) {
                if (i < 14) {
                    float finalPitch = pitch;
                    Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Aurum.getPlugin(), () -> slime.getWorld().playSound(slime.getLocation(), Sound.ENTITY_LLAMA_CHEST, 1.0f, finalPitch), (long)i);
                    pitch = (float)((double)pitch + 0.1);
                    continue;
                }
                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Aurum.getPlugin(), () -> slime.getWorld().playSound(slime.getLocation(), Sound.BLOCK_END_GATEWAY_SPAWN, 1.0f, 1.0f), 14L);
                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Aurum.getPlugin(), () -> BossLootManager.createLoot(slime.getLocation(), level, player.getUniqueId()), 14L);
                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Aurum.getPlugin(), () -> ExperienceManager.addXP(player, ExperienceManager.xpToNextLevel(level), true), 14L);
            }
        } else {
            player.sendMessage(ChatColor.GRAY + "You have already opened this chest...");
        }
    }

    private static void createLoot(Location location, int level, UUID ownerUUID) {
        assert (location.getWorld() != null);
        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        for (int i = 0; i < 5; ++i) {
            items.add(BossLootManager.generateEquipment(level));
        }
        items.add(BossLootManager.generateMoney());
        for (int j = 0; j < 3; ++j) {
            items.add(Treasure.getMobDrop(level));
        }
        for (ItemStack item : items) {
            Item itemEntity = (Item)location.getWorld().spawnEntity(location, EntityType.DROPPED_ITEM);
            itemEntity.setItemStack(item);
            itemEntity.setOwner(ownerUUID);
            itemEntity.setVelocity(BossLootManager.velocityFactory());
            itemEntity.setPickupDelay(20);
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Aurum.getPlugin(), () -> itemEntity.setOwner(null), 400L);
        }
    }

    private static ItemStack generateEquipment(int level) {
        int roll = ThreadLocalRandom.current().nextInt(0, 100);
        if (roll < 50) {
            return itemFactory.createRandomItem(Rarity.LEGENDARY, Treasure.shiftLevel(level));
        }
        if (roll < 80) {
            return itemFactory.createRandomItem(Rarity.EPIC, Treasure.shiftLevel(level));
        }
        if (roll < 90) {
            return itemFactory.createRandomItem(Rarity.RARE, Treasure.shiftLevel(level));
        }
        if (roll == 91) {
            return itemFactory.createRandomItem(Rarity.ARTIFACT, 0);
        }
        if (roll == 92) {
            return itemFactory.createRandomItem(Rarity.ELDRITCH, 0);
        }
        return itemFactory.createRandomItem(Rarity.COMMON, Treasure.shiftLevel(level));
    }

    private static ItemStack generateMoney() {
        int amount;
        int typeRoll = ThreadLocalRandom.current().nextInt(0, 3);
        ItemStack itemStack = switch (typeRoll) {
            case 0 -> {
                amount = ThreadLocalRandom.current().nextInt(1, 65);
                yield BossLootManager.itemFactory.miscItems.coinBronze();
            }
            case 1 -> {
                amount = ThreadLocalRandom.current().nextInt(1, 33);
                yield BossLootManager.itemFactory.miscItems.coinSilver();
            }
            default -> {
                amount = ThreadLocalRandom.current().nextInt(1, 17);
                yield BossLootManager.itemFactory.miscItems.coinGold();
            }
        };
        itemStack.setAmount(amount);
        return itemStack;
    }

    private static Vector velocityFactory() {
        return new Vector(ThreadLocalRandom.current().nextDouble(-0.2, 0.2), 0.5, ThreadLocalRandom.current().nextDouble(-0.2, 0.2));
    }
}

