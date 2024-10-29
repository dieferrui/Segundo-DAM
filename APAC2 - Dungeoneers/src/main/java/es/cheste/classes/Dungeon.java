package es.cheste.classes;

import es.cheste.enums.Biome;
import es.cheste.enums.Difficulty;

import lombok.*;

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

    public Dungeon(String name, Biome biome, Difficulty difficulty, int floors, boolean hasBoss) {
        this.name = name;
        this.biome = biome;
        this.difficulty = difficulty;
        this.floors = floors;
        this.hasBoss = hasBoss;
        this.pointsToBeat = getPointsByDifficulty();
    }

    private int getPointsByDifficulty() {
        int diffPoints = 0;

        switch (difficulty) {
            case TRIVIAL: diffPoints = 5; break;
            case LOW: diffPoints = 8; break;
            case MODERATE: diffPoints = 12; break;
            case MEDIUM: diffPoints = 18; break;
            case HARD: diffPoints = 22; break;
            case VERY_HARD: diffPoints = 24; break;
            case EXTREME: diffPoints = 26; break;
            default: diffPoints = 28; break;
        }

        return diffPoints;
    }

    public String describe() {
        return "The dungeon \"" + name + "\" is located in a " + biome + " biome, has " + difficulty + " difficulty" +
                ", has " + floors + " floors and " + (hasBoss ? "has a boss." : "does not have a boss.");
    }
}
