/*
 * Decompiled with CFR 0.152.
 */
package goldenshadow.aurum.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public enum Rarity {
    COMMON(Component.text("Common Item", NamedTextColor.DARK_AQUA)),
    RARE(Component.text("Rare Item", NamedTextColor.AQUA)),
    EPIC(Component.text("Epic Item", NamedTextColor.LIGHT_PURPLE)),
    LEGENDARY(Component.text("Legendary Item", NamedTextColor.RED)),
    ARTIFACT(Component.text("Artifact", NamedTextColor.GOLD)),
    ELDRITCH(Component.text("Eldritch Artifact", NamedTextColor.GREEN));

    private final Component name;

    private Rarity(Component name) {
        this.name = name;
    }

    public Component getName() {
        return this.name;
    }

    public static NamedTextColor getTextColor(Rarity rarity) {
        return switch (rarity) {
            default -> throw new IncompatibleClassChangeError();
            case COMMON -> NamedTextColor.DARK_AQUA;
            case RARE -> NamedTextColor.AQUA;
            case EPIC -> NamedTextColor.LIGHT_PURPLE;
            case LEGENDARY -> NamedTextColor.RED;
            case ARTIFACT -> NamedTextColor.GOLD;
            case ELDRITCH -> NamedTextColor.GREEN;
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

