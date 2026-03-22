/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.adventure.text.Component
 *  net.kyori.adventure.text.TextComponent
 *  net.kyori.adventure.text.format.NamedTextColor
 *  net.kyori.adventure.text.format.TextColor
 *  org.bukkit.DyeColor
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Fox$Type
 *  org.bukkit.entity.Frog$Variant
 *  org.bukkit.entity.Horse$Color
 *  org.bukkit.entity.Horse$Style
 *  org.bukkit.entity.Llama$Color
 *  org.bukkit.entity.MushroomCow$Variant
 *  org.bukkit.entity.Parrot$Variant
 *  org.bukkit.entity.Rabbit$Type
 *  org.bukkit.entity.Villager$Profession
 *  org.bukkit.entity.Villager$Type
 */
package goldenshadow.aurum.entities;

import goldenshadow.aurum.entities.AIType;
import goldenshadow.aurum.entities.SpellName;
import goldenshadow.aurum.entities.XpType;
import java.util.Arrays;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Frog;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Llama;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Villager;

public class EntityData {
    private double health = 5.0;
    private EntityType type;
    private int level = 1;
    private XpType xpType = XpType.NONE;
    private AIType aiType = AIType.VANILLA;
    private double damage = 1.0;
    private double speed = 0.23;
    private double knockback = 0.0;
    private double followRange = 20.0;
    private SpellName[] spellNames = new SpellName[0];
    private String name = "NO NAME";
    private String mainhand = "EMPTY";
    private String mainhandName = "Air";
    private String offhand = "EMPTY";
    private String offhandName = "Air";
    private String helmet = "EMPTY";
    private String helmetName = "Air";
    private String chestplate = "EMPTY";
    private String chestplateName = "Air";
    private String leggings = "EMPTY";
    private String leggingsName = "Air";
    private String boots = "EMPTY";
    private String bootsName = "Air";
    private boolean defaultLoot = true;
    private String customLoot;
    private double knockbackResistance = 0.0;
    private boolean isBaby = false;
    private DyeColor dyeColor = DyeColor.WHITE;
    private Horse.Color horseColor = Horse.Color.BROWN;
    private Horse.Style horseStyle = Horse.Style.NONE;
    private Llama.Color llamaColor = Llama.Color.CREAMY;
    private Parrot.Variant parrotVariant = Parrot.Variant.CYAN;
    private Frog.Variant frogVariant = Frog.Variant.WARM;
    private MushroomCow.Variant mushroomCowVariant = MushroomCow.Variant.RED;
    private Villager.Type villagerType = Villager.Type.PLAINS;
    private Villager.Profession villagerProfession = Villager.Profession.NITWIT;
    private Fox.Type foxType = Fox.Type.RED;
    private Rabbit.Type rabbitType = Rabbit.Type.BROWN;
    private boolean creeperPowered = false;
    private int creeperExplosionRadius = 3;
    private int creeperMaxFuseTicks = 30;
    private double spellDamageMultiplier = 1.0;
    private double spellCastChance = 0.1;
    private int size = 0;
    private double itemDropChance = 1.0;

