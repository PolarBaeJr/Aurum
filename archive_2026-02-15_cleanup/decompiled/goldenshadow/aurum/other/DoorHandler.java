/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.World
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Slime
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.persistence.PersistentDataContainer
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 */
package goldenshadow.aurum.other;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.other.Direction;
import goldenshadow.aurum.other.Door;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class DoorHandler {
    private static final ArrayList<Door> activeDoors = new ArrayList();
    private static final ArrayList<Door> toDelete = new ArrayList();
    private static final Plugin plugin = Aurum.getPlugin();

    public static void removeActiveDoor(Door door) {
        toDelete.add(door);
    }

    public static void doorLoop() {
        for (Door d : toDelete) {
            activeDoors.removeIf(x -> x.equals(d));
        }
        toDelete.clear();
        activeDoors.forEach(Door::tick);
    }

    public static void spawnEntities(Location location, Direction direction, int requiredTokens, String openCommand, String closeCommand, boolean snowDoor, Material singleBlock, Location singleBlockLoc) {
        assert (location.getWorld() != null);
        ArmorStand textLow = (ArmorStand)location.getWorld().spawnEntity(new Location(location.getWorld(), DoorHandler.roundToHalf(location.getX()), DoorHandler.roundToHalf(location.getY()) + 0.2, DoorHandler.roundToHalf(location.getZ())), EntityType.ARMOR_STAND);
        textLow.setInvulnerable(true);
        textLow.setGravity(false);
        textLow.setVisible(false);
        textLow.setMarker(true);
        textLow.getScoreboardTags().add("aurum_door");
        textLow.setCustomNameVisible(true);
        textLow.setCustomName(ChatColor.GRAY + "to open the door");
        ArmorStand textMid = (ArmorStand)location.getWorld().spawnEntity(new Location(location.getWorld(), DoorHandler.roundToHalf(location.getX()), DoorHandler.roundToHalf(location.getY()) + 0.5, DoorHandler.roundToHalf(location.getZ())), EntityType.ARMOR_STAND);
        textMid.setInvulnerable(true);
        textMid.setGravity(false);
        textMid.setVisible(false);
        textMid.setMarker(true);
        textMid.getScoreboardTags().add("aurum_door");
        textMid.getScoreboardTags().add("aurum_door_display");
        textMid.setCustomNameVisible(true);
        textMid.setCustomName(ChatColor.GRAY + "Get " + ChatColor.YELLOW + "[0/" + requiredTokens + " Tokens]");
        ArmorStand textUp = (ArmorStand)location.getWorld().spawnEntity(new Location(location.getWorld(), DoorHandler.roundToHalf(location.getX()), DoorHandler.roundToHalf(location.getY()) + 0.8, DoorHandler.roundToHalf(location.getZ())), EntityType.ARMOR_STAND);
        textUp.setInvulnerable(true);
        textUp.setGravity(false);
        textUp.setVisible(false);
        textUp.setMarker(true);
        textUp.getScoreboardTags().add("aurum_door");
        textUp.setCustomNameVisible(true);
        textUp.setCustomName(ChatColor.RED + String.valueOf(ChatColor.BOLD) + "SLAY!");
        Slime doorHitbox = (Slime)location.getWorld().spawnEntity(new Location(location.getWorld(), DoorHandler.roundToHalf(location.getX()), DoorHandler.roundToHalf(location.getY()), DoorHandler.roundToHalf(location.getZ())), EntityType.SLIME);
        doorHitbox.setSize(3);
        doorHitbox.setInvisible(true);
        doorHitbox.setAI(false);
        doorHitbox.setRemoveWhenFarAway(false);
        doorHitbox.setGravity(false);
        doorHitbox.setInvulnerable(true);
        doorHitbox.getScoreboardTags().add("aurum_door");
        doorHitbox.getPersistentDataContainer().set(new NamespacedKey(plugin, "doorTokensReq"), PersistentDataType.INTEGER, (Object)requiredTokens);
        doorHitbox.getPersistentDataContainer().set(new NamespacedKey(plugin, "doorLocationX"), PersistentDataType.DOUBLE, (Object)(direction == Direction.EAST ? location.getX() + 1.0 : (direction == Direction.WEST ? location.getX() - 1.0 : location.getX())));
        doorHitbox.getPersistentDataContainer().set(new NamespacedKey(plugin, "doorLocationZ"), PersistentDataType.DOUBLE, (Object)(direction == Direction.SOUTH ? location.getZ() + 1.0 : (direction == Direction.NORTH ? location.getZ() - 1.0 : location.getZ())));
        doorHitbox.getPersistentDataContainer().set(new NamespacedKey(plugin, "doorLocationY"), PersistentDataType.DOUBLE, (Object)location.getY());
        if (direction == null) {
            direction = Direction.NORTH;
        }
        doorHitbox.getPersistentDataContainer().set(new NamespacedKey(plugin, "doorDirection"), PersistentDataType.STRING, (Object)direction.toString());
        doorHitbox.getPersistentDataContainer().set(new NamespacedKey(plugin, "doorSnow"), PersistentDataType.STRING, (Object)Boolean.toString(snowDoor));
        if (openCommand != null && closeCommand != null) {
            doorHitbox.getPersistentDataContainer().set(new NamespacedKey(plugin, "doorOpenCommand"), PersistentDataType.STRING, (Object)openCommand);
            doorHitbox.getPersistentDataContainer().set(new NamespacedKey(plugin, "doorCloseCommand"), PersistentDataType.STRING, (Object)closeCommand);
        }
        double bX = singleBlockLoc != null ? singleBlockLoc.getX() : 0.0;
        double bY = singleBlockLoc != null ? singleBlockLoc.getY() : 0.0;
        double bZ = singleBlockLoc != null ? singleBlockLoc.getZ() : 0.0;
        doorHitbox.getPersistentDataContainer().set(new NamespacedKey(plugin, "doorBlockLocationX"), PersistentDataType.DOUBLE, (Object)bX);
        doorHitbox.getPersistentDataContainer().set(new NamespacedKey(plugin, "doorBlockLocationY"), PersistentDataType.DOUBLE, (Object)bY);
        doorHitbox.getPersistentDataContainer().set(new NamespacedKey(plugin, "doorBlockLocationZ"), PersistentDataType.DOUBLE, (Object)bZ);
        doorHitbox.getPersistentDataContainer().set(new NamespacedKey(plugin, "doorSingleBlock"), PersistentDataType.STRING, (Object)(singleBlock != null ? singleBlock.toString() : Material.AIR.toString()));
    }

    public static void rightClick(Slime slime, Player player) {
        int tokens = DoorHandler.countTokens(player, true) + DoorHandler.countTokens(player, false);
        if (tokens > 0) {
            if (activeDoors.stream().noneMatch(x -> x.getRoot().equals(slime))) {
                activeDoors.add(DoorHandler.buildDoor(slime));
            }
            for (Door d : activeDoors) {
                if (!d.getRoot().equals(slime)) continue;
                if (d.isMiniBossDoor()) {
                    d.addTokens(DoorHandler.countTokens(player, true));
                    DoorHandler.clearTokens(player);
                    DoorHandler.clearMiniBossTokens(player);
                    continue;
                }
                d.addTokens(DoorHandler.countTokens(player, false));
                DoorHandler.clearTokens(player);
            }
        }
    }

    public static Door buildDoor(Slime slime) {
        boolean snow;
        Material material;
        ArmorStand display = null;
        for (Entity e : slime.getNearbyEntities(3.0, 3.0, 3.0)) {
            if (!(e instanceof ArmorStand) || !e.getScoreboardTags().contains("aurum_door_display")) continue;
            display = (ArmorStand)e;
        }
        boolean isMiniBossDoor = slime.getPersistentDataContainer().has(new NamespacedKey(plugin, "doorIsMiniBossDoor"), PersistentDataType.BYTE);
        String singleBlockString = (String)slime.getPersistentDataContainer().get(new NamespacedKey(plugin, "doorSingleBlock"), PersistentDataType.STRING);
        Location location = DoorHandler.buildLocation(slime.getPersistentDataContainer(), slime.getWorld(), "doorLocationX", "doorLocationY", "doorLocationZ");
        Location blockLoc = DoorHandler.buildLocation(slime.getPersistentDataContainer(), slime.getWorld(), "doorBlockLocationX", "doorBlockLocationY", "doorBlockLocationZ");
        Integer tokensReq = (Integer)slime.getPersistentDataContainer().get(new NamespacedKey(plugin, "doorTokensReq"), PersistentDataType.INTEGER);
        assert (tokensReq != null);
        String openCommand = (String)slime.getPersistentDataContainer().get(new NamespacedKey(plugin, "doorOpenCommand"), PersistentDataType.STRING);
        String closeCommand = (String)slime.getPersistentDataContainer().get(new NamespacedKey(plugin, "doorCloseCommand"), PersistentDataType.STRING);
        if (openCommand != null) {
            return new Door(slime, display, (int)tokensReq, openCommand, closeCommand, isMiniBossDoor);
        }
        if (singleBlockString != null && (material = Material.valueOf((String)singleBlockString)) != Material.AIR) {
            return new Door(slime, display, (int)tokensReq, material, blockLoc, isMiniBossDoor);
        }
        String snowDoorString = (String)slime.getPersistentDataContainer().get(new NamespacedKey(plugin, "doorSnow"), PersistentDataType.STRING);
        if (snowDoorString != null && (snow = Boolean.parseBoolean(snowDoorString))) {
            return new Door(slime, display, tokensReq, isMiniBossDoor);
        }
        Direction direction = null;
        String dir = (String)slime.getPersistentDataContainer().get(new NamespacedKey(plugin, "doorDirection"), PersistentDataType.STRING);
        if (dir != null) {
            direction = Direction.valueOf(dir);
        }
        return new Door(slime, display, location, direction, tokensReq, isMiniBossDoor);
    }

    private static Location buildLocation(PersistentDataContainer data, World world, String keyX, String keyY, String keyZ) {
        Double x = (Double)data.get(new NamespacedKey(plugin, keyX), PersistentDataType.DOUBLE);
        Double y = (Double)data.get(new NamespacedKey(plugin, keyY), PersistentDataType.DOUBLE);
        Double z = (Double)data.get(new NamespacedKey(plugin, keyZ), PersistentDataType.DOUBLE);
        assert (x != null && y != null && z != null);
        return new Location(world, x.doubleValue(), y.doubleValue(), z.doubleValue());
    }

    public static int countTokens(Player player, boolean miniBossTokens) {
        int count = 0;
        for (int i = 0; i < player.getInventory().getSize(); ++i) {
            ItemStack itemStack = player.getInventory().getItem(i);
            if (itemStack == null || itemStack.getItemMeta() == null) continue;
            if (miniBossTokens) {
                if (!itemStack.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "doorMiniBossToken"), PersistentDataType.BYTE)) continue;
                count += itemStack.getAmount();
                continue;
            }
            if (!itemStack.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "doorToken"), PersistentDataType.BYTE)) continue;
            count += itemStack.getAmount();
        }
        return count;
    }

    public static void clearTokens(Player player) {
        for (int i = 0; i < player.getInventory().getSize(); ++i) {
            ItemStack itemStack = player.getInventory().getItem(i);
            if (itemStack == null || itemStack.getItemMeta() == null || !itemStack.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "doorToken"), PersistentDataType.BYTE)) continue;
            player.getInventory().setItem(i, new ItemStack(Material.AIR));
        }
    }

    public static void clearMiniBossTokens(Player player) {
        for (int i = 0; i < player.getInventory().getSize(); ++i) {
            ItemStack itemStack = player.getInventory().getItem(i);
            if (itemStack == null || itemStack.getItemMeta() == null || !itemStack.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(plugin, "doorMiniBossToken"), PersistentDataType.BYTE)) continue;
            player.getInventory().setItem(i, new ItemStack(Material.AIR));
        }
    }

    public static void addDoor(Door door) {
        if (activeDoors.stream().noneMatch(d -> d.getRoot().equals(door.getRoot()))) {
            activeDoors.add(door);
        }
    }

    public static List<Door> getActiveDoors() {
        return activeDoors;
    }

    private static double roundToHalf(double num) {
        return (int)num < 0 ? (double)((int)num) - 0.5 : (double)((int)num) + 0.5;
    }
}

