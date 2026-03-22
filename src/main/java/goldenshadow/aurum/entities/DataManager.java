/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.adventure.text.Component
 *  net.kyori.adventure.text.format.NamedTextColor
 *  net.kyori.adventure.text.format.TextColor
 *  org.bukkit.Bukkit
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.Particle$DustOptions
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 */
package goldenshadow.aurum.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.entities.EntityData;
import goldenshadow.aurum.entities.IntervalSpawner;
import goldenshadow.aurum.entities.RegistryTypeAdapters;
import goldenshadow.aurum.entities.RespawnLocationAdapter;
import goldenshadow.aurum.entities.SpawnInterval;
import goldenshadow.aurum.entities.SpawnLocation;
import goldenshadow.aurum.entities.SpawnLocationAdapter;
import goldenshadow.aurum.other.RespawnLocation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class DataManager {
    private static List<SpawnLocation> locationList = new ArrayList<SpawnLocation>();
    private static List<RespawnLocation> respawnLocationList = new ArrayList<RespawnLocation>();
    private static HashMap<String, EntityData> mobMap = new HashMap();
    private static final List<List<SpawnLocation>> splitList = new ArrayList<List<SpawnLocation>>();
    private static IntervalSpawner fast_spawner;
    private static IntervalSpawner normal_spawner;
    private static IntervalSpawner slow_spawner;
    private static IntervalSpawner very_slow_spawner;
    private static long lastBackup;

    public static void loadFromFile() throws FileNotFoundException {
        Type type;
        FileReader mobsReader;
        FileReader respawnReader;
        RespawnLocation[] respawnLocArray;
        FileReader locationsReader;
        SpawnLocation[] spawnLocArray;
        Gson gson = RegistryTypeAdapters.registerAll(new GsonBuilder()).registerTypeAdapter((Type)((Object)SpawnLocation.class), new SpawnLocationAdapter()).registerTypeAdapter((Type)((Object)RespawnLocation.class), new RespawnLocationAdapter()).create();
        File locationsFile = new File(Aurum.getPlugin().getDataFolder().getAbsolutePath() + "/spawn_locations.json");
        File respawnFile = new File(Aurum.getPlugin().getDataFolder().getAbsolutePath() + "/respawn_locations.json");
        File mobsFile = new File(Aurum.getPlugin().getDataFolder().getAbsolutePath() + "/mobs.json");
        if (locationsFile.exists() && (spawnLocArray = gson.fromJson((Reader)(locationsReader = new FileReader(locationsFile)), SpawnLocation[].class)) != null) {
            locationList = new ArrayList<SpawnLocation>(Arrays.asList(spawnLocArray));
        }
        if (respawnFile.exists() && (respawnLocArray = gson.fromJson((Reader)(respawnReader = new FileReader(respawnFile)), RespawnLocation[].class)) != null) {
            respawnLocationList = new ArrayList<RespawnLocation>(Arrays.asList(respawnLocArray));
        }
        if (mobsFile.exists() && (mobMap = (HashMap)gson.fromJson((Reader)(mobsReader = new FileReader(mobsFile)), type = new TypeToken<HashMap<String, EntityData>>(){}.getType())) == null) {
            mobMap = new HashMap();
        }
        DataManager.splitLocations();
        fast_spawner = new IntervalSpawner(splitList.get(0), 15);
        normal_spawner = new IntervalSpawner(splitList.get(1), 30);
        slow_spawner = new IntervalSpawner(splitList.get(2), 60);
        very_slow_spawner = new IntervalSpawner(splitList.get(3), 150);
    }

    public static void saveToFiles() throws IOException {
        DataManager.deleteUnusedLocations();
        Gson gson = RegistryTypeAdapters.registerAll(new GsonBuilder()).registerTypeAdapter((Type)((Object)SpawnLocation.class), new SpawnLocationAdapter()).registerTypeAdapter((Type)((Object)RespawnLocation.class), new RespawnLocationAdapter()).create();
        File locationsFile = new File(Aurum.getPlugin().getDataFolder().getAbsolutePath() + "/spawn_locations.json");
        File respawnFile = new File(Aurum.getPlugin().getDataFolder().getAbsolutePath() + "/respawn_locations.json");
        File mobsFile = new File(Aurum.getPlugin().getDataFolder().getAbsolutePath() + "/mobs.json");
        if (System.currentTimeMillis() > lastBackup) {
            DataManager.backUpFiles();
            lastBackup = System.currentTimeMillis() + 300000L;
        }
        locationsFile.getParentFile().mkdir();
        mobsFile.getParentFile().mkdir();
        respawnFile.getParentFile().mkdir();
        locationsFile.createNewFile();
        mobsFile.createNewFile();
        locationsFile.createNewFile();
        FileWriter locWriter = new FileWriter(locationsFile, false);
        FileWriter mobWriter = new FileWriter(mobsFile, false);
        FileWriter respawnWriter = new FileWriter(respawnFile, false);
        gson.toJson(mobMap, (Appendable)mobWriter);
        gson.toJson(locationList, (Appendable)locWriter);
        gson.toJson(respawnLocationList, (Appendable)respawnWriter);
        ((Writer)mobWriter).flush();
        ((Writer)locWriter).flush();
        ((Writer)respawnWriter).flush();
        ((Writer)mobWriter).close();
        ((Writer)locWriter).close();
        ((Writer)respawnWriter).close();
    }

    public static void spawnTick() {
        if (fast_spawner == null || normal_spawner == null || slow_spawner == null || very_slow_spawner == null) {
            return;
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.getScoreboardTags().contains("aurum_debug_spawning")) continue;
            p.sendMessage((Component)Component.text((String)"--------------------", (TextColor)NamedTextColor.AQUA));
        }
        fast_spawner.ticker();
        normal_spawner.ticker();
        slow_spawner.ticker();
        very_slow_spawner.ticker();
    }

    public static void highlightLoop() {
        if (Bukkit.getServer().getOnlinePlayers().stream().anyMatch(x -> x.getScoreboardTags().contains("aurum_debug_nodes"))) {
            for (int i = 0; i < 4; ++i) {
                for (SpawnLocation location : splitList.get(i)) {
                    for (Entity entity : location.getWorld().getNearbyEntities(location.getLocation(), 30.0, 30.0, 30.0, x -> x.getScoreboardTags().contains("aurum_debug_nodes"))) {
                        if (!(entity instanceof Player)) continue;
                        Player player = (Player)entity;
                        Particle.DustOptions dustOptions = location.getSpawn_interval() == SpawnInterval.FAST ? new Particle.DustOptions(Color.RED, 3.0f) : (location.getSpawn_interval() == SpawnInterval.NORMAL ? new Particle.DustOptions(Color.YELLOW, 3.0f) : (location.getSpawn_interval() == SpawnInterval.SLOW ? new Particle.DustOptions(Color.LIME, 3.0f) : new Particle.DustOptions(Color.AQUA, 3.0f)));
                        player.spawnParticle(Particle.DUST, location.getLocation(), 10,dustOptions);
                    }
                }
            }
        }
    }

    private static void splitLocations() {
        splitList.clear();
        ArrayList<SpawnLocation> very_slow = new ArrayList<SpawnLocation>();
        ArrayList<SpawnLocation> slow = new ArrayList<SpawnLocation>();
        ArrayList<SpawnLocation> normal = new ArrayList<SpawnLocation>();
        ArrayList<SpawnLocation> fast = new ArrayList<SpawnLocation>();
        for (SpawnLocation location : locationList) {
            if (location.getSpawn_interval() == SpawnInterval.FAST) {
                fast.add(location);
                continue;
            }
            if (location.getSpawn_interval() == SpawnInterval.NORMAL) {
                normal.add(location);
                continue;
            }
            if (location.getSpawn_interval() == SpawnInterval.SLOW) {
                slow.add(location);
                continue;
            }
            if (location.getSpawn_interval() != SpawnInterval.VERY_SLOW) continue;
            very_slow.add(location);
        }
        splitList.add(fast);
        splitList.add(normal);
        splitList.add(slow);
        splitList.add(very_slow);
    }

    public static void removeMob(String key) {
        mobMap.remove(key);
    }

    public static void removeLocation(UUID uuid) {
        for (SpawnLocation loc : locationList) {
            if (!loc.getUuid().equals(uuid)) continue;
            locationList.remove(loc);
            break;
        }
    }

    public static void addMob(EntityData mob, String name) {
        if (mobMap.containsKey(name)) {
            return;
        }
        mobMap.put(name, mob);
    }

    public static void addSpawnLocation(Location location, String entity, SpawnInterval spawnInterval, int respawnTolerance) {
        if (mobMap.containsKey(entity)) {
            locationList.add(new SpawnLocation(location.getWorld(), location.getX(), location.getY(), location.getZ(), entity, spawnInterval, respawnTolerance));
        }
    }

    public static void deleteUnusedLocations() {
        locationList.removeIf(location -> !mobMap.containsKey(location.getEntity()));
    }

    public static List<String> getListOfMobKeys() {
        if (mobMap != null) {
            return new ArrayList<String>(mobMap.keySet());
        }
        return new ArrayList<String>();
    }

    public static EntityData getMobByName(String name) {
        if (mobMap.containsKey(name)) {
            return mobMap.get(name);
        }
        return null;
    }

    public static List<SpawnLocation> getSpawnLocationList() {
        return locationList;
    }

    public static List<RespawnLocation> getRespawnLocationList() {
        return respawnLocationList;
    }

    public static void addRespawnLocation(Location location, int radius) {
        respawnLocationList.add(new RespawnLocation(location.getWorld(), location.getX(), location.getY(), location.getZ(), radius));
    }

    public static void removeRespawnLocation(UUID uuid) {
        for (RespawnLocation loc : respawnLocationList) {
            if (!loc.getUuid().equals(uuid)) continue;
            respawnLocationList.remove(loc);
            break;
        }
    }

    public static void backUpFiles() {
        try {
            File locationsFile = new File(Aurum.getPlugin().getDataFolder().getAbsolutePath() + "/spawn_locations.json");
            File respawnFile = new File(Aurum.getPlugin().getDataFolder().getAbsolutePath() + "/respawn_locations.json");
            File mobsFile = new File(Aurum.getPlugin().getDataFolder().getAbsolutePath() + "/mobs.json");
            File itemFile = new File(Aurum.getPlugin().getDataFolder().getAbsolutePath() + "/items.json");
            File groupFile = new File(Aurum.getPlugin().getDataFolder().getAbsolutePath() + "/item_groups.json");
            File[] files = new File[]{locationsFile, respawnFile, mobsFile, itemFile, groupFile};
            SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss");
            String backupDirectory = Aurum.getPlugin().getDataFolder().getAbsolutePath() + "/backups/" + sdf.format(new Date());
            Files.createDirectories(Paths.get(backupDirectory, new String[0]), new FileAttribute[0]);
            for (File file : files) {
                Path sourceFile = file.toPath();
                Path targetFile = Paths.get(backupDirectory + "/" + file.getName(), new String[0]);
                Files.copy(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getIntervalSpawnerInfo(Player player) {
        if (fast_spawner == null || normal_spawner == null || slow_spawner == null || very_slow_spawner == null) {
            player.sendMessage((Component)Component.text((String)"Spawners not initialized!", (TextColor)NamedTextColor.RED));
            return;
        }
        player.sendMessage(fast_spawner.toComponent());
        player.sendMessage(normal_spawner.toComponent());
        player.sendMessage(slow_spawner.toComponent());
        player.sendMessage(very_slow_spawner.toComponent());
    }

    static {
        lastBackup = System.currentTimeMillis() + 300000L;
    }
}

