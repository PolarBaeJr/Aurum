/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityCreature
 *  net.minecraft.world.entity.EntityInsentient
 *  net.minecraft.world.entity.EntityLiving
 *  net.minecraft.world.entity.ai.attributes.AttributeBase
 *  net.minecraft.world.entity.ai.attributes.AttributeMapBase
 *  net.minecraft.world.entity.ai.attributes.AttributeModifiable
 *  net.minecraft.world.entity.ai.attributes.GenericAttributes
 *  net.minecraft.world.entity.ai.goal.PathfinderGoal
 *  net.minecraft.world.entity.ai.goal.PathfinderGoalAvoidTarget
 *  net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer
 *  net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack
 *  net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget
 *  net.minecraft.world.entity.monster.EntitySlime
 *  net.minecraft.world.entity.player.EntityHuman
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.World
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity
 *  org.bukkit.entity.Ageable
 *  org.bukkit.entity.Creeper
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Fox
 *  org.bukkit.entity.Frog
 *  org.bukkit.entity.Hoglin
 *  org.bukkit.entity.Horse
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Llama
 *  org.bukkit.entity.MagmaCube
 *  org.bukkit.entity.MushroomCow
 *  org.bukkit.entity.Parrot
 *  org.bukkit.entity.Phantom
 *  org.bukkit.entity.PiglinAbstract
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Rabbit
 *  org.bukkit.entity.Sheep
 *  org.bukkit.entity.Shulker
 *  org.bukkit.entity.Slime
 *  org.bukkit.entity.Villager
 *  org.bukkit.entity.ZombieVillager
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.util.io.BukkitObjectInputStream
 */
package goldenshadow.aurum.entities;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.entities.AIType;
import goldenshadow.aurum.entities.EntityData;
import goldenshadow.aurum.entities.XpType;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.ai.attributes.AttributeBase;
import net.minecraft.world.entity.ai.attributes.AttributeMapBase;
import net.minecraft.world.entity.ai.attributes.AttributeModifiable;
import net.minecraft.world.entity.ai.attributes.GenericAttributes;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.ai.goal.PathfinderGoalAvoidTarget;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.monster.EntitySlime;
import net.minecraft.world.entity.player.EntityHuman;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Frog;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Llama;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.PiglinAbstract;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Shulker;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Villager;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.io.BukkitObjectInputStream;

public class CustomEntity {
    private static final Plugin plugin = Aurum.getPlugin();

