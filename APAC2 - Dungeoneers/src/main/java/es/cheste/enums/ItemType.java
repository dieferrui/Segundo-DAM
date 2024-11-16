package es.cheste.enums;

import lombok.Getter;

/**
 * Enumeración que representa diferentes tipos de ítems.
 */
@Getter
public enum ItemType {
    ARMOR("Armor"),
    WEAPON("Weapon"),
    ACCESSORY("Accessory"),
    POTION("Potion");

    public final String type;

    /**
     * Constructor para la enumeración ItemType.
     *
     * @param type El nombre del tipo de ítem.
     */
    ItemType(String type) {
        this.type = type;
    }
}