package es.cheste.enums;

import lombok.Getter;

/**
 * Enumeración que representa diferentes ascendencias.
 */
@Getter
public enum Ancestry {
    DWARF("Dwarf"),
    ELF("Elf"),
    GNOME("Gnome"),
    GOBLIN("Goblin"),
    HALFLING("Halfling"),
    HUMAN("Human"),
    LESHY("Leshy"),
    ORC("Orc");

    private final String ancestryName;

    /**
     * Constructor para la enumeración Ancestry.
     *
     * @param ancestryName El nombre de la ascendencia.
     */
    Ancestry(String ancestryName) {
        this.ancestryName = ancestryName;
    }
}