    public static void deserialize(LivingEntity entity, EntityData data, UUID uuid) {
        entity.getScoreboardTags().add("aurum_mob");
        if (uuid != null) {
            entity.getPersistentDataContainer().set(new NamespacedKey(plugin, "NodeUUID"), PersistentDataType.STRING, (Object)uuid.toString());
        }
        if (data.getXpType() == null) {
            data.setXpType(XpType.NONE);
        }
        if (data.getAiType() == null) {
            data.setAiType(AIType.HOSTILE);
        }
        CustomEntity.registerAttribute(GenericAttributes.f, (Entity)entity);
        CustomEntity.registerAttribute(GenericAttributes.a, (Entity)entity);
        CustomEntity.registerAttribute(GenericAttributes.g, (Entity)entity);
        CustomEntity.registerAttribute(GenericAttributes.d, (Entity)entity);
        CustomEntity.registerAttribute(GenericAttributes.b, (Entity)entity);
        CustomEntity.registerAttribute(GenericAttributes.c, (Entity)entity);
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(data.getDamage());
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(data.getHealth());
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK)).setBaseValue(data.getKnockback());
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(data.getSpeed());
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_FOLLOW_RANGE)).setBaseValue(data.getFollowRange());
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE)).setBaseValue(data.getKnockbackResistance());
        entity.setHealth(data.getHealth());
        entity.setMaximumNoDamageTicks(5);
        entity.getPersistentDataContainer().set(new NamespacedKey(plugin, "mobLevel"), PersistentDataType.INTEGER, (Object)data.getLevel());
        entity.getPersistentDataContainer().set(new NamespacedKey(plugin, "spellMultiplier"), PersistentDataType.DOUBLE, (Object)data.getSpellDamageMultiplier());
        entity.getPersistentDataContainer().set(new NamespacedKey(plugin, "spellChance"), PersistentDataType.DOUBLE, (Object)data.getSpellCastChance());
        entity.getPersistentDataContainer().set(new NamespacedKey(plugin, "xpType"), PersistentDataType.STRING, (Object)data.getXpType().toString());
        entity.getPersistentDataContainer().set(new NamespacedKey(plugin, "aiType"), PersistentDataType.STRING, (Object)data.getAiType().toString().toLowerCase());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.getSpells().length; ++i) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append((Object)data.getSpells()[i]);
        }
        entity.getPersistentDataContainer().set(new NamespacedKey(plugin, "spells"), PersistentDataType.STRING, (Object)stringBuilder.toString());
        entity.setCustomNameVisible(true);
        String name = "";
        if (data.getXpType() == XpType.HIGH) {
            name = name.concat(ChatColor.GRAY + "[\u2724] ");
        }
        if (data.getXpType() == XpType.BOSS) {
            name = name.concat(ChatColor.GRAY + "[\u2620] ");
        }
        name = name.concat((data.getAiType() == AIType.FRIENDLY || data.getAiType() == AIType.FRIENDLY_SCARED ? ChatColor.GREEN : ChatColor.RED) + data.getName() + ChatColor.GRAY + " [Lv. " + data.getLevel() + "]");
        entity.setCustomName(name);
        try {
            assert (entity.getEquipment() != null);
            entity.getEquipment().setItemInMainHand(CustomEntity.decodeItem(data.getMainhand()), true);
            entity.getEquipment().setItemInOffHand(CustomEntity.decodeItem(data.getOffhand()), true);
            entity.getEquipment().setHelmet(CustomEntity.decodeItem(data.getHelmet()), true);
            entity.getEquipment().setChestplate(CustomEntity.decodeItem(data.getChestplate()), true);
            entity.getEquipment().setLeggings(CustomEntity.decodeItem(data.getLeggings()), true);
            entity.getEquipment().setBoots(CustomEntity.decodeItem(data.getBoots()), true);
            assert (entity.getEquipment().getHelmet() != null);
            if (entity.getEquipment().getHelmet().getType() == Material.AIR) {
                entity.getEquipment().setHelmet(new ItemStack(Material.STONE_BUTTON));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (!data.isDefaultLoot()) {
            entity.getPersistentDataContainer().set(new NamespacedKey(plugin, "customLoot"), PersistentDataType.STRING, (Object)data.getCustomLoot());
            entity.getPersistentDataContainer().set(new NamespacedKey(plugin, "lootChance"), PersistentDataType.DOUBLE, (Object)data.getItemDropChance());
        }
        if (entity instanceof Ageable) {
            if (data.isBaby()) {
                ((Ageable)entity).setBaby();
            } else {
                ((Ageable)entity).setAdult();
            }
        }
        if (entity instanceof PiglinAbstract) {
            ((PiglinAbstract)entity).setImmuneToZombification(true);
        }
        if (entity instanceof Hoglin) {
            ((Hoglin)entity).setImmuneToZombification(true);
        }
        if (entity instanceof Sheep) {
            ((Sheep)entity).setColor(data.getDyeColor());
        }
        if (entity instanceof Shulker) {
            ((Shulker)entity).setColor(data.getDyeColor());
        }
        if (entity instanceof Llama) {
            ((Llama)entity).setColor(data.getLlamaColor());
        }
        if (entity instanceof Horse) {
            ((Horse)entity).setColor(data.getHorseColor());
            ((Horse)entity).setStyle(data.getHorseStyle());
        }
        if (entity instanceof Frog) {
            ((Frog)entity).setVariant(data.getFrogVariant());
        }
        if (entity instanceof MushroomCow) {
            ((MushroomCow)entity).setVariant(data.getMushroomCowVariant());
        }
        if (entity instanceof Parrot) {
            ((Parrot)entity).setVariant(data.getParrotVariant());
        }
        if (entity instanceof ZombieVillager) {
            ((ZombieVillager)entity).setVillagerProfession(data.getVillagerProfession());
        }
        if (entity instanceof Villager) {
            ((Villager)entity).setProfession(data.getVillagerProfession());
            ((Villager)entity).setVillagerType(data.getVillagerType());
        }
        if (entity instanceof Fox) {
            ((Fox)entity).setFoxType(data.getFoxType());
        }
        if (entity instanceof Rabbit) {
            ((Rabbit)entity).setRabbitType(data.getRabbitType());
        }
        if (entity instanceof Creeper) {
            ((Creeper)entity).setExplosionRadius(data.getCreeperExplosionRadius());
            ((Creeper)entity).setMaxFuseTicks(data.getCreeperMaxFuseTicks());
            ((Creeper)entity).setPowered(data.isCreeperPowered());
        }
        if (entity instanceof Slime) {
            ((Slime)entity).setSize(data.getSize());
            net.minecraft.world.entity.Entity mcEntity = ((CraftEntity)entity).getHandle();
            EntitySlime slime = (EntitySlime)mcEntity;
            Objects.requireNonNull(slime.a(GenericAttributes.a)).a(data.getHealth());
            slime.t((float)data.getHealth());
        }
        if (entity instanceof Phantom) {
            ((Phantom)entity).setSize(data.getSize());
        }
        if (entity instanceof MagmaCube) {
            ((MagmaCube)entity).setSize(data.getSize());
        }
        if (data.getAiType() == AIType.HOSTILE) {
            CustomEntity.makeHostile((Entity)entity);
        }
        if (data.getAiType() == AIType.HOSTILE_RANGED) {
            CustomEntity.makeRangedHostile((Entity)entity);
        }
        if (data.getAiType() == AIType.FRIENDLY || data.getAiType() == AIType.FRIENDLY_SCARED) {
            entity.getScoreboardTags().add("aurum_friendly");
            CustomEntity.makeFriendly((Entity)entity);
            if (data.getAiType() == AIType.FRIENDLY_SCARED) {
                CustomEntity.makeScared((Entity)entity);
            }
        }
        entity.setRemoveWhenFarAway(true);
    }

    public static ItemStack decodeItem(String string) throws IOException, ClassNotFoundException {
        if (string.equals("EMPTY")) {
            return new ItemStack(Material.AIR);
        }
        byte[] serializedObject = Base64.getDecoder().decode(string);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(serializedObject);
        BukkitObjectInputStream inputStream = new BukkitObjectInputStream((InputStream)byteArrayInputStream);
        ItemStack i = (ItemStack)inputStream.readObject();
        inputStream.close();
        return i;
    }

    public static void entityDespawner() {
        for (World w : Bukkit.getWorlds()) {
            for (Entity e : w.getEntities()) {
                if (!e.getScoreboardTags().contains("aurum_mob") || e.getScoreboardTags().contains("aurum_training_dummy") || !Bukkit.getOnlinePlayers().stream().noneMatch(x -> x.getWorld().equals(e.getWorld()) && x.getLocation().distance(e.getLocation()) < 64.0)) continue;
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (!p.getScoreboardTags().contains("aurum_debug_despawning")) continue;
                    p.sendMessage(ChatColor.DARK_AQUA + "Manually despawned: " + e.getCustomName());
                }
                String s = (String)e.getPersistentDataContainer().get(new NamespacedKey(plugin, "xpType"), PersistentDataType.STRING);
                if (s != null && s.equals(XpType.BOSS.toString())) continue;
                e.remove();
            }
        }
    }

    private static void registerAttribute(AttributeBase attribute, Entity entity) {
        if (!(entity instanceof LivingEntity)) {
            return;
        }
        net.minecraft.world.entity.Entity mcEntity = ((CraftEntity)entity).getHandle();
        EntityLiving creature = (EntityLiving)mcEntity;
        AttributeMapBase attributeMap = creature.eM();
        attributeMap.b().add(new AttributeModifiable(attribute, attributeInstance -> attributeInstance.a(1.0)));
        try {
            Field attributeField = AttributeMapBase.class.getDeclaredField("b");
            attributeField.setAccessible(true);
            Map map = (Map)attributeField.get(attributeMap);
            AttributeModifiable attributeModifiable = new AttributeModifiable(attribute, AttributeModifiable::a);
            map.put(attribute, attributeModifiable);
        }
        catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static void makeHostile(Entity entity) {
        net.minecraft.world.entity.Entity mcEntity = ((CraftEntity)entity).getHandle();
        EntityLiving creature = (EntityLiving)mcEntity;
        EntityCreature p = (EntityCreature)creature;
        p.bO.a(0, (PathfinderGoal)new PathfinderGoalNearestAttackableTarget((EntityInsentient)p, EntityHuman.class, true, true));
        p.bO.a(0, (PathfinderGoal)new PathfinderGoalMeleeAttack(p, 1.0, true));
    }

    private static void makeRangedHostile(Entity entity) {
        net.minecraft.world.entity.Entity mcEntity = ((CraftEntity)entity).getHandle();
        EntityLiving creature = (EntityLiving)mcEntity;
        EntityCreature p = (EntityCreature)creature;
        p.bO.a(x -> x instanceof PathfinderGoalMeleeAttack);
        p.bO.a(0, (PathfinderGoal)new PathfinderGoalLookAtPlayer((EntityInsentient)p, EntityHuman.class, 15.0f, 1.0f, false));
        entity.addScoreboardTag("aurum_mob_ranged");
    }

    private static void makeScared(Entity entity) {
        net.minecraft.world.entity.Entity mcEntity = ((CraftEntity)entity).getHandle();
        EntityLiving creature = (EntityLiving)mcEntity;
        EntityCreature p = (EntityCreature)creature;
        p.bO.a(0, (PathfinderGoal)new PathfinderGoalAvoidTarget(p, EntityHuman.class, 15.0f, 1.5, 1.5));
    }

    private static void makeFriendly(Entity entity) {
        net.minecraft.world.entity.Entity mcEntity = ((CraftEntity)entity).getHandle();
        EntityLiving creature = (EntityLiving)mcEntity;
        EntityCreature p = (EntityCreature)creature;
        p.bO.a(x -> x instanceof PathfinderGoalMeleeAttack);
    }
}

