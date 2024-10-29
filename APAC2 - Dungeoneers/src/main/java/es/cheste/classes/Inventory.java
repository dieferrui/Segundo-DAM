package es.cheste.classes;

import lombok.*;

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

    // Constructor to create an Inventory line without negative quantities
    public Inventory(Character character, Item item, int quantity) {
        this.characterName = character.getName();
        this.itemName = item.getName();
        this.quantity = Math.max(0, quantity);
    }

    // Constructor to retrieve an Inventory line from the database (not for user input)
    public Inventory(int slotNumber, String characterName, String itemName, int quantity) {
        this.slotNumber = slotNumber;
        this.characterName = characterName;
        this.itemName = itemName;
        this.quantity = Math.max(0, quantity);
    }

    public String describe() {
        return "The character " + characterName + " has " + quantity + " of " + itemName + ".";
    }
}
