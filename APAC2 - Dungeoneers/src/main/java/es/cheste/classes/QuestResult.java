package es.cheste.classes;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor

public class QuestResult {
    private int questId;
    private String PartyId;
    private String DungeonId;
    private boolean success;
    private String report;

    // Standard constructor with automated success check and ID generation
    public QuestResult(Party party, Dungeon dungeon, String report) {
        this.questId = party.hashCode() + dungeon.hashCode() + report.hashCode();
        this.PartyId = party.getPartyName();
        this.DungeonId = dungeon.getName();
        this.success = party.getPtPower() >= dungeon.getPointsToBeat();
        this.report = report;
    }

    // Constructor to generate simplified results report based on automated success check
    public QuestResult(Party party, Dungeon dungeon) {
        this.questId = party.hashCode() + dungeon.hashCode() + report.hashCode();
        this.PartyId = party.getPartyName();
        this.DungeonId = dungeon.getName();
        this.success = party.getPtPower() >= dungeon.getPointsToBeat();
        this.report = getSimpleReport();
    }

    // Private method to generate a simple report based on the success of the party
    private String getSimpleReport() {
        return success ? "The party successfully cleared the dungeon." : "The party was forced to leave the dungeon before completion.";
    }

    // Private method to describe result entry
    public String describe() {
        return "\nParty ID: " + PartyId + "\nDungeon ID: " + DungeonId + "\nSuccess: " + (success ? " Yes" : " No")  + "\nReport: " + report;
    }
}
