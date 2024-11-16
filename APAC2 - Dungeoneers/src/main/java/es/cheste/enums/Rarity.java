package es.cheste.enums;

import lombok.Getter;

/**
 * Enumeración que representa diferentes niveles de rareza.
 */
@Getter
public enum Rarity {
    COMMON("Commnon"),
    UNCOMMON("Uncommon"),
    RARE("Rare"),
    EPIC("Epic"),
    LEGENDARY("Legendary");

    public final String rarity;

    /**
     * Constructor para la enumeración Rarity.
     *
     * @param rarity El nombre del nivel de rareza.
     */
    Rarity(String rarity) {
        this.rarity = rarity;
    }
}