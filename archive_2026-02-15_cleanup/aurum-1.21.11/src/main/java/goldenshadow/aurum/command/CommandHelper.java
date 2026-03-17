/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.bukkit.Bukkit
 *  org.bukkit.DyeColor
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeModifier
 *  org.bukkit.attribute.AttributeModifier$Operation
 *  org.bukkit.command.BlockCommandSender
 *  org.bukkit.enchantments.Enchantment
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.ArmorStand$LockType
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Fox$Type
 *  org.bukkit.entity.Frog$Variant
 *  org.bukkit.entity.Horse$Color
 *  org.bukkit.entity.Horse$Style
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Llama$Color
 *  org.bukkit.entity.MushroomCow$Variant
 *  org.bukkit.entity.Parrot$Variant
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Rabbit$Type
 *  org.bukkit.entity.Skeleton
 *  org.bukkit.entity.Slime
 *  org.bukkit.entity.Villager$Profession
 *  org.bukkit.entity.Villager$Type
 *  org.bukkit.inventory.EquipmentSlot
 *  org.bukkit.inventory.ItemFlag
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataContainer
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.util.io.BukkitObjectOutputStream
 */
package goldenshadow.aurum.command;

import goldenshadow.aurum.APIProvider;
import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.entities.AIType;
import goldenshadow.aurum.entities.CustomEntity;
import goldenshadow.aurum.entities.DataManager;
import goldenshadow.aurum.entities.DiagnosticLogger;
import goldenshadow.aurum.entities.EntityData;
import goldenshadow.aurum.entities.SpawnInterval;
import goldenshadow.aurum.entities.SpawnLocation;
import goldenshadow.aurum.entities.SpellName;
import goldenshadow.aurum.entities.XpType;
import goldenshadow.aurum.items.BossLootManager;
import goldenshadow.aurum.items.ConditionalInteractionHandler;
import goldenshadow.aurum.items.EventInteractionHandler;
import goldenshadow.aurum.items.ItemBuyer;
import goldenshadow.aurum.items.ItemDataManager;
import goldenshadow.aurum.items.ItemFactory;
import goldenshadow.aurum.items.ItemHelper;
import goldenshadow.aurum.items.ItemType;
import goldenshadow.aurum.items.PickupInteractionHandler;
import goldenshadow.aurum.items.Rarity;
import goldenshadow.aurum.items.RuneAbilityHandler;
import goldenshadow.aurum.items.RuneSmith;
import goldenshadow.aurum.items.Treasure;
import goldenshadow.aurum.items.flags.AttributeID;
import goldenshadow.aurum.items.flags.Rune;
import goldenshadow.aurum.other.Direction;
import goldenshadow.aurum.other.DoorHandler;
import goldenshadow.aurum.other.ExperienceManager;
import goldenshadow.aurum.other.RespawnLocation;
import goldenshadow.aurum.other.RuneStoneManager;
import goldenshadow.aurum.other.TrainingDummy;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import javax.annotation.Nullable;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Frog;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Llama;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.io.BukkitObjectOutputStream;

public class CommandHelper {
    private final ItemHelper itemHelper = new ItemHelper();
    private final ItemFactory itemFactory = new ItemFactory();
    private final HashMap<UUID, EntityData> editMob = new HashMap();
    final String[] consumableEffectNames = new String[]{"ABSORPTION", "BAD_OMEN", "BLINDNESS", "CONDUIT_POWER", "CONFUSION", "DAMAGE_RESISTANCE", "DARKNESS", "DOLPHINS_GRACE", "FAST_DIGGING", "FIRE_RESISTANCE", "GLOWING", "HARM", "HEAL", "HEALTH_BOOST", "HERO_OF_THE_VILLAGE", "HUNGER", "INCREASE_DAMAGE", "INVISIBILITY", "JUMP", "LEVITATION", "LUCK", "NIGHT_VISION", "POISON", "REGENERATION", "SATURATION", "SLOW", "SLOW_DIGGING", "SLOW_FALLING", "SPEED", "UNLUCK", "WATER_BREATHING", "WEAKNESS", "WITHER", "PRECISE_HEALTH", "PRECISE_DAMAGE"};
    final String[] livingEntities = new String[]{"ALLAY", "AXOLOTL", "BAT", "BEE", "BLAZE", "CAMEL", "CAT", "CAVE_SPIDER", "CHICKEN", "COD", "COW", "CREEPER", "DOLPHIN", "DONKEY", "DROWNED", "ELDER_GUARDIAN", "ENDERMAN", "ENDERMITE", "EVOKER", "FOX", "FROG", "GHAST", "GIANT", "GLOW_SQUID", "GOAT", "IRON_GOLEM", "GUARDIAN", "HOGLIN", "HORSE", "HUSK", "ILLUSIONER", "LLAMA", "MAGMA_CUBE", "MULE", "MUSHROOM_COW", "OCELOT", "PANDA", "PARROT", "PHANTOM", "PIG", "PIGLIN", "PIGLIN_BRUTE", "PILLAGER", "POLAR_BEAR", "PUFFERFISH", "RABBIT", "RAVAGER", "SALMON", "SHEEP", "SHULKER", "SILVERFISH", "SKELETON", "SKELETON_HORSE", "SLIME", "SNIFFER", "SNOWMAN", "SPIDER", "SQUID", "STRAY", "STRIDER", "TADPOLE", "TRADER_LLAMA", "TROPICAL_FISH", "TURTLE", "VEX", "VILLAGER", "VINDICATOR", "WANDERING_TRADER", "WARDEN", "WITCH", "WITHER_SKELETON", "WOLF", "ZOGLIN", "ZOMBIE", "ZOMBIE_HORSE", "ZOMBIE_VILLAGER", "ZOMBIFIED_PIGLIN"};

