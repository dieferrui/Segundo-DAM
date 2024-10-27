package es.cheste.interfaces;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.Inventory;

import java.util.List;

public interface InventoryDAO {
    void insert(Inventory inventory) throws DAOException;

    Inventory obtainBySlotNumber(int slotNumber) throws DAOException;

    List<Inventory> obtainCharacterInventory(String charName) throws DAOException;
    List<Inventory> obtainCharacterEquipment(String charName) throws DAOException;
    List<Inventory> obtainCharacterConsumables(String charName) throws DAOException;

    void update(int slotNumber, Inventory inventory) throws DAOException;
    void delete(int slotNumber) throws DAOException;
}
