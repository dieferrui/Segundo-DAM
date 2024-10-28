package es.cheste.interfaces;

import es.cheste.enums.ItemType;
import es.cheste.enums.Rarity;
import es.cheste.exceptions.DAOException;
import es.cheste.classes.Item;

import java.util.List;

public interface ItemDAO {
    void insert(Item item) throws DAOException;

    Item obtainByName(String itemName) throws DAOException;
    Item obtainMostExpensive() throws DAOException;

    List<Item> obtainAll() throws DAOException;
    List<Item> obtainAllByType(ItemType type) throws DAOException;
    List<Item> obtainAllByRarity(Rarity rarity) throws DAOException;

    void update(Item item, String oldItem) throws DAOException;
    void delete(String itemName) throws DAOException;
}
