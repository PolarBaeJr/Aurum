/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 */
package goldenshadow.aurum.items.spells;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.events.EntityDamage;
import goldenshadow.aurum.items.ItemHelper;
import goldenshadow.aurum.items.spells.PlayerSpell;
import goldenshadow.aurum.items.spells.Ritual;
import goldenshadow.aurum.items.spells.Taunt;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerSpellManager {
    private static final ArrayList<PlayerSpell> activeSpells = new ArrayList<>();
    private static final ArrayList<UUID> toRemove = new ArrayList<>();
    static final ItemHelper itemHelper = new ItemHelper();

    public static void cast(PlayerSpell spell) {
        activeSpells.add(spell);
    }

    public static void castLoop() {
        for (UUID uuid : toRemove) {
            activeSpells.removeIf(x -> x.getUuid().equals(uuid));
        }
        toRemove.clear();
        for (PlayerSpell s : activeSpells) {
            s.tick();
        }
    }

    public static void removeSpell(UUID uuid) {
        toRemove.add(uuid);
    }

    public static List<Player> getTauntingPlayers() {
        ArrayList<Player> list = new ArrayList<Player>();
        for (PlayerSpell spell : activeSpells) {
            if (!(spell instanceof Taunt)) continue;
            list.add(spell.getPlayer());
        }
        return list;
    }

    public static void disableRitual(Player player) {
        for (PlayerSpell spell : activeSpells) {
            if (!(spell instanceof Ritual) || !spell.getPlayer().equals(player)) continue;
            PlayerSpellManager.removeSpell(spell.getUuid());
        }
    }

    public static void applyDamage(LivingEntity entity, double damage, Player player) {
        EntityDamage.spellCasters.add(player.getUniqueId());
        entity.damage(damage, (Entity)player);
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Aurum.getPlugin(), () -> {
            do {
                EntityDamage.spellCasters.remove(player.getUniqueId());
            } while (EntityDamage.spellCasters.contains(player.getUniqueId()));
        }, 1L);
    }
}

