/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.Particle
 *  org.bukkit.Sound
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeInstance
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityDamageEvent
 *  org.bukkit.event.entity.EntityDamageEvent$DamageCause
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.inventory.EquipmentSlot
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package goldenshadow.aurum.items;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.items.ItemHelper;
import goldenshadow.aurum.items.RuneAbilityHandler;
import goldenshadow.aurum.items.flags.AttributeID;
import goldenshadow.aurum.items.spells.Combustion;
import goldenshadow.aurum.items.spells.PlayerSpellManager;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AttributeHandler {
    private static final ItemHelper itemHelper = new ItemHelper();

    private static boolean isCritical(Player player) {
        return player.getFallDistance() > 0.0f && !player.isOnGround() && !player.isInsideVehicle() && !player.hasPotionEffect(PotionEffectType.BLINDNESS) && player.getLocation().getBlock().getType() != Material.LADDER && player.getLocation().getBlock().getType() != Material.VINE;
    }

    public static void entityAttackEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getHand() == EquipmentSlot.HAND && (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)) {
            if (player.hasCooldown(player.getInventory().getItemInMainHand().getType())) {
                event.setCancelled(true);
            } else {
                AttributeHandler.applyCooldown(player);
            }
        }
    }

    public static void meleeHit(EntityDamageByEntityEvent event) {
        Entity entity;
        Player player = (Player)event.getDamager();
        if (player.hasCooldown(player.getInventory().getItemInMainHand().getType())) {
            event.setCancelled(true);
            return;
        }
        AttributeHandler.applyCooldown(player);
        ItemStack i = player.getInventory().getItemInMainHand();
        if (i.getItemMeta() != null && i.getItemMeta().getPersistentDataContainer().has(new NamespacedKey((Plugin)Aurum.getPlugin(), "minLevel"), PersistentDataType.INTEGER)) {
            event.setDamage(Objects.requireNonNull(player.getAttribute(Attribute.ATTACK_DAMAGE)).getValue() * (AttributeHandler.isCritical(player) ? 1.5 : 1.0));
        }
        if (!itemHelper.isHighEnoughLevel(player, player.getInventory().getItemInMainHand())) {
            event.setCancelled(true);
            return;
        }
        if (!Aurum.getPlugin().getConfig().getBoolean("Critical-hits") && AttributeHandler.isCritical(player)) {
            double split = event.getDamage() / 3.0;
            event.setDamage(split * 2.0);
        }
        if ((entity = event.getEntity()) instanceof LivingEntity) {
            Entity entity2;
            Location location;
            LivingEntity entity22 = (LivingEntity)entity;
            if (itemHelper.isAttributeEquipped(player, AttributeID.EXPLODING) && (double)ThreadLocalRandom.current().nextInt(0, 101) <= itemHelper.getAttributeRoll(player, AttributeID.EXPLODING, false)) {
                location = entity22.getLocation();
                entity22.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
                entity22.getWorld().spawnParticle(Particle.EXPLOSION, location, 3);
                for (Entity nearbyEntity : event.getEntity().getNearbyEntities(1.0, 1.0, 1.0)) {
                    if (!(nearbyEntity instanceof LivingEntity) || !nearbyEntity.getScoreboardTags().contains("aurum_mob")) continue;
                    entity22.damage(event.getDamage() * 0.6, (Entity)player);
                }
            }
            if (itemHelper.isAttributeEquipped(player, AttributeID.MAIN_ATTACK_DAMAGE)) {
                event.setDamage(Math.max(0.0, event.getDamage() * itemHelper.getAttributeRoll(player, AttributeID.MAIN_ATTACK_DAMAGE, true)));
            }
            if (itemHelper.isAttributeEquipped(player, AttributeID.FREEZING) && (double)ThreadLocalRandom.current().nextInt(0, 101) <= itemHelper.getAttributeRoll(player, AttributeID.FREEZING, false)) {
                event.getEntity().setFreezeTicks(200);
                location = event.getEntity().getLocation();
                entity22.getWorld().spawnParticle(Particle.SNOWFLAKE, location, 20, 0.2, 2.0, 0.2);
            }
            if (itemHelper.isAttributeEquipped(player, AttributeID.LIFE_STEAL) && RuneAbilityHandler.isBloodrushNotActive(player) && (double)ThreadLocalRandom.current().nextInt(0, 101) <= itemHelper.getAttributeRoll(player, AttributeID.LIFE_STEAL, false)) {
                double stolen = Math.ceil(0.2 * Math.sqrt(event.getFinalDamage()));
                player.setHealth(Math.min(player.getHealth() + stolen, Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue()));
            }
            if (itemHelper.isAttributeEquipped(player, AttributeID.CRITICAL_STRIKE) && (double)ThreadLocalRandom.current().nextInt(0, 101) <= itemHelper.getAttributeRoll(player, AttributeID.CRITICAL_STRIKE, false)) {
                event.setDamage(event.getDamage() * 1.5);
                entity22.getWorld().spawnParticle(Particle.WITCH, event.getEntity().getLocation(), 10, 0.0, 2.0, 0.0);
            }
            if (itemHelper.isAttributeEquipped(player, AttributeID.COMBUSTION) && (double)ThreadLocalRandom.current().nextInt(0, 101) <= itemHelper.getAttributeRoll(player, AttributeID.COMBUSTION, false)) {
                PlayerSpellManager.cast(new Combustion(player, (LivingEntity)event.getEntity(), event.getDamage()));
            }
            if ((entity2 = event.getEntity()) instanceof Player) {
                Entity nearbyEntity;
                Entity nearbyEntity2;
                Player attackedPlayer = (Player)entity2;
                if (itemHelper.isAttributeEquipped(attackedPlayer, AttributeID.REBOUND) && (double)ThreadLocalRandom.current().nextInt(0, 101) <= itemHelper.getAttributeRoll(attackedPlayer, AttributeID.REBOUND, false) && (nearbyEntity2 = event.getDamager()) instanceof LivingEntity) {
                    LivingEntity attacker = (LivingEntity)nearbyEntity2;
                    attacker.setVelocity(attacker.getLocation().getDirection().multiply(-1.1).setY(0.5));
                }
                if (itemHelper.isAttributeEquipped(attackedPlayer, AttributeID.REFLECTION) && (double)ThreadLocalRandom.current().nextInt(0, 101) <= itemHelper.getAttributeRoll(player, AttributeID.REFLECTION, false) && (nearbyEntity = event.getDamager()) instanceof LivingEntity) {
                    LivingEntity e = (LivingEntity)nearbyEntity;
                    if (itemHelper.isHighEnoughLevel(attackedPlayer, player.getInventory().getItemInMainHand())) {
                        AttributeInstance a = attackedPlayer.getAttribute(Attribute.ATTACK_DAMAGE);
                        assert (a != null);
                        e.damage(a.getValue(), (Entity)attackedPlayer);
                    }
                }
            }
        }
    }

    public static void playerDamageTaken(EntityDamageEvent event) {
        Player player = (Player)event.getEntity();
        if (itemHelper.isHighEnoughLevel(player, player.getInventory().getItemInMainHand())) {
            if (itemHelper.isAttributeEquipped(player, AttributeID.LIGHT_FOOT) && event.getCause().equals((Object)EntityDamageEvent.DamageCause.FALL) && itemHelper.getAttributeRoll(player, AttributeID.LIGHT_FOOT, false) > 0.0) {
                event.setDamage(event.getDamage() - event.getDamage() * (itemHelper.getAttributeRoll(player, AttributeID.LIGHT_FOOT, true) - 1.0));
            }
            if (itemHelper.isAttributeEquipped(player, AttributeID.RESISTANCE)) {
                double roll = itemHelper.getAttributeRoll(player, AttributeID.RESISTANCE, false);
                double multiplier = 1.0;
                if (roll >= 0.0) {
                    for (; roll > 0.0; roll -= 10.0) {
                        if (roll > 100.0) {
                            multiplier -= 0.01;
                            continue;
                        }
                        multiplier -= 0.05;
                    }
                    multiplier = Math.max(0.1, multiplier);
                } else {
                    while (roll < 0.0) {
                        multiplier = roll < -100.0 ? (multiplier = multiplier + 0.01) : (multiplier = multiplier + 0.05);
                        roll += 10.0;
                    }
                }
                event.setDamage(event.getDamage() * multiplier);
            }
            if (itemHelper.isAttributeEquipped(player, AttributeID.DODGE) && (double)ThreadLocalRandom.current().nextInt(0, 101) < itemHelper.getAttributeRoll(player, AttributeID.DODGE, true) && ThreadLocalRandom.current().nextInt(0, 101) < 31) {
                event.setCancelled(true);
            }
        }
    }

    public static void attributeLoop() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (!itemHelper.isHighEnoughLevel(player, player.getInventory().getItemInMainHand()) || !itemHelper.isAttributeEquipped(player, AttributeID.JUMP_HEIGHT) || !(itemHelper.getAttributeRoll(player, AttributeID.JUMP_HEIGHT, false) > 0.0)) continue;
            PotionEffect jump_height = new PotionEffect(PotionEffectType.JUMP_BOOST, 40, (int)(itemHelper.getAttributeRoll(player, AttributeID.JUMP_HEIGHT, false) - 1.0), false, false);
            player.addPotionEffect(jump_height);
        }
    }

    public static void itemSwitched(Player player) {
        player.removePotionEffect(PotionEffectType.MINING_FATIGUE);
        AttributeHandler.applyCooldown(player);
    }

    private static void applyCooldown(Player player) {
        int attackTier = (int)itemHelper.getAttributeRoll(player, AttributeID.ATTACK_SPEED, false);
        ItemStack i = player.getInventory().getItemInMainHand();
        if (i.getItemMeta() != null && i.getItemMeta().getPersistentDataContainer().has(new NamespacedKey((Plugin)Aurum.getPlugin(), "minLevel"), PersistentDataType.INTEGER)) {
            if (attackTier >= 2) {
                player.setCooldown(i.getType(), 5);
            }
            if (attackTier == 1) {
                player.setCooldown(i.getType(), 10);
                player.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 40, 3, false, false));
            }
            if (attackTier == 0) {
                player.setCooldown(i.getType(), 15);
                player.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 40, 5, false, false));
            }
            if (attackTier == -1) {
                player.setCooldown(i.getType(), 25);
                player.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 40, 7, false, false));
            }
            if (attackTier <= -2) {
                player.setCooldown(i.getType(), 40);
                player.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 40, 9, false, false));
            }
        }
    }
}

