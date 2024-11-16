package es.cheste.classes;

import lombok.*;

/**
 * Clase que representa un grupo de personajes con diversas características.
 */
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

    /**
     * Constructor completo para la clase Party.
     * Calcula automáticamente el poder del grupo.
     *
     * @param partyName Nombre del grupo.
     * @param ptLeader Líder del grupo.
     * @param ptStriker Atacante del grupo.
     * @param ptTank Tanque del grupo.
     * @param ptHealer Sanador del grupo.
     */
    public Party(String partyName, Character ptLeader, Character ptStriker, Character ptTank, Character ptHealer) {
        this.partyName = partyName;
        this.ptLeader = ptLeader;
        this.ptStriker = ptStriker;
        this.ptTank = ptTank;
        this.ptHealer = ptHealer;
        this.ptPower = calculatePartyPower();
    }

    /**
     * Función privada que calcula el poder del grupo.
     *
     * @return El poder del grupo.
     */
    private int calculatePartyPower() {
        int partyPower;

        int leaderPower = ptLeader.getChaMod() + Math.max(ptLeader.getStrMod(), ptLeader.getDexMod());
        int strikerPower = Math.max(ptStriker.getStrMod(), ptStriker.getDexMod()) + Math.max(ptStriker.getDexMod(), ptStriker.getIntMod());
        int tankPower = ptTank.getConMod() + Math.max(ptTank.getStrMod(), ptTank.getChaMod());
        int healerPower = ptHealer.getWisMod() + Math.max(ptHealer.getChaMod(), ptHealer.getIntMod());

        partyPower = leaderPower + strikerPower + tankPower + healerPower;

        return Math.max(partyPower, 0);
    }

    /**
     * Función que describe el grupo.
     *
     * @return Una cadena con la descripción del grupo.
     */
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