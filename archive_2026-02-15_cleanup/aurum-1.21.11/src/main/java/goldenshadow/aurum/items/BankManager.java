/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.entity.Player
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.inventory.InventoryCloseEvent
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.inventory.EquipmentSlot
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryHolder
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.persistence.PersistentDataContainer
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.util.io.BukkitObjectInputStream
 *  org.bukkit.util.io.BukkitObjectOutputStream
 */
package goldenshadow.aurum.items;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import goldenshadow.aurum.Aurum;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

public class BankManager {
    private static Map<UUID, String> bankData = new HashMap<UUID, String>();
    private static long lastSave = System.currentTimeMillis() + 600000L;

    public static void opened(PlayerInteractEvent event) {
        if (Aurum.getPlugin().getConfig().getBoolean("Bank")) {
            Player player = event.getPlayer();
            if (event.getHand() == EquipmentSlot.HAND && event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock() != null && event.getClickedBlock().getType().equals((Object)Material.ENDER_CHEST)) {
                event.setCancelled(true);
                ArrayList<ItemStack> bankItems = bankData.containsKey(player.getUniqueId()) ? BankManager.retrieveItems(player) : (player.getPersistentDataContainer().has(new NamespacedKey((Plugin)Aurum.getPlugin(), "bankItems"), PersistentDataType.STRING) ? BankManager.retrieveItemsFromNBT(player) : BankManager.retrieveItems(player));
                Inventory bank = Bukkit.createInventory((InventoryHolder)player, (int)54, (String)(player.getName() + "'s Bank"));
                if (bankItems.size() > 0) {
                    for (int i = 0; i < bank.getSize(); ++i) {
                        bank.setItem(i, bankItems.get(i));
                    }
                }
                player.openInventory(bank);
            }
        }
    }

    public static void closed(InventoryCloseEvent event) {
        Player player = (Player)event.getPlayer();
        if (event.getView().getTitle().equals(player.getName() + "'s Bank")) {
            ArrayList<ItemStack> items = new ArrayList<ItemStack>();
            Arrays.stream(event.getInventory().getContents()).forEach(item -> items.add(Objects.requireNonNullElseGet(item, () -> new ItemStack(Material.AIR))));
            BankManager.storeItems(items, player);
            if (System.currentTimeMillis() > lastSave) {
                BankManager.saveToFiles();
                lastSave = System.currentTimeMillis() + 600000L;
            }
        }
    }

    public static void join(Player player) {
        PersistentDataContainer data = player.getPersistentDataContainer();
        if (!data.has(new NamespacedKey((Plugin)Aurum.getPlugin(), "bankItems"), PersistentDataType.STRING)) {
            data.set(new NamespacedKey((Plugin)Aurum.getPlugin(), "bankItems"), PersistentDataType.STRING, "");
        }
    }

    private static void storeItems(List<ItemStack> items, Player player) {
        if (items.size() == 0) {
            bankData.put(player.getUniqueId(), "");
        } else {
            try {
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                BukkitObjectOutputStream outputStream = new BukkitObjectOutputStream((OutputStream)byteStream);
                for (ItemStack item : items) {
                    outputStream.writeObject((Object)item);
                }
                outputStream.flush();
                byte[] rawData = byteStream.toByteArray();
                String encodedData = Base64.getEncoder().encodeToString(rawData);
                bankData.put(player.getUniqueId(), encodedData);
                outputStream.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static ArrayList<ItemStack> retrieveItemsFromNBT(Player player) {
        PersistentDataContainer data = player.getPersistentDataContainer();
        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        String encodedItems = (String)data.get(new NamespacedKey((Plugin)Aurum.getPlugin(), "bankItems"), PersistentDataType.STRING);
        if (encodedItems != null && !encodedItems.isEmpty()) {
            byte[] rawData = Base64.getDecoder().decode(encodedItems);
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(rawData);
                BukkitObjectInputStream objectInputStream = new BukkitObjectInputStream((InputStream)byteArrayInputStream);
                for (int i = 0; i < 54; ++i) {
                    items.add((ItemStack)objectInputStream.readObject());
                }
                objectInputStream.close();
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return items;
    }

    private static ArrayList<ItemStack> retrieveItems(Player player) {
        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        String encodedItems = bankData.getOrDefault(player.getUniqueId(), "");
        if (!encodedItems.isEmpty()) {
            byte[] rawData = Base64.getDecoder().decode(encodedItems);
            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(rawData);
                BukkitObjectInputStream objectInputStream = new BukkitObjectInputStream((InputStream)byteArrayInputStream);
                for (int i = 0; i < 54; ++i) {
                    items.add((ItemStack)objectInputStream.readObject());
                }
                objectInputStream.close();
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return items;
    }

    public static void saveToFiles() {
        try {
            Gson gson = new GsonBuilder().create();
            File file = new File(Aurum.getPlugin().getDataFolder().getAbsolutePath() + "/bankData.json");
            file.getParentFile().mkdir();
            file.createNewFile();
            FileWriter writer = new FileWriter(file, false);
            gson.toJson(bankData, (Appendable)writer);
            ((Writer)writer).flush();
            ((Writer)writer).close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFromFile() {
        try {
            Type type;
            FileReader reader;
            Gson gson = new GsonBuilder().create();
            File file = new File(Aurum.getPlugin().getDataFolder().getAbsolutePath() + "/bankData.json");
            if (file.exists() && (bankData = (Map)gson.fromJson((Reader)(reader = new FileReader(file)), type = new TypeToken<HashMap<UUID, String>>(){}.getType())) == null) {
                bankData = new HashMap<UUID, String>();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

