package goldenshadow.aurum.entities;

import goldenshadow.aurum.Aurum;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.UUID;
import org.bukkit.Bukkit;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
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
import org.bukkit.entity.Mob;
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
            entity.getPersistentDataContainer().set(new NamespacedKey(plugin, "NodeUUID"), PersistentDataType.STRING, uuid.toString());
        }
        if (data.getXpType() == null) {
            data.setXpType(XpType.NONE);
        }
        if (data.getAiType() == null) {
            data.setAiType(AIType.HOSTILE);
        }

        setBaseAttribute(entity, Attribute.ATTACK_DAMAGE, data.getDamage());
        setBaseAttribute(entity, Attribute.MAX_HEALTH, data.getHealth());
        setBaseAttribute(entity, Attribute.ATTACK_KNOCKBACK, data.getKnockback());
        setBaseAttribute(entity, Attribute.MOVEMENT_SPEED, data.getSpeed());
        setBaseAttribute(entity, Attribute.FOLLOW_RANGE, data.getFollowRange());
        setBaseAttribute(entity, Attribute.KNOCKBACK_RESISTANCE, data.getKnockbackResistance());

        AttributeInstance maxHealth = entity.getAttribute(Attribute.MAX_HEALTH);
        double maxValue = maxHealth != null ? maxHealth.getValue() : data.getHealth();
        entity.setHealth(Math.min(data.getHealth(), maxValue));
        entity.setMaximumNoDamageTicks(5);

        entity.getPersistentDataContainer().set(new NamespacedKey(plugin, "mobLevel"), PersistentDataType.INTEGER, data.getLevel());
        entity.getPersistentDataContainer().set(new NamespacedKey(plugin, "spellMultiplier"), PersistentDataType.DOUBLE, data.getSpellDamageMultiplier());
        entity.getPersistentDataContainer().set(new NamespacedKey(plugin, "spellChance"), PersistentDataType.DOUBLE, data.getSpellCastChance());
        entity.getPersistentDataContainer().set(new NamespacedKey(plugin, "xpType"), PersistentDataType.STRING, data.getXpType().toString());
        entity.getPersistentDataContainer().set(new NamespacedKey(plugin, "aiType"), PersistentDataType.STRING, data.getAiType().toString().toLowerCase());

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.getSpells().length; ++i) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(data.getSpells()[i]);
        }
        entity.getPersistentDataContainer().set(new NamespacedKey(plugin, "spells"), PersistentDataType.STRING, stringBuilder.toString());
        entity.setCustomNameVisible(true);

        Component nameComponent = Component.empty();
        if (data.getXpType() == XpType.HIGH) {
            nameComponent = nameComponent.append(Component.text("[\u2724] ", NamedTextColor.GRAY));
        }
        if (data.getXpType() == XpType.BOSS) {
            nameComponent = nameComponent.append(Component.text("[\u2620] ", NamedTextColor.GRAY));
        }
        NamedTextColor nameColor = (data.getAiType() == AIType.FRIENDLY || data.getAiType() == AIType.FRIENDLY_SCARED) ? NamedTextColor.GREEN : NamedTextColor.RED;
        nameComponent = nameComponent
                .append(Component.text(data.getName(), nameColor))
                .append(Component.text(" [Lv. " + data.getLevel() + "]", NamedTextColor.GRAY));
        entity.customName(nameComponent);

        try {
            if (entity.getEquipment() != null) {
                entity.getEquipment().setItemInMainHand(decodeItem(data.getMainhand()), true);
                entity.getEquipment().setItemInOffHand(decodeItem(data.getOffhand()), true);
                entity.getEquipment().setHelmet(decodeItem(data.getHelmet()), true);
                entity.getEquipment().setChestplate(decodeItem(data.getChestplate()), true);
                entity.getEquipment().setLeggings(decodeItem(data.getLeggings()), true);
                entity.getEquipment().setBoots(decodeItem(data.getBoots()), true);
                if (entity.getEquipment().getHelmet() != null && entity.getEquipment().getHelmet().getType() == Material.AIR) {
                    entity.getEquipment().setHelmet(new ItemStack(Material.STONE_BUTTON));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (!data.isDefaultLoot()) {
            entity.getPersistentDataContainer().set(new NamespacedKey(plugin, "customLoot"), PersistentDataType.STRING, data.getCustomLoot());
            entity.getPersistentDataContainer().set(new NamespacedKey(plugin, "lootChance"), PersistentDataType.DOUBLE, data.getItemDropChance());
        }
        if (entity instanceof Ageable ageable) {
            if (data.isBaby()) {
                ageable.setBaby();
            } else {
                ageable.setAdult();
            }
        }
        if (entity instanceof PiglinAbstract piglinAbstract) {
            piglinAbstract.setImmuneToZombification(true);
        }
        if (entity instanceof Hoglin hoglin) {
            hoglin.setImmuneToZombification(true);
        }
        if (entity instanceof Sheep sheep) {
            sheep.setColor(data.getDyeColor());
        }
        if (entity instanceof Shulker shulker) {
            shulker.setColor(data.getDyeColor());
        }
        if (entity instanceof Llama llama) {
            llama.setColor(data.getLlamaColor());
        }
        if (entity instanceof Horse horse) {
            horse.setColor(data.getHorseColor());
            horse.setStyle(data.getHorseStyle());
        }
        if (entity instanceof Frog frog) {
            frog.setVariant(data.getFrogVariant());
        }
        if (entity instanceof MushroomCow mushroomCow) {
            mushroomCow.setVariant(data.getMushroomCowVariant());
        }
        if (entity instanceof Parrot parrot) {
            parrot.setVariant(data.getParrotVariant());
        }
        if (entity instanceof ZombieVillager zombieVillager) {
            zombieVillager.setVillagerProfession(data.getVillagerProfession());
        }
        if (entity instanceof Villager villager) {
            villager.setProfession(data.getVillagerProfession());
            villager.setVillagerType(data.getVillagerType());
        }
        if (entity instanceof Fox fox) {
            fox.setFoxType(data.getFoxType());
        }
        if (entity instanceof Rabbit rabbit) {
            rabbit.setRabbitType(data.getRabbitType());
        }
        if (entity instanceof Creeper creeper) {
            creeper.setExplosionRadius(data.getCreeperExplosionRadius());
            creeper.setMaxFuseTicks(data.getCreeperMaxFuseTicks());
            creeper.setPowered(data.isCreeperPowered());
        }
        if (entity instanceof Slime slime) {
            slime.setSize(data.getSize());
            setBaseAttribute(entity, Attribute.MAX_HEALTH, data.getHealth());
            AttributeInstance slimeHealth = entity.getAttribute(Attribute.MAX_HEALTH);
            double slimeMax = slimeHealth != null ? slimeHealth.getValue() : data.getHealth();
            entity.setHealth(Math.min(data.getHealth(), slimeMax));
        }
        if (entity instanceof Phantom phantom) {
            phantom.setSize(data.getSize());
        }
        if (entity instanceof MagmaCube magmaCube) {
            magmaCube.setSize(data.getSize());
        }
        if (data.getAiType() == AIType.HOSTILE) {
            makeHostile(entity);
        }
        if (data.getAiType() == AIType.HOSTILE_RANGED) {
            makeRangedHostile(entity);
        }
        if (data.getAiType() == AIType.FRIENDLY || data.getAiType() == AIType.FRIENDLY_SCARED) {
            entity.getScoreboardTags().add("aurum_friendly");
            makeFriendly(entity);
            if (data.getAiType() == AIType.FRIENDLY_SCARED) {
                makeScared(entity);
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
                if (!e.getScoreboardTags().contains("aurum_mob") || e.getScoreboardTags().contains("aurum_training_dummy") || !Bukkit.getOnlinePlayers().stream().noneMatch(x -> x.getWorld().equals(e.getWorld()) && x.getLocation().distance(e.getLocation()) < 64.0)) {
                    continue;
                }
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (!p.getScoreboardTags().contains("aurum_debug_despawning")) {
                        continue;
                    }
                    Component despawnMsg = Component.text("Manually despawned: ", NamedTextColor.DARK_AQUA);
                    Component entityName = e.customName();
                    if (entityName != null) {
                        despawnMsg = despawnMsg.append(entityName);
                    }
                    p.sendMessage(despawnMsg);
                }
                String s = e.getPersistentDataContainer().get(new NamespacedKey(plugin, "xpType"), PersistentDataType.STRING);
                if (s != null && s.equals(XpType.BOSS.toString())) {
                    continue;
                }
                e.remove();
            }
        }
    }

    private static void setBaseAttribute(LivingEntity entity, Attribute attribute, double value) {
        AttributeInstance instance = entity.getAttribute(attribute);
        if (instance != null) {
            instance.setBaseValue(value);
        }
    }

    private static void makeHostile(Entity entity) {
        if (entity instanceof Mob mob) {
            mob.setAware(true);
        }
    }

    private static void makeRangedHostile(Entity entity) {
        makeHostile(entity);
        entity.addScoreboardTag("aurum_mob_ranged");
    }

    private static void makeScared(Entity entity) {
        if (entity instanceof Mob mob) {
            mob.setAware(true);
            mob.setTarget(null);
        }
    }

    private static void makeFriendly(Entity entity) {
        if (entity instanceof Mob mob) {
            mob.setTarget(null);
        }
    }
}
