/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.adventure.text.Component
 *  net.kyori.adventure.text.TextComponent
 *  net.kyori.adventure.text.format.NamedTextColor
 *  net.kyori.adventure.text.format.TextColor
 *  net.kyori.adventure.text.format.TextDecoration
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.inventory.meta.components.CustomModelDataComponent
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
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(1.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text((String)"Gold Coin", (TextColor)NamedTextColor.GOLD).decorate(TextDecoration.BOLD));
        ArrayList<Component> lore = new ArrayList<Component>();
        lore.add(Component.empty());
        lore.add(Component.text((String)"A golden coin with the image of a turtle stamped onto it", (TextColor)NamedTextColor.GRAY));
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack coinSilver() {
        ItemStack itemStack = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(Float.valueOf(2.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text((String)"Silver Coin", (TextColor)NamedTextColor.GOLD).decorate(TextDecoration.BOLD));
        ArrayList<Component> lore = new ArrayList<Component>();
        lore.add(Component.empty());
        lore.add(Component.text((String)"A silver coin with the image of a turtle stamped onto it", (TextColor)NamedTextColor.GRAY));
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack coinBronze() {
        ItemStack itemStack = new ItemStack(Material.GOLD_NUGGET);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(Float.valueOf(3.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(Component.text((String)"Bronze Coin", (TextColor)NamedTextColor.GOLD).decorate(TextDecoration.BOLD));
        ArrayList<Component> lore = new ArrayList<Component>();
        lore.add(Component.empty());
        lore.add(Component.text((String)"A bronze coin with the image of a turtle stamped onto it", (TextColor)NamedTextColor.GRAY));
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack runeCharge() {
        ItemStack itemStack = new ItemStack(Material.CLAY_BALL);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
        customModelDataComponent.setFloats(List.of(Float.valueOf(2.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of Charging ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(3.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of Blood ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(4.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of Scorching ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(5.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of Healing ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(6.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of Lightning ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(7.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of Wind ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(8.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of Resurgence ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(10.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of Expulsion ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(11.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of Impulsion ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(12.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of Fire ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(13.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of Water ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(14.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of the Falling Star ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(15.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of Swiftness ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(16.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of Shielding ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(17.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of Regeneration ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(18.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of Grace ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(19.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of Vitality ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(20.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of Restoration ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(21.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of the Imposter ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(22.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of Arcana ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(23.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of the Ground ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(24.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of the Spinning Blade ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(25.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of the Ritual ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        customModelDataComponent.setFloats(List.of(Float.valueOf(26.0f)));
        meta.setCustomModelDataComponent(customModelDataComponent);
        meta.displayName(((TextComponent)((TextComponent)Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)).append((Component)Component.text((String)" Rune of Frost ", (TextColor)NamedTextColor.DARK_PURPLE))).append(Component.text((String)"E", (TextColor)NamedTextColor.LIGHT_PURPLE).decorate(TextDecoration.OBFUSCATED)));
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
        ArrayList<Component> lore = new ArrayList<Component>();
        meta.displayName(Component.text((String)"Dungeon Shard", (TextColor)NamedTextColor.DARK_GREEN).decorate(TextDecoration.BOLD));
        lore.add(Component.empty());
        lore.add(Component.text((String)"Use this to open the dungeon somewhere", (TextColor)NamedTextColor.GRAY));
        lore.add(Component.text((String)"in the jungle...", (TextColor)NamedTextColor.GRAY));
        NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "ShardID");
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER,0);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack shardSnow() {
        ItemStack itemStack = new ItemStack(Material.QUARTZ);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        ArrayList<Component> lore = new ArrayList<Component>();
        meta.displayName(Component.text((String)"Dungeon Shard", (TextColor)NamedTextColor.WHITE).decorate(TextDecoration.BOLD));
        lore.add(Component.empty());
        lore.add(Component.text((String)"Use this to open the dungeon somewhere", (TextColor)NamedTextColor.GRAY));
        lore.add(Component.text((String)"in the snow tundra...", (TextColor)NamedTextColor.GRAY));
        NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "ShardID");
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER,1);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack shardDesert() {
        ItemStack itemStack = new ItemStack(Material.FERMENTED_SPIDER_EYE);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        ArrayList<Component> lore = new ArrayList<Component>();
        meta.displayName(Component.text((String)"Dungeon Shard", (TextColor)NamedTextColor.DARK_RED).decorate(TextDecoration.BOLD));
        lore.add(Component.empty());
        lore.add(Component.text((String)"Use this to open the dungeon somewhere", (TextColor)NamedTextColor.GRAY));
        lore.add(Component.text((String)"in the desert...", (TextColor)NamedTextColor.GRAY));
        NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "ShardID");
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER,2);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack shardEnchantedForest() {
        ItemStack itemStack = new ItemStack(Material.AMETHYST_SHARD);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        ArrayList<Component> lore = new ArrayList<Component>();
        meta.displayName(Component.text((String)"Dungeon Shard", (TextColor)NamedTextColor.DARK_AQUA).decorate(TextDecoration.BOLD));
        lore.add(Component.empty());
        lore.add(Component.text((String)"Use this to open the dungeon somewhere", (TextColor)NamedTextColor.GRAY));
        lore.add(Component.text((String)"in the enchanted forest...", (TextColor)NamedTextColor.GRAY));
        NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "ShardID");
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER,3);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack shardLava() {
        ItemStack itemStack = new ItemStack(Material.FIRE_CHARGE);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        ArrayList<Component> lore = new ArrayList<Component>();
        meta.displayName(Component.text((String)"Dungeon Shard", (TextColor)NamedTextColor.GOLD).decorate(TextDecoration.BOLD));
        lore.add(Component.empty());
        lore.add(Component.text((String)"Use this to open the dungeon somewhere", (TextColor)NamedTextColor.GRAY));
        lore.add(Component.text((String)"in the volcanic area...", (TextColor)NamedTextColor.GRAY));
        NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "ShardID");
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER,4);
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}

