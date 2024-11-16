package es.cheste.interfaces;

import es.cheste.enums.ItemType;
import es.cheste.enums.Rarity;
import es.cheste.exceptions.DAOException;
import es.cheste.classes.Item;

import java.util.List;

public interface ItemDAO {
    /**
     * Función para insertar un nuevo ítem en la base de datos.
     *
     * @param item El ítem a insertar.
     * @throws DAOException si ocurre un error durante la inserción.
     */
    void insert(Item item) throws DAOException;

    /**
     * Función para obtener un ítem por su nombre.
     *
     * @param itemName El nombre del ítem.
     * @return El ítem obtenido.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    Item obtainByName(String itemName) throws DAOException;

    /**
     * Función para obtener el ítem más caro.
     *
     * @return El ítem más caro.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    Item obtainMostExpensive() throws DAOException;

    /**
     * Función para obtener todos los ítems.
     *
     * @return Una lista de todos los ítems.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    List<Item> obtainAll() throws DAOException;

    /**
     * Función para obtener todos los ítems por su tipo.
     *
     * @param type El tipo de ítem.
     * @return Una lista de ítems del tipo especificado.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    List<Item> obtainAllByType(ItemType type) throws DAOException;

    /**
     * Función para obtener todos los ítems por su rareza.
     *
     * @param rarity La rareza del ítem.
     * @return Una lista de ítems de la rareza especificada.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    List<Item> obtainAllByRarity(Rarity rarity) throws DAOException;

    /**
     * Función para actualizar un ítem existente.
     *
     * @param item El ítem actualizado.
     * @param oldItem El nombre del ítem a actualizar.
     * @throws DAOException si ocurre un error durante la actualización.
     */
    void update(Item item, String oldItem) throws DAOException;

    /**
     * Función para eliminar un ítem por su nombre.
     *
     * @param itemName El nombre del ítem a eliminar.
     * @throws DAOException si ocurre un error durante la eliminación.
     */
    void delete(String itemName) throws DAOException;
}