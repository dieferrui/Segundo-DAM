package es.cheste.enums;

public enum ItemType {
    ARMOR("Armor"),
    WEAPON("Weapon"),
    ACCESORY("Accesory"),
    POTION("Potion");

    public final String type;

    ItemType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
