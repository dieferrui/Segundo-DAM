package es.cheste.enums;

import lombok.Getter;

/**
 * Enumeración que representa diferentes niveles de dificultad.
 */
@Getter
public enum Difficulty {
    TRIVIAL("E"),
    LOW("D"),
    MODERATE("C"),
    MEDIUM("B"),
    HARD("A"),
    VERY_HARD("S"),
    EXTREME("S+"),
    IMPOSSIBLE("S++");

    public final String difficultyName;

    /**
     * Constructor para la enumeración Difficulty.
     *
     * @param difficultyName El nombre del nivel de dificultad.
     */
    Difficulty(String difficultyName) {
        this.difficultyName = difficultyName;
    }
}