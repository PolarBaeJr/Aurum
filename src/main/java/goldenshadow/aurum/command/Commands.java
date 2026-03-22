/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.adventure.text.Component
 *  net.kyori.adventure.text.TextComponent
 *  net.kyori.adventure.text.format.NamedTextColor
 *  net.kyori.adventure.text.format.TextColor
 *  org.bukkit.Location
 *  org.bukkit.command.BlockCommandSender
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Slime
 *  org.jetbrains.annotations.NotNull
 */
package goldenshadow.aurum.command;

import goldenshadow.aurum.Aurum;
import goldenshadow.aurum.command.CommandHelper;
import goldenshadow.aurum.other.Door;
import goldenshadow.aurum.other.DoorHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.jetbrains.annotations.NotNull;

public class Commands
implements CommandExecutor {
    private final CommandHelper commandHelper = new CommandHelper();

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("aurum")) {
            if (args.length > 0 && args[0].equalsIgnoreCase("api")) {
                this.commandHelper.api(args);
                return true;
            }
            if (sender instanceof BlockCommandSender) {
                BlockCommandSender b = (BlockCommandSender)sender;
                this.commandHelper.blockSender(args, b);
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage((Component)Component.text((String)"[Aurum] Error: Command must be run by player!"));
                return true;
            }
            Player player = (Player)sender;
            if (!player.isOp()) {
                sender.sendMessage((Component)Component.text((String)"[Aurum] Error: You do not have permission to run this command!", (TextColor)NamedTextColor.RED));
                return true;
            }
            if (args[0].equalsIgnoreCase("edit_item")) {
                this.commandHelper.editItemSubCommand(args, player);
                return true;
            }
            if (args[0].equalsIgnoreCase("give")) {
                this.commandHelper.giveSubCommand(args, player);
                return true;
            }
            if (args[0].equalsIgnoreCase("debug")) {
                this.commandHelper.debugSubCommand(args, player);
                return true;
            }
            if (args[0].equalsIgnoreCase("place")) {
                this.commandHelper.placeSubCommand(args, player);
                return true;
            }
            if (args[0].equalsIgnoreCase("remove")) {
                this.commandHelper.removeSubCommand(args, player);
                return true;
            }
            if (args[0].equalsIgnoreCase("toggle")) {
                this.commandHelper.toggleSubCommand(args, player);
                return true;
            }
            if (args[0].equalsIgnoreCase("mob")) {
                this.commandHelper.mobSubCommand(args, player);
                return true;
            }
            if (args[0].equalsIgnoreCase("xp")) {
                this.commandHelper.xpSubCommand(args, player);
                return true;
            }
            if (args[0].equalsIgnoreCase("item")) {
                this.commandHelper.itemSubCommand(args, player);
                return true;
            }
            player.sendMessage((Component)Component.text((String)"[Aurum] Error: Invalid syntax!", (TextColor)NamedTextColor.RED));
            return true;
        }
        if (label.equalsIgnoreCase("suicide")) {
            if (sender instanceof Player) {
                Player player = (Player)sender;
                if (Aurum.getPlugin().getConfig().getBoolean("kill-command")) {
                    player.setHealth(0.0);
                    return true;
                }
                player.sendMessage(((TextComponent)((TextComponent)Component.text((String)"[", (TextColor)NamedTextColor.DARK_RED).append((Component)Component.text((String)"!", (TextColor)NamedTextColor.RED))).append((Component)Component.text((String)"] ", (TextColor)NamedTextColor.DARK_RED))).append((Component)Component.text((String)"You cannot run this command at the moment...", (TextColor)NamedTextColor.GRAY)));
                return true;
            }
            sender.sendMessage((Component)Component.text((String)"[Aurum] This command must be run by a player!"));
            return true;
        }
        if (label.equalsIgnoreCase("open_door") && args.length == 3) {
            if (this.commandHelper.isDouble(args[0]) && this.commandHelper.isDouble(args[1]) && this.commandHelper.isDouble(args[2])) {
                Location loc = null;
                if (sender instanceof BlockCommandSender) {
                    loc = new Location(((BlockCommandSender)sender).getBlock().getWorld(), Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
                }
                if (sender instanceof Player) {
                    loc = new Location(((Player)sender).getWorld(), Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
                    sender.sendMessage(Component.text((String)"[Aurum] ", (TextColor)NamedTextColor.GOLD).append((Component)Component.text((String)"Opening door!", (TextColor)NamedTextColor.YELLOW)));
                }
                if (loc != null) {
                    assert (loc.getWorld() != null);
                    for (Entity e : loc.getWorld().getNearbyEntities(loc, 3.0, 3.0, 3.0)) {
                        Slime s;
                        if (!(e instanceof Slime) || !(s = (Slime)e).getScoreboardTags().contains("aurum_door")) continue;
                        DoorHandler.addDoor(DoorHandler.buildDoor(s));
                        for (Door d : DoorHandler.getActiveDoors()) {
                            if (!d.getRoot().equals((Object)s)) continue;
                            d.addTokens(d.getRequiredTokens());
                        }
                    }
                }
                return true;
            }
            sender.sendMessage((Component)Component.text((String)"Invalid arguments! Usage: /open_door <x> <y> <z>"));
            return true;
        }
        return false;
    }
}

