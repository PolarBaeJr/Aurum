/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  net.md_5.bungee.api.ChatMessageType
 *  net.md_5.bungee.api.chat.BaseComponent
 *  net.md_5.bungee.api.chat.TextComponent
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.Sound
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Fireball
 *  org.bukkit.entity.LightningStrike
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityDamageEvent
 *  org.bukkit.event.entity.EntityDamageEvent$DamageCause
 *  org.bukkit.event.entity.EntityRegainHealthEvent
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.projectiles.ProjectileSource
 *  org.bukkit.util.Vector
 */
package goldenshadow.aurum.items;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.items.ItemHelper;
import goldenshadow.aurum.items.flags.Rune;
import goldenshadow.aurum.items.spells.ArcaneRay;
import goldenshadow.aurum.items.spells.FrozenSpark;
import goldenshadow.aurum.items.spells.GroundSlam;
import goldenshadow.aurum.items.spells.Meteor;
import goldenshadow.aurum.items.spells.Pirouette;
import goldenshadow.aurum.items.spells.PlayerSpellManager;
import goldenshadow.aurum.items.spells.Ritual;
import goldenshadow.aurum.items.spells.Taunt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public class RuneAbilityHandler {
    private static final Map<UUID, Long> chargeCooldown = new HashMap<UUID, Long>();
    private static final Map<UUID, Long> bloodRushCooldown = new HashMap<UUID, Long>();
    private static final Map<UUID, Double> bloodRushAccumulator = new HashMap<UUID, Double>();
    private static final Map<UUID, Boolean> bloodRushActive = new HashMap<UUID, Boolean>();
    private static final Map<UUID, Long> fireballCooldown = new HashMap<UUID, Long>();
    private static final Map<UUID, Long> smiteCooldown = new HashMap<UUID, Long>();
    private static final Map<UUID, Boolean> smiteActive = new HashMap<UUID, Boolean>();
    private static final Map<UUID, Long> windSlashCooldown = new HashMap<UUID, Long>();
    private static final Map<UUID, Integer> healAccumulator = new HashMap<UUID, Integer>();
    private static final Map<UUID, Long> healCooldown = new HashMap<UUID, Long>();
    private static final Map<UUID, Long> resurgenceCooldown = new HashMap<UUID, Long>();
    private static final Map<UUID, Long> displacementCooldown = new HashMap<UUID, Long>();
    private static final Map<UUID, Boolean> displacementActive = new HashMap<UUID, Boolean>();
    private static final Map<UUID, Long> timeLockCooldown = new HashMap<UUID, Long>();
    private static final Map<UUID, Long> timeLockLocked = new HashMap<UUID, Long>();
    private static final Map<UUID, Long> shockWaveCooldown = new HashMap<UUID, Long>();
    private static final Map<UUID, Long> distortionCooldown = new HashMap<UUID, Long>();
    private static final Map<UUID, Boolean> leechFootActive = new HashMap<UUID, Boolean>();
    private static final Map<UUID, Long> leechFootCooldown = new HashMap<UUID, Long>();
    private static final Map<UUID, Long> fallingStarCooldown = new HashMap<UUID, Long>();
    private static final Map<UUID, Long> arcaneRayCooldown = new HashMap<UUID, Long>();
    private static final Map<UUID, Long> groundSlamCooldown = new HashMap<UUID, Long>();
    private static final Map<UUID, Long> pirouetteCooldown = new HashMap<UUID, Long>();
    private static final Map<UUID, Long> ritualCooldown = new HashMap<UUID, Long>();
    private static final Map<UUID, Long> frozenSparkCooldown = new HashMap<UUID, Long>();
    private static final Map<UUID, Long> tauntCooldown = new HashMap<UUID, Long>();
    private static final ItemHelper itemHelper = new ItemHelper();

    public static void resetAllCooldowns(UUID uuid) {
        leechFootActive.put(uuid, false);
        bloodRushActive.put(uuid, false);
        displacementActive.put(uuid, false);
        smiteActive.put(uuid, false);
        smiteCooldown.remove(uuid);
        bloodRushAccumulator.remove(uuid);
        chargeCooldown.remove(uuid);
        fallingStarCooldown.remove(uuid);
        leechFootCooldown.remove(uuid);
        distortionCooldown.remove(uuid);
        shockWaveCooldown.remove(uuid);
        timeLockCooldown.remove(uuid);
        displacementCooldown.remove(uuid);
        resurgenceCooldown.remove(uuid);
        windSlashCooldown.remove(uuid);
        fireballCooldown.remove(uuid);
        bloodRushCooldown.remove(uuid);
        distortionCooldown.remove(uuid);
        arcaneRayCooldown.remove(uuid);
        groundSlamCooldown.remove(uuid);
        pirouetteCooldown.remove(uuid);
        ritualCooldown.remove(uuid);
        frozenSparkCooldown.remove(uuid);
        tauntCooldown.remove(uuid);
    }

    public static void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInMainHand().getType() == Material.CLAY_BALL) {
            return;
        }
        if (itemHelper.isHighEnoughLevel(player, player.getInventory().getItemInMainHand())) {
            Vector multiplied;
            Location difference;
            if (itemHelper.isRuneEquipped(player, Rune.CHARGE)) {
                if (chargeCooldown.containsKey(player.getUniqueId()) && chargeCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                    player.getWorld().playSound((Entity)player, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.0f);
                    int timeLeft = (int)((chargeCooldown.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000L);
                    RuneAbilityHandler.sendActionBarMessage(player, "Charge is still on cooldown for " + (timeLeft + 1) + " seconds");
                    return;
                }
                chargeCooldown.put(player.getUniqueId(), (long)((double)System.currentTimeMillis() + 2000.0 * itemHelper.getCooldownReduction(player)));
                player.setVelocity(player.getLocation().getDirection().multiply(1.5).setY(0.6));
                player.getWorld().playSound((Entity)player, Sound.ITEM_TRIDENT_RIPTIDE_3, 1.0f, 1.0f);
            }
            if (itemHelper.isRuneEquipped(player, Rune.BLOOD_RUSH)) {
                if (bloodRushCooldown.containsKey(player.getUniqueId())) {
                    if (bloodRushActive.get(player.getUniqueId()).booleanValue()) {
                        RuneAbilityHandler.sendActionBarMessage(player, "You have exited out of blood rush");
                        bloodRushCooldown.put(player.getUniqueId(), (long)((double)System.currentTimeMillis() + 30000.0 * itemHelper.getCooldownReduction(player)));
                        bloodRushAccumulator.remove(player.getUniqueId());
                        player.getWorld().playSound((Entity)player, Sound.ENTITY_RAVAGER_STUNNED, 1.0f, 1.0f);
                        player.setWorldBorder(null);
                        bloodRushActive.put(player.getUniqueId(), false);
                        bloodRushAccumulator.remove(player.getUniqueId());
                        return;
                    }
                    if (bloodRushCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                        player.getWorld().playSound((Entity)player, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.0f);
                        int timeLeft = (int)((bloodRushCooldown.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000L);
                        RuneAbilityHandler.sendActionBarMessage(player, "Blood rush is still on cooldown for " + (timeLeft + 1) + " seconds");
                        return;
                    }
                }
                bloodRushCooldown.put(player.getUniqueId(), (long)((double)System.currentTimeMillis() + 40000.0 * itemHelper.getCooldownReduction(player)));
                player.removePotionEffect(PotionEffectType.REGENERATION);
                player.getWorld().playSound((Entity)player, Sound.ENTITY_RAVAGER_ROAR, 1.0f, 1.0f);
                RuneAbilityHandler.sendActionBarMessage(player, "You have entered blood rush");
                player.setWorldBorder(Aurum.getPlugin().getServer().createWorldBorder());
                bloodRushActive.put(player.getUniqueId(), true);
                bloodRushAccumulator.put(player.getUniqueId(), 0.0);
                if (player.getWorldBorder() != null) {
                    player.getWorldBorder().setWarningDistance(Integer.MAX_VALUE);
                }
            }
            if (itemHelper.isRuneEquipped(player, Rune.FIREBALL)) {
                if (fireballCooldown.containsKey(player.getUniqueId()) && fireballCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                    player.getWorld().playSound((Entity)player, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.0f);
                    int timeLeft = (int)((fireballCooldown.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000L);
                    RuneAbilityHandler.sendActionBarMessage(player, "Fireball is still on cooldown for " + (timeLeft + 1) + " seconds");
                    return;
                }
                fireballCooldown.put(player.getUniqueId(), (long)((double)System.currentTimeMillis() + 10000.0 * itemHelper.getCooldownReduction(player)));
                Location loc = player.getEyeLocation().toVector().add(player.getLocation().getDirection().multiply(2)).toLocation(player.getWorld(), player.getLocation().getYaw(), player.getLocation().getPitch());
                Fireball fireball = (Fireball)player.getWorld().spawn(loc, Fireball.class);
                fireball.setShooter((ProjectileSource)player);
                fireball.addScoreboardTag("aurum_rune_fireball");
            }
            if (itemHelper.isRuneEquipped(player, Rune.SMITE)) {
                if (smiteCooldown.containsKey(player.getUniqueId())) {
                    if (smiteActive.get(player.getUniqueId()).booleanValue()) {
                        RuneAbilityHandler.sendActionBarMessage(player, "You have deactivated smite");
                        smiteCooldown.put(player.getUniqueId(), (long)((double)System.currentTimeMillis() + 30000.0 * itemHelper.getCooldownReduction(player)));
                        smiteActive.put(player.getUniqueId(), false);
                        return;
                    }
                    if (smiteCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                        player.getWorld().playSound((Entity)player, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.0f);
                        int timeLeft = (int)((smiteCooldown.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000L);
                        RuneAbilityHandler.sendActionBarMessage(player, "Smite is still on cooldown for " + (timeLeft + 1) + " seconds");
                        return;
                    }
                }
                smiteCooldown.put(player.getUniqueId(), (long)((double)System.currentTimeMillis() + 30000.0 * itemHelper.getCooldownReduction(player) + 5000.0));
                player.getWorld().playSound((Entity)player, Sound.ENTITY_ILLUSIONER_PREPARE_BLINDNESS, 1.0f, 1.0f);
                RuneAbilityHandler.sendActionBarMessage(player, "Smite is now active");
                smiteActive.put(player.getUniqueId(), true);
            }
            if (itemHelper.isRuneEquipped(player, Rune.WIND_SLASH)) {
                if (windSlashCooldown.containsKey(player.getUniqueId()) && windSlashCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                    player.getWorld().playSound((Entity)player, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.0f);
                    int timeLeft = (int)((windSlashCooldown.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000L);
                    RuneAbilityHandler.sendActionBarMessage(player, "Wind Slash is still on cooldown for " + (timeLeft + 1) + " seconds");
                    return;
                }
                windSlashCooldown.put(player.getUniqueId(), (long)((double)System.currentTimeMillis() + 5000.0 * itemHelper.getCooldownReduction(player)));
                ArrayList<Location> locations = new ArrayList<Location>();
                for (int i = 0; i < 21; ++i) {
                    locations.add(player.getLocation().add(ThreadLocalRandom.current().nextDouble(-2.0, 3.0), ThreadLocalRandom.current().nextDouble(0.0, 3.0), ThreadLocalRandom.current().nextDouble(-2.0, 3.0)));
                }
                for (Entity entity : player.getNearbyEntities(3.0, 3.0, 3.0)) {
                    if (!entity.getScoreboardTags().contains("aurum_mob") && !itemHelper.isAttackablePlayer(entity) || !(entity instanceof LivingEntity)) continue;
                    PlayerSpellManager.applyDamage((LivingEntity)entity, Math.max(0.0, Objects.requireNonNull(player.getAttribute(Attribute.ATTACK_DAMAGE)).getValue() * 1.4 * itemHelper.getSpellDamageMultiplier(player)), player);
                }
                for (Location location : locations) {
                    assert (location.getWorld() != null);
                    double x = ThreadLocalRandom.current().nextBoolean() ? 0.1 : -0.1;
                    double y = ThreadLocalRandom.current().nextBoolean() ? 0.1 : -0.1;
                    double z = ThreadLocalRandom.current().nextBoolean() ? 0.1 : -0.1;
                    for (int i = 0; i < 20; ++i) {
                        location.getWorld().spawnParticle(Particle.END_ROD, location, 0);
                        location.add(x, y, z);
                        if (i != 1 && i != 10 && i != 19) continue;
                        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_SHEEP_SHEAR, 1.0f, 1.0f);
                    }
                }
            }
            if (itemHelper.isRuneEquipped(player, Rune.HEAL)) {
                if (healCooldown.containsKey(player.getUniqueId()) && healCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                    return;
                }
                healCooldown.put(player.getUniqueId(), System.currentTimeMillis() + 150L);
                if (healAccumulator.containsKey(player.getUniqueId())) {
                    healAccumulator.put(player.getUniqueId(), healAccumulator.get(player.getUniqueId()) + 1);
                } else {
                    healAccumulator.put(player.getUniqueId(), 1);
                }
                if (healAccumulator.get(player.getUniqueId()) > 3) {
                    healAccumulator.put(player.getUniqueId(), 0);
                    int addition = 1;
                    if (itemHelper.isRuneEquipped(player, Rune.RESTORATION) && player.getHealth() < Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue() / 2.0) {
                        addition = 2;
                    }
                    if (player.getHealth() + (double)addition <= Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue()) {
                        player.setHealth(player.getHealth() + (double)addition);
                        player.getWorld().playSound((Entity)player, Sound.ENTITY_WITCH_DRINK, 1.0f, 1.0f);
                    }
                }
            }
            if (itemHelper.isRuneEquipped(player, Rune.RIFT_STEP)) {
                player.setVelocity(player.getLocation().getDirection().multiply(2).setY(1));
                player.damage(2.0);
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ILLUSIONER_MIRROR_MOVE, 1.0f, 1.0f);
            }
            if (itemHelper.isRuneEquipped(player, Rune.DISPLACEMENT)) {
                if (displacementCooldown.containsKey(player.getUniqueId())) {
                    if (displacementActive.get(player.getUniqueId()).booleanValue()) {
                        RuneAbilityHandler.sendActionBarMessage(player, "You have deactivated displacement");
                        displacementCooldown.put(player.getUniqueId(), (long)((double)System.currentTimeMillis() + 10000.0 * itemHelper.getCooldownReduction(player)));
                        displacementActive.put(player.getUniqueId(), false);
                        return;
                    }
                    if (displacementCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                        player.getWorld().playSound((Entity)player, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.0f);
                        int timeLeft = (int)((displacementCooldown.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000L);
                        RuneAbilityHandler.sendActionBarMessage(player, "Displacement is still on cooldown for " + (timeLeft + 1) + " seconds");
                        return;
                    }
                }
                displacementCooldown.put(player.getUniqueId(), (long)((double)System.currentTimeMillis() + 10000.0 * itemHelper.getCooldownReduction(player) + 10000.0));
                player.getWorld().playSound((Entity)player, Sound.ENTITY_EVOKER_PREPARE_ATTACK, 1.0f, 1.0f);
                RuneAbilityHandler.sendActionBarMessage(player, "Displacement is now active");
                displacementActive.put(player.getUniqueId(), true);
            }
            if (itemHelper.isRuneEquipped(player, Rune.TIME_LOCK)) {
                if (timeLockCooldown.containsKey(player.getUniqueId()) && timeLockCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                    player.getWorld().playSound((Entity)player, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.0f);
                    int timeLeft = (int)((timeLockCooldown.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000L);
                    RuneAbilityHandler.sendActionBarMessage(player, "Time Lock is still on cooldown for " + (timeLeft + 1) + " seconds");
                    return;
                }
                player.getWorld().playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 1.0f);
                timeLockCooldown.put(player.getUniqueId(), (long)((double)System.currentTimeMillis() + 20000.0 * itemHelper.getCooldownReduction(player)));
                for (Entity entity : player.getNearbyEntities(5.0, 5.0, 5.0)) {
                    if (!entity.getScoreboardTags().contains("aurum_mob") || !(entity instanceof LivingEntity)) continue;
                    ((LivingEntity)entity).setAI(false);
                    timeLockLocked.put(entity.getUniqueId(), System.currentTimeMillis() + 5000L);
                }
            }
            if (itemHelper.isRuneEquipped(player, Rune.SHOCK_WAVE)) {
                if (shockWaveCooldown.containsKey(player.getUniqueId()) && shockWaveCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                    player.getWorld().playSound((Entity)player, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.0f);
                    int timeLeft = (int)((shockWaveCooldown.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000L);
                    RuneAbilityHandler.sendActionBarMessage(player, "Shock Wave is still on cooldown for " + (timeLeft + 1) + " seconds");
                    return;
                }
                player.getWorld().playSound((Entity)player, Sound.ENTITY_WARDEN_SONIC_BOOM, 1.0f, 1.0f);
                player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 20, 1.0, 0.0, 1.0);
                shockWaveCooldown.put(player.getUniqueId(), (long)((double)System.currentTimeMillis() + 15000.0 * itemHelper.getCooldownReduction(player)));
                for (Entity entity : player.getNearbyEntities(5.0, 5.0, 5.0)) {
                    if (!entity.getScoreboardTags().contains("aurum_mob") && !itemHelper.isAttackablePlayer(entity)) continue;
                    difference = entity.getLocation().subtract(player.getLocation());
                    Vector normalizedDifference = difference.toVector().normalize();
                    multiplied = normalizedDifference.multiply(1.6).setY(0.8);
                    if (entity.getScoreboardTags().contains("aurum_training_dummy")) continue;
                    entity.setVelocity(multiplied);
                }
            }
            if (itemHelper.isRuneEquipped(player, Rune.DISTORTION)) {
                if (distortionCooldown.containsKey(player.getUniqueId()) && distortionCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                    player.getWorld().playSound((Entity)player, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.0f);
                    int timeLeft = (int)((distortionCooldown.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000L);
                    RuneAbilityHandler.sendActionBarMessage(player, "Distortion is still on cooldown for " + (timeLeft + 1) + " seconds");
                    return;
                }
                player.getWorld().playSound((Entity)player, Sound.ENTITY_WARDEN_SONIC_CHARGE, 1.0f, 1.0f);
                player.getWorld().spawnParticle(Particle.LARGE_SMOKE, player.getLocation(), 20, 1.0, 0.0, 1.0);
                distortionCooldown.put(player.getUniqueId(), (long)((double)System.currentTimeMillis() + 15000.0 * itemHelper.getCooldownReduction(player)));
                for (Entity entity : player.getNearbyEntities(10.0, 10.0, 10.0)) {
                    if (!entity.getScoreboardTags().contains("aurum_mob") && !itemHelper.isAttackablePlayer(entity)) continue;
                    difference = player.getLocation().subtract(entity.getLocation());
                    Vector normalizedDifference = difference.toVector().normalize();
                    multiplied = normalizedDifference.multiply(1.6).setY(0.8);
                    if (entity.getScoreboardTags().contains("aurum_training_dummy")) continue;
                    entity.setVelocity(multiplied);
                }
            }
            if (itemHelper.isRuneEquipped(player, Rune.LEECH_FOOT)) {
                if (leechFootCooldown.containsKey(player.getUniqueId()) && leechFootCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                    return;
                }
                leechFootCooldown.put(player.getUniqueId(), System.currentTimeMillis() + 500L);
                if (leechFootActive.containsKey(player.getUniqueId()) && leechFootActive.get(player.getUniqueId()).booleanValue()) {
                    RuneAbilityHandler.sendActionBarMessage(player, "You have disabled Leach Foot");
                    leechFootActive.put(player.getUniqueId(), false);
                    player.removePotionEffect(PotionEffectType.SPEED);
                    return;
                }
                if (leechFootActive.containsKey(player.getUniqueId()) && !leechFootActive.get(player.getUniqueId()).booleanValue()) {
                    RuneAbilityHandler.sendActionBarMessage(player, "You have enabled Leach Foot");
                    leechFootActive.put(player.getUniqueId(), true);
                    player.getWorld().playSound((Entity)player, Sound.ENTITY_ILLUSIONER_PREPARE_BLINDNESS, 1.0f, 1.0f);
                    PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 40, 99, false, false);
                    player.addPotionEffect(speed);
                    player.damage(4.0);
                    return;
                }
                RuneAbilityHandler.sendActionBarMessage(player, "You have enabled Leach Foot");
                leechFootActive.put(player.getUniqueId(), true);
                player.getWorld().playSound((Entity)player, Sound.ENTITY_ILLUSIONER_PREPARE_BLINDNESS, 1.0f, 1.0f);
                PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 40, 99, false, false);
                player.addPotionEffect(speed);
            }
            if (itemHelper.isRuneEquipped(player, Rune.FALLING_STAR)) {
                if (fallingStarCooldown.containsKey(player.getUniqueId()) && fallingStarCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                    player.getWorld().playSound((Entity)player, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.0f);
                    int timeLeft = (int)((fallingStarCooldown.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000L);
                    RuneAbilityHandler.sendActionBarMessage(player, "Falling Star is still on cooldown for " + (timeLeft + 1) + " seconds");
                    return;
                }
                fallingStarCooldown.put(player.getUniqueId(), (long)((double)System.currentTimeMillis() + 20000.0 * itemHelper.getCooldownReduction(player)));
                PlayerSpellManager.cast(new Meteor(player));
            }
            if (itemHelper.isRuneEquipped(player, Rune.ARCANE_RAY)) {
                if (arcaneRayCooldown.containsKey(player.getUniqueId()) && arcaneRayCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                    player.getWorld().playSound((Entity)player, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.0f);
                    int timeLeft = (int)((arcaneRayCooldown.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000L);
                    RuneAbilityHandler.sendActionBarMessage(player, "Arcane Ray is still on cooldown for " + (timeLeft + 1) + " seconds");
                    return;
                }
                arcaneRayCooldown.put(player.getUniqueId(), (long)((double)System.currentTimeMillis() + 20000.0 * itemHelper.getCooldownReduction(player)));
                PlayerSpellManager.cast(new ArcaneRay(player));
            }
            if (itemHelper.isRuneEquipped(player, Rune.GROUND_SLAM)) {
                if (groundSlamCooldown.containsKey(player.getUniqueId()) && groundSlamCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                    player.getWorld().playSound((Entity)player, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.0f);
                    int timeLeft = (int)((groundSlamCooldown.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000L);
                    RuneAbilityHandler.sendActionBarMessage(player, "Ground Slam is still on cooldown for " + (timeLeft + 1) + " seconds");
                    return;
                }
                groundSlamCooldown.put(player.getUniqueId(), (long)((double)System.currentTimeMillis() + 8000.0 * itemHelper.getCooldownReduction(player)));
                PlayerSpellManager.cast(new GroundSlam(player));
            }
            if (itemHelper.isRuneEquipped(player, Rune.PIROUETTE)) {
                if (pirouetteCooldown.containsKey(player.getUniqueId()) && pirouetteCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                    player.getWorld().playSound((Entity)player, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.0f);
                    int timeLeft = (int)((pirouetteCooldown.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000L);
                    RuneAbilityHandler.sendActionBarMessage(player, "Pirouette is still on cooldown for " + (timeLeft + 1) + " seconds");
                    return;
                }
                pirouetteCooldown.put(player.getUniqueId(), (long)((double)System.currentTimeMillis() + 5000.0 * itemHelper.getCooldownReduction(player)));
                PlayerSpellManager.cast(new Pirouette(player));
            }
            if (itemHelper.isRuneEquipped(player, Rune.RITUAL)) {
                if (ritualCooldown.containsKey(player.getUniqueId()) && ritualCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                    player.getWorld().playSound((Entity)player, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.0f);
                    int timeLeft = (int)((ritualCooldown.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000L);
                    RuneAbilityHandler.sendActionBarMessage(player, "Ritual is still on cooldown for " + (timeLeft + 1) + " seconds");
                    return;
                }
                ritualCooldown.put(player.getUniqueId(), (long)((double)System.currentTimeMillis() + 60000.0 * itemHelper.getCooldownReduction(player)));
                PlayerSpellManager.cast(new Ritual(player));
            }
            if (itemHelper.isRuneEquipped(player, Rune.FROZEN_SPARK)) {
                if (frozenSparkCooldown.containsKey(player.getUniqueId()) && frozenSparkCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                    player.getWorld().playSound((Entity)player, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.0f);
                    int timeLeft = (int)((frozenSparkCooldown.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000L);
                    RuneAbilityHandler.sendActionBarMessage(player, "Frozen Spark is still on cooldown for " + (timeLeft + 1) + " seconds");
                    return;
                }
                frozenSparkCooldown.put(player.getUniqueId(), (long)((double)System.currentTimeMillis() + 10000.0 * itemHelper.getCooldownReduction(player)));
                PlayerSpellManager.cast(new FrozenSpark(player));
            }
            if (itemHelper.isRuneEquipped(player, Rune.TAUNT)) {
                if (tauntCooldown.containsKey(player.getUniqueId()) && tauntCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                    player.getWorld().playSound((Entity)player, Sound.ITEM_FLINTANDSTEEL_USE, 1.0f, 1.0f);
                    int timeLeft = (int)((tauntCooldown.get(player.getUniqueId()) - System.currentTimeMillis()) / 1000L);
                    RuneAbilityHandler.sendActionBarMessage(player, "Taunt is still on cooldown for " + (timeLeft + 1) + " seconds");
                    return;
                }
                tauntCooldown.put(player.getUniqueId(), (long)((double)System.currentTimeMillis() + 15000.0 * itemHelper.getCooldownReduction(player)));
                PlayerSpellManager.cast(new Taunt(player));
            }
        }
    }

    public static void runeLoop() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PotionEffect p;
            PotionEffect speed;
            if (smiteCooldown.containsKey(player.getUniqueId()) && smiteActive.get(player.getUniqueId()).booleanValue() && (double)smiteCooldown.get(player.getUniqueId()).longValue() - 30000.0 * itemHelper.getCooldownReduction(player) < (double)System.currentTimeMillis()) {
                RuneAbilityHandler.sendActionBarMessage(player, "Smite has run out");
                smiteActive.put(player.getUniqueId(), false);
            }
            if (resurgenceCooldown.containsKey(player.getUniqueId()) && resurgenceCooldown.get(player.getUniqueId()) < System.currentTimeMillis()) {
                RuneAbilityHandler.sendActionBarMessage(player, "Resurgence has refreshed");
                resurgenceCooldown.remove(player.getUniqueId());
            }
            if (displacementCooldown.containsKey(player.getUniqueId()) && displacementActive.containsKey(player.getUniqueId()) && displacementActive.get(player.getUniqueId()).booleanValue() && (double)displacementCooldown.get(player.getUniqueId()).longValue() - 10000.0 * itemHelper.getCooldownReduction(player) < (double)System.currentTimeMillis()) {
                RuneAbilityHandler.sendActionBarMessage(player, "Displacement has run out");
                displacementActive.put(player.getUniqueId(), false);
            }
            for (Entity entity : player.getNearbyEntities(40.0, 40.0, 40.0)) {
                if (!(entity instanceof LivingEntity) || !timeLockLocked.containsKey(entity.getUniqueId()) || timeLockLocked.get(entity.getUniqueId()) >= System.currentTimeMillis()) continue;
                ((LivingEntity)entity).setAI(true);
            }
            if (leechFootActive.containsKey(player.getUniqueId()) && leechFootActive.get(player.getUniqueId()).booleanValue()) {
                speed = new PotionEffect(PotionEffectType.SPEED, 40, 99, false, false);
                player.addPotionEffect(speed);
                player.damage(4.0);
            }
            if (itemHelper.isRuneEquipped(player, Rune.DRAGON_SKIN)) {
                PotionEffect dragon_skin = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 40, 0, false, false);
                player.addPotionEffect(dragon_skin);
            }
            if (itemHelper.isRuneEquipped(player, Rune.FISH_LUNG)) {
                PotionEffect fish_lung = new PotionEffect(PotionEffectType.WATER_BREATHING, 40, 0, false, false);
                PotionEffect dolphin = new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 40, 0, false, false);
                player.addPotionEffect(fish_lung);
                player.addPotionEffect(dolphin);
            }
            if (itemHelper.isRuneEquipped(player, Rune.SWIFTNESS)) {
                speed = new PotionEffect(PotionEffectType.SPEED, 40, 1, false, false);
                player.addPotionEffect(speed);
            }
            if (itemHelper.isRuneEquipped(player, Rune.ARCANE_SHIELD)) {
                p = new PotionEffect(PotionEffectType.RESISTANCE, 40, 0, false, false);
                player.addPotionEffect(p);
            }
            if (itemHelper.isRuneEquipped(player, Rune.GRACE)) {
                p = new PotionEffect(PotionEffectType.SLOW_FALLING, 40, 0, false, false);
                player.addPotionEffect(p);
            }
            if (itemHelper.isRuneEquipped(player, Rune.VITALITY)) {
                p = new PotionEffect(PotionEffectType.HEALTH_BOOST, 40, 1, false, false);
                player.addPotionEffect(p);
            }
            if (!itemHelper.isRuneEquipped(player, Rune.REGENERATION) || !RuneAbilityHandler.isBloodrushNotActive(player)) continue;
            if (itemHelper.isRuneEquipped(player, Rune.RESTORATION) && player.getHealth() < Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue() / 2.0) {
                player.setHealth(Math.min(Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue(), player.getHealth() + 2.0));
                continue;
            }
            player.setHealth(Math.min(Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue(), player.getHealth() + 1.0));
        }
    }

    public static void onRegenHealth(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Player && bloodRushActive.containsKey(event.getEntity().getUniqueId()) && bloodRushActive.get(event.getEntity().getUniqueId()).booleanValue()) {
            event.setCancelled(true);
        }
    }

    public static void onDamageEvent(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            EntityDamageByEntityEvent edeEvent;
            Player player = (Player)entity;
            if (event instanceof EntityDamageByEntityEvent && (edeEvent = (EntityDamageByEntityEvent)event).getDamager() instanceof Player && !itemHelper.isAttackablePlayer(edeEvent.getEntity())) {
                return;
            }
            if (bloodRushActive.containsKey(player.getUniqueId()) && bloodRushActive.get(player.getUniqueId()).booleanValue()) {
                double damageTakenSum = 0.0;
                if (bloodRushAccumulator.containsKey(player.getUniqueId())) {
                    damageTakenSum = bloodRushAccumulator.get(player.getUniqueId());
                }
                if (bloodRushAccumulator.get(player.getUniqueId()) < 100.0) {
                    double gainedPercent = event.getDamage() / (Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue() - 1.0);
                    bloodRushAccumulator.put(player.getUniqueId(), Math.min(100.0, gainedPercent * 100.0 + damageTakenSum));
                    RuneAbilityHandler.sendActionBarMessage(player, "Blood Rush: " + (int)bloodRushAccumulator.get(player.getUniqueId()).doubleValue() + "%");
                    player.getWorld().playSound((Entity)player, Sound.ENTITY_RAVAGER_STEP, 1.0f, 1.0f);
                }
            }
            if (event.getFinalDamage() >= player.getHealth() && itemHelper.isRuneEquipped(player, Rune.RESURGENCE)) {
                if (resurgenceCooldown.containsKey(player.getUniqueId()) && resurgenceCooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                    return;
                }
                resurgenceCooldown.put(player.getUniqueId(), System.currentTimeMillis() + 600000L);
                player.sendMessage(org.bukkit.ChatColor.DARK_RED + "[" + org.bukkit.ChatColor.RED + "!" + org.bukkit.ChatColor.DARK_RED + "] " + org.bukkit.ChatColor.GRAY + "Resurgence triggered! Will refresh in 10 minutes!");
                event.setCancelled(true);
                ItemStack playerItem = player.getInventory().getItemInMainHand();
                ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
                ItemMeta meta = totem.getItemMeta();
                assert (meta != null);
                meta.setCustomModelData(Integer.valueOf(1));
                totem.setItemMeta(meta);
                player.getInventory().setItemInMainHand(totem);
                player.damage(player.getHealth() + 10.0);
                player.getInventory().setItemInMainHand(playerItem);
            }
            if (itemHelper.isRuneEquipped(player, Rune.SACRIFICE)) {
                int playerCount = 0;
                for (Entity entity2 : player.getNearbyEntities(5.0, 5.0, 5.0)) {
                    if (!(entity2 instanceof Player)) continue;
                    ++playerCount;
                }
                for (Entity entity3 : player.getNearbyEntities(5.0, 5.0, 5.0)) {
                    if (!(entity3 instanceof Player) || !RuneAbilityHandler.isBloodrushNotActive((Player)entity3)) continue;
                    int multiplier = 1;
                    if (itemHelper.isRuneEquipped(player, Rune.RESTORATION) && player.getHealth() < Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue() / 2.0) {
                        multiplier = 2;
                    }
                    double d = ((Player)entity3).getHealth() + event.getDamage() / 2.0 * (double)multiplier / (double)playerCount;
                    ((Player)entity3).setHealth(Math.min(d, Objects.requireNonNull(((Player)entity3).getAttribute(Attribute.MAX_HEALTH)).getValue()));
                }
            }
            if (itemHelper.isRuneEquipped(player, Rune.GOLD_PACT)) {
                event.setDamage(event.getDamage() * 2.0);
            }
        }
    }

    public static void entityDamageEntity(EntityDamageByEntityEvent event) {
        Entity lightning;
        Player attacker;
        ProjectileSource projectileSource;
        Fireball fireball;
        Entity damagerEntity;
        Player player;
        Entity entity = event.getDamager();
        if (entity instanceof Player && bloodRushActive.containsKey((player = (Player)entity).getUniqueId()) && bloodRushActive.get(player.getUniqueId()).booleanValue() && bloodRushAccumulator.containsKey(player.getUniqueId())) {
            double bloodRushPercent = bloodRushAccumulator.get(player.getUniqueId());
            double m = 1.0 + bloodRushPercent / 100.0 * 3.0;
            double damage = m * event.getDamage();
            event.setDamage(damage);
        }
        if (event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE && (damagerEntity = event.getDamager()) instanceof Fireball && (fireball = (Fireball)damagerEntity).getScoreboardTags().contains("aurum_rune_fireball") && (projectileSource = fireball.getShooter()) instanceof Player) {
            Player shooter = (Player)projectileSource;
            event.setDamage(Objects.requireNonNull(shooter.getAttribute(Attribute.ATTACK_DAMAGE)).getValue() * 3.0);
            if (event.getEntity() instanceof Player && !itemHelper.isAttackablePlayer(event.getEntity())) {
                event.setCancelled(true);
            }
        }
        if (event.getDamager() instanceof LightningStrike && (event.getDamager().getScoreboardTags().contains("aurum_thunder") || !Aurum.getPlugin().getConfig().getBoolean("Thunder-damage"))) {
            event.setCancelled(true);
        }
        if (event.getDamager() instanceof Player && smiteActive.containsKey((attacker = (Player)event.getDamager()).getUniqueId()) && smiteActive.get(attacker.getUniqueId()).booleanValue() && (event.getEntity().getScoreboardTags().contains("aurum_mob") || itemHelper.isAttackablePlayer(event.getEntity()))) {
            lightning = (LightningStrike)event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.LIGHTNING_BOLT);
            lightning.addScoreboardTag("aurum_thunder");
            event.setDamage(Math.max(0.0, event.getDamage() * 1.5 * itemHelper.getSpellDamageMultiplier(attacker)));
        }
        if ((lightning = event.getDamager()) instanceof Player) {
            attacker = (Player)lightning;
            if (event.getEntity().getScoreboardTags().contains("aurum_mob") && !event.getEntity().getScoreboardTags().contains("aurum_training_dummy") && displacementActive.containsKey(attacker.getUniqueId()) && displacementActive.get(attacker.getUniqueId()).booleanValue()) {
                attacker.getWorld().spawnParticle(Particle.PORTAL, attacker.getLocation(), 50, 0.1, 1.0, 0.1);
                event.getEntity().getWorld().spawnParticle(Particle.PORTAL, attacker.getLocation(), 50, 0.1, 1.0, 0.1);
                Location location = event.getEntity().getLocation();
                location.setDirection(attacker.getLocation().getDirection().multiply(-1).setY(0));
                event.getEntity().teleport(attacker.getLocation());
                attacker.teleport(location);
                attacker.getWorld().playSound((Entity)attacker, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
            }
        }
    }

    public static void onDeath(Player player) {
        leechFootActive.put(player.getUniqueId(), false);
        bloodRushActive.put(player.getUniqueId(), false);
        displacementActive.put(player.getUniqueId(), false);
        smiteActive.put(player.getUniqueId(), false);
        bloodRushAccumulator.remove(player.getUniqueId());
    }

    public static void deactivateAbilities(Player player) {
        if (bloodRushCooldown.containsKey(player.getUniqueId()) && bloodRushActive.get(player.getUniqueId()).booleanValue()) {
            RuneAbilityHandler.sendActionBarMessage(player, "You have exited out of blood rush");
            bloodRushCooldown.put(player.getUniqueId(), System.currentTimeMillis() + 30000L);
            bloodRushAccumulator.remove(player.getUniqueId());
            player.getWorld().playSound((Entity)player, Sound.ENTITY_RAVAGER_STUNNED, 1.0f, 1.0f);
            player.setWorldBorder(null);
            bloodRushActive.put(player.getUniqueId(), false);
            bloodRushAccumulator.remove(player.getUniqueId());
        }
        if (smiteCooldown.containsKey(player.getUniqueId()) && smiteActive.get(player.getUniqueId()).booleanValue()) {
            RuneAbilityHandler.sendActionBarMessage(player, "You have deactivated smite");
            smiteCooldown.put(player.getUniqueId(), System.currentTimeMillis() + 30000L);
            smiteActive.put(player.getUniqueId(), false);
        }
        if (displacementCooldown.containsKey(player.getUniqueId()) && displacementActive.get(player.getUniqueId()).booleanValue()) {
            RuneAbilityHandler.sendActionBarMessage(player, "You have deactivated displacement");
            displacementCooldown.put(player.getUniqueId(), System.currentTimeMillis() + 10000L);
            displacementActive.put(player.getUniqueId(), false);
        }
        if (leechFootActive.containsKey(player.getUniqueId()) && leechFootActive.get(player.getUniqueId()).booleanValue()) {
            RuneAbilityHandler.sendActionBarMessage(player, "You have disabled Leach Foot");
            leechFootActive.put(player.getUniqueId(), false);
            player.removePotionEffect(PotionEffectType.SPEED);
        }
        PlayerSpellManager.disableRitual(player);
    }

    public static boolean isBloodrushNotActive(Player player) {
        if (bloodRushActive.containsKey(player.getUniqueId())) {
            return bloodRushActive.get(player.getUniqueId()) == false;
        }
        return true;
    }

    private static void sendActionBarMessage(Player player, String rawString) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, (BaseComponent)new TextComponent(ChatColor.DARK_RED + "[" + ChatColor.RED + "!" + ChatColor.DARK_RED + "] " + ChatColor.GRAY + rawString + ChatColor.DARK_RED + " [" + ChatColor.RED + "!" + ChatColor.DARK_RED + "]"));
    }
}
