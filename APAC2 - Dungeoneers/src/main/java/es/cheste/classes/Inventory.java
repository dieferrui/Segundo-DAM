package es.cheste.classes;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor

public class Inventory {
    private String characterId;
    private String itemId;
    private int quantity;

    // Constructor to create an Inventory line without negative quantities
    public Inventory(String characterId, String itemId, int quantity) {
        this.characterId = characterId;
        this.itemId = itemId;
        this.quantity = Math.max(0, quantity);
    }
}
