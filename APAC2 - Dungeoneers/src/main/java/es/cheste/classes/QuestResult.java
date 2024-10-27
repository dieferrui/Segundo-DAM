package es.cheste.classes;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor

public class QuestResult {
    private String PartyId;
    private String DungeonId;
    private boolean success;
    private String report;

    // Standard constructor with automated success check
    public QuestResult(Party party, Dungeon dungeon, String report) {
        this.PartyId = party.getPartyName();
        this.DungeonId = dungeon.getName();
        this.success = party.getPtPower() >= dungeon.getPointsToBeat();
        this.report = report;
    }

    // Constructor to generate simplified results report based on automated success check
    public QuestResult(Party party, Dungeon dungeon) {
        this.PartyId = party.getPartyName();
        this.DungeonId = dungeon.getName();
        this.success = party.getPtPower() >= dungeon.getPointsToBeat();
        this.report = getSimpleReport();
    }

    // Private method to generate a simple report based on the success of the party
    private String getSimpleReport() {
        return success ? "The party successfully cleared the dungeon." : "The party was forced to leave the dungeon before completion.";
    }
}
