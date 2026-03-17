/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemStack
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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

public class ItemDataManager {
    private static Map<String, ItemStack> itemMap = new HashMap<String, ItemStack>();
    private static Map<String, List<String>> groupMap = new HashMap<String, List<String>>();

    public static void loadFromFile() {
        try {
            Gson gson = new GsonBuilder().create();
            File itemFile = new File(Aurum.getPlugin().getDataFolder().getAbsolutePath() + "/items.json");
            File groupFile = new File(Aurum.getPlugin().getDataFolder().getAbsolutePath() + "/item_groups.json");
            if (itemFile.exists()) {
                FileReader reader = new FileReader(itemFile);
                Type type = new TypeToken<HashMap<String, String>>(){}.getType();
                Map<String, String> encodedMap = gson.fromJson((Reader)reader, type);
                if (encodedMap == null) {
                    itemMap = new HashMap<String, ItemStack>();
                } else {
                    itemMap.clear();
                    for (Map.Entry<String, String> entry : encodedMap.entrySet()) {
                        itemMap.put(entry.getKey(), ItemDataManager.decodeItem(entry.getValue()));
                    }
                }
            }
            if (groupFile.exists()) {
                FileReader reader2 = new FileReader(groupFile);
                Type type2 = new TypeToken<HashMap<String, List<String>>>(){}.getType();
                Map<String, List<String>> groups = gson.fromJson((Reader)reader2, type2);
                groupMap = groups == null ? new HashMap<String, List<String>>() : groups;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveToFile() {
        HashMap<String, String> encodedItems = new HashMap<String, String>();
        for (String s : itemMap.keySet()) {
            encodedItems.put(s, ItemDataManager.encodeItem(itemMap.get(s)));
        }
        try {
            Gson gson = new GsonBuilder().create();
            File file = new File(Aurum.getPlugin().getDataFolder().getAbsolutePath() + "/items.json");
            File file2 = new File(Aurum.getPlugin().getDataFolder().getAbsolutePath() + "/item_groups.json");
            file.getParentFile().mkdir();
            file2.getParentFile().mkdir();
            file.createNewFile();
            file2.createNewFile();
            FileWriter writer = new FileWriter(file, false);
            FileWriter writer2 = new FileWriter(file2, false);
            gson.toJson(encodedItems, (Appendable)writer);
            gson.toJson(groupMap, (Appendable)writer2);
            ((Writer)writer2).flush();
            ((Writer)writer).flush();
            ((Writer)writer2).close();
            ((Writer)writer).close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getItemKeys() {
        if (itemMap != null) {
            return new ArrayList<String>(itemMap.keySet());
        }
        return new ArrayList<String>();
    }

    public static ItemStack getItem(String itemKey) {
        if (itemMap.containsKey(itemKey)) {
            return itemMap.get(itemKey);
        }
        return new ItemStack(Material.RED_WOOL);
    }

    public static void deleteItem(String itemKey) {
        itemMap.remove(itemKey);
        ItemDataManager.saveToFile();
        ItemDataManager.loadFromFile();
    }

    public static void saveItem(ItemStack itemStack, String name) {
        itemMap.put(name, itemStack);
        ItemDataManager.saveToFile();
        ItemDataManager.loadFromFile();
    }

    public static void createGroup(String name) {
        groupMap.put(name, new ArrayList<>());
        ItemDataManager.saveToFile();
        ItemDataManager.loadFromFile();
    }

    public static void addItemToGroup(String group, String item) {
        if (groupMap.containsKey(group)) {
            groupMap.get(group).add(item);
            ItemDataManager.saveToFile();
            ItemDataManager.loadFromFile();
        }
    }

    public static void removeItemFromGroup(String group, String item) {
        if (groupMap.containsKey(group)) {
            groupMap.get(group).remove(item);
            ItemDataManager.saveToFile();
            ItemDataManager.loadFromFile();
        }
    }

    public static void deleteGroup(String group) {
        groupMap.remove(group);
        ItemDataManager.saveToFile();
        ItemDataManager.loadFromFile();
    }

    public static List<ItemStack> getItemsFromGroup(String group) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        if (groupMap.containsKey(group)) {
            for (String s : groupMap.get(group)) {
                list.add(ItemDataManager.getItem(s));
            }
        }
        return list;
    }

    public static List<String> getGroups() {
        return new ArrayList<String>(groupMap.keySet());
    }

    public static List<String> getGroupContents(String group) {
        List<String> list = new ArrayList<String>();
        if (groupMap.containsKey(group)) {
            list = groupMap.get(group);
        }
        return list;
    }

    private static String encodeItem(ItemStack itemStack) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream objectOutputStream = new BukkitObjectOutputStream((OutputStream)byteArrayOutputStream);
            objectOutputStream.writeObject((Object)itemStack);
            objectOutputStream.flush();
            byte[] serialized = byteArrayOutputStream.toByteArray();
            String encoded = Base64.getEncoder().encodeToString(serialized);
            objectOutputStream.close();
            return encoded;
        }
        catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    private static ItemStack decodeItem(String string) {
        if (string.equals("error")) {
            return new ItemStack(Material.RED_WOOL);
        }
        try {
            byte[] serializedObject = Base64.getDecoder().decode(string);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializedObject);
            BukkitObjectInputStream inputStream = new BukkitObjectInputStream((InputStream)byteArrayInputStream);
            ItemStack i = (ItemStack)inputStream.readObject();
            inputStream.close();
            return i;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ItemStack(Material.RED_WOOL);
        }
    }
}
