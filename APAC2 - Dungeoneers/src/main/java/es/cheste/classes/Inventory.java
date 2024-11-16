package es.cheste.classes;

import lombok.*;

/**
 * Clase que representa un inventario con diversas características.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Inventory {
    private int slotNumber;
    private String characterName;
    private String itemName;
    private int quantity;

    /**
     * Constructor para crear una línea de inventario sin cantidades negativas ni nulas.
     *
     * @param character El personaje al que pertenece el inventario.
     * @param item El objeto del inventario.
     * @param quantity La cantidad del objeto en el inventario.
     */
    public Inventory(Character character, Item item, int quantity) {
        this.characterName = character.getName();
        this.itemName = item.getName();
        this.quantity = Math.max(0, quantity);
    }

    /**
     * Constructor para recuperar una línea de inventario de la base de datos (no para entrada de usuario).
     *
     * @param slotNumber El número de ranura general del inventario.
     * @param characterName El nombre del personaje al que pertenece el inventario.
     * @param itemName El nombre del objeto en el inventario.
     * @param quantity La cantidad del objeto en el inventario.
     */
    public Inventory(int slotNumber, String characterName, String itemName, int quantity) {
        this.slotNumber = slotNumber;
        this.characterName = characterName;
        this.itemName = itemName;
        this.quantity = Math.max(1, quantity);
    }

    /**
     * Función que describe la entrada de inventario.
     *
     * @return Una cadena con la descripción de la entrada de inventario.
     */
    public String describe() {
        return "The character " + characterName + " has " + quantity + " of " + itemName + ".";
    }
}