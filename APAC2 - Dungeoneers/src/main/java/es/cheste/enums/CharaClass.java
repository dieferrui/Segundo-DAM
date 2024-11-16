package es.cheste.enums;

import lombok.Getter;

/**
 * Enumeración que representa diferentes clases de personajes.
 */
@Getter
public enum CharaClass {
    ALCHEMIST("Alchemist"),
    BARBARIAN("Barbarian"),
    BARD("Bard"),
    CHAMPION("Champion"),
    CLERIC("Cleric"),
    DRUID("Druid"),
    FIGHTER("Fighter"),
    MONK("Monk"),
    RANGER("Ranger"),
    ROGUE("Rogue"),
    SORCERER("Sorcerer"),
    WITCH("Witch"),
    WIZARD("Wizard");

    private final String className;

    /**
     * Constructor para la enumeración CharaClass.
     *
     * @param className El nombre de la clase de personaje.
     */
    CharaClass(String className) {
        this.className = className;
    }
}