package es.cheste.enums;

import lombok.Getter;

@Getter
public enum ItemType {
    ARMOR("Armor"),
    WEAPON("Weapon"),
    ACCESORY("Accesory"),
    POTION("Potion");

    public final String type;

    ItemType(String type) {
        this.type = type;
    }

}
