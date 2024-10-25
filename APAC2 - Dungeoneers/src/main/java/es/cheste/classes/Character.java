package es.cheste.classes;

import es.cheste.enums.Ancestry;
import es.cheste.enums.CharaClass;

import java.util.Objects;
import java.util.Random;

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

    public Character() {
        // Empty constructor, not to be utilized by the user
    }

    // Method that describes the character better than the standard toString (production purposes)
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

    // Getters
    public String getName() {
        return name;
    }

    public CharaClass getChClass() {
        return chClass;
    }

    public Ancestry getAncestry() {
        return ancestry;
    }

    public int getDexMod() {
        return dexMod;
    }

    public int getStrMod() {
        return strMod;
    }

    public int getConMod() {
        return conMod;
    }

    public int getIntMod() {
        return intMod;
    }

    public int getWisMod() {
        return wisMod;
    }

    public int getChaMod() {
        return chaMod;
    }

    // Setters
    public static void setRandom(Random random) {
        Character.random = random;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChClass(CharaClass chClass) {
        this.chClass = chClass;
    }

    public void setAncestry(Ancestry ancestry) {
        this.ancestry = ancestry;
    }

    public void setDexMod(int dexMod) {
        this.dexMod = dexMod;
    }

    public void setStrMod(int strMod) {
        this.strMod = strMod;
    }

    public void setConMod(int conMod) {
        this.conMod = conMod;
    }

    public void setIntMod(int intMod) {
        this.intMod = intMod;
    }

    public void setWisMod(int wisMod) {
        this.wisMod = wisMod;
    }

    public void setChaMod(int chaMod) {
        this.chaMod = chaMod;
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return getDexMod() == character.getDexMod() && getStrMod() == character.getStrMod() &&
                getConMod() == character.getConMod() && getIntMod() == character.getIntMod() &&
                getWisMod() == character.getWisMod() && getChaMod() == character.getChaMod() &&
                Objects.equals(getName(), character.getName()) && getChClass() == character.getChClass() &&
                getAncestry() == character.getAncestry();
    }

    // Hash
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getChClass(), getAncestry(), getDexMod(),
                getStrMod(), getConMod(), getIntMod(), getWisMod(), getChaMod());
    }

    // To String (debug purposes)
    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", chClass=" + chClass +
                ", ancestry=" + ancestry +
                ", dexMod=" + dexMod +
                ", strMod=" + strMod +
                ", conMod=" + conMod +
                ", intMod=" + intMod +
                ", wisMod=" + wisMod +
                ", chaMod=" + chaMod +
                '}';
    }
}
