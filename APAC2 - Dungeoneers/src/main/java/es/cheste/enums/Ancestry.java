package es.cheste.enums;

import lombok.Getter;

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

    Ancestry(String ancestryName) {
        this.ancestryName = ancestryName;
    }

}
