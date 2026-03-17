/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jline.internal.Nullable
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Particle
 *  org.bukkit.Sound
 *  org.bukkit.block.Block
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Slime
 *  org.bukkit.util.Vector
 */
package goldenshadow.aurum.other;

import goldenshadow.aurum.other.Direction;
import goldenshadow.aurum.other.DoorHandler;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.util.Vector;

public class Door {
    @Nullable
    private final ArmorStand displayEntity;
    private final Slime rootEntity;
    @Nullable
    private final Location doorLocation;
    @Nullable
    private final Direction direction;
    private final int requiredTokens;
    private int currentTokens = 0;
    private int opening_stage = 0;
    private int opening_tick = 0;
    private int decay_tick = 0;
    @Nullable
    private final String open_command;
    @Nullable
    private final String close_command;
    private final boolean snowDoor;
    private final List<Location> snowSpawnLocations = new ArrayList<Location>();
    @Nullable
    private final Material singleBlock;
    @Nullable
    private final Location singleBlockLoc;
    private final boolean isMiniBossDoor;

    public Door(Slime rootEntity, ArmorStand displayEntity, Location doorLocation, Direction direction, int requiredTokens, boolean isMiniBossDoor) {
        this.rootEntity = rootEntity;
        this.displayEntity = displayEntity;
        this.doorLocation = doorLocation;
        this.requiredTokens = requiredTokens;
        this.snowDoor = false;
        this.singleBlock = null;
        this.open_command = null;
        this.close_command = null;
        this.singleBlockLoc = null;
        this.isMiniBossDoor = isMiniBossDoor;
        this.direction = direction == Direction.NORTH || direction == Direction.SOUTH ? Direction.NORTH : Direction.EAST;
    }

    public Door(Slime rootEntity, ArmorStand displayEntity, int requiredTokens, String open_command, String close_command, boolean isMiniBossDoor) {
        this.rootEntity = rootEntity;
        this.displayEntity = displayEntity;
        this.doorLocation = null;
        this.direction = null;
        this.snowDoor = false;
        this.singleBlock = null;
        this.singleBlockLoc = null;
        this.requiredTokens = requiredTokens;
        this.open_command = open_command;
        this.close_command = close_command;
        this.isMiniBossDoor = isMiniBossDoor;
    }

    public Door(Slime rootEntity, ArmorStand displayEntity, int requiredTokens, boolean isMiniBossDoor) {
        this.rootEntity = rootEntity;
        this.displayEntity = displayEntity;
        this.requiredTokens = requiredTokens;
        this.snowDoor = true;
        this.singleBlock = null;
        this.open_command = null;
        this.close_command = null;
        this.doorLocation = null;
        this.isMiniBossDoor = isMiniBossDoor;
        this.direction = null;
        this.singleBlockLoc = null;
        Location location = rootEntity.getLocation().clone().add(0.0, -0.5, 0.0);
        this.snowSpawnLocations.add(location.clone());
        this.snowSpawnLocations.add(location.clone().add(1.0, 0.0, 0.0));
        this.snowSpawnLocations.add(location.clone().add(-1.0, 0.0, 0.0));
        this.snowSpawnLocations.add(location.clone().add(1.0, 0.0, 1.0));
        this.snowSpawnLocations.add(location.clone().add(1.0, 0.0, -1.0));
        this.snowSpawnLocations.add(location.clone().add(-1.0, 0.0, 1.0));
        this.snowSpawnLocations.add(location.clone().add(-1.0, 0.0, -1.0));
        this.snowSpawnLocations.add(location.clone().add(0.0, 0.0, 1.0));
        this.snowSpawnLocations.add(location.clone().add(0.0, 0.0, -1.0));
    }

    public Door(Slime rootEntity, ArmorStand displayEntity, int requiredTokens, Material singleBlock, Location singleBlockLoc, boolean isMiniBossDoor) {
        this.rootEntity = rootEntity;
        this.displayEntity = displayEntity;
        this.doorLocation = null;
        this.direction = null;
        this.snowDoor = false;
        this.isMiniBossDoor = isMiniBossDoor;
        this.requiredTokens = requiredTokens;
        this.open_command = null;
        this.close_command = null;
        this.singleBlock = singleBlock;
        this.singleBlockLoc = singleBlockLoc;
    }

