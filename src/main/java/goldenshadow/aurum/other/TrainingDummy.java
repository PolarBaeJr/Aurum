/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.adventure.text.Component
 *  net.kyori.adventure.text.format.NamedTextColor
 *  net.kyori.adventure.text.format.TextColor
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Skeleton
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 */
package goldenshadow.aurum.other;

import goldenshadow.aurum.Aurum;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class TrainingDummy {
    private static final Map<UUID, List<Integer>> taskIds = new HashMap<UUID, List<Integer>>();

    public static void spawnNPC(Location location) {
        assert (location.getWorld() != null);
        Skeleton dummy = (Skeleton)location.getWorld().spawnEntity(location, EntityType.SKELETON, false);
        ArmorStand name = (ArmorStand)location.getWorld().spawnEntity(location.add(0.0, 2.3, 0.0), EntityType.ARMOR_STAND);
        name.setInvisible(true);
        name.setMarker(true);
        name.customName((Component)Component.text((String)"Training Dummy", (TextColor)NamedTextColor.YELLOW));
        name.setCustomNameVisible(true);
        name.addScoreboardTag("aurum_training_dummy");
        dummy.setAI(false);
        dummy.addScoreboardTag("aurum_training_dummy");
        dummy.addScoreboardTag("aurum_mob");
        Objects.requireNonNull(dummy.getAttribute(Attribute.MAX_HEALTH)).setBaseValue(10000.0);
        dummy.setHealth(10000.0);
        dummy.customName((Component)Component.text((String)"0 Damage", (TextColor)NamedTextColor.GRAY));
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
        s.customName((Component)Component.text((String)((int)damage + " Damage"), (TextColor)NamedTextColor.GRAY));
        s.setHealth(10000.0);
        int id = Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Aurum.getPlugin(), () -> s.customName((Component)Component.text((String)"0 Damage", (TextColor)NamedTextColor.GRAY)), 40L);
        if (taskIds.containsKey(s.getUniqueId())) {
            taskIds.get(s.getUniqueId()).add(id);
        } else {
            taskIds.put(s.getUniqueId(), new ArrayList<Integer>(List.of(Integer.valueOf(id))));
        }
    }

    private static ItemStack getHead() {
        return new ItemStack(Material.SKELETON_SKULL);
    }
}

