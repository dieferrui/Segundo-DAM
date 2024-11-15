package es.cheste.enums;

import lombok.Getter;

@Getter
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

}
