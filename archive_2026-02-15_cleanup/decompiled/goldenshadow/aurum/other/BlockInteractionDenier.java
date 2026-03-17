/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.entity.EntityType
 *  org.bukkit.event.player.PlayerInteractEntityEvent
 *  org.bukkit.event.player.PlayerInteractEvent
 */
package goldenshadow.aurum.other;

import goldenshadow.aurum.Aurum;
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockInteractionDenier {
    private static final Material[] disallowedBlocks = new Material[]{Material.TRAPPED_CHEST, Material.HOPPER, Material.DROPPER, Material.DISPENSER, Material.WHITE_BED, Material.GRAY_BED, Material.LIGHT_GRAY_BED, Material.BLACK_BED, Material.BROWN_BED, Material.RED_BED, Material.ORANGE_BED, Material.YELLOW_BED, Material.LIME_BED, Material.GREEN_BED, Material.CYAN_BED, Material.LIGHT_BLUE_BED, Material.BLUE_BED, Material.PURPLE_BED, Material.MAGENTA_BED, Material.PINK_BED, Material.END_PORTAL_FRAME, Material.RESPAWN_ANCHOR, Material.CHEST, Material.BARREL, Material.WHITE_SHULKER_BOX, Material.GRAY_SHULKER_BOX, Material.LIGHT_GRAY_SHULKER_BOX, Material.BLACK_SHULKER_BOX, Material.BROWN_SHULKER_BOX, Material.RED_SHULKER_BOX, Material.ORANGE_SHULKER_BOX, Material.YELLOW_SHULKER_BOX, Material.LIME_SHULKER_BOX, Material.GREEN_SHULKER_BOX, Material.CYAN_SHULKER_BOX, Material.LIGHT_BLUE_SHULKER_BOX, Material.BLUE_SHULKER_BOX, Material.PURPLE_SHULKER_BOX, Material.MAGENTA_SHULKER_BOX, Material.PINK_SHULKER_BOX, Material.CARTOGRAPHY_TABLE, Material.SMITHING_TABLE, Material.CRAFTING_TABLE, Material.STONECUTTER, Material.GRINDSTONE, Material.LOOM, Material.FURNACE, Material.SMOKER, Material.BLAST_FURNACE, Material.CAMPFIRE, Material.SOUL_CAMPFIRE, Material.ANVIL, Material.DAMAGED_ANVIL, Material.CHIPPED_ANVIL, Material.ENCHANTING_TABLE, Material.BREWING_STAND, Material.LODESTONE, Material.BEACON, Material.CAULDRON, Material.SHULKER_BOX, Material.REPEATER, Material.COMPARATOR, Material.DAYLIGHT_DETECTOR};
    private static final Material[] disallowedOpenable = new Material[]{Material.ACACIA_TRAPDOOR, Material.CHERRY_TRAPDOOR, Material.SPRUCE_TRAPDOOR, Material.OAK_TRAPDOOR, Material.BIRCH_TRAPDOOR, Material.DARK_OAK_TRAPDOOR, Material.JUNGLE_TRAPDOOR, Material.MANGROVE_TRAPDOOR, Material.WARPED_TRAPDOOR, Material.CRIMSON_TRAPDOOR, Material.BAMBOO_TRAPDOOR, Material.ACACIA_FENCE_GATE, Material.CHERRY_FENCE_GATE, Material.SPRUCE_FENCE_GATE, Material.OAK_FENCE_GATE, Material.BIRCH_FENCE_GATE, Material.DARK_OAK_FENCE_GATE, Material.JUNGLE_FENCE_GATE, Material.MANGROVE_FENCE_GATE, Material.WARPED_FENCE_GATE, Material.CRIMSON_FENCE_GATE, Material.BAMBOO_FENCE_GATE};
    private static final EntityType[] disallowedEntities = new EntityType[]{EntityType.ITEM_FRAME, EntityType.GLOW_ITEM_FRAME, EntityType.ARMOR_STAND, EntityType.ALLAY};

    public static void interaction(PlayerInteractEvent event) {
        if (!event.getPlayer().isOp()) {
            if (Aurum.getPlugin().getConfig().getBoolean("deny-blocks") && event.getClickedBlock() != null && Arrays.stream(disallowedBlocks).anyMatch(x -> event.getClickedBlock().getType() == x)) {
                event.setCancelled(true);
            }
            if (Aurum.getPlugin().getConfig().getBoolean("deny-openable") && event.getClickedBlock() != null && Arrays.stream(disallowedOpenable).anyMatch(x -> event.getClickedBlock().getType() == x)) {
                event.setCancelled(true);
            }
        }
    }

    public static void entityInteract(PlayerInteractEntityEvent event) {
        if (!event.getPlayer().isOp() && Aurum.getPlugin().getConfig().getBoolean("deny-blocks") && Arrays.stream(disallowedEntities).anyMatch(x -> event.getRightClicked().getType() == x)) {
            event.setCancelled(true);
        }
    }
}

