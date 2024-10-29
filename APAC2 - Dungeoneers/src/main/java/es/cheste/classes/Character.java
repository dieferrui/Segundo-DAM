package es.cheste.classes;

import es.cheste.enums.Ancestry;
import es.cheste.enums.CharaClass;

import java.util.Random;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor

public class Character {
    private static Random random = new Random();

    private String name;
    private CharaClass chClass;
    private Ancestry ancestry;
    private int dexMod;
    private int strMod;
    private int conMod;
    private int intMod;
    private int wisMod;
    private int chaMod;

    // Constructor for full Character (automatically corrects stats to be in range [-1, 4])
    public Character(String name, CharaClass chClass, Ancestry ancestry, int dexMod, int strMod, int conMod,
                     int intMod, int wisMod, int chaMod) {

        // Stat correction procedure
        dexMod = Math.min(4, Math.max(-1, dexMod));
        strMod = Math.min(4, Math.max(-1, strMod));
        conMod = Math.min(4, Math.max(-1, conMod));
        intMod = Math.min(4, Math.max(-1, intMod));
        wisMod = Math.min(4, Math.max(-1, wisMod));
        chaMod = Math.min(4, Math.max(-1, chaMod));

        this.name = name;
        this.chClass = chClass;
        this.ancestry = ancestry;
        this.dexMod = dexMod;
        this.strMod = strMod;
        this.conMod = conMod;
        this.intMod = intMod;
        this.wisMod = wisMod;
        this.chaMod = chaMod;
    }

    // Constructor for no stats Character (assigns random stats in range [-1, 4])
    public Character(String name, CharaClass chClass, Ancestry ancestry) {
        this.name = name;
        this.chClass = chClass;
        this.ancestry = ancestry;
        this.dexMod = random.nextInt(6) - 1;
        this.strMod = random.nextInt(6) - 1;
        this.conMod = random.nextInt(6) - 1;
        this.intMod = random.nextInt(6) - 1;
        this.wisMod = random.nextInt(6) - 1;
        this.chaMod = random.nextInt(6) - 1;
    }

    public String describe() {
        return "Name: " + name + "\n" +
                "Class: " + chClass.getClassName() + "\n" +
                "Ancestry: " + ancestry.getAncestryName() + "\n" +
                "Dexterity: " + dexMod + "\n" +
                "Strength: " + strMod + "\n" +
                "Constitution: " + conMod + "\n" +
                "Intelligence: " + intMod + "\n" +
                "Wisdom: " + wisMod + "\n" +
                "Charisma: " + chaMod;
    }
}
