/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeModifier
 *  org.bukkit.attribute.AttributeModifier$Operation
 *  org.bukkit.inventory.EquipmentSlot
 *  org.bukkit.inventory.ItemFlag
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 */
package goldenshadow.aurum.items;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.items.ItemCreationHelper;
import goldenshadow.aurum.items.ItemType;
import goldenshadow.aurum.items.MiscItems;
import goldenshadow.aurum.items.Rarity;
import goldenshadow.aurum.items.flags.AttributeID;
import goldenshadow.aurum.items.flags.Rune;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class ItemFactory {
    private static final String[] commonWeaponNamesStart = new String[]{"Old ", "Rusty ", "Basic ", "Soldier's ", "Grandpa's ", "Ineffective "};
    private static final String[] rareWeaponNameStart = new String[]{"Gladiator's ", "Polished ", "Enhanced ", "Baron's ", "Magical ", "Unique ", "Powerful "};
    private static final String[] epicWeaponNameStart = new String[]{"Paladin's ", "Sorcerer's ", "Enchanted ", "Mastercrafted ", "Famous ", "Savior's "};
    private static final String[] legendaryWeaponNamesStart = new String[]{"Hero's ", "Fabled ", "Mythical ", "Ancient ", "Monarch's ", "Emperor's ", "Foretold ", "Otherworldly "};
    private static final String[] swordNames = new String[]{"Sword", "Dagger", "Blade", "Greatsword", "Cutlass", "Longsword", "Knife", "Saber", "Rapier"};
    private static final String[] spearNames = new String[]{"Spear", "Halberd", "Javelin", "Pike", "Lance", "Polearm"};
    private static final String[] wandNames = new String[]{"Wand", "Staff", "Rod", "Cane", "Scepter"};
    private static final int[] commonWeaponCodes = new int[]{166, 165, 164, 181, 129, 128, 134, 131, 19, 23, 156, 22, 28};
    private static final int[] rareWeaponCodes = new int[]{176, 162, 82, 142, 127, 144, 132, 69, 77, 39};
    private static final int[] epicWeaponCodes = new int[]{62, 83, 172, 183, 180, 135, 143, 145, 140, 21, 34, 193, 40, 30};
    private static final int[] legendaryWeaponCodes = new int[]{177, 163, 173, 6, 136, 146, 133, 184, 80, 61, 45, 50, 197};
    private static final String[] commonArmorNameStart = new String[]{"Old ", "Rusty ", "Basic ", "Soldier's ", "Grandpa's ", "Ineffective "};
    private static final String[] rareArmorNameStart = new String[]{"Gladiator's ", "Polished ", "Enhanced ", "Baron's ", "Magical ", "Unique ", "Powerful "};
    private static final String[] epicArmorNameStart = new String[]{"Paladin's ", "Sorcerer's ", "Enchanted ", "Mastercrafted ", "Famous ", "Savior's "};
    private static final String[] legendaryArmorNamesStart = new String[]{"Hero's ", "Fabled ", "Mythical ", "Ancient ", "Monarch's ", "Emperor's ", "Foretold ", "Otherworldly "};
    private static final String[] helmetNames = new String[]{"Helmet", "Visor", "Helm", "Bassinet", "Hood", "Shawl", "Great Helm"};
    private static final String[] chestplateNames = new String[]{"Chestplate", "Hauberk", "Cuirass", "Battleplate", "Breastplate", "Plate Armor"};
    private static final String[] leggingsNames = new String[]{"Guards", "Greaves", "Leggings", "Leg Plates", "Pants"};
    private static final String[] bootsNames = new String[]{"Boots", "Shoes", "Sabatons", "Sandals", "Boots", "Boots"};
    public final ItemCreationHelper itemCreationHelper = new ItemCreationHelper();
    public final MiscItems miscItems = new MiscItems();

    public ItemStack buildWeapon(Rarity rarity, int level) {
        if (rarity == Rarity.ARTIFACT) {
            return this.getRandomArtifactWeapon();
        }
        if (rarity == Rarity.ELDRITCH) {
            return this.getRandomEldritchWeapon();
        }
        ItemStack itemStack = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        int customModelCode = this.getCustomModelCode(rarity);
        meta.getPersistentDataContainer().set(new NamespacedKey((Plugin)Aurum.getPlugin(), "minLevel"), PersistentDataType.INTEGER, level);
        meta.setCustomModelData(Integer.valueOf(customModelCode));
        List<Component> lore = new ArrayList<>();
        ItemType itemType = this.getItemType(customModelCode, rarity);
        meta.displayName(this.getNameComponent(itemType, rarity));
        int attackDamage = this.itemCreationHelper.getBaseDamage(rarity, itemType, level);
        meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(NamespacedKey.fromString("aurum:generic_attack_damage"), (double)attackDamage + this.itemCreationHelper.getAttributeSum(Attribute.ATTACK_DAMAGE, meta), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        lore.add(Component.empty());
        lore.add(Component.text("Base Attack Damage: ", NamedTextColor.GRAY).append(Component.text(String.valueOf(attackDamage), NamedTextColor.WHITE)));
        lore.add(Component.text("Minimum Level Req: ", NamedTextColor.GRAY).append(Component.text(String.valueOf(level), NamedTextColor.WHITE)));
        lore.add(Component.empty());
        for (int i = 0; i < this.getAttributeAmount(rarity, itemType); ++i) {
            AttributeID attribute = this.itemCreationHelper.getRandomAttribute(meta);
            meta = i < this.getPositiveRollAmount(rarity, itemType) ? this.itemCreationHelper.addAttribute(meta, this.itemCreationHelper.makePositive(this.itemCreationHelper.getRandomRoll(attribute, rarity, itemType, level)), attribute, lore, itemType) : (ThreadLocalRandom.current().nextBoolean() ? this.itemCreationHelper.addAttribute(meta, this.itemCreationHelper.getRandomRoll(attribute, rarity, itemType, level * -1), attribute, lore, itemType) : this.itemCreationHelper.addAttribute(meta, this.itemCreationHelper.getRandomRoll(attribute, rarity, itemType, level), attribute, lore, itemType));
        }
        lore = meta.lore();
        if (lore == null) {
            lore = new ArrayList<>();
        }
        if (ThreadLocalRandom.current().nextInt(0, 100) < this.getRuneSlotChance(rarity)) {
            lore.add(Component.empty());
            lore.add(Component.text("Empty Rune Slot", NamedTextColor.DARK_GRAY));
            meta = this.itemCreationHelper.addRuneData(meta, Rune.EMPTY);
        }
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.values());
        lore.add(Component.empty());
        lore.add(rarity.getName());
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack buildArmor(Rarity rarity, int level, ItemType itemType) {
        if (rarity == Rarity.ARTIFACT) {
            return this.getRandomArtifactArmor();
        }
        if (rarity == Rarity.ELDRITCH) {
            return this.getRandomEldritchArmor();
        }
        ItemStack itemStack = new ItemStack(this.getArmorMaterial(itemType, level));
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        int customModelCode = this.getCustomModelCode(rarity);
        meta.getPersistentDataContainer().set(new NamespacedKey((Plugin)Aurum.getPlugin(), "minLevel"), PersistentDataType.INTEGER, level);
        meta.setCustomModelData(Integer.valueOf(customModelCode));
        List<Component> lore = new ArrayList<>();
        meta.displayName(this.getNameComponent(itemType, rarity));
        int baseHealth = this.itemCreationHelper.getBaseHealth(rarity, itemType, level);
        meta.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(NamespacedKey.fromString("aurum:health"), (double)baseHealth + this.itemCreationHelper.getAttributeSum(Attribute.MAX_HEALTH, meta), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
        lore.add(Component.empty());
        lore.add(Component.text("Base Health Bonus: ", NamedTextColor.GRAY).append(Component.text(String.valueOf(baseHealth), NamedTextColor.WHITE)));
        lore.add(Component.text("Minimum Level Req: ", NamedTextColor.GRAY).append(Component.text(String.valueOf(level), NamedTextColor.WHITE)));
        lore.add(Component.empty());
        for (int i = 0; i < this.getAttributeAmount(rarity, itemType); ++i) {
            AttributeID attribute = this.itemCreationHelper.getRandomAttribute(meta);
            meta = i < this.getPositiveRollAmount(rarity, itemType) ? this.itemCreationHelper.addAttribute(meta, this.itemCreationHelper.makePositive(this.itemCreationHelper.getRandomRoll(attribute, rarity, itemType, level)), attribute, lore, itemType) : (ThreadLocalRandom.current().nextBoolean() ? this.itemCreationHelper.addAttribute(meta, this.itemCreationHelper.getRandomRoll(attribute, rarity, itemType, level * -1), attribute, lore, itemType) : this.itemCreationHelper.addAttribute(meta, this.itemCreationHelper.getRandomRoll(attribute, rarity, itemType, level), attribute, lore, itemType));
        }
        lore = meta.lore();
        if (lore == null) {
            lore = new ArrayList<>();
        }
        if (ThreadLocalRandom.current().nextInt(0, 100) < this.getRuneSlotChance(rarity)) {
            lore.add(Component.empty());
            lore.add(Component.text("Empty Rune Slot", NamedTextColor.DARK_GRAY));
            meta = this.itemCreationHelper.addRuneData(meta, Rune.EMPTY);
        }
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.values());
        lore.add(Component.empty());
        lore.add(rarity.getName());
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public ItemStack createRandomItem(Rarity rarity, int level) {
        ItemFactory itemFactory = new ItemFactory();
        if (rarity == Rarity.ARTIFACT || rarity == Rarity.ELDRITCH) {
            if (ThreadLocalRandom.current().nextBoolean()) {
                return itemFactory.buildWeapon(rarity, 0);
            }
            return itemFactory.buildArmor(rarity, 0, ItemType.HELMET);
        }
        int i = ThreadLocalRandom.current().nextInt(0, 5);
        return switch (i) {
            case 0 -> itemFactory.buildWeapon(rarity, level);
            case 1 -> itemFactory.buildArmor(rarity, level, ItemType.HELMET);
            case 2 -> itemFactory.buildArmor(rarity, level, ItemType.CHESTPLATE);
            case 3 -> itemFactory.buildArmor(rarity, level, ItemType.LEGGINGS);
            default -> itemFactory.buildArmor(rarity, level, ItemType.BOOTS);
        };
    }

    public ItemStack buildCustomItem(ItemType itemType, Material material, int level, int baseValue, Integer customModelData, Rarity rarity, String displayName, Map<AttributeID, Integer> attributesList, List<Rune> runeList) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        assert (meta != null);
        meta.displayName(Component.text(displayName, Rarity.getTextColor(rarity)));
        if (!itemType.isArmor()) {
            meta.setCustomModelData(customModelData);
        }
        List<Component> lore = new ArrayList<>();
        lore.add(Component.empty());
        lore.add(Component.text("Base " + (itemType.isArmor() ? "Health Bonus: " : "Attack Damage: "), NamedTextColor.GRAY).append(Component.text(String.valueOf(baseValue), NamedTextColor.WHITE)));
        lore.add(Component.text("Minimum Level Req: ", NamedTextColor.GRAY).append(Component.text(String.valueOf(level), NamedTextColor.WHITE)));
        meta.getPersistentDataContainer().set(new NamespacedKey((Plugin)Aurum.getPlugin(), "minLevel"), PersistentDataType.INTEGER, level);
        if (!attributesList.isEmpty()) {
            lore.add(Component.empty());
        }
        for (AttributeID attribute : attributesList.keySet()) {
            meta = this.itemCreationHelper.addAttribute(meta, attributesList.get((Object)attribute), attribute, lore, itemType);
            lore = meta.lore();
            assert (lore != null);
        }
        lore.add(Component.empty());
        lore.add(rarity.getName());
        for (Rune rune : runeList) {
            this.itemCreationHelper.addRuneAbility(meta, rune, lore, itemType.isArmor() && material != Material.CARVED_PUMPKIN);
            lore = meta.lore();
            assert (lore != null);
        }
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.values());
        meta.lore(lore);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    private int getRuneSlotChance(Rarity rarity) {
        return switch (rarity) {
            case Rarity.RARE -> 2;
            case Rarity.EPIC -> 5;
            case Rarity.LEGENDARY -> 10;
            default -> 0;
        };
    }

    private Material getArmorMaterial(ItemType itemType, int level) {
        return switch (itemType) {
            case ItemType.HELMET -> {
                if (level < 21) {
                    yield Material.LEATHER_HELMET;
                }
                if (level < 41) {
                    yield Material.GOLDEN_HELMET;
                }
                if (level < 61) {
                    yield Material.CHAINMAIL_HELMET;
                }
                if (level < 81) {
                    yield Material.IRON_HELMET;
                }
                yield Material.DIAMOND_HELMET;
            }
            case ItemType.CHESTPLATE -> {
                if (level < 21) {
                    yield Material.LEATHER_CHESTPLATE;
                }
                if (level < 41) {
                    yield Material.GOLDEN_CHESTPLATE;
                }
                if (level < 61) {
                    yield Material.CHAINMAIL_CHESTPLATE;
                }
                if (level < 81) {
                    yield Material.IRON_CHESTPLATE;
                }
                yield Material.DIAMOND_CHESTPLATE;
            }
            case ItemType.LEGGINGS -> {
                if (level < 21) {
                    yield Material.LEATHER_LEGGINGS;
                }
                if (level < 41) {
                    yield Material.GOLDEN_LEGGINGS;
                }
                if (level < 61) {
                    yield Material.CHAINMAIL_LEGGINGS;
                }
                if (level < 81) {
                    yield Material.IRON_LEGGINGS;
                }
                yield Material.DIAMOND_LEGGINGS;
            }
            case ItemType.BOOTS -> {
                if (level < 21) {
                    yield Material.LEATHER_BOOTS;
                }
                if (level < 41) {
                    yield Material.GOLDEN_BOOTS;
                }
                if (level < 61) {
                    yield Material.CHAINMAIL_BOOTS;
                }
                if (level < 81) {
                    yield Material.IRON_BOOTS;
                }
                yield Material.DIAMOND_BOOTS;
            }
            default -> Material.TURTLE_HELMET;
        };
    }

    private int getCustomModelCode(Rarity rarity) {
        return switch (rarity) {
            case Rarity.COMMON -> commonWeaponCodes[ThreadLocalRandom.current().nextInt(0, commonWeaponCodes.length)];
            case Rarity.RARE -> rareWeaponCodes[ThreadLocalRandom.current().nextInt(0, rareWeaponCodes.length)];
            case Rarity.EPIC -> epicWeaponCodes[ThreadLocalRandom.current().nextInt(0, epicWeaponCodes.length)];
            case Rarity.LEGENDARY -> legendaryWeaponCodes[ThreadLocalRandom.current().nextInt(0, legendaryWeaponCodes.length)];
            default -> 0;
        };
    }

    private Component getNameComponent(ItemType itemType, Rarity rarity) {
        String prefix = "";
        NamedTextColor color = Rarity.getTextColor(rarity);
        switch (rarity) {
            case COMMON: {
                prefix = itemType.isArmor() ? commonArmorNameStart[ThreadLocalRandom.current().nextInt(0, commonArmorNameStart.length)] : commonWeaponNamesStart[ThreadLocalRandom.current().nextInt(0, commonWeaponNamesStart.length)];
                break;
            }
            case RARE: {
                prefix = itemType.isArmor() ? rareArmorNameStart[ThreadLocalRandom.current().nextInt(0, rareArmorNameStart.length)] : rareWeaponNameStart[ThreadLocalRandom.current().nextInt(0, rareWeaponNameStart.length)];
                break;
            }
            case EPIC: {
                prefix = itemType.isArmor() ? epicArmorNameStart[ThreadLocalRandom.current().nextInt(0, epicArmorNameStart.length)] : epicWeaponNameStart[ThreadLocalRandom.current().nextInt(0, epicWeaponNameStart.length)];
                break;
            }
            case LEGENDARY: {
                prefix = itemType.isArmor() ? legendaryArmorNamesStart[ThreadLocalRandom.current().nextInt(0, legendaryArmorNamesStart.length)] : legendaryWeaponNamesStart[ThreadLocalRandom.current().nextInt(0, legendaryWeaponNamesStart.length)];
                break;
            }
        }
        String suffix = switch (itemType) {
            case BOOTS -> bootsNames[ThreadLocalRandom.current().nextInt(0, bootsNames.length)];
            case LEGGINGS -> leggingsNames[ThreadLocalRandom.current().nextInt(0, leggingsNames.length)];
            case CHESTPLATE -> chestplateNames[ThreadLocalRandom.current().nextInt(0, chestplateNames.length)];
            case HELMET -> helmetNames[ThreadLocalRandom.current().nextInt(0, helmetNames.length)];
            case WAND -> wandNames[ThreadLocalRandom.current().nextInt(0, wandNames.length)];
            case SPEAR -> spearNames[ThreadLocalRandom.current().nextInt(0, spearNames.length)];
            case SWORD -> swordNames[ThreadLocalRandom.current().nextInt(0, swordNames.length)];
        };
        return Component.text(prefix + suffix, color).decorate(TextDecoration.BOLD);
    }

    private ItemType getItemType(int cmd, Rarity rarity) {
        return switch (rarity) {
            case Rarity.COMMON -> {
                if (cmd < 4) {
                    yield ItemType.WAND;
                }
                if (cmd < 8) {
                    yield ItemType.SPEAR;
                }
                yield ItemType.SWORD;
            }
            case Rarity.RARE, Rarity.LEGENDARY -> {
                if (cmd < 3) {
                    yield ItemType.WAND;
                }
                if (cmd < 7) {
                    yield ItemType.SPEAR;
                }
                yield ItemType.SWORD;
            }
            case Rarity.EPIC -> {
                if (cmd < 5) {
                    yield ItemType.WAND;
                }
                if (cmd < 9) {
                    yield ItemType.SPEAR;
                }
                yield ItemType.SWORD;
            }
            default -> ItemType.SWORD;
        };
    }

    private int getAttributeAmount(Rarity rarity, ItemType itemType) {
        return switch (rarity) {
            case Rarity.COMMON -> {
                switch (itemType) {
                    default: {
                        throw new IncompatibleClassChangeError();
                    }
                    case BOOTS: 
                    case WAND: {
                        yield ThreadLocalRandom.current().nextInt(1, 4);
                    }
                    case LEGGINGS: 
                    case SPEAR: {
                        yield ThreadLocalRandom.current().nextInt(1, 3);
                    }
                    case SWORD: {
                        yield ThreadLocalRandom.current().nextInt(0, 3);
                    }
                    case HELMET: {
                        yield ThreadLocalRandom.current().nextInt(2, 4);
                    }
                    case CHESTPLATE: 
                }
                yield ThreadLocalRandom.current().nextInt(0, 2);
            }
            case Rarity.RARE -> {
                switch (itemType) {
                    default: {
                        throw new IncompatibleClassChangeError();
                    }
                    case LEGGINGS: 
                    case WAND: {
                        yield ThreadLocalRandom.current().nextInt(1, 4);
                    }
                    case CHESTPLATE: 
                    case SPEAR: {
                        yield ThreadLocalRandom.current().nextInt(1, 3);
                    }
                    case SWORD: {
                        yield ThreadLocalRandom.current().nextInt(0, 3);
                    }
                    case HELMET: {
                        yield 3;
                    }
                    case BOOTS: 
                }
                yield ThreadLocalRandom.current().nextInt(2, 4);
            }
            case Rarity.EPIC -> {
                switch (itemType) {
                    default: {
                        throw new IncompatibleClassChangeError();
                    }
                    case LEGGINGS: 
                    case WAND: {
                        yield ThreadLocalRandom.current().nextInt(2, 5);
                    }
                    case SPEAR: {
                        yield ThreadLocalRandom.current().nextInt(2, 4);
                    }
                    case SWORD: {
                        yield ThreadLocalRandom.current().nextInt(1, 4);
                    }
                    case HELMET: {
                        yield ThreadLocalRandom.current().nextInt(3, 6);
                    }
                    case CHESTPLATE: {
                        yield 3;
                    }
                    case BOOTS: 
                }
                yield ThreadLocalRandom.current().nextInt(3, 5);
            }
            case Rarity.LEGENDARY -> {
                switch (itemType) {
                    default: {
                        throw new IncompatibleClassChangeError();
                    }
                    case BOOTS: 
                    case WAND: {
                        yield ThreadLocalRandom.current().nextInt(4, 6);
                    }
                    case CHESTPLATE: 
                    case SPEAR: {
                        yield ThreadLocalRandom.current().nextInt(3, 5);
                    }
                    case SWORD: {
                        yield ThreadLocalRandom.current().nextInt(2, 5);
                    }
                    case HELMET: {
                        yield ThreadLocalRandom.current().nextInt(5, 7);
                    }
                    case LEGGINGS: 
                }
                yield ThreadLocalRandom.current().nextInt(2, 6);
            }
            default -> 0;
        };
    }

    private int getPositiveRollAmount(Rarity rarity, ItemType itemType) {
        return switch (rarity) {
            case Rarity.COMMON -> {
                if (itemType == ItemType.HELMET) {
                    yield 1;
                }
                yield 0;
            }
            case Rarity.RARE -> {
                switch (itemType) {
                    case HELMET: 
                    case BOOTS: 
                    case WAND: {
                        yield 1;
                    }
                }
                yield 0;
            }
            case Rarity.EPIC -> {
                switch (itemType) {
                    case HELMET: 
                    case BOOTS: {
                        yield 2;
                    }
                }
                yield 1;
            }
            case Rarity.LEGENDARY -> {
                if (itemType == ItemType.HELMET) {
                    yield 3;
                }
                yield 2;
            }
            default -> 0;
        };
    }

    private ItemStack getRandomArtifactWeapon() {
        int i = ThreadLocalRandom.current().nextInt(0, 11);
        return switch (i) {
            case 0 -> this.artifactWendigo();
            case 1 -> this.artifactShipwreck();
            case 2 -> this.artifactWindweaver();
            case 3 -> this.artifactValkyrie();
            case 4 -> this.artifactRefractal();
            case 5 -> this.artifactObligation();
            case 6 -> this.artifactBrisingr();
            case 7 -> this.artifactPrism();
            case 8 -> this.artifactSunflare();
            case 9 -> this.artifactArondight();
            default -> this.artifactHonour();
        };
    }

    private ItemStack getRandomEldritchWeapon() {
        int i = ThreadLocalRandom.current().nextInt(0, 8);
        return switch (i) {
            case 0 -> this.eldritchClawsOfTheBeast();
            case 1 -> this.eldritchBleedingThorn();
            case 2 -> this.eldritchEdgeOfTheApocalypse();
            case 3 -> this.eldritchBladeOfWoe();
            case 4 -> this.eldritchSoulBinder();
            case 5 -> this.eldritchCallOfTheAbyss();
            case 6 -> this.eldritchSpiritGuider();
            default -> this.eldritchNavigator();
        };
    }

    private ItemStack getRandomArtifactArmor() {
        int i = ThreadLocalRandom.current().nextInt(0, 8);
        return switch (i) {
            case 0 -> this.artifactVelsharoon();
            case 1 -> this.artifactRaincaller();
            case 2 -> this.artifactGuildCrown();
            case 3 -> this.artifactAlignment();
            case 4 -> this.artifactVagabond();
            case 5 -> this.artifactFullMoon();
            case 6 -> this.artifactValhalla();
            default -> this.artifactLastStand();
        };
    }

    private ItemStack getRandomEldritchArmor() {
        int i = ThreadLocalRandom.current().nextInt(0, 6);
        return switch (i) {
            case 0 -> this.eldritchZealotsShroud();
            case 1 -> this.eldritchCapOfFools();
            case 2 -> this.eldritchSteelLeaf();
            case 3 -> this.eldritchHeadOfTheBeast();
            case 4 -> this.eldritchWovenFirmament();
            default -> this.eldritchEbonyScales();
        };
    }

    public ItemStack artifactWendigo() {
        return this.buildCustomItem(ItemType.SWORD, Material.IRON_SWORD, 82, 330, 4, Rarity.ARTIFACT, "Wendigo", Map.of(AttributeID.COMBUSTION, -100, AttributeID.REFLECTION, 60, AttributeID.JUMP_HEIGHT, 1, AttributeID.FREEZING, 100), List.of(Rune.EMPTY));
    }

    public ItemStack artifactShipwreck() {
        return this.buildCustomItem(ItemType.SPEAR, Material.IRON_SWORD, 93, 320, 47, Rarity.ARTIFACT, "Shipwreck", Map.of(AttributeID.HEALTH, 4, AttributeID.EXPLODING, 50, AttributeID.JUMP_HEIGHT, -3, AttributeID.LIFE_STEAL, -40, AttributeID.CRITICAL_STRIKE, -15), List.of(Rune.EMPTY, Rune.EMPTY));
    }

    public ItemStack artifactWindweaver() {
        return this.buildCustomItem(ItemType.WAND, Material.IRON_SWORD, 92, 170, 48, Rarity.ARTIFACT, "Windweaver", Map.of(AttributeID.HEALTH, -20, AttributeID.EXPLODING, -500, AttributeID.JUMP_HEIGHT, 3, AttributeID.DODGE, 20, AttributeID.WALK_SPEED, 220), List.of());
    }

    public ItemStack artifactValkyrie() {
        return this.buildCustomItem(ItemType.WAND, Material.IRON_SWORD, 79, 240, 95, Rarity.ARTIFACT, "Valkyrie", Map.of(AttributeID.REBOUND, -50, AttributeID.COMBUSTION, 10, AttributeID.FREEZING, 10, AttributeID.EXPLODING, 10, AttributeID.CRITICAL_STRIKE, 65, AttributeID.LIFE_STEAL, 15), List.of(Rune.EMPTY));
    }

    public ItemStack artifactRefractal() {
        return this.buildCustomItem(ItemType.SWORD, Material.IRON_SWORD, 88, 300, 121, Rarity.ARTIFACT, "Refractal", Map.of(AttributeID.RUNE_DAMAGE, 50, AttributeID.DODGE, 30, AttributeID.REFLECTION, 100, AttributeID.HEALTH, 15), List.of(Rune.EMPTY, Rune.EMPTY));
    }

    public ItemStack artifactObligation() {
        return this.buildCustomItem(ItemType.SPEAR, Material.IRON_SWORD, 90, 150, 207, Rarity.ARTIFACT, "Obligation", Map.of(AttributeID.HEALTH, 40, AttributeID.RESISTANCE, 25, AttributeID.REBOUND, 80, AttributeID.EXPLODING, 55), List.of());
    }

    public ItemStack artifactBrisingr() {
        return this.buildCustomItem(ItemType.SWORD, Material.IRON_SWORD, 95, 250, 114, Rarity.ARTIFACT, "Brisingr", Map.of(AttributeID.RUNE_AFFINITY, 30, AttributeID.HEALTH, -8, AttributeID.WALK_SPEED, 30, AttributeID.DODGE, 25, AttributeID.EXPLODING, 35, AttributeID.COMBUSTION, 100), List.of(Rune.EMPTY, Rune.EMPTY, Rune.EMPTY));
    }

    public ItemStack artifactPrism() {
        return this.buildCustomItem(ItemType.SPEAR, Material.IRON_SWORD, 85, 240, 123, Rarity.ARTIFACT, "Prism", Map.of(AttributeID.RUNE_AFFINITY, 30, AttributeID.RUNE_DAMAGE, 30, AttributeID.MAIN_ATTACK_DAMAGE, 15, AttributeID.LIFE_STEAL, 10, AttributeID.DODGE, 40, AttributeID.HEALTH, 10, AttributeID.EXPLODING, 40, AttributeID.FREEZING, 80, AttributeID.COMBUSTION, 80), List.of(Rune.EMPTY, Rune.EMPTY));
    }

    public ItemStack artifactSunflare() {
        return this.buildCustomItem(ItemType.WAND, Material.IRON_SWORD, 90, 180, 200, Rarity.ARTIFACT, "Sunflare", Map.of(AttributeID.RUNE_AFFINITY, 50, AttributeID.CRITICAL_STRIKE, -30, AttributeID.WALK_SPEED, 30, AttributeID.JUMP_HEIGHT, 1, AttributeID.DODGE, 20, AttributeID.RESISTANCE, 10, AttributeID.HEALTH, 20), List.of(Rune.EMPTY));
    }

    public ItemStack artifactArondight() {
        return this.buildCustomItem(ItemType.SWORD, Material.IRON_SWORD, 100, 350, 199, Rarity.ARTIFACT, "Arondight", Map.of(AttributeID.MAIN_ATTACK_DAMAGE, 25, AttributeID.LIGHT_FOOT, -15, AttributeID.HEALTH, 15, AttributeID.REBOUND, 40), List.of());
    }

    public ItemStack artifactHonour() {
        return this.buildCustomItem(ItemType.SWORD, Material.IRON_SWORD, 100, 300, 49, Rarity.ARTIFACT, "Honour", Map.of(AttributeID.LIGHT_FOOT, 70, AttributeID.CRITICAL_STRIKE, 40, AttributeID.HEALTH, -6, AttributeID.JUMP_HEIGHT, 2, AttributeID.DODGE, 50, AttributeID.WALK_SPEED, 40), List.of());
    }

    public ItemStack eldritchClawsOfTheBeast() {
        return this.buildCustomItem(ItemType.SWORD, Material.IRON_SWORD, 99, 500, 41, Rarity.ELDRITCH, "Claws of the Beast", Map.of(AttributeID.CRITICAL_STRIKE, 30, AttributeID.WALK_SPEED, -30, AttributeID.HEALTH, -60, AttributeID.LIFE_STEAL, -200, AttributeID.RESISTANCE, -50, AttributeID.JUMP_HEIGHT, 3), List.of(Rune.DISPLACEMENT));
    }

    public ItemStack eldritchBleedingThorn() {
        return this.buildCustomItem(ItemType.SWORD, Material.IRON_SWORD, 92, 280, 167, Rarity.ELDRITCH, "Bleeding Thorn", Map.of(AttributeID.EXPLODING, 25, AttributeID.DODGE, 30, AttributeID.REFLECTION, -200, AttributeID.HEALTH, -30, AttributeID.LIFE_STEAL, 100), List.of());
    }

    public ItemStack eldritchEdgeOfTheApocalypse() {
        return this.buildCustomItem(ItemType.SPEAR, Material.IRON_SWORD, 97, 800, 43, Rarity.ELDRITCH, "Edge of the Apocalypse", Map.of(AttributeID.MAIN_ATTACK_DAMAGE, -50, AttributeID.CRITICAL_STRIKE, -100, AttributeID.RESISTANCE, -200), List.of());
    }

    public ItemStack eldritchBladeOfWoe() {
        return this.buildCustomItem(ItemType.SWORD, Material.IRON_SWORD, 98, 390, 54, Rarity.ELDRITCH, "Blade of Woe", Map.of(AttributeID.EXPLODING, -1000, AttributeID.LIFE_STEAL, 80, AttributeID.HEALTH, -40), List.of(Rune.SACRIFICE));
    }

    public ItemStack eldritchSoulBinder() {
        return this.buildCustomItem(ItemType.SWORD, Material.IRON_SWORD, 99, 260, 149, Rarity.ELDRITCH, "Soul Binder", Map.of(AttributeID.REBOUND, -24, AttributeID.HEALTH, -1, AttributeID.FREEZING, -222, AttributeID.MAIN_ATTACK_DAMAGE, 15), List.of(Rune.TIME_LOCK));
    }

    public ItemStack eldritchCallOfTheAbyss() {
        return this.buildCustomItem(ItemType.SWORD, Material.IRON_SWORD, 93, 236, 55, Rarity.ELDRITCH, "Call of the Abyss", Map.of(AttributeID.HEALTH, -5, AttributeID.DODGE, -33, AttributeID.REBOUND, 59, AttributeID.LIFE_STEAL, 42), List.of(Rune.LEECH_FOOT));
    }

    public ItemStack eldritchSpiritGuider() {
        return this.buildCustomItem(ItemType.SWORD, Material.IRON_SWORD, 93, 240, 148, Rarity.ELDRITCH, "Spirit Guider", Map.of(AttributeID.CRITICAL_STRIKE, -1000, AttributeID.LIFE_STEAL, 120, AttributeID.WALK_SPEED, -99, AttributeID.DODGE, 58, AttributeID.JUMP_HEIGHT, 3), List.of(Rune.DISTORTION));
    }

    public ItemStack eldritchNavigator() {
        return this.buildCustomItem(ItemType.WAND, Material.IRON_SWORD, 91, 283, 117, Rarity.ELDRITCH, "Navigator", Map.of(AttributeID.LIGHT_FOOT, 23, AttributeID.EXPLODING, 33, AttributeID.MAIN_ATTACK_DAMAGE, -38, AttributeID.RESISTANCE, -68, AttributeID.HEALTH, -10), List.of(Rune.RIFT_STEP));
    }

    public ItemStack artifactVelsharoon() {
        return this.buildCustomItem(ItemType.HELMET, Material.CARVED_PUMPKIN, 85, 15, 29, Rarity.ARTIFACT, "Velsharoon", Map.of(AttributeID.MAIN_ATTACK_DAMAGE, -10, AttributeID.RUNE_AFFINITY, 10, AttributeID.RUNE_DAMAGE, 70, AttributeID.RESISTANCE, -20, AttributeID.FREEZING, 10, AttributeID.LIFE_STEAL, 40, AttributeID.WALK_SPEED, 15, AttributeID.CRITICAL_STRIKE, 30), List.of(Rune.EMPTY));
    }

    public ItemStack artifactRaincaller() {
        return this.buildCustomItem(ItemType.HELMET, Material.CARVED_PUMPKIN, 89, 35, 42, Rarity.ARTIFACT, "Raincaller", Map.of(AttributeID.XP_BONUS, 15, AttributeID.RUNE_AFFINITY, 20, AttributeID.RESISTANCE, -50, AttributeID.REBOUND, -30, AttributeID.LIFE_STEAL, 30, AttributeID.FREEZING, 40, AttributeID.CRITICAL_STRIKE, 10, AttributeID.JUMP_HEIGHT, 3), List.of(Rune.EMPTY));
    }

    public ItemStack artifactGuildCrown() {
        HashMap<AttributeID, Integer> map = new HashMap<AttributeID, Integer>(Map.of(AttributeID.LIGHT_FOOT, 7, AttributeID.CRITICAL_STRIKE, 7, AttributeID.DODGE, 7, AttributeID.REFLECTION, 7, AttributeID.COMBUSTION, 7, AttributeID.REBOUND, 7, AttributeID.LIFE_STEAL, 7, AttributeID.FREEZING, 7));
        map.put(AttributeID.WALK_SPEED, 7);
        map.put(AttributeID.MAIN_ATTACK_DAMAGE, 7);
        map.put(AttributeID.RESISTANCE, 7);
        map.put(AttributeID.EXPLODING, 7);
        return this.buildCustomItem(ItemType.HELMET, Material.CARVED_PUMPKIN, 77, 7, 17, Rarity.ARTIFACT, "Guild Crown", map, List.of(Rune.EMPTY));
    }

    public ItemStack artifactAlignment() {
        return this.buildCustomItem(ItemType.HELMET, Material.CARVED_PUMPKIN, 84, 17, 72, Rarity.ARTIFACT, "Alignment", Map.of(AttributeID.RUNE_AFFINITY, 50, AttributeID.RUNE_DAMAGE, 20, AttributeID.MAIN_ATTACK_DAMAGE, -5, AttributeID.REBOUND, 20, AttributeID.DODGE, 30, AttributeID.FREEZING, 60, AttributeID.WALK_SPEED, 130), List.of(Rune.EMPTY));
    }

    public ItemStack artifactVagabond() {
        return this.buildCustomItem(ItemType.HELMET, Material.CARVED_PUMPKIN, 88, 18, 32, Rarity.ARTIFACT, "Vagabond", Map.of(AttributeID.XP_BONUS, 15, AttributeID.RUNE_AFFINITY, 30, AttributeID.JUMP_HEIGHT, 2, AttributeID.LIGHT_FOOT, 50, AttributeID.LIFE_STEAL, 30, AttributeID.WALK_SPEED, 30), List.of(Rune.EMPTY, Rune.EMPTY));
    }

    public ItemStack artifactFullMoon() {
        return this.buildCustomItem(ItemType.HELMET, Material.CARVED_PUMPKIN, 93, 15, 24, Rarity.ARTIFACT, "Full Moon", Map.of(AttributeID.REFLECTION, 40, AttributeID.LIGHT_FOOT, 66, AttributeID.DODGE, 35, AttributeID.JUMP_HEIGHT, 3, AttributeID.MAIN_ATTACK_DAMAGE, -30, AttributeID.RUNE_AFFINITY, 110), List.of());
    }

    public ItemStack artifactValhalla() {
        return this.buildCustomItem(ItemType.HELMET, Material.CARVED_PUMPKIN, 95, 20, 49, Rarity.ARTIFACT, "Valhalla", Map.of(AttributeID.DODGE, -30, AttributeID.FREEZING, 40, AttributeID.CRITICAL_STRIKE, 20, AttributeID.MAIN_ATTACK_DAMAGE, 20), List.of());
    }

    public ItemStack artifactLastStand() {
        return this.buildCustomItem(ItemType.HELMET, Material.CARVED_PUMPKIN, 99, -10, 14, Rarity.ARTIFACT, "Last Stand", Map.of(AttributeID.RESISTANCE, 40, AttributeID.CRITICAL_STRIKE, -20, AttributeID.MAIN_ATTACK_DAMAGE, 40, AttributeID.WALK_SPEED, -40), List.of(Rune.EMPTY));
    }

    public ItemStack eldritchZealotsShroud() {
        return this.buildCustomItem(ItemType.HELMET, Material.CARVED_PUMPKIN, 90, 0, 16, Rarity.ELDRITCH, "Zealot's Shroud", Map.of(AttributeID.REFLECTION, -120, AttributeID.REBOUND, 80, AttributeID.CRITICAL_STRIKE, -100, AttributeID.MAIN_ATTACK_DAMAGE, 50, AttributeID.WALK_SPEED, -10, AttributeID.JUMP_HEIGHT, -2, AttributeID.DODGE, 40), List.of());
    }

    public ItemStack eldritchCapOfFools() {
        return this.buildCustomItem(ItemType.HELMET, Material.CARVED_PUMPKIN, 97, -55, 30, Rarity.ELDRITCH, "Cap of Fools", Map.of(AttributeID.CRITICAL_STRIKE, 35, AttributeID.RESISTANCE, -30, AttributeID.DODGE, -1000, AttributeID.LIGHT_FOOT, -100, AttributeID.MAIN_ATTACK_DAMAGE, 100, AttributeID.JUMP_HEIGHT, 2), List.of());
    }

    public ItemStack eldritchSteelLeaf() {
        return this.buildCustomItem(ItemType.HELMET, Material.CARVED_PUMPKIN, 76, 30, 60, Rarity.ELDRITCH, "Steeleaf", Map.of(AttributeID.CRITICAL_STRIKE, -100, AttributeID.MAIN_ATTACK_DAMAGE, -50, AttributeID.RESISTANCE, 30, AttributeID.LIFE_STEAL, 50), List.of(Rune.GOLD_PACT));
    }

    public ItemStack eldritchHeadOfTheBeast() {
        return this.buildCustomItem(ItemType.HELMET, Material.CARVED_PUMPKIN, 99, -20, 33, Rarity.ELDRITCH, "Head of the Beast", Map.of(AttributeID.WALK_SPEED, -66, AttributeID.LIFE_STEAL, 50, AttributeID.LIGHT_FOOT, -200, AttributeID.DODGE, -30, AttributeID.RESISTANCE, 200), List.of());
    }

    public ItemStack eldritchWovenFirmament() {
        return this.buildCustomItem(ItemType.HELMET, Material.CARVED_PUMPKIN, 94, 10, 69, Rarity.ELDRITCH, "Woven Firmament", Map.of(AttributeID.RUNE_AFFINITY, 150, AttributeID.RUNE_DAMAGE, -50, AttributeID.MAIN_ATTACK_DAMAGE, -20, AttributeID.WALK_SPEED, 20, AttributeID.JUMP_HEIGHT, 2, AttributeID.DODGE, 150, AttributeID.FREEZING, 40, AttributeID.LIGHT_FOOT, 100), List.of());
    }

    public ItemStack eldritchEbonyScales() {
        HashMap<AttributeID, Integer> map = new HashMap<AttributeID, Integer>(Map.of(AttributeID.HEALTH, 7, AttributeID.RUNE_DAMAGE, -20, AttributeID.RUNE_AFFINITY, -10, AttributeID.MAIN_ATTACK_DAMAGE, -15, AttributeID.CRITICAL_STRIKE, -15, AttributeID.DODGE, 30, AttributeID.LIFE_STEAL, 20, AttributeID.REFLECTION, 70));
        map.put(AttributeID.RESISTANCE, 40);
        return this.buildCustomItem(ItemType.CHESTPLATE, Material.NETHERITE_CHESTPLATE, 96, 50, null, Rarity.ELDRITCH, "Ebony Scales", map, List.of());
    }
}
