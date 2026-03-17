/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.EntityPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityCreature
 *  net.minecraft.world.entity.EntityLiving
 *  org.bukkit.Particle
 *  org.bukkit.Sound
 *  org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity
 *  org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityTargetEvent$TargetReason
 */
package goldenshadow.aurum.items.spells;

import goldenshadow.aurum.items.spells.PlayerSpell;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.EntityLiving;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;

public class Taunt
extends PlayerSpell {
    public Taunt(Player player) {
        super(player);
        player.getWorld().playSound(this.getInitialLocation(), Sound.ENTITY_RAVAGER_ROAR, 1.0f, 1.0f);
        player.getWorld().spawnParticle(Particle.SMOKE_LARGE, player.getEyeLocation(), 10, 0.2, 0.2, 0.2, 0.4);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getTick() == 1 || this.getTick() == 10 || this.getTick() == 15 || this.getTick() == 20) {
            for (Entity e : this.getPlayer().getNearbyEntities(10.0, 10.0, 10.0)) {
                if (!e.getScoreboardTags().contains("aurum_mob") || !(e instanceof LivingEntity)) continue;
                Taunt.tauntMob((LivingEntity)e, this.getPlayer());
            }
        }
        if (this.getTick() > 20) {
            this.spellFinished();
        }
    }

    private static void tauntMob(LivingEntity entity, Player player) {
        try {
            net.minecraft.world.entity.Entity mcEntity = ((CraftEntity)entity).getHandle();
            EntityLiving creature = (EntityLiving)mcEntity;
            EntityCreature p = (EntityCreature)creature;
            EntityPlayer nmsLivingEntity = ((CraftPlayer)player).getHandle();
            p.setTarget((EntityLiving)nmsLivingEntity, EntityTargetEvent.TargetReason.CUSTOM, false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

