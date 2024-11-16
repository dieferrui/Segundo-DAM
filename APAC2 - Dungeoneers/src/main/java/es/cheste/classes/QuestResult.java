package es.cheste.classes;

import lombok.*;

/**
 * Clase que representa el resultado de una misión.
 */
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

    /**
     * Constructor estándar con verificación automática de éxito (de uso al actualizar resultados de misiones).
     *
     * @param party El grupo que realiza la misión.
     * @param dungeon La mazmorra en la que se realiza la misión.
     * @param report El informe de la misión.
     * @param questId El ID de la misión.
     */
    public QuestResult(Party party, Dungeon dungeon, String report, int questId) {
        this.questId = questId;
        this.PartyId = party.getPartyName();
        this.DungeonId = dungeon.getName();
        this.success = party.getPtPower() >= dungeon.getPointsToBeat();
        this.report = report;
    }

    /**
     * Constructor para generar un informe de resultados simplificado basado en la verificación
     * automática de éxito (de uso al generar resultados de misiones).
     *
     * @param party El grupo que realiza la misión.
     * @param dungeon La mazmorra en la que se realiza la misión.
     * @param questId El ID de la misión.
     */
    public QuestResult(Party party, Dungeon dungeon, int questId) {
        this.questId = questId;
        this.PartyId = party.getPartyName();
        this.DungeonId = dungeon.getName();
        this.success = party.getPtPower() >= dungeon.getPointsToBeat();
        this.report = getSimpleReport();
    }

    /**
     * Función privada que genera un informe simplificado de los resultados.
     *
     * @return Una cadena de informe simplificado de los resultados.
     */
    private String getSimpleReport() {
        return success ? "The party successfully cleared the dungeon." : "The party was forced to leave the dungeon before completion.";
    }

    /**
     * Función que describe el resultado de la misión.
     *
     * @return Una cadena con la descripción del resultado de la misión.
     */
    public String describe() {
        return "\nParty ID: " + PartyId + "\nDungeon ID: " + DungeonId + "\nSuccess: " + (success ? "Yes" : "No")  + "\nReport: " + report;
    }
}