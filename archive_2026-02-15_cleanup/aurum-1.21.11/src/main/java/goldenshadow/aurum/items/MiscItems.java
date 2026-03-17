/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 */
package goldenshadow.aurum.items;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.items.ItemCreationHelper;
import goldenshadow.aurum.items.flags.Rune;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class MiscItems {
    private final ItemCreationHelper itemCreationHelper = new ItemCreationHelper();

    public ItemStack coinGold() {
        ItemStack itemStack = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(1));
        meta.setDisplayName(ChatColor.GOLD + String.valueOf(ChatColor.BOLD) + "Gold Coin");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.GRAY + "A golden coin with the image of a turtle stamped onto it");
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack coinSilver() {
        ItemStack itemStack = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(2));
        meta.setDisplayName(ChatColor.GOLD + String.valueOf(ChatColor.BOLD) + "Silver Coin");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.GRAY + "A silver coin with the image of a turtle stamped onto it");
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack coinBronze() {
        ItemStack itemStack = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(3));
        meta.setDisplayName(ChatColor.GOLD + String.valueOf(ChatColor.BOLD) + "Bronze Coin");
        List<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ChatColor.GRAY + "A bronze coin with the image of a turtle stamped onto it");
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeCharge() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(2));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of Charging " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.CHARGE, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeBloodRush() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(3));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of Blood " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.BLOOD_RUSH, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeFireball() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(4));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of Scorching " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.FIREBALL, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeHeal() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(5));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of Healing " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.HEAL, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeSmite() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(6));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of Lightning " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.SMITE, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeWindSlash() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(7));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of Wind " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.WIND_SLASH, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeResurgence() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(8));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of Resurgence " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.RESURGENCE, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeShockWave() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(10));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of Expulsion " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.SHOCK_WAVE, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeDistortion() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(11));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of Impulsion " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.DISTORTION, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeDragonSkin() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(12));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of Fire " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.DRAGON_SKIN, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeFishLung() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(13));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of Water " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.FISH_LUNG, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeFallingStar() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(14));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of the Falling Star " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.FALLING_STAR, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeSwiftness() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(15));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of Swiftness " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.SWIFTNESS, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeArcaneShield() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(16));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of Shielding " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.ARCANE_SHIELD, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeRegeneration() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(17));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of Regeneration " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.REGENERATION, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeGrace() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(18));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of Grace " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.GRACE, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeVitality() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(19));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of Vitality " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.VITALITY, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeRestoration() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(20));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of Restoration " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.RESTORATION, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeAmogus() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(21));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of the Imposter " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.AMOGUS, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeArcaneRay() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(22));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of Arcana " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.ARCANE_RAY, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeGroundSlam() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(23));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of the Ground " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.GROUND_SLAM, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runePirouette() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(24));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of the Spinning Blade " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.PIROUETTE, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeRitual() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(25));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of the Ritual " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.RITUAL, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeFrozenSpark() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.setCustomModelData(Integer.valueOf(26));
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + String.valueOf(ChatColor.MAGIC) + "E" + ChatColor.DARK_PURPLE + " Rune of Frost " + ChatColor.LIGHT_PURPLE + ChatColor.MAGIC + "E");
        List<String> lore = new ArrayList<String>(List.of("", ""));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.FROZEN_SPARK, lore, false);
        lore = meta.getLore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack shardJungle() {
        ItemStack itemStack = new ItemStack(Material.EMERALD);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        List<String> lore = new ArrayList<>();
        meta.setDisplayName(ChatColor.DARK_GREEN + String.valueOf(ChatColor.BOLD) + "Dungeon Shard");
        lore.add("");
        lore.add(ChatColor.GRAY + "Use this to open the dungeon somewhere");
        lore.add(ChatColor.GRAY + "in the jungle...");
        NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "ShardID");
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 0);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack shardSnow() {
        ItemStack itemStack = new ItemStack(Material.QUARTZ);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        List<String> lore = new ArrayList<>();
        meta.setDisplayName(ChatColor.WHITE + String.valueOf(ChatColor.BOLD) + "Dungeon Shard");
        lore.add("");
        lore.add(ChatColor.GRAY + "Use this to open the dungeon somewhere");
        lore.add(ChatColor.GRAY + "in the snow tundra...");
        NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "ShardID");
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 1);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack shardDesert() {
        ItemStack itemStack = new ItemStack(Material.FERMENTED_SPIDER_EYE);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        List<String> lore = new ArrayList<>();
        meta.setDisplayName(ChatColor.DARK_RED + String.valueOf(ChatColor.BOLD) + "Dungeon Shard");
        lore.add("");
        lore.add(ChatColor.GRAY + "Use this to open the dungeon somewhere");
        lore.add(ChatColor.GRAY + "in the desert...");
        NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "ShardID");
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 2);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack shardEnchantedForest() {
        ItemStack itemStack = new ItemStack(Material.AMETHYST_SHARD);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        List<String> lore = new ArrayList<>();
        meta.setDisplayName(ChatColor.DARK_AQUA + String.valueOf(ChatColor.BOLD) + "Dungeon Shard");
        lore.add("");
        lore.add(ChatColor.GRAY + "Use this to open the dungeon somewhere");
        lore.add(ChatColor.GRAY + "in the enchanted forest...");
        NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "ShardID");
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 3);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack shardLava() {
        ItemStack itemStack = new ItemStack(Material.FIRE_CHARGE);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        List<String> lore = new ArrayList<>();
        meta.setDisplayName(ChatColor.GOLD + String.valueOf(ChatColor.BOLD) + "Dungeon Shard");
        lore.add("");
        lore.add(ChatColor.GRAY + "Use this to open the dungeon somewhere");
        lore.add(ChatColor.GRAY + "in the volcanic area...");
        NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "ShardID");
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 4);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}

