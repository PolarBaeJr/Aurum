/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  com.mojang.authlib.properties.Property
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Skeleton
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.inventory.meta.SkullMeta
 *  org.bukkit.plugin.Plugin
 */
package goldenshadow.aurum.other;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import goldenshadow.aurum.Aurum;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

public class TrainingDummy {
    private static final Map<UUID, List<Integer>> taskIds = new HashMap<UUID, List<Integer>>();

    public static void spawnNPC(Location location) {
        assert (location.getWorld() != null);
        Skeleton dummy = (Skeleton)location.getWorld().spawnEntity(location, EntityType.SKELETON, false);
        ArmorStand name = (ArmorStand)location.getWorld().spawnEntity(location.add(0.0, 2.3, 0.0), EntityType.ARMOR_STAND);
        name.setInvisible(true);
        name.setMarker(true);
        name.setCustomName(ChatColor.YELLOW + "Training Dummy");
        name.setCustomNameVisible(true);
        name.addScoreboardTag("aurum_training_dummy");
        dummy.setAI(false);
        dummy.addScoreboardTag("aurum_training_dummy");
        dummy.addScoreboardTag("aurum_mob");
        Objects.requireNonNull(dummy.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(10000.0);
        dummy.setHealth(10000.0);
        dummy.setCustomName(ChatColor.GRAY + "0 Damage");
        dummy.setCustomNameVisible(true);
        dummy.setRemoveWhenFarAway(false);
        assert (dummy.getEquipment() != null);
        dummy.getEquipment().setHelmet(TrainingDummy.getHead(), true);
        dummy.setMaximumNoDamageTicks(5);
    }

    public static void hit(Skeleton s, double damage) {
        if (taskIds.containsKey(s.getUniqueId())) {
            taskIds.get(s.getUniqueId()).forEach(x -> Bukkit.getScheduler().cancelTask(x.intValue()));
            taskIds.remove(s.getUniqueId());
        }
        s.setCustomName(ChatColor.GRAY + Integer.toString((int)damage) + " Damage");
        s.setHealth(10000.0);
        int id = Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Aurum.getPlugin(), () -> s.setCustomName(ChatColor.GRAY + "0 Damage"), 40L);
        if (taskIds.containsKey(s.getUniqueId())) {
            taskIds.get(s.getUniqueId()).add(id);
        } else {
            taskIds.put(s.getUniqueId(), new ArrayList<Integer>(List.of(Integer.valueOf(id))));
        }
    }

    private static ItemStack getHead() {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta)itemStack.getItemMeta();
        assert (meta != null);
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "");
        gameProfile.getProperties().put((Object)"textures", (Object)new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzVmNzViYWUxMzQ5NmI5N2Y1NWRjNDJmYzM3MjQyYTg4MTU4OTUyYjZjY2U5M2MwNTdhYjAyOGFjYmE4MGIyMCJ9fX0="));
        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, gameProfile);
        }
        catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        itemStack.setItemMeta((ItemMeta)meta);
        return itemStack;
    }
}

