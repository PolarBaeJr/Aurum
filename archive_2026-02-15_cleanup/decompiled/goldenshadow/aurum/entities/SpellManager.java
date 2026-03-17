/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.NamespacedKey
 *  org.bukkit.World
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 */
package goldenshadow.aurum.entities;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.entities.SpellName;
import goldenshadow.aurum.entities.spells.Blizzard;
import goldenshadow.aurum.entities.spells.Charge;
import goldenshadow.aurum.entities.spells.Earthquake;
import goldenshadow.aurum.entities.spells.Explosion;
import goldenshadow.aurum.entities.spells.Fangs;
import goldenshadow.aurum.entities.spells.Faze;
import goldenshadow.aurum.entities.spells.Flame;
import goldenshadow.aurum.entities.spells.Flash;
import goldenshadow.aurum.entities.spells.Heal;
import goldenshadow.aurum.entities.spells.Missile;
import goldenshadow.aurum.entities.spells.Repel;
import goldenshadow.aurum.entities.spells.Shockwave;
import goldenshadow.aurum.entities.spells.Slash;
import goldenshadow.aurum.entities.spells.Spell;
import goldenshadow.aurum.entities.spells.VenomSpit;
import goldenshadow.aurum.entities.spells.Watched;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class SpellManager {
    private static final ArrayList<Spell> activeSpells = new ArrayList();
    private static final ArrayList<UUID> toRemove = new ArrayList();

    public static void spellLoop() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.getScoreboardTags().contains("aurum_debug_spells")) continue;
            p.sendMessage(ChatColor.DARK_AQUA + "Spell tick reached!");
        }
        int spellsCast = 0;
        for (World w : Bukkit.getWorlds()) {
            for (LivingEntity e : w.getLivingEntities()) {
                String spellData;
                if (!e.getScoreboardTags().contains("aurum_mob") || !Bukkit.getOnlinePlayers().stream().anyMatch(x -> x.getWorld().equals(e.getWorld()) && x.getLocation().distance(e.getLocation()) < 30.0)) continue;
                NamespacedKey key = new NamespacedKey((Plugin)Aurum.getPlugin(), "spells");
                if (!e.getPersistentDataContainer().has(key, PersistentDataType.STRING) || e.getCustomName() == null || (spellData = (String)e.getPersistentDataContainer().get(key, PersistentDataType.STRING)) == null || spellData.equals("")) continue;
                Double spellChance = (Double)e.getPersistentDataContainer().get(new NamespacedKey((Plugin)Aurum.getPlugin(), "spellChance"), PersistentDataType.DOUBLE);
                if (spellChance == null) {
                    spellChance = 0.0;
                }
                if (!(ThreadLocalRandom.current().nextDouble(0.0, 1.0) < spellChance) || !activeSpells.stream().noneMatch(x -> x.getCasterUUID().equals(e.getUniqueId()))) continue;
                SpellManager.castSpell(e);
                ++spellsCast;
            }
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.getScoreboardTags().contains("aurum_debug_spells")) continue;
            p.sendMessage(ChatColor.DARK_AQUA + "Total spells cast in this tick: " + spellsCast);
        }
    }

    public static void castLoop() {
        for (UUID uuid : toRemove) {
            activeSpells.removeIf(x -> x.getUuid().equals(uuid));
        }
        toRemove.clear();
        for (Spell s : activeSpells) {
            s.tick();
        }
    }

    private static void castSpell(LivingEntity entity) {
        String s = (String)entity.getPersistentDataContainer().get(new NamespacedKey((Plugin)Aurum.getPlugin(), "spells"), PersistentDataType.STRING);
        if (s != null && !s.equals("")) {
            String[] spellsAsStrings = s.split(",");
            SpellName[] spellNames = new SpellName[spellsAsStrings.length];
            if (spellNames.length == 0) {
                return;
            }
            for (int i = 0; i < spellNames.length; ++i) {
                spellNames[i] = SpellName.valueOf(spellsAsStrings[i]);
            }
            SpellName spellNameToCast = spellNames[ThreadLocalRandom.current().nextInt(0, spellNames.length)];
            switch (spellNameToCast) {
                case CHARGE: {
                    activeSpells.add(new Charge(entity));
                    break;
                }
                case SLASH: {
                    activeSpells.add(new Slash(entity));
                    break;
                }
                case WATCHED: {
                    activeSpells.add(new Watched(entity));
                    break;
                }
                case MISSILE: {
                    activeSpells.add(new Missile(entity));
                    break;
                }
                case REPEL: {
                    activeSpells.add(new Repel(entity));
                    break;
                }
                case FANGS: {
                    activeSpells.add(new Fangs(entity));
                    break;
                }
                case FLASH: {
                    activeSpells.add(new Flash(entity));
                    break;
                }
                case BLIZZARD: {
                    activeSpells.add(new Blizzard(entity));
                    break;
                }
                case EXPLOSION: {
                    activeSpells.add(new Explosion(entity));
                    break;
                }
                case EARTHQUAKE: {
                    activeSpells.add(new Earthquake(entity));
                    break;
                }
                case VENOM_SPIT: {
                    activeSpells.add(new VenomSpit(entity));
                    break;
                }
                case FAZE: {
                    activeSpells.add(new Faze(entity));
                    break;
                }
                case SHOCKWAVE: {
                    activeSpells.add(new Shockwave(entity));
                    break;
                }
                case HEAL: {
                    activeSpells.add(new Heal(entity));
                    break;
                }
                case FLAME: {
                    activeSpells.add(new Flame(entity));
                }
            }
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!p.getScoreboardTags().contains("aurum_debug_spells")) continue;
                p.sendMessage(ChatColor.DARK_AQUA + "Selected " + ChatColor.AQUA + entity.getCustomName() + ChatColor.DARK_AQUA + " to cast spell: " + ChatColor.AQUA + spellNameToCast);
            }
        }
    }

    public static void cast(Spell spell) {
        activeSpells.add(spell);
    }

    public static void removeSpell(UUID uuid) {
        toRemove.add(uuid);
    }
}

