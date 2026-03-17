/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.TabCompleter
 *  org.bukkit.event.Listener
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.java.JavaPlugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package goldenshadow.aurum;

import goldenshadow.aurum.command.CommandTab;
import goldenshadow.aurum.command.Commands;
import goldenshadow.aurum.entities.CustomEntity;
import goldenshadow.aurum.entities.DataManager;
import goldenshadow.aurum.entities.EntityHandler;
import goldenshadow.aurum.entities.IntervalSpawner;
import goldenshadow.aurum.entities.RangedAIManager;
import goldenshadow.aurum.entities.SpellManager;
import goldenshadow.aurum.events.EntityDamage;
import goldenshadow.aurum.events.EntityDeath;
import goldenshadow.aurum.events.EntityRegainHealth;
import goldenshadow.aurum.events.InventoryClick;
import goldenshadow.aurum.events.InventoryClose;
import goldenshadow.aurum.events.MiscEvents;
import goldenshadow.aurum.events.PlayerDeath;
import goldenshadow.aurum.events.PlayerFish;
import goldenshadow.aurum.events.PlayerInteract;
import goldenshadow.aurum.events.PlayerInteractEntity;
import goldenshadow.aurum.events.PlayerJoin;
import goldenshadow.aurum.events.PlayerQuit;
import goldenshadow.aurum.items.AttributeHandler;
import goldenshadow.aurum.items.BankManager;
import goldenshadow.aurum.items.ConditionalInteractionHandler;
import goldenshadow.aurum.items.EventInteractionHandler;
import goldenshadow.aurum.items.ItemDataManager;
import goldenshadow.aurum.items.ItemLevelHandler;
import goldenshadow.aurum.items.PickupInteractionHandler;
import goldenshadow.aurum.items.RuneAbilityHandler;
import goldenshadow.aurum.items.Treasure;
import goldenshadow.aurum.items.spells.PlayerSpellManager;
import goldenshadow.aurum.other.DoorHandler;
import goldenshadow.aurum.other.RespawnManager;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class Aurum
extends JavaPlugin {
    private static Aurum plugin;

    public void onEnable() {
        plugin = this;
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        Bukkit.getLogger().info("[Aurum] Starting up...");
        try {
            DataManager.loadFromFile();
            BankManager.readFromFile();
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)plugin, ItemDataManager::loadFromFile, 1L);
        }
        catch (Exception e) {
            plugin.getLogger().log(Level.WARNING, "Could not load saved json files!");
        }
        Objects.requireNonNull(this.getCommand("aurum")).setExecutor((CommandExecutor)new Commands());
        Objects.requireNonNull(this.getCommand("aurum")).setTabCompleter((TabCompleter)new CommandTab());
        Objects.requireNonNull(this.getCommand("suicide")).setExecutor((CommandExecutor)new Commands());
        Objects.requireNonNull(this.getCommand("open_door")).setExecutor((CommandExecutor)new Commands());
        this.registerEvents();
        new BukkitRunnable(){

            public void run() {
                SpellManager.castLoop();
                PlayerSpellManager.castLoop();
                DoorHandler.doorLoop();
            }
        }.runTaskTimer((Plugin)this, 0L, 1L);
        new BukkitRunnable(){

            public void run() {
                DataManager.spawnTick();
                RespawnManager.respawnNodeLoop();
            }
        }.runTaskTimer((Plugin)this, 0L, 10L);
        new BukkitRunnable(){

            public void run() {
                ItemLevelHandler.armorLoop();
                AttributeHandler.attributeLoop();
                RuneAbilityHandler.runeLoop();
                DataManager.highlightLoop();
                CustomEntity.entityDespawner();
                EntityHandler.barLoop();
            }
        }.runTaskTimer((Plugin)this, 0L, 20L);
        new BukkitRunnable(){

            public void run() {
                PickupInteractionHandler.pickUpInteractionLoop();
                EventInteractionHandler.eventInteractionLoop();
                ConditionalInteractionHandler.eventInteractionLoop();
                Treasure.treasureGlint();
                RangedAIManager.loop();
            }
        }.runTaskTimer((Plugin)this, 0L, 60L);
        new BukkitRunnable(){

            public void run() {
                SpellManager.spellLoop();
                IntervalSpawner.listLoadedWorlds();
            }
        }.runTaskTimer((Plugin)this, 0L, 100L);
        new BukkitRunnable(){

            public void run() {
                EntityHandler.playerPassiveRegen();
            }
        }.runTaskTimer((Plugin)this, 0L, 300L);
        new BukkitRunnable(){

            public void run() {
                if (Aurum.this.getConfig().getBoolean("advertisement")) {
                    if (ThreadLocalRandom.current().nextBoolean()) {
                        Bukkit.broadcast(PlayerJoin.getAdvertisementMessage());
                    } else {
                        Bukkit.broadcast(PlayerJoin.getInfoMessage());
                    }
                }
            }
        }.runTaskTimer((Plugin)this, 0L, 36000L);
    }

    public void onDisable() {
        try {
            DataManager.saveToFiles();
            BankManager.saveToFiles();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Aurum getPlugin() {
        return plugin;
    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents((Listener)new EntityDamage(), (Plugin)plugin);
        this.getServer().getPluginManager().registerEvents((Listener)new EntityDeath(), (Plugin)plugin);
        this.getServer().getPluginManager().registerEvents((Listener)new EntityRegainHealth(), (Plugin)plugin);
        this.getServer().getPluginManager().registerEvents((Listener)new InventoryClick(), (Plugin)plugin);
        this.getServer().getPluginManager().registerEvents((Listener)new InventoryClose(), (Plugin)plugin);
        this.getServer().getPluginManager().registerEvents((Listener)new MiscEvents(), (Plugin)plugin);
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerDeath(), (Plugin)plugin);
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerFish(), (Plugin)plugin);
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerInteract(), (Plugin)plugin);
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerInteractEntity(), (Plugin)plugin);
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerJoin(), (Plugin)plugin);
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerQuit(), (Plugin)plugin);
    }
}

