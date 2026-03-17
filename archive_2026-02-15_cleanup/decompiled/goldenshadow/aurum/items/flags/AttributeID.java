/*
 * Decompiled with CFR 0.152.
 */
package goldenshadow.aurum.items.flags;

public enum AttributeID {
    EXPLODING("Exploding"),
    MAIN_ATTACK_DAMAGE("Main Attack Damage"),
    HEALTH("Health"),
    JUMP_HEIGHT("Jump Height"),
    FREEZING("Freezing"),
    LIFE_STEAL("Life Steal"),
    REBOUND("Rebound"),
    COMBUSTION("Combustion"),
    REFLECTION("Reflection"),
    WALK_SPEED("Walk Speed"),
    DODGE("Dodge"),
    CRITICAL_STRIKE("Critical Strike"),
    LIGHT_FOOT("Light Foot"),
    RESISTANCE("Resistance"),
    RUNE_DAMAGE("Rune Damage"),
    RUNE_AFFINITY("Rune Affinity"),
    ATTACK_SPEED("Attack Speed"),
    XP_BONUS("XP Bonus"),
    FISH_BAIT("Fish Bait");

    private final String name;

    private AttributeID(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static boolean isValidEnum(String s) {
        try {
            AttributeID.valueOf(s);
            return true;
        }
        catch (IllegalArgumentException e) {
            return false;
        }
    }
}

