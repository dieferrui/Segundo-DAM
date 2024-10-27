package es.cheste.interfaces;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.Item;

import java.util.List;

public interface ItemDAO {
    void insert(Item item) throws DAOException;

    Item obtainByName(String itemName) throws DAOException;
    Item obtainMostExpensive() throws DAOException;

    List<Item> obtainAll() throws DAOException;
    List<Item> obtainAllByType() throws DAOException;
    List<Item> obtainAllByRarity() throws DAOException;

    void update(Item item) throws DAOException;
    void delete(String itemName) throws DAOException;
}
