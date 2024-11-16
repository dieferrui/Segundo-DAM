package es.cheste.classes;

import es.cheste.enums.Biome;
import es.cheste.enums.Difficulty;

import lombok.*;

/**
 * Clase que representa una mazmorra con diversas características.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Dungeon {
    private String name;
    private Biome biome;
    private Difficulty difficulty;
    private int floors;
    private int pointsToBeat;
    private boolean hasBoss;

    /**
     * Constructor completo para la clase Dungeon.
     * Calcula automáticamente los puntos necesarios para superar la mazmorra según la dificultad.
     *
     * @param name Nombre de la mazmorra.
     * @param biome Bioma en el que se encuentra la mazmorra.
     * @param difficulty Dificultad de la mazmorra.
     * @param floors Número de pisos de la mazmorra.
     * @param hasBoss Indica si la mazmorra tiene un jefe final.
     */
    public Dungeon(String name, Biome biome, Difficulty difficulty, int floors, boolean hasBoss) {
        this.name = name;
        this.biome = biome;
        this.difficulty = difficulty;
        this.floors = floors;
        this.hasBoss = hasBoss;
        this.pointsToBeat = getPointsByDifficulty();
    }

    /**
     * Función privada que calcula los puntos necesarios para superar la mazmorra según la dificultad.
     *
     * @return Los puntos necesarios para superar la mazmorra.
     */
    private int getPointsByDifficulty() {
        return switch (difficulty) {
            case TRIVIAL -> 5;
            case LOW -> 8;
            case MODERATE -> 12;
            case MEDIUM -> 18;
            case HARD -> 22;
            case VERY_HARD -> 24;
            case EXTREME -> 26;
            default -> 28;
        };
    }

    /**
     * Función que describe la mazmorra.
     *
     * @return Una cadena con la descripción de la mazmorra.
     */
    public String describe() {
        return "The dungeon \"" + name + "\" is located in a " + biome + " biome, has " + difficulty + " difficulty" +
                ", has " + floors + " floors and " + (hasBoss ? "has a boss." : "does not have a boss.");
    }
}