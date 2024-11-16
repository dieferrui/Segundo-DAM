package es.cheste.implementations;

import es.cheste.enums.ItemType;
import es.cheste.enums.Rarity;
import es.cheste.exceptions.DAOException;
import es.cheste.classes.DBConnection;
import es.cheste.classes.Item;
import es.cheste.interfaces.ItemDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ImpItemDAO implements ItemDAO {

    DBConnection c = new DBConnection();

    // Cadenas finales
    private static final String NOT_OBTAINED = "Item not obtained.";
    private static final String LIST_NOT_OBTAINED = "Item list not obtained.";
    private static final String STATEMENT_ERROR = "Statement error.";

    // Consultas SQL
    private static final String INSERT = "INSERT INTO Item (name, type, description, rarity, value, consumable) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String OBTAIN_BY_NAME = "SELECT * FROM Item WHERE name = ?";
    private static final String OBTAIN_MOST_EXPENSIVE = "SELECT * FROM Item WHERE value = (SELECT MAX(value) FROM Item)";
    private static final String OBTAIN_ALL = "SELECT * FROM Item";
    private static final String OBTAIN_ALL_BY_TYPE = "SELECT * FROM Item WHERE type = ?";
    private static final String OBTAIN_ALL_BY_RARITY = "SELECT * FROM Item WHERE rarity = ?";
    private static final String UPDATE = "UPDATE Item SET name = ?, type = ?, description = ?, rarity = ?, value = ?, consumable = ? WHERE name = ?";
    private static final String DELETE = "DELETE FROM Item WHERE name = ?";

    /**
     * Función para insertar un nuevo ítem en la base de datos.
     *
     * @param item El ítem a insertar.
     * @throws DAOException si ocurre un error durante la inserción.
     */
    @Override
    public void insert(Item item) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(INSERT)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getType().getType());
            ps.setString(3, item.getDescription());
            ps.setString(4, item.getRarity().getRarity());
            ps.setInt(5, item.getValue());
            ps.setBoolean(6, item.isConsumable());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new DAOException("Item insertion failed, no lines affected.", null);
            }

        } catch (SQLException e) {
            throw new DAOException("Item not inserted.", e);
        }
    }

    /**
     * Función para obtener un ítem por su nombre.
     *
     * @param itemName El nombre del ítem.
     * @return El ítem obtenido.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public Item obtainByName(String itemName) throws DAOException {
        Item item = null;

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_BY_NAME)) {
            ps.setString(1, itemName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    item = mapItem(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException(NOT_OBTAINED, e);
        }

        return item;
    }

    /**
     * Función para obtener el ítem más caro.
     *
     * @return El ítem más caro.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public Item obtainMostExpensive() throws DAOException {
        Item item = null;

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_MOST_EXPENSIVE)) {
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    item = mapItem(rs);
                }
            }

        } catch (SQLException e) {
            throw new DAOException(NOT_OBTAINED, e);
        }

        return item;
    }

    /**
     * Función para obtener todos los ítems.
     *
     * @return Una lista de todos los ítems.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public List<Item> obtainAll() throws DAOException {
        List<Item> entries = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Item item = mapItem(rs);
                entries.add(item);
            }

        } catch (SQLException e) {
            throw new DAOException(LIST_NOT_OBTAINED, e);
        }

        return entries;
    }

    /**
     * Función para obtener todos los ítems por su tipo.
     *
     * @param type El tipo de ítem.
     * @return Una lista de ítems del tipo especificado.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public List<Item> obtainAllByType(ItemType type) throws DAOException {
        List<Item> items = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_ALL_BY_TYPE)) {
            ps.setString(1, type.getType());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Item item = mapItem(rs);
                    items.add(item);
                }

            } catch (SQLException e) {
                throw new DAOException(LIST_NOT_OBTAINED, e);
            }

        } catch (SQLException e) {
            throw new DAOException(STATEMENT_ERROR, e);
        }

        return items;
    }

    /**
     * Función para obtener todos los ítems por su rareza.
     *
     * @param rarity La rareza del ítem.
     * @return Una lista de ítems de la rareza especificada.
     * @throws DAOException si ocurre un error durante la obtención.
     */
    @Override
    public List<Item> obtainAllByRarity(Rarity rarity) throws DAOException {
        List<Item> items = new ArrayList<>();

        try (PreparedStatement ps = c.getConnection().prepareStatement(OBTAIN_ALL_BY_RARITY)) {
            ps.setString(1, rarity.getRarity());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Item item = mapItem(rs);
                    items.add(item);
                }

            } catch (SQLException e) {
                throw new DAOException(LIST_NOT_OBTAINED, e);
            }

        } catch (SQLException e) {
            throw new DAOException(STATEMENT_ERROR, e);
        }

        return items;
    }

    /**
     * Función para actualizar un ítem existente.
     *
     * @param item El ítem actualizado.
     * @param oldIt El nombre del ítem a actualizar.
     * @throws DAOException si ocurre un error durante la actualización.
     */
    @Override
    public void update(Item item, String oldIt) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(UPDATE)) {

            ps.setString(1, item.getName());
            ps.setString(2, item.getType().getType());
            ps.setString(3, item.getDescription());
            ps.setString(4, item.getRarity().getRarity());
            ps.setInt(5, item.getValue());
            ps.setBoolean(6, item.isConsumable());
            ps.setString(7, oldIt);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Item update failed, no lines affected.", null);

            }

        } catch (SQLException e) {
            throw new DAOException("Item not updated.", e);

        }
    }

    /**
     * Función para eliminar un ítem por su nombre.
     *
     * @param itemName El nombre del ítem a eliminar.
     * @throws DAOException si ocurre un error durante la eliminación.
     */
    @Override
    public void delete(String itemName) throws DAOException {
        try (PreparedStatement ps = c.getConnection().prepareStatement(DELETE)) {
            ps.setString(1, itemName);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                throw new DAOException("Item deletion failed, no lines affected.", null);
            }
        } catch (SQLException e) {
            throw new DAOException("Item not deleted.", e);
        }
    }

    /**
     * Función para mapear un ResultSet a un objeto Item.
     *
     * @param rs El ResultSet de la consulta.
     * @return El objeto Item mapeado.
     * @throws SQLException si ocurre un error durante el mapeo.
     */
    private Item mapItem(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        String type = rs.getString("type");
        String description = rs.getString("description");
        String rarity = rs.getString("rarity");
        int value = rs.getInt("value");
        boolean consumable = rs.getBoolean("consumable");

        return new Item(name, identifyType(type), description, identifyRarity(rarity), value, consumable);
    }

    /**
     * Función para identificar el tipo de un ítem a partir de una cadena.
     *
     * @param ty La cadena que representa el tipo.
     * @return El tipo identificado.
     */
    private ItemType identifyType(String ty) {
        return switch (ty) {
            case "Armor" -> ItemType.ARMOR;
            case "Weapon" -> ItemType.WEAPON;
            case "Accessory" -> ItemType.ACCESSORY;
            default -> ItemType.POTION;
        };
    }

    /**
     * Función para identificar la rareza de un ítem a partir de una cadena.
     *
     * @param rar La cadena que representa la rareza.
     * @return La rareza identificada.
     */
    private Rarity identifyRarity(String rar) {
        return switch (rar) {
            case "Uncommon" -> Rarity.UNCOMMON;
            case "Rare" -> Rarity.RARE;
            case "Epic" -> Rarity.EPIC;
            case "Legendary" -> Rarity.LEGENDARY;
            default -> Rarity.COMMON;
        };
    }

}