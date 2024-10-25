package es.cheste.classes;

import java.util.Objects;

public class Party {
    private String partyName;
    private Character ptLeader;
    private Character ptStriker;
    private Character ptTank;
    private Character ptHealer;
    private int ptPower;

    // Constructor
    public Party(String partyName, Character ptLeader, Character ptStriker, Character ptTank, Character ptHealer) {
        this.partyName = partyName;
        this.ptLeader = ptLeader;
        this.ptStriker = ptStriker;
        this.ptTank = ptTank;
        this.ptHealer = ptHealer;
        this.ptPower = calculatePartyPower();
    }

    public Party() {
        // Empty constructor, not to be utilized by the user
    }

    // Private method to calculate the party's total power based on specified stats
    private int calculatePartyPower() {
        int partyPower = 0;

        int leaderPower = ptLeader.getChaMod() + Math.max(ptLeader.getStrMod(), ptLeader.getDexMod());
        int strikerPower = Math.max(ptStriker.getStrMod(), ptStriker.getDexMod()) + Math.max(ptStriker.getDexMod(), ptStriker.getIntMod());
        int tankPower = ptTank.getConMod() + Math.max(ptTank.getStrMod(), ptTank.getChaMod());
        int healerPower = ptHealer.getWisMod() + Math.max(ptHealer.getChaMod(), ptHealer.getIntMod());

        partyPower = leaderPower + strikerPower + tankPower + healerPower;

        return Math.max(partyPower, 0);
    }

    // Method to describe the party in a human-readable way
    public String describe() {
        return "Party Details:\n" +
                "  Party Name: " + partyName + "\n" +
                "  Leader: " + ptLeader.getName() + "\n" +
                "  Striker: " + ptStriker.getName() + "\n" +
                "  Tank: " + ptTank.getName() + "\n" +
                "  Healer: " + ptHealer.getName() + "\n" +
                "  Power Level: " + ptPower + "\n";
    }

    // Getters
    public String getPartyName() {
        return partyName;
    }

    public Character getPtLeader() {
        return ptLeader;
    }

    public Character getPtStriker() {
        return ptStriker;
    }

    public Character getPtTank() {
        return ptTank;
    }

    public Character getPtHealer() {
        return ptHealer;
    }

    public int getPtPower() {
        return ptPower;
    }

    // Setters
    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public void setPtLeader(Character ptLeader) {
        this.ptLeader = ptLeader;
    }

    public void setPtStriker(Character ptStriker) {
        this.ptStriker = ptStriker;
    }

    public void setPtTank(Character ptTank) {
        this.ptTank = ptTank;
    }

    public void setPtHealer(Character ptHealer) {
        this.ptHealer = ptHealer;
    }

    public void setPtPower(int ptPower) {
        this.ptPower = ptPower;
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Party party = (Party) o;
        return getPtPower() == party.getPtPower() && Objects.equals(getPartyName(), party.getPartyName()) && Objects.equals(getPtLeader(), party.getPtLeader()) && Objects.equals(getPtStriker(), party.getPtStriker()) && Objects.equals(getPtTank(), party.getPtTank()) && Objects.equals(getPtHealer(), party.getPtHealer());
    }

    // Hash
    @Override
    public int hashCode() {
        return Objects.hash(getPartyName(), getPtLeader(), getPtStriker(), getPtTank(), getPtHealer(), getPtPower());
    }

    // To String (debug purposes)
    @Override
    public String toString() {
        return "Party{" +
                "partyName='" + partyName + '\'' +
                ", ptLeader=" + ptLeader +
                ", ptStriker=" + ptStriker +
                ", ptTank=" + ptTank +
                ", ptHealer=" + ptHealer +
                ", ptPower=" + ptPower +
                '}';
    }
}
