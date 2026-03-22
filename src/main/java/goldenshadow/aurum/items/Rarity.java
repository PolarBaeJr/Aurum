/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.adventure.text.Component
 *  net.kyori.adventure.text.format.NamedTextColor
 *  net.kyori.adventure.text.format.TextColor
 */
package goldenshadow.aurum.items;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

public enum Rarity {
    COMMON((Component)Component.text((String)"Common Item", (TextColor)NamedTextColor.DARK_AQUA)),
    RARE((Component)Component.text((String)"Rare Item", (TextColor)NamedTextColor.AQUA)),
    EPIC((Component)Component.text((String)"Epic Item", (TextColor)NamedTextColor.LIGHT_PURPLE)),
    LEGENDARY((Component)Component.text((String)"Legendary Item", (TextColor)NamedTextColor.RED)),
    ARTIFACT((Component)Component.text((String)"Artifact", (TextColor)NamedTextColor.GOLD)),
    ELDRITCH((Component)Component.text((String)"Eldritch Artifact", (TextColor)NamedTextColor.GREEN));

    private final Component name;

    private Rarity(Component name) {
        this.name = name;
    }

    public Component getName() {
        return this.name;
    }

    public static NamedTextColor getTextColor(Rarity rarity) {
        return switch (rarity.ordinal()) {
            default -> throw new IncompatibleClassChangeError();
            case 0 -> NamedTextColor.DARK_AQUA;
            case 1 -> NamedTextColor.AQUA;
            case 2 -> NamedTextColor.LIGHT_PURPLE;
            case 3 -> NamedTextColor.RED;
            case 4 -> NamedTextColor.GOLD;
            case 5 -> NamedTextColor.GREEN;
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

