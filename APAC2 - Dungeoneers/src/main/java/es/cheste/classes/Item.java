package es.cheste.classes;

import es.cheste.enums.ItemType;
import es.cheste.enums.Rarity;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

public class Item {
    private String name;
    private ItemType type;
    private String description;
    private Rarity rarity;
    private int value;
    private boolean consumable;

    // Method to describe item
    public String describe() {
        return "The item \"" + name + "\" is a " + rarity.getRarity() + " " + type.getType() + " that costs " + value + " gold." +
                (consumable ? "It is a consumable item." : "It is not consumable.\n") + this.getDescription();
    }
}