    public void api(String[] args) {
        if (args.length >= 2) {
            Player p;
            Location location;
            if (args[1].equalsIgnoreCase("open_door") && args.length == 6 && this.isDouble(args[3]) && this.isDouble(args[4]) && this.isDouble(args[5])) {
                location = new Location(Bukkit.getWorld((String)args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]), Double.parseDouble(args[5]));
                APIProvider.openDoorAtLocation(location);
            }
            if (args[1].equalsIgnoreCase("clear_tokens") && args.length == 3 && (p = Bukkit.getPlayer((String)args[2])) != null) {
                APIProvider.clearTokensFromPlayer(p);
            }
            if (args[1].equalsIgnoreCase("spawn_mob") && args.length == 8 && this.isDouble(args[3]) && this.isDouble(args[4]) && this.isDouble(args[5])) {
                location = new Location(Bukkit.getWorld((String)args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]), Double.parseDouble(args[5]));
                APIProvider.spawnMobAtLocation(location, args[6], UUID.fromString(args[7]));
            }
            if (args[1].equalsIgnoreCase("disable_abilities") && args.length == 3 && (p = Bukkit.getPlayer((String)args[2])) != null) {
                APIProvider.disableAbilities(p);
            }
            if (args[1].equalsIgnoreCase("give_item") && args.length == 4 && (p = Bukkit.getPlayer((String)args[2])) != null && ItemDataManager.getItemKeys().contains(args[3])) {
                p.getInventory().addItem(new ItemStack[]{ItemDataManager.getItem(args[3])});
            }
            if (args[1].equalsIgnoreCase("give_group") && args.length == 4 && (p = Bukkit.getPlayer((String)args[2])) != null && ItemDataManager.getGroups().contains(args[3])) {
                for (ItemStack i : ItemDataManager.getItemsFromGroup(args[3])) {
                    p.getInventory().addItem(new ItemStack[]{i});
                }
            }
            if (args[1].equalsIgnoreCase("enable_pvp")) {
                Aurum.getPlugin().getConfig().set("pvp", true);
                Aurum.getPlugin().saveConfig();
            }
            if (args[1].equalsIgnoreCase("disable_pvp")) {
                Aurum.getPlugin().getConfig().set("pvp", false);
                Aurum.getPlugin().saveConfig();
            }
        }
    }

    public void blockSender(String[] args, BlockCommandSender b) {
        if (args.length >= 3) {
            double d;
            double distance;
            Player nearestPlayer;
            if (args[0].equalsIgnoreCase("item") && args[1].equalsIgnoreCase("group") && args[2].equalsIgnoreCase("give_nearest") && args.length == 4 && ItemDataManager.getGroups().contains(args[3].toLowerCase())) {
                nearestPlayer = null;
                distance = 5.0;
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (!p.getWorld().equals(b.getBlock().getWorld()) || !((d = p.getLocation().distance(b.getBlock().getLocation())) < distance)) continue;
                    nearestPlayer = p;
                    distance = d;
                }
                if (nearestPlayer != null) {
                    for (ItemStack i : ItemDataManager.getItemsFromGroup(args[3].toLowerCase())) {
                        nearestPlayer.getInventory().addItem(new ItemStack[]{i});
                    }
                }
            }
            if (args[0].equalsIgnoreCase("item") && args[1].equalsIgnoreCase("give_nearest") && ItemDataManager.getItemKeys().contains(args[2].toLowerCase())) {
                nearestPlayer = null;
                distance = 5.0;
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (!p.getWorld().equals(b.getBlock().getWorld()) || !((d = p.getLocation().distance(b.getBlock().getLocation())) < distance)) continue;
                    nearestPlayer = p;
                    distance = d;
                }
                if (nearestPlayer != null) {
                    nearestPlayer.getInventory().addItem(new ItemStack[]{ItemDataManager.getItem(args[2].toLowerCase())});
                }
            }
        }
    }

    public void editItemSubCommand(String[] args, Player player) {
        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            player.sendMessage(Component.text("[Aurum] Error: You must be holding an item to use this command!", NamedTextColor.RED));
        }
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        assert (meta != null);
        List<Component> lore = meta.lore() != null ? new ArrayList<>(meta.lore()) : null;
        int index;
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.values());
        meta.removeAttributeModifier(Attribute.ATTACK_SPEED);
        meta.addAttributeModifier(Attribute.ATTACK_SPEED, new AttributeModifier(NamespacedKey.fromString("aurum:attack_speed"), 0.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        if (lore == null) {
            lore = new ArrayList<>();
            lore.add(Component.empty());
        }
        if (lore.size() < 6) {
            lore.clear();
            for (int i = 0; i < 6; ++i) {
                lore.add(Component.text(" "));
            }
        }
        if (args.length >= 2) {
            if (args[1].equalsIgnoreCase("add_attribute")) {
                if (args.length == 4 && AttributeID.isValidEnum(args[2])) {
                    AttributeID attribute = AttributeID.valueOf(args[2]);
                    if (this.isInteger(args[3])) {
                        if (attribute == AttributeID.FISH_BAIT) {
                            if (meta.hasEnchant(Enchantment.LURE)) {
                                player.sendMessage(Component.text("[Aurum] Error: This item already has this attribute!", NamedTextColor.RED));
                                return;
                            }
                            if (args[3].equalsIgnoreCase("1")) {
                                meta.addEnchant(Enchantment.LURE, 1, true);
                                lore.add(4, Component.text("+20% ", NamedTextColor.GREEN).append(Component.text("Fish Bait", NamedTextColor.GRAY)));
                            } else if (args[3].equalsIgnoreCase("2")) {
                                meta.addEnchant(Enchantment.LURE, 2, true);
                                lore.add(4, Component.text("+40% ", NamedTextColor.GREEN).append(Component.text("Fish Bait", NamedTextColor.GRAY)));
                            } else if (args[3].equalsIgnoreCase("3")) {
                                meta.addEnchant(Enchantment.LURE, 3, true);
                                lore.add(4, Component.text("+60% ", NamedTextColor.GREEN).append(Component.text("Fish Bait", NamedTextColor.GRAY)));
                            } else if (args[3].equalsIgnoreCase("4")) {
                                meta.addEnchant(Enchantment.LURE, 4, true);
                                lore.add(4, Component.text("+80% ", NamedTextColor.GREEN).append(Component.text("Fish Bait", NamedTextColor.GRAY)));
                            } else {
                                meta.addEnchant(Enchantment.LURE, 5, true);
                                lore.add(4, Component.text("+100% ", NamedTextColor.GREEN).append(Component.text("Fish Bait", NamedTextColor.GRAY)));
                            }
                            meta.lore(lore);
                        } else {
                            if (this.itemHelper.hasAttribute(meta, attribute)) {
                                player.sendMessage(Component.text("[Aurum] Error: This item already has this attribute!", NamedTextColor.RED));
                                return;
                            }
                            meta = this.itemFactory.itemCreationHelper.insertAttribute(meta, Integer.parseInt(args[3]), attribute, lore, item.getType());
                            meta.lore(lore);
                        }
                        meta.lore(lore);
                        item.setItemMeta(meta);
                        player.getInventory().setItemInMainHand(item);
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Attribute added!", NamedTextColor.YELLOW)));
                        return;
                    }
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum add_attribute <attribute> <roll>", NamedTextColor.RED));
            } else if (args[1].equalsIgnoreCase("add_rune")) {
                if (args.length == 3 && Rune.isValidEnum(args[2])) {
                    Rune rune = Rune.valueOf(args[2]);
                    meta = this.itemFactory.itemCreationHelper.addRuneAbility(meta, rune, lore, !this.itemHelper.isNotArmor(item));
                    item.setItemMeta(meta);
                    player.getInventory().setItemInMainHand(item);
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Rune ability added!", NamedTextColor.YELLOW)));
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum add_rune <rune_ability>", NamedTextColor.RED));
            } else if (args[1].equalsIgnoreCase("set_base_damage")) {
                if (args.length == 3 && this.isInteger(args[2])) {
                    meta.removeAttributeModifier(Attribute.ATTACK_DAMAGE);
                    meta.addAttributeModifier(Attribute.ATTACK_DAMAGE, new AttributeModifier(NamespacedKey.fromString("aurum:generic_attack_damage"), (double)Integer.parseInt(args[2]), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
                    lore.set(1, Component.text("Base Attack Damage: ", NamedTextColor.GRAY).append(Component.text(String.valueOf(Integer.parseInt(args[2])), NamedTextColor.WHITE)));
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Base Attack Damage set!", NamedTextColor.YELLOW)));
                    meta.lore(lore);
                    item.setItemMeta(meta);
                    player.getInventory().setItemInMainHand(item);
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum edit_item set_base_damage <damage>", NamedTextColor.RED));
            } else if (args[1].equalsIgnoreCase("set_base_health")) {
                if (args.length == 3 && this.isInteger(args[2])) {
                    double oldBase = 0.0;
                    if (lore.size() >= 2 && PlainTextComponentSerializer.plainText().serialize(lore.get(1)).contains("Base Health Bonus")) {
                        oldBase = Double.parseDouble(Character.toString(PlainTextComponentSerializer.plainText().serialize(lore.get(1)).charAt(PlainTextComponentSerializer.plainText().serialize(lore.get(1)).length() - 1)));
                    }
                    double newBase = (double)Integer.parseInt(args[2]) + this.itemFactory.itemCreationHelper.getAttributeSum(Attribute.MAX_HEALTH, meta) - oldBase;
                    meta.removeAttributeModifier(Attribute.MAX_HEALTH);
                    meta.addAttributeModifier(Attribute.MAX_HEALTH, new AttributeModifier(NamespacedKey.fromString("aurum:health"), newBase, AttributeModifier.Operation.ADD_NUMBER, this.itemFactory.itemCreationHelper.parseMaterial(item.getType())));
                    lore.set(1, Component.text("Base Health Bonus: ", NamedTextColor.GRAY).append(Component.text(String.valueOf(Integer.parseInt(args[2])), NamedTextColor.WHITE)));
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Base Health Bonus set!", NamedTextColor.YELLOW)));
                    meta.lore(lore);
                    item.setItemMeta(meta);
                    player.getInventory().setItemInMainHand(item);
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum edit_item set_base_health <health>", NamedTextColor.RED));
            } else if (args[1].equalsIgnoreCase("set_CustomModelData")) {
                if (args.length == 3 && this.isInteger(args[2])) {
                    meta.setCustomModelData(Integer.valueOf(Integer.parseInt(args[2])));
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("CustomModelData set!", NamedTextColor.YELLOW)));
                    meta.lore(lore);
                    item.setItemMeta(meta);
                    player.getInventory().setItemInMainHand(item);
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum edit_item set_CustomModelData <CustomModelData>", NamedTextColor.RED));
            } else if (args[1].equalsIgnoreCase("set_level")) {
                if (args.length == 3 && this.isInteger(args[2])) {
                    NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "minLevel");
                    meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, Integer.parseInt(args[2]));
                    lore.set(2, Component.text("Minimum Level Req: ", NamedTextColor.GRAY).append(Component.text(String.valueOf(Integer.parseInt(args[2])), NamedTextColor.WHITE)));
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Level requirement set!", NamedTextColor.YELLOW)));
                    meta.lore(lore);
                    item.setItemMeta(meta);
                    player.getInventory().setItemInMainHand(item);
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum edit_item set_level <level>", NamedTextColor.RED));
            } else if (args[1].equalsIgnoreCase("set_name")) {
                if (args.length >= 3) {
                    StringBuilder name = new StringBuilder();
                    for (int i = 0; i < args.length - 2; ++i) {
                        if (i != 0) {
                            name.append(" ").append(args[i + 2]);
                            continue;
                        }
                        name.append(args[i + 2]);
                    }
                    if (PlainTextComponentSerializer.plainText().serialize(lore.get(lore.size() - 1)).contains("Common")) {
                        meta.displayName(Component.text(name.toString(), NamedTextColor.DARK_AQUA, TextDecoration.BOLD));
                    } else if (PlainTextComponentSerializer.plainText().serialize(lore.get(lore.size() - 1)).contains("Rare")) {
                        meta.displayName(Component.text(name.toString(), NamedTextColor.AQUA, TextDecoration.BOLD));
                    } else if (PlainTextComponentSerializer.plainText().serialize(lore.get(lore.size() - 1)).contains("Epic")) {
                        meta.displayName(Component.text(name.toString(), NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD));
                    } else if (PlainTextComponentSerializer.plainText().serialize(lore.get(lore.size() - 1)).contains("Legendary")) {
                        meta.displayName(Component.text(name.toString(), NamedTextColor.RED, TextDecoration.BOLD));
                    } else if (PlainTextComponentSerializer.plainText().serialize(lore.get(lore.size() - 1)).contains("Artifact") && !PlainTextComponentSerializer.plainText().serialize(lore.get(lore.size() - 1)).contains("Eldritch")) {
                        meta.displayName(Component.text(name.toString(), NamedTextColor.GOLD, TextDecoration.BOLD));
                    } else if (PlainTextComponentSerializer.plainText().serialize(lore.get(lore.size() - 1)).contains("Eldritch")) {
                        meta.displayName(Component.text(name.toString(), NamedTextColor.GREEN, TextDecoration.BOLD));
                    } else if (PlainTextComponentSerializer.plainText().serialize(lore.get(lore.size() - 1)).contains("Consumable")) {
                        meta.displayName(Component.text(name.toString(), NamedTextColor.BLUE, TextDecoration.BOLD));
                    } else {
                        meta.displayName(Component.text(name.toString()));
                    }
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Name set!", NamedTextColor.YELLOW)));
                    meta.lore(lore);
                    item.setItemMeta(meta);
                    player.getInventory().setItemInMainHand(item);
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum edit_item set_name <name>", NamedTextColor.RED));
            } else if (args[1].equalsIgnoreCase("set_rarity")) {
                if (args.length == 3 && Rarity.isValidEnum(args[2])) {
                    Rarity rarity = Rarity.valueOf(args[2]);
                    lore.set(lore.size() - 1, rarity.getName());
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Rarity set!", NamedTextColor.YELLOW)));
                    meta.lore(lore);
                    item.setItemMeta(meta);
                    player.getInventory().setItemInMainHand(item);
                    String[] s = new String[]{"edit_item", "set_name", PlainTextComponentSerializer.plainText().serialize(meta.displayName())};
                    this.editItemSubCommand(s, player);
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum edit_item set_rarity <rarity>", NamedTextColor.RED));
            } else if (args[1].equalsIgnoreCase("add_rune_slot")) {
                if (args.length == 2) {
                    meta = this.itemFactory.itemCreationHelper.addRuneAbility(meta, Rune.EMPTY, lore, false);
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Rune Slot added!", NamedTextColor.YELLOW)));
                    meta.lore(lore);
                    item.setItemMeta(meta);
                    player.getInventory().setItemInMainHand(item);
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum edit_item add_rune_slot", NamedTextColor.RED));
            } else if (args[1].equalsIgnoreCase("add_consumable_effect")) {
                if (args.length == 6) {
                    for (String name : this.consumableEffectNames) {
                        if (!name.equalsIgnoreCase(args[2]) || !this.isInteger(args[4]) || !this.isInteger(args[5])) continue;
                        lore.set(0, Component.text("Right click to use", NamedTextColor.DARK_GRAY));
                        lore.set(lore.size() - 1, Component.text("Consumable Item", NamedTextColor.BLUE));
                        meta.displayName(Component.text(PlainTextComponentSerializer.plainText().serialize(meta.displayName()), NamedTextColor.BLUE, TextDecoration.BOLD));
                        meta = this.itemFactory.itemCreationHelper.addConsumableEffect(meta, lore, args[2], args[3], Integer.parseInt(args[4]), Integer.parseInt(args[5]));
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Added consumable effect to item!", NamedTextColor.YELLOW)));
                        meta.addItemFlags(ItemFlag.values());
                        meta.lore(lore);
                        item.setItemMeta(meta);
                        player.getInventory().setItemInMainHand(item);
                        return;
                    }
                } else {
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum edit_item add_consumable_effect <name> <display name> <strength> <duration>", NamedTextColor.RED));
                    player.sendMessage(Component.text("Underscores will be replaced with spaces for the display name.", NamedTextColor.RED));
                }
            } else if (args[1].equalsIgnoreCase("custom")) {
                if (args[2].equalsIgnoreCase("set_name_with_colour")) {
                    if (args.length >= 4) {
                        StringBuilder name = new StringBuilder();
                        for (int i = 0; i < args.length - 3; ++i) {
                            if (i != 0) {
                                name.append(" ").append(args[i + 3]);
                                continue;
                            }
                            name.append(args[i + 3]);
                        }
                        meta.displayName(LegacyComponentSerializer.legacyAmpersand().deserialize(name.toString()));
                        item.setItemMeta(meta);
                        player.getInventory().setItemInMainHand(item);
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Name set!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum edit_item custom set_name_with_colour <text>", NamedTextColor.RED));
                } else if (args[2].equalsIgnoreCase("remove_lore_line")) {
                    if (this.isInteger(args[3])) {
                        index = Integer.parseInt(args[3]) - 1;
                        lore = meta.lore() != null ? new ArrayList<>(meta.lore()) : null;
                        if (lore != null) {
                            if (lore.size() > index && index >= 0) {
                                lore.remove(index);
                                meta.lore(lore);
                                item.setItemMeta(meta);
                                player.getInventory().setItemInMainHand(item);
                                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Lore line removed!", NamedTextColor.YELLOW)));
                                return;
                            }
                            player.sendMessage(Component.text("[Aurum] Error: Index is out of bounds!", NamedTextColor.RED));
                            return;
                        }
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum edit_item custom remove_lore_line <index>", NamedTextColor.RED));
                } else if (args[2].equalsIgnoreCase("add_lore_line")) {
                    if (args.length >= 4) {
                        lore = meta.lore() != null ? new ArrayList<>(meta.lore()) : null;
                        if (lore == null) {
                            lore = new ArrayList<>();
                        }
                        StringBuilder name = new StringBuilder();
                        for (int i = 0; i < args.length - 3; ++i) {
                            if (i != 0) {
                                name.append(" ").append(args[i + 3]);
                                continue;
                            }
                            name.append(args[i + 3]);
                        }
                        lore.add(LegacyComponentSerializer.legacyAmpersand().deserialize(name.toString()));
                        meta.lore(lore);
                        item.setItemMeta(meta);
                        player.getInventory().setItemInMainHand(item);
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Lore line add!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum edit_item custom add_lore_line <text>", NamedTextColor.RED));
                } else if (args[2].equalsIgnoreCase("set_lore_line")) {
                    if (args.length >= 5 && this.isInteger(args[3])) {
                        index = Integer.parseInt(args[3]) - 1;
                        lore = meta.lore() != null ? new ArrayList<>(meta.lore()) : null;
                        if (lore != null) {
                            if (lore.size() > index && index >= 0) {
                                StringBuilder name = new StringBuilder();
                                for (int i = 0; i < args.length - 4; ++i) {
                                    if (i != 0) {
                                        name.append(" ").append(args[i + 4]);
                                        continue;
                                    }
                                    name.append(args[i + 4]);
                                }
                                lore.set(index, LegacyComponentSerializer.legacyAmpersand().deserialize(name.toString()));
                                meta.lore(lore);
                                item.setItemMeta(meta);
                                player.getInventory().setItemInMainHand(item);
                                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Lore line set!", NamedTextColor.YELLOW)));
                                return;
                            }
                            player.sendMessage(Component.text("[Aurum] Error: Index is out of bounds!", NamedTextColor.RED));
                            return;
                        }
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum edit_item custom set_lore_line <index> <text>", NamedTextColor.RED));
                }
            } else if (args[1].equalsIgnoreCase("make_token")) {
                meta.getPersistentDataContainer().set(new NamespacedKey((Plugin)Aurum.getPlugin(), "doorToken"), PersistentDataType.BYTE, Byte.valueOf("1"));
                item.setItemMeta(meta);
                player.getInventory().setItemInMainHand(item);
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Made item into door token!", NamedTextColor.YELLOW)));
            } else if (args[1].equalsIgnoreCase("make_miniboss_token")) {
                meta.getPersistentDataContainer().set(new NamespacedKey((Plugin)Aurum.getPlugin(), "doorMiniBossToken"), PersistentDataType.BYTE, Byte.valueOf("1"));
                item.setItemMeta(meta);
                player.getInventory().setItemInMainHand(item);
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Made item into miniboss door token!", NamedTextColor.YELLOW)));
            } else if (args[1].equalsIgnoreCase("set_consumable_uses")) {
                if (args.length == 3 && this.isInteger(args[2])) {
                    int uses = Integer.parseInt(args[2]);
                    meta.getPersistentDataContainer().set(new NamespacedKey((Plugin)Aurum.getPlugin(), "consumableUses"), PersistentDataType.INTEGER, uses);
                    String name = LegacyComponentSerializer.legacySection().serialize(meta.displayName());
                    String pattern = " \u00a77\\[\\d+/\\d+\\]";
                    name = name.replaceAll(pattern, "");
                    name = name + " \u00a77[" + uses + "/" + uses + "]";
                    meta.displayName(LegacyComponentSerializer.legacySection().deserialize(name));
                    item.setItemMeta(meta);
                    player.getInventory().setItemInMainHand(item);
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set consumable uses!", NamedTextColor.YELLOW)));
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum edit_item set_consumable_uses <uses>", NamedTextColor.RED));
            } else {
                player.sendMessage(Component.text("[Aurum] Error: Invalid syntax!", NamedTextColor.RED));
            }
        } else {
            player.sendMessage(Component.text("[Aurum] Error: Invalid syntax!", NamedTextColor.RED));
        }
    }

    public void giveSubCommand(String[] args, Player player) {
        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(Component.text("[Aurum] Error: Your inventory is full!", NamedTextColor.RED));
            return;
        }
        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("gold_coin")) {
                player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.coinGold()});
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " gold coin!", NamedTextColor.YELLOW)));
                return;
            }
            if (args[1].equalsIgnoreCase("silver_coin")) {
                player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.coinSilver()});
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " silver coin!", NamedTextColor.YELLOW)));
                return;
            }
            if (args[1].equalsIgnoreCase("bronze_coin")) {
                player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.coinBronze()});
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " bronze coin!", NamedTextColor.YELLOW)));
                return;
            }
        }
        if (args.length == 3) {
            if (args[1].equalsIgnoreCase("rune")) {
                if (args[2].equalsIgnoreCase("Charge")) {
                    player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeCharge()});
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                    return;
                }
                if (args[2].equalsIgnoreCase("Blood_Rush")) {
                    player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeBloodRush()});
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                    return;
                }
                if (args[2].equalsIgnoreCase("Frozen_Spark")) {
                    player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeFrozenSpark()});
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                    return;
                }
                if (args[2].equalsIgnoreCase("Distortion")) {
                    player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeDistortion()});
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                    return;
                }
                if (args[2].equalsIgnoreCase("Fireball")) {
                    player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeFireball()});
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                    return;
                }
                if (args[2].equalsIgnoreCase("Heal")) {
                    player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeHeal()});
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                    return;
                }
                if (args[2].equalsIgnoreCase("Dragon_Skin")) {
                    player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeDragonSkin()});
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                    return;
                }
                if (args[2].equalsIgnoreCase("Falling_Star")) {
                    player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeFallingStar()});
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                    return;
                }
                if (args[2].equalsIgnoreCase("Fish_Lung")) {
                    player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeFishLung()});
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                    return;
                }
                if (args[2].equalsIgnoreCase("Resurgence")) {
                    player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeResurgence()});
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                } else {
                    if (args[2].equalsIgnoreCase("Shock_Wave")) {
                        player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeShockWave()});
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                        return;
                    }
                    if (args[2].equalsIgnoreCase("Smite")) {
                        player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeSmite()});
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                        return;
                    }
                    if (args[2].equalsIgnoreCase("Wind_Slash")) {
                        player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeWindSlash()});
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                        return;
                    }
                    if (args[2].equalsIgnoreCase("Swiftness")) {
                        player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeSwiftness()});
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                        return;
                    }
                    if (args[2].equalsIgnoreCase("Arcane_Shield")) {
                        player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeArcaneShield()});
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                        return;
                    }
                    if (args[2].equalsIgnoreCase("Regeneration")) {
                        player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeRegeneration()});
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                        return;
                    }
                    if (args[2].equalsIgnoreCase("Grace")) {
                        player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeGrace()});
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                        return;
                    }
                    if (args[2].equalsIgnoreCase("Vitality")) {
                        player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeVitality()});
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                        return;
                    }
                    if (args[2].equalsIgnoreCase("Restoration")) {
                        player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeRestoration()});
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                        return;
                    }
                    if (args[2].equalsIgnoreCase("Amogus")) {
                        player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeAmogus()});
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                        return;
                    }
                    if (args[2].equalsIgnoreCase("Arcane_Ray")) {
                        player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeArcaneRay()});
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                        return;
                    }
                    if (args[2].equalsIgnoreCase("Ground_Slam")) {
                        player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeGroundSlam()});
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                        return;
                    }
                    if (args[2].equalsIgnoreCase("Pirouette")) {
                        player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runePirouette()});
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                        return;
                    }
                    if (args[2].equalsIgnoreCase("Ritual")) {
                        player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.runeRitual()});
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave rune to " + player.getName() + "!", NamedTextColor.YELLOW)));
                        return;
                    }
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum give rune <rune>", NamedTextColor.RED));
                return;
            }
            if (args[1].equalsIgnoreCase("shard")) {
                if (args[2].equalsIgnoreCase("Jungle")) {
                    player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.shardJungle()});
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave shard to " + player.getName() + "!", NamedTextColor.YELLOW)));
                    return;
                }
                if (args[2].equalsIgnoreCase("Snow")) {
                    player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.shardSnow()});
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave shard to " + player.getName() + "!", NamedTextColor.YELLOW)));
                    return;
                }
                if (args[2].equalsIgnoreCase("Desert")) {
                    player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.shardDesert()});
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave shard to " + player.getName() + "!", NamedTextColor.YELLOW)));
                    return;
                }
                if (args[2].equalsIgnoreCase("Enchanted_Forest")) {
                    player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.shardEnchantedForest()});
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave shard to " + player.getName() + "!", NamedTextColor.YELLOW)));
                    return;
                }
                if (args[2].equalsIgnoreCase("Lava")) {
                    player.getInventory().addItem(new ItemStack[]{this.itemFactory.miscItems.shardLava()});
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave shard to " + player.getName() + "!", NamedTextColor.YELLOW)));
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum give shard <shard>", NamedTextColor.RED));
                return;
            }
        }
        if (args.length == 4) {
            if (this.isInteger(args[3])) {
                if (Rarity.isValidEnum(args[2])) {
                    Rarity rarity = Rarity.valueOf(args[2]);
                    if (args[1].equalsIgnoreCase("weapon")) {
                        player.getInventory().addItem(new ItemStack[]{this.itemFactory.buildWeapon(rarity, Integer.parseInt(args[3]))});
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new weapon!", NamedTextColor.YELLOW)));
                        return;
                    }
                    if (args[1].equalsIgnoreCase("helmet")) {
                        player.getInventory().addItem(new ItemStack[]{this.itemFactory.buildArmor(rarity, Integer.parseInt(args[3]), ItemType.HELMET)});
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new helmet!", NamedTextColor.YELLOW)));
                        return;
                    }
                    if (args[1].equalsIgnoreCase("chestplate")) {
                        player.getInventory().addItem(new ItemStack[]{this.itemFactory.buildArmor(rarity, Integer.parseInt(args[3]), ItemType.CHESTPLATE)});
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new chestplate!", NamedTextColor.YELLOW)));
                        return;
                    }
                    if (args[1].equalsIgnoreCase("leggings")) {
                        player.getInventory().addItem(new ItemStack[]{this.itemFactory.buildArmor(rarity, Integer.parseInt(args[3]), ItemType.LEGGINGS)});
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new leggings!", NamedTextColor.YELLOW)));
                        return;
                    }
                    if (args[1].equalsIgnoreCase("boots")) {
                        player.getInventory().addItem(new ItemStack[]{this.itemFactory.buildArmor(rarity, Integer.parseInt(args[3]), ItemType.BOOTS)});
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new boots!", NamedTextColor.YELLOW)));
                        return;
                    }
                }
            } else {
                if (args[2].equalsIgnoreCase("artifact")) {
                    if (args[1].equalsIgnoreCase("weapon")) {
                        if (args[3].equalsIgnoreCase("Wendigo")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.artifactWendigo()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new artifact weapon!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Shipwreck")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.artifactShipwreck()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new artifact weapon!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Windweaver")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.artifactWindweaver()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new artifact weapon!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Valkyrie")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.artifactValkyrie()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new artifact weapon!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Refractal")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.artifactRefractal()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new artifact weapon!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Obligation")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.artifactObligation()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new artifact weapon!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Brisingr")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.artifactBrisingr()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new artifact weapon!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Prism")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.artifactPrism()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new artifact weapon!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Sunflare")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.artifactSunflare()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new artifact weapon!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Arondight")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.artifactArondight()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new artifact weapon!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Honour")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.artifactHonour()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new artifact weapon!", NamedTextColor.YELLOW)));
                            return;
                        }
                        player.sendMessage(Component.text("[Aurum] Usage: /aurum give weapon artifact <name>", NamedTextColor.RED));
                        return;
                    }
                    if (args[1].equalsIgnoreCase("helmet")) {
                        if (args[3].equalsIgnoreCase("Velsharoon")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.artifactVelsharoon()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new artifact armor!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Raincaller")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.artifactRaincaller()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new artifact armor!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Guild_Crown")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.artifactGuildCrown()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new artifact armor!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Alignment")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.artifactAlignment()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new artifact armor!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Vagabond")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.artifactVagabond()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new artifact armor!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Full_Moon")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.artifactFullMoon()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new artifact armor!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Valhalla")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.artifactValhalla()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new artifact armor!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Last_Stand")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.artifactLastStand()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new artifact armor!", NamedTextColor.YELLOW)));
                            return;
                        }
                        player.sendMessage(Component.text("[Aurum] Usage: /aurum give helmet artifact <name>", NamedTextColor.RED));
                        return;
                    }
                }
                if (args[2].equalsIgnoreCase("eldritch")) {
                    if (args[1].equalsIgnoreCase("weapon")) {
                        if (args[3].equalsIgnoreCase("Claws_of_the_Beast")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.eldritchClawsOfTheBeast()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new eldritch weapon!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Bleeding_Thorn")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.eldritchBleedingThorn()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new eldritch weapon!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Edge_of_the_Apocalypse")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.eldritchEdgeOfTheApocalypse()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new eldritch weapon!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Blade_of_Woe")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.eldritchBladeOfWoe()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new eldritch weapon!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Soul_Binder")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.eldritchSoulBinder()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new eldritch weapon!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Call_of_the_Abyss")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.eldritchCallOfTheAbyss()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new eldritch weapon!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Spirit_Guider")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.eldritchSpiritGuider()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new eldritch weapon!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Navigator")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.eldritchNavigator()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new eldritch weapon!", NamedTextColor.YELLOW)));
                            return;
                        }
                        player.sendMessage(Component.text("[Aurum] Usage: /aurum give weapon eldritch <name>", NamedTextColor.RED));
                        return;
                    }
                    if (args[1].equalsIgnoreCase("helmet")) {
                        if (args[3].equalsIgnoreCase("Zealots_Shroud")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.eldritchZealotsShroud()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new eldritch armor!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Cap_of_Fools")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.eldritchCapOfFools()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new eldritch armor!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Steeleaf")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.eldritchSteelLeaf()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new eldritch armor!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Head_of_the_Beast")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.eldritchHeadOfTheBeast()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new eldritch armor!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("Woven_Firmament")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.eldritchWovenFirmament()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new eldritch armor!", NamedTextColor.YELLOW)));
                            return;
                        }
                        player.sendMessage(Component.text("[Aurum] Usage: /aurum give helmet eldritch <name>", NamedTextColor.RED));
                        return;
                    }
                    if (args[1].equalsIgnoreCase("chestplate")) {
                        if (args[3].equalsIgnoreCase("Ebony_Scales")) {
                            player.getInventory().addItem(new ItemStack[]{this.itemFactory.eldritchEbonyScales()});
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " new eldritch armor!", NamedTextColor.YELLOW)));
                        } else {
                            player.sendMessage(Component.text("[Aurum] Usage: /aurum give chestplate eldritch <name>", NamedTextColor.RED));
                        }
                        return;
                    }
                }
            }
        }
        player.sendMessage(Component.text("[Aurum] Error: Invalid syntax!", NamedTextColor.RED));
    }

    public void placeSubCommand(String[] args, Player player) {
        if (args[1].equalsIgnoreCase("chest_t1") || args[1].equalsIgnoreCase("chest_t2") || args[1].equalsIgnoreCase("chest_t3")) {
            ItemMeta meta;
            ItemStack itemStack;
            Location location = player.getLocation();
            location.setX(this.roundToHalf(location.getX()));
            location.setZ(this.roundToHalf(location.getZ()));
            location.setY((double)((int)location.getY()));
            assert (location.getWorld() != null);
            Slime chestHitbox = (Slime)location.getWorld().spawnEntity(location, EntityType.SLIME, false);
            chestHitbox.setSize(2);
            chestHitbox.setAI(false);
            chestHitbox.setInvulnerable(true);
            chestHitbox.setRemoveWhenFarAway(false);
            chestHitbox.setInvisible(true);
            chestHitbox.setGravity(false);
            chestHitbox.addScoreboardTag("aurum_chest");
            ArmorStand chestModel = (ArmorStand)location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
            chestModel.setVisible(false);
            chestModel.setGravity(false);
            chestModel.setInvulnerable(true);
            chestModel.addScoreboardTag("aurum_chest_model");
            chestModel.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.ADDING_OR_CHANGING);
            chestModel.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.ADDING_OR_CHANGING);
            chestModel.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.ADDING_OR_CHANGING);
            chestModel.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.ADDING_OR_CHANGING);
            chestModel.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.ADDING_OR_CHANGING);
            chestModel.addEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.ADDING_OR_CHANGING);
            if (args[1].equalsIgnoreCase("chest_t1")) {
                chestHitbox.addScoreboardTag("aurum_chest_small");
                itemStack = new ItemStack(Material.STICK);
                meta = itemStack.getItemMeta();
                assert (meta != null);
                meta.setCustomModelData(Integer.valueOf(100048));
                itemStack.setItemMeta(meta);
                assert (chestModel.getEquipment() != null);
                chestModel.getEquipment().setHelmet(itemStack);
            }
            if (args[1].equalsIgnoreCase("chest_t2")) {
                chestHitbox.addScoreboardTag("aurum_chest_medium");
                itemStack = new ItemStack(Material.STICK);
                meta = itemStack.getItemMeta();
                assert (meta != null);
                meta.setCustomModelData(Integer.valueOf(100049));
                itemStack.setItemMeta(meta);
                assert (chestModel.getEquipment() != null);
                chestModel.getEquipment().setHelmet(itemStack);
            }
            if (args[1].equalsIgnoreCase("chest_t3")) {
                chestHitbox.addScoreboardTag("aurum_chest_large");
                itemStack = new ItemStack(Material.STICK);
                meta = itemStack.getItemMeta();
                assert (meta != null);
                meta.setCustomModelData(Integer.valueOf(100050));
                itemStack.setItemMeta(meta);
                assert (chestModel.getEquipment() != null);
                chestModel.getEquipment().setHelmet(itemStack);
            }
            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Placed new chest!", NamedTextColor.YELLOW)));
            return;
        }
        if (args[1].equalsIgnoreCase("pickup_interaction")) {
            if (args[2].equalsIgnoreCase("single_use") || args[2].equalsIgnoreCase("unlimited_use")) {
                if (args.length == 4 && (args[3].equalsIgnoreCase("small") || args[3].equalsIgnoreCase("normal") || args[3].equalsIgnoreCase("large"))) {
                    if (player.getInventory().getItemInMainHand().getItemMeta() != null) {
                        Slime hitbox = (Slime)player.getWorld().spawnEntity(player.getLocation(), EntityType.SLIME);
                        if (args[3].equalsIgnoreCase("small")) {
                            hitbox.setSize(1);
                        }
                        if (args[3].equalsIgnoreCase("normal")) {
                            hitbox.setSize(2);
                        }
                        if (args[3].equalsIgnoreCase("large")) {
                            hitbox.setSize(3);
                        }
                        hitbox.setAI(false);
                        hitbox.setInvulnerable(true);
                        hitbox.setRemoveWhenFarAway(false);
                        hitbox.setInvisible(true);
                        hitbox.setGravity(false);
                        hitbox.addScoreboardTag("aurum_pickup_interaction:" + args[2].toLowerCase());
                        hitbox.addScoreboardTag("aurum_pickup_interaction");
                        assert (hitbox.getEquipment() != null);
                        hitbox.getEquipment().setHelmet(player.getInventory().getItemInMainHand());
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Pickup interaction placed!", NamedTextColor.YELLOW)));
                    } else {
                        player.sendMessage(Component.text("[Aurum] Error: You must be holding a template item while using this command!", NamedTextColor.RED));
                    }
                    return;
                }
                if (args[2].equalsIgnoreCase("single_use")) {
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum place pickup_interaction single_use <hitbox_size>", NamedTextColor.RED));
                } else {
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum place pickup_interaction unlimited_use <hitbox_size>", NamedTextColor.RED));
                }
                return;
            }
            if (args[2].equalsIgnoreCase("timed_use")) {
                if (args.length == 5 && this.isInteger(args[3]) && (args[4].equalsIgnoreCase("small") || args[4].equalsIgnoreCase("normal") || args[4].equalsIgnoreCase("large"))) {
                    if (player.getInventory().getItemInMainHand().getItemMeta() != null) {
                        Slime hitbox = (Slime)player.getWorld().spawnEntity(player.getLocation(), EntityType.SLIME);
                        if (args[4].equalsIgnoreCase("small")) {
                            hitbox.setSize(1);
                        }
                        if (args[4].equalsIgnoreCase("normal")) {
                            hitbox.setSize(2);
                        }
                        if (args[4].equalsIgnoreCase("large")) {
                            hitbox.setSize(3);
                        }
                        hitbox.setAI(false);
                        hitbox.setInvulnerable(true);
                        hitbox.setRemoveWhenFarAway(false);
                        hitbox.setInvisible(true);
                        hitbox.setGravity(false);
                        NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "aurum_pickup_interaction_time");
                        PersistentDataContainer container = hitbox.getPersistentDataContainer();
                        container.set(key, PersistentDataType.INTEGER, Integer.parseInt(args[3]));
                        hitbox.addScoreboardTag("aurum_pickup_interaction:timed_use");
                        hitbox.addScoreboardTag("aurum_pickup_interaction");
                        assert (hitbox.getEquipment() != null);
                        hitbox.getEquipment().setHelmet(player.getInventory().getItemInMainHand());
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Pickup interaction placed!", NamedTextColor.YELLOW)));
                    } else {
                        player.sendMessage(Component.text("[Aurum] Error: You must be holding a template item while using this command!", NamedTextColor.RED));
                    }
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum place pickup_interaction timed_use <cooldown in seconds> <hitbox_size>", NamedTextColor.RED));
                return;
            }
        }
        if (args[1].equalsIgnoreCase("event_interaction")) {
            if (args[2].equalsIgnoreCase("single_use") || args[2].equalsIgnoreCase("unlimited_use")) {
                if (args.length > 4 && (args[3].equalsIgnoreCase("small") || args[3].equalsIgnoreCase("normal") || args[3].equalsIgnoreCase("large"))) {
                    Slime hitbox = (Slime)player.getWorld().spawnEntity(player.getLocation(), EntityType.SLIME);
                    if (args[3].equalsIgnoreCase("small")) {
                        hitbox.setSize(1);
                    }
                    if (args[3].equalsIgnoreCase("normal")) {
                        hitbox.setSize(2);
                    }
                    if (args[3].equalsIgnoreCase("large")) {
                        hitbox.setSize(3);
                    }
                    hitbox.setAI(false);
                    hitbox.setInvulnerable(true);
                    hitbox.setRemoveWhenFarAway(false);
                    hitbox.setInvisible(true);
                    hitbox.setGravity(false);
                    hitbox.addScoreboardTag("aurum_event_interaction:" + args[2].toLowerCase());
                    hitbox.addScoreboardTag("aurum_event_interaction");
                    StringBuilder cmd = new StringBuilder();
                    for (int i = 4; i < args.length; ++i) {
                        if (i == 4) {
                            cmd = new StringBuilder(args[i]);
                            continue;
                        }
                        cmd.append(" ").append(args[i]);
                    }
                    hitbox.getPersistentDataContainer().set(new NamespacedKey((Plugin)Aurum.getPlugin(), "aurum_event_interaction_command"), PersistentDataType.STRING, cmd.toString());
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Event interaction placed!", NamedTextColor.YELLOW)));
                    return;
                }
                if (args[2].equalsIgnoreCase("single_use")) {
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum place event_interaction single_use <hitbox_size> <command>", NamedTextColor.RED));
                } else {
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum place event_interaction unlimited_use <hitbox_size> <command>", NamedTextColor.RED));
                }
                return;
            }
            if (args[2].equalsIgnoreCase("timed_use")) {
                if (args.length > 5 && this.isInteger(args[3]) && (args[4].equalsIgnoreCase("small") || args[4].equalsIgnoreCase("normal") || args[4].equalsIgnoreCase("large"))) {
                    Slime hitbox = (Slime)player.getWorld().spawnEntity(player.getLocation(), EntityType.SLIME);
                    if (args[4].equalsIgnoreCase("small")) {
                        hitbox.setSize(1);
                    }
                    if (args[4].equalsIgnoreCase("normal")) {
                        hitbox.setSize(2);
                    }
                    if (args[4].equalsIgnoreCase("large")) {
                        hitbox.setSize(3);
                    }
                    hitbox.setAI(false);
                    hitbox.setInvulnerable(true);
                    hitbox.setRemoveWhenFarAway(false);
                    hitbox.setInvisible(true);
                    hitbox.setGravity(false);
                    NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "aurum_event_interaction_time");
                    PersistentDataContainer container = hitbox.getPersistentDataContainer();
                    container.set(key, PersistentDataType.INTEGER, Integer.parseInt(args[3]));
                    hitbox.addScoreboardTag("aurum_event_interaction:timed_use");
                    hitbox.addScoreboardTag("aurum_event_interaction");
                    StringBuilder cmd = new StringBuilder();
                    for (int i = 5; i < args.length; ++i) {
                        if (i == 5) {
                            cmd = new StringBuilder(args[i]);
                            continue;
                        }
                        cmd.append(" ").append(args[i]);
                    }
                    hitbox.getPersistentDataContainer().set(new NamespacedKey((Plugin)Aurum.getPlugin(), "aurum_event_interaction_command"), PersistentDataType.STRING, cmd.toString());
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Event interaction placed!", NamedTextColor.YELLOW)));
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum place event_interaction timed_use <cooldown in seconds> <hitbox_size> <command>", NamedTextColor.RED));
                return;
            }
        }
        if (args[1].equalsIgnoreCase("conditional_interaction")) {
            if (args[2].equalsIgnoreCase("single_use") || args[2].equalsIgnoreCase("unlimited_use")) {
                if (args.length > 4 && (args[3].equalsIgnoreCase("small") || args[3].equalsIgnoreCase("normal") || args[3].equalsIgnoreCase("large"))) {
                    if (player.getInventory().getItemInMainHand().getItemMeta() != null) {
                        Slime hitbox = (Slime)player.getWorld().spawnEntity(player.getLocation(), EntityType.SLIME);
                        if (args[3].equalsIgnoreCase("small")) {
                            hitbox.setSize(1);
                        }
                        if (args[3].equalsIgnoreCase("normal")) {
                            hitbox.setSize(2);
                        }
                        if (args[3].equalsIgnoreCase("large")) {
                            hitbox.setSize(3);
                        }
                        hitbox.setAI(false);
                        hitbox.setInvulnerable(true);
                        hitbox.setRemoveWhenFarAway(false);
                        hitbox.setInvisible(true);
                        hitbox.setGravity(false);
                        assert (hitbox.getEquipment() != null);
                        hitbox.getEquipment().setHelmet(player.getInventory().getItemInMainHand());
                        hitbox.addScoreboardTag("aurum_conditional_interaction:" + args[2].toLowerCase());
                        hitbox.addScoreboardTag("aurum_conditional_interaction");
                        StringBuilder cmd = new StringBuilder();
                        for (int i = 4; i < args.length; ++i) {
                            if (i == 4) {
                                cmd = new StringBuilder(args[i]);
                                continue;
                            }
                            cmd.append(" ").append(args[i]);
                        }
                        hitbox.getPersistentDataContainer().set(new NamespacedKey((Plugin)Aurum.getPlugin(), "aurum_conditional_interaction_command"), PersistentDataType.STRING, cmd.toString());
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Conditional interaction placed!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Error: You must be holding an item!", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("single_use")) {
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum place conditional_interaction single_use <hitbox_size> <command>", NamedTextColor.RED));
                } else {
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum place conditional_interaction unlimited_use <hitbox_size> <command>", NamedTextColor.RED));
                }
                return;
            }
            if (args[2].equalsIgnoreCase("timed_use")) {
                if (args.length > 5 && this.isInteger(args[3]) && (args[4].equalsIgnoreCase("small") || args[4].equalsIgnoreCase("normal") || args[4].equalsIgnoreCase("large"))) {
                    if (player.getInventory().getItemInMainHand().getItemMeta() != null) {
                        Slime hitbox = (Slime)player.getWorld().spawnEntity(player.getLocation(), EntityType.SLIME);
                        if (args[4].equalsIgnoreCase("small")) {
                            hitbox.setSize(1);
                        }
                        if (args[4].equalsIgnoreCase("normal")) {
                            hitbox.setSize(2);
                        }
                        if (args[4].equalsIgnoreCase("large")) {
                            hitbox.setSize(3);
                        }
                        hitbox.setAI(false);
                        hitbox.setInvulnerable(true);
                        hitbox.setRemoveWhenFarAway(false);
                        hitbox.setInvisible(true);
                        hitbox.setGravity(false);
                        assert (hitbox.getEquipment() != null);
                        hitbox.getEquipment().setHelmet(player.getInventory().getItemInMainHand());
                        NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "aurum_conditional_interaction_time");
                        PersistentDataContainer container = hitbox.getPersistentDataContainer();
                        container.set(key, PersistentDataType.INTEGER, Integer.parseInt(args[3]));
                        hitbox.addScoreboardTag("aurum_conditional_interaction:timed_use");
                        hitbox.addScoreboardTag("aurum_conditional_interaction");
                        StringBuilder cmd = new StringBuilder();
                        for (int i = 5; i < args.length; ++i) {
                            if (i == 5) {
                                cmd = new StringBuilder(args[i]);
                                continue;
                            }
                            cmd.append(" ").append(args[i]);
                        }
                        hitbox.getPersistentDataContainer().set(new NamespacedKey((Plugin)Aurum.getPlugin(), "aurum_conditional_interaction_command"), PersistentDataType.STRING, cmd.toString());
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Conditional interaction placed!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Error: You must be holding an item!", NamedTextColor.RED));
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum place conditional_interaction timed_use <cooldown in seconds> <hitbox_size> <command>", NamedTextColor.RED));
                return;
            }
        }
        if (args[1].equalsIgnoreCase("spawn_node")) {
            if (args.length == 5 && (args[2].equalsIgnoreCase("slow") || args[2].equalsIgnoreCase("normal") || args[2].equalsIgnoreCase("fast") || args[2].equalsIgnoreCase("very_slow")) && DataManager.getListOfMobKeys().contains(args[3]) && this.isInteger(args[4])) {
                DataManager.addSpawnLocation(player.getLocation(), args[3], args[2].equalsIgnoreCase("slow") ? SpawnInterval.SLOW : (args[2].equalsIgnoreCase("normal") ? SpawnInterval.NORMAL : (args[2].equalsIgnoreCase("fast") ? SpawnInterval.FAST : SpawnInterval.VERY_SLOW)), Integer.parseInt(args[4]));
                try {
                    DataManager.saveToFiles();
                    DataManager.loadFromFile();
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Spawn node added!", NamedTextColor.YELLOW)));
                return;
            }
            player.sendMessage(Component.text("[Aurum] Usage: /aurum place spawn_node <spawn_interval> <mob_name> <respawn_tolerance>", NamedTextColor.RED));
            player.sendMessage(Component.text("Respawn tolerance defines how many mobs spawned by this node can live at once before the node stops spawning more mobs. This exists to stop mob clusters from forming.", NamedTextColor.GRAY));
            return;
        }
        if (args[1].equalsIgnoreCase("door") && args.length > 2) {
            if (args[2].equalsIgnoreCase("normal")) {
                if (args.length == 5 && (args[3].equals("NORTH") || args[3].equals("SOUTH") || args[3].equals("EAST") || args[3].equals("WEST")) && this.isInteger(args[4])) {
                    DoorHandler.spawnEntities(player.getLocation(), Direction.valueOf(args[3]), Integer.parseInt(args[4]), null, null, false, null, null);
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Door placed!", NamedTextColor.YELLOW)));
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum place door normal <direction> <tokens required>", NamedTextColor.RED));
                return;
            }
            if (args[2].equalsIgnoreCase("custom")) {
                if (args.length > 4 && this.isInteger(args[3])) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 4; i < args.length; ++i) {
                        stringBuilder.append(args[i]).append(" ");
                    }
                    String[] commands = this.getDoorCommands(stringBuilder.toString());
                    if (commands.length == 2) {
                        DoorHandler.spawnEntities(player.getLocation(), null, Integer.parseInt(args[3]), commands[0], commands[1], false, null, null);
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Door placed!", NamedTextColor.YELLOW)));
                        return;
                    }
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum place door custom <tokens required> <open command> <close command>", NamedTextColor.RED));
                player.sendMessage(Component.text("You need to use the \"<\" and \">\" to seperate the commands for each other, so for example: ", NamedTextColor.GRAY).append(Component.text("/aurum place door custom 32 <say open> <say close>", NamedTextColor.DARK_GRAY)));
                return;
            }
            if (args[2].equalsIgnoreCase("snow")) {
                if (args.length == 4 && this.isInteger(args[3])) {
                    DoorHandler.spawnEntities(player.getLocation(), null, Integer.parseInt(args[3]), null, null, true, null, null);
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Door placed!", NamedTextColor.YELLOW)));
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum place door snow <tokens required>", NamedTextColor.RED));
                return;
            }
            if (args[2].equalsIgnoreCase("single_block")) {
                if (args.length == 8 && this.isInteger(args[3]) && this.isInteger(args[5]) && this.isInteger(args[6]) && this.isInteger(args[7]) && Arrays.stream(Material.values()).anyMatch(x -> x.toString().equals(args[4]))) {
                    Location loc = new Location(player.getWorld(), (double)Integer.parseInt(args[5]), (double)Integer.parseInt(args[6]), (double)Integer.parseInt(args[7]));
                    DoorHandler.spawnEntities(player.getLocation(), null, Integer.parseInt(args[3]), null, null, false, Material.valueOf((String)args[4]), loc);
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Door placed!", NamedTextColor.YELLOW)));
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum place door single_block <tokens required> <block type> <x> <y> <z>", NamedTextColor.RED));
                return;
            }
            player.sendMessage(Component.text("[Aurum] Usage: /aurum place door <type>", NamedTextColor.RED));
            return;
        }
        if (args[1].equalsIgnoreCase("respawn_node")) {
            if (args.length == 3 && this.isInteger(args[2])) {
                DataManager.addRespawnLocation(player.getLocation(), Integer.parseInt(args[2]));
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Respawn nodes placed!", NamedTextColor.YELLOW)));
                try {
                    DataManager.saveToFiles();
                    DataManager.loadFromFile();
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return;
            }
            player.sendMessage(Component.text("[Aurum] Usage: /aurum place respawn_node <radius>", NamedTextColor.RED));
            return;
        }
        if (args[1].equalsIgnoreCase("boss_chest")) {
            if (args.length == 3 && this.isInteger(args[2])) {
                BossLootManager.createBossChest(player.getLocation(), Integer.parseInt(args[2]));
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("New boss chest placed!", NamedTextColor.YELLOW)));
                return;
            }
            player.sendMessage(Component.text("[Aurum] Usage: /aurum place boss_chest <level>", NamedTextColor.RED));
            return;
        }
        if (args[1].equalsIgnoreCase("item_buyer")) {
            ItemBuyer.spawnNPC(player.getLocation());
            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Placed new item buyer!", NamedTextColor.YELLOW)));
            return;
        }
        if (args[1].equalsIgnoreCase("rune_stone")) {
            RuneStoneManager.createRuneStone(player.getLocation());
            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Placed new rune stone!", NamedTextColor.YELLOW)));
            return;
        }
        if (args[1].equalsIgnoreCase("training_dummy")) {
            TrainingDummy.spawnNPC(player.getLocation());
            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Placed new training dummy!", NamedTextColor.YELLOW)));
            return;
        }
        player.sendMessage(Component.text("[Aurum] Usage: /aurum place <feature>", NamedTextColor.RED));
    }

    public void debugSubCommand(String[] args, Player player) {
        if (args.length >= 2 && args.length <= 3) {
            if (args[1].equalsIgnoreCase("show_damage")) {
                if (player.getScoreboardTags().contains("aurum_debug_damage")) {
                    player.removeScoreboardTag("aurum_debug_damage");
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Damage debug toggled to false", NamedTextColor.YELLOW)));
                } else {
                    player.addScoreboardTag("aurum_debug_damage");
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Damage debug toggled to true", NamedTextColor.YELLOW)));
                }
                return;
            }
            if (args[1].equalsIgnoreCase("reset_cooldowns")) {
                RuneAbilityHandler.resetAllCooldowns(player.getUniqueId());
                Treasure.resetChestCooldown(player.getUniqueId());
                PickupInteractionHandler.resetCooldown(player.getUniqueId());
                EventInteractionHandler.resetCooldown(player.getUniqueId());
                ConditionalInteractionHandler.resetCooldown(player.getUniqueId());
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Rest all cooldowns!", NamedTextColor.YELLOW)));
                player.sendMessage(Component.text("This command is unsupported and might cause abilities to not work for you anymore!", NamedTextColor.RED));
                return;
            }
            if (args[1].equalsIgnoreCase("open_runesmith_gui")) {
                player.openInventory(RuneSmith.createRuneSmithGUI(player));
                return;
            }
            if (args[1].equalsIgnoreCase("open_chest") && args[2] != null) {
                boolean group = player.getNearbyEntities(5.0, 5.0, 5.0).stream().anyMatch(x -> x instanceof Player);
                if (args[2].equalsIgnoreCase("small")) {
                    player.openInventory(Treasure.small_treasure(this.itemHelper.isRuneEquipped(player, Rune.GOLD_PACT), group, player.getLevel(), player));
                    player.sendMessage(Component.text("[Aurum] Opened small chest!", NamedTextColor.DARK_AQUA));
                    return;
                }
                if (args[2].equalsIgnoreCase("medium")) {
                    player.openInventory(Treasure.medium_treasure(this.itemHelper.isRuneEquipped(player, Rune.GOLD_PACT), group, player.getLevel(), player));
                    player.sendMessage(Component.text("[Aurum] Opened medium chest!", NamedTextColor.DARK_AQUA));
                    return;
                }
                if (args[2].equalsIgnoreCase("large")) {
                    player.openInventory(Treasure.large_treasure(this.itemHelper.isRuneEquipped(player, Rune.GOLD_PACT), group, player.getLevel(), player));
                    player.sendMessage(Component.text("[Aurum] Opened large chest!", NamedTextColor.DARK_AQUA));
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum debug open_chest <size>", NamedTextColor.RED));
                return;
            }
            if (args[1].equalsIgnoreCase("reset_single_use_interaction")) {
                player.getNearbyEntities(0.5, 0.5, 0.5).stream().filter(x -> x.getScoreboardTags().contains("aurum_pickup_interaction:single_use")).forEach(x -> {
                    String data;
                    NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "pickup_interaction_used");
                    PersistentDataContainer container = x.getPersistentDataContainer();
                    if (container.has(key, PersistentDataType.STRING) && (data = (String)container.get(key, PersistentDataType.STRING)) != null) {
                        data = this.removeName(data, player.getName());
                        container.set(key, PersistentDataType.STRING, data);
                    }
                });
                player.getNearbyEntities(0.5, 0.5, 0.5).stream().filter(x -> x.getScoreboardTags().contains("aurum_event_interaction:single_use")).forEach(x -> {
                    String data;
                    NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "event_interaction_used");
                    PersistentDataContainer container = x.getPersistentDataContainer();
                    if (container.has(key, PersistentDataType.STRING) && (data = (String)container.get(key, PersistentDataType.STRING)) != null) {
                        data = this.removeName(data, player.getName());
                        container.set(key, PersistentDataType.STRING, data);
                    }
                });
                player.sendMessage(Component.text("[Aurum] Reset nearby single use interactions for you!", NamedTextColor.DARK_AQUA));
                return;
            }
            if (args[1].equalsIgnoreCase("highlight_spawn_nodes")) {
                if (player.getScoreboardTags().contains("aurum_debug_nodes")) {
                    player.removeScoreboardTag("aurum_debug_nodes");
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Spawn node highlight toggled to false", NamedTextColor.YELLOW)));
                } else {
                    player.addScoreboardTag("aurum_debug_nodes");
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Spawn node highlight toggled to true", NamedTextColor.YELLOW)));
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Nodes will be visualised with following colors: ", NamedTextColor.YELLOW)).append(Component.text("Fast -> Red, Normal -> Yellow, Slow -> Green, Very Slow -> Blue", NamedTextColor.GRAY)));
                }
                return;
            }
            if (args[1].equalsIgnoreCase("reload_files")) {
                try {
                    DataManager.saveToFiles();
                    DataManager.loadFromFile();
                    ItemDataManager.saveToFile();
                    ItemDataManager.loadFromFile();
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Reloaded files!", NamedTextColor.YELLOW)));
                return;
            }
            if (args[1].equalsIgnoreCase("spawn_node_info")) {
                SpawnLocation location = this.getClosestNode(player);
                if (location != null) {
                    player.sendMessage(location.toComponent());
                }
                return;
            }
            if (args[1].equalsIgnoreCase("highlight_respawn_nodes")) {
                if (player.getScoreboardTags().contains("aurum_debug_respawn")) {
                    player.removeScoreboardTag("aurum_debug_respawn");
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Respawn node highlight toggled to false", NamedTextColor.YELLOW)));
                } else {
                    player.addScoreboardTag("aurum_debug_respawn");
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Respawn node highlight toggled to true", NamedTextColor.YELLOW)));
                }
                return;
            }
            if (args[1].equalsIgnoreCase("backup_files")) {
                DataManager.backUpFiles();
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Backed up files!", NamedTextColor.YELLOW)));
                return;
            }
            if (args[1].equalsIgnoreCase("reset_boss_chests")) {
                player.getScoreboardTags().removeIf(x -> x.contains("bossChest_"));
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Boss chests have been reset for you!", NamedTextColor.YELLOW)));
                return;
            }
            if (args[1].equalsIgnoreCase("open_item_buyer_gui")) {
                player.openInventory(ItemBuyer.createInventory());
                return;
            }
            if (args[1].equalsIgnoreCase("xp_multiplier")) {
                int m;
                if (args.length == 3 && this.isInteger(args[2]) && (m = Integer.parseInt(args[2])) > 0) {
                    Aurum.getPlugin().getConfig().set("xp-multiplier", m);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Changed xp multiplier!", NamedTextColor.YELLOW)));
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum debug xp_multiplier <value>", NamedTextColor.RED));
                return;
            }
            if (args[1].equalsIgnoreCase("open_rune_stone_gui")) {
                player.openInventory(RuneStoneManager.createGUI(player));
                return;
            }
            if (args[1].equalsIgnoreCase("spawning")) {
                if (player.getScoreboardTags().contains("aurum_debug_spawning")) {
                    player.removeScoreboardTag("aurum_debug_spawning");
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Spawning debug toggled to false", NamedTextColor.YELLOW)));
                } else {
                    player.addScoreboardTag("aurum_debug_spawning");
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Spawning debug toggled to true", NamedTextColor.YELLOW)));
                }
                return;
            }
            if (args[1].equalsIgnoreCase("spells")) {
                if (player.getScoreboardTags().contains("aurum_debug_spells")) {
                    player.removeScoreboardTag("aurum_debug_spells");
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Spell debug toggled to false", NamedTextColor.YELLOW)));
                } else {
                    player.addScoreboardTag("aurum_debug_spells");
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Spell debug toggled to true", NamedTextColor.YELLOW)));
                }
                return;
            }
            if (args[1].equalsIgnoreCase("precise_mob_spawns")) {
                if (player.getScoreboardTags().contains("aurum_debug_precise_mob_spawns")) {
                    player.removeScoreboardTag("aurum_debug_precise_mob_spawns");
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Precise mob spawns debug toggled to false", NamedTextColor.YELLOW)));
                } else {
                    player.addScoreboardTag("aurum_debug_precise_mob_spawns");
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Precise mob spawns debug toggled to true", NamedTextColor.YELLOW)));
                }
                return;
            }
            if (args[1].equalsIgnoreCase("interval_spawner_info")) {
                DataManager.getIntervalSpawnerInfo(player);
                return;
            }
            if (args[1].equalsIgnoreCase("log_spawn_diagnostics")) {
                DiagnosticLogger.writeFile();
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Saved spawning log to new file!", NamedTextColor.YELLOW)));
                return;
            }
            if (args[1].equalsIgnoreCase("getAllEntities")) {
                player.sendMessage(player.getWorld().getLivingEntities().toString());
                return;
            }
            if (args[1].equalsIgnoreCase("despawning")) {
                if (player.getScoreboardTags().contains("aurum_debug_despawning")) {
                    player.removeScoreboardTag("aurum_debug_despawning");
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Despawning debug toggled to false", NamedTextColor.YELLOW)));
                } else {
                    player.addScoreboardTag("aurum_debug_despawning");
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Despawning debug toggled to true", NamedTextColor.YELLOW)));
                }
                return;
            }
            if (args[1].equalsIgnoreCase("make_door_miniboss_door")) {
                for (Entity e : player.getNearbyEntities(3.0, 3.0, 3.0)) {
                    Slime s;
                    if (!(e instanceof Slime) || !(s = (Slime)e).getScoreboardTags().contains("aurum_door")) continue;
                    s.getPersistentDataContainer().set(new NamespacedKey((Plugin)Aurum.getPlugin(), "doorIsMiniBossDoor"), PersistentDataType.BYTE, Byte.valueOf("1"));
                }
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Made nearby doors miniboss doors!", NamedTextColor.YELLOW)));
                return;
            }
        }
        player.sendMessage(Component.text("[Aurum] Usage: /aurum debug <type>", NamedTextColor.RED));
    }

    public void removeSubCommand(String[] args, Player player) {
        if (args[1].equalsIgnoreCase("chest") && player.getName().equals("_GoldenShadow")) {
            player.getNearbyEntities(0.5, 0.5, 0.5).stream().filter(x -> x.getScoreboardTags().contains("aurum_chest")).forEach(x -> {
                if (x instanceof Slime) {
                    x.remove();
                }
            });
            player.getNearbyEntities(0.5, 0.5, 0.5).stream().filter(x -> x.getScoreboardTags().contains("aurum_chest_model")).forEach(x -> {
                if (x instanceof ArmorStand) {
                    x.remove();
                }
            });
            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Removed nearby chests!", NamedTextColor.YELLOW)));
            return;
        }
        if (args[1].equalsIgnoreCase("pickup_interaction")) {
            player.getNearbyEntities(0.5, 0.5, 0.5).stream().filter(x -> x.getScoreboardTags().contains("aurum_pickup_interaction")).forEach(x -> {
                if (x instanceof Slime) {
                    x.remove();
                }
            });
            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Removed nearby pickup_interactions!", NamedTextColor.YELLOW)));
            return;
        }
        if (args[1].equalsIgnoreCase("event_interaction")) {
            player.getNearbyEntities(0.5, 0.5, 0.5).stream().filter(x -> x.getScoreboardTags().contains("aurum_event_interaction")).forEach(x -> {
                if (x instanceof Slime) {
                    x.remove();
                }
            });
            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Removed nearby event_interactions!", NamedTextColor.YELLOW)));
            return;
        }
        if (args[1].equalsIgnoreCase("conditional_interaction")) {
            player.getNearbyEntities(0.5, 0.5, 0.5).stream().filter(x -> x.getScoreboardTags().contains("aurum_conditional_interaction")).forEach(x -> {
                if (x instanceof Slime) {
                    x.remove();
                }
            });
            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Removed nearby conditional_interactions!", NamedTextColor.YELLOW)));
            return;
        }
        if (args[1].equalsIgnoreCase("spawn_node")) {
            SpawnLocation location = this.getClosestNode(player);
            if (location != null) {
                DataManager.removeLocation(location.getUuid());
                try {
                    DataManager.saveToFiles();
                    DataManager.loadFromFile();
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Spawn node removed!", NamedTextColor.YELLOW)));
            }
            return;
        }
        if (args[1].equalsIgnoreCase("door")) {
            player.getNearbyEntities(1.0, 1.0, 1.0).stream().filter(x -> x.getScoreboardTags().contains("aurum_door")).forEach(x -> {
                if (x instanceof Slime) {
                    x.remove();
                }
                if (x instanceof ArmorStand) {
                    x.remove();
                }
            });
            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Removed nearby doors!", NamedTextColor.YELLOW)));
            return;
        }
        if (args[1].equalsIgnoreCase("respawn_node")) {
            RespawnLocation location = this.getClosestRespawnNode(player);
            if (location != null) {
                DataManager.removeRespawnLocation(location.getUuid());
                try {
                    DataManager.saveToFiles();
                    DataManager.loadFromFile();
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Respawn node removed!", NamedTextColor.YELLOW)));
            }
            return;
        }
        if (args[1].equalsIgnoreCase("boss_chest")) {
            player.getNearbyEntities(0.5, 0.5, 0.5).stream().filter(x -> x.getScoreboardTags().contains("aurum_boss_chest")).forEach(x -> {
                if (x instanceof Slime) {
                    x.remove();
                }
                if (x instanceof ArmorStand) {
                    x.remove();
                }
            });
            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Removed nearby boss chests!", NamedTextColor.YELLOW)));
            return;
        }
        if (args[1].equalsIgnoreCase("rune_stone")) {
            player.getNearbyEntities(0.5, 0.5, 0.5).stream().filter(x -> x.getScoreboardTags().contains("aurum_rune_stone")).forEach(x -> {
                if (x instanceof Slime) {
                    x.remove();
                }
                if (x instanceof ArmorStand) {
                    x.remove();
                }
            });
            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Removed nearby rune stones!", NamedTextColor.YELLOW)));
            return;
        }
        if (args[1].equalsIgnoreCase("training_dummy")) {
            player.getNearbyEntities(1.0, 1.0, 1.0).stream().filter(x -> x.getScoreboardTags().contains("aurum_training_dummy")).forEach(x -> {
                if (x instanceof Skeleton) {
                    x.remove();
                }
                if (x instanceof ArmorStand) {
                    x.remove();
                }
            });
            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Removed nearby training dummies!", NamedTextColor.YELLOW)));
            return;
        }
        player.sendMessage(Component.text("[Aurum] Usage: /aurum remove <feature>", NamedTextColor.RED));
    }

    public void toggleSubCommand(String[] args, Player player) {
        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("critical_hits")) {
                if (Aurum.getPlugin().getConfig().getBoolean("Critical-hits")) {
                    Aurum.getPlugin().getConfig().set("Critical-hits", false);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Disabled critical hits!", NamedTextColor.YELLOW)));
                } else {
                    Aurum.getPlugin().getConfig().set("Critical-hits", true);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Enabled critical hits!", NamedTextColor.YELLOW)));
                }
                return;
            }
            if (args[1].equalsIgnoreCase("kill_command")) {
                if (Aurum.getPlugin().getConfig().getBoolean("kill-command")) {
                    Aurum.getPlugin().getConfig().set("kill-command", false);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Disabled suicide command!", NamedTextColor.YELLOW)));
                } else {
                    Aurum.getPlugin().getConfig().set("kill-command", true);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Enabled suicide command!", NamedTextColor.YELLOW)));
                }
                return;
            }
            if (args[1].equalsIgnoreCase("thunder_damage")) {
                if (Aurum.getPlugin().getConfig().getBoolean("Thunder-damage")) {
                    Aurum.getPlugin().getConfig().set("Thunder-damage", false);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Disabled thunder damage!", NamedTextColor.YELLOW)));
                } else {
                    Aurum.getPlugin().getConfig().set("Thunder-damage", true);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Enabled thunder damage!", NamedTextColor.YELLOW)));
                }
                return;
            }
            if (args[1].equalsIgnoreCase("bank")) {
                if (Aurum.getPlugin().getConfig().getBoolean("Bank")) {
                    Aurum.getPlugin().getConfig().set("Bank", false);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Disabled larger bank!", NamedTextColor.YELLOW)));
                } else {
                    Aurum.getPlugin().getConfig().set("Bank", true);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Enabled larger bank!", NamedTextColor.YELLOW)));
                }
                return;
            }
            if (args[1].equalsIgnoreCase("ExperienceSystem")) {
                if (Aurum.getPlugin().getConfig().getBoolean("ExperienceSystem")) {
                    Aurum.getPlugin().getConfig().set("ExperienceSystem", false);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Disabled Experience System!", NamedTextColor.YELLOW)));
                } else {
                    Aurum.getPlugin().getConfig().set("ExperienceSystem", true);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Enabled Experience System!", NamedTextColor.YELLOW)));
                }
                return;
            }
            if (args[1].equalsIgnoreCase("RespawnSystem")) {
                if (Aurum.getPlugin().getConfig().getBoolean("RespawnSystem")) {
                    Aurum.getPlugin().getConfig().set("RespawnSystem", false);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Disabled Respawn System!", NamedTextColor.YELLOW)));
                } else {
                    Aurum.getPlugin().getConfig().set("RespawnSystem", true);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Enabled Respawn System!", NamedTextColor.YELLOW)));
                }
                return;
            }
            if (args[1].equalsIgnoreCase("deny_block_interactions")) {
                if (Aurum.getPlugin().getConfig().getBoolean("deny-blocks")) {
                    Aurum.getPlugin().getConfig().set("deny-blocks", false);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Disabled block interaction denying!", NamedTextColor.YELLOW)));
                } else {
                    Aurum.getPlugin().getConfig().set("deny-blocks", true);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Enabled block interactions denying!", NamedTextColor.YELLOW)));
                }
                return;
            }
            if (args[1].equalsIgnoreCase("deny_openable_interactions")) {
                if (Aurum.getPlugin().getConfig().getBoolean("deny-openable")) {
                    Aurum.getPlugin().getConfig().set("deny-openable", false);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Disabled openable interaction denying!", NamedTextColor.YELLOW)));
                } else {
                    Aurum.getPlugin().getConfig().set("deny-openable", true);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Enabled openable interactions denying!", NamedTextColor.YELLOW)));
                }
                return;
            }
            if (args[1].equalsIgnoreCase("advertisement")) {
                if (Aurum.getPlugin().getConfig().getBoolean("advertisement")) {
                    Aurum.getPlugin().getConfig().set("advertisement", false);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Disabled advertisement!", NamedTextColor.YELLOW)));
                } else {
                    Aurum.getPlugin().getConfig().set("advertisement", true);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Enabled advertisement!", NamedTextColor.YELLOW)));
                }
                return;
            }
            if (args[1].equalsIgnoreCase("passive_regen")) {
                if (Aurum.getPlugin().getConfig().getBoolean("passive-regen")) {
                    Aurum.getPlugin().getConfig().set("passive-regen", false);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Disabled passive regeneration!", NamedTextColor.YELLOW)));
                } else {
                    Aurum.getPlugin().getConfig().set("passive-regen", true);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Enabled passive regeneration!", NamedTextColor.YELLOW)));
                }
            }
            if (args[1].equalsIgnoreCase("pvp")) {
                if (Aurum.getPlugin().getConfig().getBoolean("pvp")) {
                    Aurum.getPlugin().getConfig().set("pvp", false);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Disabled PvP!", NamedTextColor.YELLOW)));
                } else {
                    Aurum.getPlugin().getConfig().set("pvp", true);
                    Aurum.getPlugin().saveConfig();
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Enabled PvP!", NamedTextColor.YELLOW)));
                }
            }
        }
    }

    public void mobSubCommand(String[] args, Player player) {
        if (args[1].equalsIgnoreCase("new")) {
            for (String mob : this.livingEntities) {
                if (!mob.equals(args[2])) continue;
                EntityData e = new EntityData();
                e.setType(EntityType.valueOf((String)args[2]));
                this.editMob.put(player.getUniqueId(), e);
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Editing new mob! Run ", NamedTextColor.YELLOW)).append(Component.text("/aurum mob info", NamedTextColor.GRAY)).append(Component.text(" to see the current stats!", NamedTextColor.YELLOW)));
                return;
            }
            player.sendMessage(Component.text("[Aurum] Usage: /aurum mob new <type>", NamedTextColor.RED));
            return;
        }
        if (args[1].equalsIgnoreCase("info")) {
            if (this.editMob.containsKey(player.getUniqueId())) {
                player.sendMessage(this.editMob.get(player.getUniqueId()).toComponent());
                return;
            }
            player.sendMessage(Component.text("[Aurum] Error: You aren't currently editing a mob!", NamedTextColor.RED));
            return;
        }
        if (args[1].equalsIgnoreCase("scrap")) {
            if (this.editMob.containsKey(player.getUniqueId())) {
                this.editMob.remove(player.getUniqueId());
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Scrapped the current mob you were editing!", NamedTextColor.YELLOW)));
                return;
            }
            player.sendMessage(Component.text("[Aurum] Error: You aren't currently editing a mob!", NamedTextColor.RED));
        }
        if (args[1].equalsIgnoreCase("delete")) {
            if (args.length == 3) {
                if (DataManager.getListOfMobKeys().contains(args[2].toLowerCase())) {
                    DataManager.removeMob(args[2].toLowerCase());
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Deleted mob!", NamedTextColor.YELLOW)));
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Error: No such mob exists!", NamedTextColor.RED));
                return;
            }
            player.sendMessage(Component.text("[Aurum] Usage: /aurum mob delete <name>", NamedTextColor.RED));
            return;
        }
        if (args[1].equalsIgnoreCase("spawn") && args.length == 3) {
            if (DataManager.getListOfMobKeys().contains(args[2].toLowerCase())) {
                EntityData data = DataManager.getMobByName(args[2].toLowerCase());
                assert (data != null);
                LivingEntity e = (LivingEntity)player.getWorld().spawnEntity(player.getLocation(), data.getType(), false);
                CustomEntity.deserialize(e, DataManager.getMobByName(args[2].toLowerCase()), null);
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Spawned mob!", NamedTextColor.YELLOW)));
                return;
            }
            player.sendMessage(Component.text("[Aurum] Error: No mob by that name exists!", NamedTextColor.RED));
            return;
        }
        if (args[1].equalsIgnoreCase("save")) {
            if (args.length == 3) {
                if (!DataManager.getListOfMobKeys().contains(args[2].toLowerCase())) {
                    DataManager.addMob(this.editMob.get(player.getUniqueId()), args[2].toLowerCase());
                    try {
                        DataManager.saveToFiles();
                    }
                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Saved mob!", NamedTextColor.YELLOW)));
                    this.editMob.remove(player.getUniqueId());
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Error: A mob with this name already exists!", NamedTextColor.RED));
                return;
            }
            player.sendMessage(Component.text("[Aurum] Usage: /aurum mob save <name>", NamedTextColor.RED));
            return;
        }
        if (args[1].equalsIgnoreCase("edit")) {
            if (this.editMob.containsKey(player.getUniqueId())) {
                if (args[2].equalsIgnoreCase("set_name")) {
                    if (args.length > 3) {
                        StringBuilder name = new StringBuilder();
                        for (int i = 3; i < args.length; ++i) {
                            if (i != 3) {
                                name.append(" ").append(args[i]);
                                continue;
                            }
                            name.append(args[i]);
                        }
                        this.editMob.get(player.getUniqueId()).setName(name.toString());
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set name!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit set_name <name>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("set_mainhand")) {
                    if (args.length == 3) {
                        this.editMob.get(player.getUniqueId()).setMainhand(this.encodeItem(player.getInventory().getItemInMainHand()));
                        this.editMob.get(player.getUniqueId()).setMainhandName(player.getInventory().getItemInMainHand().getType().name());
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set mainhand!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit set_mainhand", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("loot_chance")) {
                    if (args.length == 4 && this.isDouble(args[3])) {
                        this.editMob.get(player.getUniqueId()).setItemDropChance(Double.parseDouble(args[3]));
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set drop chance!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit loot_chance <0 - 1>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("set_offhand")) {
                    if (args.length == 3) {
                        this.editMob.get(player.getUniqueId()).setOffhand(this.encodeItem(player.getInventory().getItemInMainHand()));
                        this.editMob.get(player.getUniqueId()).setOffhandName(player.getInventory().getItemInMainHand().getType().name());
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set offhand!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit set_offhand", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("set_helmet")) {
                    if (args.length == 3) {
                        this.editMob.get(player.getUniqueId()).setHelmet(this.encodeItem(player.getInventory().getItemInMainHand()));
                        this.editMob.get(player.getUniqueId()).setHelmetName(player.getInventory().getItemInMainHand().getType().name());
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set helmet!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit set_helmet", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("set_chestplate")) {
                    if (args.length == 3) {
                        this.editMob.get(player.getUniqueId()).setChestplate(this.encodeItem(player.getInventory().getItemInMainHand()));
                        this.editMob.get(player.getUniqueId()).setChestplateName(player.getInventory().getItemInMainHand().getType().name());
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set chestplate!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit set_chestplate", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("set_leggings")) {
                    if (args.length == 3) {
                        this.editMob.get(player.getUniqueId()).setLeggings(this.encodeItem(player.getInventory().getItemInMainHand()));
                        this.editMob.get(player.getUniqueId()).setLeggingsName(player.getInventory().getItemInMainHand().getType().name());
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set leggings!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit set_leggings", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("set_boots")) {
                    if (args.length == 3) {
                        this.editMob.get(player.getUniqueId()).setBoots(this.encodeItem(player.getInventory().getItemInMainHand()));
                        this.editMob.get(player.getUniqueId()).setBootsName(player.getInventory().getItemInMainHand().getType().name());
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set boots!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit set_boots", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("set_level")) {
                    if (args.length == 4 && this.isInteger(args[3])) {
                        this.editMob.get(player.getUniqueId()).setLevel(Integer.parseInt(args[3]));
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set level!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit set_level <level>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("set_health")) {
                    if (args.length == 4 && this.isInteger(args[3])) {
                        this.editMob.get(player.getUniqueId()).setHealth(Integer.parseInt(args[3]));
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set health!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit set_health <health>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("set_spell_damage_multiplier")) {
                    if (args.length == 4 && this.isDouble(args[3])) {
                        this.editMob.get(player.getUniqueId()).setSpellDamageMultiplier(Double.parseDouble(args[3]));
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set spell damage multiplier!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit set_spell_damage_multiplier <double>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("set_damage")) {
                    if (args.length == 4 && this.isDouble(args[3])) {
                        this.editMob.get(player.getUniqueId()).setDamage(Double.parseDouble(args[3]));
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set damage!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit set_damage <damage>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("set_knockback_resistance")) {
                    if (args.length == 4 && this.isDouble(args[3])) {
                        this.editMob.get(player.getUniqueId()).setKnockbackResistance(Double.parseDouble(args[3]));
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set knockback resistance!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit set_damage <damage>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("set_speed")) {
                    if (args.length == 4 && this.isDouble(args[3])) {
                        this.editMob.get(player.getUniqueId()).setSpeed(Double.parseDouble(args[3]));
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set speed!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit set_speed <speed>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("set_knockback")) {
                    if (args.length == 4 && this.isDouble(args[3])) {
                        this.editMob.get(player.getUniqueId()).setKnockback(Double.parseDouble(args[3]));
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set knockback!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit set_knockback <knockback>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("set_follow_range")) {
                    if (args.length == 4 && this.isDouble(args[3])) {
                        this.editMob.get(player.getUniqueId()).setFollowRange(Double.parseDouble(args[3]));
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set follow range!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit set_follow_range <follow range>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("set_loot")) {
                    if (args.length == 4) {
                        if (args[3].equalsIgnoreCase("default")) {
                            this.editMob.get(player.getUniqueId()).setLootType(true);
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set loot type to default!", NamedTextColor.YELLOW)));
                            return;
                        }
                        if (args[3].equalsIgnoreCase("custom") || args[3].equalsIgnoreCase("none")) {
                            this.editMob.get(player.getUniqueId()).setLootType(false);
                            ItemStack item = args[3].equalsIgnoreCase("custom") ? player.getInventory().getItemInMainHand() : new ItemStack(Material.AIR);
                            this.editMob.get(player.getUniqueId()).setCustomLoot(this.encodeItem(item));
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set loot type!", NamedTextColor.YELLOW)));
                            return;
                        }
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit set_loot <type>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("set_xp_type")) {
                    if (args.length == 4 && (args[3].equalsIgnoreCase("none") || args[3].equalsIgnoreCase("low") || args[3].equalsIgnoreCase("normal") || args[3].equalsIgnoreCase("high") || args[3].equalsIgnoreCase("boss"))) {
                        if (args[3].equalsIgnoreCase("none")) {
                            this.editMob.get(player.getUniqueId()).setXpType(XpType.NONE);
                        }
                        if (args[3].equalsIgnoreCase("low")) {
                            this.editMob.get(player.getUniqueId()).setXpType(XpType.LOW);
                        }
                        if (args[3].equalsIgnoreCase("normal")) {
                            this.editMob.get(player.getUniqueId()).setXpType(XpType.NORMAL);
                        }
                        if (args[3].equalsIgnoreCase("high")) {
                            this.editMob.get(player.getUniqueId()).setXpType(XpType.HIGH);
                        }
                        if (args[3].equalsIgnoreCase("boss")) {
                            this.editMob.get(player.getUniqueId()).setXpType(XpType.BOSS);
                        }
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set xp type!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit set_xp_type <xp_type>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("isBaby")) {
                    if (args.length == 4 && (args[3].equalsIgnoreCase("true") || args[3].equalsIgnoreCase("false"))) {
                        this.editMob.get(player.getUniqueId()).setBaby(args[3].equalsIgnoreCase("true"));
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set age!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit isBaby <boolean>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("sheep_wool_color")) {
                    if (args.length == 4) {
                        for (String s : new String[]{"BLACK", "BLUE", "BROWN", "CYAN", "GRAY", "GREEN", "LIGHT_BLUE", "LIGHT_GRAY", "LIME", "MAGENTA", "ORANGE", "PINK", "PURPLE", "RED", "WHITE", "YELLOW"}) {
                            if (!s.equals(args[3])) continue;
                            this.editMob.get(player.getUniqueId()).setDyeColor(DyeColor.valueOf((String)args[3]));
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Dye color set!", NamedTextColor.YELLOW)));
                            return;
                        }
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit sheep_wool_color <color>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("horse_pattern")) {
                    if (args.length == 4) {
                        for (String s : new String[]{"BLACK_DOTS", "NONE", "WHITE", "WHITE_DOTS", "WHITEFIELD"}) {
                            if (!s.equals(args[3])) continue;
                            this.editMob.get(player.getUniqueId()).setHorseStyle(Horse.Style.valueOf((String)args[3]));
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Horse pattern set!", NamedTextColor.YELLOW)));
                            return;
                        }
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit horse_pattern <pattern>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("horse_color")) {
                    if (args.length == 4) {
                        for (String s : new String[]{"BLACK", "BROWN", "CHESTNUT", "CREAMY", "DARK_BROWN", "GRAY", "WHITE"}) {
                            if (!s.equals(args[3])) continue;
                            this.editMob.get(player.getUniqueId()).setHorseColor(Horse.Color.valueOf((String)args[3]));
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Horse color set!", NamedTextColor.YELLOW)));
                            return;
                        }
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit horse_color <color>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("frog_variant")) {
                    if (args.length == 4) {
                        for (String s : new String[]{"COLD", "WARM", "TEMPERATE"}) {
                            if (!s.equals(args[3])) continue;
                            this.editMob.get(player.getUniqueId()).setFrogVariant(Frog.Variant.valueOf((String)args[3]));
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Frog variant set!", NamedTextColor.YELLOW)));
                            return;
                        }
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit frog_variant <variant>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("parrot_variant")) {
                    if (args.length == 4) {
                        for (String s : new String[]{"BLUE", "CYAN", "GRAY", "GREEN", "RED"}) {
                            if (!s.equals(args[3])) continue;
                            this.editMob.get(player.getUniqueId()).setParrotVariant(Parrot.Variant.valueOf((String)args[3]));
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Parrot variant set!", NamedTextColor.YELLOW)));
                            return;
                        }
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit parrot_variant <variant>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("mushroom_cow_variant")) {
                    if (args.length == 4) {
                        for (String s : new String[]{"BROWN", "RED"}) {
                            if (!s.equals(args[3])) continue;
                            this.editMob.get(player.getUniqueId()).setMushroomCowVariant(MushroomCow.Variant.valueOf((String)args[3]));
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Mushroom cow variant set!", NamedTextColor.YELLOW)));
                            return;
                        }
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit mushroom_cow_variant <variant>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("llama_color")) {
                    if (args.length == 4) {
                        for (String s : new String[]{"BROWN", "CREAMY", "GRAY", "WHITE"}) {
                            if (!s.equals(args[3])) continue;
                            this.editMob.get(player.getUniqueId()).setLlamaColor(Llama.Color.valueOf((String)args[3]));
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Llama color set!", NamedTextColor.YELLOW)));
                            return;
                        }
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit llama_color <color>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("villager_biome")) {
                    if (args.length == 4) {
                        for (String s : new String[]{"DESERT", "JUNGLE", "PLAINS", "SAVANNA", "SNOW", "SWAMP", "TAIGA"}) {
                            if (!s.equals(args[3])) continue;
                            this.editMob.get(player.getUniqueId()).setVillagerType(Villager.Type.valueOf((String)args[3]));
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Villager biome set!", NamedTextColor.YELLOW)));
                            return;
                        }
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit villager_biome <biome>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("villager_profession")) {
                    if (args.length == 4) {
                        for (String s : new String[]{"ARMORER", "BUTCHER", "CARTOGRAPHER", "CLERIC", "FARMER", "FISHERMAN", "FLETCHER", "LEATHERWORKER", "LIBRARIAN", "MASON", "NITWIT", "SHEPHERD", "NONE", "SHEPHERD", "TOOLSMITH", "WEAPONSMITH"}) {
                            if (!s.equals(args[3])) continue;
                            this.editMob.get(player.getUniqueId()).setVillagerProfession(Villager.Profession.valueOf((String)args[3]));
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Villager profession set!", NamedTextColor.YELLOW)));
                            return;
                        }
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit villager_profession <profession>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("add_spell")) {
                    if (args.length == 4) {
                        for (String s : new String[]{"Charge", "Slash", "Watched", "Missile", "Repel", "Fangs", "Flash", "Blizzard", "Explosion", "Earthquake", "Venom_spit", "Faze", "Shockwave", "Heal", "Flame"}) {
                            if (!s.equals(args[3])) continue;
                            SpellName[] oldSpellNames = this.editMob.get(player.getUniqueId()).getSpells();
                            SpellName[] newSpellNames = new SpellName[oldSpellNames.length + 1];
                            System.arraycopy(oldSpellNames, 0, newSpellNames, 0, oldSpellNames.length);
                            newSpellNames[newSpellNames.length - 1] = SpellName.valueOf(args[3].toUpperCase());
                            this.editMob.get(player.getUniqueId()).setSpells(newSpellNames);
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Spell added!", NamedTextColor.YELLOW)));
                            return;
                        }
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit add_spell <spell>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("remove_spell")) {
                    if (args.length == 4) {
                        for (String s : new String[]{"Charge", "Slash", "Watched", "Missile", "Repel", "Fangs", "Flash", "Blizzard", "Explosion", "Earthquake", "Venom_spit", "Faze", "Shockwave", "Heal", "Flame"}) {
                            if (!s.equals(args[3])) continue;
                            ArrayList<SpellName> resultList = new ArrayList<SpellName>();
                            for (SpellName spellName : this.editMob.get(player.getUniqueId()).getSpells()) {
                                if (spellName.toString().equalsIgnoreCase(args[3])) continue;
                                resultList.add(spellName);
                            }
                            this.editMob.get(player.getUniqueId()).setSpells(resultList.toArray(new SpellName[0]));
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Spell removed!", NamedTextColor.YELLOW)));
                            return;
                        }
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit remove_spell <spell>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("set_ai_type")) {
                    if (args.length == 4) {
                        for (String s : new String[]{"HOSTILE", "VANILLA", "FRIENDLY", "HOSTILE_RANGED", "FRIENDLY_SCARED"}) {
                            if (!s.equals(args[3])) continue;
                            this.editMob.get(player.getUniqueId()).setAiType(AIType.valueOf(args[3]));
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("AI type set!", NamedTextColor.YELLOW)));
                            return;
                        }
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit set_ai_type <AI_Type>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("set_fox_type")) {
                    if (args.length == 4) {
                        for (String s : new String[]{"RED", "SNOW"}) {
                            if (!s.equals(args[3])) continue;
                            this.editMob.get(player.getUniqueId()).setFoxType(Fox.Type.valueOf((String)args[3]));
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Fox type set!", NamedTextColor.YELLOW)));
                            return;
                        }
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit set_fox_type <color>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("set_rabbit_type")) {
                    if (args.length == 4) {
                        for (String s : new String[]{"BLACK", "BLACK_AND_WHITE", "BROWN", "GOLD", "SALT_AND_PEPPER", "THE_KILLER_BUNNY", "WHITE"}) {
                            if (!s.equals(args[3])) continue;
                            this.editMob.get(player.getUniqueId()).setRabbitType(Rabbit.Type.valueOf((String)args[3]));
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Rabbit type set!", NamedTextColor.YELLOW)));
                            return;
                        }
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit set_rabbit_type <type>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("creeper_explosion_radius")) {
                    if (args.length == 4 && this.isInteger(args[3])) {
                        this.editMob.get(player.getUniqueId()).setCreeperExplosionRadius(Integer.parseInt(args[3]));
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Creeper explosion radius set!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit creeper_explosion_radius <radius>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("creeper_max_fuse_tick")) {
                    if (args.length == 4 && this.isInteger(args[3])) {
                        this.editMob.get(player.getUniqueId()).setCreeperMaxFuseTicks(Integer.parseInt(args[3]));
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Creeper max fuse tick set!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit creeper_max_fuse_tick <tick>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("creeper_powered")) {
                    if (args.length == 4 && (args[3].equals("true") || args[3].equals("false"))) {
                        this.editMob.get(player.getUniqueId()).setCreeperPowered(Boolean.parseBoolean(args[3]));
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Creeper creeper type!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum mob edit creeper_powered <boolean>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("spell_cast_chance")) {
                    if (args.length == 4 && this.isDouble(args[3])) {
                        this.editMob.get(player.getUniqueId()).setSpellCastChance(Double.parseDouble(args[3]));
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set spell cast chance!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("Usage: /aurum mob edit spell_cast_chance <chance (0.0 to 1.0)>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("size")) {
                    if (args.length == 4 && this.isInteger(args[3])) {
                        this.editMob.get(player.getUniqueId()).setSize(Integer.parseInt(args[3]));
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Set size!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("Usage: /aurum mob edit size <size>", NamedTextColor.RED));
                    return;
                }
            }
            player.sendMessage(Component.text("[Aurum] Error: You aren't currently editing a mob! Run ", NamedTextColor.RED).append(Component.text("/aurum mob new <type> ", NamedTextColor.GRAY)).append(Component.text("to start making a new one!", NamedTextColor.RED)));
            return;
        }
        if (args[1].equalsIgnoreCase("test_spawn")) {
            if (this.editMob.containsKey(player.getUniqueId())) {
                LivingEntity entity = (LivingEntity)player.getWorld().spawnEntity(player.getLocation(), this.editMob.get(player.getUniqueId()).getType());
                CustomEntity.deserialize(entity, this.editMob.get(player.getUniqueId()), null);
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Test spawned mob!", NamedTextColor.YELLOW)));
                return;
            }
            player.sendMessage(Component.text("[Aurum] Error: You are not currently editing a mob!", NamedTextColor.RED));
            return;
        }
        if (args[1].equalsIgnoreCase("edit_saved") && args.length == 3) {
            if (DataManager.getListOfMobKeys().contains(args[2].toLowerCase())) {
                this.editMob.put(player.getUniqueId(), DataManager.getMobByName(args[2]));
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Now editing existing mob!", NamedTextColor.YELLOW)));
                return;
            }
            player.sendMessage(Component.text("[Aurum] Error: No mob by that name exists!", NamedTextColor.RED));
        }
    }

    public void xpSubCommand(String[] args, Player player) {
        if (args.length >= 3) {
            Player target = this.getPlayerFromName(args[2], player);
            if (target != null) {
                if (args[1].equalsIgnoreCase("set_xp")) {
                    if (args.length == 4 && this.isInteger(args[3])) {
                        ExperienceManager.setXP(target, Integer.parseInt(args[3]));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum xp set_xp <name> <value>", NamedTextColor.RED));
                    return;
                }
                if (args[1].equalsIgnoreCase("set_level")) {
                    if (args.length == 4 && this.isInteger(args[3])) {
                        ExperienceManager.setLevel(target, Integer.parseInt(args[3]));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum xp set_level <name> <value>", NamedTextColor.RED));
                    return;
                }
                if (args[1].equalsIgnoreCase("add_xp")) {
                    if (args.length == 4 && this.isInteger(args[3])) {
                        ExperienceManager.addXP(target, Integer.parseInt(args[3]), false);
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum xp add_xp <name> <value>", NamedTextColor.RED));
                    return;
                }
                if (args[1].equalsIgnoreCase("remove_xp") && args.length == 4 && this.isInteger(args[3])) {
                    ExperienceManager.removeXp(target, Integer.parseInt(args[3]));
                    return;
                }
                if (args[1].equalsIgnoreCase("get_xp")) {
                    if (args.length == 3) {
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text(args[2] + "'s xp is: " + ExperienceManager.getXP(target), NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum xp get_xp <name>", NamedTextColor.RED));
                    return;
                }
                if (args[1].equalsIgnoreCase("reset")) {
                    if (args.length == 3) {
                        ExperienceManager.resetLevelAndXP(target);
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum xp reset <name>", NamedTextColor.RED));
                    return;
                }
                if (args[1].equalsIgnoreCase("hard_reset")) {
                    if (args.length == 3) {
                        ExperienceManager.hardReset(target);
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum xp hard_reset <name>", NamedTextColor.RED));
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Usage: /aurum xp <operation>", NamedTextColor.RED));
                return;
            }
            player.sendMessage(Component.text("[Aurum] Error: Invalid target!", NamedTextColor.RED));
        }
        player.sendMessage(Component.text("[Aurum] Usage: /aurum xp <operation>", NamedTextColor.RED));
    }

    public void itemSubCommand(String[] args, Player player) {
        if (args.length >= 3) {
            if (args[1].equalsIgnoreCase("save")) {
                String name = args[2];
                if (ItemDataManager.getItemKeys().contains(name.toLowerCase())) {
                    player.sendMessage(Component.text("[Aurum] Error: An item with that name exists already! Please delete the old one before saving a new item under this name!", NamedTextColor.RED));
                    return;
                }
                if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                    player.sendMessage(Component.text("[Aurum] Error: You must be holding the item you want to save!", NamedTextColor.RED));
                    return;
                }
                ItemDataManager.saveItem(player.getInventory().getItemInMainHand().clone(), name.toLowerCase());
                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Saved item!", NamedTextColor.YELLOW)));
                return;
            }
            if (args[1].equalsIgnoreCase("give")) {
                if (ItemDataManager.getItemKeys().contains(args[2].toLowerCase())) {
                    player.getInventory().addItem(new ItemStack[]{ItemDataManager.getItem(args[2].toLowerCase())});
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave you saved item!", NamedTextColor.YELLOW)));
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Error: No saved item of that name exists!", NamedTextColor.RED));
                return;
            }
            if (args[1].equalsIgnoreCase("delete")) {
                if (ItemDataManager.getItemKeys().contains(args[2].toLowerCase())) {
                    ItemDataManager.deleteItem(args[2].toLowerCase());
                    player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Item deleted!", NamedTextColor.YELLOW)));
                    return;
                }
                player.sendMessage(Component.text("[Aurum] Error: No saved item of that name exists!", NamedTextColor.RED));
                return;
            }
            if (args[1].equalsIgnoreCase("group") && args.length >= 4) {
                if (args[2].equalsIgnoreCase("create")) {
                    if (args.length == 4) {
                        if (ItemDataManager.getGroups().contains(args[3].toLowerCase())) {
                            player.sendMessage(Component.text("[Aurum] Error: A group of this name already exists!", NamedTextColor.RED));
                            return;
                        }
                        ItemDataManager.createGroup(args[3].toLowerCase());
                        player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("New group created!", NamedTextColor.YELLOW)));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum item group create <name>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("delete")) {
                    if (args.length == 4) {
                        if (ItemDataManager.getGroups().contains(args[3].toLowerCase())) {
                            ItemDataManager.deleteGroup(args[3].toLowerCase());
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Group deleted!", NamedTextColor.YELLOW)));
                            return;
                        }
                        player.sendMessage(Component.text("[Aurum] Error: No group of this name exists!", NamedTextColor.RED));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum item group delete <group>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("give")) {
                    if (args.length == 4) {
                        if (ItemDataManager.getGroups().contains(args[3].toLowerCase())) {
                            for (ItemStack i : ItemDataManager.getItemsFromGroup(args[3].toLowerCase())) {
                                player.getInventory().addItem(new ItemStack[]{i});
                            }
                            player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave you all group items!", NamedTextColor.YELLOW)));
                            return;
                        }
                        player.sendMessage(Component.text("[Aurum] Error: No group of this name exists!", NamedTextColor.RED));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum item group give <group>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("give_nearest")) {
                    if (args.length == 4) {
                        if (ItemDataManager.getGroups().contains(args[3].toLowerCase())) {
                            for (ItemStack i : ItemDataManager.getItemsFromGroup(args[3].toLowerCase())) {
                                player.getInventory().addItem(new ItemStack[]{i});
                            }
                            return;
                        }
                        player.sendMessage(Component.text("[Aurum] Error: No group of this name exists!", NamedTextColor.RED));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum item group give_nearest <group>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("add_item")) {
                    if (args.length == 5) {
                        if (ItemDataManager.getGroups().contains(args[3].toLowerCase())) {
                            if (ItemDataManager.getItemKeys().contains(args[4].toLowerCase())) {
                                ItemDataManager.addItemToGroup(args[3].toLowerCase(), args[4].toLowerCase());
                                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Added item to group!", NamedTextColor.YELLOW)));
                                return;
                            }
                            player.sendMessage(Component.text("[Aurum] Error: No item of this name exists!", NamedTextColor.RED));
                            return;
                        }
                        player.sendMessage(Component.text("[Aurum] Error: No group of this name exists!", NamedTextColor.RED));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum item group add_item <group> <item>", NamedTextColor.RED));
                    return;
                }
                if (args[2].equalsIgnoreCase("remove_item")) {
                    if (args.length == 5) {
                        if (ItemDataManager.getGroups().contains(args[3].toLowerCase())) {
                            if (ItemDataManager.getItemKeys().contains(args[4].toLowerCase())) {
                                ItemDataManager.removeItemFromGroup(args[3].toLowerCase(), args[4].toLowerCase());
                                player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Removed item from group!", NamedTextColor.YELLOW)));
                                return;
                            }
                            player.sendMessage(Component.text("[Aurum] Error: No item of this name exists!", NamedTextColor.RED));
                            return;
                        }
                        player.sendMessage(Component.text("[Aurum] Error: No group of this name exists!", NamedTextColor.RED));
                        return;
                    }
                    player.sendMessage(Component.text("[Aurum] Usage: /aurum item group remove_item <group> <item>", NamedTextColor.RED));
                    return;
                }
            }
        }
        player.sendMessage(Component.text("[Aurum] Usage: /aurum item <give/save/delete/group> ...", NamedTextColor.RED));
    }

    public boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    private String removeName(String names, String nameToRemove) {
        String[] nameArray = names.split(",");
        StringBuilder result = new StringBuilder();
        for (String n : nameArray) {
            String trimmed = n.trim();
            if (trimmed.equals(nameToRemove)) continue;
            result.append(trimmed).append(",");
        }
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    @Nullable
    private SpawnLocation getClosestNode(Player player) {
        SpawnLocation spawnLocation = null;
        double lowestDistance = 1000.0;
        for (SpawnLocation loc : DataManager.getSpawnLocationList()) {
            double d;
            if (loc.getWorld() != player.getWorld() || !((d = loc.getLocation().distance(player.getLocation())) <= 4.0) || !(d < lowestDistance)) continue;
            lowestDistance = d;
            spawnLocation = loc;
        }
        if (spawnLocation == null) {
            player.sendMessage(Component.text("[Aurum] Error: There is no spawn node nearby!", NamedTextColor.RED));
        }
        return spawnLocation;
    }

    private double roundToHalf(double num) {
        return (int)num < 0 ? (double)((int)num) - 0.5 : (double)((int)num) + 0.5;
    }

    private String[] getDoorCommands(String input) {
        ArrayList<String> matches = new ArrayList<String>();
        int index = 0;
        while (index < input.length()) {
            int start = input.indexOf("<", index);
            int end = input.indexOf(">", start);
            if (start == -1 || end == -1) break;
            String match = input.substring(start + 1, end);
            matches.add(match);
            index = end + 1;
        }
        String[] result = new String[matches.size()];
        result = matches.toArray(result);
        return result;
    }

    @Nullable
    private RespawnLocation getClosestRespawnNode(Player player) {
        RespawnLocation spawnLocation = null;
        double lowestDistance = 1000.0;
        for (RespawnLocation loc : DataManager.getRespawnLocationList()) {
            double d;
            if (loc.getLocation().getWorld() != player.getWorld() || !((d = loc.getLocation().distance(player.getLocation())) <= 4.0) || !(d < lowestDistance)) continue;
            lowestDistance = d;
            spawnLocation = loc;
        }
        if (spawnLocation == null) {
            player.sendMessage(Component.text("[Aurum] Error: There is no respawn node nearby!", NamedTextColor.RED));
        }
        return spawnLocation;
    }

    private String encodeItem(ItemStack item) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream objectOutputStream = new BukkitObjectOutputStream((OutputStream)byteArrayOutputStream);
            objectOutputStream.writeObject((Object)item);
            objectOutputStream.flush();
            byte[] serialized = byteArrayOutputStream.toByteArray();
            String encoded = Base64.getEncoder().encodeToString(serialized);
            objectOutputStream.close();
            return encoded;
        }
        catch (IOException e) {
            Bukkit.getLogger().log(Level.SEVERE, e.getMessage());
            return "Error";
        }
    }

    private Player getPlayerFromName(String name, Player player) {
        if (name.equals("@s")) {
            return player;
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.getName().equals(name)) continue;
            return p;
        }
        return null;
    }
}