    public void tick() {
        assert (this.rootEntity.getLocation().getWorld() != null);
        if (this.currentTokens < this.requiredTokens) {
            ++this.decay_tick;
        }
        if (this.decay_tick > 3600) {
            if (this.displayEntity != null) {
                this.displayEntity.customName(Component.text("Get ", NamedTextColor.GRAY).append(Component.text("[0/" + this.requiredTokens + " Tokens]", NamedTextColor.YELLOW)));
            }
            DoorHandler.removeActiveDoor(this);
        }
        if (this.currentTokens >= this.requiredTokens) {
            ++this.opening_tick;
        }
        if (this.opening_tick == 1) {
            this.rootEntity.getLocation().getWorld().getNearbyEntities(this.rootEntity.getLocation(), 20.0, 20.0, 20.0).forEach(x -> {
                if (x instanceof Player) {
                    Player p = (Player)x;
                    p.sendMessage(Component.text("[", NamedTextColor.GOLD).append(Component.text("!", NamedTextColor.YELLOW)).append(Component.text("] ", NamedTextColor.GOLD)).append(Component.text("Opening!", NamedTextColor.GRAY)));
                    if (this.isMiniBossDoor) {
                        DoorHandler.clearTokens(p);
                        DoorHandler.clearMiniBossTokens(p);
                    } else {
                        DoorHandler.clearTokens(p);
                    }
                }
            });
            if (this.open_command != null) {
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), this.open_command);
            } else {
                this.shiftDoor();
            }
        }
        if (this.opening_tick > 1 && this.snowDoor) {
            for (Location loc : this.snowSpawnLocations) {
                assert (loc.getWorld() != null);
                loc.getWorld().spawnParticle(Particle.SNOWFLAKE, loc, 0, 0.0, 10.0, 0.0, 0.1);
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (!p.getWorld().equals(loc.getWorld()) || p.getLocation().getBlockX() != loc.getBlockX() || p.getLocation().getBlockZ() != loc.getBlockZ() || p.getLocation().getBlockY() < loc.getBlockY() || p.getLocation().getBlockY() - loc.getBlockY() > 8) continue;
                    p.setVelocity(new Vector(0.0, 0.4, 0.0));
                }
            }
        }
        if (this.opening_tick == 10) {
            if (this.singleBlockLoc != null) {
                this.singleBlockLoc.getBlock().setType(this.singleBlock);
            }
            if (this.open_command == null) {
                this.shiftDoor();
            }
        }
        if (this.opening_tick == 20 && this.open_command == null) {
            this.shiftDoor();
        }
        if (this.opening_tick == 3600 && this.open_command == null) {
            this.shiftDoor();
        }
        if (this.opening_tick == 3610 && this.open_command == null) {
            this.shiftDoor();
        }
        if (this.opening_tick == 3620) {
            if (this.singleBlockLoc != null) {
                this.singleBlockLoc.getBlock().setType(Material.AIR);
            }
            if (this.close_command == null) {
                this.shiftDoor();
            } else {
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), this.close_command);
            }
            if (this.displayEntity != null) {
                this.displayEntity.customName(Component.text("Get ", NamedTextColor.GRAY).append(Component.text("[0/" + this.requiredTokens + " Tokens]", NamedTextColor.YELLOW)));
            }
            DoorHandler.removeActiveDoor(this);
        }
    }

    public void addTokens(int amount) {
        this.currentTokens += amount;
        if (this.displayEntity != null) {
            this.displayEntity.customName(Component.text("Get ", NamedTextColor.GRAY).append(Component.text("[" + Math.min(this.currentTokens, this.requiredTokens) + "/" + this.requiredTokens + " Tokens]", NamedTextColor.YELLOW)));
        }
        this.decay_tick = 0;
    }

    public Slime getRoot() {
        return this.rootEntity;
    }

    private void shiftDoor() {
        if (this.doorLocation != null) {
            Block b4;
            Block b3;
            Block b2;
            Block b1;
            Location loc;
            Location loc1 = null;
            Location loc2 = null;
            if (this.direction == Direction.NORTH) {
                loc1 = this.locationFactory(this.doorLocation, -1, 0);
                loc2 = this.locationFactory(this.doorLocation, 1, 0);
            }
            if (this.direction == Direction.EAST) {
                loc1 = this.locationFactory(this.doorLocation, 0, -1);
                loc2 = this.locationFactory(this.doorLocation, 0, 1);
            }
            Location[] locations = new Location[]{this.doorLocation, loc1, loc2};
            assert (this.doorLocation.getWorld() != null);
            this.doorLocation.getWorld().playSound(this.doorLocation, Sound.BLOCK_PISTON_CONTRACT, 1.0f, 1.0f);
            if (this.opening_stage < 3) {
                for (Location location : locations) {
                    loc = new Location(location.getWorld(), location.getX(), location.getY() + (double)this.opening_stage, location.getZ());
                    b1 = loc.getBlock();
                    b2 = loc.add(0.0, 1.0, 0.0).getBlock();
                    b3 = loc.add(0.0, 1.0, 0.0).getBlock();
                    b4 = loc.add(0.0, 1.0, 0.0).getBlock();
                    b4.setType(b3.getType());
                    b4.setBlockData(b3.getBlockData());
                    b3.setType(b2.getType());
                    b3.setBlockData(b2.getBlockData());
                    b2.setType(b1.getType());
                    b2.setBlockData(b1.getBlockData());
                    b1.setType(Material.AIR);
                }
            }
            if (this.opening_stage > 2) {
                for (Location location : locations) {
                    loc = new Location(location.getWorld(), location.getX(), location.getY() + (double)Math.abs(this.opening_stage - 6), location.getZ());
                    b1 = loc.getBlock();
                    b2 = loc.add(0.0, 1.0, 0.0).getBlock();
                    b3 = loc.add(0.0, 1.0, 0.0).getBlock();
                    b4 = loc.add(0.0, -3.0, 0.0).getBlock();
                    b4.setType(b1.getType());
                    b4.setBlockData(b1.getBlockData());
                    b1.setType(b2.getType());
                    b1.setBlockData(b2.getBlockData());
                    b2.setType(b3.getType());
                    b2.setBlockData(b3.getBlockData());
                    b3.setType(Material.AIR);
                }
            }
            ++this.opening_stage;
        }
    }

    private Location locationFactory(Location location, int xOffset, int zOffset) {
        return new Location(location.getWorld(), location.getX() + (double)xOffset, location.getY(), location.getZ() + (double)zOffset);
    }

    public int getRequiredTokens() {
        return this.requiredTokens;
    }

    public boolean isMiniBossDoor() {
        return this.isMiniBossDoor;
    }
}
