package es.cheste.enums;

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

    CharaClass(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
