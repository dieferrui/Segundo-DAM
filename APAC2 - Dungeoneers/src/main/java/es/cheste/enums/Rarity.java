package es.cheste.enums;

public enum Rarity {
    COMMON("Commnon"),
    UNCOMMON("Uncommon"),
    RARE("Rare"),
    EPIC("Epic"),
    LEGENDARY("Legendary");

    public final String rarity;

    Rarity(String rarity) {
        this.rarity = rarity;
    }

    public String getRarity() {
        return rarity;
    }
}