    public Component toComponent() {
        return ((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.text((String)"=======================================\n", (TextColor)NamedTextColor.DARK_AQUA).append((Component)Component.text((String)("Type: " + String.valueOf(this.type) + ", Health: " + this.health + "\n"), (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)("Level: " + this.level + ", XpType: " + this.xpType.toString() + "\n"), (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)("AiType: " + this.aiType.toString() + ", Damage: " + this.damage + "\n"), (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)("WalkSpeed: " + this.speed + ", AttackKnockback: " + this.knockback + "\n"), (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)("FollowRange: " + this.followRange + ", Name: " + this.name + "\n"), (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)("Mainhand: " + this.mainhandName + ", Offhand: " + this.offhandName + "\n"), (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)("Helmet: " + this.helmetName + ", Chestplate: " + this.chestplateName + "\n"), (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)("Leggings: " + this.leggingsName + ", Boots: " + this.bootsName + "\n"), (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)("DefaultLoot: " + this.defaultLoot + ", Knockback Resistance: " + this.knockbackResistance + "\n"), (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)("Spells: " + Arrays.toString((Object[])this.spellNames) + "\n"), (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)("Baby: " + this.isBaby + ", Dye Color: " + this.dyeColor.toString() + "\n"), (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)("Horse Color: " + this.horseColor.toString() + ", HorseStyle: " + this.horseStyle.toString() + "\n"), (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)("Llama Color: " + this.llamaColor.toString() + ", Parrot Variant: " + String.valueOf(this.parrotVariant) + "\n"), (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)("Frog Variant: " + this.frogVariant.toString() + ", Mooshroom Variant: " + this.mushroomCowVariant.toString() + "\n"), (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)("Villager Biome: " + this.villagerType.toString() + ", Villager Profession: " + this.villagerProfession.toString() + "\n"), (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)("Fox Type: " + this.foxType.toString() + ", Rabbit Type: " + this.rabbitType.toString() + "\n"), (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)("Spell Multiplier: " + this.spellDamageMultiplier + ", Spell Cast Chance: " + this.spellCastChance + "\n"), (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)("Size: " + this.size + ", Item Drop Chance: " + this.itemDropChance), (TextColor)NamedTextColor.YELLOW))).append((Component)Component.text((String)"=======================================\n", (TextColor)NamedTextColor.DARK_AQUA));
    }

    public int getSize() {
        return this.size;
    }

    public double getItemDropChance() {
        return this.itemDropChance;
    }

    public void setItemDropChance(double itemDropChance) {
        this.itemDropChance = itemDropChance;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getSpellCastChance() {
        return this.spellCastChance;
    }

    public void setSpellCastChance(double spellCastChance) {
        this.spellCastChance = spellCastChance;
    }

    public boolean isCreeperPowered() {
        return this.creeperPowered;
    }

    public int getCreeperExplosionRadius() {
        return this.creeperExplosionRadius;
    }

    public int getCreeperMaxFuseTicks() {
        return this.creeperMaxFuseTicks;
    }

    public void setCreeperExplosionRadius(int creeperExplosionRadius) {
        this.creeperExplosionRadius = creeperExplosionRadius;
    }

    public void setCreeperMaxFuseTicks(int creeperMaxFuseTicks) {
        this.creeperMaxFuseTicks = creeperMaxFuseTicks;
    }

    public void setCreeperPowered(boolean creeperPowered) {
        this.creeperPowered = creeperPowered;
    }

    public void setFoxType(Fox.Type foxType) {
        this.foxType = foxType;
    }

    public Fox.Type getFoxType() {
        return this.foxType;
    }

    public Rabbit.Type getRabbitType() {
        return this.rabbitType;
    }

    public void setRabbitType(Rabbit.Type rabbitType) {
        this.rabbitType = rabbitType;
    }

    public double getHealth() {
        return this.health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public EntityType getType() {
        return this.type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public XpType getXpType() {
        return this.xpType;
    }

    public void setXpType(XpType xpType) {
        this.xpType = xpType;
    }

    public AIType getAiType() {
        return this.aiType;
    }

    public void setAiType(AIType aiType) {
        this.aiType = aiType;
    }

    public double getDamage() {
        return this.damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getKnockback() {
        return this.knockback;
    }

    public void setKnockback(double knockback) {
        this.knockback = knockback;
    }

    public double getFollowRange() {
        return this.followRange;
    }

    public void setFollowRange(double followRange) {
        this.followRange = followRange;
    }

    public SpellName[] getSpells() {
        return this.spellNames;
    }

    public void setSpells(SpellName[] spellNames) {
        this.spellNames = spellNames;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDefaultLoot() {
        return this.defaultLoot;
    }

    public void setLootType(boolean defaultLoot) {
        this.defaultLoot = defaultLoot;
    }

    public String getCustomLoot() {
        return this.customLoot;
    }

    public void setCustomLoot(String customLoot) {
        this.customLoot = customLoot;
    }

    public boolean isBaby() {
        return this.isBaby;
    }

    public void setBaby(boolean baby) {
        this.isBaby = baby;
    }

    public DyeColor getDyeColor() {
        return this.dyeColor;
    }

    public void setDyeColor(DyeColor dyeColor) {
        this.dyeColor = dyeColor;
    }

    public Horse.Color getHorseColor() {
        return this.horseColor;
    }

    public void setHorseColor(Horse.Color horseColor) {
        this.horseColor = horseColor;
    }

    public Horse.Style getHorseStyle() {
        return this.horseStyle;
    }

    public void setHorseStyle(Horse.Style horseStyle) {
        this.horseStyle = horseStyle;
    }

    public Llama.Color getLlamaColor() {
        return this.llamaColor;
    }

    public void setLlamaColor(Llama.Color llamaColor) {
        this.llamaColor = llamaColor;
    }

    public Parrot.Variant getParrotVariant() {
        return this.parrotVariant;
    }

    public void setParrotVariant(Parrot.Variant parrotVariant) {
        this.parrotVariant = parrotVariant;
    }

    public Frog.Variant getFrogVariant() {
        return this.frogVariant;
    }

    public void setFrogVariant(Frog.Variant frogVariant) {
        this.frogVariant = frogVariant;
    }

    public MushroomCow.Variant getMushroomCowVariant() {
        return this.mushroomCowVariant;
    }

    public void setMushroomCowVariant(MushroomCow.Variant mushroomCowVariant) {
        this.mushroomCowVariant = mushroomCowVariant;
    }

    public Villager.Type getVillagerType() {
        return this.villagerType;
    }

    public void setVillagerType(Villager.Type villagerType) {
        this.villagerType = villagerType;
    }

    public Villager.Profession getVillagerProfession() {
        return this.villagerProfession;
    }

    public void setVillagerProfession(Villager.Profession villagerProfession) {
        this.villagerProfession = villagerProfession;
    }

    public void setMainhandName(String mainhandName) {
        this.mainhandName = mainhandName;
    }

    public void setOffhand(String offhand) {
        this.offhand = offhand;
    }

    public void setOffhandName(String offhandName) {
        this.offhandName = offhandName;
    }

    public void setHelmet(String helmet) {
        this.helmet = helmet;
    }

    public void setHelmetName(String helmetName) {
        this.helmetName = helmetName;
    }

    public void setChestplate(String chestplate) {
        this.chestplate = chestplate;
    }

    public void setChestplateName(String chestplateName) {
        this.chestplateName = chestplateName;
    }

    public void setLeggings(String leggings) {
        this.leggings = leggings;
    }

    public void setLeggingsName(String leggingsName) {
        this.leggingsName = leggingsName;
    }

    public String getMainhand() {
        return this.mainhand;
    }

    public void setMainhand(String mainhand) {
        this.mainhand = mainhand;
    }

    public String getOffhand() {
        return this.offhand;
    }

    public String getHelmet() {
        return this.helmet;
    }

    public String getChestplate() {
        return this.chestplate;
    }

    public String getLeggings() {
        return this.leggings;
    }

    public String getBoots() {
        return this.boots;
    }

    public void setBoots(String boots) {
        this.boots = boots;
    }

    public void setBootsName(String bootsName) {
        this.bootsName = bootsName;
    }

    public void setKnockbackResistance(double knockbackResistance) {
        this.knockbackResistance = knockbackResistance;
    }

    public double getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public double getSpellDamageMultiplier() {
        return this.spellDamageMultiplier;
    }

    public void setSpellDamageMultiplier(double spellDamageMultiplier) {
        this.spellDamageMultiplier = spellDamageMultiplier;
    }
}

