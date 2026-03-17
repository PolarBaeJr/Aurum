/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Particle
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 */
package goldenshadow.aurum.items.spells;

import goldenshadow.aurum.items.flags.AttributeID;
import goldenshadow.aurum.items.spells.PlayerSpellManager;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public abstract class PlayerSpell {
    private int tick = 0;
    private final Player player;
    private final Location initialLocation;
    private final UUID uuid;
    private final double damageMultiplier;

    public PlayerSpell(Player player) {
        this.player = player;
        this.initialLocation = player.getLocation();
        this.uuid = UUID.randomUUID();
        this.damageMultiplier = PlayerSpellManager.itemHelper.isAttributeEquipped(player, AttributeID.RUNE_DAMAGE) ? PlayerSpellManager.itemHelper.getAttributeRoll(player, AttributeID.RUNE_DAMAGE, true) : 1.0;
    }

    public void tick() {
        ++this.tick;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    protected double getDamageMultiplier() {
        return this.damageMultiplier;
    }

    protected void spellFinished() {
        PlayerSpellManager.removeSpell(this.uuid);
    }

    protected int getTick() {
        return this.tick;
    }

    protected Player getPlayer() {
        return this.player;
    }

    protected Location getInitialLocation() {
        return this.initialLocation;
    }

    protected Location rayCastFromPlayer() {
        Location loc = this.player.getEyeLocation();
        for (int i = 0; i < 30; ++i) {
            loc = loc.add(this.player.getEyeLocation().getDirection().multiply(1.2));
            assert (loc.getWorld() != null);
            loc.getWorld().spawnParticle(Particle.FLAME, loc, 0);
            if (!loc.getBlock().isEmpty() && !loc.getBlock().isPassable() || loc.getWorld().getNearbyEntities(loc, 0.5, 0.5, 0.5).stream().anyMatch(x -> x instanceof LivingEntity)) break;
        }
        return loc;
    }
}

