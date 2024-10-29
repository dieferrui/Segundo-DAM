package es.cheste.classes;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor

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

    private int calculatePartyPower() {
        int partyPower = 0;

        int leaderPower = ptLeader.getChaMod() + Math.max(ptLeader.getStrMod(), ptLeader.getDexMod());
        int strikerPower = Math.max(ptStriker.getStrMod(), ptStriker.getDexMod()) + Math.max(ptStriker.getDexMod(), ptStriker.getIntMod());
        int tankPower = ptTank.getConMod() + Math.max(ptTank.getStrMod(), ptTank.getChaMod());
        int healerPower = ptHealer.getWisMod() + Math.max(ptHealer.getChaMod(), ptHealer.getIntMod());

        partyPower = leaderPower + strikerPower + tankPower + healerPower;

        return Math.max(partyPower, 0);
    }

    public String describe() {
        return "Party Details:\n" +
                "  Party Name: " + partyName + "\n" +
                "  Leader: " + ptLeader.getName() + "\n" +
                "  Striker: " + ptStriker.getName() + "\n" +
                "  Tank: " + ptTank.getName() + "\n" +
                "  Healer: " + ptHealer.getName() + "\n" +
                "  Power Level: " + ptPower + "\n";
    }
}
