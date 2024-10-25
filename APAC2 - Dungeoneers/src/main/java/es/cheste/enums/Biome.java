package es.cheste.enums;

public enum Biome {
    DESERT("Desert"),
    FOREST("Forest"),
    MOUNTAIN("Mountain"),
    PLAINS("Plains"),
    SWAMP("Swamp"),
    TUNDRA("Tundra"),
    CAVE("Cave"),
    CITY("City");

    public final String biomeName;

    Biome(String biomeName) {
        this.biomeName = biomeName;
    }

    public String getBiomeName() {
        return biomeName;
    }
}
