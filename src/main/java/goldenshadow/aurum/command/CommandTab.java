/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandSender
 *  org.bukkit.command.TabCompleter
 *  org.jetbrains.annotations.NotNull
 */
package goldenshadow.aurum.command;

import goldenshadow.aurum.command.CommandHelper;
import goldenshadow.aurum.entities.DataManager;
import goldenshadow.aurum.items.ItemDataManager;
import goldenshadow.aurum.items.Rarity;
import goldenshadow.aurum.items.flags.AttributeID;
import goldenshadow.aurum.items.flags.Rune;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

public class CommandTab
implements TabCompleter {
    private List<String> arguments = new ArrayList<String>();
    private final CommandHelper commandHelper = new CommandHelper();

    public List<String> onTabComplete(CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender.isOp()) {
            ArrayList<String> result = new ArrayList<String>();
            if (args.length == 1) {
                this.arguments = new ArrayList<String>(Arrays.asList("edit_item", "give", "debug", "place", "remove", "toggle", "mob", "xp", "item"));
                for (String a : this.arguments) {
                    if (!a.toLowerCase().startsWith(args[0].toLowerCase())) continue;
                    result.add(a);
                }
                return result;
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("edit_item")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("add_rune_slot", "set_level", "set_base_damage", "add_attribute", "set_name", "set_rarity", "add_rune", "set_customModelData", "set_base_health", "add_consumable_effect", "custom", "make_token", "set_consumable_uses", "make_miniboss_token"));
                } else if (args[0].equalsIgnoreCase("place")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("chest_t1", "chest_t2", "chest_t3", "pickup_interaction", "event_interaction", "spawn_node", "door", "respawn_node", "conditional_interaction", "boss_chest", "item_buyer", "rune_stone", "training_dummy"));
                } else if (args[0].equalsIgnoreCase("remove")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("chest", "pickup_interaction", "event_interaction", "spawn_node", "door", "respawn_node", "conditional_interaction", "boss_chest", "rune_stone", "training_dummy"));
                } else if (args[0].equalsIgnoreCase("give")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("weapon", "helmet", "chestplate", "leggings", "boots", "rune", "gold_coin", "silver_coin", "bronze_coin", "shard"));
                } else if (args[0].equalsIgnoreCase("debug")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("show_damage", "reset_cooldowns", "open_runesmith_gui", "open_chest", "reset_single_use_interaction", "highlight_spawn_nodes", "reload_files", "spawn_node_info", "highlight_respawn_nodes", "backup_files", "reset_boss_chests", "open_item_buyer_gui", "xp_multiplier", "open_rune_stone_gui", "spawning", "spells", "precise_mob_spawns", "interval_spawner_info", "log_spawn_diagnostics", "getAllEntities", "despawning", "make_door_miniboss_door"));
                } else if (args[0].equalsIgnoreCase("toggle")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("critical_hits", "thunder_damage", "bank", "ExperienceSystem", "RespawnSystem", "kill_command", "deny_block_interactions", "advertisement", "passive_regen", "pvp", "deny_openable_interactions"));
                } else if (args[0].equalsIgnoreCase("mob")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("new", "save", "spawn", "edit", "delete", "scrap", "info", "test_spawn", "edit_saved"));
                } else if (args[0].equalsIgnoreCase("xp")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("set_xp", "set_level", "add_xp", "remove_xp", "get_xp", "reset", "hard_reset"));
                } else if (args[0].equalsIgnoreCase("item")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("save", "give", "delete", "group", "give_nearest"));
                } else {
                    this.arguments.clear();
                }
                for (String a : this.arguments) {
                    if (!a.toLowerCase().startsWith(args[1].toLowerCase())) continue;
                    result.add(a);
                }
                return result;
            }
            if (args.length == 3) {
                if (args[0].equalsIgnoreCase("edit_item") && args[1].equalsIgnoreCase("add_attribute")) {
                    this.arguments = new ArrayList<String>(Arrays.stream(AttributeID.values()).map(Enum::toString).toList());
                } else if (args[0].equalsIgnoreCase("edit_item") && args[1].equalsIgnoreCase("add_consumable_effect")) {
                    this.arguments = new ArrayList<String>(List.of(this.commandHelper.consumableEffectNames));
                } else if (args[0].equalsIgnoreCase("edit_item") && args[1].equalsIgnoreCase("set_rarity")) {
                    this.arguments = new ArrayList<String>(Arrays.stream(Rarity.values()).map(Enum::toString).toList());
                } else if (args[0].equalsIgnoreCase("xp")) {
                    ArrayList<String> playerNames = new ArrayList<String>();
                    Bukkit.getOnlinePlayers().forEach(x -> playerNames.add(x.getName()));
                    this.arguments = playerNames;
                } else if (args[0].equalsIgnoreCase("edit_item") && args[1].equalsIgnoreCase("custom")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("set_name_with_colour", "remove_lore_line", "add_lore_line", "set_lore_line"));
                } else if (args[0].equalsIgnoreCase("place") && (args[1].equalsIgnoreCase("pickup_interaction") || args[1].equalsIgnoreCase("event_interaction") || args[1].equalsIgnoreCase("conditional_interaction"))) {
                    this.arguments = new ArrayList<String>(Arrays.asList("single_use", "timed_use", "unlimited_use"));
                } else if (args[0].equalsIgnoreCase("place") && args[1].equalsIgnoreCase("spawn_node")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("fast", "slow", "normal", "very_slow"));
                } else if (args[0].equalsIgnoreCase("place") && args[1].equalsIgnoreCase("door")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("normal", "custom", "snow", "single_block"));
                } else if (args[0].equalsIgnoreCase("give") && (args[1].equalsIgnoreCase("weapon") || args[1].equalsIgnoreCase("helmet") || args[1].equalsIgnoreCase("chestplate") || args[1].equalsIgnoreCase("leggings") || args[1].equalsIgnoreCase("boots"))) {
                    this.arguments = new ArrayList<String>(Arrays.stream(Rarity.values()).map(Enum::toString).toList());
                } else if (args[0].equalsIgnoreCase("give") && args[1].equalsIgnoreCase("rune")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("Blood_Rush", "Charge", "Fireball", "Smite", "Wind_Slash", "Heal", "Resurgence", "Shock_Wave", "Distortion", "Dragon_Skin", "Fish_Lung", "Falling_Star", "Swiftness", "Arcane_Shield", "Regeneration", "Grace", "Vitality", "Restoration", "Amogus", "Arcane_Ray", "Ground_Slam", "Pirouette", "Ritual", "Frozen_Spark"));
                } else if (args[0].equalsIgnoreCase("give") && args[1].equalsIgnoreCase("shard")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("Jungle", "Enchanted_Forest", "Snow", "Desert", "Lava"));
                } else if (args[0].equalsIgnoreCase("give") && args[1].equalsIgnoreCase("chest")) {
                    this.arguments = new ArrayList<String>(Arrays.stream(Rarity.values()).map(Enum::toString).toList());
                } else if (args[0].equalsIgnoreCase("edit_item") && args[1].equalsIgnoreCase("add_rune")) {
                    this.arguments = new ArrayList<String>(Arrays.stream(Rune.values()).map(Enum::toString).toList());
                } else if (args[0].equalsIgnoreCase("debug") && args[1].equalsIgnoreCase("open_chest")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("small", "medium", "large"));
                } else if (args[0].equalsIgnoreCase("mob") && (args[1].equalsIgnoreCase("spawn") || args[1].equalsIgnoreCase("delete") || args[1].equalsIgnoreCase("edit_saved"))) {
                    this.arguments = DataManager.getListOfMobKeys();
                } else if (args[0].equalsIgnoreCase("mob") && args[1].equalsIgnoreCase("edit")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("set_name", "set_mainhand", "set_level", "set_offhand", "set_xp_type", "set_helmet", "set_chestplate", "set_leggings", "set_boots", "set_damage", "set_speed", "set_knockback", "set_follow_range", "set_loot", "add_spell", "remove_spell", "isBaby", "sheep_wool_color", "horse_pattern", "horse_color", "frog_variant", "parrot_variant", "mushroom_cow_variant", "llama_color", "villager_biome", "villager_profession", "set_ai_type", "set_health", "set_knockback_resistance", "set_fox_type", "set_rabbit_type", "creeper_explosion_radius", "creeper_max_fuse_tick", "creeper_powered", "set_spell_damage_multiplier", "spell_cast_chance", "size", "loot_chance"));
                } else if (args[0].equalsIgnoreCase("mob") && args[1].equalsIgnoreCase("new")) {
                    this.arguments = new ArrayList<String>(List.of(this.commandHelper.livingEntities));
                } else if (args[0].equalsIgnoreCase("item") && (args[1].equalsIgnoreCase("give") || args[1].equalsIgnoreCase("delete") || args[1].equalsIgnoreCase("give_nearest"))) {
                    this.arguments = ItemDataManager.getItemKeys();
                } else if (args[0].equalsIgnoreCase("item") && args[1].equalsIgnoreCase("group")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("create", "delete", "add_item", "remove_item", "give", "give_nearest"));
                } else {
                    this.arguments.clear();
                }
                for (String a : this.arguments) {
                    if (!a.toLowerCase().startsWith(args[2].toLowerCase())) continue;
                    result.add(a);
                }
                return result;
            }
            if (args.length == 4) {
                if (args[0].equalsIgnoreCase("give") && args[1].equalsIgnoreCase("weapon") && args[2].equalsIgnoreCase("artifact")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("Wendigo", "Shipwreck", "Windweaver", "Valkyrie", "Refractal", "Obligation", "Brisingr", "Prism", "Sunflare", "Arondight", "Honour"));
                } else if (args[0].equalsIgnoreCase("give") && args[1].equalsIgnoreCase("weapon") && args[2].equalsIgnoreCase("eldritch")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("Claws_of_the_Beast", "Bleeding_Thorn", "Edge_of_the_Apocalypse", "Blade_of_Woe", "Soul_Binder", "Call_of_the_Abyss", "Spirit_Guider", "Navigator"));
                } else if (args[0].equalsIgnoreCase("give") && args[1].equalsIgnoreCase("helmet") && args[2].equalsIgnoreCase("artifact")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("Velsharoon", "Raincaller", "Guild_Crown", "Alignment", "Vagabond", "Full_Moon", "Valhalla", "Last_Stand"));
                } else if (args[0].equalsIgnoreCase("give") && args[1].equalsIgnoreCase("helmet") && args[2].equalsIgnoreCase("eldritch")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("Zealots_Shroud", "Cap_of_Fools", "Steeleaf", "Head_of_the_Beast", "Woven_Firmament"));
                } else if (args[0].equalsIgnoreCase("give") && args[1].equalsIgnoreCase("chestplate") && args[2].equalsIgnoreCase("eldritch")) {
                    this.arguments = new ArrayList<String>(Collections.singletonList("Ebony_Scales"));
                } else if (args[0].equalsIgnoreCase("place") && (args[1].equalsIgnoreCase("pickup_interaction") || args[1].equalsIgnoreCase("event_interaction") || args[1].equalsIgnoreCase("conditional_interaction")) && (args[2].equalsIgnoreCase("single_use") || args[2].equalsIgnoreCase("unlimited_use"))) {
                    this.arguments = new ArrayList<String>(Arrays.asList("small", "normal", "large"));
                } else if (args[0].equalsIgnoreCase("place") && args[1].equalsIgnoreCase("door") && args[2].equalsIgnoreCase("normal")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("NORTH", "SOUTH", "WEST", "EAST"));
                } else if (args[0].equalsIgnoreCase("place") && args[1].equalsIgnoreCase("spawn_node") && (args[2].equalsIgnoreCase("very_slow") || args[2].equalsIgnoreCase("slow") || args[2].equalsIgnoreCase("normal") || args[2].equalsIgnoreCase("fast"))) {
                    this.arguments = DataManager.getListOfMobKeys();
                } else if (args[0].equalsIgnoreCase("mob") && args[1].equalsIgnoreCase("edit") && (args[2].equalsIgnoreCase("add_spell") || args[2].equalsIgnoreCase("remove_spell"))) {
                    this.arguments = new ArrayList<String>(Arrays.asList("Charge", "Slash", "Watched", "Missile", "Repel", "Fangs", "Flash", "Blizzard", "Explosion", "Earthquake", "Venom_spit", "Faze", "Shockwave", "Heal", "Flame"));
                } else if (args[0].equalsIgnoreCase("mob") && args[1].equalsIgnoreCase("edit") && args[2].equalsIgnoreCase("set_loot_type")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("default", "custom", "none"));
                } else if (args[0].equalsIgnoreCase("mob") && args[1].equalsIgnoreCase("edit") && args[2].equalsIgnoreCase("set_xp_type")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("none", "low", "normal", "high", "boss"));
                } else if (args[0].equalsIgnoreCase("mob") && args[1].equalsIgnoreCase("edit") && (args[2].equalsIgnoreCase("isBaby") || args[2].equalsIgnoreCase("creeperPowered"))) {
                    this.arguments = new ArrayList<String>(Arrays.asList("true", "false"));
                } else if (args[0].equalsIgnoreCase("mob") && args[1].equalsIgnoreCase("edit") && args[2].equalsIgnoreCase("sheep_wool_color")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("BLACK", "BLUE", "BROWN", "CYAN", "GRAY", "GREEN", "LIGHT_BLUE", "LIGHT_GRAY", "LIME", "MAGENTA", "ORANGE", "PINK", "PURPLE", "RED", "WHITE", "YELLOW"));
                } else if (args[0].equalsIgnoreCase("mob") && args[1].equalsIgnoreCase("edit") && args[2].equalsIgnoreCase("horse_pattern")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("BLACK_DOTS", "NONE", "WHITE", "WHITE_DOTS", "WHITEFIELD"));
                } else if (args[0].equalsIgnoreCase("mob") && args[1].equalsIgnoreCase("edit") && args[2].equalsIgnoreCase("horse_color")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("BLACK", "BROWN", "CHESTNUT", "CREAMY", "DARK_BROWN", "GRAY", "WHITE"));
                } else if (args[0].equalsIgnoreCase("mob") && args[1].equalsIgnoreCase("edit") && args[2].equalsIgnoreCase("frog_variant")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("WARM", "COLD", "TEMPERATE"));
                } else if (args[0].equalsIgnoreCase("mob") && args[1].equalsIgnoreCase("edit") && args[2].equalsIgnoreCase("parrot_variant")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("BLUE", "CYAN", "GRAY", "GREEN", "RED"));
                } else if (args[0].equalsIgnoreCase("mob") && args[1].equalsIgnoreCase("edit") && args[2].equalsIgnoreCase("mushroom_cow_variant")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("BROWN", "RED"));
                } else if (args[0].equalsIgnoreCase("mob") && args[1].equalsIgnoreCase("edit") && args[2].equalsIgnoreCase("set_loot")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("default", "custom", "none"));
                } else if (args[0].equalsIgnoreCase("mob") && args[1].equalsIgnoreCase("edit") && args[2].equalsIgnoreCase("llama_color")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("BROWN", "CREAMY", "GRAY", "WHITE"));
                } else if (args[0].equalsIgnoreCase("mob") && args[1].equalsIgnoreCase("edit") && args[2].equalsIgnoreCase("villager_biome")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("DESERT", "JUNGLE", "PLAINS", "SAVANNA", "SNOW", "SWAMP", "TAIGA"));
                } else if (args[0].equalsIgnoreCase("mob") && args[1].equalsIgnoreCase("edit") && args[2].equalsIgnoreCase("villager_profession")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("ARMORER", "BUTCHER", "CARTOGRAPHER", "CLERIC", "FARMER", "FISHERMAN", "FLETCHER", "LEATHERWORKER", "LIBRARIAN", "MASON", "NITWIT", "SHEPHERD", "NONE", "SHEPHERD", "TOOLSMITH", "WEAPONSMITH"));
                } else if (args[0].equalsIgnoreCase("mob") && args[1].equalsIgnoreCase("edit") && args[2].equalsIgnoreCase("set_ai_type")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("HOSTILE", "VANILLA", "FRIENDLY", "HOSTILE_RANGED", "FRIENDLY_SCARED"));
                } else if (args[0].equalsIgnoreCase("mob") && args[1].equalsIgnoreCase("edit") && args[2].equalsIgnoreCase("set_fox_type")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("RED", "SNOW"));
                } else if (args[0].equalsIgnoreCase("mob") && args[1].equalsIgnoreCase("edit") && args[2].equalsIgnoreCase("set_rabbit_type")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("BLACK", "BLACK_AND_WHITE", "BROWN", "GOLD", "SALT_AND_PEPPER", "THE_KILLER_BUNNY", "WHITE"));
                } else if (args[0].equalsIgnoreCase("item") && args[1].equalsIgnoreCase("group") && (args[2].equalsIgnoreCase("delete") || args[2].equalsIgnoreCase("give") || args[2].equalsIgnoreCase("give_nearest") || args[2].equalsIgnoreCase("add_item") || args[2].equalsIgnoreCase("remove_item"))) {
                    this.arguments = ItemDataManager.getGroups();
                } else {
                    this.arguments.clear();
                }
                for (String a : this.arguments) {
                    if (!a.toLowerCase().startsWith(args[3].toLowerCase())) continue;
                    result.add(a);
                }
                return result;
            }
            if (args.length == 5) {
                if (args[0].equalsIgnoreCase("place") && (args[1].equalsIgnoreCase("pickup_interaction") || args[1].equalsIgnoreCase("event_interaction") || args[1].equalsIgnoreCase("conditional_interaction")) && args[2].equalsIgnoreCase("timed_use")) {
                    this.arguments = new ArrayList<String>(Arrays.asList("small", "normal", "large"));
                }
                if (args[0].equalsIgnoreCase("place") && args[1].equalsIgnoreCase("door") && args[2].equalsIgnoreCase("single_block")) {
                    this.arguments = Arrays.stream(Material.values()).map(Enum::toString).collect(Collectors.toList());
                } else if (args[0].equalsIgnoreCase("item") && args[1].equalsIgnoreCase("group") && args[2].equalsIgnoreCase("add_item")) {
                    this.arguments = ItemDataManager.getItemKeys();
                } else if (args[0].equalsIgnoreCase("item") && args[1].equalsIgnoreCase("group") && args[2].equalsIgnoreCase("remove_item")) {
                    this.arguments = ItemDataManager.getGroupContents(args[3]);
                } else {
                    this.arguments.clear();
                }
                for (String a : this.arguments) {
                    if (!a.toLowerCase().startsWith(args[4].toLowerCase())) continue;
                    result.add(a);
                }
                return result;
            }
            if (args.length > 5) {
                this.arguments.clear();
                return this.arguments;
            }
        }
        return null;
    }
}

