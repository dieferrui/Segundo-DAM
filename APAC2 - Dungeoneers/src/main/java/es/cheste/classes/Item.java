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
    private String effect;
    private Rarity rarity;
    private int value;
    private boolean consumable;
}

