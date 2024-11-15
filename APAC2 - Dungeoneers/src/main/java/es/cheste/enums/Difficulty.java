package es.cheste.enums;

import lombok.Getter;

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

    Difficulty(String difficultyName) {
        this.difficultyName = difficultyName;
    }

}
