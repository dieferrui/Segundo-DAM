package es.cheste.interfaces;

import es.cheste.exceptions.DAOException;
import es.cheste.classes.Inventory;

import java.util.List;

public interface InventoryDAO {
    /**
     * Función para insertar un nuevo inventario en la base de datos.
     *
     * @param inventory El inventario a insertar.
     * @throws DAOException si ocurre un error durante la inserción.
     */
    void insert(Inventory inventory) throws DAOException;

    /**
     * Función para obtener un inventario por su número de ranura.
     *
     * @param slotNumber El número de ranura del inventario.
     * @return El inventario obtenido.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    Inventory obtainBySlotNumber(int slotNumber) throws DAOException;

    /**
     * Función para obtener el inventario de un personaje por su nombre.
     *
     * @param charName El nombre del personaje.
     * @return Una lista de inventarios del personaje especificado.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    List<Inventory> obtainCharacterInventory(String charName) throws DAOException;

    /**
     * Función para obtener el equipo de un personaje por su nombre.
     *
     * @param charName El nombre del personaje.
     * @return Una lista de equipos del personaje especificado.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    List<Inventory> obtainCharacterEquipment(String charName) throws DAOException;

    /**
     * Función para obtener los consumibles de un personaje por su nombre.
     *
     * @param charName El nombre del personaje.
     * @return Una lista de consumibles del personaje especificado.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    List<Inventory> obtainCharacterConsumables(String charName) throws DAOException;

    /**
     * Función para actualizar un inventario existente.
     *
     * @param slotNumber El número de ranura del inventario a actualizar.
     * @param inventory El inventario actualizado.
     * @throws DAOException si ocurre un error durante la actualización.
     */
    void update(int slotNumber, Inventory inventory) throws DAOException;

    /**
     * Función para eliminar un inventario por su número de ranura.
     *
     * @param slotNumber El número de ranura del inventario a eliminar.
     * @throws DAOException si ocurre un error durante la eliminación.
     */
    void delete(int slotNumber) throws DAOException;
}