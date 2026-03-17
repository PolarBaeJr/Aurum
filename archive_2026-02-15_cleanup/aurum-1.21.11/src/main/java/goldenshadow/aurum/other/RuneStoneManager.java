/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
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
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;

public class RuneStoneManager {
    public static Inventory createGUI(Player player) {
        Inventory inventory = Bukkit.createInventory(null, (int)9, Component.text("Rune Stone", NamedTextColor.WHITE).decorate(TextDecoration.BOLD));
        int playerLevel = ExperienceManager.getLevel(player);
        inventory.setItem(0, RuneStoneManager.fillerElement());
        inventory.setItem(8, RuneStoneManager.fillerElement());
        inventory.setItem(1, createTeleportItem("Esper", 5, playerLevel, "Cost: 1 Silver Coin"));
        inventory.setItem(2, createTeleportItem("Arbidel", 35, playerLevel, "Cost: 2 Silvers Coin"));
        inventory.setItem(3, createTeleportItem("Runesmith Tower", 45, playerLevel, "Cost: 4 Silver Coins"));
        inventory.setItem(4, createTeleportItem("Hearth", 60, playerLevel, "Cost: 6 Silver Coins"));
        inventory.setItem(5, createTeleportItem("Scavenger Camp", 75, playerLevel, "Cost: 10 Silver Coins"));
        inventory.setItem(6, createTeleportItem("Waya's Antiques and Oddities", 82, playerLevel, "Cost: 12 Silver Coins"));
        inventory.setItem(7, createTeleportItem("Venir", 90, playerLevel, "Cost: 16 Silver Coins"));
        return inventory;
    }

    private static ItemStack createTeleportItem(String name, int levelReq, int playerLevel, String cost) {
        boolean meetsLevel = playerLevel >= levelReq;
        ItemStack item = new ItemStack(meetsLevel ? Material.ENDER_EYE : Material.ENDER_PEARL);
        ItemMeta meta = item.getItemMeta();
        assert (meta != null);
        List<Component> lore = new ArrayList<>();
        meta.displayName(Component.text(name, NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
        lore.add(Component.text("Click to teleport", NamedTextColor.DARK_GRAY));
        lore.add(Component.text(""));
        if (meetsLevel) {
            lore.add(Component.text("\u2714 ", NamedTextColor.GREEN).append(Component.text("Level Req: " + levelReq, NamedTextColor.GRAY)));
        } else {
            lore.add(Component.text("\u2716 ", NamedTextColor.RED).append(Component.text("Level Req: " + levelReq, NamedTextColor.GRAY)));
        }
        lore.add(Component.text(""));
        lore.add(Component.text(cost, NamedTextColor.AQUA));
        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
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
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(100085f));
        meta.setCustomModelDataComponent(customModelDataComponent);
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
                    player.sendMessage(Component.text("[", NamedTextColor.DARK_RED).append(Component.text("!", NamedTextColor.RED)).append(Component.text("] ", NamedTextColor.DARK_RED)).append(Component.text("You are not high enough level to travel to that location!", NamedTextColor.GRAY)));
                    player.playSound((Entity)player, Sound.ENTITY_CAT_HISS, 1.0f, 1.0f);
                    return;
                }
                if (event.getCurrentItem().getType() == Material.ENDER_EYE) {
                    assert (event.getCurrentItem().getItemMeta() != null);
                    String displayName = PlainTextComponentSerializer.plainText().serialize(event.getCurrentItem().getItemMeta().displayName());
                    int cost = RuneStoneManager.getCostFromName(displayName);
                    if (RuneStoneManager.hasEnoughCoins(player, cost)) {
                        RuneStoneManager.removeCoins(player, cost);
                        player.teleport(RuneStoneManager.getLocationByCost(cost));
                        player.playSound((Entity)player, Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.0f);
                    } else {
                        player.sendMessage(Component.text("[", NamedTextColor.DARK_RED).append(Component.text("!", NamedTextColor.RED)).append(Component.text("] ", NamedTextColor.DARK_RED)).append(Component.text("You don't have enough silver coins to travel to that location", NamedTextColor.GRAY)));
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
        meta.displayName(Component.text(" "));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private static boolean hasEnoughCoins(Player player, int amount) {
        int count = 0;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item == null || item.getType() != Material.GOLD_NUGGET) continue;
            assert (item.getItemMeta() != null);
            List<Float> cmdFloats = item.getItemMeta().getCustomModelDataComponent().getFloats();
            if (cmdFloats.isEmpty() || cmdFloats.get(0) != 2f) continue;
            count += item.getAmount();
        }
        return count >= amount;
    }

    private static void removeCoins(Player player, int amount) {
        int count = 0;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType() == Material.GOLD_NUGGET) {
                assert (item.getItemMeta() != null);
                List<Float> cmdFloats2 = item.getItemMeta().getCustomModelDataComponent().getFloats();
                if (!cmdFloats2.isEmpty() && cmdFloats2.get(0) == 2f) {
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
