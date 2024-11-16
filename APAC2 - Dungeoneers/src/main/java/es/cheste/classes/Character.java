package es.cheste.classes;

import es.cheste.enums.Ancestry;
import es.cheste.enums.CharaClass;

import java.util.Random;
import lombok.*;

/**
 * Clase que representa un personaje con diversas características y modificadores de estadísticas.
 */
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

    /**
     * Constructor completo para la clase Character.
     * Corrige automáticamente las estadísticas para que estén en el rango [-1, 4].
     *
     * @param name Nombre del personaje.
     * @param chClass Clase del personaje.
     * @param ancestry Ancestro del personaje.
     * @param dexMod Modificador de destreza.
     * @param strMod Modificador de fuerza.
     * @param conMod Modificador de constitución.
     * @param intMod Modificador de inteligencia.
     * @param wisMod Modificador de sabiduría.
     * @param chaMod Modificador de carisma.
     */
    public Character(String name, CharaClass chClass, Ancestry ancestry, int dexMod, int strMod, int conMod,
                     int intMod, int wisMod, int chaMod) {

        // Procedimiento de corrección de estadísticas
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

    /**
     * Constructor para la clase Character sin estadísticas.
     * Asigna estadísticas aleatorias en el rango [-1, 4].
     *
     * @param name Nombre del personaje.
     * @param chClass Clase del personaje.
     * @param ancestry Ancestro del personaje.
     */
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

    /**
     * Función que describe al personaje.
     *
     * @return Una cadena con la descripción del personaje.
     */
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