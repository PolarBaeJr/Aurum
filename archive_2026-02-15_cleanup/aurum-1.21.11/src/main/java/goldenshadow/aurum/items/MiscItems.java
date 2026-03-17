/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class MiscItems {
    private final ItemCreationHelper itemCreationHelper = new ItemCreationHelper();

    public ItemStack coinGold() {
        ItemStack itemStack = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(1f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("Gold Coin", NamedTextColor.GOLD).decorate(TextDecoration.BOLD));
        List<Component> lore = new ArrayList<>();
        lore.add(Component.empty());
        lore.add(Component.text("A golden coin with the image of a turtle stamped onto it", NamedTextColor.GRAY));
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack coinSilver() {
        ItemStack itemStack = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(2f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("Silver Coin", NamedTextColor.GOLD).decorate(TextDecoration.BOLD));
        List<Component> lore = new ArrayList<>();
        lore.add(Component.empty());
        lore.add(Component.text("A silver coin with the image of a turtle stamped onto it", NamedTextColor.GRAY));
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack coinBronze() {
        ItemStack itemStack = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(3f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("Bronze Coin", NamedTextColor.GOLD).decorate(TextDecoration.BOLD));
        List<Component> lore = new ArrayList<>();
        lore.add(Component.empty());
        lore.add(Component.text("A bronze coin with the image of a turtle stamped onto it", NamedTextColor.GRAY));
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeCharge() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(2f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of Charging ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.CHARGE, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeBloodRush() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(3f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of Blood ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.BLOOD_RUSH, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeFireball() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(4f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of Scorching ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.FIREBALL, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeHeal() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(5f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of Healing ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.HEAL, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeSmite() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(6f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of Lightning ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.SMITE, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeWindSlash() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(7f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of Wind ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.WIND_SLASH, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeResurgence() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(8f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of Resurgence ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.RESURGENCE, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeShockWave() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(10f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of Expulsion ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.SHOCK_WAVE, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeDistortion() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(11f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of Impulsion ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.DISTORTION, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeDragonSkin() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(12f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of Fire ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.DRAGON_SKIN, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeFishLung() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(13f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of Water ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.FISH_LUNG, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeFallingStar() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(14f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of the Falling Star ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.FALLING_STAR, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeSwiftness() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(15f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of Swiftness ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.SWIFTNESS, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeArcaneShield() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(16f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of Shielding ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.ARCANE_SHIELD, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeRegeneration() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(17f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of Regeneration ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.REGENERATION, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeGrace() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(18f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of Grace ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.GRACE, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeVitality() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(19f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of Vitality ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.VITALITY, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeRestoration() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(20f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of Restoration ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.RESTORATION, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeAmogus() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(21f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of the Imposter ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.AMOGUS, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeArcaneRay() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(22f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of Arcana ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.ARCANE_RAY, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeGroundSlam() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(23f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of the Ground ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.GROUND_SLAM, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runePirouette() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(24f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of the Spinning Blade ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.PIROUETTE, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeRitual() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(25f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of the Ritual ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.RITUAL, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeFrozenSpark() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(26f));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)
            .append(Component.text(" Rune of Frost ", NamedTextColor.DARK_PURPLE))
            .append(Component.text("E", NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
        List<Component> lore = new ArrayList<Component>(List.of(Component.empty(), Component.empty()));
        meta = this.itemCreationHelper.addRuneAbility(meta, Rune.FROZEN_SPARK, lore, false);
        lore = meta.lore();
        assert (lore != null);
        lore.remove(lore.size() - 1);
        lore.remove(lore.size() - 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack shardJungle() {
        ItemStack itemStack = new ItemStack(Material.EMERALD);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        List<Component> lore = new ArrayList<>();
        meta.displayName(Component.text("Dungeon Shard", NamedTextColor.DARK_GREEN).decorate(TextDecoration.BOLD));
        lore.add(Component.empty());
        lore.add(Component.text("Use this to open the dungeon somewhere", NamedTextColor.GRAY));
        lore.add(Component.text("in the jungle...", NamedTextColor.GRAY));
        NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "ShardID");
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 0);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack shardSnow() {
        ItemStack itemStack = new ItemStack(Material.QUARTZ);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        List<Component> lore = new ArrayList<>();
        meta.displayName(Component.text("Dungeon Shard", NamedTextColor.WHITE).decorate(TextDecoration.BOLD));
        lore.add(Component.empty());
        lore.add(Component.text("Use this to open the dungeon somewhere", NamedTextColor.GRAY));
        lore.add(Component.text("in the snow tundra...", NamedTextColor.GRAY));
        NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "ShardID");
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack shardDesert() {
        ItemStack itemStack = new ItemStack(Material.FERMENTED_SPIDER_EYE);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        List<Component> lore = new ArrayList<>();
        meta.displayName(Component.text("Dungeon Shard", NamedTextColor.DARK_RED).decorate(TextDecoration.BOLD));
        lore.add(Component.empty());
        lore.add(Component.text("Use this to open the dungeon somewhere", NamedTextColor.GRAY));
        lore.add(Component.text("in the desert...", NamedTextColor.GRAY));
        NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "ShardID");
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 2);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack shardEnchantedForest() {
        ItemStack itemStack = new ItemStack(Material.AMETHYST_SHARD);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        List<Component> lore = new ArrayList<>();
        meta.displayName(Component.text("Dungeon Shard", NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
        lore.add(Component.empty());
        lore.add(Component.text("Use this to open the dungeon somewhere", NamedTextColor.GRAY));
        lore.add(Component.text("in the enchanted forest...", NamedTextColor.GRAY));
        NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "ShardID");
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 3);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack shardLava() {
        ItemStack itemStack = new ItemStack(Material.FIRE_CHARGE);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        List<Component> lore = new ArrayList<>();
        meta.displayName(Component.text("Dungeon Shard", NamedTextColor.GOLD).decorate(TextDecoration.BOLD));
        lore.add(Component.empty());
        lore.add(Component.text("Use this to open the dungeon somewhere", NamedTextColor.GRAY));
        lore.add(Component.text("in the volcanic area...", NamedTextColor.GRAY));
        NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "ShardID");
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 4);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}

