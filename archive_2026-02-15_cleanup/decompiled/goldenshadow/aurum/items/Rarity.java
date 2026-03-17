/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 */
package goldenshadow.aurum.items;

import org.bukkit.ChatColor;

public enum Rarity {
    COMMON(ChatColor.DARK_AQUA + "Common Item"),
    RARE(ChatColor.AQUA + "Rare Item"),
    EPIC(ChatColor.LIGHT_PURPLE + "Epic Item"),
    LEGENDARY(ChatColor.RED + "Legendary Item"),
    ARTIFACT(ChatColor.GOLD + "Artifact"),
    ELDRITCH(ChatColor.GREEN + "Eldritch Artifact");

    private final String name;

    private Rarity(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static ChatColor getChatColor(Rarity rarity) {
        return switch (rarity) {
            default -> throw new IncompatibleClassChangeError();
            case COMMON -> ChatColor.DARK_AQUA;
            case RARE -> ChatColor.AQUA;
            case EPIC -> ChatColor.LIGHT_PURPLE;
            case LEGENDARY -> ChatColor.RED;
            case ARTIFACT -> ChatColor.GOLD;
            case ELDRITCH -> ChatColor.GREEN;
        };
    }

    public static boolean isValidEnum(String s) {
        try {
            Rarity.valueOf(s);
            return true;
        }
        catch (IllegalArgumentException e) {
            return false;
        }
    }
}

