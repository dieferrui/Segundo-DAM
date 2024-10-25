package es.cheste.classes;

import es.cheste.enums.Biome;
import es.cheste.enums.Difficulty;

import java.util.Objects;

public class Dungeon {
    private String name;
    private Biome biome;
    private Difficulty difficulty;
    private int floors;
    private boolean hasBoss;

    public Dungeon(String name, Biome biome, Difficulty difficulty, int floors, boolean hasBoss) {
        this.name = name;
        this.biome = biome;
        this.difficulty = difficulty;
        this.floors = floors;
        this.hasBoss = hasBoss;
    }

    public Dungeon() {
        // Empty constructor, not to be utilized by the user
    }

    // Getters
    public String getName() {
        return name;
    }

    public Biome getBiome() {
        return biome;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getFloors() {
        return floors;
    }

    public boolean hasBoss() {
        return hasBoss;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setBiome(Biome biome) {
        this.biome = biome;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public void setBoss(boolean hasBoss) {
        this.hasBoss = hasBoss;
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dungeon dungeon = (Dungeon) o;
        return getFloors() == dungeon.getFloors() && hasBoss == dungeon.hasBoss &&
                Objects.equals(getName(), dungeon.getName()) && getBiome() == dungeon.getBiome() &&
                getDifficulty() == dungeon.getDifficulty();
    }

    // Hash
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBiome(), getDifficulty(), getFloors(), hasBoss);
    }

    // To String (debug purposes)
    @Override
    public String toString() {
        return "Dungeon{" +
                "name='" + name + '\'' +
                ", biome=" + biome +
                ", difficulty=" + difficulty +
                ", floors=" + floors +
                ", hasBoss=" + hasBoss +
                '}';
    }

    // Method to describe the dungeon better than the standard toString (production purposes)
    public String describe() {
        return "The dungeon " + name + " is located in a " + biome + " biome, has a difficulty of " + difficulty +
                ", has " + floors + " floors and " + (hasBoss ? "has a boss" : "does not have a boss");
    }
}
