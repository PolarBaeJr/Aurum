/*
 * Decompiled with CFR 0.152.
 */
package goldenshadow.aurum.items;

public enum ItemType {
    WAND,
    SPEAR,
    SWORD,
    HELMET,
    CHESTPLATE,
    LEGGINGS,
    BOOTS;


    public boolean isArmor() {
        return this == HELMET || this == CHESTPLATE || this == LEGGINGS || this == BOOTS;
    }
}

