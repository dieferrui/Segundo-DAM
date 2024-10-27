package es.cheste.classes;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor

public class Inventory {
    private String characterName;
    private String itemName;
    private int quantity;

    // Constructor to create an Inventory line without negative quantities
    public Inventory(Character character, Item item, int quantity) {
        this.characterName = character.getName();
        this.itemName = item.getName();
        this.quantity = Math.max(0, quantity);
    }

    // Method to describe inventory line
    public String describe() {
        return "The character " + characterName + " has " + quantity + " of " + itemName + ".";
    }
}
