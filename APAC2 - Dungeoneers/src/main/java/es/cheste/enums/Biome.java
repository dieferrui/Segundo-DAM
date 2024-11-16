package es.cheste.enums;

import lombok.Getter;

/**
 * Enumeración que representa diferentes biomas.
 */
@Getter
public enum Biome {
    DESERT("Desert"),
    FOREST("Forest"),
    MOUNTAIN("Mountain"),
    PLAINS("Plains"),
    SWAMP("Swamp"),
    TUNDRA("Tundra"),
    CAVE("Cave"),
    CITY("City");

    private final String biomeName;

    /**
     * Constructor para la enumeración Biome.
     *
     * @param biomeName El nombre del bioma.
     */
    Biome(String biomeName) {
        this.biomeName = biomeName;
    }
}