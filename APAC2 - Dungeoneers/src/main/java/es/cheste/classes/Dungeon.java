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

    public String describe() {
        return "The dungeon \"" + name + "\" is located in a " + biome + " biome, has " + difficulty + " difficulty" +
                ", has " + floors + " floors and " + (hasBoss ? "has a boss." : "does not have a boss.");
    }
}
